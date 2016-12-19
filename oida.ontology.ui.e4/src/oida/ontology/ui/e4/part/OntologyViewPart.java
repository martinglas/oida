package oida.ontology.ui.e4.part;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Injector;

import oida.ontology.ui.OntologyView.OntologyViewInjectorProvider;

/**
 * 
 * @since 2016-11-08
 * @author Michael.Shamiyeh
 *
 */
public class OntologyViewPart implements ISelectionListener {
	private TreeViewer treeViewer;
	private ViewerFactory viewerFactory;

	@PostConstruct
	public void postConstruct(Composite parent, ESelectionService selectionService) {
		selectionService.addSelectionListener(OntologyManagerPart.ONTOLOGYMGRPART_ID, this);
		
		Injector injector = OntologyViewInjectorProvider.getInjector();
		
		viewerFactory = injector.getInstance(ViewerFactory.class);
		
		treeViewer = new TreeViewer(parent);
	}

	@Override
	public void selectionChanged(MPart part, Object selection) {
		viewerFactory.initialize(treeViewer, selection);
	}
}
