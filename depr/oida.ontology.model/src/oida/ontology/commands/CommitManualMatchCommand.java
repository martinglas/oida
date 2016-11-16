///*******************************************************************************
// * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
// *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
// *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
// ******************************************************************************/
//package oida.ontology.commands;
//
//import org.apache.jena.ontology.OntResource;
//
//import oida.ontology.matcher.ManualMatch;
//import oida.ontology.util.OntologyHelper;
//
//
//
///**
// * This command commits a match between two ontologies which is based on a
// * manual match.
// * 
// * @author Martin Glas
// * 
// */
//public class CommitManualMatchCommand {
//
//	private ManualMatch manualMatch;
//
//	/**
//	 * This is the constructor method of the class.
//	 * 
//	 * @param manualMatch
//	 *            the ManualMatch object which should be committed
//	 */
//	public CommitManualMatchCommand(ManualMatch manualMatch) {
//		this.manualMatch = manualMatch;
//	}
//
//	/**
//	 * This method commits the ManualMatch command.
//	 */
//	public void doRun() {
//		OntResource sourceResource = manualMatch.getSource();
//		OntResource targetResource = manualMatch.getTarget();
//
//		OntologyHelper.linkEquivalentResources(sourceResource, targetResource);
//
//	}
//
//	/**
//	 * This method takes back the ManualMatch command.
//	 */
//	public void undo() {
//		OntologyHelper.unlinkEquivalentResources(manualMatch.getSource(),
//				manualMatch.getTarget());
//	}
//
//}
