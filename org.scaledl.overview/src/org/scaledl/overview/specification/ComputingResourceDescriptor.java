/**
 */
package org.scaledl.overview.specification;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Computing Resource Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.specification.ComputingResourceDescriptor#isEditable <em>Editable</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.ComputingResourceDescriptor#getMemory <em>Memory</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.ComputingResourceDescriptor#getCpu <em>Cpu</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.ComputingResourceDescriptor#getCpuUnits <em>Cpu Units</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.ComputingResourceDescriptor#getStorage <em>Storage</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.specification.SpecificationPackage#getComputingResourceDescriptor()
 * @model
 * @generated
 */
public interface ComputingResourceDescriptor extends Descriptor {
	/**
	 * Returns the value of the '<em><b>Editable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Editable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Editable</em>' attribute.
	 * @see #setEditable(boolean)
	 * @see org.scaledl.overview.specification.SpecificationPackage#getComputingResourceDescriptor_Editable()
	 * @model
	 * @generated
	 */
	boolean isEditable();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.specification.ComputingResourceDescriptor#isEditable <em>Editable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Editable</em>' attribute.
	 * @see #isEditable()
	 * @generated
	 */
	void setEditable(boolean value);

	/**
	 * Returns the value of the '<em><b>Memory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Memory</em>' attribute.
	 * @see #setMemory(int)
	 * @see org.scaledl.overview.specification.SpecificationPackage#getComputingResourceDescriptor_Memory()
	 * @model
	 * @generated
	 */
	int getMemory();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.specification.ComputingResourceDescriptor#getMemory <em>Memory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Memory</em>' attribute.
	 * @see #getMemory()
	 * @generated
	 */
	void setMemory(int value);

	/**
	 * Returns the value of the '<em><b>Cpu</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cpu</em>' attribute.
	 * @see #setCpu(int)
	 * @see org.scaledl.overview.specification.SpecificationPackage#getComputingResourceDescriptor_Cpu()
	 * @model
	 * @generated
	 */
	int getCpu();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.specification.ComputingResourceDescriptor#getCpu <em>Cpu</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cpu</em>' attribute.
	 * @see #getCpu()
	 * @generated
	 */
	void setCpu(int value);

	/**
	 * Returns the value of the '<em><b>Cpu Units</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cpu Units</em>' attribute.
	 * @see #setCpuUnits(int)
	 * @see org.scaledl.overview.specification.SpecificationPackage#getComputingResourceDescriptor_CpuUnits()
	 * @model
	 * @generated
	 */
	int getCpuUnits();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.specification.ComputingResourceDescriptor#getCpuUnits <em>Cpu Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cpu Units</em>' attribute.
	 * @see #getCpuUnits()
	 * @generated
	 */
	void setCpuUnits(int value);

	/**
	 * Returns the value of the '<em><b>Storage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Storage</em>' attribute.
	 * @see #setStorage(int)
	 * @see org.scaledl.overview.specification.SpecificationPackage#getComputingResourceDescriptor_Storage()
	 * @model
	 * @generated
	 */
	int getStorage();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.specification.ComputingResourceDescriptor#getStorage <em>Storage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Storage</em>' attribute.
	 * @see #getStorage()
	 * @generated
	 */
	void setStorage(int value);

} // ComputingResourceDescriptor
