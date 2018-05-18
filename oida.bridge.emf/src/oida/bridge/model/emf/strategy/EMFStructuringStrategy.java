package oida.bridge.model.emf.strategy;

import org.eclipse.emf.ecore.EPackage;

import oida.bridge.model.strategy.IStructuringStrategy;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-21
 *
 */
public abstract class EMFStructuringStrategy implements IStructuringStrategy {

    @Override
    public Object getMetaModelInformationObject() {
	return getEMFPackageInformation();
    }

    protected abstract EPackage[] getEMFPackageInformation();
}
