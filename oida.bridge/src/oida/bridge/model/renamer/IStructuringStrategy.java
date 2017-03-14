package oida.bridge.model.renamer;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-03-13
 *
 */
public interface IStructuringStrategy {
	enum StructuringAdvice { CREATE_INDIVIDUAL, CREATE_OBJECTPROPERTY, RENAME_INDIVIDUAL, RENAME_OBJECTPROPERTY, CHANGE_DATATYPEPROPERTY };
	
	StructuringAdvice determineStructuringAdviceAfterCreate(EObject createdModelElement);
	
	StructuringAdvice determineStructuringAdviceAfterSet(EAttribute changedAttribute);
}
