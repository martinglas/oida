/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.ui.e4.handler;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 2016-11-08
 *
 */
public class SaveOntologyLibrary {
	@Execute
	void execute(EPartService partService, @Named(IServiceConstants.ACTIVE_PART) MPart part) {
		partService.savePart(part, false);
	}
}