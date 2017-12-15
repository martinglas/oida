package oida.bridge.recommender.name;

import java.util.List;
import java.util.Optional;

import bridgemodel.recommendation.Recommendation;
import bridgemodel.recommendation.RecommendationType;
import oida.bridge.model.ontology.OIDAModelBaseOntology;
import oida.bridge.recommender.IPrimaryRecommender;
import oida.bridge.service.IOIDABridge;
import oida.ontology.Ontology;
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
	public List<Recommendation> findRecommendationsForSelectedIndividual(OntologyIndividual selectedModelElement, IOIDABridge oidaBridge) {
		return findRecommendationsForSelectedModelElement(selectedModelElement, oidaBridge);
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