package oida.bridge.recommender;

import java.util.List;

import bridgemodel.Recommendation;
import oida.bridge.service.IOIDABridge;
import oida.ontology.Ontology;
import oida.ontology.OntologyEntity;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-03-09
 *
 */
public interface IRecommender {
	/**
	 * Method for initialization tasks, when a model is opened and observed by the OIDA bridge.
	 * @param model The observed model.
	 * @param 
	 */
	void initializeRecommenderForModel(final Ontology observedModelOntology, final Ontology referenceOntology);
	
	/**
	 * Invokes a search for ontology element mapping recommendations based on the selected model element.
	 * 
	 * @param selectedModelElement The currently selected model element for which the mapping recommendations should be found.
	 * @return Potential ontology entities, which can be mapped.
	 */
	List<Recommendation> findRecommendationsForSelectedModelElement(final OntologyEntity selectedModelElement, IOIDABridge oidaBridge);
	
	/**
	 * Sets a flag, which determines, if the recommender is active.
	 * 
	 * @param active true, if the recommender should be active, otherwise false.
	 */
	void setActive(boolean active);
	
	/**
	 * Gives information, if the recommender is currently active.
	 * 
	 * @return true, if the recommender is active.
	 */
	boolean isActive();
}
