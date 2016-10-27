/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.merge;

import static oida.model.integration.transformation.util.TransformatorHelper.REFERENCE_ONTOLOGY_URI;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import oida.model.integration.matching.diffmodel.DiffModel;
import oida.model.integration.matching.diffmodel.ReferenceMatching;
import oida.model.integration.matching.util.MatchingHelper;
import oida.model.integration.merge.util.MergeHelper;
import oida.model.integration.modelprovider.ModelProviderHelper;
import oida.model.integration.transformation.mapping.ReferenceMapping;
import oida.model.integration.transformation.modelgenerator.GeneratorHelper;
import oida.model.integration.transformation.modelgenerator.Metamodel;
import oida.model.integration.transformation.ontologygenerator.Renamer;
import oida.model.integration.transformation.util.TransformatorHelper;
import oida.ontology.io.Owl;
import oida.ontology.model.OntologyModel;
import oida.ontology.util.OntologyHelper;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.Ontology;

public class MergeModel {

	public static final String NAME_ATTRIBUTE = "hasName";
	List<EAttribute> currentEAttributeNames = new ArrayList<EAttribute>();
	private DiffModel diffModel;

	private List<ReferenceMatching> currentMatchings = new ArrayList<ReferenceMatching>();

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	private EObject sourceRootObject;
	private EObject targetRootObject;

	private OntologyModel ontologyModel;
	private Ontology targetOntology;
	private ReferenceMatching currentReferenceMatching;

	private IntegratedAttributes currentIntegratedAttributes;
	private MergeCounters mergeCounters;
	private Renamer targetRenamer;
	private Renamer sourceRenamer;

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public MergeModel(EObject sourceRootObject, Renamer sourceRenamer,
			EObject targetRootObject, Renamer targetRenamer) {
		setSourceRootObject(sourceRootObject);
		setTargetRootObject(targetRootObject);
		setOntologyModel(OntologyModel.getInstance());
		setSourceRenamer(sourceRenamer);
		setTargetRenamer(targetRenamer);

		ontologyModel
				.getOntologyModel()
				.getDocumentManager()
				.addAltEntry(
						TransformatorHelper.getReferenceOntologyURI()
								.toString(),
						OntologyHelper.getOntologyURL(REFERENCE_ONTOLOGY_URI)
								.toString());

		OntologyHelper.addMerologyFileEntry();

		ontologyModel.getOntologyModel().read(
				TransformatorHelper.getReferenceOntologyURI().toString());

		Metamodel referenceMetamodel = GeneratorHelper
				.generateReferenceMetamodel();

		List<ReferenceMapping> sourceReferenceMappings = TransformatorHelper
				.getReferenceMappings(sourceRootObject, referenceMetamodel,
						getSourceRenamer());

		// generate reference target model
		List<ReferenceMapping> targetReferenceMappings = TransformatorHelper
				.getReferenceMappings(targetRootObject, referenceMetamodel,
						getTargetRenamer());

		String targetRootObjectName = ModelProviderHelper
				.getModelElementName(targetRootObject);

		setTargetOntology(ontologyModel.getOntology(OntologyHelper
				.getOntologyURI(targetRootObjectName + ".owl")));
		Metamodel targetMetamodel = new Metamodel(targetRootObject.eClass()
				.getEPackage());
		setDiffModel(MatchingHelper.getDiffModel(sourceReferenceMappings,
				getSourceRenamer(), targetReferenceMappings,
				referenceMetamodel, targetOntology, targetMetamodel,
				getTargetRenamer()));

		mergeCounters = new MergeCounters();

		setCurrentMatchings(MergeHelper
				.getConflictingMatchings(getReferenceMatchings()));

		setCurrentIntegratedAttributes(new IntegratedAttributes());

	}

	private Renamer getSourceRenamer() {

		return this.sourceRenamer;
	}

	private void setSourceRenamer(Renamer sourceRenamer) {
		this.sourceRenamer = sourceRenamer;

	}

	private Renamer getTargetRenamer() {

		return this.targetRenamer;
	}

	private void setTargetRenamer(Renamer targetRenamer) {
		this.targetRenamer = targetRenamer;

	}

	public List<ReferenceMatching> getReferenceMatchings() {
		return diffModel.getReferenceMatchings();
	}

