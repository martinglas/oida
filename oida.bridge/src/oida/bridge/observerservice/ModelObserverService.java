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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;

import oida.bridge.observerservice.changehandler.IChangeHandler;
import oida.ontology.manager.IOntologyManager;

/**
 * 
 * @author Martin Glas, Michael Shamiyeh
 * @since 2017-01-13
 *
 */
public class ModelObserverService extends EContentAdapter implements IModelObserverService {
	private static ModelObserverService instance;

	public static ModelObserverService getInstance() {
		if (instance == null)
			instance = new ModelObserverService();

		return instance;
	}

	private IChangeHandler changeHandler;
	
	public void setChangeHandler(IChangeHandler changeHandler) {
		this.changeHandler = changeHandler;
	}

	private Map<EObject, IOntologyManager> modelOntologies;

	private ModelObserverService() {
		modelOntologies = new HashMap<EObject, IOntologyManager>();
	}

	public void addModelForObservation(EObject componentRepository, IOntologyManager ontologyManager) {
		componentRepository.eResource().eAdapters().add(this);

		System.out.println("OIDA Bridge: Model '" + componentRepository.toString() + "' observed.");

		modelOntologies.put(componentRepository, ontologyManager);
	}

	@Override
	public void notifyChanged(Notification notification) {
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
		// ChangeHandler.setOntologyManager(mgr);

		// System.out.println("OIDA Bridge: Notfication received from the data
		// model '" + notification.getNotifier().toString() + "'. Data model has
		// changed!!!");
		// ChangeHandler.handle(notification);
	}
}
