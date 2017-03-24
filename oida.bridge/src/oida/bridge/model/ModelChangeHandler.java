package oida.bridge.model;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import oida.bridge.model.helper.Extractor;
import oida.bridge.model.renamer.IRenamerStrategy;
import oida.bridge.model.renamer.IStructuringStrategy;
import oida.bridge.service.IOIDABridge.OntologyObjectProperties;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyIndividual;
import oida.ontology.OntologyObjectPropertyAssertion;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.OntologyManagerException;
import oida.ontology.service.IOIDAOntologyService;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-03-07
 *
 */
public class ModelChangeHandler extends AbstractModelChangeHandler {
	private final String MSG_PREFIX = "OIDA Model change handler: ";

	private final String SYMO_MODELONT_NS = "http://oida.local.";
	private final String MODELONT_PREFIX = "modont";

	private HashMap<EObject, OntologyEntity> emfToOntologyMap = new HashMap<EObject, OntologyEntity>();
	private IRenamerStrategy renamerStrategy;

	private EObject modelObject;

	public EObject getModelObject() {
		return modelObject;
	}

	@Override
	public void initializeModelOntology(EObject modelObject, IRenamerStrategy renamerStrategy, IStructuringStrategy structuringStrategy) {
		this.modelObject = modelObject;
		this.modelObject.eAdapters().add(this);

		emfToOntologyMap.clear();
		this.renamerStrategy = renamerStrategy;

		setStructuringStrategy(structuringStrategy);

		generateLocalNamespace();
		generateOntologyClasses();
		generateIndividuals();
		generateObjectProperties();

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
	 * This methods generates ontology classes representing model classes which have instance objects in the model.
	 */
	private void generateOntologyClasses() {
		for (EClass eClass : Extractor.getAllClassesOfInstanceEObjects(modelObject))
			createOntologyClassHierarchyForModelElement(eClass, getModelOntologyManager());
	}

	/**
	 * This method extracts instances as individuals in the tree under a given EObject.
	 */
	private void generateIndividuals() {
		List<EObject> comprisedEObjects = Extractor.getInstanceEObjects(modelObject);

		// Create Individuals
		for (EObject eObject : comprisedEObjects) {
			OntologyClass oCl = (OntologyClass)emfToOntologyMap.get(eObject.eClass());
			createIndividualForModelElement(oCl, eObject, getModelOntologyManager());
		}
	}

	private void generateObjectProperties() {
		List<EObject> comprisedEObjects = Extractor.getInstanceEObjects(modelObject);

		for (EObject eObject : comprisedEObjects) {
			System.out.println(MSG_PREFIX + "Object Properties for '" + eObject.toString() + "'");
			OntologyIndividual object = (OntologyIndividual)emfToOntologyMap.get(eObject);
			List<EReference> references = Extractor.getAllEReferences(eObject);

			for (EReference ref : references) {
				System.out.println(MSG_PREFIX + "Reference '" + ref.getName() + "':");
				// TODO Dirty:
				try {
					List<EObject> referencedObjects = (List<EObject>)eObject.eGet(ref);

					if (referencedObjects != null) {
						for (EObject refObject : referencedObjects) {
							System.out.println(MSG_PREFIX + "Reference '" + refObject.toString() + "':");
							OntologyIndividual individual = (OntologyIndividual)emfToOntologyMap.get(refObject);
							OntologyObjectProperties objectProperty = getStructuringStrategy().determineObjectPropertyRelation(ref);
							createObjectPropertyAssertion(objectProperty, object, individual);
						}
					}
				} catch(Exception e) {}
			}
		}
	}

	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);

		switch (notification.getEventType()) {
		case Notification.ADD:
			OntologyIndividual ontologyIndividual = createIndividualAndClassesForModelObject((EObject)notification.getNewValue());
			OntologyIndividual object = (OntologyIndividual)emfToOntologyMap.get(notification.getNotifier());
			OntologyObjectProperties objectProperty = getStructuringStrategy().determineObjectPropertyRelation((EStructuralFeature)notification.getFeature());
			createObjectPropertyAssertion(objectProperty, object, ontologyIndividual);
			break;
		case Notification.SET:
			if (notification.getFeature() instanceof EAttribute) {
				EAttribute attr = (EAttribute)notification.getFeature();

				switch (getStructuringStrategy().determineStructuringAdviceAfterSet(attr)) {
				case CHANGE_DATATYPEPROPERTY:
					System.out.println(MSG_PREFIX + "ToDo: Change property!" + notification.getFeature().toString());
					break;
				case RENAME_INDIVIDUAL:
					getModelOntologyManager().renameEntity(emfToOntologyMap.get(notification.getNotifier()), renamerStrategy.getEObjectName((EObject)notification.getNotifier()));
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
			OntologyClass oCl = ontologyManager.createClass(renamerStrategy.getEClassName(eClass), MODELONT_PREFIX);
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
			OntologyIndividual oIn = ontologyManager.createIndividualOfClass(renamerStrategy.getEObjectName(newValue), MODELONT_PREFIX, ontologyClass);
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

	private OntologyObjectPropertyAssertion createObjectPropertyAssertion(OntologyObjectProperties objectProperty, OntologyIndividual individual, OntologyIndividual referencedObject) {
		if (objectProperty != null) {
			switch (objectProperty) {
			case HAS_PART:
				return getModelOntologyManager().createObjectPropertyAssertion(getOntologyService().getMereology().getHasPartDirectlyObjectProperty(), individual, referencedObject);
			case HAS_PARAMETER:
				return getModelOntologyManager().createObjectPropertyAssertion(getOntologyService().getMereology().getHasPartDirectlyObjectProperty(), individual, referencedObject);
			default:
				return getModelOntologyManager().createObjectPropertyAssertion(getOntologyService().getMereology().getHasPartDirectlyObjectProperty(), individual, referencedObject);
			}
		} else
			return null;
	}
}
