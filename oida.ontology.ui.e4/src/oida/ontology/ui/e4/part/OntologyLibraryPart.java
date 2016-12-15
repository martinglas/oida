package oida.ontology.ui.e4.part;

import java.io.IOException;
import java.util.EventObject;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.parsley.composite.TreeFormComposite;
import org.eclipse.emf.parsley.composite.TreeFormFactory;
import org.eclipse.emf.parsley.edit.ui.dnd.ViewerDragAndDropHelper;
import org.eclipse.emf.parsley.menus.ViewerContextMenuHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Injector;

import oida.ontology.service.IOIDAOntologyService;
import oida.ontology.ui.OntologyLibraryView.OntologyLibraryViewInjectorProvider;

/**
 * 
 * @since 2016-11-08
 * @author Michael.Shamiyeh
 *
 */
public class OntologyLibraryPart {
	// the EMF Parley composite for showing a tree and a detail form
	private TreeFormComposite treeFormComposite;

	@Inject
	IOIDAOntologyService ontologyService;

	@Inject
	MDirtyable dirty;

	@PostConstruct
	public void postConstruct(Composite parent) {
		// Guice injector
		Injector injector = OntologyLibraryViewInjectorProvider.getInjector();

		TreeFormFactory treeFormFactory = injector.getInstance(TreeFormFactory.class);
		// create the tree-form composite
		
		treeFormComposite = treeFormFactory.createTreeFormComposite(parent, SWT.NONE);
		
		// Guice injected viewer context menu helper
		ViewerContextMenuHelper contextMenuHelper = injector.getInstance(ViewerContextMenuHelper.class);
		// Guice injected viewer drag and drop helper
		ViewerDragAndDropHelper dragAndDropHelper = injector.getInstance(ViewerDragAndDropHelper.class);

		// set context menu and drag and drop
		contextMenuHelper.addViewerContextMenu(treeFormComposite.getViewer(), ontologyService.getEditingDomain());
		dragAndDropHelper.addDragAndDrop(treeFormComposite.getViewer(), ontologyService.getEditingDomain());

		// update the composite
		treeFormComposite.update(ontologyService.getLibraryResource());
		
		ontologyService.getEditingDomain().getCommandStack().addCommandStackListener(new CommandStackListener() {
			public void commandStackChanged(EventObject event) {
				if (dirty != null)
					dirty.setDirty(true);
			}
		});
	}

	@Persist
	public void save(MDirtyable dirty) throws IOException {
		ontologyService.getLibraryResource().save(null);
		if (dirty != null) {
			dirty.setDirty(false);
		}
	}
}
