/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.model;

import java.util.ArrayList;
import java.util.List;

import oida.ontology.util.OntologyHelper;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.vocabulary.RDFS;

/**
 * This class provides ontology metrics.
 * 
 * @author Martin Glas
 * 
 */
public final class OntologyMetrics {

	private OntologyMetrics() {

	}

	/**
	 * This class returns the number of statements in an ontology.
	 * 
	 * @param ontologyModel
	 *            the OntologyModel
	 * @return the number of statements in the given ontologyModel
	 */
	public static long getNumberOfStatements(OntologyModel ontologyModel) {

		long result = 0;
		if (ontologyModel != null) {
			result = ontologyModel.getOntologyModel().size();
		}

		return result;

	}

	/**
	 * This method returns the ratio between the number of object proeperties
	 * and the number of classes in a given ontology model.
	 * 
	 * @param ontologyModel
	 *            the ontology model which is measured.
	 * @return the ratio between the number of object properties and the number
	 *         of classes
	 */
	public static Double getObjectPropertiesToClassesRatio(
			OntologyModel ontologyModel) {
		Double result = Double.NaN;
		if (ontologyModel != null) {
			int numberOfClasses = countClasses(ontologyModel);
			if (numberOfClasses > 0) {
				result = new Double(countObjectProperties(ontologyModel))
						/ numberOfClasses;
			}
		}
		return result;
	}

	public static int countObjectProperties(OntologyModel ontologyModel) {

		return ontologyModel.getObjectProperties().size();
	}

	public static int countClasses(OntologyModel ontologyModel) {

		return ontologyModel.getClasses().size();
	}

	/**
	 * This method returns the ratio between the number of datatype properties
	 * and the number of classes in a given ontology model.
	 * 
	 * @param ontologyModel
	 *            the ontology model which is measured.
	 * @return the ratio between the number of datatype properties and the
	 *         number of classes
	 */
	public static Double getDatatypePropertiesToClassesRatio(
			OntologyModel ontologyModel) {
		Double result = Double.NaN;
		if (ontologyModel != null) {
			int numberOfClasses = countClasses(ontologyModel);
			if (numberOfClasses > 0) {
				result = new Double(countDatatypeProperties(ontologyModel))
						/ numberOfClasses;
			}
		}

		return result;
	}

	public static int countDatatypeProperties(OntologyModel ontologyModel) {

		return ontologyModel.getDatatypeProperties().size();
	}

	private static int getMaxTaxonomyDepth(OntClass rootClass) {
		int result = 0;
		for (OntClass ontClass : rootClass.listSubClasses(true).toList()) {
			int depth = getMaxTaxonomyDepth(ontClass);
			if (depth > result) {
				result = depth;
			}
		}
		result++;
		return result;
	}

	private static List<OntClass> getRootClasses(List<OntClass> ontClasses) {
		List<OntClass> resultList = new ArrayList<OntClass>();
		for (OntClass ontClass : ontClasses) {
			OntClass superClass = ontClass.getSuperClass();

			if (superClass.equals(RDFS.Resource)) {
				resultList.add(ontClass);
			}
		}
		return resultList;
	}

	/**
	 * This method returns the maximal depth of a taxonomy tree in an ontology
	 * model.
	 * 
	 * @param ontologyModel
	 *            the ontology model which is to be measured
	 * @return the maximal depth of the taxonomy tree in an ontology
	 */
	public static int getMaxTaxonomyDepth(OntologyModel ontologyModel) {
		List<OntClass> ontClasses = ontologyModel.getClasses();
		List<OntClass> rootClasses = getRootClasses(ontClasses);
		// iterate over root Classes and find tallest tree
		int maxDepth = 0;
		for (OntClass rootOntClass : rootClasses) {
			int taxonomyDepth = getMaxTaxonomyDepth(rootOntClass);
			if (taxonomyDepth > maxDepth) {
				maxDepth = taxonomyDepth;
			}
		}
		return maxDepth;

	}

	public static int countIndividuals(OntologyModel ontologyModel) {
		return ontologyModel.getIndividuals().size();

	}

	public static int getMaxMerologyDepth(OntologyModel ontologyModel) {
		ObjectProperty partOfObjectProperty = OntologyHelper
				.getPartOfProperty(ontologyModel);
		int maxMerologyDepth = 0;
		for (Individual individual : ontologyModel.getIndividuals()) {
			int merologyDepth = getMaxMerologyDepth(individual,
					partOfObjectProperty);
			if (merologyDepth > maxMerologyDepth) {
				maxMerologyDepth = merologyDepth;
			}
		}
		return maxMerologyDepth;
	}

	private static int getMaxMerologyDepth(Individual individual,
			ObjectProperty partOfObjectProperty) {
		int maxDepth = 0;
		if (individual.hasProperty(partOfObjectProperty)) {

			for (Individual containingIndividual : OntologyHelper
					.getIndividuals(individual.listPropertyValues(
							partOfObjectProperty).toList())) {
				int merologyDepth = getMaxMerologyDepth(containingIndividual,
						partOfObjectProperty);
				if (merologyDepth > maxDepth) {
					maxDepth = merologyDepth;
				}
			}
			maxDepth++;
		}
		return maxDepth;
	}
}
