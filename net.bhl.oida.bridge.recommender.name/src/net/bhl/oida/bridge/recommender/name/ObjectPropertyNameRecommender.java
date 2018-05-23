package net.bhl.oida.bridge.recommender.name;

import java.util.List;

import net.bhl.oida.bridge.model.recommendation.Recommendation;
import net.bhl.oida.bridge.model.recommendation.RecommendationType;
import net.bhl.oida.bridge.recommender.IObjectPropertyRecommender;
import net.bhl.oida.bridge.service.OIDABridge;
import net.bhl.oida.ontology.model.owlontology.Ontology;
import net.bhl.oida.ontology.model.owlontology.OntologyEntity;
import net.bhl.oida.ontology.model.owlontology.OntologyObjectProperty;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-13
 *
 */
public class ObjectPropertyNameRecommender extends AbstractNameRecommender<OntologyObjectProperty> implements IObjectPropertyRecommender {
    private final String NAME = "Object Property Name Recommender";

    @Override
    public String getName() {
	return NAME;
    }

    @Override
    public void initializeRecommenderForMetaModel(Ontology observedModelOntology, Ontology referenceOntology) {
	initializeRecommender(observedModelOntology, referenceOntology);
    }

    @Override
    public List<Recommendation> findRecommendationsForSelectedObjectProperty(OntologyObjectProperty selectedModelElement, OIDABridge oidaBridge) {
	return findRecommendationsForSelectedModelElement(selectedModelElement, oidaBridge);
    }

    @Override
    protected List<OntologyObjectProperty> getSearchEntityList() {
	return getReferenceOntology().getObjectProperties();
    }

    @Override
    protected String getSearchName(OntologyEntity entity) {
	if (entity instanceof OntologyObjectProperty)
	    return entity.getName();

	return null;
    }

    @Override
    protected RecommendationType getRecommendationType() {
	return RecommendationType.EQUIVALENT_TO;
    }
}
