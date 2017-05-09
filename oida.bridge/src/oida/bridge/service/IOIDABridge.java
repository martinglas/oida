/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge.service;

import java.io.File;
import java.util.Optional;

import org.eclipse.emf.ecore.resource.Resource;

import bridgemodel.Recommendation;
import oida.bridge.model.changehandler.IModelChangeHandler;
import oida.ontology.manager.IOntologyManager;

/**
 * The OIDA bridge is the interface for model handling applications to OIDA.
 * 
 * @author Michael Shamiyeh
 * @since 2017-03-03
 *
 */
public interface IOIDABridge {
	public enum OntologyObjectProperties { HAS_PART, HAS_PARAMETER };
	
	/**
	 * Returns the Ontology Manager for the Meta-Model Ontology.
	 * 
	 * @return An Optional-Object of the Ontology Manager.
	 */
	Optional<IOntologyManager> getMetaModelOntologyManager();
	
	/**
	 * Starts the OIDA model ontology management for a passed model object.
	 * 
	 * @param modelObject
	 *            The model object, which should be observed and mapped to the
	 *            current reference ontology.
	 * @param modelOntologyDirectory
	 *            A directory, where OIDA can store the model ontology file.
	 * @param modelObjectId
	 *            An id of the model object, which is used for the file name
	 *            generation of the model ontology file.
	 * @throws OIDABridgeException
	 *             If 1) the model ontology directory is invalid, 2) no renamer
	 *             strategy has been registered, or 3) no reference ontology is
	 *             set at the moment. If the exception is thrown, the model
	 *             element is not observed.
	 */
	void invokeModelObservation(final Object modelObject, final File modelOntologyDirectory, final String modelObjectId) throws OIDABridgeException;

	/**
	 * Saves the model ontology in the current state, if the model object is
	 * observed.
	 * 
	 * @param modelObject
	 *            The model object, which's ontology should be saved.
	 */
	void saveModelOntology(final Object modelObject);

	/**
	 * Stops the model ontology management for the passed model object.
	 * 
	 * @param modelObject
	 *            The model object, which should not be observed any more.
	 */
	void stopModelObservation(final Object modelObject);

	/**
	 * Can be called by external editors or other parts, if the selected model
	 * element changes. This triggers the bridge to find new mapping
	 * recommendations.
	 * 
	 * @param modelObject The observed model.
	 * @param firstSelectedElement The first selected model element.
	 */
	void reportModelSelectionChanged(Object modelObject, Object firstSelectedElement);
	
	/**
	 * Provides ontology class recommendations for the currently selected model element.
	 * 
	 * @return Resource object containing recommendations.
	 */
	Resource getCurrentPrimaryRecommendationsResource();
	
	/**
	 * Provides ontology class or object property recommendations for the currently selected model element.
	 * 
	 * @return Resource object containing recommendations.
	 */
	Resource getCurrentSecondaryRecommendationsResource();
	
	/**
	 * Creates a primary mapping relation between the currently selected model element and a recommended reference ontology entity.
	 * 
	 * @param selectedRecommendation A Recommendation object, containing the selected reference ontology entity.
	 */
	void establishPrimaryMapping(Recommendation selectedRecommendation);
	
	/**
	 * Creates a secondary mapping relation between the currently selected model element and a recommended reference ontology entity.
	 * 
	 * @param selectedRecommendation A Recommendation object, containing the selected reference ontology entity.
	 */
	void establishSecondaryMapping(Recommendation selectedRecommendation);
	
	Optional<IModelChangeHandler> getModelChangeHandler(Object model);
}
