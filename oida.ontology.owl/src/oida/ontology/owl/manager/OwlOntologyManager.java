package oida.ontology.owl.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.OWLXMLDocumentFormat;
import org.semanticweb.owlapi.formats.PrefixDocumentFormat;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import oida.ontology.Ontology;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyFactory;
import oida.ontology.OntologyIndividual;
import oida.ontology.OntologyItem;
import oida.ontology.manager.AbstractOntologyManager;
import oida.ontology.manager.OntologyManagerException;
import oida.ontologyMgr.LocalOntologyEntry;

public class OwlOntologyManager extends AbstractOntologyManager {
	public static String HASHTAG = "#";

	private HashMap<OntologyItem, OWLObject> internal2apiMap;
	private HashMap<OWLObject, OntologyItem> api2internalMap;

	private OWLOntologyManager manager;
	private OWLOntology ontology;
	private OWLDataFactory factory;
	private PrefixDocumentFormat prefixMgr;

	//private OWLReasoner reasoner;

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

	OwlOntologyManager() {
		internal2apiMap = new HashMap<OntologyItem, OWLObject>();
		api2internalMap = new HashMap<OWLObject, OntologyItem>();

		manager = OWLManager.createConcurrentOWLOntologyManager();
	}

	public void initializeReasoner() {
		//reasoner = PelletReasonerFactory.getInstance().createReasoner(ontology);

		// ((PelletReasoner)reasoner).getKB().realize();
		// ((PelletReasoner)reasoner).getKB().printClassTree();
	}

