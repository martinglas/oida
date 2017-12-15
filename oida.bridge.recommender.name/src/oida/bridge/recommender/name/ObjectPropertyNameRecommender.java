package oida.bridge.recommender.name;

import java.util.List;

import bridgemodel.recommendation.Recommendation;
import bridgemodel.recommendation.RecommendationType;
import oida.bridge.recommender.IObjectPropertyRecommender;
import oida.bridge.service.IOIDABridge;
import oida.ontology.Ontology;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyObjectProperty;

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
	public List<Recommendation> findRecommendationsForSelectedObjectProperty(OntologyObjectProperty selectedModelElement, IOIDABridge oidaBridge) {
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
