/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.emf.ecore.util.EContentAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oida.ontology.manager.IOntologyManager;
import oida.ontologyMgr.OntologyFile;
import oida.util.constants.StringConstants;

/**
 * 
 * @author Michael Shamiyeh
 * @since 13.12.2016
 *
 */
public abstract class AbstractOIDAOntologyService extends EContentAdapter implements IOIDAOntologyService {
	protected static Logger LOGGER = LoggerFactory.getLogger(AbstractOIDAOntologyService.class);

	protected Map<String, OntologyFile> iriMappings = new HashMap<String, OntologyFile>();
	
	private IOntologyManager referenceOntologyManager;

	public AbstractOIDAOntologyService() {
	}
	
	public Optional<IOntologyManager> getReferenceOntologyManager() {
		if (referenceOntologyManager == null)
			return Optional.empty();
		
		return Optional.of(referenceOntologyManager);
	}
	
	protected void setReferenceOntologyManager(IOntologyManager ontologyManager) {
		this.referenceOntologyManager = ontologyManager;
	}
	
	@Override
	public boolean checkOntologyExistance(String iri) {
		return iriMappings.containsKey(iri);
	}
	
	@Override
	public boolean checkOntologyExistance(OntologyFile ontologyFile) {
		File checkFile = new File(ontologyFile.getPath() + ontologyFile.getFileName());
		
		if (checkFile.exists() && checkFile.isFile())
			return true;
		
		return false;
	}
	
	@Override
	public Optional<IOntologyManager> getOntologyManager(OntologyFile ontologyFile) {
		return getOntologyManager(ontologyFile, StringConstants.EMPTY, false);
	}
}
