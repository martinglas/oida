/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge.model.changehandler;

import java.util.Optional;

import oida.bridge.model.strategy.IRenamerStrategy;
import oida.bridge.model.strategy.IStructuringStrategy;
import oida.ontology.OntologyEntity;
import oida.ontology.manager.IOntologyManager;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-01-20
 *
 */
public interface IModelChangeHandler {
	IRenamerStrategy getRenamerStrategy();
	void setRenamerStrategy(IRenamerStrategy renamerStrategy);
	
	IStructuringStrategy getStructuringStrategy();
	void setStructuringStrategy(IStructuringStrategy structuringStrategy);
	
	IOntologyManager getModelOntologyManager();
	
	String getModelOntologyPrefix();
	void setModelOntologyPrefix();
	
	void initializeChangeHandler(IRenamerStrategy renamerStrategy, IStructuringStrategy structuringStrategy);
	
	void startChangeTracking(Object modelObject, IOntologyManager modelOntologyManager);

	Object getModelObject();
	
	Optional<OntologyEntity> getOntologyEntityForModelElement(Object modelElement);
	
	void closeModelOntology();
}
