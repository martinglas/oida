///*******************************************************************************
// * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
// *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
// *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
// ******************************************************************************/
//package oida.ontology.commands;
//
//import java.net.URL;
//
//import oida.ontology.io.Owl;
//
//import org.eclipse.emf.common.command.AbstractCommand;
//
///**
// * This is a command which is supposed to save an ontology to a file.
// * 
// * @author Martin Glas
// * 
// */
//
//public class SaveOntologyCommand extends AbstractCommand {
//	private URL fileURL;
//
//	/**
//	 * This is the constructor method of the class.
//	 * 
//	 * @param fileURL
//	 *            the URL of the file where the ontology has to be saved
//	 */
//	public SaveOntologyCommand(URL fileURL) {
//		this.fileURL = fileURL;
//
//	}
//
//	protected void doRun() {
//
//		// URL defaultOntologyURL = new
//		// URL("file:/C:/Users/martin.glas/Projects/Core Projects/modelintegration/AP2-ModelMatching/ontologies/apa/apa2.owl");
//
//		Owl.saveToOwl(fileURL);
//
//	}
//
//	@Override
//	public void execute() {
//		doRun();
//
//	}
//
//	@Override
//	public void redo() {
//		// TODO Auto-generated method stub
//
//	}
//
//}
