/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.owl.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.OWLXMLDocumentFormat;
import org.semanticweb.owlapi.model.AddImport;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAsymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.model.OWLInverseFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLInverseObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLIrreflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyChange;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.OWLReflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.OWLSubObjectPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLTransitiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.parameters.Imports;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.util.OWLEntityRenamer;
import org.semanticweb.owlapi.util.SimpleIRIMapper;

import oida.ontology.Ontology;
import oida.ontology.OntologyAnnotation;
import oida.ontology.OntologyAnnotationProperty;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyIndividual;
import oida.ontology.OntologyItem;
import oida.ontology.OntologyObjectProperty;
import oida.ontology.OntologyObjectPropertyAssertion;
import oida.ontology.manager.AbstractOntologyManager;
import oida.ontology.manager.OntologyManagerException;
import oida.ontologyMgr.OntologyFile;
import oida.util.constants.StringConstants;

/**
 * 
 * @author Michael.Shamiyeh
 *
 */
public class OwlOntologyManager extends AbstractOntologyManager {
	private static final String MESSAGE_PREFIX = "OIDA OWL Ontology Manager: ";

	private HashMap<OntologyItem, OWLObject> internal2apiMap;
	private HashMap<OWLObject, OntologyItem> api2internalMap;

	private OntologyClass thingClass;
	private OntologyObjectProperty topObjectProperty;

	private OWLOntologyManager owlOntologyManager;
	private OWLOntology owlOntology;
	private OWLDataFactory owlDataFactory;
	private OWLXMLDocumentFormat owlPrefixManager;

	private OWLReasoner reasoner;

	/**
	 * Standard Constructor.
	 */
	OwlOntologyManager() {
		clearOntology();
	}

	@Override
	public void clearOntology() {
		internal2apiMap = new HashMap<OntologyItem, OWLObject>();
		api2internalMap = new HashMap<OWLObject, OntologyItem>();

		owlPrefixManager = new OWLXMLDocumentFormat();
		owlOntologyManager = OWLManager.createOWLOntologyManager();
		owlDataFactory = owlOntologyManager.getOWLDataFactory();
	}

	@Override
	public void initializeReasoner() {
		// reasoner =
		// PelletReasonerFactory.getInstance().createReasoner(owlOntology);
		//
		// ((PelletReasoner)reasoner).getKB().realize();
		// ((PelletReasoner)reasoner).getKB().printClassTree();
	}

	@Override
	public void addGlobalIRIToLocalPathMapping(String iri, String localPath) {
		localPath = "file:/" + localPath.replace("\\", "/");

		owlOntologyManager.getIRIMappers().add(new SimpleIRIMapper(IRI.create(iri), IRI.create(localPath)));
	}

	private void initialize() {
		internal2apiMap.clear();
		api2internalMap.clear();

		owlPrefixManager.clear();
		owlOntologyManager.clearOntologies();
	}

	@Override
	public Ontology createOntology(String iri) throws OntologyManagerException {
		initialize();

		try {
			owlOntology = owlOntologyManager.createOntology(IRI.create(iri));
			owlOntologyManager.setOntologyFormat(owlOntology, owlPrefixManager);

			Ontology o = generateInternalOntologyObject(iri, owlOntology.classesInSignature().count(), owlOntology.individualsInSignature().count());
			setOntology(o);

			initThingClass(o);

			for (String prefixName : owlPrefixManager.getPrefixName2PrefixMap().keySet()) {
				generateInternalNamespaceObject(o, prefixName, owlPrefixManager.getPrefixName2PrefixMap().get(prefixName));
			}

			System.out.println(MESSAGE_PREFIX + "Ontology created: '" + iri + "'");

			return o;
		} catch (OWLOntologyCreationException e) {
			throw new OntologyManagerException(MESSAGE_PREFIX + "Error while creating ontology '" + iri + "': " + e.getMessage(), e);
		}
	}

