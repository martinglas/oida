package oida.bridge.observerservice.emf;

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

import javax.inject.Inject;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import oida.bridge.observerservice.changehandler.IChangeHandler;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.OntologyManagerException;
import oida.ontology.service.IOIDAOntologyService;

/**
 * 
 * @author Martin Glas, Michael Shamiyeh
 * @since 2017-01-13
 *
 */
public class EMFModelObserverService extends AbstractEMFModelObserverService {

	// Singleton:
	private static EMFModelObserverService instance;

	public static EMFModelObserverService getInstance() {
		if (instance == null)
			instance = new EMFModelObserverService();

		return instance;
	}

	private EMFModelObserverService() {
		modelOntologies = new HashMap<Resource, IOntologyManager>();
	}

	@Inject
	IOIDAOntologyService oidaService;

	private IChangeHandler changeHandler;

	public void setChangeHandler(IChangeHandler changeHandler) {
		this.changeHandler = changeHandler;
	}

	private Map<Resource, IOntologyManager> modelOntologies;

	@Override
	public void addEMFModelForObservation(EObject modelRootComponent, IOntologyManager ontologyManager) {
		changeHandler.initializeModelOntology(modelRootComponent, ontologyManager);

		try {
			ontologyManager.saveOntology();
		} catch (OntologyManagerException e) {
			e.printStackTrace();
		}

		modelRootComponent.eResource().eAdapters().add(this);

		System.out.println("OIDA Bridge: Model '" + modelRootComponent.toString() + "' observed.");

		modelOntologies.put(modelRootComponent.eResource(), ontologyManager);
	}

	@Override
	public void notifyChanged(Notification notification) {
		IOntologyManager modelOntologyManager;
		
		if (notification.getNotifier() instanceof Resource)
			modelOntologyManager = modelOntologies.get(notification.getNotifier());
		else
			modelOntologyManager = modelOntologies.get(((EObject)notification.getNotifier()).eResource());

		switch (notification.getEventType()) {
		case ADD:
			changeHandler.handleAdd(notification, modelOntologyManager);
			break;
		case ADD_MANY:
			changeHandler.handleAddMany(notification, modelOntologyManager);
			break;
		case MOVE:
			changeHandler.handleMove(notification, modelOntologyManager);
			break;
		case NO_INDEX:
			changeHandler.handleNoIndex(notification, modelOntologyManager);
			break;
		case REMOVE:
			changeHandler.handleRemove(notification, modelOntologyManager);
			break;
		case REMOVE_MANY:
			changeHandler.handleRemoveMany(notification, modelOntologyManager);
			break;
		case REMOVING_ADAPTER:
			changeHandler.handleRemovingAdapter(notification, modelOntologyManager);
			break;
		case RESOLVE:
			changeHandler.handleResolve(notification, modelOntologyManager);
			break;
		case SET:
			changeHandler.handleSet(notification, modelOntologyManager);
			break;
		case UNSET:
			changeHandler.handleUnset(notification, modelOntologyManager);
			break;
		default:
			System.out.println("Uncovered notification type: " + notification.getEventType());
			break;
		}
	}
}
