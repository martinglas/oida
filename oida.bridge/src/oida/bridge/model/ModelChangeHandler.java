package oida.bridge.model;

import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import oida.bridge.model.helper.Extractor;
import oida.bridge.model.helper.Renamer;
import oida.bridge.model.renamer.IRenamerStrategy;
import oida.bridge.model.renamer.IStructuringStrategy;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyIndividual;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.OntologyManagerException;

public class ModelChangeHandler extends AbstractModelChangeHandler {
	private final String MSG_PREFIX = "OIDA Model change handler: ";
	
	private final String SYMO_MODELONT_NS = "http://oida.local.";
	private final String MODELONT_PREFIX = "modont";

	private HashMap<EObject, OntologyEntity> emfToOntologyMap = new HashMap<EObject, OntologyEntity>();
	private Renamer renamer;

	private EObject modelObject;

	public EObject getModelObject() {
		return modelObject;
	}

	@Override
	public void initializeModelOntology(EObject modelObject, IRenamerStrategy renamerStrategy, IStructuringStrategy structuringStrategy) {
		this.modelObject = modelObject;
		this.modelObject.eAdapters().add(this);

		emfToOntologyMap.clear();
		renamer = new Renamer(this.modelObject, renamerStrategy);
		
		setStructuringStrategy(structuringStrategy);

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

	/**
	 * This methods generates ontology classes representing model classes which
	 * have instance objects in the model.
	 */
	private void generateOntologyClasses() {
		for (EClass eClass : Extractor.getAllClassesOfInstanceEObjects(modelObject))
			createOntologyClassHierarchyForModelElement(eClass, getModelOntologyManager());
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
			createIndividualForModelElement(oCl, eObject, getModelOntologyManager());
		}

		// // Create properties between individuals
		// for (EObject eObject : comprisedEObjects) {
		// OntologyIndividual currentIndividual = emfToOntologyMap.get(eObject);
		// //URI individualURI = OntologyHelper.generateURI(targetOntology,
		// renamer.getEObjectName(eObject));
		//
		// for (EStructuralFeature eStructuralFeature :
		// eObject.eClass().getEAllStructuralFeatures()) {
		//
		// if (eStructuralFeature instanceof EReference) {
		// EReference eReference = (EReference)eStructuralFeature;
		//
		// URI objectPropertyURI = OntologyHelper.generateURI(targetOntology,
		// renamer.getEStructuralFeatureName(eReference));
		// Object referenceTargetObject = eObject.eGet(eReference);
		// // Handle reference with cardinality greater 1
		// if (referenceTargetObject instanceof EList) {
		// EList<EObject> referenceObjectList =
		// (EList<EObject>)referenceTargetObject;
		// for (EObject targetEObject : referenceObjectList) {
		// URI targetIndividualURI = OntologyHelper.generateURI(targetOntology,
		// renamer.getEObjectName(targetEObject));
		//
		// ontologyModel.addObjectPropertyInstance(individualURI,
		// objectPropertyURI, targetIndividualURI);
		// }
		// }
		// // Handle reference with cardinality 1
		// else if (referenceTargetObject instanceof EObject) {
		// EObject referenceEObject = (EObject)referenceTargetObject;
		// URI targetIndividualURI = OntologyHelper.generateURI(targetOntology,
		// renamer.getEObjectName(referenceEObject));
		//
		// ontologyModel.addObjectPropertyInstance(individualURI,
		// objectPropertyURI, targetIndividualURI);
		// }
		// }
		// // Handle attribute
		// else if (eStructuralFeature instanceof EAttribute) {
		// EAttribute eAttribute = (EAttribute)eStructuralFeature;
		// Object eAttributeValue = "";
		// if (eObject.eGet(eAttribute) != null) {
		// eAttributeValue = eObject.eGet(eAttribute);
		// }
		//
		// URI datatypePropertyURI = OntologyHelper.generateURI(targetOntology,
		// renamer.getEStructuralFeatureName(eAttribute));
		//
		// Literal literal =
		// ontologyModel.getOntologyModel().createTypedLiteral(eAttributeValue);
		// // ontologyModel.addDatatypePropertyInstance(individualURI,
		// // datatypePropertyURI, eAttributeValue);
		// ontologyModel.addDatatypePropertyInstance(individualURI,
		// datatypePropertyURI, literal);
		// }
		// }
		// }
	}

	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
		
