/**
 */
package oida.ontology;

import org.eclipse.emf.ecore.EAttribute;
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
 * @see oida.ontology.OntologyFactory
 * @model kind="package"
 * @generated
 */
public interface OntologyPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ontology";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://net/bauhaus-luftfahrt/oida/ontology";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "net.bauhaus-luftfahrt.oida.ontology";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OntologyPackage eINSTANCE = oida.ontology.impl.OntologyPackageImpl.init();

	/**
	 * The meta object id for the '{@link oida.ontology.impl.OntologyItemImpl <em>Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oida.ontology.impl.OntologyItemImpl
	 * @see oida.ontology.impl.OntologyPackageImpl#getOntologyItem()
	 * @generated
	 */
	int ONTOLOGY_ITEM = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY_ITEM__NAME = 0;

	/**
	 * The number of structural features of the '<em>Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY_ITEM_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY_ITEM_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link oida.ontology.impl.OntologyEntityImpl <em>Entity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oida.ontology.impl.OntologyEntityImpl
	 * @see oida.ontology.impl.OntologyPackageImpl#getOntologyEntity()
	 * @generated
	 */
	int ONTOLOGY_ENTITY = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY_ENTITY__NAME = ONTOLOGY_ITEM__NAME;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY_ENTITY__PREFIX = ONTOLOGY_ITEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Containing Ontology</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY_ENTITY__CONTAINING_ONTOLOGY = ONTOLOGY_ITEM_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY_ENTITY_FEATURE_COUNT = ONTOLOGY_ITEM_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY_ENTITY_OPERATION_COUNT = ONTOLOGY_ITEM_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link oida.ontology.impl.OntologyClassImpl <em>Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oida.ontology.impl.OntologyClassImpl
	 * @see oida.ontology.impl.OntologyPackageImpl#getOntologyClass()
	 * @generated
	 */
	int ONTOLOGY_CLASS = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY_CLASS__NAME = ONTOLOGY_ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY_CLASS__PREFIX = ONTOLOGY_ENTITY__PREFIX;

	/**
	 * The feature id for the '<em><b>Containing Ontology</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY_CLASS__CONTAINING_ONTOLOGY = ONTOLOGY_ENTITY__CONTAINING_ONTOLOGY;

	/**
	 * The feature id for the '<em><b>Individuals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY_CLASS__INDIVIDUALS = ONTOLOGY_ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY_CLASS_FEATURE_COUNT = ONTOLOGY_ENTITY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY_CLASS_OPERATION_COUNT = ONTOLOGY_ENTITY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link oida.ontology.impl.OntologyImpl <em>Ontology</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oida.ontology.impl.OntologyImpl
	 * @see oida.ontology.impl.OntologyPackageImpl#getOntology()
	 * @generated
	 */
	int ONTOLOGY = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY__NAME = ONTOLOGY_ITEM__NAME;

	/**
	 * The feature id for the '<em><b>Entities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY__ENTITIES = ONTOLOGY_ITEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Nr Of Classes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY__NR_OF_CLASSES = ONTOLOGY_ITEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Nr Of Individuals</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY__NR_OF_INDIVIDUALS = ONTOLOGY_ITEM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Ontology Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY__ONTOLOGY_ENTRY = ONTOLOGY_ITEM_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Ontology</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY_FEATURE_COUNT = ONTOLOGY_ITEM_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Ontology</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY_OPERATION_COUNT = ONTOLOGY_ITEM_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link oida.ontology.impl.OntologyIndividualImpl <em>Individual</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oida.ontology.impl.OntologyIndividualImpl
	 * @see oida.ontology.impl.OntologyPackageImpl#getOntologyIndividual()
	 * @generated
	 */
	int ONTOLOGY_INDIVIDUAL = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY_INDIVIDUAL__NAME = ONTOLOGY_ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY_INDIVIDUAL__PREFIX = ONTOLOGY_ENTITY__PREFIX;

	/**
	 * The feature id for the '<em><b>Containing Ontology</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY_INDIVIDUAL__CONTAINING_ONTOLOGY = ONTOLOGY_ENTITY__CONTAINING_ONTOLOGY;

	/**
	 * The feature id for the '<em><b>Types</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY_INDIVIDUAL__TYPES = ONTOLOGY_ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Individual</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY_INDIVIDUAL_FEATURE_COUNT = ONTOLOGY_ENTITY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Individual</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONTOLOGY_INDIVIDUAL_OPERATION_COUNT = ONTOLOGY_ENTITY_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link oida.ontology.OntologyClass <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class</em>'.
	 * @see oida.ontology.OntologyClass
	 * @generated
	 */
	EClass getOntologyClass();

	/**
	 * Returns the meta object for the reference list '{@link oida.ontology.OntologyClass#getIndividuals <em>Individuals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Individuals</em>'.
	 * @see oida.ontology.OntologyClass#getIndividuals()
	 * @see #getOntologyClass()
	 * @generated
	 */
	EReference getOntologyClass_Individuals();

	/**
	 * Returns the meta object for class '{@link oida.ontology.OntologyEntity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity</em>'.
	 * @see oida.ontology.OntologyEntity
	 * @generated
	 */
	EClass getOntologyEntity();

	/**
	 * Returns the meta object for the attribute '{@link oida.ontology.OntologyEntity#getPrefix <em>Prefix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Prefix</em>'.
	 * @see oida.ontology.OntologyEntity#getPrefix()
	 * @see #getOntologyEntity()
	 * @generated
	 */
	EAttribute getOntologyEntity_Prefix();

	/**
	 * Returns the meta object for the container reference '{@link oida.ontology.OntologyEntity#getContainingOntology <em>Containing Ontology</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Containing Ontology</em>'.
	 * @see oida.ontology.OntologyEntity#getContainingOntology()
	 * @see #getOntologyEntity()
	 * @generated
	 */
	EReference getOntologyEntity_ContainingOntology();

	/**
	 * Returns the meta object for class '{@link oida.ontology.Ontology <em>Ontology</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ontology</em>'.
	 * @see oida.ontology.Ontology
	 * @generated
	 */
	EClass getOntology();

	/**
	 * Returns the meta object for the containment reference list '{@link oida.ontology.Ontology#getEntities <em>Entities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Entities</em>'.
	 * @see oida.ontology.Ontology#getEntities()
	 * @see #getOntology()
	 * @generated
	 */
	EReference getOntology_Entities();

	/**
	 * Returns the meta object for the attribute '{@link oida.ontology.Ontology#getNrOfClasses <em>Nr Of Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nr Of Classes</em>'.
	 * @see oida.ontology.Ontology#getNrOfClasses()
	 * @see #getOntology()
	 * @generated
	 */
	EAttribute getOntology_NrOfClasses();

	/**
	 * Returns the meta object for the attribute '{@link oida.ontology.Ontology#getNrOfIndividuals <em>Nr Of Individuals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nr Of Individuals</em>'.
	 * @see oida.ontology.Ontology#getNrOfIndividuals()
	 * @see #getOntology()
	 * @generated
	 */
	EAttribute getOntology_NrOfIndividuals();

	/**
	 * Returns the meta object for the reference '{@link oida.ontology.Ontology#getOntologyEntry <em>Ontology Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Ontology Entry</em>'.
	 * @see oida.ontology.Ontology#getOntologyEntry()
	 * @see #getOntology()
	 * @generated
	 */
	EReference getOntology_OntologyEntry();

	/**
	 * Returns the meta object for class '{@link oida.ontology.OntologyIndividual <em>Individual</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Individual</em>'.
	 * @see oida.ontology.OntologyIndividual
	 * @generated
	 */
	EClass getOntologyIndividual();

	/**
	 * Returns the meta object for the reference list '{@link oida.ontology.OntologyIndividual#getTypes <em>Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Types</em>'.
	 * @see oida.ontology.OntologyIndividual#getTypes()
	 * @see #getOntologyIndividual()
	 * @generated
	 */
	EReference getOntologyIndividual_Types();

	/**
	 * Returns the meta object for class '{@link oida.ontology.OntologyItem <em>Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Item</em>'.
	 * @see oida.ontology.OntologyItem
	 * @generated
	 */
	EClass getOntologyItem();

	/**
	 * Returns the meta object for the attribute '{@link oida.ontology.OntologyItem#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see oida.ontology.OntologyItem#getName()
	 * @see #getOntologyItem()
	 * @generated
	 */
	EAttribute getOntologyItem_Name();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	OntologyFactory getOntologyFactory();

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
		 * The meta object literal for the '{@link oida.ontology.impl.OntologyClassImpl <em>Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oida.ontology.impl.OntologyClassImpl
		 * @see oida.ontology.impl.OntologyPackageImpl#getOntologyClass()
		 * @generated
		 */
		EClass ONTOLOGY_CLASS = eINSTANCE.getOntologyClass();

		/**
		 * The meta object literal for the '<em><b>Individuals</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ONTOLOGY_CLASS__INDIVIDUALS = eINSTANCE.getOntologyClass_Individuals();

		/**
		 * The meta object literal for the '{@link oida.ontology.impl.OntologyEntityImpl <em>Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oida.ontology.impl.OntologyEntityImpl
		 * @see oida.ontology.impl.OntologyPackageImpl#getOntologyEntity()
		 * @generated
		 */
		EClass ONTOLOGY_ENTITY = eINSTANCE.getOntologyEntity();

		/**
		 * The meta object literal for the '<em><b>Prefix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ONTOLOGY_ENTITY__PREFIX = eINSTANCE.getOntologyEntity_Prefix();

		/**
		 * The meta object literal for the '<em><b>Containing Ontology</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ONTOLOGY_ENTITY__CONTAINING_ONTOLOGY = eINSTANCE.getOntologyEntity_ContainingOntology();

		/**
		 * The meta object literal for the '{@link oida.ontology.impl.OntologyImpl <em>Ontology</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oida.ontology.impl.OntologyImpl
		 * @see oida.ontology.impl.OntologyPackageImpl#getOntology()
		 * @generated
		 */
		EClass ONTOLOGY = eINSTANCE.getOntology();

		/**
		 * The meta object literal for the '<em><b>Entities</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ONTOLOGY__ENTITIES = eINSTANCE.getOntology_Entities();

		/**
		 * The meta object literal for the '<em><b>Nr Of Classes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ONTOLOGY__NR_OF_CLASSES = eINSTANCE.getOntology_NrOfClasses();

		/**
		 * The meta object literal for the '<em><b>Nr Of Individuals</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ONTOLOGY__NR_OF_INDIVIDUALS = eINSTANCE.getOntology_NrOfIndividuals();

		/**
		 * The meta object literal for the '<em><b>Ontology Entry</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ONTOLOGY__ONTOLOGY_ENTRY = eINSTANCE.getOntology_OntologyEntry();

		/**
		 * The meta object literal for the '{@link oida.ontology.impl.OntologyIndividualImpl <em>Individual</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oida.ontology.impl.OntologyIndividualImpl
		 * @see oida.ontology.impl.OntologyPackageImpl#getOntologyIndividual()
		 * @generated
		 */
		EClass ONTOLOGY_INDIVIDUAL = eINSTANCE.getOntologyIndividual();

		/**
		 * The meta object literal for the '<em><b>Types</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ONTOLOGY_INDIVIDUAL__TYPES = eINSTANCE.getOntologyIndividual_Types();

		/**
		 * The meta object literal for the '{@link oida.ontology.impl.OntologyItemImpl <em>Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oida.ontology.impl.OntologyItemImpl
		 * @see oida.ontology.impl.OntologyPackageImpl#getOntologyItem()
		 * @generated
		 */
		EClass ONTOLOGY_ITEM = eINSTANCE.getOntologyItem();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ONTOLOGY_ITEM__NAME = eINSTANCE.getOntologyItem_Name();

	}

} //OntologyPackage
