/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.model;

import oida.ontology.util.OntologyHelper;

import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.util.iterator.Filter;

public class OntologyFilter<R extends OntResource> extends Filter<R> {
	private Ontology ontology;

	public OntologyFilter(Ontology ontology) {
		this.ontology = ontology;
	}

	public Ontology getOntology() {
		return ontology;
	}

	public void setOntology(Ontology ontology) {
		this.ontology = ontology;
	}

	@Override
	public boolean accept(R ontResource) {
		if (OntologyHelper.isInOntology(getOntology(), ontResource)) {
			return false;

		}
		return true;
	}

}
