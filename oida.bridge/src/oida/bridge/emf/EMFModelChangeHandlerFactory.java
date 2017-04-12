package oida.bridge.emf;

import oida.bridge.model.IModelChangeHandler;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.OntologyManagerException;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-07
 *
 */
public class EMFModelChangeHandlerFactory {
	private EMFModelOntology emfModelOntology;
	
	private static EMFModelChangeHandlerFactory instance;
	
	public static EMFModelChangeHandlerFactory getInstance() {
		if (instance == null)
			instance = new EMFModelChangeHandlerFactory();
		
		return instance;
	}
	
	private EMFModelChangeHandlerFactory() {
	}
	
	public void initialize(IOntologyManager ontologyManager) {
		emfModelOntology = new EMFModelOntology();
		
		try {
			emfModelOntology.loadOrInitializeOntology(ontologyManager);
		} catch (OntologyManagerException e) {
			e.printStackTrace();
		}
	}
	
	public IModelChangeHandler createModelChangeHandler() {
		return new EMFModelChangeHandler(emfModelOntology);
	}
}
