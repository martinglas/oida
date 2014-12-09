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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see oida.ontology.matcher.MatcherPackage
 * @generated
 */
public interface MatcherFactory extends EFactory {
	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	MatcherFactory eINSTANCE = oida.ontology.matcher.impl.MatcherFactoryImpl
			.init();

	/**
	 * Returns a new object of class '<em>Match Recommendation</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Match Recommendation</em>'.
	 * @generated
	 */
	MatchRecommendation createMatchRecommendation();

	/**
	 * Returns a new object of class '<em>Manual Match</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Manual Match</em>'.
	 * @generated
	 */
	ManualMatch createManualMatch();

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	MatcherPackage getMatcherPackage();

} // MatcherFactory
