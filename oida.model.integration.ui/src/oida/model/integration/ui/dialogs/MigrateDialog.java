/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.ui.dialogs;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import oida.model.integration.matching.diffmodel.ReferenceMatching;
import oida.model.integration.merge.IntegratedAttribute;
import oida.model.integration.merge.MergeModel;
import oida.model.integration.modelprovider.ModelProviderHelper;
import oida.model.integration.transformation.util.TransformatorHelper;
import oida.model.integration.ui.providers.NewElementsTableLabelProvider;
import oida.model.integration.ui.util.CommentEditingSupport;
import oida.model.integration.ui.util.ContainerFilter;
import oida.model.integration.ui.util.EObjectsComparer;
import oida.model.integration.ui.util.ElementNameEditingSupport;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
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
import org.eclipse.swt.widgets.Tree;

public class MigrateDialog extends Dialog {
	private DataBindingContext m_bindingContext;

	protected Object result;
	protected Shell shlMigratedialog;
	private Table tableNewElements;
	private Table tableAttributes;
	private MergeModel mergeModel;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	private TableViewer tableViewerNewElements;
	private TableViewer tableViewerAttributes;
	private Label lblCommitedImports;
	private Label lblReportedImports;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param mergeModel
	 */
	public MigrateDialog(Shell parent, int style, MergeModel mergeModel) {
		super(parent, style);
		setText("SWT Dialog");
		setMergeModel(mergeModel);
		getMergeModel().getMergeCounters().setCurrentMatchings(mergeModel.getDiffModel().getTheirMatchings());

	}

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlMigratedialog.open();
		shlMigratedialog.layout();
		Display display = getParent().getDisplay();
		while (!shlMigratedialog.isDisposed()) {
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
		shlMigratedialog = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.RESIZE);
		shlMigratedialog.setSize(802, 400);
		shlMigratedialog.setText("Import");
		shlMigratedialog.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(shlMigratedialog, SWT.NONE);

		Composite compositeTargetModel = new Composite(sashForm, SWT.NONE);
		compositeTargetModel.setLayout(new FillLayout(SWT.HORIZONTAL));

