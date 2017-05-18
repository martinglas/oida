package oida.bridge.recommender.name;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import bridgemodel.BridgemodelFactory;
import bridgemodel.Recommendation;
import bridgemodel.RecommendationType;
import oida.bridge.recommender.AbstractRecommender;
import oida.bridge.service.IOIDABridge;
import oida.ontology.Ontology;
import oida.ontology.OntologyEntity;
import oida.util.constants.StringConstants;

/**
 * @author Michael Shamiyeh
 * @since 2017-03-09
 *
 */
public abstract class AbstractNameRecommender<T extends OntologyEntity> extends AbstractRecommender {
	@Override
	public void initializeRecommenderForModel(Ontology observedModelOntology, Ontology referenceOntology) {
		setModelOntology(observedModelOntology);
		setReferenceOntology(referenceOntology);
	}
	
	@Override
	public List<Recommendation> findRecommendationsForSelectedModelElement(OntologyEntity selectedModelElement, IOIDABridge oidaBridge) {
		List<Recommendation> recommendations = new ArrayList<Recommendation>();

		String selectedElementName = getSearchName(selectedModelElement);
		if (selectedElementName == null || selectedElementName.equals(StringConstants.EMPTY))
			return recommendations;
		
		List<T> recommendedEntities = getSearchEntityList().stream().filter(e -> e.getName().toLowerCase().contains(selectedElementName.toLowerCase())).collect(Collectors.toList());
		
		for (T entity : recommendedEntities) {
			Recommendation r = BridgemodelFactory.eINSTANCE.createRecommendation();
			r.setRecommendedEntity(entity);
			r.setRecommenderName(getRecommenderName());
			r.setRecommendationType(getRecommendationType());
			r.setRecommenderMessage("'" + entity.getName() + "' contains '" + selectedElementName + "'");
			recommendations.add(r);
		}

		return recommendations;
	}
	
	protected abstract String getSearchName(OntologyEntity entity);
	
	protected abstract String getRecommenderName();
	
	protected abstract List<T> getSearchEntityList();
	
	protected abstract RecommendationType getRecommendationType();
}
