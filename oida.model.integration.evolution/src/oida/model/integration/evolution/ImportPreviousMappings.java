/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.evolution;

import static oida.ontology.util.OntologyHelper.DEFAULTONTOLOGY_URI;
import static oida.ontology.util.OntologyHelper.REFERENCE_ONTOLOGY_FILENAME;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import oida.model.integration.modelprovider.ModelProviderHelper;
import oida.ontology.io.Owl;
import oida.ontology.model.OntologyModel;
import oida.ontology.util.OntologyHelper;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.ontology.ProfileRegistry;
import org.apache.jena.rdf.model.ModelFactory;

/**
 * This class handles the import of previous mappigs form an onology generated from a file to a reference onology
 * containing domain concepts.
 * 
 * @author Martin Glas
 */
public class ImportPreviousMappings {

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	private OntModel previousOntModel;
	private EObject rootEObject;

	private String previousOntologyFileName = "";

	private Ontology oldOntology;

	private String filePath = "";

	/**
	 * This is the constructor of the class.
	 * 
	 * @param rootEObject the root object of the source model which generated
	 * @param defaultOntologyFilePath the file path where the prvious ontologyies can be foud
	 */
	public ImportPreviousMappings(EObject rootEObject, String defaultOntologyFilePath) {

		setRootEObject(rootEObject);
		setOntologyFilePath(defaultOntologyFilePath);

	}

	/**
	 * This methods loads the previously mapped source ontology into the OntologyModel singleton.
	 */
	public void loadIntoOntologyModel() {
		if (getPreviousOntologyFileName() != "") {
			OntologyHelper.addMerologyFileEntry();
			OntologyHelper.addReferenceOntologyFileEntry();

			URI previousOntologyURI = OntologyHelper.getOntologyURI(ModelProviderHelper
				.getModelElementName(getRootEObject()) + ".owl");

			URL previousOntologyURL = OntologyHelper.getFileURL(getFilePath(), getPreviousOntologyFileName());

			setOldOntology(Owl.loadFromOwl(previousOntologyURI, previousOntologyURL, previousOntologyURI));
		}
	}

	private void setOldOntology(Ontology oldOntology) {
		this.oldOntology = oldOntology;

	}

	/**
	 * This method loads the previously mapped ontology into the previousOntModel.
	 */
	public void loadOntology() {
		setPreviousOntModel(ModelFactory.createOntologyModel(ProfileRegistry.OWL_DL_LANG));
		OntologyHelper.addMerologyFileEntry(getPreviousOntModel());
		OntologyHelper.addReferenceOntologyFileEntry(getPreviousOntModel());
		URI previousOntologyURI = OntologyHelper.getOntologyURI(ModelProviderHelper
			.getModelElementName(getRootEObject()) + ".owl");

		URL previousOntologyURL = OntologyHelper.getFileURL(getFilePath(), getPreviousOntologyFileName());

		Owl.loadFromOwlToOntModelWithImport(previousOntologyURI, previousOntologyURL, getPreviousOntModel());
		setOldOntology(getPreviousOntModel().getOntology(previousOntologyURI.toString()));

	}

	private String getFilePath() {

		return this.filePath;
	}

	/**
	 * This method adds a property change listener.
	 * 
	 * @param propertyName the name of the property
	 * @param listener the listener
	 */
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * This method removes a property change listener.
	 * 
	 * @param listener the listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	/**
	 * This method returns the file name of the previously mapped ontology.
	 * 
	 * @return the name of the previously mapped ontology
	 */
	public String getPreviousOntologyFileName() {
		return previousOntologyFileName;
	}

	/**
	 * This method sets the file name of the previously mapped ontology.
	 * 
	 * @param previousOntologyFileName the name of the previously mapped ontology
	 */
	public void setPreviousOntologyFileName(String previousOntologyFileName) {
		propertyChangeSupport.firePropertyChange("previousOntologyFileName", this.previousOntologyFileName,
			this.previousOntologyFileName = previousOntologyFileName);
	}

	/**
	 * This method returns and OntModel which contains the previously mapped ontology.
	 * 
	 * @return the OntModel which contains the previously mapped ontology
	 */
	public OntModel getPreviousOntModel() {
		return previousOntModel;
	}

	/**
	 * This method sets the OntModel object which contains the previously mapped ontology.
	 * 
	 * @param previousOntModel an OntModel object which contains the previously mapped ontology
	 */
	public void setPreviousOntModel(OntModel previousOntModel) {
		this.previousOntModel = previousOntModel;
	}

