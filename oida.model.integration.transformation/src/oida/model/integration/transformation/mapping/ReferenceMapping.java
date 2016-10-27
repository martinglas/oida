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
package oida.model.integration.transformation.mapping;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link oida.model.integration.transformation.mapping.ReferenceMapping#getSourceEObject <em>Source EObject</em>}</li>
 *   <li>{@link oida.model.integration.transformation.mapping.ReferenceMapping#getReferenceEObject <em>Reference EObject</em>}</li>
 *   <li>{@link oida.model.integration.transformation.mapping.ReferenceMapping#getEClassMapping <em>EClass Mapping</em>}</li>
 * </ul>
 *
 * @see oida.model.integration.transformation.mapping.MappingPackage#getReferenceMapping()
 * @model
 * @generated
 */
public interface ReferenceMapping extends EObject {
	/**
	 * Returns the value of the '<em><b>Source EObject</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source EObject</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source EObject</em>' reference.
	 * @see #setSourceEObject(EObject)
	 * @see oida.model.integration.transformation.mapping.MappingPackage#getReferenceMapping_SourceEObject()
	 * @model
	 * @generated
	 */
	EObject getSourceEObject();

	/**
	 * Sets the value of the '{@link oida.model.integration.transformation.mapping.ReferenceMapping#getSourceEObject <em>Source EObject</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source EObject</em>' reference.
	 * @see #getSourceEObject()
	 * @generated
	 */
	void setSourceEObject(EObject value);

	/**
	 * Returns the value of the '<em><b>Reference EObject</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference EObject</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference EObject</em>' reference.
	 * @see #setReferenceEObject(EObject)
	 * @see oida.model.integration.transformation.mapping.MappingPackage#getReferenceMapping_ReferenceEObject()
	 * @model
	 * @generated
	 */
	EObject getReferenceEObject();

	/**
	 * Sets the value of the '{@link oida.model.integration.transformation.mapping.ReferenceMapping#getReferenceEObject <em>Reference EObject</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference EObject</em>' reference.
	 * @see #getReferenceEObject()
	 * @generated
	 */
	void setReferenceEObject(EObject value);

	/**
	 * Returns the value of the '<em><b>EClass Mapping</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EClass Mapping</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EClass Mapping</em>' reference.
	 * @see #setEClassMapping(EClassMapping)
	 * @see oida.model.integration.transformation.mapping.MappingPackage#getReferenceMapping_EClassMapping()
	 * @model
	 * @generated
	 */
	EClassMapping getEClassMapping();

	/**
	 * Sets the value of the '{@link oida.model.integration.transformation.mapping.ReferenceMapping#getEClassMapping <em>EClass Mapping</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EClass Mapping</em>' reference.
	 * @see #getEClassMapping()
	 * @generated
	 */
	void setEClassMapping(EClassMapping value);

} // ReferenceMapping
