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

	public static OntologyNamespace generateInternalNamespaceObject(Ontology ontology, String prefix, String nsName) {
		OntologyNamespace newNS = OntologyFactory.eINSTANCE.createOntologyNamespace();
		setOntologyEntityData(newNS, ontology, nsName, prefix);
		ontology.getNamespaces().add(newNS);

		return newNS;
	}

	public static OntologyClass generateInternalClassObject(Ontology ontology, OntologyClass superClass, String prefix, String className) {
		OntologyClass newClass = OntologyFactory.eINSTANCE.createOntologyClass();
		setOntologyEntityData(newClass, ontology, className, prefix);
		ontology.getClasses().add(newClass);
		
		if (superClass != null) {
			superClass.getSubClasses().add(newClass);
			newClass.getSuperClasses().add(superClass);
		}

		return newClass;
	}

	public static OntologyIndividual generateInternalIndividualObject(Ontology ontology, String prefix, String individualName) {
		OntologyIndividual newIndividual = OntologyFactory.eINSTANCE.createOntologyIndividual();
		setOntologyEntityData(newIndividual, ontology, individualName, prefix);
		ontology.getIndividuals().add(newIndividual);

		return newIndividual;
	}

	public static OntologyObjectProperty generateInternalObjectPropertyObject(Ontology ontology, OntologyObjectProperty superObjectProperty, String prefix, String propertyName) {
		OntologyObjectProperty newProperty = OntologyFactory.eINSTANCE.createOntologyObjectProperty();
		setOntologyEntityData(newProperty, ontology, propertyName, prefix);
		ontology.getObjectProperties().add(newProperty);

		if (superObjectProperty != null) {
			superObjectProperty.getSubObjectProperties().add(newProperty);
			newProperty.getSuperObjectProperties().add(superObjectProperty);
		}
		
		return newProperty;
	}

	public static OntologyAnnotationProperty generateInternalAnnotationPropertyObject(Ontology ontology, String prefix, String propertyName) {
		OntologyAnnotationProperty property = OntologyFactory.eINSTANCE.createOntologyAnnotationProperty();
		setOntologyEntityData(property, ontology, propertyName, prefix);

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
		entity.setName(name);
		entity.setPrefix(prefix);
	}
}
