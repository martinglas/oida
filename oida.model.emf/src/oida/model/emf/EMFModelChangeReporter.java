package oida.model.emf;

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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;

import oida.bridge.IOIDABridge;
import oida.bridge.model.changereporter.IModelChangeReporter;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-03-03
 * 
 */
public class EMFModelChangeReporter extends EContentAdapter implements IModelChangeReporter<EObject> {
	private IOIDABridge bridge;
	
	private List<EObject> eObjectList = new ArrayList<EObject>();

	private static IModelChangeReporter<EObject> instance;
	public static IModelChangeReporter<EObject> getInstance() {
		if (instance == null)
			instance = new EMFModelChangeReporter();
		
		return instance;
	}
	
	private EMFModelChangeReporter() {
	}

	@Override
	public void registerOIDABridge(IOIDABridge bridge) {
		this.bridge = bridge;
	}

	@Override
	public void addModelForObservation(EObject modelObject) {
		modelObject.eResource().eAdapters().add(this);
		this.eObjectList.add(modelObject);
	}

	@Override
	public void releaseObservedModel(EObject modelObject) {
		if (this.eObjectList.contains(modelObject)) {
			this.eObjectList.remove(modelObject);
			modelObject.eResource().eAdapters().remove(this);
		}
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		switch (notification.getEventType()) {
		case ADD:
			bridge.handleEntityAddedEvent();
			break;
		case ADD_MANY:
			break;
		case MOVE:
			break;
		case NO_INDEX:
			break;
		case REMOVE:
			break;
		case REMOVE_MANY:
			break;
		case REMOVING_ADAPTER:
			break;
		case RESOLVE:
			break;
		case SET:
			break;
		case UNSET:
			break;
		default:
			System.out.println("Uncovered notification type: " + notification.getEventType());
			break;
		}
	}
}
