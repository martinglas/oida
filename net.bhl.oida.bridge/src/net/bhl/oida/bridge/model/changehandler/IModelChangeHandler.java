/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package net.bhl.oida.bridge.model.changehandler;

import java.util.Optional;

import net.bhl.oida.bridge.model.strategy.IRenamerStrategy;
import net.bhl.oida.bridge.model.strategy.IStructuringStrategy;
import net.bhl.oida.ontology.manager.IOntologyManager;
import net.bhl.oida.ontology.model.owlontology.AOntologyItem;
import net.bhl.oida.ontology.model.owlontology.OntologyClass;
import net.bhl.oida.ontology.model.owlontology.OntologyIndividual;
import net.bhl.oida.ontology.model.owlontology.OntologyObjectProperty;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-01-20
 *
 */
public interface IModelChangeHandler {
    IModelChangeHandler getSuperModelChangeHandler();
    void setSuperModelChangeHandler(IModelChangeHandler changeHandler);
    
    IRenamerStrategy getRenamerStrategy();
    void setRenamerStrategy(IRenamerStrategy renamerStrategy);

    IStructuringStrategy getStructuringStrategy();
    void setStructuringStrategy(IStructuringStrategy structuringStrategy);

    IOntologyManager getMetaModelOntologyManager();

    IOntologyManager getModelOntologyManager();

    void startChangeTracking(IRenamerStrategy renamerStrategy, IStructuringStrategy structuringStrategy, IOntologyManager metaModelOntology, IOntologyManager modelOntologyManager, Object modelObject);

    Object getModelObject();

    Optional<AOntologyItem> getOntologyEntityForModelElement(Object modelElement);
    Optional<OntologyClass> getOntologyClassForModelElement(Object modelElement);
    Optional<OntologyIndividual> getOntologyIndividualForModelElement(Object modelElement);
    Optional<OntologyObjectProperty> getOntologyObjectPropertyForModelElement(Object modelElement);

    void closeModelOntology();
}
