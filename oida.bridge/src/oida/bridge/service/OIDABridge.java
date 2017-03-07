/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import oida.bridge.model.IModelChangeHandler;
import oida.bridge.model.ModelChangeHandler;
import oida.bridge.model.renamer.IRenamerStrategy;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.OntologyManagerException;
import oida.ontology.service.IOIDAOntologyService;
import oida.ontologyMgr.OntologyFile;
import oida.util.OIDAUtil;

/**
 * @author Michael.Shamiyeh
 * @since 2017-03-03
 *
 */
public class OIDABridge implements IOIDABridge {
	private Map<EObject, IModelChangeHandler> modelHandlerMap = new HashMap<EObject, IModelChangeHandler>();
	
	private IRenamerStrategy renamerStrategy;

	private IOIDAOntologyService oidaService;

	private static IOIDABridge instance;

	public static IOIDABridge getInstance() {
		if (instance == null)
			instance = new OIDABridge();

		return instance;
	}

	private OIDABridge() {
		modelHandlerMap.clear();
	}

	@Override
	public void registerRenamerStrategy(IRenamerStrategy renamerStrategy) {
		this.renamerStrategy = renamerStrategy;
	}

	@Override
	public void addChangeHandler(Object modelObject, File modelOntologyFile) throws OIDABridgeException {
		if (!(modelObject instanceof EObject))
			throw new OIDABridgeException("Object is not of type 'EObject'.");

		try {
			OntologyFile modelOntologyfile = OIDAUtil.getOntologyFile(modelOntologyFile);
			IOntologyManager modelOntologyManager = oidaService.getOntologyManager(modelOntologyfile, false);

			if (modelOntologyManager == null) {
				modelOntologyManager = oidaService.getOntologyManager(modelOntologyfile, true);
				modelOntologyManager.addImportDeclaration(oidaService.getMereology().getOntology());
			}

			IModelChangeHandler changeHandler = new ModelChangeHandler();
			changeHandler.setModelOntologyManager(modelOntologyManager);
			changeHandler.initializeModelOntology((EObject)modelObject, renamerStrategy);

			changeHandler.registerRenamerStrategy(renamerStrategy);
			modelHandlerMap.put(changeHandler.getModelObject(), changeHandler);
		} catch (OntologyManagerException e) {
			throw new OIDABridgeException("OIDA bridge could not create a model ontology.", e);
		}
	}

	@Override
	public void saveModelOntology(EObject modelObject) {
		try {
			modelHandlerMap.get(modelObject).getModelOntologyManager().saveOntology();
		} catch (OntologyManagerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeChangeHandler(Object modelObject) {
		modelHandlerMap.get(modelObject).closeModelOntology();
		modelHandlerMap.remove(modelObject);
	}

	@Override
	public void setOntologyService(IOIDAOntologyService ontologyService) {
		this.oidaService = ontologyService;
	}
}
