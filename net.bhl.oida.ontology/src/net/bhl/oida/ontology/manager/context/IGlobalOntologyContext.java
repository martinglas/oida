/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package net.bhl.oida.ontology.manager.context;

import java.util.Map;
import java.util.Optional;

import net.bhl.oida.ontology.manager.IOntologyManager;
import net.bhl.oida.ontology.model.ontologymgr.LocalOntologyMetaInfo;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-03-23
 *
 */
public interface IGlobalOntologyContext {
	/**
	 * Searches the global ontology context for an ontology having a specific IRI.
	 * 
	 * @param ontologyIRI The IRI of the wanted ontology.
	 * @return An optional object containing the appropriate ontology manager if existing.
	 */
	Optional<IOntologyManager> findOntology(String ontologyIRI);
	
	/**
	 * Holds a map of local paths for ontologies with global iris.
	 * 
	 * @return a map with the global iris as keys, and OntologyFile objects containing the local path as values.
	 */
	Map<String, LocalOntologyMetaInfo> getGlobalIRIToLocalIRIMappings();
}
