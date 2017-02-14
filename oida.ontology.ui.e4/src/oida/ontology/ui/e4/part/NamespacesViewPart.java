package oida.ontology.ui.e4.part;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Injector;

import oida.ontology.service.IOIDAOntologyService;
import oida.ontology.ui.NamespacesView.NamespacesViewInjectorProvider;


/**
 * 
 * @since 2016-11-08
 * @author Michael.Shamiyeh
 *
 */
public class NamespacesViewPart implements ISelectionListener {
	public static final String PART_ID = "oida.ontology.ui.e4.part.namespacesView";
	
	private TreeViewer treeViewer;
	private ViewerFactory viewerFactory;
	
	@Inject
	IOIDAOntologyService oidaService;

	@PostConstruct
	public void postConstruct(Composite parent, ESelectionService selectionService) {
		selectionService.addSelectionListener(OntologyManagerPart.PART_ID, this);

		Injector injector = NamespacesViewInjectorProvider.getInjector();

		viewerFactory = injector.getInstance(ViewerFactory.class);

		treeViewer = new TreeViewer(parent);
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = treeViewer.getStructuredSelection();

				if (!selection.isEmpty())
					selectionService.setSelection(selection.getFirstElement());
			}
		});
	}

	@Override
	public void selectionChanged(MPart part, Object selection) {
		viewerFactory.initialize(treeViewer, selection);
	}
}
