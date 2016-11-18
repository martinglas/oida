package oida.ontology.service;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.parsley.edit.domain.InjectableAdapterFactoryEditingDomain;

import oida.ontologyMgr.Library;
import oida.ontologyMgr.OntologyMgrFactory;
import oida.ontologyMgr.provider.OntologyMgrItemProviderAdapterFactory;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 
 *
 */
public class OIDAOntologyService extends AbstractOIDAOntologyService implements INotifyChangedListener {
	private EditingDomain editingDomain;
	
	private Resource resource;
	
	public OIDAOntologyService() {
		super();
		
		OntologyMgrItemProviderAdapterFactory adapterFactory = new OntologyMgrItemProviderAdapterFactory();
		adapterFactory.addListener(this);
		
		ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(adapterFactory);
		composedAdapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		
		editingDomain = new InjectableAdapterFactoryEditingDomain(composedAdapterFactory);		
	}
	
	public void initialize(URI uri) {
		loadExistingOIDAOntologyServiceData(uri);
		
		if (resource == null || resource.getContents().isEmpty())
			initializeNewOIDAOntologyServiceData(uri);
	}

	public void loadExistingOIDAOntologyServiceData(URI uri) {
        ResourceSet resSet = new ResourceSetImpl();
        resource = resSet.getResource(uri, true);
	}
		
	public void initializeNewOIDAOntologyServiceData(URI uri) {
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

	@Override
	public void notifyChanged(Notification notification) {
		System.out.println("Change!!! " + notification.getNewValue());
	}
}
