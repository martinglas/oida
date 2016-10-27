/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.ui.editors;

import static oida.ontology.util.OntologyHelper.REFERENCE_ONTOLOGY_FILENAME;
import oida.model.integration.evolution.ImportPreviousMappings;
import oida.model.integration.matching.MatchingModel;
import oida.model.integration.transformation.ontologygenerator.AllContainerContext;
import oida.model.integration.ui.dialogs.CreateNewReferenceIndividualDialog;
import oida.model.integration.ui.dialogs.CreateNewReferenceOntClassDialog;
import oida.model.integration.ui.dialogs.ImportPreviousMappingsDialog;
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
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.ViewPart;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntResource;

public class ExtendingMatchEditor extends EditorPart {

	private MatchingModel matchingModel;

	private DataBindingContext m_bindingContext;

	private EObject rootEObject;
	private String rootEObjectLabel = "This is a testlabel";

	public static final String ID = "net.bhl.cdt.model.integration.ui.editors.ExtendingMatchEditor"; //$NON-NLS-1$

	private OntologyModel ontologyModel;
	private Text textFileName;
	private ListViewer listViewerModelReference;

	private ListViewer listViewerMetamodelReference;

	private Text txtSourceResource;
	private Text txtReferenceresource;
	private Button btnConfirm;

