package oida.ontology;

import oida.ontology.ui.provider.IOidaUIDataProvider;
import oida.ontologyMgr.Library;
import oida.ontologyMgr.OntologyMgrFactory;
import oida.ontologyMgr.OntologyMgrPackage;

public class OIDACoreUIDataProvider implements IOidaUIDataProvider {

	public OIDACoreUIDataProvider() {
	}

	@Override
	public Library getLibrary() {
		OntologyMgrPackage.eINSTANCE.eClass();
		
		Library dummy = OntologyMgrFactory.eINSTANCE.createLibrary();
		
		dummy.setOwner("Dummy");
		
		return dummy;
	}

}
