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
