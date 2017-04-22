package oida.bridge.model.changehandler.emf;

import java.util.Optional;

import oida.bridge.model.changehandler.IModelChangeHandler;
import oida.bridge.model.changehandler.IModelChangeHandlerFactory;
import oida.bridge.model.changehandler.emf.ontology.EMFModelOntology;
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
	public void initialize(IOIDAOntologyService ontologyService) {
		OntologyFile emfOntologyFile = OIDAUtil.getOntologyFile(OIDAUtil.getOIDAWorkPath(), FileConstants.EMFONTOLOGY_FILENAME);
		
		try {
			Optional<IOntologyManager> optOntologyManager = ontologyService.getOntologyManager(emfOntologyFile, true);
			
			if (optOntologyManager.isPresent()) {
				EMFModelOntology.getInstance().loadOrInitializeOntology(optOntologyManager.get());
			}
			else
				System.out.println("Bla");
		} catch (OntologyManagerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public IModelChangeHandler getChangeHandler() {
		return new EMFModelChangeHandler();
	}
}
