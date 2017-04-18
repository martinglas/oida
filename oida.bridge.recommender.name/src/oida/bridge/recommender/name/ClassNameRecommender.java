package oida.bridge.recommender.name;

import bridgemodel.RecommendationType;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyIndividual;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-13
 *
 */
public class ClassNameRecommender extends IndividualNameRecommender {
	private final String NAME = "Class Name Recommender";
	
	@Override
	protected String getRecommenderName() {
		return NAME;
	}
	
	@Override
	protected String getSearchName(OntologyEntity entity) {
		if (entity instanceof OntologyIndividual && !((OntologyIndividual)entity).getTypes().isEmpty())
			return ((OntologyIndividual)entity).getTypes().get(0).getName();
		
		return null;
	}
	
	@Override
	protected RecommendationType getRecommendationType() {
		return RecommendationType.EQUIVALENT_TO;
	}
}
