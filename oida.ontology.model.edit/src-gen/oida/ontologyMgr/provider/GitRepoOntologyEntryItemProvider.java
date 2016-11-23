/**
 */
package oida.ontologyMgr.provider;


import java.util.Collection;
import java.util.List;

import oida.ontologyMgr.GitRepoOntologyEntry;
import oida.ontologyMgr.OntologyMgrPackage;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link oida.ontologyMgr.GitRepoOntologyEntry} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GitRepoOntologyEntryItemProvider extends LocalOntologyEntryItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GitRepoOntologyEntryItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addRepoURLPropertyDescriptor(object);
			addGitUsernamePropertyDescriptor(object);
			addGitPasswordPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Repo URL feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRepoURLPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_GitRepoOntologyEntry_repoURL_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_GitRepoOntologyEntry_repoURL_feature", "_UI_GitRepoOntologyEntry_type"),
				 OntologyMgrPackage.Literals.GIT_REPO_ONTOLOGY_ENTRY__REPO_URL,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Git Username feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addGitUsernamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_GitRepoOntologyEntry_gitUsername_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_GitRepoOntologyEntry_gitUsername_feature", "_UI_GitRepoOntologyEntry_type"),
				 OntologyMgrPackage.Literals.GIT_REPO_ONTOLOGY_ENTRY__GIT_USERNAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Git Password feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addGitPasswordPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_GitRepoOntologyEntry_gitPassword_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_GitRepoOntologyEntry_gitPassword_feature", "_UI_GitRepoOntologyEntry_type"),
				 OntologyMgrPackage.Literals.GIT_REPO_ONTOLOGY_ENTRY__GIT_PASSWORD,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns GitRepoOntologyEntry.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/GitRepoOntologyEntry"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((GitRepoOntologyEntry)object).getGitUsername();
		return label == null || label.length() == 0 ?
			getString("_UI_GitRepoOntologyEntry_type") :
			getString("_UI_GitRepoOntologyEntry_type") + " " + label;
	}
	

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(GitRepoOntologyEntry.class)) {
			case OntologyMgrPackage.GIT_REPO_ONTOLOGY_ENTRY__REPO_URL:
			case OntologyMgrPackage.GIT_REPO_ONTOLOGY_ENTRY__GIT_USERNAME:
			case OntologyMgrPackage.GIT_REPO_ONTOLOGY_ENTRY__GIT_PASSWORD:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

}
