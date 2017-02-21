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

		ontologyFileObject.setPath(file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(file.getName())));
		ontologyFileObject.setFileName(file.getName());
		
		return ontologyFileObject;
	}
}
