package oida.bridge.model.strategy.emf;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import oida.bridge.model.strategy.IRenamerStrategy;
import oida.util.constants.StringConstants;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-21
 *
 */
public abstract class EMFRenamerStrategy implements IRenamerStrategy {
	@Override
	public String getClassName(Object classObject) {
		return ((EClass)classObject).getName();
	}

	@Override
	public String getRelationID(Object relationObject) {
		EStructuralFeature strFeature = (EStructuralFeature)relationObject;
		return strFeature.getEContainingClass().getName() + StringConstants.UNDERSCORE + strFeature.getName();
	}
}
