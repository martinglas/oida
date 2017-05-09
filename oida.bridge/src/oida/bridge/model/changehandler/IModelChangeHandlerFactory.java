package oida.bridge.model.changehandler;

import oida.bridge.model.strategy.IRenamerStrategy;
import oida.bridge.model.strategy.IStructuringStrategy;
import oida.ontology.manager.IOntologyManager;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-21
 *
 */
public interface IModelChangeHandlerFactory {
	/**
	 * Creates a new instance of a model change handler.
	 * 
	 * @return Model Change Handler object.
	 */
	IModelChangeHandler getChangeHandler(IRenamerStrategy renamerStrategy, IStructuringStrategy structuringStrategy, IOntologyManager metaModelOntology);
}
