/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.ui.util;

import static oida.ontology.util.OntologyHelper.MODEL_PATH;
import static oida.ontology.util.OntologyHelper.ONTOLOGYPLUGIN_ID;
import oida.ontology.util.OntologyHelper;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import com.hp.hpl.jena.ontology.OntResource;

public final class OntologyUIHelper {
	public static class OntResourceSorter extends ViewerSorter {
		public int compare(Viewer viewer, Object e1, Object e2) {

			OntResource individual1 = (OntResource) e1;
			OntResource individual2 = (OntResource) e2;
			return String.CASE_INSENSITIVE_ORDER.compare(individual1.getLocalName(), individual2.getLocalName());

		}
	}

	/**
	 * Returns the shell of the active workbench.
	 * 
	 * @return The active workbench shell
	 */
	public static Shell getShell() {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	}

	/**
	 * Returns the selected EObject on which the handler is called.
	 * 
	 * @param event The ExecutionEvent
	 * @return The selected EObject
	 */
	public static EObject getSelection(ExecutionEvent event) {
		EObject result = null;
		ISelection sel = HandlerUtil.getCurrentSelection(event);
		if (!(sel instanceof IStructuredSelection)) {
			return null;
		}
		IStructuredSelection ssel = (IStructuredSelection) sel;
		if (ssel.isEmpty()) {
			return null;
		}
		Object obj = ssel.getFirstElement();
		if (obj instanceof EObject) {
			result = (EObject) obj;
		}
		return result;
	}

	/**
	 * This method returns EObjects loaded from an XML file.
	 * 
	 * @param filename The name of the file from which the Elements are loaded
	 * @return a list of Objects which was loaded from a file with the given filename
	 */
	public static EList<EObject> loadEObjectsFromFile(String filename) {

		ResourceSetImpl resourceSet = new ResourceSetImpl();

		Resource resource = resourceSet.getResource(
			URI.createURI(OntologyHelper.getPluginFilePath(ONTOLOGYPLUGIN_ID) + MODEL_PATH + filename), true);

		EList<EObject> result = new BasicEList<EObject>();

		for (EObject obj : resource.getContents()) {
			result.add(EcoreUtil.copy(obj));
		}

		return result;
	}

}
