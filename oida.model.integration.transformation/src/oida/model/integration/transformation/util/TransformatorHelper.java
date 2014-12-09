/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.transformation.util;



import static oida.ontology.util.OntologyHelper.DEFAULTONTOLOGY_URI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import oida.model.integration.modelprovider.ModelProviderHelper;
import oida.model.integration.transformation.mapping.AttributeMapping;
import oida.model.integration.transformation.mapping.EClassMapping;
import oida.model.integration.transformation.mapping.MappingFactory;
import oida.model.integration.transformation.mapping.ReferenceMapping;
import oida.model.integration.transformation.modelgenerator.GeneratorHelper;
import oida.model.integration.transformation.modelgenerator.Metamodel;
import oida.model.integration.transformation.ontologygenerator.ContainerContext;
import oida.model.integration.transformation.ontologygenerator.Renamer;
import oida.model.integration.transformation.ontologygenerator.RenamerStrategy;
import oida.ontology.io.Owl;
import oida.ontology.model.OntologyModel;
import oida.ontology.util.OntologyHelper;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.datatypes.TypeMapper;
import com.hp.hpl.jena.graph.impl.AdhocDatatype;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.rdf.model.Resource;



public class TransformatorHelper {

	public static final String REFERENCE_ONTOLOGY_URI = "reference.owl";

	public static EClassMapping getReferenceEClassMapping(EObject eObject,
			Renamer renamer, Ontology sourceOntology,
			Metamodel referenceMetamodel) {
		// Get Individual from Ontology Model

		Individual referenceIndividual = getReferenceIndividual(eObject,
				renamer, sourceOntology);
		// If mapped get Mapped Individual
		if (referenceIndividual != null) {

			// Get OntClass of mapped Individual
			OntClass referenceClass = getTargetOntClass(referenceIndividual,
					getReferenceOntology());

			if (referenceClass == null) {
				System.out.println("EClass was null");
			}

			EClass referenceEClass = (EClass) referenceMetamodel
					.getBasePackage().getEClassifier(
							referenceClass.getLocalName());
			EClassMapping newEClassMapping = MappingFactory.eINSTANCE
					.createEClassMapping();
			newEClassMapping.setReferenceEClass(referenceEClass);
			newEClassMapping.setReferneceIndividual(referenceIndividual);
			return newEClassMapping;

		}
		return null;
	}

	public static ReferenceMapping getTargetReferenceMapping(
			EObject referenceEObject, Metamodel referenceMetamodel,
			Ontology targetOntology, Metamodel targetMetamodel,
			Renamer targetRenamer) {
		// Get Individual from Ontology Model

		Individual referenceIndividual = getReferenceIndividual(referenceEObject);
		// If mapped get Mapped Individual
		if (referenceIndividual != null) {

			// Get OntClass of mapped Individual
			OntClass referenceClass = getTargetOntClass(referenceIndividual,
					getReferenceOntology());

			EClass referenceEClass = (EClass) referenceMetamodel
					.getBasePackage().getEClassifier(
							referenceClass.getLocalName());

			EClassMapping newEClassMapping = MappingFactory.eINSTANCE
					.createEClassMapping();
			newEClassMapping.setReferenceEClass(referenceEClass);
			newEClassMapping.setReferneceIndividual(referenceIndividual);

			// Get OntClass of mapped Individual
			OntClass targetOntClass = getTargetOntClass(referenceIndividual,
					targetOntology);

			EClass targetEClass = (EClass) targetMetamodel.getBasePackage()
					.getEClassifier(targetOntClass.getLocalName());

			EObject targetEObject = EcoreUtil.create(targetEClass);
			// set attributes

			for (EAttribute targetEAttribute : targetEObject.eClass()
					.getEAllAttributes()) {
				EAttribute referenceEAttribute = getReferenceEAttribute(
						targetEAttribute,
						newEClassMapping.getReferenceEClass(), targetOntology,
						targetRenamer);

				if (referenceEAttribute != null) {
					// FIXME In the future there should be a warning if a
					// certain attribute
					// is not set.
					AttributeMapping newAttributeMapping = MappingFactory.eINSTANCE
							.createAttributeMapping();
					newAttributeMapping.setSourceEAttribute(targetEAttribute);
					newAttributeMapping
							.setReferenceEAttribute(referenceEAttribute);

					newEClassMapping.getAttributeMappings().add(
							newAttributeMapping);

					if (referenceEObject.eGet(referenceEAttribute) != null) {
						String targetAttributeValue = "";
						if (referenceEAttribute.getName().equals("hasName")) {
							targetAttributeValue = "";
						} else {
							targetAttributeValue = referenceEObject.eGet(
									referenceEAttribute).toString();
						}

						EClassifier targetEClassifier = targetEAttribute
								.getEType();

						targetEObject.eSet(targetEAttribute, EcoreUtil
								.createFromString(
										(EDataType) targetEClassifier,
										targetAttributeValue));
					} else {
						System.out.println("The source Attribute "
								+ referenceEAttribute.getName()
								+ " was not set");
					}

				}

			}

			ReferenceMapping newReferenceMapping = MappingFactory.eINSTANCE
					.createReferenceMapping();
			newReferenceMapping.setEClassMapping(newEClassMapping);
			newReferenceMapping.setSourceEObject(targetEObject);
			newReferenceMapping.setReferenceEObject(referenceEObject);

			return newReferenceMapping;

		}
		return null;
	}

