package oida.bridge.recommender.name;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.Notification;

import bridgemodel.BridgemodelFactory;
import bridgemodel.Recommendation;
import oida.bridge.recommend.AbstractRecommender;
import oida.ontology.Ontology;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyIndividual;
import oida.ontology.OntologyPackage;
import oida.util.constants.StringConstants;

/**
 * @author Michael Shamiyeh
 * @since 2017-03-09
 *
 */
public class NameRecommender extends AbstractRecommender {
	private final String NAME = "Name Recommender";
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
	public List<Recommendation> findRecommendationsForSelectedModelElement(OntologyEntity selectedModelElement) {
		List<Recommendation> recommendations = new ArrayList<Recommendation>();

		String selectedElementName = selectedModelElement.getName();

		if (selectedElementName == null || selectedElementName.equals(StringConstants.EMPTY))
			return recommendations;
		
		List<OntologyClass> recommendedClasses = getReferenceOntology().getClasses().stream().filter(c -> c.getName().toLowerCase().contains(selectedElementName.toLowerCase())).collect(Collectors.toList());

		for (OntologyClass c: recommendedClasses) {
			Recommendation r = BridgemodelFactory.eINSTANCE.createRecommendation();
			r.setRecommendedEntity(c);
			r.setRecommenderName(NAME);
			r.setRecommenderMessage("'" + c.getName() + "' contains '" + selectedElementName + "'");
			recommendations.add(r);
		}

		return recommendations;
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
