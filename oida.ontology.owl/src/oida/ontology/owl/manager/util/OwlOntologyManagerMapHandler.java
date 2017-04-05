/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.owl.manager.util;

import java.util.HashMap;
import java.util.Optional;

import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;

import oida.ontology.Ontology;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyIndividual;
import oida.ontology.OntologyItem;
import oida.ontology.OntologyObjectProperty;
import oida.ontology.manager.util.OntologyManagerUtils;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-04-03
 *
 */
public final class OwlOntologyManagerMapHandler {
	private HashMap<String, OWLObject> owlAPIMap;
	private HashMap<String, OntologyItem> internalAPIMap;

	private OWLClass owlThingClass;
	private OntologyClass thingClass;

	private OWLObjectProperty owlTopObjectProperty;
	private OntologyObjectProperty topObjectProperty;

	public OWLClass getOwlThingClass() {
		return owlThingClass;
	}

	public OntologyClass getThingClass() {
		return thingClass;
	}

	public OWLObjectProperty getOwlTopObjectProperty() {
		return owlTopObjectProperty;
	}

	public OntologyObjectProperty getTopObjectProperty() {
		return topObjectProperty;
	}

	/**
	 * Standard constructor.
	 */
	public OwlOntologyManagerMapHandler() {
		owlAPIMap = new HashMap<String, OWLObject>();
		internalAPIMap = new HashMap<String, OntologyItem>();
	}

	public void initializeOntology(OWLDataFactory owlDataFactory, OWLOntology owlOntology, Ontology ontology) {
		owlAPIMap.clear();
		internalAPIMap.clear();

		owlThingClass = owlDataFactory.getOWLThing();
		thingClass = OntologyManagerUtils.generateInternalClassObject(ontology, null,
				owlThingClass.getIRI().getIRIString());
		toMap(owlThingClass, thingClass);
		ontology.setClassHierarchy(thingClass);

		owlTopObjectProperty = owlDataFactory.getOWLTopObjectProperty();
		topObjectProperty = OntologyManagerUtils.generateInternalObjectPropertyObject(ontology, null,
				owlTopObjectProperty.getIRI().getIRIString());
		toMap(owlTopObjectProperty, topObjectProperty);
		ontology.setObjectPropertyHierarchy(topObjectProperty);

		internalAPIMap.put(owlOntology.getOntologyID().getOntologyIRI().toString(), ontology);
		owlAPIMap.put(owlOntology.getOntologyID().getOntologyIRI().toString(), owlOntology);
	}

	/**
	 * Internal helper method to retrieve an OWLOntology of the internal map.
	 * 
	 * @param o
	 *            The Ontology object, for which the appropriate OWL-object
	 *            should be found.
	 * @return The OWLOntology object, or null if it is not existing.z
	 */
	public Optional<OWLOntology> getOWLOntologyFromMap(Ontology o) {
		OWLObject owlObj = owlAPIMap.get(o);
		if (owlObj == null || !(owlObj instanceof OWLOntology))
			return Optional.empty();

		return Optional.of((OWLOntology) owlObj);
	}

	/**
	 * Internal helper method to retrieve an OWLClass of the internal map.
	 * 
	 * @param c
	 *            The OntologyClass object, for which the appropriate OWL-object
	 *            should be found.
	 * @return The OWLClass object, or null if it is not existing.
	 */
	public Optional<OWLClass> getOWLClass(final OntologyClass c) {
		OWLObject owlObj = owlAPIMap.get(c.getIri());
		if (owlObj == null || !(owlObj instanceof OWLClass))
			return Optional.empty();

		return Optional.of((OWLClass) owlObj);
	}

	/**
	 * Internal helper method to retrieve an OWLNamedIndividual of the internal
	 * map.
	 * 
	 * @param i
	 *            The OntologyIndividual object, for which the appropriate
	 *            OWL-object should be found.
	 * @return The OWLNamedIndividual object, or null if it is not existing.
	 */
	public Optional<OWLNamedIndividual> getOWLIndividual(OntologyIndividual i) {
		OWLObject owlObj = owlAPIMap.get(i.getIri());
		if (owlObj == null || !(owlObj instanceof OWLNamedIndividual))
			return Optional.empty();

		return Optional.of((OWLNamedIndividual) owlObj);
	}

	/**
	 * Internal helper method to retrieve an OWLEntity of the internal map.
	 * 
	 * @param i
	 *            The OntologyEntity object, for which the appropriate
	 *            OWL-object should be found.
	 * @return The OWLEntity object, or null if it is not existing.
	 */
	public Optional<OWLEntity> getOWLEntity(OntologyEntity i) {
		OWLObject owlObj = owlAPIMap.get(i.getIri());
		if (owlObj == null || !(owlObj instanceof OWLEntity))
			return Optional.empty();

		return Optional.of((OWLEntity) owlObj);
	}

