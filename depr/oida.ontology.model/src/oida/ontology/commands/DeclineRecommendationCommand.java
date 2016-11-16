///*******************************************************************************
// * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
// *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
// *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
// ******************************************************************************/
//package oida.ontology.commands;
//
//import oida.ontology.matcher.MatchRecommendation;
//import oida.ontology.util.OntologyHelper;
//
//import org.apache.jena.ontology.OntResource;
//
///**
// * This class is a command which declines a MatchingRecommendation by setting a
// * disjoint/different from relationship between ontology resources.
// * 
// * @author martin.glas
// * 
// */
//public class DeclineRecommendationCommand {
//	private MatchRecommendation recommendation;
//
//	/**
//	 * This is the constructor of the command taking a Matching Recommendation
//	 * object as parameter.
//	 * 
//	 * @param matchingRecommendation
//	 *            the Matching RecommendationObject which should be declined.
//	 */
//	public DeclineRecommendationCommand(
//			MatchRecommendation matchingRecommendation) {
//		recommendation = matchingRecommendation;
//	}
//
//	/**
//	 * This method declined a recommendation by setting a disjoint/differentFrom
//	 * Relationship between two ontology resources.
//	 */
//	public void doRun() {
//		OntResource sourceResource = recommendation.getSource();
//		OntResource targetResource = recommendation.getTarget();
//
//		OntologyHelper.declareDisjoint(sourceResource, targetResource);
//
//	}
//
//}
