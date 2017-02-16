package oida.ontology.service;

import java.io.File;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;

import oida.ontology.manager.IOntologyManager;
import oida.ontologyMgr.Library;
import oida.ontologyMgr.OntologyFile;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 13.12.2016
 *
 */
public interface IOIDAOntologyService {
	public Library getLibrary();

	public Resource getLibraryResource();

	public Resource getManagedOntologiesResource();

	public EditingDomain getEditingDomain();

	public OntologyFile getOntologyFile(File file);
	public OntologyFile getOntologyFile(String path, String fileName);
	
	public IOntologyManager getOntologyManager(OntologyFile entry, boolean createIfNotExisting);
}
