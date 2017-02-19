/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

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
public final class OIDAOntologyService extends AbstractOIDAOntologyService implements INotifyChangedListener {
	private static OIDAOntologyService instance;
	public static OIDAOntologyService getInstance() {
		if (instance == null)
			instance = new OIDAOntologyService();
		
		return instance;
	}
	
	private EditingDomain editingDomain;
	private Resource libraryResource;

	private IOntologyManagerFactory managerFactory;

	private Map<OntologyFile, IOntologyManager> managedOntologies;
	
	private Resource managedOntologyResource;

	private OIDAOntologyService() {
		super();
		
		OntologyMgrItemProviderAdapterFactory adapterFactory = new OntologyMgrItemProviderAdapterFactory();
		// adapterFactory.addListener(this);

		ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(adapterFactory);
		composedAdapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		composedAdapterFactory.addListener(this);

		editingDomain = new InjectableAdapterFactoryEditingDomain(composedAdapterFactory);
		
		managedOntologies = new HashMap<OntologyFile, IOntologyManager>();
	}

	private void initialize(IOntologyManagerFactory managerFactory) {
		if (managerFactory != null) {
			this.managerFactory = managerFactory;
		} else {
			System.out.println("SYMO4PD OIDA Service: Initialized without an Ontology Manager Factory.");
		}

		if (getLibrary().getReferenceOntology() != null) {
			System.out.println("SYMO4PD OIDA Service: Loading reference ontology...");
			getOntologyManager(getLibrary().getReferenceOntology(), true);
		} else {
			System.out.println("SYMO4PD OIDA Service: No reference ontology set.");
		}
	}

	public void initialize(URI oidaServiceDataFileURI, URI oidaManagerDataFileURI, IOntologyManagerFactory managerFactory) {
		libraryResource = loadExistingOIDAServiceData(oidaServiceDataFileURI);
		managedOntologyResource = editingDomain.createResource(oidaManagerDataFileURI.toString());

		if (libraryResource == null || libraryResource.getContents().isEmpty()) {
			initializeNewOIDAServiceData(oidaServiceDataFileURI);
		}

		libraryResource.getResourceSet().eAdapters().add(this);

		initialize(managerFactory);
	}

	public Resource loadExistingOIDAServiceData(URI oidaServiceDataFileURI) {
		if (oidaServiceDataFileURI.isFile() && new File(oidaServiceDataFileURI.toFileString()).exists())
			return editingDomain.loadResource(oidaServiceDataFileURI.toString());
		
		return null;
	}

	public void initializeNewOIDAServiceData(URI oidaDataFileURI) {
		libraryResource = editingDomain.createResource(oidaDataFileURI.toString());
		libraryResource.getContents().add(OntologyMgrFactory.eINSTANCE.createLibrary());
	}

	@Override
	public Library getLibrary() {
		if (libraryResource == null || libraryResource.getContents().isEmpty())
			return null;

		EObject root = libraryResource.getContents().get(0);
		if (root instanceof Library)
			return (Library)root;

		return null;
	}

	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	public Resource getLibraryResource() {
		return libraryResource;
	}

	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getFeature() != null) {
			if (notification.getFeature() == OntologyMgrPackage.eINSTANCE.getLibrary_ReferenceOntology()) {
				getOntologyManager((OntologyFile)notification.getNewValue(), true);
			}
		}
	}

	@Override
	public Resource getManagedOntologiesResource() {
		return managedOntologyResource;
	}

	@Override
	public IOntologyManager getOntologyManager(OntologyFile ontologyFile, boolean createIfNotExisting) {
		if (managedOntologies.containsKey(ontologyFile)) {
			return managedOntologies.get(ontologyFile);
		}
		
		IOntologyManager mgr = managerFactory.getNewManager();

		try {
			mgr.loadOntology(ontologyFile);
			managedOntologyResource.getContents().add(mgr.getOntology());
			managedOntologies.put(ontologyFile, mgr);

			System.out.println("SYMO4PD OIDA Service: Added new ontology manager for: " + ontologyFile.getFileName() + ".");
		} catch (OntologyManagerException e) {
			try {
				// TODO
				mgr.createOntology("http://de.oida/" + ontologyFile.getFileName().replace(".owl", "").replace("\\", ""));
				mgr.setOntologyFile(ontologyFile);
				managedOntologyResource.getContents().add(mgr.getOntology());
				managedOntologies.put(ontologyFile, mgr);
				
				System.out.println("SYMO4PD OIDA Service: Added new ontology manager for: " + ontologyFile.getFileName() + ".");
			} catch (OntologyManagerException e1) {
				e1.printStackTrace();
			}
		}

		return mgr;
	}

	@Override
	public OntologyFile getOntologyFile(File file) {
		// TODO Auto-generated method stub
		return null;
	}
}