package oida.bridge.util;

public class BridgeHelper {

	public static String getNSfromRootElementName(String rootContainerName) {

		return "http://" + rootContainerName + "#";
	}

	public static String getModelOntologyName(String rootContainerName) {
		return rootContainerName + ".owl";
	}

}
