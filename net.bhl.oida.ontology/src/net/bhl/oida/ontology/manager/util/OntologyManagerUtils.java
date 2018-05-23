/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package net.bhl.oida.ontology.manager.util;

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
import net.bhl.oida.ontology.model.owlontology.OntologyObjectSomeValuesFrom;
import net.bhl.oida.ontology.model.owlontology.OntologySubClassAssignment;
import net.bhl.oida.ontology.model.owlontology.OwlontologyFactory;
import net.bhl.oida.util.constants.StringConstants;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-03
 *
 */
public class OntologyManagerUtils {
    private final static OwlontologyFactory ONTOLOGYFACTORY = OwlontologyFactory.eINSTANCE;
    
    public static Ontology generateInternalOntologyObject(String iri) {
	Ontology newOntology = ONTOLOGYFACTORY.createOntology();
	newOntology.setIri(iri);

	Ontology newLocalOntology = ONTOLOGYFACTORY.createOntology();
	newLocalOntology.setIri(iri);
	newOntology.setLocalOntology(newLocalOntology);

	return newOntology;
    }

    public static OntologyNamespace generateInternalNamespaceObject(Ontology ontology, final String name, final String prefix) {
	OntologyNamespace newNS = ONTOLOGYFACTORY.createOntologyNamespace();
	setOntologyEntityData(newNS, ontology, name, prefix);
	ontology.getNamespaces().add(newNS);

	return newNS;
    }

    public static OntologyClass generateInternalClassObject(final String iri) {
	return generateInternalClassObject(null, null, iri);
    }

    public static OntologyClass generateInternalClassObject(Ontology ontology, OntologyClass superClass, final String iri) {
	return generateInternalClassObject(ontology, superClass, extractNameFromIRI(iri), extractPrefixFromIRI(iri));
    }

    public static OntologyClass generateInternalClassObject(Ontology ontology, OntologyClass superClass, final String name, final String prefix) {
	OntologyClass newClass = ONTOLOGYFACTORY.createOntologyClass();
	setOntologyEntityData(newClass, ontology, name, prefix);

	if (ontology != null)
	    ontology.getClasses().add(newClass);

	if (superClass != null)
	    assignSubClassToSuperClass(newClass, superClass);

	return newClass;
    }

    public static OntologySubClassAssignment assignSubClassToSuperClass(OntologyClass subClass, OntologyClass superClass) {
	OntologySubClassAssignment assignment = ONTOLOGYFACTORY.createOntologySubClassAssignment();
	assignment.setSubClass(subClass);
	assignment.setSuperClass(superClass);

	subClass.getSuperClasses().add(superClass);
	superClass.getSubClasses().add(subClass);

	return assignment;
    }
    
    public static OntologyObjectSomeValuesFrom createSomeValuesFromRestriction(OntologyClass clazz, OntologyObjectProperty objectProperty, OntologyClass valueClass) {
	OntologyObjectSomeValuesFrom someValuesFrom = ONTOLOGYFACTORY.createOntologyObjectSomeValuesFrom();
	
	someValuesFrom.setObjectProperty(objectProperty);
	someValuesFrom.setClazz(valueClass);
	
	clazz.getSomeValuesFrom().add(someValuesFrom);
	
	return someValuesFrom;
    }

    public static void assignSubObjectPropertyToSuperObjectProperty(OntologyObjectProperty subProperty, OntologyObjectProperty superProperty) {
	subProperty.getSuperProperties().add(superProperty);
	superProperty.getSubObjectProperties().add(subProperty);
    }

    public static void assignIndividualToClass(OntologyIndividual individual, OntologyClass clazz) {
	individual.getTypes().add(clazz);
	clazz.getIndividuals().add(individual);
    }

    public static OntologyIndividual generateInternalIndividualObject(Ontology ontology, final String name, final String namespace) {
	OntologyIndividual newIndividual = ONTOLOGYFACTORY.createOntologyIndividual();
	setOntologyEntityData(newIndividual, ontology, name, namespace);
	ontology.getIndividuals().add(newIndividual);

	return newIndividual;
    }

    public static OntologyIndividual generateInternalIndividualObject(Ontology ontology, final String iri) {
	return generateInternalIndividualObject(ontology, extractNameFromIRI(iri), extractPrefixFromIRI(iri));
    }

    public static OntologyObjectProperty generateInternalObjectPropertyObject(Ontology ontology, OntologyObjectProperty superObjectProperty, final String name, final String prefix) {
	OntologyObjectProperty newProperty = ONTOLOGYFACTORY.createOntologyObjectProperty();
	setOntologyEntityData(newProperty, ontology, name, prefix);
	ontology.getObjectProperties().add(newProperty);

	if (superObjectProperty != null)
	    assignSubObjectPropertyToSuperObjectProperty(newProperty, superObjectProperty);

	return newProperty;
    }