	/**
	 * This method returns the root EObject of the current source model.
	 * 
	 * @return the root EObject of the current source model
	 */
	public EObject getRootEObject() {
		return rootEObject;
	}

	/**
	 * This method sets the root EObject of the current source model.
	 * 
	 * @param rootEObject the root EObject of the current source model
	 */
	public void setRootEObject(EObject rootEObject) {
		this.rootEObject = rootEObject;
	}

	/**
	 * This method sets the filePath where the ontologies are stored.
	 * 
	 * @param thefilePath the file path to the ontologies
	 */
	public void setOntologyFilePath(String thefilePath) {
		propertyChangeSupport.firePropertyChange("filePath", this.filePath, this.filePath = thefilePath);

	}

	/**
	 * This method imports the equivalences from a previously set ontology into the current source ontology. Thereby,
	 * obsolete equivalences are dropped.
	 */
	public List<URI> importEquivalences() {
		List<URI> obsoleteMappingsURIs = new ArrayList<URI>();
		loadOntology();

		// List Classes with equivalences

		obsoleteMappingsURIs.addAll(importOntClassEquivalences());
		obsoleteMappingsURIs.addAll(importIndividualEquivalences());
		obsoleteMappingsURIs.addAll(importOntPropertyEquivaences());
		// List DatatypeProperties with equivalences
		return obsoleteMappingsURIs;
	}

	private List<URI> importOntPropertyEquivaences() {
		List<URI> obsoleteMappingsURIs = new ArrayList<URI>();
		OntologyModel ontologyModel = OntologyModel.getInstance();
		List<OntProperty> newBaseOntProperties = ontologyModel.getResourcesByClass(OntProperty.class);
		// List ObjectProperties with equivalences
		for (OntProperty previousOntProperty : OntologyHelper.getOntResourcesByClass(OntProperty.class, oldOntology,
			getPreviousOntModel())) {
			for (OntProperty equivalentOntProperty : previousOntProperty.listEquivalentProperties().toList()) {
				if (equivalentOntProperty.getNameSpace()
					.equals(DEFAULTONTOLOGY_URI + REFERENCE_ONTOLOGY_FILENAME + "#")) {

					List<OntProperty> candidateOntProperties = new ArrayList<OntProperty>();
					for (OntProperty newOntProperty : newBaseOntProperties) {
						if (newOntProperty.getNameSpace().equals(previousOntProperty.getNameSpace())) {
							if (newOntProperty.getLocalName().endsWith(previousOntProperty.getLocalName())) {
								candidateOntProperties.add(newOntProperty);
							}
						}
					}
					if (candidateOntProperties.size() == 1) {

						OntProperty newReferenceOntProperty = ontologyModel.getOntProperty(URI
							.createURI(equivalentOntProperty.getURI()));
						if (newReferenceOntProperty != null) {
							candidateOntProperties.get(0).addEquivalentProperty(newReferenceOntProperty);

						} else {
							System.out.println("Mapping of " + previousOntProperty.getLocalName()
								+ " is obsolete. Target " + equivalentOntProperty.getLocalName()
								+ " does not exist any more.");
						}

					} else if (candidateOntProperties.isEmpty()) {
						System.out.println("Mapping of " + previousOntProperty.getLocalName()
							+ " is obsolete. It does not exist any more.");
						obsoleteMappingsURIs.add(URI.createURI(previousOntProperty.toString()));
					} else if (candidateOntProperties.size() > 1) {
						System.out.println("Mapping of " + previousOntProperty.getLocalName()
							+ " is ambiguous. Remap the following Properties:");
						for (OntProperty ontProperty : candidateOntProperties) {
							System.out.println(ontProperty.getLocalName());
						}
						obsoleteMappingsURIs.add(URI.createURI(previousOntProperty.toString()));
					}
				}
			}
		}
		return obsoleteMappingsURIs;
	}

