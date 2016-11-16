/**
 */
package oida.ontologyMgr.impl;

import oida.ontologyMgr.GitRepoOntology;
import oida.ontologyMgr.Library;
import oida.ontologyMgr.LocalOntology;
import oida.ontologyMgr.ManagedOntology;
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
	private EClass localOntologyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gitRepoOntologyEClass = null;

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

		// Create package meta-data objects
		theOntologyMgrPackage.createPackageContents();

		// Initialize created meta-data
		theOntologyMgrPackage.initializePackageContents();

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
	public EReference getLibrary_ActiveOntology() {
		return (EReference)libraryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLocalOntology() {
		return localOntologyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLocalOntology_Path() {
		return (EAttribute)localOntologyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLocalOntology_File() {
		return (EAttribute)localOntologyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLocalOntology_MaintenanceFile() {
		return (EAttribute)localOntologyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGitRepoOntology() {
		return gitRepoOntologyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGitRepoOntology_RepoURL() {
		return (EAttribute)gitRepoOntologyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGitRepoOntology_GitUsername() {
		return (EAttribute)gitRepoOntologyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGitRepoOntology_GitPassword() {
		return (EAttribute)gitRepoOntologyEClass.getEStructuralFeatures().get(2);
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
	public EAttribute getManagedOntology_IsConsistent() {
		return (EAttribute)managedOntologyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getManagedOntology_LocalOntology() {
		return (EReference)managedOntologyEClass.getEStructuralFeatures().get(1);
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
		createEReference(libraryEClass, LIBRARY__ACTIVE_ONTOLOGY);

		localOntologyEClass = createEClass(LOCAL_ONTOLOGY);
		createEAttribute(localOntologyEClass, LOCAL_ONTOLOGY__PATH);
		createEAttribute(localOntologyEClass, LOCAL_ONTOLOGY__FILE);
		createEAttribute(localOntologyEClass, LOCAL_ONTOLOGY__MAINTENANCE_FILE);

		gitRepoOntologyEClass = createEClass(GIT_REPO_ONTOLOGY);
		createEAttribute(gitRepoOntologyEClass, GIT_REPO_ONTOLOGY__REPO_URL);
		createEAttribute(gitRepoOntologyEClass, GIT_REPO_ONTOLOGY__GIT_USERNAME);
		createEAttribute(gitRepoOntologyEClass, GIT_REPO_ONTOLOGY__GIT_PASSWORD);

		managedOntologyEClass = createEClass(MANAGED_ONTOLOGY);
		createEAttribute(managedOntologyEClass, MANAGED_ONTOLOGY__IS_CONSISTENT);
		createEReference(managedOntologyEClass, MANAGED_ONTOLOGY__LOCAL_ONTOLOGY);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		gitRepoOntologyEClass.getESuperTypes().add(this.getLocalOntology());

		// Initialize classes, features, and operations; add parameters
		initEClass(libraryEClass, Library.class, "Library", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLibrary_Owner(), ecorePackage.getEString(), "owner", null, 0, 1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLibrary_CreationDate(), ecorePackage.getEString(), "creationDate", null, 0, 1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLibrary_Ontologies(), this.getLocalOntology(), null, "ontologies", null, 0, -1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLibrary_ActiveOntology(), this.getLocalOntology(), null, "activeOntology", null, 0, 1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(localOntologyEClass, LocalOntology.class, "LocalOntology", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLocalOntology_Path(), ecorePackage.getEString(), "path", null, 0, 1, LocalOntology.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLocalOntology_File(), ecorePackage.getEString(), "file", "ontology.owl", 0, 1, LocalOntology.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLocalOntology_MaintenanceFile(), ecorePackage.getEString(), "maintenanceFile", null, 0, 1, LocalOntology.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(gitRepoOntologyEClass, GitRepoOntology.class, "GitRepoOntology", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGitRepoOntology_RepoURL(), ecorePackage.getEString(), "repoURL", null, 0, 1, GitRepoOntology.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGitRepoOntology_GitUsername(), ecorePackage.getEString(), "gitUsername", null, 0, 1, GitRepoOntology.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGitRepoOntology_GitPassword(), ecorePackage.getEString(), "gitPassword", null, 0, 1, GitRepoOntology.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(managedOntologyEClass, ManagedOntology.class, "ManagedOntology", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getManagedOntology_IsConsistent(), ecorePackage.getEBoolean(), "IsConsistent", null, 0, 1, ManagedOntology.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getManagedOntology_LocalOntology(), this.getLocalOntology(), null, "LocalOntology", null, 1, 1, ManagedOntology.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //OntologyMgrPackageImpl
