package oida.bridge.recommender.classhierarchy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bridgemodel.BridgemodelFactory;
import bridgemodel.ClassEqualsMapping;
import bridgemodel.Recommendation;
import bridgemodel.RecommendationType;
import oida.bridge.recommender.AbstractRecommender;
import oida.bridge.recommender.IClassRecommender;
import oida.bridge.service.IOIDABridge;
import oida.ontology.Ontology;
import oida.ontology.OntologyClass;

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
	public List<Recommendation> findRecommendationsForSelectedClass(OntologyClass selectedModelElement, IOIDABridge oidaBridge) {
		List<Recommendation> recommendations = new ArrayList<Recommendation>();

		for (OntologyClass modelSuperClass : ((OntologyClass)selectedModelElement).getSuperClasses()) {
			Optional<ClassEqualsMapping> optMapping = oidaBridge.getMapping(modelSuperClass);

			if (optMapping.isPresent()) {
				for (OntologyClass referenceSubClass : optMapping.get().getClazz2().getSubClasses()) {
					if (!referenceSubClass.isMappingExists()) {
						Recommendation r = BridgemodelFactory.eINSTANCE.createRecommendation();

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