	public ExtendingMatchEditor() {
		setPartName("Model Matcher");

	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {

		if (input instanceof MatchingEditorInput) {
			this.rootEObject = ((MatchingEditorInput) input).getRootEObject();

		}

		ImportPreviousMappings previousMappingImportModel = new ImportPreviousMappings(rootEObject,
			OntologyHelper.getPluginFilePath() + OntologyHelper.ONTOLOGY_PATH);

		ImportPreviousMappingsDialog loadPreviousMappingDialog = new ImportPreviousMappingsDialog(PlatformUI
			.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL,
			previousMappingImportModel);

		loadPreviousMappingDialog.open();

		matchingModel = new MatchingModel(rootEObject, REFERENCE_ONTOLOGY_FILENAME,
			new AllContainerContext(rootEObject));

		previousMappingImportModel.importEquivalences();
		// Extract Individuals
		ontologyModel = OntologyModel.getInstance();

		setInput(input);
		setSite(site);
	}

	/**
	 * Create contents of the editor part.
	 * 
	 * @param parent
	 */
	@Override
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

		listViewerMetamodelReference = new ListViewer(compositeMetaModelRecommendations, SWT.BORDER | SWT.V_SCROLL);
		listViewerMetamodelReference.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {

			}
		});

		final List listMetamodelReference = listViewerMetamodelReference.getList();
		listMetamodelReference.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		listMetamodelReference.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				listViewerModelReference.getList().deselectAll();
				OntResource currentReferenceOntResource = (OntResource) ((StructuredSelection) listViewerMetamodelReference
					.getSelection()).getFirstElement();

				matchingModel.selectOntResource(currentReferenceOntResource);
				if (currentReferenceOntResource.isClass()) {
					matchingModel.setCurrentReferenceClass(currentReferenceOntResource.asClass());
				}

			}
		});
		listViewerMetamodelReference.setSorter(new OntResourceSorter());

		sashFormMetaModel.setWeights(new int[] { 1, 1 });

		Composite MatchingOperations = new Composite(sashForm, SWT.NONE);
		MatchingOperations.setLayout(new GridLayout(4, false));

		Button btnAddToReference = new Button(MatchingOperations, SWT.NONE);
		GridData gd_btnAddToReference = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnAddToReference.widthHint = 112;
		btnAddToReference.setLayoutData(gd_btnAddToReference);
		btnAddToReference.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				OntResource ontResource = matchingModel.getManualMatch().getSource();
				if (ontResource.isIndividual()) {
					Individual sourceIndividual = ontResource.asIndividual();
					CreateNewReferenceIndividualDialog createNewReferenceIndividual = new CreateNewReferenceIndividualDialog(

					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.DIALOG_TRIM
						| SWT.APPLICATION_MODAL, matchingModel.getCurrentReferenceClass());
					createNewReferenceIndividual.setSourceIndividual(sourceIndividual);
					createNewReferenceIndividual.open();
					listViewerModelReference.refresh();

				} else if (ontResource.isClass()) {
					OntClass sourceOntClass = ontResource.asClass();
					CreateNewReferenceOntClassDialog createNewReferenceOntClass = new CreateNewReferenceOntClassDialog(

					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.DIALOG_TRIM
						| SWT.APPLICATION_MODAL, matchingModel.getCurrentReferenceClass());
					createNewReferenceOntClass.setSourceOntClass(sourceOntClass);
					createNewReferenceOntClass.open();
					listViewerMetamodelReference.refresh();
				}
			}
		});
		btnAddToReference.setText("Add to reference");

		txtSourceResource = new Text(MatchingOperations, SWT.BORDER);
		txtSourceResource.setEditable(false);
		txtSourceResource.setText("Unmapped resources");
		GridData gd_txtSourceResource = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_txtSourceResource.widthHint = 137;
		txtSourceResource.setLayoutData(gd_txtSourceResource);

		btnConfirm = new Button(MatchingOperations, SWT.NONE);
		GridData gd_btnConfirm = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_btnConfirm.widthHint = 124;
		btnConfirm.setLayoutData(gd_btnConfirm);

		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				matchingModel.toggleManualMatch();
			}
		});
		btnConfirm.setText("Confirm");

		txtReferenceresource = new Text(MatchingOperations, SWT.BORDER);
		txtReferenceresource.setEditable(false);
		txtReferenceresource.setText("Reasoner Status");
		GridData gd_txtReferenceresource = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_txtReferenceresource.widthHint = 257;
		txtReferenceresource.setLayoutData(gd_txtReferenceresource);

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

				matchingModel.selectOntResource(referenceIndividual);

			}
		});
		listViewerModelReference.setSorter(new OntResourceSorter());

		treeModel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Get Individual
				EObject eObject = (EObject) ((StructuredSelection) treeViewerModel.getSelection()).getFirstElement();

				String baseIndividualName = matchingModel.getEObjectName(eObject);
				String baseOntologyURIString = ontologyModel.getBaseOntologyURI().toString();

				Individual sourceIndividual = ontologyModel.getIndividual(URI.createURI(baseOntologyURIString + "#"
					+ baseIndividualName));

				matchingModel.selectOntResource(sourceIndividual);
				// Get Type and select in treeviewerMetamodel
				listViewerMetamodelReference.refresh();

				OntResource metaSourceOntResource = matchingModel.getSourceMetaOntResource();
				treeViewerMetamodel.setSelection(new StructuredSelection(metaSourceOntResource), true);

				// Get ReferenceOntClass of the individual and select it if
				// possible

				if (matchingModel.getReferenceIndividual() != null) {
					// manualMatch.setTarget(referenceIndividual);
					listViewerModelReference.setSelection(
						new StructuredSelection(matchingModel.getReferenceIndividual()), true);
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

				matchingModel.selectOntResource(sourceOntResource);

				matchingModel.setSourceMetaOntResource(sourceOntResource);
				OntResource referenceMetaOntResource = ontologyModel.getReferenceOntResource(sourceOntResource);

				listViewerMetamodelReference.refresh();

				// if equivalent reference class exists select the
				// external class and switch to revoke

				if (referenceMetaOntResource != null) {
					listViewerMetamodelReference.setSelection(new StructuredSelection(referenceMetaOntResource), true);
				}

			}

		});

		sashFormModel.setWeights(new int[] { 1, 1 });

		Composite compositeFileOperations = new Composite(sashForm, SWT.NONE);
		compositeFileOperations.setLayout(new GridLayout(2, true));

		Label lblFileName = new Label(compositeFileOperations, SWT.NONE);
		lblFileName.setText("Mapping ontology file name");
		new Label(compositeFileOperations, SWT.NONE);

		textFileName = new Text(compositeFileOperations, SWT.BORDER);
		textFileName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnSave = new Button(compositeFileOperations, SWT.NONE);

		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				Owl.saveToOwl(OntologyHelper.getOntologyURL(matchingModel.getSourceOntologyFileName()),
					ontologyModel.getBaseOntology());

			}
		});
		btnSave.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnSave.setText("Save mapping");
		new Label(compositeFileOperations, SWT.NONE);

		Button btnSaveReferenceOntology = new Button(compositeFileOperations, SWT.NONE);
		btnSaveReferenceOntology.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				OntModel referenceOntModel = ontologyModel.getOntologyModel().getImportedModel(
					ontologyModel.getReferenceOntologyURI().toString());
				Owl.saveModelToOwl(OntologyHelper.getOntologyURL(matchingModel.getReferenceOntologyFileName()),
					referenceOntModel);

			}
		});
		btnSaveReferenceOntology.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnSaveReferenceOntology.setText("Save reference");
		sashForm.setWeights(new int[] { 173, 36, 161, 85 });
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
			matchingModel, "sourceOntologyFileName");
		bindingContext.bindValue(textFileNameObserveTextObserveWidget,
			recommendationProviderSourceOntologyFileNameObserveValue, null, null);
		//
		IObservableValue btnDeclineObserveTextObserveWidget = SWTObservables.observeText(btnConfirm);
		IObservableValue recommendationProviderButtonStringObserveValue = BeansObservables.observeValue(matchingModel,
			"buttonString");
		bindingContext.bindValue(btnDeclineObserveTextObserveWidget, recommendationProviderButtonStringObserveValue,
			null, null);
		//
		IObservableValue txtSourceResourceObserveTextObserveWidget = SWTObservables.observeText(txtSourceResource);
		IObservableValue recommendationProviderMappingStatusObserveValue = BeansObservables.observeValue(matchingModel,
			"mappingStatus");
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
			Realm.getDefault(), matchingModel, "currentIndividualCandidates");
		listViewerModelReference.setInput(recommendationProviderCurrentIndividualCandidatesObserveList);
		//
		ObservableListContentProvider listContentProvider_1 = new ObservableListContentProvider();
		listViewerMetamodelReference.setContentProvider(listContentProvider_1);
		//
		IObservableMap observeMap_1 = PojoObservables.observeMap(listContentProvider_1.getKnownElements(),
			OntResource.class, "localName");
		listViewerMetamodelReference.setLabelProvider(new ObservableMapLabelProvider(observeMap_1));
		//
		IObservableList recommendationProviderMetaCandiatesOntResourcesObserveList = BeansObservables.observeList(
			Realm.getDefault(), matchingModel, "metaCandiatesOntResources");
		listViewerMetamodelReference.setInput(recommendationProviderMetaCandiatesOntResourcesObserveList);
		//
		return bindingContext;
	}
}
