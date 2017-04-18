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
import java.util.List;
import java.util.Map;
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
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.util.OWLEntityRenamer;
import org.semanticweb.owlapi.util.SimpleIRIMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oida.ontology.Ontology;
import oida.ontology.OntologyAnnotation;
import oida.ontology.OntologyAnnotationProperty;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyIndividual;
import oida.ontology.OntologyObjectProperty;
import oida.ontology.OntologyObjectPropertyAssertion;
import oida.ontology.manager.AbstractOntologyManager;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.OntologyManagerException;
import oida.ontology.manager.util.OntologyManagerUtils;
import oida.ontology.owl.manager.util.OwlOntologyManagerMapHandler;
import oida.ontologyMgr.OntologyFile;
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

	private OWLOntologyManager owlOntologyManager;
	private OWLOntology owlOntology;
	private OWLDataFactory owlDataFactory;

	private OWLXMLDocumentFormat owlPrefixManager;

	private OWLReasoner reasoner;

	/**
	 * Standard Constructor.
	 */
	OwlOntologyManager() {
		clearOntologyManager();
	}

	@Override
	public void clearOntologyManager() {
		owlPrefixManager = new OWLXMLDocumentFormat();
		owlOntologyManager = OWLManager.createOWLOntologyManager();
		owlDataFactory = owlOntologyManager.getOWLDataFactory();

		mapHandler = new OwlOntologyManagerMapHandler();
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
		clearOntologyManager();

		try {
			owlOntology = owlOntologyManager.createOntology(IRI.create(iri));
			owlOntologyManager.setOntologyFormat(owlOntology, owlPrefixManager);
			setOntology(OntologyManagerUtils.generateInternalOntologyObject(iri, owlOntology.classesInSignature().count(), owlOntology.individualsInSignature().count()));

			mapHandler.initializeOntology(owlDataFactory, owlOntology, getOntology());

			for (String prefixName : owlPrefixManager.getPrefixName2PrefixMap().keySet())
				OntologyManagerUtils.generateInternalNamespaceObject(getOntology(), prefixName, owlPrefixManager.getPrefixName2PrefixMap().get(prefixName));

			LOGGER.info("Ontology created: '" + iri + "'");
			return getOntology();
		} catch (OWLOntologyCreationException e) {
			throw new OntologyManagerException("Error while creating ontology '" + iri + "': " + e.getMessage(), e);
		}
	}

	@Override
	public Ontology loadOntology(OntologyFile ontologyFile) throws OntologyManagerException {
		clearOntologyManager();

		if (ontologyFile == null)
			return null;

		File file = getOntologyFileObject(ontologyFile);
		if (file != null && file.exists()) {
			try {
				owlOntology = owlOntologyManager.loadOntologyFromOntologyDocument(file);
				//owlPrefixManager.copyPrefixesFrom(owlOntologyManager.getOntologyFormat(owlOntology).asPrefixOWLDocumentFormat());
				owlOntologyManager.setOntologyFormat(owlOntology, owlPrefixManager);
				setOntology(OntologyManagerUtils.generateInternalOntologyObject(owlOntology.getOntologyID().getOntologyIRI().get().getIRIString(), owlOntology.classesInSignature().count(),
						owlOntology.individualsInSignature().count()));

				mapHandler.initializeOntology(owlDataFactory, owlOntology, getOntology());

				extractOntologyContent(owlOntology, getOntology(), true);
				setOntologyFile(ontologyFile);
				getOntology().setOntologyFile(ontologyFile);
				LOGGER.info("Ontology loaded: '" + file.getName() + "'");

				return getOntology();
			} catch (OWLOntologyCreationException e) {
				throw new OntologyManagerException("Error while loading ontology from file '" + file.getName() + "': " + e.getMessage(), e);
			}
		} else
			throw new OntologyManagerException("Error while loading ontology: File doesn't exist.");
	}

	@Override
	public void addGlobalIRIToLocalPathMapping(String iri, String localPath) {
		localPath = "file:/" + localPath.replace("\\", "/");
		owlOntologyManager.getIRIMappers().add(new SimpleIRIMapper(IRI.create(iri), IRI.create(localPath)));
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

	protected void extractOntologyContent(OWLOntology owlOntology, Ontology ontology, boolean includeImports) {
		extractClassHierarchy(owlOntology, ontology, includeImports);
		extractObjectPropertyHierarchy(owlOntology, ontology, includeImports);
		extractIndividuals(owlOntology, ontology, includeImports);

		// Optional<OwlOntologyManager> existingManagedOntology = findExistingManagedOntology(owlOntology.getOntologyID().getOntologyIRI().get());
		// if (existingManagedOntology.isPresent()) {
		// ontology = existingManagedOntology.get().getOntology();
		// mapHandler.toMap(owlOntology, ontology);
		// mapHandler.importFromOtherOwlOntologyManager(existingManagedOntology.get().mapHandler);
		// } else {
		// ontology = OntologyManagerUtils.generateInternalOntologyObject(owlOntology.getOntologyID().getOntologyIRI().get().toString(), owlOntology.classesInSignature(Imports.INCLUDED).count(),
		// owlOntology.individualsInSignature().count());
		// addThingClass(ontology);
		// addTopObjectProperty(ontology);
		//
		// mapHandler.toMap(owlOntology, ontology);
		//
		// for (OWLOntology owlImportOntology : owlOntology.imports().collect(Collectors.toList()))
		// ontology.getImports().add(extractOntologyContent(owlImportOntology));
		//
		// for (String prefixName : owlPrefixManager.getPrefixName2PrefixMap().keySet())
		// OntologyManagerUtils.generateInternalNamespaceObject(ontology, prefixName, owlPrefixManager.getPrefixName2PrefixMap().get(prefixName));
		// }
	}

	protected void extractClassHierarchy(OWLOntology owlOntology, Ontology ontology, boolean includeImports) {
		Imports imports = Imports.INCLUDED;
		if (!includeImports)
			imports = Imports.EXCLUDED;

		List<OWLClass> allClasses = owlOntology.classesInSignature(imports).collect(Collectors.toList());

		// Generate internal class objects for all OWL-classes as subclass of Thing:
		for (OWLClass owlClass : allClasses) {
			if (!owlClass.getIRI().getIRIString().equals(mapHandler.getOwlThingClass().getIRI().getIRIString()))
				mapHandler.toMap(owlClass, OntologyManagerUtils.generateInternalClassObject(ontology, mapHandler.getThingClass(), owlClass.getIRI().getIRIString()));
		}

		// Establish class hierarchy:
		for (OWLClass owlClass : allClasses) {
			Optional<OntologyClass> optInternalClass = mapHandler.getInternalClass(owlClass, ontology);
			if (optInternalClass.isPresent()) {
				OntologyClass internalClass = optInternalClass.get();

				List<OWLSubClassOfAxiom> axioms = owlOntology.axioms(owlClass, imports).filter(ax -> ax instanceof OWLSubClassOfAxiom).map(ax -> (OWLSubClassOfAxiom)ax).collect(Collectors.toList());
				for (OWLSubClassOfAxiom axiom : axioms) {
					if (axiom.getSuperClass().isOWLClass()) {
						OWLClass superClass = axiom.getSuperClass().asOWLClass();

						Optional<OntologyClass> optInternalSuperClass = mapHandler.getInternalClass(superClass, ontology);
						if (optInternalSuperClass.isPresent()) {
							OntologyClass internalSuperClass = optInternalSuperClass.get();
							internalSuperClass.getSubClasses().add(internalClass);
							internalClass.getSuperClasses().add(internalSuperClass);

							if (internalClass.getSuperClasses().contains(mapHandler.getThingClass())) {
								mapHandler.getThingClass().getSubClasses().remove(internalClass);
								internalClass.getSuperClasses().remove(mapHandler.getThingClass());
							}
						}
					}
				}
			}
		}
	}

	protected void extractObjectPropertyHierarchy(OWLOntology owlOntology, Ontology ontology, boolean includeImports) {
		Imports imports = Imports.INCLUDED;
		if (!includeImports)
			imports = Imports.EXCLUDED;

		List<OWLObjectProperty> allObjectProperties = owlOntology.objectPropertiesInSignature(imports).collect(Collectors.toList());

		for (OWLObjectProperty owlObjectProperty : allObjectProperties) {
			if (!owlObjectProperty.getIRI().getIRIString().equals(mapHandler.getOwlTopObjectProperty().getIRI().getIRIString()))
				mapHandler.toMap(owlObjectProperty, OntologyManagerUtils.generateInternalObjectPropertyObject(ontology, mapHandler.getTopObjectProperty(), owlObjectProperty.getIRI().getIRIString()));
		}

		for (OWLObjectProperty owlObjectProperty : allObjectProperties) {
			Optional<OntologyObjectProperty> optInternalObjectProperty = mapHandler.getInternalObjectProperty(owlObjectProperty, ontology);

			if (optInternalObjectProperty.isPresent()) {
				OntologyObjectProperty internalObjectProperty = optInternalObjectProperty.get();

				List<OWLSubObjectPropertyOfAxiom> axioms = owlOntology.axioms(owlObjectProperty, imports).filter(ax -> ax instanceof OWLSubObjectPropertyOfAxiom)
						.map(ax -> (OWLSubObjectPropertyOfAxiom)ax).collect(Collectors.toList());
				for (OWLSubObjectPropertyOfAxiom axiom : axioms) {
					if (axiom.getSuperProperty().isOWLObjectProperty()) {
						OWLObjectProperty superObjectProperty = axiom.getSuperProperty().asOWLObjectProperty();
						Optional<OntologyObjectProperty> optInternalSuperObjectProperty = mapHandler.getInternalObjectProperty(superObjectProperty, ontology);
						if (optInternalSuperObjectProperty.isPresent()) {
							OntologyObjectProperty internalSuperObjectProperty = optInternalSuperObjectProperty.get();

							internalSuperObjectProperty.getSubObjectProperties().add(internalObjectProperty);
							internalObjectProperty.getSuperObjectProperties().add(internalSuperObjectProperty);

							if (internalObjectProperty.getSuperObjectProperties().contains(mapHandler.getTopObjectProperty())) {
								mapHandler.getTopObjectProperty().getSubObjectProperties().remove(internalObjectProperty);
								internalObjectProperty.getSuperObjectProperties().remove(mapHandler.getTopObjectProperty());
							}
						}
					} else if (internalObjectProperty.getSuperObjectProperties().isEmpty()) {
						mapHandler.getTopObjectProperty().getSubObjectProperties().add(internalObjectProperty);
						internalObjectProperty.getSuperObjectProperties().add(mapHandler.getTopObjectProperty());
					}
				}
			}
		}
	}

	private void extractIndividuals(OWLOntology owlOntology, Ontology ontology, boolean includeImports) {
		Imports imports = Imports.INCLUDED;
		if (!includeImports)
			imports = Imports.EXCLUDED;

		for (OWLNamedIndividual owlIndividual : owlOntology.individualsInSignature(imports).collect(Collectors.toList())) {
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

//	private String getPrefixOfNamespace(String namespace) {
//		for (Entry<String, String> entry : owlPrefixManager.getPrefixName2PrefixMap().entrySet()) {
//			if (entry.getValue().equals(namespace)) {
//				return entry.getKey().substring(0, entry.getKey().length() - 1);
//			}
//		}
//
//		return StringConstants.EMPTY;
//	}

	@Override
	public void saveOntology() throws OntologyManagerException {
		File file = new File(getOntology().getOntologyFile().getPath() + getOntology().getOntologyFile().getFileName());

		try {
			FileOutputStream outputStream = new FileOutputStream(file);
			owlOntologyManager.saveOntology(owlOntology, owlPrefixManager, outputStream);
		} catch (FileNotFoundException e) {
			throw new OntologyManagerException("Error while saving ontology to file '" + file.getName() + "': " + e.getMessage(), e);
		} catch (OWLOntologyStorageException e) {
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

		if (!prefInternal.endsWith(StringConstants.HASHTAG)) {
			prefInternal = prefInternal.concat(StringConstants.HASHTAG);
		}

		if (setDefault) {
			owlPrefixManager.setDefaultPrefix(prefInternal);
			OntologyManagerUtils.generateInternalNamespaceObject(getOntology(), StringConstants.EMPTY, prefInternal);
		} else {
			owlPrefixManager.setPrefix(prefixName, prefInternal);
			OntologyManagerUtils.generateInternalNamespaceObject(getOntology(), prefixName, prefInternal);
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
		// Check, if the import is already existing:
		if (getOntology().getImports().stream().filter(o -> o.getName().equals(importOntologyIRIString)).findFirst().isPresent())
			return;

		IRI importOntologyIRI = IRI.create(importOntologyIRIString);
		OWLImportsDeclaration owlImportDeclaration = owlDataFactory.getOWLImportsDeclaration(importOntologyIRI);
		owlOntologyManager.applyChange(new AddImport(owlOntology, owlImportDeclaration));

		try {
			OWLOntology owlImportedOntology = owlOntologyManager.loadOntology(importOntologyIRI);
			Ontology importedOntology = OntologyManagerUtils.generateInternalOntologyObject(owlImportedOntology.getOntologyID().getOntologyIRI().get().getIRIString(), owlImportedOntology.classesInSignature().count(),
					owlImportedOntology.individualsInSignature().count());
			
			getOntology().getImports().add(importedOntology);
			
			extractOntologyContent(owlImportedOntology, importedOntology, true);
			
		} catch (OWLOntologyCreationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		try {
//			Optional<IOntologyManager> existingManagedOntology = getGlobalOntologyContext().findOntology(importOntologyIRIString);
//			if (existingManagedOntology.isPresent()) {
//				OwlOntologyManager existingOWLMgr = (OwlOntologyManager)existingManagedOntology.get();
//
//				getOntology().getImports().add(existingOWLMgr.getOntology());
//				mapHandler.importFromOtherOwlOntologyManager(existingOWLMgr.mapHandler);
//			} else {
//				OWLOntology owlImportOntology = owlOntologyManager.loadOntology(importOntologyIRI);
//
//				Iterator<IRI> iriIt = owlImportOntology.directImportsDocuments().iterator();
//				while (iriIt.hasNext())
//					System.out.println(MESSAGE_PREFIX + "Direct Import: " + iriIt.next().toString());
//
//				// Ontology o = extractOntologyContent(owlImportOntology);
//			}
//		} catch (OWLOntologyCreationException e) {
//			throw new OntologyManagerException(MESSAGE_PREFIX + "Error while loading ontology '" + importOntologyIRI.toString() + "': " + e.getMessage(), e);
//		}
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

		OWLClass newClass = owlDataFactory.getOWLClass(OntologyManagerUtils.buildFullIRIString(name, prefix), owlPrefixManager);
		OWLAxiom declareNewClass = owlDataFactory.getOWLDeclarationAxiom(newClass);

		owlOntologyManager.addAxiom(owlOntology, declareNewClass);

		clazz = OntologyManagerUtils.generateInternalClassObject(getOntology(), mapHandler.getThingClass(), name, prefix);

		if (!prefix.isEmpty()) {
			clazz.setPrefix(prefix);
		}

		mapHandler.toMap(newClass, clazz);

		return clazz;
	}

	@Override
	public void assignSubClassToSuperClass(OntologyClass subClass, OntologyClass superClass) {
		Optional<OWLClass> optSubOwlClass = mapHandler.getOWLClass(subClass);
		Optional<OWLClass> optSuperOwlClass = mapHandler.getOWLClass(superClass);

		if (optSubOwlClass.isPresent() && optSuperOwlClass.isPresent()) {
			owlOntologyManager.addAxiom(owlOntology, owlDataFactory.getOWLSubClassOfAxiom(optSubOwlClass.get(), optSuperOwlClass.get()));

			subClass.getSuperClasses().add(superClass);
			superClass.getSubClasses().add(subClass);
		}
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

		OWLNamedIndividual owlIndividual = owlDataFactory.getOWLNamedIndividual(OntologyManagerUtils.buildFullIRIString(name, prefix), owlPrefixManager);
		OWLAxiom declareNewIndividual = owlDataFactory.getOWLDeclarationAxiom(owlIndividual);

		owlOntologyManager.addAxiom(owlOntology, declareNewIndividual);

		OntologyIndividual ind = OntologyManagerUtils.generateInternalIndividualObject(getOntology(), name, prefix);

		mapHandler.toMap(owlIndividual, ind);

		return ind;
	}

	@Override
	public void renameEntity(OntologyEntity entity, String newName) {
		Collection<OWLOntology> c = new ArrayList<OWLOntology>();
		c.add(owlOntology);

		OWLEntityRenamer renamer = new OWLEntityRenamer(owlOntologyManager, c);

		Optional<OWLEntity> optRenameEntity = mapHandler.getOWLEntity(entity);
		if (optRenameEntity.isPresent()) {
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

			clazz.getIndividuals().add(individual);
			individual.getTypes().add(clazz);
		}
	}

	@Override
	public OntologyObjectProperty createObjectProperty(final String propertyName, final String prefix) {
		OWLObjectProperty owlProperty = owlDataFactory.getOWLObjectProperty(OntologyManagerUtils.buildFullIRIString(propertyName, prefix), owlPrefixManager);
		OWLDeclarationAxiom owlAxiom = owlDataFactory.getOWLDeclarationAxiom(owlProperty);
		owlOntologyManager.addAxiom(owlOntology, owlAxiom);

		OntologyObjectProperty prop = OntologyManagerUtils.generateInternalObjectPropertyObject(getOntology(), mapHandler.getTopObjectProperty(), propertyName, prefix);
		mapHandler.toMap(owlProperty, prop);

		return prop;
	}

	@Override
	public void assignSubObjectPropertyToSuperObjectProperty(OntologyObjectProperty subProperty, OntologyObjectProperty superProperty) {
		Optional<OWLObjectProperty> optSubOwlObjectProperty = mapHandler.getOWLObjectProperty(subProperty);
		Optional<OWLObjectProperty> optSuperOwlObjectProperty = mapHandler.getOWLObjectProperty(superProperty);

		if (optSubOwlObjectProperty.isPresent() && optSuperOwlObjectProperty.isPresent()) {
			OWLSubObjectPropertyOfAxiom owlSubObjectPropertyAxiom = owlDataFactory.getOWLSubObjectPropertyOfAxiom(optSubOwlObjectProperty.get(), optSuperOwlObjectProperty.get());
			owlOntologyManager.addAxiom(owlOntology, owlSubObjectPropertyAxiom);

			superProperty.getSubObjectProperties().add(subProperty);
			subProperty.getSuperObjectProperties().add(superProperty);
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

			OntologyObjectPropertyAssertion assertion = OntologyManagerUtils.generateInternalObjectPropertyAssertionObject(getOntology(), property, object);
			individual.getObjectPropertyAssertions().add(assertion);

			return assertion;
		} else
			return null;
	}

	@Override
	public OntologyAnnotationProperty createAnnotationProperty(String propertyName, String prefix) {
		OWLAnnotationProperty owlProperty = owlDataFactory.getOWLAnnotationProperty(OntologyManagerUtils.buildFullIRIString(propertyName, prefix), owlPrefixManager);
		OWLDeclarationAxiom owlAxiom = owlDataFactory.getOWLDeclarationAxiom(owlProperty);

		owlOntologyManager.addAxiom(owlOntology, owlAxiom);

		OntologyAnnotationProperty prop = OntologyManagerUtils.generateInternalAnnotationPropertyObject(getOntology(), prefix, propertyName);
		mapHandler.toMap(owlProperty, prop);

		return prop;
	}

	@Override
	public OntologyAnnotation annotateClass(OntologyAnnotationProperty property, String annotationValue, OntologyClass clazz) {
		Optional<OWLAnnotationProperty> optOwlAnnotationProperty = mapHandler.getOWLAnnotationProperty(property);
		Optional<OWLClass> optOwlClass = mapHandler.getOWLClass(clazz);

		if (optOwlAnnotationProperty.isPresent() && optOwlClass.isPresent()) {
			OWLAnnotation owlAnnotation = owlDataFactory.getOWLAnnotation(optOwlAnnotationProperty.get(), owlDataFactory.getOWLLiteral(annotationValue));
			OWLAnnotationAssertionAxiom owlAxiom = owlDataFactory.getOWLAnnotationAssertionAxiom(optOwlClass.get().getIRI(), owlAnnotation);

			owlOntologyManager.addAxiom(owlOntology, owlAxiom);

			OntologyAnnotation annotation = OntologyManagerUtils.generateInternalAnnotationObject(getOntology(), property, annotationValue);
			clazz.getAnnotations().add(annotation);
			mapHandler.toMap(owlAnnotation, annotation);

			return annotation;
		}

		return null;
	}

	@Override
	public void assignClassEquivalence(OntologyClass clazz, OntologyClass equivalentClazz) {
		Optional<OWLClass> optOwlClass = mapHandler.getOWLClass(clazz);
		Optional<OWLClass> optOwlEquivalentClass = mapHandler.getOWLClass(equivalentClazz);
		
		if (optOwlClass.isPresent() && optOwlEquivalentClass.isPresent()) {
			OWLEquivalentClassesAxiom owlAxiom = owlDataFactory.getOWLEquivalentClassesAxiom(optOwlClass.get(), optOwlEquivalentClass.get());
			owlOntologyManager.addAxiom(owlOntology, owlAxiom);
		}
	}

	@Override
	public void assignObjectPropertyEquivalence(OntologyObjectProperty objectProperty, OntologyObjectProperty equivalentObjectProperty) {
		Optional<OWLObjectProperty> optOwlObjectProperty = mapHandler.getOWLObjectProperty(objectProperty);
		Optional<OWLObjectProperty> optOwlEquivalentObjectProperty = mapHandler.getOWLObjectProperty(equivalentObjectProperty);
		
		if (optOwlObjectProperty.isPresent() && optOwlEquivalentObjectProperty.isPresent()) {
			OWLEquivalentObjectPropertiesAxiom owlAxiom = owlDataFactory.getOWLEquivalentObjectPropertiesAxiom(optOwlObjectProperty.get(), optOwlEquivalentObjectProperty.get());
			owlOntologyManager.addAxiom(owlOntology, owlAxiom);
		}
	}
}
