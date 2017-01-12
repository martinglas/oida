/**
 */
package oida.ontologyMgr.impl;

import oida.ontologyMgr.LocalReferenceOntologyFileSet;
import oida.ontologyMgr.OntologyMgrPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Local Reference Ontology File Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link oida.ontologyMgr.impl.LocalReferenceOntologyFileSetImpl#getMaintenanceFileName <em>Maintenance File Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LocalReferenceOntologyFileSetImpl extends OntologyFileImpl implements LocalReferenceOntologyFileSet {
	/**
	 * The default value of the '{@link #getMaintenanceFileName() <em>Maintenance File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaintenanceFileName()
	 * @generated
	 * @ordered
	 */
	protected static final String MAINTENANCE_FILE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMaintenanceFileName() <em>Maintenance File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaintenanceFileName()
	 * @generated
	 * @ordered
	 */
	protected String maintenanceFileName = MAINTENANCE_FILE_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LocalReferenceOntologyFileSetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OntologyMgrPackage.Literals.LOCAL_REFERENCE_ONTOLOGY_FILE_SET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMaintenanceFileName() {
		return maintenanceFileName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaintenanceFileName(String newMaintenanceFileName) {
		String oldMaintenanceFileName = maintenanceFileName;
		maintenanceFileName = newMaintenanceFileName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntologyMgrPackage.LOCAL_REFERENCE_ONTOLOGY_FILE_SET__MAINTENANCE_FILE_NAME, oldMaintenanceFileName, maintenanceFileName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OntologyMgrPackage.LOCAL_REFERENCE_ONTOLOGY_FILE_SET__MAINTENANCE_FILE_NAME:
				return getMaintenanceFileName();
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
			case OntologyMgrPackage.LOCAL_REFERENCE_ONTOLOGY_FILE_SET__MAINTENANCE_FILE_NAME:
				setMaintenanceFileName((String)newValue);
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
			case OntologyMgrPackage.LOCAL_REFERENCE_ONTOLOGY_FILE_SET__MAINTENANCE_FILE_NAME:
				setMaintenanceFileName(MAINTENANCE_FILE_NAME_EDEFAULT);
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
			case OntologyMgrPackage.LOCAL_REFERENCE_ONTOLOGY_FILE_SET__MAINTENANCE_FILE_NAME:
				return MAINTENANCE_FILE_NAME_EDEFAULT == null ? maintenanceFileName != null : !MAINTENANCE_FILE_NAME_EDEFAULT.equals(maintenanceFileName);
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
		result.append(" (maintenanceFileName: ");
		result.append(maintenanceFileName);
		result.append(')');
		return result.toString();
	}

} //LocalReferenceOntologyFileSetImpl
