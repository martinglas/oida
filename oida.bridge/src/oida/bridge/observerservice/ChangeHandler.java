package oida.bridge.observerservice;

import static org.eclipse.emf.common.notify.Notification.ADD;
import static org.eclipse.emf.common.notify.Notification.ADD_MANY;
import static org.eclipse.emf.common.notify.Notification.MOVE;
import static org.eclipse.emf.common.notify.Notification.NO_INDEX;
import static org.eclipse.emf.common.notify.Notification.REMOVE;
import static org.eclipse.emf.common.notify.Notification.REMOVE_MANY;
import static org.eclipse.emf.common.notify.Notification.REMOVING_ADAPTER;
import static org.eclipse.emf.common.notify.Notification.RESOLVE;
import static org.eclipse.emf.common.notify.Notification.SET;
import static org.eclipse.emf.common.notify.Notification.UNSET;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import de.symo.model.base.ANameItem;
import oida.bridge.symo.SymoHelper;
import oida.ontology.manager.IOntologyManager;

public class ChangeHandler {

	private static IOntologyManager manager;

	public static void setOntologyManager(IOntologyManager manager) {
		ChangeHandler.manager = manager;
	}

	public static void handle(Notification notification) {
		switch (notification.getEventType()) {

		case ADD: {
			String newElementName = "";
			if (notification.getNewValue() instanceof ANameItem) {
				newElementName = ((ANameItem)notification.getNewValue()).getName();
			}
			System.out.println("Element " + newElementName + " has been created");

			// Get root container
			if (notification.getNewValue() instanceof EObject) {
				EObject rootContainer = SymoHelper.getRootContainer((EObject)notification.getNewValue());
				String ModelOntologyName = SymoHelper.getElementName(rootContainer);

				// GetModelManager
				// GetClass named after the eClass of the eObject

				// GetOntology named after the root container
				// GetClass

			}

			// Get model ontology

			// Add element to root ontology

		}
			break;

		case ADD_MANY: {
		}
			break;

		case MOVE: {
		}
			break;

		case NO_INDEX: {
		}
			break;

		case REMOVE: {
			String removedElementName = "";
			if (notification.getNewValue() instanceof ANameItem) {
				removedElementName = ((ANameItem)notification.getNewValue()).getName();
			}
			System.out.println("Element" + removedElementName + " has been removed");
		}
			break;

		case REMOVE_MANY: {
		}
			break;

		case REMOVING_ADAPTER: {
			System.out.println("Removing adapter");
		}
			break;

		case RESOLVE: {
		}
			break;

		case SET: {
			String featureName = "";
			String changedElementName = "";
			String oldValue = "";
			String newValue = "";
			if (notification.getFeature() instanceof EStructuralFeature) {
				EStructuralFeature feature = ((EStructuralFeature)notification.getFeature());
				featureName = feature.getName();
			}
			if (notification.getNotifier() instanceof ANameItem) {
				changedElementName = ((ANameItem)notification.getNotifier()).getName();
			}
			if (notification.getOldValue() != null) {
				oldValue = notification.getOldValue().toString();
			}
			if (notification.getNewValue() != null) {
				newValue = notification.getNewValue().toString();
			}

			System.out.println("Attribute " + featureName + " of Element " + changedElementName + " has been changed from " + oldValue + " to " + newValue);
		}
			break;

		case UNSET: {
		}
			break;

		default:
			System.out.println("Uncovered notification type: " + notification.getEventType());
			break;

		}

	}

}
