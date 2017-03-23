/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge.model;

import org.eclipse.emf.ecore.EObject;

import oida.bridge.model.renamer.IRenamerStrategy;
import oida.bridge.model.renamer.IStructuringStrategy;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.service.IOIDAOntologyService;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-01-20
 *
 */
public interface IModelChangeHandler {
	void setOntologyService(IOIDAOntologyService ontologyService);
	
	IRenamerStrategy getRenamerStrategy();
	void setRenamerStrategy(IRenamerStrategy renamerStrategy);
	
	IStructuringStrategy getStructuringStrategy();
	void setStructuringStrategy(IStructuringStrategy structuringStrategy);
	
	IOntologyManager getModelOntologyManager();
	void setModelOntologyManager(IOntologyManager ontologyManager);
	
	void initializeModelOntology(EObject modelObject, IRenamerStrategy renamerStrategy, IStructuringStrategy structuringStrategy);
	
	EObject getModelObject();
	
	void closeModelOntology();
}
