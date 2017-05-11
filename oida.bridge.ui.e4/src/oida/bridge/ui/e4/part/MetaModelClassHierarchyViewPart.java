 
package oida.bridge.ui.e4.part;

import javax.inject.Inject;

import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.SelectionChangedEvent;

import com.google.inject.Injector;

import oida.bridge.service.IOIDABridge;
import oida.bridge.ui.MetaModelClassHierarchyView.MetaModelClassHierarchyViewInjectorProvider;
import oida.util.ui.e4.AbstractTreeViewPart;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-25
 *
 */
public class MetaModelClassHierarchyViewPart extends AbstractTreeViewPart {
	public static final String PART_ID = "oida.bridge.ui.e4.part.metamodelclasshierarchyview";
	
	@Inject
	IOIDABridge oidaBridge;

	@Override
	protected void handleSelectionChanged(SelectionChangedEvent event) {
		// Left blank intentionally
	}

	@Override
	protected ViewerFactory initilizeViewerFactory() {
		Injector injector = MetaModelClassHierarchyViewInjectorProvider.getInjector();		
		return injector.getInstance(ViewerFactory.class);
	}
	
	@Override
	protected Object getViewObject() {
		if (oidaBridge.getMetaModelHandler().isPresent())
			return oidaBridge.getMetaModelHandler().get().getModelOntologyManager().getActiveOntology();
		else
			return null;
	}
}