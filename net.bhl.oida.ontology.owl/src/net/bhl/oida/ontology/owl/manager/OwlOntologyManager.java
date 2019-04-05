/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package net.bhl.oida.ontology.owl.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.OWLXMLDocumentFormat;
import org.semanticweb.owlapi.model.AddImport;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAsymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.model.OWLInverseFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLInverseObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLIrreflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
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
import org.semanticweb.owlapi.util.OWLEntityRenamer;
import org.semanticweb.owlapi.util.OWLOntologyWalker;
import org.semanticweb.owlapi.util.OWLOntologyWalkerVisitor;
import org.semanticweb.owlapi.util.SimpleIRIMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.bhl.oida.ontology.manager.AbstractOntologyManager;
import net.bhl.oida.ontology.manager.OntologyManagerException;
import net.bhl.oida.ontology.manager.util.OntologyManagerUtils;
import net.bhl.oida.ontology.model.ontologymgr.LocalOntologyMetaInfo;
import net.bhl.oida.ontology.model.ontologymgr.OntologyMetaInfo;
import net.bhl.oida.ontology.model.owlontology.Ontology;
import net.bhl.oida.ontology.model.owlontology.OntologyAnnotation;
import net.bhl.oida.ontology.model.owlontology.OntologyAnnotationProperty;
import net.bhl.oida.ontology.model.owlontology.OntologyClass;
import net.bhl.oida.ontology.model.owlontology.OntologyClassEquivalence;
import net.bhl.oida.ontology.model.owlontology.OntologyEntity;
import net.bhl.oida.ontology.model.owlontology.OntologyIndividual;
import net.bhl.oida.ontology.model.owlontology.OntologyNamespace;
import net.bhl.oida.ontology.model.owlontology.OntologyObjectProperty;
import net.bhl.oida.ontology.model.owlontology.OntologyObjectPropertyAssertion;
import net.bhl.oida.ontology.model.owlontology.OntologyObjectPropertyEquivalence;
import net.bhl.oida.ontology.model.owlontology.OntologySubClassAssignment;
import net.bhl.oida.ontology.owl.manager.util.OwlOntologyManagerMapHandler;
import net.bhl.oida.ontology.owl.manager.util.UnicornMapHandler;
import net.bhl.oida.util.OIDAUtil;
import net.bhl.oida.util.constants.StringConstants;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2016-11-23
 *
 */
public class OwlOntologyManager extends AbstractOntologyManager {
	private static Logger LOGGER = LoggerFactory.getLogger(OwlOntologyManager.class);

	private UnicornMapHandler mapHandler;

	private OWLOntologyManager owlOntologyManager;
	private OWLOntology owlOntology;
	private OWLDataFactory owlDataFactory;

	private OWLXMLDocumentFormat owlPrefixManager;

	// private OWLReasoner reasoner;

	/**
	 * Standard Constructor.
	 */
	OwlOntologyManager() {
		initializeOntologyManager();
	}

