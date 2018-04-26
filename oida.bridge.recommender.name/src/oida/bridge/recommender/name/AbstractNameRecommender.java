package oida.bridge.recommender.name;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import bridgemodel.recommendation.Recommendation;
import bridgemodel.recommendation.RecommendationFactory;
import bridgemodel.recommendation.RecommendationType;
import oida.bridge.recommender.AbstractRecommender;
import oida.bridge.recommender.name.helper.LevenshteinStringSimilarity;
import oida.bridge.service.OIDABridge;
import oida.ontology.IMappable;
import oida.ontology.Ontology;
import oida.ontology.OntologyEntity;
import oida.util.constants.StringConstants;

/**
 * @author Michael Shamiyeh
 * @since 2017-03-09
 *
 */
public abstract class AbstractNameRecommender<T extends OntologyEntity> extends AbstractRecommender {
    private final double LEVENSHTEIN_CUTOFF = 0.3;
    
    public void initializeRecommender(Ontology observedModelOntology, Ontology referenceOntology) {
	setModelOntology(observedModelOntology);
	setReferenceOntology(referenceOntology);
    }

    public List<Recommendation> findRecommendationsForSelectedModelElement(OntologyEntity selectedModelElement, OIDABridge oidaBridge) {
	List<Recommendation> recommendations = new ArrayList<Recommendation>();

	String selectedElementName = getSearchName(selectedModelElement);
	if (selectedElementName == null || selectedElementName.equals(StringConstants.EMPTY))
	    return recommendations;
	
	List<T> recommendedEntities = getSearchEntityList().stream().filter(e -> (LevenshteinStringSimilarity.similarity(e.getName().toLowerCase(), selectedElementName.toLowerCase()) > LEVENSHTEIN_CUTOFF)).collect(Collectors.toList());

	for (T entity : recommendedEntities) {
	    if (entity instanceof IMappable && !((IMappable)entity).isMappingExists()) {
		Recommendation r = RecommendationFactory.eINSTANCE.createRecommendation();
		r.setRecommendedEntity(entity);
		r.setRecommenderName(getName());
		r.setRecommendationType(getRecommendationType());
		r.setReliability(LevenshteinStringSimilarity.similarity(entity.getName().toLowerCase(), selectedElementName.toLowerCase()) * 100);
		r.setRecommenderMessage("The name '" + entity.getName() + "' contains the string '" + selectedElementName + "'");
		recommendations.add(r);
	    }
	}

	return recommendations;
    }

    protected abstract String getSearchName(OntologyEntity entity);

    protected abstract List<T> getSearchEntityList();

    protected abstract RecommendationType getRecommendationType();
}
