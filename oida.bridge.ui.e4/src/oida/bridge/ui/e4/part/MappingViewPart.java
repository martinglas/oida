package oida.bridge.ui.e4.part;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.composite.FormDetailComposite;
import org.eclipse.emf.parsley.composite.FormFactory;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Injector;

import oida.bridge.model.changehandler.IModelChangeHandler;
import oida.bridge.service.IOIDABridge;
import oida.ontology.OntologyEntity;
import oida.ontology.ui.view.MappingView.MappingViewInjectorProvider;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-18
 *
 */
public class MappingViewPart {
	public static final String PART_ID = "oida.bridge.ui.e4.partdescriptor.oidamappingview";

	private FormDetailComposite formComposite;

	@PostConstruct
	public void postConstruct(Composite parent, ESelectionService selectionService, IOIDABridge oidaBridge) {
		Injector injector = MappingViewInjectorProvider.getInjector();
		FormFactory formFactory = injector.getInstance(FormFactory.class);
		
		selectionService.addSelectionListener("de.symo.model.editor.e4.partdescriptor.symomodeleditor", new ISelectionListener() {
			
			@Override
			public void selectionChanged(MPart part, Object selection) {
				if (formComposite != null)
					formComposite.dispose();
				
				EObject selectedModelObject = (EObject)((StructuredSelection)selection).getFirstElement();
				
				EObject baseModelObject = selectedModelObject;
				while (baseModelObject.eContainer() != null)
					baseModelObject = baseModelObject.eContainer();

				Optional<IModelChangeHandler> changeHandler = oidaBridge.getModelChangeHandler(baseModelObject);
				
				if (changeHandler.isPresent()) {
					OntologyEntity entity = changeHandler.get().getOntologyEntityForModelElement(selectedModelObject);
					
					if (entity != null) {
						formComposite = formFactory.createFormDetailComposite(parent, SWT.NONE);
						formComposite.init(entity);
					}
				}
			}
		});
	}
}
