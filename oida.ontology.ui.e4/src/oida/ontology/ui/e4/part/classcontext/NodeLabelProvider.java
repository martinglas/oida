package oida.ontology.ui.e4.part.classcontext;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.gef4.zest.fx.ZestProperties;
import org.eclipse.gef4.zest.fx.jface.IGraphAttributesProvider;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import javafx.scene.shape.Polygon;
import oida.ontology.OntologyClass;

public class NodeLabelProvider extends LabelProvider implements IGraphAttributesProvider {
	public static class TriangleHead extends Polygon {
		public TriangleHead() {
			super(6,3,0,0,6,-3,6,3);
		}
	}
	
	private ResourceManager resourceManager = new LocalResourceManager(JFaceResources.getResources());

	@Override
	public String getText(Object element) {
		if (element instanceof OntologyClass) {
			OntologyClass myNode = (OntologyClass)element;
			return myNode.getName();
		}

		return element.toString();
	}
	
	@Override
    public Image getImage(Object element) {
        return null;
    }

	@Override
	public Map<String, Object> getEdgeAttributes(Object sourceNode, Object targetNode) {
		Map<String, Object> edgeAttributes = new HashMap<>();
		edgeAttributes.put(ZestProperties.TARGET_DECORATION__E, new TriangleHead());
		return edgeAttributes;
	}

	@Override
	public Map<String, Object> getGraphAttributes() {
		return Collections.emptyMap();
	}

	@Override
	public Map<String, Object> getNestedGraphAttributes(Object nestingNode) {
		return Collections.emptyMap();
	}

	@Override
	public Map<String, Object> getNodeAttributes(Object node) {
		return Collections.emptyMap();
	}
}
