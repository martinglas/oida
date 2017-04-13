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

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;

import bridgemodel.BridgemodelFactory;
import bridgemodel.Recommendation;
import bridgemodel.RecommendationSet;
import bridgemodel.provider.BridgemodelItemProviderAdapterFactory;
import oida.bridge.Activator;
import oida.bridge.emf.EMFModelChangeHandlerFactory;
import oida.bridge.model.IModelChangeHandler;
import oida.bridge.model.renamer.IRenamerStrategy;
import oida.bridge.model.renamer.IStructuringStrategy;
import oida.bridge.recommend.IRecommender;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyIndividual;
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
	private final String MSG_PREFIX = "OIDA Bridge: ";

	private Map<EObject, IModelChangeHandler> modelHandlerMap = new HashMap<EObject, IModelChangeHandler>();

	private List<IRecommender> recommender;

	private IRenamerStrategy renamerStrategy;
	private IStructuringStrategy structuringStrategy;

	private Resource currentRecommendationsResource;

	private IOIDAOntologyService oidaOntologyService;

	@Inject
	public OIDABridge(IOIDAOntologyService oidaOntologyService) {
		System.out.println(MSG_PREFIX + "Initializing service...");
		this.oidaOntologyService = oidaOntologyService;
		modelHandlerMap.clear();

		System.out.println(MSG_PREFIX + "Evaluating model change handler renamer strategy extensions.");
		try {
			renamerStrategy = ExtensionPointUtil.loadSingleExtension(Activator.getExtensionRegistry(), IRenamerStrategy.class, Activator.OIDA_MODEL_RENAMERSTRATEGY);

			if (renamerStrategy != null)
				System.out.println(MSG_PREFIX + "Renamer strategy set: '" + renamerStrategy.getClass().getName() + "'.");
			else
				System.out.println(MSG_PREFIX + "No renamer strategy found.");
		} catch (CoreException e) {
			System.out.println(MSG_PREFIX + "Error while evaluating renamer strategy extension point.");
			e.printStackTrace();
		}

		System.out.println(MSG_PREFIX + "Evaluating model change handler structuring strategy extensions.");
		try {
			structuringStrategy = ExtensionPointUtil.loadSingleExtension(Activator.getExtensionRegistry(), IStructuringStrategy.class, Activator.OIDA_MODEL_STRUCTURINGSTRATEGY);

			if (structuringStrategy != null)
				System.out.println(MSG_PREFIX + "Structuring strategy set: '" + structuringStrategy.getClass().getName() + "'.");
			else
				System.out.println(MSG_PREFIX + "No structuring strategy found.");
		} catch (CoreException e) {
			System.out.println(MSG_PREFIX + "Error while evaluating structuring strategy extension point.");
			e.printStackTrace();
		}

		try {
			recommender = ExtensionPointUtil.loadExtensions(Activator.getExtensionRegistry(), IRecommender.class, Activator.OIDA_RECOMMENDER_EXTENSIONPOINT_ID);
		} catch (CoreException e) {
			e.printStackTrace();
		}

		for (IRecommender r : recommender)
			System.out.println(MSG_PREFIX + "Recommender registered: " + r.toString() + ".");

		OntologyFile emfOntologyFile = OIDAUtil.getOntologyFile(OIDAUtil.getOIDAWorkPath(), FileConstants.EMFONTOLOGY_FILENAME);
		EMFModelChangeHandlerFactory.getInstance().initialize(oidaOntologyService.getOntologyManager(emfOntologyFile, true));

		BridgemodelItemProviderAdapterFactory adapterFactory = new BridgemodelItemProviderAdapterFactory();

		ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(adapterFactory);
		composedAdapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());

		AdapterFactoryEditingDomain editingDomain = new AdapterFactoryEditingDomain(composedAdapterFactory, new BasicCommandStack());
		currentRecommendationsResource = editingDomain.createResource("http://de.oida/bridge/currentrecommendations");
		currentRecommendationsResource.getContents().add(BridgemodelFactory.eINSTANCE.createRecommendationSet());

		System.out.println(MSG_PREFIX + "Service registered.");
	}

	@Override
	public void invokeModelObservation(final EObject modelObject, final File modelOntologyDirectory, final String modelObjectId) throws OIDABridgeException {
		if (renamerStrategy == null)
			throw new OIDABridgeException(MSG_PREFIX + "No renamer strategy found. Model won't be observed.");

		if (modelOntologyDirectory == null)
			throw new OIDABridgeException(MSG_PREFIX + "No directory for a model ontology has been passed. Model won't be observed.");

		if (!modelOntologyDirectory.exists())
			if (!modelOntologyDirectory.mkdirs())
				throw new OIDABridgeException(
						MSG_PREFIX + "The directory for the model ontology doesn't exist/could not be created ['" + modelOntologyDirectory.toString() + "']. Model won't be observed.");

		File modelOntologyFile = new File(modelOntologyDirectory, generateModelOntologyFileName(modelObjectId));

		try {
			OntologyFile ontologyfile = OIDAUtil.getOntologyFile(modelOntologyFile);
			IOntologyManager modelOntologyManager = oidaOntologyService.getOntologyManager(ontologyfile, false);

			if (modelOntologyManager == null) {
				modelOntologyManager = oidaOntologyService.getOntologyManager(ontologyfile, true);
				modelOntologyManager.addImportDeclaration(oidaOntologyService.getReferenceOntologyManager().getOntology());
			}

			IModelChangeHandler changeHandler = EMFModelChangeHandlerFactory.getInstance().createModelChangeHandler();
			changeHandler.setOntologyService(oidaOntologyService);
			changeHandler.setModelOntologyManager(modelOntologyManager);
			changeHandler.initializeModelOntology((EObject)modelObject, renamerStrategy, structuringStrategy);

			changeHandler.setRenamerStrategy(renamerStrategy);
			modelHandlerMap.put(changeHandler.getModelObject(), changeHandler);

			for (IRecommender rec : recommender) {
				rec.initializeRecommenderForModel(modelOntologyManager.getOntology(), oidaOntologyService.getReferenceOntologyManager().getOntology());
			}
		} catch (OntologyManagerException e) {
			throw new OIDABridgeException(MSG_PREFIX + "Could not create a model ontology.", e);
		}
	}

	@Override
	public void saveModelOntology(final EObject modelObject) {
		try {
			if (modelHandlerMap.containsKey(modelObject))
				modelHandlerMap.get(modelObject).getModelOntologyManager().saveOntology();
		} catch (OntologyManagerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stopModelObservation(final EObject modelObject) {
		modelHandlerMap.get(modelObject).closeModelOntology();
		modelHandlerMap.remove(modelObject);
	}

	@Override
	public void reportModelSelectionChanged(EObject modelObject, EObject firstSelectedElement) {
		if (modelObject == null || firstSelectedElement == null)
			return;
		
		RecommendationSet recSet = (RecommendationSet)currentRecommendationsResource.getContents().get(0);
		recSet.getRecommendations().clear();
		recSet.setModelObject(modelObject);
		recSet.setOntologyEntity(modelHandlerMap.get(modelObject).getOntologyEntityForModelElement(firstSelectedElement));

		for (IRecommender rec : recommender)
			recSet.getRecommendations().addAll(rec.findRecommendationsForSelectedModelElement(recSet.getOntologyEntity()));
	}

	private String generateModelOntologyFileName(String modelObjectId) throws OIDABridgeException {
		if (oidaOntologyService.getLibrary().getReferenceOntology() == null)
			throw new OIDABridgeException(MSG_PREFIX + "No reference ontology set. Model won't be observed.");

		return modelObjectId + StringConstants.UNDERSCORE + oidaOntologyService.getLibrary().getReferenceOntology().getFileName();
	}

	@Override
	public Resource getCurrentRecommendationsResource() {
		return currentRecommendationsResource;
	}

	@Override
	public void establishMapping(Recommendation selectedRecommendation) {
		RecommendationSet recSet = (RecommendationSet)currentRecommendationsResource.getContents().get(0);
		IOntologyManager modelOntologyManager = modelHandlerMap.get(recSet.getModelObject()).getModelOntologyManager();

		if (recSet.getOntologyEntity() instanceof OntologyIndividual && selectedRecommendation.getRecommendedEntity() instanceof OntologyClass) {
			modelOntologyManager.assignIndividualToClass((OntologyIndividual)recSet.getOntologyEntity(), (OntologyClass)selectedRecommendation.getRecommendedEntity());
			
			System.out.println(MSG_PREFIX + "Mapping establisehd. Individual '" + recSet.getOntologyEntity().getIri() + "' is of type '" + selectedRecommendation.getRecommendedEntity().getIri() + "'");
		}
	}
}
