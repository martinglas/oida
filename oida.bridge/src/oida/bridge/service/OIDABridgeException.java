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

	/**
	 * Standard constructor.
	 */
	public OIDABridgeException() {
		super();
	}

	/**
	 * Constructor, which takes an exception message.
	 * 
	 * @param message
	 *            The exception message.
	 */
	public OIDABridgeException(String message) {
		super(message);
	}

	/**
	 * Constructor, which takes an exception message as well as an inner
	 * exception.
	 * 
	 * @param message
	 *            The exception message.
	 * @param inner
	 *            The inner exception.
	 */
	public OIDABridgeException(String message, Exception inner) {
		super(message, inner);
	}
}
