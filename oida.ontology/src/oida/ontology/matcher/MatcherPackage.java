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
package oida.ontology.matcher;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see net.bhl.cdt.ontology.matcher.MatchingFactory
 * @model kind="package"
 * @generated
 */
public interface MatcherPackage extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "matcher";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://bhl.net/cdt/ontology/matcher";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "net.bhl.cdt.ontology";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	MatcherPackage eINSTANCE = oida.ontology.matcher.impl.MatcherPackageImpl
			.init();

	/**
	 * The meta object id for the '
	 * {@link oida.ontology.matcher.impl.MatchRecommendationImpl
	 * <em>Match Recommendation</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see oida.ontology.matcher.impl.MatchRecommendationImpl
	 * @see oida.ontology.matcher.impl.MatcherPackageImpl#getMatchRecommendation()
	 * @generated
	 */
	int MATCH_RECOMMENDATION = 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATCH_RECOMMENDATION__SOURCE = 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATCH_RECOMMENDATION__TARGET = 1;

	/**
	 * The number of structural features of the '<em>Match Recommendation</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATCH_RECOMMENDATION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '
	 * {@link oida.ontology.matcher.impl.ManualMatchImpl
	 * <em>Manual Match</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see oida.ontology.matcher.impl.ManualMatchImpl
	 * @see oida.ontology.matcher.impl.MatcherPackageImpl#getManualMatch()
	 * @generated
	 */
	int MANUAL_MATCH = 1;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MANUAL_MATCH__SOURCE = MATCH_RECOMMENDATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MANUAL_MATCH__TARGET = MATCH_RECOMMENDATION__TARGET;

	/**
	 * The number of structural features of the '<em>Manual Match</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MANUAL_MATCH_FEATURE_COUNT = MATCH_RECOMMENDATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '<em>Ont Resource</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see com.hp.hpl.jena.ontology.OntResource
	 * @see oida.ontology.matcher.impl.MatcherPackageImpl#getOntResource()
	 * @generated
	 */
	int ONT_RESOURCE = 2;

	/**
	 * Returns the meta object for class '
	 * {@link oida.ontology.matcher.MatchRecommendation
	 * <em>Match Recommendation</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Match Recommendation</em>'.
	 * @see oida.ontology.matcher.MatchRecommendation
	 * @generated
	 */
	EClass getMatchRecommendation();

	/**
	 * Returns the meta object for the attribute '
	 * {@link oida.ontology.matcher.MatchRecommendation#getSource
	 * <em>Source</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Source</em>'.
	 * @see oida.ontology.matcher.MatchRecommendation#getSource()
	 * @see #getMatchRecommendation()
	 * @generated
	 */
	EAttribute getMatchRecommendation_Source();

	/**
	 * Returns the meta object for the attribute '
	 * {@link oida.ontology.matcher.MatchRecommendation#getTarget
	 * <em>Target</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Target</em>'.
	 * @see oida.ontology.matcher.MatchRecommendation#getTarget()
	 * @see #getMatchRecommendation()
	 * @generated
	 */
	EAttribute getMatchRecommendation_Target();

	/**
	 * Returns the meta object for class '
	 * {@link oida.ontology.matcher.ManualMatch <em>Manual Match</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Manual Match</em>'.
	 * @see oida.ontology.matcher.ManualMatch
	 * @generated
	 */
	EClass getManualMatch();

	/**
	 * Returns the meta object for data type '
	 * {@link com.hp.hpl.jena.ontology.OntResource <em>Ont Resource</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Ont Resource</em>'.
	 * @see com.hp.hpl.jena.ontology.OntResource
	 * @model instanceClass="com.hp.hpl.jena.ontology.OntResource"
	 * @generated
	 */
	EDataType getOntResource();

	/**
	 * Returns the factory that creates the instances of the model. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MatcherFactory getMatcherFactory();

	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that
	 * represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '
		 * {@link oida.ontology.matcher.impl.MatchRecommendationImpl
		 * <em>Match Recommendation</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see oida.ontology.matcher.impl.MatchRecommendationImpl
		 * @see oida.ontology.matcher.impl.MatcherPackageImpl#getMatchRecommendation()
		 * @generated
		 */
		EClass MATCH_RECOMMENDATION = eINSTANCE.getMatchRecommendation();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATCH_RECOMMENDATION__SOURCE = eINSTANCE
				.getMatchRecommendation_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATCH_RECOMMENDATION__TARGET = eINSTANCE
				.getMatchRecommendation_Target();

		/**
		 * The meta object literal for the '
		 * {@link oida.ontology.matcher.impl.ManualMatchImpl
		 * <em>Manual Match</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see oida.ontology.matcher.impl.ManualMatchImpl
		 * @see oida.ontology.matcher.impl.MatcherPackageImpl#getManualMatch()
		 * @generated
		 */
		EClass MANUAL_MATCH = eINSTANCE.getManualMatch();

		/**
		 * The meta object literal for the '<em>Ont Resource</em>' data type.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see com.hp.hpl.jena.ontology.OntResource
		 * @see oida.ontology.matcher.impl.MatcherPackageImpl#getOntResource()
		 * @generated
		 */
		EDataType ONT_RESOURCE = eINSTANCE.getOntResource();

	}

} // MatcherPackage
