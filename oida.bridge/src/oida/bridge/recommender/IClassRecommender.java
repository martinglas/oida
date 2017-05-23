package oida.bridge.recommender;

import java.util.List;

import bridgemodel.Recommendation;
import oida.bridge.service.IOIDABridge;
import oida.ontology.OntologyClass;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-03-09
 *
 */
public interface IClassRecommender extends ISecondaryRecommender {
	List<Recommendation> findRecommendationsForSelectedClass(final OntologyClass selectedModelElement, IOIDABridge oidaBridge);
}
