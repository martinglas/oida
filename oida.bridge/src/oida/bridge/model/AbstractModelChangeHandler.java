/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge.model;

import org.eclipse.emf.ecore.util.EContentAdapter;

import oida.bridge.model.renamer.IRenamerStrategy;
import oida.bridge.model.renamer.IStructuringStrategy;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.service.IOIDAOntologyService;

/**
 * 
 * @author Michael Shamiyeh, Martin Glas
 * @since 2017-01-20
 *
 */
public abstract class AbstractModelChangeHandler extends EContentAdapter implements IModelChangeHandler {
	private IOIDAOntologyService ontologyService;
	private IOntologyManager modelOntologyManager;
	
	private IRenamerStrategy renamerStrategy;
	private IStructuringStrategy structuringStrategy;
	
	public void setOntologyService(IOIDAOntologyService ontologyService) {
		this.ontologyService = ontologyService;
	}
	
	public IOIDAOntologyService getOntologyService() {
		return ontologyService;
	}
	
	public IOntologyManager getModelOntologyManager() {
		return modelOntologyManager;
	}
	
	public void setModelOntologyManager(IOntologyManager ontologyManager) {
		this.modelOntologyManager = ontologyManager;
	}
	
	public IRenamerStrategy getRenamerStrategy() {
		return renamerStrategy;
	}
	
	public void setRenamerStrategy(IRenamerStrategy renamerStrategy) {
		this.renamerStrategy = renamerStrategy;
	}
	
	public IStructuringStrategy getStructuringStrategy() {
		return structuringStrategy;
	}
	
	public void setStructuringStrategy(IStructuringStrategy structuringStrategy) {
		this.structuringStrategy = structuringStrategy;
	}
}
