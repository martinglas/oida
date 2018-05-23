package net.bhl.oida.bridge.recommender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.bhl.oida.bridge.Activator;
import net.bhl.oida.bridge.model.mapping.provider.MappingItemProviderAdapterFactory;
import net.bhl.oida.bridge.model.recommendation.AggregatedRecommendation;
import net.bhl.oida.bridge.model.recommendation.Recommendation;
import net.bhl.oida.bridge.model.recommendation.RecommendationFactory;
import net.bhl.oida.bridge.model.recommendation.RecommendationSet;
import net.bhl.oida.bridge.model.recommendation.provider.RecommendationItemProviderAdapterFactory;
import net.bhl.oida.bridge.service.OIDABridge;
import net.bhl.oida.ontology.model.owlontology.Ontology;
import net.bhl.oida.ontology.model.owlontology.OntologyClass;
import net.bhl.oida.ontology.model.owlontology.OntologyDatatypeProperty;
import net.bhl.oida.ontology.model.owlontology.OntologyEntity;
import net.bhl.oida.ontology.model.owlontology.OntologyIndividual;
import net.bhl.oida.ontology.model.owlontology.OntologyObjectProperty;
import net.bhl.oida.util.ExtensionPointUtil;

/**
 * 
 * @author Michael Shamiyeh
 * @since 25.04.2018
 *
 */
