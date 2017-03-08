package oida.bridge.model.ontology;

import org.eclipse.emf.ecore.EObject;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-03-08
 *
 */
public interface IModelOntologyPathProvider {
	String providePathForModelOntology(EObject modelObject);
}
