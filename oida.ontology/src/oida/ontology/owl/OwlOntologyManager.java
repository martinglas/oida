package oida.ontology.owl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.OWLXMLDocumentFormat;
import org.semanticweb.owlapi.formats.PrefixDocumentFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectAllValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectOneOf;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.parameters.ChangeApplied;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.search.EntitySearcher;
import org.semanticweb.owlapi.util.SimpleIRIMapper;

import com.clarkparsia.pellet.owlapiv3.PelletReasonerFactory;

import uk.ac.manchester.cs.owl.owlapi.OWLLiteralImpl;

/**
 * 
 * @since 2016-11-10
 * @author Michael.Shamiyeh
 *
 */
public class OwlOntologyManager {
	public static String HASHTAG = "#";

	private OWLOntologyManager manager;
	private OWLOntology ontology;
	private OWLDataFactory factory;
	private PrefixDocumentFormat prefixMgr;

	private OWLReasoner reasoner;
	
	public void addPrefix(String prefixName, String prefix, boolean setDefault) {
		String prefInternal = prefix;
		
		if (!prefInternal.endsWith(HASHTAG))
			prefInternal = prefInternal.concat(HASHTAG);
		
		if (setDefault)
			prefixMgr.setDefaultPrefix(prefInternal);
		else
			prefixMgr.setPrefix(prefixName, prefInternal);
	}
	
	public void addPrefix(String prefixName, String prefix) {
		addPrefix(prefixName, prefix, false);
	}

	public OwlOntologyManager() {
		manager = OWLManager.createConcurrentOWLOntologyManager();
	}
	
	public void initializeReasoner() {
		reasoner = PelletReasonerFactory.getInstance().createReasoner(ontology);

		//((PelletReasoner)reasoner).getKB().realize();
		//((PelletReasoner)reasoner).getKB().printClassTree();
	}

	public void createOntology() throws OwlOntologyManagerException {
		try {
			ontology = manager.createOntology();
			factory = manager.getOWLDataFactory();
			prefixMgr = new OWLXMLDocumentFormat();
		} catch (OWLOntologyCreationException e) {
			throw new OwlOntologyManagerException("Ontology creation failed.", e);
		}
	}

	public void loadOntology(File file) throws OwlOntologyManagerException {
		try {
			ontology = manager.loadOntologyFromOntologyDocument(file);

			factory = manager.getOWLDataFactory();
			prefixMgr = (PrefixDocumentFormat)manager.getOntologyFormat(ontology);
			System.out.println("Ontology loaded: '" + ontology.toString() + "'");			
		} catch (OWLOntologyCreationException e) {
			throw new OwlOntologyManagerException("Error while loading ontology from file '" + file.getName() + "'", e);
		}
	}

	public void addIRIMapping(String local, String iri) {
		manager.getIRIMappers().add(new SimpleIRIMapper(IRI.create(iri), IRI.create(local)));
	}

	public void saveOntology(File file) throws OwlOntologyManagerException {
		try {
			FileOutputStream outputStream = new FileOutputStream(file);
			manager.saveOntology(ontology, outputStream);
		} catch (FileNotFoundException e) {
			throw new OwlOntologyManagerException("Error while saving ontology to file '" + file.getName() + "'", e);
		} catch (OWLOntologyStorageException e) {
			throw new OwlOntologyManagerException("Error while saving ontology to file '" + file.getName() + "'", e);
		}
	}
	
	public void createClass(String name) {
		createClassInternal(name);
	}
	
	public void removeClass(String name) {
	}
	
	public void declareAsSubClass(String subClassName, String superClass) {
		OWLClass subClass = getClassByName(subClassName);
		
		if (subClass == null)
			subClass = createClassInternal(subClassName);
		
		declareAsSubClassInternal(subClass, getClassByName(superClass));
	}

	public String[] getDeclaredClasses() {
		return ontology.classesInSignature().map(c -> c.getIRI().toString()).collect(Collectors.toList()).toArray(new String[0]);
	}
	
	public String[] getDeclaredClassesShortForm() {
		return ontology.classesInSignature().map(c -> c.getIRI().getShortForm()).collect(Collectors.toList()).toArray(new String[0]);
	}
	
	public void createObjectProperty(String propertyName, String domainClassName) {
		OWLObjectProperty property = factory.getOWLObjectProperty(propertyName, prefixMgr);
		
		OWLClass clazz = getClassByName(domainClassName);
		
		OWLObjectPropertyDomainAxiom axiom = factory.getOWLObjectPropertyDomainAxiom(property, clazz);
		
		manager.addAxiom(ontology, axiom);
	}
	
	public void createRestriction(String propertyName, String individualName, String className) {
		OWLObjectProperty property = getObjectPropertyByName(propertyName);
		OWLNamedIndividual individual = getIndividualByName(individualName);
		OWLClass clazz = getClassByName(className);
		
		Set<OWLEntity> entities=new HashSet<OWLEntity>();
		entities.add(individual);		
		
		OWLObjectOneOf individualCollection = factory.getOWLObjectOneOf(individual);
		
		OWLObjectAllValuesFrom restriction = factory.getOWLObjectAllValuesFrom(property, individualCollection);
		manager.addAxiom(ontology, factory.getOWLSubClassOfAxiom(clazz, restriction));
	}
	
