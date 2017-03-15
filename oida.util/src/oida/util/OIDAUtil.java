package oida.util;

import java.io.File;

import org.eclipse.core.resources.ResourcesPlugin;

import oida.util.constants.FileConstants;
import oida.util.constants.StringConstants;
import oida.ontologyMgr.OntologyFile;
import oida.ontologyMgr.OntologyMgrFactory;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-02-21
 *
 */
public class OIDAUtil {
	public static String getOIDAWorkPath() {
		return ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + FileConstants.OIDA_WORK_SUBDIRECTORY;
	}
	
	public static void createOIDAWorkDirectory() {
		File f = new File(getOIDAWorkPath());
		
		if (!f.exists())
			f.mkdir();
	}
	
	public static OntologyFile getOntologyFile(String path, String fileName) {
		String fullPath = path;
		if (!path.endsWith(StringConstants.BACKSLASH))
			fullPath = path + StringConstants.BACKSLASH;
		
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
