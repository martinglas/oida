package oida.bridge.model.emf.metamodel.ontology;

import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import oida.bridge.model.changehandler.AbstractModelChangeHandler;
import oida.bridge.model.ontology.OIDAModelBaseOntology;
import oida.bridge.model.strategy.IRenamerStrategy;
import oida.bridge.model.strategy.IStructuringStrategy;
import oida.ontology.Ontology;
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
	public static final String EMFONTOLOGY_REFERENCE_NAME = "emf_reference";
	public static final String EMFONTOLOGY_REFERENCE_BIDIR_NAME = "emf_reference_bidirectional";
	
	private OntologyObjectProperty emfReferenceObjectProperty;
	private OntologyObjectProperty emfReferenceBiDirectionalObjectProperty;
	
	private static EMFMetaModelOntology INSTANCE;

	public static EMFMetaModelOntology getInstance() {
		if (INSTANCE == null)
			INSTANCE = new EMFMetaModelOntology();

		return INSTANCE;
	}

	private EMFMetaModelOntology() {
	}

	public IOntologyManager createMetaModelOntology(IRenamerStrategy renamerStrategy, IStructuringStrategy structuringStrategy, IOntologyManager manager, Ontology referenceOntology) {
		setRenamerStrategy(renamerStrategy);
		setStructuringStrategy(structuringStrategy);
		
		// check if the passed ontology manager has imports, to determine if the ontology has been created already: 
		if (manager.getOntology().getImports().size() > 0)
			setModelOntologyManager(manager);
		else {
			// add reference ontology to includes:
			try {
				manager.addImportDeclaration(referenceOntology);
			} catch (OntologyManagerException e) {
				e.printStackTrace();
			}
			
			initializeModelOntology(manager);
		}
		
		extractMappings(manager);

		return getModelOntologyManager();
	}

	@Override
	protected IOntologyManager initializeModelOntology(IOntologyManager modelOntologyManager) {
		setModelOntologyManager(modelOntologyManager);

		try {
			modelOntologyManager.addImportDeclaration(OIDAModelBaseOntology.getInstance().getOntologyManager().getOntology());

			emfReferenceObjectProperty = modelOntologyManager.createObjectProperty(EMFONTOLOGY_REFERENCE_NAME);
			modelOntologyManager.setObjectPropertyCharacteristics(emfReferenceObjectProperty, false, false, false, false, false, false, false);

			emfReferenceBiDirectionalObjectProperty = modelOntologyManager.createObjectProperty(EMFONTOLOGY_REFERENCE_BIDIR_NAME);
			modelOntologyManager.setObjectPropertyCharacteristics(emfReferenceBiDirectionalObjectProperty, false, false, false, true, false, false, false);
		} catch (OntologyManagerException e) {
			e.printStackTrace();
		}
		
		extractClassHierarchy((EPackage[])getStructuringStrategy().getMetaModelInformationObject());
		extractObjectPropertyHierarchy((EPackage[])getStructuringStrategy().getMetaModelInformationObject());

		return modelOntologyManager;
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
				ontologyManager.assignSubObjectPropertyToSuperObjectProperty(referenceObjectProperty, emfReferenceObjectProperty);
			else
				ontologyManager.assignSubObjectPropertyToSuperObjectProperty(referenceObjectProperty, emfReferenceBiDirectionalObjectProperty);

			if (strFeature.getEOpposite() != null && getOntologyEntityForModelElement(strFeature.getEOpposite().getEReferenceType()) == null) {
				Optional<OntologyClass> domainClass = getOntologyClassForModelElement(strFeature.getEOpposite().getEReferenceType());
				if (domainClass.isPresent())
					ontologyManager.assignObjectPropertyDomain(referenceObjectProperty, domainClass.get());
			}
		}
	}

	@Override
	public void closeModelOntology() {
		// TODO Auto-generated method stub

	}

	public OntologyObjectProperty getEmfReferenceObjectProperty() {
		return emfReferenceObjectProperty;
	}

	public OntologyObjectProperty getEmfReferenceBiDirectionalObjectProperty() {
		return emfReferenceBiDirectionalObjectProperty;
	}
}
