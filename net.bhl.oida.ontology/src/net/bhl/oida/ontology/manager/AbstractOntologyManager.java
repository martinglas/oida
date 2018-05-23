/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package net.bhl.oida.ontology.manager;

import java.util.Optional;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.util.EContentAdapter;

import net.bhl.oida.ontology.manager.context.IGlobalOntologyContext;
import net.bhl.oida.ontology.manager.util.OntologyManagerUtils;
import net.bhl.oida.ontology.model.ontologymgr.OntologyMetaInfo;
import net.bhl.oida.ontology.model.owlontology.Ontology;
import net.bhl.oida.ontology.model.owlontology.OntologyAnnotationProperty;
import net.bhl.oida.ontology.model.owlontology.OntologyClass;
import net.bhl.oida.ontology.model.owlontology.OntologyClassEquivalence;
import net.bhl.oida.ontology.model.owlontology.OntologyIndividual;
import net.bhl.oida.ontology.model.owlontology.OntologyObjectProperty;
import net.bhl.oida.ontology.model.owlontology.OntologyObjectPropertyEquivalence;
import net.bhl.oida.util.constants.StringConstants;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2016-11-23
 *
 */
public abstract class AbstractOntologyManager extends EContentAdapter implements IOntologyManager {
    private IGlobalOntologyContext globalContext;

    protected boolean localOntologyActive = true;

    @Override
    public void setGlobalOntologyContext(IGlobalOntologyContext context) {
	this.globalContext = context;
    }

    /**
     * Returns the global ontology context.
     * 
     * @return A global ontology context object, or null, if it is not set.
     */
    protected IGlobalOntologyContext getGlobalOntologyContext() {
	return this.globalContext;
    }
    
    private OntologyMetaInfo ontologyMetaInfo;

    private Ontology ontology;

    @Override
    public Ontology getOntology() {
	return ontology;
    }
    
    public OntologyMetaInfo getMetaInfo() {
	return ontologyMetaInfo;
    }
    
    public void setMetaInfo(OntologyMetaInfo metaInfo) {
	ontologyMetaInfo = metaInfo;
    }

    protected void setOntology(Ontology ontology, OntologyMetaInfo metaInfo) {
	this.ontology = ontology;
	this.ontology.eAdapters().add(this);
    }

    @Override
    public void addImportDeclaration(Ontology importOntology) throws OntologyManagerException {
	addImportDeclaration(importOntology.getIri());
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
    public OntologyIndividual createIndividual(final String name) {
	return createIndividual(name, StringConstants.EMPTY);
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
    public Optional<OntologyClass> getClass(final String iri) {
	return getOntology().getClasses().stream().filter(cl -> cl.getIri().equals(iri)).findFirst();
    }

    @Override
    public Optional<OntologyClass> getClass(final String name, final String namespace) {
	return getClass(OntologyManagerUtils.buildFullIRIString(name, namespace));
    }

    @Override
    public Stream<OntologyClass> getAllClasses() {
	return getOntology().getClasses().stream();
    }

    @Override
    public Optional<OntologyIndividual> getIndividual(final String iri) {
	return getOntology().getIndividuals().stream().filter(cl -> cl.getIri().equals(iri)).findFirst();
    }

    @Override
    public Optional<OntologyIndividual> getIndividual(final String name, final String namespace) {
	return getIndividual(OntologyManagerUtils.buildFullIRIString(name, namespace));
    }

    @Override
    public Optional<OntologyObjectProperty> getObjectProperty(final String name, final String namespace) {
	return getObjectProperty(OntologyManagerUtils.buildFullIRIString(name, namespace));
    }

    @Override
    public Optional<OntologyObjectProperty> getObjectProperty(final String iri) {
	return getOntology().getObjectProperties().stream().filter(op -> op.getIri().equals(iri)).findFirst();
    }

    @Override
    public Stream<OntologyIndividual> getAllIndividuals() {
	return getOntology().getIndividuals().stream();
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
    public OntologyObjectProperty createObjectProperty(String propertyName, OntologyClass domain) {
	return createObjectProperty(propertyName, StringConstants.EMPTY, domain);
    }

    @Override
    public OntologyObjectProperty createObjectProperty(String propertyName, OntologyClass domain, OntologyClass range) {
	return createObjectProperty(propertyName, StringConstants.EMPTY, domain, range);
    }

    @Override
    public OntologyObjectProperty createObjectProperty(String propertyName, String prefix, OntologyClass domain) {
	return createObjectProperty(propertyName, prefix, domain, null);
    }

    @Override
    public OntologyObjectProperty createObjectProperty(String propertyName, String prefix, OntologyClass domain, OntologyClass range) {
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

    @Override
    public Stream<OntologyClassEquivalence> getAllClassEquivalences() {
	return getOntology().getClassEquivalences().stream();
    }

    @Override
    public Stream<OntologyObjectPropertyEquivalence> getAllObjectPropertyEquivalences() {
	return getOntology().getObjectPropertyEquivalences().stream();
    }
}
