package oida.bridge.recommender;

import oida.ontology.Ontology;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-03-09
 *
 */
public interface ISecondaryRecommender extends IRecommender {
	/**
	 * Method for initialization tasks, when a model is opened and observed by the OIDA bridge.
	 * @param model The observed model.
	 * @param 
	 */
	void initializeRecommenderForMetaModel(final Ontology observedMetaModelOntology, final Ontology referenceOntology);
}
