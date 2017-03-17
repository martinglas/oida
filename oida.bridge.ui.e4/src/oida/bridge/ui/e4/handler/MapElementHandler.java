 
package oida.bridge.ui.e4.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;

import oida.bridge.ui.e4.part.RecommendationsViewPart;

import org.eclipse.e4.core.di.annotations.CanExecute;

public class MapElementHandler {
	@Execute
	public void execute() {
		System.out.println("Do Mapping!");
	}
	
	@CanExecute
	public boolean canExecute(@Optional ESelectionService selectionService) {
		if (selectionService == null || selectionService.getSelection(RecommendationsViewPart.PART_ID) == null)
			return false;
		
		return true;
	}	
}