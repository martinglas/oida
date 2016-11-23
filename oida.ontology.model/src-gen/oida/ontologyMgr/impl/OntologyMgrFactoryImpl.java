/**
 */
package oida.ontologyMgr.impl;

import oida.ontologyMgr.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OntologyMgrFactoryImpl extends EFactoryImpl implements OntologyMgrFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OntologyMgrFactory init() {
		try {
			OntologyMgrFactory theOntologyMgrFactory = (OntologyMgrFactory)EPackage.Registry.INSTANCE.getEFactory(OntologyMgrPackage.eNS_URI);
			if (theOntologyMgrFactory != null) {
				return theOntologyMgrFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new OntologyMgrFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OntologyMgrFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case OntologyMgrPackage.LIBRARY: return createLibrary();
			case OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY: return createLocalOntologyEntry();
			case OntologyMgrPackage.GIT_REPO_ONTOLOGY_ENTRY: return createGitRepoOntologyEntry();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Library createLibrary() {
		LibraryImpl library = new LibraryImpl();
		return library;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LocalOntologyEntry createLocalOntologyEntry() {
		LocalOntologyEntryImpl localOntologyEntry = new LocalOntologyEntryImpl();
		return localOntologyEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GitRepoOntologyEntry createGitRepoOntologyEntry() {
		GitRepoOntologyEntryImpl gitRepoOntologyEntry = new GitRepoOntologyEntryImpl();
		return gitRepoOntologyEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OntologyMgrPackage getOntologyMgrPackage() {
		return (OntologyMgrPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static OntologyMgrPackage getPackage() {
		return OntologyMgrPackage.eINSTANCE;
	}

} //OntologyMgrFactoryImpl
