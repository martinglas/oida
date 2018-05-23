/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
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

import net.bhl.oida.bridge.model.changehandler.AbstractModelChangeHandler;
import net.bhl.oida.ontology.manager.IOntologyManager;
import net.bhl.oida.ontology.model.owlontology.AOntologyItem;
import net.bhl.oida.ontology.model.owlontology.OntologyClass;
import net.bhl.oida.ontology.model.owlontology.OntologyIndividual;
import net.bhl.oida.ontology.model.owlontology.OntologyObjectProperty;
import oida.bridge.model.emf.changehandler.util.Extractor;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-03-07
 *
 */
public final class EMFModelChangeHandler extends AbstractModelChangeHandler {
    protected static Logger LOGGER = LoggerFactory.getLogger(EMFModelChangeHandler.class);

    private final String MSG_PREFIX = "OIDA Model change handler: ";

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
		    // OntologyIndividual ontologyIndividual =
		    // createIndividualAndClassesForModelObject((EObject)notification.getNewValue());
		    // OntologyIndividual object =
		    // (OntologyIndividual)getOntologyEntityForModelElement(notification.getNotifier());
		    // OntologyObjectProperties objectProperty =
		    // getStructuringStrategy().determineObjectPropertyRelation((EStructuralFeature)notification.getFeature());
		    // createObjectPropertyAssertion(objectProperty, object,
		    // ontologyIndividual);
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
	List<EObject> comprisedEObjects = Extractor.getInstanceEObjects(getModelObjectInternal());

	generateIndividuals(comprisedEObjects);

	for (EObject eObject : comprisedEObjects)
	    createOntologyObjectPropertyAssignment(eObject, getModelOntologyManager());

	return modelOntologyManager;
    }

    @Override
    public void closeModelOntology() {
    }

    /**
     * This method extracts instances as individuals in the tree under a given
     * EObject.
     */
    private void generateIndividuals(List<EObject> comprisedEObjects) {
	for (EObject eObject : comprisedEObjects) {
	    Optional<OntologyClass> optOntologyClass = getMetaModelOntologyManager().getClass(getRenamerStrategy().getClassName(eObject.eClass()), getMetaModelOntologyManager().getDefaultNamespace());

	    if (optOntologyClass.isPresent())
		createIndividualForModelObject(eObject, optOntologyClass.get());
	}
    }

    private OntologyClass createOntologyClassHierarchyForModelElement(EClass eClass, IOntologyManager ontologyManager) {
	Optional<AOntologyItem> optOntologyClass = getOntologyEntityForModelElement(eClass);

	if (!optOntologyClass.isPresent()) {
	    OntologyClass oCl = createOntologyClassForMetaModelClass(eClass);

	    for (EClass superClass : eClass.getESuperTypes()) {
		OntologyClass superOCl = createOntologyClassHierarchyForModelElement(superClass, ontologyManager);
		ontologyManager.assignSubClassToSuperClass(oCl, superOCl);
	    }

	    return oCl;
	} else
	    return (OntologyClass)optOntologyClass.get();
    }

    private OntologyIndividual createIndividualAndClassesForModelObject(EObject modelObject) {
	OntologyClass ontRefClass = createOntologyClassHierarchyForModelElement(modelObject.eClass(), getModelOntologyManager());
	OntologyIndividual ontInd = createIndividualForModelObject(modelObject, ontRefClass);

	System.out.println(MSG_PREFIX + "Parent object: " + modelObject.eContainer().toString());
	System.out.println(MSG_PREFIX + "Feature name: " + modelObject.toString());

	return ontInd;
    }

    private void createOntologyObjectPropertyAssignment(EObject eObject, IOntologyManager modelOntologyManager) {
	if (eObject.eContainingFeature() == null)
	    return;

	Optional<OntologyObjectProperty> referenceObjectProperty = getOntologyObjectPropertyForModelElement(eObject.eContainingFeature());
	Optional<OntologyIndividual> containerIndividual = getOntologyIndividualForModelElement(eObject.eContainer());
	Optional<OntologyIndividual> individual = getOntologyIndividualForModelElement(eObject);

	if (referenceObjectProperty.isPresent() && containerIndividual.isPresent() && individual.isPresent())
	    modelOntologyManager.createObjectPropertyAssertion(referenceObjectProperty.get(), containerIndividual.get(), individual.get());
    }
}
