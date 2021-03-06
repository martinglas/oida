/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package net.bhl.oida.ontology.predefined;

import net.bhl.oida.ontology.manager.IOntologyManager;
import net.bhl.oida.ontology.manager.OntologyManagerException;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-03-22
 *
 */
public interface IPredefinedOntology {
	IOntologyManager getOntologyManager();
	
	void loadOrInitializeOntology(IOntologyManager ontologyManager) throws OntologyManagerException;
}
