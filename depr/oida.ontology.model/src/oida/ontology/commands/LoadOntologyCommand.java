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
//import oida.ontology.model.OntologyModel;
//
//import org.eclipse.emf.common.command.AbstractCommand;
//import org.eclipse.emf.common.util.URI;
//
//import org.apache.jena.ontology.Individual;
//import org.apache.jena.ontology.OntClass;
//
///**
// * This is a command which is supposed to load an Ontology from a specific file.
// * 
// * @author Martin Glas
// * 
// */
//
//public class LoadOntologyCommand extends AbstractCommand {
//	private URL ontologyURL;
//	private URI ontologyURI;
//
//	/**
//	 * This is the constructor method of the class.
//	 * 
//	 * @param ontologyURL
//	 *            the URL of the ontology
//	 * @param ontologyURI
//	 *            the URI of the ontology
//	 */
//	public LoadOntologyCommand(URL ontologyURL, URI ontologyURI) {
//		setOntologyURL(ontologyURL);
//		setOntologyURI(ontologyURI);
//
//	}
//
//	private void setOntologyURL(URL ontologyURL) {
//		this.ontologyURL = ontologyURL;
//
//	}
//
//	protected void doRun() {
//
//		Owl.loadFromOwl(getOntologyURI(), getOntologyURL());
//
//		OntologyModel ontologyModel = OntologyModel.getInstance();
//
//		for (Individual individual : ontologyModel.getIndividuals()) {
//			System.out.println("Individual: " + individual);
//		}
//
//		for (OntClass ontClass : ontologyModel.listClasses()) {
//			System.out.println("Class: " + ontClass);
//		}
//
//		for (URI ontologyURI : ontologyModel.getImportedOntologyURIs()) {
//			System.out.println("Imported: " + ontologyURI);
//		}
//
//	}
//
//	private URL getOntologyURL() {
//
//		return this.ontologyURL;
//	}
//
//	/**
//	 * This is the setter method of the ontology URI.
//	 * 
//	 * @param ontologyURI
//	 *            The ontology URI
//	 */
//	public void setOntologyURI(URI ontologyURI) {
//		this.ontologyURI = ontologyURI;
//	}
//
//	/**
//	 * This is the getter method of the ontology URI.
//	 * 
//	 * @return currentOntologyURI
//	 */
//	public URI getOntologyURI() {
//		return ontologyURI;
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
