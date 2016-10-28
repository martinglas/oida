package oida.ontology.manager.ui;

import com.google.inject.Injector;
import oida.ontology.manager.ui.UiInjectorProvider;
import org.eclipse.emf.parsley.runtime.ui.AbstractGuiceAwareExecutableExtensionFactory;

@SuppressWarnings("all")
public class UiExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {
  @Override
  public Injector getInjector() throws Exception {
    return UiInjectorProvider.getInjector();
  }
}
