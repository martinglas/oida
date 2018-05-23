/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package net.bhl.oida.ontology.predefined;

import net.bhl.oida.ontology.manager.IOntologyManager;
import net.bhl.oida.ontology.manager.OntologyManagerException;
import net.bhl.oida.ontology.model.owlontology.Ontology;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-03-22
 *
 */
public abstract class AbstractPredefinedOntology implements IPredefinedOntology {
    private IOntologyManager ontologyManager;

    public IOntologyManager getOntologyManager() {
	return ontologyManager;
    }

    protected void setOntologyManager(IOntologyManager ontologyManager) {
	this.ontologyManager = ontologyManager;
    }

    @Override
    public void loadOrInitializeOntology(IOntologyManager ontologyManager) throws OntologyManagerException {
	setOntologyManager(ontologyManager);
	if (!checkPreDefinedOntology(ontologyManager.getOntology())) {
	    initializeOntology(ontologyManager);
	    ontologyManager.saveLocalOntology();
	}
    }

    protected abstract boolean checkPreDefinedOntology(Ontology ontology);

    protected abstract void initializeOntology(IOntologyManager ontologyManager) throws OntologyManagerException;
}
