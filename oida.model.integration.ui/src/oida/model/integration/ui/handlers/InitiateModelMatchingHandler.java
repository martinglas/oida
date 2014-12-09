/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.ui.handlers;

import oida.model.integration.ui.editors.ExtendingMatchEditor;
import oida.model.integration.ui.editors.MatchingEditorInput;
import oida.model.integration.ui.util.OntologyUIHelper;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class InitiateModelMatchingHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		try {
			EObject selection = OntologyUIHelper.getSelection(event);

			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.openEditor(new MatchingEditorInput((EObject) selection), ExtendingMatchEditor.ID, true);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		return null;
	}
}
