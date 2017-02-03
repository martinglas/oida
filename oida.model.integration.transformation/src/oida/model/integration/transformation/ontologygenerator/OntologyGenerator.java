/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.transformation.ontologygenerator;

import java.util.List;

import org.apache.jena.datatypes.TypeMapper;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import de.symo.oida.changehandler.modelhelper.Extractor;
import oida.model.integration.transformation.typemapping.BaseUnitType;
import oida.model.integration.transformation.typemapping.ProductUnitType;
import oida.model.integration.transformation.typemapping.TransformedUnitType;
import oida.model.integration.transformation.typemapping.UnitType;
import oida.model.integration.transformation.util.TransformatorHelper;
import oida.ontology.OntologyClass;
import oida.ontology.manager.IOntologyManager;

/**
 * This class generates an OWL ontology form a given EMF Model. It generates
 * only metamodel elements which have instances.
 * 
 * @author martin.glas
 * 
 */
public class OntologyGenerator {
	private IOntologyManager ontologyMgr;
	
//	private OntologyModel ontologyModel;
//	private Ontology targetOntology;
	private Extractor extractor;
	private Renamer renamer;

	/**
	 * The constructor takes as parameters the desired URI of the target
	 * ontology, the root object containing the of the Model, and the renamer
	 * strategy which maps model object and ontology concept names.
	 * 
	 * @param targetOntologyURI
	 *            The URI of the target ontology
	 * @param rootEObject
	 *            the root model object containing the model
	 * @param renamerStrategy
	 *            the renamer strategy mapping model to ontology names
	 */
	public OntologyGenerator(IOntologyManager ontologyMgr, EObject rootEObject, RenamerStrategy renamerStrategy) {
		this.ontologyMgr = ontologyMgr;
		
//		setRenamer(new Renamer(rootEObject));
//		getRenamer().setRenamerStrategy(renamerStrategy);
//		getRenamer().isUnabiguous();

		extractor = new Extractor(rootEObject);

//		this.targetOntology = ontologyModel.addOntology(targetOntologyURI);
//		OntologyHelper.importMerology(targetOntology);

//		TypeMapper.getInstance().registerDatatype(UnitType.theUnitType);
//		TypeMapper.getInstance().registerDatatype(BaseUnitType.theBaseUnitType);
//		TypeMapper.getInstance().registerDatatype(ProductUnitType.theProductUnitType);
//		TypeMapper.getInstance().registerDatatype(TransformedUnitType.theTransformedUnitType);
	}

	private void setRenamer(Renamer renamer) {
		this.renamer = renamer;
	}

	/**
	 * This method returns the current renamer strategy of the
	 * OntologyGenerator.
	 * 
	 * @return the current renamer strategy
	 */
	public Renamer getRenamer() {
		return this.renamer;

	}


	/**
	 * This method scans a given model for references in its metamodel and
	 * creates them accordingly.
	 * 
	 */
	public void generateObjectProperties() {
		// Get partOf_directly ObjectProperty
		ObjectProperty containmentProperty = OntologyHelper
				.getPartOfProperty(ontologyModel);

		for (EReference eReference : extractor.getAllEReferences()) {
			EClass sourceClass = eReference.getEContainingClass();

			URI domainClassURI = OntologyHelper.generateURI(targetOntology,
					sourceClass.getName());

			URI objectPropertyURI = OntologyHelper.generateURI(targetOntology,
					renamer.getEStructuralFeatureName(eReference));

			if ((!ontologyModel.containsProperty(objectPropertyURI))) {
				// Ensure that domain class exist
				if (!ontologyModel.isClassExisting(domainClassURI)) {
					ontologyModel.addOntClass(domainClassURI);

				}

				EClassifier targetClassifier = eReference.getEType();
				URI rangeClassURI = OntologyHelper.generateURI(targetOntology,
						targetClassifier.getName());

				// Ensure that range class exists
				if (!ontologyModel.isClassExisting(rangeClassURI)) {
					ontologyModel.addOntClass(rangeClassURI);

				}
				ObjectProperty newObjectProperty = ontologyModel
						.addObjectProperty(domainClassURI, objectPropertyURI,
								rangeClassURI);
				// Handle containment reference
				if (eReference.isContainment()) {
					newObjectProperty.addSuperProperty(containmentProperty);
				}

			}

		}

	}

	/**
	 * This method creates the DatatypeProperties of a given model.
	 * 
	 */
	public void generateDatatypeProperties() {

		for (EAttribute eAttribute : extractor.getAllEAttributes()) {
			EClass sourceClass = eAttribute.getEContainingClass();

			URI domainClassURI = OntologyHelper.generateURI(targetOntology,
					sourceClass.getName());

			URI datatypePropertyURI = OntologyHelper.generateURI(targetOntology,
					renamer.getEStructuralFeatureName(eAttribute));

			if ((!ontologyModel.containsProperty(datatypePropertyURI))) {

				// Ensure that the domain class exists in the ontology
				if (!ontologyModel.isClassExisting(domainClassURI)) {
					ontologyModel.addOntClass(domainClassURI);
				}

				Resource datatypeURI = TransformatorHelper
						.getOntologyDatatype(eAttribute.getEType());
				ontologyModel.addDatatypeProperty(domainClassURI,
						datatypePropertyURI, datatypeURI);

			}

		}

	}

	/**
	 * This method generates properties representing structural features, such
	 * as attributes and references.
	 */
	public void generateProperties() {
		generateObjectProperties();
		generateDatatypeProperties();
	}

}
