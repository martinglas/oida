package oida.bridge.observerservice;

import org.eclipse.emf.ecore.EObject;

import oida.ontology.manager.IOntologyManager;

public interface IModelObserverService {
	void addModelForObservation(EObject componentRepository, IOntologyManager ontologyManager);
}
