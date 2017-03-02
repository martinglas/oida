package oida.util;

import java.io.File;

import org.eclipse.core.resources.ResourcesPlugin;

import oida.ontologyMgr.OntologyFile;
import oida.ontologyMgr.OntologyMgrFactory;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-02-21
 *
 */
public class OIDAUtil {
	public static final String OIDA_WORK_SUBDIRECTORY = "\\.oida\\";
	
	public static final String ONTOLOGY_LIBRARY_FILE = "OntLib.xmi";
	public static final String ONTOLOGY_MANAGER_FILE = "OntMgr.xmi";
	
	public static final String MEREOLOGY_FILENAME = "mereology.owl";
	
	public static String getOIDAWorkPath() {
		return ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + OIDA_WORK_SUBDIRECTORY;
	}
	
	public static void createOIDAWorkDirectory() {
		File f = new File(getOIDAWorkPath());
		
		if (!f.exists())
			f.mkdir();
	}
	
	public static OntologyFile getOntologyFile(String path, String fileName) {
		String fullPath = path;
		if (!path.endsWith("\\"))
			fullPath = path + "\\";
		
		fullPath = fullPath + fileName;
		
		return getOntologyFile(new File(fullPath));
	}
	
	public static OntologyFile getOntologyFile(File file) {
		OntologyFile ontologyFileObject = OntologyMgrFactory.eINSTANCE.createOntologyFile();

		ontologyFileObject.setPath(file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(file.getName())));
		ontologyFileObject.setFileName(file.getName());
		
		return ontologyFileObject;
	}
}
