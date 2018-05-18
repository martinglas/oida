package oida.bridge.model.strategy;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-03-13
 *
 */
public interface IStructuringStrategy {
    enum StructuringAdvice {
	CREATE_INDIVIDUAL, CREATE_OBJECTPROPERTY, RENAME_INDIVIDUAL, RENAME_OBJECTPROPERTY, CHANGE_DATATYPEPROPERTY, CHANGE_NAMEANNOTATION
    };

    Object getMetaModelInformationObject();

    StructuringAdvice determineStructuringAdviceAfterCreate(Object createdModelElement);

    StructuringAdvice determineStructuringAdviceAfterSet(EAttribute changedAttribute);

    EStructuralFeature getPartOfStructuralFeature();
}
