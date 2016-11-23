package oida.ontology.ui.e4.part;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import oida.ontology.OntologyPackage;
import oida.ontology.service.OIDAOntologyService;

public class OntologyManagerPart {

	private TableViewer tableViewer;
	
	@Inject
	ViewerFactory viewerFactory;
	
	@Inject
	OIDAOntologyService ontologyService;

	@PostConstruct
	public void postConstruct(Composite parent) {
		// Guice injector
		//Injector injector = OntologyManagerViewInjectorProvider.getInjector();
		
		tableViewer = viewerFactory.createTableViewer(parent,  SWT.NONE, OntologyPackage.eINSTANCE.getOntology());
		
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		//tableViewer.setInput();
		

		// update the composite
		//treeFormComposite.update(ontologyService.getResource());
	}
}
