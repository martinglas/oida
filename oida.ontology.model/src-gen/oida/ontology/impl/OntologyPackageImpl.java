/**
 */
package oida.ontology.impl;

import oida.ontology.Ontology;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyFactory;
import oida.ontology.OntologyIndividual;
import oida.ontology.OntologyItem;
import oida.ontology.OntologyPackage;

import oida.ontologyMgr.OntologyMgrPackage;

import oida.ontologyMgr.impl.OntologyMgrPackageImpl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OntologyPackageImpl extends EPackageImpl implements OntologyPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ontologyClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ontologyEntityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ontologyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ontologyIndividualEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ontologyItemEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see oida.ontology.OntologyPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private OntologyPackageImpl() {
		super(eNS_URI, OntologyFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link OntologyPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static OntologyPackage init() {
		if (isInited) return (OntologyPackage)EPackage.Registry.INSTANCE.getEPackage(OntologyPackage.eNS_URI);

		// Obtain or create and register package
		OntologyPackageImpl theOntologyPackage = (OntologyPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof OntologyPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new OntologyPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		OntologyMgrPackageImpl theOntologyMgrPackage = (OntologyMgrPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OntologyMgrPackage.eNS_URI) instanceof OntologyMgrPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OntologyMgrPackage.eNS_URI) : OntologyMgrPackage.eINSTANCE);

		// Create package meta-data objects
		theOntologyPackage.createPackageContents();
		theOntologyMgrPackage.createPackageContents();

		// Initialize created meta-data
		theOntologyPackage.initializePackageContents();
		theOntologyMgrPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theOntologyPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(OntologyPackage.eNS_URI, theOntologyPackage);
		return theOntologyPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOntologyClass() {
		return ontologyClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOntologyClass_Individuals() {
		return (EReference)ontologyClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOntologyEntity() {
		return ontologyEntityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOntologyEntity_Prefix() {
		return (EAttribute)ontologyEntityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOntologyEntity_ContainingOntology() {
		return (EReference)ontologyEntityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOntology() {
		return ontologyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOntology_Entities() {
		return (EReference)ontologyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOntology_NrOfClasses() {
		return (EAttribute)ontologyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOntology_NrOfIndividuals() {
		return (EAttribute)ontologyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOntology_OntologyEntry() {
		return (EReference)ontologyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOntologyIndividual() {
		return ontologyIndividualEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOntologyIndividual_Types() {
		return (EReference)ontologyIndividualEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOntologyItem() {
		return ontologyItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOntologyItem_Name() {
		return (EAttribute)ontologyItemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OntologyFactory getOntologyFactory() {
		return (OntologyFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		ontologyClassEClass = createEClass(ONTOLOGY_CLASS);
		createEReference(ontologyClassEClass, ONTOLOGY_CLASS__INDIVIDUALS);

		ontologyEntityEClass = createEClass(ONTOLOGY_ENTITY);
		createEAttribute(ontologyEntityEClass, ONTOLOGY_ENTITY__PREFIX);
		createEReference(ontologyEntityEClass, ONTOLOGY_ENTITY__CONTAINING_ONTOLOGY);

		ontologyEClass = createEClass(ONTOLOGY);
		createEReference(ontologyEClass, ONTOLOGY__ENTITIES);
		createEAttribute(ontologyEClass, ONTOLOGY__NR_OF_CLASSES);
		createEAttribute(ontologyEClass, ONTOLOGY__NR_OF_INDIVIDUALS);
		createEReference(ontologyEClass, ONTOLOGY__ONTOLOGY_ENTRY);

		ontologyIndividualEClass = createEClass(ONTOLOGY_INDIVIDUAL);
		createEReference(ontologyIndividualEClass, ONTOLOGY_INDIVIDUAL__TYPES);

		ontologyItemEClass = createEClass(ONTOLOGY_ITEM);
		createEAttribute(ontologyItemEClass, ONTOLOGY_ITEM__NAME);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		OntologyMgrPackage theOntologyMgrPackage = (OntologyMgrPackage)EPackage.Registry.INSTANCE.getEPackage(OntologyMgrPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		ontologyClassEClass.getESuperTypes().add(this.getOntologyEntity());
		ontologyEntityEClass.getESuperTypes().add(this.getOntologyItem());
		ontologyEClass.getESuperTypes().add(this.getOntologyItem());
		ontologyIndividualEClass.getESuperTypes().add(this.getOntologyEntity());

		// Initialize classes, features, and operations; add parameters
		initEClass(ontologyClassEClass, OntologyClass.class, "OntologyClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOntologyClass_Individuals(), this.getOntologyIndividual(), null, "individuals", null, 0, -1, OntologyClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ontologyEntityEClass, OntologyEntity.class, "OntologyEntity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOntologyEntity_Prefix(), ecorePackage.getEString(), "prefix", null, 0, 1, OntologyEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOntologyEntity_ContainingOntology(), this.getOntology(), this.getOntology_Entities(), "containingOntology", null, 1, 1, OntologyEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ontologyEClass, Ontology.class, "Ontology", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOntology_Entities(), this.getOntologyEntity(), this.getOntologyEntity_ContainingOntology(), "entities", null, 0, -1, Ontology.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOntology_NrOfClasses(), ecorePackage.getEInt(), "nrOfClasses", null, 0, 1, Ontology.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOntology_NrOfIndividuals(), ecorePackage.getEInt(), "nrOfIndividuals", null, 0, 1, Ontology.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOntology_OntologyEntry(), theOntologyMgrPackage.getLocalOntologyEntry(), theOntologyMgrPackage.getLocalOntologyEntry_ManagedOntology(), "ontologyEntry", null, 0, 1, Ontology.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ontologyIndividualEClass, OntologyIndividual.class, "OntologyIndividual", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOntologyIndividual_Types(), this.getOntologyClass(), null, "types", null, 0, -1, OntologyIndividual.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ontologyItemEClass, OntologyItem.class, "OntologyItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOntologyItem_Name(), ecorePackage.getEString(), "name", null, 0, 1, OntologyItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //OntologyPackageImpl
