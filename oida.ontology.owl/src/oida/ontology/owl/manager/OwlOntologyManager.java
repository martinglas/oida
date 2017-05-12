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
import org.semanticweb.owlapi.util.OWLEntityRenamer;
import org.semanticweb.owlapi.util.SimpleIRIMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oida.ontology.Ontology;
import oida.ontology.OntologyAnnotation;
import oida.ontology.OntologyAnnotationProperty;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyClassEquivalence;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyIndividual;
import oida.ontology.OntologyObjectProperty;
import oida.ontology.OntologyObjectPropertyAssertion;
import oida.ontology.OntologyObjectPropertyEquivalence;
import oida.ontology.manager.AbstractOntologyManager;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.OntologyManagerException;
import oida.ontology.manager.util.OntologyManagerUtils;
import oida.ontology.owl.manager.util.OwlOntologyManagerMapHandler;
import oida.ontologyMgr.OntologyFile;
import oida.util.OIDAUtil;
import oida.util.constants.StringConstants;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2016-11-23
 *
 */
public class OwlOntologyManager extends AbstractOntologyManager {
	private static Logger LOGGER = LoggerFactory.getLogger(OwlOntologyManager.class);

	private OwlOntologyManagerMapHandler mapHandler;
	private OwlOntologyManagerMapHandler mapHandlerLocal;
	private Map<Object, Object> twinMap;

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

		mapHandler = new OwlOntologyManagerMapHandler();
		mapHandlerLocal = new OwlOntologyManagerMapHandler();
		twinMap = new HashMap<Object, Object>();
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
	public Ontology createOntology(String iri) throws OntologyManagerException {
		try {
			owlOntology = owlOntologyManager.createOntology(IRI.create(iri));
			owlPrefixManager.setDefaultPrefix(iri);
			owlOntologyManager.setOntologyFormat(owlOntology, owlPrefixManager);

			setOntology(OntologyManagerUtils.generateInternalOntologyObject(iri));
			mapHandler.initializeOntology(owlDataFactory, owlOntology, getOntology());
			mapHandlerLocal.initializeOntology(owlDataFactory, owlOntology, getOntology().getLocalOntology());

			for (String prefixName : owlPrefixManager.getPrefixName2PrefixMap().keySet()) {
				OntologyManagerUtils.generateInternalNamespaceObject(getOntology(), prefixName, owlPrefixManager.getPrefixName2PrefixMap().get(prefixName));
				OntologyManagerUtils.generateInternalNamespaceObject(getOntology().getLocalOntology(), prefixName, owlPrefixManager.getPrefixName2PrefixMap().get(prefixName));
			}

			LOGGER.info("Ontology created: '" + iri + "'");
			return getOntology();
		} catch (OWLOntologyCreationException e) {
			throw new OntologyManagerException("Error while creating ontology '" + iri + "': " + e.getMessage(), e);
		}
	}

	@Override
	public Ontology loadOntology(String iri) throws OntologyManagerException {
		if (iri == null || iri.contentEquals(StringConstants.EMPTY))
			return null;

		try {
			updateIRIMappings();
			
			owlOntology = owlOntologyManager.loadOntology(IRI.create(iri));
			owlOntologyManager.setOntologyFormat(owlOntology, owlPrefixManager);
			owlPrefixManager.setDefaultPrefix(owlOntology.getOntologyID().getOntologyIRI().get().getIRIString());

			setOntology(OntologyManagerUtils.generateInternalOntologyObject(iri));
			mapHandler.initializeOntology(owlDataFactory, owlOntology, getOntology());
			mapHandlerLocal.initializeOntology(owlDataFactory, owlOntology, getOntology().getLocalOntology());

			extractOntologyContent(owlOntology, true);

			LOGGER.info("Ontology loaded: '" + iri + "'");

			return getOntology();
		} catch (OWLOntologyCreationException e) {
			throw new OntologyManagerException("Error while loading ontology from file '" + iri + "': " + e.getMessage(), e);
		}
	}

	private void updateIRIMappings() {
		owlOntologyManager.getIRIMappers().clear();
		
		for (Entry<String, String> entry : getGlobalOntologyContext().getGlobalIRIToLocalIRIMappings().entrySet())
			owlOntologyManager.getIRIMappers().add(new SimpleIRIMapper(IRI.create(entry.getKey()), IRI.create(entry.getValue())));
	}

	/**
	 * Searches the global ontology context for an existing ontology with a specified IRI.
	 * 
	 * @param iri The IRI of the ontology to search for.
	 * @return An OwlOntologyManager with the ontology, if existing. Null, if the IRI is null, the ontology is not existing already or the ontology is existing but managed with another ontology
	 *         manager implementation.
	 */
	protected Optional<OwlOntologyManager> findExistingManagedOntology(IRI iri) {
		if (iri == null)
			return Optional.empty();

		Optional<IOntologyManager> existingManagedOntology = getGlobalOntologyContext().findOntology(iri.getIRIString());

		if (existingManagedOntology.isPresent() && (existingManagedOntology.get() instanceof OwlOntologyManager))
			return Optional.of((OwlOntologyManager)existingManagedOntology.get());

		return Optional.empty();
	}

