package oida.ontology.reference;

import oida.ontologyMgr.LocalOntology;
import oida.ontologyMgr.OntologyMgrFactory;

public class ReferenceOntologyMgr {
	private LocalOntology referenceOntology;

	public LocalOntology getReferenceOntology() {
		return referenceOntology;
	}

	public void setReferenceOntology(LocalOntology referenceOntology) {
		this.referenceOntology = referenceOntology;
	}

	private static ReferenceOntologyMgr instance;
	
	public static ReferenceOntologyMgr get() {
		if (instance == null)
			instance = new ReferenceOntologyMgr();
		
		return instance;
	}
	
	private ReferenceOntologyMgr() {
		referenceOntology = OntologyMgrFactory.eINSTANCE.createLocalOntology();
	}
}
