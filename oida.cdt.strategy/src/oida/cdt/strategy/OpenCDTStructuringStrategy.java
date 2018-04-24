package oida.cdt.strategy;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import model.base.BasePackage;
import model.engineering.EngineeringPackage;
import oida.bridge.model.emf.strategy.EMFStructuringStrategy;
import oida.bridge.service.IOIDABridge.OntologyObjectProperties;

/**
 * 
 * @author Michael Shamiyeh
 * @since 24.04.2018
 *
 */
public class OpenCDTStructuringStrategy extends EMFStructuringStrategy {
    @Override
    protected EPackage[] getEMFPackageInformation() {
	return new EPackage[]{ BasePackage.eINSTANCE, EngineeringPackage.eINSTANCE };
    }
    
    @Override
    public StructuringAdvice determineStructuringAdviceAfterCreate(Object createdModelElement) {
	return null;
    }

    @Override
    public StructuringAdvice determineStructuringAdviceAfterSet(EAttribute changedAttribute) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public OntologyObjectProperties determineObjectPropertyRelation(EStructuralFeature feature) {
	// TODO Auto-generated method stub
	return null;
    }
}
