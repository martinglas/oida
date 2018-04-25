package oida.bridge.recommender;

import java.util.List;

import bridgemodel.recommendation.Recommendation;
import oida.bridge.service.OIDABridge;
import oida.ontology.OntologyDatatypeProperty;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-03-09
 *
 */
public interface IDatatypePropertyRecommender extends ISecondaryRecommender {
	List<Recommendation> findRecommendationsForSelectedDatatypeProperty(final OntologyDatatypeProperty selectedModelElement, OIDABridge oidaBridge);
}
