/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.ui.providers;

import oida.ontology.model.OntologyModel;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.ontology.Ontology;

public class OntClassContentProvider implements ITreeContentProvider {
	private static Object[] EMPTY_ARRAY = new Object[0];
	private Ontology ontology;
	protected TreeViewer viewer;

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.viewer = (TreeViewer) viewer;

	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof OntResource) {
			OntologyModel ontologyModel = OntologyModel.getInstance();
			OntResource topOntResource = (OntResource) inputElement;
			OntResource topResource = ontologyModel.getOntologyModel()
					.getOntResource(
							("http://www.w3.org/2000/01/rdf-schema#Resource"));
			if (topOntResource.equals(topResource)) {
				return ontologyModel.getSubOntResources(OntClass.class,
						getOntology(), topOntResource).toArray();
			} else {
				return ontologyModel.getSubOntResources(
						topOntResource.getClass(), getOntology(),
						topOntResource).toArray();
			}

		}
		return EMPTY_ARRAY;

	}

	@Override
	public Object[] getChildren(Object parentElement) {
		OntologyModel ontologyModel = OntologyModel.getInstance();
		if (parentElement instanceof OntClass) {
			OntClass ontClass = (OntClass) parentElement;
			// return ontClass.listSubClasses(true).toList().toArray();
			Object[] resultObjectArray = concat(ontologyModel
					.getBaseDatatypeProperties(ontClass).toArray(),
					ontologyModel.getBaseObjectProperties(ontClass).toArray(),
					ontClass.listSubClasses(true).toList().toArray());
			return resultObjectArray;
		}
		return EMPTY_ARRAY;
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof OntClass) {
			return ((OntClass) element).getSuperClass();
		}
		if (element instanceof OntProperty) {
			return (OntClass) ((OntProperty) element).getDomain();
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;

	}

	protected Object[] concat(Object[] object, Object[] more, Object[] more2) {
		Object[] both = new Object[object.length + more.length + more2.length];
		System.arraycopy(object, 0, both, 0, object.length);
		System.arraycopy(more, 0, both, object.length, more.length);
		System.arraycopy(more2, 0, both, object.length + more.length,
				more2.length);
		return both;
	}

	public Ontology getOntology() {
		return ontology;
	}

	public void setOntology(Ontology ontology) {
		this.ontology = ontology;
	}

}
