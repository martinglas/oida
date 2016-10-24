/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import oida.ontology.util.OntologyHelper;

import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.ontology.ProfileRegistry;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.eclipse.emf.common.util.URI;



/**
 * This class provides an OWL ontology model object.
 * 
 * @author Martin Glas
 * 
 */
public final class OntologyModel {
	private OntModel ontologyModel;
	private URI referenceOntologyURI;
	private URI baseOntologyURI;
	private static OntologyModel instance;

	/**
	 * This method gets the instance of the singleton.
	 * 
	 * @return the singleton instance of the class
	 */
	public static OntologyModel getInstance() {
		if (instance == null) {
			instance = new OntologyModel();

		}
		return instance;
	}

	/**
	 * This is the constructor method which initializes the Ontology object.
	 */
	private OntologyModel() {
		// OntModel newOntologyModel = ModelFactory
		// .createOntologyModel(ProfileRegistry.OWL_DL_LANG);

		OntModel newOntologyModel = ModelFactory
				.createOntologyModel(ProfileRegistry.OWL_DL_LANG);

		this.ontologyModel = newOntologyModel;
	}

	/**
	 * This method creates a new Ontology and adds it to the ontology model.
	 * 
	 * @param ontologyURI
	 *            the URI of the ontology
	 * @return the newly created OntologyObject
	 */
	public Ontology addOntology(URI ontologyURI) {
		return getOntologyModel().createOntology(ontologyURI.toString());
	}

	/**
	 * The getter method of the ontologyModel object.
	 * 
	 * @return the returned OntModel
	 */
	public OntModel getOntologyModel() {
		return ontologyModel;
	}

	/**
	 * This method adds a class with a given name to the Ontology.
	 * 
	 * @param ontClasslassURI
	 *            the name of the class
	 * @return
	 */
	public OntClass addOntClass(URI ontClasslassURI) {
		OntClass returnOntClass = getOntologyClass(ontClasslassURI);
		if (getOntologyClass(ontClasslassURI) == null) {
			returnOntClass = ontologyModel.createClass(ontClasslassURI
					.toString());

		} else {
			System.out.println("Class " + ontClasslassURI
					+ " already existed in the ontology");
		}
		return returnOntClass;

	}

	/**
	 * This class creates an individual as an instance of a certain class in the
	 * ontology model.
	 * 
	 * @param individualURI
	 *            The name of the individual
	 * @param ontClass
	 *            The class the individual is instance of
	 */
	public Individual addIndividual(URI individualURI, OntClass ontClass) {
		Individual individual = getIndividual(individualURI);
		if (individual == null) {
			individual = ontologyModel.createIndividual(
					individualURI.toString(), ontClass);
		} else {

			System.out.println("Individual " + individualURI
					+ " already existed in the ontology");
		}
		return individual;
	}

	/**
	 * This class returns an Individual having a certain name if it exists in
	 * the ontology model.
	 * 
	 * @param individualName
	 *            The name of the Individual
	 * @return The Individual
	 */
	public Individual getIndividual(URI individualName) {

		return ontologyModel.getIndividual(individualName.toString());
	}

	/**
	 * This method returns an OntologyClass having a certain name if it exists
	 * in the ontology model.
	 * 
	 * @param classURI
	 *            The name of the ontology class
	 * @return The returned OntologyClass Object
	 */
	public OntClass getOntologyClass(URI classURI) {
		return ontologyModel.getOntClass(classURI.toString());
	}

	/**
	 * This method instantiates an object property between two individuals and
	 * adds it to the ontology model.
	 * 
	 * @param domainIndividualURI
	 *            The source individual name
	 * @param objectPropertyURI
	 *            The name of the object property
	 * @param rangeIndividualURI
	 *            The target individual name
	 */
	public void addObjectPropertyInstance(URI domainIndividualURI,
			URI objectPropertyURI, URI rangeIndividualURI) {

		if (!containsIndividual(domainIndividualURI)) {
			System.out
					.println("The domain Individual "
							+ domainIndividualURI
							+ " is missing in the ontology. Can't create new object property instance");
			return;
		}

		if (!containsIndividual(rangeIndividualURI)) {
			System.out
					.println("The range Individual "
							+ rangeIndividualURI
							+ " is missing in the ontology. Can't create new object property instance");
			return;
		}

		if (!containsProperty(objectPropertyURI)) {
			System.out
					.println("The ObjectProperty "
							+ objectPropertyURI
							+ " is missing in the ontology. Can't create new object property instance");
			return;
		}

		ObjectProperty objectProperty = getOntologyModel().getObjectProperty(
				objectPropertyURI.toString());

		Individual targetIndividual = getOntologyModel().getIndividual(
				rangeIndividualURI.toString());

		if (!getOntologyModel().getIndividual(domainIndividualURI.toString())
				.hasProperty(objectProperty, targetIndividual)) {
			getOntologyModel().getIndividual(domainIndividualURI.toString())
					.addProperty(objectProperty, targetIndividual);
			//

		}
	}

