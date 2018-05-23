package net.bhl.oida.cdt.strategy;

import java.util.ArrayList;
import java.util.List;

import model.base.AIdentifiableItem;
import model.base.ANamedItem;
import oida.bridge.model.emf.strategy.EMFRenamerStrategy;

/**
 * 
 * @author Michael Shamiyeh
 * @since 24.04.2018
 *
 */
public class OpenCDTRenamerStrategy extends EMFRenamerStrategy {
    @Override
    public String getObjectID(Object object) {
	if (object instanceof AIdentifiableItem)
	    return ((AIdentifiableItem)object).getId();
	
	if(object instanceof ANamedItem)
	    return ((ANamedItem)object).getName();
	
	return "NoIDFound";
    }

    @Override
    public String getObjectName(Object object) {
	if (object instanceof ANamedItem)
	    return ((ANamedItem)object).getName();

	return "NoNameFound";
    }

    @Override
    public List<Object> getNameRelevantObjectsForObject(Object object) {
	List<Object> relevantObjects = new ArrayList<Object>();
	return relevantObjects;
    }

    @Override
    public String getMetaModelName() {
	return "OpenCDTMetaModel";
    }

}
