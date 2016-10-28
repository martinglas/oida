package oida.ontology.manager.ui;

import com.google.inject.Guice;
import com.google.inject.Injector;
import oida.ontology.manager.ui.UiEmfParsleyGuiceModule;
import org.eclipse.emf.parsley.runtime.ui.PluginUtil;

@SuppressWarnings("all")
public class UiInjectorProvider {
  private static Injector injector;
  
  public static synchronized Injector getInjector() {
    if (injector == null) {
      injector = Guice.createInjector(
        new UiEmfParsleyGuiceModule(PluginUtil.getPlugin(
          PluginUtil.getBundle(UiInjectorProvider.class))));
    }
    return injector;
  }
}
