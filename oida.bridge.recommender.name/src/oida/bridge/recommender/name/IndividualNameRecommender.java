package oida.bridge.recommender.name;

import java.util.List;
import java.util.Optional;

import bridgemodel.RecommendationType;
import oida.bridge.model.ontology.OIDAModelBaseOntology;
import oida.ontology.OntologyAnnotation;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyIndividual;
import oida.util.constants.StringConstants;

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
		if (entity instanceof OntologyIndividual) {
			Optional<OntologyAnnotation> optAnnotation = entity.getAnnotations().stream().filter(a -> a.getAnnotationproperty().equals(OIDAModelBaseOntology.getInstance().getNameAnnotationProperty())).findFirst();
			
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