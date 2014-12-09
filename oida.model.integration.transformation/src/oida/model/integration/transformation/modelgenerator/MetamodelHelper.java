/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.transformation.modelgenerator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;

/**
 * This is a helper class for the Metamodel class.
 * 
 * @author Martin Glas
 * 
 */
public final class MetamodelHelper {
	private MetamodelHelper() {

	}

	/**
	 * This method creates an EPackage form a given name and URI.
	 * 
	 * @param name
	 *            the name of the package
	 * @param uri
	 *            the URI of the package
	 * @return the crated EPackgage instance
	 */
	public static EPackage createPackage(String name, URI uri) {
		EPackage newPackage = EcoreFactory.eINSTANCE.createEPackage();
		newPackage.setName(name);
		newPackage.setNsPrefix(name);
		newPackage.setNsURI(uri.toString());

		return newPackage;
	}

	/**
	 * This method creates an EAttribute object from a given name and EDataType.
	 * 
	 * @param attributeName
	 *            the name of the attribute
	 * @param attributeType
	 *            the Type of the attribute
	 * @return the created EAttribute instance
	 */
	public static EAttribute createAttribute(String attributeName,
			EDataType attributeType) {
		EAttribute newAttribute = EcoreFactory.eINSTANCE.createEAttribute();
		newAttribute.setName(attributeName);
		newAttribute.setEType(attributeType);

		return newAttribute;

	}

	/**
	 * This method creates a class form a given class name.
	 * 
	 * @param className
	 *            the name of the class
	 * @return the newly created EClass instance
	 */
	public static EClass createClass(String className) {
		EClass newClass = EcoreFactory.eINSTANCE.createEClass();
		newClass.setName(className);
		return newClass;
	}

	/**
	 * This method creates a class form a given class name and a list of
	 * attributes.
	 * 
	 * @param className
	 *            the name of the class
	 * @param attributeList
	 *            the list of attributes of the class
	 * @return the newly created EClass instance
	 */
	public static EClass createClass(String className,
			List<EAttribute> attributeList) {
		EClass newClass = createClass(className);

		newClass.getEStructuralFeatures().addAll(attributeList);
		return newClass;
	}

	/**
	 * This method creates a class form a given class name and a list of
	 * attributes.
	 * 
	 * @param className
	 *            the name of the class
	 * @param attributeList
	 *            the list of attributes of the class
	 * @param superClass
	 *            the superclass of the subclass to be created
	 * @return the newly created EClass instance
	 */
	public static EClass createSubClass(String className, EClass superClass,
			List<EAttribute> attributeList) {
		EClass newSubClass = createClass(className, attributeList);
		newSubClass.getESuperTypes().add(superClass);
		return newSubClass;
	}

	/**
	 * This method loads EPackage instances form a ECore file given by the URL.
	 * 
	 * @param url
	 *            the URL of the ECore file
	 * @return the list of EPackages in the given ECore file
	 */
	public static List<EPackage> loadFrom(URL url) {
		List<EPackage> resultPackageList = new ArrayList<EPackage>();
		ResourceSet resourceSet = new ResourceSetImpl();

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("ecore", new XMLResourceFactoryImpl());

		Resource resultResource = resourceSet.getResource(
				URI.createURI(url.toString()), true);

		for (EObject resourceContent : resultResource.getContents()) {
			if (resourceContent instanceof EPackage) {
				resultPackageList.add((EPackage) resourceContent);
			}
		}

		return resultPackageList;

	}

	/**
	 * This method saves a given EPackage instance to a given file URL.
	 * 
	 * @param packageToSave
	 *            the package which is to be saved
	 * @param fileURL
	 *            the URL of the target file
	 */

	public static void savePackageToURL(EPackage packageToSave, URL fileURL) {
		List<EPackage> packageList = new ArrayList<EPackage>();
		packageList.add(packageToSave);
		savePackagesToURL(packageList, fileURL);
	}

	/**
	 * This method saves a given List of EPackage instances to a given file URL.
	 * 
	 * @param packageList
	 *            the list of packages which are to be saved
	 * @param fileURL
	 *            the URL of the target file
	 */
	public static void savePackagesToURL(List<EPackage> packageList, URL fileURL) {
		// As of here we preparing to save the model content

		// Obtain a new resource set
		ResourceSet resourceSet = new ResourceSetImpl();
		// Register the XMI resource factory for the .ecore extension

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("ecore", new XMLResourceFactoryImpl());

		// Create a resource
		Resource resource = resourceSet.createResource(URI.createURI(fileURL
				.toString()));
		// Get the first model element and cast it to the right type, in my
		// example everything is hierarchical included in this first node
		resource.getContents().addAll(packageList);

		// Now save the content.
		try {
			resource.save(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static EReference createReference(String referenceName,
			EClass rangeClass, int upperBound) {

		EReference newReference = EcoreFactory.eINSTANCE.createEReference();
		newReference.setName(referenceName);

		newReference.setEType(rangeClass);
		newReference.setUpperBound(upperBound);
		return newReference;

	}

	public static EReference createContainment(String referenceName,
			EClass rangeClass, int upperBound) {
		EReference newReference = createReference(referenceName, rangeClass,
				upperBound);
		newReference.setContainment(true);
		return newReference;
	}
}