	@Override
	public void initializeOntologyManager() {
		owlPrefixManager = new OWLXMLDocumentFormat();
		owlOntologyManager = OWLManager.createOWLOntologyManager();
		owlDataFactory = owlOntologyManager.getOWLDataFactory();

		mapHandler = new UnicornMapHandler();
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
	public Ontology createLocalOntology(LocalOntologyMetaInfo metaInfo) throws OntologyManagerException {
		if (metaInfo.getIri() == null || metaInfo.getIri().contentEquals(StringConstants.EMPTY))
			return null;

		try {
			owlOntology = owlOntologyManager.createOntology(IRI.create(metaInfo.getIri()));
			owlPrefixManager.setDefaultPrefix(metaInfo.getIri());
			owlOntologyManager.setOntologyFormat(owlOntology, owlPrefixManager);
			initializeInternalOntology(metaInfo);

			LOGGER.info("Ontology created: '" + metaInfo.getIri() + "'");
			return getOntology();
		} catch (OWLOntologyCreationException e) {
			throw new OntologyManagerException(
					"Error while creating ontology '" + metaInfo.getIri() + "': " + e.getMessage(), e);
		}
	}

	@Override
	public Ontology loadOntology(OntologyMetaInfo metaInfo) throws OntologyManagerException {
		return loadOntologyInternal(metaInfo, metaInfo.getIri());
	}

	@Override
	public Ontology loadLocalOntology(LocalOntologyMetaInfo metaInfo) throws OntologyManagerException {
		Optional<File> optFile = OIDAUtil.getOntologyFileObject(metaInfo, false);
		if (!optFile.isPresent() || !optFile.get().exists())
			throw new OntologyManagerException("Error while loading ontology: File doesn't exist.");
		else
			return loadOntologyInternal(metaInfo, OIDAUtil.convertPathToIRI(metaInfo.getLocalPath()));
	}

	private Ontology loadOntologyInternal(OntologyMetaInfo metaInfo, String iri) throws OntologyManagerException {
		if (iri == null || iri.contentEquals(StringConstants.EMPTY))
			return null;

		setMetaInfo(metaInfo);

		try {
			LOGGER.info("Loading Ontology: '" + iri + "'...");
			updateIRIMappings();

			owlOntology = owlOntologyManager.loadOntology(IRI.create(iri));
			owlPrefixManager = (OWLXMLDocumentFormat) owlOntologyManager.getOntologyFormat(owlOntology);
			owlPrefixManager.setDefaultPrefix(owlOntology.getOntologyID().getOntologyIRI().get().getIRIString());
			// owlOntologyManager.setOntologyFormat(owlOntology, owlPrefixManager);
			initializeInternalOntology(metaInfo);

			refreshOntologyRepresentation(true);

			getMetaInfo().setIri(iri);
			LOGGER.info("Ontology loaded: '" + iri + "'.");
			return getOntology();
		} catch (OWLOntologyCreationException e) {
			throw new OntologyManagerException(
					"Error while loading ontology from file '" + iri + "': " + e.getMessage(), e);
		}
	}

	private void updateIRIMappings() {
		owlOntologyManager.getIRIMappers().clear();

		for (Entry<String, LocalOntologyMetaInfo> entry : getGlobalOntologyContext().getGlobalIRIToLocalIRIMappings()
				.entrySet())
			owlOntologyManager.getIRIMappers().add(new SimpleIRIMapper(IRI.create(entry.getKey()),
					IRI.create(OIDAUtil.getFileIriString(entry.getValue()))));
	}

	@Override
	public void refreshOntologyRepresentation(boolean buildLocalRepresentation) {
		refreshOntologyRepresentationInternal(owlOntology, buildLocalRepresentation);
	}

	public void refreshOntologyRepresentationInternal(OWLOntology owlOntology, boolean buildLocalRepresentation) {
		extractImports(owlOntology, getOntology());

		extractNamespaces(getOntology());

		// Classes:
		Stream<OWLClass> allClasses = owlOntology.classesInSignature(Imports.INCLUDED);
		List<OWLSubClassOfAxiom> allSubClassOfAxioms = owlOntology.axioms(AxiomType.SUBCLASS_OF, Imports.INCLUDED)
				.collect(Collectors.toList());
		List<OWLEquivalentClassesAxiom> allEquivalentClassAxioms = owlOntology
				.axioms(AxiomType.EQUIVALENT_CLASSES, Imports.INCLUDED).collect(Collectors.toList());
		extractClassHierarchy(getOntology(), allClasses, allSubClassOfAxioms, allEquivalentClassAxioms,
				mapHandler.getMapHandler());

		if (buildLocalRepresentation) {
			Stream<OWLClass> allLocalClasses = owlOntology.classesInSignature(Imports.INCLUDED)
					.filter(cl -> cl.getIRI().getNamespace().equals(getDefaultNamespace() + StringConstants.HASHTAG));
			List<OWLSubClassOfAxiom> allLocalSubClassOfAxioms = owlOntology
					.axioms(AxiomType.SUBCLASS_OF, Imports.EXCLUDED).collect(Collectors.toList());
			List<OWLEquivalentClassesAxiom> allLocalEquivalentClassAxioms = owlOntology
					.axioms(AxiomType.EQUIVALENT_CLASSES, Imports.EXCLUDED).collect(Collectors.toList());
			extractClassHierarchy(getOntology().getLocalOntology(), allLocalClasses, allLocalSubClassOfAxioms,
					allLocalEquivalentClassAxioms, mapHandler.getMapHandlerLocal());
		}

		// Object Properties:
		Stream<OWLObjectProperty> allObjectProperties = owlOntology.objectPropertiesInSignature(Imports.INCLUDED);
		List<OWLSubObjectPropertyOfAxiom> allSubObjectPropertyOfAxioms = owlOntology
				.axioms(AxiomType.SUB_OBJECT_PROPERTY, Imports.INCLUDED).collect(Collectors.toList());
		List<OWLEquivalentObjectPropertiesAxiom> allEquivalentObjectPropertiesAxioms = owlOntology
				.axioms(AxiomType.EQUIVALENT_OBJECT_PROPERTIES, Imports.INCLUDED).collect(Collectors.toList());
		extractObjectPropertyHierarchy(getOntology(), allObjectProperties, allSubObjectPropertyOfAxioms,
				allEquivalentObjectPropertiesAxioms, mapHandler.getMapHandler());

		if (buildLocalRepresentation) {
			Stream<OWLObjectProperty> allLocalObjectProperties = owlOntology
					.objectPropertiesInSignature(Imports.INCLUDED)
					.filter(cl -> cl.getIRI().getNamespace().equals(getDefaultNamespace() + StringConstants.HASHTAG));
			List<OWLSubObjectPropertyOfAxiom> allLocalSubObjectPropertiesOfAxioms = owlOntology
					.axioms(AxiomType.SUB_OBJECT_PROPERTY, Imports.EXCLUDED).collect(Collectors.toList());
			List<OWLEquivalentObjectPropertiesAxiom> allLocalEquivalentClassAxioms = owlOntology
					.axioms(AxiomType.EQUIVALENT_OBJECT_PROPERTIES, Imports.EXCLUDED).collect(Collectors.toList());
			extractObjectPropertyHierarchy(getOntology().getLocalOntology(), allLocalObjectProperties,
					allLocalSubObjectPropertiesOfAxioms, allLocalEquivalentClassAxioms,
					mapHandler.getMapHandlerLocal());
		}

		extractClassRestrictions(owlOntology, mapHandler.getMapHandler());

		// Individuals:
		List<OWLNamedIndividual> allIndividuals = owlOntology.individualsInSignature(Imports.INCLUDED)
				.collect(Collectors.toList());
		List<OWLClassAssertionAxiom> allClassAssertionAxioms = owlOntology
				.axioms(AxiomType.CLASS_ASSERTION, Imports.INCLUDED).collect(Collectors.toList());
		extractIndividuals(getOntology(), allIndividuals, allClassAssertionAxioms, mapHandler.getMapHandler());

		if (buildLocalRepresentation) {
			List<OWLNamedIndividual> allLocalIndividuals = owlOntology.individualsInSignature(Imports.INCLUDED)
					.filter(i -> i.getIRI().getNamespace().equals(getDefaultNamespace() + StringConstants.HASHTAG))
					.collect(Collectors.toList());
			List<OWLClassAssertionAxiom> allLocalClassAssertionAxioms = owlOntology
					.axioms(AxiomType.CLASS_ASSERTION, Imports.EXCLUDED).collect(Collectors.toList());
			extractIndividuals(getOntology().getLocalOntology(), allLocalIndividuals, allLocalClassAssertionAxioms,
					mapHandler.getMapHandlerLocal());
		}

		// Annotation Properties:
		List<OWLAnnotationProperty> allAnnotationProperties = owlOntology
				.annotationPropertiesInSignature(Imports.INCLUDED).collect(Collectors.toList());
		extractAnnotationProperties(getOntology(), allAnnotationProperties, mapHandler.getMapHandler());
		extractAnnotationProperties(getOntology().getLocalOntology(), allAnnotationProperties,
				mapHandler.getMapHandlerLocal());
	}

	private void initializeInternalOntology(OntologyMetaInfo metaInfo) {
		setOntology(OntologyManagerUtils.generateInternalOntologyObject(metaInfo.getIri()), metaInfo);
		mapHandler.getMapHandler().initializeOntology(owlDataFactory, owlOntology, getOntology());
		mapHandler.getMapHandlerLocal().initializeOntology(owlDataFactory, owlOntology,
				getOntology().getLocalOntology());

		for (String prefixName : owlPrefixManager.getPrefixName2PrefixMap().keySet()) {
			OntologyNamespace namespace = OntologyManagerUtils.generateInternalNamespaceObject(getOntology(),
					prefixName, owlPrefixManager.getPrefixName2PrefixMap().get(prefixName));
			getOntology().getLocalOntology().getNamespaces().add(namespace);
		}
	}

	private void extractImports(OWLOntology owlOntology, Ontology ontology) {
		for (OWLOntology importedOwlOntology : owlOntology.directImports().collect(Collectors.toList()))
			ontology.getImports().add(OntologyManagerUtils.generateInternalOntologyObject(
					importedOwlOntology.getOntologyID().getOntologyIRI().get().getIRIString()));

		ontology.getLocalOntology().getImports().addAll(ontology.getImports());
	}

	private void extractNamespaces(Ontology ontology) {
		for (String prefix : owlPrefixManager.getPrefixName2PrefixMap().keySet())
			ontology.getNamespaces().add(OntologyManagerUtils.generateInternalNamespaceObject(ontology,
					owlPrefixManager.getPrefixName2PrefixMap().get(prefix), prefix));
	}

	private void extractClassHierarchy(Ontology ontology, Stream<OWLClass> classes,
			List<OWLSubClassOfAxiom> owlSubClassOfAxioms, List<OWLEquivalentClassesAxiom> owlClassEquivalences,
			OwlOntologyManagerMapHandler mapHandler) {
		List<OWLClass> owlClasses = classes.collect(Collectors.toList());

		// create internal class objects for all owl classes. add the objects to
		// the map handler and the ontology class collection:
		for (OWLClass owlClass : owlClasses) {
			if (!owlClass.getIRI().getIRIString().equals(mapHandler.getOwlThingClass().getIRI().getIRIString())) {
				OntologyClass internalClass = OntologyManagerUtils.generateInternalClassObject(ontology, null,
						owlClass.getIRI().getIRIString());
				mapHandler.toMap(owlClass, internalClass);
				ontology.getClasses().add(internalClass);
			}
		}

		// establish the class hierarchy based on the passed sub class axioms:
		for (OntologyClass internalClass : ontology.getClasses()) {
			OWLClass owlClass = mapHandler.getOWLClass(internalClass).get();

			if (!owlClass.getIRI().getIRIString().equals(mapHandler.getOwlThingClass().getIRI().getIRIString())) {
				boolean addedAsSubClass = false;
				for (OWLSubClassOfAxiom owlAxiom : owlSubClassOfAxioms) {
					if (owlAxiom.getSubClass().isOWLClass() && owlAxiom.getSubClass().equals(owlClass)
							&& owlAxiom.getSuperClass().isOWLClass()) {
						OWLClass owlSuperClass = owlAxiom.getSuperClass().asOWLClass();

						Optional<OntologyClass> optInternalSuperClass = mapHandler.getInternalClass(owlSuperClass);
						if (optInternalSuperClass.isPresent()) {
							OntologyClass internalSuperClass = optInternalSuperClass.get();
							internalSuperClass.getSubClasses().add(internalClass);
							internalClass.getSuperClasses().add(internalSuperClass);
							addedAsSubClass = true;
						}
					}
				}

				// if no super class is defined, the Thing class is set as super
				// class:
				if (!addedAsSubClass) {
					mapHandler.getThingClass().getSubClasses().add(internalClass);
					internalClass.getSuperClasses().add(mapHandler.getThingClass());
				}
			}

			// establish class equivalence relationships:
			for (OWLEquivalentClassesAxiom owlEquivalence : owlClassEquivalences) {
				if (owlEquivalence.contains(owlClass)) {
					List<OWLClass> equivalentClasses = owlEquivalence.namedClasses().filter(cl -> !cl.equals(owlClass))
							.collect(Collectors.toList());

					for (OWLClass equivalentOwlCLass : equivalentClasses) {
						Optional<OntologyClass> optInternalEquivalentClass = mapHandler
								.getInternalClass(equivalentOwlCLass);

						if (!optInternalEquivalentClass.isPresent())
							optInternalEquivalentClass = this.mapHandler.getMapHandler()
									.getInternalClass(equivalentOwlCLass);

						if (optInternalEquivalentClass.isPresent())
							OntologyManagerUtils.assignClassesEquivalent(ontology, internalClass,
									optInternalEquivalentClass.get());
					}
				}
			}
		}
	}

	private void extractClassRestrictions(OWLOntology ontology, OwlOntologyManagerMapHandler mapHandler) {
		OWLOntologyWalker walker = new OWLOntologyWalker(Collections.singleton(ontology));
		OWLOntologyWalkerVisitor visitor = new OWLOntologyWalkerVisitor(walker) {
			@Override
			public void visit(OWLObjectSomeValuesFrom someValuesFrom) {
				if (getCurrentAxiom() instanceof OWLSubClassOfAxiom) {
					OWLClassExpression restrictedClassExpression = ((OWLSubClassOfAxiom) getCurrentAxiom())
							.getSubClass();
					OWLClassExpression restrictionClassExpression = someValuesFrom.getFiller();
					OWLObjectPropertyExpression objectPropertyExpression = someValuesFrom.getProperty();

					if (restrictedClassExpression.isOWLClass() && restrictionClassExpression.isOWLClass()
							&& objectPropertyExpression.isOWLObjectProperty()) {
						Optional<OntologyClass> restrictedClass = mapHandler
								.getInternalClass(restrictedClassExpression.asOWLClass());
						Optional<OntologyClass> restrictionClass = mapHandler
								.getInternalClass(restrictionClassExpression.asOWLClass());
						Optional<OntologyObjectProperty> objectProperty = mapHandler
								.getInternalObjectProperty(objectPropertyExpression.asOWLObjectProperty());

						if (restrictedClass.isPresent() && restrictionClass.isPresent() && objectProperty.isPresent())
							OntologyManagerUtils.createSomeValuesFromRestriction(restrictedClass.get(),
									objectProperty.get(), restrictionClass.get());
					}
				}
			}
		};

		walker.walkStructure(visitor);
	}

	private void extractObjectPropertyHierarchy(Ontology ontology, Stream<OWLObjectProperty> objectProperties,
			List<OWLSubObjectPropertyOfAxiom> owlSubObjectPropertyOfAxioms,
			List<OWLEquivalentObjectPropertiesAxiom> owlObjectPropertiesEquivalences,
			OwlOntologyManagerMapHandler mapHandler) {
		List<OWLObjectProperty> owlObjectProperties = objectProperties.collect(Collectors.toList());

		// create internal object property objects for all owl object
		// properties. add the objects to
		// the map handler and the ontology object property collection:
		for (OWLObjectProperty owlObjectProperty : owlObjectProperties) {
			if (!owlObjectProperty.getIRI().getIRIString()
					.equals(mapHandler.getOwlTopObjectProperty().getIRI().getIRIString())) {
				OntologyObjectProperty internalObjectProperty = OntologyManagerUtils
						.generateInternalObjectPropertyObject(ontology, null,
								owlObjectProperty.getIRI().getIRIString());
				mapHandler.toMap(owlObjectProperty, internalObjectProperty);
				ontology.getObjectProperties().add(internalObjectProperty);
			}
		}

		// establish the class hierarchy based on the passed sub class axioms:
		for (OntologyObjectProperty internalObjectProperty : ontology.getObjectProperties()) {
			Optional<OWLObjectProperty> owlObjectProperty = mapHandler.getOWLObjectProperty(internalObjectProperty);

			if (!owlObjectProperty.isPresent())
				System.out.println(internalObjectProperty.getIri());

			if (!owlObjectProperty.get().getIRI().getIRIString()
					.equals(mapHandler.getOwlTopObjectProperty().getIRI().getIRIString())) {
				boolean addedAsSubObjectProperty = false;
				for (OWLSubObjectPropertyOfAxiom owlAxiom : owlSubObjectPropertyOfAxioms) {
					if (owlAxiom.getSubProperty().isOWLObjectProperty()
							&& owlAxiom.getSubProperty().equals(owlObjectProperty)
							&& owlAxiom.getSuperProperty().isOWLObjectProperty()) {
						OWLObjectProperty owlSuperObjectProperty = owlAxiom.getSubProperty().asOWLObjectProperty();

						Optional<OntologyObjectProperty> optInternalSuperObjectProperty = mapHandler
								.getInternalObjectProperty(owlSuperObjectProperty);
						if (optInternalSuperObjectProperty.isPresent()) {
							OntologyObjectProperty internalSuperObjectProperty = optInternalSuperObjectProperty.get();
							internalSuperObjectProperty.getSubObjectProperties().add(internalObjectProperty);
							internalObjectProperty.getSuperProperties().add(internalSuperObjectProperty);
							addedAsSubObjectProperty = true;
						}
					}
				}

				// if no super object property is defined, the TopObjectProperty
				// is set as super
				// property:
				if (!addedAsSubObjectProperty) {
					mapHandler.getTopObjectProperty().getSubObjectProperties().add(internalObjectProperty);
					internalObjectProperty.getSuperProperties().add(mapHandler.getTopObjectProperty());
				}
			}

			// establish class equivalence relationships:
			for (OWLEquivalentObjectPropertiesAxiom owlEquivalence : owlObjectPropertiesEquivalences) {
				if (owlEquivalence.containsEntityInSignature(owlObjectProperty.get())) {
					List<OWLObjectProperty> equivalentObjectProperties = owlEquivalence.objectPropertiesInSignature()
							.filter(op -> !op.equals(owlObjectProperty)).collect(Collectors.toList());

					for (OWLObjectProperty equivalentOwlObjectProperty : equivalentObjectProperties) {
						Optional<OntologyObjectProperty> optInternalEquivalentObjectProperty = mapHandler
								.getInternalObjectProperty(equivalentOwlObjectProperty);

						if (!optInternalEquivalentObjectProperty.isPresent())
							optInternalEquivalentObjectProperty = this.mapHandler.getMapHandler()
									.getInternalObjectProperty(equivalentOwlObjectProperty);

						if (optInternalEquivalentObjectProperty.isPresent())
							OntologyManagerUtils.assignObjectPropertiesEquivalent(ontology, internalObjectProperty,
									optInternalEquivalentObjectProperty.get());
					}
				}
			}
		}
	}

	private void extractIndividuals(Ontology ontology, List<OWLNamedIndividual> allindividuals,
			List<OWLClassAssertionAxiom> allClassAssertionAxioms, OwlOntologyManagerMapHandler mapHandler) {
		for (OWLNamedIndividual owlIndividual : allindividuals) {
			OntologyIndividual individual = OntologyManagerUtils.generateInternalIndividualObject(ontology,
					owlIndividual.getIRI().getIRIString());

			mapHandler.toMap(owlIndividual, individual);

			for (OWLClassAssertionAxiom a : allClassAssertionAxioms) {
				if (a.getIndividual().equals(owlIndividual) && a.getClassExpression().isOWLClass()) {
					Optional<OntologyClass> optC = mapHandler.getInternalClass(a.getClassExpression().asOWLClass(),
							ontology);
					if (optC.isPresent()) {
						optC.get().getIndividuals().add(individual);
						individual.getTypes().add(optC.get());

						OntologyManagerUtils.generateInternalClassAssertionObject(ontology,
								a.getClassExpression().asOWLClass().getIRI().toString(),
								owlIndividual.getIRI().getIRIString());
					}
				}
			}
		}

		for (OWLNamedIndividual owlIndividual : allindividuals) {
			for (OWLObjectPropertyAssertionAxiom opa : owlOntology.objectPropertyAssertionAxioms(owlIndividual)
					.collect(Collectors.toList())) {
				OWLObjectProperty op = opa.objectPropertiesInSignature().collect(Collectors.toList()).get(0);
				OWLNamedIndividual subject = (OWLNamedIndividual) opa.getSubject();
				OWLNamedIndividual object = (OWLNamedIndividual) opa.getObject();

				Optional<OntologyObjectProperty> opInternal = mapHandler.getInternalObjectProperty(op);
				Optional<OntologyIndividual> subjectInternal = mapHandler.getInternalIndividual(owlIndividual);
				Optional<OntologyIndividual> objectInternal = mapHandler.getInternalIndividual(object);

				if (opInternal.isPresent() && subjectInternal.isPresent() && objectInternal.isPresent())
					createObjectPropertyAssertion(opInternal.get(), subjectInternal.get(), objectInternal.get());
			}
		}
	}

	private void extractAnnotationProperties(Ontology ontology, List<OWLAnnotationProperty> allAnnotationProperties,
			OwlOntologyManagerMapHandler mapHandler) {
		for (OWLAnnotationProperty owlAnnotationProperty : allAnnotationProperties) {
			mapHandler.toMap(owlAnnotationProperty, OntologyManagerUtils
					.generateInternalAnnotationPropertyObject(ontology, owlAnnotationProperty.getIRI().getIRIString()));
		}
	}

	@Override
	public void saveLocalOntology() throws OntologyManagerException {
		OntologyMetaInfo optOntologyMetaInfo = getMetaInfo();
		if (!(optOntologyMetaInfo instanceof LocalOntologyMetaInfo))
			throw new OntologyManagerException("Error while saving ontology: Ontology is no local ontology.");

		LocalOntologyMetaInfo metaInfo = (LocalOntologyMetaInfo) optOntologyMetaInfo;
		Optional<File> optFile = OIDAUtil.getOntologyFileObject(metaInfo, true);

		if (optFile.isPresent()) {
			try {
				FileOutputStream outputStream = new FileOutputStream(optFile.get());
				owlOntologyManager.saveOntology(owlOntology, owlPrefixManager, outputStream);
				LOGGER.info("Ontology saved: '" + getOntology().getIri() + "'");
			} catch (FileNotFoundException e) {
				throw new OntologyManagerException(
						"Error while saving ontology to file '" + metaInfo.getLocalPath() + "': " + e.getMessage(), e);
			} catch (OWLOntologyStorageException e) {
				throw new OntologyManagerException(
						"Error while saving ontology to file '" + metaInfo.getLocalPath() + "': " + e.getMessage(), e);
			}
		}
	}

	@Override
	public String getDefaultNamespace() {
		return owlPrefixManager.getDefaultPrefix().replace(StringConstants.HASHTAG, StringConstants.EMPTY);
	}

	@Override
	public void addImportDeclaration(String iri) throws OntologyManagerException {
		// Check, if the import is already existing:
		for (Ontology o : getOntology().getImports())
			if (o.getIri().equals(iri))
				return;

		updateIRIMappings();

		IRI importOntologyIRI = IRI.create(iri);
		OWLImportsDeclaration owlImportDeclaration = owlDataFactory.getOWLImportsDeclaration(importOntologyIRI);
		owlOntologyManager.applyChange(new AddImport(owlOntology, owlImportDeclaration));

		Ontology internalImportedOntology = OntologyManagerUtils.generateInternalOntologyObject(iri);
		getOntology().getImports().add(internalImportedOntology);
		getOntology().getLocalOntology().getImports().add(internalImportedOntology);

		try {
			OWLOntology owlImportedOntology = owlOntologyManager.loadOntology(importOntologyIRI);
			refreshOntologyRepresentationInternal(owlImportedOntology, false);
		} catch (OWLOntologyCreationException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public OntologyClass createClass(final String name, final String namespace) {
		Optional<OntologyClass> optInternalClass = getClass(name, namespace);
		if (optInternalClass.isPresent()) {
			return optInternalClass.get();
		}

		OWLClass owlNewClass = owlDataFactory.getOWLClass(OntologyManagerUtils.buildFullIRIString(name, namespace));
		OWLAxiom owlNewClassDeclarationAxiom = owlDataFactory.getOWLDeclarationAxiom(owlNewClass);
		owlOntologyManager.addAxiom(owlOntology, owlNewClassDeclarationAxiom);

		OntologyClass internalClass = OntologyManagerUtils.generateInternalClassObject(getOntology(),
				mapHandler.getMapHandler().getThingClass(), name, namespace);
		mapHandler.getMapHandler().toMap(owlNewClass, internalClass);

		if (namespace.equals(getDefaultNamespace())) {
			mapHandler.getMapHandlerLocal().toMap(owlNewClass,
					OntologyManagerUtils.generateInternalClassObject(getOntology().getLocalOntology(),
							mapHandler.getMapHandlerLocal().getThingClass(), name, namespace));
		}

		return internalClass;
	}

	@Override
	public OntologyIndividual createIndividual(final String name, final String namespace) {
		Optional<OntologyIndividual> optInternalIndividual = getIndividual(name, namespace);
		if (optInternalIndividual.isPresent()) {
			return optInternalIndividual.get();
		}

		OWLNamedIndividual owlIndividual = owlDataFactory
				.getOWLNamedIndividual(OntologyManagerUtils.buildFullIRIString(name, namespace));
		OWLAxiom declareNewIndividual = owlDataFactory.getOWLDeclarationAxiom(owlIndividual);
		owlOntologyManager.addAxiom(owlOntology, declareNewIndividual);

		OntologyIndividual internalIndividual = OntologyManagerUtils.generateInternalIndividualObject(getOntology(),
				name, namespace);
		mapHandler.getMapHandler().toMap(owlIndividual, internalIndividual);

		if (namespace.equals(getDefaultNamespace())) {
			mapHandler.getMapHandlerLocal().toMap(owlIndividual, OntologyManagerUtils
					.generateInternalIndividualObject(getOntology().getLocalOntology(), name, namespace));
		}

		return internalIndividual;
	}

	@Override
	public OntologyObjectProperty createObjectProperty(final String name, final String namespace) {
		Optional<OntologyObjectProperty> optInternalObjectProperty = getObjectProperty(name, namespace);
		if (optInternalObjectProperty.isPresent()) {
			return optInternalObjectProperty.get();
		}

		OWLObjectProperty owlProperty = owlDataFactory
				.getOWLObjectProperty(OntologyManagerUtils.buildFullIRIString(name, namespace));
		OWLDeclarationAxiom owlAxiom = owlDataFactory.getOWLDeclarationAxiom(owlProperty);
		owlOntologyManager.addAxiom(owlOntology, owlAxiom);

		OntologyObjectProperty internalObjectProperty = OntologyManagerUtils.generateInternalObjectPropertyObject(
				getOntology(), mapHandler.getMapHandler().getTopObjectProperty(), name, namespace);
		mapHandler.getMapHandler().toMap(owlProperty, internalObjectProperty);

		if (namespace.equals(getDefaultNamespace())) {
			mapHandler.getMapHandlerLocal().toMap(owlProperty,
					OntologyManagerUtils.generateInternalObjectPropertyObject(getOntology().getLocalOntology(),
							mapHandler.getMapHandler().getTopObjectProperty(), name, namespace));
		}

		return internalObjectProperty;
	}

	@Override
	public void assignSubClassToSuperClass(OntologyClass subClass, OntologyClass superClass) {
		Optional<OWLClass> optSubOwlClass = mapHandler.getMapHandler().getOWLClass(subClass);
		Optional<OWLClass> optSuperOwlClass = mapHandler.getMapHandler().getOWLClass(superClass);

		if (optSubOwlClass.isPresent() && optSuperOwlClass.isPresent()) {
			OWLSubClassOfAxiom owlAxiom = owlDataFactory.getOWLSubClassOfAxiom(optSubOwlClass.get(),
					optSuperOwlClass.get());
			owlOntologyManager.addAxiom(owlOntology, owlAxiom);

			OntologySubClassAssignment subClassAssignment = OntologyManagerUtils.assignSubClassToSuperClass(subClass,
					superClass);
			mapHandler.getMapHandler().toMap(owlAxiom, subClassAssignment);

			Optional<OntologyClass> twinSubClass = mapHandler.getTwinClass(subClass);
			Optional<OntologyClass> twinSuperClass = mapHandler.getTwinClass(superClass);

			if (twinSubClass.isPresent() && twinSuperClass.isPresent()) {
				OntologySubClassAssignment twinSubClassAssignment = OntologyManagerUtils
						.assignSubClassToSuperClass(twinSubClass.get(), twinSuperClass.get());
				mapHandler.getMapHandlerLocal().toMap(owlAxiom, twinSubClassAssignment);
			}
		}
	}

	public void removeSubClassAssignment(OntologyClass subClass, OntologyClass superClass) {
		Optional<OntologySubClassAssignment> optSubClassOfAssignment = mapHandler.getMapHandler()
				.getInternalSubClassAssignment(subClass, superClass);

		if (optSubClassOfAssignment.isPresent()) {
			Optional<OWLSubClassOfAxiom> optOwlSubClassOfAxiom = mapHandler.getMapHandler()
					.getOWLSubClassOfAxiom(optSubClassOfAssignment.get());

			if (optOwlSubClassOfAxiom.isPresent()) {
				owlOntologyManager.removeAxiom(owlOntology, optOwlSubClassOfAxiom.get());
				mapHandler.removeSubClassAssignment(optSubClassOfAssignment.get());
			}
		}
	}

	@Override
	public void renameEntity(OntologyEntity entity, String newName) {
		Collection<OWLOntology> c = new ArrayList<OWLOntology>();
		c.add(owlOntology);

		OWLEntityRenamer renamer = new OWLEntityRenamer(owlOntologyManager, c);

		Optional<OWLEntity> optRenameEntity = mapHandler.getMapHandler().getOWLEntity(entity);
		if (optRenameEntity.isPresent()) {
			List<OWLOntologyChange> changes = renamer.changeIRI(optRenameEntity.get(),
					IRI.create(optRenameEntity.get().getIRI().toString().replace(entity.getName(), newName)));
			owlOntologyManager.applyChanges(changes);

			Optional<OWLEntity> optEntity = changes.get(1).signature().findFirst();
			if (optEntity.isPresent())
				mapHandler.getMapHandler().toMap(optEntity.get(), entity);

			OntologyManagerUtils.changeOntologyEntityName(entity, newName);

			Optional<OntologyEntity> twinEntity = mapHandler.getTwinEntity(entity);
			if (twinEntity.isPresent())
				OntologyManagerUtils.changeOntologyEntityName(twinEntity.get(), newName);
		}
	}

	@Override
	public void assignIndividualToClass(OntologyIndividual individual, OntologyClass clazz) {
		Optional<OWLNamedIndividual> optOwlIndividual = mapHandler.getMapHandler().getOWLIndividual(individual);
		Optional<OWLClass> optOwlClass = mapHandler.getMapHandler().getOWLClass(clazz);

		if (optOwlIndividual.isPresent() && optOwlClass.isPresent()) {
			owlOntologyManager.addAxiom(owlOntology,
					owlDataFactory.getOWLClassAssertionAxiom(optOwlClass.get(), optOwlIndividual.get()));

			OntologyManagerUtils.assignIndividualToClass(individual, clazz);

			Optional<OntologyIndividual> twinIndividual = mapHandler.getTwinIndividual(individual);
			Optional<OntologyClass> twinClass = mapHandler.getTwinClass(clazz);

			if (twinIndividual.isPresent() && twinClass.isPresent())
				OntologyManagerUtils.assignIndividualToClass(twinIndividual.get(), twinClass.get());
		}
	}

	@Override
	public void assignSubObjectPropertyToSuperObjectProperty(OntologyObjectProperty subProperty,
			OntologyObjectProperty superProperty) {
		Optional<OWLObjectProperty> optSubOwlObjectProperty = mapHandler.getMapHandler()
				.getOWLObjectProperty(subProperty);
		Optional<OWLObjectProperty> optSuperOwlObjectProperty = mapHandler.getMapHandler()
				.getOWLObjectProperty(superProperty);

		if (optSubOwlObjectProperty.isPresent() && optSuperOwlObjectProperty.isPresent()) {
			OWLSubObjectPropertyOfAxiom owlSubObjectPropertyAxiom = owlDataFactory
					.getOWLSubObjectPropertyOfAxiom(optSubOwlObjectProperty.get(), optSuperOwlObjectProperty.get());
			owlOntologyManager.addAxiom(owlOntology, owlSubObjectPropertyAxiom);

			OntologyManagerUtils.assignSubObjectPropertyToSuperObjectProperty(subProperty, superProperty);

			Optional<OntologyObjectProperty> twinSubProperty = mapHandler.getTwinObjectProperty(subProperty);
			Optional<OntologyObjectProperty> twinSuperProperty = mapHandler.getTwinObjectProperty(superProperty);

			if (twinSubProperty.isPresent() && twinSuperProperty.isPresent())
				OntologyManagerUtils.assignSubObjectPropertyToSuperObjectProperty(twinSubProperty.get(),
						twinSuperProperty.get());
		}
	}

	@Override
	public void assignInverseObjectProperty(OntologyObjectProperty property, OntologyObjectProperty inverseProperty) {
		Optional<OWLObjectProperty> optOwlObjectProperty = mapHandler.getMapHandler().getOWLObjectProperty(property);
		Optional<OWLObjectProperty> optOwlInverseObjectProperty = mapHandler.getMapHandler()
				.getOWLObjectProperty(inverseProperty);

		if (optOwlObjectProperty.isPresent() && optOwlInverseObjectProperty.isPresent()) {
			OWLInverseObjectPropertiesAxiom owlInverseAxiom = owlDataFactory
					.getOWLInverseObjectPropertiesAxiom(optOwlObjectProperty.get(), optOwlInverseObjectProperty.get());
			owlOntologyManager.addAxiom(owlOntology, owlInverseAxiom);

			property.getInverseObjectProperties().add(inverseProperty);
			inverseProperty.getInverseObjectProperties().add(property);

			Optional<OntologyObjectProperty> twinProperty = mapHandler.getTwinObjectProperty(property);
			Optional<OntologyObjectProperty> twinInverseProperty = mapHandler.getTwinObjectProperty(inverseProperty);

			if (twinProperty.isPresent() && twinInverseProperty.isPresent()) {
				twinProperty.get().getInverseObjectProperties().add(twinInverseProperty.get());
				twinInverseProperty.get().getInverseObjectProperties().add(twinProperty.get());
			}
		}
	}

	@Override
	public void makeObjectPropertyFunctional(OntologyObjectProperty property) {
		Optional<OWLObjectProperty> optOwlObjectProperty = mapHandler.getMapHandler().getOWLObjectProperty(property);
		if (optOwlObjectProperty.isPresent()) {
			OWLFunctionalObjectPropertyAxiom owlFunctionalOP = owlDataFactory
					.getOWLFunctionalObjectPropertyAxiom(optOwlObjectProperty.get());
			owlOntologyManager.addAxiom(owlOntology, owlFunctionalOP);

			property.setFunctional(true);

			Optional<OntologyObjectProperty> twinProperty = mapHandler.getTwinObjectProperty(property);

			if (twinProperty.isPresent())
				twinProperty.get().setFunctional(true);
		}
	}

	@Override
	public void makeObjectPropertyInverseFunctional(OntologyObjectProperty property) {
		Optional<OWLObjectProperty> optOwlObjectProperty = mapHandler.getMapHandler().getOWLObjectProperty(property);
		if (optOwlObjectProperty.isPresent()) {
			OWLInverseFunctionalObjectPropertyAxiom owlInverseFunctionalOP = owlDataFactory
					.getOWLInverseFunctionalObjectPropertyAxiom(optOwlObjectProperty.get());
			owlOntologyManager.addAxiom(owlOntology, owlInverseFunctionalOP);

			property.setInverseFunctional(true);

			Optional<OntologyObjectProperty> twinProperty = mapHandler.getTwinObjectProperty(property);

			if (twinProperty.isPresent())
				twinProperty.get().setInverseFunctional(true);
		}
	}

	@Override
	public void makeObjectPropertyTransitive(OntologyObjectProperty property) {
		Optional<OWLObjectProperty> optOwlObjectProperty = mapHandler.getMapHandler().getOWLObjectProperty(property);
		if (optOwlObjectProperty.isPresent()) {
			OWLTransitiveObjectPropertyAxiom owlTransitiveOP = owlDataFactory
					.getOWLTransitiveObjectPropertyAxiom(optOwlObjectProperty.get());
			owlOntologyManager.addAxiom(owlOntology, owlTransitiveOP);

			property.setTransitive(true);

			Optional<OntologyObjectProperty> twinProperty = mapHandler.getTwinObjectProperty(property);

			if (twinProperty.isPresent())
				twinProperty.get().setTransitive(true);
		}
	}

	@Override
	public void makeObjectPropertySymmetric(OntologyObjectProperty property) {
		Optional<OWLObjectProperty> optOwlObjectProperty = mapHandler.getMapHandler().getOWLObjectProperty(property);
		if (optOwlObjectProperty.isPresent()) {
			OWLSymmetricObjectPropertyAxiom owlSymmetricOP = owlDataFactory
					.getOWLSymmetricObjectPropertyAxiom(optOwlObjectProperty.get());
			owlOntologyManager.addAxiom(owlOntology, owlSymmetricOP);

			property.setSymmetric(true);

			Optional<OntologyObjectProperty> twinProperty = mapHandler.getTwinObjectProperty(property);

			if (twinProperty.isPresent())
				twinProperty.get().setSymmetric(true);
		}
	}

	@Override
	public void makeObjectPropertyAsymmetric(OntologyObjectProperty property) {
		Optional<OWLObjectProperty> optOwlObjectProperty = mapHandler.getMapHandler().getOWLObjectProperty(property);
		if (optOwlObjectProperty.isPresent()) {
			OWLAsymmetricObjectPropertyAxiom owlAsymmetricOP = owlDataFactory
					.getOWLAsymmetricObjectPropertyAxiom(optOwlObjectProperty.get());
			owlOntologyManager.addAxiom(owlOntology, owlAsymmetricOP);

			property.setAsymmetric(true);

			Optional<OntologyObjectProperty> twinProperty = mapHandler.getTwinObjectProperty(property);

			if (twinProperty.isPresent())
				twinProperty.get().setAsymmetric(true);
		}
	}

	@Override
	public void makeObjectPropertyReflexive(OntologyObjectProperty property) {
		Optional<OWLObjectProperty> optOwlObjectProperty = mapHandler.getMapHandler().getOWLObjectProperty(property);
		if (optOwlObjectProperty.isPresent()) {
			OWLReflexiveObjectPropertyAxiom owlReflexiveOP = owlDataFactory
					.getOWLReflexiveObjectPropertyAxiom(optOwlObjectProperty.get());
			owlOntologyManager.addAxiom(owlOntology, owlReflexiveOP);

			property.setReflexive(true);

			Optional<OntologyObjectProperty> twinProperty = mapHandler.getTwinObjectProperty(property);

			if (twinProperty.isPresent())
				twinProperty.get().setReflexive(true);
		}
	}

	@Override
	public void makeObjectPropertyIrreflexive(OntologyObjectProperty property) {
		Optional<OWLObjectProperty> optOwlObjectProperty = mapHandler.getMapHandler().getOWLObjectProperty(property);
		if (optOwlObjectProperty.isPresent()) {
			OWLIrreflexiveObjectPropertyAxiom owlIrreflexiveOP = owlDataFactory
					.getOWLIrreflexiveObjectPropertyAxiom(optOwlObjectProperty.get());
			owlOntologyManager.addAxiom(owlOntology, owlIrreflexiveOP);
			property.setIrreflexive(true);

			Optional<OntologyObjectProperty> twinProperty = mapHandler.getTwinObjectProperty(property);

			if (twinProperty.isPresent())
				twinProperty.get().setIrreflexive(true);
		}
	}

	@Override
	public void assignObjectPropertyRange(OntologyObjectProperty property, OntologyClass range) {
		Optional<OWLObjectProperty> optOwlObjectProperty = mapHandler.getMapHandler().getOWLObjectProperty(property);
		Optional<OWLClass> optOwlClass = mapHandler.getMapHandler().getOWLClass(range);

		if (optOwlObjectProperty.isPresent() && optOwlClass.isPresent()) {
			OWLObjectPropertyRangeAxiom owlAxiom = owlDataFactory
					.getOWLObjectPropertyRangeAxiom(optOwlObjectProperty.get(), optOwlClass.get());
			owlOntologyManager.addAxiom(owlOntology, owlAxiom);

			property.setRange(range);

			Optional<OntologyObjectProperty> twinProperty = mapHandler.getTwinObjectProperty(property);
			Optional<OntologyClass> twinClass = mapHandler.getTwinClass(range);

			if (twinProperty.isPresent() && twinClass.isPresent())
				twinProperty.get().setRange(twinClass.get());
		}
	}

	@Override
	public void assignObjectPropertyDomain(OntologyObjectProperty property, OntologyClass domain) {
		Optional<OWLObjectProperty> optOwlObjectProperty = mapHandler.getMapHandler().getOWLObjectProperty(property);
		Optional<OWLClass> optOwlClass = mapHandler.getMapHandler().getOWLClass(domain);

		if (optOwlObjectProperty.isPresent() && optOwlClass.isPresent()) {
			OWLObjectPropertyDomainAxiom owlAxiom = owlDataFactory
					.getOWLObjectPropertyDomainAxiom(optOwlObjectProperty.get(), optOwlClass.get());
			owlOntologyManager.addAxiom(owlOntology, owlAxiom);

			property.setDomain(domain);

			Optional<OntologyObjectProperty> twinProperty = mapHandler.getTwinObjectProperty(property);
			Optional<OntologyClass> twinClass = mapHandler.getTwinClass(domain);

			if (twinProperty.isPresent() && twinClass.isPresent())
				twinProperty.get().setDomain(twinClass.get());
		}
	}

	@Override
	public OntologyObjectPropertyAssertion createObjectPropertyAssertion(OntologyObjectProperty property,
			OntologyIndividual individual, OntologyIndividual object) {
		Optional<OWLObjectProperty> optOwlObjectProperty = mapHandler.getMapHandler().getOWLObjectProperty(property);
		Optional<OWLNamedIndividual> optOwlIndividual = mapHandler.getMapHandler().getOWLIndividual(individual);
		Optional<OWLNamedIndividual> optOwlObject = mapHandler.getMapHandler().getOWLIndividual(object);

		if (optOwlObjectProperty.isPresent() && optOwlIndividual.isPresent() && optOwlObject.isPresent()) {
			OWLObjectPropertyAssertionAxiom owlAssertionAxiom = owlDataFactory.getOWLObjectPropertyAssertionAxiom(
					optOwlObjectProperty.get(), optOwlIndividual.get(), optOwlObject.get());
			owlOntologyManager.addAxiom(owlOntology, owlAssertionAxiom);

			OntologyObjectPropertyAssertion assertion = OntologyManagerUtils
					.generateObjectPropertyAssertionObject(getOntology(), property, object);
			individual.getObjectPropertyAssertions().add(assertion);

			return assertion;
		} else
			return null;
	}

	@Override
	public OntologyAnnotationProperty createAnnotationProperty(String propertyName, String namespace) {
		OWLAnnotationProperty owlProperty = owlDataFactory
				.getOWLAnnotationProperty(OntologyManagerUtils.buildFullIRIString(propertyName, namespace));
		OWLDeclarationAxiom owlAxiom = owlDataFactory.getOWLDeclarationAxiom(owlProperty);

		owlOntologyManager.addAxiom(owlOntology, owlAxiom);

		OntologyAnnotationProperty prop = OntologyManagerUtils.generateInternalAnnotationPropertyObject(getOntology(),
				propertyName, namespace);
		mapHandler.getMapHandler().toMap(owlProperty, prop);

		if (namespace.equals(getDefaultNamespace())) {
			mapHandler.getMapHandlerLocal().toMap(owlProperty,
					OntologyManagerUtils.generateInternalAnnotationPropertyObject(getOntology().getLocalOntology(),
							propertyName, namespace));
		}

		return prop;
	}

	@Override
	public Optional<OntologyAnnotation> annotateClass(OntologyAnnotationProperty property, String annotationValue,
			OntologyClass clazz) {
		Optional<OWLAnnotationProperty> optOwlAnnotationProperty = mapHandler.getMapHandler()
				.getOWLAnnotationProperty(property);
		Optional<OWLClass> optOwlClass = mapHandler.getMapHandler().getOWLClass(clazz);

		if (optOwlAnnotationProperty.isPresent() && optOwlClass.isPresent()) {
			OWLAnnotation owlAnnotation = owlDataFactory.getOWLAnnotation(optOwlAnnotationProperty.get(),
					owlDataFactory.getOWLLiteral(annotationValue));
			OWLAnnotationAssertionAxiom owlAxiom = owlDataFactory
					.getOWLAnnotationAssertionAxiom(optOwlClass.get().getIRI(), owlAnnotation);

			owlOntologyManager.addAxiom(owlOntology, owlAxiom);

			OntologyAnnotation annotation = OntologyManagerUtils.generateAnnotationObject(getOntology(), property,
					annotationValue);
			clazz.getAnnotations().add(annotation);
			mapHandler.getMapHandler().toMap(owlAnnotation, annotation);

			// TODO: Copy Annotations to local ontology

			return Optional.of(annotation);
		}

		return Optional.empty();
	}

	@Override
	public Optional<OntologyAnnotation> annotateIndividual(OntologyIndividual individual,
			OntologyAnnotationProperty property, String annotationValue) {
		Optional<OWLAnnotationProperty> optOwlAnnotationProperty = mapHandler.getMapHandler()
				.getOWLAnnotationProperty(property);
		Optional<OWLNamedIndividual> optOwlIndividual = mapHandler.getMapHandler().getOWLIndividual(individual);

		if (optOwlAnnotationProperty.isPresent() && optOwlIndividual.isPresent()) {
			OWLAnnotation owlAnnotation = owlDataFactory.getOWLAnnotation(optOwlAnnotationProperty.get(),
					owlDataFactory.getOWLLiteral(annotationValue));
			OWLAnnotationAssertionAxiom owlAxiom = owlDataFactory
					.getOWLAnnotationAssertionAxiom(optOwlIndividual.get().getIRI(), owlAnnotation);

			owlOntologyManager.addAxiom(owlOntology, owlAxiom);

			OntologyAnnotation annotation = OntologyManagerUtils.generateAnnotationObject(getOntology(), property,
					annotationValue);
			individual.getAnnotations().add(annotation);
			mapHandler.getMapHandler().toMap(owlAnnotation, annotation);

			// TODO: Copy Annotations to local ontology

			return Optional.of(annotation);
		}

		return Optional.empty();
	}

	@Override
	public Optional<OntologyClassEquivalence> assignClassEquivalence(OntologyClass clazz,
			OntologyClass equivalentClazz) {
		Optional<OWLClass> optOwlClass = mapHandler.getMapHandler().getOWLClass(clazz);
		Optional<OWLClass> optOwlEquivalentClass = mapHandler.getMapHandler().getOWLClass(equivalentClazz);

		if (optOwlClass.isPresent() && optOwlEquivalentClass.isPresent()) {
			OWLEquivalentClassesAxiom owlAxiom = owlDataFactory.getOWLEquivalentClassesAxiom(optOwlClass.get(),
					optOwlEquivalentClass.get());

			owlOntologyManager.addAxiom(owlOntology, owlAxiom);

			OntologyClassEquivalence equivalence = OntologyManagerUtils.assignClassesEquivalent(getOntology(), clazz,
					equivalentClazz);
			mapHandler.getMapHandler().toMap(owlAxiom, equivalence);

			// TODO: Copy Annotations to local ontology

			return Optional.of(equivalence);
		}

		return Optional.empty();
	}

	@Override
	public void removeClassEquivalence(OntologyClassEquivalence equivalence) {
		Optional<OWLEquivalentClassesAxiom> optOwlClassesEquivalentAxiom = mapHandler.getMapHandler()
				.getOWLEqivalentClassesAxiom(equivalence);

		if (optOwlClassesEquivalentAxiom.isPresent()) {
			owlOntology.removeAxiom(optOwlClassesEquivalentAxiom.get());

			getOntology().getObjectPropertyEquivalences().remove(equivalence);
			equivalence.getClass1().getEquivalentClasses().remove(equivalence.getClass2());
			equivalence.getClass2().getEquivalentClasses().remove(equivalence.getClass1());
		}
	}

	@Override
	public Optional<OntologyObjectPropertyEquivalence> assignObjectPropertyEquivalence(
			OntologyObjectProperty objectProperty, OntologyObjectProperty equivalentObjectProperty) {
		Optional<OWLObjectProperty> optOwlObjectProperty = mapHandler.getMapHandler()
				.getOWLObjectProperty(objectProperty);
		Optional<OWLObjectProperty> optOwlEquivalentObjectProperty = mapHandler.getMapHandler()
				.getOWLObjectProperty(equivalentObjectProperty);

		if (optOwlObjectProperty.isPresent() && optOwlEquivalentObjectProperty.isPresent()) {
			OWLEquivalentObjectPropertiesAxiom owlAxiom = owlDataFactory.getOWLEquivalentObjectPropertiesAxiom(
					optOwlObjectProperty.get(), optOwlEquivalentObjectProperty.get());
			owlOntologyManager.addAxiom(owlOntology, owlAxiom);

			OntologyObjectPropertyEquivalence equivalence = OntologyManagerUtils
					.assignObjectPropertiesEquivalent(getOntology(), objectProperty, equivalentObjectProperty);
			mapHandler.getMapHandler().toMap(owlAxiom, equivalence);

			// TODO: Copy Annotations to local ontology

			return Optional.of(equivalence);
		}

		return Optional.empty();
	}

	@Override
	public void removeObjectPropertyEquivalence(OntologyObjectPropertyEquivalence equivalence) {
		Optional<OWLEquivalentObjectPropertiesAxiom> optOwlObjectPropertyEquivalentAxiom = mapHandler.getMapHandler()
				.getOWLEqivalentObjectPropertiesAxiom(equivalence);

		if (optOwlObjectPropertyEquivalentAxiom.isPresent()) {
			owlOntology.removeAxiom(optOwlObjectPropertyEquivalentAxiom.get());

			getOntology().getObjectPropertyEquivalences().remove(equivalence);
			equivalence.getObjectProperty1().getEquivalentProperties().remove(equivalence.getObjectProperty2());
			equivalence.getObjectProperty2().getEquivalentProperties().remove(equivalence.getObjectProperty1());
		}
	}

	@Override
	public Stream<OntologyObjectProperty> getAllObjectProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteLocalOntology() throws OntologyManagerException {
		LOGGER.error("Not implemented yet.");
	}

	@Override
	public void removeClass(String iri) {
		LOGGER.error("Not implemented yet.");
	}

	@Override
	public void removeClass(String iri, String namespace) {
		LOGGER.error("Not implemented yet.");
	}

	@Override
	public void removeIndividual(String iri) {
		LOGGER.error("Not implemented yet.");
	}

	@Override
	public void removeIndividual(String iri, String namespace) {
		LOGGER.error("Not implemented yet.");
	}

	@Override
	public void removeObjectProperty(String iri) {
		LOGGER.error("Not implemented yet.");
	}

	@Override
	public void removeObjectProperty(String iri, String namespace) {
		LOGGER.error("Not implemented yet.");
	}
}
