/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import bridgemodel.AggregatedRecommendation;
import bridgemodel.BridgemodelFactory;
import bridgemodel.ClassEqualsMapping;
import bridgemodel.ClassMappingSet;
import bridgemodel.InstanceMappingSet;
import bridgemodel.InstanceOfMapping;
import bridgemodel.ObjectPropertyEqualsMapping;
import bridgemodel.ObjectPropertyMappingSet;
import bridgemodel.Recommendation;
import bridgemodel.RecommendationSet;
import bridgemodel.provider.BridgemodelItemProviderAdapterFactory;
import oida.bridge.Activator;
import oida.bridge.model.changehandler.IModelChangeHandler;
import oida.bridge.model.changehandler.IModelChangeHandlerFactory;
import oida.bridge.model.ontology.IMetaModelOntologyProvider;
import oida.bridge.model.ontology.OIDAModelBaseOntology;
import oida.bridge.model.strategy.IRenamerStrategy;
import oida.bridge.model.strategy.IStructuringStrategy;
import oida.bridge.recommender.IClassRecommender;
import oida.bridge.recommender.IDatatypePropertyRecommender;
import oida.bridge.recommender.IObjectPropertyRecommender;
import oida.bridge.recommender.IPrimaryRecommender;
import oida.bridge.recommender.IRecommender;
import oida.ontology.Ontology;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyClassEquivalence;
import oida.ontology.OntologyDatatypeProperty;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyIndividual;
import oida.ontology.OntologyObjectProperty;
import oida.ontology.OntologyObjectPropertyEquivalence;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.OntologyManagerException;
import oida.ontology.service.IOIDAOntologyService;
import oida.ontologyMgr.LocalOntologyMetaInfo;
import oida.ontologyMgr.SystemOntologyMetaInfo;
import oida.util.ExtensionPointUtil;
import oida.util.OIDAUtil;
import oida.util.constants.FileConstants;
import oida.util.constants.OntologyConstants;
import oida.util.constants.StringConstants;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-03-03
 *
 */
@Creatable
@Singleton
public final class OIDABridge implements IOIDABridge {
	protected static Logger LOGGER = LoggerFactory.getLogger(OIDABridge.class);

	private boolean extensionPointsReady = false;
	private boolean modelBaseOntologyReady = false;
	private boolean metaModelOntologyReady = false;

	private List<IPrimaryRecommender> recommenderPrimary;
	
	private List<IClassRecommender> recommenderSecondaryClass;
	private List<IObjectPropertyRecommender> recommenderSecondaryObjectProperty;
	private List<IDatatypePropertyRecommender> recommenderSecondaryDatatypeProperty;

	private IRenamerStrategy renamerStrategy;
	private IStructuringStrategy structuringStrategy;

	private IModelChangeHandler metaModelOntologyHandler;

	private Map<Object, IModelChangeHandler> modelHandlerMap = new HashMap<Object, IModelChangeHandler>();

	private Resource currentPrimaryRecommendationsResource;
	private Resource currentSecondaryRecommendationsResource;
	
	private Resource metaModelClassMappingsResource;
	private Resource metaModelObjectPropertyMappingsResource;
	private Resource modelMappingsResource;

	private boolean secondaryRecommendationSystemEnabled = false;

	private IModelChangeHandlerFactory changeHandlerFactory;

	private IOIDAOntologyService oidaOntologyService;

	@Inject
	public OIDABridge(IOIDAOntologyService oidaOntologyService) {
		this.oidaOntologyService = oidaOntologyService;
		tryInitialization();
	}

	private void tryInitialization() {
		LOGGER.info("Initializing OIDA Bridge Service...");
		modelHandlerMap.clear();

		BridgemodelItemProviderAdapterFactory adapterFactory = new BridgemodelItemProviderAdapterFactory();
		ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(adapterFactory);
		composedAdapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());

		AdapterFactoryEditingDomain editingDomain = new AdapterFactoryEditingDomain(composedAdapterFactory, new BasicCommandStack());

		metaModelClassMappingsResource = editingDomain.createResource("http://de.oida/bridge/classmappings");
		metaModelClassMappingsResource.getContents().add(BridgemodelFactory.eINSTANCE.createClassMappingSet());
		
		metaModelObjectPropertyMappingsResource = editingDomain.createResource("http://de.oida/bridge/objectpropertymappings");
		metaModelObjectPropertyMappingsResource.getContents().add(BridgemodelFactory.eINSTANCE.createObjectPropertyMappingSet());
		