	public List<ReferenceMatching> getConflictingMatchings() {
		return currentMatchings;
	}

	private void setDiffModel(DiffModel newDiffModel) {
		propertyChangeSupport.firePropertyChange("diffModel", this.diffModel,
				this.diffModel = newDiffModel);
	}

	public DiffModel getDiffModel() {
		return this.diffModel;
	}

	public void setCurrentMatchings(List<ReferenceMatching> currentMatchings) {
		propertyChangeSupport
				.firePropertyChange("currentMatchings", this.currentMatchings,
						this.currentMatchings = currentMatchings);
		mergeCounters.setCurrentMatchings(currentMatchings);

		mergeCounters.updateStatus();
	}

	public void selectConflictingObject(
			ReferenceMatching conflictingReferenceMatching) {

		setCurrentReferenceMatching(conflictingReferenceMatching);

	}

	private void setCurrentReferenceMatching(
			ReferenceMatching currentReferenceMatching) {
		propertyChangeSupport.firePropertyChange("currentReferenceMatching",
				this.currentReferenceMatching,
				this.currentReferenceMatching = currentReferenceMatching);

		setCurrentEAttributeNames(currentReferenceMatching
				.getSourceReferenceMapping().getSourceEObject().eClass()
				.getEAllAttributes());

		getCurrentIntegratedAttributes().setReferenceMatching(
				currentReferenceMatching);
	}

	private void setCurrentIntegratedAttributes(
			IntegratedAttributes newIntegratedAttributes) {
		propertyChangeSupport.firePropertyChange("currentIntegratedAttributes",
				this.currentIntegratedAttributes,
				this.currentIntegratedAttributes = newIntegratedAttributes);

	}

	private void setCurrentEAttributeNames(List<EAttribute> newEAttributeNames) {
		propertyChangeSupport.firePropertyChange("currentEAttributeNames",
				this.currentEAttributeNames,
				this.currentEAttributeNames = newEAttributeNames);

	}

	public List<EAttribute> getCurrentEAttributeNames() {

		return currentEAttributeNames;

	}

	public ReferenceMatching getCurrentReferenceMatching() {

		return currentReferenceMatching;
	}

	public IntegratedAttributes getCurrentIntegratedAttributes() {
		return currentIntegratedAttributes;
	}

	public void confirmCurrentReferenceMatching() {

		if (!getCurrentReferenceMatching().isConfirmed()) {
			getCurrentReferenceMatching().setConfirmed(true);
			mergeCounters.increaseNumberOfConfirmedMatchings();
		}

		if (getCurrentReferenceMatching().isReported()) {
			getCurrentReferenceMatching().setReported(false);
			mergeCounters.decreaseNumberOfReportedMatchings();
		}

		mergeCounters.updateStatus();
	}

	public void reportCurrentReferenceMatching() {

		if (!getCurrentReferenceMatching().isReported()) {
			getCurrentReferenceMatching().setReported(true);

			mergeCounters.increaseNumberOfReportedMatchings();
		}
		if (getCurrentReferenceMatching().isConfirmed()) {
			getCurrentReferenceMatching().setConfirmed(false);
			mergeCounters.decreaseNumberOfConfirmedMatchings();
		}

		mergeCounters.updateStatus();
	}

	public MergeCounters getMergeCounters() {
		return mergeCounters;
	}

	public void resolveConflicts() {
		for (final ReferenceMatching referenceMatching : diffModel
				.getReferenceMatchings()) {
			if (referenceMatching.isConfirmed()) {
				for (EAttribute referenceEAttribute : referenceMatching
						.getTargetReferenceMapping().getReferenceEObject()
						.eClass().getEAllAttributes()) {
					if (!referenceEAttribute.getName().equals(NAME_ATTRIBUTE)) {
						EAttribute targetEAttribute = MergeHelper
								.getTargetEAttribute(referenceMatching,
										referenceEAttribute);

						if (targetEAttribute != null) {
							EDataType targetEDataType = (EDataType) targetEAttribute
									.getEAttributeType();

							Object newReferenceValue = referenceMatching
									.getSourceReferenceMapping()
									.getReferenceEObject()
									.eGet(referenceEAttribute);

							String newReferenceValueString = "";
							if (newReferenceValue != null) {
								newReferenceValueString = newReferenceValue
										.toString();
							}

							referenceMatching
									.getTargetReferenceMapping()
									.getSourceEObject()
									.eSet(targetEAttribute,
											EcoreUtil.createFromString(
													targetEDataType,
													newReferenceValueString));

						}
					}

				}
			}

		}
	}

