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
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyFactory;
import oida.ontology.OntologyIndividual;
import oida.ontology.OntologyNamespace;
import oida.ontology.OntologyObjectProperty;
import oida.ontology.OntologyObjectPropertyAssertion;
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
		newOntology.setNrOfClasses(nrOfClasses);
		newOntology.setNrOfIndividuals(nrOfIndividuals);
		newOntology.getImports();
		return newOntology;
	}

	public static OntologyNamespace generateInternalNamespaceObject(Ontology ontology, final String name, final String prefix) {
		OntologyNamespace newNS = OntologyFactory.eINSTANCE.createOntologyNamespace();
		setOntologyEntityData(newNS, ontology, name, prefix);
		ontology.getNamespaces().add(newNS);

		return newNS;
	}

	public static OntologyClass generateInternalClassObject(Ontology ontology, OntologyClass superClass, final String name, final String prefix) {
		OntologyClass newClass = OntologyFactory.eINSTANCE.createOntologyClass();
		setOntologyEntityData(newClass, ontology, name, prefix);
		ontology.getClasses().add(newClass);
		
		if (superClass != null) {
			superClass.getSubClasses().add(newClass);
			newClass.getSuperClasses().add(superClass);
		}

		return newClass;
	}
	
	public static OntologyClass generateInternalClassObject(Ontology ontology, OntologyClass superClass, final String iri) {
		return generateInternalClassObject(ontology, superClass, extractNameFromIRI(iri), extractPrefixFromIRI(iri));
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
			newProperty.getSuperObjectProperties().add(superObjectProperty);
		}
		
		return newProperty;
	}
	
	public static OntologyObjectProperty generateInternalObjectPropertyObject(Ontology ontology, OntologyObjectProperty superObjectProperty, final String iri) {
		return generateInternalObjectPropertyObject(ontology, superObjectProperty, extractNameFromIRI(iri), extractPrefixFromIRI(iri));
	}

	public static OntologyAnnotationProperty generateInternalAnnotationPropertyObject(Ontology ontology, final String name, final String prefix) {
		OntologyAnnotationProperty property = OntologyFactory.eINSTANCE.createOntologyAnnotationProperty();
		setOntologyEntityData(property, ontology, name, prefix);

		return property;
	}

	public static OntologyAnnotation generateInternalAnnotationObject(Ontology ontology, OntologyAnnotationProperty property, String value) {
		OntologyAnnotation annotation = OntologyFactory.eINSTANCE.createOntologyAnnotation();
		annotation.setContainingOntology(ontology);
		annotation.setAnnotationproperty(property);
		annotation.setValue(value);

		return annotation;
	}
	
	public static OntologyObjectPropertyAssertion generateInternalObjectPropertyAssertionObject(Ontology ontology, OntologyObjectProperty objectProperty, OntologyIndividual individual) {
		OntologyObjectPropertyAssertion assertion = OntologyFactory.eINSTANCE.createOntologyObjectPropertyAssertion();
		assertion.setContainingOntology(ontology);
		assertion.setObjectProperty(objectProperty);
		assertion.setObject(individual);
		
		return assertion;
	}

	public static void setOntologyEntityData(OntologyEntity entity, Ontology ontology, String name, String prefix) {
		entity.setContainingOntology(ontology);
		entity.setIri(buildFullIRIString(name, prefix));
		entity.setName(name);
		entity.setPrefix(prefix);
	}

	public static String extractPrefixFromIRI(String iri) {
		return iri.substring(0, iri.lastIndexOf(StringConstants.COLON));
	}

	public static String extractNameFromIRI(String iri) {
		return iri.substring(iri.lastIndexOf(StringConstants.COLON) + 1);
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
			return prefix.concat(StringConstants.COLON + name);
	}
}