    public static OntologyObjectProperty generateInternalObjectPropertyObject(Ontology ontology, OntologyObjectProperty superObjectProperty, final String iri) {
	return generateInternalObjectPropertyObject(ontology, superObjectProperty, extractNameFromIRI(iri), extractPrefixFromIRI(iri));
    }

    public static OntologyAnnotationProperty generateInternalAnnotationPropertyObject(Ontology ontology, final String iri) {
	OntologyAnnotationProperty property = ONTOLOGYFACTORY.createOntologyAnnotationProperty();
	setOntologyEntityData(property, ontology, extractNameFromIRI(iri), extractPrefixFromIRI(iri));
	ontology.getAnnotationProperties().add(property);

	return property;
    }

    public static OntologyAnnotationProperty generateInternalAnnotationPropertyObject(Ontology ontology, final String name, final String namespace) {
	OntologyAnnotationProperty property = ONTOLOGYFACTORY.createOntologyAnnotationProperty();
	setOntologyEntityData(property, ontology, name, namespace);
	ontology.getAnnotationProperties().add(property);

	return property;
    }

    public static OntologyAnnotation generateAnnotationObject(Ontology ontology, OntologyAnnotationProperty property, String value) {
	OntologyAnnotation annotation = ONTOLOGYFACTORY.createOntologyAnnotation();
	annotation.setContainingOntology(ontology);
	annotation.setAnnotationproperty(property);
	annotation.setValue(value);

	return annotation;
    }

    public static OntologyObjectPropertyAssertion generateObjectPropertyAssertionObject(Ontology ontology, OntologyObjectProperty objectProperty, OntologyIndividual individual) {
	OntologyObjectPropertyAssertion assertion = ONTOLOGYFACTORY.createOntologyObjectPropertyAssertion();
	assertion.setContainingOntology(ontology);
	assertion.setObjectProperty(objectProperty);
	assertion.setObject(individual);

	return assertion;
    }

    public static void generateInternalClassAssertionObject(Ontology ontology, String string, String iriString) {
	//OntologyClassAssertion assignment = OntologyFactory.eINSTANCE.createOntologyCl;
    }
    
    public static OntologyClassEquivalence assignClassesEquivalent(Ontology ontology, OntologyClass class1, OntologyClass class2) {
	OntologyClassEquivalence equivalence = ONTOLOGYFACTORY.createOntologyClassEquivalence();
	equivalence.setClass1(class1);
	equivalence.setClass2(class2);
	ontology.getClassEquivalences().add(equivalence);

	class1.getEquivalentClasses().add(class2);
	class2.getEquivalentClasses().add(class1);

	return equivalence;
    }

    public static OntologyObjectPropertyEquivalence assignObjectPropertiesEquivalent(Ontology ontology, OntologyObjectProperty objectProperty1, OntologyObjectProperty objectProperty2) {
	OntologyObjectPropertyEquivalence equivalence = ONTOLOGYFACTORY.createOntologyObjectPropertyEquivalence();
	equivalence.setObjectProperty1(objectProperty1);
	equivalence.setObjectProperty2(objectProperty2);
	ontology.getObjectPropertyEquivalences().add(equivalence);

	objectProperty1.getEquivalentProperties().add(objectProperty2);
	objectProperty2.getEquivalentProperties().add(objectProperty1);

	return equivalence;
    }

    public static void setOntologyEntityData(OntologyEntity entity, Ontology ontology, final String name, final String namespace) {
	if (ontology != null)
	    entity.setContainingOntology(ontology);

	entity.setIri(buildFullIRIString(name, namespace));
	entity.setName(name);
	entity.setPrefix(namespace);
    }

    public static void changeOntologyEntityName(OntologyEntity entity, String newName) {
	entity.setName(newName);
	entity.setIri(buildFullIRIString(newName, entity.getPrefix()));
    }

    public static String extractPrefixFromIRI(String iri) {
	if (iri.contains(StringConstants.HASHTAG))
	    return iri.substring(0, iri.lastIndexOf(StringConstants.HASHTAG));

	if (iri.contains(StringConstants.SLASH))
	    return iri.substring(0, iri.lastIndexOf(StringConstants.SLASH));

	return iri;
    }

    public static String extractNameFromIRI(String iri) {
	if (iri.contains(StringConstants.HASHTAG))
	    return iri.substring(iri.lastIndexOf(StringConstants.HASHTAG) + 1);

	if (iri.contains(StringConstants.SLASH))
	    return iri.substring(iri.lastIndexOf(StringConstants.SLASH) + 1);

	return iri;
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
    public static String buildFullIRIString(final String name, final String prefix) {
	if (prefix.isEmpty())
	    return name;
	else
	    return prefix.concat(StringConstants.HASHTAG + name);
    }
}
