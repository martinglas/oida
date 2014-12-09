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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link oida.model.integration.transformation.mapping.AttributeMapping#getSourceEAttribute <em>Source EAttribute</em>}</li>
 *   <li>{@link oida.model.integration.transformation.mapping.AttributeMapping#getReferenceEAttribute <em>Reference EAttribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see oida.model.integration.transformation.mapping.MappingPackage#getAttributeMapping()
 * @model
 * @generated
 */
public interface AttributeMapping extends EObject {
	/**
	 * Returns the value of the '<em><b>Source EAttribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source EAttribute</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source EAttribute</em>' attribute.
	 * @see #setSourceEAttribute(EAttribute)
	 * @see oida.model.integration.transformation.mapping.MappingPackage#getAttributeMapping_SourceEAttribute()
	 * @model dataType="net.bhl.cdt.model.integration.transformation.mapping.EAttribute"
	 * @generated
	 */
	EAttribute getSourceEAttribute();

	/**
	 * Sets the value of the '{@link oida.model.integration.transformation.mapping.AttributeMapping#getSourceEAttribute <em>Source EAttribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source EAttribute</em>' attribute.
	 * @see #getSourceEAttribute()
	 * @generated
	 */
	void setSourceEAttribute(EAttribute value);

	/**
	 * Returns the value of the '<em><b>Reference EAttribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference EAttribute</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference EAttribute</em>' attribute.
	 * @see #setReferenceEAttribute(EAttribute)
	 * @see oida.model.integration.transformation.mapping.MappingPackage#getAttributeMapping_ReferenceEAttribute()
	 * @model dataType="net.bhl.cdt.model.integration.transformation.mapping.EAttribute"
	 * @generated
	 */
	EAttribute getReferenceEAttribute();

	/**
	 * Sets the value of the '{@link oida.model.integration.transformation.mapping.AttributeMapping#getReferenceEAttribute <em>Reference EAttribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference EAttribute</em>' attribute.
	 * @see #getReferenceEAttribute()
	 * @generated
	 */
	void setReferenceEAttribute(EAttribute value);

} // AttributeMapping
