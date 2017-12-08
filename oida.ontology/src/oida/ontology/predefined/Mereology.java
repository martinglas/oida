/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.predefined;

import java.util.Optional;

import oida.ontology.Ontology;
import oida.ontology.OntologyObjectProperty;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.OntologyManagerException;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-03-22
 *
 */
public class Mereology extends AbstractPredefinedOntology {
	public static final String MEREOLOGY_NAMESPACE = "http://www.bauhaus-luftfahrt.net/ontologies/merology.owl";
	public static final String MEREOLOGY_PREFIX = "mer";
	
	public static final String MEREOLOGY_HASPART_NAME = "hasPart";
	public static final String MEREOLOGY_HASPART_DIRECTLY_NAME = "hasPart_directly";
	public static final String MEREOLOGY_PARTOF_NAME = "partOf";
	public static final String MEREOLOGY_PARTOF_DIRECTLY_NAME = "partOf_directly";

	private OntologyObjectProperty partOfObjectProperty;
	private OntologyObjectProperty partOfDirectlyObjectProperty;

	private OntologyObjectProperty hasPartObjectProperty;
	private OntologyObjectProperty hasPartDirectlyObjectProperty;
	
	public OntologyObjectProperty getPartOfObjectProperty() {
		return partOfObjectProperty;
	}

	public OntologyObjectProperty getPartOfDirectlyObjectProperty() {
		return partOfDirectlyObjectProperty;
	}

	public OntologyObjectProperty getHasPartObjectProperty() {
		return hasPartObjectProperty;
	}

	public OntologyObjectProperty getHasPartDirectlyObjectProperty() {
		return hasPartDirectlyObjectProperty;
	}

	@Override
	protected boolean checkPreDefinedOntology(Ontology ontology) {
		Optional<OntologyObjectProperty> hasPartOptional = ontology.getObjectProperties().stream().filter(p -> p.getName().equals(MEREOLOGY_HASPART_NAME)).findFirst();
		if (hasPartOptional.isPresent())
			hasPartObjectProperty = hasPartOptional.get();
		else
			return false;

		Optional<OntologyObjectProperty> hasPartDirectlyOptional = ontology.getObjectProperties().stream().filter(p -> p.getName().equals(MEREOLOGY_HASPART_DIRECTLY_NAME)).findFirst();
		if (hasPartDirectlyOptional.isPresent())
			hasPartDirectlyObjectProperty = hasPartDirectlyOptional.get();
		else
			return false;
		
		Optional<OntologyObjectProperty> partOfOptional = ontology.getObjectProperties().stream().filter(p -> p.getName().equals(MEREOLOGY_PARTOF_NAME)).findFirst();
		if (partOfOptional.isPresent())
			partOfObjectProperty = partOfOptional.get();
		else
			return false;
		
		Optional<OntologyObjectProperty> partOfDirectlyOptional = ontology.getObjectProperties().stream().filter(p -> p.getName().equals(MEREOLOGY_PARTOF_DIRECTLY_NAME)).findFirst();
		if (partOfDirectlyOptional.isPresent())
			partOfDirectlyObjectProperty = partOfDirectlyOptional.get();
		else
			return false;
		
		return true;
	}

	@Override
	protected void initializeOntology(IOntologyManager ontologyManager) throws OntologyManagerException {
		//ontologyManager.addNamespace(MEREOLOGY_PREFIX, MEREOLOGY_NAMESPACE, false);

		hasPartObjectProperty = ontologyManager.createObjectProperty(MEREOLOGY_HASPART_NAME, MEREOLOGY_PREFIX);
		ontologyManager.setObjectPropertyCharacteristics(hasPartObjectProperty, false, false, true, false, false, false, false);

		hasPartDirectlyObjectProperty = ontologyManager.createObjectProperty(MEREOLOGY_HASPART_DIRECTLY_NAME, MEREOLOGY_PREFIX);
		ontologyManager.assignSubObjectPropertyToSuperObjectProperty(hasPartDirectlyObjectProperty, hasPartObjectProperty);

		partOfObjectProperty = ontologyManager.createObjectProperty(MEREOLOGY_PARTOF_NAME, MEREOLOGY_PREFIX);
		ontologyManager.setObjectPropertyCharacteristics(partOfObjectProperty, false, false, true, false, false, false, false);
		ontologyManager.assignInverseObjectProperty(partOfObjectProperty, hasPartObjectProperty);

		partOfDirectlyObjectProperty = ontologyManager.createObjectProperty(MEREOLOGY_PARTOF_DIRECTLY_NAME, MEREOLOGY_PREFIX);
		ontologyManager.assignSubObjectPropertyToSuperObjectProperty(partOfDirectlyObjectProperty, partOfObjectProperty);
		ontologyManager.assignInverseObjectProperty(partOfDirectlyObjectProperty, hasPartDirectlyObjectProperty);

		ontologyManager.saveLocalOntology();
	}
}
