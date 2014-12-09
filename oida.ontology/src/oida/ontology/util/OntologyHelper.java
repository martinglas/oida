/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import oida.ontology.matcher.MatchRecommendation;
import oida.ontology.model.AnonymousOntClassFilter;
import oida.ontology.model.OntologyFilter;
import oida.ontology.model.OntologyModel;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * This is a helper class for the ontology plug-in.
 * 
 * @author Martin Glas
 * 
 */
public final class OntologyHelper {

	/**
	 * This is the file name of the merology ontology.
	 */
	public static String MEROLOGY_FILENAME = "merology.owl";
	/**
	 * This is the file name of the standard reference ontology.
	 */
	public static String REFERENCE_ONTOLOGY_FILENAME = "reference.owl";
	/**
	 * /** This is the path relative to the plug-in path of the merology
	 * ontology.
	 */
	public static String ONTOLOGY_PATH = "ontologies/";
	/**
	 * /** This is the path to models relative to the plug-in path.
	 */
	public static String MODEL_PATH = "testmodels/";

	/**
	 * This is the path of the ontology plug-in.
	 */
	public static String PLUGINPATH = "platform:/plugin/net.bhl.cdt.ontology/";
	/**
	 * This is the default URL of the BHL ontologies.
	 */
	public static String DEFAULTONTOLOGY_URI = "http://www.bauhaus-luftfahrt.net/ontologies/";

	/**
	 * This is the name of the object property in the merology ontology which
	 * correlates to a containment reference.
	 */
	public static String CONTAINMENT_PROPERTY_NAME = "hasPart_directly";

	/**
	 * This is the id of the ontology plug-in as string.
	 */
	public static String ONTOLOGYPLUGIN_ID = "net.bhl.cdt.ontology";

	private OntologyHelper() {

	}

	public static void addMerologyFileEntry() {
		OntologyModel ontologyModel = OntologyModel.getInstance();
		ontologyModel
				.getOntologyModel()
				.getDocumentManager()
				.addAltEntry(
						DEFAULTONTOLOGY_URI + MEROLOGY_FILENAME,
						OntologyHelper.getPluginFilePath() + ONTOLOGY_PATH
								+ MEROLOGY_FILENAME);

	}

	/**
	 * This method loads the merology ontology to the ontology model.
	 * 
	 * @param baseOntology
	 */
	public static void importMerology(Ontology baseOntology) {
		OntologyModel ontologyModel = OntologyModel.getInstance();
		ontologyModel
				.getOntologyModel()
				.getDocumentManager()
				.addAltEntry(
						DEFAULTONTOLOGY_URI + MEROLOGY_FILENAME,
						OntologyHelper.getPluginFilePath() + ONTOLOGY_PATH
								+ MEROLOGY_FILENAME);
		baseOntology.addImport(ontologyModel.getOntologyModel().createResource(
				DEFAULTONTOLOGY_URI + MEROLOGY_FILENAME));

		ontologyModel.getOntologyModel().loadImports();

		// URI merologyURI = URI.createURI(DEFAULTONTOLOGY_URI +
		// MEROLOGYFILENAME);
		// URL merologyURL;
		// String urlString = getPluginFilePath() + MEROLOGYPATH
		// + MEROLOGYFILENAME;
		//
		// try {
		// merologyURL = new URL(urlString);
		// Owl.loadFromOwl(merologyURI, merologyURL);
		// } catch (MalformedURLException e) {
		// System.out
		// .println("Could not generate URL from string" + urlString);
		// e.printStackTrace();
		// }
		//
		// for (Individual individual : ontologyModel.getIndividuals()) {
		// System.out.println("Individual: " + individual);
		// }
		//
		// for (OntClass ontClass : ontologyModel.listClasses()) {
		// System.out.println("Class: " + ontClass);
		// }
		//
		// for (URI ontologyURI : ontologyModel.getImportedOntologyURIs()) {
		// System.out.println("Imported: " + ontologyURI);
		// }
		//
		// for (ObjectProperty objectProperty : ontologyModel
		// .getObjectProperties()) {
		// System.out.println("Imported: " + objectProperty);
		// }

	}

