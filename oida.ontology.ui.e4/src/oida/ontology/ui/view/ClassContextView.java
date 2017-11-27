package oida.ontology.ui.view;

import org.eclipse.gef4.graph.Graph;

public class ClassContextView {
	public ClassContextView() {
		Graph.Builder builder = new Graph.Builder();
		
		builder.node("Node1");
		builder.node("Node2");
		builder.node("Node3");
		
		builder.edge("Node1", "Node2");
		builder.edge("Node2", "Node3");
		builder.edge("Node3", "Node1");
		
		Graph g = builder.build();
		
	}
}
