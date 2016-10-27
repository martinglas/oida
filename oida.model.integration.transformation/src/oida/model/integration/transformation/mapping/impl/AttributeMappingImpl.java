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
package oida.model.integration.transformation.mapping.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import oida.model.integration.transformation.mapping.AttributeMapping;
import oida.model.integration.transformation.mapping.MappingPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link oida.model.integration.transformation.mapping.impl.AttributeMappingImpl#getSourceEAttribute <em>Source EAttribute</em>}</li>
 *   <li>{@link oida.model.integration.transformation.mapping.impl.AttributeMappingImpl#getReferenceEAttribute <em>Reference EAttribute</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AttributeMappingImpl extends EObjectImpl implements AttributeMapping {
	/**
	 * The default value of the '{@link #getSourceEAttribute() <em>Source EAttribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceEAttribute()
	 * @generated
	 * @ordered
	 */
	protected static final EAttribute SOURCE_EATTRIBUTE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourceEAttribute() <em>Source EAttribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceEAttribute()
	 * @generated
	 * @ordered
	 */
	protected EAttribute sourceEAttribute = SOURCE_EATTRIBUTE_EDEFAULT;

	/**
	 * The default value of the '{@link #getReferenceEAttribute() <em>Reference EAttribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceEAttribute()
	 * @generated
	 * @ordered
	 */
	protected static final EAttribute REFERENCE_EATTRIBUTE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReferenceEAttribute() <em>Reference EAttribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceEAttribute()
	 * @generated
	 * @ordered
	 */
	protected EAttribute referenceEAttribute = REFERENCE_EATTRIBUTE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AttributeMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MappingPackage.Literals.ATTRIBUTE_MAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSourceEAttribute() {
		return sourceEAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceEAttribute(EAttribute newSourceEAttribute) {
		EAttribute oldSourceEAttribute = sourceEAttribute;
		sourceEAttribute = newSourceEAttribute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.ATTRIBUTE_MAPPING__SOURCE_EATTRIBUTE, oldSourceEAttribute, sourceEAttribute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReferenceEAttribute() {
		return referenceEAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferenceEAttribute(EAttribute newReferenceEAttribute) {
		EAttribute oldReferenceEAttribute = referenceEAttribute;
		referenceEAttribute = newReferenceEAttribute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.ATTRIBUTE_MAPPING__REFERENCE_EATTRIBUTE, oldReferenceEAttribute, referenceEAttribute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MappingPackage.ATTRIBUTE_MAPPING__SOURCE_EATTRIBUTE:
				return getSourceEAttribute();
			case MappingPackage.ATTRIBUTE_MAPPING__REFERENCE_EATTRIBUTE:
				return getReferenceEAttribute();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MappingPackage.ATTRIBUTE_MAPPING__SOURCE_EATTRIBUTE:
				setSourceEAttribute((EAttribute)newValue);
				return;
			case MappingPackage.ATTRIBUTE_MAPPING__REFERENCE_EATTRIBUTE:
				setReferenceEAttribute((EAttribute)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case MappingPackage.ATTRIBUTE_MAPPING__SOURCE_EATTRIBUTE:
				setSourceEAttribute(SOURCE_EATTRIBUTE_EDEFAULT);
				return;
			case MappingPackage.ATTRIBUTE_MAPPING__REFERENCE_EATTRIBUTE:
				setReferenceEAttribute(REFERENCE_EATTRIBUTE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case MappingPackage.ATTRIBUTE_MAPPING__SOURCE_EATTRIBUTE:
				return SOURCE_EATTRIBUTE_EDEFAULT == null ? sourceEAttribute != null : !SOURCE_EATTRIBUTE_EDEFAULT.equals(sourceEAttribute);
			case MappingPackage.ATTRIBUTE_MAPPING__REFERENCE_EATTRIBUTE:
				return REFERENCE_EATTRIBUTE_EDEFAULT == null ? referenceEAttribute != null : !REFERENCE_EATTRIBUTE_EDEFAULT.equals(referenceEAttribute);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (sourceEAttribute: ");
		result.append(sourceEAttribute);
		result.append(", referenceEAttribute: ");
		result.append(referenceEAttribute);
		result.append(')');
		return result.toString();
	}

} //AttributeMappingImpl
