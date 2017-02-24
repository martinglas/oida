/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
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