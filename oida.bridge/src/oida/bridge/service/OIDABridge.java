/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.emf.ecore.EObject;
import org.osgi.framework.ServiceReference;

import oida.bridge.Activator;
import oida.bridge.model.IModelChangeHandler;
import oida.bridge.model.ModelChangeHandler;
import oida.bridge.model.renamer.IRenamerStrategy;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.OntologyManagerException;
import oida.ontology.service.IOIDAOntologyService;
import oida.ontologyMgr.OntologyFile;
import oida.util.OIDAUtil;

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

	private IRenamerStrategy renamerStrategy;

	@Inject
	private IOIDAOntologyService oidaOntologyService;

	public OIDABridge() {
		modelHandlerMap.clear();
		
		renamerStrategy = loadRenamerStrategyExtension();
		
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
				 throw new OIDABridgeException(MSG_PREFIX + "The directory for the model ontology doesn't exist/could not be created ['" + modelOntologyDirectory.toString() + "']. Model won't be observed.");
		
		File modelOntologyFile = new File(modelOntologyDirectory, generateModelOntologyFileName(modelObjectId));
		
		try {
			OntologyFile ontologyfile = OIDAUtil.getOntologyFile(modelOntologyFile);
			IOntologyManager modelOntologyManager = oidaOntologyService.getOntologyManager(ontologyfile, false);

			if (modelOntologyManager == null) {
				modelOntologyManager = oidaOntologyService.getOntologyManager(ontologyfile, true);
				modelOntologyManager.addImportDeclaration(oidaOntologyService.getMereology().getOntology());
			}

			IModelChangeHandler changeHandler = new ModelChangeHandler();
			changeHandler.setModelOntologyManager(modelOntologyManager);
			changeHandler.initializeModelOntology((EObject)modelObject, renamerStrategy);

			changeHandler.registerRenamerStrategy(renamerStrategy);
			modelHandlerMap.put(changeHandler.getModelObject(), changeHandler);
		} catch (OntologyManagerException e) {
			throw new OIDABridgeException(MSG_PREFIX + "Could not create a model ontology.", e);
		}
	}
	
	private String generateModelOntologyFileName(String modelObjectId) throws OIDABridgeException {
		if (oidaOntologyService.getLibrary().getReferenceOntology() == null)
			throw new OIDABridgeException(MSG_PREFIX + "No reference ontology set. Model won't be observed.");
		
		return modelObjectId + "_" + oidaOntologyService.getLibrary().getReferenceOntology().getFileName();
	}

	private IRenamerStrategy loadRenamerStrategyExtension() {
		ServiceReference<?> serviceReference = Activator.getBundleContext().getServiceReference(IExtensionRegistry.class.getName());
		IExtensionRegistry registry = (IExtensionRegistry)Activator.getBundleContext().getService(serviceReference);

		if (registry != null) {
			IConfigurationElement[] config = registry.getConfigurationElementsFor(Activator.OIDA_MODEL_CHANGEHANDLER_RENAMER_EXTENSIONPOINT_ID);
			try {
				for (IConfigurationElement e : config) {
					System.out.println("OIDA Bridge: Evaluating model change handler renamer strategy extensions.");
					final Object o = e.createExecutableExtension("class");
					if (o instanceof IRenamerStrategy) {
						System.out.println("OIDA Bridge: Renamer strategy set: '" + o.getClass().getName() + "'.");
						return (IRenamerStrategy)o;
					}
				}
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}

		return null;
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
}
