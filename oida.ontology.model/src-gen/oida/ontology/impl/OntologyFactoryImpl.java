/**
 */
package oida.ontology.impl;

import oida.ontology.*;

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
public class OntologyFactoryImpl extends EFactoryImpl implements OntologyFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OntologyFactory init() {
		try {
			OntologyFactory theOntologyFactory = (OntologyFactory)EPackage.Registry.INSTANCE.getEFactory(OntologyPackage.eNS_URI);
			if (theOntologyFactory != null) {
				return theOntologyFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new OntologyFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OntologyFactoryImpl() {
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
			case OntologyPackage.ONTOLOGY_CLASS: return createOntologyClass();
			case OntologyPackage.ONTOLOGY_ENTITY: return createOntologyEntity();
			case OntologyPackage.ONTOLOGY: return createOntology();
			case OntologyPackage.ONTOLOGY_INDIVIDUAL: return createOntologyIndividual();
			case OntologyPackage.ONTOLOGY_ITEM: return createOntologyItem();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OntologyClass createOntologyClass() {
		OntologyClassImpl ontologyClass = new OntologyClassImpl();
		return ontologyClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OntologyEntity createOntologyEntity() {
		OntologyEntityImpl ontologyEntity = new OntologyEntityImpl();
		return ontologyEntity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ontology createOntology() {
		OntologyImpl ontology = new OntologyImpl();
		return ontology;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OntologyIndividual createOntologyIndividual() {
		OntologyIndividualImpl ontologyIndividual = new OntologyIndividualImpl();
		return ontologyIndividual;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OntologyItem createOntologyItem() {
		OntologyItemImpl ontologyItem = new OntologyItemImpl();
		return ontologyItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OntologyPackage getOntologyPackage() {
		return (OntologyPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static OntologyPackage getPackage() {
		return OntologyPackage.eINSTANCE;
	}

} //OntologyFactoryImpl
