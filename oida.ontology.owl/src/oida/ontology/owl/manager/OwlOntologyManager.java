package oida.ontology.owl.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.PrefixDocumentFormat;
import org.semanticweb.owlapi.formats.RDFXMLDocumentFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.parameters.Imports;
import org.semanticweb.owlapi.util.SimpleIRIMapper;

import oida.ontology.Ontology;
import oida.ontology.OntologyAnnotation;
import oida.ontology.OntologyAnnotationProperty;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyIndividual;
import oida.ontology.OntologyItem;
import oida.ontology.OntologyObjectProperty;
import oida.ontology.manager.AbstractOntologyManager;
import oida.ontology.manager.OntologyManagerException;
import oida.ontologyMgr.OntologyFile;

/**
 * 
 * @author Michael.Shamiyeh
 *
 */
public class OwlOntologyManager extends AbstractOntologyManager {
	private static String MESSAGE_PREFIX = "OIDA OWL Ontology Manager: ";

	private HashMap<OntologyItem, OWLObject> internal2apiMap;
	private HashMap<OWLObject, OntologyItem> api2internalMap;

	private OWLOntologyManager manager;
	private OWLOntology ontology;
	private OWLDataFactory factory;
	private PrefixDocumentFormat prefixMgr;

	// private OWLReasoner reasoner;

	OwlOntologyManager() {
		internal2apiMap = new HashMap<OntologyItem, OWLObject>();
		api2internalMap = new HashMap<OWLObject, OntologyItem>();

		manager = OWLManager.createOWLOntologyManager();
	}

	@Override
	public void initializeReasoner() {
		// reasoner =
		// PelletReasonerFactory.getInstance().createReasoner(ontology);

		// ((PelletReasoner)reasoner).getKB().realize();
		// ((PelletReasoner)reasoner).getKB().printClassTree();
	}

	@Override
	public void addGlobalIRIToLocalPathMapping(String iri, String localPath) {
		localPath = "file:/" + localPath.replace("\\", "/");

		manager.getIRIMappers().add(new SimpleIRIMapper(IRI.create(iri), IRI.create(localPath)));
	}

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

