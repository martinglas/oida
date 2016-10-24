/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.matcher.util;

import java.util.ArrayList;
import java.util.List;

import oida.ontology.matcher.ManualMatch;
import oida.ontology.matcher.MatchRecommendation;
import oida.ontology.matcher.MatcherFactory;
import oida.ontology.model.OntologyModel;
import oida.ontology.util.OntologyHelper;

import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.ontology.Ontology;

/**
 * This is a helper class for finding different kinds of matches between two
 * ontologies.
 * 
 * @author MartinGlas
 * 
 */
public final class MatchHelper {

	private MatchHelper() {

	}

	/**
	 * This method finds string matches between resources of a source and target
	 * ontology. The resources must be of the same kind (Individual, class,
	 * object property, datatype property).
	 * 
	 * @param sourceOntology
	 *            the source ontology
	 * @param targetOntology
	 *            the target ontology
	 * @return a list of MatchingRecommendation objects
	 */
	public static List<MatchRecommendation> getStringMatches(
			Ontology sourceOntology, Ontology targetOntology) {
		List<MatchRecommendation> stringRecommendations = new ArrayList<MatchRecommendation>();
		OntologyModel ontologyModel = OntologyModel.getInstance();

		stringRecommendations.addAll(getStringMatches(OntClass.class,
				ontologyModel, sourceOntology, targetOntology));

		stringRecommendations.addAll(getStringMatches(OntProperty.class,
				ontologyModel, sourceOntology, targetOntology));

		stringRecommendations.addAll(getStringMatches(Individual.class,
				ontologyModel, sourceOntology, targetOntology));

		return stringRecommendations;
	}

	private static <R extends OntResource> List<MatchRecommendation> getStringMatches(
			Class<R> clazz, OntologyModel ontologyModel,
			Ontology sourceOntology, Ontology targetOntology) {

		List<MatchRecommendation> stringRecommendations = new ArrayList<MatchRecommendation>();
		// getList of all Classes of the source ontology
		List<R> sourceOntologyResources = ontologyModel.getResourcesByClass(
				clazz, sourceOntology);
		// getList of all Classes of the target ontology
		List<R> targetOntologyResources = ontologyModel.getResourcesByClass(
				clazz, targetOntology);
		// Iterate through the list. If there is a match create a
		// recommendation and add it to the result list
		for (R sourceOntologyResource : sourceOntologyResources) {
			for (R targetOntologyResource : targetOntologyResources) {
				if (sourceOntologyResource.getLocalName().equalsIgnoreCase(
						targetOntologyResource.getLocalName())) {
					MatchRecommendation newMatchRecommendation = MatcherFactory.eINSTANCE
							.createMatchRecommendation();
					newMatchRecommendation.setSource(sourceOntologyResource);
					newMatchRecommendation.setTarget(targetOntologyResource);
					stringRecommendations.add(newMatchRecommendation);
				}
			}
		}
		return stringRecommendations;
	}

	/**
	 * This method finds structural matches between classes of a source and
	 * target ontology. ThIt asserts that two classes are equivalent if all
	 * datatype properties are equivalent.
	 * 
	 * @param sourceOntology
	 *            the source ontology
	 * @param targetOntology
	 *            the target ontology
	 * @return a list of MatchingRecommendation objects
	 */
	public static List<MatchRecommendation> getStructuralMatches(
			Ontology sourceOntology, Ontology targetOntology) {
		OntologyModel ontologyModel = OntologyModel.getInstance();
		List<MatchRecommendation> recommendations = new ArrayList<MatchRecommendation>();
		// Get list of unmapped OntClasses

		List<OntClass> unmappedOntClasses = getUnmappedResourcesByClass(
				ontologyModel.getResourcesByClass(OntClass.class,
						sourceOntology), targetOntology);

		// For every unmapped class get all datatype Properties which have the
		// class as domain.
		for (OntClass unmappedClass : unmappedOntClasses) {
			List<OntProperty> classProperties = unmappedClass
					.listDeclaredProperties(true).toList();
			List<DatatypeProperty> datatypeProperties = new ArrayList<DatatypeProperty>();
			for (OntProperty classProperty : classProperties) {
				if (classProperty.isDatatypeProperty()) {
					datatypeProperties.add(classProperty.asDatatypeProperty());
				}
			}

			// if datatype properties are all mapped to reference and m

			OntClass sameTargetDomain = getSameTargetDomain(datatypeProperties,
					targetOntology);
			if (sameTargetDomain != null) {
				MatchRecommendation newMatchRecommendation = MatcherFactory.eINSTANCE
						.createMatchRecommendation();
				newMatchRecommendation.setSource(unmappedClass);
				newMatchRecommendation.setTarget(sameTargetDomain);

				recommendations.add(newMatchRecommendation);
			}

		}

		return recommendations;
	}