	private static Individual getReferenceIndividual(EObject eObject,
			Renamer renamer, Ontology sourceOntology) {
		OntologyModel ontologyModel = OntologyModel.getInstance();
		String objectID = renamer.getEObjectName(eObject);

		URI individualURI = URI.createURI(sourceOntology.getURI() + "#"
				+ objectID);
		Individual individual = ontologyModel.getIndividual(individualURI);

		if (individual == null) {
			System.out
					.println("Individual not found source model and source ontology are inconsitent at "
							+ objectID);
		}

		Individual referenceIndividual = getTargetIndividual(individual,
				getReferenceOntology());
		return referenceIndividual;
	}

	private static Individual getReferenceIndividual(EObject referenceEObject) {
		OntologyModel ontologyModel = OntologyModel.getInstance();
		String objectID = ModelProviderHelper
				.getModelElementName(referenceEObject);

		URI individualURI = URI.createURI(getReferenceOntology().getURI() + "#"
				+ objectID);
		Individual referenceIndividual = ontologyModel
				.getIndividual(individualURI);

		if (referenceIndividual == null) {
			System.out.println("Reference EObject " + objectID
					+ " not found in refnerence ontology");
		}

		return referenceIndividual;
	}

	private static OntClass getTargetOntClass(Individual individual,
			Ontology targetOntology) {

		if (OntologyHelper.isInOntology(targetOntology, individual)) {
			return individual.getOntClass(true);
		} else {
			OntologyModel ontologyModel = OntologyModel.getInstance();

			List<OntClass> targetOntClasses = ontologyModel
					.getOntClasses(targetOntology);

			OntClass superOntClass = individual.getOntClass();
			List<OntClass> referenceOntClasses = new ArrayList<OntClass>();
			referenceOntClasses.add(superOntClass);

			OntResource thingOntResource = ontologyModel.getOntologyModel()
					.getOntResource(
							"http://www.w3.org/2000/01/rdf-schema#Resource");

			while (!superOntClass.getSuperClass().equals(thingOntResource)) {
				referenceOntClasses.add(superOntClass.getSuperClass());
				superOntClass = superOntClass.getSuperClass();
			}

			for (OntClass referenceOntClass : referenceOntClasses) {

				for (OntClass targetOntClass : targetOntClasses) {
					if (targetOntClass.hasEquivalentClass(referenceOntClass)) {
						return targetOntClass;
					}

				}
			}
		}

		return null;

	}

	private static boolean isMappedToReference(OntResource ontResource) {
		if (ontResource.canAs(Individual.class)) {
			Individual individual = ontResource.asIndividual();
			List<Individual> sameIndividuals = OntologyHelper
					.getIndividuals(individual.listSameAs().toList());
			for (Individual sameIndividual : sameIndividuals) {

				if (OntologyHelper.isInOntology(getReferenceOntology(),
						sameIndividual)) {
					return true;
				}

			}
		}
		return false;
	}

	private static Individual getTargetIndividual(Individual sourceIndividual,
			Ontology targetOntology) {

		List<Individual> equivalentIndividuals = OntologyHelper
				.getIndividuals(sourceIndividual.listSameAs().toList());
		for (Individual equivalentIndividual : equivalentIndividuals) {
			if (OntologyHelper.isInOntology(targetOntology,
					equivalentIndividual)) {
				return equivalentIndividual;
			}
		}
		return null;
	}

