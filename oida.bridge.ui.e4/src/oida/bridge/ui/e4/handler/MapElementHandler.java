 
package oida.bridge.ui.e4.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;

import bridgemodel.Recommendation;
import bridgemodel.RecommendationSet;
import oida.bridge.service.IOIDABridge;
import oida.bridge.ui.e4.part.RecommendationsViewPart;

import org.eclipse.e4.core.di.annotations.CanExecute;

public class MapElementHandler {
	@Execute
	public void execute(IOIDABridge oidaBridge, ESelectionService selectionService) {
		Recommendation selectedRecommendation = (Recommendation)selectionService.getSelection(RecommendationsViewPart.PART_ID);
		oidaBridge.establishMapping(selectedRecommendation);
	}
	
	@CanExecute
	public boolean canExecute(@Optional ESelectionService selectionService, EPartService partService) {
		if (selectionService == null || selectionService.getSelection(RecommendationsViewPart.PART_ID) == null)
			return false;
		
		return true;
	}	
}