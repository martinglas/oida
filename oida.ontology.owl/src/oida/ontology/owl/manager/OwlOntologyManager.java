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
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyChange;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLReflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLSubObjectPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLTransitiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.parameters.Imports;
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

	private OWLOntologyManager owlOntologyManager;
	private OWLOntology owlOntology;
	private OWLDataFactory owlDataFactory;
	private OWLXMLDocumentFormat owlPrefixManager;

	// private OWLReasoner reasoner;

	OwlOntologyManager() {
		internal2apiMap = new HashMap<OntologyItem, OWLObject>();
		api2internalMap = new HashMap<OWLObject, OntologyItem>();

		owlOntologyManager = OWLManager.createOWLOntologyManager();
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

		owlOntologyManager.getIRIMappers().add(new SimpleIRIMapper(IRI.create(iri), IRI.create(localPath)));
	}

	@Override
	public Ontology createOntology(String iri) throws OntologyManagerException {
		try {
			owlPrefixManager = new OWLXMLDocumentFormat();

			owlOntology = owlOntologyManager.createOntology(IRI.create(iri));
			owlDataFactory = owlOntologyManager.getOWLDataFactory();

			owlOntologyManager.setOntologyFormat(owlOntology, owlPrefixManager);

			Ontology o = generateInternalOntologyObject(iri, owlOntology.classesInSignature().count(), owlOntology.individualsInSignature().count());
			setOntology(o);

			for (String prefixName : owlPrefixManager.getPrefixName2PrefixMap().keySet())
				generateInternalNamespaceObject(o, prefixName, owlPrefixManager.getPrefixName2PrefixMap().get(prefixName));

			// create thing-class in oida internal model:
			OWLClass thingClass = owlDataFactory.getOWLThing();
			OntologyClass thingOClass = generateInternalClassObject(o, thingClass.getIRI().getNamespace(), thingClass.getIRI().getShortForm());
			toMap(thingClass, thingOClass);

			System.out.println(MESSAGE_PREFIX + "Ontology created: '" + iri + "'");

			return o;
		} catch (OWLOntologyCreationException e) {
			throw new OntologyManagerException(MESSAGE_PREFIX + "Error while creating ontology '" + iri + "': " + e.getMessage(), e);
		}
	}

	@Override
	public Ontology loadOntology(OntologyFile ontologyFile) throws OntologyManagerException {
		if (ontologyFile == null)
			return null;

		File file = getOntologyFileObject(ontologyFile);
		if (file != null) {
			try {
				owlOntology = owlOntologyManager.loadOntologyFromOntologyDocument(file);
				owlDataFactory = owlOntologyManager.getOWLDataFactory();
				owlPrefixManager = new OWLXMLDocumentFormat();

				owlPrefixManager.copyPrefixesFrom(owlOntologyManager.getOntologyFormat(owlOntology).asPrefixOWLDocumentFormat());

				System.out.println(MESSAGE_PREFIX + "Ontology loaded: '" + file.getName() + "'");

				Ontology o = generateInternalOntologyObject(owlOntology.getOntologyID().getOntologyIRI().get().toString(), owlOntology.classesInSignature(Imports.INCLUDED).count(),
						owlOntology.individualsInSignature().count());
				setOntology(o);
				setOntologyFile(ontologyFile);

				for (String prefixName : owlPrefixManager.getPrefixName2PrefixMap().keySet())
					generateInternalNamespaceObject(o, prefixName, owlPrefixManager.getPrefixName2PrefixMap().get(prefixName));

				for (OWLClass owlClass : owlOntology.classesInSignature(Imports.INCLUDED).collect(Collectors.toList()))
					toMap(owlClass, generateInternalClassObject(o, getPrefixOfNamespace(owlClass.getIRI().getNamespace()), owlClass.getIRI().getShortForm()));

				for (OWLNamedIndividual owlIndividual : owlOntology.individualsInSignature(Imports.INCLUDED).collect(Collectors.toList())) {
					OntologyIndividual individual = generateInternalIndividualObject(o, getPrefixOfNamespace(owlIndividual.getIRI().getNamespace()), owlIndividual.getIRI().getShortForm());
					toMap(owlIndividual, individual);

					for (OWLClassAssertionAxiom a : owlOntology.classAssertionAxioms(owlIndividual).collect(Collectors.toList())) {
						if (a.getClassExpression().isOWLClass()) {
							OntologyClass c = getInternalClass(a.getClassExpression().asOWLClass());

							if (c != null)
								c.getIndividuals().add(individual);
						}
					}
				}
				
				for (OWLOntology importOntology : owlOntology.imports().collect(Collectors.toList()))
					o.getImports().add(importOntology.getOntologyID().getDefaultDocumentIRI().get().toString());

				o.setOntologyFile(ontologyFile);
				return o;
			} catch (Exception e) {
				throw new OntologyManagerException(MESSAGE_PREFIX + "Error while loading ontology from file '" + file.getName() + "': " + e.getMessage(), e);
			}
		} else {
			throw new OntologyManagerException(MESSAGE_PREFIX + "Error while loading ontology: File doesn't exist.");
		}
	}

	private String getPrefixOfNamespace(String namespace) {
		for (Entry<String, String> entry : owlPrefixManager.getPrefixName2PrefixMap().entrySet()) {
			if (entry.getValue().equals(namespace))
				return entry.getKey().substring(0, entry.getKey().length() - 1);
		}

		return STR_EMPTY;
	}

	@Override
	public void saveOntology() throws OntologyManagerException {
		File file = new File(getOntology().getOntologyFile().getPath() + getOntology().getOntologyFile().getFileName());

		try {
			FileOutputStream outputStream = new FileOutputStream(file);
			owlOntologyManager.saveOntology(owlOntology, owlPrefixManager, outputStream);
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

		if (!prefInternal.endsWith(STR_HASHTAG))
			prefInternal = prefInternal.concat(STR_HASHTAG);

		if (setDefault) {
			owlPrefixManager.setDefaultPrefix(prefInternal);
			generateInternalNamespaceObject(getOntology(), STR_EMPTY, prefInternal);
		} else {
			owlPrefixManager.setPrefix(prefixName, prefInternal);
			generateInternalNamespaceObject(getOntology(), prefixName, prefInternal);
		}
	}

	@Override
	public boolean isNamespaceExisting(String prefix) {
		return owlPrefixManager.containsPrefixMapping(prefix + ":");
	}

	@Override
	public String getDefaultNamespace() {
		return owlPrefixManager.getDefaultPrefix().replace(STR_HASHTAG, STR_EMPTY);
	}

	@Override
	public String getNamespace(String prefix) {
		if (isNamespaceExisting(prefix))
			return owlPrefixManager.getPrefix(prefix + STR_COLON).replace(STR_HASHTAG, STR_EMPTY);
		else
			return STR_EMPTY;
	}

	@Override
	public Map<String, String> getAllNamespaces() {
		return owlPrefixManager.getPrefixName2PrefixMap();
	}

	@Override
	public void addImportDeclaration(String importOntologyIRIString) throws OntologyManagerException {
		if (getOntology().getImports().contains(importOntologyIRIString))
			return;
		
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
		if (clazz != null)
			return clazz;

		String fullClassName = buildFullEntityString(name, prefix);

		OWLClass newClass = owlDataFactory.getOWLClass(fullClassName, owlPrefixManager);
		OWLAxiom declareNewClass = owlDataFactory.getOWLDeclarationAxiom(newClass);

		owlOntologyManager.addAxiom(owlOntology, declareNewClass);

		clazz = generateInternalClassObject(getOntology(), prefix, name);

		if (!prefix.isEmpty())
			clazz.setPrefix(prefix);

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
		return createIndividual(name, STR_EMPTY);
	}

	@Override
	public OntologyIndividual createIndividual(final String name, final String prefix) {
		OntologyIndividual individual = getIndividual(name, prefix);
		if (individual != null)
			return individual;

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

		entity.setName(newName);

		owlOntologyManager.applyChanges(changes);
	}

	@Override
	public void assignIndividualToClass(OntologyIndividual individual, OntologyClass clazz) {
		owlOntologyManager.addAxiom(owlOntology, owlDataFactory.getOWLClassAssertionAxiom(getOWLClass(clazz), getOWLIndividual(individual)));

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
	public OntologyObjectProperty createObjectProperty(String propertyName, String prefix) {
		String fullPropertyName = buildFullEntityString(propertyName, prefix);

		OWLObjectProperty owlProperty = owlDataFactory.getOWLObjectProperty(fullPropertyName, owlPrefixManager);
		OWLDeclarationAxiom owlAxiom = owlDataFactory.getOWLDeclarationAxiom(owlProperty);
		owlOntologyManager.addAxiom(owlOntology, owlAxiom);

		OntologyObjectProperty prop = generateInternalObjectPropertyObject(getOntology(), prefix, propertyName);
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

	private OWLEntity getOWLEntity(OntologyEntity i) {
		OWLObject owlObj = internal2apiMap.get(i);

		if (owlObj instanceof OWLEntity)
			return (OWLEntity)owlObj;
		else
			return null;
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

		if (owlObj instanceof OWLObjectProperty)
			return (OWLObjectProperty)owlObj;
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