	public void createIndividualOfClass(String individualName, String className) {
		OWLNamedIndividual individual = factory.getOWLNamedIndividual(individualName, prefixMgr);
		
		manager.addAxiom(ontology, factory.getOWLClassAssertionAxiom(getClassByName(className), individual));
	}

	public String[] getIndividualsOfClass(String className) {
		ArrayList<String> ret = new ArrayList<String>();
		
		for (OWLClass cl : ontology.classesInSignature().collect(Collectors.toList())) {
		    if (cl.getIRI().toString().equals(className)) {
		        NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(cl, false);
		        
		        instances.entities().map(OWLNamedIndividual::getIRI).forEach(i -> { ret.add(i.getIRIString()); });
		        break;
		    }
		}
		
		return ret.toArray(new String[0]);
	}
	
	public String[] getSubjectsOfObjectPropertyOfClass(String propertyName, String individualName) {
		ArrayList<String> ret = new ArrayList<String>();

		OWLObjectProperty property = getObjectPropertyByName(propertyName);
		
		for(OWLObjectPropertyAssertionAxiom a : getObjectPropertyAssertionsInternal(propertyName, individualName)) {
			if (a.getProperty().getNamedProperty().equals(property))
				ret.add(a.getObject().toStringID());
		}
		
		return ret.toArray(new String[0]);
	}
	
	public boolean isTypeOf(String individualName, String className) {
		OWLNamedIndividual individual = getIndividualByName(individualName);
		OWLClass cl = getClassByName(className);
		
		EntitySearcher.getTypes(individual, ontology);
		
		return reasoner.getTypes(individual).containsEntity(cl);
	}
	
	public void assertDataTypePropertyToIndividual(String propertyName, String individualName, String value) {
		OWLDataProperty dataProperty = getDataPropertyByName(propertyName);
		OWLIndividual individual = getIndividualByName(individualName);
		
		OWLDataPropertyAssertionAxiom axiom = factory.getOWLDataPropertyAssertionAxiom(dataProperty, individual, value);
		manager.addAxiom(ontology, axiom);
	}
	
	public void assertAnnotationPropertyToClass(String propertyName, String className, String value) {
		OWLAnnotationProperty annotProperty = getAnnotationPropertyByName(propertyName);
		OWLClass clazz = getClassByName(className);
		
		OWLLiteralImpl literal = new OWLLiteralImpl(value, null, factory.getStringOWLDatatype());
		
		OWLAnnotationAssertionAxiom axiom = factory.getOWLAnnotationAssertionAxiom(annotProperty, clazz.getIRI(), literal);
		manager.addAxiom(ontology, axiom);
	}

	public void createDataTypeProperty(String name, String domainName) {
		OWLDataProperty property = factory.getOWLDataProperty(name, prefixMgr);
		manager.addAxiom(ontology, factory.getOWLDataPropertyDomainAxiom(property, getClassByName(domainName)));
	}
	
	public void createClassAnnotationProperty(String name, String domainName) {
		OWLAnnotationProperty property = factory.getOWLAnnotationProperty(name, prefixMgr);
		manager.addAxiom(ontology, factory.getOWLAnnotationPropertyDomainAxiom(property, getClassByName(domainName).getIRI()));
	}
	
	private OWLClass getClassByName(String name) {
		Optional<OWLClass> opt = ontology.classesInSignature().filter(cl -> cl.getIRI().toString().substring(cl.getIRI().toString().indexOf(HASHTAG) + 1).equals(name)).findFirst();
		
		if (opt.isPresent())
			return opt.get();
		
		return null;
	}
	
	private OWLDataProperty getDataPropertyByName(String name) {
		return factory.getOWLDataProperty(name, prefixMgr);
	}
	
	private OWLAnnotationProperty getAnnotationPropertyByName(String name) {
		return factory.getOWLAnnotationProperty(name, prefixMgr);
	}
	
	private ChangeApplied declareAsSubClassInternal(OWLClass subClass, OWLClass superClass) {
		return manager.addAxiom(ontology, factory.getOWLSubClassOfAxiom(subClass, superClass));
	}
	
	private OWLNamedIndividual getIndividualByName(String name) {
		for (OWLNamedIndividual i : ontology.individualsInSignature().collect(Collectors.toList())) {
			if (i.getIRI().toString().endsWith(name))
				return i;
		}
		
		return null;
	}
	
	private OWLObjectProperty getObjectPropertyByName(String name) {
		for (OWLObjectProperty p : ontology.objectPropertiesInSignature().collect(Collectors.toList())) {
			if (p.getIRI().toString().substring(p.getIRI().toString().indexOf(HASHTAG) + 1).equals(name))
				return p;
		}
		
		return null;
	}
	
	private OWLObjectPropertyAssertionAxiom[] getObjectPropertyAssertionsInternal(String propertyName, String individualName) {
		return ontology.objectPropertyAssertionAxioms(getIndividualByName(individualName)).collect(Collectors.toList()).toArray(new OWLObjectPropertyAssertionAxiom[0]);
	}
	

	private OWLClass createClassInternal(String name) {
		OWLClass newClass = factory.getOWLClass(name, prefixMgr);
		OWLAxiom declareNewClass = factory.getOWLDeclarationAxiom(newClass);

		manager.addAxiom(ontology, declareNewClass);
		
		return newClass;
	}
}
