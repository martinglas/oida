/**
 */
package oida.impl;

import oida.*;

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
public class OidaFactoryImpl extends EFactoryImpl implements OidaFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OidaFactory init() {
		try {
			OidaFactory theOidaFactory = (OidaFactory)EPackage.Registry.INSTANCE.getEFactory(OidaPackage.eNS_URI);
			if (theOidaFactory != null) {
				return theOidaFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new OidaFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OidaFactoryImpl() {
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
			case OidaPackage.OIDA_SYSTEM: return createOIDASystem();
			case OidaPackage.MANAGED_ONTOLOGY: return createManagedOntology();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OIDASystem createOIDASystem() {
		OIDASystemImpl oidaSystem = new OIDASystemImpl();
		return oidaSystem;
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
	public OidaPackage getOidaPackage() {
		return (OidaPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static OidaPackage getPackage() {
		return OidaPackage.eINSTANCE;
	}

} //OidaFactoryImpl
