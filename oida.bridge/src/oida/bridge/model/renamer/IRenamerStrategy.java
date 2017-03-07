/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge.model.renamer;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * 
 * @author Martin Glas
 *
 */
public interface IRenamerStrategy {
	public String getEObjectName(EObject eObject);

	public String getEStructuralFeatureName(EStructuralFeature eStructuralFeature);

	public String getEClassName(EClass eClass);
}