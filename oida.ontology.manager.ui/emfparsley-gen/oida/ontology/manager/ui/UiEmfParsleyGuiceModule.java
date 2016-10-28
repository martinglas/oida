package oida.ontology.manager.ui;

import oida.ontology.manager.ui.binding.UiFormControlFactory;
import oida.ontology.manager.ui.resource.UiResourceManager;
import oida.ontology.manager.ui.ui.provider.UiFeatureCaptionProvider;
import oida.ontology.manager.ui.ui.provider.UiFeaturesProvider;
import oida.ontology.manager.ui.ui.provider.UiLabelProvider;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.composite.FormControlFactory;
import org.eclipse.emf.parsley.resource.ResourceManager;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.ui.plugin.AbstractUIPlugin;

@SuppressWarnings("all")
public class UiEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
  public UiEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public int valueTreeFormSashStyle() {
    return SWT.HORIZONTAL;
  }
  
  @Override
  public Class<? extends ILabelProvider> bindILabelProvider() {
    return UiLabelProvider.class;
  }
  
  @Override
  public Class<? extends FeatureCaptionProvider> bindFeatureCaptionProvider() {
    return UiFeatureCaptionProvider.class;
  }
  
  @Override
  public Class<? extends FeaturesProvider> bindFeaturesProvider() {
    return UiFeaturesProvider.class;
  }
  
  @Override
  public Class<? extends FormControlFactory> bindFormControlFactory() {
    return UiFormControlFactory.class;
  }
  
  @Override
  public Class<? extends ResourceManager> bindResourceManager() {
    return UiResourceManager.class;
  }
}
