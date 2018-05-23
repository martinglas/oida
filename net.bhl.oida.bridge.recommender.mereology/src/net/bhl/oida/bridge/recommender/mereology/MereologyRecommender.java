package net.bhl.oida.bridge.recommender.mereology;

import java.util.ArrayList;
import java.util.List;

import net.bhl.oida.bridge.model.recommendation.Recommendation;
import net.bhl.oida.bridge.recommender.IPrimaryRecommender;
import net.bhl.oida.bridge.service.OIDABridge;
import net.bhl.oida.ontology.model.owlontology.Ontology;
import net.bhl.oida.ontology.model.owlontology.OntologyIndividual;

/**
 * 
 * @author Michael Shamiyeh
 * @since 26.04.2018
 *
 */
public class MereologyRecommender implements IPrimaryRecommender {

    public MereologyRecommender() { }

    @Override
    public void initializeRecommenderForModel(Ontology observedModelOntology, Ontology referenceOntology) {
	// TODO Auto-generated method stub
    }

    @Override
    public List<Recommendation> findRecommendationsForSelectedIndividual(OntologyIndividual selectedModelElement, OIDABridge oidaBridge) {
	List<Recommendation> list = new ArrayList<Recommendation>();
	
	selectedModelElement.getObjectPropertyAssertions();
	
	return list;
    }
}