		switch (notification.getEventType()) {
		case Notification.ADD:
			OntologyIndividual ontologyIndividual = createIndividualAndClassesForModelObject((EObject)notification.getNewValue());
			
			getStructuringStrategy().determineObjectPropertyRelation((EObject)notification.getNewValue());
			
			break;
		case Notification.SET:
			if (notification.getFeature() instanceof EAttribute) {
				EAttribute attr = (EAttribute)notification.getFeature();
				
				switch (getStructuringStrategy().determineStructuringAdviceAfterSet(attr)) {
				case CHANGE_DATATYPEPROPERTY:
					System.out.println(MSG_PREFIX + "ToDo: Change property!" + notification.getFeature().toString());
					break;
				case RENAME_INDIVIDUAL:
					getModelOntologyManager().renameEntity(emfToOntologyMap.get(notification.getNotifier()), renamer.getEObjectName((EObject)notification.getNotifier()));
					System.out.println(MSG_PREFIX + "Renamed individual: " + notification.getNotifier().toString());
					break;
				default:
					break;
				}
			} else if (notification.getFeature() instanceof EReference) {
				createIndividualAndClassesForModelObject((EObject)notification.getNewValue());
			}
			
			break;
		case Notification.REMOVE:
			System.out.println("MSG_PREFIX + Model Element: REMOVE.");
			break;
		case Notification.UNSET:
			break;
		}
	}
	
	private OntologyClass createOntologyClassHierarchyForModelElement(EClass eClass, IOntologyManager ontologyManager) {
		if (!emfToOntologyMap.containsKey(eClass)) {
			OntologyClass oCl = ontologyManager.createClass(renamer.getEClassName(eClass), MODELONT_PREFIX);
			emfToOntologyMap.put(eClass, oCl);
			System.out.println(MSG_PREFIX + "Class created: '" + oCl.getName() + "'.");

			for (EClass superClass : eClass.getESuperTypes()) {
				OntologyClass superOCl = createOntologyClassHierarchyForModelElement(superClass, ontologyManager);
				ontologyManager.assignSubClassToSuperClass(oCl, superOCl);
			}

			return oCl;
		} else
			return (OntologyClass)emfToOntologyMap.get(eClass);
	}

	private OntologyIndividual createIndividualForModelElement(OntologyClass ontologyClass, EObject newValue, IOntologyManager ontologyManager) {
		if (!emfToOntologyMap.containsKey(newValue)) {
			OntologyIndividual oIn = ontologyManager.createIndividualOfClass(renamer.getEObjectName(newValue), MODELONT_PREFIX, ontologyClass);
			emfToOntologyMap.put(newValue, oIn);
			System.out.println(MSG_PREFIX + "Individual created: '" + oIn.getName() + "'.");
			return oIn;
		} else
			return (OntologyIndividual)emfToOntologyMap.get(newValue);
	}
	
	private OntologyIndividual createIndividualAndClassesForModelObject(EObject modelObject) {
		OntologyClass ontRefClass = createOntologyClassHierarchyForModelElement(modelObject.eClass(), getModelOntologyManager());
		OntologyIndividual ontInd = createIndividualForModelElement(ontRefClass, modelObject, getModelOntologyManager());
		
		System.out.println(MSG_PREFIX + "Parent object: " + modelObject.eContainer().toString());
		System.out.println(MSG_PREFIX + "Feature name: " + modelObject.toString());
		
		return ontInd;
	}
}
