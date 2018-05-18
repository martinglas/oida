/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge.model.changehandler;

import java.util.Optional;

import oida.bridge.model.strategy.IRenamerStrategy;
import oida.bridge.model.strategy.IStructuringStrategy;
import oida.ontology.AOntologyItem;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyIndividual;
import oida.ontology.OntologyObjectProperty;
import oida.ontology.manager.IOntologyManager;

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
