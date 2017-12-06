package oida.bridge.ui.e4.part;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.google.inject.Injector;

import bridgemodel.ObjectPropertyEqualsMapping;
import oida.bridge.service.IOIDABridge;
import oida.bridge.ui.ObjectPropertyHierarchyView.ObjectPropertyHierarchyViewInjectorProvider;
import oida.ontology.OntologyObjectProperty;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-05-10
 *
 */
public class MetaModelObjectPropertyMappingPart {
	public static final String PART_ID = "oida.bridge.ui.e4.part.metamodelobjectpropertymappingpart";
	private ViewerFactory objectPropertyHierarchyViewerFactory;

	private TreeViewer metaModelObjectPropertiesTreeViewer;
	private TreeViewer referenceOntologyObjectPropertiesTreeViewer;

	private OntologyObjectProperty selectedMetaModelObjectProperty;
	private OntologyObjectProperty selectedReferenceOntologyObjectProperty;

	private Button addMappingButton;
	private Button removeMappingButton;

	@PostConstruct
	public void postConstruct(Composite parent, MPart part, IOIDABridge oidaBridge) {
		parent.setLayout(new GridLayout(2, false));
		parent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Injector injector = ObjectPropertyHierarchyViewInjectorProvider.getInjector();

		objectPropertyHierarchyViewerFactory = injector.getInstance(ViewerFactory.class);

		Label metaModelLabel = new Label(parent, SWT.NONE);
		metaModelLabel.setText("Meta Model Relations");
		Label referenceOntologyLabel = new Label(parent, SWT.NONE);
		referenceOntologyLabel.setText("Reference Ontology Object Property Hierarchy");

		metaModelObjectPropertiesTreeViewer = new TreeViewer(parent, SWT.FILL);
		metaModelObjectPropertiesTreeViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		metaModelObjectPropertiesTreeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = metaModelObjectPropertiesTreeViewer.getStructuredSelection();

				if (!selection.isEmpty()) {
					selectedMetaModelObjectProperty = (OntologyObjectProperty)selection.getFirstElement();
					setButtonState();
					
					if (selectedMetaModelObjectProperty.isMappingExists())  {
						Optional<ObjectPropertyEqualsMapping> optMapping = oidaBridge.getObjectPropertyMapping(selectedMetaModelObjectProperty);
						
						if (optMapping.isPresent()) {
							referenceOntologyObjectPropertiesTreeViewer.getTree().setFocus();
							referenceOntologyObjectPropertiesTreeViewer.expandAll();
							referenceOntologyObjectPropertiesTreeViewer.setSelection(new StructuredSelection(optMapping.get().getObjectProperty2()));
						}
					}
				}
			}
		});

		referenceOntologyObjectPropertiesTreeViewer = new TreeViewer(parent, SWT.FILL);
		referenceOntologyObjectPropertiesTreeViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		referenceOntologyObjectPropertiesTreeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = referenceOntologyObjectPropertiesTreeViewer.getStructuredSelection();

				if (!selection.isEmpty()) {
					selectedReferenceOntologyObjectProperty = (OntologyObjectProperty)selection.getFirstElement();
					setButtonState();
				}
			}
		});

		addMappingButton = new Button(parent, SWT.PUSH);
		addMappingButton.setText("Map Relation");
		addMappingButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				oidaBridge.establishSecondaryObjectPropertyMapping(selectedMetaModelObjectProperty, selectedReferenceOntologyObjectProperty);
			}
		});

		removeMappingButton = new Button(parent, SWT.NONE);
		removeMappingButton.setText("Remove Relation-Mapping");
		removeMappingButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				oidaBridge.removeSecondaryObjectPropertyMapping(selectedMetaModelObjectProperty);
			}
		});

		objectPropertyHierarchyViewerFactory.initialize(metaModelObjectPropertiesTreeViewer, oidaBridge.getModelOntology().get().getLocalOntology());
		objectPropertyHierarchyViewerFactory.initialize(referenceOntologyObjectPropertiesTreeViewer, oidaBridge.getReferenceOntology());

		setButtonState();
	}

	private void setButtonState() {
		if (selectedMetaModelObjectProperty == null) {
			removeMappingButton.setEnabled(false);
			addMappingButton.setEnabled(false);
		} else {
			if (selectedMetaModelObjectProperty.isMappingExists()) {
				removeMappingButton.setEnabled(true);
				addMappingButton.setEnabled(false);
			} else {
				if (selectedReferenceOntologyObjectProperty != null && !selectedReferenceOntologyObjectProperty.isMappingExists()) {
					addMappingButton.setEnabled(true);
				} else {
					addMappingButton.setEnabled(false);
				}

				removeMappingButton.setEnabled(false);
			}
		}
	}
}
