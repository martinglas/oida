package oida.bridge.model.emf.changehandler.ontology;

import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import oida.bridge.model.changehandler.AbstractModelChangeHandler;
import oida.ontology.OntologyClass;
import oida.ontology.OntologyEntity;
import oida.ontology.OntologyObjectProperty;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.OntologyManagerException;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-25
 *
 */
public class EMFMetaModelOntology extends AbstractModelChangeHandler {
	private static EMFMetaModelOntology INSTANCE;

	public static EMFMetaModelOntology getInstance() {
		if (INSTANCE == null)
			INSTANCE = new EMFMetaModelOntology();

		return INSTANCE;
	}

	private EMFMetaModelOntology() {
	}

	@Override
	protected IOntologyManager initializeModelOntology(IOntologyManager modelOntologyManager) {
		setModelOntologyManager(modelOntologyManager);

		try {
			modelOntologyManager.addImportDeclaration(EMFOntology.getInstance().getOntologyManager().getOntology());
		} catch (OntologyManagerException e) {
			e.printStackTrace();
		}

		return modelOntologyManager;
	}

	public void initialize(IOntologyManager ontologyManager, EPackage[] ePackages) {
		initializeModelOntology(ontologyManager);

		extractClassHierarchy(ePackages);
		extractObjectPropertyHierarchy(ePackages);
	}

	private void extractClassHierarchy(EPackage[] ePackages) {
		for (EPackage ePackage : ePackages) {
			for (EObject eObject : ePackage.eContents()) {
				if (eObject instanceof EClass) {
					createOntologyClassHierarchyForModelElement((EClass)eObject, getModelOntologyManager());
				}
			}

			extractClassHierarchy((EPackage[])ePackage.getESubpackages().toArray());
		}
	}

	private void extractObjectPropertyHierarchy(EPackage[] ePackages) {
		for (EPackage ePackage : ePackages) {
			for (EObject eObject : ePackage.eContents()) {
				if (eObject instanceof EClass) {
					createOntologyObjectPropertiesForModelClassReferences((EClass)eObject, getModelOntologyManager());
				}
			}

			extractObjectPropertyHierarchy((EPackage[])ePackage.getESubpackages().toArray());
		}
	}

	private OntologyClass createOntologyClassHierarchyForModelElement(EClass eClass, IOntologyManager ontologyManager) {
		Optional<OntologyEntity> optOntologyClass = getOntologyEntityForModelElement(eClass);

		if (!optOntologyClass.isPresent()) {
			OntologyClass oCl = createOntologyClassForMetaModelClass(eClass);

			for (EClass superClass : eClass.getESuperTypes()) {
				OntologyClass superOCl = createOntologyClassHierarchyForModelElement(superClass, ontologyManager);
				ontologyManager.assignSubClassToSuperClass(oCl, superOCl);
			}

			return oCl;
		} else
			return (OntologyClass)optOntologyClass.get();
	}

	// TODO Range <-> Domain???!!!
	private void createOntologyObjectPropertiesForModelClassReferences(EClass eClass, IOntologyManager ontologyManager) {
		for (EReference strFeature : eClass.getEAllReferences()) {
			String relationID = getRenamerStrategy().getRelationID(strFeature);
			OntologyObjectProperty referenceObjectProperty = createOntologyObjectPropertyForMetaModelRelation(relationID, getOntologyClassForModelElement(eClass).get());

			if (strFeature.getEOpposite() == null)
				ontologyManager.assignSubObjectPropertyToSuperObjectProperty(referenceObjectProperty, EMFOntology.getInstance().getEmfReferenceObjectProperty());
			else
				ontologyManager.assignSubObjectPropertyToSuperObjectProperty(referenceObjectProperty, EMFOntology.getInstance().getEmfReferenceBiDirectionalObjectProperty());

			if (strFeature.getEOpposite() != null && getOntologyEntityForModelElement(strFeature.getEOpposite().getEReferenceType()) == null) {
				Optional<OntologyClass> domainClass = getOntologyClassForModelElement(strFeature.getEOpposite().getEReferenceType());
				if (domainClass.isPresent())
					ontologyManager.assignObjectPropertyDomain(referenceObjectProperty, domainClass.get());
			}
		}
	}

	@Override
	public String getModelOntologyPrefix() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModelOntologyPrefix() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeModelOntology() {
		// TODO Auto-generated method stub

	}

}
