package oida.util.ui.e4;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-05-08
 *
 */
public abstract class AbstractTreeViewPart implements ISelectionChangedListener {
	private TreeViewer treeViewer;
	private ViewerFactory viewerFactory;
	
	private ESelectionService selectionService;
	
	protected TreeViewer getTreeViewer() {
		return treeViewer;
	}
	
	protected ViewerFactory getViewerFactory() {
		return viewerFactory;
	}
	
	protected ESelectionService getSelectionService() {
		return selectionService;
	}
	
	@PostConstruct
	public void postConstruct(Composite parent, ESelectionService selectionService) {
		this.selectionService = selectionService;
		
		viewerFactory = initilizeViewerFactory();

		treeViewer = new TreeViewer(parent);
		treeViewer.addSelectionChangedListener(this);
		
		viewerFactory.initialize(treeViewer, getViewObject());
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		IStructuredSelection selection = treeViewer.getStructuredSelection();
		if (!selection.isEmpty())
			selectionService.setSelection(selection.getFirstElement());
		
		handleSelectionChanged(event);
	}
	
	protected abstract void handleSelectionChanged(SelectionChangedEvent event);
	
	protected abstract ViewerFactory initilizeViewerFactory();
	
	protected abstract Object getViewObject();
}
