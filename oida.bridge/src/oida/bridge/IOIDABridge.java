/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge;

import oida.bridge.observerservice.changehandler.IChangeHandler;

public interface IOIDABridge {
	void registerChangeHandler(IChangeHandler changeHandler);
	
	void handleEntityAddedEvent();
	void handleEntityRemovedEvent();
}
