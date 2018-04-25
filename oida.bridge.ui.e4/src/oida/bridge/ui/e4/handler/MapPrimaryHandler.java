
package oida.bridge.ui.e4.handler;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;

import bridgemodel.recommendation.Recommendation;
import oida.bridge.service.OIDABridge;
import oida.bridge.ui.e4.part.PrimaryRecommendationsViewPart;

public class MapPrimaryHandler {
    @Execute
    public void execute(OIDABridge oidaBridge, ESelectionService selectionService) {
	Recommendation selectedRecommendation = (Recommendation)selectionService.getSelection(PrimaryRecommendationsViewPart.PART_ID);
	oidaBridge.establishPrimaryMapping(selectedRecommendation);
    }

    @CanExecute
    public boolean canExecute(@Optional ESelectionService selectionService, EPartService partService) {
	if (selectionService == null || selectionService.getSelection(PrimaryRecommendationsViewPart.PART_ID) == null)
	    return false;

	return true;
    }
}