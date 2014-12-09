/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.ui.dialogs;

import oida.model.integration.ui.util.OntologyUIHelper.OntResourceSorter;
import oida.ontology.model.ReferenceOntologyModel;

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
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.hp.hpl.jena.ontology.OntClass;

public class CreateNewReferenceOntClassDialog extends Dialog {
	private DataBindingContext m_bindingContext;

	protected Object result;
	protected Shell shell;
	private Text textNewOntClassName;
	private ReferenceOntologyModel referenceOntologfyContentProvider;
	private ListViewer listViewer;
	private OntClass sourceOntClass;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param referenceOntClass
	 * @param sourceIndividual
	 */
	public CreateNewReferenceOntClassDialog(Shell parent, int style,
			OntClass referenceSuperOntClass) {
		super(parent, style);
		setText("Create new reference class");
		this.referenceOntologfyContentProvider = new ReferenceOntologyModel();

		referenceOntologfyContentProvider
				.setCurrentOntClass(referenceSuperOntClass);

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
		shell.setLayout(new GridLayout(1, false));

		Label lblClass = new Label(shell, SWT.NONE);
		lblClass.setText("Superclass");

		listViewer = new ListViewer(shell, SWT.BORDER | SWT.V_SCROLL);
		List listReferenceClasses = listViewer.getList();

		listReferenceClasses.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				OntClass currentReferenceOntClass = (OntClass) ((StructuredSelection) listViewer
						.getSelection()).getFirstElement();

				referenceOntologfyContentProvider
						.setCurrentOntClass(currentReferenceOntClass);

			}
		});
		listViewer.setSorter(new OntResourceSorter());

		GridData gd_listReferenceClasses = new GridData(SWT.FILL, SWT.FILL,
				false, true, 1, 1);
		gd_listReferenceClasses.heightHint = 102;
		listReferenceClasses.setLayoutData(gd_listReferenceClasses);

		Button btnAddNewSuperclass = new Button(shell, SWT.NONE);
		btnAddNewSuperclass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {

				CreateNewReferenceOntClassDialog createNewReferenceOntClass = new CreateNewReferenceOntClassDialog(

						PlatformUI.getWorkbench().getActiveWorkbenchWindow()
								.getShell(), SWT.DIALOG_TRIM
								| SWT.APPLICATION_MODAL,
						(OntClass) ((StructuredSelection) listViewer
								.getSelection()).getFirstElement());
				createNewReferenceOntClass.open();
				listViewer.refresh();
			}
		});
		GridData gd_btnAddNewSuperclass = new GridData(SWT.FILL, SWT.CENTER,
				false, false, 1, 1);
		gd_btnAddNewSuperclass.widthHint = 161;
		btnAddNewSuperclass.setLayoutData(gd_btnAddNewSuperclass);
		btnAddNewSuperclass.setText("Add new superclass");

		Label lblName = new Label(shell, SWT.NONE);
		lblName.setText("Name");

		textNewOntClassName = new Text(shell, SWT.BORDER);
		textNewOntClassName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 1, 1));

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false,
				1, 1));
		composite.setLayout(new GridLayout(3, false));

		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		GridData gd_btnCancel = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnCancel.widthHint = 100;
		btnCancel.setLayoutData(gd_btnCancel);
		btnCancel.setText("Cancel");

		Composite composite_1 = new Composite(composite, SWT.NONE);
		GridData gd_composite_1 = new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1);
		gd_composite_1.heightHint = 14;
		composite_1.setLayoutData(gd_composite_1);

		Label lblInfo = new Label(composite_1, SWT.NONE);
		lblInfo.setBounds(0, 0, 185, 15);
		lblInfo.setText("Select superclass and input name");

		Button btnOk = new Button(composite, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				referenceOntologfyContentProvider
						.createNewReferenceClass(getSourceOntClass());

				shell.close();
			}
		});
		GridData gd_btnOk = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1,
				1);
		gd_btnOk.widthHint = 100;
		btnOk.setLayoutData(gd_btnOk);
		btnOk.setText("OK");
		m_bindingContext = initDataBindings();

	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap observeMap = PojoObservables.observeMap(
				listContentProvider.getKnownElements(), OntClass.class,
				"localName");
		listViewer.setLabelProvider(new ObservableMapLabelProvider(observeMap));
		listViewer.setContentProvider(listContentProvider);
		//
		IObservableList referenceOntologfyContentProviderReferenceOntClassesObserveList = BeansObservables
				.observeList(Realm.getDefault(),
						referenceOntologfyContentProvider,
						"referenceOntClasses");
		listViewer
				.setInput(referenceOntologfyContentProviderReferenceOntClassesObserveList);
		OntClass currentOntClass = referenceOntologfyContentProvider
				.getCurrentOntClass();
		if (currentOntClass != null) {
			listViewer.setSelection(new StructuredSelection(currentOntClass),
					true);
		}
		//
		IObservableValue textNewOntClassNameObserveTextObserveWidget = SWTObservables
				.observeText(textNewOntClassName, SWT.Modify);
		IObservableValue referenceOntologfyContentProviderNewClassNameObserveValue = BeansObservables
				.observeValue(referenceOntologfyContentProvider, "newClassName");
		bindingContext.bindValue(textNewOntClassNameObserveTextObserveWidget,
				referenceOntologfyContentProviderNewClassNameObserveValue,
				null, null);
		//
		return bindingContext;
	}

	private OntClass getSourceOntClass() {
		return sourceOntClass;
	}

	public void setSourceOntClass(OntClass sourceOntClass) {
		this.sourceOntClass = sourceOntClass;
		referenceOntologfyContentProvider.setNewClassName(sourceOntClass
				.getLocalName());
	}
}
