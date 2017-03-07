/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge.model;

import org.eclipse.emf.ecore.EObject;

import oida.bridge.model.renamer.IRenamerStrategy;
import oida.ontology.manager.IOntologyManager;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-01-20
 *
 */
public interface IModelChangeHandler {
	void registerRenamerStrategy(IRenamerStrategy renamerStrategy);
	
	IOntologyManager getModelOntologyManager();
	void setModelOntologyManager(IOntologyManager ontologyManager);
	
	void initializeModelOntology(EObject modelObject, IRenamerStrategy renamerStrategy);
	
	EObject getModelObject();
	
	void closeModelOntology();
	
}
