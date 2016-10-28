package oida.ontology.manager.ui.resource;

import OIDAOntMgr.Library;
import OIDAOntMgr.OIDAOntMgrFactory;
import java.util.Date;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.resource.ResourceManager;

@SuppressWarnings("all")
public class UiResourceManager extends ResourceManager {
  @Override
  public void initialize(final Resource it) {
    final Library l = OIDAOntMgrFactory.eINSTANCE.createLibrary();
    Date _date = new Date();
    l.setCreationDate(_date);
    EList<EObject> _contents = it.getContents();
    _contents.add(l);
  }
}
