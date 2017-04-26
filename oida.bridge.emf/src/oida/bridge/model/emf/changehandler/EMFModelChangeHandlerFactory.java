package oida.bridge.model.emf.changehandler;

import java.util.Optional;

import org.eclipse.emf.ecore.EPackage;

import oida.bridge.model.changehandler.IModelChangeHandler;
import oida.bridge.model.changehandler.IModelChangeHandlerFactory;
import oida.bridge.model.emf.changehandler.ontology.EMFMetaModelOntology;
import oida.bridge.model.emf.changehandler.ontology.EMFOntology;
import oida.bridge.model.strategy.IRenamerStrategy;
import oida.bridge.model.strategy.IStructuringStrategy;
import oida.ontology.manager.IOntologyManager;
import oida.ontology.manager.OntologyManagerException;
import oida.ontology.service.IOIDAOntologyService;
import oida.ontologyMgr.OntologyFile;
import oida.util.OIDAUtil;
import oida.util.constants.FileConstants;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-07
 *
 */
public class EMFModelChangeHandlerFactory implements IModelChangeHandlerFactory {
	private IRenamerStrategy renamerStrategy;
	private IStructuringStrategy structuringStrategy;

	@Override
	public void initialize(IOIDAOntologyService ontologyService, IRenamerStrategy renamerStrategy, IStructuringStrategy structuringStrategy, IOntologyManager metaModelOntologyManager) {
		this.renamerStrategy = renamerStrategy;
		this.structuringStrategy = structuringStrategy;

		OntologyFile emfOntologyFile = OIDAUtil.getOntologyFile(OIDAUtil.getOIDAWorkPath(), FileConstants.EMFONTOLOGY_FILENAME);

		try {
			Optional<IOntologyManager> optOntologyManager = ontologyService.getOntologyManager(emfOntologyFile, true);

			if (optOntologyManager.isPresent()) {
				EMFOntology.getInstance().loadOrInitializeOntology(optOntologyManager.get());
			} else
				System.out.println("Bla");

			EMFMetaModelOntology.getInstance().setRenamerStrategy(renamerStrategy);
			EMFMetaModelOntology.getInstance().setStructuringStrategy(structuringStrategy);
			EMFMetaModelOntology.getInstance().initialize(metaModelOntologyManager, (EPackage[])structuringStrategy.getMetaModelInformationObject());
			metaModelOntologyManager.saveOntology();
		} catch (OntologyManagerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public IOntologyManager getMetaModelOntology() {
		return EMFMetaModelOntology.getInstance().getModelOntologyManager();
	}

	@Override
	public IModelChangeHandler getChangeHandler() {
		EMFModelChangeHandler changeHandler = new EMFModelChangeHandler();
		changeHandler.initializeChangeHandler(renamerStrategy, structuringStrategy, getMetaModelOntology());
		return changeHandler;
	}
}
