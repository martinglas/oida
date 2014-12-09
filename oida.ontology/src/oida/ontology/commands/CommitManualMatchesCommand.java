/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.commands;

import java.util.List;

import oida.ontology.matcher.ManualMatch;

/**
 * This class is a command which commits a list of ManualMatch Objects.
 * 
 * @author Martin Glas
 * 
 */
public class CommitManualMatchesCommand {

	private List<ManualMatch> manualMatches;

	/**
	 * This is the constructor method of the class which takes a list of
	 * ManualMatch objects as parameter.
	 * 
	 * @param manualMatches
	 *            a list of ManualMatch objects
	 */
	public CommitManualMatchesCommand(List<ManualMatch> manualMatches) {
		this.manualMatches = manualMatches;
	}

	/**
	 * This method commits a list of ManualMatch objects.
	 * 
	 */
	public void doRun() {
		for (ManualMatch manualMatch : manualMatches) {
			CommitManualMatchCommand commitManualMatchCommand = new CommitManualMatchCommand(
					manualMatch);
			commitManualMatchCommand.doRun();
		}

	}

}