	private void extractOntologyContent(OWLOntology owlOntology, boolean toLocal) {
		extractImports(owlOntology, getOntology());

		Stream<OWLClass> allClasses = owlOntology.classesInSignature(Imports.INCLUDED);
		Stream<OWLSubClassOfAxiom> allSubClassOfAxioms = owlOntology.axioms(AxiomType.SUBCLASS_OF, Imports.INCLUDED);
		Stream<OWLEquivalentClassesAxiom> allEquivalentClassAxioms = owlOntology.axioms(AxiomType.EQUIVALENT_CLASSES, Imports.INCLUDED);
		extractClassHierarchy(getOntology(), allClasses, allSubClassOfAxioms, allEquivalentClassAxioms, mapHandler);
		
		if (toLocal) {
			Stream<OWLClass> allLocalClasses = allClasses.filter(cl -> cl.getIRI().getNamespace().equals(getDefaultNamespace() + StringConstants.HASHTAG));
			Stream<OWLSubClassOfAxiom> allLocalSubClassOfAxioms = owlOntology.axioms(AxiomType.SUBCLASS_OF, Imports.EXCLUDED);
			Stream<OWLEquivalentClassesAxiom> allLocalEquivalentClassAxioms = owlOntology.axioms(AxiomType.EQUIVALENT_CLASSES, Imports.EXCLUDED);
			extractClassHierarchy(getOntology().getLocalOntology(), allLocalClasses, allLocalSubClassOfAxioms, allLocalEquivalentClassAxioms, mapHandler);
		}

//		extractObjectPropertyHierarchy(owlOntology, ontology, mapHandler, imports, imports);
//		extractIndividuals(owlOntology, ontology, mapHandler, imports);
//		extractAnnotationProperties(owlOntology, ontology, mapHandler, imports);
	}

	private void extractImports(OWLOntology owlOntology, Ontology ontology) {
		for (OWLOntology importedOwlOntology : owlOntology.directImports().collect(Collectors.toList()))
			ontology.getImports().add(OntologyManagerUtils.generateInternalOntologyObject(importedOwlOntology.getOntologyID().getOntologyIRI().get().getIRIString()));

		ontology.getLocalOntology().getImports().addAll(ontology.getImports());
	}

