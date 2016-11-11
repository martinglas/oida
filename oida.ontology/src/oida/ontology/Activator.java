/*******************************************************************************
 * <copyright> Copyright (c) 2009-2014 Martin Glas. All rights reserved. This program and the accompanying
 *  materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 *  and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.ontology;

import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import oida.ontology.manager.IOntologyManagerClient;
import oida.ontologyMgr.LocalOntology;

public class Activator implements BundleActivator, IListChangeListener<LocalOntology> {

	private static final String MANAGER_EXTENSIONPOINT_ID = "oida.ontology.managerextensionpoint";
	
	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		
		IExtensionRegistry registry = (IExtensionRegistry)bundleContext.getService(bundleContext.getServiceReference(IExtensionRegistry.class.getName()));
		
		IConfigurationElement[] config = registry.getConfigurationElementsFor(MANAGER_EXTENSIONPOINT_ID);
		try {
			for (IConfigurationElement e : config) {
				System.out.println("Evaluating extension");
				final Object o = e.createExecutableExtension("class");
				if (o instanceof IOntologyManagerClient) {
					registerOntologyManagerClient((IOntologyManagerClient)o);
				}
			}
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private void registerOntologyManagerClient(IOntologyManagerClient client) {
		client.getOntologyList().addListChangeListener(this);
	}
	
	@Override
	public void handleListChange(ListChangeEvent<? extends LocalOntology> event) {
		System.out.println("Ontology List changed!!!");
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