public final class RecommenderSystem {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecommenderSystem.class);

    private static RecommenderSystem INSTANCE;

    public static RecommenderSystem getInstance() {
	if (INSTANCE == null)
	    INSTANCE = new RecommenderSystem();

	return INSTANCE;
    }

    private RecommenderSystem() {
	ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(new MappingItemProviderAdapterFactory());
	composedAdapterFactory.addAdapterFactory(new RecommendationItemProviderAdapterFactory());
	composedAdapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());

	AdapterFactoryEditingDomain editingDomain = new AdapterFactoryEditingDomain(composedAdapterFactory, new BasicCommandStack());

	currentPrimaryRecommendationsResource = editingDomain.createResource("http://de.oida/bridge/currentprimaryrecommendations");
	currentPrimaryRecommendationsResource.getContents().add(RecommendationFactory.eINSTANCE.createRecommendationSet());

	currentSecondaryRecommendationsResource = editingDomain.createResource("http://de.oida/bridge/currentsecondaryrecommendations");
	currentSecondaryRecommendationsResource.getContents().add(RecommendationFactory.eINSTANCE.createRecommendationSet());
    }

    private OIDABridge oidaBridge;

    private List<IPrimaryRecommender> primaryRecommenderList;

    private List<IClassRecommender> recommenderSecondaryClass;
    private List<IObjectPropertyRecommender> recommenderSecondaryObjectProperty;
    private List<IDatatypePropertyRecommender> recommenderSecondaryDatatypeProperty;

    private HashMap<IPrimaryRecommender, Resource> recommenderResources = new HashMap<IPrimaryRecommender, Resource>();
    private Resource currentPrimaryRecommendationsResource;

    public Resource getCurrentPrimaryRecommendationsResource() {
	return currentPrimaryRecommendationsResource;
    }

    private Resource currentSecondaryRecommendationsResource;

    public Resource getCurrentSecondaryRecommendationsResource() {
	return currentSecondaryRecommendationsResource;
    }

    public void initializeRecommenderSystem(OIDABridge bridge) {
	oidaBridge = bridge;

	LOGGER.info("Initializing OIDA Bridge Recommender System...");
	LOGGER.info("Step 1/4: Evaluating Primary Recommender extensions.");
	try {
	    primaryRecommenderList = ExtensionPointUtil.loadExtensions(Activator.getExtensionRegistry(), IPrimaryRecommender.class, Activator.OIDA_RECOMMENDER_PRIMARY_EXTENSIONPOINT_ID);

	    for (IPrimaryRecommender r : primaryRecommenderList)
		LOGGER.info("Primary Recommender registered: " + r.toString() + ".");
	} catch (CoreException e) {
	    LOGGER.error("Error while evaluating Primary Recommender extension point.", e);
	}

	LOGGER.info("Step 2/4: Evaluating Secondary Class-Recommender extensions.");
	try {
	    recommenderSecondaryClass = ExtensionPointUtil.loadExtensions(Activator.getExtensionRegistry(), IClassRecommender.class, Activator.OIDA_RECOMMENDER_SECONDARY_CLASS_EXTENSIONPOINT_ID);

	    for (IRecommender r : recommenderSecondaryClass)
		LOGGER.info("Secondary Class-Recommender registered: " + r.toString() + ".");
	} catch (CoreException e) {
	    LOGGER.error("Error while evaluating Secondary Class-Recommender extension point.", e);
	}

	LOGGER.info("Step 3/4: Evaluating Secondary ObjectProperty-Recommender extensions.");
	try {
	    recommenderSecondaryObjectProperty = ExtensionPointUtil.loadExtensions(Activator.getExtensionRegistry(), IObjectPropertyRecommender.class,
		    Activator.OIDA_RECOMMENDER_SECONDARY_OBJECTPROPERTY_EXTENSIONPOINT_ID);

	    for (IObjectPropertyRecommender r : recommenderSecondaryObjectProperty)
		LOGGER.info("Secondary ObjectProperty-Recommender registered: " + r.toString() + ".");
	} catch (CoreException e) {
	    LOGGER.error("Error while evaluating Secondary ObjectProperty-Recommender extension point.", e);
	}

	LOGGER.info("Step 4/4: Evaluating Secondary Datatype-Property-Recommender extensions.");
	try {
	    recommenderSecondaryDatatypeProperty = ExtensionPointUtil.loadExtensions(Activator.getExtensionRegistry(), IDatatypePropertyRecommender.class,
		    Activator.OIDA_RECOMMENDER_SECONDARY_DATATYPEPROPERTY_EXTENSIONPOINT_ID);

	    for (IDatatypePropertyRecommender r : recommenderSecondaryDatatypeProperty)
		LOGGER.info("Secondary Datatype-Property-Recommender registered: " + r.toString() + ".");
	} catch (CoreException e) {
	    LOGGER.error("Error while evaluating Secondary Datatype-Property-Recommender extension point.", e);
	}
    }

    public void initializePrimaryRecommenders(Ontology modelOntology, Ontology referenceOntology) {
	for (IPrimaryRecommender rec : primaryRecommenderList)
	    rec.initializeRecommenderForModel(modelOntology, referenceOntology);
    }
    
    public void initializeSecondaryRecommenders(Ontology metaModelOntology, Ontology referenceOntology) {
	for (IClassRecommender rec : recommenderSecondaryClass)
	    rec.initializeRecommenderForMetaModel(metaModelOntology, referenceOntology);
	for (IObjectPropertyRecommender rec : recommenderSecondaryObjectProperty)
	    rec.initializeRecommenderForMetaModel(metaModelOntology, referenceOntology);
	for (IDatatypePropertyRecommender rec : recommenderSecondaryDatatypeProperty)
	    rec.initializeRecommenderForMetaModel(metaModelOntology, referenceOntology);
    }

    public void findPrimaryRecommendations(Object selectedModelObject, OntologyIndividual selectedIndividual) {
	RecommendationSet set = (RecommendationSet)currentPrimaryRecommendationsResource.getContents().get(0);
	set.getRecommendations().clear();
	set.setModelObject(selectedModelObject);
	set.setOntologyEntity(selectedIndividual);

	for (IPrimaryRecommender rec : primaryRecommenderList)
	    set.getRecommendations().addAll(rec.findRecommendationsForSelectedIndividual(selectedIndividual, oidaBridge));

	List<Recommendation> aggregatedRecommendations = new ArrayList<Recommendation>();
	while (!set.getRecommendations().isEmpty()) {
	    Recommendation r = set.getRecommendations().get(0);

	    Recommendation r2 = null;
	    for (int i = 1; i < set.getRecommendations().size(); i++) {
		if (r.getRecommendedEntity().equals(set.getRecommendations().get(i).getRecommendedEntity())) {
		    r2 = set.getRecommendations().get(i);
		    break;
		}
	    }

	    if (r2 != null) {
		AggregatedRecommendation ar = RecommendationFactory.eINSTANCE.createAggregatedRecommendation();
		ar.getRecommendations().add(r);
		ar.getRecommendations().add(r2);
		ar.setRecommenderName(r.getRecommenderName() + ", " + r2.getRecommenderName());
		ar.setRecommenderMessage(r.getRecommenderMessage() + " AND " + r2.getRecommenderMessage());
		ar.setRecommendedEntity(r.getRecommendedEntity());
		ar.setReliability(r.getReliability() + r2.getReliability());

		set.getRecommendations().remove(r);
		set.getRecommendations().remove(r2);
		aggregatedRecommendations.add(ar);
	    } else {
		set.getRecommendations().remove(r);
		aggregatedRecommendations.add(r);
	    }
	}

	set.getRecommendations().addAll(aggregatedRecommendations);
    }

    public void findSecondaryRecommendations(Object selectedObject) {
	RecommendationSet set = (RecommendationSet)currentSecondaryRecommendationsResource.getContents().get(0);
	set.getRecommendations().clear();
	set.setOntologyEntity((OntologyEntity)selectedObject);

	if (selectedObject instanceof OntologyClass) {
	    for (IClassRecommender rec : recommenderSecondaryClass)
		set.getRecommendations().addAll(rec.findRecommendationsForSelectedClass((OntologyClass)set.getOntologyEntity(), oidaBridge));
	}

	if (selectedObject instanceof OntologyObjectProperty) {
	    for (IObjectPropertyRecommender rec : recommenderSecondaryObjectProperty)
		set.getRecommendations().addAll(rec.findRecommendationsForSelectedObjectProperty((OntologyObjectProperty)set.getOntologyEntity(), oidaBridge));
	}

	if (selectedObject instanceof OntologyDatatypeProperty) {
	    for (IDatatypePropertyRecommender rec : recommenderSecondaryDatatypeProperty)
		set.getRecommendations().addAll(rec.findRecommendationsForSelectedDatatypeProperty((OntologyDatatypeProperty)set.getOntologyEntity(), oidaBridge));
	}

	List<Recommendation> aggregated = new ArrayList<Recommendation>();
	for (Recommendation r : set.getRecommendations()) {
	    boolean added = false;
	    for (Recommendation r2 : set.getRecommendations()) {
		if (r != r2 && r.getRecommendedEntity().equals(r2.getRecommendedEntity())) {
		    AggregatedRecommendation ar = RecommendationFactory.eINSTANCE.createAggregatedRecommendation();
		    ar.getRecommendations().add(r);
		    ar.getRecommendations().add(r2);

		    ar.setRecommendedEntity(r.getRecommendedEntity());
		    ar.setReliability(r.getReliability() + r2.getReliability());

		    aggregated.add(ar);
		    added = true;
		    break;
		}
	    }

	    if (!added)
		aggregated.add(r);
	}

	set.getRecommendations().addAll(aggregated);
    }
}
