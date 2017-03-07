/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge.service;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-03-06
 *
 */
public class OIDABridgeException extends Exception {
	private static final long serialVersionUID = 1L;

	public OIDABridgeException(String message) {
		super(message);
	}
	
	public OIDABridgeException(String message, Exception inner) {
	}
}
