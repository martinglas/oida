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

import org.eclipse.emf.ecore.EObject;

import com.hp.hpl.jena.ontology.OntResource;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Match Recommendation</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link oida.ontology.matcher.MatchRecommendation#getSource <em>
 * Source</em>}</li>
 * <li>{@link oida.ontology.matcher.MatchRecommendation#getTarget <em>
 * Target</em>}</li>
 * </ul>
 * </p>
 * 
 * @see oida.ontology.matcher.MatcherPackage#getMatchRecommendation()
 * @model
 * @generated
 */
public interface MatchRecommendation extends EObject {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Source</em>' attribute.
	 * @see #setSource(OntResource)
	 * @see oida.ontology.matcher.MatcherPackage#getMatchRecommendation_Source()
	 * @model dataType="net.bhl.cdt.ontology.matcher.OntResource"
	 * @generated
	 */
	OntResource getSource();

	/**
	 * Sets the value of the '
	 * {@link oida.ontology.matcher.MatchRecommendation#getSource
	 * <em>Source</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Source</em>' attribute.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(OntResource value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Target</em>' attribute.
	 * @see #setTarget(OntResource)
	 * @see oida.ontology.matcher.MatcherPackage#getMatchRecommendation_Target()
	 * @model dataType="net.bhl.cdt.ontology.matcher.OntResource"
	 * @generated
	 */
	OntResource getTarget();

	/**
	 * Sets the value of the '
	 * {@link oida.ontology.matcher.MatchRecommendation#getTarget
	 * <em>Target</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Target</em>' attribute.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(OntResource value);

} // MatchRecommendation
