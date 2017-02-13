package oida.bridge.observerservice.emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;

import oida.bridge.observerservice.IModelObserverService;
import oida.bridge.observerservice.ModelObserverServiceException;
import oida.ontology.manager.IOntologyManager;

public abstract class AbstractEMFModelObserverService extends EContentAdapter implements IModelObserverService {
	@Override
	public void addModelForObservation(Object modelRootComponent, IOntologyManager ontologyManager) throws ModelObserverServiceException {
		if (modelRootComponent instanceof EObject)
			addEMFModelForObservation((EObject)modelRootComponent, ontologyManager);
		else
			throw new ModelObserverServiceException("The passed object is not an EMF-model.");
	}
	
	public abstract void addEMFModelForObservation(EObject modelRootComponent, IOntologyManager ontologyManager);
}
