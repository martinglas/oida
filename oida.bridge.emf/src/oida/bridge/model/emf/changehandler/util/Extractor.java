/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge.model.emf.changehandler.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * This class handles the extraction of concepts from a given ExchangeModel tree to an ontology.
 * 
 * @author Martin Glas
 * 
 */
public class Extractor {
	// private List<EClass> allClassesOfInstanceEObjects;

	public static List<EClass> getDirectClassesOfInstanceEObjects(EObject rootEObject) {
		List<EClass> directEClasses = new ArrayList<EClass>();
		directEClasses.add(rootEObject.eClass());
		Iterator<EObject> childrenIterator = rootEObject.eAllContents();
		while (childrenIterator.hasNext()) {
			EObject childEObject = childrenIterator.next();
			EClass childEClass = childEObject.eClass();
			if (!directEClasses.contains(childEClass)) {
				directEClasses.add(childEClass);
			}
		}

		return directEClasses;
	}

	/**
	 * This method return all classes of the instance objects from the tree under the rootObject.
	 */
	public static List<EClass> getAllClassesOfInstanceEObjects(EObject rootEObject) {
		List<EClass> directEClasses = getDirectClassesOfInstanceEObjects(rootEObject);
		List<EClass> indirectEClasses = new ArrayList<EClass>();

		indirectEClasses.addAll(directEClasses);
		for (EClass eClass : directEClasses) {
			if (!eClass.getESuperTypes().isEmpty()) {
				for (EClass eSuperType : eClass.getESuperTypes()) {
					addEClassUntilRoot(eSuperType, indirectEClasses);
				}
			}
		}

		return indirectEClasses;
	}

	private static void addEClassUntilRoot(EClass eClass, List<EClass> targetEClasses) {
		if (!targetEClasses.contains(eClass)) {
			if (!eClass.getESuperTypes().isEmpty()) {
				for (EClass eSuperType : eClass.getESuperTypes()) {
					addEClassUntilRoot(eSuperType, targetEClasses);
				}
			}

			targetEClasses.add(eClass);
		}
	}

	public static List<EObject> getInstanceEObjects(EObject rootEObject) {
		return ModelProviderHelper.getComprisedEObjects(rootEObject);
	}

	public static List<EStructuralFeature> getAllEStructuralFeatures(EObject rootEObject) {
		List<EStructuralFeature> structuralFeatures = new ArrayList<EStructuralFeature>();
		for (EClass eClass : getAllClassesOfInstanceEObjects(rootEObject)) {
			structuralFeatures.addAll(eClass.getEStructuralFeatures());
		}

		return structuralFeatures;
	}

	public static List<EAttribute> getAllEAttributes(EObject rootEObject) {
		List<EAttribute> eAttributes = new ArrayList<EAttribute>();
		for (EClass eClass : getAllClassesOfInstanceEObjects(rootEObject)) {
			eAttributes.addAll(eClass.getEAttributes());

		}
		return eAttributes;
	}

	public static List<EReference> getAllEReferences(EObject rootEObject) {
		List<EReference> eReferences = new ArrayList<EReference>();
		for (EClass eClass : getAllClassesOfInstanceEObjects(rootEObject)) {
			eReferences.addAll(eClass.getEReferences());

		}
		return eReferences;
	}

}
