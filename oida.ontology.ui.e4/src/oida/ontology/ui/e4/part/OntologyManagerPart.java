package oida.ontology.ui.e4.part;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Injector;

import oida.ontology.OntologyPackage;
import oida.ontology.service.IOIDAOntologyService;
import oida.ontology.ui.OntologyManagerView.OntologyManagerViewInjectorProvider;

/**
 * 
 * @since 2016-11-08
 * @author Michael.Shamiyeh
 *
 */
public class OntologyManagerPart {
	public static final String ONTOLOGYMGRPART_ID = "oida.ontology.ui.e4.part.ontologymanager";
	
	private TableViewer tableViewer;
	
	@Inject
	IOIDAOntologyService ontologyService;
	
	@Inject
	ESelectionService selectionService;

	@PostConstruct
	public void postConstruct(Composite parent) {
		// Guice injector
		Injector injector = OntologyManagerViewInjectorProvider.getInjector();
		
		ViewerFactory viewerFactory = injector.getInstance(ViewerFactory.class);
		
		tableViewer = viewerFactory.createTableViewer(parent, SWT.FULL_SELECTION, OntologyPackage.eINSTANCE.getOntology());
		tableViewer.setContentProvider(new ObservableListContentProvider());
		tableViewer.setInput(ontologyService.getManagedOntologies());
		
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
	        @Override
	        public void selectionChanged(SelectionChangedEvent event) {
	                IStructuredSelection selection = tableViewer.getStructuredSelection();
	                selectionService.setSelection(selection.getFirstElement());
	        }
		});
	}
}
