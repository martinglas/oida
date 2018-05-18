package oida.bridge.model.ontology;

import oida.bridge.model.changehandler.IModelChangeHandler;
import oida.bridge.model.strategy.IRenamerStrategy;
import oida.bridge.model.strategy.IStructuringStrategy;
import oida.ontology.Ontology;
import oida.ontology.manager.IOntologyManager;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-05-09
 *
 */
public interface IMetaModelOntologyProvider {
    IModelChangeHandler createMetaModelOntology(IRenamerStrategy renamerStrategy, IStructuringStrategy structuringStrategy, IOntologyManager manager, Ontology referenceOntology);
}
