package oida.bridge.recommender.classhierarchy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bridgemodel.BridgemodelFactory;
import bridgemodel.ClassEqualsMapping;
import bridgemodel.Recommendation;
import bridgemodel.RecommendationType;
import oida.bridge.recommender.AbstractRecommender;
import oida.bridge.recommender.IPrimaryRecommender;
import oida.bridge.service.IOIDABridge;
import oida.ontology.Ontology;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyIndividual;

public class IndividualClassRecommender extends AbstractRecommender implements IPrimaryRecommender {

	@Override
	public String getName() {
		return "Class Recommender";
	}

	@Override
	public void initializeRecommenderForModel(Ontology observedModelOntology, Ontology referenceOntology) {
		setModelOntology(observedModelOntology);
		setReferenceOntology(referenceOntology);
	}

	@Override
	public List<Recommendation> findRecommendationsForSelectedIndividual(OntologyIndividual selectedModelElement, IOIDABridge oidaBridge) {
		List<Recommendation> recommendations = new ArrayList<Recommendation>();

		for (OntologyClass modelSuperClass : ((OntologyIndividual)selectedModelElement).getTypes()) {
			Optional<ClassEqualsMapping> optMapping = oidaBridge.getClassMapping(modelSuperClass);

			if (optMapping.isPresent()) {
				for (OntologyClass referenceSubClass : optMapping.get().getClazz2().getSubClasses()) {
					Recommendation r = BridgemodelFactory.eINSTANCE.createRecommendation();

					r.setRecommendationType(RecommendationType.EQUIVALENT_TO);
					r.setRecommendedEntity(referenceSubClass);
					r.setRecommenderMessage(selectedModelElement.getName() + " is an instance of " + modelSuperClass.getName() + ". " + modelSuperClass.getName() + " is equal to "
							+ optMapping.get().getClazz2().getName() + ". " + referenceSubClass.getName() + " is a sub class of " + optMapping.get().getClazz2().getName() + ".");
					r.setReliability(100);
					r.setRecommenderName(getName());

					recommendations.add(r);
				}
			}
		}
		
		return recommendations;
	}

}