	/**
	 * This method instantiates an data type property between two individuals
	 * and adds it to the ontology model.
	 * 
	 * @param domainIndividualURI
	 *            The source individual name
	 * @param propertyValue
	 *            the value of the data type property as String
	 * @param datatypePropertyURI
	 *            The name of the data type property
	 */
	public void addDatatypePropertyInstance(URI domainIndividualURI,
			URI datatypePropertyURI, String propertyValue) {
		if (!containsIndividual(domainIndividualURI)) {
			System.out
					.println("The domain Individual "
							+ domainIndividualURI
							+ " is missing in the ontology. Can't create new object property instance");
			return;
		}

		if (!containsProperty(datatypePropertyURI)) {
			System.out
					.println("The Datatype  "
							+ datatypePropertyURI
							+ " is missing in the ontology. Can't create new object property instance");
			return;
		}

		DatatypeProperty datatypeProperty = getOntologyModel()
				.getDatatypeProperty(datatypePropertyURI.toString());

		if (!getIndividual(domainIndividualURI).hasProperty(datatypeProperty)) {
			getIndividual(domainIndividualURI).addProperty(datatypeProperty,
					propertyValue);

		}

	}

	/**
	 * This method returns an ObjectProperty if it exists in the ontology model
	 * .
	 * 
	 * @param objectPropertyName
	 *            the name of the ObjectProperty
	 * @return The ObjectProperty from the ontology model
	 */
	public ObjectProperty getObjectProperty(URI objectPropertyName) {
		return getOntologyModel().getObjectProperty(
				objectPropertyName.toString());
	}

	/**
	 * This method returns all object properties in the ontology.
	 * 
	 * @return a list of all object properties in the ontology model
	 */
	public List<ObjectProperty> getObjectProperties() {
		List<ObjectProperty> objectProperties = new ArrayList<ObjectProperty>(
				getOntologyModel().listObjectProperties().toList());
		return objectProperties;

	}

	/**
	 * This method returns an iterator of all individuals in the ontology model.
	 * 
	 * @return The List of all individuals
	 */
	public List<Individual> getIndividuals() {
		List<Individual> resultList = new ArrayList<Individual>();
		Iterator<Individual> individualsIterator = getOntologyModel()
				.listIndividuals();
		while (individualsIterator.hasNext()) {
			resultList.add(individualsIterator.next());

		}
		return resultList;

	}

	/**
	 * This method returns a list of all classes in the ontology model.
	 * 
	 * @return The iterator of all classes
	 */
	public List<OntClass> listClasses() {
		List<OntClass> resultList = new ArrayList<OntClass>();
		Iterator<OntClass> ontClassIterator = getOntologyModel().listClasses();
		while (ontClassIterator.hasNext()) {
			resultList.add(ontClassIterator.next());
		}
		return resultList;
	}

	/**
	 * This method returns a list of all classes in the ontology model having
	 * individuals.
	 * 
	 * @return The list of all classes with individuals
	 */
	public List<OntClass> getClassesWithInstances() {
		List<OntClass> classesInOntology = getClasses();
		List<OntClass> classesWithInstances = new ArrayList<OntClass>();
		for (OntClass ontologyClass : classesInOntology) {

			if (getInstances(ontologyClass).size() > 0) {
				classesWithInstances.add(ontologyClass);

			}
		}
		return classesWithInstances;
	}

	/**
	 * This method returns a list of all classes in the ontology model having
	 * individuals.
	 * 
	 * @return The list of all classes with individuals
	 */
	public List<OntClass> getClassesWithInstances(Ontology ontology) {
		List<OntClass> classesInOntology = getResourcesByClass(OntClass.class,
				ontology);
		List<OntClass> classesWithInstances = new ArrayList<OntClass>();
		for (OntClass ontologyClass : classesInOntology) {

			if (getInstances(ontologyClass).size() > 0) {
				classesWithInstances.add(ontologyClass);

			}
		}
		return classesWithInstances;
	}

