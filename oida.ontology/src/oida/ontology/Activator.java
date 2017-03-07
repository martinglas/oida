/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology;

import org.eclipse.e4.core.di.InjectorFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import oida.ontology.service.IOIDAOntologyService;
import oida.ontology.service.OIDAOntologyService;

/**
 * 
 * @author Michael.Shamiyeh
 * @since
 */
public class Activator implements BundleActivator {
	/**
	 * This is the ID of the extension point which exposes the Ontology Manager.
	 */
	public static final String ONTOLOGYMANAGERFACTORY_EXTENSIONPOINT_ID = "oida.ontology.managerFactory";

	private static BundleContext context;

	public static BundleContext getBundleContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		InjectorFactory.getDefault().addBinding(IOIDAOntologyService.class).implementedBy(OIDAOntologyService.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}
}
