/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.transformation.commands;

import java.net.URL;

import org.apache.jena.ontology.Ontology;

import oida.model.integration.transformation.modelgenerator.GeneratorHelper;
import oida.model.integration.transformation.modelgenerator.Metamodel;
import oida.model.integration.transformation.modelgenerator.MetamodelHelper;
import oida.ontology.model.OntologyModel;

/**
 * This class provides command to generate an ECore metamodel form an owl
 * ontology.
 * 
 * @author Martin Glas
 * 
 */

public class GenerateEcoreFormOWLCommand {
	private OntologyModel ontologyModel;
	private URL ecoreFileURL;
	private Ontology ontology;

	/**
	 * This is the command constructor.
	 * 
	 * @param ecoreFileURL
	 *            the Path where the Ecore file will be stored
	 */
	public GenerateEcoreFormOWLCommand(URL ecoreFileURL, Ontology ontology) {
		setOntologyModel(OntologyModel.getInstance());
		this.ecoreFileURL = ecoreFileURL;
		this.ontology = ontology;

	}

	/**
	 * This method executes and generates an Ecore metamodel file.
	 */
	public void doRun() {
		// generate List of classes

		Metamodel metamodel = GeneratorHelper.generateMetamodel(ontology);

		MetamodelHelper.savePackageToURL(metamodel.getBasePackage(),
				ecoreFileURL);

	}

	/**
	 * This is the getter method of the ontologyModel variable.
	 * 
	 * @return the current ontologyModel
	 */
	public OntologyModel getOntologyModel() {
		return ontologyModel;
	}

	/**
	 * This is the setter method of the ontologyModel variable.
	 * 
	 * @param ontologyModel
	 *            an OntologyModel instance
	 */
	public void setOntologyModel(OntologyModel ontologyModel) {
		this.ontologyModel = ontologyModel;
	}

}
