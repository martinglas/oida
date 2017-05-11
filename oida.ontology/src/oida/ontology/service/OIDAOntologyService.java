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
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.parsley.edit.domain.InjectableAdapterFactoryEditingDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * @author Michael Shamiyeh
 * @since 13.12.2016
 *
 */
@Creatable
@Singleton
public final class OIDAOntologyService extends EContentAdapter implements INotifyChangedListener, IGlobalOntologyContext, IOIDAOntologyService {
	protected static Logger LOGGER = LoggerFactory.getLogger(OIDAOntologyService.class);
	
	private URI uriLibrary = URI.createFileURI(OIDAUtil.getOIDAWorkPath() + FileConstants.ONTOLOGY_LIBRARY_FILE);
	private URI uriManager = URI.createFileURI(OIDAUtil.getOIDAWorkPath() + FileConstants.ONTOLOGY_MANAGER_FILE);
	
	private EditingDomain editingDomain;
	private Resource libraryResource;

	private IOntologyManagerFactory managerFactory;

	private Map<String, OntologyFile> iriMappings = new HashMap<String, OntologyFile>();
	
	private IOntologyManager referenceOntologyManager;
	
	private Map<Ontology, IOntologyManager> managedOntologies = new HashMap<Ontology, IOntologyManager>();

	private Resource managedOntologyResource;

	public OIDAOntologyService() {
		super();
		
		OntologyMgrItemProviderAdapterFactory adapterFactory = new OntologyMgrItemProviderAdapterFactory();
		// adapterFactory.addListener(this);

		ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(adapterFactory);
		composedAdapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		composedAdapterFactory.addListener(this);

		editingDomain = new InjectableAdapterFactoryEditingDomain(composedAdapterFactory);

		tryInitialization();
	}
	
	private void tryInitialization() {
		LOGGER.info("Initializing OIDA Ontology Service...");
		
		libraryResource = loadExistingOIDAServiceData(uriLibrary);
		managedOntologyResource = editingDomain.createResource(uriManager.toString());

		if (libraryResource == null || libraryResource.getContents().isEmpty())
			initializeNewOIDAServiceData(uriLibrary);

		libraryResource.getResourceSet().eAdapters().add(this);

		OIDAUtil.createOIDAWorkDirectories();

		try {
			LOGGER.info("Evaluating ontology manager extensions.");
			this.managerFactory = ExtensionPointUtil.loadSingleExtension(Activator.getExtensionRegistry(), IOntologyManagerFactory.class, Activator.ONTOLOGYMANAGERFACTORY_EXTENSIONPOINT_ID);
			LOGGER.info("Initialized with manager '" + this.managerFactory.getClass().getName() + "'.");
		} catch (CoreException e) {
			LOGGER.error("Initialized without an Ontology Manager Factory.", e);
		}
		
		autoLoadOntologies();
		
		LOGGER.info("OIDA Ontology Service initialized.");
	}

	@Override
	public Optional<IOntologyManager> getReferenceOntologyManager() {
		if (referenceOntologyManager == null) {
			Optional<IOntologyManager> optReferencOntManager = loadReferenceOntology();
			
			if (optReferencOntManager.isPresent())
				referenceOntologyManager = optReferencOntManager.get();
				else
				return Optional.empty();
		}
		
		return Optional.of(referenceOntologyManager);
	}
	
	@Override
	public Optional<IOntologyManager> loadReferenceOntology() {
		if (getLibrary().getReferenceOntology() != null) {
			LOGGER.info("Loading reference ontology...");
			
			Optional<IOntologyManager> optRefOntManager = getOntologyManager(getLibrary().getReferenceOntology());
			
			if (optRefOntManager.isPresent())
				LOGGER.info("Reference ontology loaded: '" + optRefOntManager.get().getOntologyWithIncludes().getIri() + "'.");
			else
				LOGGER.error("Reference ontology couldn't be loaded.");
			
			return optRefOntManager;
				
		} else {
			LOGGER.info("No reference ontology set.");
			return Optional.empty();
		}
	}

