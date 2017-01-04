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
	
	void addNamespace(String prefix, String namespace);
	void addNamespace(String prefix, String namespace, boolean setDefault);
	
	boolean isNamespaceExisting(String prefix);
	
	String getNamespace(String prefix);
	
	/**
	 * Creates a class within the default namespace of the ontology.
	 * 
	 * @param name The short class name (without any prefix).
	 * @return An representation object of the created class.
	 */
	OntologyClass createClass(String name);
	
	/**
	 * Creates a class within a namespace. The namespace has to added beforehand with (@link #addPrefix(prefixName, prefix) addPrefix) and is identified by the prefix.
	 * 
	 * @param name The short class name (without any prefix).
	 * @param prefix The prefix of the namespace of the new class (without a colon).
	 * @return An representation object of the created class.
	 */
	OntologyClass createClass(String name, String prefix);
	
	void assignSubClassToSuperClass(OntologyClass subClass, OntologyClass superClass);
	
	OntologyClass createSubClass(String name, OntologyClass superClass);
	OntologyClass createSubClass(String name, String prefix, OntologyClass superClass);

	OntologyIndividual getIndividual(String name);
	OntologyIndividual getIndividual(String name, String prefix);
	Stream<OntologyIndividual> getAllIndividuals();

	OntologyIndividual createIndividual(String name);
	OntologyIndividual createIndividual(String name, String prefix);
	
	void assignIndividualToClass(OntologyIndividual individual, OntologyClass clazz);
	
	OntologyIndividual createIndividualOfClass(String individualName, String className);
	OntologyIndividual createIndividualOfClass(String individualName, String individualPrefix, String className, String classPrefix);
	OntologyIndividual createIndividualOfClass(String individualName, OntologyClass clazz);
	OntologyIndividual createIndividualOfClass(String individualName, String individualPrefix, OntologyClass clazz);
	
	OntologyClass getClass(String name);
	OntologyClass getClass(String name, String prefix);
	Stream<OntologyClass> getAllClasses();
}
