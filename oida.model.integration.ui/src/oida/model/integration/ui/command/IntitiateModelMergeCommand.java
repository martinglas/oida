/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.ui.command;

import oida.model.integration.merge.MergeModel;
import oida.model.integration.transformation.ontologygenerator.AllContainerContext;
import oida.model.integration.transformation.ontologygenerator.ContainerContext;
import oida.model.integration.transformation.ontologygenerator.Renamer;
import oida.model.integration.ui.dialogs.ConflictResolutionDialog;
import oida.model.integration.ui.dialogs.MigrateDialog;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.ui.PlatformUI;

public class IntitiateModelMergeCommand extends AbstractCommand {

	private MergeModel mergeModel;

	/**
	 * Constructor.
	 * 
	 * @param calculationGraph Calculation graph object which the viewer will show
	 */
	public IntitiateModelMergeCommand(EObject rootSourceObject, EObject rootTargetObject) {

		Renamer targetRenamer = new Renamer(rootTargetObject);
		targetRenamer.setRenamerStrategy(new AllContainerContext(rootTargetObject));
		Renamer sourceRenamer = new Renamer(rootSourceObject);
		sourceRenamer.setRenamerStrategy(new AllContainerContext(rootSourceObject));
		mergeModel = new MergeModel(rootSourceObject, sourceRenamer, rootTargetObject, targetRenamer);

	}

	@Override
	public void execute() {
		ConflictResolutionDialog mergeDialog = new ConflictResolutionDialog(PlatformUI.getWorkbench()
			.getActiveWorkbenchWindow().getShell(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL, mergeModel);
		mergeDialog.open();
		mergeModel.resolveConflicts();

		MigrateDialog migrateDialog = new MigrateDialog(
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL,
			mergeModel);

		mergeModel.setCurrentMatchings(mergeModel.getDiffModel().getTheirMatchings());

		migrateDialog.open();
		Renamer renamer = new Renamer(mergeModel.getTargetRootObject());
		renamer.setRenamerStrategy(new ContainerContext(mergeModel.getTargetRootObject()));
		mergeModel.updateTargetOntology(renamer);
		mergeModel.resetOntologyModel();

	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub

	}
}
