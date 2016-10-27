/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.transformation.modelgenerator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.Ontology;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import oida.model.integration.transformation.typemapping.DatatypeProvider;
import oida.model.integration.transformation.util.TransformatorHelper;
import oida.ontology.model.OntologyModel;



/**
 * This is a helper class for the owl to ecore generator.
 * 
 * @author Martin Glas
 * 
 */
public final class GeneratorHelper {

	private GeneratorHelper() {

	}

	/**
	 * This method generates plain Ecore classes from a list of ontology
	 * classes.
	 * 
	 * @param ontologyClasses
	 *            the list of ontology classes
	 * @param metamodel
	 *            the target metamodel object
	 */
	public static void generateClasses(List<OntClass> ontologyClasses,
			Metamodel metamodel) {
		for (OntClass ontologyClass : ontologyClasses) {
			metamodel.addClass(MetamodelHelper.createClass(
					ontologyClass.getLocalName(), new ArrayList<EAttribute>()));
		}
	}

	/**
	 * This method generates instance relations between classes based on their
	 * subclass relations in the ontology into a given metamodel where the
	 * classes already exist.
	 * 
	 * @param ontologyClasses
	 *            the ontology classes
	 * @param metamodel
	 *            the metamodel where the plain classes already exist
	 */
	public static void generateInstanceRelations(
			List<OntClass> ontologyClasses, Metamodel metamodel) {
		for (OntClass ontologyClass : ontologyClasses) {
			if (ontologyClass.getSuperClass() != null) {
				String className = ontologyClass.getLocalName();

				List<OntClass> subclasses = new ArrayList<OntClass>(
						ontologyClass.listSubClasses(true).toList());

				if (subclasses.size() > 0) {

					EClass superclass = (EClass) metamodel.getBasePackage()
							.getEClassifier(className);

					for (OntClass subOntologyClass : subclasses) {
						String subclassName = subOntologyClass.getLocalName();
						if (ontologyClasses.contains(subOntologyClass)) {
							EClass subclass = (EClass) metamodel
									.getBasePackage().getEClassifier(
											subclassName);

							subclass.getESuperTypes().add(superclass);
						}

					}
				}

			}

		}

	}

	/**
	 * This method generates reference relations between Ecore classes existing
	 * in a given metamodel based on the object properties in an ontology.
	 * 
	 * @param ontologyClasses
	 *            the ontology classes which are in the scope
	 * @param ontologyModel
	 *            the ontology model
	 * @param metamodel
	 *            the target metamodel where the Ecore classes exist
	 */
	public static void generateReferences(List<OntClass> ontologyClasses,
			OntologyModel ontologyModel, Metamodel metamodel) {
		List<ObjectProperty> objectProperties = ontologyModel
				.getObjectProperties();
		for (ObjectProperty objectProperty : objectProperties) {
			if (!objectProperty.listDomain().toList().isEmpty()
					&& !objectProperty.listRange().toList().isEmpty()) {
				URI domainClassURI = URI.createURI(objectProperty.getDomain()

				.getURI());
				URI rangeClassURI = URI.createURI(objectProperty.getRange()
						.getURI());

				OntClass domainOntologyClass = ontologyModel
						.getOntologyClass(domainClassURI);
				OntClass rangeOntologyClass = ontologyModel
						.getOntologyClass(rangeClassURI);

				if ((ontologyClasses.contains(domainOntologyClass))
						&& (ontologyClasses.contains(rangeOntologyClass))) {
					String domainClassName = domainOntologyClass.getLocalName();
					String rangeClassName = rangeOntologyClass.getLocalName();

					EClass domainClass = (EClass) metamodel.getBasePackage()
							.getEClassifier(domainClassName);
					EClass rangeClass = (EClass) metamodel.getBasePackage()
							.getEClassifier(rangeClassName);

					EReference newReference;
					String referenceName = objectProperty.getLocalName();
					if ((objectProperty.getSuperProperty() != null)
							&& (objectProperty.getSuperProperty()
									.getLocalName().equals("hasPart_directly"))) {

						newReference = MetamodelHelper.createContainment(
								referenceName, rangeClass, (int) -1);
					} else {
						newReference = MetamodelHelper.createReference(
								referenceName, rangeClass, (int) -1);
						domainClass.getEStructuralFeatures().add(newReference);
					}
					domainClass.getEStructuralFeatures().add(newReference);

				}

			}
		}

	}

