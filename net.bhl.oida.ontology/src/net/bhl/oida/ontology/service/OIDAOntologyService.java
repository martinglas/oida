/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package net.bhl.oida.ontology.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.inject.Singleton;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.parsley.edit.domain.InjectableAdapterFactoryEditingDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.bhl.oida.ontology.Activator;
import net.bhl.oida.ontology.manager.IOntologyManager;
import net.bhl.oida.ontology.manager.IOntologyManagerFactory;
import net.bhl.oida.ontology.manager.OntologyManagerException;
import net.bhl.oida.ontology.manager.context.IGlobalOntologyContext;
import net.bhl.oida.ontology.model.ontologymgr.Library;
import net.bhl.oida.ontology.model.ontologymgr.LocalOntologyMetaInfo;
import net.bhl.oida.ontology.model.ontologymgr.OntologyMetaInfo;
import net.bhl.oida.ontology.model.ontologymgr.OntologymgrFactory;
import net.bhl.oida.ontology.model.ontologymgr.OntologymgrPackage;
import net.bhl.oida.ontology.model.ontologymgr.provider.OntologymgrItemProviderAdapterFactory;
import net.bhl.oida.ontology.model.owlontology.Ontology;
import net.bhl.oida.util.ExtensionPointUtil;
import net.bhl.oida.util.OIDAUtil;
import net.bhl.oida.util.constants.FileConstants;
import net.bhl.oida.util.constants.StringConstants;

/**
 * 
 * @author Michael Shamiyeh
 * @since 13.12.2016
 *
 */
@Creatable
@Singleton
public final class OIDAOntologyService extends EContentAdapter implements INotifyChangedListener, IGlobalOntologyContext {
    protected static Logger LOGGER = LoggerFactory.getLogger(OIDAOntologyService.class);

    private URI uriLibrary = URI.createFileURI(OIDAUtil.getOIDAWorkPath() + FileConstants.ONTOLOGY_LIBRARY_FILE);

    private EditingDomain editingDomain;
    private Resource libraryResource;

    private IOntologyManagerFactory managerFactory;

    private Map<String, LocalOntologyMetaInfo> iriMappings = new HashMap<String, LocalOntologyMetaInfo>();
    
    private IOntologyManager referenceOntologyManager;

    private Map<Ontology, IOntologyManager> managedOntologies = new HashMap<Ontology, IOntologyManager>();

    public OIDAOntologyService() {
	super();

	OntologymgrItemProviderAdapterFactory adapterFactory = new OntologymgrItemProviderAdapterFactory();
	// adapterFactory.addListener(this);

	ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(adapterFactory);
	composedAdapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
	composedAdapterFactory.addListener(this);

	editingDomain = new InjectableAdapterFactoryEditingDomain(composedAdapterFactory);

	tryInitialization();
    }

    private void tryInitialization() {
	LOGGER.info("Initializing OIDA Ontology Service...");

	OIDAUtil.createOIDAWorkDirectories();

	libraryResource = loadExistingOIDAServiceData(uriLibrary);

	if (libraryResource == null || libraryResource.getContents().isEmpty())
	    initializeNewOIDAServiceData(uriLibrary);

	libraryResource.getResourceSet().eAdapters().add(this);

	for (OntologyMetaInfo metaInfo : getLibrary().getOntologies()) {
	    if (metaInfo instanceof LocalOntologyMetaInfo)
		iriMappings.put(metaInfo.getIri(), (LocalOntologyMetaInfo)metaInfo);
	}

	try {
	    LOGGER.info("Evaluating ontology manager extensions.");
	    this.managerFactory = ExtensionPointUtil.loadSingleExtension(Activator.getExtensionRegistry(), IOntologyManagerFactory.class, Activator.ONTOLOGYMANAGERFACTORY_EXTENSIONPOINT_ID);
	    LOGGER.info("Initialized with manager '" + this.managerFactory.getClass().getName() + "'.");
	} catch (CoreException e) {
	    LOGGER.error("Initialized without an Ontology Manager Factory.", e);
	}

	loadReferenceOntology();
	autoLoadOntologies();

	LOGGER.info("OIDA Ontology Service initialized.");
    }

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

