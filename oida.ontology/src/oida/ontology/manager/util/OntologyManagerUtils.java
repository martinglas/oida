/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.manager.util;

import oida.ontology.Ontology;
import oida.ontology.OntologyAnnotation;
import oida.ontology.OntologyAnnotationProperty;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyClassEquivalence;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyFactory;
import oida.ontology.OntologyIndividual;
import oida.ontology.OntologyNamespace;
import oida.ontology.OntologyObjectProperty;
import oida.ontology.OntologyObjectPropertyAssertion;
import oida.ontology.OntologyObjectPropertyEquivalence;
import oida.util.constants.StringConstants;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-04-03
 *
 */
public class OntologyManagerUtils {
	public static Ontology generateInternalOntologyObject(String name, long nrOfClasses, long nrOfIndividuals) {
		Ontology newOntology = OntologyFactory.eINSTANCE.createOntology();
		newOntology.setName(name);
		newOntology.setIri(name);
		newOntology.setNrOfClasses(nrOfClasses);
		newOntology.setNrOfIndividuals(nrOfIndividuals);
		return newOntology;
	}

	public static OntologyNamespace generateInternalNamespaceObject(Ontology ontology, final String name, final String prefix) {
		OntologyNamespace newNS = OntologyFactory.eINSTANCE.createOntologyNamespace();
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
		OntologyClass newClass = OntologyFactory.eINSTANCE.createOntologyClass();
		setOntologyEntityData(newClass, ontology, name, prefix);
		
		if (ontology != null)
			ontology.getClasses().add(newClass);
		
		if (superClass != null) {
			superClass.getSubClasses().add(newClass);
			newClass.getSuperClasses().add(superClass);
		}

		return newClass;
	}

	public static OntologyIndividual generateInternalIndividualObject(Ontology ontology, final String name, final String prefix) {
		OntologyIndividual newIndividual = OntologyFactory.eINSTANCE.createOntologyIndividual();
		setOntologyEntityData(newIndividual, ontology, name, prefix);
		ontology.getIndividuals().add(newIndividual);

		return newIndividual;
	}
	
	public static OntologyIndividual generateInternalIndividualObject(Ontology ontology, final String iri) {
		return generateInternalIndividualObject(ontology, extractNameFromIRI(iri), extractPrefixFromIRI(iri));
	}

	public static OntologyObjectProperty generateInternalObjectPropertyObject(Ontology ontology, OntologyObjectProperty superObjectProperty, final String name, final String prefix) {
		OntologyObjectProperty newProperty = OntologyFactory.eINSTANCE.createOntologyObjectProperty();
		setOntologyEntityData(newProperty, ontology, name, prefix);
		ontology.getObjectProperties().add(newProperty);

		if (superObjectProperty != null) {
			superObjectProperty.getSubObjectProperties().add(newProperty);
			newProperty.getSuperProperties().add(superObjectProperty);
		}
		
		return newProperty;
	}
	
	public static OntologyObjectProperty generateInternalObjectPropertyObject(Ontology ontology, OntologyObjectProperty superObjectProperty, final String iri) {
		return generateInternalObjectPropertyObject(ontology, superObjectProperty, extractNameFromIRI(iri), extractPrefixFromIRI(iri));
	}

	public static OntologyAnnotationProperty generateInternalAnnotationPropertyObject(Ontology ontology, final String iri) {
		OntologyAnnotationProperty property = OntologyFactory.eINSTANCE.createOntologyAnnotationProperty();
		setOntologyEntityData(property, ontology, extractNameFromIRI(iri), extractPrefixFromIRI(iri));
		ontology.getAnnotationProperties().add(property);

		return property;
	}
	
	public static OntologyAnnotationProperty generateInternalAnnotationPropertyObject(Ontology ontology, final String name, final String prefix) {
		OntologyAnnotationProperty property = OntologyFactory.eINSTANCE.createOntologyAnnotationProperty();
		setOntologyEntityData(property, ontology, name, prefix);
		ontology.getAnnotationProperties().add(property);

		return property;
	}

	public static OntologyAnnotation generateAnnotationObject(Ontology ontology, OntologyAnnotationProperty property, String value) {
		OntologyAnnotation annotation = OntologyFactory.eINSTANCE.createOntologyAnnotation();
		annotation.setContainingOntology(ontology);
		annotation.setAnnotationproperty(property);
		annotation.setValue(value);

		return annotation;
	}
	
	public static OntologyObjectPropertyAssertion generateObjectPropertyAssertionObject(Ontology ontology, OntologyObjectProperty objectProperty, OntologyIndividual individual) {
		OntologyObjectPropertyAssertion assertion = OntologyFactory.eINSTANCE.createOntologyObjectPropertyAssertion();
		assertion.setContainingOntology(ontology);
		assertion.setObjectProperty(objectProperty);
		assertion.setObject(individual);
		
		return assertion;
	}
	
	public static OntologyClassEquivalence assignClassesEquivalent(Ontology ontology, OntologyClass class1, OntologyClass class2) {
		OntologyClassEquivalence equivalence = OntologyFactory.eINSTANCE.createOntologyClassEquivalence();
		equivalence.setClass1(class1);
		equivalence.setClass2(class2);
		ontology.getClassEquivalences().add(equivalence);
		
		class1.getEquivalentClasses().add(class2);
		class2.getEquivalentClasses().add(class1);
		
		return equivalence;
	}
	
	public static OntologyObjectPropertyEquivalence assignObjectPropertiesEquivalent(Ontology ontology, OntologyObjectProperty objectProperty1, OntologyObjectProperty objectProperty2) {
		OntologyObjectPropertyEquivalence equivalence = OntologyFactory.eINSTANCE.createOntologyObjectPropertyEquivalence();
		equivalence.setObjectProperty1(objectProperty1);
		equivalence.setObjectProperty2(objectProperty2);
		ontology.getObjectPropertyEquivalences().add(equivalence);
		
		objectProperty1.getEquivalentProperties().add(objectProperty2);
		objectProperty2.getEquivalentProperties().add(objectProperty1);
		
		return equivalence;
	}

	public static void setOntologyEntityData(OntologyEntity entity, Ontology ontology, String name, String prefix) {
		if (ontology != null)
			entity.setContainingOntology(ontology);
		
		entity.setIri(buildFullIRIString(name, prefix));
		entity.setName(name);
		entity.setPrefix(prefix);
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
	 * Builds the full name of an entity, consisting of an optional prefix, followed by a colon and the short name.
	 * 
	 * @param name The name of the entity.
	 * @param prefix The prefix of the entity or an empty string.
	 * @return The concatinated full name string.
	 */
	public static String buildFullIRIString(final String name, final String prefix) {
		if (prefix.isEmpty())
			return name;
		else
			return prefix.concat(StringConstants.HASHTAG + name);
	}
}
