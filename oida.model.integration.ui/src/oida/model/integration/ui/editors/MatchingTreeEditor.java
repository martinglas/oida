/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.ui.editors;

import oida.model.integration.matching.MatchingModel;
import oida.model.integration.transformation.ontologygenerator.ContainerContext;
import oida.model.integration.ui.providers.OntClassContentProvider;
import oida.model.integration.ui.providers.OntClassLabelProvider;
import oida.model.integration.ui.util.OntologyUIHelper.OntResourceSorter;
import oida.ontology.io.Owl;
import oida.ontology.model.OntologyModel;
import oida.ontology.util.OntologyHelper;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.ViewPart;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntResource;

public class MatchingTreeEditor extends EditorPart {

	private MatchingModel recommendationProvider;

	private DataBindingContext m_bindingContext;
	// private DataBindingContext m_bindingContext;

	private EObject rootEObject;
	private String rootEObjectLabel = "This is a testlabel";
	// private URI sourceOntologyURI;

	public static final String ID = "net.bhl.cdt.model.integration.ui.editors.MappingTreeEditor"; //$NON-NLS-1$

	private OntologyModel ontologyModel;
	private Text textFileName;
	private ListViewer listViewerModelReference;
	// private ManualMatch manualMatch;

	private TreeViewer treeViewerMetamodelReference;

	private Text txtSourceResource;
	private Text txtReferenceresource;
	private Button btnConfirm;

	public MatchingTreeEditor() {
		setPartName("Model Matcher");

	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		if (input instanceof MatchingEditorInput) {
			this.rootEObject = ((MatchingEditorInput) input).getRootEObject();
			// eObjectsLabelProvider = ((MatchingEditorInput) input)
			// .getEObjectLabelProvider();

		}
		recommendationProvider = new MatchingModel(rootEObject, "reference-c.owl", new ContainerContext(rootEObject));
		// TODO Extract Individuals
		ontologyModel = OntologyModel.getInstance();

		setInput(input);
		setSite(site);
	}

	/**
	 * Create contents of the editor part.
	 * 
	 * @param parent
	 */

	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		SashForm sashForm = new SashForm(parent, SWT.VERTICAL);
		GridData gd_sashForm = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_sashForm.widthHint = 585;
		sashForm.setLayoutData(gd_sashForm);

		SashForm sashFormMetaModel = new SashForm(sashForm, SWT.NONE);

		Composite compositeMetaModel = new Composite(sashFormMetaModel, SWT.NONE);
		compositeMetaModel.setLayout(new GridLayout(1, false));

		Label labelMetamodelItems = new Label(compositeMetaModel, SWT.NONE);
		labelMetamodelItems.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		labelMetamodelItems.setText("Metamodel Items");

		final TreeViewer treeViewerMetamodel = new TreeViewer(compositeMetaModel, SWT.BORDER);
		treeViewerMetamodel.setContentProvider(new OntClassContentProvider());
		treeViewerMetamodel.setLabelProvider(new OntClassLabelProvider());
		OntResource topOntResource = ontologyModel.getOntologyModel().getOntResource(
			("http://www.w3.org/2000/01/rdf-schema#Resource"));
		OntClassContentProvider ontClassContentProvider = (OntClassContentProvider) treeViewerMetamodel
			.getContentProvider();
		ontClassContentProvider.setOntology(OntologyModel.getInstance().getBaseOntology());
		treeViewerMetamodel.setInput(topOntResource);

		final Tree treeMetamodel = treeViewerMetamodel.getTree();
		treeMetamodel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite compositeMetaModelRecommendations = new Composite(sashFormMetaModel, SWT.NONE);
		compositeMetaModelRecommendations.setLayout(new GridLayout(1, false));

		Label lblMetamodelRecommendations = new Label(compositeMetaModelRecommendations, SWT.NONE);
		lblMetamodelRecommendations.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lblMetamodelRecommendations.setText("Metamodel recommendations");

