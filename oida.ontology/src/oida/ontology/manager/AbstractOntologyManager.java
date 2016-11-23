package oida.ontology.manager;

import oida.ontology.Ontology;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyIndividual;
import oida.ontologyMgr.LocalOntologyEntry;

public abstract class AbstractOntologyManager implements IOntologyManager {
	
	private Ontology ontology;
	private LocalOntologyEntry ontologyEntry;
	
	public Ontology getOntology() {
		return ontology;
	}

	protected void setOntology(Ontology ontology) {
		this.ontology = ontology;
	}
	
	@Override
	public LocalOntologyEntry getOntologyEntry() {
		return ontologyEntry;
	}

	protected void setOntologyEntry(LocalOntologyEntry entry) {
		this.ontologyEntry = entry;
	}

	@Override
	public OntologyIndividual createIndividualOfClass(String individualName, String className) {
		return createIndividualOfClass(individualName, createClass(className));
	}
	
	@Override
	public OntologyIndividual createIndividualOfClass(String individualName, String individualPrefix, String className, String classPrefix) {
		return createIndividualOfClass(individualName, individualPrefix, createClass(className, classPrefix));
	}
	
	@Override
	public OntologyIndividual createIndividualOfClass(String individualName, OntologyClass clazz) {
		OntologyIndividual individual = createIndividual(individualName);
		assignIndividualToClass(individual, clazz);
		return individual;
	}
	
	@Override
	public OntologyIndividual createIndividualOfClass(String individualName, String individualPrefix, OntologyClass clazz) {
		OntologyIndividual individual = createIndividual(individualName, individualPrefix);
		assignIndividualToClass(individual, clazz);
		return individual;
	}
}
