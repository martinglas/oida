/**
 */
package oida.impl;

import oida.ManagedOntology;
import oida.OIDASystem;
import oida.OidaFactory;
import oida.OidaPackage;

import oida.ontology.OntologyPackage;

import oida.ontology.impl.OntologyPackageImpl;

import oida.ontologyMgr.OntologyMgrPackage;

import oida.ontologyMgr.impl.OntologyMgrPackageImpl;

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
public class OidaPackageImpl extends EPackageImpl implements OidaPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oidaSystemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass managedOntologyEClass = null;

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
	 * @see oida.OidaPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private OidaPackageImpl() {
		super(eNS_URI, OidaFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link OidaPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static OidaPackage init() {
		if (isInited) return (OidaPackage)EPackage.Registry.INSTANCE.getEPackage(OidaPackage.eNS_URI);

		// Obtain or create and register package
		OidaPackageImpl theOidaPackage = (OidaPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof OidaPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new OidaPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		OntologyMgrPackageImpl theOntologyMgrPackage = (OntologyMgrPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OntologyMgrPackage.eNS_URI) instanceof OntologyMgrPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OntologyMgrPackage.eNS_URI) : OntologyMgrPackage.eINSTANCE);
		OntologyPackageImpl theOntologyPackage = (OntologyPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OntologyPackage.eNS_URI) instanceof OntologyPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OntologyPackage.eNS_URI) : OntologyPackage.eINSTANCE);

		// Create package meta-data objects
		theOidaPackage.createPackageContents();
		theOntologyMgrPackage.createPackageContents();
		theOntologyPackage.createPackageContents();

		// Initialize created meta-data
		theOidaPackage.initializePackageContents();
		theOntologyMgrPackage.initializePackageContents();
		theOntologyPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theOidaPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(OidaPackage.eNS_URI, theOidaPackage);
		return theOidaPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOIDASystem() {
		return oidaSystemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOIDASystem_OntologyLibrary() {
		return (EReference)oidaSystemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOIDASystem_ReferenceOntology() {
		return (EReference)oidaSystemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getManagedOntology() {
		return managedOntologyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getManagedOntology_Ontology() {
		return (EReference)managedOntologyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getManagedOntology_OntologyEntry() {
		return (EReference)managedOntologyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OidaFactory getOidaFactory() {
		return (OidaFactory)getEFactoryInstance();
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
		oidaSystemEClass = createEClass(OIDA_SYSTEM);
		createEReference(oidaSystemEClass, OIDA_SYSTEM__ONTOLOGY_LIBRARY);
		createEReference(oidaSystemEClass, OIDA_SYSTEM__REFERENCE_ONTOLOGY);

		managedOntologyEClass = createEClass(MANAGED_ONTOLOGY);
		createEReference(managedOntologyEClass, MANAGED_ONTOLOGY__ONTOLOGY);
		createEReference(managedOntologyEClass, MANAGED_ONTOLOGY__ONTOLOGY_ENTRY);
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
		OntologyPackage theOntologyPackage = (OntologyPackage)EPackage.Registry.INSTANCE.getEPackage(OntologyPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theOntologyMgrPackage);
		getESubpackages().add(theOntologyPackage);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(oidaSystemEClass, OIDASystem.class, "OIDASystem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOIDASystem_OntologyLibrary(), theOntologyMgrPackage.getLibrary(), null, "OntologyLibrary", null, 0, 1, OIDASystem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOIDASystem_ReferenceOntology(), this.getManagedOntology(), null, "ReferenceOntology", null, 0, 1, OIDASystem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(managedOntologyEClass, ManagedOntology.class, "ManagedOntology", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getManagedOntology_Ontology(), theOntologyPackage.getOntology(), null, "Ontology", null, 0, 1, ManagedOntology.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getManagedOntology_OntologyEntry(), theOntologyMgrPackage.getLocalOntologyEntry(), null, "OntologyEntry", null, 0, 1, ManagedOntology.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //OidaPackageImpl
