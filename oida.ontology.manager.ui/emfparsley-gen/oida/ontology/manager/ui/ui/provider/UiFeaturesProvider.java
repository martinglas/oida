package oida.ontology.manager.ui.ui.provider;

import org.eclipse.emf.parsley.ui.provider.EClassToEStructuralFeatureAsStringsMap;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;

@SuppressWarnings("all")
public class UiFeaturesProvider extends FeaturesProvider {
  @Override
  public void buildStringMap(final EClassToEStructuralFeatureAsStringsMap stringMap) {
    super.buildStringMap(stringMap);
    
    stringMap.mapTo("OIDAOntMgr.Library",
      "owner", "creationDate");
    stringMap.mapTo("OIDAOntMgr.LocalOntology",
      "path", "file", "maintenanceFile");
    stringMap.mapTo("OIDAOntMgr.GitRepoOntology",
      "path", "file", "maintenanceFile", "repoURL", "gitUsername", "gitPassword");
  }
}
