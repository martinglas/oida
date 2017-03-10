package oida.bridge.recommender.name;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;

import oida.bridge.recommend.AbstractRecommender;
import oida.ontology.Ontology;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyIndividual;
import oida.ontology.OntologyPackage;

/**
 * @author Michael Shamiyeh
 * @since 2017-03-09
 *
 */
public class NameRecommender extends AbstractRecommender {
	private Map<String, OntologyEntity> names = new HashMap<String, OntologyEntity>();

	@Override
	public void initializeRecommenderForModel(Ontology observedModelOntology, Ontology referenceOntology) {
		setModelOntology(observedModelOntology);
		setReferenceOntology(referenceOntology);

		names.clear();
		for (OntologyIndividual individual : getReferenceOntology().getIndividuals())
			names.put(individual.getName(), individual);

		connectReferenceOntologyAdapter();
	}

	@Override
	public OntologyEntity findRecommendationsForSelectedModelElement(OntologyEntity selectedModelElement) {
		String selectedElementName = selectedModelElement.getName();

		if (names.containsKey(selectedElementName))
			return names.get(selectedElementName);

		return null;
	}

	@Override
	public void notifyChanged(Notification notification) {
		switch (notification.getEventType()) {
		case Notification.ADD:
			if (notification.getFeature().equals(OntologyPackage.ONTOLOGY__INDIVIDUALS)) {
				names.put(((OntologyIndividual)notification.getNewValue()).getName(), (OntologyIndividual)notification.getNewValue());
			}
			break;
		case Notification.REMOVE:
			if (notification.getFeature().equals(OntologyPackage.ONTOLOGY__INDIVIDUALS)) {
				names.remove(((OntologyIndividual)notification.getNewValue()).getName());
			}
			break;
		}
	}
}
