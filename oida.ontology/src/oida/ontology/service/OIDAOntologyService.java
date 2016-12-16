package oida.ontology.service;

import java.io.File;

import javax.inject.Inject;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.parsley.edit.domain.InjectableAdapterFactoryEditingDomain;

import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.IOntologyManagerFactory;
import oida.ontology.manager.OntologyManagerException;
import oida.ontologyMgr.Library;
import oida.ontologyMgr.LocalOntologyEntry;
import oida.ontologyMgr.OntologyMgrFactory;
import oida.ontologyMgr.OntologyMgrPackage;
import oida.ontologyMgr.provider.OntologyMgrItemProviderAdapterFactory;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 13.12.2016
 *
 */
public class OIDAOntologyService extends AbstractOIDAOntologyService implements INotifyChangedListener {
	private EditingDomain editingDomain;
	private Resource resource;
	
	private IOntologyManagerFactory managerFactory;
	
	private WritableList<IOntologyManager> managedOntologies;

	public OIDAOntologyService() {
		super();

		OntologyMgrItemProviderAdapterFactory adapterFactory = new OntologyMgrItemProviderAdapterFactory();
		//adapterFactory.addListener(this);
		
		ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(adapterFactory);
		composedAdapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		composedAdapterFactory.addListener(this);
		
		editingDomain = new InjectableAdapterFactoryEditingDomain(composedAdapterFactory);
		managedOntologies = new WritableList<IOntologyManager>();
	}

	public void initialize(URI oidaServiceDataFileURI, IOntologyManagerFactory managerFactory) {
		managedOntologies.clear();
		
		if (managerFactory != null)
			this.managerFactory = managerFactory;
		else
			System.out.println("OIDA Ontology Service: Initialized without an Ontology Manager Factory!");

		loadExistingOIDAServiceData(oidaServiceDataFileURI);

		if (resource == null || resource.getContents().isEmpty())
			initializeNewOIDAServiceData(oidaServiceDataFileURI);
		
		resource.getResourceSet().eAdapters().add(this);
		
		if (getLibrary().getReferenceOntology() != null)
			loadManagedOntology(getLibrary().getReferenceOntology(), true);
	}

	public void loadExistingOIDAServiceData(URI oidaServiceDataFileURI) {
		if (oidaServiceDataFileURI.isFile() && new File(oidaServiceDataFileURI.toFileString()).exists())
			resource = editingDomain.loadResource(oidaServiceDataFileURI.toString());
	}

	public void initializeNewOIDAServiceData(URI oidaDataFileURI) {
		resource = editingDomain.createResource(oidaDataFileURI.toString());
		resource.getContents().add(OntologyMgrFactory.eINSTANCE.createLibrary());
	}

	@Override
	public Library getLibrary() {
		if (resource == null || resource.getContents().isEmpty())
			return null;

		EObject root = resource.getContents().get(0);
		if (root instanceof Library)
			return (Library) root;

		return null;
	}

	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	public Resource getLibraryResource() {
		return resource;
	}

//	public IOntologyManager addManagedOntology(LocalOntologyEntry localOntology) throws OntologyManagerException {
//		IOntologyManager mgr = managerFactory.getNewManager();
//
//		Ontology ontologyObj = mgr.loadOntology(localOntology, true);
//
//		managedOntologies.put(ontologyObj, mgr);
//
//		return mgr;
//	}

//	public IOntologyManager getManagedOntology(String ontologyName) throws OntologyManagerException {
//		if (managedOntologies.containsKey(ontologyName))
//			return managedOntologies.get(ontologyName);
//		else
//			throw new OntologyManagerException("An ontology with the name '" + ontologyName + "' does not exist.");
//	}

//	public List<IOntologyManager> getManagedOntologies() {
//		return managedOntologies.keySet();
//	}
	
	@Inject
	public IOntologyManager createNewOntologyManager(IOntologyManagerFactory factory) {
		return factory.getNewManager();
	}

	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getFeature() != null) {
			if (notification.getFeature() == OntologyMgrPackage.eINSTANCE.getLibrary_ReferenceOntology()) {
				loadManagedOntology((LocalOntologyEntry)notification.getNewValue(), true);
			}
		}
	}
	
	private void loadManagedOntology(LocalOntologyEntry entry, boolean createIfNotExisting) {
		IOntologyManager mgr = managerFactory.getNewManager();
		managedOntologies.add(mgr);
		
		try {
			mgr.loadOntology(entry, createIfNotExisting);
		} catch (OntologyManagerException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public IObservableList<IOntologyManager> getManagedOntologies() {
		return managedOntologies;
	}
}