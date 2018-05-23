package net.bhl.oida.bridge.recommender;

import java.util.List;

import net.bhl.oida.bridge.model.recommendation.Recommendation;
import net.bhl.oida.bridge.service.OIDABridge;
import net.bhl.oida.ontology.model.owlontology.OntologyObjectProperty;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-03-09
 *
 */
public interface IObjectPropertyRecommender extends ISecondaryRecommender {
	List<Recommendation> findRecommendationsForSelectedObjectProperty(final OntologyObjectProperty selectedModelElement, OIDABridge oidaBridge);
}