	private static Ontology getReferenceOntology() {
		OntologyModel ontologyModel = OntologyModel.getInstance();
		return ontologyModel.getOntology(getReferenceOntologyURI());

	}

	public static URI getReferenceOntologyURI() {

		return URI.createURI(DEFAULTONTOLOGY_URI + REFERENCE_ONTOLOGY_URI);
	}

	public static EAttribute getReferenceEAttribute(
			EAttribute sourceEAttribute, EClass referenceEClass,
			Ontology sourceOntology, Renamer renamer) {
		// Get Individual from Ontology Model
		OntologyModel ontologyModel = OntologyModel.getInstance();
		String attributeID = renamer
				.getEStructuralFeatureName(sourceEAttribute);

		DatatypeProperty datatypeProperty = ontologyModel
				.getDatatypeProperty(URI.createURI(sourceOntology.getURI()
						+ "#" + attributeID));

		DatatypeProperty referenceDatatypeProperty = getTargetDatatypeProperty(
				datatypeProperty, getReferenceOntology());

		if (referenceDatatypeProperty != null) {
			EAttribute referenceEAttribute = (EAttribute) getEStructuralFeatureDirectly(
					referenceEClass, referenceDatatypeProperty);
			if (referenceEAttribute != null) {
				return referenceEAttribute;
			}
			referenceEAttribute = (EAttribute) getImplicitelyCastedEStructuralFeature(
					referenceEClass, referenceDatatypeProperty);

			if (referenceEAttribute != null) {
				System.out.println("The attribute "
						+ sourceEAttribute.getName()
						+ "was casted to a bigger Datatype in "
						+ referenceEAttribute.getName() + ". No data was lost");

				return referenceEAttribute;
			}

			referenceEAttribute = (EAttribute) getExplicitelyCastedEStructuralFeature(
					referenceEClass, referenceDatatypeProperty);

			if (referenceEAttribute != null) {
				System.out.println("The attribute "
						+ sourceEAttribute.getName()
						+ "was casted to a smaller Datatype in "
						+ referenceEAttribute.getName() + ". Data can be lost");

				return referenceEAttribute;
			}

		}

		return null;
	}

	private static EStructuralFeature getExplicitelyCastedEStructuralFeature(
			EClass eClass, OntProperty referenceDatatypeProperty) {
		List<OntProperty> subOntProperties = OntologyHelper
				.getOntProperties(referenceDatatypeProperty.listSubProperties(
						true).toList());

		for (OntProperty subOntProperty : subOntProperties) {
			EStructuralFeature eStructuralFeature = getEStructuralFeatureDirectly(
					eClass, subOntProperty);
			if (eStructuralFeature != null) {
				return eStructuralFeature;
			} else {
				eStructuralFeature = getExplicitelyCastedEStructuralFeature(
						eClass, subOntProperty);
				if (eStructuralFeature != null) {
					return eStructuralFeature;
				}
			}
		}
		return null;
	}

	private static EStructuralFeature getImplicitelyCastedEStructuralFeature(
			EClass eClass, OntProperty referenceDatatypeProperty) {

		List<OntProperty> superOntProperties = OntologyHelper
				.getOntProperties(referenceDatatypeProperty
						.listSuperProperties(false).toList());

		for (OntProperty superOntProperty : superOntProperties) {
			EStructuralFeature eStructuralFeature = getEStructuralFeatureDirectly(
					eClass, superOntProperty);
			if (eStructuralFeature != null) {
				return eStructuralFeature;
			} else {
				if (eClass == null) {
					System.out.println("EClass was null");
				}
				eStructuralFeature = getImplicitelyCastedEStructuralFeature(
						eClass, superOntProperty);
				if (eStructuralFeature != null) {
					return eStructuralFeature;
				}
			}
		}
		return null;
	}

	private static EStructuralFeature getEStructuralFeatureDirectly(
			EClass eClass, OntProperty referenceDatatypeProperty) {
		return eClass.getEStructuralFeature(referenceDatatypeProperty
				.getLocalName());

	}

	private static DatatypeProperty getTargetDatatypeProperty(
			DatatypeProperty sourceDatatypeProperty, Ontology targetOntology) {
		if (sourceDatatypeProperty == null) {
			System.out.println("Null");
		}
		List<DatatypeProperty> equivalentDatatypeProperties = OntologyHelper
				.getDatatypeProperties(sourceDatatypeProperty
						.listEquivalentProperties().toList());

		for (DatatypeProperty equivalentDatatypeProperty : equivalentDatatypeProperties) {
			if (OntologyHelper.isInOntology(targetOntology,
					equivalentDatatypeProperty)) {
				return equivalentDatatypeProperty;
			}
		}
		return null;
	}

