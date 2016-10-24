/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.reasoner;

import java.util.Iterator;

import oida.ontology.model.OntologyModel;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.reasoner.ValidityReport;
import org.apache.jena.reasoner.ValidityReport.Report;

/**
 * This class provides reasoning services on the ontology model.
 * 
 * @author Martin Glas
 * 
 */
public final class ReasonerService {
	private InfModel inferenceModel;
	private static ReasonerService instance;

	/**
	 * This method gets the instance of the singleton.
	 * 
	 * @return the singleton instance of the class
	 */
	public static ReasonerService getInstance() {
		if (instance == null) {
			instance = new ReasonerService();

		}
		return instance;
	}

	/**
	 * This method resets the ontologyModel singleton.
	 */
	public void reset() {

		instance = new ReasonerService();

	}

	/**
	 * This is the constructor method which initializes the Ontology object.
	 */
	private ReasonerService() {
		OntologyModel ontologyModel = OntologyModel.getInstance();

		Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
		InfModel newInferenceModel = ModelFactory.createInfModel(reasoner,
				ontologyModel.getOntologyModel());

		this.inferenceModel = newInferenceModel;
	}

	/**
	 * This method prints a report on the validity of the ontology.
	 */
	public void printReport() {
		ValidityReport validityReport = inferenceModel.validate();

		if (validityReport.isValid()) {
			System.out.println("Ontology is consistent");
		} else {
			System.out.println("Conflicts:");
			for (Iterator<Report> i = validityReport.getReports(); i.hasNext();) {
				Report report = i.next();

				System.out.println("   Type of conflict:" + report.getType());
				System.out.println("   Inconsitent resource: "
						+ report.getExtension());
				System.out
						.println("   Description: " + report.getDescription());

			}
		}

	}

	/**
	 * This class checks if a given ontology model is valid.
	 * 
	 * @param ontologyModel
	 *            the ontology model to be checked
	 * @return true if the ontology model is valid, false if not
	 */
	public boolean isConsistent(OntologyModel ontologyModel) {
		inferenceModel.rebind();
		ValidityReport validityReport = inferenceModel.validate();

		return validityReport.isValid();

	}
}
