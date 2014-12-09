/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.ui.command;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import oida.model.integration.modelprovider.ModelProviderHelper;
import oida.model.integration.transformation.commands.GenerateOntologyCommand;
import oida.model.integration.transformation.ontologygenerator.ContainerContext;
import oida.model.integration.ui.util.OntologyUIHelper;
import oida.ontology.commands.SaveOntologyCommand;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

/**
 * This is a Handler for the ExtractOntologyCommand.
 * 
 * @author Martin Glas
 */

public class GenerateOntologyHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		EObject selection = OntologyUIHelper.getSelection(event);

		String filePath = getFilePath();

		// If a file is selected run the ImportFileCommand
		if (filePath != null) {
			String ontologyName = ModelProviderHelper.getModelElementName(selection).replace(" ", "_") + ".owl";
			GenerateOntologyCommand extractOntologyCommand = new GenerateOntologyCommand(selection,
				URI.createURI("http://www.bauhaus-luftfahrt.net/ontologies/" + ontologyName), new ContainerContext(
					selection));
			extractOntologyCommand.execute();

			URL filePathURL = getFileURL(filePath);
			SaveOntologyCommand saveOntologyCommand = new SaveOntologyCommand(filePathURL);

			saveOntologyCommand.execute();

		}

		return null;
	}

	private URL getFileURL(String filePath) {
		File newFile = new File(filePath);
		try {
			URL newURL = new URL(newFile.toURI().toString());
			return newURL;
		} catch (MalformedURLException e) {
			System.out.println("Could not form a valid URL");
			e.printStackTrace();
		}

		return null;
	}

	private String getFilePath() {
		// Show a standard file dialog where the user can select the file for
		// importing
		FileDialog fileDialog = new FileDialog(OntologyUIHelper.getShell(), SWT.SAVE);

		// Create a File object from the selected file string of the dialog
		String filePath = fileDialog.open();

		return filePath;
	}

}
