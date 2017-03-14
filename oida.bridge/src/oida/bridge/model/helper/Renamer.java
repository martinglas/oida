package oida.bridge.model.helper;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import oida.bridge.model.renamer.IRenamerStrategy;

/**
 * This class renames model elements in order to avoid naming conflicts.
 * 
 * @author Martin Glas
 * 
 */
public class Renamer {
	private IRenamerStrategy renamerStrategy;
	private EObject rootEObject;

	public Renamer(EObject rootEObject, IRenamerStrategy strategy) {

		this.rootEObject = rootEObject;
		this.renamerStrategy = strategy;
	}

	public String getEObjectName(EObject eObject) {
		// TODO Add null pointer handling
		return renamerStrategy.getEObjectName(eObject);
	}
	
	public String getEClassName(EClass eClass) {
		return renamerStrategy.getEClassName(eClass);
	}

	// public boolean isUnabiguous() {
	// List<EObject> comprisedObjects =
	// ModelProviderHelper.getComprisedEObjects(rootEObject);
	// List<String> names = new ArrayList<String>();
	// for (EObject comprisedObject : comprisedObjects) {
	// String name = getEObjectName(comprisedObject);
	// if (names.contains(name)) {
	// System.out.println("The " +
	// getRenamerStrategy().getClass().getSimpleName() + " renaming strategy is
	// ambiguous. At least two objects are named " + name);
	// return false;
	// }
	//
	// else {
	// names.add(name);
	// }
	// }
	// System.out.println("The " +
	// getRenamerStrategy().getClass().getSimpleName() + " renaming strategy is
	// unambiguous.");
	// return true;
	// }
	//
	// public List<EObject> getAmbiguousEObjects() {
	// List<EObject> comprisedEObjects =
	// ModelProviderHelper.getComprisedEObjects(rootEObject);
	//
	// List<String> individualNames = new ArrayList<String>();
	// List<String> ambiguousNames = new ArrayList<String>();
	// for (EObject comprisedObject : comprisedEObjects) {
	// String name = getEObjectName(comprisedObject);
	// if (individualNames.contains(name)) {
	// ambiguousNames.add(name);
	// } else {
	// individualNames.add(name);
	// }
	// }
	//
	// List<EObject> ambiguousEObjects = new ArrayList<EObject>();
	//
	// for (EObject comprisedObject : comprisedEObjects) {
	// String name = getEObjectName(comprisedObject);
	// if (ambiguousNames.contains(name)) {
	// ambiguousEObjects.add(comprisedObject);
	// }
	//
	// }
	//
	// return ambiguousEObjects;
	//
	// }
	//
	// public void printAmbiguousObjects(EObject rootEObject, String
	// listFileName) {
	// Writer output = null;
	// File file = new File(listFileName);
	// try {
	// output = new BufferedWriter(new FileWriter(file));
	// output.write("Ambiguous Objects:");
	// for (EObject ambiguousEObject : getAmbiguousEObjects()) {
	// output.write("\n");
	// printContainers(ambiguousEObject, rootEObject, output);
	//
	// }
	// output.close();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// private void printContainers(EObject eObject, EObject rootEObject, Writer
	// output) throws IOException {
	// output.write(ModelProviderHelper.getModelElementName(eObject) + ".");
	// if ((!eObject.equals(rootEObject)) && (eObject.eContainer() != null)) {
	// printContainers(eObject.eContainer(), rootEObject, output);
	// }
	//
	// }
	//
	// public String getEStructuralFeatureName(EStructuralFeature
	// eStructuralFeature) {
	// return
	// getRenamerStrategy().getEStructuralFeatureName(eStructuralFeature);
	// }
	//
	// public String getEClassName(EClass eClass) {
	//
	// return getRenamerStrategy().getEClassName(eClass);
	// }

}
