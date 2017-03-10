import org.eclipse.emf.ecore.EObject;

import oida.bridge.recommend.IRecommender;
import oida.ontology.Ontology;
import oida.ontology.OntologyEntity;

/**
 * @author Michael Shamiyeh
 * @since 2017-03-09
 *
 */
public class DefaultRecommender implements IRecommender {
	@Override
	public void initializeRecommenderForModel(final EObject observedModel, final Ontology referenceOntology) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OntologyEntity findRecommendationsForSelectedModelElement(final EObject selectedModelElement) {
		// TODO Auto-generated method stub
		return null;
	}
}
