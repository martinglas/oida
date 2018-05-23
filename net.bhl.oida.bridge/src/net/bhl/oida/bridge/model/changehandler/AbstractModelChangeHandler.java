/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package net.bhl.oida.bridge.model.changehandler;

import java.util.HashMap;
import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.bhl.oida.bridge.model.ontology.OIDAModelBaseOntology;
import net.bhl.oida.bridge.model.strategy.IRenamerStrategy;
import net.bhl.oida.bridge.model.strategy.IStructuringStrategy;
import net.bhl.oida.ontology.manager.IOntologyManager;
import net.bhl.oida.ontology.manager.OntologyManagerException;
import net.bhl.oida.ontology.model.owlontology.AOntologyItem;
import net.bhl.oida.ontology.model.owlontology.OntologyAnnotation;
import net.bhl.oida.ontology.model.owlontology.OntologyClass;
import net.bhl.oida.ontology.model.owlontology.OntologyEntity;
import net.bhl.oida.ontology.model.owlontology.OntologyIndividual;
import net.bhl.oida.ontology.model.owlontology.OntologyObjectProperty;
import net.bhl.oida.ontology.model.owlontology.OntologyObjectPropertyAssertion;

/**
 * 
 * @author Michael Shamiyeh, Martin Glas
 * @since 2017-01-20
 *
 */
public abstract class AbstractModelChangeHandler implements IModelChangeHandler {
    protected static Logger LOGGER = LoggerFactory.getLogger(AbstractModelChangeHandler.class);

    private IOntologyManager metaModelOntologyManager;
    private IOntologyManager modelOntologyManager;

    private IModelChangeHandler superModelChangeHandler;
    
    private Object modelObject;

    private IRenamerStrategy renamerStrategy;
    private IStructuringStrategy structuringStrategy;

    private HashMap<Object, AOntologyItem> modelToOntologyMap = new HashMap<Object, AOntologyItem>();

    protected void initializeCollections() {
	modelToOntologyMap.clear();
    }
    
    @Override
    public IModelChangeHandler getSuperModelChangeHandler() {
        return superModelChangeHandler;
    }
    
    @Override
    public void setSuperModelChangeHandler(IModelChangeHandler changeHandler) {
	superModelChangeHandler = changeHandler;
    }

    @Override
    public Optional<AOntologyItem> getOntologyEntityForModelElement(Object modelElement) {
	if (modelToOntologyMap.containsKey(modelElement))
	    return Optional.of(modelToOntologyMap.get(modelElement));
	else
	    if (getSuperModelChangeHandler() != null)
		return getSuperModelChangeHandler().getOntologyEntityForModelElement(modelElement);
	    else
		return Optional.empty();
    }

    @Override
    public Optional<OntologyClass> getOntologyClassForModelElement(Object modelElement) {
	if (modelToOntologyMap.containsKey(modelElement) && modelToOntologyMap.get(modelElement) instanceof OntologyClass)
	    return Optional.of((OntologyClass)modelToOntologyMap.get(modelElement));
	else
	    if (getSuperModelChangeHandler() != null)
		return getSuperModelChangeHandler().getOntologyClassForModelElement(modelElement);
	    else
		return Optional.empty();
    }

    @Override
    public Optional<OntologyIndividual> getOntologyIndividualForModelElement(Object modelElement) {
	if (modelToOntologyMap.containsKey(modelElement) && modelToOntologyMap.get(modelElement) instanceof OntologyIndividual)
	    return Optional.of((OntologyIndividual)modelToOntologyMap.get(modelElement));
	else
	    if (getSuperModelChangeHandler() != null)
		return getSuperModelChangeHandler().getOntologyIndividualForModelElement(modelElement);
	    else
		return Optional.empty();
    }

    @Override
    public Optional<OntologyObjectProperty> getOntologyObjectPropertyForModelElement(Object modelElement) {
	if (modelToOntologyMap.containsKey(modelElement) && modelToOntologyMap.get(modelElement) instanceof OntologyObjectProperty)
	    return Optional.of((OntologyObjectProperty)modelToOntologyMap.get(modelElement));
	else
	    if (getSuperModelChangeHandler() != null)
		return getSuperModelChangeHandler().getOntologyObjectPropertyForModelElement(modelElement);
	    else
		return Optional.empty();
    }

    public IOntologyManager getMetaModelOntologyManager() {
	return metaModelOntologyManager;
    }

    protected void setMetaModelOntologyManager(IOntologyManager ontologyManager) {
	this.metaModelOntologyManager = ontologyManager;
    }

    public IOntologyManager getModelOntologyManager() {
	return modelOntologyManager;
    }

    protected void setModelOntologyManager(IOntologyManager ontologyManager) {
	this.modelOntologyManager = ontologyManager;
    }

    public Object getModelObject() {
	return modelObject;
    }

    protected void setModelObject(Object modelObject) {
	this.modelObject = modelObject;
    }

    public IRenamerStrategy getRenamerStrategy() {
	return renamerStrategy;
    }

    public void setRenamerStrategy(IRenamerStrategy renamerStrategy) {
	this.renamerStrategy = renamerStrategy;
    }

