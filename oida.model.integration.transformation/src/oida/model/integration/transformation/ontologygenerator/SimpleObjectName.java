/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.transformation.ontologygenerator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import de.symo.oida.changehandler.modelhelper.ModelProviderHelper;
import oida.bridge.observerservice.renamer.RenamerStrategy;

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
