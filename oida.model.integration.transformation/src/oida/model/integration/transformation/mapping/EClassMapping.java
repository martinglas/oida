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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.hp.hpl.jena.ontology.Individual;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EClass Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link oida.model.integration.transformation.mapping.EClassMapping#getReferenceEClass <em>Reference EClass</em>}</li>
 *   <li>{@link oida.model.integration.transformation.mapping.EClassMapping#getReferneceIndividual <em>Refernece Individual</em>}</li>
 *   <li>{@link oida.model.integration.transformation.mapping.EClassMapping#getAttributeMappings <em>Attribute Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @see oida.model.integration.transformation.mapping.MappingPackage#getEClassMapping()
 * @model
 * @generated
 */
public interface EClassMapping extends EObject {
	/**
	 * Returns the value of the '<em><b>Reference EClass</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference EClass</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference EClass</em>' attribute.
	 * @see #setReferenceEClass(EClass)
	 * @see oida.model.integration.transformation.mapping.MappingPackage#getEClassMapping_ReferenceEClass()
	 * @model dataType="net.bhl.cdt.model.integration.transformation.mapping.EClass"
	 * @generated
	 */
	EClass getReferenceEClass();

	/**
	 * Sets the value of the '{@link oida.model.integration.transformation.mapping.EClassMapping#getReferenceEClass <em>Reference EClass</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference EClass</em>' attribute.
	 * @see #getReferenceEClass()
	 * @generated
	 */
	void setReferenceEClass(EClass value);

	/**
	 * Returns the value of the '<em><b>Refernece Individual</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Refernece Individual</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Refernece Individual</em>' attribute.
	 * @see #setReferneceIndividual(Individual)
	 * @see oida.model.integration.transformation.mapping.MappingPackage#getEClassMapping_ReferneceIndividual()
	 * @model dataType="net.bhl.cdt.model.integration.transformation.mapping.Individual"
	 * @generated
	 */
	Individual getReferneceIndividual();

	/**
	 * Sets the value of the '{@link oida.model.integration.transformation.mapping.EClassMapping#getReferneceIndividual <em>Refernece Individual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Refernece Individual</em>' attribute.
	 * @see #getReferneceIndividual()
	 * @generated
	 */
	void setReferneceIndividual(Individual value);

	/**
	 * Returns the value of the '<em><b>Attribute Mappings</b></em>' reference list.
	 * The list contents are of type {@link oida.model.integration.transformation.mapping.AttributeMapping}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute Mappings</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute Mappings</em>' reference list.
	 * @see oida.model.integration.transformation.mapping.MappingPackage#getEClassMapping_AttributeMappings()
	 * @model
	 * @generated
	 */
	EList<AttributeMapping> getAttributeMappings();

} // EClassMapping
