package oida.ontology.owl.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.PrefixDocumentFormat;
import org.semanticweb.owlapi.formats.RDFXMLDocumentFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
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
import oida.ontologyMgr.OntologyFile;

/**
 * 
 * @author Michael.Shamiyeh
 *
 */
public class OwlOntologyManager extends AbstractOntologyManager {
	public static String STR_EMPTY = "";
	public static String HASHTAG = "#";

	private HashMap<OntologyItem, OWLObject> internal2apiMap;
	private HashMap<OWLObject, OntologyItem> api2internalMap;
	
	private OWLOntologyManager manager;
	private OWLOntology ontology;
	private OWLDataFactory factory;
	private PrefixDocumentFormat prefixMgr;

	//private OWLReasoner reasoner;

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

	@Override
	public Ontology createOntology(OntologyFile ontologyFile) throws OntologyManagerException {
		setOntologyEntry(ontologyFile);
		
		try {
			File file = getOntologyFile(ontologyFile, true);
			
			if (file != null) {
				ontology = manager.createOntology(IRI.create(file));
				factory = manager.getOWLDataFactory();			
				prefixMgr = new RDFXMLDocumentFormat();

				Ontology o = OntologyFactory.eINSTANCE.createOntology();
				setOntology(o);
				toMap(ontology, o);
				
				manager.saveOntology(ontology);
				System.out.println("SYMO4PD OWL Ontology Manager: Ontology created: '" + file.getName() + "'");
				
				return o;
			}
			else {
				return null;
			}
		} catch (Exception e) {
			throw new OntologyManagerException("Ontology creation failed: " + e.getMessage());
		}
	}

	@Override
	public Ontology loadOntology(OntologyFile ontologyFile, boolean createIfNotExisting) throws OntologyManagerException {
		if (ontologyFile == null)
			return null;
		
		File file = getOntologyFile(ontologyFile);	
		if (file != null) {
			try {
				ontology = manager.loadOntologyFromOntologyDocument(file);

				factory = manager.getOWLDataFactory();
				prefixMgr = (PrefixDocumentFormat)manager.getOntologyFormat(ontology);
				System.out.println("SYMO4PD OWL Ontology Manager: Ontology loaded: '" + file.getName() + "'");
				
				Ontology o = OntologyFactory.eINSTANCE.createOntology();
				o.setName(file.getName());
				o.setNrOfClasses(ontology.classesInSignature().count());
				o.setNrOfIndividuals(ontology.individualsInSignature().count());
				
				for (OWLClass c : ontology.classesInSignature().collect(Collectors.toList())) {
					OntologyClass oC = OntologyFactory.eINSTANCE.createOntologyClass();
					oC.setName(c.getIRI().getShortForm());
					
					o.getClasses().add(oC);
				}
				
				for (OWLNamedIndividual i : ontology.individualsInSignature().collect(Collectors.toList())) {
					OntologyIndividual oI = OntologyFactory.eINSTANCE.createOntologyIndividual();
					oI.setName(i.getIRI().getShortForm());
					
					o.getIndividuals().add(oI);
				}
				
				setOntologyEntry(ontologyFile);
				setOntology(o);
				toMap(ontology, o);
				
				return o;
			} catch (Exception e) {
				throw new OntologyManagerException("Error while loading ontology from file '" + file.getName() + "': " + e.getMessage(), e);
			}
		} else {
			return createOntology(ontologyFile);
		}
	}

	@Override
	public void saveOntology() throws OntologyManagerException {
		File file = new File(getOntologyEntry().getPath() + getOntologyEntry().getFileName());
		
		try { 
			FileOutputStream outputStream = new FileOutputStream(file);
			manager.saveOntology(ontology, prefixMgr, outputStream);
		} catch (FileNotFoundException e) {
			throw new OntologyManagerException("Error while saving ontology to file '" + file.getName() + "': " + e.getMessage(), e);
		} catch (Exception e) {
			throw new OntologyManagerException("Error while saving ontology to file '" + file.getName() + "': " + e.getMessage(), e);
		}
	}
	
	@Override
	public void addNamespace(String prefixName, String prefix) {
		addNamespace(prefixName, prefix, false);
	}
	
	@Override
	public void addNamespace(String prefixName, String prefix, boolean setDefault) {
		String prefInternal = prefix;

		if (!prefInternal.endsWith(HASHTAG))
			prefInternal = prefInternal.concat(HASHTAG);

		prefixMgr.setPrefix(prefixName, prefInternal);
		
		if (setDefault)
			prefixMgr.setDefaultPrefix(prefInternal);
	}
	
	@Override
	public boolean isNamespaceExisting(String prefix) {
		return prefixMgr.containsPrefixMapping(prefix);
	}
	
	@Override
	public String getNamespace(String prefix) {
		if (isNamespaceExisting(prefix))
			return prefixMgr.getPrefix(prefix);
		else
			return STR_EMPTY;
	}

	
	@Override
	public OntologyClass createClass(String name) {
		return createClass(name, "");
	}

	@Override
	public OntologyClass createClass(String name, String prefix) {
		String fullClassName = buildFullEntityString(name, prefix);

		OWLClass newClass = factory.getOWLClass(fullClassName, prefixMgr);
		OWLAxiom declareNewClass = factory.getOWLDeclarationAxiom(newClass);
		
		manager.addAxiom(ontology, declareNewClass);
		
		OntologyClass clazz = OntologyFactory.eINSTANCE.createOntologyClass();
		clazz.setName(name);
		
		if (!prefix.isEmpty())
			clazz.setPrefix(prefixMgr.getPrefix(prefix));
		
		clazz.setContainingOntology(getOntology());
		
		toMap(newClass, clazz);
		
		return clazz;
	}

