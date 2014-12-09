/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology.model;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.util.iterator.Filter;

public class AnonymousOntClassFilter extends Filter<OntClass> {

	@Override
	public boolean accept(OntClass ontClass) {
		if (ontClass.isAnon()) {
			return true;
		}
		return false;
	}

}
