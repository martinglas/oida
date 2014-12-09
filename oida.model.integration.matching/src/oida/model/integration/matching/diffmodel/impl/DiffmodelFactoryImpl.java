/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package oida.model.integration.matching.diffmodel.impl;

import oida.model.integration.matching.diffmodel.DiffModel;
import oida.model.integration.matching.diffmodel.DiffmodelFactory;
import oida.model.integration.matching.diffmodel.DiffmodelPackage;
import oida.model.integration.matching.diffmodel.ReferenceMatching;

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
public class DiffmodelFactoryImpl extends EFactoryImpl implements DiffmodelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DiffmodelFactory init() {
		try {
			DiffmodelFactory theDiffmodelFactory = (DiffmodelFactory)EPackage.Registry.INSTANCE.getEFactory("http://bhl.net/cdt/matching.diffmodel"); 
			if (theDiffmodelFactory != null) {
				return theDiffmodelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new DiffmodelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiffmodelFactoryImpl() {
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
			case DiffmodelPackage.REFERENCE_MATCHING: return createReferenceMatching();
			case DiffmodelPackage.DIFF_MODEL: return createDiffModel();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceMatching createReferenceMatching() {
		ReferenceMatchingImpl referenceMatching = new ReferenceMatchingImpl();
		return referenceMatching;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiffModel createDiffModel() {
		DiffModelImpl diffModel = new DiffModelImpl();
		return diffModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiffmodelPackage getDiffmodelPackage() {
		return (DiffmodelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static DiffmodelPackage getPackage() {
		return DiffmodelPackage.eINSTANCE;
	}

} //DiffmodelFactoryImpl
