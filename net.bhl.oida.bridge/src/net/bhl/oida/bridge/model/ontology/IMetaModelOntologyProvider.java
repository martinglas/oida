package net.bhl.oida.bridge.model.ontology;

import net.bhl.oida.bridge.model.changehandler.IModelChangeHandler;
import net.bhl.oida.bridge.model.strategy.IRenamerStrategy;
import net.bhl.oida.bridge.model.strategy.IStructuringStrategy;
import net.bhl.oida.ontology.manager.IOntologyManager;
import net.bhl.oida.ontology.model.owlontology.Ontology;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-05-09
 *
 */
public interface IMetaModelOntologyProvider {
    IModelChangeHandler createMetaModelOntology(IRenamerStrategy renamerStrategy, IStructuringStrategy structuringStrategy, IOntologyManager manager, Ontology referenceOntology);
}
