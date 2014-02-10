/**
 */
package eu.cloudscaleproject.env.csm.architecture;

import org.eclipse.emf.common.util.EList;

import eu.cloudscaleproject.env.csm.core.Entity;
import eu.cloudscaleproject.env.csm.definition.AvailabilityZoneDescriptor;
import eu.cloudscaleproject.env.csm.definition.CloudEnvironmentDescriptor;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Cloud Environment</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The CloudProvider model represents abstract cloud environment. It consists of components, the deployment information of node components (for IaaS clouds) and internal connections between the components (see InternalConnection). 
 * The nature of the cloud (IaaS and PaaS) are specified through the cloud provider descriptor (see CloudProviderDescriptor).
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getInternalConnections <em>Internal Connections</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getCloudProviderDescriptor <em>Cloud Provider Descriptor</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getAvailabilityZoneDescriptor <em>Availability Zone Descriptor</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getArchitecture <em>Architecture</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getInfrastructureLayer <em>Infrastructure Layer</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getPlatformLayer <em>Platform Layer</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getSoftwareLayer <em>Software Layer</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getCloudEnvironment()
 * @model
 * @generated
 */
public interface CloudEnvironment extends Entity {
	/**
	 * Returns the value of the '<em><b>Internal Connections</b></em>'
	 * containment reference list. The list contents are of type
	 * {@link eu.cloudscaleproject.env.csm.architecture.InternalConnection}.
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Internal Connections</em>' containment
	 *         reference list.
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getCloudEnvironment_InternalConnections()
	 * @model containment="true"
	 * @generated
	 */
	EList<InternalConnection> getInternalConnections();

	/**
	 * Returns the value of the '<em><b>Cloud Provider Descriptor</b></em>'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Cloud Provider Descriptor</em>' reference.
	 * @see #setCloudProviderDescriptor(CloudEnvironmentDescriptor)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getCloudEnvironment_CloudProviderDescriptor()
	 * @model required="true"
	 * @generated
	 */
	CloudEnvironmentDescriptor getCloudProviderDescriptor();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getCloudProviderDescriptor <em>Cloud Provider Descriptor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cloud Provider Descriptor</em>' reference.
	 * @see #getCloudProviderDescriptor()
	 * @generated
	 */
	void setCloudProviderDescriptor(CloudEnvironmentDescriptor value);

	/**
	 * Returns the value of the '<em><b>Availability Zone Descriptor</b></em>'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Availability Zone Descriptor</em>'
	 *         reference.
	 * @see #setAvailabilityZoneDescriptor(AvailabilityZoneDescriptor)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getCloudEnvironment_AvailabilityZoneDescriptor()
	 * @model
	 * @generated
	 */
	AvailabilityZoneDescriptor getAvailabilityZoneDescriptor();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getAvailabilityZoneDescriptor <em>Availability Zone Descriptor</em>}' reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Availability Zone Descriptor</em>' reference.
	 * @see #getAvailabilityZoneDescriptor()
	 * @generated
	 */
	void setAvailabilityZoneDescriptor(AvailabilityZoneDescriptor value);

	/**
	 * Returns the value of the '<em><b>Architecture</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eu.cloudscaleproject.env.csm.architecture.Architecture#getCloudProviders <em>Cloud Providers</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Architecture</em>' container reference.
	 * @see #setArchitecture(Architecture)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getCloudEnvironment_Architecture()
	 * @see eu.cloudscaleproject.env.csm.architecture.Architecture#getCloudProviders
	 * @model opposite="cloudProviders" transient="false"
	 * @generated
	 */
	Architecture getArchitecture();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getArchitecture <em>Architecture</em>}' container reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Architecture</em>' container reference.
	 * @see #getArchitecture()
	 * @generated
	 */
	void setArchitecture(Architecture value);

	/**
	 * Returns the value of the '<em><b>Infrastructure Layer</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link eu.cloudscaleproject.env.csm.architecture.InfrastructureLayer#getCloudProvider <em>Cloud Provider</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Infrastructure Layer</em>' containment reference.
	 * @see #setInfrastructureLayer(InfrastructureLayer)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getCloudEnvironment_InfrastructureLayer()
	 * @see eu.cloudscaleproject.env.csm.architecture.InfrastructureLayer#getCloudProvider
	 * @model opposite="cloudProvider" containment="true" required="true"
	 * @generated
	 */
	InfrastructureLayer getInfrastructureLayer();

	/**
	 * Sets the value of the '
	 * {@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getInfrastructureLayer
	 * <em>Infrastructure Layer</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Infrastructure Layer</em>'
	 *            containment reference.
	 * @see #getInfrastructureLayer()
	 * @generated
	 */
	void setInfrastructureLayer(InfrastructureLayer value);

	/**
	 * Returns the value of the '<em><b>Platform Layer</b></em>' containment
	 * reference. It is bidirectional and its opposite is '
	 * {@link eu.cloudscaleproject.env.csm.architecture.PlatformLayer#getCloudEnvironment
	 * <em>Cloud Environment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Platform Layer</em>' containment reference.
	 * @see #setPlatformLayer(PlatformLayer)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getCloudEnvironment_PlatformLayer()
	 * @see eu.cloudscaleproject.env.csm.architecture.PlatformLayer#getCloudEnvironment
	 * @model opposite="cloudEnvironment" containment="true" required="true"
	 * @generated
	 */
	PlatformLayer getPlatformLayer();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getPlatformLayer <em>Platform Layer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Platform Layer</em>' containment reference.
	 * @see #getPlatformLayer()
	 * @generated
	 */
	void setPlatformLayer(PlatformLayer value);

	/**
	 * Returns the value of the '<em><b>Software Layer</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link eu.cloudscaleproject.env.csm.architecture.SoftwareLayer#getCloudProvider <em>Cloud Provider</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Software Layer</em>' containment reference.
	 * @see #setSoftwareLayer(SoftwareLayer)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getCloudEnvironment_SoftwareLayer()
	 * @see eu.cloudscaleproject.env.csm.architecture.SoftwareLayer#getCloudProvider
	 * @model opposite="cloudProvider" containment="true" required="true"
	 * @generated
	 */
	SoftwareLayer getSoftwareLayer();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getSoftwareLayer <em>Software Layer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Software Layer</em>' containment reference.
	 * @see #getSoftwareLayer()
	 * @generated
	 */
	void setSoftwareLayer(SoftwareLayer value);

} // CloudEnvironment
