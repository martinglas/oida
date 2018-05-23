package net.bhl.oida.bridge.recommender;

import java.util.List;

import net.bhl.oida.bridge.model.recommendation.Recommendation;
import net.bhl.oida.bridge.service.OIDABridge;
import net.bhl.oida.ontology.model.owlontology.OntologyClass;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-03-09
 *
 */
public interface IClassRecommender extends ISecondaryRecommender {
	List<Recommendation> findRecommendationsForSelectedClass(final OntologyClass selectedModelElement, OIDABridge oidaBridge);
}
