/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.ui.dialogs;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import oida.model.integration.evolution.ImportPreviousMappings;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ImportPreviousMappingsDialog extends Dialog {
	private DataBindingContext m_bindingContext;

	protected Object result;
	protected Shell shlLoadPreviousMapping;
	private Text textFileName;
	private ImportPreviousMappings previousMappingImportModel;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param previousMappingImportModel
	 */
	public ImportPreviousMappingsDialog(Shell parent, int style,
			ImportPreviousMappings previousMappingImportModel) {
		super(parent, style);
		setText("SWT Dialog");
		setPreviousMappingImportModel(previousMappingImportModel);
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlLoadPreviousMapping.open();
		shlLoadPreviousMapping.layout();
		Display display = getParent().getDisplay();
		while (!shlLoadPreviousMapping.isDisposed()) {
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
		shlLoadPreviousMapping = new Shell(getParent(), getStyle());
		shlLoadPreviousMapping.setSize(300, 90);
		shlLoadPreviousMapping.setText("Load previous mapping?");
		shlLoadPreviousMapping.setLayout(new GridLayout(3, false));

		Label lblFilename = new Label(shlLoadPreviousMapping, SWT.NONE);
		GridData gd_lblFilename = new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1);
		gd_lblFilename.widthHint = 60;
		lblFilename.setLayoutData(gd_lblFilename);
		lblFilename.setText("Filename");

		textFileName = new Text(shlLoadPreviousMapping, SWT.BORDER);
		textFileName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		new Label(shlLoadPreviousMapping, SWT.NONE);

		Button btnNo = new Button(shlLoadPreviousMapping, SWT.NONE);
		btnNo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlLoadPreviousMapping.close();
			}
		});
		btnNo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1,
				1));
		btnNo.setText("No");

		Button btnCleanUpMappings = new Button(shlLoadPreviousMapping,
				SWT.CHECK);
		btnCleanUpMappings.setText("Clean up mappings");

		Button btnYes = new Button(shlLoadPreviousMapping, SWT.NONE);
		GridData gd_btnYes = new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1);
		gd_btnYes.widthHint = 60;
		btnYes.setLayoutData(gd_btnYes);
		btnYes.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlLoadPreviousMapping.close();
			}
		});
		btnYes.setText("Yes");

		m_bindingContext = initDataBindings();

	}

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public ImportPreviousMappings getPreviousMappingImportModel() {
		return previousMappingImportModel;
	}

	public void setPreviousMappingImportModel(
			ImportPreviousMappings previousMappingImportModel) {
		this.previousMappingImportModel = previousMappingImportModel;
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue textFileNameObserveTextObserveWidget = SWTObservables
				.observeText(textFileName, SWT.Modify);
		IObservableValue previousMappingImportModelFileNameObserveValue = BeansObservables
				.observeValue(previousMappingImportModel,
						"previousOntologyFileName");
		bindingContext.bindValue(textFileNameObserveTextObserveWidget,
				previousMappingImportModelFileNameObserveValue, null, null);
		//
		return bindingContext;
	}
}
