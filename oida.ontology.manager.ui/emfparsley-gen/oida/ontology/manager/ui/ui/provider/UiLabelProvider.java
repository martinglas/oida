package oida.ontology.manager.ui.ui.provider;

import OIDAOntMgr.GitRepoOntology;
import OIDAOntMgr.Library;
import OIDAOntMgr.LocalOntology;
import com.google.inject.Inject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;

@SuppressWarnings("all")
public class UiLabelProvider extends ViewerLabelProvider {
  @Inject
  public UiLabelProvider(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }
  
  public String text(final Library it) {
    return "Listed Ontologies";
  }
  
  public String text(final LocalOntology e) {
    String _file = e.getFile();
    return _file;
  }
  
  public String text(final GitRepoOntology e) {
    String _file = e.getFile();
    return _file;
  }
  
  public Object image(final Library it) {
    return "OntologyLibrary_16.png";
  }
  
  public Object image(final LocalOntology it) {
    return "OntologyLocal_16.png";
  }
  
  public Object image(final GitRepoOntology it) {
    return "OntologyRepository_16.png";
  }
}
