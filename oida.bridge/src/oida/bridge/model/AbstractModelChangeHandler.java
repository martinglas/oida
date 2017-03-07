/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge.model;

import oida.bridge.model.renamer.IRenamerStrategy;
import oida.ontology.manager.IOntologyManager;

/**
 * 
 * @author Michael Shamiyeh, Martin Glas
 * @since 2017-01-20
 *
 */
public abstract class AbstractModelChangeHandler implements IModelChangeHandler {
	private IOntologyManager modelOntologyManager;
	private IRenamerStrategy renamerStrategy;
	
	public IOntologyManager getModelOntologyManager() {
		return modelOntologyManager;
	}
	
	public void setModelOntologyManager(IOntologyManager ontologyManager) {
		this.modelOntologyManager = ontologyManager;
	}
	
	public void registerRenamerStrategy(IRenamerStrategy renamerStrategy) {
		this.renamerStrategy = renamerStrategy;
	}
}
