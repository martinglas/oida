/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.ui.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.IElementComparer;

public class EObjectsComparer implements IElementComparer {

	@Override
	public boolean equals(Object a, Object b) {

		return EcoreUtil.equals((EObject) a, (EObject) b);
	}

	@Override
	public int hashCode(Object element) {
		return element.hashCode();
	}

}
