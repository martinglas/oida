/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.transformation.ontologygenerator;

import oida.model.integration.modelprovider.ModelProviderHelper;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SimpleObjectName implements RenamerStrategy {
	protected EObject rootEObject;

	public SimpleObjectName(EObject rootObject) {
		this.rootEObject = rootObject;
	}

	@Override
	public String getEObjectName(EObject eObject) {
		return ModelProviderHelper.getModelElementName(eObject);
	}

	@Override
	public String getEStructuralFeatureName(
			EStructuralFeature eStructuralFeature) {

		return eStructuralFeature.getName();
	}

	@Override
	public String getEClassName(EClass eClass) {

		return eClass.getName();
	}

}