	/**
	 * This method returns an iterator of all imported ontologies in the
	 * ontology model.
	 * 
	 * @return The iterator of all imported ontologies
	 */
	public List<Ontology> getOntologiesURIs() {
		List<Ontology> resultList = new ArrayList<Ontology>();
		Iterator<Ontology> ontologyIterator = getOntologyModel()
				.listOntologies();
		while (ontologyIterator.hasNext()) {
			resultList.add(ontologyIterator.next());
		}
		return resultList;
	}

	/**
	 * This method returns an iterator of all imported ontologies in the
	 * ontology model.
	 * 
	 * @return The iterator of all imported ontologies
	 */
	public List<URI> getImportedOntologyURIs() {
		List<URI> resultList = new ArrayList<URI>();
		Iterator<String> uriIterator = getOntologyModel()
				.listImportedOntologyURIs().iterator();
		while (uriIterator.hasNext()) {
			String uriString = uriIterator.next();
			resultList.add(URI.createURI(uriString));

		}

		return resultList;
	}

	/**
	 * This class returns true if a class with a certain name exists in the
	 * ontology model.
	 * 
	 * @param uri
	 *            the name of the class
	 * @return true or false if it exists
	 */
	public boolean containsClass(URI uri) {
		if (ontologyModel.getOntClass(uri.toString()) != null) {
			return true;
		}
		return false;
	}

	/**
	 * This class returns true if an individual with a certain name exists in
	 * the ontology model.
	 * 
	 * @param uri
	 *            the name of the individual
	 * @return true or false if it exists
	 */
	public boolean containsIndividual(URI uri) {
		if (ontologyModel.getIndividual(uri.toString()) != null) {
			return true;
		}
		return false;
	}

	/**
	 * This class returns true if a property with a certain name exists in the
	 * ontology model.
	 * 
	 * @param uri
	 *            the name of the property
	 * @return true or false if it exists
	 */
	public boolean containsProperty(URI uri) {
		OntProperty property = ontologyModel.getOntProperty(uri.toString());
		if (property != null) {
			return true;
		}
		return false;
	}

	/**
	 * This class returns true if an concept (class, individual or property)
	 * with a certain name exists in the ontology model.
	 * 
	 * @param uri
	 *            the name of the concept
	 * @return true or false if it exists
	 */
	public boolean containsConcept(URI uri) {
		if (containsClass(uri)) {
			return true;
		}
		if (containsProperty(uri)) {
			return true;
		}
		if (containsIndividual(uri)) {
			return true;
		}

		return false;

	}

	/**
	 * This method resets the ontologyModel singleton.
	 */
	public void reset() {

		instance = new OntologyModel();

	}

	/**
	 * This method counts the number of statements in the ontologyModel
	 * singleton.
	 * 
	 * @return the number of statements in the ontology
	 */
	public long getSize() {
		return getOntologyModel().size();
	}

	/**
	 * This method returns all Individuals which are an instance of a class
	 * specified by an URI.
	 * 
	 * @param classURI
	 *            the URI of the class
	 * @return a list of Individuals which are instances of the given class
	 */
	public List<Individual> getInstances(URI classURI) {

		OntClass ontClass = getOntologyClass(classURI);

		return getInstances(ontClass);
	}

	/**
	 * This method returns a list of ontology classes in the ontology model.
	 * 
	 * @return a list of ontology classes in the ontology model
	 */
	public List<OntClass> getClasses() {
		Ontology ontology;

		return ontologyModel.listClasses().toList();
	}

	/**
	 * This method returns all Individuals which are an instance of a given
	 * ontology class.
	 * 
	 * @param ontClass
	 *            the OntClass object
	 * @return a list of Individuals which are instances of the given class
	 */
	public List<Individual> getInstances(OntClass ontClass) {
		List<Individual> resultList = new ArrayList<Individual>();
		Iterator<? extends OntResource> resourceIterator = ontClass
				.listInstances();
		while (resourceIterator.hasNext()) {
			OntResource resource = resourceIterator.next();
			if (resource instanceof Individual) {
				resultList.add((Individual) resource);
			}
		}
		return resultList;
	}

