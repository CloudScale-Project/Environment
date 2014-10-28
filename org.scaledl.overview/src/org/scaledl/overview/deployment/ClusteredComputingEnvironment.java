/**
 */
package org.scaledl.overview.deployment;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Clustered Computing Environment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.deployment.ClusteredComputingEnvironment#getLoadBalancer <em>Load Balancer</em>}</li>
 *   <li>{@link org.scaledl.overview.deployment.ClusteredComputingEnvironment#getSize <em>Size</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.deployment.DeploymentPackage#getClusteredComputingEnvironment()
 * @model
 * @generated
 */
public interface ClusteredComputingEnvironment extends ComputingEnvironment {
	/**
	 * Returns the value of the '<em><b>Load Balancer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Load Balancer</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Load Balancer</em>' containment reference.
	 * @see #setLoadBalancer(LoadBalancer)
	 * @see org.scaledl.overview.deployment.DeploymentPackage#getClusteredComputingEnvironment_LoadBalancer()
	 * @model containment="true" required="true"
	 * @generated
	 */
	LoadBalancer getLoadBalancer();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.deployment.ClusteredComputingEnvironment#getLoadBalancer <em>Load Balancer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Load Balancer</em>' containment reference.
	 * @see #getLoadBalancer()
	 * @generated
	 */
	void setLoadBalancer(LoadBalancer value);

	/**
	 * Returns the value of the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Size</em>' attribute.
	 * @see #setSize(int)
	 * @see org.scaledl.overview.deployment.DeploymentPackage#getClusteredComputingEnvironment_Size()
	 * @model
	 * @generated
	 */
	int getSize();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.deployment.ClusteredComputingEnvironment#getSize <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size</em>' attribute.
	 * @see #getSize()
	 * @generated
	 */
	void setSize(int value);

} // ClusteredComputingEnvironment
