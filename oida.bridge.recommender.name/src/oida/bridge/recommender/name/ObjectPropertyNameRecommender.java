package oida.bridge.recommender.name;

import java.util.List;

import bridgemodel.RecommendationType;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyObjectProperty;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-13
 *
 */
public class ObjectPropertyNameRecommender extends AbstractNameRecommender<OntologyObjectProperty> {
	private final String NAME = "Object Property Name Recommender";
	
	@Override
	protected String getRecommenderName() {
		return NAME;
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
