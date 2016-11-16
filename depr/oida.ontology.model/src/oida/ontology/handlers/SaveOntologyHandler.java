///*******************************************************************************
// * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
// *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
// *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
// ******************************************************************************/
//package oida.ontology.handlers;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//
//import oida.ontology.commands.SaveOntologyCommand;
//
//import org.eclipse.core.commands.AbstractHandler;
//import org.eclipse.core.commands.ExecutionEvent;
//import org.eclipse.core.commands.ExecutionException;
//
///**
// * This is a Handler for the SaveOntologyCommand.
// * 
// * @author Martin Glas
// * 
// */
//
//public class SaveOntologyHandler extends AbstractHandler {
//
//	@Override
//	public Object execute(ExecutionEvent event) throws ExecutionException {
//
//		URL fileURL;
//		try {
//			fileURL = new URL(
//					"file:/C:/Users/martin.glas/Projects/Core Projects/modelintegration/AP2-ModelMatching/ontologies/apa/apa2.owl");
//
//			SaveOntologyCommand saveOntologyCommand = new SaveOntologyCommand(
//					fileURL);
//			saveOntologyCommand.execute();
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			System.out.println("URL was malformed");
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//}
