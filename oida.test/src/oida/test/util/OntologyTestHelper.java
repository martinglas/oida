/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.test.util;



import oida.util.OidaHelper;

public class OntologyTestHelper {
	public final static String TEST_ONTOLOGY_FILE_NAME = "SYMO4PDReference.owl";
	public final static String OIDA_TEST_ONTOLOGIES_FOLDER_NAME="testfiles/";
	public final static  String OIDA_TEST_PLUG_IN_ID="oida.test";
	
	
	public static String getTestOntologyFilePath() {
		return OidaHelper.getPluginFilePath(OIDA_TEST_PLUG_IN_ID)+OIDA_TEST_ONTOLOGIES_FOLDER_NAME;
		
	}


	public static String getTestReferenceOntologyFileName() {
		
		return TEST_ONTOLOGY_FILE_NAME;
	}

}