	@Override
	public Ontology loadOntology(OntologyFile ontologyFile) throws OntologyManagerException {
		initialize();

		if (ontologyFile == null) {
			return null;
		}

		File file = getOntologyFileObject(ontologyFile);
		if (file != null && file.exists()) {
			try {
				owlPrefixManager.clear();
				owlOntology = owlOntologyManager.loadOntologyFromOntologyDocument(file);

				owlPrefixManager.copyPrefixesFrom(owlOntologyManager.getOntologyFormat(owlOntology).asPrefixOWLDocumentFormat());

				Ontology o = generateInternalOntologyObject(owlOntology.getOntologyID().getOntologyIRI().get().toString(), owlOntology.classesInSignature(Imports.INCLUDED).count(),
						owlOntology.individualsInSignature().count());
				setOntology(o);
				setOntologyFile(ontologyFile);

				initThingClass(o);
				initTopObjectProperty(o);

				for (String prefixName : owlPrefixManager.getPrefixName2PrefixMap().keySet()) {
					generateInternalNamespaceObject(o, prefixName, owlPrefixManager.getPrefixName2PrefixMap().get(prefixName));
				}

				extractClassHierarchy(owlOntology, thingClass, true);
				extractObjectPropertyHierarchy(owlOntology, topObjectProperty, true);

				for (OWLNamedIndividual owlIndividual : owlOntology.individualsInSignature(Imports.INCLUDED).collect(Collectors.toList())) {
					OntologyIndividual individual = generateInternalIndividualObject(o, getPrefixOfNamespace(owlIndividual.getIRI().getNamespace()), owlIndividual.getIRI().getShortForm());
					toMap(owlIndividual, individual);

					for (OWLClassAssertionAxiom a : owlOntology.classAssertionAxioms(owlIndividual).collect(Collectors.toList())) {
						if (a.getClassExpression().isOWLClass()) {
							OntologyClass c = getInternalClass(a.getClassExpression().asOWLClass());

							if (c != null) {
								c.getIndividuals().add(individual);
							}
						}
					}
				}

				for (OWLOntology importOntology : owlOntology.imports().collect(Collectors.toList())) {
					o.getImports().add(importOntology.getOntologyID().getDefaultDocumentIRI().get().toString());
				}

				o.setOntologyFile(ontologyFile);

				System.out.println(MESSAGE_PREFIX + "Ontology loaded: '" + file.getName() + "'");

				return o;
			} catch (OWLOntologyCreationException e) {
				throw new OntologyManagerException(MESSAGE_PREFIX + "Error while loading ontology from file '" + file.getName() + "': " + e.getMessage(), e);
			}
		} else {
			throw new OntologyManagerException(MESSAGE_PREFIX + "Error while loading ontology: File doesn't exist.");
		}
	}

	protected void extractClassHierarchy(OWLOntology owlOntology, OntologyClass thingClass, boolean includeImports) {
		Imports imports = Imports.INCLUDED;

		if (!includeImports)
			imports = Imports.EXCLUDED;

		List<OWLClass> allClasses = owlOntology.classesInSignature(imports).collect(Collectors.toList());

		for (OWLClass owlClass : allClasses)
			toMap(owlClass, generateInternalClassObject(getOntology(), null, getPrefixOfNamespace(owlClass.getIRI().getNamespace()), owlClass.getIRI().getShortForm()));

		for (OWLClass owlClass : allClasses) {
			if (!owlClass.equals(owlDataFactory.getOWLThing())) {
				List<OWLSubClassOfAxiom> axioms = owlOntology.axioms(owlClass, imports).filter(ax -> ax instanceof OWLSubClassOfAxiom).map(ax -> (OWLSubClassOfAxiom)ax).collect(Collectors.toList());

				OntologyClass internalClass = getInternalClass(owlClass);

				if (axioms.size() == 0) {
					thingClass.getSubClasses().add(internalClass);
					internalClass.getSuperClasses().add(thingClass);
				} else {
					for (OWLSubClassOfAxiom axiom : axioms) {
						if (axiom.getSuperClass().isOWLClass()) {
							OWLClass superClass = axiom.getSuperClass().asOWLClass();
							OntologyClass internalSuperClass = getInternalClass(superClass);

							internalSuperClass.getSubClasses().add(internalClass);
							internalClass.getSuperClasses().add(internalSuperClass);

							if (internalClass.getSuperClasses().contains(thingClass)) {
								thingClass.getSubClasses().remove(internalClass);
								internalClass.getSuperClasses().remove(thingClass);
							}
						} else if (internalClass.getSuperClasses().isEmpty()) {
							thingClass.getSubClasses().add(internalClass);
							internalClass.getSuperClasses().add(thingClass);
						}
					}
				}

			}
		}
	}

