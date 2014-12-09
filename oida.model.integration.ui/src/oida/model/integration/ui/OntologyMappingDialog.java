/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class OntologyMappingDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Table table;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public OntologyMappingDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(450, 300);
		shell.setText(getText());

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(10, 10, 424, 64);

		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNewButton.setBounds(0, 0, 203, 54);
		btnNewButton.setText("Reccommend mapping");

		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setBounds(10, 80, 424, 182);

		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(0, 0, 424, 182);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnSource = new TableColumn(table, SWT.NONE);
		tblclmnSource.setWidth(100);
		tblclmnSource.setText("Source");

		TableColumn tblclmnReference = new TableColumn(table, SWT.NONE);
		tblclmnReference.setWidth(100);
		tblclmnReference.setText("Reference");

	}
}
