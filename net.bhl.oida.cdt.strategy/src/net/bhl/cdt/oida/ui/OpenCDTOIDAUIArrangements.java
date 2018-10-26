package net.bhl.cdt.oida.ui;

import net.bhl.cdt.ui.e4.E4ResourceIds;
import net.bhl.oida.bridge.ui.e4.IUIArrangements;

public class OpenCDTOIDAUIArrangements implements IUIArrangements {
    @Override
    public String getRecommendationViewStackID() {
	return "net.bhl.cdt.ui.e4.perspective.cdtmodelling.partstack.bottom";
	//return E4ResourceIds.PARTSTACK_MODEL_ADDITIONS_TOP;
    }

    @Override
    public String getEntityDetailsStackID() {
	return E4ResourceIds.PARTSTACK_MODEL_ADDITIONS_BOTTOM;
    }

    @Override
    public String getModelEditorPartID() {
	return E4ResourceIds.PARTDESCRIPTOR_MODELEDITOR_ID;
    }
}