	private void extractClassHierarchy(Ontology ontology, Stream<OWLClass> classes, Stream<OWLSubClassOfAxiom> subClassOfAxioms, Stream<OWLEquivalentClassesAxiom> classEquivalences, OwlOntologyManagerMapHandler mapHandler) {
		List<OWLClass> owlClasses = classes.collect(Collectors.toList());

		// create internal class objects for all owl classes. add the objects to the map handler and the ontology class collection:
		for (OWLClass owlClass : owlClasses) {
			if (!owlClass.getIRI().getIRIString().equals(mapHandler.getOwlThingClass().getIRI().getIRIString())) {
				OntologyClass internalClass = OntologyManagerUtils.generateInternalClassObject(ontology, null, owlClass.getIRI().getIRIString());
				mapHandler.toMap(owlClass, internalClass);
				ontology.getClasses().add(internalClass);
			}
		}

		// establish the class hierarchy based on the passed sub class axioms:
		for (OntologyClass internalClass : ontology.getClasses()) {
			OWLClass owlClass = mapHandler.getOWLClass(internalClass).get();

			List<OWLSubClassOfAxiom> owlSubClassOfAxioms = subClassOfAxioms.filter(axiom -> axiom.getSubClass().isOWLClass() && axiom.getSubClass().equals(owlClass)).collect(Collectors.toList());
			for (OWLSubClassOfAxiom owlAxiom : owlSubClassOfAxioms) {
				if (owlAxiom.getSuperClass().isOWLClass()) {
					OWLClass owlSuperClass = owlAxiom.getSuperClass().asOWLClass();

					Optional<OntologyClass> optInternalSuperClass = mapHandler.getInternalClass(owlSuperClass);
					if (optInternalSuperClass.isPresent()) {
						OntologyClass internalSuperClass = optInternalSuperClass.get();
						internalSuperClass.getSubClasses().add(internalClass);
						internalClass.getSuperClasses().add(internalSuperClass);
					}
				}
			}

			// if no super class is defined, the Thing class is set as super class:
			if (owlSubClassOfAxioms.isEmpty()) {
				mapHandler.getThingClass().getSubClasses().add(internalClass);
				internalClass.getSuperClasses().add(mapHandler.getThingClass());
			}

			// establish class equivalence relationships:
			List<OWLEquivalentClassesAxiom> owlclassEquivalences = classEquivalences.filter(equivalence -> equivalence.contains(owlClass)).collect(Collectors.toList());
			for (OWLEquivalentClassesAxiom owlEquivalence : owlclassEquivalences) {
				List<OWLClass> equivalentClasses = owlEquivalence.namedClasses().filter(cl -> !cl.equals(owlClass)).collect(Collectors.toList());

				for (OWLClass equivalentOwlCLass : equivalentClasses) {
					Optional<OntologyClass> optInternalEquivalentClass = mapHandler.getInternalClass(equivalentOwlCLass);
					
					if (!optInternalEquivalentClass.isPresent())
						optInternalEquivalentClass = this.mapHandler.getInternalClass(equivalentOwlCLass);
					
					if (optInternalEquivalentClass.isPresent())
						OntologyManagerUtils.assignClassesEquivalent(ontology, internalClass, optInternalEquivalentClass.get());
				}
			}
		}
	}

/*	private void extractClassHierarchy(OWLOntology owlOntology, Ontology ontology, OwlOntologyManagerMapHandler mapHandler, boolean includeImportsForClasses, Imports includeImportsForSubClassAxioms) {
		List<OntologyClass> classes = new ArrayList<OntologyClass>();

		Stream<OWLClass> classStream = owlOntology.classesInSignature();
		if (!includeImportsForClasses)
			classStream = classStream.filter(cl -> cl.getIRI().getNamespace().equals(getDefaultNamespace() + StringConstants.HASHTAG));

		List<OWLClass> owlAllClasses = classStream.collect(Collectors.toList());
		for (OWLClass owlClass : owlAllClasses) {
			if (!owlClass.getIRI().getIRIString().equals(mapHandler.getOwlThingClass().getIRI().getIRIString())) {
				OntologyClass clazz = OntologyManagerUtils.generateInternalClassObject(ontology, null, owlClass.getIRI().getIRIString());
				mapHandler.toMap(owlClass, clazz);
				classes.add(clazz);
			}
		}

		// Establish class hierarchy for all selected classes (including or excluding imports):
		for (OntologyClass clazz : classes) {
			Optional<OWLClass> optOWLClass = mapHandler.getOWLClass(clazz);
			if (optOWLClass.isPresent()) {
				OWLClass owlClass = optOWLClass.get();

				// load subclass axioms and establish class hierarchy:
				List<OWLSubClassOfAxiom> axioms = owlOntology.axioms(owlClass, includeImportsForSubClassAxioms).filter(ax -> ax instanceof OWLSubClassOfAxiom).map(ax -> (OWLSubClassOfAxiom)ax)
						.collect(Collectors.toList());
				for (OWLSubClassOfAxiom axiom : axioms) {
					if (axiom.getSuperClass().isOWLClass()) {
						OWLClass superClass = axiom.getSuperClass().asOWLClass();

						Optional<OntologyClass> optInternalSuperClass = mapHandler.getInternalClass(superClass);
						if (optInternalSuperClass.isPresent()) {
							OntologyClass internalSuperClass = optInternalSuperClass.get();
							internalSuperClass.getSubClasses().add(clazz);
							clazz.getSuperClasses().add(internalSuperClass);
						}
					}
				}

				// if no super class is defined, the Thing class is set:
				if (axioms.isEmpty()) {
					mapHandler.getThingClass().getSubClasses().add(clazz);
					clazz.getSuperClasses().add(mapHandler.getThingClass());
				}

				// load class equivalences:
				List<OWLEquivalentClassesAxiom> equivalences = owlOntology.equivalentClassesAxioms(owlClass).collect(Collectors.toList());
				for (OWLEquivalentClassesAxiom equ : equivalences) {
					List<OWLClass> equivalentClasses = equ.namedClasses().filter(cl -> !cl.equals(owlClass)).collect(Collectors.toList());

					for (OWLClass equivalentOwlCLass : equivalentClasses) {
						Optional<OntologyClass> optInternalEquivalentClass = mapHandler.getInternalClass(equivalentOwlCLass);

						if (!optInternalEquivalentClass.isPresent())
							optInternalEquivalentClass = mapHandler.getInternalClass(equivalentOwlCLass);

						if (optInternalEquivalentClass.isPresent())
							OntologyManagerUtils.assignClassesEquivalent(ontology, clazz, optInternalEquivalentClass.get());
					}
				}
			}
		}
	}*/

