
package oida.ontology.ui.e4.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import oida.ontology.service.IOIDAOntologyService;
import oida.ontologyMgr.LocalOntologyMetaInfo;
import oida.ontologyMgr.OntologyMgrFactory;
import oida.util.constants.StringConstants;

public class AddOntologyFileHandler {
	@Execute
	public void execute(Shell shell, IOIDAOntologyService ontologyService) {
		FileDialog fd = new FileDialog(shell, SWT.OPEN);
		fd.setText("Open");
		String[] filterExt = { "*.owl", "*.*" };
		fd.setFilterExtensions(filterExt);
		if (fd.open() != null) {
			LocalOntologyMetaInfo metaInfo = OntologyMgrFactory.eINSTANCE.createLocalOntologyMetaInfo();

			metaInfo.setLocalPath(fd.getFilterPath() + StringConstants.BACKSLASH + fd.getFileName());

			ontologyService.AddOntologyToLibrary(metaInfo);
		}
	}
}