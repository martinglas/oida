/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.service;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;

import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.OntologyManagerException;
import oida.ontologyMgr.Library;
import oida.ontologyMgr.OntologyFile;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 13.12.2016
 *
 */
public interface IOIDAOntologyService {
	Library getLibrary();

	Resource getLibraryResource();

	Resource getManagedOntologiesResource();

	public EditingDomain getEditingDomain();
	
	public IOntologyManager getOntologyManager(OntologyFile entry, boolean createIfNotExisting);
	
	public IOntologyManager getMereology() throws OntologyManagerException;
}
