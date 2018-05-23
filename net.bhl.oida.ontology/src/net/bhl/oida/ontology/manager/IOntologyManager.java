/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package net.bhl.oida.ontology.manager;

import java.util.Optional;
import java.util.stream.Stream;

import net.bhl.oida.ontology.manager.context.IGlobalOntologyContext;
import net.bhl.oida.ontology.model.ontologymgr.LocalOntologyMetaInfo;
import net.bhl.oida.ontology.model.ontologymgr.OntologyMetaInfo;
import net.bhl.oida.ontology.model.owlontology.Ontology;
import net.bhl.oida.ontology.model.owlontology.OntologyAnnotation;
import net.bhl.oida.ontology.model.owlontology.OntologyAnnotationProperty;
import net.bhl.oida.ontology.model.owlontology.OntologyClass;
import net.bhl.oida.ontology.model.owlontology.OntologyClassEquivalence;
import net.bhl.oida.ontology.model.owlontology.OntologyEntity;
import net.bhl.oida.ontology.model.owlontology.OntologyIndividual;
import net.bhl.oida.ontology.model.owlontology.OntologyObjectProperty;
import net.bhl.oida.ontology.model.owlontology.OntologyObjectPropertyAssertion;
import net.bhl.oida.ontology.model.owlontology.OntologyObjectPropertyEquivalence;

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
     * @param context
     *            The context object.
     */
    void setGlobalOntologyContext(IGlobalOntologyContext context);

    Ontology createLocalOntology(LocalOntologyMetaInfo localOntologyMetaInfo) throws OntologyManagerException;

    Ontology loadLocalOntology(LocalOntologyMetaInfo localOntologyMetaInfo) throws OntologyManagerException;

    Ontology loadOntology(OntologyMetaInfo ontologyMetaInfo) throws OntologyManagerException;

    void deleteLocalOntology() throws OntologyManagerException;

    void saveLocalOntology() throws OntologyManagerException;

    void refreshOntologyRepresentation(boolean buildLocalRepresentation);

    OntologyMetaInfo getMetaInfo();
    void setMetaInfo (OntologyMetaInfo metaInfo);
    
    /**
     * Returns the managed ontology resource.
     * 
     * @return an Ontology object.
     */
    Ontology getOntology();

    String getDefaultNamespace();

    void addImportDeclaration(final String iri) throws OntologyManagerException;

    void addImportDeclaration(Ontology importOntology) throws OntologyManagerException;

    void renameEntity(OntologyEntity entity, final String newName);

    // Class Operations
    /**
     * Creates a class within the default namespace of the ontology.
     * 
     * @param name
     *            The short class name (without any namespace).
     * @return An representation object of the created class.
     */
    OntologyClass createClass(final String name);

    /**
     * Creates a class within a specified namespace.
     * 
     * @param name
     *            The short class name (without any namespace).
     * @param namespace
     *            The namespace of the new class (without a colon).
     * @return An representation object of the created class.
     */
    OntologyClass createClass(final String name, final String namespace);

    /**
     * Assigns a class to a specified super class.
     * 
     * @param subClass
     *            the sub class object.
     * @param superClass
     *            the super class object.
     */
    void assignSubClassToSuperClass(OntologyClass subClass, OntologyClass superClass);

    OntologyClass createSubClass(final String name, OntologyClass superClass);

    OntologyClass createSubClass(final String name, final String namespace, OntologyClass superClass);

    Optional<OntologyClassEquivalence> assignClassEquivalence(OntologyClass clazz, OntologyClass equivalentClazz);

    void removeClassEquivalence(OntologyClassEquivalence equivalence);

    Stream<OntologyClassEquivalence> getAllClassEquivalences();

    boolean isClassExisting(final String name);

    boolean isClassExisting(final String name, final String namespace);

    Optional<OntologyClass> getClass(final String iri);

    Optional<OntologyClass> getClass(final String name, final String namespace);

    Stream<OntologyClass> getAllClasses();

    void removeClass(final String iri);

    void removeClass(final String iri, final String namespace);

    // Individual Operations
    OntologyIndividual createIndividual(final String name);

    OntologyIndividual createIndividual(final String name, final String namespace);

    void assignIndividualToClass(OntologyIndividual individual, OntologyClass clazz);

    OntologyIndividual createIndividualOfClass(final String individualName, OntologyClass clazz);

    OntologyIndividual createIndividualOfClass(final String individualName, final String individualNamespace, OntologyClass clazz);

    Optional<OntologyIndividual> getIndividual(final String iri);

    Optional<OntologyIndividual> getIndividual(final String name, final String namespace);

    Stream<OntologyIndividual> getAllIndividuals();

    void removeIndividual(final String iri);

    void removeIndividual(final String iri, final String namespace);

    // Object Property Operations
    OntologyObjectProperty createObjectProperty(final String propertyName);

    OntologyObjectProperty createObjectProperty(final String propertyName, final String namespace);

    OntologyObjectProperty createObjectProperty(final String propertyName, final String namespace, OntologyClass domain);

    OntologyObjectProperty createObjectProperty(final String propertyName, final String namespace, OntologyClass domain, OntologyClass range);

    OntologyObjectProperty createObjectProperty(final String propertyName, OntologyClass domain);

    OntologyObjectProperty createObjectProperty(final String propertyName, OntologyClass domain, OntologyClass range);

    void assignSubObjectPropertyToSuperObjectProperty(OntologyObjectProperty subProperty, OntologyObjectProperty superProperty);

    Optional<OntologyObjectPropertyEquivalence> assignObjectPropertyEquivalence(OntologyObjectProperty objectProperty, OntologyObjectProperty equivalentObjectProperty);

    void removeObjectPropertyEquivalence(OntologyObjectPropertyEquivalence equivalence);

    Stream<OntologyObjectPropertyEquivalence> getAllObjectPropertyEquivalences();

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

    OntologyObjectPropertyAssertion createObjectPropertyAssertion(OntologyObjectProperty property, OntologyIndividual individual1, OntologyIndividual individual2);

    Optional<OntologyObjectProperty> getObjectProperty(final String iri);

    Optional<OntologyObjectProperty> getObjectProperty(final String name, final String namespace);

    Stream<OntologyObjectProperty> getAllObjectProperties();

    void removeObjectProperty(final String iri);

    void removeObjectProperty(final String iri, final String namespace);

    // Annotation Operations
    OntologyAnnotationProperty createAnnotationProperty(final String propertyName);

    OntologyAnnotationProperty createAnnotationProperty(final String propertyName, final String namespace);

    Optional<OntologyAnnotation> annotateClass(OntologyAnnotationProperty property, final String annotationValue, OntologyClass clazz);

    Optional<OntologyAnnotation> annotateIndividual(OntologyIndividual individual, OntologyAnnotationProperty property, final String annotationValue);
}
