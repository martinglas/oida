/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.ui.handlers;

import oida.model.integration.ui.command.IntitiateModelMergeCommand;
import oida.model.integration.ui.util.OntologyUIHelper;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.emf.ecore.EObject;

public class InitiateModelMergeHandler extends AbstractHandler implements IHandler {
	private static String TESTMODEL_SOURCE_FILENAME = "testSourceModel-Simcad.xmi";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		EObject sourceRootEObject = OntologyUIHelper.loadEObjectsFromFile(TESTMODEL_SOURCE_FILENAME).get(0);

		new IntitiateModelMergeCommand(sourceRootEObject, OntologyUIHelper.getSelection(event)).execute();

		return null;
	}
}
