
package net.bhl.oida.bridge.ui.e4.part;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.composite.FormDetailComposite;
import org.eclipse.emf.parsley.composite.FormFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Injector;

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

    @PostConstruct
    public void postConstruct(Composite parent, ESelectionService selectionService) {
	this.parent = parent;
	selectionService.addSelectionListener(PrimaryRecommendationsViewPart.PART_ID, this);
	Injector injector = RecommendationFormViewInjectorProvider.getInjector();
	viewerFactory = injector.getInstance(FormFactory.class);
    }

    @Override
    public void selectionChanged(MPart part, Object selection) {
	if (formComposite != null)
	    formComposite.dispose();

	formComposite = viewerFactory.createFormDetailReadOnlyComposite(parent, SWT.NONE);
	formComposite.init((EObject)selection);
	parent.layout();
    }
}