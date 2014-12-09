/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;

import oida.ontology.model.OntologyModel;
import oida.ontology.util.OntologyHelper;

import org.eclipse.emf.common.util.URI;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.ontology.ProfileRegistry;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * This class handles the persistence of an ontology from and to OWL files.
 * 
 * @author Martin Glas
 * 
 */
public final class Owl {

	private Owl() {

	}

	/**
	 * This class saves a n OntologyModel into an OWL file.
	 * 
	 * 
	 * the source Ontology which has to be saved
	 * 
	 * @param fileURL
	 *            the name of the target File
	 */
	public static void saveToOwl(URL fileURL) {

		try {
			String fileURLString = fileURL.getFile();
			File ontologyFile = new File(fileURLString);
			FileOutputStream onologyFileOutputStream = new FileOutputStream(
					ontologyFile);
			OutputStreamWriter ontologyOutputStreamWriter = new OutputStreamWriter(
					onologyFileOutputStream, "UTF8");
			BufferedWriter ontologyBufferedWriter = new BufferedWriter(
					ontologyOutputStreamWriter);

			OntologyModel ontologymodel = OntologyModel.getInstance();
			ontologymodel.getOntologyModel().write(ontologyBufferedWriter);
			ontologyBufferedWriter.close();
		} catch (IOException e) {
			System.out.println("Writing Ontology to file failed");
		}

	}

	public static void saveToOwl(URL fileURL, Ontology baseOntology) {

		try {
			String fileURLString = fileURL.getFile();
			File ontologyFile = new File(fileURLString);
			FileOutputStream onologyFileOutputStream = new FileOutputStream(
					ontologyFile);
			OutputStreamWriter ontologyOutputStreamWriter = new OutputStreamWriter(
					onologyFileOutputStream, "UTF8");
			BufferedWriter ontologyBufferedWriter = new BufferedWriter(
					ontologyOutputStreamWriter);

			OntologyModel ontologymodel = OntologyModel.getInstance();
			ontologymodel.getOntologyModel().write(ontologyBufferedWriter,
					"RDF/XML", baseOntology.toString());
			ontologyBufferedWriter.close();
		} catch (IOException e) {
			System.out.println("Writing Ontology to file failed");
		}

	}

	public static void saveModelToOwl(URL fileURL, OntModel ontModel) {

		try {
			String fileURLString = fileURL.getFile();
			File ontologyFile = new File(fileURLString);
			FileOutputStream onologyFileOutputStream = new FileOutputStream(
					ontologyFile);
			OutputStreamWriter ontologyOutputStreamWriter = new OutputStreamWriter(
					onologyFileOutputStream, "UTF8");
			BufferedWriter ontologyBufferedWriter = new BufferedWriter(
					ontologyOutputStreamWriter);

			ontModel.write(ontologyBufferedWriter, "RDF/XML");
			ontologyBufferedWriter.close();
		} catch (IOException e) {
			System.out.println("Writing Ontology to file failed");
		}

	}

	/**
	 * This method loads an ontology from a specific file source into the
	 * OntologyModel singleton.
	 * 
	 * @param sourceOntologyURI
	 *            The URI of the loaded owl ontology
	 * @param sourceFileURL
	 *            The location of the File expressed in a URL
	 */
	public static Ontology loadFromOwl(URI sourceOntologyURI, URL sourceFileURL) {
		OntologyModel ontologyModel = OntologyModel.getInstance();
		ontologyModel.addOntology(sourceOntologyURI);
		ontologyModel
				.getOntologyModel()
				.getDocumentManager()
				.addAltEntry(sourceOntologyURI.toString(),
						sourceFileURL.toString());

		ontologyModel.getOntologyModel().read(sourceOntologyURI.toString());
		// ontologyModel.getOntologyModel().loadImports();

		for (Ontology ontology : ontologyModel.getOntologiesURIs()) {
			System.out.println("Successfully loaded " + ontology.getURI());
		}

		for (String importedOntologyURIString : ontologyModel
				.getOntologyModel().listImportedOntologyURIs()) {
			System.out.println("Successfully imported "
					+ importedOntologyURIString);
		}

		return ontologyModel.getOntology(sourceOntologyURI);

	}

	/**
	 * This method loads an ontology from a specific file source into the
	 * OntologyModel singleton.
	 * 
	 * @param sourceOntologyURI
	 *            The URI of the loaded owl ontology
	 * @param sourceFileURL
	 *            The location of the File expressed in a URL
	 */
	public static Ontology loadFromOwl(URI sourceOntologyURI,
			URL sourceFileURL, URI baseOntologyURI) {
		OntologyModel ontologyModel = OntologyModel.getInstance();
		ontologyModel.addOntology(sourceOntologyURI);
		ontologyModel
				.getOntologyModel()
				.getDocumentManager()
				.addAltEntry(sourceOntologyURI.toString(),
						sourceFileURL.toString());

		ontologyModel.getOntologyModel().read(sourceOntologyURI.toString(),
				baseOntologyURI.toString(), "RDF/XML-ABBREV");
		// ontologyModel.getOntologyModel().loadImports();

		for (Ontology ontology : ontologyModel.getOntologiesURIs()) {
			System.out.println("Successfully loaded " + ontology.getURI());
		}

		for (String importedOntologyURIString : ontologyModel
				.getOntologyModel().listImportedOntologyURIs()) {
			System.out.println("Successfully imported "
					+ importedOntologyURIString);
		}

		Ontology importedOntology = ontologyModel.getOntology(baseOntologyURI);

		return importedOntology;

	}

	public static Ontology importOntology(Ontology baseOntology,
			URI importOntologyURI, URL importOntologyURL) {

		OntModel importOntologyModel = ModelFactory
				.createOntologyModel(ProfileRegistry.OWL_DL_LANG);

		importOntologyModel.getDocumentManager().addAltEntry(
				importOntologyURI.toString(), importOntologyURL.toString());
		OntologyModel ontologyModel = OntologyModel.getInstance();
		ontologyModel.getOntologyModel().addSubModel(importOntologyModel);

		baseOntology.addImport(ontologyModel.getOntologyModel().createResource(
				importOntologyURI.toString()));

		ontologyModel.getOntologyModel().loadImports();

		Ontology importedOntology = ontologyModel
				.getOntology(importOntologyURI);

		return importedOntology;

	}

	public static OntModel loadFromOwlToOntModel(URI sourceOntologyURI,
			URL sourceFileURL, OntModel ontModel) {

		OntologyHelper.addMerologyFileEntry(ontModel);

		ontModel.getDocumentManager().addAltEntry(sourceOntologyURI.toString(),
				sourceFileURL.toString());

		ontModel.read(sourceOntologyURI.toString());

		return ontModel;

	}

	public static OntModel loadFromOwlToOntModelWithImport(
			URI sourceOntologyURI, URL sourceFileURL, OntModel subOntModel) {
		OntModel ontModel = ModelFactory
				.createOntologyModel(ProfileRegistry.OWL_DL_LANG);
		ontModel.addSubModel(subOntModel);

		ontModel.getDocumentManager().addAltEntry(sourceOntologyURI.toString(),
				sourceFileURL.toString());

		ontModel.read(sourceOntologyURI.toString());

		return ontModel;
	}
}
