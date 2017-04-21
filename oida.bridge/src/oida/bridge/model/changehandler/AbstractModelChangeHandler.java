/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge.model.changehandler;

import java.util.HashMap;
import java.util.Optional;

import org.eclipse.emf.ecore.EClass;

import oida.bridge.model.ontology.OIDAModelBaseOntology;
import oida.bridge.model.strategy.IRenamerStrategy;
import oida.bridge.model.strategy.IStructuringStrategy;
import oida.ontology.OntologyAnnotation;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyIndividual;
import oida.ontology.OntologyObjectProperty;
import oida.ontology.manager.IOntologyManager;

/**
 * 
 * @author Michael Shamiyeh, Martin Glas
 * @since 2017-01-20
 *
 */
public abstract class AbstractModelChangeHandler implements IModelChangeHandler {
	private IOntologyManager modelOntologyManager;

	private Object modelObject;

	private IRenamerStrategy renamerStrategy;
	private IStructuringStrategy structuringStrategy;

	private HashMap<Object, OntologyEntity> modelToOntologyMap = new HashMap<Object, OntologyEntity>();

	protected void clearOntologyEntityToModelElementMap() {
		modelToOntologyMap.clear();
	}
	
	@Override
	public Optional<OntologyEntity> getOntologyEntityForModelElement(Object modelElement) {
		if (modelToOntologyMap.containsKey(modelElement))
			return Optional.of(modelToOntologyMap.get(modelElement));
		else
			return Optional.empty();
	}
	
	protected Optional<OntologyClass> getOntologyClassForModelElement(Object modelElement) {
		if (modelToOntologyMap.containsKey(modelElement) && modelToOntologyMap.get(modelElement) instanceof OntologyClass)
			return Optional.of((OntologyClass)modelToOntologyMap.get(modelElement));
		else
			return Optional.empty();
	}
	
	protected Optional<OntologyIndividual> getOntologyIndividualForModelElement(Object modelElement) {
		if (modelToOntologyMap.containsKey(modelElement) && modelToOntologyMap.get(modelElement) instanceof OntologyIndividual)
			return Optional.of((OntologyIndividual)modelToOntologyMap.get(modelElement));
		else
			return Optional.empty();
	}
	
	protected Optional<OntologyObjectProperty> getOntologyObjectPropertyForModelElement(Object modelElement) {
		if (modelToOntologyMap.containsKey(modelElement) && modelToOntologyMap.get(modelElement) instanceof OntologyObjectProperty)
			return Optional.of((OntologyObjectProperty)modelToOntologyMap.get(modelElement));
		else
			return Optional.empty();
	}
	
	@Override
	public OIDAModelBaseOntology getModelBaseOntology() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setModelBaseOntology(OIDAModelBaseOntology baseOntology) {
		
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
	public void initializeChangeHandler(IRenamerStrategy renamerStrategy, IStructuringStrategy structuringStrategy) {
		setRenamerStrategy(renamerStrategy);
		setStructuringStrategy(structuringStrategy);
	}

	@Override
	public void startChangeTracking(Object modelObject, IOntologyManager modelOntologyManager) {
		setModelObject(modelObject);
		initializeModelOntology(modelOntologyManager);
	}

	protected abstract void initializeModelOntology(IOntologyManager modelOntologyManager);

	protected OntologyIndividual createIndividualForModelObject(Object modelObject, OntologyClass ontologyClass) {
		if (!modelToOntologyMap.containsKey(modelObject)) {
			String individualId = getRenamerStrategy().getObjectID(modelObject);
			String individualName = getRenamerStrategy().getObjectName(modelObject);

			OntologyIndividual oIn = modelOntologyManager.createIndividualOfClass(individualId, modelOntologyManager.getDefaultNamespace(), ontologyClass);
			Optional<OntologyAnnotation> ontAnnotation = modelOntologyManager.annotateIndividual(oIn, getModelBaseOntology().getNameAnnotationProperty(), individualName);
			modelToOntologyMap.put(modelObject, oIn);
			System.out.println("OIDA Model Change Handler: Individual created: '" + oIn.getName() + "' (Annotated with: '" + ontAnnotation.get().getValue() + "').");
			return oIn;
		} else
			return (OntologyIndividual)modelToOntologyMap.get(modelObject);
	}
	
	protected void changeIndividualName(Object modelObject) {
		if (modelToOntologyMap.containsKey(modelObject)) {
			String newIndividualID = getRenamerStrategy().getObjectID(modelObject);
			modelOntologyManager.renameEntity(modelToOntologyMap.get(modelObject), newIndividualID);
			System.out.println("OIDA Model Change Handler: Renamed individual: " + newIndividualID);
		}
	}
	
	protected void changeIndividualNameAnnotation() {
		
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
	
	protected OntologyObjectProperty createOntologyObjectPropertyForMetaModelRelation(String relationID, OntologyClass range) {
		if (!modelToOntologyMap.containsKey(relationID)) {
			OntologyObjectProperty oOP = modelOntologyManager.createObjectProperty(relationID, range);
			modelToOntologyMap.put(relationID, oOP);
			System.out.println("OIDA Model Change Handler: " + "Object Property created: '" + oOP.getName() + "'.");

			return oOP;
		} else
			return (OntologyObjectProperty)modelToOntologyMap.get(relationID);
	}
}
