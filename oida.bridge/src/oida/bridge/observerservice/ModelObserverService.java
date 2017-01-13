package oida.bridge.observerservice;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;

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
	public void notifyChanged(Notification msg) {
		System.out.println("OIDA Bridge: Notfication received from the data model '" + msg.getNotifier().toString() + "'. Data model has changed!!!");
	}
}
