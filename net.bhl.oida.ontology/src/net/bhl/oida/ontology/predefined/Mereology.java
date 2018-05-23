/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package net.bhl.oida.ontology.predefined;

import java.util.Optional;

import net.bhl.oida.ontology.manager.IOntologyManager;
import net.bhl.oida.ontology.manager.OntologyManagerException;
import net.bhl.oida.ontology.model.owlontology.Ontology;
import net.bhl.oida.ontology.model.owlontology.OntologyObjectProperty;
import net.bhl.oida.util.constants.FileConstants;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-03-22
 *
 */
public class Mereology extends AbstractPredefinedOntology {
    public static final String MEREOLOGY_FILENAME = "mereology" + FileConstants.OWL_FILE_POSTFIX;
    
    public static final String MEREOLOGY_IRI = "http://www.bauhaus-luftfahrt.net/ontologies/merology.owl";
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

    private static Mereology INSTANCE;
    public static Mereology getInstance() {
	if (INSTANCE == null)
	    INSTANCE = new Mereology();
	
	return INSTANCE;
    }
    
    private Mereology() {
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
