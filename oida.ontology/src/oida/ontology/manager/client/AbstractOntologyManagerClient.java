package oida.ontology.manager.client;

import java.util.Observable;

import oida.ontologyMgr.LocalOntology;

public abstract class AbstractOntologyManagerClient extends Observable {
	private LocalOntology observedOntology;
	
	public LocalOntology getObservedOntology() {
		return observedOntology;
	}

	public void setObservedOntology(LocalOntology observedOntology) {
		this.observedOntology = observedOntology;
		notifyObservers();
	}

	public AbstractOntologyManagerClient() {
	}
	
	public abstract void Initialize();
}