	@Override
	public void assignSubClassToSuperClass(OntologyClass subClass, OntologyClass superClass) {
		manager.addAxiom(ontology, factory.getOWLSubClassOfAxiom(getOWLClass(subClass), getOWLClass(superClass)));
		
		subClass.getSuperClasses().add(superClass);
		superClass.getSubClasses().add(subClass);
	}

	@Override
	public OntologyClass getClass(final String name) {
		return getClass(name, STR_EMPTY);
	}

	@Override
	public OntologyClass getClass(final String name, final String prefix) {
		Optional<OWLClass> opt;
		
		if (!prefix.isEmpty())
			opt = ontology.classesInSignature().filter(cl -> cl.getIRI().toString().equals(prefix + ":" + name)).findFirst();
		else
			opt = ontology.classesInSignature().filter(cl -> cl.getIRI().toString().equals(name)).findFirst();
			
		if (opt.isPresent()) {
			return (OntologyClass)api2internalMap.get(opt.get());
		}

		return null;
	}

	@Override
	public Stream<OntologyClass> getAllClasses() {
		return api2internalMap.values().stream().filter(obj -> obj instanceof OntologyClass).map(c -> (OntologyClass)c);
	}

	@Override
	public OntologyIndividual createIndividual(final String name) {
		return createIndividual(name, STR_EMPTY);
	}

	@Override
	public OntologyIndividual createIndividual(final String name, final String prefix) {
		String fullIndividualName = buildFullEntityString(name, prefix);
		
		OWLNamedIndividual individual = factory.getOWLNamedIndividual(fullIndividualName, prefixMgr);
		OWLAxiom declareNewIndividual = factory.getOWLDeclarationAxiom(individual);
		
		manager.addAxiom(ontology, declareNewIndividual);
		
		OntologyIndividual ind = OntologyFactory.eINSTANCE.createOntologyIndividual();
		ind.setName(name);
		
		if (!prefix.isEmpty())
			ind.setPrefix(prefixMgr.getPrefix(prefix));
		
		ind.setContainingOntology(getOntology());
		
		toMap(individual, ind);
		
		return ind;
	}

	@Override
	public void assignIndividualToClass(OntologyIndividual individual, OntologyClass clazz) {
		manager.addAxiom(ontology, factory.getOWLClassAssertionAxiom(getOWLClass(clazz), getOWLIndividual(individual)));
		
		clazz.getIndividuals().add(individual);
		individual.getTypes().add(clazz);
	}
	
	@Override
	public OntologyIndividual getIndividual(String name) {
		return getIndividual(name, STR_EMPTY);
	}

	@Override
	public OntologyIndividual getIndividual(String name, String prefix) {
		Optional<OWLNamedIndividual> opt;
		
		if (!prefix.isEmpty())
			opt = ontology.individualsInSignature().filter(ind -> ind.getIRI().toString().equals(prefix + ":" + name)).findFirst();
		else
			opt = ontology.individualsInSignature().filter(ind -> ind.getIRI().toString().equals(name)).findFirst();
			
		if (opt.isPresent()) {
			return (OntologyIndividual)api2internalMap.get(opt.get());
		}

		return null;
	}

	@Override
	public Stream<OntologyIndividual> getAllIndividuals() {
		return api2internalMap.values().stream().filter(obj -> obj instanceof OntologyIndividual).map(i -> (OntologyIndividual)i);
	}

	/**
	 * Internal helper method to retrieve an OWLClass of the internal map.
	 * 
	 * @param c The OntologyClass object, for which the appropriate OWL-object should be found.
	 * @return The OWLClass object, or null if it is not existing.
	 */
	private OWLClass getOWLClass(OntologyClass c) {
		OWLObject obj = internal2apiMap.get(c);
		
		if (obj instanceof OWLClass)
			return (OWLClass)obj;
		else
			return null;
	}
	
	/**
	 * Internal helper method to retrieve an OWLNamedIndividual of the internal map.
	 * 
	 * @param i The OntologyIndividual object, for which the appropriate OWL-object should be found.
	 * @return The OWLNamedIndividual object, or null if it is not existing.
	 */
	private OWLNamedIndividual getOWLIndividual(OntologyIndividual i) {
		OWLObject obj = internal2apiMap.get(i);
		
		if (obj instanceof OWLNamedIndividual)
			return (OWLNamedIndividual)obj;
		else
			return null;
	}
	
	/**
	 * Puts an OWLObject and an internal API-object in the internal maps.
	 * 
	 * @param apiObj The OWL-API-object.
	 * @param internalObj The internal API-object.
	 */
	private void toMap(OWLObject apiObj, OntologyItem internalObj) {
		internal2apiMap.put(internalObj, apiObj);
		api2internalMap.put(apiObj, internalObj);
	}
	
	/**
	 * Builds the full name of an entity, consisting of an optional prefix, followed by a colon and the short name.
	 * 
	 * @param name The name of the entity.
	 * @param prefix The prefix of the entity or an empty string.
	 * @return The concatinated full name string.
	 */
	private String buildFullEntityString(final String name, final String prefix) {
		if (prefix.isEmpty())
			return name;
		else
			return prefix.concat(":" + name);
	}
}
