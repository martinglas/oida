/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.ui.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class EvolutionDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Table table;
	private Table table_1;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public EvolutionDialog(Shell parent, int style) {
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
		shell.setSize(450, 357);
		shell.setText(getText());

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(0, 77, 444, 195);

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(0, 0, 229, 195);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnOldOntology = new TableColumn(table, SWT.NONE);
		tblclmnOldOntology.setWidth(125);
		tblclmnOldOntology.setText("Old Ontology");

		TableColumn tblclmnReference = new TableColumn(table, SWT.NONE);
		tblclmnReference.setWidth(100);
		tblclmnReference.setText("Reference ");

		table_1 = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setBounds(235, 0, 209, 195);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);

		TableColumn tblclmnNewOntology = new TableColumn(table_1, SWT.NONE);
		tblclmnNewOntology.setWidth(204);
		tblclmnNewOntology.setText("New Ontology");

		Button btnConfirmAllRecommendations = new Button(shell, SWT.NONE);
		btnConfirmAllRecommendations.setBounds(238, 10, 196, 25);
		btnConfirmAllRecommendations.setText("Confirm all Recommendations");

		Button btnPerformOntologyEvolution = new Button(shell, SWT.NONE);
		btnPerformOntologyEvolution.setBounds(238, 278, 196, 25);
		btnPerformOntologyEvolution.setText("Perform Ontology Evolution");

		Button btnStringMatching = new Button(shell, SWT.CHECK);
		btnStringMatching.setBounds(10, 10, 106, 16);
		btnStringMatching.setText("String Matching");

		Button btnReasonerMatching = new Button(shell, SWT.CHECK);
		btnReasonerMatching.setBounds(10, 55, 123, 16);
		btnReasonerMatching.setText("Reasoner Matching");

		Button btnStructuralMatching = new Button(shell, SWT.CHECK);
		btnStructuralMatching.setBounds(10, 32, 139, 16);
		btnStructuralMatching.setText("Structural Matching");

		Label lblUnmappedConcepts = new Label(shell, SWT.NONE);
		lblUnmappedConcepts.setBounds(10, 288, 123, 15);
		lblUnmappedConcepts.setText("0 Unmapped concepts");

	}
}
