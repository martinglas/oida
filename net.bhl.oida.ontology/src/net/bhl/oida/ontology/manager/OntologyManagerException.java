/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package net.bhl.oida.ontology.manager;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2016-11-10
 *
 */
@SuppressWarnings("serial")
public class OntologyManagerException extends Exception {
	public OntologyManagerException() {
    }

    public OntologyManagerException(String message) {
        super(message);
    }

    public OntologyManagerException(Throwable cause) {
        super(cause);
    }

    public OntologyManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
