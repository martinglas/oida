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
package oida.model.integration.matching.diffmodel.impl;

import oida.model.integration.matching.diffmodel.DiffmodelPackage;
import oida.model.integration.matching.diffmodel.ReferenceMatching;
import oida.model.integration.transformation.mapping.ReferenceMapping;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reference Matching</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link oida.model.integration.matching.diffmodel.impl.ReferenceMatchingImpl#getSourceReferenceMapping <em>Source Reference Mapping</em>}</li>
 *   <li>{@link oida.model.integration.matching.diffmodel.impl.ReferenceMatchingImpl#getTargetReferenceMapping <em>Target Reference Mapping</em>}</li>
 *   <li>{@link oida.model.integration.matching.diffmodel.impl.ReferenceMatchingImpl#isConfirmed <em>Confirmed</em>}</li>
 *   <li>{@link oida.model.integration.matching.diffmodel.impl.ReferenceMatchingImpl#isReported <em>Reported</em>}</li>
 *   <li>{@link oida.model.integration.matching.diffmodel.impl.ReferenceMatchingImpl#getComment <em>Comment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReferenceMatchingImpl extends EObjectImpl implements ReferenceMatching {
	/**
	 * The cached value of the '{@link #getSourceReferenceMapping() <em>Source Reference Mapping</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceReferenceMapping()
	 * @generated
	 * @ordered
	 */
	protected ReferenceMapping sourceReferenceMapping;

	/**
	 * The cached value of the '{@link #getTargetReferenceMapping() <em>Target Reference Mapping</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetReferenceMapping()
	 * @generated
	 * @ordered
	 */
	protected ReferenceMapping targetReferenceMapping;

	/**
	 * The default value of the '{@link #isConfirmed() <em>Confirmed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConfirmed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONFIRMED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isConfirmed() <em>Confirmed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConfirmed()
	 * @generated
	 * @ordered
	 */
	protected boolean confirmed = CONFIRMED_EDEFAULT;

	/**
	 * The default value of the '{@link #isReported() <em>Reported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isReported() <em>Reported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReported()
	 * @generated
	 * @ordered
	 */
	protected boolean reported = REPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #getComment() <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComment()
	 * @generated
	 * @ordered
	 */
	protected static final String COMMENT_EDEFAULT = " ";

	/**
	 * The cached value of the '{@link #getComment() <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComment()
	 * @generated
	 * @ordered
	 */
	protected String comment = COMMENT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferenceMatchingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DiffmodelPackage.Literals.REFERENCE_MATCHING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceMapping getSourceReferenceMapping() {
		if (sourceReferenceMapping != null && sourceReferenceMapping.eIsProxy()) {
			InternalEObject oldSourceReferenceMapping = (InternalEObject)sourceReferenceMapping;
			sourceReferenceMapping = (ReferenceMapping)eResolveProxy(oldSourceReferenceMapping);
			if (sourceReferenceMapping != oldSourceReferenceMapping) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiffmodelPackage.REFERENCE_MATCHING__SOURCE_REFERENCE_MAPPING, oldSourceReferenceMapping, sourceReferenceMapping));
			}
		}
		return sourceReferenceMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceMapping basicGetSourceReferenceMapping() {
		return sourceReferenceMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceReferenceMapping(ReferenceMapping newSourceReferenceMapping) {
		ReferenceMapping oldSourceReferenceMapping = sourceReferenceMapping;
		sourceReferenceMapping = newSourceReferenceMapping;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiffmodelPackage.REFERENCE_MATCHING__SOURCE_REFERENCE_MAPPING, oldSourceReferenceMapping, sourceReferenceMapping));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceMapping getTargetReferenceMapping() {
		if (targetReferenceMapping != null && targetReferenceMapping.eIsProxy()) {
			InternalEObject oldTargetReferenceMapping = (InternalEObject)targetReferenceMapping;
			targetReferenceMapping = (ReferenceMapping)eResolveProxy(oldTargetReferenceMapping);
			if (targetReferenceMapping != oldTargetReferenceMapping) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiffmodelPackage.REFERENCE_MATCHING__TARGET_REFERENCE_MAPPING, oldTargetReferenceMapping, targetReferenceMapping));
			}
		}
		return targetReferenceMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceMapping basicGetTargetReferenceMapping() {
		return targetReferenceMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetReferenceMapping(ReferenceMapping newTargetReferenceMapping) {
		ReferenceMapping oldTargetReferenceMapping = targetReferenceMapping;
		targetReferenceMapping = newTargetReferenceMapping;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiffmodelPackage.REFERENCE_MATCHING__TARGET_REFERENCE_MAPPING, oldTargetReferenceMapping, targetReferenceMapping));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isConfirmed() {
		return confirmed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConfirmed(boolean newConfirmed) {
		boolean oldConfirmed = confirmed;
		confirmed = newConfirmed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiffmodelPackage.REFERENCE_MATCHING__CONFIRMED, oldConfirmed, confirmed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isReported() {
		return reported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReported(boolean newReported) {
		boolean oldReported = reported;
		reported = newReported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiffmodelPackage.REFERENCE_MATCHING__REPORTED, oldReported, reported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComment(String newComment) {
		String oldComment = comment;
		comment = newComment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiffmodelPackage.REFERENCE_MATCHING__COMMENT, oldComment, comment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DiffmodelPackage.REFERENCE_MATCHING__SOURCE_REFERENCE_MAPPING:
				if (resolve) return getSourceReferenceMapping();
				return basicGetSourceReferenceMapping();
			case DiffmodelPackage.REFERENCE_MATCHING__TARGET_REFERENCE_MAPPING:
				if (resolve) return getTargetReferenceMapping();
				return basicGetTargetReferenceMapping();
			case DiffmodelPackage.REFERENCE_MATCHING__CONFIRMED:
				return isConfirmed();
			case DiffmodelPackage.REFERENCE_MATCHING__REPORTED:
				return isReported();
			case DiffmodelPackage.REFERENCE_MATCHING__COMMENT:
				return getComment();
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
			case DiffmodelPackage.REFERENCE_MATCHING__SOURCE_REFERENCE_MAPPING:
				setSourceReferenceMapping((ReferenceMapping)newValue);
				return;
			case DiffmodelPackage.REFERENCE_MATCHING__TARGET_REFERENCE_MAPPING:
				setTargetReferenceMapping((ReferenceMapping)newValue);
				return;
			case DiffmodelPackage.REFERENCE_MATCHING__CONFIRMED:
				setConfirmed((Boolean)newValue);
				return;
			case DiffmodelPackage.REFERENCE_MATCHING__REPORTED:
				setReported((Boolean)newValue);
				return;
			case DiffmodelPackage.REFERENCE_MATCHING__COMMENT:
				setComment((String)newValue);
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
			case DiffmodelPackage.REFERENCE_MATCHING__SOURCE_REFERENCE_MAPPING:
				setSourceReferenceMapping((ReferenceMapping)null);
				return;
			case DiffmodelPackage.REFERENCE_MATCHING__TARGET_REFERENCE_MAPPING:
				setTargetReferenceMapping((ReferenceMapping)null);
				return;
			case DiffmodelPackage.REFERENCE_MATCHING__CONFIRMED:
				setConfirmed(CONFIRMED_EDEFAULT);
				return;
			case DiffmodelPackage.REFERENCE_MATCHING__REPORTED:
				setReported(REPORTED_EDEFAULT);
				return;
			case DiffmodelPackage.REFERENCE_MATCHING__COMMENT:
				setComment(COMMENT_EDEFAULT);
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
			case DiffmodelPackage.REFERENCE_MATCHING__SOURCE_REFERENCE_MAPPING:
				return sourceReferenceMapping != null;
			case DiffmodelPackage.REFERENCE_MATCHING__TARGET_REFERENCE_MAPPING:
				return targetReferenceMapping != null;
			case DiffmodelPackage.REFERENCE_MATCHING__CONFIRMED:
				return confirmed != CONFIRMED_EDEFAULT;
			case DiffmodelPackage.REFERENCE_MATCHING__REPORTED:
				return reported != REPORTED_EDEFAULT;
			case DiffmodelPackage.REFERENCE_MATCHING__COMMENT:
				return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
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
		result.append(" (confirmed: ");
		result.append(confirmed);
		result.append(", reported: ");
		result.append(reported);
		result.append(", comment: ");
		result.append(comment);
		result.append(')');
		return result.toString();
	}

} //ReferenceMatchingImpl
