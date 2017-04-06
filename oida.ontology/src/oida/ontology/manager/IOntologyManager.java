/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.manager;

import java.util.Map;
import java.util.stream.Stream;

import oida.ontology.Ontology;
import oida.ontology.OntologyAnnotation;
import oida.ontology.OntologyAnnotationProperty;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyIndividual;
import oida.ontology.OntologyObjectProperty;
import oida.ontology.OntologyObjectPropertyAssertion;
import oida.ontology.manager.context.IGlobalOntologyContext;
import oida.ontologyMgr.OntologyFile;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2016-11-23
 * 
 */
public interface IOntologyManager {
	/**
	 * A setter for a global ontology context object.
	 * @param context The context object.
	 */
	void setGlobalOntologyContext(IGlobalOntologyContext context);
	
	/**
	 * Returns the managed ontology.
	 * @return an Ontology object.
	 */
	Ontology getOntology();
	
	Ontology createOntology(String ontologyIRI) throws OntologyManagerException;
	Ontology loadOntology(OntologyFile ontologyFile) throws OntologyManagerException;
	void saveOntology() throws OntologyManagerException;
	void saveOntology(OntologyFile ontologyFile) throws OntologyManagerException;
	
	void setOntologyFile(OntologyFile ontologyFile);
	OntologyFile getOntologyFile();
	
	void clearOntologyManager();
	
	void initializeReasoner();
	
	void addGlobalIRIToLocalPathMapping(String iri, String localPath);
	
	void addNamespace(String prefix, String namespace);
	void addNamespace(String prefix, String namespace, boolean setDefault);
	
	boolean isNamespaceExisting(String prefix);
	
	String getDefaultNamespace();
	String getNamespace(String prefix);
	
	Map<String, String> getAllNamespaces();
	
	void addImportDeclaration(String importOntologyIRI) throws OntologyManagerException;
	void addImportDeclaration(Ontology importOntology) throws OntologyManagerException;
	
	/**
	 * Creates a class within the default namespace of the ontology.
	 * 
	 * @param name The short class name (without any prefix).
	 * @return An representation object of the created class.
	 */
	OntologyClass createClass(String name);
	
	/**
	 * Creates a class within a namespace. The namespace has to added beforehand with (@link #addPrefix(prefixName, prefix) addPrefix) and is identified by the prefix.
	 * 
	 * @param name The short class name (without any prefix).
	 * @param prefix The prefix of the namespace of the new class (without a colon).
	 * @return An representation object of the created class.
	 */
	OntologyClass createClass(String name, String prefix);
	
	void assignSubClassToSuperClass(OntologyClass subClass, OntologyClass superClass);
	
	OntologyClass createSubClass(String name, OntologyClass superClass);
	OntologyClass createSubClass(String name, String prefix, OntologyClass superClass);

	OntologyIndividual getIndividual(String name);
	OntologyIndividual getIndividual(String name, String prefix);
	Stream<OntologyIndividual> getAllIndividuals();

	OntologyIndividual createIndividual(String name);
	OntologyIndividual createIndividual(String name, String prefix);
	
	void assignIndividualToClass(OntologyIndividual individual, OntologyClass clazz);
	
	OntologyIndividual createIndividualOfClass(String individualName, String className);
	OntologyIndividual createIndividualOfClass(String individualName, String individualPrefix, String className, String classPrefix);
	OntologyIndividual createIndividualOfClass(String individualName, OntologyClass clazz);
	OntologyIndividual createIndividualOfClass(String individualName, String individualPrefix, OntologyClass clazz);
	
	void renameEntity(OntologyEntity entity, String newName);
	
	OntologyClass getClass(String name);
	OntologyClass getClass(String name, String prefix);
	Stream<OntologyClass> getAllClasses();
	
	boolean isClassExisting(String name);
	boolean isClassExisting(String name, String prefix);
	
	OntologyObjectProperty createObjectProperty(String propertyName);
	OntologyObjectProperty createObjectProperty(String propertyName, OntologyClass range);
	OntologyObjectProperty createObjectProperty(String propertyName, OntologyClass range, OntologyClass domain);
	
	OntologyObjectProperty createObjectProperty(String propertyName, String prefix);
	OntologyObjectProperty createObjectProperty(String propertyName, String prefix, OntologyClass range);
	OntologyObjectProperty createObjectProperty(String propertyName, String prefix, OntologyClass range, OntologyClass domain);
	
	void assignSubObjectPropertyToSuperObjectProperty(OntologyObjectProperty subProperty, OntologyObjectProperty superProperty);
	void assignInverseObjectProperty(OntologyObjectProperty property, OntologyObjectProperty inverseProperty);
	
	void makeObjectPropertyFunctional(OntologyObjectProperty property);
	void makeObjectPropertyInverseFunctional(OntologyObjectProperty property);
	void makeObjectPropertyTransitive(OntologyObjectProperty property);
	void makeObjectPropertySymmetric(OntologyObjectProperty property);
	void makeObjectPropertyAsymmetric(OntologyObjectProperty property);
	void makeObjectPropertyReflexive(OntologyObjectProperty property);
	void makeObjectPropertyIrreflexive(OntologyObjectProperty property);
	
	void setObjectPropertyCharacteristics(OntologyObjectProperty property, boolean functional, boolean inverseFunctional, boolean transitive, boolean symmetric, boolean asymmetric, boolean reflexive, boolean irreflexive);
	
	void assignObjectPropertyRange(OntologyObjectProperty property, OntologyClass range);
	void assignObjectPropertyDomain(OntologyObjectProperty property, OntologyClass domain);
	
	OntologyObjectPropertyAssertion createObjectPropertyAssertion(OntologyObjectProperty property, OntologyIndividual individual1, OntologyIndividual individual2);
	
	OntologyAnnotationProperty createAnnotationProperty(String propertyName);
	OntologyAnnotationProperty createAnnotationProperty(String propertyName, String prefix);
	
	OntologyAnnotation annotateClass(OntologyAnnotationProperty property, String annotationValue, OntologyClass clazz);
}
