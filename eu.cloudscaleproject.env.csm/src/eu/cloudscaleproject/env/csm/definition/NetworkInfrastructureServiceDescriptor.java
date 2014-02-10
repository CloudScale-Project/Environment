/**
 */
package eu.cloudscaleproject.env.csm.definition;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Network Infrastructure Service Descriptor</b></em>'. <!-- end-user-doc
 * -->
 * 
 * <!-- begin-model-doc --> The ConnectionDescriptor specifies properties of the
 * connections. In the current version following properties are defined:
 * bandwidth and latency. <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor#getBandwidth
 * <em>Bandwidth</em>}</li>
 * <li>
 * {@link eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor#getLatency
 * <em>Latency</em>}</li>
 * </ul>
 * </p>
 * 
 * @see eu.cloudscaleproject.env.csm.definition.DefinitionPackage#getNetworkInfrastructureServiceDescriptor()
 * @model
 * @generated
 */
public interface NetworkInfrastructureServiceDescriptor extends
		InfrastructureServiceDescriptor {
	/**
	 * Returns the value of the '<em><b>Bandwidth</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Bandwidth</em>' attribute.
	 * @see #setBandwidth(int)
	 * @see eu.cloudscaleproject.env.csm.definition.DefinitionPackage#getNetworkInfrastructureServiceDescriptor_Bandwidth()
	 * @model
	 * @generated
	 */
	int getBandwidth();

	/**
	 * Sets the value of the '
	 * {@link eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor#getBandwidth
	 * <em>Bandwidth</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Bandwidth</em>' attribute.
	 * @see #getBandwidth()
	 * @generated
	 */
	void setBandwidth(int value);

	/**
	 * Returns the value of the '<em><b>Latency</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Latency</em>' attribute.
	 * @see #setLatency(int)
	 * @see eu.cloudscaleproject.env.csm.definition.DefinitionPackage#getNetworkInfrastructureServiceDescriptor_Latency()
	 * @model
	 * @generated
	 */
	int getLatency();

	/**
	 * Sets the value of the '
	 * {@link eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor#getLatency
	 * <em>Latency</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Latency</em>' attribute.
	 * @see #getLatency()
	 * @generated
	 */
	void setLatency(int value);

} // NetworkInfrastructureServiceDescriptor