    public IStructuringStrategy getStructuringStrategy() {
	return structuringStrategy;
    }

    public void setStructuringStrategy(IStructuringStrategy structuringStrategy) {
	this.structuringStrategy = structuringStrategy;
    }

    @Override
    public void startChangeTracking(IRenamerStrategy renamerStrategy, IStructuringStrategy structuringStrategy, IOntologyManager metaModelOntology, IOntologyManager modelOntologyManager,
	    Object modelObject) {
	setRenamerStrategy(renamerStrategy);
	setStructuringStrategy(structuringStrategy);
	setMetaModelOntologyManager(metaModelOntology);
	setModelOntologyManager(modelOntologyManager);
	setModelObject(modelObject);

	initializeCollections();

	try {
	    modelOntologyManager.addImportDeclaration(getMetaModelOntologyManager().getOntology());

	    initializeModelOntology(modelOntologyManager);

	    try {
		getModelOntologyManager().saveLocalOntology();
	    } catch (OntologyManagerException e) {
		LOGGER.error("Error while saving model ontology for model '" + getModelObject().toString() + "'", e);
	    }
	} catch (OntologyManagerException e) {
	    LOGGER.error("Error while initializing model ontology for model '" + getModelObject().toString() + "'", e);
	}
    }

    protected abstract IOntologyManager initializeModelOntology(IOntologyManager modelOntologyManager);

    protected OntologyIndividual createIndividualForModelObject(Object modelObject, OntologyClass ontologyClass) {
	if (!modelToOntologyMap.containsKey(modelObject)) {
	    String individualId = getRenamerStrategy().getObjectID(modelObject);
	    String individualName = getRenamerStrategy().getObjectName(modelObject);

	    OntologyIndividual oIn = modelOntologyManager.createIndividualOfClass(individualId, modelOntologyManager.getDefaultNamespace(), ontologyClass);
	    Optional<OntologyAnnotation> ontAnnotation = modelOntologyManager.annotateIndividual(oIn, OIDAModelBaseOntology.getInstance().getNameAnnotationProperty(), individualName);
	    modelToOntologyMap.put(modelObject, oIn);
	    System.out.println("OIDA Model Change Handler: Individual created: '" + oIn.getName() + "' (Annotated with: '" + ontAnnotation.get().getValue() + "').");
	    return oIn;
	} else
	    return (OntologyIndividual)modelToOntologyMap.get(modelObject);
    }

    protected void changeIndividualName(Object modelObject) {
	if (modelToOntologyMap.containsKey(modelObject)) {
	    String newIndividualID = getRenamerStrategy().getObjectID(modelObject);
	    modelOntologyManager.renameEntity((OntologyEntity)modelToOntologyMap.get(modelObject), newIndividualID);
	    System.out.println("OIDA Model Change Handler: Renamed individual: " + newIndividualID);
	}
    }
    
    protected void createObjectPropertyAssertionForStructuralFeature(String relationID, Object modelObject1, Object modelObject2) {
	if (modelToOntologyMap.containsKey(relationID)) {
	    OntologyObjectProperty oOP = (OntologyObjectProperty)modelToOntologyMap.get(relationID);

	    OntologyIndividual individual1 = (OntologyIndividual)modelToOntologyMap.get(modelObject1);
	    OntologyIndividual individual2 = (OntologyIndividual)modelToOntologyMap.get(modelObject2);
	    
	    OntologyObjectPropertyAssertion assertion  = modelOntologyManager.createObjectPropertyAssertion(oOP, individual1, individual2);
	    modelToOntologyMap.put(individual1.getName() + relationID + individual2.getName(), assertion);
	}
    }

    protected OntologyClass createOntologyClassForMetaModelClass(Object clazzObject) {
	if (!modelToOntologyMap.containsKey(clazzObject)) {
	    String className = getRenamerStrategy().getClassName((EClass)clazzObject);

	    OntologyClass oCl = modelOntologyManager.createClass(className, modelOntologyManager.getDefaultNamespace());
	    modelToOntologyMap.put(clazzObject, oCl);
	    System.out.println("OIDA Model Change Handler: " + "Class created: '" + oCl.getName() + "'.");

	    return oCl;
	} else
	    return (OntologyClass)modelToOntologyMap.get(clazzObject);
    }

    protected OntologyObjectProperty createOntologyObjectPropertyForMetaModelRelation(String relationID, OntologyClass domain) {
	if (!modelToOntologyMap.containsKey(relationID)) {
	    OntologyObjectProperty oOP = modelOntologyManager.createObjectProperty(relationID, modelOntologyManager.getDefaultNamespace(), domain);
	    modelToOntologyMap.put(relationID, oOP);
	    System.out.println("OIDA Model Change Handler: " + "Object Property created: '" + oOP.getName() + "'.");

	    return oOP;
	} else
	    return (OntologyObjectProperty)modelToOntologyMap.get(relationID);
    }
    
    protected void assignSystemOntologyObjectPropertyToFeature(EStructuralFeature feature, OntologyObjectProperty systemOntologyObjectProperty) {
	modelToOntologyMap.put(feature, systemOntologyObjectProperty);
    }
}