	/**
	 * This method returns the plug-in file path as string.
	 * 
	 * @return the plug-in file path
	 */
	public static String getPluginFilePath() {
		return getPluginFilePath(ONTOLOGYPLUGIN_ID);
	}

	/**
	 * This method links equivalent resources by creating an equivalence object
	 * property between a source and a target resource.
	 * 
	 * @param sourceResource
	 *            the source resource
	 * @param targetResource
	 *            the target resource
	 */
	public static void linkEquivalentResources(OntResource sourceResource,
			OntResource targetResource) {
		if (sourceResource.isClass()) {
			OntClass sourceOntClass = sourceResource.asClass();
			if (targetResource.isClass()) {
				OntClass targetOntClass = targetResource.asClass();
				sourceOntClass.addEquivalentClass(targetOntClass);
			}
		}

		else if (sourceResource.isProperty()) {
			OntProperty sourceOntProperty = sourceResource.asProperty();
			if (targetResource.isProperty()) {
				OntProperty targetOntProperty = targetResource.asProperty();
				sourceOntProperty.addEquivalentProperty(targetOntProperty);
			}
		}

		else if (sourceResource.isIndividual()) {
			Individual sourceIndividual = sourceResource.asIndividual();
			if (targetResource.isIndividual()) {
				Individual targetIndividual = targetResource.asIndividual();
				sourceIndividual.addSameAs(targetIndividual);
			} else if (targetResource.isClass()) {
				OntClass referenceClass = targetResource.asClass();
				sourceIndividual.addOntClass(referenceClass);
			}
		}

	}

	/**
	 * This method unlinks resources by removing an equivalence object property
	 * between a source and a target resource.
	 * 
	 * @param sourceResource
	 *            the source resource
	 * @param targetResource
	 *            the target resource
	 */
	public static void unlinkEquivalentResources(OntResource sourceResource,
			OntResource targetResource) {
		if (sourceResource.isClass()) {
			OntClass sourceOntClass = sourceResource.asClass();
			if (targetResource.isClass()) {
				OntClass targetOntClass = targetResource.asClass();
				sourceOntClass.removeEquivalentClass(targetOntClass);
			}
		}

		else if (sourceResource.isProperty()) {
			OntProperty sourceOntProperty = sourceResource.asProperty();
			if (targetResource.isProperty()) {
				OntProperty targetOntProperty = targetResource.asProperty();
				sourceOntProperty.removeEquivalentProperty(targetOntProperty);
			}
		}

		else if (sourceResource.isIndividual()) {
			Individual sourceIndividual = sourceResource.asIndividual();
			if (targetResource.isIndividual()) {
				Individual targetIndividual = targetResource.asIndividual();
				sourceIndividual.removeSameAs(targetIndividual);
			}
		}

	}

	/**
	 * This method declares two resources disjoint by creating an
	 * disjoint/differentFrom object property between a source and a target
	 * resource.
	 * 
	 * @param sourceResource
	 *            the source resource
	 * @param targetResource
	 *            the target resource
	 */
	public static void declareDisjoint(OntResource sourceResource,
			OntResource targetResource) {
		if (sourceResource.isClass()) {
			OntClass sourceOntClass = sourceResource.asClass();
			if (targetResource.isClass()) {
				OntClass targetOntClass = targetResource.asClass();
				sourceOntClass.addDisjointWith(targetOntClass);
			}
		}

		else if (sourceResource.isProperty()) {
			OntProperty sourceOntProperty = sourceResource.asProperty();
			if (targetResource.isProperty()) {
				OntProperty targetOntProperty = targetResource.asProperty();
				sourceOntProperty.addDifferentFrom(targetOntProperty);
			}
		}

		else if (sourceResource.isIndividual()) {
			Individual sourceIndividual = sourceResource.asIndividual();
			if (targetResource.isIndividual()) {
				Individual targetIndividual = targetResource.asIndividual();
				sourceIndividual.addDifferentFrom(targetIndividual);
			}
		}

	}

