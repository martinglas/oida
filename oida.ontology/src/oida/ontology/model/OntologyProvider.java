/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.model;

import java.util.List;

import org.eclipse.emf.common.util.URI;

import com.hp.hpl.jena.ontology.OntClass;

public class OntologyProvider {

	private OntologyModel ontologyModel;
	private URI referenceOntologyURI;

	public OntologyProvider(URI referenceOntologyURI) {
		ontologyModel = OntologyModel.getInstance();
		this.referenceOntologyURI = referenceOntologyURI;

	}

	public List<OntClass> getOntClasses() {

		return ontologyModel.getClasses();

	}

	public List<OntClass> getOntClassesWithInstances() {
		List<OntClass> resultList = ontologyModel.getClassesWithInstances();
		return resultList;

	}

}
