
package oida.bridge.ui.e4.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;

import bridgemodel.Recommendation;
import oida.bridge.service.IOIDABridge;
import oida.bridge.ui.e4.part.MetaModelClassHierarchyViewPart;
import oida.bridge.ui.e4.part.ReferenceOntologyClassViewPart;
import oida.bridge.ui.e4.part.SecondaryRecommendationsViewPart;

import org.eclipse.e4.core.di.annotations.CanExecute;

public class MapSecondaryHandler {
	@Execute
	public void execute(IOIDABridge oidaBridge, ESelectionService selectionService) {
		Recommendation selectedRecommendation = (Recommendation)selectionService.getSelection(SecondaryRecommendationsViewPart.PART_ID);
		oidaBridge.establishSecondaryMapping(selectedRecommendation);
	}

	@CanExecute
	public boolean canExecute(@Optional ESelectionService selectionService, EPartService partService) {
		if (selectionService == null || selectionService.getSelection(MetaModelClassHierarchyViewPart.PART_ID) == null || selectionService.getSelection(ReferenceOntologyClassViewPart.PART_ID) == null)
			return false;

		return true;
	}

}