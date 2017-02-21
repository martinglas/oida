/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge.observerservice.changehandler;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;

import oida.ontology.manager.IOntologyManager;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-01-20
 *
 */
public interface IChangeHandler {
	void initializeModelOntology(EObject modelContainer, IOntologyManager ontManager);

	void handleAdd(Notification notification, IOntologyManager ontManager);

	void handleAddMany(Notification notification, IOntologyManager ontManager);

	void handleMove(Notification notification, IOntologyManager ontManager);

	void handleNoIndex(Notification notification, IOntologyManager ontManager);

	void handleRemove(Notification notification, IOntologyManager ontManager);

	void handleRemoveMany(Notification notification, IOntologyManager ontManager);

	void handleRemovingAdapter(Notification notification, IOntologyManager ontManager);

	void handleResolve(Notification notification, IOntologyManager ontManager);

	void handleSet(Notification notification, IOntologyManager ontManager);

	void handleUnset(Notification notification, IOntologyManager ontManager);
}