	/**
	 * This method generates reference relations between Ecore classes existing
	 * in a given metamodel based on the object properties in an ontology.
	 * 
	 * @param ontologyClasses
	 *            the ontology classes which are in the scope
	 * @param ontologyModel
	 *            the ontology model
	 * @param metamodel
	 *            the target metamodel where the Ecore classes exist
	 */
	public static void generateAttributes(List<OntClass> ontologyClasses,
			OntologyModel ontologyModel, Metamodel metamodel) {
		List<DatatypeProperty> datatypeProperties = ontologyModel
				.getDatatypeProperties();
		for (DatatypeProperty datatypeProperty : datatypeProperties) {

			if ((datatypeProperty.getRange() != null)
					&& (datatypeProperty.getDomain() != null)) {
				URI domainClassURI = URI.createURI(datatypeProperty.getDomain()
						.getURI());
				URI rangeURI = URI.createURI(datatypeProperty.getRange()
						.getURI());

				OntClass domainOntologyClass = ontologyModel
						.getOntologyClass(domainClassURI);
				String typeName = datatypeProperty.getRange().getLocalName();

				EDataType attributeType = DatatypeProvider
						.getAttributeType(typeName);
				if ((ontologyClasses.contains(domainOntologyClass))
						&& (attributeType != null)) {
					String domainClassName = domainOntologyClass.getLocalName();

					EClass domainClass = (EClass) metamodel.getBasePackage()
							.getEClassifier(domainClassName);

					String attributeName = datatypeProperty.getLocalName();

					EAttribute newAttribute = MetamodelHelper.createAttribute(
							attributeName, attributeType);

					domainClass.getEStructuralFeatures().add(newAttribute);

				}

			} else {
				System.out.println("Could not generate attribute "
						+ datatypeProperty.getLocalName()
						+ "It is underdefined.");
			}

		}

	}

	public static Metamodel generateMetamodel(Ontology ontology) {
		OntologyModel ontologyModel = OntologyModel.getInstance();
		List<OntClass> classesWithInstances = ontologyModel
				.getClassesWithInstances(ontology);
		// String packageName = ontologyModel.getOntologiesURIs().get(0)
		// .getLocalName().replace(".owl", "");
		String packageName = ontology.getLocalName().replace(".owl", "");

		EPackage basePackage = MetamodelHelper.createPackage(packageName,
				URI.createURI(ontology.getURI()));

		Metamodel metamodel = new Metamodel(basePackage);
		// Create classes
		GeneratorHelper.generateClasses(classesWithInstances, metamodel);

		// create Inheritance Relations between classes
		GeneratorHelper.generateInstanceRelations(classesWithInstances,
				metamodel);

		// Create references
		GeneratorHelper.generateReferences(classesWithInstances, ontologyModel,
				metamodel);

		// Create attributes
		GeneratorHelper.generateAttributes(classesWithInstances, ontologyModel,
				metamodel);
		return metamodel;
	}

	public static Metamodel generateReferenceMetamodel() {
		OntologyModel ontologyModel = OntologyModel.getInstance();
		Ontology referenceOntology = ontologyModel
				.getOntology(TransformatorHelper.getReferenceOntologyURI());
		if (referenceOntology != null) {
			return generateMetamodel(referenceOntology);

		}
		// TODO Create exception
		return null;
	}

	public static void saveToXMI(EObject rootEObject, URL modelFileURL) {
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("xmi", new XMIResourceFactoryImpl());

		// Obtain a new resource set
		ResourceSet resourceSet = new ResourceSetImpl();

		// Create a resource
		Resource resource = resourceSet.createResource(URI
				.createURI(modelFileURL.toString()));
		// Get the first model element and cast it to the right type, in my
		// example everything is hierarchical included in this first node
		resource.getContents().add(rootEObject);

		// Now save the content.
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void saveToXMI(List<EObject> eObjects, URL modelFileURL) {
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("xmi", new XMIResourceFactoryImpl());

		// Obtain a new resource set
		ResourceSet resourceSet = new ResourceSetImpl();

		// Create a resource
		Resource resource = resourceSet.createResource(URI
				.createURI(modelFileURL.toString()));
		// Get the first model element and cast it to the right type, in my
		// example everything is hierarchical included in this first node
		resource.getContents().addAll(eObjects);

		// Now save the content.
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
