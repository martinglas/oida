/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.manager;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oida.ontology.Ontology;
import oida.ontology.OntologyAnnotationProperty;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyIndividual;
import oida.ontology.OntologyObjectProperty;
import oida.ontology.OntologyPackage;
import oida.ontology.manager.context.IGlobalOntologyContext;
import oida.ontology.manager.util.OntologyManagerUtils;
import oida.ontologyMgr.OntologyFile;
import oida.util.constants.StringConstants;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2016-11-23
 *
 */
public abstract class AbstractOntologyManager extends EContentAdapter implements IOntologyManager {
	private static Logger LOGGER = LoggerFactory.getLogger(AbstractOntologyManager.class);
	
	private IGlobalOntologyContext globalContext;
	
	@Override
	public void setGlobalOntologyContext(IGlobalOntologyContext context) {
		this.globalContext = context;
	}
	
	/**
	 * Returns the global ontology context.
	 * @return A global ontology context object, or null, if it is not set.
	 */
	protected IGlobalOntologyContext getGlobalOntologyContext() {
		return this.globalContext;
	}
	
	private Ontology ontology;
	
	/**
	 * This is a getter returns the Ontology of the OntologyManager.
	 * 
	 * @return the Ontology of the OntologyManager
	 */
	public Ontology getOntology() {
		return ontology;
	}

	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getFeature().equals(OntologyPackage.eINSTANCE.getOntology_Individuals())) {
			ontology.setNrOfIndividuals(ontology.getIndividuals().size());
		}
		if (notification.getFeature().equals(OntologyPackage.eINSTANCE.getOntology_Classes())) {
			ontology.setNrOfClasses(ontology.getClasses().size());
		}
		if (notification.getFeature().equals(OntologyPackage.eINSTANCE.getOntology_ObjectProperties())) {
			ontology.setNrOfObjectProperties(ontology.getObjectProperties().size());
		}
	}

	protected void setOntology(Ontology ontology) {
		if (this.ontology != null) {
			this.ontology.eAdapters().remove(this);
		}

		this.ontology = ontology;
		this.ontology.eAdapters().add(this);
	}

	public void setOntologyFile(OntologyFile file) {
		if (this.ontology != null) {
			this.ontology.setOntologyFile(file);
		}
	}

	public OntologyFile getOntologyFile() {
		if (this.ontology != null) {
			return this.ontology.getOntologyFile();
		} else {
			return null;
		}
	}

	protected Optional<File> getOntologyFileObject(OntologyFile ontologyFile) {
		return getOntologyFileObject(ontologyFile, false);
	}

	protected Optional<File> getOntologyFileObject(OntologyFile ontologyFile, boolean createIfNotExisting) {
		if (ontologyFile.getPath() == null) {
			LOGGER.error("GetOntologyFile for '" + ontologyFile.toString() + "': Ontology file path is not set.");
			return Optional.empty();
		}

		if (ontologyFile.getFileName() == null) {
			LOGGER.error("GetOntologyFile for '" + ontologyFile.toString() + "': Ontology filename is not set.");
			return Optional.empty();
		}
		
		if (!ontologyFile.getPath().endsWith(StringConstants.BACKSLASH))
			ontologyFile.setPath(ontologyFile.getPath().concat(StringConstants.BACKSLASH));

		File file = new File(ontologyFile.getPath() + ontologyFile.getFileName());

		if (!file.exists()) {
			if (createIfNotExisting) {
				try {
					file.createNewFile();
					LOGGER.info("GetOntologyFile for '" + ontologyFile.toString() + "': Ontology file '" + file.getAbsolutePath() + "' has been created.");
					return Optional.of(file);
				} catch (IOException e) {
					LOGGER.error("GetOntologyFile for '" + ontologyFile.toString() + "': Error while creating file '" + file.getAbsolutePath() + "'.", e);
					return Optional.empty();
				}
			} else {
				LOGGER.warn("GetOntologyFile for '" + ontologyFile.toString() + "': Ontology file '" + file.getAbsolutePath() + "' doesn't exist and has NOT been created.");
				return Optional.empty();
			}
		} else {
			return Optional.of(file);
		}
	}

	@Override
	public void saveOntology(OntologyFile ontologyFile) throws OntologyManagerException {
		setOntologyFile(ontologyFile);
		saveOntology();
	}

	@Override
	public OntologyClass createClass(String name) {
		return createClass(name, StringConstants.EMPTY);
	}

	@Override
	public OntologyClass createSubClass(String name, OntologyClass superClass) {
		return createSubClass(name, StringConstants.EMPTY, superClass);
	}

	@Override
	public OntologyClass createSubClass(String name, String prefix, OntologyClass superClass) {
		OntologyClass subClass = createClass(name, prefix);
		assignSubClassToSuperClass(subClass, superClass);
		return subClass;
	}

	@Override
	public OntologyIndividual createIndividualOfClass(String individualName, String className) {
		return createIndividualOfClass(individualName, createClass(className));
	}

	@Override
	public OntologyIndividual createIndividualOfClass(String individualName, String individualPrefix, String className, String classPrefix) {
		return createIndividualOfClass(individualName, individualPrefix, createClass(className, classPrefix));
	}

	@Override
	public OntologyIndividual createIndividualOfClass(String individualName, OntologyClass clazz) {
		OntologyIndividual individual = createIndividual(individualName);
		assignIndividualToClass(individual, clazz);
		return individual;
	}

	@Override
	public OntologyIndividual createIndividualOfClass(String individualName, String individualPrefix, OntologyClass clazz) {
		OntologyIndividual individual = createIndividual(individualName, individualPrefix);
		assignIndividualToClass(individual, clazz);
		return individual;
	}

	@Override
	public OntologyClass getClass(final String name) {
		return getClass(name, StringConstants.EMPTY);
	}

	@Override
	public OntologyClass getClass(final String name, final String prefix) {
		Optional<OntologyClass> opt = ontology.getClasses().stream().filter(cl -> cl.getName().equals(name) && cl.getPrefix().equals(prefix)).findFirst();

		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}

	@Override
	public Stream<OntologyClass> getAllClasses() {
		return ontology.getClasses().stream();
	}

	@Override
	public OntologyIndividual getIndividual(final String name, final String prefix) {
		return getIndividual(OntologyManagerUtils.buildFullIRIString(name, prefix));
	}
	
	@Override
	public OntologyIndividual getIndividual(final String iri) {
		Optional<OntologyIndividual> opt = ontology.getIndividuals().stream().filter(cl -> cl.getIri().equals(iri)).findFirst();

		if (opt.isPresent())
			return opt.get();
		else
			return null;
	}

	@Override
	public Stream<OntologyIndividual> getAllIndividuals() {
		return ontology.getIndividuals().stream();
	}

	@Override
	public boolean isClassExisting(final String name) {
		return isClassExisting(name, StringConstants.EMPTY);
	}

	@Override
	public boolean isClassExisting(final String name, final String prefix) {
		return getClass(name, prefix) != null;
	}

	@Override
	public OntologyObjectProperty createObjectProperty(final String propertyName) {
		return createObjectProperty(propertyName, StringConstants.EMPTY);
	}

	@Override
	public OntologyObjectProperty createObjectProperty(String propertyName, OntologyClass range) {
		return createObjectProperty(propertyName, StringConstants.EMPTY, range);
	}

	@Override
	public OntologyObjectProperty createObjectProperty(String propertyName, OntologyClass range, OntologyClass domain) {
		return createObjectProperty(propertyName, StringConstants.EMPTY, range, domain);
	}

	@Override
	public OntologyObjectProperty createObjectProperty(String propertyName, String prefix, OntologyClass range) {
		return createObjectProperty(propertyName, prefix, range, null);
	}

	@Override
	public OntologyObjectProperty createObjectProperty(String propertyName, String prefix, OntologyClass range, OntologyClass domain) {
		OntologyObjectProperty property = createObjectProperty(propertyName, prefix);

		if (range != null)
			assignObjectPropertyRange(property, range);

		if (domain != null)
			assignObjectPropertyDomain(property, domain);

		return property;
	}

	@Override
	public void setObjectPropertyCharacteristics(OntologyObjectProperty property, boolean functional, boolean inverseFunctional, boolean transitive, boolean symmetric, boolean asymmetric,
			boolean reflexive, boolean irreflexive) {
		if (functional)
			makeObjectPropertyFunctional(property);

		if (inverseFunctional)
			makeObjectPropertyInverseFunctional(property);

		if (transitive)
			makeObjectPropertyTransitive(property);

		if (symmetric)
			makeObjectPropertySymmetric(property);

		if (asymmetric)
			makeObjectPropertyAsymmetric(property);

		if (reflexive)
			makeObjectPropertyReflexive(property);

		if (irreflexive)
			makeObjectPropertyIrreflexive(property);
	}

	@Override
	public OntologyAnnotationProperty createAnnotationProperty(String propertyName) {
		return createAnnotationProperty(propertyName, StringConstants.EMPTY);
	}
}
