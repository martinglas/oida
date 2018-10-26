package net.bhl.oida.bridge.recommender.mereology;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.bhl.oida.bridge.model.mapping.InstanceOfMapping;
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
import net.bhl.oida.ontology.model.owlontology.OntologyObjectPropertyAssertion;
import net.bhl.oida.ontology.model.owlontology.OntologyObjectSomeValuesFrom;
import net.bhl.oida.ontology.predefined.Mereology;
import net.bhl.oida.util.constants.StringConstants;

/**
 * 
 * @author Michael Shamiyeh
 * @since 26.04.2018
 *
 */
public class MereologyRecommender extends AbstractRecommender implements IPrimaryRecommender {

    public MereologyRecommender() {
    }

    @Override
    public void initializeRecommenderForModel(Ontology observedModelOntology, Ontology referenceOntology) {
	setModelOntology(observedModelOntology);
	setReferenceOntology(referenceOntology);
    }

    @Override
    public List<Recommendation> findRecommendationsForSelectedIndividual(OntologyIndividual selectedModelElement, OIDABridge oidaBridge) {
	List<Recommendation> list = new ArrayList<Recommendation>();

	for (OntologyObjectPropertyAssertion assertion : selectedModelElement.getObjectPropertyAssertions()) {
	    if (assertion.getObjectProperty().getIri().equals(Mereology.getInstance().getPartOfDirectlyObjectProperty().getIri())) {
		Optional<InstanceOfMapping> mapping = oidaBridge.getMapping(assertion.getObject());

		if (mapping.isPresent()) {
		    System.out.println("Parent Component " + selectedModelElement.getName() + " is mapped to " + mapping.get().getClazz().getName());

		    for (OntologyClass c : getReferenceOntology().getClasses()) {
			for (OntologyObjectSomeValuesFrom someValFrom : c.getSomeValuesFrom()) {
			    if (someValFrom.getClazz().getIri().equals(mapping.get().getClazz().getIri()) && someValFrom.getObjectProperty().getIri().contains("in_category_part-of")) {
				Recommendation r = RecommendationFactory.eINSTANCE.createRecommendation();

				r.setRecommendationType(RecommendationType.INSTANCE_OF);
				r.setRecommendedEntity(c);
				r.setRecommenderMessage("The parent component of" + StringConstants.NEWLINE + 
					"'" + getSearchName(selectedModelElement) + "' is" + StringConstants.NEWLINE + 
					"'" + getSearchName(mapping.get().getIndividual())+ "'." + StringConstants.NEWLINE + 
					"It is mapped to the Class '" + mapping.get().getClazz().getName() + "'." + StringConstants.NEWLINE + 
					"'" + c.getName() + "' " + StringConstants.NEWLINE + 
					"is marked as a sub component of " + StringConstants.NEWLINE + 
					"'" + mapping.get().getClazz().getName() + "'" + StringConstants.NEWLINE +
					"in the knowledge-base.");
				r.setReliability(100);
				r.setRecommenderName(getName());
				
				list.add(r);
			    }
			}
		    }
		}
	    }
	}

	return list;
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
    public String getName() {
	return "Part-Of Recommender";
    }
}
