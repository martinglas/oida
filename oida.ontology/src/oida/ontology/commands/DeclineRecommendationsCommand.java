/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.commands;

import java.util.List;

import oida.ontology.matcher.MatchRecommendation;

/**
 * This class is a command which declines a list of MatchingRecommendation
 * objects.
 * 
 * @author Martin Glas
 * 
 */
public class DeclineRecommendationsCommand {
	private List<MatchRecommendation> recommendations;

	/**
	 * This is the constructor of the command taking a list of
	 * MatchingRecommendation Objects as parameter.
	 * 
	 * @param matchingRecommendations
	 *            a list of MatchingRecommendations objects which should be
	 *            declined
	 */
	public DeclineRecommendationsCommand(
			List<MatchRecommendation> matchingRecommendations) {
		recommendations = matchingRecommendations;
	}

	/**
	 * This method declines a list of MatchRecommendation objects.
	 */
	public void doRun() {
		for (MatchRecommendation recommendation : recommendations) {
			DeclineRecommendationCommand declineRecommendationCommand = new DeclineRecommendationCommand(
					recommendation);
			declineRecommendationCommand.doRun();
		}

	}

}