		treeViewerMetamodelReference = new TreeViewer(compositeMetaModelRecommendations, SWT.BORDER | SWT.V_SCROLL);
		treeViewerMetamodelReference.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {

			}
		});
		treeViewerMetamodelReference.setContentProvider(new OntClassContentProvider());
		treeViewerMetamodelReference.setLabelProvider(new OntClassLabelProvider());
		OntClassContentProvider referenceOntologyContentProvider = (OntClassContentProvider) treeViewerMetamodelReference
			.getContentProvider();
		referenceOntologyContentProvider.setOntology(OntologyModel.getInstance().getReferenceOntology());

		treeViewerMetamodelReference.setInput(topOntResource);

		final Tree treeMetamodelReference = treeViewerMetamodelReference.getTree();
		treeMetamodelReference.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		treeMetamodelReference.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				listViewerModelReference.getList().deselectAll();
				OntResource currentReferenceOntResource = (OntResource) ((StructuredSelection) treeViewerMetamodelReference
					.getSelection()).getFirstElement();

				recommendationProvider.selectOntResource(currentReferenceOntResource);
				if (currentReferenceOntResource.isClass()) {
					recommendationProvider.setCurrentReferenceClass(currentReferenceOntResource.asClass());
				}

			}
		});

		treeViewerMetamodelReference.setSorter(new OntResourceSorter());

		sashFormMetaModel.setWeights(new int[] { 1, 1 });

		Composite MatchingOperations = new Composite(sashForm, SWT.NONE);
		MatchingOperations.setLayout(new GridLayout(3, false));

		txtSourceResource = new Text(MatchingOperations, SWT.BORDER);
		txtSourceResource.setEditable(false);
		txtSourceResource.setText("Number of unmapped resources");
		txtSourceResource.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		btnConfirm = new Button(MatchingOperations, SWT.NONE);
		btnConfirm.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnConfirm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				recommendationProvider.toggleManualMatch();
			}
		});
		btnConfirm.setText("Confirm");

		txtReferenceresource = new Text(MatchingOperations, SWT.BORDER);
		txtReferenceresource.setEditable(false);
		txtReferenceresource.setText("Reasoner Status");
		txtReferenceresource.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		SashForm sashFormModel = new SashForm(sashForm, SWT.NONE);

		Composite compositeModel = new Composite(sashFormModel, SWT.NONE);
		compositeModel.setLayout(new GridLayout(1, false));

		Label labelModelItems = new Label(compositeModel, SWT.NONE);
		labelModelItems.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		labelModelItems.setText("Model Items");

		final TreeViewer treeViewerModel = new TreeViewer(compositeModel, SWT.BORDER);

		treeViewerModel.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {

			}
		});
		treeViewerModel.setLabelProvider(new AdapterFactoryLabelProvider(new ComposedAdapterFactory(
			ComposedAdapterFactory.Descriptor.Registry.INSTANCE)));

		treeViewerModel.setContentProvider(new AdapterFactoryContentProvider(new ComposedAdapterFactory(
			ComposedAdapterFactory.Descriptor.Registry.INSTANCE)) {
			@Override
			public Object[] getElements(Object object) {
				if (object instanceof Object[]) {
					return (Object[]) object;
				}
				return super.getElements(object);
			}
		});
		treeViewerModel.setInput(new Object[] { rootEObject });

		Tree treeModel = treeViewerModel.getTree();
		treeModel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite compositeModelRecommendations = new Composite(sashFormModel, SWT.NONE);
		compositeModelRecommendations.setLayout(new GridLayout(1, false));

		Label labelModelRecommendations = new Label(compositeModelRecommendations, SWT.NONE);
		labelModelRecommendations.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		labelModelRecommendations.setText("Model Recommendations");

		listViewerModelReference = new ListViewer(compositeModelRecommendations, SWT.BORDER | SWT.V_SCROLL);
		List listModelReference = listViewerModelReference.getList();
		listModelReference.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		listModelReference.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				Individual referenceIndividual = (Individual) ((StructuredSelection) listViewerModelReference
					.getSelection()).getFirstElement();

				recommendationProvider.selectOntResource(referenceIndividual);

			}
		});
		listViewerModelReference.setSorter(new OntResourceSorter());

		treeModel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Get Individual
				EObject eObject = (EObject) ((StructuredSelection) treeViewerModel.getSelection()).getFirstElement();

				String baseIndividualName = recommendationProvider.getEObjectName(eObject);
				String baseOntologyURIString = ontologyModel.getBaseOntologyURI().toString();

				Individual sourceIndividual = ontologyModel.getIndividual(URI.createURI(baseOntologyURIString + "#"
					+ baseIndividualName));

				recommendationProvider.selectOntResource(sourceIndividual);
				// Get Type and select in treeviewerMetamodel

				treeViewerMetamodel.setSelection(
					new StructuredSelection(recommendationProvider.getSourceMetaOntResource()), true);

				// Get ReferenceOntClass of the individual and select it if
				// possible

				if (recommendationProvider.getReferenceIndividual() != null) {
					// manualMatch.setTarget(referenceIndividual);
					listViewerModelReference.setSelection(
						new StructuredSelection(recommendationProvider.getReferenceIndividual()), true);
				}

				//

			}
		});

		treeMetamodel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				treeViewerModel.getTree().deselectAll();

				// Set internal Resource of manual Mapping
				OntResource sourceOntResource = (OntResource) ((StructuredSelection) treeViewerMetamodel.getSelection())
					.getFirstElement();

				recommendationProvider.selectOntResource(sourceOntResource);

				recommendationProvider.setSourceMetaOntResource(sourceOntResource);
				OntResource referenceMetaOntResource = recommendationProvider.getReferenceMetaOntResource();
				if (referenceMetaOntResource != null) {
					treeViewerMetamodelReference.setInput(referenceMetaOntResource);
					treeViewerMetamodelReference.refresh();
				} else {
					treeViewerMetamodelReference.setInput(ontologyModel.getOntologyModel().getOntResource(
						"http://www.w3.org/2000/01/rdf-schema#Resource"));
					treeViewerMetamodelReference.refresh();
				}

				// TODO if equivalent reference class exists select the
				// external class and switch to revoke

				if (referenceMetaOntResource != null) {
					treeViewerMetamodelReference.setSelection(new StructuredSelection(referenceMetaOntResource), true);
				}

			}

		});

		sashFormModel.setWeights(new int[] { 1, 1 });

		Composite compositeFileOperations = new Composite(sashForm, SWT.NONE);
		compositeFileOperations.setLayout(new GridLayout(2, true));

		Label lblFileName = new Label(compositeFileOperations, SWT.NONE);
		lblFileName.setText("Matching ontology file name");
		new Label(compositeFileOperations, SWT.NONE);

		textFileName = new Text(compositeFileOperations, SWT.BORDER);
		textFileName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnSave = new Button(compositeFileOperations, SWT.NONE);
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				Owl.saveToOwl(OntologyHelper.getOntologyURL(recommendationProvider.getSourceOntologyFileName()),
					ontologyModel.getBaseOntology());

			}
		});
		btnSave.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnSave.setText("Save");
		sashForm.setWeights(new int[] { 173, 36, 193, 53 });
		m_bindingContext = initDataBindings();

	}

	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (part instanceof ViewPart) {
			if (selection instanceof ITreeSelection) {
				ITreeSelection treeSel = (ITreeSelection) selection;
				if (treeSel.getFirstElement() instanceof EObject) {
					rootEObject = ((EObject) treeSel.getFirstElement());

				}

			}
		}

	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	public String getRootEObjectLabel() {
		return rootEObjectLabel;
	}

	public void setRootEObjectLabel(String rootEObjectLabel) {
		this.rootEObjectLabel = rootEObjectLabel;
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue textFileNameObserveTextObserveWidget = SWTObservables.observeText(textFileName, SWT.Modify);
		IObservableValue recommendationProviderSourceOntologyFileNameObserveValue = BeansObservables.observeValue(
			recommendationProvider, "sourceOntologyFileName");
		bindingContext.bindValue(textFileNameObserveTextObserveWidget,
			recommendationProviderSourceOntologyFileNameObserveValue, null, null);
		//
		IObservableValue btnDeclineObserveTextObserveWidget = SWTObservables.observeText(btnConfirm);
		IObservableValue recommendationProviderButtonStringObserveValue = BeansObservables.observeValue(
			recommendationProvider, "buttonString");
		bindingContext.bindValue(btnDeclineObserveTextObserveWidget, recommendationProviderButtonStringObserveValue,
			null, null);
		//
		IObservableValue txtSourceResourceObserveTextObserveWidget = SWTObservables.observeText(txtSourceResource);
		IObservableValue recommendationProviderMappingStatusObserveValue = BeansObservables.observeValue(
			recommendationProvider, "mappingStatus");
		bindingContext.bindValue(txtSourceResourceObserveTextObserveWidget,
			recommendationProviderMappingStatusObserveValue, null, null);
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		listViewerModelReference.setContentProvider(listContentProvider);
		//
		IObservableMap observeMap = PojoObservables.observeMap(listContentProvider.getKnownElements(),
			Individual.class, "localName");
		listViewerModelReference.setLabelProvider(new ObservableMapLabelProvider(observeMap));
		//
		IObservableList recommendationProviderCurrentIndividualCandidatesObserveList = BeansObservables.observeList(
			Realm.getDefault(), recommendationProvider, "currentIndividualCandidates");
		listViewerModelReference.setInput(recommendationProviderCurrentIndividualCandidatesObserveList);
		//
		return bindingContext;
	}

}
