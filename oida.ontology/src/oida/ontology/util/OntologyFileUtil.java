/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.util;

import java.io.File;

import oida.ontologyMgr.OntologyFile;
import oida.ontologyMgr.OntologyMgrFactory;

/**
 * 
 * @author Michael
 * @since 2017-02-13
 *
 */
public class OntologyFileUtil {
	public static OntologyFile createOntologyFileObject(File file) {
		OntologyFile ontologyFileObject = OntologyMgrFactory.eINSTANCE.createOntologyFile();
		
		ontologyFileObject.setPath(file.getAbsolutePath());
		ontologyFileObject.setFileName(file.getName());
		
		return ontologyFileObject;
	}
}
