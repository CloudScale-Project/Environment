/**
 */
package eu.cloudscaleproject.env.csm.architecture;

import org.eclipse.emf.common.util.EList;

import eu.cloudscaleproject.env.csm.core.Entity;
import eu.cloudscaleproject.env.csm.core.OperationInterface;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Architecture</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The Architecture view provides a descriptive abstraction of the system’s architecture, without any additional performance or cost information. The root entity of the system architecture is the cloud provider (see CloudProvider), defining an abstract cloud environment that consists of components (see Component), defining provided services, and connections (see Connection) between them, defining requested intererfaces.
 * Additionally, for the purpose of connectivity with the external environment, connectors (see Connector) are introduced into the model that can function as an operation provider or as an operation requester.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.Architecture#getCloudProviders <em>Cloud Providers</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.Architecture#getProxies <em>Proxies</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.Architecture#getExternalConnections <em>External Connections</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.Architecture#getHybridConnections <em>Hybrid Connections</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.Architecture#getUnresolvedOperationInterfaces <em>Unresolved Operation Interfaces</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getArchitecture()
 * @model
 * @generated
 */
public interface Architecture extends Entity {
	/**
	 * Returns the value of the '<em><b>Cloud Providers</b></em>' containment reference list.
	 * The list contents are of type {@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment}.
	 * It is bidirectional and its opposite is '{@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getArchitecture <em>Architecture</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cloud Providers</em>' containment reference list.
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getArchitecture_CloudProviders()
	 * @see eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getArchitecture
	 * @model opposite="architecture" containment="true"
	 * @generated
	 */
	EList<CloudEnvironment> getCloudProviders();

	/**
	 * Returns the value of the '<em><b>Proxies</b></em>' containment reference
	 * list. The list contents are of type
	 * {@link eu.cloudscaleproject.env.csm.architecture.Proxy}. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Proxies</em>' containment reference list.
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getArchitecture_Proxies()
	 * @model containment="true"
	 * @generated
	 */
	EList<Proxy> getProxies();

	/**
	 * Returns the value of the '<em><b>External Connections</b></em>'
	 * containment reference list. The list contents are of type
	 * {@link eu.cloudscaleproject.env.csm.architecture.ExternalConnection}.
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>External Connections</em>' containment
	 *         reference list.
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getArchitecture_ExternalConnections()
	 * @model containment="true"
	 * @generated
	 */
	EList<ExternalConnection> getExternalConnections();

	/**
	 * Returns the value of the '<em><b>Hybrid Connections</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link eu.cloudscaleproject.env.csm.architecture.HybridConnection}. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Hybrid Connections</em>' containment
	 *         reference list.
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getArchitecture_HybridConnections()
	 * @model containment="true"
	 * @generated
	 */
	EList<HybridConnection> getHybridConnections();

	/**
	 * Returns the value of the '<em><b>Unresolved Operation Interfaces</b></em>
	 * ' containment reference list. The list contents are of type
	 * {@link eu.cloudscaleproject.env.csm.core.OperationInterface}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unresolved Operation Interfaces</em>'
	 * containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Unresolved Operation Interfaces</em>'
	 *         containment reference list.
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getArchitecture_UnresolvedOperationInterfaces()
	 * @model containment="true"
	 * @generated
	 */
	EList<OperationInterface> getUnresolvedOperationInterfaces();

} // Architecture
