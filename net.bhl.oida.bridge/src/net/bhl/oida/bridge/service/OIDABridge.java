/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package net.bhl.oida.bridge.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.bhl.oida.bridge.Activator;
import net.bhl.oida.bridge.model.changehandler.IModelChangeHandler;
import net.bhl.oida.bridge.model.changehandler.IModelChangeHandlerFactory;
import net.bhl.oida.bridge.model.mapping.ClassEqualsMapping;
import net.bhl.oida.bridge.model.mapping.InstanceOfMapping;
import net.bhl.oida.bridge.model.mapping.Mapping;
import net.bhl.oida.bridge.model.mapping.MappingFactory;
import net.bhl.oida.bridge.model.mapping.MappingSet;
import net.bhl.oida.bridge.model.mapping.ObjectPropertyEqualsMapping;
import net.bhl.oida.bridge.model.mapping.provider.MappingItemProviderAdapterFactory;
import net.bhl.oida.bridge.model.ontology.IMetaModelOntologyProvider;
import net.bhl.oida.bridge.model.ontology.OIDAModelBaseOntology;
import net.bhl.oida.bridge.model.recommendation.Recommendation;
import net.bhl.oida.bridge.model.recommendation.RecommendationSet;
import net.bhl.oida.bridge.model.recommendation.provider.RecommendationItemProviderAdapterFactory;
import net.bhl.oida.bridge.model.strategy.IRenamerStrategy;
import net.bhl.oida.bridge.model.strategy.IStructuringStrategy;
import net.bhl.oida.bridge.recommender.RecommenderSystem;
import net.bhl.oida.ontology.manager.IOntologyManager;
import net.bhl.oida.ontology.manager.OntologyManagerException;
import net.bhl.oida.ontology.model.ontologymgr.LocalOntologyMetaInfo;
import net.bhl.oida.ontology.model.ontologymgr.SystemOntologyMetaInfo;
import net.bhl.oida.ontology.model.owlontology.AOntologyItem;
import net.bhl.oida.ontology.model.owlontology.Ontology;
import net.bhl.oida.ontology.model.owlontology.OntologyClass;
import net.bhl.oida.ontology.model.owlontology.OntologyClassEquivalence;
import net.bhl.oida.ontology.model.owlontology.OntologyIndividual;
import net.bhl.oida.ontology.model.owlontology.OntologyNamespace;
import net.bhl.oida.ontology.model.owlontology.OntologyObjectProperty;
import net.bhl.oida.ontology.model.owlontology.OntologyObjectPropertyEquivalence;
import net.bhl.oida.ontology.predefined.Mereology;
import net.bhl.oida.ontology.service.OIDAOntologyService;
import net.bhl.oida.util.ExtensionPointUtil;
import net.bhl.oida.util.OIDAUtil;
import net.bhl.oida.util.constants.FileConstants;
import net.bhl.oida.util.constants.OntologyConstants;
import net.bhl.oida.util.constants.StringConstants;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-03-03
 *
 */
@Creatable
@Singleton
public final class OIDABridge {
    protected static Logger LOGGER = LoggerFactory.getLogger(OIDABridge.class);

    private boolean extensionPointsReady = false;
    private boolean modelBaseOntologyReady = false;
    private boolean metaModelOntologyReady = false;

    private IRenamerStrategy renamerStrategy;
    private IStructuringStrategy structuringStrategy;

    private IModelChangeHandler metaModelOntologyHandler;

    private Map<Object, IModelChangeHandler> modelHandlerMap = new HashMap<Object, IModelChangeHandler>();

    private Resource metaModelClassMappingsResource;
    private Resource metaModelObjectPropertyMappingsResource;
    private Resource modelMappingsResource;

    private IModelChangeHandlerFactory changeHandlerFactory;

    private OIDAOntologyService oidaOntologyService;

    @Inject
    public OIDABridge(OIDAOntologyService oidaOntologyService) {
	LOGGER.info("Initializing OIDA Bridge Service...");

	this.oidaOntologyService = oidaOntologyService;
	modelHandlerMap.clear();

	ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(new MappingItemProviderAdapterFactory());
	composedAdapterFactory.addAdapterFactory(new RecommendationItemProviderAdapterFactory());
	composedAdapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());