		final TreeViewer treeViewerTargetModel = new TreeViewer(compositeTargetModel, SWT.BORDER);
		Tree treeTargetModel = treeViewerTargetModel.getTree();
		treeTargetModel.addSelectionListener(new SelectionAdapter() {

			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent e) {
				EObject containerEObject = (EObject) ((StructuredSelection) treeViewerTargetModel.getSelection())
					.getFirstElement();
				if ((mergeModel.getCurrentReferenceMatching() != null)
					&& (mergeModel.getDiffModel().getTheirMatchings().contains(mergeModel.getCurrentReferenceMatching()))) {
					EObject currentEObject = mergeModel.getCurrentReferenceMatching().getTargetReferenceMapping()
						.getSourceEObject();

					MergeModel mergeModel1 = mergeModel;

					for (EReference containmentEReference : containerEObject.eClass().getEAllContainments()) {
						EClassifier referenceEClassifier = containmentEReference.getEType();

						if (referenceEClassifier.isInstance(currentEObject)) {
							if (containmentEReference.isMany()) {
								((EList<EObject>) containerEObject.eGet(containmentEReference)).add(mergeModel
									.getCurrentReferenceMatching().getTargetReferenceMapping().getSourceEObject());

							} else {
								containerEObject.eSet(containmentEReference, mergeModel.getCurrentReferenceMatching()
									.getTargetReferenceMapping().getSourceEObject());
							}
							tableViewerNewElements.refresh();
							break;
						}

					}

				}
			}
		});

		SashForm sashForm_1 = new SashForm(sashForm, SWT.VERTICAL);

		Composite compositeNewElements = new Composite(sashForm_1, SWT.NONE);
		compositeNewElements.setLayout(new GridLayout(1, false));

		tableViewerNewElements = new TableViewer(compositeNewElements, SWT.BORDER | SWT.FULL_SELECTION);
		tableNewElements = tableViewerNewElements.getTable();
		tableNewElements.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ReferenceMatching referenceMatching = (ReferenceMatching) ((StructuredSelection) tableViewerNewElements
					.getSelection()).getFirstElement();
				mergeModel.selectConflictingObject(referenceMatching);
				if (mergeModel.getCurrentReferenceMatching() != null) {
					EObject currentContainer = referenceMatching.getTargetReferenceMapping().getSourceEObject()
						.eContainer();
					if (currentContainer != null) {
						List<EObject> selectionList = new ArrayList<EObject>();
						selectionList.add(currentContainer);
						treeViewerTargetModel.setSelection(new StructuredSelection(selectionList,
							new EObjectsComparer()), true);
					}
				}

			}
		});
		tableNewElements.setLinesVisible(true);
		tableNewElements.setHeaderVisible(true);
		tableNewElements.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		TableViewerColumn tableViewerElement = new TableViewerColumn(tableViewerNewElements, SWT.NONE);
		TableColumn tblclmnElement = tableViewerElement.getColumn();
		tblclmnElement.setWidth(100);
		tblclmnElement.setText("Element");
		tableViewerElement.setEditingSupport(new ElementNameEditingSupport(tableViewerNewElements));

		TableViewerColumn tableViewerMeaning = new TableViewerColumn(tableViewerNewElements, SWT.NONE);
		TableColumn tblclmnMeaning = tableViewerMeaning.getColumn();
		tblclmnMeaning.setWidth(113);
		tblclmnMeaning.setText("Meaning");

		TableViewerColumn tableViewerContainer = new TableViewerColumn(tableViewerNewElements, SWT.NONE);
		TableColumn tblclmnContainer = tableViewerContainer.getColumn();
		tblclmnContainer.setWidth(104);
		tblclmnContainer.setText("Container");

		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewerNewElements, SWT.NONE);
		TableColumn tblclmnStatus = tableViewerColumn_1.getColumn();
		tblclmnStatus.setWidth(71);
		tblclmnStatus.setText("Status");

		TableViewerColumn tableViewerComment = new TableViewerColumn(tableViewerNewElements, SWT.NONE);
		TableColumn tblclmnComment = tableViewerComment.getColumn();
		tblclmnComment.setWidth(160);
		tblclmnComment.setText("Comment");
		tableViewerComment.setEditingSupport(new CommentEditingSupport(tableViewerNewElements));

		Composite compositeAttribute = new Composite(sashForm_1, SWT.NONE);
		compositeAttribute.setLayout(new GridLayout(2, false));

		tableViewerAttributes = new TableViewer(compositeAttribute, SWT.BORDER | SWT.FULL_SELECTION);

		tableViewerAttributes.setContentProvider(new ArrayContentProvider());

		tableAttributes = tableViewerAttributes.getTable();
		GridData gd_tableAttributes = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_tableAttributes.widthHint = 208;
		tableAttributes.setLayoutData(gd_tableAttributes);
		tableAttributes.setLinesVisible(true);
		tableAttributes.setHeaderVisible(true);

		TableViewerColumn tableViewerColumnAttribute = new TableViewerColumn(tableViewerAttributes, SWT.NONE);
		TableColumn tblclmnAttribute = tableViewerColumnAttribute.getColumn();
		tblclmnAttribute.setWidth(85);
		tblclmnAttribute.setText("Attribute");
		tableViewerColumnAttribute.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				IntegratedAttribute integratedAttribute = (IntegratedAttribute) element;
				return integratedAttribute.getAttributeName();
			}
		});

		TableViewerColumn tableViewerColumnValue = new TableViewerColumn(tableViewerAttributes, SWT.NONE);
		TableColumn tblclmnValue = tableViewerColumnValue.getColumn();
		tblclmnValue.setWidth(102);
		tblclmnValue.setText("Value");

		Composite composite = new Composite(compositeAttribute, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, false, 1, 1));
		composite.setLayout(new GridLayout(3, false));

		lblCommitedImports = new Label(composite, SWT.NONE);
		lblCommitedImports.setText("commited imports");

		Button btnCommit = new Button(composite, SWT.NONE);
		btnCommit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				EObject container = mergeModel.getCurrentReferenceMatching().getTargetReferenceMapping()
					.getSourceEObject().eContainer();
				String name = ModelProviderHelper.getModelElementName(mergeModel.getCurrentReferenceMatching()
					.getTargetReferenceMapping().getSourceEObject());
				if ((container != null) && (name != "")) {
					mergeModel.confirmCurrentReferenceMatching();
					tableViewerNewElements.refresh();
				} else {
					System.out.println("Cannot confirm underdefined model element");
				}

			}
		});
		btnCommit.setText("Confirm");
		new Label(composite, SWT.NONE);

		lblReportedImports = new Label(composite, SWT.NONE);
		lblReportedImports.setText("Reported imports");

		Button btnReport = new Button(composite, SWT.NONE);
		btnReport.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnReport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TransformatorHelper.removeContainment(mergeModel.getCurrentReferenceMatching()
					.getTargetReferenceMapping().getSourceEObject());
				mergeModel.reportCurrentReferenceMatching();
				tableViewerNewElements.refresh();
			}

		});
		btnReport.setText("Report");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);

		Button btnFinish = new Button(composite, SWT.NONE);
		btnFinish.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlMigratedialog.close();
			}
		});
		btnFinish.setText("Finish");
		sashForm_1.setWeights(new int[] { 183, 93 });
		sashForm.setWeights(new int[] { 125, 316 });

		ObservableListContentProvider listContentProviderNewElements = new ObservableListContentProvider();
		tableViewerNewElements.setContentProvider(listContentProviderNewElements);

		IObservableMap[] observeMapsNewElements = PojoObservables.observeMaps(
			listContentProviderNewElements.getKnownElements(), ReferenceMatching.class, new String[] { "confirmed",
				"reported" });
		tableViewerNewElements.setLabelProvider(new NewElementsTableLabelProvider(observeMapsNewElements));

		treeViewerTargetModel.setLabelProvider(new AdapterFactoryLabelProvider(new ComposedAdapterFactory(
			ComposedAdapterFactory.Descriptor.Registry.INSTANCE)));

		treeViewerTargetModel.setContentProvider(new AdapterFactoryContentProvider(new ComposedAdapterFactory(
			ComposedAdapterFactory.Descriptor.Registry.INSTANCE)) {
			@Override
			public Object[] getElements(Object object) {
				if (object instanceof Object[]) {
					return (Object[]) object;
				}
				return super.getElements(object);
			}
		});
		treeViewerTargetModel.addFilter(new ContainerFilter());

		treeViewerTargetModel.setInput(new Object[] { mergeModel.getTargetRootObject() });

		m_bindingContext = initDataBindings();

	}

	public MergeModel getMergeModel() {
		return mergeModel;
	}

	public void setMergeModel(MergeModel mergeModel) {
		propertyChangeSupport.firePropertyChange("mergeModel", this.mergeModel, this.mergeModel = mergeModel);
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		tableViewerNewElements.setContentProvider(listContentProvider);
		//
		IObservableMap[] observeMaps = PojoObservables.observeMaps(listContentProvider.getKnownElements(),
			ReferenceMatching.class, new String[] { "confirmed", "reported" });
		tableViewerNewElements.setLabelProvider(new NewElementsTableLabelProvider(observeMaps));
		//
		IObservableList mergeModelDiffModeltheirMatchingsObserveList = PojoObservables.observeList(Realm.getDefault(),
			mergeModel.getDiffModel(), "theirMatchings");
		tableViewerNewElements.setInput(mergeModelDiffModeltheirMatchingsObserveList);
		//
		ObservableListContentProvider listContentProvider_1 = new ObservableListContentProvider();
		tableViewerAttributes.setContentProvider(listContentProvider_1);
		//
		IObservableMap[] observeMaps_1 = BeansObservables.observeMaps(listContentProvider_1.getKnownElements(),
			IntegratedAttribute.class, new String[] { "attributeName", "theirAttributeValue" });
		tableViewerAttributes.setLabelProvider(new ObservableMapLabelProvider(observeMaps_1));
		//
		IObservableList mergeModelCurrentIntegratedAttributesintegratedAttributesObserveList = BeansObservables
			.observeList(Realm.getDefault(), mergeModel.getCurrentIntegratedAttributes(), "integratedAttributes");
		tableViewerAttributes.setInput(mergeModelCurrentIntegratedAttributesintegratedAttributesObserveList);
		//
		IObservableValue lblCommitedImportsObserveTextObserveWidget = SWTObservables.observeText(lblCommitedImports);
		IObservableValue mergeModelConfirmationStatusObserveValue = BeansObservables.observeValue(
			mergeModel.getMergeCounters(), "confirmationStatus");
		bindingContext.bindValue(lblCommitedImportsObserveTextObserveWidget, mergeModelConfirmationStatusObserveValue,
			null, null);
		//
		IObservableValue lblReportedImportsObserveTextObserveWidget = SWTObservables.observeText(lblReportedImports);
		IObservableValue mergeModelReportingStatusObserveValue = BeansObservables.observeValue(
			mergeModel.getMergeCounters(), "reportingStatus");
		bindingContext.bindValue(lblReportedImportsObserveTextObserveWidget, mergeModelReportingStatusObserveValue,
			null, null);
		//
		return bindingContext;
	}

}
