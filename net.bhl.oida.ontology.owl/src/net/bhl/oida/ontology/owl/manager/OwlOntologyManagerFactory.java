/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package net.bhl.oida.ontology.owl.manager;

import net.bhl.oida.ontology.manager.IOntologyManager;
import net.bhl.oida.ontology.manager.IOntologyManagerFactory;

public class OwlOntologyManagerFactory implements IOntologyManagerFactory {
	public OwlOntologyManagerFactory() {
	}
	
	@Override
	public IOntologyManager getNewManager() {
		return (IOntologyManager)new OwlOntologyManager();
	}
}
