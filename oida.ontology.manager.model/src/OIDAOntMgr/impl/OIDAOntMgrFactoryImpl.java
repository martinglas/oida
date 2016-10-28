/**
 */
package OIDAOntMgr.impl;

import OIDAOntMgr.*;

import org.apache.jena.ontology.OntModel;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
public class OIDAOntMgrFactoryImpl extends EFactoryImpl implements OIDAOntMgrFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OIDAOntMgrFactory init() {
		try {
			OIDAOntMgrFactory theOIDAOntMgrFactory = (OIDAOntMgrFactory)EPackage.Registry.INSTANCE.getEFactory(OIDAOntMgrPackage.eNS_URI);
			if (theOIDAOntMgrFactory != null) {
				return theOIDAOntMgrFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new OIDAOntMgrFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OIDAOntMgrFactoryImpl() {
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
			case OIDAOntMgrPackage.LIBRARY: return createLibrary();
			case OIDAOntMgrPackage.LOCAL_ONTOLOGY: return createLocalOntology();
			case OIDAOntMgrPackage.GIT_REPO_ONTOLOGY: return createGitRepoOntology();
			case OIDAOntMgrPackage.MANAGED_ONTOLOGY: return createManagedOntology();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case OIDAOntMgrPackage.ONT_MODEL:
				return createOntModelFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case OIDAOntMgrPackage.ONT_MODEL:
				return convertOntModelToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
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
	public LocalOntology createLocalOntology() {
		LocalOntologyImpl localOntology = new LocalOntologyImpl();
		return localOntology;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GitRepoOntology createGitRepoOntology() {
		GitRepoOntologyImpl gitRepoOntology = new GitRepoOntologyImpl();
		return gitRepoOntology;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManagedOntology createManagedOntology() {
		ManagedOntologyImpl managedOntology = new ManagedOntologyImpl();
		return managedOntology;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OntModel createOntModelFromString(EDataType eDataType, String initialValue) {
		return (OntModel)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOntModelToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OIDAOntMgrPackage getOIDAOntMgrPackage() {
		return (OIDAOntMgrPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static OIDAOntMgrPackage getPackage() {
		return OIDAOntMgrPackage.eINSTANCE;
	}

} //OIDAOntMgrFactoryImpl
