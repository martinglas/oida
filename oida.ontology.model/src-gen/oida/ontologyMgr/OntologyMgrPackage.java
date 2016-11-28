/**
 */
package oida.ontologyMgr;

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
 * @see oida.ontologyMgr.OntologyMgrFactory
 * @model kind="package"
 * @generated
 */
public interface OntologyMgrPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ontologyMgr";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://net/bauhaus-luftfahrt/oida/ontologyMgr";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "net.bauhaus-luftfahrt.oida.ontologyMgr";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OntologyMgrPackage eINSTANCE = oida.ontologyMgr.impl.OntologyMgrPackageImpl.init();

	/**
	 * The meta object id for the '{@link oida.ontologyMgr.impl.LibraryImpl <em>Library</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oida.ontologyMgr.impl.LibraryImpl
	 * @see oida.ontologyMgr.impl.OntologyMgrPackageImpl#getLibrary()
	 * @generated
	 */
	int LIBRARY = 0;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__OWNER = 0;

	/**
	 * The feature id for the '<em><b>Creation Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__CREATION_DATE = 1;

	/**
	 * The feature id for the '<em><b>Ontologies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__ONTOLOGIES = 2;

	/**
	 * The feature id for the '<em><b>Reference Ontology</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__REFERENCE_ONTOLOGY = 3;

	/**
	 * The number of structural features of the '<em>Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link oida.ontologyMgr.impl.LocalOntologyEntryImpl <em>Local Ontology Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oida.ontologyMgr.impl.LocalOntologyEntryImpl
	 * @see oida.ontologyMgr.impl.OntologyMgrPackageImpl#getLocalOntologyEntry()
	 * @generated
	 */
	int LOCAL_ONTOLOGY_ENTRY = 1;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_ONTOLOGY_ENTRY__PATH = 0;

	/**
	 * The feature id for the '<em><b>File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_ONTOLOGY_ENTRY__FILE = 1;

	/**
	 * The feature id for the '<em><b>Maintenance File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_ONTOLOGY_ENTRY__MAINTENANCE_FILE = 2;

	/**
	 * The feature id for the '<em><b>Managed Ontology</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_ONTOLOGY_ENTRY__MANAGED_ONTOLOGY = 3;

	/**
	 * The number of structural features of the '<em>Local Ontology Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_ONTOLOGY_ENTRY_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Local Ontology Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_ONTOLOGY_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link oida.ontologyMgr.impl.GitRepoOntologyEntryImpl <em>Git Repo Ontology Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oida.ontologyMgr.impl.GitRepoOntologyEntryImpl
	 * @see oida.ontologyMgr.impl.OntologyMgrPackageImpl#getGitRepoOntologyEntry()
	 * @generated
	 */
	int GIT_REPO_ONTOLOGY_ENTRY = 2;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPO_ONTOLOGY_ENTRY__PATH = LOCAL_ONTOLOGY_ENTRY__PATH;

	/**
	 * The feature id for the '<em><b>File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPO_ONTOLOGY_ENTRY__FILE = LOCAL_ONTOLOGY_ENTRY__FILE;

	/**
	 * The feature id for the '<em><b>Maintenance File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPO_ONTOLOGY_ENTRY__MAINTENANCE_FILE = LOCAL_ONTOLOGY_ENTRY__MAINTENANCE_FILE;

	/**
	 * The feature id for the '<em><b>Managed Ontology</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPO_ONTOLOGY_ENTRY__MANAGED_ONTOLOGY = LOCAL_ONTOLOGY_ENTRY__MANAGED_ONTOLOGY;

	/**
	 * The feature id for the '<em><b>Repo URL</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPO_ONTOLOGY_ENTRY__REPO_URL = LOCAL_ONTOLOGY_ENTRY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Git Username</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPO_ONTOLOGY_ENTRY__GIT_USERNAME = LOCAL_ONTOLOGY_ENTRY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Git Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPO_ONTOLOGY_ENTRY__GIT_PASSWORD = LOCAL_ONTOLOGY_ENTRY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Git Repo Ontology Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPO_ONTOLOGY_ENTRY_FEATURE_COUNT = LOCAL_ONTOLOGY_ENTRY_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Git Repo Ontology Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPO_ONTOLOGY_ENTRY_OPERATION_COUNT = LOCAL_ONTOLOGY_ENTRY_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link oida.ontologyMgr.Library <em>Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Library</em>'.
	 * @see oida.ontologyMgr.Library
	 * @generated
	 */
	EClass getLibrary();

	/**
	 * Returns the meta object for the attribute '{@link oida.ontologyMgr.Library#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Owner</em>'.
	 * @see oida.ontologyMgr.Library#getOwner()
	 * @see #getLibrary()
	 * @generated
	 */
	EAttribute getLibrary_Owner();

	/**
	 * Returns the meta object for the attribute '{@link oida.ontologyMgr.Library#getCreationDate <em>Creation Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Creation Date</em>'.
	 * @see oida.ontologyMgr.Library#getCreationDate()
	 * @see #getLibrary()
	 * @generated
	 */
	EAttribute getLibrary_CreationDate();

	/**
	 * Returns the meta object for the containment reference list '{@link oida.ontologyMgr.Library#getOntologies <em>Ontologies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ontologies</em>'.
	 * @see oida.ontologyMgr.Library#getOntologies()
	 * @see #getLibrary()
	 * @generated
	 */
	EReference getLibrary_Ontologies();

	/**
	 * Returns the meta object for the reference '{@link oida.ontologyMgr.Library#getReferenceOntology <em>Reference Ontology</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference Ontology</em>'.
	 * @see oida.ontologyMgr.Library#getReferenceOntology()
	 * @see #getLibrary()
	 * @generated
	 */
	EReference getLibrary_ReferenceOntology();

	/**
	 * Returns the meta object for class '{@link oida.ontologyMgr.LocalOntologyEntry <em>Local Ontology Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Local Ontology Entry</em>'.
	 * @see oida.ontologyMgr.LocalOntologyEntry
	 * @generated
	 */
	EClass getLocalOntologyEntry();

	/**
	 * Returns the meta object for the attribute '{@link oida.ontologyMgr.LocalOntologyEntry#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see oida.ontologyMgr.LocalOntologyEntry#getPath()
	 * @see #getLocalOntologyEntry()
	 * @generated
	 */
	EAttribute getLocalOntologyEntry_Path();

	/**
	 * Returns the meta object for the attribute '{@link oida.ontologyMgr.LocalOntologyEntry#getFile <em>File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File</em>'.
	 * @see oida.ontologyMgr.LocalOntologyEntry#getFile()
	 * @see #getLocalOntologyEntry()
	 * @generated
	 */
	EAttribute getLocalOntologyEntry_File();

	/**
	 * Returns the meta object for the attribute '{@link oida.ontologyMgr.LocalOntologyEntry#getMaintenanceFile <em>Maintenance File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maintenance File</em>'.
	 * @see oida.ontologyMgr.LocalOntologyEntry#getMaintenanceFile()
	 * @see #getLocalOntologyEntry()
	 * @generated
	 */
	EAttribute getLocalOntologyEntry_MaintenanceFile();

	/**
	 * Returns the meta object for the reference '{@link oida.ontologyMgr.LocalOntologyEntry#getManagedOntology <em>Managed Ontology</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Managed Ontology</em>'.
	 * @see oida.ontologyMgr.LocalOntologyEntry#getManagedOntology()
	 * @see #getLocalOntologyEntry()
	 * @generated
	 */
	EReference getLocalOntologyEntry_ManagedOntology();

	/**
	 * Returns the meta object for class '{@link oida.ontologyMgr.GitRepoOntologyEntry <em>Git Repo Ontology Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Git Repo Ontology Entry</em>'.
	 * @see oida.ontologyMgr.GitRepoOntologyEntry
	 * @generated
	 */
	EClass getGitRepoOntologyEntry();

	/**
	 * Returns the meta object for the attribute '{@link oida.ontologyMgr.GitRepoOntologyEntry#getRepoURL <em>Repo URL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Repo URL</em>'.
	 * @see oida.ontologyMgr.GitRepoOntologyEntry#getRepoURL()
	 * @see #getGitRepoOntologyEntry()
	 * @generated
	 */
	EAttribute getGitRepoOntologyEntry_RepoURL();

	/**
	 * Returns the meta object for the attribute '{@link oida.ontologyMgr.GitRepoOntologyEntry#getGitUsername <em>Git Username</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Git Username</em>'.
	 * @see oida.ontologyMgr.GitRepoOntologyEntry#getGitUsername()
	 * @see #getGitRepoOntologyEntry()
	 * @generated
	 */
	EAttribute getGitRepoOntologyEntry_GitUsername();

	/**
	 * Returns the meta object for the attribute '{@link oida.ontologyMgr.GitRepoOntologyEntry#getGitPassword <em>Git Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Git Password</em>'.
	 * @see oida.ontologyMgr.GitRepoOntologyEntry#getGitPassword()
	 * @see #getGitRepoOntologyEntry()
	 * @generated
	 */
	EAttribute getGitRepoOntologyEntry_GitPassword();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	OntologyMgrFactory getOntologyMgrFactory();

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
		 * The meta object literal for the '{@link oida.ontologyMgr.impl.LibraryImpl <em>Library</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oida.ontologyMgr.impl.LibraryImpl
		 * @see oida.ontologyMgr.impl.OntologyMgrPackageImpl#getLibrary()
		 * @generated
		 */
		EClass LIBRARY = eINSTANCE.getLibrary();

		/**
		 * The meta object literal for the '<em><b>Owner</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LIBRARY__OWNER = eINSTANCE.getLibrary_Owner();

		/**
		 * The meta object literal for the '<em><b>Creation Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LIBRARY__CREATION_DATE = eINSTANCE.getLibrary_CreationDate();

		/**
		 * The meta object literal for the '<em><b>Ontologies</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIBRARY__ONTOLOGIES = eINSTANCE.getLibrary_Ontologies();

		/**
		 * The meta object literal for the '<em><b>Reference Ontology</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIBRARY__REFERENCE_ONTOLOGY = eINSTANCE.getLibrary_ReferenceOntology();

		/**
		 * The meta object literal for the '{@link oida.ontologyMgr.impl.LocalOntologyEntryImpl <em>Local Ontology Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oida.ontologyMgr.impl.LocalOntologyEntryImpl
		 * @see oida.ontologyMgr.impl.OntologyMgrPackageImpl#getLocalOntologyEntry()
		 * @generated
		 */
		EClass LOCAL_ONTOLOGY_ENTRY = eINSTANCE.getLocalOntologyEntry();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCAL_ONTOLOGY_ENTRY__PATH = eINSTANCE.getLocalOntologyEntry_Path();

		/**
		 * The meta object literal for the '<em><b>File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCAL_ONTOLOGY_ENTRY__FILE = eINSTANCE.getLocalOntologyEntry_File();

		/**
		 * The meta object literal for the '<em><b>Maintenance File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCAL_ONTOLOGY_ENTRY__MAINTENANCE_FILE = eINSTANCE.getLocalOntologyEntry_MaintenanceFile();

		/**
		 * The meta object literal for the '<em><b>Managed Ontology</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOCAL_ONTOLOGY_ENTRY__MANAGED_ONTOLOGY = eINSTANCE.getLocalOntologyEntry_ManagedOntology();

		/**
		 * The meta object literal for the '{@link oida.ontologyMgr.impl.GitRepoOntologyEntryImpl <em>Git Repo Ontology Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oida.ontologyMgr.impl.GitRepoOntologyEntryImpl
		 * @see oida.ontologyMgr.impl.OntologyMgrPackageImpl#getGitRepoOntologyEntry()
		 * @generated
		 */
		EClass GIT_REPO_ONTOLOGY_ENTRY = eINSTANCE.getGitRepoOntologyEntry();

		/**
		 * The meta object literal for the '<em><b>Repo URL</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_REPO_ONTOLOGY_ENTRY__REPO_URL = eINSTANCE.getGitRepoOntologyEntry_RepoURL();

		/**
		 * The meta object literal for the '<em><b>Git Username</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_REPO_ONTOLOGY_ENTRY__GIT_USERNAME = eINSTANCE.getGitRepoOntologyEntry_GitUsername();

		/**
		 * The meta object literal for the '<em><b>Git Password</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_REPO_ONTOLOGY_ENTRY__GIT_PASSWORD = eINSTANCE.getGitRepoOntologyEntry_GitPassword();

	}

} //OntologyMgrPackage