    public Optional<IOntologyManager> loadReferenceOntology() {
	OntologyMetaInfo metaInfo = getLibrary().getReferenceOntology();

	if (metaInfo != null) {
	    LOGGER.info("Loading reference ontology...");

	    Optional<IOntologyManager> optRefOntManager;
	    if (metaInfo instanceof LocalOntologyMetaInfo)
		optRefOntManager = getLocalOntologyManager((LocalOntologyMetaInfo)metaInfo);
	    else
		optRefOntManager = getRemoteOntologyManager(metaInfo);

	    if (optRefOntManager.isPresent())
		LOGGER.info("Reference ontology loaded: '" + optRefOntManager.get().getOntology().getIri() + "'.");
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
	for (OntologyMetaInfo ontologyMetaInfo : getLibrary().getOntologies()) {
	    if (ontologyMetaInfo.isLoadAtStartup()) {
		if (ontologyMetaInfo instanceof LocalOntologyMetaInfo)
		    getLocalOntologyManager((LocalOntologyMetaInfo)ontologyMetaInfo);
		else
		    getRemoteOntologyManager(ontologyMetaInfo);
	    }
	}
    }

    public Resource loadExistingOIDAServiceData(URI oidaServiceDataFileURI) {
	if (oidaServiceDataFileURI.isFile() && new File(oidaServiceDataFileURI.toFileString()).exists())
	    return editingDomain.loadResource(oidaServiceDataFileURI.toString());

	return null;
    }

    public void initializeNewOIDAServiceData(URI oidaDataFileURI) {
	libraryResource = editingDomain.createResource(oidaDataFileURI.toString());
	libraryResource.getContents().add(OntologymgrFactory.eINSTANCE.createLibrary());
    }

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

    public void AddOntologyToLibrary(OntologyMetaInfo metaInfo) {
	Command command = AddCommand.create(getEditingDomain(), getLibrary(), OntologymgrPackage.eINSTANCE.getLibrary_Ontologies(), metaInfo);
	getEditingDomain().getCommandStack().execute(command);

	if (metaInfo instanceof LocalOntologyMetaInfo) {
	    getLocalOntologyManager((LocalOntologyMetaInfo)metaInfo);
	    iriMappings.put(metaInfo.getIri(), (LocalOntologyMetaInfo)metaInfo);
	} else
	    getRemoteOntologyManager(metaInfo);
    }

    public void SetReferenceOntology(OntologyMetaInfo metaInfo) {
	Command command = SetCommand.create(getEditingDomain(), getLibrary(), OntologymgrPackage.eINSTANCE.getLibrary_ReferenceOntology(), metaInfo);
	getEditingDomain().getCommandStack().execute(command);
    }

    public void saveLibraryResource() {
	try {
	    getLibraryResource().save(null);
	    LOGGER.info("Library saved.");
	} catch (IOException e) {
	    LOGGER.error("Library could not be saved.", e);
	}
    }

    @Override
    public void notifyChanged(Notification notification) {
	if (notification.getFeature() != null) {
	    if (notification.getFeature() == OntologymgrPackage.eINSTANCE.getLibrary_ReferenceOntology()) {
		OntologyMetaInfo metaInfo = (OntologyMetaInfo)notification.getNewValue();

		Optional<IOntologyManager> optReferenceOntologyMgr;
		if (metaInfo instanceof LocalOntologyMetaInfo)
		    optReferenceOntologyMgr = getLocalOntologyManager((LocalOntologyMetaInfo)metaInfo);
		else
		    optReferenceOntologyMgr = getRemoteOntologyManager((OntologyMetaInfo)metaInfo);

		if (optReferenceOntologyMgr.isPresent())
		    referenceOntologyManager = optReferenceOntologyMgr.get();
	    }
	}
    }

    public Map<String, LocalOntologyMetaInfo> getGlobalIRIToLocalIRIMappings() {
	return iriMappings;
    }

    public boolean checkOntologyExistance(String iri) {
	return iriMappings.containsKey(iri);
    }

    public boolean checkLocalOntologyExistance(LocalOntologyMetaInfo ontologyMetaInfo) {
	File checkFile = new File(ontologyMetaInfo.getLocalPath());

	if (checkFile.exists() && checkFile.isFile())
	    return true;

	return false;
    }

    public Optional<IOntologyManager> findOntology(String ontologyIRI) {
	for (IOntologyManager manager : managedOntologies.values()) {
	    Optional<IOntologyManager> foundOntology = findOntologyWithin(manager.getOntology(), ontologyIRI);

	    if (foundOntology.isPresent())
		return foundOntology;
	}

	return Optional.empty();
    }

    private Optional<IOntologyManager> findOntologyWithin(Ontology ontology, String wantedOntologyIRI) {
	if (ontology.getIri().contentEquals(wantedOntologyIRI))
	    return Optional.of(managedOntologies.get(ontology));

	for (Ontology importOntology : ontology.getImports()) {
	    Optional<IOntologyManager> foundOntology = findOntologyWithin(importOntology, wantedOntologyIRI);

	    if (foundOntology.isPresent())
		return foundOntology;
	}

	return Optional.empty();
    }

    public Optional<IOntologyManager> getRemoteOntologyManager(OntologyMetaInfo ontologyMetaInfo) {
	// return existing ontology manager if possible:
	if (ontologyMetaInfo != null && managedOntologies.containsKey(ontologyMetaInfo))
	    return Optional.of(managedOntologies.get(ontologyMetaInfo));

	try {
	    IOntologyManager mgr = managerFactory.getNewManager();
	    mgr.setGlobalOntologyContext(this);
	    Ontology ontology = mgr.loadOntology(ontologyMetaInfo);
	    ontologyMetaInfo.setOntology(ontology);
	    managedOntologies.put(ontology, mgr);

	    LOGGER.info("Added new ontology manager for: " + ontology.getIri() + ".");
	    return Optional.of(mgr);
	} catch (OntologyManagerException e) {

	}

	return Optional.empty();
    }

    public Optional<IOntologyManager> getLocalOntologyManager(LocalOntologyMetaInfo ontologyMetaInfo) {
	// return existing ontology manager if possible:
	if (ontologyMetaInfo != null && managedOntologies.containsKey(ontologyMetaInfo))
	    return Optional.of(managedOntologies.get(ontologyMetaInfo));

	try {
	    IOntologyManager mgr = managerFactory.getNewManager();
	    mgr.setGlobalOntologyContext(this);
	    mgr.setMetaInfo(ontologyMetaInfo);
	    Ontology ontology = mgr.loadLocalOntology(ontologyMetaInfo);
	    ontologyMetaInfo.setIri(ontology.getIri());
	    ontologyMetaInfo.setOntology(ontology);
	    managedOntologies.put(ontology, mgr);
	    getGlobalIRIToLocalIRIMappings().put(ontology.getIri(), ontologyMetaInfo);

	    LOGGER.info("Added new ontology manager for: " + ontology.getIri() + ".");
	    return Optional.of(mgr);
	} catch (OntologyManagerException e) {

	}

	return Optional.empty();
    }

    public Optional<IOntologyManager> createLocalOntology(LocalOntologyMetaInfo ontologyMetaInfo, String IRI) {
	IOntologyManager mgr = managerFactory.getNewManager();
	mgr.setGlobalOntologyContext(this);
	mgr.setMetaInfo(ontologyMetaInfo);
	
	try {
	    Ontology ontology;
	    if (IRI != null && !IRI.equals(StringConstants.EMPTY))
		ontologyMetaInfo.setIri(IRI);
	    else
		ontologyMetaInfo.setIri(OIDAUtil.getFileIriString(ontologyMetaInfo));

	    ontology = mgr.createLocalOntology(ontologyMetaInfo);
	    ontologyMetaInfo.setOntology(ontology);
	    managedOntologies.put(ontology, mgr);

	    AddOntologyToLibrary(ontologyMetaInfo);

	    LOGGER.info("New ontology with IRI '" + IRI + "' created: '" + ontologyMetaInfo.getLocalPath() + "'.");
	    return Optional.of(mgr);
	} catch (OntologyManagerException e) {
	    LOGGER.error("Ontology with IRI '" + IRI + "' could not be created.", e);
	    return Optional.empty();
	}
    }
}