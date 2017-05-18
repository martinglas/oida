package oida.bridge.ui.e4.part;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Injector;

import oida.bridge.service.IOIDABridge;
import oida.bridge.ui.ClassHierarchyView.ClassHierarchyViewInjectorProvider;
import oida.ontology.service.IOIDAOntologyService;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-05-12
 *
 */
public class ReferenceOntologyClassHierarchyPart {
	public static final String PART_ID = "oida.bridge.ui.e4.part.referenceontologyclasshierarchy";
	
	private TreeViewer treeViewer;
	
	@PostConstruct
	public void postConstruct(Composite parent, ESelectionService selectionService, MPart part, IOIDABridge oidaBridge, IOIDAOntologyService oidaService) {
		Injector injector = ClassHierarchyViewInjectorProvider.getInjector();

		ViewerFactory classHierarchyViewerFactory = injector.getInstance(ViewerFactory.class);

		treeViewer = new TreeViewer(parent, SWT.SINGLE | SWT.FILL);
		treeViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = treeViewer.getStructuredSelection();

				if (!selection.isEmpty())
					selectionService.setSelection(selection.getFirstElement());
			}
		});

		classHierarchyViewerFactory.initialize(treeViewer, oidaService.getReferenceOntologyManager().get().getOntology());
	}
}