	public static List<DatatypeProperty> getDatatypeProperties(
			List<? extends OntProperty> propertyList) {
		List<DatatypeProperty> datatypeProperties = new ArrayList<DatatypeProperty>();
		for (OntProperty ontProperty : propertyList) {
			if (ontProperty.isDatatypeProperty()) {
				datatypeProperties.add(ontProperty.asDatatypeProperty());
			}

		}
		return datatypeProperties;
	}

	public static List<ObjectProperty> getObjectProperties(
			List<? extends OntProperty> propertyList) {
		List<ObjectProperty> objectProperties = new ArrayList<ObjectProperty>();
		for (OntProperty ontProperty : propertyList) {
			if (ontProperty.isObjectProperty()) {
				objectProperties.add(ontProperty.asObjectProperty());
			}

		}
		return objectProperties;
	}

	public static List<OntProperty> getOntProperties(
			List<? extends OntProperty> propertyList) {
		List<OntProperty> ontProperties = new ArrayList<OntProperty>();
		for (OntProperty ontProperty : propertyList) {
			if (ontProperty.isProperty()) {
				ontProperties.add(ontProperty.asProperty());
			}

		}
		return ontProperties;
	}

	public static boolean isInOntology(Ontology targetOntology,
			OntResource ontologyResource) {

		if (targetOntology != null) {

			if (!ontologyResource.isAnon()) {
				if (ontologyResource.getNameSpace() != null) {
					if (ontologyResource.getNameSpace().equals(
							targetOntology.getURI() + "#")) {
						return true;
					}
				} else {
					System.out.println("Namespace of Resource is null: "
							+ ontologyResource.getClass().toString() + ": "
							+ ontologyResource.getLocalName() + " ontology:"
							+ targetOntology);
				}
			} else {
				System.out.println("Resource is anonymous ");
			}
		} else {
			System.out.println("target Ontology  is null");
		}

		return false;
	}

	public static OntResource getOntologyResource(String resourceName,
			Ontology ontology) {
		OntologyModel ontologyModel = OntologyModel.getInstance();
		OntResource sourceResource = ontologyModel.getOntologyModel()
				.getOntResource(ontology.getURI() + "#" + resourceName);
		if (sourceResource == null) {
			System.out.println("Could not find resource " + resourceName);
		}
		return sourceResource;
	}

	public static URI getOntologyURI(String fileName) {

		return URI.createURI(DEFAULTONTOLOGY_URI + fileName);
	}

	public static URL getOntologyURL(String fileName) {
		String ontologyURLString = getPluginFilePath() + ONTOLOGY_PATH
				+ fileName;
		try {
			return new URL(ontologyURLString);
		} catch (MalformedURLException e) {
			System.out.println("Could not form a valid URL from "
					+ ontologyURLString);
			e.printStackTrace();
		}
		return null;
	}

