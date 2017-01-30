package oida.bridge.util;

import de.symo.model.base.ModelHelper;

public class BridgeHelper {

	public static String getNSfromRootElementName(String rootContainerName) {
		
		return "http://"+rootContainerName+"#";
	}
	
	public static String getModelOntologyName(String rootContainerName){
		return rootContainerName+".owl";
	}

}
