package oida.bridge.recommender;

import java.util.List;

import bridgemodel.Recommendation;
import oida.bridge.service.IOIDABridge;
import oida.ontology.OntologyDatatypeProperty;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-03-09
 *
 */
public interface IDatatypePropertyRecommender extends ISecondaryRecommender {
	List<Recommendation> findRecommendationsForSelectedDatatypeProperty(final OntologyDatatypeProperty selectedModelElement, IOIDABridge oidaBridge);
}