	/**
	 * This method returns all datatypeProperties available in the ontology
	 * model.
	 * 
	 * @return a list of DatatypeProperty objects
	 */
	public List<DatatypeProperty> getDatatypeProperties() {

		return ontologyModel.listDatatypeProperties().toList();
	}

	/**
	 * This method creates a new ObjectProperty given a name and the domain and
	 * range classes. If the classes do not exist, they are created
	 * 
	 * @param domainClassURI
	 *            the URI of the domain class
	 * @param objectPropertyURI
	 *            the URI of the ObjectProperty which is to be created
	 * @param rangeClassURI
	 *            the URI of the range class
	 * @return the created ObjectProperty object
	 */
	public ObjectProperty addObjectProperty(URI domainClassURI,
			URI objectPropertyURI, URI rangeClassURI) {
		if (getObjectProperty(objectPropertyURI) == null) {
			if (!containsClass(domainClassURI)) {
				System.out
						.println("The domainClass "
								+ domainClassURI
								+ " is missing in the ontology. Can't create new object property");
				return null;
			}

			if (!containsClass(rangeClassURI)) {
				System.out
						.println("The rangeClass "
								+ rangeClassURI
								+ " is missing in the ontology. Can't create new object property");
				return null;
			}
			// if (getOntologyClass(domainClassURI) == null) {
			// ontologyModel.createClass(domainClassURI.toString());
			// }

			OntClass domainClass = getOntologyClass(domainClassURI);

			// if (getOntologyClass(rangeClassURI) == null) {
			// ontologyModel.createClass(rangeClassURI.toString());
			// }
			OntClass rangeClass = getOntologyClass(rangeClassURI);

			ObjectProperty newObjectProperty = ontologyModel
					.createObjectProperty(objectPropertyURI.toString());
			newObjectProperty.setDomain(domainClass);
			newObjectProperty.setRange(rangeClass);
			ObjectProperty testObjectProperty = ontologyModel
					.getObjectProperty(objectPropertyURI.toString());

			return newObjectProperty;

		} else {
			System.out.println("The object property " + objectPropertyURI
					+ " already existed. New one not created");
			return null;
		}

	}

	/**
	 * This method returns an Ontology object having a given URI.
	 * 
	 * @param ontologyURI
	 *            the given URI
	 * @return the Ontology object
	 */
	public Ontology getOntology(URI ontologyURI) {
		return ontologyModel.getOntology(ontologyURI.toString());
	}

	/**
	 * This method creates a new DatatypeProperty given a name and the domain
	 * and range classes. If the classes do not exist, they are created
	 * 
	 * @param domainClassURI
	 *            the URI of the domain class
	 * @param datatypePropertyURI
	 *            the URI of the ObjectProperty which is to be created
	 * @param datatypeResource
	 *            the Resource of the range data type of the data type property
	 */
	public void addDatatypeProperty(URI domainClassURI,
			URI datatypePropertyURI, Resource datatypeResource) {
		if (getObjectProperty(datatypePropertyURI) == null) {
			if (!containsClass(domainClassURI)) {
				System.out
						.println("The domain class "
								+ domainClassURI
								+ " is missing in the ontology. Can't create new datatype property");
				return;
			}

			// if (getOntologyClass(domainClassURI) == null) {
			// ontologyModel.createClass(domainClassURI.toString());
			// }
			OntClass domainClass = getOntologyClass(domainClassURI);

			DatatypeProperty newDatatypeProperty = ontologyModel
					.createDatatypeProperty(datatypePropertyURI.toString());
			newDatatypeProperty.setDomain(domainClass);

			newDatatypeProperty.setRange(datatypeResource);

			return;

		}

		else {
			System.out.println("The datatype property " + datatypePropertyURI
					+ " already existed. New one not created");
		}
	}

	/**
	 * This method returns the number of OntClass resources in an ontology
	 * model.
	 * 
	 * @return the number of OntClass resources
	 */
	public int getNumberOfOntClasses() {

		return getClasses().size();
	}

	/**
	 * This method returns the number of Individual resourcesin an ontology
	 * model.
	 * 
	 * @return the number of Individual resources
	 */
	public int getNumberOfIndividuals() {

		return getIndividuals().size();
	}

	/**
	 * This method returns the number of ObjectProperty resources in an ontology
	 * model.
	 * 
	 * @return the number of ObjectProperty resources
	 */
	public int getNumberOfObjectProperties() {

		return getObjectProperties().size();
	}

