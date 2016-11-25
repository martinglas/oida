/**
 */
package oida;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see oida.OidaFactory
 * @model kind="package"
 * @generated
 */
public interface OidaPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "oida";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://net/bauhaus-luftfahrt/oida";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "net.bauhaus-luftfahrt.oida";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OidaPackage eINSTANCE = oida.impl.OidaPackageImpl.init();

	/**
	 * The meta object id for the '{@link oida.impl.OIDASystemImpl <em>OIDA System</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oida.impl.OIDASystemImpl
	 * @see oida.impl.OidaPackageImpl#getOIDASystem()
	 * @generated
	 */
	int OIDA_SYSTEM = 0;

	/**
	 * The feature id for the '<em><b>Ontology Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OIDA_SYSTEM__ONTOLOGY_LIBRARY = 0;

	/**
	 * The feature id for the '<em><b>Reference Ontology</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OIDA_SYSTEM__REFERENCE_ONTOLOGY = 1;

	/**
	 * The number of structural features of the '<em>OIDA System</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OIDA_SYSTEM_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>OIDA System</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OIDA_SYSTEM_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link oida.impl.ManagedOntologyImpl <em>Managed Ontology</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oida.impl.ManagedOntologyImpl
	 * @see oida.impl.OidaPackageImpl#getManagedOntology()
	 * @generated
	 */
	int MANAGED_ONTOLOGY = 1;

	/**
	 * The feature id for the '<em><b>Ontology</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_ONTOLOGY__ONTOLOGY = 0;

	/**
	 * The feature id for the '<em><b>Ontology Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_ONTOLOGY__ONTOLOGY_ENTRY = 1;

	/**
	 * The number of structural features of the '<em>Managed Ontology</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_ONTOLOGY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Managed Ontology</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_ONTOLOGY_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link oida.OIDASystem <em>OIDA System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>OIDA System</em>'.
	 * @see oida.OIDASystem
	 * @generated
	 */
	EClass getOIDASystem();

	/**
	 * Returns the meta object for the reference '{@link oida.OIDASystem#getOntologyLibrary <em>Ontology Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Ontology Library</em>'.
	 * @see oida.OIDASystem#getOntologyLibrary()
	 * @see #getOIDASystem()
	 * @generated
	 */
	EReference getOIDASystem_OntologyLibrary();

	/**
	 * Returns the meta object for the reference '{@link oida.OIDASystem#getReferenceOntology <em>Reference Ontology</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference Ontology</em>'.
	 * @see oida.OIDASystem#getReferenceOntology()
	 * @see #getOIDASystem()
	 * @generated
	 */
	EReference getOIDASystem_ReferenceOntology();

	/**
	 * Returns the meta object for class '{@link oida.ManagedOntology <em>Managed Ontology</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Managed Ontology</em>'.
	 * @see oida.ManagedOntology
	 * @generated
	 */
	EClass getManagedOntology();

	/**
	 * Returns the meta object for the reference '{@link oida.ManagedOntology#getOntology <em>Ontology</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Ontology</em>'.
	 * @see oida.ManagedOntology#getOntology()
	 * @see #getManagedOntology()
	 * @generated
	 */
	EReference getManagedOntology_Ontology();

	/**
	 * Returns the meta object for the reference '{@link oida.ManagedOntology#getOntologyEntry <em>Ontology Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Ontology Entry</em>'.
	 * @see oida.ManagedOntology#getOntologyEntry()
	 * @see #getManagedOntology()
	 * @generated
	 */
	EReference getManagedOntology_OntologyEntry();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	OidaFactory getOidaFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link oida.impl.OIDASystemImpl <em>OIDA System</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oida.impl.OIDASystemImpl
		 * @see oida.impl.OidaPackageImpl#getOIDASystem()
		 * @generated
		 */
		EClass OIDA_SYSTEM = eINSTANCE.getOIDASystem();

		/**
		 * The meta object literal for the '<em><b>Ontology Library</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OIDA_SYSTEM__ONTOLOGY_LIBRARY = eINSTANCE.getOIDASystem_OntologyLibrary();

		/**
		 * The meta object literal for the '<em><b>Reference Ontology</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OIDA_SYSTEM__REFERENCE_ONTOLOGY = eINSTANCE.getOIDASystem_ReferenceOntology();

		/**
		 * The meta object literal for the '{@link oida.impl.ManagedOntologyImpl <em>Managed Ontology</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oida.impl.ManagedOntologyImpl
		 * @see oida.impl.OidaPackageImpl#getManagedOntology()
		 * @generated
		 */
		EClass MANAGED_ONTOLOGY = eINSTANCE.getManagedOntology();

		/**
		 * The meta object literal for the '<em><b>Ontology</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MANAGED_ONTOLOGY__ONTOLOGY = eINSTANCE.getManagedOntology_Ontology();

		/**
		 * The meta object literal for the '<em><b>Ontology Entry</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MANAGED_ONTOLOGY__ONTOLOGY_ENTRY = eINSTANCE.getManagedOntology_OntologyEntry();

	}

} //OidaPackage
