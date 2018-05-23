/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package net.bhl.oida.ontology.ui.e4.part;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Injector;

import net.bhl.oida.ontology.service.OIDAOntologyService;
import net.bhl.oida.ontology.ui.IndividualsView.IndividualsViewInjectorProvider;

public class IndividualsViewPart implements ISelectionListener {
	public static final String PART_ID = "oida.ontology.ui.e4.part.individualsView";
	
	private TreeViewer treeViewer;
	private ViewerFactory viewerFactory;
	
	@Inject
	OIDAOntologyService oidaService;
	
	@PostConstruct
	public void postConstruct(Composite parent, ESelectionService selectionService) {
		selectionService.addSelectionListener(ClassHierarchyViewPart.PART_ID, this);

		Injector injector = IndividualsViewInjectorProvider.getInjector();

		viewerFactory = injector.getInstance(ViewerFactory.class);

		treeViewer = new TreeViewer(parent);
	}

	@Override
	public void selectionChanged(MPart part, Object selection) {
		viewerFactory.initialize(treeViewer, selection);
	}
}
