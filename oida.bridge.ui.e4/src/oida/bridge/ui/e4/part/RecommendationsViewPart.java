package oida.bridge.ui.e4.part;

import javax.annotation.PostConstruct;

import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

import bridgemodel.BridgemodelPackage;
import oida.bridge.service.IOIDABridge;

public class RecommendationsViewPart {
	private TableViewer tableViewer;
	
	@Inject
	IOIDABridge oidaBridge;
	
	@Inject
	ViewerFactory viewerFactory;
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		tableViewer = viewerFactory.createTableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION, BridgemodelPackage.eINSTANCE.getRecommendation());

		tableViewer.setInput(null); //oidaBridge.getCurrentRecommendations());
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
