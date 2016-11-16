package oida.ontology.ui.e4.parts;

import java.io.IOException;
import java.util.EventObject;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.composite.TreeFormComposite;
import org.eclipse.emf.parsley.composite.TreeFormFactory;
import org.eclipse.emf.parsley.edit.ui.dnd.ViewerDragAndDropHelper;
import org.eclipse.emf.parsley.menus.ViewerContextMenuHelper;
import org.eclipse.emf.parsley.resource.ResourceLoader;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Injector;

import oida.ontology.ui.Activator;
import oida.ontology.ui.e4.E4InjectorProvider;

/**
 * 
 * @since 2016-11-08
 * @author Michael.Shamiyeh
 *
 */
public class OntologyLibraryPart {
	// the EMF Parley composite for showing a tree and a detail form
	private TreeFormComposite treeFormComposite;
	// the EMF Resource
	private Resource resource;
	// URI for EMF Resource
	private URI uri = URI.createFileURI(ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + "//OntLib.onl");

	@Inject
	MDirtyable dirty;
		
	@PostConstruct
	public void postConstruct(Composite parent) {
		// Guice injector
		Injector injector = E4InjectorProvider.getInjector();

		// The EditingDomain is needed for context menu and drag and drop
		EditingDomain editingDomain = injector.getInstance(EditingDomain.class);

		ResourceLoader resourceLoader = injector.getInstance(ResourceLoader.class);
		// load the resource
		resource = resourceLoader.getResource(editingDomain, uri).getResource();

		TreeFormFactory treeFormFactory = injector.getInstance(TreeFormFactory.class);
		// create the tree-form composite
		
		treeFormComposite = treeFormFactory.createTreeFormComposite(parent, SWT.NONE);

		// Guice injected viewer context menu helper
		ViewerContextMenuHelper contextMenuHelper = injector.getInstance(ViewerContextMenuHelper.class);
		// Guice injected viewer drag and drop helper
		ViewerDragAndDropHelper dragAndDropHelper = injector.getInstance(ViewerDragAndDropHelper.class);

		// set context menu and drag and drop
		contextMenuHelper.addViewerContextMenu(treeFormComposite.getViewer(), editingDomain);
		dragAndDropHelper.addDragAndDrop(treeFormComposite.getViewer(), editingDomain);

		// update the composite
		treeFormComposite.update(resource);

		editingDomain.getCommandStack().addCommandStackListener(new CommandStackListener() {
			public void commandStackChanged(EventObject event) {
				if (dirty != null)
					dirty.setDirty(true);
			}
		});
	}

	@Persist
	public void save(MDirtyable dirty) throws IOException {
		resource.save(null);
		if (dirty != null) {
			dirty.setDirty(false);
		}
	}
}
