/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge.service;

import java.io.File;

import org.eclipse.emf.ecore.EObject;

import oida.bridge.model.renamer.IRenamerStrategy;
import oida.ontology.service.IOIDAOntologyService;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-03-03
 *
 */
public interface IOIDABridge {
	void setOntologyService(IOIDAOntologyService ontologyService);
	
	void invokeModelObservation(Object modelObject, File modelOntologyDirectory) throws OIDABridgeException;
	
	void addChangeHandler(Object modelObject, File modelOntologyFile) throws OIDABridgeException;
	void removeChangeHandler(Object modelObject);
	
	void registerRenamerStrategy(IRenamerStrategy renamerStrategy);
	
	void saveModelOntology(EObject newEObject);
}
