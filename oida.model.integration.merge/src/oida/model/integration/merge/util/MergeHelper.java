/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.merge.util;

import java.util.ArrayList;
import java.util.List;

import oida.model.integration.matching.diffmodel.ReferenceMatching;
import oida.model.integration.transformation.mapping.AttributeMapping;

import org.eclipse.emf.ecore.EAttribute;

/**
 * This is a Helper class for ontology-based model merge.
 * 
 * @author Martin Glas
 * 
 */
public final class MergeHelper {

	private MergeHelper() {

	}

	public static List<ReferenceMatching> getConflictingMatchings(
			List<ReferenceMatching> referenceMatchings) {
		List<ReferenceMatching> conflictingMatchings = new ArrayList<ReferenceMatching>();
		for (ReferenceMatching referenceMatching : referenceMatchings) {
			// Check for conflicts
			int numberOfAttributeConflicts = 0;
			for (EAttribute eAttribute : referenceMatching
					.getSourceReferenceMapping().getReferenceEObject().eClass()
					.getEAllAttributes()) {
				Object sourceEAttributeValue = referenceMatching
						.getSourceReferenceMapping().getReferenceEObject()
						.eGet(eAttribute);
				Object targetEAttributeValue = referenceMatching
						.getTargetReferenceMapping().getReferenceEObject()
						.eGet(eAttribute);

				if (sourceEAttributeValue != null
						&& targetEAttributeValue != null) {
					if (!sourceEAttributeValue.equals(targetEAttributeValue)) {

						numberOfAttributeConflicts++;
					}
				} else if (sourceEAttributeValue != null
						|| targetEAttributeValue != null) {
					numberOfAttributeConflicts++;
				}
			}
			if (numberOfAttributeConflicts > 0) {
				conflictingMatchings.add(referenceMatching);
			}

		}
		return conflictingMatchings;
	}

	public static EAttribute getTargetEAttribute(
			ReferenceMatching referenceMatching, EAttribute referenceEAttribute) {
		for (AttributeMapping eAttributeMapping : referenceMatching
				.getTargetReferenceMapping().getEClassMapping()
				.getAttributeMappings()) {
			if (eAttributeMapping.getReferenceEAttribute().equals(
					referenceEAttribute)) {
				return eAttributeMapping.getSourceEAttribute();
			}
		}
		return null;
	}

}