	// public void addIRIMapping(String local, String iri) {
	// manager.getIRIMappers().add(new SimpleIRIMapper(IRI.create(iri),
	// IRI.create(local)));
	// }
	//
	//
	//
	// public void declareAsSubClass(String subClassName, String superClass) {
	// OWLClass subClass = getClassByName(subClassName);
	//
	// if (subClass == null)
	// subClass = createClassInternal(subClassName);
	//
	// declareAsSubClassInternal(subClass, getClassByName(superClass));
	// }
	//
	// public String[] getDeclaredClasses() {
	// return ontology.classesInSignature().map(c ->
	// c.getIRI().toString()).collect(Collectors.toList()).toArray(new
	// String[0]);
	// }
	//
	// public String[] getDeclaredClassesShortForm() {
	// return ontology.classesInSignature().map(c ->
	// c.getIRI().getShortForm()).collect(Collectors.toList()).toArray(new
	// String[0]);
	// }
	//
	// public void createObjectProperty(String propertyName, String
	// domainClassName) {
	// OWLObjectProperty property = factory.getOWLObjectProperty(propertyName,
	// prefixMgr);
	//
	// OWLClass clazz = getClassByName(domainClassName);
	//
	// OWLObjectPropertyDomainAxiom axiom =
	// factory.getOWLObjectPropertyDomainAxiom(property, clazz);
	//
	// manager.addAxiom(ontology, axiom);
	// }
	//
	// public void createRestriction(String propertyName, String individualName,
	// String className) {
	// OWLObjectProperty property = getObjectPropertyByName(propertyName);
	// OWLNamedIndividual individual = getIndividualByName(individualName);
	// OWLClass clazz = getClassByName(className);
	//
	// Set<OWLEntity> entities=new HashSet<OWLEntity>();
	// entities.add(individual);
	//
	// OWLObjectOneOf individualCollection =
	// factory.getOWLObjectOneOf(individual);
	//
	// OWLObjectAllValuesFrom restriction =
	// factory.getOWLObjectAllValuesFrom(property, individualCollection);
	// manager.addAxiom(ontology, factory.getOWLSubClassOfAxiom(clazz,
	// restriction));
	// }
	//
	// public void createIndividualOfClass(String individualName, String
	// className) {
	// OWLNamedIndividual individual =
	// factory.getOWLNamedIndividual(individualName, prefixMgr);
	//
	// manager.addAxiom(ontology,
	// factory.getOWLClassAssertionAxiom(getClassByName(className),
	// individual));
	// }
	//
	// public String[] getIndividualsOfClass(String className) {
	// ArrayList<String> ret = new ArrayList<String>();
	//
	// for (OWLClass cl :
	// ontology.classesInSignature().collect(Collectors.toList())) {
	// if (cl.getIRI().toString().equals(className)) {
	// NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(cl, false);
	//
	// instances.entities().map(OWLNamedIndividual::getIRI).forEach(i -> {
	// ret.add(i.getIRIString()); });
	// break;
	// }
	// }
	//
	// return ret.toArray(new String[0]);
	// }
	//
	// public String[] getSubjectsOfObjectPropertyOfClass(String propertyName,
	// String individualName) {
	// ArrayList<String> ret = new ArrayList<String>();
	//
	// OWLObjectProperty property = getObjectPropertyByName(propertyName);
	//
	// for(OWLObjectPropertyAssertionAxiom a :
	// getObjectPropertyAssertionsInternal(propertyName, individualName)) {
	// if (a.getProperty().getNamedProperty().equals(property))
	// ret.add(a.getObject().toStringID());
	// }
	//
	// return ret.toArray(new String[0]);
	// }
	//
	// public boolean isTypeOf(String individualName, String className) {
	// OWLNamedIndividual individual = getIndividualByName(individualName);
	// OWLClass cl = getClassByName(className);
	//
	// EntitySearcher.getTypes(individual, ontology);
	//
	// return reasoner.getTypes(individual).containsEntity(cl);
	// }
	//
	// public void assertDataTypePropertyToIndividual(String propertyName,
	// String individualName, String value) {
	// OWLDataProperty dataProperty = getDataPropertyByName(propertyName);
	// OWLIndividual individual = getIndividualByName(individualName);
	//
	// OWLDataPropertyAssertionAxiom axiom =
	// factory.getOWLDataPropertyAssertionAxiom(dataProperty, individual,
	// value);
	// manager.addAxiom(ontology, axiom);
	// }
	//
	// public void assertAnnotationPropertyToClass(String propertyName, String
	// className, String value) {
	// OWLAnnotationProperty annotProperty =
	// getAnnotationPropertyByName(propertyName);
	// OWLClass clazz = getClassByName(className);
	//
	// OWLLiteralImpl literal = new OWLLiteralImpl(value, null,
	// factory.getStringOWLDatatype());
	//
	// OWLAnnotationAssertionAxiom axiom =
	// factory.getOWLAnnotationAssertionAxiom(annotProperty, clazz.getIRI(),
	// literal);
	// manager.addAxiom(ontology, axiom);
	// }
	//
	// public void createDataTypeProperty(String name, String domainName) {
	// OWLDataProperty property = factory.getOWLDataProperty(name, prefixMgr);
	// manager.addAxiom(ontology,
	// factory.getOWLDataPropertyDomainAxiom(property,
	// getClassByName(domainName)));
	// }
	//
	// public void createClassAnnotationProperty(String name, String domainName)
	// {
	// OWLAnnotationProperty property = factory.getOWLAnnotationProperty(name,
	// prefixMgr);
	// manager.addAxiom(ontology,
	// factory.getOWLAnnotationPropertyDomainAxiom(property,
	// getClassByName(domainName).getIRI()));
	// }
	//
	// private OWLClass getClassByName(String name) {
	// Optional<OWLClass> opt = ontology.classesInSignature().filter(cl ->
	// cl.getIRI().toString().substring(cl.getIRI().toString().indexOf(HASHTAG)
	// + 1).equals(name)).findFirst();
	//
	// if (opt.isPresent())
	// return opt.get();
	//
	// return null;
	// }
	//
	// private OWLDataProperty getDataPropertyByName(String name) {
	// return factory.getOWLDataProperty(name, prefixMgr);
	// }
	//
	// private OWLAnnotationProperty getAnnotationPropertyByName(String name) {
	// return factory.getOWLAnnotationProperty(name, prefixMgr);
	// }
	//
	// private ChangeApplied declareAsSubClassInternal(OWLClass subClass,
	// OWLClass superClass) {
	// return manager.addAxiom(ontology, factory.getOWLSubClassOfAxiom(subClass,
	// superClass));
	// }
	//
	// private OWLNamedIndividual getIndividualByName(String name) {
	// for (OWLNamedIndividual i :
	// ontology.individualsInSignature().collect(Collectors.toList())) {
	// if (i.getIRI().toString().endsWith(name))
	// return i;
	// }
	//
	// return null;
	// }
	//
	// private OWLObjectProperty getObjectPropertyByName(String name) {
	// for (OWLObjectProperty p :
	// ontology.objectPropertiesInSignature().collect(Collectors.toList())) {
	// if
	// (p.getIRI().toString().substring(p.getIRI().toString().indexOf(HASHTAG) +
	// 1).equals(name))
	// return p;
	// }
	//
	// return null;
	// }
	//
	// private OWLObjectPropertyAssertionAxiom[]
	// getObjectPropertyAssertionsInternal(String propertyName, String
	// individualName) {
	// return
	// ontology.objectPropertyAssertionAxioms(getIndividualByName(individualName)).collect(Collectors.toList()).toArray(new
	// OWLObjectPropertyAssertionAxiom[0]);
	// }
	//
	//
	// private OWLClass createClassInternal(String name) {
	// OWLClass newClass = factory.getOWLClass(name, prefixMgr);
	// OWLAxiom declareNewClass = factory.getOWLDeclarationAxiom(newClass);
	//
	// manager.addAxiom(ontology, declareNewClass);
	//
	// return newClass;
	// }

