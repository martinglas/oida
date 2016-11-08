package oida.ontology.manager.ui.swt;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;

/**
 * 
 * @since 2016-11-08
 * @author Michael.Shamiyeh
 *
 */
public class FilePathSelectorComposite extends Composite {

	private Text pathTextBox;
	private Button openDialogButton;

	private boolean fileSelector = true;

	public FilePathSelectorComposite(Composite parent, int style) {
		super(parent, style);
		createContent();
	}

	public FilePathSelectorComposite(Composite parent, int style, boolean fileSelector) {
		super(parent, style);
		this.fileSelector = fileSelector;
		createContent();
	}

	public void createContent() {
		GridLayout layout = new GridLayout(2, false);
		setLayout(layout);
		
		pathTextBox = new Text(this, SWT.BORDER | SWT.READ_ONLY);

		openDialogButton = new Button(this, SWT.PUSH);
		openDialogButton.setText("...");
		openDialogButton.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent e) {
				if (fileSelector) {
					FileDialog dlg = new FileDialog(openDialogButton.getShell(), SWT.OPEN);
					dlg.setText("Open");
					String path = dlg.open();
					if (path == null)
						return;
					pathTextBox.setText(path);
				} else {
					DirectoryDialog dlg = new DirectoryDialog(openDialogButton.getShell(), SWT.OPEN);
					dlg.setText("Open");
					String path = dlg.open();
					if (path == null)
						return;
					pathTextBox.setText(path);
				}
			}
		});
	}

	public String getText() {
		return pathTextBox.getText();

	}

	public Text getTextControl() {
		return pathTextBox;
	}

	public File getFile() {
		String text = pathTextBox.getText();
		if (text.length() == 0)
			return null;
		return new File(text);
	}
}
