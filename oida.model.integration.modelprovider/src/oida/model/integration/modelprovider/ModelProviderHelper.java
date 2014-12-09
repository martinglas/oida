/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.modelprovider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class ModelProviderHelper {

	public static final String REFERENCE_ONTOLOGY_URI = "reference.owl";

	public static List<EObject> getComprisedEObjects(EObject rootModelElement) {
		List<EObject> comprisedEObjectsList = new ArrayList<EObject>();
		comprisedEObjectsList.add(rootModelElement);
		Iterator<EObject> childrenEObjectsIterator = rootModelElement.eAllContents();
		while (childrenEObjectsIterator.hasNext()) {
			comprisedEObjectsList.add(childrenEObjectsIterator.next());
		}
		return comprisedEObjectsList;
	}

	public static EObject getChildByName(EObject parent, String eObjectName) {
		TreeIterator<EObject> iterator = parent.eAllContents();
		while (iterator.hasNext()) {
			EObject ob = iterator.next();
			if (getModelElementName(ob).equals(eObjectName)) {
				return ob;
			}
		}
		return null;
	}

	/**
	 * This method returns all child Objects of a given Element having a specific type and name.
	 * 
	 * @param <T> The Type parameter which should match clazz
	 * @param parent The methods looks in the child objects of this parent Element
	 * @param clazz The type of Elements the method looks for
	 * @param name The name of Elements the method looks for
	 * @return The list of Elements which are found among the child objects of the parent Element
	 */
	@SuppressWarnings("unchecked")
	public static <T extends EObject> List<T> getChildrenByClassAndName(EObject parent, Class<T> clazz, String name) {
		List<T> result = new ArrayList<T>();
		if (parent == null) {
			return result;
		}
		TreeIterator<EObject> iterator = parent.eAllContents();
		while (iterator.hasNext()) {
			EObject eObject = iterator.next();
			String objectName = getModelElementName(eObject);
			if ((clazz.isInstance(eObject) && (objectName.equals(name)))) {
				result.add((T) eObject);
			}

		}
		return result;
	}

	/**
	 * This method generates a name of an EObject looking for a name attribute.
	 * 
	 * @param eObject The ModelElement
	 * @return The generated Name
	 */
	public static EAttribute getModelElementNameAttribute(EObject eObject) {
		EClass eClass = eObject.eClass();

		for (EStructuralFeature eStructuralFeature : eClass.getEAllStructuralFeatures()) {
			if (eStructuralFeature instanceof EAttribute) {
				EAttribute eAttribute = (EAttribute) eStructuralFeature;
				if (eAttribute.getName().equals("name")) {
					return eAttribute;

				}
			}
		}
		for (EStructuralFeature eStructuralFeature : eClass.getEAllStructuralFeatures()) {
			if (eStructuralFeature instanceof EAttribute) {
				EAttribute eAttribute = (EAttribute) eStructuralFeature;
				if (eAttribute.getName().toLowerCase().contains("name")) {

					return eAttribute;
				}
			}
		}

		return null;
	}

	/**
	 * This method generates a name of an EObject looking for a name attribute.
	 * 
	 * @param eObject The ModelElement
	 * @return The generated Name
	 */
	public static String getModelElementName(EObject eObject) {

		EAttribute eAttribute = getModelElementNameAttribute(eObject);
		if (eAttribute != null) {
			return eObject.eGet(eAttribute).toString().replace(" ", "_");

		}

		return eObject.toString();

	}

	/**
	 * This method generates a name of the container of an EObject looking for a name attribute. It stops if the
	 * container is the root element of a model.
	 * 
	 * @param eObject The ModelElement
	 * @param rootEObject the root eObject of a model
	 * @return The generated Name
	 */
	public static String getModelElementContainmentName(EObject eObject, EObject rootEObject) {

		String containerName = "";
		if (eObject.eContainer() != null) {
			if (!eObject.equals(rootEObject)) {
				containerName = getModelElementName(eObject.eContainer()) + ".";

			}

		}

		return containerName;
	}

	/**
	 * This method generates a name of the container of an EObject looking for a name attribute. It stops if the
	 * container is the root element of a model.
	 * 
	 * @param eObject The ModelElement
	 * @param rootEObject the root eObject of a model
	 * @return The generated Name
	 */
	public static String getModelElementDeeperContainmentName(EObject eObject, EObject rootEObject) {

		String containerName = "";
		if (eObject.eContainer() != null) {
			if (!eObject.equals(rootEObject)) {
				containerName = getModelElementName(eObject.eContainer()) + ".";
				if ((eObject.eContainer().eContainer() != null) && (!eObject.eContainer().equals(rootEObject))) {
					containerName = getModelElementName(eObject.eContainer().eContainer()) + "." + containerName;
				}
			}

		}

		return containerName;
	}

	/**
	 * This method generates an Object ID from its name attribute and the name attribute of the container.
	 * 
	 * @param eObject the object which needs an ID
	 * @param rootEObject a reference to the overall container to avoid using containers names which are out of scope.)
	 * @return the generated ID for the EObject
	 */
	public static String getModelElementContextID(EObject eObject, EObject rootEObject) {
		return getModelElementContainmentName(eObject, rootEObject) + getModelElementName(eObject);
	}

	public static String getModelDeeperElementContextID(EObject eObject, EObject rootEObject) {
		return getModelElementDeeperContainmentName(eObject, rootEObject) + getModelElementName(eObject);
	}

}
