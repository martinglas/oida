package oida.bridge.observerservice.changehandler;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;

import oida.ontology.manager.IOntologyManager;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-01-20
 *
 */
public interface IChangeHandler {
	void initializeModelOntology(EObject modelContainer, IOntologyManager ontManager);

	void handleAdd(Notification notification, IOntologyManager ontManager);

	void handleAddMany(Notification notification, IOntologyManager ontManager);

	void handleMove(Notification notification, IOntologyManager ontManager);

	void handleNoIndex(Notification notification, IOntologyManager ontManager);

	void handleRemove(Notification notification, IOntologyManager ontManager);

	void handleRemoveMany(Notification notification, IOntologyManager ontManager);

	void handleRemovingAdapter(Notification notification, IOntologyManager ontManager);

	void handleResolve(Notification notification, IOntologyManager ontManager);

	void handleSet(Notification notification, IOntologyManager ontManager);

	void handleUnset(Notification notification, IOntologyManager ontManager);
}
