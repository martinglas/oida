package oida.bridge.recommender.mereology;

import java.util.List;

import bridgemodel.recommendation.Recommendation;
import oida.bridge.recommender.IPrimaryRecommender;
import oida.bridge.service.OIDABridge;
import oida.ontology.Ontology;
import oida.ontology.OntologyIndividual;

/**
 * 
 * @author Michael Shamiyeh
 * @since 26.04.2018
 *
 */
public class MereologyRecommender implements IPrimaryRecommender {

    public MereologyRecommender() {
	// TODO Auto-generated constructor stub
    }

    @Override
    public void initializeRecommenderForModel(Ontology observedModelOntology, Ontology referenceOntology) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public List<Recommendation> findRecommendationsForSelectedIndividual(OntologyIndividual selectedModelElement, OIDABridge oidaBridge) {
	// TODO Auto-generated method stub
	return null;
    }
}
