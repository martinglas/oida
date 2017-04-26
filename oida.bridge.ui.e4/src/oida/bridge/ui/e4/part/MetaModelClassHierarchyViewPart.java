 
package oida.bridge.ui.e4.part;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Injector;

import oida.bridge.service.IOIDABridge;
import oida.bridge.ui.MetaModelClassHierarchyView.MetaModelClassHierarchyViewInjectorProvider;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-25
 *
 */
public class MetaModelClassHierarchyViewPart {
	public static final String PART_ID = "oida.bridge.ui.e4.part.metamodelclasshierarchyview";
	
	private TreeViewer treeViewer;
	private ViewerFactory viewerFactory;
		
	@PostConstruct
	public void postConstruct(Composite parent, ESelectionService selectionService, IOIDABridge oidaBridge) {
		Injector injector = MetaModelClassHierarchyViewInjectorProvider.getInjector();

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
		
		viewerFactory.initialize(treeViewer, oidaBridge.getCurrentMetaModelResource());
	}
}