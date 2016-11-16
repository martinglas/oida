package oida.ontology.reference;

import oida.ontology.manager.client.AbstractOntologyManagerClient;

public class OntologyManagerClient extends AbstractOntologyManagerClient {

	public OntologyManagerClient() {
	}

	@Override
	public void Initialize() {
		setObservedOntology(ReferenceOntologyMgr.get().getReferenceOntology());
	}
}