	public static List<OntResource> getOntResources(
			List<OntResource> referenceClasses) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This method returns if a MatchRecommendation has already been executed.
	 * 
	 * @param matchRecommendation
	 *            the MatchRecommendation object
	 * @return true if the recommendation has already been executed, false if
	 *         not
	 */
	public static boolean isLinked(MatchRecommendation matchRecommendation) {
		OntResource sourceResource = matchRecommendation.getSource();
		OntResource targetResource = matchRecommendation.getTarget();
		if (matchRecommendation.getSource().isClass()) {
			OntClass sourceOntClass = sourceResource.asClass();
			if (targetResource.isClass()) {
				OntClass targetOntClass = targetResource.asClass();
				List<OntClass> equivalentClasses = sourceOntClass
						.listEquivalentClasses().toList();
				if (equivalentClasses.contains(targetOntClass)) {
					return true;
				}

			}
		}

		else if (sourceResource.isProperty()) {
			OntProperty sourceOntProperty = sourceResource.asProperty();
			if (targetResource.isProperty()) {
				OntProperty targetOntProperty = targetResource.asProperty();
				if (sourceOntProperty.listEquivalentProperties().toList()
						.contains(targetOntProperty)) {
					return true;
				}
			}
		}

		else if (sourceResource.isIndividual()) {
			Individual sourceIndividual = sourceResource.asIndividual();
			if (targetResource.isIndividual()) {
				Individual targetIndividual = targetResource.asIndividual();
				if (sourceIndividual.listSameAs().toList()
						.contains(targetIndividual)) {
					return true;
				}
			} else if (targetResource.isClass()) {
				OntClass referenceClass = targetResource.asClass();
				if (sourceIndividual.listOntClasses(true).toList()
						.contains(referenceClass)) {
					return true;
				}
			}
		}

		return false;

	}

	public static boolean hasReference(Ontology referenceOntology,
			OntResource ontResource) {

		if (ontResource.isClass()) {
			OntClass sourceOntClass = ontResource.asClass();
			List<OntClass> equivalentClasses = sourceOntClass
					.listEquivalentClasses().toList();
			for (OntClass equivalentClass : equivalentClasses) {
				if (OntologyHelper.isInOntology(referenceOntology,
						equivalentClass)) {
					return true;
				}

			}
		}

		else if (ontResource.isProperty()) {
			OntProperty sourceOntProperty = ontResource.asProperty();

			for (OntProperty equivalentProperty : sourceOntProperty
					.listEquivalentProperties().toList()) {
				if (OntologyHelper.isInOntology(referenceOntology,
						equivalentProperty)) {
					return true;
				}

			}

		}

		else if (ontResource.isIndividual()) {
			Individual sourceIndividual = ontResource.asIndividual();
			for (Individual sameIndividual : OntologyHelper
					.getIndividuals(sourceIndividual.listSameAs().toList())) {
				if (OntologyHelper.isInOntology(referenceOntology,
						sameIndividual)) {
					return true;
				}

			}
		}

		return false;
	}

	/**
	 * This method returns EObjects loaded from an XML file.
	 * 
	 * @param filename
	 *            The name of the file from which the Elements are loaded
	 * @return a list of Objects which was loaded from a file with the given
	 *         filename
	 */
	public static EList<EObject> loadEObjectsFromFile(String filename) {

		ResourceSetImpl resourceSet = new ResourceSetImpl();

		org.eclipse.emf.ecore.resource.Resource resource = resourceSet
				.getResource(
						URI.createURI(getPluginFilePath(ONTOLOGYPLUGIN_ID)
								+ MODEL_PATH + filename), true);

		EList<EObject> result = new BasicEList<EObject>();

		for (EObject obj : resource.getContents()) {
			result.add(EcoreUtil.copy(obj));
		}

		return result;
	}

	/**
	 * This method creates a URI out of a given concept name and ontology.
	 * 
	 * @param ontology
	 *            The ontology which gives the name space
	 * @param entityName
	 *            the name of the entity
	 * @return The URI of the entity
	 */
	public static URI generateURI(Ontology ontology, String entityName) {

		return URI.createURI(ontology.getURI() + "#" + entityName);

	}

	public static ObjectProperty getPartOfProperty(OntologyModel ontologyModel) {
		URI partOfPropertyURI = URI.createURI(DEFAULTONTOLOGY_URI
				+ MEROLOGY_FILENAME + "#" + CONTAINMENT_PROPERTY_NAME);
		return ontologyModel.getObjectProperty(partOfPropertyURI);

	}

	public static List<Individual> getIndividuals(List<? extends RDFNode> list) {
		List<Individual> datatypeProperties = new ArrayList<Individual>();
		for (RDFNode resource : list) {
			if (resource.canAs(Individual.class)) {
				datatypeProperties.add(resource.as(Individual.class));
			}

		}
		return datatypeProperties;
	}