	public static ReferenceMapping getReferenceMapping(EObject sourceEObject,
			Renamer renamer, Ontology sourceOntology,
			Metamodel referenceMetamodel) {
		// GetEClass of the Object
		EClassMapping referenceEClassMapping = getReferenceEClassMapping(
				sourceEObject, renamer, sourceOntology, referenceMetamodel);

		if (referenceEClassMapping != null) {
			EObject refernceEObject = EcoreUtil.create(referenceEClassMapping
					.getReferenceEClass());
			for (EAttribute sourceEAttribute : sourceEObject.eClass()
					.getEAllAttributes()) {
				EAttribute referenceEAttribute = getReferenceEAttribute(
						sourceEAttribute,
						referenceEClassMapping.getReferenceEClass(),
						sourceOntology, renamer);

				if (referenceEAttribute != null) {
					// FIXME In the future there should be a warning if a
					// certain attribute
					// is not set.
					AttributeMapping newAttributeMapping = MappingFactory.eINSTANCE
							.createAttributeMapping();
					newAttributeMapping.setSourceEAttribute(sourceEAttribute);
					newAttributeMapping
							.setReferenceEAttribute(referenceEAttribute);

					referenceEClassMapping.getAttributeMappings().add(
							newAttributeMapping);

					if (sourceEObject.eGet(sourceEAttribute) != null) {
						String sourceAttributeValue = "";
						if (referenceEAttribute.getName().equals("hasName")) {
							sourceAttributeValue = getReferenceIndividual(
									sourceEObject, renamer, sourceOntology)
									.getLocalName();
						} else {
							sourceAttributeValue = sourceEObject.eGet(
									sourceEAttribute).toString();
						}

						EClassifier referenceEClassifier = referenceEAttribute
								.getEType();

						try {
							if (sourceEAttribute.getEType()
									.getInstanceClassName().equals("double")) {
								if (sourceAttributeValue.endsWith(".0")) {
									sourceAttributeValue = sourceAttributeValue
											.replace(".0", "");
								}
							}
							Object value = EcoreUtil.createFromString(
									(EDataType) referenceEClassifier,
									sourceAttributeValue);
							refernceEObject.eSet(referenceEAttribute, value);
						} catch (NumberFormatException e) {
							System.out.println(e.getMessage());
							System.out.println("The source Attribute "
									+ sourceEAttribute.getName()
									+ " was not set");
						}

					} else {
						System.out.println("The source Attribute "
								+ sourceEAttribute.getName() + " was not set");
					}

				}

			}
			ReferenceMapping newReferenceMapping = MappingFactory.eINSTANCE
					.createReferenceMapping();
			newReferenceMapping.setSourceEObject(sourceEObject);
			newReferenceMapping.setReferenceEObject(refernceEObject);
			newReferenceMapping.setEClassMapping(referenceEClassMapping);
			return newReferenceMapping;
			// TODO get the container

		}

		return null;
	}

	public static List<EObject> getComprisedEObjects(EObject rootModelElement) {
		List<EObject> comprisedEObjectsList = new ArrayList<EObject>();
		comprisedEObjectsList.add(rootModelElement);
		Iterator<EObject> childrenEObjectsIterator = rootModelElement
				.eAllContents();
		while (childrenEObjectsIterator.hasNext()) {
			comprisedEObjectsList.add(childrenEObjectsIterator.next());
		}
		return comprisedEObjectsList;
	}

	public static EObject getChildByName(EObject parent, String eObjectName) {
		TreeIterator<EObject> iterator = parent.eAllContents();
		while (iterator.hasNext()) {
			EObject ob = iterator.next();
			if (ModelProviderHelper.getModelElementName(ob).equals(eObjectName)) {
				return ob;
			}
		}
		return null;
	}

