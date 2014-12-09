/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.ui.util;

import oida.model.integration.matching.diffmodel.ReferenceMatching;
import oida.model.integration.modelprovider.ModelProviderHelper;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;

public class ElementNameEditingSupport extends EditingSupport {

	private TableViewer viewer;

	public ElementNameEditingSupport(TableViewer viewer) {
		super(viewer);
		this.viewer = viewer;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		return new TextCellEditor(viewer.getTable());
	}

	@Override
	protected boolean canEdit(Object element) {

		return true;
	}

	@Override
	protected Object getValue(Object element) {
		ReferenceMatching referenceMatching = (ReferenceMatching) element;
		return ModelProviderHelper.getModelElementName(referenceMatching
				.getTargetReferenceMapping().getSourceEObject());
	}

	@Override
	protected void setValue(Object element, Object value) {
		ReferenceMatching referenceMatching = (ReferenceMatching) element;
		EAttribute nameAttribute = ModelProviderHelper
				.getModelElementNameAttribute(referenceMatching
						.getTargetReferenceMapping().getSourceEObject());
		referenceMatching.getTargetReferenceMapping().getSourceEObject()
				.eSet(nameAttribute, value);
		viewer.refresh();

	}

}
