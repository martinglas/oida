package oida.util;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

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
	
	public static String getOIDAMetaModelOntologyPath() {
		return getOIDAWorkPath() + FileConstants.OIDA_METAMODELONTOLOGY_SUBDIRECTORY;
	}
	
	public static void createOIDAWorkDirectories() {
		File f = new File(getOIDAWorkPath());
		
		if (!f.exists())
			f.mkdir();
		
		f = new File(getOIDAMetaModelOntologyPath());
		
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
	
	public static Optional<File> getOntologyFileObject(OntologyFile ontologyFile, boolean createIfNotExisting) {
		if (ontologyFile.getPath() == null) {
			//LOGGER.error("GetOntologyFile for '" + ontologyFile.toString() + "': Ontology file path is not set.");
			return Optional.empty();
		}

		if (ontologyFile.getFileName() == null) {
			//LOGGER.error("GetOntologyFile for '" + ontologyFile.toString() + "': Ontology filename is not set.");
			return Optional.empty();
		}
		
		if (!ontologyFile.getPath().endsWith(StringConstants.BACKSLASH))
			ontologyFile.setPath(ontologyFile.getPath().concat(StringConstants.BACKSLASH));

		File file = new File(ontologyFile.getPath() + ontologyFile.getFileName());

		if (!file.exists()) {
			if (createIfNotExisting) {
				try {
					file.createNewFile();
					//LOGGER.info("GetOntologyFile for '" + ontologyFile.toString() + "': Ontology file '" + file.getAbsolutePath() + "' has been created.");
					return Optional.of(file);
				} catch (IOException e) {
					//LOGGER.error("GetOntologyFile for '" + ontologyFile.toString() + "': Error while creating file '" + file.getAbsolutePath() + "'.", e);
					return Optional.empty();
				}
			} else {
				//LOGGER.warn("GetOntologyFile for '" + ontologyFile.toString() + "': Ontology file '" + file.getAbsolutePath() + "' doesn't exist and has NOT been created.");
				return Optional.empty();
			}
		} else {
			return Optional.of(file);
		}
	}
	
	public static String getFileIriString(OntologyFile ontologyFile) {
		return convertPathToIRI(ontologyFile.getPath() + ontologyFile.getFileName());
	}
	
	public static String convertPathToIRI(String path) {
		if (!path.startsWith(FileConstants.ONTOLOGY_FILE_IRI_PREFIX))
			path = FileConstants.ONTOLOGY_FILE_IRI_PREFIX + path;
		
		path = path.replace(StringConstants.BACKSLASH, StringConstants.SLASH);
		path = path.replace(StringConstants.SPACE, "%20");
		
		return path;
	}
}
