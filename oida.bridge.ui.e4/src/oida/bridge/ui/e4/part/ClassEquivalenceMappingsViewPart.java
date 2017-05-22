/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.bridge.ui.e4.part;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.emf.parsley.composite.TreeFormComposite;
import org.eclipse.emf.parsley.composite.TreeFormFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Injector;

import bridgemodel.ClassEqualsMapping;
import oida.bridge.service.IOIDABridge;
import oida.ontology.OntologyClass;
import oida.ontology.ui.view.ClassEquivalenceMappingsView.ClassEquivalenceMappingsViewInjectorProvider;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-04-18
 *
 */
public class ClassEquivalenceMappingsViewPart {
	public static final String PART_ID = "oida.bridge.ui.e4.part.classequivalencemappingsviewpart";

	private TreeFormComposite treeFormComposite;

	@PostConstruct
	public void postConstruct(Composite parent, MPart part, ESelectionService selectionService, IOIDABridge oidaBridge) {
		// Guice injector
		Injector injector = ClassEquivalenceMappingsViewInjectorProvider.getInjector();

		TreeFormFactory treeFormFactory = injector.getInstance(TreeFormFactory.class);
		// create the tree-form composite

		treeFormComposite = treeFormFactory.createTreeFormComposite(parent, SWT.SINGLE);

		// update the composite
		treeFormComposite.update(oidaBridge.getMetaModelMappingsResource());
		
		treeFormComposite.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectionService.setSelection(((TreeSelection)event.getSelection()).getFirstElement());
			}
		});
		
		selectionService.addSelectionListener(MetaModelClassMappingPart.PART_ID, new ISelectionListener() {
			@Override
			public void selectionChanged(MPart part, Object selection) {
				if (selection != null && selection instanceof OntologyClass && ((OntologyClass)selection).isMappingExists()) {
					Optional<ClassEqualsMapping> optMapping = oidaBridge.getClassMapping((OntologyClass)selection);

					if (optMapping.isPresent()) {
						StructuredSelection internalSelection = new StructuredSelection(optMapping.get());
						treeFormComposite.getViewer().setSelection(internalSelection);
					}
				}
			}
		});
	}
}
