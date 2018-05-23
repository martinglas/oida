package net.bhl.oida.bridge.recommender.name;

import java.util.List;

import net.bhl.oida.bridge.model.recommendation.Recommendation;
import net.bhl.oida.bridge.model.recommendation.RecommendationType;
import net.bhl.oida.bridge.recommender.IClassRecommender;
import net.bhl.oida.bridge.service.OIDABridge;
import net.bhl.oida.ontology.model.owlontology.Ontology;
import net.bhl.oida.ontology.model.owlontology.OntologyClass;
import net.bhl.oida.ontology.model.owlontology.OntologyEntity;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-13
 *
 */
public class ClassNameRecommender extends AbstractNameRecommender<OntologyClass> implements IClassRecommender {
    private final String NAME = "Class Name Recommender";

    @Override
    public String getName() {
	return NAME;
    }

    @Override
    protected String getSearchName(OntologyEntity entity) {
	if (entity instanceof OntologyClass)
	    return ((OntologyClass)entity).getName();

	return null;
    }

    @Override
    protected RecommendationType getRecommendationType() {
	return RecommendationType.EQUIVALENT_TO;
    }

    @Override
    public void initializeRecommenderForMetaModel(Ontology observedMetaModelOntology, Ontology referenceOntology) {
	initializeRecommender(observedMetaModelOntology, referenceOntology);
    }

    @Override
    public List<Recommendation> findRecommendationsForSelectedClass(OntologyClass selectedModelElement, OIDABridge oidaBridge) {
	return findRecommendationsForSelectedModelElement(selectedModelElement, oidaBridge);
    }

    @Override
    protected List<OntologyClass> getSearchEntityList() {
	return getReferenceOntology().getClasses();
    }
}
