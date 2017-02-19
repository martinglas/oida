/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.service;

import java.io.File;

import org.eclipse.emf.ecore.util.EContentAdapter;

import oida.ontologyMgr.OntologyFile;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 13.12.2016
 *
 */
public abstract class AbstractOIDAOntologyService extends EContentAdapter implements IOIDAOntologyService {
	@Override
	public OntologyFile getOntologyFile(String path, String fileName) {
		String fullPath = path;
		if (!path.endsWith("\\"))
			fullPath = path + "\\";
		
		fullPath = fullPath + fileName;
		
		return getOntologyFile(new File(fullPath));
	}
}
