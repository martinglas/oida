 
package net.bhl.oida.ontology.ui.e4.handler;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;

import net.bhl.oida.ontology.model.ontologymgr.OntologyMetaInfo;
import net.bhl.oida.ontology.model.ontologymgr.SystemOntologyMetaInfo;
import net.bhl.oida.ontology.service.OIDAOntologyService;
import net.bhl.oida.ontology.ui.e4.part.OntologyLibraryPart;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-12-13
 *
 */
public class SetReferenceOntology {
	@Execute
	public void execute(OIDAOntologyService ontologyService, ESelectionService selectionService) {
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