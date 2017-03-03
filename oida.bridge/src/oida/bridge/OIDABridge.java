/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge;

import oida.bridge.model.changereporter.IModelChangeReporter;
import oida.bridge.observerservice.changehandler.IChangeHandler;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-03-03
 *
 */
public class OIDABridge implements IOIDABridge {
	private IChangeHandler changeHandler;
	
	public OIDABridge() {
	}
	
	@Override
	public void registerChangeHandler(IChangeHandler changeHandler) {
		this.changeHandler = changeHandler;
	}
	
	public void setModelObserverService(IModelChangeReporter modelChangeReporter) {
		modelChangeReporter.registerOIDABridge(this);
	}
	
	@Override
	public void handleEntityAddedEvent() {		
	}
	
	@Override
	public void handleEntityRemovedEvent() {
		
	}
}
