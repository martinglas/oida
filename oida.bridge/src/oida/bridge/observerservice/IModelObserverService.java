package oida.bridge.observerservice;

import oida.ontology.manager.IOntologyManager;

public interface IModelObserverService {
	void addModelForObservation(Object modelRootComponent, IOntologyManager ontologyManager) throws ModelObserverServiceException;
}
