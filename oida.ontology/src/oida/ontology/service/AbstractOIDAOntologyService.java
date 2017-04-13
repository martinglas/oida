/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.service;

import org.eclipse.emf.ecore.util.EContentAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.OntologyManagerException;
import oida.ontology.predefined.Mereology;
import oida.ontologyMgr.OntologyFile;
import oida.util.OIDAUtil;
import oida.util.constants.FileConstants;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 13.12.2016
 *
 */
public abstract class AbstractOIDAOntologyService extends EContentAdapter implements IOIDAOntologyService {
	protected static Logger LOGGER = LoggerFactory.getLogger(AbstractOIDAOntologyService.class);

	private IOntologyManager referenceOntologyManager;
	
	private Mereology mereology;
	
	public AbstractOIDAOntologyService() {
	}
	
	@Override
	public Mereology getMereology() {
		if (mereology == null) {
			OntologyFile mereologyFile = OIDAUtil.getOntologyFile(OIDAUtil.getOIDAWorkPath(), FileConstants.MEREOLOGY_FILENAME);
			IOntologyManager mereologyOntology = getOntologyManager(mereologyFile, true);
			
			mereology = new Mereology();
			
			try {
				mereology.loadOrInitializeOntology(mereologyOntology);
			} catch (OntologyManagerException e) {
				LOGGER.error("Mereology couldn't be saved!", e);
			}
			
			LOGGER.info("Mereology successfully generated.");
		}
			
		return mereology;
	}
	
	
	public IOntologyManager getReferenceOntologyManager() {
		return referenceOntologyManager;
	}
	
	protected void setReferenceOntologyManager(IOntologyManager ontologyManager) {
		this.referenceOntologyManager = ontologyManager;
	}
}
