/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge.emf;

import java.util.Optional;

import oida.ontology.Ontology;
import oida.ontology.OntologyObjectProperty;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.OntologyManagerException;
import oida.ontology.predefined.AbstractPredefinedOntology;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-07
 *
 */
public class EMFModelOntology extends AbstractPredefinedOntology {
	public static final String EMFONTOLOGY_NAMESPACE = "http://www.bauhaus-luftfahrt.net/ontologies/emf.owl";
	public static final String EMFONTOLOGY_PREFIX = "emf";
	
	public static final String EMFONTOLOGY_REFERENCE_NAME = "emf_reference";
	public static final String EMFONTOLOGY_REFERENCE_BIDIR_NAME = "emf_reference_bidirectional";
	
	private OntologyObjectProperty emfReferenceObjectProperty;
	private OntologyObjectProperty emfReferenceBiDirectionalObjectProperty;

	public OntologyObjectProperty getEmfReferenceObjectProperty() {
		return emfReferenceObjectProperty;
	}
	
	public OntologyObjectProperty getEmfReferenceBiDirectionalObjectProperty() {
		return emfReferenceBiDirectionalObjectProperty;
	}

	@Override
	protected boolean checkPreDefinedOntology(Ontology ontology) {
		Optional<OntologyObjectProperty> emfReferenceOptional = ontology.getObjectProperties().stream().filter(p -> p.getName().equals(EMFONTOLOGY_REFERENCE_NAME)).findFirst();
		if (emfReferenceOptional.isPresent())
			emfReferenceObjectProperty = emfReferenceOptional.get();
		else
			return false;
		
		Optional<OntologyObjectProperty> emfReferenceBiDirectionalOptional = ontology.getObjectProperties().stream().filter(p -> p.getName().equals(EMFONTOLOGY_REFERENCE_BIDIR_NAME)).findFirst();
		if (emfReferenceBiDirectionalOptional.isPresent())
			emfReferenceBiDirectionalObjectProperty = emfReferenceBiDirectionalOptional.get();
		else
			return false;
		
		return true;
	}

	@Override
	protected void initializeOntology(IOntologyManager ontologyManager) throws OntologyManagerException {
		ontologyManager.addNamespace(EMFONTOLOGY_PREFIX, EMFONTOLOGY_NAMESPACE, false);

		emfReferenceObjectProperty = ontologyManager.createObjectProperty(EMFONTOLOGY_REFERENCE_NAME, EMFONTOLOGY_PREFIX);
		ontologyManager.setObjectPropertyCharacteristics(emfReferenceObjectProperty, false, false, false, false, false, false, false);

		emfReferenceBiDirectionalObjectProperty = ontologyManager.createObjectProperty(EMFONTOLOGY_REFERENCE_BIDIR_NAME, EMFONTOLOGY_PREFIX);
		ontologyManager.setObjectPropertyCharacteristics(emfReferenceBiDirectionalObjectProperty, false, false, false, true, false, false, false);

		ontologyManager.saveOntology();
	}
}
