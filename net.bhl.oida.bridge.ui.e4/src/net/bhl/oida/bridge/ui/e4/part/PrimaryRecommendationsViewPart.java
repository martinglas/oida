package net.bhl.oida.bridge.ui.e4.part;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Injector;

import net.bhl.oida.bridge.model.recommendation.RecommendationPackage;
import net.bhl.oida.bridge.recommender.RecommenderSystem;
import net.bhl.oida.bridge.service.OIDABridge;
import net.bhl.oida.bridge.ui.RecommendationsView.RecommendationsViewInjectorProvider;

public class PrimaryRecommendationsViewPart {
    public static final String PART_ID = "oida.bridge.ui.e4.partdescriptor.oidaprimarymapping";

    private TableViewer tableViewer;

    @PostConstruct
    public void postConstruct(Composite parent, ESelectionService selectionService, OIDABridge oidaBridge) {
	Injector injector = RecommendationsViewInjectorProvider.getInjector();
	ViewerFactory viewerFactory = injector.getInstance(ViewerFactory.class);

	tableViewer = viewerFactory.createTableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION, RecommendationPackage.eINSTANCE.getRecommendation());

	tableViewer.setInput(RecommenderSystem.getInstance().getCurrentPrimaryRecommendationsResource());

	tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
	    public void selectionChanged(SelectionChangedEvent event) {
		IStructuredSelection selection = (IStructuredSelection)event.getSelection();
		// set the selection to the service
		selectionService.setSelection(selection.size() == 1 ? selection.getFirstElement() : selection.toArray());
	    }
	});
    }
}