	private List<URI> importIndividualEquivalences() {
		List<URI> obsoleteMappingsURIs = new ArrayList<URI>();
		OntologyModel ontologyModel = OntologyModel.getInstance();
		List<Individual> newBaseIndividuals = ontologyModel.getBaseIndividuals();
		// List Individuals with equivalences
		for (Individual previousIndividual : OntologyHelper.getOntResourcesByClass(Individual.class, oldOntology,
			getPreviousOntModel())) {
			for (Individual equivalentIndividual : OntologyHelper.getIndividuals(previousIndividual.listSameAs()
				.toList())) {
				if (equivalentIndividual.getNameSpace().equals(DEFAULTONTOLOGY_URI + REFERENCE_ONTOLOGY_FILENAME + "#")) {
					// Individual newIndividual =
					// ontologyModel.getIndividual(URI
					// .createURI(previousIndividual.getURI()));
					Individual newReferenceIndividual = ontologyModel.getIndividual(URI.createURI(equivalentIndividual
						.getURI()));
					List<Individual> candidateIndividuals = new ArrayList<Individual>();
					for (Individual newIndividual : newBaseIndividuals) {
						if (newIndividual.getNameSpace().equals(previousIndividual.getNameSpace())) {
							if (newIndividual.getLocalName().endsWith(previousIndividual.getLocalName())) {
								candidateIndividuals.add(newIndividual);
							}
						}
					}
					if (candidateIndividuals.size() == 1) {
						candidateIndividuals.get(0).addSameAs(newReferenceIndividual);
					} else if (candidateIndividuals.isEmpty()) {
						System.out.println("Mapping of " + previousIndividual.getLocalName()
							+ " is obsolete. It does not exist any more.");
						obsoleteMappingsURIs.add(URI.createURI(previousIndividual.toString()));
					} else if (candidateIndividuals.size() > 1) {
						System.out.println("Mapping of " + previousIndividual.getLocalName()
							+ " is ambiguous. Remap the following Individuals:");

						obsoleteMappingsURIs.add(URI.createURI(previousIndividual.toString()));
						for (Individual individual : candidateIndividuals) {
							System.out.println(individual.getLocalName());
						}
					}
				}
			}
		}
		return obsoleteMappingsURIs;
	}

	private List<URI> importOntClassEquivalences() {
		List<URI> obsoleteMappingsURIs = new ArrayList<URI>();
		OntologyModel ontologyModel = OntologyModel.getInstance();
		List<OntClass> newBaseOntClasses = ontologyModel.getBaseClasses();

		for (OntClass previousOntClass : OntologyHelper.getOntResourcesByClass(OntClass.class, oldOntology,
			getPreviousOntModel())) {
			for (OntClass equivalentClass : previousOntClass.listEquivalentClasses().toList()) {
				// Check of the mapping is to the Reference
				if (equivalentClass.getNameSpace().equals(DEFAULTONTOLOGY_URI + REFERENCE_ONTOLOGY_FILENAME + "#")) {

					List<OntClass> candidateOntClasses = new ArrayList<OntClass>();
					for (OntClass newOntClass : newBaseOntClasses) {
						if (newOntClass.getNameSpace().equals(previousOntClass.getNameSpace())) {
							if (newOntClass.getLocalName().endsWith(previousOntClass.getLocalName())) {
								candidateOntClasses.add(newOntClass);
							}
						}
					}
					// OntClass newOntClass = ontologyModel.getOntClass(URI
					// .createURI(previousOntClass.getURI()));
					// if (newOntClass != null) {
					if (candidateOntClasses.size() == 1) {
						OntClass newOntClass = candidateOntClasses.get(0);
						OntClass newReferenceOntClass = ontologyModel.getOntClass(URI.createURI(equivalentClass
							.getURI()));
						if (newReferenceOntClass != null) {
							newOntClass.addEquivalentClass(newReferenceOntClass);

						} else {
							System.out.println("Mapping of " + previousOntClass.getLocalName()
								+ " is obsolete. Target " + equivalentClass.getLocalName()
								+ " does not exist any more.");
						}

					} else if (candidateOntClasses.isEmpty()) {
						System.out.println("Mapping of " + previousOntClass.getLocalName()
							+ " is obsolete. It does not exist any more.");
						obsoleteMappingsURIs.add(URI.createURI(previousOntClass.toString()));
					} else if (candidateOntClasses.size() > 1) {
						System.out.println("Mapping of " + previousOntClass.getLocalName()
							+ " is ambiguous. Remap the following Classes:");
						for (OntClass ontClass : candidateOntClasses) {
							System.out.println(ontClass.getLocalName());
						}
						obsoleteMappingsURIs.add(URI.createURI(previousOntClass.toString()));
					}
				}
			}
		}
		return obsoleteMappingsURIs;
	}
}
