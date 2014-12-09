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
import oida.model.integration.transformation.mapping.MappingPackage;

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
public class DiffmodelPackageImpl extends EPackageImpl implements DiffmodelPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referenceMatchingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass diffModelEClass = null;

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
	 * @see oida.model.integration.matching.diffmodel.DiffmodelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DiffmodelPackageImpl() {
		super(eNS_URI, DiffmodelFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link DiffmodelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DiffmodelPackage init() {
		if (isInited) return (DiffmodelPackage)EPackage.Registry.INSTANCE.getEPackage(DiffmodelPackage.eNS_URI);

		// Obtain or create and register package
		DiffmodelPackageImpl theDiffmodelPackage = (DiffmodelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DiffmodelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new DiffmodelPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		MappingPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theDiffmodelPackage.createPackageContents();

		// Initialize created meta-data
		theDiffmodelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDiffmodelPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(DiffmodelPackage.eNS_URI, theDiffmodelPackage);
		return theDiffmodelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReferenceMatching() {
		return referenceMatchingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReferenceMatching_SourceReferenceMapping() {
		return (EReference)referenceMatchingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReferenceMatching_TargetReferenceMapping() {
		return (EReference)referenceMatchingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReferenceMatching_Confirmed() {
		return (EAttribute)referenceMatchingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReferenceMatching_Reported() {
		return (EAttribute)referenceMatchingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReferenceMatching_Comment() {
		return (EAttribute)referenceMatchingEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDiffModel() {
		return diffModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiffModel_ReferenceMatchings() {
		return (EReference)diffModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiffModel_TheirMatchings() {
		return (EReference)diffModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiffmodelFactory getDiffmodelFactory() {
		return (DiffmodelFactory)getEFactoryInstance();
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
		referenceMatchingEClass = createEClass(REFERENCE_MATCHING);
		createEReference(referenceMatchingEClass, REFERENCE_MATCHING__SOURCE_REFERENCE_MAPPING);
		createEReference(referenceMatchingEClass, REFERENCE_MATCHING__TARGET_REFERENCE_MAPPING);
		createEAttribute(referenceMatchingEClass, REFERENCE_MATCHING__CONFIRMED);
		createEAttribute(referenceMatchingEClass, REFERENCE_MATCHING__REPORTED);
		createEAttribute(referenceMatchingEClass, REFERENCE_MATCHING__COMMENT);

		diffModelEClass = createEClass(DIFF_MODEL);
		createEReference(diffModelEClass, DIFF_MODEL__REFERENCE_MATCHINGS);
		createEReference(diffModelEClass, DIFF_MODEL__THEIR_MATCHINGS);
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
		MappingPackage theMappingPackage = (MappingPackage)EPackage.Registry.INSTANCE.getEPackage(MappingPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(referenceMatchingEClass, ReferenceMatching.class, "ReferenceMatching", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReferenceMatching_SourceReferenceMapping(), theMappingPackage.getReferenceMapping(), null, "sourceReferenceMapping", null, 0, 1, ReferenceMatching.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReferenceMatching_TargetReferenceMapping(), theMappingPackage.getReferenceMapping(), null, "targetReferenceMapping", null, 0, 1, ReferenceMatching.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceMatching_Confirmed(), ecorePackage.getEBoolean(), "confirmed", "false", 0, 1, ReferenceMatching.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceMatching_Reported(), ecorePackage.getEBoolean(), "reported", "false", 0, 1, ReferenceMatching.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceMatching_Comment(), ecorePackage.getEString(), "comment", " ", 0, 1, ReferenceMatching.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(diffModelEClass, DiffModel.class, "DiffModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDiffModel_ReferenceMatchings(), this.getReferenceMatching(), null, "referenceMatchings", null, 0, -1, DiffModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDiffModel_TheirMatchings(), this.getReferenceMatching(), null, "theirMatchings", null, 0, -1, DiffModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //DiffmodelPackageImpl
