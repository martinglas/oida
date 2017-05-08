/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge.service;

import java.io.File;
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

import bridgemodel.BridgemodelFactory;
import bridgemodel.Recommendation;
import bridgemodel.RecommendationSet;
import bridgemodel.provider.BridgemodelItemProviderAdapterFactory;
import oida.bridge.Activator;
import oida.bridge.model.changehandler.IModelChangeHandler;
import oida.bridge.model.changehandler.IModelChangeHandlerFactory;
import oida.bridge.model.ontology.OIDAModelBaseOntology;
import oida.bridge.model.strategy.IRenamerStrategy;
import oida.bridge.model.strategy.IStructuringStrategy;
import oida.bridge.recommender.IRecommender;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyIndividual;
import oida.ontology.OntologyObjectProperty;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.OntologyManagerException;
import oida.ontology.service.IOIDAOntologyService;
import oida.ontologyMgr.OntologyFile;
import oida.util.ExtensionPointUtil;
import oida.util.OIDAUtil;
import oida.util.constants.FileConstants;
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

	private Map<Object, IModelChangeHandler> modelHandlerMap = new HashMap<Object, IModelChangeHandler>();

	private IModelChangeHandlerFactory changeHandlerFactory;

	private List<IRecommender> recommenderPrimary;
	private List<IRecommender> recommenderSecondary;

	private IRenamerStrategy renamerStrategy;
	private IStructuringStrategy structuringStrategy;

	private Resource currentPrimaryRecommendationsResource;
	private Resource currentSecondaryRecommendationsResource;
	
	private Resource currentMetaModelResource;

	private IOIDAOntologyService oidaOntologyService;

	@Inject
	public OIDABridge(IOIDAOntologyService oidaOntologyService) {
		LOGGER.info("Initializing OIDA Bridge Service...");
		this.oidaOntologyService = oidaOntologyService;
		modelHandlerMap.clear();
		
		BridgemodelItemProviderAdapterFactory adapterFactory = new BridgemodelItemProviderAdapterFactory();
		ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(adapterFactory);
		composedAdapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());

		AdapterFactoryEditingDomain editingDomain = new AdapterFactoryEditingDomain(composedAdapterFactory, new BasicCommandStack());
		
		LOGGER.info("Step 1/7: Evaluating Model Change Handler Factory extensions.");
		try {
			changeHandlerFactory = ExtensionPointUtil.loadSingleExtension(Activator.getExtensionRegistry(), IModelChangeHandlerFactory.class, Activator.OIDA_EXTENSIONPOINT_ID_MODEL_CHANGEHANDLER);

			if (changeHandlerFactory != null)
				LOGGER.info("Model Change Handler Factory set: '" + changeHandlerFactory.getClass().getName() + "'.");
			else
				LOGGER.error("No Model Change Handler Factory found.");
		} catch (CoreException e) {
			LOGGER.error("Error while evaluating Model Change Handler Factory extension point.", e);
		}

		LOGGER.info("Step 2/7: Evaluating model change handler renamer strategy extensions.");
		try {
			renamerStrategy = ExtensionPointUtil.loadSingleExtension(Activator.getExtensionRegistry(), IRenamerStrategy.class, Activator.OIDA_MODEL_RENAMERSTRATEGY);

			if (renamerStrategy != null)
				LOGGER.info("Renamer strategy set: '" + renamerStrategy.getClass().getName() + "'.");
			else
				LOGGER.error("No renamer strategy found.");
		} catch (CoreException e) {
			LOGGER.error("Error while evaluating renamer strategy extension point.", e);
		}

		LOGGER.info("Step 3/7: Evaluating model change handler structuring strategy extensions.");
		try {
			structuringStrategy = ExtensionPointUtil.loadSingleExtension(Activator.getExtensionRegistry(), IStructuringStrategy.class, Activator.OIDA_MODEL_STRUCTURINGSTRATEGY);

			if (structuringStrategy != null)
				LOGGER.info("Structuring strategy set: '" + structuringStrategy.getClass().getName() + "'.");
			else
				LOGGER.error("No structuring strategy found.");
		} catch (CoreException e) {
			LOGGER.error("Error while evaluating structuring strategy extension point.", e);
		}

		LOGGER.info("Step 4/7: Evaluating primary recommender extensions.");
		try {
			recommenderPrimary = ExtensionPointUtil.loadExtensions(Activator.getExtensionRegistry(), IRecommender.class, Activator.OIDA_RECOMMENDER_PRIMARY_EXTENSIONPOINT_ID);

			for (IRecommender r : recommenderPrimary)
				LOGGER.info("Primary Recommender registered: " + r.toString() + ".");
		} catch (CoreException e) {
			LOGGER.error("Error while evaluating primary recommender extension point.", e);
		}

		LOGGER.info("Step 5/7: Evaluating secondary recommender extensions.");
		try {
			recommenderSecondary = ExtensionPointUtil.loadExtensions(Activator.getExtensionRegistry(), IRecommender.class, Activator.OIDA_RECOMMENDER_SECONDARY_EXTENSIONPOINT_ID);

			for (IRecommender r : recommenderSecondary)
				LOGGER.info("Secondary Recommender registered: " + r.toString() + ".");
		} catch (CoreException e) {
			LOGGER.error("Error while evaluating secondary recommender extension point.", e);
		}

		LOGGER.info("Step 6/7: Loading OIDA model base ontology.");
		try {
			OntologyFile oidaModelOntologyFile = OIDAUtil.getOntologyFile(OIDAUtil.getOIDAWorkPath(), FileConstants.OIDAMODELONTOLOGY_FILENAME);
			Optional<IOntologyManager> optOntologyManager = oidaOntologyService.getOntologyManager(oidaModelOntologyFile, true);

			if (optOntologyManager.isPresent()) {
				OIDAModelBaseOntology.getInstance().loadOrInitializeOntology(optOntologyManager.get());
				LOGGER.info("Model Base Ontology loaded.");
			} else
				LOGGER.error("Error while loading the Model Base Ontology.");
		} catch (OntologyManagerException e) {
			LOGGER.error("Error while loading the Model Base Ontology.", e);
		}
		
		LOGGER.info("Step 7/7: Initializing the meta model ontology for the registered meta model.");
		OntologyFile metaModelOntologyFile = OIDAUtil.getOntologyFile(new File(OIDAUtil.getOIDAMetaModelOntologyPath(), renamerStrategy.getMetaModelName() + ".owl"));
		Optional<IOntologyManager> optMetaModelOntologyManager = oidaOntologyService.getOntologyManager(metaModelOntologyFile, true);
		if (optMetaModelOntologyManager.isPresent()) {
			changeHandlerFactory.initialize(oidaOntologyService, renamerStrategy, structuringStrategy, optMetaModelOntologyManager.get());
			
			currentMetaModelResource = editingDomain.createResource("http://de.oida/bridge/currentMetaModelResource");
			currentMetaModelResource.getContents().add(optMetaModelOntologyManager.get().getOntology());
			
			LOGGER.info("Meta Model Ontology created for '" + structuringStrategy.getMetaModelInformationObject().toString() + "'.");
		} else
			LOGGER.error("Error while creating the Meta Model Ontology for '" + structuringStrategy.getMetaModelInformationObject().toString() + "'.");
		
		currentPrimaryRecommendationsResource = editingDomain.createResource("http://de.oida/bridge/currentprimaryrecommendations");
		currentPrimaryRecommendationsResource.getContents().add(BridgemodelFactory.eINSTANCE.createRecommendationSet());

		currentSecondaryRecommendationsResource = editingDomain.createResource("http://de.oida/bridge/currentsecondaryrecommendations");
		currentSecondaryRecommendationsResource.getContents().add(BridgemodelFactory.eINSTANCE.createRecommendationSet());

		LOGGER.info("OIDA Bridge Service registered.");
	}

	@Override
	public void invokeModelObservation(final Object modelObject, final File modelOntologyDirectory, final String modelObjectId) throws OIDABridgeException {
		if (renamerStrategy == null)
			throw new OIDABridgeException("No renamer strategy found. Model won't be observed.");

		if (modelOntologyDirectory == null)
			throw new OIDABridgeException("No directory for a model ontology has been passed. Model won't be observed.");

		if (!modelOntologyDirectory.exists() && !modelOntologyDirectory.mkdirs())
			throw new OIDABridgeException("The directory for the model ontology doesn't exist/could not be created ['" + modelOntologyDirectory.toString() + "']. Model won't be observed.");

		File modelOntologyFile = new File(modelOntologyDirectory, generateModelOntologyFileName(modelObjectId));
		OntologyFile ontologyfile = OIDAUtil.getOntologyFile(modelOntologyFile);

		IModelChangeHandler changeHandler = changeHandlerFactory.getChangeHandler();
		
		changeHandler.initializeChangeHandler(renamerStrategy, structuringStrategy, changeHandlerFactory.getMetaModelOntology());

		Optional<IOntologyManager> optModelOntologyMgr = oidaOntologyService.getOntologyManager(ontologyfile, true);
		if (optModelOntologyMgr.isPresent())
			changeHandler.startChangeTracking(modelObject, optModelOntologyMgr.get());
		else
			LOGGER.error("Model observation could not be startet: Model ontology not found.");

		modelHandlerMap.put(modelObject, changeHandler);

		for (IRecommender rec : recommenderPrimary)
			rec.initializeRecommenderForModel(changeHandler.getModelOntologyManager().getOntology(), oidaOntologyService.getReferenceOntologyManager().getOntology());

		for (IRecommender rec : recommenderSecondary)
			rec.initializeRecommenderForModel(changeHandler.getModelOntologyManager().getOntology(), oidaOntologyService.getReferenceOntologyManager().getOntology());
	}

	@Override
	public void saveModelOntology(final Object modelObject) {
		try {
			if (modelHandlerMap.containsKey(modelObject))
				modelHandlerMap.get(modelObject).getModelOntologyManager().saveOntology();
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

			for (IRecommender rec : recommenderPrimary)
				recPrimarySet.getRecommendations().addAll(rec.findRecommendationsForSelectedModelElement(recPrimarySet.getOntologyEntity()));

			RecommendationSet recSecondarySet = (RecommendationSet)currentSecondaryRecommendationsResource.getContents().get(0);
			recSecondarySet.getRecommendations().clear();
			recSecondarySet.setModelObject(modelObject);
			recSecondarySet.setOntologyEntity(optOntEntity.get());

			for (IRecommender rec : recommenderSecondary)
				recSecondarySet.getRecommendations().addAll(rec.findRecommendationsForSelectedModelElement(recSecondarySet.getOntologyEntity()));
		}
		else
			LOGGER.warn("No Ontology Entity found for '" + firstSelectedElement.toString() + "'.");
	}

	private String generateModelOntologyFileName(String modelObjectId) throws OIDABridgeException {
		if (oidaOntologyService.getLibrary().getReferenceOntology() == null)
			throw new OIDABridgeException("No reference ontology set. Model won't be observed.");

		return modelObjectId + StringConstants.UNDERSCORE + oidaOntologyService.getLibrary().getReferenceOntology().getFileName();
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
	public Resource getCurrentMetaModelResource() {
		return currentMetaModelResource;
	}

	@Override
	public void establishPrimaryMapping(Recommendation selectedRecommendation) {
		RecommendationSet recSet = (RecommendationSet)currentPrimaryRecommendationsResource.getContents().get(0);
		IOntologyManager modelOntologyManager = modelHandlerMap.get(recSet.getModelObject()).getModelOntologyManager();

		if (recSet.getOntologyEntity() instanceof OntologyIndividual && selectedRecommendation.getRecommendedEntity() instanceof OntologyClass) {
			modelOntologyManager.assignIndividualToClass((OntologyIndividual)recSet.getOntologyEntity(), (OntologyClass)selectedRecommendation.getRecommendedEntity());
			LOGGER.info("Primary Mapping establisehd. Individual '" + recSet.getOntologyEntity().getIri() + "' is of type '" + selectedRecommendation.getRecommendedEntity().getIri() + "'");
		}
	}

	@Override
	public void establishSecondaryMapping(Recommendation selectedRecommendation) {
		RecommendationSet recSet = (RecommendationSet)currentSecondaryRecommendationsResource.getContents().get(0);
		IOntologyManager modelOntologyManager = modelHandlerMap.get(recSet.getModelObject()).getModelOntologyManager();

		if (recSet.getOntologyEntity() instanceof OntologyClass && selectedRecommendation.getRecommendedEntity() instanceof OntologyClass) {
			modelOntologyManager.assignClassEquivalence((OntologyClass)recSet.getOntologyEntity(), (OntologyClass)selectedRecommendation.getRecommendedEntity());
			LOGGER.info("Secondary Mapping establisehd. Class '" + recSet.getOntologyEntity().getIri() + "' is equivalent to class '" + selectedRecommendation.getRecommendedEntity().getIri() + "'");
		} else if (recSet.getOntologyEntity() instanceof OntologyObjectProperty && selectedRecommendation.getRecommendedEntity() instanceof OntologyObjectProperty) {
			modelOntologyManager.assignObjectPropertyEquivalence((OntologyObjectProperty)recSet.getOntologyEntity(), (OntologyObjectProperty)selectedRecommendation.getRecommendedEntity());
			LOGGER.info("Secondary Mapping establisehd. Object property '" + recSet.getOntologyEntity().getIri() + "' is equivalent to object property '"
					+ selectedRecommendation.getRecommendedEntity().getIri() + "'");
		}
	}

	@Override
	public Optional<IModelChangeHandler> getModelChangeHandler(Object model) {
		if (modelHandlerMap.containsKey(model))
			return Optional.of(modelHandlerMap.get(model));

		return Optional.empty();
	}
}
