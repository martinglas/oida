package net.bhl.oida.bridge.ui.e4.part;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.emf.parsley.composite.FormDetailComposite;
import org.eclipse.emf.parsley.composite.FormFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Injector;

import net.bhl.oida.bridge.model.mapping.ClassEqualsMapping;
import net.bhl.oida.ontology.ui.ClassDetailView.ClassDetailViewInjectorProvider;

public class MetaModelClassDetailsViewPart {
	private FormDetailComposite formComposite;

	private FormFactory viewerFactory;

	@PostConstruct
	public void postConstruct(Composite parent, ESelectionService selectionService) {
		Injector injector = ClassDetailViewInjectorProvider.getInjector();
		viewerFactory = injector.getInstance(FormFactory.class);
		
		selectionService.addSelectionListener(ClassEquivalenceMappingsViewPart.PART_ID, new ISelectionListener() {
			@Override
			public void selectionChanged(MPart part, Object selection) {
				if (formComposite != null)
					formComposite.dispose();

				if (selection instanceof ClassEqualsMapping) {
					formComposite = viewerFactory.createFormDetailReadOnlyComposite(parent, SWT.NONE);
					formComposite.init(((ClassEqualsMapping)selection).getClazz1());
					parent.layout();
				}
			}
		});
	}
}