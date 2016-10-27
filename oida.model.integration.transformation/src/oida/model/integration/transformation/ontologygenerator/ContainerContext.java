/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.transformation.ontologygenerator;

import org.eclipse.emf.ecore.EObject;

import oida.model.integration.modelprovider.ModelProviderHelper;

public class ContainerContext extends SimpleObjectName {

	public ContainerContext(EObject rootObject) {
		super(rootObject);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getEObjectName(EObject eObject) {

		return ModelProviderHelper.getModelElementContextID(eObject,
				rootEObject);
	}

}
