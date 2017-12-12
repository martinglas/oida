 
package oida.ontology.ui.e4.handler;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;

import oida.ontology.service.IOIDAOntologyService;
import oida.ontology.ui.e4.part.OntologyLibraryPart;
import oida.ontologyMgr.OntologyMetaInfo;
import oida.ontologyMgr.SystemOntologyMetaInfo;

public class SetReferenceOntology {
	@Execute
	public void execute(IOIDAOntologyService ontologyService, ESelectionService selectionService) {
		ontologyService.SetReferenceOntology((OntologyMetaInfo)selectionService.getSelection(OntologyLibraryPart.PART_ID));
		ontologyService.loadReferenceOntology();
	}
	
	@CanExecute
	public boolean canExecute(ESelectionService selectionService) {
		if (selectionService.getSelection(OntologyLibraryPart.PART_ID) == null)
			return false;

		if (selectionService.getSelection(OntologyLibraryPart.PART_ID) instanceof SystemOntologyMetaInfo)
			return false;
		
		if (selectionService.getSelection(OntologyLibraryPart.PART_ID) instanceof OntologyMetaInfo)
			return true;
		
		return false;
	}
}