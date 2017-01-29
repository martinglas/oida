package oida.bridge.observerservice.changehandler;

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
import org.eclipse.emf.ecore.util.EContentAdapter;


import oida.ontology.manager.IOntologyManager;



public abstract class AbstractOidaContentAdapter extends EContentAdapter {
	protected IChangeHandler changeHandler;
	
	
	public AbstractOidaContentAdapter(IChangeHandler changeHandler, IOntologyManager ontologyManager) {
		this.changeHandler=changeHandler;
		changeHandler.setOntologyManager(ontologyManager);
			
			
	}
	
	
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
		
		switch (notification.getEventType()) {
		case ADD:
			changeHandler.handleAdd(notification);
			break;
		case ADD_MANY:
			changeHandler.handleAddMany(notification);
			break;
		case MOVE:
			changeHandler.handleMove(notification);
			break;
		case NO_INDEX:
			changeHandler.handleNoIndex(notification);
			break;
		case REMOVE:
			changeHandler.handleRemove(notification);
			break;
		case REMOVE_MANY:
			changeHandler.handleRemoveMany(notification);
			break;
		case REMOVING_ADAPTER:
			changeHandler.handleRemovingAdapter(notification);
			break;
		case RESOLVE:
			changeHandler.handleResolve(notification);
			break;
		case SET:
			changeHandler.handleSet(notification);
			break;
		case UNSET:
			changeHandler.handleUnset(notification);
			break;
		default:
			System.out.println("Uncovered notification type: " + notification.getEventType());
			break;
		}
	}
	
}
