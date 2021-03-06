package net.bhl.oida.bridge.ui.e4.part;

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

import net.bhl.oida.bridge.service.OIDABridge;
import net.bhl.oida.bridge.service.OIDABridgeException;
import net.bhl.oida.bridge.ui.ClassHierarchyView.ClassHierarchyViewInjectorProvider;
import net.bhl.oida.ontology.model.owlontology.Ontology;

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
    public void postConstruct(Composite parent, ESelectionService selectionService, MPart part, OIDABridge oidaBridge) {
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
	
	Ontology referenceOntology = null;
	try {
	    referenceOntology = oidaBridge.getReferenceOntology();
	} catch (OIDABridgeException e) {
	    e.printStackTrace();
	};
	
	if (referenceOntology != null)
	    classHierarchyViewerFactory.initialize(treeViewer, referenceOntology);
    }
}