	// public void createDataTypeProperty(String name, String domainName) {
	// OWLDataProperty property = factory.getOWLDataProperty(name, prefixMgr);
	// manager.addAxiom(ontology,
	// factory.getOWLDataPropertyDomainAxiom(property,
	// getClassByName(domainName)));
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
		try {
			File file = getOntologyFile(ontologyFile, true);

			if (file != null) {
				ontology = manager.createOntology(IRI.create(file));
				factory = manager.getOWLDataFactory();
				prefixMgr = new RDFXMLDocumentFormat();

				Ontology o = generateInternalOntologyObject(ontologyFile, file.getName(), ontology.classesInSignature().count(), ontology.individualsInSignature().count());
				setOntology(o);
				
				manager.saveOntology(ontology);
				System.out.println(MESSAGE_PREFIX + "Ontology created: '" + file.getName() + "'");

				return o;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new OntologyManagerException(MESSAGE_PREFIX + "Ontology creation failed: " + e.getMessage());
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
				System.out.println(MESSAGE_PREFIX + "Ontology loaded: '" + file.getName() + "'");

				Ontology o = generateInternalOntologyObject(null, ontology.toString(), ontology.classesInSignature(Imports.INCLUDED).count(), ontology.individualsInSignature().count());
				setOntology(o);

				for (OWLClass owlClass : ontology.classesInSignature(Imports.INCLUDED).collect(Collectors.toList()))
					toMap(owlClass, generateInternalClassObject(o, owlClass.getIRI().getNamespace(), owlClass.getIRI().getShortForm()));

				for (OWLNamedIndividual owlIndividual : ontology.individualsInSignature(Imports.INCLUDED).collect(Collectors.toList())) {
					OntologyIndividual individual = generateInternalIndividualObject(o, owlIndividual.getIRI().getNamespace(), owlIndividual.getIRI().getShortForm());
					toMap(owlIndividual, individual);

					for (OWLClassAssertionAxiom a : ontology.classAssertionAxioms(owlIndividual).collect(Collectors.toList())) {
						if (a.getClassExpression().isOWLClass()) {
							OntologyClass c = getInternalClass(a.getClassExpression().asOWLClass());

							if (c != null)
								c.getIndividuals().add(individual);
						}
					}
				}

				return o;
			} catch (Exception e) {
				throw new OntologyManagerException(MESSAGE_PREFIX + "Error while loading ontology from file '" + file.getName() + "': " + e.getMessage(), e);
			}
		} else {
			return createOntology(ontologyFile);
		}
	}

	@Override
	public void saveOntology() throws OntologyManagerException {
		File file = new File(getOntology().getOntologyEntry().getPath() + getOntology().getOntologyEntry().getFileName());

		try {
			FileOutputStream outputStream = new FileOutputStream(file);
			manager.saveOntology(ontology, prefixMgr, outputStream);
		} catch (FileNotFoundException e) {
			throw new OntologyManagerException(MESSAGE_PREFIX + "Error while saving ontology to file '" + file.getName() + "': " + e.getMessage(), e);
		} catch (Exception e) {
			throw new OntologyManagerException(MESSAGE_PREFIX + "Error while saving ontology to file '" + file.getName() + "': " + e.getMessage(), e);
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
	public Map<String, String> getAllNamespaces() {
		return prefixMgr.getPrefixName2PrefixMap();
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

		OntologyClass clazz = generateInternalClassObject(getOntology(), prefix, name);

		if (!prefix.isEmpty())
			clazz.setPrefix(prefixMgr.getPrefix(prefix));

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
			opt = ontology.classesInSignature(Imports.INCLUDED).filter(cl -> cl.getIRI().toString().equals(prefix + ":" + name)).findFirst();
		else
			opt = ontology.classesInSignature(Imports.INCLUDED).filter(cl -> cl.getIRI().toString().equals(name)).findFirst();

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

		OntologyIndividual ind = generateInternalIndividualObject(getOntology(), prefix, name);

		toMap(individual, ind);

		return ind;
	}

	@Override
	public void assignIndividualToClass(OntologyIndividual individual, OntologyClass clazz) {
		manager.addAxiom(ontology, factory.getOWLClassAssertionAxiom(getOWLClass(clazz), getOWLIndividual(individual)));

		clazz.getIndividuals().add(individual);
		individual.getTypes().add(clazz);
	}

	// public void createClassAnnotationProperty(String name, String domainName)
	// {
	// OWLAnnotationProperty property = factory.getOWLAnnotationProperty(name,
	// prefixMgr);
	// manager.addAxiom(ontology, property);
	// //factory.getOWLAnnotationPropertyDomainAxiom(property,
	// getClassByName(domainName).getIRI()));
	// }

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

	@Override
	public OntologyObjectProperty createObjectProperty(String propertyName, String prefix) {
		String fullPropertyName = buildFullEntityString(propertyName, prefix);
		
		OWLObjectProperty owlProperty = factory.getOWLObjectProperty(fullPropertyName, prefixMgr);
		OWLDeclarationAxiom owlAxiom = factory.getOWLDeclarationAxiom(owlProperty);
		manager.addAxiom(ontology, owlAxiom);
		
		OntologyObjectProperty prop = generateInternalObjectPropertyObject(getOntology(), prefix, propertyName);
		toMap(owlProperty, prop);
		
		return prop;
	}

	@Override
	public void assignObjectPropertyRange(OntologyObjectProperty property, OntologyClass range) {
		OWLObjectPropertyRangeAxiom owlAxiom = factory.getOWLObjectPropertyRangeAxiom((OWLObjectProperty)internal2apiMap.get(property), getOWLClass(range));
		manager.addAxiom(ontology, owlAxiom);
	}

	@Override
	public void assignObjectPropertyDomain(OntologyObjectProperty property, OntologyClass domain) {
		OWLObjectPropertyDomainAxiom owlAxiom = factory.getOWLObjectPropertyDomainAxiom((OWLObjectProperty)internal2apiMap.get(property), getOWLClass(domain));
		manager.addAxiom(ontology, owlAxiom);
	}
	
	@Override
	public OntologyAnnotationProperty createAnnotationProperty(String propertyName, String prefix) {
		String fullPropertyName = buildFullEntityString(propertyName, prefix);
		
		OWLAnnotationProperty owlProperty = factory.getOWLAnnotationProperty(fullPropertyName, prefixMgr);
		OWLDeclarationAxiom owlAxiom = factory.getOWLDeclarationAxiom(owlProperty);
		
		manager.addAxiom(ontology, owlAxiom);
		
		OntologyAnnotationProperty prop = generateInternalAnnotationPropertyObject(getOntology(), prefix, propertyName);
		toMap(owlProperty, prop);
		
		return prop;
	}

	@Override
	public OntologyAnnotation annotateClass(OntologyAnnotationProperty property, String annotationValue, OntologyClass clazz) {
		OWLAnnotation owlAnnotation = factory.getOWLAnnotation((OWLAnnotationProperty)internal2apiMap.get(property), factory.getOWLLiteral(annotationValue));
		OWLAnnotationAssertionAxiom owlAxiom = factory.getOWLAnnotationAssertionAxiom(getOWLClass(clazz).getIRI(), owlAnnotation);
		
		manager.addAxiom(ontology, owlAxiom);
		
		OntologyAnnotation annotation = generateInternalAnnotationObject(getOntology(), property, annotationValue);
		clazz.getAnnotations().add(annotation);
		toMap(owlAnnotation, annotation);
		
		return annotation;
	}

	/**
	 * Internal helper method to retrieve an OWLClass of the internal map.
	 * 
	 * @param c
	 *            The OntologyClass object, for which the appropriate OWL-object
	 *            should be found.
	 * @return The OWLClass object, or null if it is not existing.
	 */
	private OWLClass getOWLClass(OntologyClass c) {
		OWLObject owlObj = internal2apiMap.get(c);

		if (owlObj instanceof OWLClass)
			return (OWLClass)owlObj;
		else
			return null;
	}

	/**
	 * Internal helper method to retrieve an OWLNamedIndividual of the internal
	 * map.
	 * 
	 * @param i
	 *            The OntologyIndividual object, for which the appropriate
	 *            OWL-object should be found.
	 * @return The OWLNamedIndividual object, or null if it is not existing.
	 */
	private OWLNamedIndividual getOWLIndividual(OntologyIndividual i) {
		OWLObject owlObj = internal2apiMap.get(i);

		if (owlObj instanceof OWLNamedIndividual)
			return (OWLNamedIndividual)owlObj;
		else
			return null;
	}

	/**
	 * Internal helper method to retrieve an OntologyClass of the internal map.
	 * 
	 * @param c
	 *            The OWLClass object, for which the appropriate OntologyItem
	 *            should be found.
	 * @return The OntologyClass object, or null, if it is not existing.
	 */
	private OntologyClass getInternalClass(final OWLClass c) {
		if (api2internalMap.containsKey(c) && api2internalMap.get(c) instanceof OntologyClass)
			return (OntologyClass)api2internalMap.get(c);
		else
			return null;
	}

	/**
	 * Internal helper method to retrieve an OntologyIndividual of the internal
	 * map.
	 * 
	 * @param i
	 *            The OWLNamedIndividual object, for which the appropriate
	 *            OntologyItem should be found.
	 * @return The OntologyIndividual object, or null, if it is not existing.
	 */
	@SuppressWarnings("unused")
	private OntologyIndividual getInternalIndividual(final OWLNamedIndividual i) {
		if (api2internalMap.containsKey(i) && api2internalMap.get(i) instanceof OntologyIndividual)
			return (OntologyIndividual)api2internalMap.get(i);
		else
			return null;
	}

	/**
	 * Puts an OWLObject and an internal API-object in the internal maps.
	 * 
	 * @param apiObj
	 *            The OWL-API-object.
	 * @param internalObj
	 *            The internal API-object.
	 */
	private void toMap(OWLObject apiObj, OntologyItem internalObj) {
		internal2apiMap.put(internalObj, apiObj);
		api2internalMap.put(apiObj, internalObj);
	}

	/**
	 * Builds the full name of an entity, consisting of an optional prefix,
	 * followed by a colon and the short name.
	 * 
	 * @param name
	 *            The name of the entity.
	 * @param prefix
	 *            The prefix of the entity or an empty string.
	 * @return The concatinated full name string.
	 */
	private String buildFullEntityString(final String name, final String prefix) {
		if (prefix.isEmpty())
			return name;
		else
			return prefix.concat(":" + name);
	}
}
