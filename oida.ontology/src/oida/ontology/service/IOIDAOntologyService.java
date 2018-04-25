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
import oida.ontology.manager.context.IGlobalOntologyContext;
import oida.ontologyMgr.Library;
import oida.ontologyMgr.LocalOntologyMetaInfo;
import oida.ontologyMgr.OntologyMetaInfo;

/**
 * 
 * @author Michael Shamiyeh
 * @since 13.12.2016
 *
 */
public interface IOIDAOntologyService extends IGlobalOntologyContext {
	Library getLibrary();
	Resource getLibraryResource();
	
	public void AddOntologyToLibrary(OntologyMetaInfo metaInfo);
	public void SetReferenceOntology(OntologyMetaInfo metaInfo);
	
	public void saveLibraryResource();
	
	public void autoLoadOntologies();

	public EditingDomain getEditingDomain();
	
	public boolean checkLocalOntologyExistance(LocalOntologyMetaInfo ontologyMetaInfo);
	public boolean checkOntologyExistance(String iri);
	
	public Optional<IOntologyManager> getReferenceOntologyManager();
	public Optional<IOntologyManager> loadReferenceOntology();
	
	public Optional<IOntologyManager> getRemoteOntologyManager(OntologyMetaInfo ontologyMetaInfo);
	public Optional<IOntologyManager> getLocalOntologyManager(LocalOntologyMetaInfo ontologyMetaInfo);
	public Optional<IOntologyManager> createLocalOntology(LocalOntologyMetaInfo ontologyMetaInfo, String IRI);
}
