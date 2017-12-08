 
package oida.ontology.ui.e4.handler;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;

import oida.ontology.service.IOIDAOntologyService;
import oida.ontology.ui.e4.part.OntologyLibraryPart;
import oida.ontologyMgr.OntologyMetaInfo;
import oida.ontologyMgr.OntologyMgrPackage;

public class SetReferenceOntology {
	@Execute
	public void execute(IOIDAOntologyService ontologyService, ESelectionService selectionService) {
		Command command = SetCommand.create(ontologyService.getEditingDomain(), ontologyService.getLibrary(), OntologyMgrPackage.eINSTANCE.getLibrary_ReferenceOntology(), selectionService.getSelection(OntologyLibraryPart.PART_ID));
		ontologyService.getEditingDomain().getCommandStack().execute(command);
		
		ontologyService.loadReferenceOntology();
	}
	
	@CanExecute
	public boolean canExecute(ESelectionService selectionService) {
		if (selectionService.getSelection(OntologyLibraryPart.PART_ID) == null)
			return false;

		if (selectionService.getSelection(OntologyLibraryPart.PART_ID) instanceof OntologyMetaInfo)
			return true;
		else
			return false;
	}
}