/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.ui.util;

import oida.model.integration.matching.diffmodel.ReferenceMatching;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;

public class CommentEditingSupport extends EditingSupport {

	private TableViewer viewer;

	public CommentEditingSupport(TableViewer viewer) {
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
		return referenceMatching.getComment();
	}

	@Override
	protected void setValue(Object element, Object value) {
		ReferenceMatching referenceMatching = (ReferenceMatching) element;
		referenceMatching.setComment(value.toString());
		viewer.refresh();

	}

}
