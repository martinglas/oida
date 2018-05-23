package net.bhl.oida.util;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.eclipse.core.resources.ResourcesPlugin;

import net.bhl.oida.ontology.model.ontologymgr.OntologymgrFactory;
import net.bhl.oida.ontology.model.ontologymgr.LocalOntologyMetaInfo;
import net.bhl.oida.ontology.model.ontologymgr.SystemOntologyMetaInfo;
import net.bhl.oida.util.constants.FileConstants;
import net.bhl.oida.util.constants.StringConstants;

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

    public static LocalOntologyMetaInfo getOntologyMetaInfo(String path, String fileName) {
	String fullPath = path;
	if (!path.endsWith(StringConstants.BACKSLASH))
	    fullPath = path + StringConstants.BACKSLASH;

	fullPath = fullPath + fileName;

	return getLocalOntologyMetaInfo(new File(fullPath));
    }

    public static SystemOntologyMetaInfo getSystemOntologyMetaInfo(String path, String fileName) {
	String fullPath = path;
	if (!path.endsWith(StringConstants.BACKSLASH))
	    fullPath = path + StringConstants.BACKSLASH;

	fullPath = fullPath + fileName;

	return getSystemOntologyMetaInfo(new File(fullPath));
    }

    public static SystemOntologyMetaInfo getSystemOntologyMetaInfo(File file) {
	SystemOntologyMetaInfo ontologyMetaInfo = OntologymgrFactory.eINSTANCE.createSystemOntologyMetaInfo();
	ontologyMetaInfo.setLocalPath(file.getAbsolutePath());

	return ontologyMetaInfo;
    }

    public static LocalOntologyMetaInfo getLocalOntologyMetaInfo(File file) {
	LocalOntologyMetaInfo ontologyMetaInfo = OntologymgrFactory.eINSTANCE.createLocalOntologyMetaInfo();
	ontologyMetaInfo.setLocalPath(file.getAbsolutePath());

	return ontologyMetaInfo;
    }

    public static Optional<File> getOntologyFileObject(LocalOntologyMetaInfo ontologyMetaInfo, boolean createIfNotExisting) {
	if (ontologyMetaInfo.getLocalPath() == null) {
	    // LOGGER.error("GetOntologyFile for '" + ontologyFile.toString() +
	    // "': Ontology file path is not set.");
	    return Optional.empty();
	}

	File file = new File(ontologyMetaInfo.getLocalPath());

	if (!file.exists()) {
	    if (createIfNotExisting) {
		try {
		    file.createNewFile();
		    // LOGGER.info("GetOntologyFile for '" +
		    // ontologyFile.toString() + "': Ontology file '" +
		    // file.getAbsolutePath() + "' has been created.");
		    return Optional.of(file);
		} catch (IOException e) {
		    // LOGGER.error("GetOntologyFile for '" +
		    // ontologyFile.toString() + "': Error while creating file
		    // '" + file.getAbsolutePath() + "'.", e);
		    return Optional.empty();
		}
	    } else {
		// LOGGER.warn("GetOntologyFile for '" + ontologyFile.toString()
		// + "': Ontology file '" + file.getAbsolutePath() + "' doesn't
		// exist and has NOT been created.");
		return Optional.empty();
	    }
	} else {
	    return Optional.of(file);
	}
    }

    public static String getFileIriString(LocalOntologyMetaInfo ontologyMetaInfo) {
	return convertPathToIRI(ontologyMetaInfo.getLocalPath());
    }

    public static String extractFileName(String fullPath) {
	return fullPath.substring(fullPath.lastIndexOf(StringConstants.BACKSLASH) + 1, fullPath.lastIndexOf(StringConstants.DOT));
    }

    public static String convertPathToIRI(String path) {
	if (!path.startsWith(FileConstants.ONTOLOGY_FILE_IRI_PREFIX))
	    path = FileConstants.ONTOLOGY_FILE_IRI_PREFIX + path;

	path = path.replace(StringConstants.BACKSLASH, StringConstants.SLASH);
	path = path.replace(StringConstants.SPACE, "%20");

	return path;
    }
}