	protected void extractObjectPropertyHierarchy(OWLOntology owlOntology, OntologyObjectProperty topObjectProperty, boolean includeImports) {
		Imports imports = Imports.INCLUDED;

		if (!includeImports)
			imports = Imports.EXCLUDED;

		List<OWLObjectProperty> allObjectProperties = owlOntology.objectPropertiesInSignature(imports).collect(Collectors.toList());

		for (OWLObjectProperty owlObjectProperty : allObjectProperties)
			toMap(owlObjectProperty,
					generateInternalObjectPropertyObject(getOntology(), topObjectProperty, getPrefixOfNamespace(owlObjectProperty.getIRI().getNamespace()), owlObjectProperty.getIRI().getShortForm()));

		for (OWLObjectProperty owlObjectProperty : allObjectProperties) {
			List<OWLSubObjectPropertyOfAxiom> axioms = owlOntology.axioms(owlObjectProperty, imports).filter(ax -> ax instanceof OWLSubObjectPropertyOfAxiom).map(ax -> (OWLSubObjectPropertyOfAxiom)ax)
					.collect(Collectors.toList());

			OntologyObjectProperty internalObjectProperty = getInternalObjectProperty(owlObjectProperty);

			if (axioms.size() != 0) {
				for (OWLSubObjectPropertyOfAxiom axiom : axioms) {
					if (axiom.getSuperProperty().isOWLObjectProperty()) {
						OWLObjectProperty superObjectProperty = axiom.getSuperProperty().asOWLObjectProperty();
						OntologyObjectProperty internalSuperObjectProperty = getInternalObjectProperty(superObjectProperty);

						internalSuperObjectProperty.getSubObjectProperties().add(internalObjectProperty);
						internalObjectProperty.getSuperObjectProperties().add(internalSuperObjectProperty);

						if (internalObjectProperty.getSuperObjectProperties().contains(topObjectProperty)) {
							topObjectProperty.getSubObjectProperties().remove(internalObjectProperty);
							internalObjectProperty.getSuperObjectProperties().remove(topObjectProperty);
						}
					} else if (internalObjectProperty.getSuperObjectProperties().isEmpty()) {
						topObjectProperty.getSubObjectProperties().add(internalObjectProperty);
						internalObjectProperty.getSuperObjectProperties().add(topObjectProperty);
					}
				}
			}
		}
	}

	protected void initThingClass(Ontology ontology) {
		OWLClass owlThingClass = owlDataFactory.getOWLThing();
		thingClass = generateInternalClassObject(ontology, null, owlThingClass.getIRI().getNamespace(), owlThingClass.getIRI().getShortForm());
		ontology.setClassHierarchy(thingClass);

		toMap(owlThingClass, thingClass);
	}

	protected void initTopObjectProperty(Ontology ontology) {
		OWLObjectProperty owlTopObjectProperty = owlDataFactory.getOWLTopObjectProperty();
		topObjectProperty = generateInternalObjectPropertyObject(ontology, null, owlTopObjectProperty.getIRI().getNamespace(), owlTopObjectProperty.getIRI().getShortForm());
		ontology.setObjectPropertyHierarchy(topObjectProperty);

		toMap(owlTopObjectProperty, topObjectProperty);
	}

	private String getPrefixOfNamespace(String namespace) {
		for (Entry<String, String> entry : owlPrefixManager.getPrefixName2PrefixMap().entrySet()) {
			if (entry.getValue().equals(namespace)) {
				return entry.getKey().substring(0, entry.getKey().length() - 1);
			}
		}

		return StringConstants.EMPTY;
	}

