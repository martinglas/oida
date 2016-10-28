/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.evolution;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import oida.ontology.io.Owl;
import oida.ontology.util.OntologyHelper;

import org.eclipse.emf.common.util.URI;

import org.apache.jena.iri.IRI;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.ontology.ProfileRegistry;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.ResourceUtils;

public class RenameReference {
	private HashMap<URI, OntModel> sourceOntModels = new HashMap<URI, OntModel>();
	private OntModel referenceOntModel;
	private HashMap<URI, URL> newSourceOntologyURLs;
	private IRI referenceVersionIRI;
	private URL newReferenceOntologyURL;

	public RenameReference(URL referenceOntologyFileURL) {
		setReferenceOntModel(ModelFactory
				.createOntologyModel(ProfileRegistry.OWL_DL_LANG));
		Owl.loadFromOwlToOntModelWithImport(OntologyHelper
				.getOntologyURI(OntologyHelper.REFERENCE_ONTOLOGY_FILENAME),
				referenceOntologyFileURL, getReferenceOntModel());
	}

	private void setReferenceOntModel(OntModel referenceOntModel) {
		this.referenceOntModel = referenceOntModel;

	}

	public OntModel getReferenceOntModel() {
		return this.referenceOntModel;

	}

	public void setSourceOntModels(HashMap<URI, OntModel> sourceOntModels) {
		this.sourceOntModels = sourceOntModels;

	}

	public void setOldSourceOntologyURLs(HashMap<URI, URL> sourceOntologyHashMap) {

		for (Map.Entry<URI, URL> sourceOntologyEntry : sourceOntologyHashMap
				.entrySet()) {

			OntModel sourceOntModel = ModelFactory
					.createOntologyModel(ProfileRegistry.OWL_DL_LANG);
			Owl.loadFromOwlToOntModelWithImport(sourceOntologyEntry.getKey(),
					sourceOntologyEntry.getValue(), sourceOntModel);
			getSourceOntModels().put(sourceOntologyEntry.getKey(),
					sourceOntModel);
		}
	}

	private HashMap<URI, OntModel> getSourceOntModels() {

		return this.sourceOntModels;
	}

	public void renameOntResource(String oldLocalName, String newLocalName) {
		OntResource oldReferenceOntResource = getReferenceOntModel()
				.getOntResource(
						URI.createURI(
								OntologyHelper.DEFAULTONTOLOGY_URI
										+ OntologyHelper.REFERENCE_ONTOLOGY_FILENAME
										+ "#" + oldLocalName).toString());
		if (oldReferenceOntResource != null) {
			setReferenceOntologyVersionIRI(getReferenceOntModel(),
					getReferenceOntologyVersionIRI());
			ResourceUtils
					.renameResource(
							oldReferenceOntResource,
							URI.createURI(
									OntologyHelper.DEFAULTONTOLOGY_URI
											+ OntologyHelper.REFERENCE_ONTOLOGY_FILENAME
											+ "#" + newLocalName).toString()
									.toString());

		}

		for (Map.Entry<URI, OntModel> sourceOntologyEntry : getSourceOntModels()
				.entrySet()) {
			setReferenceOntologyVersionIRI(sourceOntologyEntry.getValue(),
					getReferenceOntologyVersionIRI());
			OntResource oldReferenceOntResourceInSource = sourceOntologyEntry
					.getValue()
					.getOntResource(
							URI.createURI(
									OntologyHelper.DEFAULTONTOLOGY_URI
											+ OntologyHelper.REFERENCE_ONTOLOGY_FILENAME
											+ "#" + oldLocalName).toString());
			if (oldReferenceOntResourceInSource != null) {
				ResourceUtils
						.renameResource(
								oldReferenceOntResourceInSource,
								URI.createURI(
										OntologyHelper.DEFAULTONTOLOGY_URI
												+ OntologyHelper.REFERENCE_ONTOLOGY_FILENAME
												+ "#" + newLocalName)
										.toString().toString());

			}
		}

	}

	private IRI getReferenceOntologyVersionIRI() {

		return this.referenceVersionIRI;
	}

	public OntModel getSourceOntModel(URI ontologyURI) {

		return getSourceOntModels().get(ontologyURI);
	}

	public void setNewSourceOntologyURLs(
			HashMap<URI, URL> newSourceOntologiesURLs) {
		this.newSourceOntologyURLs = newSourceOntologiesURLs;

	}

	public void saveSourceOntologies() {
		for (Map.Entry<URI, OntModel> sourceOntologyEntry : getSourceOntModels()
				.entrySet()) {
			Owl.saveModelToOwl(
					getNewSourceOntologyURL(sourceOntologyEntry.getKey()),
					sourceOntologyEntry.getValue());
		}

	}

	public void saveReferenceOntology() {
		Owl.saveModelToOwl(getNewReferenceOntologyURL(), getReferenceOntModel());
	}

	private URL getNewReferenceOntologyURL() {
		return this.newReferenceOntologyURL;
	}

	private URL getNewSourceOntologyURL(URI sourceOntologyURI) {

		return this.newSourceOntologyURLs.get(sourceOntologyURI);
	}

	public void setReferenceVersionIRI(IRI referenceVersionIRI) {
		this.referenceVersionIRI = referenceVersionIRI;

	}

	public void setReferenceOntologyVersionIRI(OntModel ontModel,
			IRI referenceOntologyVersionIRI) {
		ontModel.getOntology(
				OntologyHelper.getOntologyURI(
						OntologyHelper.REFERENCE_ONTOLOGY_FILENAME).toString())
				.setVersionInfo(referenceOntologyVersionIRI.toString());
	}

	public void setNewReferenceOntologyURL(URL newReferenceOntologyURL) {
		this.newReferenceOntologyURL = newReferenceOntologyURL;

	}

}
