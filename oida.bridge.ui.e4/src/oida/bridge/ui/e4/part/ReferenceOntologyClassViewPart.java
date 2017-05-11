 
package oida.bridge.ui.e4.part;

import javax.inject.Inject;

import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.SelectionChangedEvent;

import com.google.inject.Injector;

import oida.bridge.ui.ReferenceOntologyClassHierarchyView.ReferenceOntologyClassHierarchyViewInjectorProvider;
import oida.ontology.service.IOIDAOntologyService;
import oida.util.ui.e4.AbstractTreeViewPart;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-05-08
 *
 */
public class ReferenceOntologyClassViewPart extends AbstractTreeViewPart {
	public static final String PART_ID = "oida.bridge.ui.e4.part.referenceontologyclasshierarchy";
	
	@Inject
	IOIDAOntologyService oidaService;
	
	@Override
	protected void handleSelectionChanged(SelectionChangedEvent event) {
		// Left blank intentionally
	}

	@Override
	protected ViewerFactory initilizeViewerFactory() {
		Injector injector = ReferenceOntologyClassHierarchyViewInjectorProvider.getInjector();		
		return injector.getInstance(ViewerFactory.class);
	}

	@Override
	protected Object getViewObject() {
		if (oidaService.getReferenceOntologyManager().isPresent())
			return oidaService.getReferenceOntologyManager().get().getOntologyWithIncludes();
		
		return null;
	}
}