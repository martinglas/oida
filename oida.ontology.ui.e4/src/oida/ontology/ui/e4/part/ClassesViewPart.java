package oida.ontology.ui.e4.part;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Injector;

import oida.ontology.Ontology;
import oida.ontology.ui.ClassesView.ClassesViewInjectorProvider;


/**
 * 
 * @since 2016-11-08
 * @author Michael.Shamiyeh
 *
 */
public class ClassesViewPart implements ISelectionListener {
	public static final String PART_ID = "oida.ontology.ui.e4.part.classesView";
	
	private TreeViewer treeViewer;
	private ViewerFactory viewerFactory;
	
	private Ontology currentOntology;
	public Ontology getCurrentOntology() {
		return null;
	}

	@PostConstruct
	public void postConstruct(Composite parent, ESelectionService selectionService) {
		selectionService.addSelectionListener(OntologyManagerPart.PART_ID, this);

		Injector injector = ClassesViewInjectorProvider.getInjector();

		viewerFactory = injector.getInstance(ViewerFactory.class);

		treeViewer = new TreeViewer(parent);
	}

	@Override
	public void selectionChanged(MPart part, Object selection) {
		ResourceSet rs = new ResourceSetImpl();
		Resource r = new ResourceImpl();
		r.getContents().add((EObject)selection);
		
		rs.getResources().add(r);
		
		//currentOntology.
		
		viewerFactory.initialize(treeViewer, r);
	}
}
