/*******************************************************************************
 * <copyright> Copyright (c) 2009-2017 Bauhaus Luftfahrt e.V.. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html </copyright>
 ******************************************************************************/
package oida.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;

public class OidaHelper {

	private OidaHelper() {
	}

	/**
	 * This method returns the path of the current workspace as string.
	 * 
	 * @param pluginID
	 *            the ID of the plug-in
	 * @return the file path to the current workspace as string
	 */
	public static URL getPluginFileURL(String pluginID) {

		try {
			return FileLocator.resolve(new URL("platform:/plugin/" + pluginID + "/"));
		} catch (MalformedURLException e) {
			System.out.println("Could not form a valid URL from " + pluginID);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not locate a file folder at " + pluginID);
			e.printStackTrace();
		}
		return null;
	}

	public static String getPluginFilePath(String pluginID) {
		URL pluginPathURL = OidaHelper.getPluginFileURL(pluginID);

		return pluginPathURL.getFile();
	}

}
