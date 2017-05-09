
package oida.bridge.ui.e4.handler;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;

import bridgemodel.BridgemodelFactory;
import bridgemodel.ClassEqualsMapping;
import oida.bridge.service.IOIDABridge;
import oida.bridge.ui.e4.part.MetaModelClassHierarchyViewPart;
import oida.bridge.ui.e4.part.ReferenceOntologyClassViewPart;
import oida.ontology.OntologyClass;

public class MapSecondaryHandler {
	@Execute
	public void execute(IOIDABridge oidaBridge, ESelectionService selectionService) {
		ClassEqualsMapping mapping = BridgemodelFactory.eINSTANCE.createClassEqualsMapping();
		
		OntologyClass metaModelClass = (OntologyClass)selectionService.getSelection(MetaModelClassHierarchyViewPart.PART_ID);
		OntologyClass referenceClass = (OntologyClass)selectionService.getSelection(ReferenceOntologyClassViewPart.PART_ID);
		
		mapping.setClazz1(metaModelClass);
		mapping.setClazz2(referenceClass);
		
		metaModelClass.setMapping(mapping);
		referenceClass.setMapping(mapping);
	}

	@CanExecute
	public boolean canExecute(@Optional ESelectionService selectionService, EPartService partService) {
		if (selectionService == null || selectionService.getSelection(MetaModelClassHierarchyViewPart.PART_ID) == null || selectionService.getSelection(ReferenceOntologyClassViewPart.PART_ID) == null)
			return false;

		return true;
	}

}