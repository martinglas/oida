/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.transformation.ontologygenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import oida.model.integration.modelprovider.ModelProviderHelper;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * This class renames model elements in order to avoid naming conflicts.
 * 
 * @author martin.glas
 * 
 */
public class Renamer {
	private RenamerStrategy renamerStrategy;
	private EObject rootEObject;

	public Renamer(EObject rootEObject) {

		this.rootEObject = rootEObject;

	}

	public RenamerStrategy getRenamerStrategy() {
		return renamerStrategy;
	}

	public void setRenamerStrategy(RenamerStrategy renamerStrategy) {
		this.renamerStrategy = renamerStrategy;
	}

	public String getEObjectName(EObject eObject) {
		// TODO Add null pointer handling
		return getRenamerStrategy().getEObjectName(eObject);

	}

	public boolean isUnabiguous() {
		List<EObject> comprisedObjects = ModelProviderHelper
				.getComprisedEObjects(rootEObject);
		List<String> names = new ArrayList<String>();
		for (EObject comprisedObject : comprisedObjects) {
			String name = getEObjectName(comprisedObject);
			if (names.contains(name)) {
				System.out
						.println("The "
								+ getRenamerStrategy().getClass()
										.getSimpleName()
								+ " renaming strategy is ambiguous. At least two objects are named "
								+ name);
				return false;
			}

			else {
				names.add(name);
			}
		}
		System.out.println("The "
				+ getRenamerStrategy().getClass().getSimpleName()
				+ " renaming strategy is unambiguous.");
		return true;
	}

	public List<EObject> getAmbiguousEObjects() {
		List<EObject> comprisedEObjects = ModelProviderHelper
				.getComprisedEObjects(rootEObject);

		List<String> individualNames = new ArrayList<String>();
		List<String> ambiguousNames = new ArrayList<String>();
		for (EObject comprisedObject : comprisedEObjects) {
			String name = getEObjectName(comprisedObject);
			if (individualNames.contains(name)) {
				ambiguousNames.add(name);
			} else {
				individualNames.add(name);
			}
		}

		List<EObject> ambiguousEObjects = new ArrayList<EObject>();

		for (EObject comprisedObject : comprisedEObjects) {
			String name = getEObjectName(comprisedObject);
			if (ambiguousNames.contains(name)) {
				ambiguousEObjects.add(comprisedObject);
			}

		}

		return ambiguousEObjects;

	}

	public void printAmbiguousObjects(EObject rootEObject, String listFileName) {
		Writer output = null;
		File file = new File(listFileName);
		try {
			output = new BufferedWriter(new FileWriter(file));
			output.write("Ambiguous Objects:");
			for (EObject ambiguousEObject : getAmbiguousEObjects()) {
				output.write("\n");
				printContainers(ambiguousEObject, rootEObject, output);

			}
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void printContainers(EObject eObject, EObject rootEObject,
			Writer output) throws IOException {
		output.write(ModelProviderHelper.getModelElementName(eObject) + ".");
		if ((!eObject.equals(rootEObject)) && (eObject.eContainer() != null)) {
			printContainers(eObject.eContainer(), rootEObject, output);
		}

	}

	public String getEStructuralFeatureName(
			EStructuralFeature eStructuralFeature) {
		return getRenamerStrategy().getEStructuralFeatureName(
				eStructuralFeature);
	}

	public String getEClassName(EClass eClass) {

		return getRenamerStrategy().getEClassName(eClass);
	}

}
