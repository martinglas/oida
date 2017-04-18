package oida.bridge.recommender.name;

import java.util.List;

import bridgemodel.RecommendationType;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyIndividual;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-13
 *
 */
public class IndividualNameRecommender  extends AbstractNameRecommender<OntologyClass> {
	private final String NAME = "Individual Name Recommender";
	
	@Override
	protected String getRecommenderName() {
		return NAME;
	}

	@Override
	protected List<OntologyClass> getSearchEntityList() {
		return getReferenceOntology().getClasses();
	}

	@Override
	protected String getSearchName(OntologyEntity entity) {
		if (entity instanceof OntologyIndividual)
			return entity.getName();
		
		return null;
	}
	
	@Override
	protected RecommendationType getRecommendationType() {
		return RecommendationType.INSTANCE_OF;
	}
}