/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.model.extractor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import oida.model.integration.modelprovider.ModelProviderHelper;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

;

/**
 * This class handles the extraction of concepts from a given ExchangeModel tree
 * to an ontology.
 * 
 * @author Martin Glas
 * 
 */
public class Extractor {

	private EObject rootEObject;
	private List<EClass> allClassesOfInstanceEObjects;

	/**
	 * This is the constructor of the class which sets up the ontology.
	 * 
	 * @param targetOntologyURI
	 *            the URI of the target Ontology
	 */
	public Extractor(EObject rootEObject) {
		this.rootEObject = rootEObject;

	}

	public List<EClass> getDirectClassesOfInstanceEObjects() {
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
	 * This method return all classes of the instance objects from the tree
	 * under the rootObject.
	 * 
	 */

	public List<EClass> getAllClassesOfInstanceEObjects() {
		if (this.allClassesOfInstanceEObjects == null) {
			List<EClass> directEClasses = getDirectClassesOfInstanceEObjects();
			List<EClass> indirectEClasses = new ArrayList<EClass>();

			indirectEClasses.addAll(directEClasses);
			for (EClass eClass : directEClasses) {

				if (!eClass.getESuperTypes().isEmpty()) {
					for (EClass eSuperType : eClass.getESuperTypes()) {

						addEClassUntilRoot(eSuperType, indirectEClasses);

					}

				}

			}
			this.allClassesOfInstanceEObjects = indirectEClasses;
		}

		return this.allClassesOfInstanceEObjects;
	}

	private void addEClassUntilRoot(EClass eClass, List<EClass> targetEClasses) {
		if (!targetEClasses.contains(eClass)) {
			if (!eClass.getESuperTypes().isEmpty()) {
				for (EClass eSuperType : eClass.getESuperTypes()) {
					addEClassUntilRoot(eSuperType, targetEClasses);
				}
			}
			targetEClasses.add(eClass);

		}

	}

	public List<EObject> getInstanceEObjects() {

		return ModelProviderHelper.getComprisedEObjects(rootEObject);
	}

	public List<EStructuralFeature> getAllEStructuralFeatures() {
		List<EStructuralFeature> structuralFeatures = new ArrayList<EStructuralFeature>();
		for (EClass eClass : getAllClassesOfInstanceEObjects()) {
			structuralFeatures.addAll(eClass.getEStructuralFeatures());

		}
		return structuralFeatures;
	}

	public List<EAttribute> getAllEAttributes() {
		List<EAttribute> eAttributes = new ArrayList<EAttribute>();
		for (EClass eClass : getAllClassesOfInstanceEObjects()) {
			eAttributes.addAll(eClass.getEAttributes());

		}
		return eAttributes;
	}

	public List<EReference> getAllEReferences() {
		List<EReference> eReferences = new ArrayList<EReference>();
		for (EClass eClass : getAllClassesOfInstanceEObjects()) {
			eReferences.addAll(eClass.getEReferences());

		}
		return eReferences;
	}

}
