 
package oida.ontology.ui.e4.handler;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;

import oida.ontology.Ontology;
import oida.ontology.service.IOIDAOntologyService;
import oida.ontology.ui.e4.part.OntologyLibraryPart;

public class StartOntologyReasoning {
	@Inject
	IOIDAOntologyService oidaService;
	
	@Execute
	public void execute(ESelectionService selectionService) {
		Ontology selectedOntology = (Ontology)selectionService.getSelection(OntologyLibraryPart.PART_ID);
	}
	
	@CanExecute
	public boolean canExecute(ESelectionService selectionService) {
		return selectionService.getSelection(OntologyLibraryPart.PART_ID) != null;
	}
}