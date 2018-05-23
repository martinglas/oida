package net.bhl.oida.ontology.ui.e4.part.classcontext;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.swt.widgets.Composite;

public class ClassContextViewPart implements ISelectionListener {
//	private ContentViewer viewer;
	
	@PostConstruct
	public void postConstruct(Composite parent, ESelectionService selectionService) {
//		selectionService.addSelectionListener(ClassHierarchyViewPart.PART_ID, this);
//		
//		ZestFxJFaceModule module = new ZestFxJFaceModule();
//
//		viewer = new ZestContentViewer((Module)module);
//		viewer.createControl(parent, SWT.CENTER);
//		
//		viewer.setLabelProvider(new NodeLabelProvider());
//		viewer.setLayoutAlgorithm(new SpaceTreeLayoutAlgorithm(SpaceTreeLayoutAlgorithm.BOTTOM_UP));
//		//viewer.setLayoutAlgorithm(new TreeLayoutAlgorithm(TreeLayoutAlgorithm.BOTTOM_UP));
//		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
//			public void selectionChanged(SelectionChangedEvent event) {
//				System.out.println("Selection changed: " + (event.getSelection()));
//			}
//		});
	}

	@Override
	public void selectionChanged(MPart part, Object selection) {
//		if (selection instanceof OntologyClass) {
//			viewer.setContentProvider(new GraphContentProvider((OntologyClass)selection));
//			viewer.setInput(selection);
//		}
	}
}
