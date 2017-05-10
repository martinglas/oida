/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge.model.changehandler;

import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bridgemodel.BridgemodelFactory;
import bridgemodel.ClassEqualsMapping;
import bridgemodel.MappingSet;
import bridgemodel.ObjectPropertyEqualsMapping;
import oida.bridge.model.ontology.OIDAModelBaseOntology;
import oida.bridge.model.strategy.IRenamerStrategy;
import oida.bridge.model.strategy.IStructuringStrategy;
import oida.ontology.OntologyAnnotation;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyClassEquivalence;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyIndividual;
import oida.ontology.OntologyObjectProperty;
import oida.ontology.OntologyObjectPropertyEquivalence;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.OntologyManagerException;

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

	private Object modelObject;

	private IRenamerStrategy renamerStrategy;
	private IStructuringStrategy structuringStrategy;

	private HashMap<Object, OntologyEntity> modelToOntologyMap = new HashMap<Object, OntologyEntity>();

	private MappingSet mappings = BridgemodelFactory.eINSTANCE.createMappingSet();

	protected void initialiyeCollections() {
		modelToOntologyMap.clear();
		mappings.getMappings().clear();
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

	public MappingSet getMappings() {
		return mappings;
	}

	@Override
	public void initializeChangeHandler(IRenamerStrategy renamerStrategy, IStructuringStrategy structuringStrategy, IOntologyManager metaModelOntology) {
		setRenamerStrategy(renamerStrategy);
		setStructuringStrategy(structuringStrategy);
		setMetaModelOntologyManager(metaModelOntology);
	}

	@Override
	public void startChangeTracking(Object modelObject, IOntologyManager modelOntologyManager) {
		setModelObject(modelObject);
		initialiyeCollections();

		try {
			modelOntologyManager.addImportDeclaration(getMetaModelOntologyManager().getOntology());
			setModelOntologyManager(modelOntologyManager);
			initializeModelOntology(modelOntologyManager);
			extractMappings(modelOntologyManager);

			try {
				getModelOntologyManager().saveOntology();
			} catch (OntologyManagerException e) {
				LOGGER.error("Error while saving model ontology for model '" + getModelObject().toString() + "'", e);
			}
		} catch (OntologyManagerException e) {
			LOGGER.error("Error while initializing model ontology for model '" + getModelObject().toString() + "'", e);
		}
	}

	protected abstract IOntologyManager initializeModelOntology(IOntologyManager modelOntologyManager);

	@Override
	public Optional<ClassEqualsMapping> establishClassMapping(OntologyClass class1, OntologyClass class2) {
		Optional<OntologyClassEquivalence> optEquivalence = getMetaModelOntologyManager().assignClassEquivalence(class1, class2);
		
		if (optEquivalence.isPresent())
			return Optional.of(createClassMapping(optEquivalence.get()));
		
		return Optional.empty();
	}
	
	@Override
	public Optional<ObjectPropertyEqualsMapping> establishObjectPropertyMapping(OntologyObjectProperty objectProperty1, OntologyObjectProperty objectProperty2) {
		Optional<OntologyObjectPropertyEquivalence> optEquivalence = getMetaModelOntologyManager().assignObjectPropertyEquivalence(objectProperty1, objectProperty2);
		
		if (optEquivalence.isPresent())
			return Optional.of(createObjectPropertyMapping(optEquivalence.get()));
		
		return Optional.empty();
	}
	
	protected void extractMappings(IOntologyManager modelOntologyManager) {
		// class equivalences
		Stream<OntologyClassEquivalence> equivalences = modelOntologyManager.getAllClassEquivalences();
		for (OntologyClassEquivalence equ : equivalences.collect(Collectors.toList())) {
			if (!equ.getClass1().getPrefix().equals(equ.getClass2().getPrefix()))
				createClassMapping(equ);
		}

		// object property equivalences
		Stream<OntologyObjectPropertyEquivalence> opEquivalences = modelOntologyManager.getAllObjectPropertyEquivalences();
		for (OntologyObjectPropertyEquivalence equ : opEquivalences.collect(Collectors.toList())) {
			if (!equ.getObjectProperty1().getPrefix().equals(equ.getObjectProperty2().getPrefix()))
				createObjectPropertyMapping(equ);
		}
	}

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
			OntologyObjectProperty oOP = modelOntologyManager.createObjectProperty(relationID, modelOntologyManager.getDefaultNamespace(), range);
			modelToOntologyMap.put(relationID, oOP);
			System.out.println("OIDA Model Change Handler: " + "Object Property created: '" + oOP.getName() + "'.");

			return oOP;
		} else
			return (OntologyObjectProperty)modelToOntologyMap.get(relationID);
	}
	
	protected ClassEqualsMapping createClassMapping(OntologyClassEquivalence equivalence) {
		ClassEqualsMapping mapping = BridgemodelFactory.eINSTANCE.createClassEqualsMapping();
		mapping.setClazz1(equivalence.getClass1());
		mapping.setClazz2(equivalence.getClass2());
		mappings.getMappings().add(mapping);
		
		equivalence.getClass1().setMappingExists(true);
		
		return mapping;
	}
	
	protected ObjectPropertyEqualsMapping createObjectPropertyMapping(OntologyObjectPropertyEquivalence equivalence) {
		ObjectPropertyEqualsMapping mapping = BridgemodelFactory.eINSTANCE.createObjectPropertyEqualsMapping();
		mapping.setObjectProperty1(equivalence.getObjectProperty1());
		mapping.setObjectProperty2(equivalence.getObjectProperty2());
		mappings.getMappings().add(mapping);
		
		equivalence.getObjectProperty1().setMappingExists(true);
		
		return mapping;
	}
}
