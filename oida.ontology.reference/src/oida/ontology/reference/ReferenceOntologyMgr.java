package oida.ontology.reference;

import oida.ontologyMgr.LocalOntologyEntry;

public class ReferenceOntologyMgr {
	private LocalOntologyEntry referenceOntology;

	public LocalOntologyEntry getReferenceOntology() {
		return referenceOntology;
	}

	public void setReferenceOntology(LocalOntologyEntry referenceOntology) {
		this.referenceOntology = referenceOntology;
	}

	private static ReferenceOntologyMgr instance;
	
	public static ReferenceOntologyMgr get() {
		if (instance == null)
			instance = new ReferenceOntologyMgr();
		
		return instance;
	}
	
	private ReferenceOntologyMgr() {
		//referenceOntology = OntologyMgrFactory.eINSTANCE.createLocalOntology();
	}
}
