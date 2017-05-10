package oida.bridge.model.emf.changehandler;

import java.util.List;
import java.util.Optional;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oida.bridge.model.changehandler.AbstractModelChangeHandler;
import oida.bridge.model.emf.changehandler.util.Extractor;
import oida.bridge.model.emf.metamodel.ontology.EMFMetaModelOntology;
import oida.bridge.service.IOIDABridge.OntologyObjectProperties;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyIndividual;
import oida.ontology.OntologyObjectProperty;
import oida.ontology.manager.IOntologyManager;
import oida.util.constants.StringConstants;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-03-07
 *
 */
public class EMFModelChangeHandler extends AbstractModelChangeHandler {
	protected static Logger LOGGER = LoggerFactory.getLogger(EMFModelChangeHandler.class);

	private final String MSG_PREFIX = "OIDA Model change handler: ";

	private final String SYMO_MODELONT_NS = "http://oida.local.";
	// private final String MODELONT_PREFIX = "modont";
	private String MODELONT_PREFIX = StringConstants.EMPTY;

	public EMFModelChangeHandler() {
	}

	private EObject getModelObjectInternal() {
		return (EObject)getModelObject();
	}

	@Override
	protected void setModelObject(Object modelObject) {
		super.setModelObject(modelObject);
		((EObject)getModelObject()).eAdapters().add(new EContentAdapter() {
			@Override
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);

				switch (notification.getEventType()) {
				case Notification.ADD:
//					OntologyIndividual ontologyIndividual = createIndividualAndClassesForModelObject((EObject)notification.getNewValue());
//					OntologyIndividual object = (OntologyIndividual)getOntologyEntityForModelElement(notification.getNotifier());
//					OntologyObjectProperties objectProperty = getStructuringStrategy().determineObjectPropertyRelation((EStructuralFeature)notification.getFeature());
					// createObjectPropertyAssertion(objectProperty, object, ontologyIndividual);
					break;
				case Notification.SET:
					if (notification.getFeature() instanceof EAttribute) {
						EAttribute attr = (EAttribute)notification.getFeature();

						switch (getStructuringStrategy().determineStructuringAdviceAfterSet(attr)) {
						case CHANGE_DATATYPEPROPERTY:
							System.out.println(MSG_PREFIX + "ToDo: Change property!" + notification.getFeature().toString());
							break;
						case RENAME_INDIVIDUAL:
							changeIndividualName(notification.getNotifier());
							break;
						case CHANGE_NAMEANNOTATION:
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
		});
	}

	@Override
	public IOntologyManager initializeModelOntology(IOntologyManager modelOntologyManager) {
		generateIndividuals();

		return modelOntologyManager;
	}

	@Override
	public void closeModelOntology() {
	}

	private void generateLocalNamespace() {
		MODELONT_PREFIX = SYMO_MODELONT_NS + getModelOntologyManager().getOntology().getOntologyFile().getFileName();

		// if (!getModelOntologyManager().isNamespaceExisting(MODELONT_PREFIX))
		// getModelOntologyManager().addNamespace(MODELONT_PREFIX, namespace);
	}

	/**
	 * This methods generates ontology classes representing model classes which have instance objects in the model.
	 */
	private void generateOntologyClasses() {
		List<EClass> allEClasses = Extractor.getAllClassesOfInstanceEObjects(getModelObjectInternal());
		for (EClass eClass : allEClasses)
			createOntologyClassHierarchyForModelElement(eClass, getModelOntologyManager());

		for (EClass eClass : allEClasses)
			createOntologyObjectPropertiesForModelClassReference(eClass, getModelOntologyManager());
	}

	/**
	 * This method extracts instances as individuals in the tree under a given EObject.
	 */
	private void generateIndividuals() {
		List<EObject> comprisedEObjects = Extractor.getInstanceEObjects(getModelObjectInternal());

		// Create Individuals
		for (EObject eObject : comprisedEObjects) {
			OntologyClass ontologyClass = getMetaModelOntologyManager().getClass(getRenamerStrategy().getClassName(eObject.eClass()), getMetaModelOntologyManager().getDefaultNamespace());
//			Optional<OntologyClass> optOntologyClass = getOntologyClassForModelElement(eObject.eClass());
//			
//			if (optOntologyClass.isPresent())
				createIndividualForModelObject(eObject, ontologyClass);
		}

		for (EObject eObject : comprisedEObjects)
			createOntologyObjectPropertyAssignments(eObject, getModelOntologyManager());
	}

	private void generateObjectProperties() {
		List<EObject> comprisedEObjects = Extractor.getInstanceEObjects(getModelObjectInternal());

		for (EObject eObject : comprisedEObjects) {
			System.out.println(MSG_PREFIX + "Object Properties for '" + eObject.toString() + "'");
			Optional<OntologyIndividual> object = getOntologyIndividualForModelElement(eObject);
			List<EReference> references = Extractor.getAllEReferences(eObject);

			for (EReference ref : references) {
				System.out.println(MSG_PREFIX + "Reference '" + ref.getName() + "':");
				// TODO Dirty:
				try {
					List<EObject> referencedObjects = (List<EObject>)eObject.eGet(ref);

					if (referencedObjects != null) {
						for (EObject refObject : referencedObjects) {
							System.out.println(MSG_PREFIX + "Reference '" + refObject.toString() + "':");
							Optional<OntologyIndividual> individual = getOntologyIndividualForModelElement(refObject);
							OntologyObjectProperties objectProperty = getStructuringStrategy().determineObjectPropertyRelation(ref);
							// createObjectPropertyAssertion(objectProperty, object, individual);
						}
					}
				} catch (Exception e) {
				}
			}
		}
	}

	private OntologyClass createOntologyClassHierarchyForModelElement(EClass eClass, IOntologyManager ontologyManager) {
		Optional<OntologyEntity> optOntologyClass = getOntologyEntityForModelElement(eClass);
		
		if (!optOntologyClass.isPresent()) {
			OntologyClass oCl = createOntologyClassForMetaModelClass(eClass);
			
			for (EClass superClass : eClass.getESuperTypes()) {
				OntologyClass superOCl = createOntologyClassHierarchyForModelElement(superClass, ontologyManager);
				ontologyManager.assignSubClassToSuperClass(oCl, superOCl);
			}
			
			return oCl;
		}
		else
			return (OntologyClass)optOntologyClass.get();
	}

	private OntologyIndividual createIndividualAndClassesForModelObject(EObject modelObject) {
		OntologyClass ontRefClass = createOntologyClassHierarchyForModelElement(modelObject.eClass(), getModelOntologyManager());
		OntologyIndividual ontInd = createIndividualForModelObject(modelObject, ontRefClass);

		System.out.println(MSG_PREFIX + "Parent object: " + modelObject.eContainer().toString());
		System.out.println(MSG_PREFIX + "Feature name: " + modelObject.toString());

		return ontInd;
	}

	// TODO Range <-> Domain???!!!
	private void createOntologyObjectPropertiesForModelClassReference(EClass eClass, IOntologyManager ontologyManager) {
		for (EReference strFeature : eClass.getEAllReferences()) {
			String relationID = getRenamerStrategy().getRelationID(strFeature);
			OntologyObjectProperty referenceObjectProperty = createOntologyObjectPropertyForMetaModelRelation(relationID, getOntologyClassForModelElement(eClass).get());
			
			if (strFeature.getEOpposite() == null)
				ontologyManager.assignSubObjectPropertyToSuperObjectProperty(referenceObjectProperty, EMFMetaModelOntology.getInstance().getEmfReferenceObjectProperty());
			else
				ontologyManager.assignSubObjectPropertyToSuperObjectProperty(referenceObjectProperty, EMFMetaModelOntology.getInstance().getEmfReferenceBiDirectionalObjectProperty());

			if (strFeature.getEOpposite() != null && getOntologyEntityForModelElement(strFeature.getEOpposite().getEReferenceType()) == null) {
				Optional<OntologyClass> domainClass = getOntologyClassForModelElement(strFeature.getEOpposite().getEReferenceType());
				if (domainClass.isPresent())
					ontologyManager.assignObjectPropertyDomain(referenceObjectProperty, domainClass.get());
			}
		}
	}

	private void createOntologyObjectPropertyAssignments(EObject eObject, IOntologyManager modelOntologyManager) {
		if (eObject.eContainingFeature() == null)
			return;

		Optional<OntologyObjectProperty> referenceObjectProperty = getOntologyObjectPropertyForModelElement(eObject.eContainingFeature());
		Optional<OntologyIndividual> containerIndividual = getOntologyIndividualForModelElement(eObject.eContainer());
		Optional<OntologyIndividual> individual = getOntologyIndividualForModelElement(eObject);

		if (referenceObjectProperty.isPresent() && containerIndividual.isPresent() && individual.isPresent())
		
		modelOntologyManager.createObjectPropertyAssertion(referenceObjectProperty.get(), containerIndividual.get(), individual.get());
	}

	@Override
	public String getModelOntologyPrefix() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModelOntologyPrefix() {
		// TODO Auto-generated method stub

	}
}