	@Override
	public void saveOntology() throws OntologyManagerException {
		File file = new File(getOntology().getOntologyFile().getPath() + getOntology().getOntologyFile().getFileName());

		try {
			FileOutputStream outputStream = new FileOutputStream(file);
			owlOntologyManager.saveOntology(owlOntology, owlPrefixManager, outputStream);
		} catch (FileNotFoundException e) {
			throw new OntologyManagerException(MESSAGE_PREFIX + "Error while saving ontology to file '" + file.getName() + "': " + e.getMessage(), e);
		} catch (OWLOntologyStorageException e) {
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

		if (!prefInternal.endsWith(StringConstants.HASHTAG)) {
			prefInternal = prefInternal.concat(StringConstants.HASHTAG);
		}

		if (setDefault) {
			owlPrefixManager.setDefaultPrefix(prefInternal);
			generateInternalNamespaceObject(getOntology(), StringConstants.EMPTY, prefInternal);
		} else {
			owlPrefixManager.setPrefix(prefixName, prefInternal);
			generateInternalNamespaceObject(getOntology(), prefixName, prefInternal);
		}
	}

	@Override
	public boolean isNamespaceExisting(String prefix) {
		return owlPrefixManager.containsPrefixMapping(prefix + StringConstants.COLON);
	}

	@Override
	public String getDefaultNamespace() {
		return owlPrefixManager.getDefaultPrefix().replace(StringConstants.HASHTAG, StringConstants.EMPTY);
	}

	@Override
	public String getNamespace(String prefix) {
		if (isNamespaceExisting(prefix)) {
			return owlPrefixManager.getPrefix(prefix + StringConstants.COLON).replace(StringConstants.HASHTAG, StringConstants.EMPTY);
		} else {
			return StringConstants.EMPTY;
		}
	}

	@Override
	public Map<String, String> getAllNamespaces() {
		return owlPrefixManager.getPrefixName2PrefixMap();
	}

	@Override
	public void addImportDeclaration(String importOntologyIRIString) throws OntologyManagerException {
		if (getOntology().getImports().contains(importOntologyIRIString)) {
			return;
		}

		IRI importOntologyIRI = IRI.create(importOntologyIRIString);
		OWLImportsDeclaration owlImportDeclaration = owlDataFactory.getOWLImportsDeclaration(importOntologyIRI);
		owlOntologyManager.applyChange(new AddImport(owlOntology, owlImportDeclaration));

		try {
			owlOntologyManager.loadOntology(importOntologyIRI);
		} catch (OWLOntologyCreationException e) {
			throw new OntologyManagerException(MESSAGE_PREFIX + "Error while loading ontology '" + importOntologyIRI.toString() + "': " + e.getMessage(), e);
		}
	}

	@Override
	public void addImportDeclaration(Ontology importOntology) throws OntologyManagerException {
		addImportDeclaration(IRI.create(new File(importOntology.getOntologyFile().getPath() + importOntology.getOntologyFile().getFileName())).toString());
	}

	@Override
	public OntologyClass createClass(String name, String prefix) {
		OntologyClass clazz = getClass(name, prefix);
		if (clazz != null) {
			return clazz;
		}

		String fullClassName = buildFullEntityString(name, prefix);

		OWLClass newClass = owlDataFactory.getOWLClass(fullClassName, owlPrefixManager);
		OWLAxiom declareNewClass = owlDataFactory.getOWLDeclarationAxiom(newClass);

		owlOntologyManager.addAxiom(owlOntology, declareNewClass);

		clazz = generateInternalClassObject(getOntology(), thingClass, prefix, name);

		if (!prefix.isEmpty()) {
			clazz.setPrefix(prefix);
		}

		toMap(newClass, clazz);

		return clazz;
	}

	@Override
	public void assignSubClassToSuperClass(OntologyClass subClass, OntologyClass superClass) {
		owlOntologyManager.addAxiom(owlOntology, owlDataFactory.getOWLSubClassOfAxiom(getOWLClass(subClass), getOWLClass(superClass)));

		subClass.getSuperClasses().add(superClass);
		superClass.getSubClasses().add(subClass);
	}

	@Override
	public OntologyIndividual createIndividual(final String name) {
		return createIndividual(name, StringConstants.EMPTY);
	}

	@Override
	public OntologyIndividual createIndividual(final String name, final String prefix) {
		OntologyIndividual individual = getIndividual(name, prefix);
		if (individual != null) {
			return individual;
		}

		String fullIndividualName = buildFullEntityString(name, prefix);

		OWLNamedIndividual owlIndividual = owlDataFactory.getOWLNamedIndividual(fullIndividualName, owlPrefixManager);
		OWLAxiom declareNewIndividual = owlDataFactory.getOWLDeclarationAxiom(owlIndividual);

		owlOntologyManager.addAxiom(owlOntology, declareNewIndividual);

		OntologyIndividual ind = generateInternalIndividualObject(getOntology(), prefix, name);

		toMap(owlIndividual, ind);

		return ind;
	}

	@Override
	public void renameEntity(OntologyEntity entity, String newName) {
		Collection<OWLOntology> c = new ArrayList<OWLOntology>();
		c.add(owlOntology);

		OWLEntityRenamer renamer = new OWLEntityRenamer(owlOntologyManager, c);

		OWLEntity renameEntity = getOWLEntity(entity);
		List<OWLOntologyChange> changes = renamer.changeIRI(renameEntity, IRI.create(renameEntity.getIRI().toString().replace(entity.getName(), newName)));
		owlOntologyManager.applyChanges(changes);

		entity.setName(newName);
		Optional<OWLEntity> optEntity = changes.get(1).signature().findFirst();
		if (optEntity.isPresent())
			toMap(optEntity.get(), entity);
	}

	@Override
	public void assignIndividualToClass(OntologyIndividual individual, OntologyClass clazz) {
		owlOntologyManager.addAxiom(owlOntology, owlDataFactory.getOWLClassAssertionAxiom(getOWLClass(clazz), getOWLIndividual(individual)));

		clazz.getIndividuals().add(individual);
		individual.getTypes().add(clazz);
	}

	@Override
	public OntologyObjectProperty createObjectProperty(String propertyName, String prefix) {
		String fullPropertyName = buildFullEntityString(propertyName, prefix);

		OWLObjectProperty owlProperty = owlDataFactory.getOWLObjectProperty(fullPropertyName, owlPrefixManager);
		OWLDeclarationAxiom owlAxiom = owlDataFactory.getOWLDeclarationAxiom(owlProperty);
		owlOntologyManager.addAxiom(owlOntology, owlAxiom);

		OntologyObjectProperty prop = generateInternalObjectPropertyObject(getOntology(), topObjectProperty, prefix, propertyName);
		toMap(owlProperty, prop);

		return prop;
	}

	@Override
	public void assignSubObjectPropertyToSuperObjectProperty(OntologyObjectProperty subProperty, OntologyObjectProperty superProperty) {
		OWLSubObjectPropertyOfAxiom owlSubObjectPropertyAxiom = owlDataFactory.getOWLSubObjectPropertyOfAxiom(getOWLObjectProperty(subProperty), getOWLObjectProperty(superProperty));
		owlOntologyManager.addAxiom(owlOntology, owlSubObjectPropertyAxiom);

		superProperty.getSubObjectProperties().add(subProperty);
		subProperty.getSuperObjectProperties().add(superProperty);
	}

	@Override
	public void assignInverseObjectProperty(OntologyObjectProperty property, OntologyObjectProperty inverseProperty) {
		OWLInverseObjectPropertiesAxiom owlInverseAxiom = owlDataFactory.getOWLInverseObjectPropertiesAxiom(getOWLObjectProperty(property), getOWLObjectProperty(inverseProperty));
		owlOntologyManager.addAxiom(owlOntology, owlInverseAxiom);

		property.getInverseObjectProperties().add(inverseProperty);
		inverseProperty.getInverseObjectProperties().add(property);
	}

	@Override
	public void makeObjectPropertyFunctional(OntologyObjectProperty property) {
		OWLObjectProperty owlObjectProperty = getOWLObjectProperty(property);

		OWLFunctionalObjectPropertyAxiom owlFunctionalOP = owlDataFactory.getOWLFunctionalObjectPropertyAxiom(owlObjectProperty);
		owlOntologyManager.addAxiom(owlOntology, owlFunctionalOP);

		property.setFunctional(true);
	}

	@Override
	public void makeObjectPropertyInverseFunctional(OntologyObjectProperty property) {
		OWLObjectProperty owlObjectProperty = getOWLObjectProperty(property);

		OWLInverseFunctionalObjectPropertyAxiom owlInverseFunctionalOP = owlDataFactory.getOWLInverseFunctionalObjectPropertyAxiom(owlObjectProperty);
		owlOntologyManager.addAxiom(owlOntology, owlInverseFunctionalOP);

		property.setInverseFunctional(true);
	}

	@Override
	public void makeObjectPropertyTransitive(OntologyObjectProperty property) {
		OWLObjectProperty owlObjectProperty = getOWLObjectProperty(property);

		OWLTransitiveObjectPropertyAxiom owlTransitiveOP = owlDataFactory.getOWLTransitiveObjectPropertyAxiom(owlObjectProperty);
		owlOntologyManager.addAxiom(owlOntology, owlTransitiveOP);

		property.setTransitive(true);
	}

	@Override
	public void makeObjectPropertySymmetric(OntologyObjectProperty property) {
		OWLObjectProperty owlObjectProperty = getOWLObjectProperty(property);

		OWLSymmetricObjectPropertyAxiom owlSymmetricOP = owlDataFactory.getOWLSymmetricObjectPropertyAxiom(owlObjectProperty);
		owlOntologyManager.addAxiom(owlOntology, owlSymmetricOP);

		property.setSymmetric(true);
	}

	@Override
	public void makeObjectPropertyAsymmetric(OntologyObjectProperty property) {
		OWLObjectProperty owlObjectProperty = getOWLObjectProperty(property);

		OWLAsymmetricObjectPropertyAxiom owlAsymmetricOP = owlDataFactory.getOWLAsymmetricObjectPropertyAxiom(owlObjectProperty);
		owlOntologyManager.addAxiom(owlOntology, owlAsymmetricOP);

		property.setAsymmetric(true);
	}

	@Override
	public void makeObjectPropertyReflexive(OntologyObjectProperty property) {
		OWLObjectProperty owlObjectProperty = getOWLObjectProperty(property);

		OWLReflexiveObjectPropertyAxiom owlReflexiveOP = owlDataFactory.getOWLReflexiveObjectPropertyAxiom(owlObjectProperty);
		owlOntologyManager.addAxiom(owlOntology, owlReflexiveOP);

		property.setReflexive(true);
	}

	@Override
	public void makeObjectPropertyIrreflexive(OntologyObjectProperty property) {
		OWLObjectProperty owlObjectProperty = getOWLObjectProperty(property);

		OWLIrreflexiveObjectPropertyAxiom owlIrreflexiveOP = owlDataFactory.getOWLIrreflexiveObjectPropertyAxiom(owlObjectProperty);
		owlOntologyManager.addAxiom(owlOntology, owlIrreflexiveOP);

		property.setIrreflexive(true);
	}

	@Override
	public void assignObjectPropertyRange(OntologyObjectProperty property, OntologyClass range) {
		OWLObjectPropertyRangeAxiom owlAxiom = owlDataFactory.getOWLObjectPropertyRangeAxiom((OWLObjectProperty)internal2apiMap.get(property), getOWLClass(range));
		owlOntologyManager.addAxiom(owlOntology, owlAxiom);

		property.setRange(range);
	}

	@Override
	public void assignObjectPropertyDomain(OntologyObjectProperty property, OntologyClass domain) {
		OWLObjectPropertyDomainAxiom owlAxiom = owlDataFactory.getOWLObjectPropertyDomainAxiom((OWLObjectProperty)internal2apiMap.get(property), getOWLClass(domain));
		owlOntologyManager.addAxiom(owlOntology, owlAxiom);

		property.setDomain(domain);
	}

	@Override
	public OntologyObjectPropertyAssertion createObjectPropertyAssertion(OntologyObjectProperty property, OntologyIndividual individual, OntologyIndividual object) {
		OWLObjectProperty owlObjectProperty = getOWLObjectProperty(property);
		OWLNamedIndividual owlIndividual = getOWLIndividual(individual);
		OWLNamedIndividual owlObject = getOWLIndividual(object);

		OWLObjectPropertyAssertionAxiom owlAssertionAxiom = owlDataFactory.getOWLObjectPropertyAssertionAxiom(owlObjectProperty, owlIndividual, owlObject);
		owlOntologyManager.addAxiom(owlOntology, owlAssertionAxiom);

		OntologyObjectPropertyAssertion assertion = generateInternalObjectPropertyAssertionObject(getOntology(), property, object);
		individual.getObjectPropertyAssertions().add(assertion);

		return assertion;
	}

	@Override
	public OntologyAnnotationProperty createAnnotationProperty(String propertyName, String prefix) {
		String fullPropertyName = buildFullEntityString(propertyName, prefix);

		OWLAnnotationProperty owlProperty = owlDataFactory.getOWLAnnotationProperty(fullPropertyName, owlPrefixManager);
		OWLDeclarationAxiom owlAxiom = owlDataFactory.getOWLDeclarationAxiom(owlProperty);

		owlOntologyManager.addAxiom(owlOntology, owlAxiom);

		OntologyAnnotationProperty prop = generateInternalAnnotationPropertyObject(getOntology(), prefix, propertyName);
		toMap(owlProperty, prop);

		return prop;
	}

	@Override
	public OntologyAnnotation annotateClass(OntologyAnnotationProperty property, String annotationValue, OntologyClass clazz) {
		OWLAnnotation owlAnnotation = owlDataFactory.getOWLAnnotation((OWLAnnotationProperty)internal2apiMap.get(property), owlDataFactory.getOWLLiteral(annotationValue));
		OWLAnnotationAssertionAxiom owlAxiom = owlDataFactory.getOWLAnnotationAssertionAxiom(getOWLClass(clazz).getIRI(), owlAnnotation);

		owlOntologyManager.addAxiom(owlOntology, owlAxiom);

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

		if (owlObj instanceof OWLClass) {
			return (OWLClass)owlObj;
		} else {
			return null;
		}
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

		if (owlObj instanceof OWLNamedIndividual) {
			return (OWLNamedIndividual)owlObj;
		} else {
			return null;
		}
	}

	private OWLEntity getOWLEntity(OntologyEntity i) {
		OWLObject owlObj = internal2apiMap.get(i);

		if (owlObj instanceof OWLEntity) {
			return (OWLEntity)owlObj;
		} else {
			return null;
		}
	}

	/**
	 * Internal helper method to retrieve an OWLObjectProperty of the internal
	 * map.
	 * 
	 * @param i
	 *            The OntologyObjectProperty object, for which the appropriate
	 *            OWL-object should be found.
	 * @return The OWLObjectProperty object, or null if it is not existing.
	 */
	private OWLObjectProperty getOWLObjectProperty(OntologyEntity i) {
		OWLObject owlObj = internal2apiMap.get(i);

		if (owlObj instanceof OWLObjectProperty) {
			return (OWLObjectProperty)owlObj;
		} else {
			return null;
		}
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
		if (api2internalMap.containsKey(c) && api2internalMap.get(c) instanceof OntologyClass) {
			return (OntologyClass)api2internalMap.get(c);
		} else {
			return null;
		}
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
		if (api2internalMap.containsKey(i) && api2internalMap.get(i) instanceof OntologyIndividual) {
			return (OntologyIndividual)api2internalMap.get(i);
		} else {
			return null;
		}
	}

	/**
	 * Internal helper method to retrieve an OntologyObjectProperty of the
	 * internal map.
	 * 
	 * @param p
	 *            The OWLObjectProperty object, for which the appropriate
	 *            OntologyItem should be found.
	 * @return The OntologyObjectProperty object, or null, if it is not
	 *         existing.
	 */
	private OntologyObjectProperty getInternalObjectProperty(final OWLObjectProperty p) {
		if (api2internalMap.containsKey(p) && api2internalMap.get(p) instanceof OntologyObjectProperty) {
			return (OntologyObjectProperty)api2internalMap.get(p);
		} else {
			return null;
		}
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
		if (prefix.isEmpty()) {
			return name;
		} else {
			return prefix.concat(StringConstants.COLON + name);
		}
	}
}
