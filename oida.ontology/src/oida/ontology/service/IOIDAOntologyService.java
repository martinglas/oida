package oida.ontology.service;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;

import oida.ontology.manager.IOntologyManager;
import oida.ontologyMgr.Library;
import oida.ontologyMgr.LocalOntologyEntry;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 13.12.2016
 *
 */
public interface IOIDAOntologyService {
	public Library getLibrary();
	public Resource getLibraryResource();
	
	public IObservableList<IOntologyManager> getManagedOntologies();
	
	public EditingDomain getEditingDomain();
	
	public IOntologyManager addOntologyManager(LocalOntologyEntry entry, boolean createIfNotExisting);
}
