
package net.bhl.oida.bridge.ui.e4.part;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.composite.FormDetailComposite;
import org.eclipse.emf.parsley.composite.FormFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Injector;

import net.bhl.oida.bridge.model.recommendation.RecommendationFactory;
import net.bhl.oida.bridge.service.OIDABridge;
import net.bhl.oida.bridge.ui.RecommendationFormView.RecommendationFormViewInjectorProvider;

/**
 * 
 * @author Michael Shamiyeh
 * @since 26.04.2018
 *
 */
public class RecommendationDetailsPart implements ISelectionListener {
    public static final String PART_ID = "oida.bridge.ui.e4.partdescriptor.recommendationdetails";

    private FormDetailComposite formComposite;

    private Composite parent;
    private FormFactory viewerFactory;
    
    @Inject
    private OIDABridge oidaBridge;

    @PostConstruct
    public void postConstruct(Composite parent, ESelectionService selectionService) {
	this.parent = parent;
	selectionService.addSelectionListener(PrimaryRecommendationsViewPart.PART_ID, this);
	selectionService.addSelectionListener("net.bhl.cdt.ui.e4.partdescriptor.modeleditor", this);
	Injector injector = RecommendationFormViewInjectorProvider.getInjector();
	viewerFactory = injector.getInstance(FormFactory.class);
    }

    @Override
    public void selectionChanged(MPart part, Object selection) {
	if (formComposite != null)
	    formComposite.dispose();

	formComposite = viewerFactory.createFormDetailReadOnlyComposite(parent, SWT.NONE);

	if (part.getElementId().equals(PrimaryRecommendationsViewPart.PART_ID)) {
	    if (!(selection instanceof Object[]))
		formComposite.init((EObject)selection);
	    else
		formComposite.init(RecommendationFactory.eINSTANCE.createRecommendation());
	}
//	} else if (part.getElementId().equals("net.bhl.cdt.ui.e4.partdescriptor.modeleditor")) {
//	    try {
//		oidaBridge.getMapping(oidaBridge.getModelChangeHandler(EcoreUtil.getRootContainer((EObject)selection)).getOntologyIndividualForModelElement((EObject)selection).get());
//	    } catch (OIDABridgeException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	    }
//	}

	parent.layout();
    }
}