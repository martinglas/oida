package oida.bridge.ui.e4.part;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Injector;

import bridgemodel.Recommendation;
import oida.bridge.service.IOIDABridge;
import oida.bridge.ui.ClassHierarchyView.ClassHierarchyViewInjectorProvider;
import oida.ontology.OntologyClass;
import oida.ontology.service.IOIDAOntologyService;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-05-10
 *
 */
public class MetaModelClassMappingPart {
	public static final String PART_ID = "oida.bridge.ui.e4.part.metamodelclassmappingpart";

	private TreeViewer metaModelClassesTreeViewer;

	private OntologyClass selectedMetaModelClass;
	private OntologyClass selectedReferenceOntologyClass;
	private OntologyClass selectedRecommendedClass;

	private Button addMappingButton;
	private Button addMappingOfRecommendationButton;
	private Button removeMappingButton;

	@PostConstruct
	public void postConstruct(Composite parent, ESelectionService selectionService, MPart part, IOIDABridge oidaBridge, IOIDAOntologyService oidaService) {
		parent.setLayout(new GridLayout(1, false));
		parent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Injector injector = ClassHierarchyViewInjectorProvider.getInjector();

		ViewerFactory classHierarchyViewerFactory = injector.getInstance(ViewerFactory.class);

		selectionService.addSelectionListener(ReferenceOntologyClassHierarchyPart.PART_ID, new ISelectionListener() {
			@Override
			public void selectionChanged(MPart part, Object selection) {
				if (selection != null && selection instanceof OntologyClass) {
					selectedReferenceOntologyClass = (OntologyClass)selection;

					if (!selectedReferenceOntologyClass.isMappingExists())
						addMappingButton.setEnabled(true);
					else
						addMappingButton.setEnabled(false);
				}
			}
		});

		selectionService.addSelectionListener(SecondaryRecommendationsViewPart.PART_ID, new ISelectionListener() {
			@Override
			public void selectionChanged(MPart part, Object selection) {
				if (selection != null && selection instanceof Recommendation) {
					selectedRecommendedClass = (OntologyClass)((Recommendation)selection).getRecommendedEntity();

					if (!selectedRecommendedClass.isMappingExists())
						addMappingOfRecommendationButton.setEnabled(true);
					else
						addMappingOfRecommendationButton.setEnabled(false);
				}
			}
		});

		metaModelClassesTreeViewer = new TreeViewer(parent, SWT.SINGLE | SWT.FILL);
		metaModelClassesTreeViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		metaModelClassesTreeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = metaModelClassesTreeViewer.getStructuredSelection();

				if (!selection.isEmpty()) {
					selectedMetaModelClass = (OntologyClass)selection.getFirstElement();

					if (!selectedMetaModelClass.isMappingExists()) {
						oidaBridge.reportMetaModelSelectionChanged(selectedMetaModelClass);
						removeMappingButton.setEnabled(false);
					} else
						removeMappingButton.setEnabled(true);
					
					selectionService.setSelection(selectedMetaModelClass);
				}
			}
		});

		addMappingButton = new Button(parent, SWT.PUSH);
		addMappingButton.setText("Map on selected Class from Hierarchy");
		addMappingButton.setEnabled(false);
		addMappingButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				oidaBridge.establishSecondaryClassMapping(selectedMetaModelClass, selectedReferenceOntologyClass);
			}
		});
		
		addMappingOfRecommendationButton = new Button(parent, SWT.PUSH);
		addMappingOfRecommendationButton.setText("Map on selected Recommendation");
		addMappingOfRecommendationButton.setEnabled(false);
		addMappingOfRecommendationButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				oidaBridge.establishSecondaryClassMapping(selectedMetaModelClass, selectedRecommendedClass);
			}
		});

		removeMappingButton = new Button(parent, SWT.NONE);
		removeMappingButton.setText("Remove Class-Mapping");
		removeMappingButton.setEnabled(false);
		removeMappingButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				oidaBridge.removeSecondaryClassMapping(selectedMetaModelClass);
			}
		});

		classHierarchyViewerFactory.initialize(metaModelClassesTreeViewer, oidaBridge.getMetaModelHandler().get().getModelOntologyManager().getOntology().getLocalOntology());
	}
}
