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

import java.util.Collection;

import oida.model.integration.matching.diffmodel.DiffModel;
import oida.model.integration.matching.diffmodel.DiffmodelPackage;
import oida.model.integration.matching.diffmodel.ReferenceMatching;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Diff Model</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link oida.model.integration.matching.diffmodel.impl.DiffModelImpl#getReferenceMatchings
 * <em>Reference Matchings</em>}</li>
 * <li>
 * {@link oida.model.integration.matching.diffmodel.impl.DiffModelImpl#getTheirMatchings
 * <em>Their Matchings</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class DiffModelImpl extends EObjectImpl implements DiffModel {
	/**
	 * The cached value of the '{@link #getReferenceMatchings()
	 * <em>Reference Matchings</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getReferenceMatchings()
	 * @generated
	 * @ordered
	 */
	protected EList<ReferenceMatching> referenceMatchings;

	/**
	 * The cached value of the '{@link #getTheirMatchings()
	 * <em>Their Matchings</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTheirMatchings()
	 * @generated
	 * @ordered
	 */
	protected EList<ReferenceMatching> theirMatchings;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected DiffModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DiffmodelPackage.Literals.DIFF_MODEL;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<ReferenceMatching> getReferenceMatchings() {
		if (referenceMatchings == null) {
			referenceMatchings = new EObjectContainmentEList<ReferenceMatching>(
					ReferenceMatching.class, this,
					DiffmodelPackage.DIFF_MODEL__REFERENCE_MATCHINGS);
		}
		return referenceMatchings;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<ReferenceMatching> getTheirMatchings() {
		if (theirMatchings == null) {
			theirMatchings = new EObjectContainmentEList<ReferenceMatching>(
					ReferenceMatching.class, this,
					DiffmodelPackage.DIFF_MODEL__THEIR_MATCHINGS);
		}
		return theirMatchings;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case DiffmodelPackage.DIFF_MODEL__REFERENCE_MATCHINGS:
			return ((InternalEList<?>) getReferenceMatchings()).basicRemove(
					otherEnd, msgs);
		case DiffmodelPackage.DIFF_MODEL__THEIR_MATCHINGS:
			return ((InternalEList<?>) getTheirMatchings()).basicRemove(
					otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case DiffmodelPackage.DIFF_MODEL__REFERENCE_MATCHINGS:
			return getReferenceMatchings();
		case DiffmodelPackage.DIFF_MODEL__THEIR_MATCHINGS:
			return getTheirMatchings();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case DiffmodelPackage.DIFF_MODEL__REFERENCE_MATCHINGS:
			getReferenceMatchings().clear();
			getReferenceMatchings().addAll(
					(Collection<? extends ReferenceMatching>) newValue);
			return;
		case DiffmodelPackage.DIFF_MODEL__THEIR_MATCHINGS:
			getTheirMatchings().clear();
			getTheirMatchings().addAll(
					(Collection<? extends ReferenceMatching>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case DiffmodelPackage.DIFF_MODEL__REFERENCE_MATCHINGS:
			getReferenceMatchings().clear();
			return;
		case DiffmodelPackage.DIFF_MODEL__THEIR_MATCHINGS:
			getTheirMatchings().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case DiffmodelPackage.DIFF_MODEL__REFERENCE_MATCHINGS:
			return referenceMatchings != null && !referenceMatchings.isEmpty();
		case DiffmodelPackage.DIFF_MODEL__THEIR_MATCHINGS:
			return theirMatchings != null && !theirMatchings.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // DiffModelImpl
