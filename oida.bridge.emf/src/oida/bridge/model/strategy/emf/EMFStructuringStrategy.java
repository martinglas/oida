package oida.bridge.model.strategy.emf;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;

import oida.bridge.model.strategy.IStructuringStrategy;
import oida.bridge.service.IOIDABridge.OntologyObjectProperties;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-21
 *
 */
public class EMFStructuringStrategy implements IStructuringStrategy {

	@Override
	public StructuringAdvice determineStructuringAdviceAfterCreate(Object createdModelElement) {
		// TODO Auto-generated method stub
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
