/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

public class ObjectLabelMappings {
	private List<ObjectLabelMapping> mappings;

	public ObjectLabelMappings() {
		mappings = new ArrayList<ObjectLabelMapping>();
	}

	public List<ObjectLabelMapping> getMappings() {
		return mappings;
	}

	public void setMappings(List<ObjectLabelMapping> mappings) {
		this.mappings = mappings;
	}

	public void addMapping(EObject eObject) {
		getMappings().add(new ObjectLabelMapping(eObject));
	}

	public void clear() {
		mappings.clear();
	}

}
