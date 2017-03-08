package oida.bridge.model;

import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import oida.bridge.di.annotation.PostModelOpened;
import oida.bridge.model.helper.Extractor;
import oida.bridge.model.helper.ModelProviderHelper;
import oida.bridge.model.helper.Renamer;
import oida.bridge.model.renamer.IRenamerStrategy;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyIndividual;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.OntologyManagerException;

public class ModelChangeHandler extends AbstractModelChangeHandler {
	private final String SYMO_MODELONT_NS = "http://oida.local.";
	private final String MODELONT_PREFIX = "modont";
	
	private HashMap<EObject, OntologyEntity> emfToOntologyMap = new HashMap<EObject, OntologyEntity>();
	private Renamer renamer;
	
	private EObject modelObject;
	
	public EObject getModelObject() {
		return modelObject;
	}	
	
	@Override
	@PostModelOpened
	public void initializeModelOntology(EObject modelObject, IRenamerStrategy renamerStrategy) {
		this.modelObject = modelObject;

		emfToOntologyMap.clear();
		renamer = new Renamer(this.modelObject, renamerStrategy);

		generateLocalNamespace();
		generateOntologyClasses();
		generateIndividuals();

		try {
			getModelOntologyManager().saveOntology();
		} catch (OntologyManagerException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void closeModelOntology() {
	}
	
	private void generateLocalNamespace() {
		String namespace = SYMO_MODELONT_NS + getModelOntologyManager().getOntology().getOntologyFile().getFileName();

		if (!getModelOntologyManager().isNamespaceExisting(MODELONT_PREFIX))
			getModelOntologyManager().addNamespace(MODELONT_PREFIX, namespace);
	}

	private OntologyClass generateOntologyClassWithHierarchy(EClass eClass, IOntologyManager ontologyManager) {
		if (!emfToOntologyMap.containsKey(eClass)) {
			OntologyClass oCl = ontologyManager.createClass(eClass.getName(), MODELONT_PREFIX);
			emfToOntologyMap.put(eClass, oCl);

			for (EClass superClass : eClass.getESuperTypes()) {
				OntologyClass superOCl = generateOntologyClassWithHierarchy(superClass, ontologyManager);
				ontologyManager.assignSubClassToSuperClass(oCl, superOCl);
			}

			return oCl;
		} else
			return (OntologyClass)emfToOntologyMap.get(eClass);
	}

	private OntologyIndividual generateOntologyIndividualOfClass(EObject eObject, OntologyClass ontologyClass, IOntologyManager ontologyManager) {
		if (!emfToOntologyMap.containsKey(eObject)) {
			OntologyIndividual oIn = ontologyManager.createIndividualOfClass(ModelProviderHelper.getModelElementName(eObject), MODELONT_PREFIX, ontologyClass);
			emfToOntologyMap.put(eObject, oIn);
			return oIn;
		} else
			return (OntologyIndividual)emfToOntologyMap.get(eObject);
	}

	/**
	 * This methods generates ontology classes representing model classes which
	 * have instance objects in the model.
	 */
	private void generateOntologyClasses() {
		List<EClass> classesOfInstances = Extractor.getAllClassesOfInstanceEObjects(modelObject);

		// Create all classes which have instances in the model
		for (EClass eClass : classesOfInstances) {
			OntologyClass oCl = getModelOntologyManager().createClass(eClass.getName(), MODELONT_PREFIX);
			emfToOntologyMap.put(eClass, oCl);

			System.out.println("Class created: '" + oCl.getName() + "'.");
		}

		// Create taxonomy between classes
		for (EClass eClass : classesOfInstances) {
			if (!eClass.getESuperTypes().isEmpty()) {
				OntologyClass subClass = getModelOntologyManager().getClass(eClass.getName(), MODELONT_PREFIX);

				for (EClass eSuperType : eClass.getESuperTypes()) {
					OntologyClass superClass = getModelOntologyManager().getClass(eSuperType.getName(), MODELONT_PREFIX);

					if (!subClass.getSuperClasses().contains(superClass))
						getModelOntologyManager().assignSubClassToSuperClass(subClass, superClass);
				}
			}
		}
	}

	/**
	 * This method extracts instances as individuals in the tree under a given
	 * EObject.
	 */
	public void generateIndividuals() {
		List<EObject> comprisedEObjects = Extractor.getInstanceEObjects(modelObject);

		// Create Individuals
		for (EObject eObject : comprisedEObjects) {
			OntologyClass oCl = (OntologyClass)emfToOntologyMap.get(eObject.eClass());

			OntologyIndividual oIn = getModelOntologyManager().createIndividualOfClass(renamer.getEObjectName(eObject), MODELONT_PREFIX, oCl);

			emfToOntologyMap.put(eObject, oIn);

			System.out.println("Individual created: '" + oIn.getName() + "'.");
		}

//		// Create properties between individuals
//		for (EObject eObject : comprisedEObjects) {
//			OntologyIndividual currentIndividual = emfToOntologyMap.get(eObject);
//			//URI individualURI = OntologyHelper.generateURI(targetOntology, renamer.getEObjectName(eObject));
//
//			for (EStructuralFeature eStructuralFeature : eObject.eClass().getEAllStructuralFeatures()) {
//
//				if (eStructuralFeature instanceof EReference) {
//					EReference eReference = (EReference)eStructuralFeature;
//
//					URI objectPropertyURI = OntologyHelper.generateURI(targetOntology, renamer.getEStructuralFeatureName(eReference));
//					Object referenceTargetObject = eObject.eGet(eReference);
//					// Handle reference with cardinality greater 1
//					if (referenceTargetObject instanceof EList) {
//						EList<EObject> referenceObjectList = (EList<EObject>)referenceTargetObject;
//						for (EObject targetEObject : referenceObjectList) {
//							URI targetIndividualURI = OntologyHelper.generateURI(targetOntology, renamer.getEObjectName(targetEObject));
//							
//							ontologyModel.addObjectPropertyInstance(individualURI, objectPropertyURI, targetIndividualURI);
//						}
//					}
//					// Handle reference with cardinality 1
//					else if (referenceTargetObject instanceof EObject) {
//						EObject referenceEObject = (EObject)referenceTargetObject;
//						URI targetIndividualURI = OntologyHelper.generateURI(targetOntology, renamer.getEObjectName(referenceEObject));
//
//						ontologyModel.addObjectPropertyInstance(individualURI, objectPropertyURI, targetIndividualURI);
//					}
//				}
//				// Handle attribute
//				else if (eStructuralFeature instanceof EAttribute) {
//					EAttribute eAttribute = (EAttribute)eStructuralFeature;
//					Object eAttributeValue = "";
//					if (eObject.eGet(eAttribute) != null) {
//						eAttributeValue = eObject.eGet(eAttribute);
//					}
//
//					URI datatypePropertyURI = OntologyHelper.generateURI(targetOntology, renamer.getEStructuralFeatureName(eAttribute));
//
//					Literal literal = ontologyModel.getOntologyModel().createTypedLiteral(eAttributeValue);
//					// ontologyModel.addDatatypePropertyInstance(individualURI,
//					// datatypePropertyURI, eAttributeValue);
//					ontologyModel.addDatatypePropertyInstance(individualURI, datatypePropertyURI, literal);
//				}
//			}
//		}
	}
}
