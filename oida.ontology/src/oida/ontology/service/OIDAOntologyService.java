package oida.ontology.service;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.parsley.edit.domain.InjectableAdapterFactoryEditingDomain;

import oida.ontologyMgr.Library;
import oida.ontologyMgr.OntologyMgrFactory;
import oida.ontologyMgr.provider.OntologyMgrItemProviderAdapterFactory;

public class OIDAOntologyService extends AbstractOIDAOntologyService {
	private EditingDomain editingDomain;
	
	private Resource resource;
	
	public OIDAOntologyService() {
		ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(new OntologyMgrItemProviderAdapterFactory());
		composedAdapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		
		editingDomain = new InjectableAdapterFactoryEditingDomain(composedAdapterFactory);
	}

	public void loadOIDAModel(URI uri) {
		resource = getResource(editingDomain, uri).getResource();
	}
		
	public void initializeOIDAModel(URI uri) {
		this.initializeEmptyResource(resource);
		resource = editingDomain.createResource(uri.toString());
		resource.getContents().add(OntologyMgrFactory.eINSTANCE.createLibrary());
		
		getLibrary().getOntologies().add(OntologyMgrFactory.eINSTANCE.createLocalOntology());
	}

	@Override
	public Library getLibrary() {
		if (resource == null || resource.getContents().isEmpty())
			return null;
		
		EObject root = resource.getContents().get(0);
		if (root instanceof Library)
			return (Library)root;
		
		return null;
	}
	
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}
	
	public Resource getResource() {
		return resource;
	}
}
