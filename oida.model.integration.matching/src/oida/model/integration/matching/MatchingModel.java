/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.matching;

import static oida.ontology.util.OntologyHelper.DEFAULTONTOLOGY_URI;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import oida.model.integration.transformation.ontologygenerator.OntologyGenerator;
import oida.model.integration.transformation.ontologygenerator.RenamerStrategy;
import oida.ontology.commands.ConfirmRecommendationCommand;
import oida.ontology.io.Owl;
import oida.ontology.matcher.ManualMatch;
import oida.ontology.matcher.MatcherFactory;
import oida.ontology.model.OntologyModel;
import oida.ontology.reasoner.ReasonerService;
import oida.ontology.util.OntologyHelper;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.ontology.Ontology;

public class MatchingModel {

	private RenamerStrategy renamerStrategy;
	private OntologyGenerator ontologyGenerator;
	private OntClass currentReferenceClass;
	private List<Individual> currentIndividualCandidates;
	private List<OntResource> sourceOntResources;
	private OntologyModel ontologyModel;
	private boolean showAttributes = false;
	private boolean showReferences = false;
	private OntResource sourceMetaOntResource;
	private OntResource referenceMetaOntResource;
	private Individual sourceIndividual;
	private Individual referenceIndividual;
	private String buttonString = "Select two concepts";
	private String mappingStatus;

	private List<OntResource> metaCandiatesOntResources;

	private String referenceOntologyFileName;
	private String sourceOntologyFileName;

