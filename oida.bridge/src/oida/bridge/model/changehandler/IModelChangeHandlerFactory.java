package oida.bridge.model.changehandler;

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
	 */
	void initialize(IOIDAOntologyService ontologyService);
	
	/**
	 * Creates a new instance of a model change handler.
	 * 
	 * @return Model Change Handler object.
	 */
	IModelChangeHandler getChangeHandler();
}
