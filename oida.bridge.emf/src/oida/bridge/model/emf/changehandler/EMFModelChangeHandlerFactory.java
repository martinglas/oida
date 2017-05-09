package oida.bridge.model.emf.changehandler;

import oida.bridge.model.changehandler.IModelChangeHandler;
import oida.bridge.model.changehandler.IModelChangeHandlerFactory;
import oida.bridge.model.strategy.IRenamerStrategy;
import oida.bridge.model.strategy.IStructuringStrategy;
import oida.ontology.manager.IOntologyManager;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-07
 *
 */
public class EMFModelChangeHandlerFactory implements IModelChangeHandlerFactory {
	@Override
	public IModelChangeHandler getChangeHandler(IRenamerStrategy renamerStrategy, IStructuringStrategy structuringStrategy, IOntologyManager metaModelOntology) {
		EMFModelChangeHandler changeHandler = new EMFModelChangeHandler();
		changeHandler.initializeChangeHandler(renamerStrategy, structuringStrategy, metaModelOntology);
		return changeHandler;
	}
}
