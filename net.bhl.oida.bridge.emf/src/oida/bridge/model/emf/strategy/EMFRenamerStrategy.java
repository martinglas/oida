package oida.bridge.model.emf.strategy;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import net.bhl.oida.bridge.model.strategy.IRenamerStrategy;
import net.bhl.oida.util.constants.StringConstants;

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
