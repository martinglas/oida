package oida.bridge.observerservice.changehandler;

import org.eclipse.emf.common.notify.Notification;

import oida.ontology.manager.IOntologyManager;

/**
 * 
 * @author Michael Shamiyeh, Martin Glas
 * @since 2017-01-20
 *
 */
public interface IChangeHandler {
	void setOntologyManager(IOntologyManager manager);
	
	void handle(Notification notification);
	
	void handleAdd(Notification notification);
}