	public static void addMerologyFileEntry(OntModel ontModel) {
		ontModel.getDocumentManager().addAltEntry(
				DEFAULTONTOLOGY_URI + MEROLOGY_FILENAME,
				OntologyHelper.getPluginFilePath() + ONTOLOGY_PATH
						+ MEROLOGY_FILENAME);

	}

	public static void addReferenceOntologyFileEntry(OntModel ontModel) {
		ontModel.getDocumentManager().addAltEntry(
				DEFAULTONTOLOGY_URI + REFERENCE_ONTOLOGY_FILENAME,
				OntologyHelper.getPluginFilePath() + ONTOLOGY_PATH
						+ REFERENCE_ONTOLOGY_FILENAME);

	}

	public static void addReferenceOntologyFileEntry() {
		OntologyModel
				.getInstance()
				.getOntologyModel()
				.getDocumentManager()
				.addAltEntry(
						DEFAULTONTOLOGY_URI + REFERENCE_ONTOLOGY_FILENAME,
						OntologyHelper.getPluginFilePath() + ONTOLOGY_PATH
								+ REFERENCE_ONTOLOGY_FILENAME);

	}

	@SuppressWarnings("unchecked")
	public static <R extends Resource> List<R> getOntResourcesByClass(
			Class<R> clazz, Ontology ontology, OntModel ontModel) {

		List<R> resourcesInModel = new ArrayList<R>();

		if (clazz.equals(OntClass.class)) {
			resourcesInModel = (List<R>) ontModel.listClasses()
					.filterDrop(new OntologyFilter<OntClass>(ontology))
					.filterDrop(new AnonymousOntClassFilter()).toList();

		}

		else if (clazz.equals(OntProperty.class)) {
			resourcesInModel = (List<R>) ontModel.listAllOntProperties()
					.filterDrop(new OntologyFilter<OntProperty>(ontology))
					.toList();
		}

		else if (clazz.equals(Individual.class)) {
			resourcesInModel = (List<R>) ontModel.listIndividuals()
					.filterDrop(new OntologyFilter<Individual>(ontology))
					.toList();
		} else if (clazz.equals(ObjectProperty.class)) {
			resourcesInModel = (List<R>) ontModel.listObjectProperties()
					.filterDrop(new OntologyFilter<ObjectProperty>(ontology))
					.toList();
		} else if (clazz.equals(DatatypeProperty.class)) {
			resourcesInModel = (List<R>) ontModel.listDatatypeProperties()
					.filterDrop(new OntologyFilter<DatatypeProperty>(ontology))
					.toList();
		}

		return resourcesInModel;
	}

	/**
	 * This method returns the path of the current workspace as string.
	 * 
	 * @param pluginID
	 *            the ID of the plug-in
	 * @return the file path to the current workspace as string
	 */
	public static String getPluginFilePath(String pluginID) {
		String result = "";
		if (getPluginFileURL(pluginID) != null) {
			result = getPluginFileURL(pluginID).toString();
		}
		return result;
	}

	/**
	 * This method returns the path of the current workspace as string.
	 * 
	 * @param pluginID
	 *            the ID of the plug-in
	 * @return the file path to the current workspace as string
	 */
	public static URL getPluginFileURL(String pluginID) {

		try {
			return FileLocator.resolve(new URL("platform:/plugin/" + pluginID
					+ "/"));
		} catch (MalformedURLException e) {
			System.out.println("Could not form a valid URL from " + pluginID);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not locate a file folder at " + pluginID);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * getFileURL.
	 * 
	 * @param filePath
	 *            path of the file
	 * @param fileName
	 *            name of the file
	 * @return new URL
	 */
	public static URL getFileURL(String filePath, String fileName) {
		try {
			return new URL(filePath + fileName);
		} catch (MalformedURLException e) {
			System.out.println("Could not form a valid URL from " + filePath
					+ fileName);
			e.printStackTrace();
		}
		return null;
	}

}
