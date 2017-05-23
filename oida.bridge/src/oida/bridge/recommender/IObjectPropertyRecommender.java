package oida.bridge.recommender;

import java.util.List;

import bridgemodel.Recommendation;
import oida.bridge.service.IOIDABridge;
import oida.ontology.OntologyObjectProperty;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-03-09
 *
 */
public interface IObjectPropertyRecommender extends ISecondaryRecommender {
	List<Recommendation> findRecommendationsForSelectedObjectProperty(final OntologyObjectProperty selectedModelElement, IOIDABridge oidaBridge);
}
