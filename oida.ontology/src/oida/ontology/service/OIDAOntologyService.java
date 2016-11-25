package oida.ontology.service;

import java.io.File;
import java.util.Hashtable;
import java.util.Set;

import javax.inject.Inject;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.parsley.edit.domain.InjectableAdapterFactoryEditingDomain;

import oida.OIDASystem;
import oida.OidaFactory;
import oida.ontology.Ontology;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.IOntologyManagerFactory;
import oida.ontology.manager.OntologyManagerException;
import oida.ontologyMgr.Library;
import oida.ontologyMgr.LocalOntologyEntry;
import oida.ontologyMgr.OntologyMgrFactory;
import oida.ontologyMgr.provider.OntologyMgrItemProviderAdapterFactory;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 
 *
 */
public class OIDAOntologyService extends AbstractOIDAOntologyService implements INotifyChangedListener {
	private OIDASystem systemModel;
	
	public OIDASystem getSystemModel() {
		if (systemModel == null)
			systemModel = OidaFactory.eINSTANCE.createOIDASystem();
		
		return systemModel;
	}

	private Hashtable<Ontology, IOntologyManager> managedOntologies;
	
	private EditingDomain editingDomain;
	
	private Resource resource;
	
	@Inject
	IOntologyManagerFactory managerFactory;
	
	public OIDAOntologyService() {
		super();
		
		managedOntologies = new Hashtable<Ontology, IOntologyManager>();

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
		if (uri.isFile() && new File(uri.toFileString()).exists())
			resource = editingDomain.loadResource(uri.toString());
	}
		
	public void initializeNewOIDAOntologyServiceData(URI uri) {
		resource = editingDomain.createResource(uri.toString());
		resource.getContents().add(OntologyMgrFactory.eINSTANCE.createLibrary());
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
	
	public IOntologyManager addManagedOntology(LocalOntologyEntry localOntology) throws OntologyManagerException {
		IOntologyManager mgr = managerFactory.getNewManager();
		
		Ontology ontologyObj = mgr.loadOntology(localOntology, true);
		
		managedOntologies.put(ontologyObj, mgr);
		
		return mgr;
	}
	
	public IOntologyManager getManagedOntology(String ontologyName) throws OntologyManagerException {
		if (managedOntologies.containsKey(ontologyName))
			return managedOntologies.get(ontologyName);
		else
			throw new OntologyManagerException("An ontology with the name '" + ontologyName + "' does not exist.");
	}
	
	public Set<Ontology> getManagedOntologies() {
		return managedOntologies.keySet();
	}

	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getFeature() != null)
			System.out.println("Active ontology changed: " + notification.toString());
		
		System.out.println("Change!!! " + notification.getNewValue());
	}
}
