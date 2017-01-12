package oida.ontology.ui.e4.part;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Injector;

import oida.ontology.Ontology;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyPackage;
import oida.ontology.service.IOIDAOntologyService;
import oida.ontology.ui.OntologyManagerView.OntologyManagerViewInjectorProvider;

public class IndividualsViewPart implements ISelectionListener {
	public static final String PART_ID = "oida.ontology.ui.e4.part.individualsView";
	
	@Inject
	IOIDAOntologyService oidaService;
	
	private TableViewer tableViewer;

	@PostConstruct
	public void postConstruct(Composite parent, ESelectionService selectionService) {
		selectionService.addSelectionListener(ClassesViewPart.PART_ID, this);

		// Guice injector
		Injector injector = OntologyManagerViewInjectorProvider.getInjector();

		ViewerFactory viewerFactory = injector.getInstance(ViewerFactory.class);

		tableViewer = viewerFactory.createTableViewer(parent, SWT.FULL_SELECTION, OntologyPackage.eINSTANCE.getOntology());
		tableViewer.setContentProvider(new ObservableListContentProvider());
		
		IObservableList<Ontology> observableList = new WritableList<Ontology>();
		observableList.addAll(oidaService.getManagedOntologies());
		
		tableViewer.setInput(observableList);
	}

	@Override
	public void selectionChanged(MPart part, Object selection) {
		if (selection instanceof OntologyClass) {
			
		} else {
			
		}
		
		//tableViewer.setInput();
	}
}