	/**
	 * This method returns the number of DatatypeProperty resources in an
	 * ontology model.
	 * 
	 * @return the number of DatatypeProperty resources
	 */
	public int getNumberofDatatypeProperties() {
		return getDatatypeProperties().size();

	}

	/**
	 * This method returns Resources of a given Class from the ontology model.
	 * 
	 * @param <R>
	 *            the class parameter
	 * @param clazz
	 *            the given Class
	 * @return a list of Resources having the given Class.
	 */
	@SuppressWarnings("unchecked")
	public <R extends Resource> List<R> getResourcesByClass(Class<R> clazz) {

		List<R> resourcesInModel = new ArrayList<R>();

		if (clazz.equals(OntClass.class)) {
			resourcesInModel = (List<R>) ontologyModel.listClasses()
					.filterDrop(new AnonymousOntClassFilter()).toList();

		}

		else if (clazz.equals(OntProperty.class)) {
			resourcesInModel = (List<R>) ontologyModel.listAllOntProperties()
					.toList();
		}

		else if (clazz.equals(Individual.class)) {
			resourcesInModel = (List<R>) ontologyModel.listIndividuals()
					.toList();
		} else if (clazz.equals(ObjectProperty.class)) {
			resourcesInModel = (List<R>) ontologyModel.listObjectProperties()
					.toList();
		} else if (clazz.equals(DatatypeProperty.class)) {
			resourcesInModel = (List<R>) ontologyModel.listDatatypeProperties()
					.toList();
		}

		return resourcesInModel;
	}

	/**
	 * This method returns Resources of a given Class from a given ontology.
	 * 
	 * @param <R>
	 *            the class parameter
	 * @param clazz
	 *            the given Class
	 * @param ontology
	 *            the source ontology
	 * @return a list of Resources having the given Class.
	 */
	public <R extends Resource> List<R> getResourcesByClass(Class<R> clazz,
			Ontology ontology) {

		return OntologyHelper.getOntResourcesByClass(clazz, ontology,
				ontologyModel);
	}

	public DatatypeProperty getDatatypeProperty(URI datatypeURI) {
		return ontologyModel.getDatatypeProperty(datatypeURI.toString());
	}

	public List<Individual> getObjectPropertyInstances(
			Individual sourceIndividual, ObjectProperty objectProperty,
			Ontology ontology) {
		List<Individual> resultIndividuals = new ArrayList<Individual>();

		Resource propertyValue = sourceIndividual
				.getPropertyResourceValue(objectProperty);
		List<RDFNode> rdfNodes = sourceIndividual.listPropertyValues(
				objectProperty).toList();

		for (RDFNode rdfNode : rdfNodes) {
			if (rdfNode.canAs(Individual.class)) {
				Individual individual = rdfNode.as(Individual.class);
				if (OntologyHelper.isInOntology(ontology, individual)) {
					resultIndividuals.add(individual);
				}
			}
		}

		return resultIndividuals;
	}

	public void setReferenceOntologyURI(URI referernceOntology) {
		this.referenceOntologyURI = referernceOntology;

	}

	public URI getReferenceOntologyURI() {
		return referenceOntologyURI;

	}

	public void setBaseOntollogyURI(URI baseOntologyURI) {
		this.baseOntologyURI = baseOntologyURI;
	}

	public URI getBaseOntologyURI() {
		return baseOntologyURI;

	}

	public List<OntClass> getReferenceClasses() {

		return getResourcesByClass(OntClass.class, getReferenceOntology());
	}

	public List<OntClass> getBaseClasses() {

		return getResourcesByClass(OntClass.class, getBaseOntology());
	}

	public List<OntClass> getBaseClassesWithInstances() {
		return getClassesWithInstances(getBaseOntology());

	}

	public List<Individual> getReferenceIndividuals() {

		return getResourcesByClass(Individual.class, getReferenceOntology());
	}

	public Ontology getReferenceOntology() {
		return getOntology(getReferenceOntologyURI());
	}

	public List<Individual> getBaseIndividuals() {
		return getResourcesByClass(Individual.class, getBaseOntology());
	}

	public Ontology getBaseOntology() {
		return getOntology(getBaseOntologyURI());

	}

	public OntClass getReferenceOntClass(OntClass sourceOntClass) {

		List<OntClass> equivalentOntClasses = sourceOntClass
				.listEquivalentClasses().toList();
		for (OntClass equivalentClass : equivalentOntClasses) {
			if (OntologyHelper.isInOntology(getReferenceOntology(),
					equivalentClass)) {
				return equivalentClass;
			}

		}
		return null;

	}

