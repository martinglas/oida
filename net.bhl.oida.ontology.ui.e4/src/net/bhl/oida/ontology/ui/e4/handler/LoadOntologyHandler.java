 
package net.bhl.oida.ontology.ui.e4.handler;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;

import net.bhl.oida.ontology.model.ontologymgr.LocalOntologyMetaInfo;
import net.bhl.oida.ontology.model.ontologymgr.OntologyMetaInfo;
import net.bhl.oida.ontology.service.OIDAOntologyService;
import net.bhl.oida.ontology.ui.e4.part.OntologyLibraryPart;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-12-13
 *
 */
public class LoadOntologyHandler {
	@Execute
	public void execute(OIDAOntologyService ontologyService, ESelectionService selectionService) {
		OntologyMetaInfo metaInfo = (OntologyMetaInfo)selectionService.getSelection(OntologyLibraryPart.PART_ID);
		
		if (metaInfo instanceof LocalOntologyMetaInfo)
			ontologyService.getLocalOntologyManager((LocalOntologyMetaInfo)metaInfo);
		else
			ontologyService.getRemoteOntologyManager(metaInfo);
	}
	
	@CanExecute
	public boolean canExecute(ESelectionService selectionService) {
		if (selectionService.getSelection(OntologyLibraryPart.PART_ID) == null)
			return false;

		if (selectionService.getSelection(OntologyLibraryPart.PART_ID) instanceof OntologyMetaInfo)
			return true;
		
		return false;
	}
}