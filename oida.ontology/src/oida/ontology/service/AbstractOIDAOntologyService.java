/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.service;

import java.util.Optional;

import org.eclipse.emf.ecore.util.EContentAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oida.ontology.manager.IOntologyManager;
import oida.ontology.predefined.Mereology;
import oida.ontologyMgr.OntologyFile;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 13.12.2016
 *
 */
public abstract class AbstractOIDAOntologyService extends EContentAdapter implements IOIDAOntologyService {
	protected static Logger LOGGER = LoggerFactory.getLogger(AbstractOIDAOntologyService.class);

	private IOntologyManager referenceOntologyManager;

	public AbstractOIDAOntologyService() {
	}
	
	public IOntologyManager getReferenceOntologyManager() {
		return referenceOntologyManager;
	}
	
	protected void setReferenceOntologyManager(IOntologyManager ontologyManager) {
		this.referenceOntologyManager = ontologyManager;
	}
	
	public Optional<IOntologyManager> getOntologyManager(String ontologyIri) {
		return getOntologyManager(ontologyIri, null, false);
	}
	
	public Optional<IOntologyManager> getOntologyManager(OntologyFile ontologyFile) {
		return getOntologyManager(null, ontologyFile, false);
	}
	
	public Optional<IOntologyManager> getOntologyManager(OntologyFile ontologyFile, boolean createIfNotExisting) {
		return getOntologyManager(null, ontologyFile, createIfNotExisting);
	}
}