		modelMappingsResource = editingDomain.createResource("http://de.oida/bridge/individualmappings");
		modelMappingsResource.getContents().add(BridgemodelFactory.eINSTANCE.createInstanceMappingSet());
		
		currentPrimaryRecommendationsResource = editingDomain.createResource("http://de.oida/bridge/currentprimaryrecommendations");
		currentPrimaryRecommendationsResource.getContents().add(BridgemodelFactory.eINSTANCE.createRecommendationSet());

		currentSecondaryRecommendationsResource = editingDomain.createResource("http://de.oida/bridge/currentsecondaryrecommendations");
		currentSecondaryRecommendationsResource.getContents().add(BridgemodelFactory.eINSTANCE.createRecommendationSet());

		if (!extensionPointsReady)
			extensionPointsReady = tryInitExtensions();

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

		LOGGER.info("Step 4/7: Evaluating primary recommender extensions.");
		try {
			recommenderPrimary = ExtensionPointUtil.loadExtensions(Activator.getExtensionRegistry(), IPrimaryRecommender.class, Activator.OIDA_RECOMMENDER_PRIMARY_EXTENSIONPOINT_ID);

			for (IPrimaryRecommender r : recommenderPrimary)
				LOGGER.info("Primary Recommender registered: " + r.toString() + ".");
		} catch (CoreException e) {
			LOGGER.error("Error while evaluating primary recommender extension point.", e);
			extensionPointsReady = false;
		}

		LOGGER.info("Step 5/7: Evaluating secondary recommender extensions.");
		try {
			recommenderSecondaryClass = ExtensionPointUtil.loadExtensions(Activator.getExtensionRegistry(), IClassRecommender.class, Activator.OIDA_RECOMMENDER_SECONDARY_CLASS_EXTENSIONPOINT_ID);

			for (IRecommender r : recommenderSecondaryClass)
				LOGGER.info("Secondary Class-Recommender registered: " + r.toString() + ".");
		} catch (CoreException e) {
			LOGGER.error("Error while evaluating secondary recommender extension point.", e);
			extensionPointsReady = false;
		}
		
		try {
			recommenderSecondaryObjectProperty = ExtensionPointUtil.loadExtensions(Activator.getExtensionRegistry(), IObjectPropertyRecommender.class, Activator.OIDA_RECOMMENDER_SECONDARY_OBJECTPROPERTY_EXTENSIONPOINT_ID);

			for (IObjectPropertyRecommender r : recommenderSecondaryObjectProperty)
				LOGGER.info("Secondary ObjectProperty-Recommender registered: " + r.toString() + ".");
		} catch (CoreException e) {
			LOGGER.error("Error while evaluating secondary recommender extension point.", e);
			extensionPointsReady = false;
		}
		
		try {
			recommenderSecondaryDatatypeProperty = ExtensionPointUtil.loadExtensions(Activator.getExtensionRegistry(), IDatatypePropertyRecommender.class, Activator.OIDA_RECOMMENDER_SECONDARY_DATATYPEPROPERTY_EXTENSIONPOINT_ID);

			for (IDatatypePropertyRecommender r : recommenderSecondaryDatatypeProperty)
				LOGGER.info("Secondary Datatype-Property-Recommender registered: " + r.toString() + ".");
		} catch (CoreException e) {
			LOGGER.error("Error while evaluating secondary recommender extension point.", e);
			extensionPointsReady = false;
		}

		return extensionPointsReady;
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
				oidaOntologyService.saveLibraryResource();
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

		SystemOntologyMetaInfo metaModelOntologyMetaInfo = OIDAUtil.getSystemOntologyMetaInfo(new File(OIDAUtil.getOIDAMetaModelOntologyPath(), renamerStrategy.getMetaModelName() + FileConstants.OWL_FILE_POSTFIX));

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

				for (IClassRecommender rec : recommenderSecondaryClass)
					rec.initializeRecommenderForMetaModel(metaModelOntologyHandler.getModelOntologyManager().getOntology(), oidaOntologyService.getReferenceOntologyManager().get().getOntology());
				for (IObjectPropertyRecommender rec : recommenderSecondaryObjectProperty)
					rec.initializeRecommenderForMetaModel(metaModelOntologyHandler.getModelOntologyManager().getOntology(), oidaOntologyService.getReferenceOntologyManager().get().getOntology());
				for (IDatatypePropertyRecommender rec : recommenderSecondaryDatatypeProperty)
					rec.initializeRecommenderForMetaModel(metaModelOntologyHandler.getModelOntologyManager().getOntology(), oidaOntologyService.getReferenceOntologyManager().get().getOntology());
				
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

