/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package net.bhl.oida.bridge.util;

import net.bhl.oida.util.constants.FileConstants;
import net.bhl.oida.util.constants.StringConstants;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-12-12
 *
 */
public class BridgeHelper {

	public static String getNSfromRootElementName(String rootContainerName) {
		return FileConstants.ONTOLOGY_HTTP_IRI_PREFIX + rootContainerName + StringConstants.HASHTAG;
	}

	public static String getModelOntologyName(String rootContainerName) {
		return rootContainerName + FileConstants.OWL_FILE_POSTFIX;
	}
}
