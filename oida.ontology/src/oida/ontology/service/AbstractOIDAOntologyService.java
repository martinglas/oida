/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.service;

import org.eclipse.emf.ecore.util.EContentAdapter;

import oida.core.constants.FileConstants;
import oida.core.util.OIDAUtil;
import oida.ontology.OntologyObjectProperty;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.OntologyManagerException;
import oida.ontologyMgr.OntologyFile;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 13.12.2016
 *
 */
public abstract class AbstractOIDAOntologyService extends EContentAdapter implements IOIDAOntologyService {
	public static final String OIDAONTOLOGY_SERVICE_NAME = "OIDA Ontology Service";

	public static final String MEREOLOGY_NAMESPACE = "http://www.bauhaus-luftfahrt.net/ontologies/merology.owl";
	public static final String MEREOLOGY_PREFIX = "mer";
	
	private OntologyObjectProperty partOfDirectlyObjectProperty;
	private OntologyObjectProperty hasPartDirectlyObjectProperty;
	
	private IOntologyManager mereology;
	
	public AbstractOIDAOntologyService() {
		try {
			mereology = generateMereology();
			System.out.println(OIDAONTOLOGY_SERVICE_NAME + ": Mereology successfully generated.");
		} catch (OntologyManagerException e) {
			System.out.println(OIDAONTOLOGY_SERVICE_NAME + ": Mereology couldn't be generated.");
			e.printStackTrace();
		}
	}
	
	private IOntologyManager generateMereology() throws OntologyManagerException {
		OntologyFile mereologyFile = OIDAUtil.getOntologyFile(OIDAUtil.getOIDAWorkPath(), FileConstants.MEREOLOGY_FILENAME);
		
		IOntologyManager mereologyOntology = getOntologyManager(mereologyFile, true);
		
		if (mereologyOntology.getOntology().getNrOfObjectProperties() == 0) {
			mereologyOntology.addNamespace(MEREOLOGY_PREFIX, MEREOLOGY_NAMESPACE, false);
			
			OntologyObjectProperty hasPartObjectProperty = mereologyOntology.createObjectProperty("hasPart", MEREOLOGY_PREFIX);
			mereologyOntology.setObjectPropertyCharacteristics(hasPartObjectProperty, false, false, true, false, false, false, false);
			
			hasPartDirectlyObjectProperty = mereologyOntology.createObjectProperty("hasPart_directly", MEREOLOGY_PREFIX);
			mereologyOntology.assignSubObjectPropertyToSuperObjectProperty(hasPartDirectlyObjectProperty, hasPartObjectProperty);
			
			OntologyObjectProperty partOfObjectProperty = mereologyOntology.createObjectProperty("partOf", MEREOLOGY_PREFIX);
			mereologyOntology.setObjectPropertyCharacteristics(partOfObjectProperty, false, false, true, false, false, false, false);
			mereologyOntology.assignInverseObjectProperty(partOfObjectProperty, hasPartObjectProperty);
			
			partOfDirectlyObjectProperty = mereologyOntology.createObjectProperty("partOf_directly", MEREOLOGY_PREFIX);
			mereologyOntology.assignSubObjectPropertyToSuperObjectProperty(partOfDirectlyObjectProperty, partOfObjectProperty);
			mereologyOntology.assignInverseObjectProperty(partOfDirectlyObjectProperty, hasPartDirectlyObjectProperty);
			
			mereologyOntology.saveOntology();
		}
		
		return mereologyOntology;
	}
	
	@Override
	public IOntologyManager getMereology() {
		return mereology;
	}
	
	@Override
	public OntologyObjectProperty getPartOfProperty() {
		return partOfDirectlyObjectProperty;
	}

	@Override
	public OntologyObjectProperty getHasPartProperty() {
		return hasPartDirectlyObjectProperty;
	}
}