	@Override
	public void invokeModelObservation(final Object modelObject, final File modelOntologyDirectory, final String modelObjectId) throws OIDABridgeException {
		if (renamerStrategy == null)
			throw new OIDABridgeException("No renamer strategy found. Model won't be observed.");

		if (structuringStrategy == null)
			throw new OIDABridgeException("No structuring strategy found. Model won't be observed.");

		if (!getMetaModelHandler().isPresent())
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

			changeHandler.startChangeTracking(renamerStrategy, structuringStrategy, getMetaModelHandler().get().getModelOntologyManager(), optModelOntologyMgr.get(), modelObject);
			modelHandlerMap.put(modelObject, changeHandler);

			for (IPrimaryRecommender rec : recommenderPrimary)
				rec.initializeRecommenderForModel(changeHandler.getModelOntologyManager().getOntology(), oidaOntologyService.getReferenceOntologyManager().get().getOntology());
		} else
			LOGGER.error("Model observation could not be startet: Model ontology not found.");
	}

	@Override
	public void saveModelOntology(final Object modelObject) {
		try {
			if (modelHandlerMap.containsKey(modelObject))
				modelHandlerMap.get(modelObject).getModelOntologyManager().saveLocalOntology();
		} catch (OntologyManagerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stopModelObservation(final Object modelObject) {
		modelHandlerMap.get(modelObject).closeModelOntology();
		modelHandlerMap.remove(modelObject);
	}

	@Override
	public void reportModelSelectionChanged(Object modelObject, Object firstSelectedElement) {
		if (modelObject == null || firstSelectedElement == null)
			return;

		Optional<OntologyEntity> optOntEntity = modelHandlerMap.get(modelObject).getOntologyEntityForModelElement(firstSelectedElement);
		if (optOntEntity.isPresent()) {
			RecommendationSet recPrimarySet = (RecommendationSet)currentPrimaryRecommendationsResource.getContents().get(0);
			recPrimarySet.getRecommendations().clear();
			recPrimarySet.setModelObject(modelObject);
			recPrimarySet.setOntologyEntity(optOntEntity.get());

			List<Recommendation> recommendations = new ArrayList<Recommendation>();
			for (IPrimaryRecommender rec : recommenderPrimary)
				recommendations.addAll(rec.findRecommendationsForSelectedIndividual((OntologyIndividual)recPrimarySet.getOntologyEntity(), this));
			
			List<Recommendation> aggregated = new ArrayList<Recommendation>();
			while (!recommendations.isEmpty()) {
				Recommendation r = recommendations.get(0);
				
				Recommendation r2 = null;
				for (int i = 1; i < recommendations.size(); i++) {
					if (r.getRecommendedEntity().equals(recommendations.get(i).getRecommendedEntity())) {
						r2 = recommendations.get(i);
						break;
					}
				}
				
				if (r2 != null) {
					AggregatedRecommendation ar = BridgemodelFactory.eINSTANCE.createAggregatedRecommendation();
					ar.getRecommendations().add(r);
					ar.getRecommendations().add(r2);
					ar.setRecommenderName(r.getRecommenderName() + ", " + r2.getRecommenderName());
					ar.setRecommenderMessage(r.getRecommenderMessage() + " AND " + r2.getRecommenderMessage());
					ar.setRecommendedEntity(r.getRecommendedEntity());
					ar.setReliability(r.getReliability() + r2.getReliability());
					
					recommendations.remove(r);
					recommendations.remove(r2);
					aggregated.add(ar);
				} else {
					recommendations.remove(r);
					aggregated.add(r);
				}
			}
			
			recPrimarySet.getRecommendations().addAll(aggregated);
		} else
			LOGGER.warn("No Ontology Entity found for '" + firstSelectedElement.toString() + "'.");
	}

	@Override
	public void reportMetaModelSelectionChanged(Object selectedObject) {
		if (selectedObject == null)
			return;

		RecommendationSet recSecondarySet = (RecommendationSet)currentSecondaryRecommendationsResource.getContents().get(0);
		recSecondarySet.getRecommendations().clear();
		recSecondarySet.setOntologyEntity((OntologyEntity)selectedObject);

		List<Recommendation> recommendations = new ArrayList<Recommendation>();
		
		if (selectedObject instanceof OntologyClass) {
			for (IClassRecommender rec : recommenderSecondaryClass)
				recommendations.addAll(rec.findRecommendationsForSelectedClass((OntologyClass)recSecondarySet.getOntologyEntity(), this));
		}
		
		if (selectedObject instanceof OntologyObjectProperty) {
			for (IObjectPropertyRecommender rec : recommenderSecondaryObjectProperty)
				recommendations.addAll(rec.findRecommendationsForSelectedObjectProperty((OntologyObjectProperty)recSecondarySet.getOntologyEntity(), this));
		}
		
		if (selectedObject instanceof OntologyDatatypeProperty) {
			for (IDatatypePropertyRecommender rec : recommenderSecondaryDatatypeProperty)
				recommendations.addAll(rec.findRecommendationsForSelectedDatatypeProperty((OntologyDatatypeProperty)recSecondarySet.getOntologyEntity(), this));
		}
		
		List<Recommendation> aggregated = new ArrayList<Recommendation>();
		for (Recommendation r : recommendations) {
			boolean added = false;
			for (Recommendation r2 : recommendations) {
				if (r != r2 && r.getRecommendedEntity().equals(r2.getRecommendedEntity())) {
					AggregatedRecommendation ar = BridgemodelFactory.eINSTANCE.createAggregatedRecommendation();
					ar.getRecommendations().add(r);
					ar.getRecommendations().add(r2);
					
					ar.setRecommendedEntity(r.getRecommendedEntity());
					ar.setReliability(r.getReliability() + r2.getReliability());
					
					aggregated.add(ar);
					added = true;
					break;
				}
			}
			
			if (!added)
				aggregated.add(r);
		}
		
		recSecondarySet.getRecommendations().addAll(aggregated);
	}

	private String generateModelOntologyFileName(String modelObjectId) throws OIDABridgeException {
		if (oidaOntologyService.getLibrary().getReferenceOntology() == null)
			throw new OIDABridgeException("No reference ontology set. Model won't be observed.");

		// Dirty: Reference Ontology may be an Online-Ontology! (Cast to local ontology inadmissible)
		return modelObjectId + StringConstants.UNDERSCORE + OIDAUtil.extractFileName(((LocalOntologyMetaInfo)oidaOntologyService.getLibrary().getReferenceOntology()).getLocalPath());
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
	}

	@Override
	public Resource getCurrentPrimaryRecommendationsResource() {
		return currentPrimaryRecommendationsResource;
	}

	@Override
	public Resource getCurrentSecondaryRecommendationsResource() {
		return currentSecondaryRecommendationsResource;
	}

	@Override
	public void establishPrimaryMapping(Recommendation selectedRecommendation) {
		RecommendationSet recSet = (RecommendationSet)currentPrimaryRecommendationsResource.getContents().get(0);
		IOntologyManager modelOntologyManager = modelHandlerMap.get(recSet.getModelObject()).getModelOntologyManager();

		if (recSet.getOntologyEntity() instanceof OntologyIndividual && selectedRecommendation.getRecommendedEntity() instanceof OntologyClass) {
			OntologyIndividual mappedIndividual = (OntologyIndividual)recSet.getOntologyEntity();
			modelOntologyManager.assignIndividualToClass((OntologyIndividual)recSet.getOntologyEntity(), (OntologyClass)selectedRecommendation.getRecommendedEntity());
			
			InstanceOfMapping mapping = BridgemodelFactory.eINSTANCE.createInstanceOfMapping();
			mapping.setClazz((OntologyClass)selectedRecommendation.getRecommendedEntity());
			mapping.setIndividual(mappedIndividual);
			
			mappedIndividual.setMappingExists(true);
		
			getModelMappings().getInstanceMappings().add(mapping);
			
			LOGGER.info("Primary Mapping establisehd. Individual '" + recSet.getOntologyEntity().getIri() + "' is of type '" + selectedRecommendation.getRecommendedEntity().getIri() + "'");
		}
	}

	@Override
	public void establishSecondaryClassMapping(OntologyClass metaModelClass, OntologyClass referenceClass) {
		if (getMetaModelHandler().isPresent()) {
			Optional<OntologyClassEquivalence> optEquivalence = getMetaModelHandler().get().getModelOntologyManager().assignClassEquivalence(metaModelClass, referenceClass);

			if (optEquivalence.isPresent()) {
				ClassEqualsMapping mapping = BridgemodelFactory.eINSTANCE.createClassEqualsMapping();
				mapping.setClazz1(metaModelClass);
				mapping.setClazz2(referenceClass);
				mapping.setEquivalence(optEquivalence.get());

				metaModelClass.setMappingExists(true);
				referenceClass.setMappingExists(true);
				
				getMetaModelClassMappings().getClassMappings().add(mapping);

				LOGGER.info("Secondary class-mapping established: '" + metaModelClass.getIri() + "' equals '" + referenceClass.getIri() + "'.");

				try {
					getMetaModelHandler().get().getModelOntologyManager().saveLocalOntology();
				} catch (OntologyManagerException e) {
					LOGGER.error("Error while saving meta model ontology.", e);
				}
			}
		}
	}

	public Optional<ClassEqualsMapping> getClassMapping(OntologyClass selectedClass) {
		for (ClassEqualsMapping mapping : getMetaModelClassMappings().getClassMappings())
			if (mapping.getClazz1().getIri().equals(selectedClass.getIri()) || mapping.getClazz2().getIri().equals(selectedClass.getIri()))
				return Optional.of(mapping);

		return Optional.empty();
	}

	@Override
	public void establishSecondaryObjectPropertyMapping(OntologyObjectProperty selectedMetaModelObjectProperty, OntologyObjectProperty selectedReferenceOntologyObjectProperty) {
		if (getMetaModelHandler().isPresent()) {
			Optional<OntologyObjectPropertyEquivalence> optEquivalence = getMetaModelHandler().get().getModelOntologyManager().assignObjectPropertyEquivalence(selectedMetaModelObjectProperty,
					selectedReferenceOntologyObjectProperty);

			if (optEquivalence.isPresent()) {
				ObjectPropertyEqualsMapping mapping = BridgemodelFactory.eINSTANCE.createObjectPropertyEqualsMapping();
				mapping.setObjectProperty1(selectedMetaModelObjectProperty);
				mapping.setObjectProperty2(selectedReferenceOntologyObjectProperty);
				mapping.setEquivalence(optEquivalence.get());

				selectedMetaModelObjectProperty.setMappingExists(true);
				selectedReferenceOntologyObjectProperty.setMappingExists(true);

				getMetaModelObjectPropertyMappings().getObjectPropertyMappings().add(mapping);

				LOGGER.info("Secondary object property-mapping established: '" + selectedMetaModelObjectProperty.getIri() + "' equals '" + selectedReferenceOntologyObjectProperty.getIri() + "'.");

				try {
					getMetaModelHandler().get().getModelOntologyManager().saveLocalOntology();
				} catch (OntologyManagerException e) {
					LOGGER.error("Error while saving meta model ontology.", e);
				}
			}
		}
	}

	@Override
	public Optional<ObjectPropertyEqualsMapping> getObjectPropertyMapping(OntologyObjectProperty selectedMetaModelObjectProperty) {
		for (ObjectPropertyEqualsMapping mapping : getMetaModelObjectPropertyMappings().getObjectPropertyMappings())
			if (mapping.getObjectProperty1().equals(selectedMetaModelObjectProperty))
				return Optional.of(mapping);

		return Optional.empty();
	}

	@Override
	public void removeSecondaryObjectPropertyMapping(OntologyObjectProperty selectedMetaModelObjectProperty) {
		for (ObjectPropertyEqualsMapping mapping : getMetaModelObjectPropertyMappings().getObjectPropertyMappings()) {
			if (mapping.getObjectProperty1().equals(selectedMetaModelObjectProperty)) {
				mapping.getObjectProperty1().setMappingExists(false);
				mapping.getObjectProperty2().setMappingExists(false);

				getMetaModelHandler().get().getModelOntologyManager().removeObjectPropertyEquivalence(mapping.getEquivalence());

				getMetaModelObjectPropertyMappings().getObjectPropertyMappings().remove(mapping);
				return;
			}
		}
	}

	@Override
	public void removeSecondaryClassMapping(OntologyClass selectedMetaModelClass) {
		for (ClassEqualsMapping mapping : getMetaModelClassMappings().getClassMappings()) {
			if (mapping.getClazz1().equals(selectedMetaModelClass)) {
				mapping.getClazz1().setMappingExists(false);
				mapping.getClazz2().setMappingExists(false);

				getMetaModelHandler().get().getModelOntologyManager().removeClassEquivalence(((ClassEqualsMapping)mapping).getEquivalence());

				getMetaModelClassMappings().getClassMappings().remove(mapping);
				return;
			}
		}
	}

	private ClassEqualsMapping createClassMapping(OntologyClassEquivalence equivalence, OntologyClass metaModelClass, OntologyClass referenceClass) {
		ClassEqualsMapping mapping = BridgemodelFactory.eINSTANCE.createClassEqualsMapping();
		mapping.setClazz1(metaModelClass);
		mapping.setClazz2(referenceClass);
		mapping.setEquivalence(equivalence);

		getMetaModelClassMappings().getClassMappings().add(mapping);

		metaModelClass.setMappingExists(true);
		referenceClass.setMappingExists(true);

		return mapping;
	}

	private ObjectPropertyEqualsMapping createObjectPropertyMapping(OntologyObjectPropertyEquivalence equivalence, OntologyObjectProperty metaModelObjProperty,
			OntologyObjectProperty referenceObjProperty) {
		ObjectPropertyEqualsMapping mapping = BridgemodelFactory.eINSTANCE.createObjectPropertyEqualsMapping();
		mapping.setObjectProperty1(equivalence.getObjectProperty1());
		mapping.setObjectProperty2(equivalence.getObjectProperty2());
		mapping.setEquivalence(equivalence);

		getMetaModelObjectPropertyMappings().getObjectPropertyMappings().add(mapping);

		metaModelObjProperty.setMappingExists(true);
		referenceObjProperty.setMappingExists(true);

		return mapping;
	}

	@Override
	public Optional<IModelChangeHandler> getModelChangeHandler(Object model) {
		if (modelHandlerMap.containsKey(model))
			return Optional.of(modelHandlerMap.get(model));

		return Optional.empty();
	}

	@Override
	public Optional<IModelChangeHandler> getMetaModelHandler() {
		if (metaModelOntologyHandler != null)
			return Optional.of(metaModelOntologyHandler);

		return Optional.empty();
	}

	@Override
	public boolean isReady() {
		return extensionPointsReady && metaModelOntologyReady && modelBaseOntologyReady;
	}

	@Override
	public String getStateString() {
		if (isReady())
			return "Ready";

		return "Not Ready";
	}

	@Override
	public void setSecondaryRecommendationSystemEnabled(boolean enabled) {
		secondaryRecommendationSystemEnabled = enabled;
	}

	@Override
	public boolean isSecondaryRecommendationSystemEnabled() {
		return secondaryRecommendationSystemEnabled;
	}
	
	@Override
	public Resource getMetaModelClassMappingsResource() {
		return metaModelClassMappingsResource;
	}
	
	@Override
	public ClassMappingSet getMetaModelClassMappings() {
		return (ClassMappingSet)metaModelClassMappingsResource.getContents().get(0);
	}
	
	@Override
	public Resource getMetaModelObjectPropertyMappingsResource() {
		return metaModelObjectPropertyMappingsResource;
	}
	
	@Override
	public ObjectPropertyMappingSet getMetaModelObjectPropertyMappings() {
		return (ObjectPropertyMappingSet)metaModelObjectPropertyMappingsResource.getContents().get(0);
	}
	
	@Override
	public Resource getModelMappingsResource() {
		return modelMappingsResource;
	}
	
	@Override
	public InstanceMappingSet getModelMappings() {
		return (InstanceMappingSet)modelMappingsResource.getContents().get(0);
	}

	@Override
	public Optional<Ontology> getReferenceOntology() {
		if (oidaOntologyService.getReferenceOntologyManager().isPresent())
			return Optional.of(oidaOntologyService.getReferenceOntologyManager().get().getOntology());
		else
			return Optional.empty();
	}

	@Override
	public Optional<Ontology> getMetaModelOntology() {
		if (metaModelOntologyHandler.getModelOntologyManager() != null)
			return Optional.of(metaModelOntologyHandler.getModelOntologyManager().getOntology());
		else
			return Optional.empty();
	}
}
