package net.bhl.oida.bridge.recommender;

import java.util.List;

import net.bhl.oida.bridge.model.recommendation.Recommendation;
import net.bhl.oida.bridge.service.OIDABridge;
import net.bhl.oida.ontology.model.owlontology.OntologyDatatypeProperty;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-03-09
 *
 */
public interface IDatatypePropertyRecommender extends ISecondaryRecommender {
	List<Recommendation> findRecommendationsForSelectedDatatypeProperty(final OntologyDatatypeProperty selectedModelElement, OIDABridge oidaBridge);
}
