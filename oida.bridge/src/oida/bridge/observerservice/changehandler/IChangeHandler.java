package oida.bridge.observerservice.changehandler;

import org.eclipse.emf.common.notify.Notification;

import oida.ontology.manager.IOntologyManager;

public interface IChangeHandler {

	void handleAdd(Notification notification);

	void handleAddMany(Notification notification);

	void handleMove(Notification notification);

	void handleNoIndex(Notification notification);

	void handleRemove(Notification notification);

	void handleRemoveMany(Notification notification);

	void handleRemovingAdapter(Notification notification);

	void handleResolve(Notification notification);

	void handleSet(Notification notification);

	void handleUnset(Notification notification);

	void setOntologyManager(IOntologyManager owlOntologyManager);
	
	

}