	private void extractObjectPropertyHierarchy(OWLOntology owlOntology, Ontology ontology, OwlOntologyManagerMapHandler mapHandler, Imports includeImportsForObjectProperties,
			Imports includeImportsForSubObjPropAxioms) {
		List<OntologyObjectProperty> objectProperties = new ArrayList<OntologyObjectProperty>();

		List<OWLObjectProperty> owlAllObjectProperties = owlOntology.objectPropertiesInSignature(includeImportsForObjectProperties).collect(Collectors.toList());
		for (OWLObjectProperty owlObjectProperty : owlAllObjectProperties) {
			if (!owlObjectProperty.getIRI().getIRIString().equals(mapHandler.getOwlTopObjectProperty().getIRI().getIRIString())) {
				OntologyObjectProperty objectProperty = OntologyManagerUtils.generateInternalObjectPropertyObject(ontology, null, owlObjectProperty.getIRI().getIRIString());
				mapHandler.toMap(owlObjectProperty, objectProperty);
				objectProperties.add(objectProperty);
			}
		}

		// Establish class hierarchy for all selected classes (including or excluding imports):
		for (OntologyObjectProperty objectProperty : objectProperties) {
			Optional<OWLObjectProperty> optOWLObjectProperty = mapHandler.getOWLObjectProperty(objectProperty);
			if (optOWLObjectProperty.isPresent()) {
				OWLObjectProperty owlObjectProperty = optOWLObjectProperty.get();

				// load subclass axioms and establish class hierarchy:
				List<OWLSubObjectPropertyOfAxiom> axioms = owlOntology.axioms(owlObjectProperty, includeImportsForSubObjPropAxioms).filter(ax -> ax instanceof OWLSubObjectPropertyOfAxiom)
						.map(ax -> (OWLSubObjectPropertyOfAxiom)ax).collect(Collectors.toList());
				for (OWLSubObjectPropertyOfAxiom axiom : axioms) {
					if (axiom.getSuperProperty().isOWLObjectProperty()) {
						OWLObjectProperty superObjectProperty = axiom.getSuperProperty().asOWLObjectProperty();

						Optional<OntologyObjectProperty> optInternalSuperObjectProperty = mapHandler.getInternalObjectProperty(superObjectProperty);
						if (optInternalSuperObjectProperty.isPresent()) {
							OntologyObjectProperty internalSuperObjectProperty = optInternalSuperObjectProperty.get();
							internalSuperObjectProperty.getSubObjectProperties().add(objectProperty);
							objectProperty.getSuperProperties().add(internalSuperObjectProperty);
						}
					}
				}

				// if no super class is defined, the Thing class is set:
				if (axioms.isEmpty()) {
					mapHandler.getTopObjectProperty().getSubObjectProperties().add(objectProperty);
					objectProperty.getSuperProperties().add(mapHandler.getTopObjectProperty());
				}

				// load class equivalences:
				List<OWLEquivalentObjectPropertiesAxiom> equivalences = owlOntology.equivalentObjectPropertiesAxioms(owlObjectProperty).collect(Collectors.toList());
				for (OWLEquivalentObjectPropertiesAxiom equ : equivalences) {
					List<OWLObjectProperty> equivalentObjectProperties = equ.objectPropertiesInSignature().collect(Collectors.toList());

					Optional<OntologyObjectProperty> optInternalObjectProperty1 = mapHandler.getInternalObjectProperty(equivalentObjectProperties.get(0));
					Optional<OntologyObjectProperty> optInternalObjectProperty2 = mapHandler.getInternalObjectProperty(equivalentObjectProperties.get(1));
					if (optInternalObjectProperty1.isPresent() && optInternalObjectProperty2.isPresent())
						OntologyManagerUtils.assignObjectPropertiesEquivalent(ontology, optInternalObjectProperty1.get(), optInternalObjectProperty2.get());
				}
			}
		}
	}

	private void extractAnnotationProperties(OWLOntology owlOntology, Ontology ontology, OwlOntologyManagerMapHandler mapHandler, Imports includeImports) {
		List<OWLAnnotationProperty> allAnnotationProperties = owlOntology.annotationPropertiesInSignature(includeImports).collect(Collectors.toList());

		for (OWLAnnotationProperty owlAnnotationProperty : allAnnotationProperties)
			mapHandler.toMap(owlAnnotationProperty, OntologyManagerUtils.generateInternalAnnotationPropertyObject(ontology, owlAnnotationProperty.getIRI().getIRIString()));
	}

	private void extractIndividuals(OWLOntology owlOntology, Ontology ontology, OwlOntologyManagerMapHandler mapHandler, Imports includeImports) {
		for (OWLNamedIndividual owlIndividual : owlOntology.individualsInSignature(includeImports).collect(Collectors.toList())) {
			OntologyIndividual individual = OntologyManagerUtils.generateInternalIndividualObject(ontology, owlIndividual.getIRI().getIRIString());

			mapHandler.toMap(owlIndividual, individual);

			for (OWLClassAssertionAxiom a : owlOntology.classAssertionAxioms(owlIndividual).collect(Collectors.toList())) {
				if (a.getClassExpression().isOWLClass()) {
					Optional<OntologyClass> optC = mapHandler.getInternalClass(a.getClassExpression().asOWLClass(), ontology);
					if (optC.isPresent()) {
						optC.get().getIndividuals().add(individual);
						individual.getTypes().add(optC.get());
					}
				}
			}
		}
	}

