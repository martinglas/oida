package net.bhl.oida.bridge.recommender.name;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import net.bhl.oida.bridge.model.recommendation.Recommendation;
import net.bhl.oida.bridge.model.recommendation.RecommendationFactory;
import net.bhl.oida.bridge.model.recommendation.RecommendationType;
import net.bhl.oida.bridge.recommender.AbstractRecommender;
import net.bhl.oida.bridge.recommender.name.helper.LevenshteinStringSimilarity;
import net.bhl.oida.bridge.service.OIDABridge;
import net.bhl.oida.ontology.model.owlontology.IMappable;
import net.bhl.oida.ontology.model.owlontology.Ontology;
import net.bhl.oida.ontology.model.owlontology.OntologyEntity;
import net.bhl.oida.util.constants.StringConstants;

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
		r.setRecommenderMessage("The name of the item" + StringConstants.NEWLINE +
			"'" + entity.getName() + "'" + StringConstants.NEWLINE +
			"in the knowledge-base is similar to the string" + StringConstants.NEWLINE +
			"'" + selectedElementName + "'");
		recommendations.add(r);
	    }
	}

	return recommendations;
    }

    protected abstract String getSearchName(OntologyEntity entity);

    protected abstract List<T> getSearchEntityList();

    protected abstract RecommendationType getRecommendationType();
}