	public List<DatatypeProperty> getBaseDatatypeProperties() {
		List<DatatypeProperty> resultList = new ArrayList<DatatypeProperty>();
		resultList.addAll(getResourcesByClass(DatatypeProperty.class,
				getBaseOntology()));
		return resultList;
	}

	public List<ObjectProperty> getBaseObjectProperties() {
		List<ObjectProperty> resultList = new ArrayList<ObjectProperty>();
		resultList.addAll(getResourcesByClass(ObjectProperty.class,
				getBaseOntology()));
		return resultList;
	}

	public List<OntClass> getTopBaseClasses() {
		List<OntClass> resultOntClassList = new ArrayList<OntClass>();
		List<OntClass> ontClasses = getBaseClasses();
		OntResource topResource = ontologyModel
				.getOntResource("http://www.w3.org/2000/01/rdf-schema#Resource");
		for (OntClass ontClass : ontClasses) {
			OntClass superOntClass = ontClass.getSuperClass();
			if (superOntClass.equals(topResource)) {
				resultOntClassList.add(ontClass);
			}
		}
		return resultOntClassList;
	}

	public Individual getReferenceIndividual(Individual sourceIndividual) {

		List<Individual> equivalentIndividuals = OntologyHelper
				.getIndividuals(sourceIndividual.listSameAs().toList());
		for (Individual equivalentIndividual : equivalentIndividuals) {
			if (OntologyHelper.isInOntology(getReferenceOntology(),
					equivalentIndividual)) {
				return equivalentIndividual;
			}

		}
		return null;

	}

	public List<DatatypeProperty> getBaseDatatypeProperties(OntClass ontClass) {
		List<DatatypeProperty> resultList = new ArrayList<DatatypeProperty>();
		for (DatatypeProperty datatypeProperty : getBaseDatatypeProperties()) {

			if (datatypeProperty.getDomain().equals(ontClass)) {
				resultList.add(datatypeProperty);
			}
		}
		return resultList;
	}

	public List<ObjectProperty> getBaseObjectProperties(OntClass ontClass) {
		List<ObjectProperty> resultList = new ArrayList<ObjectProperty>();
		for (ObjectProperty objectProperty : getBaseObjectProperties()) {

			if (objectProperty.getDomain().equals(ontClass)) {
				resultList.add(objectProperty);
			}
		}
		return resultList;
	}

	public OntResource getReferenceOntResource(OntResource sourceOntResource) {
		if (sourceOntResource.isClass()) {
			return getReferenceOntClass(sourceOntResource.asClass());
		} else if (sourceOntResource.isDatatypeProperty()) {
			return getReferenceDatatypeProperty(sourceOntResource
					.asDatatypeProperty());
		} else if (sourceOntResource.isObjectProperty()) {
			return getReferenceObjectProperty(sourceOntResource
					.asObjectProperty());
		}

		else if (sourceOntResource.isIndividual()) {
			return getReferenceIndividual(sourceOntResource.asIndividual());
		}
		return null;
	}

	public DatatypeProperty getReferenceDatatypeProperty(
			DatatypeProperty sourceDatatypeProperty) {

		List<DatatypeProperty> equivalentDatatypeProperties = OntologyHelper
				.getDatatypeProperties(sourceDatatypeProperty
						.listEquivalentProperties().toList());
		for (DatatypeProperty equivalentDatatypeProperty : equivalentDatatypeProperties) {
			if (OntologyHelper.isInOntology(getReferenceOntology(),
					equivalentDatatypeProperty)) {
				return equivalentDatatypeProperty;
			}

		}
		return null;

	}

	public ObjectProperty getReferenceObjectProperty(
			ObjectProperty sourceObjectProperty) {

		List<ObjectProperty> equivalentObjectProperties = OntologyHelper
				.getObjectProperties(sourceObjectProperty
						.listEquivalentProperties().toList());
		for (ObjectProperty equivalentObjectProperty : equivalentObjectProperties) {
			if (OntologyHelper.isInOntology(getReferenceOntology(),
					equivalentObjectProperty)) {
				return equivalentObjectProperty;
			}

		}
		return null;

	}

