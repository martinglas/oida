package oida.ontology.ui.library;

import org.eclipse.core.databinding.observable.list.ObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;

import oida.ontology.manager.IOntologyManagerClient;
import oida.ontologyMgr.LocalOntology;

public class OntologyLibrary implements IOntologyManagerClient{
	private ObservableList<LocalOntology> ontologyWatchList;
	
	private static OntologyLibrary INSTANCE;
	
	public static OntologyLibrary getOntologyLibrary() {
		if (INSTANCE == null)
			INSTANCE = new OntologyLibrary();
		
		return INSTANCE;
	}
	
	private OntologyLibrary () {
		ontologyWatchList = new WritableList<LocalOntology>();
	}
	
	@Override
	public ObservableList<LocalOntology> getOntologyList() {
		return ontologyWatchList;
	}
}
