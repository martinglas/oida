package oida.ontology.util;

import java.util.ArrayList;
import java.util.List;

import net.bhl.oida.ontology.model.owlontology.OntologyClass;

public final class OntologyClassUtil {
	public static List<OntologyClass> findAllSuperClasses(OntologyClass clazz) {
		ArrayList<OntologyClass> superClasses = new ArrayList<OntologyClass>();
		superClasses.addAll(clazz.getSuperClasses());
		
		for (OntologyClass c : clazz.getSuperClasses())
			superClasses.addAll(findAllSuperClasses(c));

		return superClasses;
	}
	
	public static List<OntologyClass> findAllSiblingClasses(OntologyClass clazz) {
		ArrayList<OntologyClass> resultClasses = new ArrayList<OntologyClass>();
		
		List<OntologyClass> directSuperClasses = clazz.getSuperClasses();
		for (OntologyClass superClass : directSuperClasses) {
			for (OntologyClass siblingClass : superClass.getSubClasses()) {
				if (!siblingClass.equals(clazz) && !resultClasses.contains(siblingClass))
					resultClasses.add(siblingClass);
			}
		}

		return resultClasses;
	}
}
