/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.inject.Singleton;

import org.eclipse.core.runtime.CoreException;
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

import oida.ontology.Activator;
import oida.ontology.Ontology;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.IOntologyManagerFactory;
import oida.ontology.manager.OntologyManagerException;
import oida.ontology.manager.context.IGlobalOntologyContext;
import oida.ontologyMgr.Library;
import oida.ontologyMgr.OntologyFile;
import oida.ontologyMgr.OntologyMgrFactory;
import oida.ontologyMgr.OntologyMgrPackage;
import oida.ontologyMgr.provider.OntologyMgrItemProviderAdapterFactory;
import oida.util.ExtensionPointUtil;
import oida.util.OIDAUtil;
import oida.util.constants.FileConstants;
import oida.util.constants.StringConstants;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 13.12.2016
 *
 */
@Creatable
@Singleton
public final class OIDAOntologyService extends AbstractOIDAOntologyService implements INotifyChangedListener, IGlobalOntologyContext {
	private URI uriLibrary = URI.createFileURI(OIDAUtil.getOIDAWorkPath() + FileConstants.ONTOLOGY_LIBRARY_FILE);
	private URI uriManager = URI.createFileURI(OIDAUtil.getOIDAWorkPath() + FileConstants.ONTOLOGY_MANAGER_FILE);

	private EditingDomain editingDomain;
	private Resource libraryResource;

	private IOntologyManagerFactory managerFactory;

	private Map<Ontology, IOntologyManager> managedOntologies;

	private Resource managedOntologyResource;

	// TODO split in multiple methods:
	public OIDAOntologyService() {
		super();

		System.out.println(MSG_PREFIX + "Initialization started.");

		OntologyMgrItemProviderAdapterFactory adapterFactory = new OntologyMgrItemProviderAdapterFactory();
		// adapterFactory.addListener(this);

		ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(adapterFactory);
		composedAdapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		composedAdapterFactory.addListener(this);

		editingDomain = new InjectableAdapterFactoryEditingDomain(composedAdapterFactory);

		managedOntologies = new HashMap<Ontology, IOntologyManager>();

		libraryResource = loadExistingOIDAServiceData(uriLibrary);
		managedOntologyResource = editingDomain.createResource(uriManager.toString());

		if (libraryResource == null || libraryResource.getContents().isEmpty())
			initializeNewOIDAServiceData(uriLibrary);

		libraryResource.getResourceSet().eAdapters().add(this);

		OIDAUtil.createOIDAWorkDirectory();

		try {
			System.out.println(OIDAOntologyService.MSG_PREFIX + "Evaluating ontology manager extensions.");
			this.managerFactory = ExtensionPointUtil.loadSingleExtension(Activator.getExtensionRegistry(), IOntologyManagerFactory.class, Activator.ONTOLOGYMANAGERFACTORY_EXTENSIONPOINT_ID);
			System.out.println(MSG_PREFIX + "Initialized with manager '" + this.managerFactory.getClass().getName() + "'.");
		} catch (CoreException e1) {
			e1.printStackTrace();
			System.out.println(MSG_PREFIX + "Initialized without an Ontology Manager Factory.");
		}

		if (getLibrary().getReferenceOntology() != null) {
			System.out.println(MSG_PREFIX + "Loading reference ontology...");
			setReferenceOntologyManager(getOntologyManager(getLibrary().getReferenceOntology(), true));
		} else
			System.out.println(MSG_PREFIX + "No reference ontology set.");

		getMereology();

		System.out.println(OIDAOntologyService.MSG_PREFIX + "Service registered.");
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
				setReferenceOntologyManager(getOntologyManager((OntologyFile)notification.getNewValue(), true));
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
		mgr.setGlobalOntologyContext(this);

		try {
			Ontology ontology = mgr.loadOntology(ontologyFile);
			managedOntologyResource.getContents().add(mgr.getOntology());
			managedOntologies.put(ontology, mgr);

			System.out.println(MSG_PREFIX + "Added new ontology manager for: " + ontologyFile.getFileName() + ".");
			return mgr;
		} catch (OntologyManagerException e) {
			if (createIfNotExisting) {
				try {
					// TODO
					Ontology ontology = mgr
							.createOntology("http://de.oida/" + ontologyFile.getFileName().replace(".owl", StringConstants.EMPTY).replace(StringConstants.BACKSLASH, StringConstants.EMPTY));
					mgr.setOntologyFile(ontologyFile);
					managedOntologyResource.getContents().add(mgr.getOntology());
					managedOntologies.put(ontology, mgr);

					System.out.println(MSG_PREFIX + "Added new ontology manager for: " + ontologyFile.getFileName() + ".");
					return mgr;

				} catch (OntologyManagerException e1) {
					e1.printStackTrace();
				}
			} else
				return null;
		}

		return null;
	}

	@Override
	public Optional<IOntologyManager> findOntology(String ontologyIRI) {
		for (IOntologyManager manager : managedOntologies.values()) {
			Optional<IOntologyManager> foundOntology = findOntologyWithin(manager.getOntology(), ontologyIRI);

			if (foundOntology.isPresent())
				return foundOntology;
		}

		return Optional.empty();
	}

	private Optional<IOntologyManager> findOntologyWithin(Ontology ontology, String wantedOntologyIRI) {
		if (ontology.getName().contentEquals(wantedOntologyIRI))
			return Optional.of(managedOntologies.get(ontology));

		for (Ontology importOntology : ontology.getImports()) {
			Optional<IOntologyManager> foundOntology = findOntologyWithin(importOntology, wantedOntologyIRI);

			if (foundOntology.isPresent())
				return foundOntology;
		}

		return Optional.empty();
	}
}