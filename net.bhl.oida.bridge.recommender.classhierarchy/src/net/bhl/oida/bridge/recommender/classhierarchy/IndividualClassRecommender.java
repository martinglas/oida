package net.bhl.oida.bridge.recommender.classhierarchy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.bhl.oida.bridge.model.mapping.ClassEqualsMapping;
import net.bhl.oida.bridge.model.ontology.OIDAModelBaseOntology;
import net.bhl.oida.bridge.model.recommendation.Recommendation;
import net.bhl.oida.bridge.model.recommendation.RecommendationFactory;
import net.bhl.oida.bridge.model.recommendation.RecommendationType;
import net.bhl.oida.bridge.recommender.AbstractRecommender;
import net.bhl.oida.bridge.recommender.IPrimaryRecommender;
import net.bhl.oida.bridge.service.OIDABridge;
import net.bhl.oida.ontology.model.owlontology.Ontology;
import net.bhl.oida.ontology.model.owlontology.OntologyAnnotation;
import net.bhl.oida.ontology.model.owlontology.OntologyClass;
import net.bhl.oida.ontology.model.owlontology.OntologyEntity;
import net.bhl.oida.ontology.model.owlontology.OntologyIndividual;
import net.bhl.oida.util.constants.StringConstants;

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
    public List<Recommendation> findRecommendationsForSelectedIndividual(OntologyIndividual selectedModelElement, OIDABridge oidaBridge) {
	List<Recommendation> recommendations = new ArrayList<Recommendation>();

	if (((OntologyIndividual)selectedModelElement).getTypes().size() > 0) {
	    OntologyClass modelSuperClass = ((OntologyIndividual)selectedModelElement).getTypes().get(0);
	    Optional<ClassEqualsMapping> optMapping = oidaBridge.getMapping(modelSuperClass);

	    if (optMapping.isPresent()) {
		for (OntologyClass referenceSubClass : optMapping.get().getClazz2().getSubClasses()) {
		    Recommendation r = RecommendationFactory.eINSTANCE.createRecommendation();

		    r.setRecommendationType(RecommendationType.EQUIVALENT_TO);
		    r.setRecommendedEntity(referenceSubClass);
		    r.setRecommenderMessage("'" + getSearchName(selectedModelElement) + "'" + StringConstants.NEWLINE +
			    " is an instance of" + StringConstants.NEWLINE +
			    "'" + modelSuperClass.getName() + "'. " + StringConstants.NEWLINE +
			    "'" + modelSuperClass.getName() + "'" + StringConstants.NEWLINE +
			    " is equal to " + StringConstants.NEWLINE +
			    "' "+ optMapping.get().getClazz2().getName() + "'." + StringConstants.NEWLINE +
			    "'" + referenceSubClass.getName() + "'" + StringConstants.NEWLINE +
			    "is a sub class of" + StringConstants.NEWLINE + 
			    "'" + optMapping.get().getClazz2().getName() + "'.");
		    r.setReliability(100);
		    r.setRecommenderName(getName());

		    recommendations.add(r);
		}
	    }
	}

	return recommendations;
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
}
