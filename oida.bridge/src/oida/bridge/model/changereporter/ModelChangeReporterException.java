/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge.model.changereporter;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-03-03
 *
 */
public class ModelChangeReporterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5782636385610152368L;

	public ModelChangeReporterException(String message) {
		super(message);
	}
	
	public ModelChangeReporterException(String message, Exception inner) {
		super(message, inner);
	}
}