	public void autoLoadOntologies() {
		LOGGER.info("Auto-Load ontologies are loaded...");
		for (OntologyFile ontologyFile : getLibrary().getOntologies()) {
			if (ontologyFile.isLoadAtStartup())
				getOntologyManager(ontologyFile);
		}
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

	@Override
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	@Override
	public Resource getLibraryResource() {
		return libraryResource;
	}
	
	@Override
	public Resource getManagedOntologiesResource() {
		return managedOntologyResource;
	}

	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getFeature() != null) {
			if (notification.getFeature() == OntologyMgrPackage.eINSTANCE.getLibrary_ReferenceOntology()) {
				Optional<IOntologyManager> optReferenceOntologyMgr = getOntologyManager((OntologyFile)notification.getNewValue());
				if (optReferenceOntologyMgr.isPresent())
					referenceOntologyManager = optReferenceOntologyMgr.get();
			}
		}
	}

	@Override
	public boolean checkOntologyExistance(String iri) {
		return iriMappings.containsKey(iri);
	}
	
	@Override
	public boolean checkOntologyExistance(OntologyFile ontologyFile) {
		File checkFile = new File(ontologyFile.getPath() + ontologyFile.getFileName());
		
		if (checkFile.exists() && checkFile.isFile())
			return true;
		
		return false;
	}
	
	@Override
	public Optional<IOntologyManager> getOntologyManager(OntologyFile ontologyFile) {
		return getOntologyManager(ontologyFile, StringConstants.EMPTY, false, false);
	}
	
	@Override
	public Optional<IOntologyManager> getOntologyManager(OntologyFile ontologyFile, boolean localOntologyActive) {
		return getOntologyManager(ontologyFile, StringConstants.EMPTY, false, localOntologyActive);
	}
	
	@Override
	public Optional<IOntologyManager> getOntologyManager(OntologyFile ontologyFile, String ontologyIri, boolean localOntologyActive) {
		return getOntologyManager(ontologyFile, ontologyIri, localOntologyActive, false);
	}

	@Override
	public Optional<IOntologyManager> getOntologyManager(OntologyFile ontologyFile, String ontologyIri, boolean createIfNotExisting, boolean localOntologyActive) {
		// return existing ontology manager if possible:
		if (ontologyFile != null && managedOntologies.containsKey(ontologyFile))
			return Optional.of(managedOntologies.get(ontologyFile));
		
		IOntologyManager mgr = managerFactory.getNewManager();
		mgr.setGlobalOntologyContext(this);

		// create ontology with passed iri, if no file is available:
		if (ontologyFile == null) {
			if (ontologyIri != null) {
				try {
					Ontology ontology = mgr.createOntology(ontologyIri, localOntologyActive);
					managedOntologyResource.getContents().add(mgr.getOntologyWithIncludes());
					managedOntologies.put(ontology, mgr);
					copyIRIMappingsToManager(mgr);
					return Optional.of(mgr);
				} catch (OntologyManagerException e) {
					LOGGER.error("Ontology with IRI '" + ontologyIri + "' could not be created.", e);
					return Optional.empty();
				}
			}
			else {
				LOGGER.error("No file and no IRI set. Ontology Manager can not be created.");
				return Optional.empty();
			}
		} else {
			try {
				copyIRIMappingsToManager(mgr);
				Ontology ontology = mgr.loadOntology(ontologyFile, localOntologyActive);
				managedOntologyResource.getContents().add(mgr.getOntologyWithIncludes());
				managedOntologies.put(ontology, mgr);
				iriMappings.put(ontology.getIri(), ontologyFile);

				LOGGER.info("Added new ontology manager for: " + ontologyFile.getFileName() + ".");
				return Optional.of(mgr);
			} catch (OntologyManagerException e) {
				if (createIfNotExisting) {
					try {
						Ontology ontology;
						if (ontologyIri != null)
							ontology = mgr.createOntology(ontologyIri, localOntologyActive);
						else
							ontology = mgr.createOntology(OIDAUtil.getFileIriString(ontologyFile), localOntologyActive);
						
						mgr.setOntologyFile(ontologyFile);
						managedOntologyResource.getContents().add(mgr.getOntologyWithIncludes());
						managedOntologies.put(ontology, mgr);
						iriMappings.put(ontology.getIri(), ontologyFile);
						copyIRIMappingsToManager(mgr);

						LOGGER.info("Added new ontology manager for: " + ontologyFile.getFileName() + ".");
						return Optional.of(mgr);
					} catch (OntologyManagerException e1) {
						LOGGER.error("Ontology with IRI '" + ontologyIri + "' could not be created.", e);
						return Optional.empty();
					}
				} else
					return Optional.empty();
			}
		}
	}

	@Override
	public Optional<IOntologyManager> findOntology(String ontologyIRI) {
		for (IOntologyManager manager : managedOntologies.values()) {
			Optional<IOntologyManager> foundOntology = findOntologyWithin(manager.getOntologyWithIncludes(), ontologyIRI);

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
	
	private void copyIRIMappingsToManager(IOntologyManager manager) {
		for (String key : iriMappings.keySet())
			manager.addGlobalIRIToLocalPathMapping(key, OIDAUtil.getFileIriString(iriMappings.get(key)));
	}
}