	@Override
	public Ontology createOntology(LocalOntologyEntry entry) throws OntologyManagerException {
		setOntologyEntry(entry);
		
		try {
			ontology = manager.createOntology();
			factory = manager.getOWLDataFactory();
			prefixMgr = new OWLXMLDocumentFormat();

			Ontology o = OntologyFactory.eINSTANCE.createOntology();
			setOntology(o);
			toMap(ontology, o);

			return o;
		} catch (Exception e) {
			throw new OntologyManagerException("Ontology creation failed.");
		}
	}

	@Override
	public Ontology loadOntology(LocalOntologyEntry entry, boolean createIfNotExisting) throws OntologyManagerException {
		if (entry == null)
			return null;
		
		File file = getOntologyFile(entry);
		
		if (file == null)
			return null;
		
		setOntologyEntry(entry);

		if (file.exists()) {
			try {
				ontology = manager.loadOntologyFromOntologyDocument(file);

				factory = manager.getOWLDataFactory();
				prefixMgr = (PrefixDocumentFormat)manager.getOntologyFormat(ontology);
				System.out.println("Ontology loaded: '" + file.getName() + "'");
				
				Ontology o = OntologyFactory.eINSTANCE.createOntology();
				o.setName(file.getName());
				o.setNrOfClasses(ontology.classesInSignature().count());
				o.setNrOfIndividuals(ontology.individualsInSignature().count());
				
				setOntology(o);
				toMap(ontology, o);
				
				return o;
			} catch (Exception e) {
				throw new OntologyManagerException("Error while loading ontology from file '" + file.getName() + "'");
			}	
		} else {
			return createOntology(entry);
		}
	}

	@Override
	public void saveOntology() throws OntologyManagerException {
		File file = new File(getOntologyEntry().getPath());
		
		try { 
			FileOutputStream outputStream = new FileOutputStream(file);
			manager.saveOntology(ontology, outputStream);
		} catch (FileNotFoundException e) {
			throw new OntologyManagerException("Error while saving ontology to file '" + file.getName() + "'", e);
		} catch (Exception e) {
			throw new OntologyManagerException("Error while saving ontology to file '" + file.getName() + "'");
		}
			
//		} catch (OWLOntologyStorageException e) {
//			throw new OntologyManagerException("Error while saving ontology to file '" + file.getName() + "'", e);
//		}
	}

	@Override
	public OntologyClass createClass(String name) {
		OWLClass newClass = factory.getOWLClass(name, prefixMgr);
		OWLAxiom declareNewClass = factory.getOWLDeclarationAxiom(newClass);

		manager.addAxiom(ontology, declareNewClass);

		OntologyClass clazz = OntologyFactory.eINSTANCE.createOntologyClass();

		// TODO copy properties

		toMap(newClass, clazz);

		return clazz;
	}

	@Override
	public OntologyClass createClass(String name, String prefix) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OntologyClass getClass(String name) {
		Optional<OWLClass> opt = ontology.classesInSignature().filter(cl -> cl.getIRI().toString().substring(cl.getIRI().toString().indexOf(HASHTAG) + 1).equals(name)).findFirst();

		if (opt.isPresent()) {
			return (OntologyClass)api2internalMap.get(opt.get());
		}

		return null;
	}

	@Override
	public OntologyClass getClass(String name, String prefix) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<OntologyClass> getAllClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OntologyIndividual createIndividual(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OntologyIndividual createIndividual(String name, String prefix) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void assignIndividualToClass(OntologyIndividual individual, OntologyClass clazz) {
		// TODO Auto-generated method stub

	}

	private void toMap(OWLObject apiObj, OntologyItem internalObj) {
		internal2apiMap.put(internalObj, apiObj);
		api2internalMap.put(apiObj, internalObj);
	}
}
