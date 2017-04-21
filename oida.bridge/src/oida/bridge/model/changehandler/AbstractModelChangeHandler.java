/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge.model.changehandler;

import oida.bridge.model.strategy.IRenamerStrategy;
import oida.bridge.model.strategy.IStructuringStrategy;
import oida.ontology.manager.IOntologyManager;

/**
 * 
 * @author Michael Shamiyeh, Martin Glas
 * @since 2017-01-20
 *
 */
public abstract class AbstractModelChangeHandler implements IModelChangeHandler {
	private IOntologyManager modelOntologyManager;
	
	private Object modelObject;
	
	private IRenamerStrategy renamerStrategy;
	private IStructuringStrategy structuringStrategy;
	
	public IOntologyManager getModelOntologyManager() {
		return modelOntologyManager;
	}
	
	protected void setModelOntologyManager(IOntologyManager ontologyManager) {
		this.modelOntologyManager = ontologyManager;
	}
	
	public Object getModelObject() {
		return modelObject;
	}
	
	protected void setModelObject(Object modelObject) {
		this.modelObject = modelObject;
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
	
	@Override
	public void initializeChangeHandler(IRenamerStrategy renamerStrategy, IStructuringStrategy structuringStrategy) {
		setRenamerStrategy(renamerStrategy);
		setStructuringStrategy(structuringStrategy);
	}
	
	@Override
	public void startChangeTracking(Object modelObject, IOntologyManager modelOntologyManager) {
		setModelObject(modelObject);
		initializeModelOntology(modelOntologyManager);
	}

	protected abstract void initializeModelOntology(IOntologyManager modelOntologyManager);
}
