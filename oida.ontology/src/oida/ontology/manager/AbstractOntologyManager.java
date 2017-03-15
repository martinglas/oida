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

import oida.util.constants.StringConstants;
import oida.ontology.Ontology;
import oida.ontology.OntologyAnnotation;
import oida.ontology.OntologyAnnotationProperty;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyFactory;
import oida.ontology.OntologyIndividual;
import oida.ontology.OntologyNamespace;
import oida.ontology.OntologyObjectProperty;
import oida.ontology.OntologyObjectPropertyAssertion;
import oida.ontology.OntologyPackage;
import oida.ontologyMgr.OntologyFile;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2016-11-23
 *
 */
public abstract class AbstractOntologyManager extends EContentAdapter implements IOntologyManager {
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
		if (notification.getFeature().equals(OntologyPackage.eINSTANCE.getOntology_Objectproperties())) {
			ontology.setNrOfObjectProperties(ontology.getObjectproperties().size());
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

	protected File getOntologyFileObject(OntologyFile ontologyFile) {
		return getOntologyFileObject(ontologyFile, false);
	}

	protected File getOntologyFileObject(OntologyFile ontologyFile, boolean createIfNotExisting) {
		if (ontologyFile.getPath() == null) {
			System.out.println("OIDA Ontology Manager [getOntologyFile]: Ontology file path is not set.");
			return null;
		}

		if (ontologyFile.getFileName() == null) {
			System.out.println("OIDA Ontology Manager [getOntologyFile]: Ontology filename is not set.");
			return null;
		}

		File file = new File(ontologyFile.getPath() + ontologyFile.getFileName());

		if (!file.exists()) {
			if (createIfNotExisting) {
				try {
					file.createNewFile();
					System.out.println("OIDA Ontology Manager [getOntologyFile]: Ontology file '" + file.getAbsolutePath() + "' has been created.");
					return file;
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			} else {
				System.out.println("OIDA Ontology Manager [getOntologyFile]: Ontology file '" + file.getAbsolutePath() + "' doesn't exist and has NOT been created.");
				return null;
			}
		} else {
			return file;
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
	public OntologyIndividual getIndividual(String name) {
		return getIndividual(name, StringConstants.EMPTY);
	}

	@Override
	public OntologyIndividual getIndividual(String name, String prefix) {
		Optional<OntologyIndividual> opt = ontology.getIndividuals().stream().filter(cl -> cl.getName().equals(name) && cl.getPrefix().equals(prefix)).findFirst();

		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}

	@Override
	public Stream<OntologyIndividual> getAllIndividuals() {
		return ontology.getIndividuals().stream();
	}

	@Override
	public boolean isClassExisting(String name) {
		return isClassExisting(name, StringConstants.EMPTY);
	}

	@Override
	public boolean isClassExisting(String name, String prefix) {
		return getClass(name, prefix) != null;
	}

	@Override
	public OntologyObjectProperty createObjectProperty(String propertyName) {
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
		return createObjectProperty(propertyName, StringConstants.EMPTY, range, null);
	}

	@Override
	public OntologyObjectProperty createObjectProperty(String propertyName, String prefix, OntologyClass range, OntologyClass domain) {
		OntologyObjectProperty property = createObjectProperty(propertyName, prefix);

		if (range != null) {
			assignObjectPropertyRange(property, range);
		}

		if (domain != null) {
			assignObjectPropertyDomain(property, domain);
		}

		return property;
	}

	@Override
	public void setObjectPropertyCharacteristics(OntologyObjectProperty property, boolean functional, boolean inverseFunctional, boolean transitive, boolean symmetric, boolean asymmetric,
			boolean reflexive, boolean irreflexive) {
		if (functional) {
			makeObjectPropertyFunctional(property);
		}

		if (inverseFunctional) {
			makeObjectPropertyInverseFunctional(property);
		}

		if (transitive) {
			makeObjectPropertyTransitive(property);
		}

		if (symmetric) {
			makeObjectPropertySymmetric(property);
		}

		if (asymmetric) {
			makeObjectPropertyAsymmetric(property);
		}

		if (reflexive) {
			makeObjectPropertyReflexive(property);
		}

		if (irreflexive) {
			makeObjectPropertyIrreflexive(property);
		}
	}

	@Override
	public OntologyAnnotationProperty createAnnotationProperty(String propertyName) {
		return createAnnotationProperty(propertyName, StringConstants.EMPTY);
	}

	protected Ontology generateInternalOntologyObject(String name, long nrOfClasses, long nrOfIndividuals) {
		Ontology newOntology = OntologyFactory.eINSTANCE.createOntology();
		newOntology.setName(name);
		newOntology.setNrOfClasses(nrOfClasses);
		newOntology.setNrOfIndividuals(nrOfIndividuals);
		return newOntology;
	}

	protected OntologyNamespace generateInternalNamespaceObject(Ontology ontology, String prefix, String nsName) {
		OntologyNamespace newNS = OntologyFactory.eINSTANCE.createOntologyNamespace();
		setOntologyEntityData(newNS, ontology, nsName, prefix);
		ontology.getNamespaces().add(newNS);

		return newNS;
	}

	protected OntologyClass generateInternalClassObject(Ontology ontology, OntologyClass superClass, String prefix, String className) {
		OntologyClass newClass = OntologyFactory.eINSTANCE.createOntologyClass();
		setOntologyEntityData(newClass, ontology, className, prefix);
		ontology.getClasses().add(newClass);
		
		if (superClass != null) {
			superClass.getSubClasses().add(newClass);
			newClass.getSuperClasses().add(superClass);
		}

		return newClass;
	}

	protected OntologyIndividual generateInternalIndividualObject(Ontology ontology, String prefix, String individualName) {
		OntologyIndividual newIndividual = OntologyFactory.eINSTANCE.createOntologyIndividual();
		setOntologyEntityData(newIndividual, ontology, individualName, prefix);
		ontology.getIndividuals().add(newIndividual);

		return newIndividual;
	}

	protected OntologyObjectProperty generateInternalObjectPropertyObject(Ontology ontology, String prefix, String propertyName) {
		OntologyObjectProperty property = OntologyFactory.eINSTANCE.createOntologyObjectProperty();
		setOntologyEntityData(property, ontology, propertyName, prefix);

		return property;
	}

	protected OntologyAnnotationProperty generateInternalAnnotationPropertyObject(Ontology ontology, String prefix, String propertyName) {
		OntologyAnnotationProperty property = OntologyFactory.eINSTANCE.createOntologyAnnotationProperty();
		setOntologyEntityData(property, ontology, propertyName, prefix);

		return property;
	}

	protected OntologyAnnotation generateInternalAnnotationObject(Ontology ontology, OntologyAnnotationProperty property, String value) {
		OntologyAnnotation annotation = OntologyFactory.eINSTANCE.createOntologyAnnotation();
		annotation.setContainingOntology(ontology);
		annotation.setAnnotationproperty(property);
		annotation.setValue(value);

		return annotation;
	}
	
	protected OntologyObjectPropertyAssertion generateInternalObjectPropertyAssertionObject(Ontology ontology, OntologyObjectProperty objectProperty, OntologyIndividual individual) {
		OntologyObjectPropertyAssertion assertion = OntologyFactory.eINSTANCE.createOntologyObjectPropertyAssertion();
		assertion.setContainingOntology(ontology);
		assertion.setObjectProperty(objectProperty);
		assertion.setObject(individual);
		
		return assertion;
	}

	private void setOntologyEntityData(OntologyEntity entity, Ontology ontology, String name, String prefix) {
		entity.setContainingOntology(ontology);
		entity.setName(name);
		entity.setPrefix(prefix);
	}
}
