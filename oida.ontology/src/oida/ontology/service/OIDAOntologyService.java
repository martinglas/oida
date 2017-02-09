package oida.ontology.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.parsley.edit.domain.InjectableAdapterFactoryEditingDomain;

import oida.ontology.Ontology;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.IOntologyManagerFactory;
import oida.ontology.manager.OntologyManagerException;
import oida.ontologyMgr.Library;
import oida.ontologyMgr.OntologyFile;
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
	private static OIDAOntologyService instance;
	public static OIDAOntologyService getInstance() {
		if (instance == null)
			instance = new OIDAOntologyService();
		
		return instance;
	}
	
	private EditingDomain editingDomain;
	private Resource resource;

	private IOntologyManagerFactory managerFactory;

	private List<Ontology> managedOntologies;

	private OIDAOntologyService() {
		super();
		
		OntologyMgrItemProviderAdapterFactory adapterFactory = new OntologyMgrItemProviderAdapterFactory();
		// adapterFactory.addListener(this);

		ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(adapterFactory);
		composedAdapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		composedAdapterFactory.addListener(this);

		editingDomain = new InjectableAdapterFactoryEditingDomain(composedAdapterFactory);

		managedOntologies = new ArrayList<Ontology>();
	}

	public void initialize(IOntologyManagerFactory managerFactory) {
		managedOntologies.clear();

		if (managerFactory != null)
			this.managerFactory = managerFactory;
		else
			System.out.println("SYMO4PD OIDA Service: Initialized without an Ontology Manager Factory.");

		if (getLibrary().getReferenceOntology() != null) {
			System.out.println("SYMO4PD OIDA Service: Loading reference ontology...");
			addOntologyManager(getLibrary().getReferenceOntology(), true);
		}
		else
			System.out.println("SYMO4PD OIDA Service: No reference ontology set.");
	}

	public void initialize(URI oidaServiceDataFileURI, IOntologyManagerFactory managerFactory) {
		resource = loadExistingOIDAServiceData(oidaServiceDataFileURI);

		if (resource == null || resource.getContents().isEmpty())
			initializeNewOIDAServiceData(oidaServiceDataFileURI);

		resource.getResourceSet().eAdapters().add(this);

		initialize(managerFactory);
	}

	public Resource loadExistingOIDAServiceData(URI oidaServiceDataFileURI) {
		if (oidaServiceDataFileURI.isFile() && new File(oidaServiceDataFileURI.toFileString()).exists())
			return editingDomain.loadResource(oidaServiceDataFileURI.toString());
		
		return null;
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
			return (Library)root;

		return null;
	}

	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	public Resource getLibraryResource() {
		return resource;
	}

	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getFeature() != null) {
			if (notification.getFeature() == OntologyMgrPackage.eINSTANCE.getLibrary_ReferenceOntology()) {
				addOntologyManager((OntologyFile)notification.getNewValue(), true);
			}
		}
	}

	@Override
	public List<Ontology> getManagedOntologies() {
		return managedOntologies;
	}

	@Override
	public IOntologyManager addOntologyManager(OntologyFile ontologyFile, boolean createIfNotExisting) {
		IOntologyManager mgr = managerFactory.getNewManager();

		try {
			mgr.loadOntology(ontologyFile);
			managedOntologies.add(mgr.getOntology());
			System.out.println("SYMO4PD OIDA Service: Added new ontology manager for: " + ontologyFile.getFileName() + ".");
		} catch (OntologyManagerException e) {
			try {
				// TODO
				mgr.createOntology("http://de.oida/" + ontologyFile.getFileName().replace(".owl", "").replace("\\", ""));
				mgr.setOntologyFile(ontologyFile);
			} catch (OntologyManagerException e1) {
				e1.printStackTrace();
			}
		}

		return mgr;
	}
}