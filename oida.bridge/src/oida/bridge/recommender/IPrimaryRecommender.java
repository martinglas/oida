package oida.bridge.recommender;

import java.util.List;

import bridgemodel.Recommendation;
import oida.bridge.service.IOIDABridge;
import oida.ontology.Ontology;
import oida.ontology.OntologyIndividual;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-03-09
 *
 */
public interface IPrimaryRecommender {
	/**
	 * Method for initialization tasks, when a model is opened and observed by the OIDA bridge.
	 * @param model The observed model.
	 * @param 
	 */
	void initializeRecommenderForModel(final Ontology observedModelOntology, final Ontology referenceOntology);
	
	List<Recommendation> findRecommendationsForSelectedIndividual(final OntologyIndividual selectedModelElement, IOIDABridge oidaBridge);
}
