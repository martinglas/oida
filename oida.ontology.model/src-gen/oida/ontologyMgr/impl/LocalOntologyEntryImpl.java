/**
 */
package oida.ontologyMgr.impl;

import oida.ontology.Ontology;
import oida.ontology.OntologyPackage;

import oida.ontologyMgr.LocalOntologyEntry;
import oida.ontologyMgr.OntologyMgrPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Local Ontology Entry</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link oida.ontologyMgr.impl.LocalOntologyEntryImpl#getPath <em>Path</em>}</li>
 *   <li>{@link oida.ontologyMgr.impl.LocalOntologyEntryImpl#getFile <em>File</em>}</li>
 *   <li>{@link oida.ontologyMgr.impl.LocalOntologyEntryImpl#getMaintenanceFile <em>Maintenance File</em>}</li>
 *   <li>{@link oida.ontologyMgr.impl.LocalOntologyEntryImpl#getManagedOntology <em>Managed Ontology</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LocalOntologyEntryImpl extends MinimalEObjectImpl.Container implements LocalOntologyEntry {
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
	 * The cached value of the '{@link #getManagedOntology() <em>Managed Ontology</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManagedOntology()
	 * @generated
	 * @ordered
	 */
	protected Ontology managedOntology;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LocalOntologyEntryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OntologyMgrPackage.Literals.LOCAL_ONTOLOGY_ENTRY;
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
			eNotify(new ENotificationImpl(this, Notification.SET, OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__PATH, oldPath, path));
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
			eNotify(new ENotificationImpl(this, Notification.SET, OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__FILE, oldFile, file));
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
			eNotify(new ENotificationImpl(this, Notification.SET, OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__MAINTENANCE_FILE, oldMaintenanceFile, maintenanceFile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ontology getManagedOntology() {
		if (managedOntology != null && managedOntology.eIsProxy()) {
			InternalEObject oldManagedOntology = (InternalEObject)managedOntology;
			managedOntology = (Ontology)eResolveProxy(oldManagedOntology);
			if (managedOntology != oldManagedOntology) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__MANAGED_ONTOLOGY, oldManagedOntology, managedOntology));
			}
		}
		return managedOntology;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ontology basicGetManagedOntology() {
		return managedOntology;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetManagedOntology(Ontology newManagedOntology, NotificationChain msgs) {
		Ontology oldManagedOntology = managedOntology;
		managedOntology = newManagedOntology;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__MANAGED_ONTOLOGY, oldManagedOntology, newManagedOntology);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setManagedOntology(Ontology newManagedOntology) {
		if (newManagedOntology != managedOntology) {
			NotificationChain msgs = null;
			if (managedOntology != null)
				msgs = ((InternalEObject)managedOntology).eInverseRemove(this, OntologyPackage.ONTOLOGY__ONTOLOGY_ENTRY, Ontology.class, msgs);
			if (newManagedOntology != null)
				msgs = ((InternalEObject)newManagedOntology).eInverseAdd(this, OntologyPackage.ONTOLOGY__ONTOLOGY_ENTRY, Ontology.class, msgs);
			msgs = basicSetManagedOntology(newManagedOntology, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__MANAGED_ONTOLOGY, newManagedOntology, newManagedOntology));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__MANAGED_ONTOLOGY:
				if (managedOntology != null)
					msgs = ((InternalEObject)managedOntology).eInverseRemove(this, OntologyPackage.ONTOLOGY__ONTOLOGY_ENTRY, Ontology.class, msgs);
				return basicSetManagedOntology((Ontology)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__MANAGED_ONTOLOGY:
				return basicSetManagedOntology(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__PATH:
				return getPath();
			case OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__FILE:
				return getFile();
			case OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__MAINTENANCE_FILE:
				return getMaintenanceFile();
			case OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__MANAGED_ONTOLOGY:
				if (resolve) return getManagedOntology();
				return basicGetManagedOntology();
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
			case OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__PATH:
				setPath((String)newValue);
				return;
			case OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__FILE:
				setFile((String)newValue);
				return;
			case OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__MAINTENANCE_FILE:
				setMaintenanceFile((String)newValue);
				return;
			case OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__MANAGED_ONTOLOGY:
				setManagedOntology((Ontology)newValue);
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
			case OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__PATH:
				setPath(PATH_EDEFAULT);
				return;
			case OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__FILE:
				setFile(FILE_EDEFAULT);
				return;
			case OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__MAINTENANCE_FILE:
				setMaintenanceFile(MAINTENANCE_FILE_EDEFAULT);
				return;
			case OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__MANAGED_ONTOLOGY:
				setManagedOntology((Ontology)null);
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
			case OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__PATH:
				return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
			case OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__FILE:
				return FILE_EDEFAULT == null ? file != null : !FILE_EDEFAULT.equals(file);
			case OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__MAINTENANCE_FILE:
				return MAINTENANCE_FILE_EDEFAULT == null ? maintenanceFile != null : !MAINTENANCE_FILE_EDEFAULT.equals(maintenanceFile);
			case OntologyMgrPackage.LOCAL_ONTOLOGY_ENTRY__MANAGED_ONTOLOGY:
				return managedOntology != null;
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

} //LocalOntologyEntryImpl
