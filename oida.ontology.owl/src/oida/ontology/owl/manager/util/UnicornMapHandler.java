/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.owl.manager.util;

import java.util.Optional;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;

import oida.ontology.OntologyClass;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyIndividual;
import oida.ontology.OntologyObjectProperty;
import oida.ontology.OntologySubClassAssignment;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-05-10
 *
 */
public class UnicornMapHandler {
	private OwlOntologyManagerMapHandler mapHandler = new OwlOntologyManagerMapHandler();
	private OwlOntologyManagerMapHandler mapHandlerLocal = new OwlOntologyManagerMapHandler();
	
	public OwlOntologyManagerMapHandler getMapHandler() {
		return mapHandler;
	}
	
	public OwlOntologyManagerMapHandler getMapHandlerLocal() {
		return mapHandlerLocal;
	}
	
	
	
	public Optional<OntologyEntity> getTwinEntity(OntologyEntity e) {
		if (mapHandler.containsKey(e) && mapHandler.getOWLEntity(e).isPresent()) {
			OWLEntity owlEntity = mapHandler.getOWLEntity(e).get();
			return mapHandlerLocal.getInternalEntity(owlEntity);
		}

		if (mapHandlerLocal.containsKey(e) && mapHandlerLocal.getOWLEntity(e).isPresent()) {
			OWLEntity owlEntity = mapHandlerLocal.getOWLEntity(e).get();
			return mapHandler.getInternalEntity(owlEntity);
		}
		
		return Optional.empty();
	}
	
	public Optional<OntologyClass> getTwinClass(OntologyClass cl) {
		if (mapHandler.containsKey(cl) && mapHandler.getOWLClass(cl).isPresent()) {
			OWLClass owlClass = mapHandler.getOWLClass(cl).get();
			return mapHandlerLocal.getInternalClass(owlClass);
		}

		if (mapHandlerLocal.containsKey(cl) && mapHandlerLocal.getOWLClass(cl).isPresent()) {
			OWLClass owlClass = mapHandlerLocal.getOWLClass(cl).get();
			return mapHandler.getInternalClass(owlClass);
		}
		
		return Optional.empty();
	}
	
	public Optional<OntologyIndividual> getTwinIndividual(OntologyIndividual i) {
		if (mapHandler.containsKey(i) && mapHandler.getOWLIndividual(i).isPresent()) {
			OWLNamedIndividual owlIndividual = mapHandler.getOWLIndividual(i).get();
			return mapHandlerLocal.getInternalIndividual(owlIndividual);
		}

		if (mapHandlerLocal.containsKey(i) && mapHandlerLocal.getOWLIndividual(i).isPresent()) {
			OWLNamedIndividual owlIndividual = mapHandlerLocal.getOWLIndividual(i).get();
			return mapHandler.getInternalIndividual(owlIndividual);
		}
		
		return Optional.empty();
	}
	
	public Optional<OntologyObjectProperty> getTwinObjectProperty(OntologyObjectProperty i) {
		if (mapHandler.containsKey(i) && mapHandler.getOWLObjectProperty(i).isPresent()) {
			OWLObjectProperty owlObjectProperty = mapHandler.getOWLObjectProperty(i).get();
			return mapHandlerLocal.getInternalObjectProperty(owlObjectProperty);
		}

		if (mapHandlerLocal.containsKey(i) && mapHandlerLocal.getOWLObjectProperty(i).isPresent()) {
			OWLObjectProperty owlObjectProperty = mapHandlerLocal.getOWLObjectProperty(i).get();
			return mapHandler.getInternalObjectProperty(owlObjectProperty);
		}
		
		return Optional.empty();
	}
	
	public Optional<OntologySubClassAssignment> getTwinSubClassAssignment(OntologySubClassAssignment subClassAssignment) {
		if (mapHandler.containsKey(subClassAssignment) && mapHandler.getOWLSubClassOfAxiom(subClassAssignment).isPresent()) {
			OWLSubClassOfAxiom owlSubClassOfAxiom = mapHandler.getOWLSubClassOfAxiom(subClassAssignment).get();
			return mapHandlerLocal.getInternalSubClassOfAxiom(owlSubClassOfAxiom);
		}

		if (mapHandlerLocal.containsKey(subClassAssignment) && mapHandlerLocal.getOWLSubClassOfAxiom(subClassAssignment).isPresent()) {
			OWLSubClassOfAxiom owlSubClassOfAxiom = mapHandlerLocal.getOWLSubClassOfAxiom(subClassAssignment).get();
			return mapHandler.getInternalSubClassOfAxiom(owlSubClassOfAxiom);
		}
		
		return Optional.empty();
	}

	public void removeSubClassAssignment(OntologySubClassAssignment ontologySubClassAssignment) {
		Optional<OntologySubClassAssignment> twin = getTwinSubClassAssignment(ontologySubClassAssignment);
		
		if (mapHandler.containsKey(ontologySubClassAssignment))
			mapHandler.removeSubClassAssignment(ontologySubClassAssignment);
		else
			mapHandlerLocal.removeSubClassAssignment(ontologySubClassAssignment);
		
		if (twin.isPresent()) {
			if (mapHandler.containsKey(twin.get()))
				mapHandler.removeSubClassAssignment(twin.get());
			else
				mapHandlerLocal.removeSubClassAssignment(twin.get());
		}
	}
}
