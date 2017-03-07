/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.parsley.edit.domain.InjectableAdapterFactoryEditingDomain;
import org.osgi.framework.ServiceReference;

import oida.ontology.Activator;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.IOntologyManagerFactory;
import oida.ontology.manager.OntologyManagerException;
import oida.ontologyMgr.Library;
import oida.ontologyMgr.OntologyFile;
import oida.ontologyMgr.OntologyMgrFactory;
import oida.ontologyMgr.OntologyMgrPackage;
import oida.ontologyMgr.provider.OntologyMgrItemProviderAdapterFactory;
import oida.util.OIDAUtil;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 13.12.2016
 *
 */
@Creatable
@Singleton
public final class OIDAOntologyService extends AbstractOIDAOntologyService implements INotifyChangedListener {
	public static final String OIDAONTOLOGY_SERVICE_NAME = "OIDA Ontology Service";

	private URI uriLibrary = URI.createFileURI(OIDAUtil.getOIDAWorkPath() + OIDAUtil.ONTOLOGY_LIBRARY_FILE);
	private URI uriManager = URI.createFileURI(OIDAUtil.getOIDAWorkPath() + OIDAUtil.ONTOLOGY_MANAGER_FILE);

	private EditingDomain editingDomain;
	private Resource libraryResource;

	private IOntologyManagerFactory managerFactory;

	private Map<OntologyFile, IOntologyManager> managedOntologies;

	private Resource managedOntologyResource;

	public OIDAOntologyService() {
		super();

		System.out.println(OIDAOntologyService.OIDAONTOLOGY_SERVICE_NAME + ": Initialization started.");

		OntologyMgrItemProviderAdapterFactory adapterFactory = new OntologyMgrItemProviderAdapterFactory();
		// adapterFactory.addListener(this);

		ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(adapterFactory);
		composedAdapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		composedAdapterFactory.addListener(this);

		editingDomain = new InjectableAdapterFactoryEditingDomain(composedAdapterFactory);

		managedOntologies = new HashMap<OntologyFile, IOntologyManager>();

		libraryResource = loadExistingOIDAServiceData(uriLibrary);
		managedOntologyResource = editingDomain.createResource(uriManager.toString());

		if (libraryResource == null || libraryResource.getContents().isEmpty())
			initializeNewOIDAServiceData(uriLibrary);

		libraryResource.getResourceSet().eAdapters().add(this);

		OIDAUtil.createOIDAWorkDirectory();

		IOntologyManagerFactory managerFactory = loadManagerFactoryExtension();

		if (managerFactory != null) {
			this.managerFactory = managerFactory;
		} else {
			System.out.println(OIDAONTOLOGY_SERVICE_NAME + ": Initialized without an Ontology Manager Factory.");
		}

		if (getLibrary().getReferenceOntology() != null) {
			System.out.println(OIDAONTOLOGY_SERVICE_NAME + ": Loading reference ontology...");
			getOntologyManager(getLibrary().getReferenceOntology(), true);
		} else
			System.out.println(OIDAONTOLOGY_SERVICE_NAME + ": No reference ontology set.");

		System.out.println(OIDAOntologyService.OIDAONTOLOGY_SERVICE_NAME + ": Service registered.");
	}

	private IOntologyManagerFactory loadManagerFactoryExtension() {
		ServiceReference<?> serviceReference = Activator.getBundleContext().getServiceReference(IExtensionRegistry.class.getName());
		IExtensionRegistry registry = (IExtensionRegistry)Activator.getBundleContext().getService(serviceReference);

		if (registry != null) {
			IConfigurationElement[] config = registry.getConfigurationElementsFor(Activator.ONTOLOGYMANAGERFACTORY_EXTENSIONPOINT_ID);
			try {
				for (IConfigurationElement e : config) {
					System.out.println(OIDAOntologyService.OIDAONTOLOGY_SERVICE_NAME + ": Evaluating ontology manager extensions.");
					final Object o = e.createExecutableExtension("class");
					if (o instanceof IOntologyManagerFactory) {
						System.out.println(OIDAOntologyService.OIDAONTOLOGY_SERVICE_NAME + ": Initialized with manager '" + o.getClass().getName() + "'.");
						return (IOntologyManagerFactory)o;
					}
				}
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}

		return null;
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
		if (managedOntologies.containsKey(ontologyFile))
			return managedOntologies.get(ontologyFile);

		IOntologyManager mgr = managerFactory.getNewManager();

		try {
			mgr.loadOntology(ontologyFile);
			managedOntologyResource.getContents().add(mgr.getOntology());
			managedOntologies.put(ontologyFile, mgr);

			System.out.println(OIDAONTOLOGY_SERVICE_NAME + ": Added new ontology manager for: " + ontologyFile.getFileName() + ".");
			return mgr;
		} catch (OntologyManagerException e) {
			if (createIfNotExisting) {
				try {
					// TODO
					mgr.createOntology("http://de.oida/" + ontologyFile.getFileName().replace(".owl", "").replace("\\", ""));
					mgr.setOntologyFile(ontologyFile);
					managedOntologyResource.getContents().add(mgr.getOntology());
					managedOntologies.put(ontologyFile, mgr);

					System.out.println(OIDAONTOLOGY_SERVICE_NAME + ": Added new ontology manager for: " + ontologyFile.getFileName() + ".");
					return mgr;

				} catch (OntologyManagerException e1) {
					e1.printStackTrace();
				}
			} else
				return null;
		}

		return null;
	}

}