/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.handlers;

import java.net.MalformedURLException;
import java.net.URL;

import oida.ontology.commands.LoadOntologyCommand;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.util.URI;

/**
 * This is a Handler for the LoadOntologyCommand.
 * 
 * @author Martin Glas
 * 
 */

public class LoadOntologyHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {

		try {
			URL sourceOntologyURL = new URL(
					"file:///C:/Users/martin.glas/Projects/Core Projects/modelintegration/AP2-ModelMatching/ontologies/apa/apa.owl");
			URI sourceOntologyURI;

			sourceOntologyURI = URI
					.createURI("http://www.bauhaus-luftfahrt.net/ontologies/apa.owl");
			LoadOntologyCommand loadOntologyCommand = new LoadOntologyCommand(
					sourceOntologyURL, sourceOntologyURI);
			loadOntologyCommand.execute();

		} catch (MalformedURLException eURL) {
			System.out
					.println("The URI of the source ontology was not well-formed");
		}

		return null;
	}

}
