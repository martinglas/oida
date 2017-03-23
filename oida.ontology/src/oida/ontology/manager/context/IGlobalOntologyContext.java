/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.manager.context;

import java.util.Optional;

import oida.ontology.Ontology;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-03-23
 *
 */
public interface IGlobalOntologyContext {
	/**
	 * Searches the global ontology context for an ontology having a specific IRI.
	 * @param ontologyIRI The IRI of the wanted ontology.
	 * @return An optional object containing the ontology if existing.
	 */
	Optional<Ontology> findOntology(String ontologyIRI);
}
