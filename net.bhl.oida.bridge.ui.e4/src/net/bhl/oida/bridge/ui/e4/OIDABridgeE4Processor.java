package net.bhl.oida.bridge.ui.e4;

import java.util.Optional;

import javax.inject.Inject;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import net.bhl.oida.bridge.service.OIDABridge;
import net.bhl.oida.bridge.ui.Activator;
import net.bhl.oida.bridge.ui.e4.part.PrimaryRecommendationsViewPart;
import net.bhl.oida.bridge.ui.e4.part.RecommendationDetailsPart;
import net.bhl.oida.util.ExtensionPointUtil;

/**
 * 
 * @author Michael.Shamiyeh
 * @since 26.05.2018
 *
 */
public class OIDABridgeE4Processor {

    @Inject
    OIDABridge bridge;

    @Execute
    public void execute(EModelService modelService, EPartService partService, MApplication app) {
	Optional<IUIArrangements> uiArrangements = loadUIArrangementExtensions();

	if (uiArrangements.isPresent()) {
	    MPartStack recommendationStack = (MPartStack)modelService.find(uiArrangements.get().getRecommendationViewStackID(), app);

//	    for (IPrimaryRecommender recommender : RecommenderSystem.getInstance().getPrimaryRecommender()) {
//		MPart recommendationPart = modelService.createPart(modelService.getPartDescriptor(PrimaryRecommendationsViewPart.PART_ID));
//		//recommendationPart.setObject(RecommenderSystem.getInstance().getRecommenderResource(recommender));
//		recommendationPart.setLabel(recommender.getName());
//		recommendationStack.getChildren().add(recommendationPart);
//		modelService.bringToTop(recommendationPart);
//	    }

	    MPart recommendationPart = modelService.createPart(modelService.getPartDescriptor(PrimaryRecommendationsViewPart.PART_ID));
	    //recommendationPart.setObject(RecommenderSystem.getInstance().getCurrentPrimaryRecommendationsResource());
	    recommendationPart.setLabel("Aggregated Recommendations");
	    recommendationStack.getChildren().add(recommendationPart);
	    modelService.bringToTop(recommendationPart);

	    MPartStack recommendationDetailsStack = (MPartStack)modelService.find(uiArrangements.get().getEntityDetailsStackID(), app);
	    MPart recommendationDetailsPart = modelService.createPart(modelService.getPartDescriptor(RecommendationDetailsPart.PART_ID));
	    recommendationDetailsStack.getChildren().add(recommendationDetailsPart);
	    modelService.bringToTop(recommendationDetailsPart);
	}
    }

    private Optional<IUIArrangements> loadUIArrangementExtensions() {
	try {
	    return Optional.of(ExtensionPointUtil.loadSingleExtension(Activator.getExtensionRegistry(), IUIArrangements.class, Activator.OIDA_EXTENSIONPOINT_ID_UIARRANGEMENTS));
	} catch (CoreException e) {
	    return Optional.empty();
	}
    }
}
