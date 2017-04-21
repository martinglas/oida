package oida.bridge.model.changehandler;

import oida.bridge.model.ontology.OIDAModelBaseOntology;
import oida.ontology.service.IOIDAOntologyService;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-21
 *
 */
public interface IModelChangeHandlerFactory {
	
	void initialize(IOIDAOntologyService ontologyService, OIDAModelBaseOntology modelBaseOntology);
	
	/**
	 * Creates a new instance of a model change handler.
	 * 
	 * @return Model Change Handler object.
	 */
	IModelChangeHandler getChangeHandler();
}
