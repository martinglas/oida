/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package oida.ontology.matcher.impl;

import oida.ontology.matcher.ManualMatch;
import oida.ontology.matcher.MatchRecommendation;
import oida.ontology.matcher.MatcherFactory;
import oida.ontology.matcher.MatcherPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import com.hp.hpl.jena.ontology.OntResource;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class MatcherPackageImpl extends EPackageImpl implements MatcherPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass matchRecommendationEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass manualMatchEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType ontResourceEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
	 * package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory
	 * method {@link #init init()}, which also performs initialization of the
	 * package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see oida.ontology.matcher.MatcherPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private MatcherPackageImpl() {
		super(eNS_URI, MatcherFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model,
	 * and for any others upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link MatcherPackage#eINSTANCE} when
	 * that field is accessed. Clients should not invoke it directly. Instead,
	 * they should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static MatcherPackage init() {
		if (isInited)
			return (MatcherPackage) EPackage.Registry.INSTANCE
					.getEPackage(MatcherPackage.eNS_URI);

		// Obtain or create and register package
		MatcherPackageImpl theMatcherPackage = (MatcherPackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof MatcherPackageImpl ? EPackage.Registry.INSTANCE
				.get(eNS_URI) : new MatcherPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theMatcherPackage.createPackageContents();

		// Initialize created meta-data
		theMatcherPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theMatcherPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(MatcherPackage.eNS_URI,
				theMatcherPackage);
		return theMatcherPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getMatchRecommendation() {
		return matchRecommendationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getMatchRecommendation_Source() {
		return (EAttribute) matchRecommendationEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getMatchRecommendation_Target() {
		return (EAttribute) matchRecommendationEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getManualMatch() {
		return manualMatchEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getOntResource() {
		return ontResourceEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MatcherFactory getMatcherFactory() {
		return (MatcherFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package. This method is guarded to
	 * have no affect on any invocation but its first. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		matchRecommendationEClass = createEClass(MATCH_RECOMMENDATION);
		createEAttribute(matchRecommendationEClass,
				MATCH_RECOMMENDATION__SOURCE);
		createEAttribute(matchRecommendationEClass,
				MATCH_RECOMMENDATION__TARGET);

		manualMatchEClass = createEClass(MANUAL_MATCH);

		// Create data types
		ontResourceEDataType = createEDataType(ONT_RESOURCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This
	 * method is guarded to have no affect on any invocation but its first. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		manualMatchEClass.getESuperTypes().add(this.getMatchRecommendation());

		// Initialize classes and features; add operations and parameters
		initEClass(matchRecommendationEClass, MatchRecommendation.class,
				"MatchRecommendation", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMatchRecommendation_Source(), this.getOntResource(),
				"source", null, 0, 1, MatchRecommendation.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getMatchRecommendation_Target(), this.getOntResource(),
				"target", null, 0, 1, MatchRecommendation.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(manualMatchEClass, ManualMatch.class, "ManualMatch",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize data types
		initEDataType(ontResourceEDataType, OntResource.class, "OntResource",
				IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} // MatcherPackageImpl
