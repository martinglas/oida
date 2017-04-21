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
import oida.bridge.emf.EMFModelChangeHandlerFactory;
import oida.bridge.model.IModelChangeHandler;
import oida.bridge.model.renamer.IRenamerStrategy;
import oida.bridge.model.renamer.IStructuringStrategy;
import oida.bridge.recommender.IRecommender;
import oida.ontology.OntologyClass;
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

	private List<IRecommender> recommenderPrimary;
	private List<IRecommender> recommenderSecondary;

	private IRenamerStrategy renamerStrategy;
	private IStructuringStrategy structuringStrategy;

	private Resource currentPrimaryRecommendationsResource;
	private Resource currentSecondaryRecommendationsResource;

	private IOIDAOntologyService oidaOntologyService;

	@Inject
	public OIDABridge(IOIDAOntologyService oidaOntologyService) {
		LOGGER.info("Initializing OIDA Bridge Service...");
		this.oidaOntologyService = oidaOntologyService;
		modelHandlerMap.clear();

		LOGGER.info("Evaluating model change handler renamer strategy extensions.");
		try {
			renamerStrategy = ExtensionPointUtil.loadSingleExtension(Activator.getExtensionRegistry(), IRenamerStrategy.class, Activator.OIDA_MODEL_RENAMERSTRATEGY);

			if (renamerStrategy != null)
				LOGGER.info("Renamer strategy set: '" + renamerStrategy.getClass().getName() + "'.");
			else
				LOGGER.error("No renamer strategy found.");
		} catch (CoreException e) {
			LOGGER.error("Error while evaluating renamer strategy extension point.", e);
		}

		LOGGER.info("Evaluating model change handler structuring strategy extensions.");
		try {
			structuringStrategy = ExtensionPointUtil.loadSingleExtension(Activator.getExtensionRegistry(), IStructuringStrategy.class, Activator.OIDA_MODEL_STRUCTURINGSTRATEGY);

			if (structuringStrategy != null)
				LOGGER.info("Structuring strategy set: '" + structuringStrategy.getClass().getName() + "'.");
			else
				LOGGER.error("No structuring strategy found.");
		} catch (CoreException e) {
			LOGGER.error("Error while evaluating structuring strategy extension point.", e);
		}

		try {
			recommenderPrimary = ExtensionPointUtil.loadExtensions(Activator.getExtensionRegistry(), IRecommender.class, Activator.OIDA_RECOMMENDER_PRIMARY_EXTENSIONPOINT_ID);

			for (IRecommender r : recommenderPrimary)
				LOGGER.info("Primary Recommender registered: " + r.toString() + ".");
		} catch (CoreException e) {
			LOGGER.error("Error while evaluating primary recommender extension point.", e);
		}

		try {
			recommenderSecondary = ExtensionPointUtil.loadExtensions(Activator.getExtensionRegistry(), IRecommender.class, Activator.OIDA_RECOMMENDER_SECONDARY_EXTENSIONPOINT_ID);

			for (IRecommender r : recommenderSecondary)
				LOGGER.info("Secondary Recommender registered: " + r.toString() + ".");
		} catch (CoreException e) {
			LOGGER.error("Error while evaluating secondary recommender extension point.", e);
		}

		OntologyFile emfOntologyFile = OIDAUtil.getOntologyFile(OIDAUtil.getOIDAWorkPath(), FileConstants.EMFONTOLOGY_FILENAME);

		Optional<IOntologyManager> optEMFModelOntology = oidaOntologyService.getOntologyManager(emfOntologyFile, true);
		if (optEMFModelOntology.isPresent())
			EMFModelChangeHandlerFactory.getInstance().initialize(optEMFModelOntology.get());
		else
			LOGGER.error("Error: EMF Model Ontology could not be created. OIDA Bridge is not ready.");

		BridgemodelItemProviderAdapterFactory adapterFactory = new BridgemodelItemProviderAdapterFactory();

		ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(adapterFactory);
		composedAdapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());

		AdapterFactoryEditingDomain editingDomain = new AdapterFactoryEditingDomain(composedAdapterFactory, new BasicCommandStack());
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

		IModelChangeHandler changeHandler = EMFModelChangeHandlerFactory.getInstance().createModelChangeHandler();
		changeHandler.initializeChangeHandler(renamerStrategy, structuringStrategy);

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

		RecommendationSet recPrimarySet = (RecommendationSet)currentPrimaryRecommendationsResource.getContents().get(0);
		recPrimarySet.getRecommendations().clear();
		recPrimarySet.setModelObject(modelObject);
		recPrimarySet.setOntologyEntity(modelHandlerMap.get(modelObject).getOntologyEntityForModelElement(firstSelectedElement));

		for (IRecommender rec : recommenderPrimary)
			recPrimarySet.getRecommendations().addAll(rec.findRecommendationsForSelectedModelElement(recPrimarySet.getOntologyEntity()));

		RecommendationSet recSecondarySet = (RecommendationSet)currentSecondaryRecommendationsResource.getContents().get(0);
		recSecondarySet.getRecommendations().clear();
		recSecondarySet.setModelObject(modelObject);
		recSecondarySet.setOntologyEntity(modelHandlerMap.get(modelObject).getOntologyEntityForModelElement(firstSelectedElement));

		for (IRecommender rec : recommenderSecondary)
			recSecondarySet.getRecommendations().addAll(rec.findRecommendationsForSelectedModelElement(recSecondarySet.getOntologyEntity()));
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
