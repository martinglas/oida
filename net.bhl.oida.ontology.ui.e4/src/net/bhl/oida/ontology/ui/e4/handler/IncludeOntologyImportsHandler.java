package net.bhl.oida.ontology.ui.e4.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledItem;

import net.bhl.oida.ontology.ui.e4.part.ClassHierarchyViewPart;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-12-13
 *
 */
public class IncludeOntologyImportsHandler {
	@Execute
	void execute(MPart part, MHandledItem handledItem) {
		((ClassHierarchyViewPart)part.getObject()).updateView(handledItem.isSelected());
	}
}
