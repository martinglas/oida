 
package oida.ontology.ui.e4.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import oida.ontology.service.IOIDAOntologyService;
import oida.ontologyMgr.LocalReferenceOntologyFileSet;
import oida.ontologyMgr.OntologyMgrFactory;

public class AddOntologyFileHandler {
	@Execute
	public void execute(Shell shell, IOIDAOntologyService ontologyService) {
		FileDialog fd = new FileDialog(shell, SWT.OPEN);
        fd.setText("Open");
        String[] filterExt = { "*.owl", "*.*" };
        fd.setFilterExtensions(filterExt);
        if (fd.open() != null) {
        	LocalReferenceOntologyFileSet fileSet = OntologyMgrFactory.eINSTANCE.createLocalReferenceOntologyFileSet();
            
            fileSet.setFileName(fd.getFileName());
            fileSet.setPath(fd.getFilterPath());
            
            ontologyService.getLibrary().getOntologies().add(fileSet);
        }
	}
}