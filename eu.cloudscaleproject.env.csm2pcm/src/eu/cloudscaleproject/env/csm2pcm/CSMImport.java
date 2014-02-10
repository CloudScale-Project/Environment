package eu.cloudscaleproject.env.csm2pcm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.m2m.qvt.oml.BasicModelExtent;
import org.eclipse.m2m.qvt.oml.ExecutionContextImpl;
import org.eclipse.m2m.qvt.oml.ExecutionDiagnostic;
import org.eclipse.m2m.qvt.oml.ModelExtent;
import org.eclipse.m2m.qvt.oml.TransformationExecutor;

import eu.cloudscaleproject.env.csm.Csm;
import eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer;
import eu.cloudscaleproject.env.csm.converter.ICSMConverterCallback;
import eu.cloudscaleproject.env.csm.converter.ICsmConverter;
import eu.cloudscaleproject.env.csm.util.CsmUtil;


public class CSMImport{

	public static final String CSM_ID_PREFIX = "CSMGen_";
	
	public static boolean removePcmFromOpInterfaceContainer(final OperationInterfaceContainer opInterfaceContainer, 
											  final ICSMConverterCallback callback){
		
		TransactionalEditingDomain ed = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(opInterfaceContainer.eResource().getResourceSet());
		ed.getCommandStack().execute(new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				
				//clear interfaces
				CsmUtil.deleteInterfaces(opInterfaceContainer.getProvidedInterfaces());
				CsmUtil.deleteInterfaces(opInterfaceContainer.getRequiredInterfaces());
				if(callback != null){
					callback.callback();
				}
			}
		});
		
		return true;
	}
	
	public static boolean addPcmToOpInterfaceContainer(final OperationInterfaceContainer opInterfaceContainer, 
										 final List<EObject> external, 
										 final ICSMConverterCallback callback){
		
		final Csm csmModel = (Csm)EcoreUtil.getRootContainer(opInterfaceContainer);
		
		final ExecutionContextImpl context = new ExecutionContextImpl();

		URI transformationURI = URI.createURI("platform:/plugin/eu.cloudscaleproject.env.csm2pcm/transforms/pcm2opInterfaceContainer.qvto");
		final TransformationExecutor executor = new TransformationExecutor(transformationURI);
		executor.loadTransformation();
		
		context.setLog(new TransformationLogger());
		context.setConfigProperty("moduleID", opInterfaceContainer.getId());
		context.setConfigProperty("CSMID", CSM_ID_PREFIX);
		
		context.setConfigProperty("PCM_REPOSITORY_KEY", ICsmConverter.KEY_PCM_REPOSITORY);
		context.setConfigProperty("PCM_SYSTEM_KEY", ICsmConverter.KEY_PCM_SYSTEM);
		context.setConfigProperty("PCM_INTERFACE_KEY", ICsmConverter.KEY_PCM_INTERFACE);
		
		TransactionalEditingDomain ed = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(csmModel.eResource().getResourceSet());
		ed.getCommandStack().execute(new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				
				//clear interfaces
				CsmUtil.deleteInterfaces(opInterfaceContainer.getProvidedInterfaces());
				CsmUtil.deleteInterfaces(opInterfaceContainer.getRequiredInterfaces());

				List<EObject> inputCsmList = new ArrayList<EObject>();
				inputCsmList.add(csmModel);
				
				final ModelExtent inputCsm = new BasicModelExtent(inputCsmList);		
				final ModelExtent inputExternal = new BasicModelExtent(external);		
				
				ExecutionDiagnostic result = executor.execute(context, inputExternal, inputCsm);
				
				if(result.getSeverity() == Diagnostic.OK) {
					try {
						csmModel.eResource().save(null);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(callback != null){
						callback.callback();
					}
				}
				else{
					// turn the result diagnostic into status and send it to error log			
					IStatus status = BasicDiagnostic.toIStatus(result);
					Activator.getDefault().getLog().log(status);
				}
			}
		});
		return true;
	}
	
	/*
	private static void addInterfaces(final OperationInterfaceContainer opInterfaceContainer, final List<EObject> external){
		for(EObject eo : external){
			if(eo instanceof de.uka.ipd.sdq.pcm.system.System){
				de.uka.ipd.sdq.pcm.system.System sys = (de.uka.ipd.sdq.pcm.system.System)eo;
				
				//clear interfaces
				CsmUtil.deleteInterfaces(opInterfaceContainer.getProvidedInterfaces());
				opInterfaceContainer.getRequiredInterfaces().clear();
				
				for(ProvidedRole pr : sys.getProvidedRoles_InterfaceProvidingEntity()){
					if(pr instanceof OperationProvidedRole){
						OperationProvidedRole opr = (OperationProvidedRole)pr;
						
						eu.cloudscaleproject.env.csm.core.OperationInterface oi = CoreFactoryImpl.eINSTANCE.createOperationInterface();
						oi.setName(opr.getEntityName());
						oi.setProvidingContainer(opInterfaceContainer);
						oi.getAeMap().put(ICsmConverter.KEY_PCM_INTERFACE, opr.getProvidedInterface__OperationProvidedRole());
						
						opInterfaceContainer.getProvidedInterfaces().add(oi);
					}
				}
				for(RequiredRole rr : sys.getRequiredRoles_InterfaceRequiringEntity()){
					if(rr instanceof OperationRequiredRole){
						OperationRequiredRole orr = (OperationRequiredRole)rr;
						
						eu.cloudscaleproject.env.csm.core.OperationInterface oi = CoreFactoryImpl.eINSTANCE.createOperationInterface();
						oi.setName(orr.getEntityName());
						oi.setProvidingContainer(opInterfaceContainer);
						oi.getAeMap().put(ICsmConverter.KEY_PCM_INTERFACE, orr.getRequiredInterface__OperationRequiredRole());
						
						CsmUtil.getCloudEnvironment(opInterfaceContainer).getArchitecture().getUnresolvedOperationInterfaces().add(oi);
						opInterfaceContainer.getRequiredInterfaces().add(oi);
					}
				}
			}
		}
	}
	*/
}