	public Object getReferenceObjectProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	public OntClass getReferenceOntClass(Individual sourceIndividual) {

		for (OntClass candidateOntClass : sourceIndividual.listOntClasses(true)
				.toList()) {
			if (OntologyHelper.isInOntology(getReferenceOntology(),
					candidateOntClass))
				return candidateOntClass;
		}
		return null;
	}

	public OntClass getBaseClass(Individual sourceIndividual) {
		for (OntClass candidateOntClass : sourceIndividual.listOntClasses(true)
				.toList()) {
			if (OntologyHelper.isInOntology(getBaseOntology(),
					candidateOntClass))
				return candidateOntClass;
		}
		return null;
	}

	public List<OntResource> getAllBaseOntResorces() {
		List<OntResource> allOntResources = new ArrayList<OntResource>();
		allOntResources.addAll(getClasses());
		allOntResources.addAll(getObjectProperties());
		allOntResources.addAll(getDatatypeProperties());
		allOntResources.addAll(getIndividuals());

		List<OntResource> allBaseOntResources = new ArrayList<OntResource>();
		for (OntResource ontResource : allOntResources) {
			if (OntologyHelper.isInOntology(getBaseOntology(), ontResource)) {
				allBaseOntResources.add(ontResource);
			}
		}

		return allBaseOntResources;
	}

	public void addDatatypePropertyInstance(URI domainIndividualURI,
			URI datatypePropertyURI, Literal literal) {

		if (!containsIndividual(domainIndividualURI)) {
			System.out
					.println("The domain Individual "
							+ domainIndividualURI
							+ " is missing in the ontology. Can't create new object property instance");
			return;
		}

		if (!containsProperty(datatypePropertyURI)) {
			System.out
					.println("The Datatype  "
							+ datatypePropertyURI
							+ " is missing in the ontology. Can't create new object property instance");
			return;
		}

		DatatypeProperty datatypeProperty = getOntologyModel()
				.getDatatypeProperty(datatypePropertyURI.toString());

		if (!getIndividual(domainIndividualURI).hasProperty(datatypeProperty)) {
			getIndividual(domainIndividualURI).addProperty(datatypeProperty,
					literal);

		}

	}

	@SuppressWarnings("unchecked")
	public <R extends OntResource> List<R> getSubOntResources(Class<R> clazz,
			Ontology ontology, OntResource superOntResource) {
		List<R> resultOntResourceList = new ArrayList<R>();
		List<R> ontResources = getResourcesByClass(clazz, ontology);

		for (OntResource ontResource : ontResources) {
			if (ontResource.isClass()) {
				OntClass ontClass = ontResource.asClass();
				List<OntClass> superClasses = ontClass.listSuperClasses(true)
						.filterDrop(new AnonymousOntClassFilter()).toList();
				for (OntClass superOntClass : superClasses) {

					if (superOntClass.equals(superOntResource)) {
						resultOntResourceList.add((R) ontClass);
					}
				}

			} else if (ontResource.isProperty()) {
				OntProperty ontProperty = ontResource.asProperty();
				OntProperty superOntClass = ontProperty.getSuperProperty();

				if (superOntClass.equals(superOntResource)) {
					resultOntResourceList.add((R) ontProperty);
				}

			}
		}
		return resultOntResourceList;

	}

	public List<OntClass> getOntClasses(Ontology targetOntology) {

		List<OntClass> resultOntClasses = new ArrayList<OntClass>();
		for (OntClass candidateOntClass : ontologyModel.listClasses().toList()) {
			if (OntologyHelper.isInOntology(targetOntology, candidateOntClass))
				resultOntClasses.add(candidateOntClass);
		}
		return resultOntClasses;
	}

	public void removeOntology(Ontology sourceOntology) {
		List<OntResource> allOntResources = new ArrayList<OntResource>();
		allOntResources.addAll(getResourcesByClass(OntClass.class,
				sourceOntology));
		allOntResources.addAll(getResourcesByClass(DatatypeProperty.class,
				sourceOntology));
		allOntResources.addAll(getResourcesByClass(ObjectProperty.class,
				sourceOntology));
		allOntResources.addAll(getResourcesByClass(Individual.class,
				sourceOntology));

		for (OntResource ontResource : allOntResources) {
			ontResource.remove();
		}
		sourceOntology.remove();

	}

	public OntClass getOntClass(URI classURI) {
		return ontologyModel.getOntClass(classURI.toString());
	}

	public OntProperty getOntProperty(URI ontPropertyURI) {

		return ontologyModel.getOntProperty(ontPropertyURI.toString());
	}

}
