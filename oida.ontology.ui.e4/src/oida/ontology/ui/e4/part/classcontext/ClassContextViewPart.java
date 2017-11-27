package oida.ontology.ui.e4.part.classcontext;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.gef4.layout.algorithms.TreeLayoutAlgorithm;
import org.eclipse.gef4.zest.fx.jface.ZestContentViewer;
import org.eclipse.gef4.zest.fx.jface.ZestFxJFaceModule;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Module;

import oida.ontology.OntologyClass;
import oida.ontology.ui.e4.part.ClassesViewPart;

public class ClassContextViewPart implements ISelectionListener {
	private ZestContentViewer viewer;
	
	@PostConstruct
	public void postConstruct(Composite parent, ESelectionService selectionService) {
		selectionService.addSelectionListener(ClassesViewPart.PART_ID, this);
		
		ZestFxJFaceModule module = new ZestFxJFaceModule();

		viewer = new ZestContentViewer((Module)module);
		viewer.createControl(parent, SWT.CENTER);
		
		viewer.setLabelProvider(new NodeLabelProvider());
		viewer.setLayoutAlgorithm(new TreeLayoutAlgorithm(TreeLayoutAlgorithm.BOTTOM_UP)	);
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				System.out.println("Selection changed: " + (event.getSelection()));
			}
		});
	}

	@Override
	public void selectionChanged(MPart part, Object selection) {
		if (selection instanceof OntologyClass) {
			viewer.setContentProvider(new GraphContentProvider((OntologyClass)selection));
			viewer.setInput(selection);
		}
	}
}
