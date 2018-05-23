package net.bhl.oida.bridge.recommender.classhierarchy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.bhl.oida.bridge.model.ontology.OIDAModelBaseOntology;
import net.bhl.oida.bridge.model.recommendation.Recommendation;
import net.bhl.oida.bridge.recommender.AbstractRecommender;
import net.bhl.oida.bridge.recommender.IPrimaryRecommender;
import net.bhl.oida.bridge.service.OIDABridge;
import net.bhl.oida.ontology.model.owlontology.Ontology;
import net.bhl.oida.ontology.model.owlontology.OntologyAnnotation;
import net.bhl.oida.ontology.model.owlontology.OntologyEntity;
import net.bhl.oida.ontology.model.owlontology.OntologyIndividual;
import net.bhl.oida.util.constants.StringConstants;

public class ProjectRepoRecommender extends AbstractRecommender implements IPrimaryRecommender {
    @Override
    public String getName() {
	return "Class Hierarchy Recommender";
    }

    @Override
    public void initializeRecommenderForModel(Ontology observedModelOntology, Ontology referenceOntology) {
	setModelOntology(observedModelOntology);
	setReferenceOntology(referenceOntology);
    }

    protected String getSearchName(OntologyEntity entity) {
	if (entity instanceof OntologyIndividual) {
	    Optional<OntologyAnnotation> optAnnotation = entity.getAnnotations().stream().filter(a -> a.getAnnotationproperty().equals(OIDAModelBaseOntology.getInstance().getNameAnnotationProperty()))
		    .findFirst();

	    if (optAnnotation.isPresent())
		return optAnnotation.get().getValue();
	    else
		return entity.getName();
	}

	return StringConstants.EMPTY;
    }

    @Override
    public List<Recommendation> findRecommendationsForSelectedIndividual(OntologyIndividual selectedModelElement, OIDABridge oidaBridge) {
	List<Recommendation> recommendations = new ArrayList<Recommendation>();

	// if (getSearchName(selectedModelElement).equals("Bergdohle")) {
	// List<OntologyClass> allClasses =
	// oidaBridge.getReferenceOntology().get().getClasses();
	//
	// OntologyClass aircraft =
	// oidaBridge.getReferenceOntology().get().getClass("http://www.bauhaus-luftfahrt.net/oida/referenceontology#Aircraft").get();
	// List<OntologyClass> aircraftClasses = new ArrayList<OntologyClass>();
	//
	// for(OntologyClass c : allClasses) {
	// if (c.getSuperClasses().contains(aircraft)) {
	// aircraftClasses.add(c);
	// }
	// }
	//
	// for (OntologyClass aircraftSubClass : aircraftClasses) {
	// Recommendation r =
	// BridgemodelFactory.eINSTANCE.createRecommendation();
	//
	// r.setRecommendationType(RecommendationType.EQUIVALENT_TO);
	// r.setRecommendedEntity(aircraftSubClass);
	// r.setRecommenderMessage(aircraftSubClass.getName() + " is a
	// Aircraft.");
	// r.setReliability(100);
	// r.setRecommenderName(getName());
	//
	// recommendations.add(r);
	// }
	// }

	return recommendations;
    }

}
