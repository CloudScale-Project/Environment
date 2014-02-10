/**
 */
package eu.cloudscaleproject.env.csm.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import eu.cloudscaleproject.env.csm.CsmPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * @generated
 */
public class CsmXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CsmXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		CsmPackage.eINSTANCE.eClass();
	}

	/**
	 * Register for "*" and "xml" file extensions the CsmResourceFactoryImpl factory.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new CsmResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new CsmResourceFactoryImpl());
		}
		return registrations;
	}

} // CsmXMLProcessor
