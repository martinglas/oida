package net.bhl.oida.bridge.recommender.name;

import java.util.List;
import java.util.Optional;

import net.bhl.oida.bridge.model.ontology.OIDAModelBaseOntology;
import net.bhl.oida.bridge.model.recommendation.Recommendation;
import net.bhl.oida.bridge.model.recommendation.RecommendationType;
import net.bhl.oida.bridge.recommender.IPrimaryRecommender;
import net.bhl.oida.bridge.service.OIDABridge;
import net.bhl.oida.ontology.model.owlontology.Ontology;
import net.bhl.oida.ontology.model.owlontology.OntologyAnnotation;
import net.bhl.oida.ontology.model.owlontology.OntologyClass;
import net.bhl.oida.ontology.model.owlontology.OntologyEntity;
import net.bhl.oida.ontology.model.owlontology.OntologyIndividual;
import net.bhl.oida.util.constants.StringConstants;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-13
 *
 */
public class IndividualNameRecommender extends AbstractNameRecommender<OntologyClass> implements IPrimaryRecommender {
    private final String NAME = "Individual Name Recommender";

    @Override
    public String getName() {
	return NAME;
    }

    @Override
    public void initializeRecommenderForModel(Ontology observedModelOntology, Ontology referenceOntology) {
	initializeRecommender(observedModelOntology, referenceOntology);
    }

    @Override
    public List<Recommendation> findRecommendationsForSelectedIndividual(OntologyIndividual selectedModelElement, OIDABridge oidaBridge) {
	return findRecommendationsForSelectedModelElement(selectedModelElement, oidaBridge);
    }

    @Override
    protected List<OntologyClass> getSearchEntityList() {
	return getReferenceOntology().getClasses();
    }

    @Override
    protected String getSearchName(OntologyEntity entity) {
	if (entity instanceof OntologyIndividual) {
	    Optional<OntologyAnnotation> optAnnotation = entity.getAnnotations().stream().filter(a -> a.getAnnotationproperty().equals(OIDAModelBaseOntology.getInstance().getNameAnnotationProperty()))
		    .findFirst();

	    if (optAnnotation.isPresent())
		return optAnnotation.get().getValue();
	    else
		return entity.getName();
	}

	return StringConstants.EMPTY;
    }

    @Override
    protected RecommendationType getRecommendationType() {
	return RecommendationType.INSTANCE_OF;
    }
}