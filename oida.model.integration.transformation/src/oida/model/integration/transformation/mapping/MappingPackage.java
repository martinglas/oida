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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see oida.model.integration.transformation.mapping.MappingFactory
 * @model kind="package"
 * @generated
 */
public interface MappingPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "mapping";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://oida/transformation/mapping";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "oida.merge";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MappingPackage eINSTANCE = oida.model.integration.transformation.mapping.impl.MappingPackageImpl.init();

	/**
	 * The meta object id for the '{@link oida.model.integration.transformation.mapping.impl.ReferenceMappingImpl <em>Reference Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oida.model.integration.transformation.mapping.impl.ReferenceMappingImpl
	 * @see oida.model.integration.transformation.mapping.impl.MappingPackageImpl#getReferenceMapping()
	 * @generated
	 */
	int REFERENCE_MAPPING = 0;

	/**
	 * The feature id for the '<em><b>Source EObject</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_MAPPING__SOURCE_EOBJECT = 0;

	/**
	 * The feature id for the '<em><b>Reference EObject</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_MAPPING__REFERENCE_EOBJECT = 1;

	/**
	 * The feature id for the '<em><b>EClass Mapping</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_MAPPING__ECLASS_MAPPING = 2;

	/**
	 * The number of structural features of the '<em>Reference Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_MAPPING_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link oida.model.integration.transformation.mapping.impl.EClassMappingImpl <em>EClass Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oida.model.integration.transformation.mapping.impl.EClassMappingImpl
	 * @see oida.model.integration.transformation.mapping.impl.MappingPackageImpl#getEClassMapping()
	 * @generated
	 */
	int ECLASS_MAPPING = 1;

	/**
	 * The feature id for the '<em><b>Reference EClass</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECLASS_MAPPING__REFERENCE_ECLASS = 0;

	/**
	 * The feature id for the '<em><b>Refernece Individual</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECLASS_MAPPING__REFERNECE_INDIVIDUAL = 1;

	/**
	 * The feature id for the '<em><b>Attribute Mappings</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECLASS_MAPPING__ATTRIBUTE_MAPPINGS = 2;

	/**
	 * The number of structural features of the '<em>EClass Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECLASS_MAPPING_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link oida.model.integration.transformation.mapping.impl.AttributeMappingImpl <em>Attribute Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oida.model.integration.transformation.mapping.impl.AttributeMappingImpl
	 * @see oida.model.integration.transformation.mapping.impl.MappingPackageImpl#getAttributeMapping()
	 * @generated
	 */
	int ATTRIBUTE_MAPPING = 2;

	/**
	 * The feature id for the '<em><b>Source EAttribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_MAPPING__SOURCE_EATTRIBUTE = 0;

	/**
	 * The feature id for the '<em><b>Reference EAttribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_MAPPING__REFERENCE_EATTRIBUTE = 1;

	/**
	 * The number of structural features of the '<em>Attribute Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_MAPPING_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '<em>Individual</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.apache.jena.ontology.Individual
	 * @see oida.model.integration.transformation.mapping.impl.MappingPackageImpl#getIndividual()
	 * @generated
	 */
	int INDIVIDUAL = 3;

	/**
	 * The meta object id for the '<em>EClass</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EClass
	 * @see oida.model.integration.transformation.mapping.impl.MappingPackageImpl#getEClass()
	 * @generated
	 */
	int ECLASS = 4;

	/**
	 * The meta object id for the '<em>EAttribute</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EAttribute
	 * @see oida.model.integration.transformation.mapping.impl.MappingPackageImpl#getEAttribute()
	 * @generated
	 */
	int EATTRIBUTE = 5;


	/**
	 * Returns the meta object for class '{@link oida.model.integration.transformation.mapping.ReferenceMapping <em>Reference Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference Mapping</em>'.
	 * @see oida.model.integration.transformation.mapping.ReferenceMapping
	 * @generated
	 */
	EClass getReferenceMapping();

	/**
	 * Returns the meta object for the reference '{@link oida.model.integration.transformation.mapping.ReferenceMapping#getSourceEObject <em>Source EObject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source EObject</em>'.
	 * @see oida.model.integration.transformation.mapping.ReferenceMapping#getSourceEObject()
	 * @see #getReferenceMapping()
	 * @generated
	 */
	EReference getReferenceMapping_SourceEObject();

	/**
	 * Returns the meta object for the reference '{@link oida.model.integration.transformation.mapping.ReferenceMapping#getReferenceEObject <em>Reference EObject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference EObject</em>'.
	 * @see oida.model.integration.transformation.mapping.ReferenceMapping#getReferenceEObject()
	 * @see #getReferenceMapping()
	 * @generated
	 */
	EReference getReferenceMapping_ReferenceEObject();

	/**
	 * Returns the meta object for the reference '{@link oida.model.integration.transformation.mapping.ReferenceMapping#getEClassMapping <em>EClass Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EClass Mapping</em>'.
	 * @see oida.model.integration.transformation.mapping.ReferenceMapping#getEClassMapping()
	 * @see #getReferenceMapping()
	 * @generated
	 */
	EReference getReferenceMapping_EClassMapping();

	/**
	 * Returns the meta object for class '{@link oida.model.integration.transformation.mapping.EClassMapping <em>EClass Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EClass Mapping</em>'.
	 * @see oida.model.integration.transformation.mapping.EClassMapping
	 * @generated
	 */
	EClass getEClassMapping();

	/**
	 * Returns the meta object for the attribute '{@link oida.model.integration.transformation.mapping.EClassMapping#getReferenceEClass <em>Reference EClass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reference EClass</em>'.
	 * @see oida.model.integration.transformation.mapping.EClassMapping#getReferenceEClass()
	 * @see #getEClassMapping()
	 * @generated
	 */
	EAttribute getEClassMapping_ReferenceEClass();

	/**
	 * Returns the meta object for the attribute '{@link oida.model.integration.transformation.mapping.EClassMapping#getReferneceIndividual <em>Refernece Individual</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Refernece Individual</em>'.
	 * @see oida.model.integration.transformation.mapping.EClassMapping#getReferneceIndividual()
	 * @see #getEClassMapping()
	 * @generated
	 */
	EAttribute getEClassMapping_ReferneceIndividual();

	/**
	 * Returns the meta object for the reference list '{@link oida.model.integration.transformation.mapping.EClassMapping#getAttributeMappings <em>Attribute Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Attribute Mappings</em>'.
	 * @see oida.model.integration.transformation.mapping.EClassMapping#getAttributeMappings()
	 * @see #getEClassMapping()
	 * @generated
	 */
	EReference getEClassMapping_AttributeMappings();

	/**
	 * Returns the meta object for class '{@link oida.model.integration.transformation.mapping.AttributeMapping <em>Attribute Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute Mapping</em>'.
	 * @see oida.model.integration.transformation.mapping.AttributeMapping
	 * @generated
	 */
	EClass getAttributeMapping();

	/**
	 * Returns the meta object for the attribute '{@link oida.model.integration.transformation.mapping.AttributeMapping#getSourceEAttribute <em>Source EAttribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source EAttribute</em>'.
	 * @see oida.model.integration.transformation.mapping.AttributeMapping#getSourceEAttribute()
	 * @see #getAttributeMapping()
	 * @generated
	 */
	EAttribute getAttributeMapping_SourceEAttribute();

	/**
	 * Returns the meta object for the attribute '{@link oida.model.integration.transformation.mapping.AttributeMapping#getReferenceEAttribute <em>Reference EAttribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reference EAttribute</em>'.
	 * @see oida.model.integration.transformation.mapping.AttributeMapping#getReferenceEAttribute()
	 * @see #getAttributeMapping()
	 * @generated
	 */
	EAttribute getAttributeMapping_ReferenceEAttribute();

	/**
	 * Returns the meta object for data type '{@link org.apache.jena.ontology.Individual <em>Individual</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Individual</em>'.
	 * @see org.apache.jena.ontology.Individual
	 * @model instanceClass="org.apache.jena.ontology.Individual"
	 * @generated
	 */
	EDataType getIndividual();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.emf.ecore.EClass <em>EClass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>EClass</em>'.
	 * @see org.eclipse.emf.ecore.EClass
	 * @model instanceClass="org.eclipse.emf.ecore.EClass"
	 * @generated
	 */
	EDataType getEClass();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.emf.ecore.EAttribute <em>EAttribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>EAttribute</em>'.
	 * @see org.eclipse.emf.ecore.EAttribute
	 * @model instanceClass="org.eclipse.emf.ecore.EAttribute"
	 * @generated
	 */
	EDataType getEAttribute();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MappingFactory getMappingFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link oida.model.integration.transformation.mapping.impl.ReferenceMappingImpl <em>Reference Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oida.model.integration.transformation.mapping.impl.ReferenceMappingImpl
		 * @see oida.model.integration.transformation.mapping.impl.MappingPackageImpl#getReferenceMapping()
		 * @generated
		 */
		EClass REFERENCE_MAPPING = eINSTANCE.getReferenceMapping();

		/**
		 * The meta object literal for the '<em><b>Source EObject</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE_MAPPING__SOURCE_EOBJECT = eINSTANCE.getReferenceMapping_SourceEObject();

		/**
		 * The meta object literal for the '<em><b>Reference EObject</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE_MAPPING__REFERENCE_EOBJECT = eINSTANCE.getReferenceMapping_ReferenceEObject();

		/**
		 * The meta object literal for the '<em><b>EClass Mapping</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE_MAPPING__ECLASS_MAPPING = eINSTANCE.getReferenceMapping_EClassMapping();

		/**
		 * The meta object literal for the '{@link oida.model.integration.transformation.mapping.impl.EClassMappingImpl <em>EClass Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oida.model.integration.transformation.mapping.impl.EClassMappingImpl
		 * @see oida.model.integration.transformation.mapping.impl.MappingPackageImpl#getEClassMapping()
		 * @generated
		 */
		EClass ECLASS_MAPPING = eINSTANCE.getEClassMapping();

		/**
		 * The meta object literal for the '<em><b>Reference EClass</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ECLASS_MAPPING__REFERENCE_ECLASS = eINSTANCE.getEClassMapping_ReferenceEClass();

		/**
		 * The meta object literal for the '<em><b>Refernece Individual</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ECLASS_MAPPING__REFERNECE_INDIVIDUAL = eINSTANCE.getEClassMapping_ReferneceIndividual();

		/**
		 * The meta object literal for the '<em><b>Attribute Mappings</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ECLASS_MAPPING__ATTRIBUTE_MAPPINGS = eINSTANCE.getEClassMapping_AttributeMappings();

		/**
		 * The meta object literal for the '{@link oida.model.integration.transformation.mapping.impl.AttributeMappingImpl <em>Attribute Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oida.model.integration.transformation.mapping.impl.AttributeMappingImpl
		 * @see oida.model.integration.transformation.mapping.impl.MappingPackageImpl#getAttributeMapping()
		 * @generated
		 */
		EClass ATTRIBUTE_MAPPING = eINSTANCE.getAttributeMapping();

		/**
		 * The meta object literal for the '<em><b>Source EAttribute</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE_MAPPING__SOURCE_EATTRIBUTE = eINSTANCE.getAttributeMapping_SourceEAttribute();

		/**
		 * The meta object literal for the '<em><b>Reference EAttribute</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE_MAPPING__REFERENCE_EATTRIBUTE = eINSTANCE.getAttributeMapping_ReferenceEAttribute();

		/**
		 * The meta object literal for the '<em>Individual</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.apache.jena.ontology.Individual
		 * @see oida.model.integration.transformation.mapping.impl.MappingPackageImpl#getIndividual()
		 * @generated
		 */
		EDataType INDIVIDUAL = eINSTANCE.getIndividual();

		/**
		 * The meta object literal for the '<em>EClass</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.ecore.EClass
		 * @see oida.model.integration.transformation.mapping.impl.MappingPackageImpl#getEClass()
		 * @generated
		 */
		EDataType ECLASS = eINSTANCE.getEClass();

		/**
		 * The meta object literal for the '<em>EAttribute</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.ecore.EAttribute
		 * @see oida.model.integration.transformation.mapping.impl.MappingPackageImpl#getEAttribute()
		 * @generated
		 */
		EDataType EATTRIBUTE = eINSTANCE.getEAttribute();

	}

} //MappingPackage
