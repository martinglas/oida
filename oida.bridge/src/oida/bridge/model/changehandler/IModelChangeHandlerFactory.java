package oida.bridge.model.changehandler;

import oida.bridge.model.strategy.IRenamerStrategy;
import oida.bridge.model.strategy.IStructuringStrategy;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.service.IOIDAOntologyService;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-21
 *
 */
public interface IModelChangeHandlerFactory {
	
	/**
	 * Initializes the factory for model change handler.
	 * 
	 * @param ontologyService The OIDA Ontology Service instance, in order to enable the model change handler to get ontology manager instances.
	 * 
	 */
	void initialize(IOIDAOntologyService ontologyService, IRenamerStrategy renamerStrategy, IStructuringStrategy structuringStrategy, IOntologyManager metaModelOntologyManager);
	
	/**
	 * Gets the Meta Model Ontology.
	 * 
	 * @return An OntologyManager containing the meta model elements.
	 */
	IOntologyManager getMetaModelOntology();
	
	/**
	 * Creates a new instance of a model change handler.
	 * 
	 * @return Model Change Handler object.
	 */
	IModelChangeHandler getChangeHandler();
}
