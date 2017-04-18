package oida.bridge.recommender.name;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import bridgemodel.BridgemodelFactory;
import bridgemodel.Recommendation;
import bridgemodel.RecommendationType;
import oida.bridge.recommender.AbstractRecommender;
import oida.ontology.Ontology;
import oida.ontology.OntologyEntity;
import oida.util.constants.StringConstants;

/**
 * @author Michael Shamiyeh
 * @since 2017-03-09
 *
 */
public abstract class AbstractNameRecommender<T extends OntologyEntity> extends AbstractRecommender {
	//private Map<String, OntologyEntity> names = new HashMap<String, OntologyEntity>();

	@Override
	public void initializeRecommenderForModel(Ontology observedModelOntology, Ontology referenceOntology) {
		setModelOntology(observedModelOntology);
		setReferenceOntology(referenceOntology);

//		names.clear();
//		for (OntologyIndividual individual : getReferenceOntology().getIndividuals())
//			names.put(individual.getName(), individual);
//
//		connectReferenceOntologyAdapter();
	}
	
	@Override
	public List<Recommendation> findRecommendationsForSelectedModelElement(OntologyEntity selectedModelElement) {
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

//	@Override
//	public void notifyChanged(Notification notification) {
//		switch (notification.getEventType()) {
//		case Notification.ADD:
//			if (notification.getFeature().equals(OntologyPackage.ONTOLOGY__INDIVIDUALS)) {
//				names.put(((OntologyIndividual)notification.getNewValue()).getName(), (OntologyIndividual)notification.getNewValue());
//			}
//			break;
//		case Notification.REMOVE:
//			if (notification.getFeature().equals(OntologyPackage.ONTOLOGY__INDIVIDUALS)) {
//				names.remove(((OntologyIndividual)notification.getNewValue()).getName());
//			}
//			break;
//		}
//	}
	
	protected abstract String getSearchName(OntologyEntity entity);
	
	protected abstract String getRecommenderName();
	
	protected abstract List<T> getSearchEntityList();
	
	protected abstract RecommendationType getRecommendationType();
}
