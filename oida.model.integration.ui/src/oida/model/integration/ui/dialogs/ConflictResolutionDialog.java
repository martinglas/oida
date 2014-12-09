/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.ui.dialogs;

import oida.model.integration.matching.diffmodel.ReferenceMatching;
import oida.model.integration.merge.IntegratedAttribute;
import oida.model.integration.merge.MergeModel;
import oida.model.integration.merge.MergeModelProvider;
import oida.model.integration.ui.providers.ConflictsTableLabelProvider;
import oida.model.integration.ui.util.CommentEditingSupport;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class ConflictResolutionDialog extends Dialog {
	private DataBindingContext m_bindingContext;

	protected Object result;
	protected Shell shlResolveConflicts;
	private MergeModel mergeModel;
	private MergeModelProvider mergeModelProvider;
	private Table tableAttributes;
	private TableViewer tableViewerAttributes;
	private Table tableConflicts;
	private TableViewer tableViewerConflicts;
	private Label lblConflictsResolved;
	private Label lblMismatches;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public ConflictResolutionDialog(Shell parent, int style, MergeModel mergeModel) {
		super(parent, style);
		setText("Semantic Conflict Resolution");
		this.mergeModel = mergeModel;
		mergeModelProvider = new MergeModelProvider(mergeModel);
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlResolveConflicts.open();
		shlResolveConflicts.layout();
		Display display = getParent().getDisplay();
		while (!shlResolveConflicts.isDisposed()) {
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
		shlResolveConflicts = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.RESIZE);
		shlResolveConflicts.setSize(450, 410);
		shlResolveConflicts.setText("Resolve Conflicts");
		shlResolveConflicts.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(shlResolveConflicts, SWT.VERTICAL);

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new GridLayout(1, true));

		tableViewerConflicts = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
		tableConflicts = tableViewerConflicts.getTable();
		tableConflicts.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ReferenceMatching referenceMatching = (ReferenceMatching) ((StructuredSelection) tableViewerConflicts
					.getSelection()).getFirstElement();
				mergeModel.selectConflictingObject(referenceMatching);
			}
		});
		tableConflicts.setLinesVisible(true);
		tableConflicts.setHeaderVisible(true);
		tableConflicts.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		tableViewerConflicts.setContentProvider(new ArrayContentProvider());

		TableViewerColumn tableViewerColumnConflicts = new TableViewerColumn(tableViewerConflicts, SWT.NONE);
		TableColumn tblclmnNewColumn = tableViewerColumnConflicts.getColumn();
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Conflict");

		TableViewerColumn tableViewerColumnMeaning = new TableViewerColumn(tableViewerConflicts, SWT.NONE);
		TableColumn tblclmnMeaning = tableViewerColumnMeaning.getColumn();
		tblclmnMeaning.setWidth(100);
		tblclmnMeaning.setText("Meaning");

		TableViewerColumn tableViewerColumnStatus = new TableViewerColumn(tableViewerConflicts, SWT.NONE);
		TableColumn tblclmnStatus = tableViewerColumnStatus.getColumn();
		tblclmnStatus.setWidth(100);
		tblclmnStatus.setText("Status");

		TableViewerColumn tableViewerComment = new TableViewerColumn(tableViewerConflicts, SWT.NONE);
		TableColumn tblclmnComment = tableViewerComment.getColumn();
		tblclmnComment.setWidth(100);
		tblclmnComment.setText("Comment");
		tableViewerComment.setEditingSupport(new CommentEditingSupport(tableViewerConflicts));

		// tableViewerConflicts.setInput(mergeModel.getConflictingMatchings());

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new GridLayout(1, false));

		Composite composite_4 = new Composite(sashForm, SWT.NONE);
		composite_4.setLayout(new GridLayout(1, false));

		Composite composite_5 = new Composite(composite_4, SWT.NONE);
		composite_5.setLayout(new GridLayout(3, false));
		GridData gd_composite_5 = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_composite_5.heightHint = 92;
		composite_5.setLayoutData(gd_composite_5);

		lblConflictsResolved = new Label(composite_5, SWT.NONE);
		lblConflictsResolved.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lblConflictsResolved.setText("conflicts resolved");

		Button btnNewButton_1 = new Button(composite_5, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				mergeModel.confirmCurrentReferenceMatching();
				tableViewerConflicts.refresh();

			}
		});
		btnNewButton_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnNewButton_1.setText("Confirm");
		new Label(composite_5, SWT.NONE);

		lblMismatches = new Label(composite_5, SWT.NONE);
		lblMismatches.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lblMismatches.setText("mismatches reported");

		Button btnNewButton = new Button(composite_5, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				mergeModel.reportCurrentReferenceMatching();
				tableViewerConflicts.refresh();

			}
		});
		btnNewButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnNewButton.setText("Report");
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);

		Button btnNewButton_2 = new Button(composite_5, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// mergeModel.resolveConflicts();
				shlResolveConflicts.close();
			}
		});
		btnNewButton_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnNewButton_2.setSize(75, 25);
		btnNewButton_2.setText("Next");
		sashForm.setWeights(new int[] { 165, 113, 98 });

		tableViewerAttributes = new TableViewer(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		tableAttributes = tableViewerAttributes.getTable();
		tableAttributes.setLinesVisible(true);
		tableAttributes.setHeaderVisible(true);
		tableAttributes.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		TableViewerColumn tableViewerColumnParameter = new TableViewerColumn(tableViewerAttributes, SWT.NONE);

		tableViewerAttributes.setContentProvider(new ArrayContentProvider());
		// tableViewerAttributes.setInput(mergeModel
		// .getCurrentIntegratedAttributes().getIntegratedAttributes());

		tableViewerColumnParameter.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				IntegratedAttribute integratedAttribute = (IntegratedAttribute) element;
				return integratedAttribute.getAttributeName();
			}
		});

		tableViewerColumnParameter.getColumn().setText("Attribute");
		tableViewerColumnParameter.getColumn().setWidth(100);
		tableViewerColumnParameter.getColumn().setResizable(true);
		tableViewerColumnParameter.getColumn().setMoveable(true);

		TableViewerColumn tableViewerColumnMyValue = new TableViewerColumn(tableViewerAttributes, SWT.NONE);

		tableViewerColumnMyValue.getColumn().setWidth(100);
		tableViewerColumnMyValue.getColumn().setText("My Value");
		tableViewerColumnMyValue.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				IntegratedAttribute integratedAttribute = (IntegratedAttribute) element;

				return integratedAttribute.getMyAttributeValue();
			}

		});

		TableViewerColumn tableViewerColumnTheirValue = new TableViewerColumn(tableViewerAttributes, SWT.NONE);

		tableViewerColumnTheirValue.getColumn().setWidth(100);
		tableViewerColumnTheirValue.getColumn().setText("Their Value");
		tableViewerColumnTheirValue.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				IntegratedAttribute integratedAttribute = (IntegratedAttribute) element;
				return integratedAttribute.getTheirAttributeValue();
			}
		});

		m_bindingContext = initDataBindings();

	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		tableViewerAttributes.setContentProvider(listContentProvider);
		//
		IObservableMap[] observeMaps = BeansObservables.observeMaps(listContentProvider.getKnownElements(),
			IntegratedAttribute.class, new String[] { "attributeName", "myAttributeValue", "theirAttributeValue" });
		tableViewerAttributes.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		//
		IObservableList mergeModelCurrentIntegratedAttributesintegratedAttributesObserveList = BeansObservables
			.observeList(Realm.getDefault(), mergeModel.getCurrentIntegratedAttributes(), "integratedAttributes");
		tableViewerAttributes.setInput(mergeModelCurrentIntegratedAttributesintegratedAttributesObserveList);
		//
		ObservableListContentProvider listContentProvider_1 = new ObservableListContentProvider();
		tableViewerConflicts.setContentProvider(listContentProvider_1);
		//
		IObservableMap[] observeMaps_1 = PojoObservables.observeMaps(listContentProvider_1.getKnownElements(),
			ReferenceMatching.class, new String[] { "confirmed", "reported", "comment" });
		tableViewerConflicts.setLabelProvider(new ConflictsTableLabelProvider(observeMaps_1));
		//
		IObservableList mergeModelConflictingMatchingsObserveList = BeansObservables.observeList(Realm.getDefault(),
			mergeModel, "conflictingMatchings");
		tableViewerConflicts.setInput(mergeModelConflictingMatchingsObserveList);
		//
		IObservableValue lblConflictsResolvedObserveTextObserveWidget = SWTObservables
			.observeText(lblConflictsResolved);
		IObservableValue mergeModelConfirmationStatusObserveValue = BeansObservables.observeValue(
			mergeModel.getMergeCounters(), "confirmationStatus");
		bindingContext.bindValue(lblConflictsResolvedObserveTextObserveWidget,
			mergeModelConfirmationStatusObserveValue, null, null);
		//
		IObservableValue lblMismatchesObserveTextObserveWidget = SWTObservables.observeText(lblMismatches);
		IObservableValue mergeModelReportingStatusObserveValue = BeansObservables.observeValue(
			mergeModel.getMergeCounters(), "reportingStatus");
		bindingContext.bindValue(lblMismatchesObserveTextObserveWidget, mergeModelReportingStatusObserveValue, null,
			null);
		//
		return bindingContext;
	}
}
