package net.bhl.oida.bridge.recommender.classhierarchy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.bhl.oida.bridge.model.mapping.ClassEqualsMapping;
import net.bhl.oida.bridge.model.recommendation.Recommendation;
import net.bhl.oida.bridge.model.recommendation.RecommendationFactory;
import net.bhl.oida.bridge.model.recommendation.RecommendationType;
import net.bhl.oida.bridge.recommender.AbstractRecommender;
import net.bhl.oida.bridge.recommender.IClassRecommender;
import net.bhl.oida.bridge.service.OIDABridge;
import net.bhl.oida.ontology.model.owlontology.Ontology;
import net.bhl.oida.ontology.model.owlontology.OntologyClass;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2016-05-02
 *
 */
public class ClassHierarchyRecommender extends AbstractRecommender implements IClassRecommender {
    private final static String RECOMMENDERNAME = "Class Hierarchy Recommender";

    @Override
    public String getName() {
	return RECOMMENDERNAME;
    }

    @Override
    public void initializeRecommenderForMetaModel(Ontology observedMetaModelOntology, Ontology referenceOntology) {
	setModelOntology(observedMetaModelOntology);
	setReferenceOntology(referenceOntology);
    }

    @Override
    public List<Recommendation> findRecommendationsForSelectedClass(OntologyClass selectedModelElement, OIDABridge oidaBridge) {
	List<Recommendation> recommendations = new ArrayList<Recommendation>();

	for (OntologyClass modelSuperClass : ((OntologyClass)selectedModelElement).getSuperClasses()) {
	    Optional<ClassEqualsMapping> optMapping = oidaBridge.getMapping(modelSuperClass);

	    if (optMapping.isPresent()) {
		for (OntologyClass referenceSubClass : optMapping.get().getClazz2().getSubClasses()) {
		    if (!referenceSubClass.isMappingExists()) {
			Recommendation r = RecommendationFactory.eINSTANCE.createRecommendation();

			r.setRecommendationType(RecommendationType.EQUIVALENT_TO);
			r.setRecommendedEntity(referenceSubClass);
			r.setRecommenderMessage(selectedModelElement.getName() + " is a sub class of " + modelSuperClass.getName() + ". " + modelSuperClass.getName() + " is equal to "
				+ optMapping.get().getClazz2().getName() + ". " + referenceSubClass.getName() + " is a sub class of " + optMapping.get().getClazz2().getName() + ".");
			r.setReliability(100);
			r.setRecommenderName(getName());

			recommendations.add(r);
		    }
		}
	    }
	}

	return recommendations;
    }
}