	private ManualMatch manualMatch;

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);
	private Object previousOntModel;

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void selectOntResource(OntResource selectedOntResource) {
		if (selectedOntResource.isIndividual()) {
			Individual selectedIndividual = selectedOntResource.asIndividual();
			if (OntologyHelper.isInOntology(ontologyModel.getBaseOntology(),
					selectedIndividual)) {
				setSourceIndividual(selectedIndividual);
				manualMatch.setSource(selectedIndividual);
			} else if (OntologyHelper.isInOntology(
					ontologyModel.getReferenceOntology(), selectedIndividual)) {
				setReferenceIndividual(selectedIndividual);
				manualMatch.setTarget(selectedIndividual);
			}

		} else {
			// Than it must be a metamodel item
			if (OntologyHelper.isInOntology(ontologyModel.getBaseOntology(),
					selectedOntResource)) {
				setSourceMetaOntResource(selectedOntResource);
				setReferenceMetaOntResource(ontologyModel
						.getReferenceOntResource(selectedOntResource));
				manualMatch.setSource(selectedOntResource);
				manualMatch.setTarget(getReferenceMetaOntResource());
			} else if (OntologyHelper.isInOntology(
					ontologyModel.getReferenceOntology(), selectedOntResource)) {
				setReferenceMetaOntResource(selectedOntResource);
				manualMatch.setTarget(selectedOntResource);
			}
		}

		updateButtonString();

	}

	public OntResource getReferenceMetaOntResource() {
		// TODO Auto-generated method stub
		return this.referenceMetaOntResource;
	}

	private void setReferenceMetaOntResource(OntResource ontResource) {
		propertyChangeSupport.firePropertyChange("referenceMetaOntResource",
				this.referenceMetaOntResource,
				this.referenceMetaOntResource = ontResource);

	}

	public void setSourceIndividual(Individual sourceIndividual) {
		propertyChangeSupport
				.firePropertyChange("sourceIndividual", this.sourceIndividual,
						this.sourceIndividual = sourceIndividual);
		setSourceMetaOntResource(ontologyModel.getBaseClass(sourceIndividual));

		// Get ReferenceOntClass of the individual and select it if
		// possible
		OntClass referenceOntClass = ontologyModel
				.getReferenceOntClass(sourceIndividual);

		if (referenceOntClass != null) {
			setReferenceMetaOntResource(referenceOntClass);

		}

		else {
			referenceOntClass = ontologyModel
					.getReferenceOntClass(getSourceMetaOntResource().asClass());
			if (referenceOntClass != null) {
				setReferenceMetaOntResource(referenceOntClass);
			}
		}

		setReferenceIndividual(ontologyModel
				.getReferenceIndividual(sourceIndividual));

	}

	private void setReferenceIndividual(Individual referenceIndividual) {
		propertyChangeSupport.firePropertyChange("referenceIndividual",
				this.referenceIndividual,
				this.referenceIndividual = referenceIndividual);

	}

	private void setReferenceMetaOntResource(OntClass referenceOntResource) {
		propertyChangeSupport.firePropertyChange("referenceMetaOntResource",
				this.referenceMetaOntResource,
				this.referenceMetaOntResource = referenceOntResource);
		if (referenceOntResource.isClass()) {
			setCurrentIndividualCandidates(ontologyModel
					.getInstances(referenceOntResource.asClass()));
		}

	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public MatchingModel(EObject rootEObject, String referenceOntologyFileName,
			RenamerStrategy renamerStrategy) {
		setRenamerStrategy(renamerStrategy);
		manualMatch = MatcherFactory.eINSTANCE.createManualMatch();
		ontologyModel = OntologyModel.getInstance();
		currentIndividualCandidates = new ArrayList<Individual>();

		setSourceOntologyFileName(renamerStrategy.getEObjectName(rootEObject)
				+ ".owl");
		// setReferenceOntologyFileName(REFERENCE_ONTOLOGY_FILENAME);
		setReferenceOntologyFileName(referenceOntologyFileName);
		URI sourceOntologyURI = URI.createURI(DEFAULTONTOLOGY_URI
				+ getSourceOntologyFileName());

		ontologyModel.setBaseOntollogyURI(sourceOntologyURI);
		ontologyGenerator = new OntologyGenerator(sourceOntologyURI,
				rootEObject, renamerStrategy);
		ontologyGenerator.generateOntClasses();
		ontologyGenerator.generateProperties();
		ontologyGenerator.generateIndividuals();

		Ontology sourceOntology = ontologyModel.getOntology(sourceOntologyURI);
		OntologyHelper.addMerologyFileEntry();

		URI referenceOntologyURI = OntologyHelper
				.getOntologyURI("reference.owl");

		// Ontology referenceOntology = Owl.importOntology(sourceOntology,
		// referenceOntologyURI,
		// OntologyHelper.getOntologyURL(getReferenceOntologyFileName()));
		Ontology referenceOntology = ontologyModel
				.getOntology(referenceOntologyURI);
		if (referenceOntology == null) {
			referenceOntology = Owl.importOntology(sourceOntology,
					referenceOntologyURI, OntologyHelper
							.getOntologyURL(getReferenceOntologyFileName()));
		}

		ontologyModel.setReferenceOntologyURI(referenceOntologyURI);

		setSourceOntResources(ontologyModel.getAllBaseOntResorces());
		updateMappingStatus();

	}

	private void setRenamerStrategy(RenamerStrategy renamerStrategy) {
		this.renamerStrategy = renamerStrategy;

	}

	public OntClass getCurrentReferenceClass() {
		return currentReferenceClass;
	}

	public void setCurrentReferenceClass(OntClass currentReferenceClass) {
		propertyChangeSupport.firePropertyChange("currentReferenceClass",
				this.currentReferenceClass,
				this.currentReferenceClass = currentReferenceClass);
		setCurrentIndividualCandidates(ontologyModel
				.getInstances(currentReferenceClass));

	}

	public List<Individual> getCurrentIndividualCandidates() {
		return currentIndividualCandidates;
	}

	public void setCurrentIndividualCandidates(
			List<Individual> currentIndividualCandidates) {
		propertyChangeSupport.firePropertyChange("currentIndividualCandidates",
				this.currentIndividualCandidates,
				this.currentIndividualCandidates = currentIndividualCandidates);
	}

	private void setSourceOntResources(List<OntResource> ontResources) {
		propertyChangeSupport
				.firePropertyChange("sourceOntResource",
						this.sourceOntResources,
						this.sourceOntResources = ontResources);
	}

	public List<OntResource> getSourceOntResources() {

		return this.sourceOntResources;

	}

	public boolean isShowAttributes() {
		return showAttributes;
	}

	public void setShowAttributes(boolean showAttributes) {
		this.showAttributes = showAttributes;
	}

	public boolean isShowReferences() {
		return showReferences;
	}

	public void setShowReferences(boolean showReferences) {
		this.showReferences = showReferences;
	}

	public String getReferenceOntologyFileName() {
		return referenceOntologyFileName;
	}

	public void setReferenceOntologyFileName(String referenceOntologyFileName) {
		this.referenceOntologyFileName = referenceOntologyFileName;

	}

	public String getSourceOntologyFileName() {
		return sourceOntologyFileName;
	}

	public void setSourceOntologyFileName(String sourceOntologyFileName) {
		this.sourceOntologyFileName = sourceOntologyFileName;
	}

	public OntResource getSourceMetaOntResource() {
		return sourceMetaOntResource;
	}

	public void setSourceMetaOntResource(OntResource ontResource) {
		propertyChangeSupport.firePropertyChange("sourceMetaOntResource",
				this.sourceMetaOntResource,
				this.sourceMetaOntResource = ontResource);

	}

	public void updateMappingStatus() {
		// getList Of all Base Resources;
		int i = 0;
		List<OntResource> baseOntResources = getSourceOntResources();
		for (OntResource baseOntResource : baseOntResources) {
			if (!OntologyHelper.hasReference(
					ontologyModel.getReferenceOntology(), baseOntResource)) {
				i++;
			} else {
			}
		}
		setMappingStatus(i + " unmapped items");

	}

	private void setMappingStatus(String newStatus) {
		propertyChangeSupport.firePropertyChange("mappingStatus",
				this.mappingStatus, this.mappingStatus = newStatus);

	}

	public List<OntResource> getMetaCandiatesOntResources() {
		List<OntResource> ontResources = new ArrayList<OntResource>();
		if (sourceMetaOntResource != null) {
			if (sourceMetaOntResource.isClass()) {

				for (OntClass ontClass : ontologyModel.getReferenceClasses()) {
					ontResources.add(ontClass);
				}
			} else if (sourceMetaOntResource.isObjectProperty()) {
				for (ObjectProperty objectProperty : ontologyModel
						.getResourcesByClass(ObjectProperty.class,
								ontologyModel.getReferenceOntology())) {
					ontResources.add(objectProperty);
				}
			}

			else if (sourceMetaOntResource.isDatatypeProperty()) {
				for (DatatypeProperty datatypeProperty : ontologyModel
						.getResourcesByClass(DatatypeProperty.class,
								ontologyModel.getReferenceOntology())) {
					ontResources.add(datatypeProperty);
				}
			}
		}
		return ontResources;
	}

	public void setMetaCandiatesOntResources(
			List<OntResource> metaCandiatesOntResources) {
		propertyChangeSupport.firePropertyChange("metaCandidatesOntResources",
				this.metaCandiatesOntResources,
				this.metaCandiatesOntResources = metaCandiatesOntResources);
	}

	public String getButtonString() {

		return buttonString;

	}

	private void updateButtonString() {
		if ((manualMatch.getSource() != null)
				&& (manualMatch.getTarget() != null)) {

			if (OntologyHelper.isLinked(manualMatch))
				setButtonString("Revoke");
			else {
				setButtonString("Confirm");
			}
		}
	}

	public void setButtonString(String buttonString) {
		propertyChangeSupport.firePropertyChange("buttonString",
				this.buttonString, this.buttonString = buttonString);

	}

	public ManualMatch getManualMatch() {
		return this.manualMatch;
	}

	public void toggleManualMatch() {
		ReasonerService reasonerServive = ReasonerService.getInstance();
		String actionMessage = "";
		if ((manualMatch.getSource() != null)
				&& (manualMatch.getTarget() != null)) {
			ConfirmRecommendationCommand confirmRecommendationCommand = new ConfirmRecommendationCommand(
					manualMatch);
			if (OntologyHelper.isLinked(manualMatch)) {
				confirmRecommendationCommand.undo();
			} else {
				confirmRecommendationCommand.doRun();
				// if (reasonerServive.isConsistent(ontologyModel)) {
				// actionMessage = "Mapping Consistent";
				// } else {
				// confirmRecommendationCommand.undo();
				// actionMessage = "Last mapping inconsistent. Revoke!";
				// }

			}
			updateButtonString();
			updateMappingStatus();
			// setMappingStatus(getMappingStatus() + ". " + actionMessage);
		}

	}

	public Individual getReferenceIndividual() {
		return this.referenceIndividual;
	}

	public String getMappingStatus() {
		return this.mappingStatus;
	}

	public String getEObjectName(EObject eObject) {

		return getRenamerStrategy().getEObjectName(eObject);
	}

	private RenamerStrategy getRenamerStrategy() {

		return this.renamerStrategy;
	}

}
