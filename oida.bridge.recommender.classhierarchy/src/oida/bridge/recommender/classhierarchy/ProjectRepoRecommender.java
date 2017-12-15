package oida.bridge.recommender.classhierarchy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bridgemodel.recommendation.Recommendation;
import oida.bridge.model.ontology.OIDAModelBaseOntology;
import oida.bridge.recommender.AbstractRecommender;
import oida.bridge.recommender.IPrimaryRecommender;
import oida.bridge.service.IOIDABridge;
import oida.ontology.Ontology;
import oida.ontology.OntologyAnnotation;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyIndividual;
import oida.util.constants.StringConstants;

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
			Optional<OntologyAnnotation> optAnnotation = entity.getAnnotations().stream().filter(a -> a.getAnnotationproperty().equals(OIDAModelBaseOntology.getInstance().getNameAnnotationProperty())).findFirst();
			
			if (optAnnotation.isPresent())
				return optAnnotation.get().getValue();
			else
				return entity.getName();
		}

		return StringConstants.EMPTY;
	}
	
	@Override
	public List<Recommendation> findRecommendationsForSelectedIndividual(OntologyIndividual selectedModelElement, IOIDABridge oidaBridge) {
		List<Recommendation> recommendations = new ArrayList<Recommendation>();

//		if (getSearchName(selectedModelElement).equals("Bergdohle")) {
//			List<OntologyClass> allClasses = oidaBridge.getReferenceOntology().get().getClasses();
//			
//			OntologyClass aircraft = oidaBridge.getReferenceOntology().get().getClass("http://www.bauhaus-luftfahrt.net/oida/referenceontology#Aircraft").get();
//			List<OntologyClass> aircraftClasses = new ArrayList<OntologyClass>();
//			
//			for(OntologyClass c : allClasses) {
//				if (c.getSuperClasses().contains(aircraft)) {
//					aircraftClasses.add(c);
//				}
//			}
//			
//			for (OntologyClass aircraftSubClass : aircraftClasses) {
//				Recommendation r = BridgemodelFactory.eINSTANCE.createRecommendation();
//
//				r.setRecommendationType(RecommendationType.EQUIVALENT_TO);
//				r.setRecommendedEntity(aircraftSubClass);
//				r.setRecommenderMessage(aircraftSubClass.getName() + " is a Aircraft.");
//				r.setReliability(100);
//				r.setRecommenderName(getName());
//
//				recommendations.add(r);
//			}
//		}
		
		return recommendations;
	}

}
