/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge.ui.e4.part;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Injector;

import bridgemodel.recommendation.RecommendationPackage;
import oida.bridge.service.IOIDABridge;
import oida.bridge.ui.RecommendationsView.RecommendationsViewInjectorProvider;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-18
 *
 */
public class SecondaryRecommendationsViewPart {
	public static final String PART_ID = "oida.bridge.ui.e4.part.secondaryrecommendationspart";

	private TableViewer tableViewer;

	@PostConstruct
	public void postConstruct(Composite parent, ESelectionService selectionService, IOIDABridge oidaBridge) {
		Injector injector = RecommendationsViewInjectorProvider.getInjector();
		ViewerFactory viewerFactory = injector.getInstance(ViewerFactory.class);

		tableViewer = viewerFactory.createTableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION, RecommendationPackage.eINSTANCE.getRecommendation());

		tableViewer.setInput(oidaBridge.getCurrentSecondaryRecommendationsResource());

		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				
				if (!selection.isEmpty())
					selectionService.setSelection(selection.getFirstElement());
			}
		});
	}
}
