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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import oida.model.integration.transformation.mapping.EClassMapping;
import oida.model.integration.transformation.mapping.MappingPackage;
import oida.model.integration.transformation.mapping.ReferenceMapping;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reference Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link oida.model.integration.transformation.mapping.impl.ReferenceMappingImpl#getSourceEObject <em>Source EObject</em>}</li>
 *   <li>{@link oida.model.integration.transformation.mapping.impl.ReferenceMappingImpl#getReferenceEObject <em>Reference EObject</em>}</li>
 *   <li>{@link oida.model.integration.transformation.mapping.impl.ReferenceMappingImpl#getEClassMapping <em>EClass Mapping</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReferenceMappingImpl extends EObjectImpl implements ReferenceMapping {
	/**
	 * The cached value of the '{@link #getSourceEObject() <em>Source EObject</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceEObject()
	 * @generated
	 * @ordered
	 */
	protected EObject sourceEObject;

	/**
	 * The cached value of the '{@link #getReferenceEObject() <em>Reference EObject</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceEObject()
	 * @generated
	 * @ordered
	 */
	protected EObject referenceEObject;

	/**
	 * The cached value of the '{@link #getEClassMapping() <em>EClass Mapping</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEClassMapping()
	 * @generated
	 * @ordered
	 */
	protected EClassMapping eClassMapping;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferenceMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MappingPackage.Literals.REFERENCE_MAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getSourceEObject() {
		if (sourceEObject != null && sourceEObject.eIsProxy()) {
			InternalEObject oldSourceEObject = (InternalEObject)sourceEObject;
			sourceEObject = eResolveProxy(oldSourceEObject);
			if (sourceEObject != oldSourceEObject) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MappingPackage.REFERENCE_MAPPING__SOURCE_EOBJECT, oldSourceEObject, sourceEObject));
			}
		}
		return sourceEObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject basicGetSourceEObject() {
		return sourceEObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceEObject(EObject newSourceEObject) {
		EObject oldSourceEObject = sourceEObject;
		sourceEObject = newSourceEObject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.REFERENCE_MAPPING__SOURCE_EOBJECT, oldSourceEObject, sourceEObject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getReferenceEObject() {
		if (referenceEObject != null && referenceEObject.eIsProxy()) {
			InternalEObject oldReferenceEObject = (InternalEObject)referenceEObject;
			referenceEObject = eResolveProxy(oldReferenceEObject);
			if (referenceEObject != oldReferenceEObject) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MappingPackage.REFERENCE_MAPPING__REFERENCE_EOBJECT, oldReferenceEObject, referenceEObject));
			}
		}
		return referenceEObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject basicGetReferenceEObject() {
		return referenceEObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferenceEObject(EObject newReferenceEObject) {
		EObject oldReferenceEObject = referenceEObject;
		referenceEObject = newReferenceEObject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.REFERENCE_MAPPING__REFERENCE_EOBJECT, oldReferenceEObject, referenceEObject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClassMapping getEClassMapping() {
		if (eClassMapping != null && eClassMapping.eIsProxy()) {
			InternalEObject oldEClassMapping = (InternalEObject)eClassMapping;
			eClassMapping = (EClassMapping)eResolveProxy(oldEClassMapping);
			if (eClassMapping != oldEClassMapping) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MappingPackage.REFERENCE_MAPPING__ECLASS_MAPPING, oldEClassMapping, eClassMapping));
			}
		}
		return eClassMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClassMapping basicGetEClassMapping() {
		return eClassMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEClassMapping(EClassMapping newEClassMapping) {
		EClassMapping oldEClassMapping = eClassMapping;
		eClassMapping = newEClassMapping;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.REFERENCE_MAPPING__ECLASS_MAPPING, oldEClassMapping, eClassMapping));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MappingPackage.REFERENCE_MAPPING__SOURCE_EOBJECT:
				if (resolve) return getSourceEObject();
				return basicGetSourceEObject();
			case MappingPackage.REFERENCE_MAPPING__REFERENCE_EOBJECT:
				if (resolve) return getReferenceEObject();
				return basicGetReferenceEObject();
			case MappingPackage.REFERENCE_MAPPING__ECLASS_MAPPING:
				if (resolve) return getEClassMapping();
				return basicGetEClassMapping();
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
			case MappingPackage.REFERENCE_MAPPING__SOURCE_EOBJECT:
				setSourceEObject((EObject)newValue);
				return;
			case MappingPackage.REFERENCE_MAPPING__REFERENCE_EOBJECT:
				setReferenceEObject((EObject)newValue);
				return;
			case MappingPackage.REFERENCE_MAPPING__ECLASS_MAPPING:
				setEClassMapping((EClassMapping)newValue);
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
			case MappingPackage.REFERENCE_MAPPING__SOURCE_EOBJECT:
				setSourceEObject((EObject)null);
				return;
			case MappingPackage.REFERENCE_MAPPING__REFERENCE_EOBJECT:
				setReferenceEObject((EObject)null);
				return;
			case MappingPackage.REFERENCE_MAPPING__ECLASS_MAPPING:
				setEClassMapping((EClassMapping)null);
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
			case MappingPackage.REFERENCE_MAPPING__SOURCE_EOBJECT:
				return sourceEObject != null;
			case MappingPackage.REFERENCE_MAPPING__REFERENCE_EOBJECT:
				return referenceEObject != null;
			case MappingPackage.REFERENCE_MAPPING__ECLASS_MAPPING:
				return eClassMapping != null;
		}
		return super.eIsSet(featureID);
	}

} //ReferenceMappingImpl
