/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.ui.editors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.internal.part.NullEditorInput;

@SuppressWarnings("restriction")
public class MatchingEditorInput extends NullEditorInput implements
		IEditorInput {

	private final EObject rootEObject;

	public MatchingEditorInput(EObject rootEObject) {
		super();
		this.rootEObject = rootEObject;

	}

	public EObject getRootEObject() {
		return rootEObject;
	}

}
