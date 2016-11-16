package oida.ontology.manager;

import org.eclipse.core.databinding.observable.list.ObservableList;

public interface IOntologyManagerClient {
	public ObservableList<oida.ontologyMgr.LocalOntology> getOntologyList();
}
