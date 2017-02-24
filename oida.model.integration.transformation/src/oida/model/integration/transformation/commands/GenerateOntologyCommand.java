/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.transformation.commands;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

import oida.bridge.observerservice.renamer.RenamerStrategy;
import oida.model.integration.transformation.ontologygenerator.OntologyGenerator;

/**
 * This is a command which is supposed to extract an ontology from an Exchange
 * model tree.
 * 
 * @author Martin Glas
 * 
 */

public class GenerateOntologyCommand extends AbstractCommand {

	private EObject rootEObject;
	private URI targetOntologyURI;
	private OntologyGenerator ontologyGenerator;

	/**
	 * This is the constructor method of the class.
	 * 
	 * @param rootEObject
	 *            The exchangeElement which is the root of the ExchangeModel
	 *            tree
	 * @param targetOntologyURI
	 *            the URI of the target ontology
	 */
	public GenerateOntologyCommand(EObject rootEObject, URI targetOntologyURI,
			RenamerStrategy renamerStrategy) {

		setRootEObject(rootEObject);
		setTargetOntologyURI(targetOntologyURI);

		setOntologyGenerator(new OntologyGenerator(getTargetOntologyURI(),
				getRootEObject(), renamerStrategy));

	}

	@Override
	public void execute() {

		getOntologyGenerator().generateOntClasses();
		getOntologyGenerator().generateProperties();
		getOntologyGenerator().generateIndividuals();
	}

	/**
	 * The setter method of the targetOntologyURI variable.
	 * 
	 * @param targetOntologyURI
	 *            the target ontology URI
	 */
	public void setTargetOntologyURI(URI targetOntologyURI) {
		this.targetOntologyURI = targetOntologyURI;
	}

	/**
	 * The getter method of the targetOntologyURI variable.
	 * 
	 * @return targetOntologyURI the target ontology URI
	 */
	public URI getTargetOntologyURI() {
		return targetOntologyURI;
	}

	/**
	 * The setter method of the targetOntologyURI variable.
	 * 
	 * @param rootEObject
	 *            the ExchangElement which serves as root for the extraction
	 */
	public void setRootEObject(EObject rootEObject) {
		this.rootEObject = rootEObject;
	}

	/**
	 * The getter method of the targetOntologyURI variable.
	 * 
	 * @return targetOntologyURI the ExchangElement which serves as root for the
	 *         extraction
	 */
	public EObject getRootEObject() {
		return rootEObject;
	}

	public OntologyGenerator getOntologyGenerator() {
		return ontologyGenerator;
	}

	public void setOntologyGenerator(OntologyGenerator ontologyGenerator) {
		this.ontologyGenerator = ontologyGenerator;
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub

	}

}
