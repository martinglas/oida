package oida.ontology.ui.e4.part;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Injector;

import oida.ontology.service.IOIDAOntologyService;
import oida.ontology.ui.IndividualsView.IndividualsViewInjectorProvider;

public class IndividualsViewPart implements ISelectionListener {
	public static final String PART_ID = "oida.ontology.ui.e4.part.individualsView";
	
	private TreeViewer treeViewer;
	private ViewerFactory viewerFactory;
	
	@Inject
	IOIDAOntologyService oidaService;
	
	@PostConstruct
	public void postConstruct(Composite parent, ESelectionService selectionService) {
		selectionService.addSelectionListener(ClassesViewPart.PART_ID, this);

		Injector injector = IndividualsViewInjectorProvider.getInjector();

		viewerFactory = injector.getInstance(ViewerFactory.class);

		treeViewer = new TreeViewer(parent);
	}

	@Override
	public void selectionChanged(MPart part, Object selection) {
		viewerFactory.initialize(treeViewer, selection);
	}
}
