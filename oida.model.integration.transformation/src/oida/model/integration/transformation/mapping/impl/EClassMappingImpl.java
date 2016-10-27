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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import oida.model.integration.transformation.mapping.AttributeMapping;
import oida.model.integration.transformation.mapping.EClassMapping;
import oida.model.integration.transformation.mapping.MappingPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EClass Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link oida.model.integration.transformation.mapping.impl.EClassMappingImpl#getReferenceEClass <em>Reference EClass</em>}</li>
 *   <li>{@link oida.model.integration.transformation.mapping.impl.EClassMappingImpl#getReferneceIndividual <em>Refernece Individual</em>}</li>
 *   <li>{@link oida.model.integration.transformation.mapping.impl.EClassMappingImpl#getAttributeMappings <em>Attribute Mappings</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EClassMappingImpl extends EObjectImpl implements EClassMapping {
	/**
	 * The default value of the '{@link #getReferenceEClass() <em>Reference EClass</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceEClass()
	 * @generated
	 * @ordered
	 */
	protected static final EClass REFERENCE_ECLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReferenceEClass() <em>Reference EClass</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceEClass()
	 * @generated
	 * @ordered
	 */
	protected EClass referenceEClass = REFERENCE_ECLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #getReferneceIndividual() <em>Refernece Individual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferneceIndividual()
	 * @generated
	 * @ordered
	 */
	protected static final org.apache.jena.ontology.Individual REFERNECE_INDIVIDUAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReferneceIndividual() <em>Refernece Individual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferneceIndividual()
	 * @generated
	 * @ordered
	 */
	protected org.apache.jena.ontology.Individual referneceIndividual = REFERNECE_INDIVIDUAL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAttributeMappings() <em>Attribute Mappings</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributeMappings()
	 * @generated
	 * @ordered
	 */
	protected EList<AttributeMapping> attributeMappings;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClassMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MappingPackage.Literals.ECLASS_MAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReferenceEClass() {
		return referenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferenceEClass(EClass newReferenceEClass) {
		EClass oldReferenceEClass = referenceEClass;
		referenceEClass = newReferenceEClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.ECLASS_MAPPING__REFERENCE_ECLASS, oldReferenceEClass, referenceEClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.apache.jena.ontology.Individual getReferneceIndividual() {
		return referneceIndividual;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferneceIndividual(org.apache.jena.ontology.Individual newReferneceIndividual) {
		org.apache.jena.ontology.Individual oldReferneceIndividual = referneceIndividual;
		referneceIndividual = newReferneceIndividual;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.ECLASS_MAPPING__REFERNECE_INDIVIDUAL, oldReferneceIndividual, referneceIndividual));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AttributeMapping> getAttributeMappings() {
		if (attributeMappings == null) {
			attributeMappings = new EObjectResolvingEList<AttributeMapping>(AttributeMapping.class, this, MappingPackage.ECLASS_MAPPING__ATTRIBUTE_MAPPINGS);
		}
		return attributeMappings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MappingPackage.ECLASS_MAPPING__REFERENCE_ECLASS:
				return getReferenceEClass();
			case MappingPackage.ECLASS_MAPPING__REFERNECE_INDIVIDUAL:
				return getReferneceIndividual();
			case MappingPackage.ECLASS_MAPPING__ATTRIBUTE_MAPPINGS:
				return getAttributeMappings();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MappingPackage.ECLASS_MAPPING__REFERENCE_ECLASS:
				setReferenceEClass((EClass)newValue);
				return;
			case MappingPackage.ECLASS_MAPPING__REFERNECE_INDIVIDUAL:
				setReferneceIndividual((org.apache.jena.ontology.Individual)newValue);
				return;
			case MappingPackage.ECLASS_MAPPING__ATTRIBUTE_MAPPINGS:
				getAttributeMappings().clear();
				getAttributeMappings().addAll((Collection<? extends AttributeMapping>)newValue);
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
			case MappingPackage.ECLASS_MAPPING__REFERENCE_ECLASS:
				setReferenceEClass(REFERENCE_ECLASS_EDEFAULT);
				return;
			case MappingPackage.ECLASS_MAPPING__REFERNECE_INDIVIDUAL:
				setReferneceIndividual(REFERNECE_INDIVIDUAL_EDEFAULT);
				return;
			case MappingPackage.ECLASS_MAPPING__ATTRIBUTE_MAPPINGS:
				getAttributeMappings().clear();
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
			case MappingPackage.ECLASS_MAPPING__REFERENCE_ECLASS:
				return REFERENCE_ECLASS_EDEFAULT == null ? referenceEClass != null : !REFERENCE_ECLASS_EDEFAULT.equals(referenceEClass);
			case MappingPackage.ECLASS_MAPPING__REFERNECE_INDIVIDUAL:
				return REFERNECE_INDIVIDUAL_EDEFAULT == null ? referneceIndividual != null : !REFERNECE_INDIVIDUAL_EDEFAULT.equals(referneceIndividual);
			case MappingPackage.ECLASS_MAPPING__ATTRIBUTE_MAPPINGS:
				return attributeMappings != null && !attributeMappings.isEmpty();
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
		result.append(" (referenceEClass: ");
		result.append(referenceEClass);
		result.append(", referneceIndividual: ");
		result.append(referneceIndividual);
		result.append(')');
		return result.toString();
	}

} //EClassMappingImpl
