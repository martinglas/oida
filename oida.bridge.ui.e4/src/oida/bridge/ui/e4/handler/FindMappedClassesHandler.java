
package oida.bridge.ui.e4.handler;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;

import bridgemodel.mapping.ClassEqualsMapping;
import oida.bridge.service.IOIDABridge;
import oida.bridge.ui.e4.part.ClassEquivalenceMappingsViewPart;

public class FindMappedClassesHandler {
	@Execute
	public void execute(ESelectionService selectionService, IOIDABridge oidaBridge) {
		ClassEqualsMapping mapping = (ClassEqualsMapping)selectionService.getSelection(ClassEquivalenceMappingsViewPart.PART_ID);
	}

	@CanExecute
	public boolean canExecute(ESelectionService selectionService) {
		if (selectionService.getSelection(ClassEquivalenceMappingsViewPart.PART_ID) != null)
			return true;
		else
			return false;
	}
}