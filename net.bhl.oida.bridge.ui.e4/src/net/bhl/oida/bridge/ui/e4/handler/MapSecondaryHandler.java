
package net.bhl.oida.bridge.ui.e4.handler;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;

import net.bhl.oida.bridge.service.OIDABridge;
import net.bhl.oida.bridge.ui.e4.part.MetaModelClassMappingPart;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-05-08
 *
 */
public class MapSecondaryHandler {
    @Execute
    public void execute(OIDABridge oidaBridge, ESelectionService selectionService) {
	// OntologyClass metaModelClass =
	// (OntologyClass)selectionService.getSelection(MetaModelClassHierarchyViewPart.PART_ID);
	// OntologyClass referenceClass =
	// (OntologyClass)selectionService.getSelection(ReferenceOntologyClassViewPart.PART_ID);
	//
	// oidaBridge.establishSecondaryMapping(metaModelClass, referenceClass);
    }

    @CanExecute
    public boolean canExecute(@Optional ESelectionService selectionService, EPartService partService) {
	MPart part = partService.findPart(MetaModelClassMappingPart.PART_ID);

	// if (selectionService == null ||
	// selectionService.getSelection(MetaModelClassHierarchyViewPart.PART_ID)
	// == null ||
	// selectionService.getSelection(ReferenceOntologyClassViewPart.PART_ID)
	// == null)
	// return false;
	//
	// return true;
	return false;
    }
}