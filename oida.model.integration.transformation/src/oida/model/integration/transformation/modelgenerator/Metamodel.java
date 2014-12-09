/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.model.integration.transformation.modelgenerator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;

/**
 * This class provides methods for creating an EMF metamodel.
 * 
 * @author Martin Glas
 * 
 */
public class Metamodel {
	private EPackage basePackage;

	/**
	 * This is a constructor without parameter.
	 */
	public Metamodel() {

	}

	/**
	 * This is the constructor method.
	 * 
	 * @param basePackage
	 *            the base package of the metamodel
	 */
	public Metamodel(EPackage basePackage) {
		EcorePackage.eINSTANCE.eClass();
		setBasePackage(basePackage);

	}

	/**
	 * This is the getter method of the basePackage variable.
	 * 
	 * @return the current base package
	 */
	public EPackage getBasePackage() {
		return basePackage;
	}

	/**
	 * This is the setter method of the basePackage variable.
	 * 
	 * @param basePackage
	 *            an EPackage instance
	 */
	public void setBasePackage(EPackage basePackage) {
		this.basePackage = basePackage;
	}

	/**
	 * This method adds a new EClass to the base package.
	 * 
	 * @param newClass
	 *            the new EClass instance
	 */
	public void addClass(EClass newClass) {
		getBasePackage().getEClassifiers().add(newClass);

	}

}