	private static OntClass getSameTargetDomain(
			List<DatatypeProperty> datatypeProperties, Ontology targetOntology) {
		OntClass sameTarget = null;
		for (DatatypeProperty datatypeProperty : datatypeProperties) {
			DatatypeProperty targetDatatypeProperty = getTargetDatatypeProperty(
					datatypeProperty, targetOntology);
			if (targetDatatypeProperty != null) {
				OntClass targetClass = targetDatatypeProperty.getDomain()
						.asClass();
				if (sameTarget == null) {
					sameTarget = targetClass;
				} else if (!sameTarget.equals(targetClass)) {
					return null;
				}
			}
		}

		return sameTarget;
	}

	private static DatatypeProperty getTargetDatatypeProperty(
			DatatypeProperty sourceDatatypeProperty, Ontology targetOntology) {
		List<DatatypeProperty> equivalentDatatypeProperies = OntologyHelper
				.getDatatypeProperties(sourceDatatypeProperty
						.listEquivalentProperties().toList());

		for (DatatypeProperty equivalentDatatypeProperty : equivalentDatatypeProperies) {
			if (OntologyHelper.isInOntology(targetOntology,
					equivalentDatatypeProperty)) {
				return equivalentDatatypeProperty;
			}
		}
		return null;
	}

	private static <R extends OntResource> List<R> getUnmappedResourcesByClass(
			List<R> resources, Ontology targetOntology) {

		List<R> unmappedResources = new ArrayList<R>();
		for (R ontologyResource : resources) {
			if (ontologyResource.isClass()) {
				OntClass ontClass = ontologyResource.asClass();
				List<OntClass> equivalentClasses = ontClass
						.listEquivalentClasses().toList();
				if (equivalentClasses.isEmpty()) {
					unmappedResources.add(ontologyResource);
				} else {
					for (OntClass equivalentClass : equivalentClasses) {
						if (!equivalentClass.getNameSpace().equals(
								targetOntology.getURI() + "#")) {
							unmappedResources.add(ontologyResource);
						}
					}
				}
			}

			else if (ontologyResource.isProperty()) {
				OntProperty ontProperty = ontologyResource.asProperty();
				List<DatatypeProperty> equivalentProperties = OntologyHelper
						.getDatatypeProperties(ontProperty
								.listEquivalentProperties().toList());
				if (equivalentProperties.isEmpty()) {
					unmappedResources.add(ontologyResource);
				} else {
					for (OntProperty equivalentProperty : equivalentProperties) {
						if (!equivalentProperty.getNameSpace().equals(
								targetOntology.getURI() + "#")) {
							unmappedResources.add(ontologyResource);
						}
					}
				}
			}

			else if (ontologyResource.isIndividual()) {
				Individual individual = ontologyResource.asIndividual();
				List<Individual> sameIndividuals = OntologyHelper
						.getIndividuals(individual.listSameAs().toList());
				if (sameIndividuals.isEmpty()) {
					unmappedResources.add(ontologyResource);
				} else {
					for (Individual sameIndividual : sameIndividuals) {
						if (!sameIndividual.getNameSpace().equals(
								targetOntology.getURI() + "#")) {
							unmappedResources.add(ontologyResource);
						}
					}
				}
			}

		}

		return unmappedResources;
	}

	public static ManualMatch createManualMatch(
			OntResource sourceOntologyResource,
			OntResource targetOntologyResource) {
		ManualMatch newManualMatch = MatcherFactory.eINSTANCE
				.createManualMatch();
		newManualMatch.setSource(sourceOntologyResource);
		newManualMatch.setTarget(targetOntologyResource);
		return newManualMatch;
	}

}
