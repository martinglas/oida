/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.commands;

import java.util.List;

import oida.ontology.matcher.MatchRecommendation;

/**
 * This class is a command which confirms a list of MatchingRecommendation
 * objects.
 * 
 * @author Martin Glas
 * 
 */
public class ConfirmRecommendationsCommand {

	private List<MatchRecommendation> recommendations;

	/**
	 * This is the constructor method of the class which takes a list of
	 * MatchRecommendation objects as parameter.
	 * 
	 * @param matchingRecommendations
	 *            a list of ManualMatch objects
	 */
	public ConfirmRecommendationsCommand(
			List<MatchRecommendation> matchingRecommendations) {
		recommendations = matchingRecommendations;
	}

	/**
	 * This method commits a list of MatchingRecommendations.
	 */
	public void doRun() {
		for (MatchRecommendation recommendation : recommendations) {
			ConfirmRecommendationCommand confirmRecommendationCommand = new ConfirmRecommendationCommand(
					recommendation);
			confirmRecommendationCommand.doRun();
		}

	}

	/**
	 * This method undoes a list of MatchingRecommendations.
	 * 
	 * @param rollBack
	 *            number of commands which are rolled back form the end of the
	 *            list.
	 */
	public void undo(int rollBack) {
		for (int i = recommendations.size() - 1; i >= rollBack; i--) {
			ConfirmRecommendationCommand confirmRecommendationCommand = new ConfirmRecommendationCommand(
					recommendations.get(i));
			confirmRecommendationCommand.undo();
		}

	}
}