	AdapterFactoryEditingDomain editingDomain = new AdapterFactoryEditingDomain(composedAdapterFactory, new BasicCommandStack());

	metaModelClassMappingsResource = editingDomain.createResource("http://de.oida/bridge/classmappings");
	metaModelClassMappingsResource.getContents().add(MappingFactory.eINSTANCE.createMappingSet());

	metaModelObjectPropertyMappingsResource = editingDomain.createResource("http://de.oida/bridge/objectpropertymappings");
	metaModelObjectPropertyMappingsResource.getContents().add(MappingFactory.eINSTANCE.createMappingSet());

	modelMappingsResource = editingDomain.createResource("http://de.oida/bridge/individualmappings");
	modelMappingsResource.getContents().add(MappingFactory.eINSTANCE.createMappingSet());

	RecommenderSystem.getInstance().initializeRecommenderSystem(this);

	if (!extensionPointsReady)
	    extensionPointsReady = tryInitExtensions();

	tryInitMereology();

	if (!modelBaseOntologyReady)
	    modelBaseOntologyReady = tryInitModelBaseOntology();

	if (!metaModelOntologyReady)
	    metaModelOntologyReady = tryInitMetaModelOntology();

	LOGGER.info("OIDA Bridge Service initialized.");
    }

    private boolean tryInitExtensions() {
	extensionPointsReady = true;

	LOGGER.info("Step 1/7: Evaluating Model Change Handler Factory extensions.");
	try {
	    changeHandlerFactory = ExtensionPointUtil.loadSingleExtension(Activator.getExtensionRegistry(), IModelChangeHandlerFactory.class, Activator.OIDA_EXTENSIONPOINT_ID_MODEL_CHANGEHANDLER);

	    if (changeHandlerFactory != null)
		LOGGER.info("Model Change Handler Factory set: '" + changeHandlerFactory.getClass().getName() + "'.");
	    else {
		LOGGER.error("No Model Change Handler Factory found.");
		extensionPointsReady = false;
	    }
	} catch (CoreException e) {
	    LOGGER.error("Error while evaluating Model Change Handler Factory extension point.", e);
	    extensionPointsReady = false;
	}

	LOGGER.info("Step 2/7: Evaluating model change handler renamer strategy extensions.");
	try {
	    renamerStrategy = ExtensionPointUtil.loadSingleExtension(Activator.getExtensionRegistry(), IRenamerStrategy.class, Activator.OIDA_MODEL_RENAMERSTRATEGY);

	    if (renamerStrategy != null)
		LOGGER.info("Renamer strategy set: '" + renamerStrategy.getClass().getName() + "'.");
	    else {
		LOGGER.error("No renamer strategy found.");
		extensionPointsReady = false;
	    }
	} catch (CoreException e) {
	    LOGGER.error("Error while evaluating renamer strategy extension point.", e);
	    extensionPointsReady = false;
	}

	LOGGER.info("Step 3/7: Evaluating model change handler structuring strategy extensions.");
	try {
	    structuringStrategy = ExtensionPointUtil.loadSingleExtension(Activator.getExtensionRegistry(), IStructuringStrategy.class, Activator.OIDA_MODEL_STRUCTURINGSTRATEGY);

	    if (structuringStrategy != null)
		LOGGER.info("Structuring strategy set: '" + structuringStrategy.getClass().getName() + "'.");
	    else {
		LOGGER.error("No structuring strategy found.");
		extensionPointsReady = false;
	    }
	} catch (CoreException e) {
	    LOGGER.error("Error while evaluating structuring strategy extension point.", e);
	    extensionPointsReady = false;
	}

	return extensionPointsReady;
    }

    private boolean tryInitMereology() {
	LOGGER.info("Step 6/7: Loading Mereology.");
	SystemOntologyMetaInfo mereologyOntologyMetaInfo = OIDAUtil.getSystemOntologyMetaInfo(OIDAUtil.getOIDAWorkPath(), Mereology.MEREOLOGY_FILENAME);

	Optional<IOntologyManager> optOntologyManager;
	if (oidaOntologyService.checkLocalOntologyExistance(mereologyOntologyMetaInfo))
	    optOntologyManager = oidaOntologyService.getLocalOntologyManager(mereologyOntologyMetaInfo);
	else
	    optOntologyManager = oidaOntologyService.createLocalOntology(mereologyOntologyMetaInfo, Mereology.MEREOLOGY_IRI);

	if (optOntologyManager.isPresent()) {
	    try {
		Mereology.getInstance().loadOrInitializeOntology(optOntologyManager.get());
		oidaOntologyService.saveLibraryResource();
		oidaOntologyService.getGlobalIRIToLocalIRIMappings().put(Mereology.MEREOLOGY_IRI, (LocalOntologyMetaInfo)Mereology.getInstance().getOntologyManager().getMetaInfo());

		LOGGER.info("Mereology loaded.");
		return true;
	    } catch (OntologyManagerException e) {
		LOGGER.error("Error while loading Mereology.", e);
		return false;
	    }
	} else
	    return false;
    }

    private boolean tryInitModelBaseOntology() {
	LOGGER.info("Step 6/7: Loading OIDA model base ontology.");
	try {
	    SystemOntologyMetaInfo systemOntologyMetaInfo = OIDAUtil.getSystemOntologyMetaInfo(OIDAUtil.getOIDAWorkPath(), FileConstants.OIDAMODELONTOLOGY_FILENAME);

	    Optional<IOntologyManager> optOntologyManager;
	    if (oidaOntologyService.checkLocalOntologyExistance(systemOntologyMetaInfo))
		optOntologyManager = oidaOntologyService.getLocalOntologyManager(systemOntologyMetaInfo);
	    else
		optOntologyManager = oidaOntologyService.createLocalOntology(systemOntologyMetaInfo, OIDAModelBaseOntology.OIDA_MODELONTOLOGY_IRI);

	    if (optOntologyManager.isPresent()) {
		OIDAModelBaseOntology.getInstance().loadOrInitializeOntology(optOntologyManager.get());
		OIDAModelBaseOntology.getInstance().getOntologyManager().addImportDeclaration(Mereology.getInstance().getOntologyManager().getOntology());
		OIDAModelBaseOntology.getInstance().getOntologyManager().saveLocalOntology();
		oidaOntologyService.saveLibraryResource();
		oidaOntologyService.getGlobalIRIToLocalIRIMappings().put(OIDAModelBaseOntology.OIDA_MODELONTOLOGY_IRI,
			(LocalOntologyMetaInfo)OIDAModelBaseOntology.getInstance().getOntologyManager().getMetaInfo());

		LOGGER.info("Model Base Ontology loaded.");
		return true;
	    } else {
		LOGGER.error("Error while loading the Model Base Ontology.");
		return false;
	    }
	} catch (OntologyManagerException e) {
	    LOGGER.error("Error while loading the Model Base Ontology.", e);
	    return false;
	}
    }

    private boolean tryInitMetaModelOntology() {
	LOGGER.info("Step 7/7: Initializing the meta model ontology for the registered meta model.");

	if (!oidaOntologyService.getReferenceOntologyManager().isPresent()) {
	    LOGGER.warn("No reference ontology set. Meta model ontology can not be created.");
	    return false;
	}

	SystemOntologyMetaInfo metaModelOntologyMetaInfo = OIDAUtil
		.getSystemOntologyMetaInfo(new File(OIDAUtil.getOIDAMetaModelOntologyPath(), renamerStrategy.getMetaModelName() + FileConstants.OWL_FILE_POSTFIX));

	Optional<IOntologyManager> optMetaModelOntologyManager;
	if (oidaOntologyService.checkLocalOntologyExistance(metaModelOntologyMetaInfo))
	    optMetaModelOntologyManager = oidaOntologyService.getLocalOntologyManager(metaModelOntologyMetaInfo);
	else
	    optMetaModelOntologyManager = oidaOntologyService.createLocalOntology(metaModelOntologyMetaInfo, OntologyConstants.OIDA_METAMODEL_ONTOLOGY_BASE_IRI + renamerStrategy.getMetaModelName());

	if (optMetaModelOntologyManager.isPresent()) {
	    IMetaModelOntologyProvider provider;
	    try {
		provider = (IMetaModelOntologyProvider)ExtensionPointUtil.loadSingleExtension(Activator.getExtensionRegistry(), IMetaModelOntologyProvider.class,
			Activator.OIDA_METAMODELONTOLOGY_PROVIDER_EXTENSIONPOINT_ID);

		if (provider != null)
		    metaModelOntologyHandler = provider.createMetaModelOntology(renamerStrategy, structuringStrategy, optMetaModelOntologyManager.get(),
			    oidaOntologyService.getReferenceOntologyManager().get().getOntology());

		metaModelOntologyHandler.getModelOntologyManager().refreshOntologyRepresentation(true);
		extractMappings(metaModelOntologyHandler.getModelOntologyManager());
		metaModelOntologyHandler.getModelOntologyManager().saveLocalOntology();

		oidaOntologyService.saveLibraryResource();
		oidaOntologyService.getGlobalIRIToLocalIRIMappings().put(metaModelOntologyMetaInfo.getIri(), metaModelOntologyMetaInfo);

		RecommenderSystem.getInstance().initializeSecondaryRecommenders(metaModelOntologyHandler.getModelOntologyManager().getOntology(),
			oidaOntologyService.getReferenceOntologyManager().get().getOntology());

		LOGGER.info("Meta model ontology created: '" + metaModelOntologyHandler.getModelOntologyManager().getOntology().getIri() + "'.");
		return true;
	    } catch (CoreException e) {
		LOGGER.error("Error while creating meta model ontology.", e);
	    } catch (OntologyManagerException e) {
		LOGGER.error("Error while saving meta model ontology.", e);
	    }
	} else {
	    LOGGER.error("Error while creating the Meta Model Ontology for '" + structuringStrategy.getMetaModelInformationObject().toString() + "'.");
	}

	return false;
    }

    /**
     * Starts the OIDA model ontology management for a passed model object.
     * 
     * @param modelObject
     *            The model object, which should be observed and mapped to the
     *            current reference ontology.
     * @param modelOntologyDirectory
     *            A directory, where OIDA can store the model ontology file.
     * @param modelObjectId
     *            An id of the model object, which is used for the file name
     *            generation of the model ontology file.
     * @throws OIDABridgeException
     *             If 1) the model ontology directory is invalid, 2) no renamer
     *             strategy has been registered, or 3) no reference ontology is
     *             set at the moment. If the exception is thrown, the model
     *             element is not observed.
     */
    public void invokeModelObservation(final Object modelObject, final File modelOntologyDirectory, final String modelObjectId) throws OIDABridgeException {
	if (renamerStrategy == null)
	    throw new OIDABridgeException("No renamer strategy found. Model won't be observed.");

	if (structuringStrategy == null)
	    throw new OIDABridgeException("No structuring strategy found. Model won't be observed.");

	if (metaModelOntologyHandler == null)
	    throw new OIDABridgeException("No meta model ontology found. Model won't be observed.");

	if (modelOntologyDirectory == null)
	    throw new OIDABridgeException("No directory for a model ontology has been passed. Model won't be observed.");

	if (!modelOntologyDirectory.exists() && !modelOntologyDirectory.mkdirs())
	    throw new OIDABridgeException("The directory for the model ontology doesn't exist/could not be created ['" + modelOntologyDirectory.toString() + "']. Model won't be observed.");

	File modelOntologyFile = new File(modelOntologyDirectory, generateModelOntologyFileName(modelObjectId));
	SystemOntologyMetaInfo ontologyMetaInfo = OIDAUtil.getSystemOntologyMetaInfo(modelOntologyFile);

	Optional<IOntologyManager> optModelOntologyMgr;
	if (oidaOntologyService.checkLocalOntologyExistance(ontologyMetaInfo))
	    optModelOntologyMgr = oidaOntologyService.getLocalOntologyManager(ontologyMetaInfo);
	else
	    optModelOntologyMgr = oidaOntologyService.createLocalOntology(ontologyMetaInfo, OntologyConstants.OIDA_MODEL_ONTOLOGY_BASE_IRI + modelObjectId);

	if (optModelOntologyMgr.isPresent()) {
	    IModelChangeHandler changeHandler = changeHandlerFactory.getChangeHandler();

	    extractMappings(optModelOntologyMgr.get());
	    changeHandler.setSuperModelChangeHandler(getMetaModelHandler());
	    changeHandler.startChangeTracking(renamerStrategy, structuringStrategy, getMetaModelHandler().getModelOntologyManager(), optModelOntologyMgr.get(), modelObject);
	    modelHandlerMap.put(modelObject, changeHandler);

	    RecommenderSystem.getInstance().initializePrimaryRecommenders(changeHandler.getModelOntologyManager().getOntology(), oidaOntologyService.getReferenceOntologyManager().get().getOntology());
	} else
	    LOGGER.error("Model observation could not be startet: Model ontology not found.");
    }

    /**
     * Saves the model ontology in the current state, if the model object is
     * observed.
     * 
     * @param modelObject
     *            The model object, which's ontology should be saved.
     */
    public void saveModelOntology(final Object modelObject) {
	try {
	    if (modelHandlerMap.containsKey(modelObject))
		modelHandlerMap.get(modelObject).getModelOntologyManager().saveLocalOntology();
	} catch (OntologyManagerException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Stops the model ontology management for the passed model object.
     * 
     * @param modelObject
     *            The model object, which should not be observed any more.
     */
    public void stopModelObservation(final Object modelObject) {
	modelHandlerMap.get(modelObject).closeModelOntology();
	modelHandlerMap.remove(modelObject);
    }

    /**
     * Can be called by external editors or other parts, if the selected model
     * element changes. This triggers the bridge to find new mapping
     * recommendations.
     * 
     * @param modelObject
     *            The observed model.
     * @param firstSelectedElement
     *            The first selected model element.
     */
    public void reportModelSelectionChanged(Object modelObject, Object firstSelectedElement) {
	if (modelObject == null || firstSelectedElement == null)
	    return;

	Optional<AOntologyItem> optOntEntity = modelHandlerMap.get(modelObject).getOntologyEntityForModelElement(firstSelectedElement);
	if (optOntEntity.isPresent())
	    RecommenderSystem.getInstance().findPrimaryRecommendations(modelObject, (OntologyIndividual)optOntEntity.get());
	else
	    LOGGER.warn("No Ontology Entity found for '" + firstSelectedElement.toString() + "'.");
    }

    public void reportMetaModelSelectionChanged(Object selectedObject) {
	if (selectedObject == null)
	    return;

	RecommenderSystem.getInstance().findSecondaryRecommendations(selectedObject);
    }

    private String generateModelOntologyFileName(String modelObjectId) throws OIDABridgeException {
	if (oidaOntologyService.getLibrary().getReferenceOntology() == null)
	    throw new OIDABridgeException("No reference ontology set. Model won't be observed.");

	// Dirty: Reference Ontology may be an Online-Ontology! (Cast to local
	// ontology inadmissible)
	return modelObjectId + StringConstants.UNDERSCORE + OIDAUtil.extractFileName(((LocalOntologyMetaInfo)oidaOntologyService.getLibrary().getReferenceOntology()).getLocalPath())
		+ FileConstants.OWL_FILE_POSTFIX;
    }

    private void extractMappings(IOntologyManager modelOntologyManager) {
	// class equivalences
	for (OntologyClassEquivalence equ : modelOntologyManager.getOntology().getLocalOntology().getClassEquivalences()) {
	    if (!equ.getClass1().getPrefix().equals(equ.getClass2().getPrefix())) {
		Optional<OntologyClass> optRefClass = oidaOntologyService.getReferenceOntologyManager().get().getClass(equ.getClass2().getIri());

		if (optRefClass.isPresent())
		    createClassMapping(equ, equ.getClass1(), optRefClass.get());
	    }
	}

	// object property equivalences
	for (OntologyObjectPropertyEquivalence equ : modelOntologyManager.getOntology().getLocalOntology().getObjectPropertyEquivalences()) {
	    if (!equ.getObjectProperty1().getPrefix().equals(equ.getObjectProperty2().getPrefix())) {
		Optional<OntologyObjectProperty> optRefObjProp = oidaOntologyService.getReferenceOntologyManager().get().getObjectProperty(equ.getObjectProperty2().getIri());

		if (optRefObjProp.isPresent())
		    createObjectPropertyMapping(equ, equ.getObjectProperty1(), optRefObjProp.get());
	    }
	}

	for (OntologyIndividual individual : modelOntologyManager.getOntology().getIndividuals()) {
	    if (individual.getIri().contains(modelOntologyManager.getOntology().getIri())) {
		for (OntologyClass clazz : individual.getTypes()) {
		    try {
			for (OntologyNamespace nspc : getReferenceOntology().getNamespaces()) {
			    if (clazz.getIri().contains(nspc.getName())) {
				createInstanceOfMapping(clazz, individual);
				System.out.println("MAPPING FOUND!!!!!");
				break;
			    }
			}
		    } catch (OIDABridgeException e) {
			e.printStackTrace();
		    }
		}
	    }
	}
	// instance of relationships
	// for (OntologyClassEquivalence equ :
	// modelOntologyManager.getOntology().getLocalOntology().getClassEquivalences())
	// {
	// if (!equ.getClass1().getPrefix().equals(equ.getClass2().getPrefix()))
	// {
	// Optional<OntologyClass> optRefClass =
	// oidaOntologyService.getReferenceOntologyManager().get().getClass(equ.getClass2().getIri());
	//
	// if (optRefClass.isPresent())
	// createClassMapping(equ, equ.getClass1(), optRefClass.get());
	// }
	// }
    }

    /**
     * Creates a primary mapping relation between the currently selected model
     * element and a recommended reference ontology entity.
     * 
     * @param selectedRecommendation
     *            A Recommendation object, containing the selected reference
     *            ontology entity.
     */
    public void establishPrimaryMapping(Recommendation selectedRecommendation) {
	RecommendationSet recSet = (RecommendationSet)RecommenderSystem.getInstance().getCurrentPrimaryRecommendationsResource().getContents().get(0);
	IOntologyManager modelOntologyManager = modelHandlerMap.get(recSet.getModelObject()).getModelOntologyManager();

	if (recSet.getOntologyEntity() instanceof OntologyIndividual && selectedRecommendation.getRecommendedEntity() instanceof OntologyClass) {
	    OntologyIndividual mappedIndividual = (OntologyIndividual)recSet.getOntologyEntity();
	    modelOntologyManager.assignIndividualToClass((OntologyIndividual)recSet.getOntologyEntity(), (OntologyClass)selectedRecommendation.getRecommendedEntity());

	    createInstanceOfMapping((OntologyClass)selectedRecommendation.getRecommendedEntity(), mappedIndividual);
	    try {
		modelOntologyManager.saveLocalOntology();
		LOGGER.info("Primary Mapping establisehd. Individual '" + recSet.getOntologyEntity().getIri() + "' is of type '" + selectedRecommendation.getRecommendedEntity().getIri() + "'");
	    } catch (OntologyManagerException e) {
		e.printStackTrace();
	    }
	}
    }

    /**
     * Creates a secondary mapping relation between the currently selected model
     * element and a recommended reference ontology entity.
     * 
     * @param selectedRecommendation
     *            A Recommendation object, containing the selected reference
     *            ontology entity.
     */
    public void establishSecondaryClassMapping(OntologyClass metaModelClass, OntologyClass referenceClass) {
	try {
	    IOntologyManager modelOntMgr = getMetaModelHandler().getModelOntologyManager();

	    Optional<OntologyClassEquivalence> optEquivalence = modelOntMgr.assignClassEquivalence(metaModelClass, referenceClass);

	    if (optEquivalence.isPresent()) {

		createClassMapping(optEquivalence.get(), metaModelClass, referenceClass);
		LOGGER.info("Secondary class-mapping established: '" + metaModelClass.getIri() + "' equals '" + referenceClass.getIri() + "'.");
		modelOntMgr.saveLocalOntology();
	    }
	} catch (OIDABridgeException inner) {
	    LOGGER.error("Error while establishing the secondary class mapping.", inner);
	} catch (OntologyManagerException inner) {
	    LOGGER.error("Error while saving meta model ontology.", inner);
	}
    }

    public Optional<ClassEqualsMapping> getMapping(final OntologyClass selectedClass) {
	for (Mapping mapping : getMetaModelClassMappings().getMappings()) {
	    ClassEqualsMapping m = (ClassEqualsMapping)mapping;
	    if (m.getClazz1().getIri().equals(selectedClass.getIri()) || m.getClazz2().getIri().equals(selectedClass.getIri()))
		return Optional.of(m);
	}

	return Optional.empty();
    }

    public Optional<InstanceOfMapping> getMapping(final OntologyIndividual selectedIndividual) {
	for (Mapping mapping : getModelMappings().getMappings()) {
	    InstanceOfMapping m = (InstanceOfMapping)mapping;
	    if (m.getIndividual().getIri().equals(selectedIndividual.getIri()))
		return Optional.of(m);
	}

	return Optional.empty();
    }

    public void establishSecondaryObjectPropertyMapping(OntologyObjectProperty selectedMetaModelObjectProperty, OntologyObjectProperty selectedReferenceOntologyObjectProperty) {
	try {
	    IOntologyManager modelOntMgr = getMetaModelHandler().getModelOntologyManager();
	    Optional<OntologyObjectPropertyEquivalence> optEquivalence = modelOntMgr.assignObjectPropertyEquivalence(selectedMetaModelObjectProperty, selectedReferenceOntologyObjectProperty);

	    if (optEquivalence.isPresent()) {
		ObjectPropertyEqualsMapping mapping = MappingFactory.eINSTANCE.createObjectPropertyEqualsMapping();
		mapping.setObjectProperty1(selectedMetaModelObjectProperty);
		mapping.setObjectProperty2(selectedReferenceOntologyObjectProperty);
		mapping.setEquivalence(optEquivalence.get());

		selectedMetaModelObjectProperty.setMappingExists(true);
		selectedReferenceOntologyObjectProperty.setMappingExists(true);

		getMetaModelObjectPropertyMappings().getMappings().add(mapping);

		LOGGER.info("Secondary object property-mapping established: '" + selectedMetaModelObjectProperty.getIri() + "' equals '" + selectedReferenceOntologyObjectProperty.getIri() + "'.");

		modelOntMgr.saveLocalOntology();
	    }
	} catch (OIDABridgeException inner) {
	    LOGGER.error("Error while establishing the secondary class mapping.", inner);
	} catch (OntologyManagerException inner) {
	    LOGGER.error("Error while saving meta model ontology.", inner);
	}
    }

    public Optional<ObjectPropertyEqualsMapping> getMapping(final OntologyObjectProperty objectProperty) {
	for (Mapping mapping : getMetaModelObjectPropertyMappings().getMappings()) {
	    ObjectPropertyEqualsMapping m = (ObjectPropertyEqualsMapping)mapping;
	    if (m.getObjectProperty1().equals(objectProperty))
		return Optional.of(m);
	}

	return Optional.empty();
    }

    public void removeSecondaryObjectPropertyMapping(OntologyObjectProperty selectedMetaModelObjectProperty) {
	try {
	    IOntologyManager modelOntMgr = getMetaModelHandler().getModelOntologyManager();
	    for (Mapping mapping : getMetaModelObjectPropertyMappings().getMappings()) {
		ObjectPropertyEqualsMapping m = (ObjectPropertyEqualsMapping)mapping;
		if (m.getObjectProperty1().equals(selectedMetaModelObjectProperty)) {
		    m.getObjectProperty1().setMappingExists(false);
		    m.getObjectProperty2().setMappingExists(false);

		    modelOntMgr.removeObjectPropertyEquivalence(m.getEquivalence());

		    getMetaModelObjectPropertyMappings().getMappings().remove(mapping);
		    return;
		}
	    }
	} catch (OIDABridgeException inner) {
	    LOGGER.error("Error while removing secondary object property mapping.", inner);
	}
    }

    public void removeSecondaryClassMapping(OntologyClass selectedMetaModelClass) {
	try {
	    IOntologyManager modelOntMgr = getMetaModelHandler().getModelOntologyManager();
	    for (Mapping mapping : getMetaModelClassMappings().getMappings()) {
		ClassEqualsMapping m = (ClassEqualsMapping)mapping;
		if (m.getClazz1().equals(selectedMetaModelClass)) {
		    m.getClazz1().setMappingExists(false);
		    m.getClazz2().setMappingExists(false);

		    modelOntMgr.removeClassEquivalence(m.getEquivalence());

		    getMetaModelClassMappings().getMappings().remove(mapping);
		    return;
		}
	    }
	} catch (OIDABridgeException inner) {
	    LOGGER.error("Error while removing secondary class mapping.", inner);
	}
    }

    private ClassEqualsMapping createClassMapping(OntologyClassEquivalence equivalence, OntologyClass metaModelClass, OntologyClass referenceClass) {
	ClassEqualsMapping mapping = MappingFactory.eINSTANCE.createClassEqualsMapping();
	mapping.setClazz1(metaModelClass);
	mapping.setClazz2(referenceClass);
	mapping.setEquivalence(equivalence);

	getMetaModelClassMappings().getMappings().add(mapping);

	metaModelClass.setMappingExists(true);
	referenceClass.setMappingExists(true);

	return mapping;
    }

    private ObjectPropertyEqualsMapping createObjectPropertyMapping(OntologyObjectPropertyEquivalence equivalence, OntologyObjectProperty metaModelObjProperty,
	    OntologyObjectProperty referenceObjProperty) {
	ObjectPropertyEqualsMapping mapping = MappingFactory.eINSTANCE.createObjectPropertyEqualsMapping();
	mapping.setObjectProperty1(equivalence.getObjectProperty1());
	mapping.setObjectProperty2(equivalence.getObjectProperty2());
	mapping.setEquivalence(equivalence);

	getMetaModelObjectPropertyMappings().getMappings().add(mapping);

	metaModelObjProperty.setMappingExists(true);
	referenceObjProperty.setMappingExists(true);

	return mapping;
    }

    private InstanceOfMapping createInstanceOfMapping(OntologyClass clazz, OntologyIndividual individual) {
	InstanceOfMapping mapping = MappingFactory.eINSTANCE.createInstanceOfMapping();
	mapping.setClazz(clazz);
	mapping.setIndividual(individual);

	getModelMappings().getMappings().add(mapping);

	individual.setMappingExists(true);

	return mapping;
    }

    public IModelChangeHandler getModelChangeHandler(Object model) throws OIDABridgeException {
	if (modelHandlerMap.containsKey(model))
	    return modelHandlerMap.get(model);

	throw new OIDABridgeException("No Model Change Handler available for object '" + model.toString() + "'");
    }

    /**
     * Returns the Model Change Handler for the Meta-Model.
     * 
     * @return An Optional-Object of the Model Change Handler.
     */
    public IModelChangeHandler getMetaModelHandler() throws OIDABridgeException {
	if (metaModelOntologyHandler != null)
	    return metaModelOntologyHandler;

	throw new OIDABridgeException("No Meta Model Handler available.");
    }

    /**
     * Returns the state of the OIDA Bridge.
     * 
     * @return true if the OIDA Bridge is ready.
     */
    public boolean isReady() {
	return extensionPointsReady && metaModelOntologyReady && modelBaseOntologyReady;
    }

    /**
     * Returns a message describing the OIDA Bridge state.
     * 
     * @return a message string, or ready.
     */
    public String getStateString() {
	if (isReady())
	    return "Ready";

	return "Not Ready";
    }

    public Resource getMetaModelClassMappingsResource() {
	return metaModelClassMappingsResource;
    }

    public MappingSet getMetaModelClassMappings() {
	return (MappingSet)metaModelClassMappingsResource.getContents().get(0);
    }

    public Resource getMetaModelObjectPropertyMappingsResource() {
	return metaModelObjectPropertyMappingsResource;
    }

    public MappingSet getMetaModelObjectPropertyMappings() {
	return (MappingSet)metaModelObjectPropertyMappingsResource.getContents().get(0);
    }

    public Resource getModelMappingsResource() {
	return modelMappingsResource;
    }

    public MappingSet getModelMappings() {
	return (MappingSet)modelMappingsResource.getContents().get(0);
    }

    public Ontology getReferenceOntology() throws OIDABridgeException {
	if (oidaOntologyService.getReferenceOntologyManager().isPresent())
	    return oidaOntologyService.getReferenceOntologyManager().get().getOntology();

	throw new OIDABridgeException("No Reference Ontology loaded.");
    }

    public Ontology getMetaModelOntology() throws OIDABridgeException {
	try {
	    return getMetaModelHandler().getModelOntologyManager().getOntology();
	} catch (OIDABridgeException inner) {
	    throw new OIDABridgeException("No Meta Model Ontology available.", inner);
	}
    }
}
