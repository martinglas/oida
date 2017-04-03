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

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-04-03
 *
 */
public final class OwlOntologyManagerMapHandler {
	private HashMap<OntologyItem, OWLObject> internal2apiMap;
	private HashMap<OWLObject, OntologyItem> api2internalMap;

	private OWLClass owlThingClass;
	private OntologyClass thingClass;

	private OWLObjectProperty owlTopObjectProperty;
	private OntologyObjectProperty topObjectProperty;

	public OWLClass getOwlThingClass() {
		return owlThingClass;
	}

	public OntologyClass getThingClass() {
		if (thingClass == null) {

		}

		return thingClass;
	}

	public OWLObjectProperty getOwlTopObjectProperty() {
		return owlTopObjectProperty;
	}

	public OntologyObjectProperty getTopObjectProperty() {
		if (topObjectProperty == null) {
		}

		return topObjectProperty;
	}

	/**
	 * Standard constructor.
	 */
	public OwlOntologyManagerMapHandler(OWLDataFactory owlDataFactory) {
		internal2apiMap = new HashMap<OntologyItem, OWLObject>();
		api2internalMap = new HashMap<OWLObject, OntologyItem>();

		owlThingClass = owlDataFactory.getOWLThing();
		owlTopObjectProperty = owlDataFactory.getOWLTopObjectProperty();
	}

	/**
	 * Internal helper method to retrieve an OWLOntology of the internal map.
	 * 
	 * @param o The Ontology object, for which the appropriate OWL-object should be found.
	 * @return The OWLOntology object, or null if it is not existing.z
	 */
	public Optional<OWLOntology> getOWLOntologyFromMap(Ontology o) {
		OWLObject owlObj = internal2apiMap.get(o);
		if (owlObj == null || !(owlObj instanceof OWLOntology))
			return Optional.empty();

		return Optional.of((OWLOntology)owlObj);
	}

	/**
	 * Internal helper method to retrieve an OWLClass of the internal map.
	 * 
	 * @param c The OntologyClass object, for which the appropriate OWL-object should be found.
	 * @return The OWLClass object, or null if it is not existing.
	 */
	public Optional<OWLClass> getOWLClass(OntologyClass c) {
		OWLObject owlObj = internal2apiMap.get(c);
		if (owlObj == null || !(owlObj instanceof OWLClass))
			return Optional.empty();

		return Optional.of((OWLClass)owlObj);
	}

	/**
	 * Internal helper method to retrieve an OWLNamedIndividual of the internal map.
	 * 
	 * @param i The OntologyIndividual object, for which the appropriate OWL-object should be found.
	 * @return The OWLNamedIndividual object, or null if it is not existing.
	 */
	public Optional<OWLNamedIndividual> getOWLIndividual(OntologyIndividual i) {
		OWLObject owlObj = internal2apiMap.get(i);
		if (owlObj == null || !(owlObj instanceof OWLNamedIndividual))
			return Optional.empty();

		return Optional.of((OWLNamedIndividual)owlObj);
	}

	/**
	 * Internal helper method to retrieve an OWLEntity of the internal map.
	 * 
	 * @param i The OntologyEntity object, for which the appropriate OWL-object should be found.
	 * @return The OWLEntity object, or null if it is not existing.
	 */
	public Optional<OWLEntity> getOWLEntity(OntologyEntity i) {
		OWLObject owlObj = internal2apiMap.get(i);
		if (owlObj == null || !(owlObj instanceof OWLEntity))
			return Optional.empty();

		return Optional.of((OWLEntity)owlObj);
	}

	/**
	 * Internal helper method to retrieve an OWLObjectProperty of the internal map.
	 * 
	 * @param i The OntologyObjectProperty object, for which the appropriate OWL-object should be found.
	 * @return The OWLObjectProperty object, or null if it is not existing.
	 */
	public Optional<OWLObjectProperty> getOWLObjectProperty(OntologyObjectProperty i) {
		OWLObject owlObj = internal2apiMap.get(i);
		if (owlObj == null || !(owlObj instanceof OWLObjectProperty))
			return Optional.empty();

		return Optional.of((OWLObjectProperty)owlObj);
	}

