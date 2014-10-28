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
import org.scaledl.overview.Overview;
import org.scaledl.overview.application.OperationInterfaceContainer;
import org.scaledl.overview.converter.IOverviewConverter;
import org.scaledl.overview.converter.IOverviewConverterCallback;
import org.scaledl.overview.util.OverviewArchitectureUtil;

public class OverviewImport{

	public static final String CSM_ID_PREFIX = "CSMGen_";
	
	public static boolean removePcmFromOpInterfaceContainer(final OperationInterfaceContainer opInterfaceContainer, 
											  final IOverviewConverterCallback callback){
		
		TransactionalEditingDomain ed = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(opInterfaceContainer.eResource().getResourceSet());
		ed.getCommandStack().execute(new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				
				//clear interfaces
				OverviewArchitectureUtil.deleteInterfaces(opInterfaceContainer.getProvidedInterfaces());
				OverviewArchitectureUtil.deleteInterfaces(opInterfaceContainer.getRequiredInterfaces());
				if(callback != null){
					callback.callback();
				}
			}
		});
		
		return true;
	}
	
	public static boolean addPcmToOpInterfaceContainer(final OperationInterfaceContainer opInterfaceContainer, 
										 final List<EObject> external, 
										 final IOverviewConverterCallback callback){
		
		final Overview overviewModel = (Overview)EcoreUtil.getRootContainer(opInterfaceContainer);
		
		if (overviewModel.eResource() == null)
		{
			transform(opInterfaceContainer, external, callback);
		}
		else
		{
			    TransactionalEditingDomain ed = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(overviewModel.eResource().getResourceSet());
                ed.getCommandStack().execute(new RecordingCommand(ed) {
                        @Override
                        protected void doExecute() {
                                transform (opInterfaceContainer, external, callback);
                        }
                });
			
		}
		return true;
	}
	
	private static void transform (OperationInterfaceContainer opInterfaceContainer, List<EObject> external, IOverviewConverterCallback callback)
	{
				final Overview overviewModel = (Overview)EcoreUtil.getRootContainer(opInterfaceContainer);

                URI transformationURI = URI.createURI("platform:/plugin/eu.cloudscaleproject.env.csm2pcm/transforms/pcm2opInterfaceContainer.qvto");
                final TransformationExecutor executor = new TransformationExecutor(transformationURI);
                executor.loadTransformation();

                final ExecutionContextImpl context = new ExecutionContextImpl();

                
                context.setLog(new TransformationLogger());
                context.setConfigProperty("moduleID", opInterfaceContainer.getId());
                context.setConfigProperty("CSMID", CSM_ID_PREFIX);
                
                context.setConfigProperty("PCM_REPOSITORY_KEY", IOverviewConverter.KEY_PCM_REPOSITORY);
                context.setConfigProperty("PCM_SYSTEM_KEY", IOverviewConverter.KEY_PCM_SYSTEM);
                context.setConfigProperty("PCM_INTERFACE_KEY", IOverviewConverter.KEY_PCM_INTERFACE);

				//clear interfaces
				OverviewArchitectureUtil.deleteInterfaces(opInterfaceContainer.getProvidedInterfaces());
				OverviewArchitectureUtil.deleteInterfaces(opInterfaceContainer.getRequiredInterfaces());

				List<EObject> inputCsmList = new ArrayList<EObject>();
				inputCsmList.add(overviewModel);
				
				final ModelExtent inputCsm = new BasicModelExtent(inputCsmList);		
				final ModelExtent inputExternal = new BasicModelExtent(external);		
				
				ExecutionDiagnostic result = executor.execute(context, inputExternal, inputCsm);

				if(result.getSeverity() == Diagnostic.OK) {
					try {
						if (overviewModel.eResource() != null)
							overviewModel.eResource().save(null);
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
