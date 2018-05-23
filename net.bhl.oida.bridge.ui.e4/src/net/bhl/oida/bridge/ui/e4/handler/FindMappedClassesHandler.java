
package net.bhl.oida.bridge.ui.e4.handler;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;

import net.bhl.oida.bridge.model.mapping.ClassEqualsMapping;
import net.bhl.oida.bridge.service.OIDABridge;
import net.bhl.oida.bridge.ui.e4.part.ClassEquivalenceMappingsViewPart;

public class FindMappedClassesHandler {
    @Execute
    public void execute(ESelectionService selectionService, OIDABridge oidaBridge) {
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