	/**
	 * Internal helper method to retrieve an OWLAnnotationProperty of the internal map.
	 * 
	 * @param i The OntologyAnnotationProperty object, for which the appropriate OWL-object should be found.
	 * @return The OWLAnnotationProperty object, or null if it is not existing.
	 */
	public Optional<OWLAnnotationProperty> getOWLAnnotationProperty(OntologyEntity i) {
		OWLObject owlObj = internal2apiMap.get(i);
		if (owlObj == null || !(owlObj instanceof OWLAnnotationProperty))
			return Optional.empty();

		return Optional.of((OWLAnnotationProperty)owlObj);
	}

	/**
	 * Internal helper method to retrieve an OntologyClass of the internal map.
	 * 
	 * @param c The OWLClass object, for which the appropriate OntologyItem should be found.
	 * @param o The containing ontology. Only necessary, if the TopObjectProperty is to be received. If not, it can be null.
	 * @return The OntologyClass object, or null, if it is not existing.
	 */
	public Optional<OntologyClass> getInternalClass(final OWLClass c, final Ontology o) {
		if (c.equals(owlThingClass))
			return Optional.of(o.getClassHierarchy());

		if (api2internalMap.containsKey(c) && api2internalMap.get(c) instanceof OntologyClass)
			return Optional.of((OntologyClass)api2internalMap.get(c));

		return Optional.empty();
	}

	/**
	 * Internal helper method to retrieve an OntologyIndividual of the internal map.
	 * 
	 * @param i The OWLNamedIndividual object, for which the appropriate OntologyItem should be found.
	 * @return The OntologyIndividual object, or null, if it is not existing.
	 */
	public Optional<OntologyIndividual> getInternalIndividual(final OWLNamedIndividual i) {
		if (api2internalMap.containsKey(i) && api2internalMap.get(i) instanceof OntologyIndividual)
			return Optional.of((OntologyIndividual)api2internalMap.get(i));
		
		return Optional.empty();
	}

	/**
	 * Internal helper method to retrieve an OntologyObjectProperty of the internal map.
	 * 
	 * @param p The OWLObjectProperty object, for which the appropriate OntologyItem should be found.
	 * @param o The containing ontology. Only necessary, if the TopObjectProperty is to be received. If not, it can be null.
	 * @return The OntologyObjectProperty object, or null, if it is not existing.
	 */
	public Optional<OntologyObjectProperty> getInternalObjectProperty(final OWLObjectProperty p, final Ontology o) {
		if (p.equals(owlTopObjectProperty))
			return Optional.of(o.getObjectPropertyHierarchy());

		if (api2internalMap.containsKey(p) && api2internalMap.get(p) instanceof OntologyObjectProperty)
			return Optional.of((OntologyObjectProperty)api2internalMap.get(p));

		return Optional.empty();
	}

	/**
	 * Puts an OWLObject and an internal API-object in the internal maps.
	 * 
	 * @param apiObj The OWL-API-object.
	 * @param internalObj The internal API-object.
	 */
	public void toMap(OWLObject apiObj, OntologyItem internalObj) {
		internal2apiMap.put(internalObj, apiObj);

		// The thing class and the top object property are not put in the api-to-internal map, since there may exist more internal class/object property objects for them:
		if (apiObj != owlThingClass && apiObj != owlTopObjectProperty)
			api2internalMap.put(apiObj, internalObj);
	}

	/**
	 * Inserts all owl and oida api objects of another map handler in the own maps.
	 * 
	 * @param handler Map handler, which api objects are inserted.
	 */
	public void importFromOtherOwlOntologyManager(OwlOntologyManagerMapHandler handler) {
		api2internalMap.putAll(handler.api2internalMap);
		internal2apiMap.putAll(handler.internal2apiMap);
	}
}
