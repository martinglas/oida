/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.matching.util;

import java.util.List;

import oida.model.integration.matching.diffmodel.DiffModel;
import oida.model.integration.matching.diffmodel.DiffmodelFactory;
import oida.model.integration.matching.diffmodel.ReferenceMatching;
import oida.model.integration.modelprovider.ModelProviderHelper;
import oida.model.integration.transformation.mapping.ReferenceMapping;
import oida.model.integration.transformation.modelgenerator.Metamodel;
import oida.model.integration.transformation.ontologygenerator.Renamer;
import oida.model.integration.transformation.util.TransformatorHelper;

import com.hp.hpl.jena.ontology.Ontology;

public final class MatchingHelper {
	public static DiffModel getDiffModel(
			List<ReferenceMapping> sourceReferenceMappings,
			Renamer sourceRenamer,
			List<ReferenceMapping> targetReferenceMappings,
			Metamodel referenceMetamodel, Ontology targetOntology,
			Metamodel targetMetamodel, Renamer targetRenamer) {

		DiffModel diffModel = DiffmodelFactory.eINSTANCE.createDiffModel();

		for (ReferenceMapping sourceReferenceMapping : sourceReferenceMappings) {
			int matching = 0;
			String sourceName = ModelProviderHelper
					.getModelElementName(sourceReferenceMapping
							.getReferenceEObject());
			for (ReferenceMapping targetReferenceMapping : targetReferenceMappings) {

				String targetName = ModelProviderHelper
						.getModelElementName(targetReferenceMapping
								.getReferenceEObject());
				if (sourceName.equals(targetName)) {
					ReferenceMatching newReferenceMatching = DiffmodelFactory.eINSTANCE
							.createReferenceMatching();
					newReferenceMatching
							.setSourceReferenceMapping(sourceReferenceMapping);
					newReferenceMatching
							.setTargetReferenceMapping(targetReferenceMapping);
					newReferenceMatching.setComment("");
					diffModel.getReferenceMatchings().add(newReferenceMatching);
					matching++;
					break;
				}

			}
			if (matching == 0) {
				ReferenceMapping targetReferenceMapping = TransformatorHelper
						.getTargetReferenceMapping(
								sourceReferenceMapping.getReferenceEObject(),
								referenceMetamodel, targetOntology,
								targetMetamodel, targetRenamer);
				ReferenceMatching theirReferenceMatching = DiffmodelFactory.eINSTANCE
						.createReferenceMatching();
				theirReferenceMatching
						.setSourceReferenceMapping(targetReferenceMapping);
				theirReferenceMatching
						.setTargetReferenceMapping(targetReferenceMapping);
				theirReferenceMatching.setComment("");

				diffModel.getTheirMatchings().add(theirReferenceMatching);

			}

		}

		return diffModel;
	}

}
