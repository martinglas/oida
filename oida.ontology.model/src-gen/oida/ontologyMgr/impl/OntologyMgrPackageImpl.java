/**
 */
package oida.ontologyMgr.impl;

import oida.ontology.OntologyPackage;
import oida.ontology.impl.OntologyPackageImpl;
import oida.ontologyMgr.GitRepoOntologyEntry;
import oida.ontologyMgr.Library;
import oida.ontologyMgr.LocalOntologyEntry;
import oida.ontologyMgr.OntologyMgrFactory;
import oida.ontologyMgr.OntologyMgrPackage;

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
public class OntologyMgrPackageImpl extends EPackageImpl implements OntologyMgrPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass libraryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass localOntologyEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gitRepoOntologyEntryEClass = null;

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
	 * @see oida.ontologyMgr.OntologyMgrPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private OntologyMgrPackageImpl() {
		super(eNS_URI, OntologyMgrFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link OntologyMgrPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static OntologyMgrPackage init() {
		if (isInited) return (OntologyMgrPackage)EPackage.Registry.INSTANCE.getEPackage(OntologyMgrPackage.eNS_URI);

		// Obtain or create and register package
		OntologyMgrPackageImpl theOntologyMgrPackage = (OntologyMgrPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof OntologyMgrPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new OntologyMgrPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		OntologyPackageImpl theOntologyPackage = (OntologyPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OntologyPackage.eNS_URI) instanceof OntologyPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OntologyPackage.eNS_URI) : OntologyPackage.eINSTANCE);

		// Create package meta-data objects
		theOntologyMgrPackage.createPackageContents();
		theOntologyPackage.createPackageContents();

		// Initialize created meta-data
		theOntologyMgrPackage.initializePackageContents();
		theOntologyPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theOntologyMgrPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(OntologyMgrPackage.eNS_URI, theOntologyMgrPackage);
		return theOntologyMgrPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLibrary() {
		return libraryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLibrary_Owner() {
		return (EAttribute)libraryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLibrary_CreationDate() {
		return (EAttribute)libraryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLibrary_Ontologies() {
		return (EReference)libraryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLocalOntologyEntry() {
		return localOntologyEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLocalOntologyEntry_Path() {
		return (EAttribute)localOntologyEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLocalOntologyEntry_File() {
		return (EAttribute)localOntologyEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLocalOntologyEntry_MaintenanceFile() {
		return (EAttribute)localOntologyEntryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLocalOntologyEntry_ManagedOntology() {
		return (EReference)localOntologyEntryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGitRepoOntologyEntry() {
		return gitRepoOntologyEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGitRepoOntologyEntry_RepoURL() {
		return (EAttribute)gitRepoOntologyEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGitRepoOntologyEntry_GitUsername() {
		return (EAttribute)gitRepoOntologyEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGitRepoOntologyEntry_GitPassword() {
		return (EAttribute)gitRepoOntologyEntryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OntologyMgrFactory getOntologyMgrFactory() {
		return (OntologyMgrFactory)getEFactoryInstance();
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
		libraryEClass = createEClass(LIBRARY);
		createEAttribute(libraryEClass, LIBRARY__OWNER);
		createEAttribute(libraryEClass, LIBRARY__CREATION_DATE);
		createEReference(libraryEClass, LIBRARY__ONTOLOGIES);

		localOntologyEntryEClass = createEClass(LOCAL_ONTOLOGY_ENTRY);
		createEAttribute(localOntologyEntryEClass, LOCAL_ONTOLOGY_ENTRY__PATH);
		createEAttribute(localOntologyEntryEClass, LOCAL_ONTOLOGY_ENTRY__FILE);
		createEAttribute(localOntologyEntryEClass, LOCAL_ONTOLOGY_ENTRY__MAINTENANCE_FILE);
		createEReference(localOntologyEntryEClass, LOCAL_ONTOLOGY_ENTRY__MANAGED_ONTOLOGY);

		gitRepoOntologyEntryEClass = createEClass(GIT_REPO_ONTOLOGY_ENTRY);
		createEAttribute(gitRepoOntologyEntryEClass, GIT_REPO_ONTOLOGY_ENTRY__REPO_URL);
		createEAttribute(gitRepoOntologyEntryEClass, GIT_REPO_ONTOLOGY_ENTRY__GIT_USERNAME);
		createEAttribute(gitRepoOntologyEntryEClass, GIT_REPO_ONTOLOGY_ENTRY__GIT_PASSWORD);
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
		OntologyPackage theOntologyPackage = (OntologyPackage)EPackage.Registry.INSTANCE.getEPackage(OntologyPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		gitRepoOntologyEntryEClass.getESuperTypes().add(this.getLocalOntologyEntry());

		// Initialize classes, features, and operations; add parameters
		initEClass(libraryEClass, Library.class, "Library", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLibrary_Owner(), ecorePackage.getEString(), "owner", null, 0, 1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLibrary_CreationDate(), ecorePackage.getEString(), "creationDate", null, 0, 1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLibrary_Ontologies(), this.getLocalOntologyEntry(), null, "ontologies", null, 0, -1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(localOntologyEntryEClass, LocalOntologyEntry.class, "LocalOntologyEntry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLocalOntologyEntry_Path(), ecorePackage.getEString(), "path", null, 0, 1, LocalOntologyEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLocalOntologyEntry_File(), ecorePackage.getEString(), "file", "ontology.owl", 0, 1, LocalOntologyEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLocalOntologyEntry_MaintenanceFile(), ecorePackage.getEString(), "maintenanceFile", null, 0, 1, LocalOntologyEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLocalOntologyEntry_ManagedOntology(), theOntologyPackage.getOntology(), theOntologyPackage.getOntology_OntologyEntry(), "managedOntology", null, 0, 1, LocalOntologyEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(gitRepoOntologyEntryEClass, GitRepoOntologyEntry.class, "GitRepoOntologyEntry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGitRepoOntologyEntry_RepoURL(), ecorePackage.getEString(), "repoURL", null, 0, 1, GitRepoOntologyEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGitRepoOntologyEntry_GitUsername(), ecorePackage.getEString(), "gitUsername", null, 0, 1, GitRepoOntologyEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGitRepoOntologyEntry_GitPassword(), ecorePackage.getEString(), "gitPassword", null, 0, 1, GitRepoOntologyEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //OntologyMgrPackageImpl