	public EObject getSourceRootObject() {
		return sourceRootObject;
	}

	public void setSourceRootObject(EObject sourceRootObject) {
		propertyChangeSupport
				.firePropertyChange("sourceRootObject", this.sourceRootObject,
						this.sourceRootObject = sourceRootObject);
	}

	public EObject getTargetRootObject() {
		return targetRootObject;
	}

	public void setTargetRootObject(EObject targetRootObject) {
		propertyChangeSupport
				.firePropertyChange("targetRootObject", this.targetRootObject,
						this.targetRootObject = targetRootObject);
	}

	public void updateTargetOntology(Renamer renamer) {
		for (ReferenceMatching theirReferenceMatching : diffModel
				.getTheirMatchings()) {
			EObject targetEObject = theirReferenceMatching
					.getTargetReferenceMapping().getSourceEObject();
			if (theirReferenceMatching.isConfirmed()) {
				Individual referenceIndividual = theirReferenceMatching
						.getTargetReferenceMapping().getEClassMapping()
						.getReferneceIndividual();
				String individualName = renamer.getEObjectName(targetEObject);
				URI sourceIndividualURI = URI.createURI(getTargetOntology()
						.getURI() + "#" + individualName);
				OntClass targetOntClass = addOntClass(targetEObject.eClass(),
						getTargetOntology());

				Individual targetIndividual = getOntologyModel().addIndividual(
						sourceIndividualURI, targetOntClass);
				targetIndividual.addSameAs(referenceIndividual);

			} else {
				TransformatorHelper.removeContainment(targetEObject);
			}
		}
		String sourceRootEObjectName = ModelProviderHelper
				.getModelElementName(sourceRootObject);

		Ontology sourceOntology = ontologyModel.getOntology(OntologyHelper
				.getOntologyURI(sourceRootEObjectName + ".owl"));

		getOntologyModel().removeOntology(sourceOntology);

		Owl.saveToOwl(OntologyHelper.getOntologyURL(ModelProviderHelper
				.getModelElementName(getTargetRootObject()) + "_mapped.owl"),
				getTargetOntology());

	}

	private OntClass addOntClass(EClass eClass, Ontology targetOntology2) {

		URI newClassURI = OntologyHelper.generateURI(getTargetOntology(),
				eClass.getName());
		OntClass newOntClass = ontologyModel.getOntologyClass(newClassURI);
		if (newOntClass == null) {
			newOntClass = ontologyModel.addOntClass(newClassURI);

			URI classURI = OntologyHelper.generateURI(getTargetOntology(),
					eClass.getName());
			if (!eClass.getESuperTypes().isEmpty()) {
				for (EClass eSuperType : eClass.getESuperTypes()) {
					URI superClassURI = OntologyHelper.generateURI(
							getTargetOntology(), eSuperType.getName());
					if (!ontologyModel.containsClass(superClassURI)) {
						ontologyModel.addOntClass(superClassURI);
					}
					OntClass ontologySuperClass = ontologyModel
							.getOntologyClass(superClassURI);
					if (!ontologyModel.getOntologyClass(classURI)
							.hasSuperClass(ontologySuperClass)) {
						ontologyModel.getOntologyClass(classURI).addSuperClass(
								ontologySuperClass);
					}

				}

			}
		}
		return newOntClass;

	}

	public Ontology getTargetOntology() {
		return targetOntology;
	}

	private void setTargetOntology(Ontology targetOntology) {
		propertyChangeSupport.firePropertyChange("targetOntology",
				this.targetOntology, this.targetOntology = targetOntology);

	}

	public OntologyModel getOntologyModel() {
		return ontologyModel;
	}

	private void setOntologyModel(OntologyModel ontologyModel) {
		propertyChangeSupport.firePropertyChange("ontologyModel",
				this.ontologyModel, this.ontologyModel = ontologyModel);
	}

	public void resetOntologyModel() {
		getOntologyModel().reset();
	}

}
