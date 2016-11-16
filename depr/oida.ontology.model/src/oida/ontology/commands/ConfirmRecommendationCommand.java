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
// * This class is a command which confirms MatchRecommendation objects.
// * 
// * @author Martin Glas
// * 
// */
//public class ConfirmRecommendationCommand {
//
//	private MatchRecommendation recommendation;
//
//	/**
//	 * This constructor of the command which takes a MatchingRecommendation
//	 * object as parameter.
//	 * 
//	 * @param matchingRecommendation
//	 *            the MatchingRecommendation object is should be committed
//	 */
//	public ConfirmRecommendationCommand(
//			MatchRecommendation matchingRecommendation) {
//		recommendation = matchingRecommendation;
//	}
//
//	/**
//	 * This method commits the Matching recommendations by setting an
//	 * equivalence relationship betweeen two ressources.
//	 */
//	public void doRun() {
//		OntResource sourceResource = recommendation.getSource();
//		OntResource targetResource = recommendation.getTarget();
//
//		OntologyHelper.linkEquivalentResources(sourceResource, targetResource);
//
//	}
//
//	/**
//	 * This method undoes the mathingRecommendation by removing the equivalence
//	 * relationship.
//	 */
//	public void undo() {
//		OntologyHelper.unlinkEquivalentResources(recommendation.getSource(),
//				recommendation.getTarget());
//	}
//
//}
