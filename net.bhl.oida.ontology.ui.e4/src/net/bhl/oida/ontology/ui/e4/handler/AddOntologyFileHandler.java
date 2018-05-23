
package net.bhl.oida.ontology.ui.e4.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import net.bhl.oida.ontology.model.ontologymgr.LocalOntologyMetaInfo;
import net.bhl.oida.ontology.model.ontologymgr.OntologymgrFactory;
import net.bhl.oida.ontology.service.OIDAOntologyService;
import net.bhl.oida.util.constants.StringConstants;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2017-12-13
 *
 */
public class AddOntologyFileHandler {
	@Execute
	public void execute(Shell shell, OIDAOntologyService ontologyService) {
		FileDialog fd = new FileDialog(shell, SWT.OPEN);
		fd.setText("Open");
		String[] filterExt = { "*.owl", "*.*" };
		fd.setFilterExtensions(filterExt);
		if (fd.open() != null) {
			LocalOntologyMetaInfo metaInfo = OntologymgrFactory.eINSTANCE.createLocalOntologyMetaInfo();

			metaInfo.setLocalPath(fd.getFilterPath() + StringConstants.BACKSLASH + fd.getFileName());

			ontologyService.AddOntologyToLibrary(metaInfo);
		}
	}
}