package oida.ontology.manager.ui;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.parsley.composite.FormControlFactory;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;

@SuppressWarnings("deprecation")
public class OntologyLibraryFormControlFactory extends FormControlFactory {
	@SuppressWarnings("rawtypes")
	public Control control_Library_creationDate(DataBindingContext dbc, IObservableValue featureObservable) {
		//Creating the control
		Label label = getToolkit().createLabel(getParent(), "");
		label.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TREE_BORDER);
		label.setBackground(getToolkit().getColors().getColor(IFormColors.TITLE));
		//Binding the control to the feature observable
		dbc.bindValue(SWTObservables.observeText(label, SWT.Modify), featureObservable);
		return label;
	}
}
