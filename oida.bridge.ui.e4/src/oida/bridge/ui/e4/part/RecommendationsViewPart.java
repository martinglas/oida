package oida.bridge.ui.e4.part;

import javax.annotation.PostConstruct;

import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Injector;

import bridgemodel.BridgemodelPackage;
import oida.bridge.service.IOIDABridge;
import oida.bridge.ui.RecommendationsView.RecommendationsViewInjectorProvider;

public class RecommendationsViewPart {
	public static final String PART_ID = "oida.bridge.ui.e4.partdescriptor.oidamap";
	
	private TableViewer tableViewer;
	
	//@Inject
	//private IOIDABridge oidaBridge;
	
	@PostConstruct
	public void postConstruct(Composite parent, IOIDABridge oidaBridge) {
		Injector injector = RecommendationsViewInjectorProvider.getInjector();
		ViewerFactory viewerFactory = injector.getInstance(ViewerFactory.class);
		
		tableViewer = viewerFactory.createTableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION, BridgemodelPackage.eINSTANCE.getRecommendation());

		tableViewer.setInput(oidaBridge.getCurrentRecommendationsResource()); //oidaBridge.getCurrentRecommendations());
//
//		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
//			@Override
//			public void selectionChanged(SelectionChangedEvent event) {
//				IStructuredSelection selection = tableViewer.getStructuredSelection();
//
//				if (!selection.isEmpty())
//					selectionService.setSelection(selection.getFirstElement());
//			}
//		});
	}
}
