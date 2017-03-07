/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model;

import static org.junit.Assert.fail;

import org.junit.Test;

import oida.ontology.service.IOIDAOntologyService;

public class TestPluginTest {
	
	@Test
	public void test(IOIDAOntologyService ontologyService) {
		
		TotalObserver observ2 = new TotalObserver();
        observ2.doStuff();
		fail("Not yet implemented");
	}

}
