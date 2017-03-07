/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge.util;

public class BridgeHelper {

	public static String getNSfromRootElementName(String rootContainerName) {

		return "http://" + rootContainerName + "#";
	}

	public static String getModelOntologyName(String rootContainerName) {
		return rootContainerName + ".owl";
	}
}
