package oida.bridge.model.emf.metamodel.ontology;

import oida.bridge.model.ontology.IMetaModelOntologyProvider;
import oida.bridge.model.strategy.IRenamerStrategy;
import oida.bridge.model.strategy.IStructuringStrategy;
import oida.ontology.manager.IOntologyManager;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-05-09
 *
 */
public class EMFMetaModelOntologyProvider implements IMetaModelOntologyProvider {

	@Override
	public IOntologyManager createMetaModelOntology(IRenamerStrategy renamerStrategy, IStructuringStrategy structuringStrategy, IOntologyManager manager) {
		return EMFMetaModelOntology.getInstance().createMetaModelOntology(renamerStrategy, structuringStrategy, manager);
	}
}
