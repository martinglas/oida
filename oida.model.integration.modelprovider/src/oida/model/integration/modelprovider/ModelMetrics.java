/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.modelprovider;

import oida.model.integration.model.extractor.Extractor;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public final class ModelMetrics {

	public static int countAllClasses(EObject rootModelElement) {
		Extractor extractor = new Extractor(rootModelElement);

		return extractor.getAllClassesOfInstanceEObjects().size();
	}

	public static int countAllAttributes(EObject rootModelElement) {
		Extractor extractor = new Extractor(rootModelElement);

		return extractor.getAllEAttributes().size();
	}

	public static int countAllReferences(EObject rootModelElement) {
		Extractor extractor = new Extractor(rootModelElement);
		return extractor.getAllEReferences().size();
	}

	public static int getMaxTaxonomyDepth(EObject rootModelElement) {
		Extractor extractor = new Extractor(rootModelElement);
		int maxTaxonomyDepth = 0;

		for (EClass eclass : extractor.getAllClassesOfInstanceEObjects()) {
			int currentDepth = getMaxDepth(eclass);
			if (currentDepth > maxTaxonomyDepth) {
				maxTaxonomyDepth = currentDepth;

			}
		}
		maxTaxonomyDepth++;
		return maxTaxonomyDepth;
	}

	private static int getMaxDepth(EClass eclass) {
		int maxTaxonomyDepth = 0;
		if (!eclass.getESuperTypes().isEmpty()) {
			for (EClass superType : eclass.getESuperTypes()) {
				int superTypeDepth = getMaxDepth(superType);
				if (superTypeDepth > maxTaxonomyDepth) {
					maxTaxonomyDepth = superTypeDepth;
				}
			}
			maxTaxonomyDepth++;
		}
		return maxTaxonomyDepth;
	}

	public static int getMaxContainmentTreeDepth(EObject rootModelElement) {
		Extractor extractor = new Extractor(rootModelElement);
		int maxContainmentDepth = 0;
		for (EObject eObject : extractor.getInstanceEObjects()) {
			int containmentDepth = getContainmentDepth(eObject,
					rootModelElement);
			if (containmentDepth > maxContainmentDepth) {
				maxContainmentDepth = containmentDepth;
			}
		}

		return maxContainmentDepth;
	}

	private static int getContainmentDepth(EObject eObject,
			EObject rootModelElement) {
		int containmentDepth = 0;
		if (!eObject.equals(rootModelElement)) {
			EObject container = eObject.eContainer();

			if (container != null) {
				containmentDepth = getContainmentDepth(container,
						rootModelElement) + 1;
			}
		}
		return containmentDepth;
	}

	public static Object countObjects(EObject rootModelElement) {
		Extractor extractor = new Extractor(rootModelElement);
		return extractor.getInstanceEObjects().size();
	}
}
