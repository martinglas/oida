/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.manager;

import java.util.Optional;
import java.util.stream.Stream;

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
import oida.ontology.manager.context.IGlobalOntologyContext;
import oida.ontologyMgr.LocalOntologyMetaInfo;
import oida.ontologyMgr.OntologyMetaInfo;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2016-11-23
 * 
 */
public interface IOntologyManager {
	
	void initializeOntologyManager();

	void initializeReasoner();
	
	/**
	 * A setter for a global ontology context object.
	 * 
	 * @param context The context object.
	 */
	void setGlobalOntologyContext(IGlobalOntologyContext context);

	/**
	 * Returns the managed ontology resource.
	 * 
	 * @return an Ontology object.
	 */
	Ontology getOntology();
	
	String getDefaultNamespace();
	
	Optional<OntologyMetaInfo> getOntologyMetaInfo();
	
	void setOntologyMetaInfo(OntologyMetaInfo ontologyMetaInfo);

	Ontology createOntology(LocalOntologyMetaInfo localOntologyMetaInfo) throws OntologyManagerException;
	
	Ontology loadOntology(OntologyMetaInfo ontologyMetaInfo) throws OntologyManagerException;
	
	Ontology loadLocalOntology(LocalOntologyMetaInfo localOntologyMetaInfo) throws OntologyManagerException;
	
	void refreshOntologyRepresentation(boolean buildLocalRepresentation);

	void saveOntology() throws OntologyManagerException;

	void addImportDeclaration(final String iri) throws OntologyManagerException;

	void addImportDeclaration(Ontology importOntology) throws OntologyManagerException;

	// Class Operations
	
	/**
	 * Creates a class within the default namespace of the ontology.
	 * 
	 * @param name The short class name (without any namespace).
	 * @return An representation object of the created class.
	 */
	OntologyClass createClass(String name);

	/**
	 * Creates a class within a specified namespace.
	 * 
	 * @param name The short class name (without any namespace).
	 * @param namespace The namespace of the new class (without a colon).
	 * @return An representation object of the created class.
	 */
	OntologyClass createClass(final String name, final String namespace);

	/**
	 * Assigns a class to a specified super class.
	 * @param subClass the sub class object.
	 * @param superClass the super class object.
	 */
	void assignSubClassToSuperClass(OntologyClass subClass, OntologyClass superClass);

	OntologyClass createSubClass(final String name, OntologyClass superClass);

	OntologyClass createSubClass(final String name, final String namespace, OntologyClass superClass);

	Stream<OntologyClass> getAllClasses();

	boolean isClassExisting(final String name);

	boolean isClassExisting(final String name, final String namespace);
	
	Optional<OntologyClass> getClass(final String iri);

	Optional<OntologyClass> getClass(final String name, final String namespace);
	
	// Individual Operations
	
	OntologyIndividual createIndividual(final String name);

	OntologyIndividual createIndividual(final String name, final String namespace);

	void assignIndividualToClass(OntologyIndividual individual, OntologyClass clazz);
	
	OntologyIndividual createIndividualOfClass(String individualName, String className);

	OntologyIndividual createIndividualOfClass(String individualName, OntologyClass clazz);

	OntologyIndividual createIndividualOfClass(String individualName, String individualNamespace, OntologyClass clazz);
	
	Stream<OntologyIndividual> getAllIndividuals();
	
	Optional<OntologyIndividual> getIndividual(final String iri);

	Optional<OntologyIndividual> getIndividual(final String name, final String namespace);

	// Object Property Operations
	
	OntologyObjectProperty createObjectProperty(String propertyName);

	OntologyObjectProperty createObjectProperty(String propertyName, OntologyClass domain);

	OntologyObjectProperty createObjectProperty(String propertyName, OntologyClass domain, OntologyClass range);

	OntologyObjectProperty createObjectProperty(String propertyName, String namespace);

	OntologyObjectProperty createObjectProperty(String propertyName, String namespace, OntologyClass domain);

	OntologyObjectProperty createObjectProperty(String propertyName, String namespace, OntologyClass domain, OntologyClass range);
	
	void assignSubObjectPropertyToSuperObjectProperty(OntologyObjectProperty subProperty, OntologyObjectProperty superProperty);
	
	void assignInverseObjectProperty(OntologyObjectProperty property, OntologyObjectProperty inverseProperty);
	
	void makeObjectPropertyFunctional(OntologyObjectProperty property);

	void makeObjectPropertyInverseFunctional(OntologyObjectProperty property);

	void makeObjectPropertyTransitive(OntologyObjectProperty property);

	void makeObjectPropertySymmetric(OntologyObjectProperty property);

	void makeObjectPropertyAsymmetric(OntologyObjectProperty property);

	void makeObjectPropertyReflexive(OntologyObjectProperty property);

	void makeObjectPropertyIrreflexive(OntologyObjectProperty property);

	void setObjectPropertyCharacteristics(OntologyObjectProperty property, boolean functional, boolean inverseFunctional, boolean transitive, boolean symmetric, boolean asymmetric, boolean reflexive,
			boolean irreflexive);

	void assignObjectPropertyRange(OntologyObjectProperty property, OntologyClass range);

	void assignObjectPropertyDomain(OntologyObjectProperty property, OntologyClass domain);

	Optional<OntologyObjectProperty> getObjectProperty(final String iri);

	Optional<OntologyObjectProperty> getObjectProperty(final String name, final String namespace);

	OntologyObjectPropertyAssertion createObjectPropertyAssertion(OntologyObjectProperty property, OntologyIndividual individual1, OntologyIndividual individual2);
	
	// Annotation Operations
	
	OntologyAnnotationProperty createAnnotationProperty(String propertyName);

	OntologyAnnotationProperty createAnnotationProperty(String propertyName, String namespace);

	Optional<OntologyAnnotation> annotateClass(OntologyAnnotationProperty property, String annotationValue, OntologyClass clazz);

	Optional<OntologyAnnotation> annotateIndividual(OntologyIndividual individual, OntologyAnnotationProperty property, String annotationValue);

	
	void renameEntity(OntologyEntity entity, String newName);
	
	Optional<OntologyClassEquivalence> assignClassEquivalence(OntologyClass clazz, OntologyClass equivalentClazz);
	
	void removeClassEquivalence(OntologyClassEquivalence equivalence);

	Optional<OntologyObjectPropertyEquivalence> assignObjectPropertyEquivalence(OntologyObjectProperty objectProperty, OntologyObjectProperty equivalentObjectProperty);
	
	void removeObjectPropertyEquivalence(OntologyObjectPropertyEquivalence equivalence);

	Stream<OntologyClassEquivalence> getAllClassEquivalences();

	Stream<OntologyObjectPropertyEquivalence> getAllObjectPropertyEquivalences();
}
