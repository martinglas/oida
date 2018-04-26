/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.ui.e4.part;

import java.io.IOException;
import java.util.EventObject;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.parsley.edit.ui.dnd.ViewerDragAndDropHelper;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Injector;

import oida.ontology.service.OIDAOntologyService;
import oida.ontology.ui.OntologyLibraryView.OntologyLibraryViewInjectorProvider;
import oida.ontologyMgr.OntologyMgrPackage;

/**
 * 
 * @since 2016-11-08
 * @author Michael.Shamiyeh
 *
 */
public class OntologyLibraryPart {
	public static final String PART_ID = "oida.ontology.ui.e4.part.ontologylibrary";

	private TreeViewer treeViewer;

	@Inject
	OIDAOntologyService oidaService;

	@Inject
	MDirtyable dirty;
	
	@Inject
	ESelectionService selectionService;

	@PostConstruct
	public void postConstruct(Composite parent) {
		// Guice injector
		Injector injector = OntologyLibraryViewInjectorProvider.getInjector();

		ViewerFactory viewerFactory = injector.getInstance(ViewerFactory.class);

		treeViewer = viewerFactory.createTreeViewerWithColumns(parent, OntologyMgrPackage.Literals.ONTOLOGY_META_INFO, oidaService.getLibraryResource());

		// Guice injected viewer context menu helper
		//ViewerContextMenuHelper contextMenuHelper = injector.getInstance(ViewerContextMenuHelper.class);
		
		// Guice injected viewer drag and drop helper
		ViewerDragAndDropHelper dragAndDropHelper = injector.getInstance(ViewerDragAndDropHelper.class);

		// set context menu and drag and drop
		//contextMenuHelper.addViewerContextMenu(treeViewer, oidaService.getEditingDomain());
		dragAndDropHelper.addDragAndDrop(treeViewer, oidaService.getEditingDomain());

		oidaService.getEditingDomain().getCommandStack().addCommandStackListener(new CommandStackListener() {
			public void commandStackChanged(EventObject event) {
				if (dirty != null)
					dirty.setDirty(true);
			}
		});

		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = treeViewer.getStructuredSelection();

				if (!selection.isEmpty())
					selectionService.setSelection(selection.getFirstElement());
			}
		});
	}
	
	@Persist
	public void save(MDirtyable dirty) throws IOException {
		oidaService.saveLibraryResource();
		if (dirty != null) {
			dirty.setDirty(false);
		}
	}
}
