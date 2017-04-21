/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge.model.strategy;

import java.util.List;

/**
 * 
 * @author Martin Glas, Michael Shamiyeh
 * @since 2009
 *
 */
public interface IRenamerStrategy {
	/**
	 * Delivers an ID string for a given EObject. The ID has to be stable beyond runtime.
	 * 
	 * @param eObject An instance of an EObject. 
	 * @return The ID string for the EObject.
	 */
	public String getObjectID(Object object);
	
	public String getObjectName(Object object);

	public List<Object> getNameRelevantObjectsForObject(Object object);

	public String getClassName(Object classObject);

	public String getRelationID(Object relationObject);
}