	public static List<ReferenceMapping> getReferenceMappings(
			EObject rootEObject, Renamer renamer, Ontology sourceOntology,
			Metamodel referenceMetamodel) {
		List<ReferenceMapping> referenceMappings = new ArrayList<ReferenceMapping>();
		List<EObject> sourceEObjects = TransformatorHelper
				.getComprisedEObjects(rootEObject);
		for (EObject sourceEObject : sourceEObjects) {
			ReferenceMapping referenceMapping = getReferenceMapping(
					sourceEObject, renamer, sourceOntology, referenceMetamodel);
			if (referenceMapping != null) {
				referenceMappings.add(referenceMapping);
			}
		}

		return referenceMappings;
	}

	public static List<ReferenceMapping> getReferenceMappings(
			String testModelFileName, String testSourceOntologyMappedFileName,
			String testSourceOntologyLocalName,
			String testReferenceModelFileName) {

		OntologyModel ontologyModel = OntologyModel.getInstance();

		EObject rootEObject = OntologyHelper.loadEObjectsFromFile(
				testModelFileName).get(0);

		// load test ontology

		OntologyHelper.addMerologyFileEntry();

		Owl.loadFromOwl(
				OntologyHelper.getOntologyURI(testSourceOntologyLocalName),
				OntologyHelper.getOntologyURL(testSourceOntologyMappedFileName));
		Ontology sourceOntology = ontologyModel.getOntology(OntologyHelper
				.getOntologyURI(testSourceOntologyLocalName));

		Metamodel referenceMetamodel = GeneratorHelper
				.generateReferenceMetamodel();

		Renamer renamer = new Renamer(rootEObject);

		RenamerStrategy renamerStrategy = new ContainerContext(rootEObject);
		renamer.setRenamerStrategy(renamerStrategy);

		// generateExpectedEClass();
		return TransformatorHelper.getReferenceMappings(rootEObject, renamer,
				sourceOntology, referenceMetamodel);
	}

	public static List<ReferenceMapping> getReferenceMappings(
			EObject rootEObject, Metamodel referenceMetamodel, Renamer renamer) {
		OntologyModel ontologyModel = OntologyModel.getInstance();

		// load test ontology

		String rootObjectName = ModelProviderHelper
				.getModelElementName(rootEObject);

		Owl.loadFromOwl(OntologyHelper.getOntologyURI(rootObjectName + ".owl"),
				OntologyHelper.getOntologyURL(rootObjectName + "-ev3.owl"));
		Ontology sourceOntology = ontologyModel.getOntology(OntologyHelper
				.getOntologyURI(rootObjectName + ".owl"));

		// Renamer renamer = new Renamer(rootEObject);
		//
		// RenamerStrategy renamerStrategy = new ContainerContext(rootEObject);
		// renamer.setRenamerStrategy(renamerStrategy);

		// generateExpectedEClass();
		return TransformatorHelper.getReferenceMappings(rootEObject, renamer,
				sourceOntology, referenceMetamodel);
	}

	@SuppressWarnings("unchecked")
	public static void removeContainment(EObject sourceEObject) {

		EObject containerEObject = sourceEObject.eContainer();
		if (containerEObject != null) {

			for (EReference containmentEReference : containerEObject.eClass()
					.getEAllContainments()) {
				EClassifier referenceEClassifier = containmentEReference
						.getEType();

				if (referenceEClassifier.isInstance(sourceEObject)) {
					if (containmentEReference.isMany()) {
						((EList<EObject>) containerEObject
								.eGet(containmentEReference))
								.remove(sourceEObject);

					} else {
						containerEObject.eUnset(containmentEReference);

						break;
					}

				}
			}
		}
	}

	/**
	 * This method returns an onology datatype given a class.
	 * 
	 * @param datatype
	 *            the Datatype which has to be translated into XML
	 * @return the XML datatype as ontology resource
	 */
	public static Resource getOntologyDatatype(EClassifier datatype) {
		RDFDatatype rdfDatatype = TypeMapper.getInstance().getTypeByClass(
				datatype.getInstanceClass());
		if (rdfDatatype == null) {
			Class<?> c = datatype.getInstanceClass();
			// log.warn( "inventing a datatype for " + c );
			RDFDatatype dt = new AdhocDatatype(c);
			TypeMapper.getInstance().registerDatatype(dt);
			rdfDatatype = dt;
		}
		String datatypeURI = rdfDatatype.getURI().toString();
		return OntologyModel.getInstance().getOntologyModel()
				.getResource(datatypeURI);
	}

	public static String getEStructuralFeatureName(
			EStructuralFeature eStructuralFeature) {

		return eStructuralFeature.getEContainingClass().getName() + "."
				+ eStructuralFeature.getName();
	}

}