	/**
	 * Internal helper method to retrieve an OWLObjectProperty of the internal
	 * map.
	 * 
	 * @param i
	 *            The OntologyObjectProperty object, for which the appropriate
	 *            OWL-object should be found.
	 * @return The OWLObjectProperty object, or null if it is not existing.
	 */
	public Optional<OWLObjectProperty> getOWLObjectProperty(OntologyObjectProperty i) {
		OWLObject owlObj = owlAPIMap.get(i.getIri());
		if (owlObj == null || !(owlObj instanceof OWLObjectProperty))
			return Optional.empty();

		return Optional.of((OWLObjectProperty) owlObj);
	}

	/**
	 * Internal helper method to retrieve an OWLAnnotationProperty of the
	 * internal map.
	 * 
	 * @param i
	 *            The OntologyAnnotationProperty object, for which the
	 *            appropriate OWL-object should be found.
	 * @return The OWLAnnotationProperty object, or null if it is not existing.
	 */
	public Optional<OWLAnnotationProperty> getOWLAnnotationProperty(OntologyEntity i) {
		OWLObject owlObj = owlAPIMap.get(i.getIri());
		if (owlObj == null || !(owlObj instanceof OWLAnnotationProperty))
			return Optional.empty();

		return Optional.of((OWLAnnotationProperty) owlObj);
	}

	/**
	 * Internal helper method to retrieve an OntologyClass of the internal map.
	 * 
	 * @param c
	 *            The OWLClass object, for which the appropriate OntologyItem
	 *            should be found.
	 * @param o
	 *            The containing ontology. Only necessary, if the
	 *            TopObjectProperty is to be received. If not, it can be null.
	 * @return The OntologyClass object, or null, if it is not existing.
	 */
	public Optional<OntologyClass> getInternalClass(final OWLClass c, final Ontology o) {
		if (c.getIRI().equals(owlThingClass.getIRI().getIRIString()))
			return Optional.of(o.getClassHierarchy());

		if (internalAPIMap.containsKey(c) && internalAPIMap.get(c) instanceof OntologyClass)
			return Optional.of((OntologyClass) internalAPIMap.get(c));

		return Optional.empty();
	}

	/**
	 * Internal helper method to retrieve an OntologyIndividual of the internal
	 * map.
	 * 
	 * @param i
	 *            The OWLNamedIndividual object, for which the appropriate
	 *            OntologyItem should be found.
	 * @return The OntologyIndividual object, or null, if it is not existing.
	 */
	public Optional<OntologyIndividual> getInternalIndividual(final OWLNamedIndividual i) {
		if (internalAPIMap.containsKey(i.getIRI().getIRIString())
				&& internalAPIMap.get(i.getIRI().getIRIString()) instanceof OntologyIndividual)
			return Optional.of((OntologyIndividual) internalAPIMap.get(i.getIRI().getIRIString()));

		return Optional.empty();
	}

	/**
	 * Internal helper method to retrieve an OntologyObjectProperty of the
	 * internal map.
	 * 
	 * @param p
	 *            The OWLObjectProperty object, for which the appropriate
	 *            OntologyItem should be found.
	 * @param o
	 *            The containing ontology. Only necessary, if the
	 *            TopObjectProperty is to be received. If not, it can be null.
	 * @return The OntologyObjectProperty object, or null, if it is not
	 *         existing.
	 */
	public Optional<OntologyObjectProperty> getInternalObjectProperty(final OWLObjectProperty p, final Ontology o) {
		if (p.getIRI().getIRIString().equals(owlTopObjectProperty.getIRI().getIRIString()))
			return Optional.of(o.getObjectPropertyHierarchy());

		if (internalAPIMap.containsKey(p.getIRI().getIRIString())
				&& internalAPIMap.get(p.getIRI().getIRIString()) instanceof OntologyObjectProperty)
			return Optional.of((OntologyObjectProperty) internalAPIMap.get(p.getIRI().getIRIString()));

		return Optional.empty();
	}

	/**
	 * Puts an OWLObject and an internal API-object in the internal maps.
	 * 
	 * @param apiObj
	 *            The OWL-API-object.
	 * @param internalObj
	 *            The internal API-object.
	 */
	public void toMap(OWLEntity apiObj, OntologyItem internalObj) {
		owlAPIMap.put(apiObj.getIRI().getIRIString(), apiObj);

		// The thing class and the top object property are not put in the
		// api-to-internal map, since there may exist more internal class/object
		// property objects for them:
		if (apiObj != owlThingClass && apiObj != owlTopObjectProperty)
			internalAPIMap.put(apiObj.getIRI().getIRIString(), internalObj);
	}

	/**
	 * Inserts all owl and oida api objects of another map handler in the own
	 * maps.
	 * 
	 * @param handler
	 *            Map handler, which api objects are inserted.
	 */
	public void importFromOtherOwlOntologyManager(OwlOntologyManagerMapHandler handler) {
		internalAPIMap.putAll(handler.internalAPIMap);
		owlAPIMap.putAll(handler.owlAPIMap);
	}
}
