/**
 */
package oida.ontologyMgr.impl;

import oida.ontologyMgr.LocalOntology;
import oida.ontologyMgr.OntologyMgrPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Local Ontology</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link oida.ontologyMgr.impl.LocalOntologyImpl#getPath <em>Path</em>}</li>
 *   <li>{@link oida.ontologyMgr.impl.LocalOntologyImpl#getFile <em>File</em>}</li>
 *   <li>{@link oida.ontologyMgr.impl.LocalOntologyImpl#getMaintenanceFile <em>Maintenance File</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LocalOntologyImpl extends MinimalEObjectImpl.Container implements LocalOntology {
	/**
	 * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected String path = PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getFile() <em>File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFile()
	 * @generated
	 * @ordered
	 */
	protected static final String FILE_EDEFAULT = "ontology.owl";

	/**
	 * The cached value of the '{@link #getFile() <em>File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFile()
	 * @generated
	 * @ordered
	 */
	protected String file = FILE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaintenanceFile() <em>Maintenance File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaintenanceFile()
	 * @generated
	 * @ordered
	 */
	protected static final String MAINTENANCE_FILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMaintenanceFile() <em>Maintenance File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaintenanceFile()
	 * @generated
	 * @ordered
	 */
	protected String maintenanceFile = MAINTENANCE_FILE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LocalOntologyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OntologyMgrPackage.Literals.LOCAL_ONTOLOGY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPath() {
		return path;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPath(String newPath) {
		String oldPath = path;
		path = newPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntologyMgrPackage.LOCAL_ONTOLOGY__PATH, oldPath, path));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFile() {
		return file;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFile(String newFile) {
		String oldFile = file;
		file = newFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntologyMgrPackage.LOCAL_ONTOLOGY__FILE, oldFile, file));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMaintenanceFile() {
		return maintenanceFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaintenanceFile(String newMaintenanceFile) {
		String oldMaintenanceFile = maintenanceFile;
		maintenanceFile = newMaintenanceFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntologyMgrPackage.LOCAL_ONTOLOGY__MAINTENANCE_FILE, oldMaintenanceFile, maintenanceFile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OntologyMgrPackage.LOCAL_ONTOLOGY__PATH:
				return getPath();
			case OntologyMgrPackage.LOCAL_ONTOLOGY__FILE:
				return getFile();
			case OntologyMgrPackage.LOCAL_ONTOLOGY__MAINTENANCE_FILE:
				return getMaintenanceFile();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case OntologyMgrPackage.LOCAL_ONTOLOGY__PATH:
				setPath((String)newValue);
				return;
			case OntologyMgrPackage.LOCAL_ONTOLOGY__FILE:
				setFile((String)newValue);
				return;
			case OntologyMgrPackage.LOCAL_ONTOLOGY__MAINTENANCE_FILE:
				setMaintenanceFile((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case OntologyMgrPackage.LOCAL_ONTOLOGY__PATH:
				setPath(PATH_EDEFAULT);
				return;
			case OntologyMgrPackage.LOCAL_ONTOLOGY__FILE:
				setFile(FILE_EDEFAULT);
				return;
			case OntologyMgrPackage.LOCAL_ONTOLOGY__MAINTENANCE_FILE:
				setMaintenanceFile(MAINTENANCE_FILE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case OntologyMgrPackage.LOCAL_ONTOLOGY__PATH:
				return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
			case OntologyMgrPackage.LOCAL_ONTOLOGY__FILE:
				return FILE_EDEFAULT == null ? file != null : !FILE_EDEFAULT.equals(file);
			case OntologyMgrPackage.LOCAL_ONTOLOGY__MAINTENANCE_FILE:
				return MAINTENANCE_FILE_EDEFAULT == null ? maintenanceFile != null : !MAINTENANCE_FILE_EDEFAULT.equals(maintenanceFile);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (path: ");
		result.append(path);
		result.append(", file: ");
		result.append(file);
		result.append(", maintenanceFile: ");
		result.append(maintenanceFile);
		result.append(')');
		return result.toString();
	}

} //LocalOntologyImpl
