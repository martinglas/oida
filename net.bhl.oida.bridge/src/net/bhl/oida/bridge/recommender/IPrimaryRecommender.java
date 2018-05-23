package net.bhl.oida.bridge.recommender;

import java.util.List;

import net.bhl.oida.bridge.model.recommendation.Recommendation;
import net.bhl.oida.bridge.service.OIDABridge;
import net.bhl.oida.ontology.model.owlontology.Ontology;
import net.bhl.oida.ontology.model.owlontology.OntologyIndividual;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-03-09
 *
 */
public interface IPrimaryRecommender {
    /**
     * Method for initialization tasks, when a model is opened and observed by
     * the OIDA bridge.
     * 
     * @param model
     *            The observed model.
     * @param
     */
    void initializeRecommenderForModel(final Ontology observedModelOntology, final Ontology referenceOntology);

    List<Recommendation> findRecommendationsForSelectedIndividual(final OntologyIndividual selectedModelElement, OIDABridge oidaBridge);
}
