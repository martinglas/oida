package net.bhl.oida.ontology.ui.e4.part.classcontext;
//package oida.ontology.ui.e4.part.classcontext;
//
//import java.util.ArrayList;
//
//import org.eclipse.gef4.zest.fx.jface.IGraphContentProvider;
//
//import oida.ontology.OntologyClass;
//import oida.ontology.util.OntologyClassUtil;
//
//public class GraphContentProvider implements IGraphContentProvider {
//	private OntologyClass ontologyClass;
//	
//	public GraphContentProvider(OntologyClass cl) {
//		ontologyClass = cl;
//	}
//	
//	@Override
//	public Object[] getAdjacentNodes(Object node) {
//		ArrayList<Object> nodes = new ArrayList<Object>();
//		nodes.addAll(((OntologyClass)node).getSuperClasses());
//		return nodes.toArray();
//	}
//
//	@Override
//	public Object[] getNestedGraphNodes(Object node) {
//		return null;
//	}
//
//	@Override
//	public boolean hasNestedGraph(Object node) {
//		return false;
//	}
//
//	@Override
//	public Object[] getNodes() {
//		ArrayList<Object> nodes = new ArrayList<Object>();
//		nodes.add(ontologyClass);
//		nodes.addAll(OntologyClassUtil.findAllSuperClasses(ontologyClass));
//		nodes.addAll(OntologyClassUtil.findAllSiblingClasses(ontologyClass));
//		return nodes.toArray();
//	}
//}
