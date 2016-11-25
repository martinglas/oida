package oida.ontology.ui.e4.part;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Injector;

import oida.ontology.OntologyPackage;
import oida.ontology.service.OIDAOntologyService;
import oida.ontology.ui.OntologyManagerView.OntologyManagerViewInjectorProvider;

public class OntologyManagerPart {

	private TableViewer tableViewer;
	
	@Inject
	OIDAOntologyService ontologyService;

	@PostConstruct
	public void postConstruct(Composite parent) {
		// Guice injector
		Injector injector = OntologyManagerViewInjectorProvider.getInjector();
		
		ViewerFactory viewerFactory = injector.getInstance(ViewerFactory.class);
		
		tableViewer = viewerFactory.createTableViewer(parent,  SWT.NONE, OntologyPackage.eINSTANCE.getOntology());
		
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		tableViewer.setInput(ontologyService.getManagedOntologies());
		
		// update the composite
		//treeFormComposite.update(ontologyService.getResource());
	}
}
