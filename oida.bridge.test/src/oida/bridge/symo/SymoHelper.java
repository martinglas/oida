package oida.bridge.symo;

import org.eclipse.emf.ecore.EObject;

import de.symo.model.element.ElementPackage;
import de.symo.model.element.MetaData;
import de.symo.model.symo.ComponentRepository;
import de.symo.model.symo.ProjectRepository;
import de.symo.model.symo.SymoFactory;
import de.symo.model.symo.SymoPackage;

public class SymoHelper {

	public static EObject getRootContainer(EObject eObject) {
		EObject eContainer=eObject.eContainer();
		if (eObject.eContainer()!=null ) {
			if (isRootContainer(eContainer)){
				return eContainer;
			}
			
			else return getRootContainer(eContainer);
		}
		
		return null;
	}

	public static boolean isRootContainer(EObject eContainer) {
		if (ElementPackage.eINSTANCE.getARepository().isInstance(eContainer)) {
			return true;
		}
	
		return false;
	}

	/**
	 * This helper class returns a name for a given class from the symo metamodel. This helper class should be replaced by an metamodel ontology 
	 * @param rootContainer
	 * @return
	 */
	public static String getElementName(EObject rootContainer) {
		switch (rootContainer.eClass().getClassifierID()) {
			case  SymoPackage.PROJECT_REPOSITORY: {
				ProjectRepository projectRepository= (ProjectRepository) rootContainer;
				for (MetaData metadata:projectRepository.getMetaData()){
					if (metadata.getName().contains("name")){
						return metadata.getValue();
					}
				}
				
				//projectRepository.getMetaData().stream().filter(nmd -> {return nmd.getName().contains("name");}).findFirst().get().getValue();
				
			}break;
			
			
			
			
		
		}
		return null;
	}

}
