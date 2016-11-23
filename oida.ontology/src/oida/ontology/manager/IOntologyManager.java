package oida.ontology.manager;

import java.util.stream.Stream;

import oida.ontology.Ontology;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyIndividual;
import oida.ontologyMgr.LocalOntologyEntry;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2016-11-23
 * 
 */
public interface IOntologyManager {
	Ontology getOntology();
	LocalOntologyEntry getOntologyEntry();
	
	Ontology createOntology(LocalOntologyEntry entry) throws OntologyManagerException;
	Ontology loadOntology(LocalOntologyEntry entry, boolean createIfNotExisting) throws OntologyManagerException;
	void saveOntology() throws OntologyManagerException;
	
	void initializeReasoner();
	
	OntologyClass createClass(String name);
	OntologyClass createClass(String name, String prefix);
	OntologyClass getClass(String name);
	OntologyClass getClass(String name, String prefix);
	
	Stream<OntologyClass> getAllClasses();
	
	OntologyIndividual createIndividual(String name);
	OntologyIndividual createIndividual(String name, String prefix);
	
	void assignIndividualToClass(OntologyIndividual individual, OntologyClass clazz);
	
	OntologyIndividual createIndividualOfClass(String individualName, String className);
	OntologyIndividual createIndividualOfClass(String individualName, String individualPrefix, String className, String classPrefix);
	OntologyIndividual createIndividualOfClass(String individualName, OntologyClass clazz);
	OntologyIndividual createIndividualOfClass(String individualName, String individualPrefix, OntologyClass clazz);
}
