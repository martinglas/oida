/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.service;

import java.util.Optional;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;

import oida.ontology.manager.IOntologyManager;
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
	
	public Optional<IOntologyManager> getReferenceOntologyManager();

	public EditingDomain getEditingDomain();
	
	public boolean checkOntologyExistance(OntologyFile ontologyFile);
	public boolean checkOntologyExistance(String iri);
	
	//public Optional<IOntologyManager> getOntologyManager(String ontologyIri);
	//public Optional<IOntologyManager> getOntologyManager(OntologyFile ontologyFile);
	public Optional<IOntologyManager> getOntologyManager(OntologyFile ontologyFile);
	public Optional<IOntologyManager> getOntologyManager(OntologyFile ontologyFile, boolean localHierarchyOnly);
	public Optional<IOntologyManager> getOntologyManager(OntologyFile ontologyFile, String ontologyIri, boolean createIfNotExisting);
	public Optional<IOntologyManager> getOntologyManager(OntologyFile ontologyFile, String ontologyIri, boolean createIfNotExisting, boolean localHierarchyOnly);
}