	@Override
	public void saveOntology() throws OntologyManagerException {
		Optional<OntologyFile> optOntologyFile = getOntologyFile();
		if (optOntologyFile.isPresent()) {
			Optional<File> optFile = OIDAUtil.getOntologyFileObject(optOntologyFile.get(), true);

			if (optFile.isPresent()) {
				try {
					FileOutputStream outputStream = new FileOutputStream(optFile.get());
					owlOntologyManager.saveOntology(owlOntology, owlPrefixManager, outputStream);
					LOGGER.info("Ontology saved: '" + getOntology().getIri() + "'");
				} catch (FileNotFoundException e) {
					throw new OntologyManagerException("Error while saving ontology to file '" + optOntologyFile.get().getFileName() + "': " + e.getMessage(), e);
				} catch (OWLOntologyStorageException e) {
					throw new OntologyManagerException("Error while saving ontology to file '" + optOntologyFile.get().getFileName() + "': " + e.getMessage(), e);
				}
			}
		} else {
			throw new OntologyManagerException("Error while saving ontology: Manager has no file information.");
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
			extractOntologyContent(owlImportedOntology, false);
		} catch (OWLOntologyCreationException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void assignSubClassToSuperClass(OntologyClass subClass, OntologyClass superClass) {
		Optional<OWLClass> optSubOwlClass = mapHandler.getOWLClass(subClass);
		Optional<OWLClass> optSuperOwlClass = mapHandler.getOWLClass(superClass);

		if (optSubOwlClass.isPresent() && optSuperOwlClass.isPresent()) {
			owlOntologyManager.addAxiom(owlOntology, owlDataFactory.getOWLSubClassOfAxiom(optSubOwlClass.get(), optSuperOwlClass.get()));

			OntologyManagerUtils.assignSubClassToSuperClass(subClass, superClass);
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

		OntologyClass internalClass = OntologyManagerUtils.generateInternalClassObject(getOntology(), mapHandler.getThingClass(), name, namespace);
		mapHandler.toMap(owlNewClass, internalClass);
		
		if (namespace.equals(getDefaultNamespace())) {
			mapHandlerLocal.toMap(owlNewClass, OntologyManagerUtils.generateInternalClassObject(getOntology().getLocalOntology(), mapHandlerLocal.getThingClass(), name, namespace));
		}

		return internalClass;
	}
	
	@Override
	public OntologyIndividual createIndividual(final String name, final String namespace) {
		Optional<OntologyIndividual> optInternalIndividual = getIndividual(name, namespace);
		if (optInternalIndividual.isPresent()) {
			return optInternalIndividual.get();
		}

		OWLNamedIndividual owlIndividual = owlDataFactory.getOWLNamedIndividual(OntologyManagerUtils.buildFullIRIString(name, namespace));
		OWLAxiom declareNewIndividual = owlDataFactory.getOWLDeclarationAxiom(owlIndividual);
		owlOntologyManager.addAxiom(owlOntology, declareNewIndividual);

		OntologyIndividual internalIndividual = OntologyManagerUtils.generateInternalIndividualObject(getOntology(), name, namespace);
		mapHandler.toMap(owlIndividual, internalIndividual);
		
		if (namespace.equals(getDefaultNamespace())) {
			mapHandlerLocal.toMap(owlIndividual, OntologyManagerUtils.generateInternalIndividualObject(getOntology().getLocalOntology(), name, namespace));
		}

		return internalIndividual;
	}

	@Override
	public OntologyObjectProperty createObjectProperty(final String name, final String namespace) {
		Optional<OntologyObjectProperty> optInternalObjectProperty = getObjectProperty(name, namespace);
		if (optInternalObjectProperty.isPresent()) {
			return optInternalObjectProperty.get();
		}
		
		OWLObjectProperty owlProperty = owlDataFactory.getOWLObjectProperty(OntologyManagerUtils.buildFullIRIString(name, namespace));
		OWLDeclarationAxiom owlAxiom = owlDataFactory.getOWLDeclarationAxiom(owlProperty);
		owlOntologyManager.addAxiom(owlOntology, owlAxiom);

		OntologyObjectProperty internalObjectProperty = OntologyManagerUtils.generateInternalObjectPropertyObject(getOntology(), mapHandler.getTopObjectProperty(), name, namespace);
		mapHandler.toMap(owlProperty, internalObjectProperty);
		
		if (namespace.equals(getDefaultNamespace())) {
			mapHandlerLocal.toMap(owlProperty, OntologyManagerUtils.generateInternalObjectPropertyObject(getOntology().getLocalOntology(), mapHandler.getTopObjectProperty(), name, namespace));
		}

		return internalObjectProperty;
	}


	@Override
	public void renameEntity(OntologyEntity entity, String newName) {
		Collection<OWLOntology> c = new ArrayList<OWLOntology>();
		c.add(owlOntology);

		OWLEntityRenamer renamer = new OWLEntityRenamer(owlOntologyManager, c);

		Optional<OWLEntity> optRenameEntity = mapHandler.getOWLEntity(entity);
		if (optRenameEntity.isPresent()) {
			OntologyManagerUtils.changeOntologyEntityName(entity, newName);
			List<OWLOntologyChange> changes = renamer.changeIRI(optRenameEntity.get(), IRI.create(optRenameEntity.get().getIRI().toString().replace(entity.getName(), newName)));
			owlOntologyManager.applyChanges(changes);

			entity.setName(newName);
			Optional<OWLEntity> optEntity = changes.get(1).signature().findFirst();
			if (optEntity.isPresent())
				mapHandler.toMap(optEntity.get(), entity);
		}
	}

	@Override
	public void assignIndividualToClass(OntologyIndividual individual, OntologyClass clazz) {
		Optional<OWLNamedIndividual> optOwlIndividual = mapHandler.getOWLIndividual(individual);
		Optional<OWLClass> optOwlClass = mapHandler.getOWLClass(clazz);

		if (optOwlIndividual.isPresent() && optOwlClass.isPresent()) {
			owlOntologyManager.addAxiom(owlOntology, owlDataFactory.getOWLClassAssertionAxiom(optOwlClass.get(), optOwlIndividual.get()));

			OntologyManagerUtils.assignIndividualToClass(individual, clazz);
		}
	}

	@Override
	public void assignSubObjectPropertyToSuperObjectProperty(OntologyObjectProperty subProperty, OntologyObjectProperty superProperty) {
		Optional<OWLObjectProperty> optSubOwlObjectProperty = mapHandler.getOWLObjectProperty(subProperty);
		Optional<OWLObjectProperty> optSuperOwlObjectProperty = mapHandler.getOWLObjectProperty(superProperty);

		if (optSubOwlObjectProperty.isPresent() && optSuperOwlObjectProperty.isPresent()) {
			OWLSubObjectPropertyOfAxiom owlSubObjectPropertyAxiom = owlDataFactory.getOWLSubObjectPropertyOfAxiom(optSubOwlObjectProperty.get(), optSuperOwlObjectProperty.get());
			owlOntologyManager.addAxiom(owlOntology, owlSubObjectPropertyAxiom);

			OntologyManagerUtils.assignSubObjectPropertyToSuperObjectProperty(subProperty, superProperty);
		}
	}

	@Override
	public void assignInverseObjectProperty(OntologyObjectProperty property, OntologyObjectProperty inverseProperty) {
		Optional<OWLObjectProperty> optOwlObjectProperty = mapHandler.getOWLObjectProperty(property);
		Optional<OWLObjectProperty> optOwlInverseObjectProperty = mapHandler.getOWLObjectProperty(inverseProperty);

		if (optOwlObjectProperty.isPresent() && optOwlInverseObjectProperty.isPresent()) {
			OWLInverseObjectPropertiesAxiom owlInverseAxiom = owlDataFactory.getOWLInverseObjectPropertiesAxiom(optOwlObjectProperty.get(), optOwlInverseObjectProperty.get());
			owlOntologyManager.addAxiom(owlOntology, owlInverseAxiom);

			property.getInverseObjectProperties().add(inverseProperty);
			inverseProperty.getInverseObjectProperties().add(property);
		}
	}

	@Override
	public void makeObjectPropertyFunctional(OntologyObjectProperty property) {
		Optional<OWLObjectProperty> optOwlObjectProperty = mapHandler.getOWLObjectProperty(property);
		if (optOwlObjectProperty.isPresent()) {
			OWLFunctionalObjectPropertyAxiom owlFunctionalOP = owlDataFactory.getOWLFunctionalObjectPropertyAxiom(optOwlObjectProperty.get());
			owlOntologyManager.addAxiom(owlOntology, owlFunctionalOP);

			property.setFunctional(true);
		}
	}

	@Override
	public void makeObjectPropertyInverseFunctional(OntologyObjectProperty property) {
		Optional<OWLObjectProperty> optOwlObjectProperty = mapHandler.getOWLObjectProperty(property);
		if (optOwlObjectProperty.isPresent()) {
			OWLInverseFunctionalObjectPropertyAxiom owlInverseFunctionalOP = owlDataFactory.getOWLInverseFunctionalObjectPropertyAxiom(optOwlObjectProperty.get());
			owlOntologyManager.addAxiom(owlOntology, owlInverseFunctionalOP);

			property.setInverseFunctional(true);
		}
	}

	@Override
	public void makeObjectPropertyTransitive(OntologyObjectProperty property) {
		Optional<OWLObjectProperty> optOwlObjectProperty = mapHandler.getOWLObjectProperty(property);
		if (optOwlObjectProperty.isPresent()) {
			OWLTransitiveObjectPropertyAxiom owlTransitiveOP = owlDataFactory.getOWLTransitiveObjectPropertyAxiom(optOwlObjectProperty.get());
			owlOntologyManager.addAxiom(owlOntology, owlTransitiveOP);

			property.setTransitive(true);
		}
	}

	@Override
	public void makeObjectPropertySymmetric(OntologyObjectProperty property) {
		Optional<OWLObjectProperty> optOwlObjectProperty = mapHandler.getOWLObjectProperty(property);
		if (optOwlObjectProperty.isPresent()) {
			OWLSymmetricObjectPropertyAxiom owlSymmetricOP = owlDataFactory.getOWLSymmetricObjectPropertyAxiom(optOwlObjectProperty.get());
			owlOntologyManager.addAxiom(owlOntology, owlSymmetricOP);

			property.setSymmetric(true);
		}
	}

	@Override
	public void makeObjectPropertyAsymmetric(OntologyObjectProperty property) {
		Optional<OWLObjectProperty> optOwlObjectProperty = mapHandler.getOWLObjectProperty(property);
		if (optOwlObjectProperty.isPresent()) {
			OWLAsymmetricObjectPropertyAxiom owlAsymmetricOP = owlDataFactory.getOWLAsymmetricObjectPropertyAxiom(optOwlObjectProperty.get());
			owlOntologyManager.addAxiom(owlOntology, owlAsymmetricOP);

			property.setAsymmetric(true);
		}
	}

	@Override
	public void makeObjectPropertyReflexive(OntologyObjectProperty property) {
		Optional<OWLObjectProperty> optOwlObjectProperty = mapHandler.getOWLObjectProperty(property);
		if (optOwlObjectProperty.isPresent()) {
			OWLReflexiveObjectPropertyAxiom owlReflexiveOP = owlDataFactory.getOWLReflexiveObjectPropertyAxiom(optOwlObjectProperty.get());
			owlOntologyManager.addAxiom(owlOntology, owlReflexiveOP);

			property.setReflexive(true);
		}
	}

	@Override
	public void makeObjectPropertyIrreflexive(OntologyObjectProperty property) {
		Optional<OWLObjectProperty> optOwlObjectProperty = mapHandler.getOWLObjectProperty(property);
		if (optOwlObjectProperty.isPresent()) {
			OWLIrreflexiveObjectPropertyAxiom owlIrreflexiveOP = owlDataFactory.getOWLIrreflexiveObjectPropertyAxiom(optOwlObjectProperty.get());
			owlOntologyManager.addAxiom(owlOntology, owlIrreflexiveOP);
			property.setIrreflexive(true);
		}
	}

	@Override
	public void assignObjectPropertyRange(OntologyObjectProperty property, OntologyClass range) {
		Optional<OWLObjectProperty> optOwlObjectProperty = mapHandler.getOWLObjectProperty(property);
		Optional<OWLClass> optOwlClass = mapHandler.getOWLClass(range);

		if (optOwlObjectProperty.isPresent() && optOwlClass.isPresent()) {
			OWLObjectPropertyRangeAxiom owlAxiom = owlDataFactory.getOWLObjectPropertyRangeAxiom(optOwlObjectProperty.get(), optOwlClass.get());
			owlOntologyManager.addAxiom(owlOntology, owlAxiom);

			property.setRange(range);
		}
	}

	@Override
	public void assignObjectPropertyDomain(OntologyObjectProperty property, OntologyClass domain) {
		Optional<OWLObjectProperty> optOwlObjectProperty = mapHandler.getOWLObjectProperty(property);
		Optional<OWLClass> optOwlClass = mapHandler.getOWLClass(domain);

		if (optOwlObjectProperty.isPresent() && optOwlClass.isPresent()) {
			OWLObjectPropertyDomainAxiom owlAxiom = owlDataFactory.getOWLObjectPropertyDomainAxiom(optOwlObjectProperty.get(), optOwlClass.get());
			owlOntologyManager.addAxiom(owlOntology, owlAxiom);

			property.setDomain(domain);
		}
	}

	@Override
	public OntologyObjectPropertyAssertion createObjectPropertyAssertion(OntologyObjectProperty property, OntologyIndividual individual, OntologyIndividual object) {
		Optional<OWLObjectProperty> optOwlObjectProperty = mapHandler.getOWLObjectProperty(property);
		Optional<OWLNamedIndividual> optOwlIndividual = mapHandler.getOWLIndividual(individual);
		Optional<OWLNamedIndividual> optOwlObject = mapHandler.getOWLIndividual(object);

		if (optOwlObjectProperty.isPresent() && optOwlIndividual.isPresent() && optOwlObject.isPresent()) {
			OWLObjectPropertyAssertionAxiom owlAssertionAxiom = owlDataFactory.getOWLObjectPropertyAssertionAxiom(optOwlObjectProperty.get(), optOwlIndividual.get(), optOwlObject.get());
			owlOntologyManager.addAxiom(owlOntology, owlAssertionAxiom);

			OntologyObjectPropertyAssertion assertion = OntologyManagerUtils.generateObjectPropertyAssertionObject(getActiveOntology(), property, object);
			individual.getObjectPropertyAssertions().add(assertion);

			return assertion;
		} else
			return null;
	}

	@Override
	public OntologyAnnotationProperty createAnnotationProperty(String propertyName, String prefix) {
		OWLAnnotationProperty owlProperty = owlDataFactory.getOWLAnnotationProperty(OntologyManagerUtils.buildFullIRIString(propertyName, prefix));// , owlPrefixManager);
		OWLDeclarationAxiom owlAxiom = owlDataFactory.getOWLDeclarationAxiom(owlProperty);

		owlOntologyManager.addAxiom(owlOntology, owlAxiom);

		OntologyAnnotationProperty prop = OntologyManagerUtils.generateInternalAnnotationPropertyObject(getActiveOntology(), propertyName, prefix);
		mapHandler.toMap(owlProperty, prop);

		return prop;
	}

	@Override
	public Optional<OntologyAnnotation> annotateClass(OntologyAnnotationProperty property, String annotationValue, OntologyClass clazz) {
		Optional<OWLAnnotationProperty> optOwlAnnotationProperty = mapHandler.getOWLAnnotationProperty(property);
		Optional<OWLClass> optOwlClass = mapHandler.getOWLClass(clazz);

		if (optOwlAnnotationProperty.isPresent() && optOwlClass.isPresent()) {
			OWLAnnotation owlAnnotation = owlDataFactory.getOWLAnnotation(optOwlAnnotationProperty.get(), owlDataFactory.getOWLLiteral(annotationValue));
			OWLAnnotationAssertionAxiom owlAxiom = owlDataFactory.getOWLAnnotationAssertionAxiom(optOwlClass.get().getIRI(), owlAnnotation);

			owlOntologyManager.addAxiom(owlOntology, owlAxiom);

			OntologyAnnotation annotation = OntologyManagerUtils.generateAnnotationObject(getActiveOntology(), property, annotationValue);
			clazz.getAnnotations().add(annotation);
			getActiveMapHandler().toMap(owlAnnotation, annotation);

			return Optional.of(annotation);
		}

		return Optional.empty();
	}

	@Override
	public Optional<OntologyAnnotation> annotateIndividual(OntologyIndividual individual, OntologyAnnotationProperty property, String annotationValue) {
		Optional<OWLAnnotationProperty> optOwlAnnotationProperty = mapHandler.getOWLAnnotationProperty(property);
		Optional<OWLNamedIndividual> optOwlIndividual = mapHandler.getOWLIndividual(individual);

		if (optOwlAnnotationProperty.isPresent() && optOwlIndividual.isPresent()) {
			OWLAnnotation owlAnnotation = owlDataFactory.getOWLAnnotation(optOwlAnnotationProperty.get(), owlDataFactory.getOWLLiteral(annotationValue));
			OWLAnnotationAssertionAxiom owlAxiom = owlDataFactory.getOWLAnnotationAssertionAxiom(optOwlIndividual.get().getIRI(), owlAnnotation);

			owlOntologyManager.addAxiom(owlOntology, owlAxiom);

			OntologyAnnotation annotation = OntologyManagerUtils.generateAnnotationObject(getActiveOntology(), property, annotationValue);
			individual.getAnnotations().add(annotation);
			getActiveMapHandler().toMap(owlAnnotation, annotation);

			return Optional.of(annotation);
		}

		return Optional.empty();
	}

	@Override
	public Optional<OntologyClassEquivalence> assignClassEquivalence(OntologyClass clazz, OntologyClass equivalentClazz) {
		Optional<OWLClass> optOwlClass = mapHandler.getOWLClass(clazz);
		Optional<OWLClass> optOwlEquivalentClass = mapHandler.getOWLClass(equivalentClazz);

		if (optOwlClass.isPresent() && optOwlEquivalentClass.isPresent()) {
			OWLEquivalentClassesAxiom owlAxiom = owlDataFactory.getOWLEquivalentClassesAxiom(optOwlClass.get(), optOwlEquivalentClass.get());

			owlOntologyManager.addAxiom(owlOntology, owlAxiom);

			OntologyClassEquivalence equivalence = OntologyManagerUtils.assignClassesEquivalent(getActiveOntology(), clazz, equivalentClazz);
			getActiveMapHandler().toMap(owlAxiom, equivalence);

			return Optional.of(equivalence);
		}

		return Optional.empty();
	}

	@Override
	public Optional<OntologyObjectPropertyEquivalence> assignObjectPropertyEquivalence(OntologyObjectProperty objectProperty, OntologyObjectProperty equivalentObjectProperty) {
		Optional<OWLObjectProperty> optOwlObjectProperty = mapHandler.getOWLObjectProperty(objectProperty);
		Optional<OWLObjectProperty> optOwlEquivalentObjectProperty = mapHandler.getOWLObjectProperty(equivalentObjectProperty);

		if (optOwlObjectProperty.isPresent() && optOwlEquivalentObjectProperty.isPresent()) {
			OWLEquivalentObjectPropertiesAxiom owlAxiom = owlDataFactory.getOWLEquivalentObjectPropertiesAxiom(optOwlObjectProperty.get(), optOwlEquivalentObjectProperty.get());
			owlOntologyManager.addAxiom(owlOntology, owlAxiom);

			OntologyObjectPropertyEquivalence equivalence = OntologyManagerUtils.assignObjectPropertiesEquivalent(getActiveOntology(), objectProperty, equivalentObjectProperty);
			getActiveMapHandler().toMap(owlAxiom, equivalence);

			return Optional.of(equivalence);
		}

		return Optional.empty();
	}
}
