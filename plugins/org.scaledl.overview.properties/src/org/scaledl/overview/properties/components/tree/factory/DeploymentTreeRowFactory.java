package org.scaledl.overview.properties.components.tree.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.TreeViewer;
import org.scaledl.overview.deployment.ClusteredComputingEnvironment;
import org.scaledl.overview.deployment.ComputingEnvironment;
import org.scaledl.overview.deployment.DeploymentFactory;
import org.scaledl.overview.deployment.DeploymentPackage;
import org.scaledl.overview.deployment.GenericDeployment;
import org.scaledl.overview.deployment.LoadBalancer;
import org.scaledl.overview.deployment.RuntimeDeployment;
import org.scaledl.overview.deployment.ScalableComputingEnvironment;
import org.scaledl.overview.deployment.ScalingCapacityType;
import org.scaledl.overview.deployment.ScalingManager;
import org.scaledl.overview.deployment.ScalingPolicy;
import org.scaledl.overview.deployment.ScalingPolicyType;
import org.scaledl.overview.deployment.SchedulingPolicy;
import org.scaledl.overview.deployment.ServiceDeployment;
import org.scaledl.overview.properties.ImageResource;
import org.scaledl.overview.properties.components.editors.EditorScalingManager;
import org.scaledl.overview.properties.components.tree.TreeCell;
import org.scaledl.overview.properties.components.tree.TreeRow;
import org.scaledl.overview.properties.components.tree.editors.CellEditorDropdown;
import org.scaledl.overview.properties.components.tree.editors.CellEditorFloat;
import org.scaledl.overview.properties.components.tree.editors.CellEditorInt;
import org.scaledl.overview.properties.components.tree.editors.CellEditorString;
import org.scaledl.overview.specification.ComputingInfrastructureServiceDescriptor;
import org.scaledl.overview.specification.ComputingResourceDescriptor;

public class DeploymentTreeRowFactory extends SpecificationTreeRowFactory{

	public DeploymentTreeRowFactory(TreeViewer treeViewer) {
		super(treeViewer);
	}
	
	public TreeRow createLoadBalancer(String name, LoadBalancer loadBalancer, TreeRow parent){
		TreeCell child10 = new TreeCell("Load balancer");
		TreeCell child11 = new TreeCell(loadBalancer);
		
		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{child10, child11}, parent){
			
			public boolean hasChildren() {
				return true;
			};
			
			protected TreeRow[] doGetChildren() {
				final LoadBalancer lb = 
						(LoadBalancer)this.getCell(1).getData();
				
				TreeCell child0 = new TreeCell("Scheduling policy");
				TreeCell child1 = new TreeCell(lb.getSchedulingPolicy());
				
				child1.bind(lb, DeploymentPackage.Literals.LOAD_BALANCER__SCHEDULING_POLICY);
				child1.setEditor(new CellEditorDropdown(treeComposite, child1, SchedulingPolicy.VALUES));
				
				TreeRow row = new TreeRow(treeViewer, new TreeCell[]{child0, child1}, this);
				return new TreeRow[]{row};
			};
		};
		
		return treeRow;
	}
	
	public TreeRow createScalingPolicy(String name, ScalingPolicy scalingPolicy, TreeRow parent){
		TreeCell cell0 = new TreeCell(name);
		TreeCell cell1 = new TreeCell(scalingPolicy);
		
		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{cell0, cell1}, parent){
			
			public boolean hasChildren(){
				return true;
			}
			
			@Override
			protected TreeRow[] doGetChildren() {
				
				ScalingPolicy sp = (ScalingPolicy)getCell(1).getData();
				
				List<TreeRow> out = new ArrayList<TreeRow>();

				{
					TreeCell child0 = new TreeCell("Scaling policy type");
					TreeCell child1 = new TreeCell(sp.getScalingPolicyType());
					child1.bind(sp, DeploymentPackage.Literals.SCALING_POLICY__SCALING_POLICY_TYPE);
					child1.setEditor(new CellEditorDropdown(treeComposite, child1, ScalingPolicyType.VALUES));
					out.add(new TreeRow(treeViewer, new TreeCell[]{child0, child1}, this));
				}

				{
					TreeCell child0 = new TreeCell("Scaling policy capacity");
					TreeCell child1 = new TreeCell(sp.getScalingCapacityType());
					child1.bind(sp, DeploymentPackage.Literals.SCALING_POLICY__SCALING_CAPACITY_TYPE);
					child1.setEditor(new CellEditorDropdown(treeComposite, child1, ScalingCapacityType.VALUES));
					out.add(new TreeRow(treeViewer, new TreeCell[]{child0, child1}, this));
				}

				{
					TreeCell child0 = new TreeCell("Adjustment");
					TreeCell child1 = new TreeCell(sp.getAdjustment());
					child1.bind(sp, DeploymentPackage.Literals.SCALING_POLICY__ADJUSTMENT);
					child1.setEditor(new CellEditorFloat(treeComposite, sp));
					out.add(new TreeRow(treeViewer, new TreeCell[]{child0, child1}, this));
				}
				
				{
					TreeCell child0 = new TreeCell("Cooldown");
					TreeCell child1 = new TreeCell(sp.getCooldown());
					child1.bind(sp, DeploymentPackage.Literals.SCALING_POLICY__COOLDOWN);
					child1.setEditor(new CellEditorInt(treeComposite, sp));
					out.add(new TreeRow(treeViewer, new TreeCell[]{child0, child1}, this));
				}
				
				{
					TreeCell child0 = new TreeCell("Trigger");
					TreeCell child1 = new TreeCell(sp.getTrigger());
					child1.bind(sp, DeploymentPackage.Literals.SCALING_POLICY__TRIGGER);
					child1.setEditor(new CellEditorString(treeComposite, sp));
					out.add(new TreeRow(treeViewer, new TreeCell[]{child0, child1}, this));
				}
				
				return out.toArray(new TreeRow[out.size()]);
			}
		};
		
		return treeRow;
	}
	
	public TreeRow createScalingPolicyList(String name, List<? extends ScalingPolicy> spList, TreeRow parent){
		TreeCell cell0 = new TreeCell(name);
		TreeCell cell1 = new TreeCell(spList);
		cell1.setShowText(false);
		
		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{cell0, cell1}, parent){
			
			public boolean hasChildren(){
				return true;
			}
			
			@Override
			protected TreeRow[] doGetChildren() {
				
				List<TreeRow> out = new ArrayList<TreeRow>();

				@SuppressWarnings("unchecked")
				List<? extends ScalingPolicy> spList = (List<? extends ScalingPolicy>)getCell(1).getData();
				//add scaling policy
				for(ScalingPolicy sp : spList){
					out.add(createScalingPolicy("Scaling policy", sp, this));
				}
				
				return out.toArray(new TreeRow[out.size()]);
			}
		};
		
		return treeRow;
	}
	
	public TreeRow createScalingManager(String name, ScalingManager scalingManager, TreeRow parent){
		TreeCell cell0 = new TreeCell(name);
		TreeCell cell1 = new TreeCell(scalingManager);
		
		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{cell0, cell1}, parent){
			
			public boolean hasChildren(){
				return true;
			}
			
			@Override
			protected TreeRow[] doGetChildren() {
				
				ScalingManager sm = (ScalingManager)getCell(1).getData();
				
				List<TreeRow> out = new ArrayList<TreeRow>();
				
				{
					TreeCell child0 = new TreeCell("Min. instances");
					TreeCell child1 = new TreeCell(sm.getMinimumSize());
					child1.bind(sm, DeploymentPackage.Literals.SCALING_MANAGER__MINIMUM_SIZE);
					child1.setEditor(new CellEditorInt(treeComposite, sm));
					out.add(new TreeRow(treeViewer, new TreeCell[]{child0, child1}, this));
				}

				{
					TreeCell child0 = new TreeCell("Max. instances");
					TreeCell child1 = new TreeCell(sm.getMaximumSize());
					child1.bind(sm, DeploymentPackage.Literals.SCALING_MANAGER__MAXIMUM_SIZE);
					child1.setEditor(new CellEditorInt(treeComposite, sm));
					out.add(new TreeRow(treeViewer, new TreeCell[]{child0, child1}, this));
				}
				
				{
					TreeRow scalingPolicies = createScalingPolicyList("Scaling policies:", sm.getScalingPolicy(), this);
					scalingPolicies.setChildListEditor(new EditorScalingManager(treeComposite, sm));
					scalingPolicies.getCell(1).bind(sm, DeploymentPackage.Literals.SCALING_MANAGER__SCALING_POLICY);
					out.add(scalingPolicies);
				}
				
				return out.toArray(new TreeRow[out.size()]);
			}
		};
						
		return treeRow;
	}
	
	public TreeRow createComputingEnvironment(String name, ComputingEnvironment ce, TreeRow parent){
		TreeCell cell0 = new TreeCell(name, ImageResource.INFRASTRUCTURE_SERVICE);
		TreeCell cell1 = new TreeCell(ce);
    	
    	TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{cell0, cell1}, parent){
			
			public boolean hasChildren(){
				return true;
			}
			
			@Override
			protected TreeRow[] doGetChildren() {
				
				List<TreeRow> out = new ArrayList<TreeRow>();

				final ComputingEnvironment compEnv = 
						(ComputingEnvironment)this.getCell(1).getData();

				if(compEnv == null){
					return null;
				}

				out.addAll(createEntity(compEnv, this));

				{
					//computing instance chooser
					TreeCell child0 = new TreeCell("Instance type");
					TreeCell child1 = new TreeCell(compEnv.getInstanceDescriptor());
					
					ComputingResourceDescriptor crd = compEnv.getInstanceDescriptor();
					
					/*
					for(EObject eObject : crd.eCrossReferences()){
						if(eObject instanceof ComputingInfrastructureService){
							cis = (ComputingInfrastructureService)eObject;
						}
					}
					*/
					
					if(crd != null && crd.eContainer() instanceof ComputingInfrastructureServiceDescriptor){
						child1.setEditor(new CellEditorDropdown(treeComposite, child1, 
								((ComputingInfrastructureServiceDescriptor)crd.eContainer()).getComputingResourceDescriptors()));
						child1.bind(compEnv, DeploymentPackage.Literals.COMPUTING_ENVIRONMENT__INSTANCE_DESCRIPTOR);
					}
					
					out.add(new TreeRow(treeViewer, new TreeCell[]{child0, child1}, this));
					
				}
				
				if(compEnv instanceof ClusteredComputingEnvironment){
					ClusteredComputingEnvironment cce = (ClusteredComputingEnvironment)compEnv;
					
					TreeCell child00 = new TreeCell("Instances count");
					TreeCell child01 = new TreeCell(cce.getSize());
					
					child01.bind(cce, DeploymentPackage.Literals.CLUSTERED_COMPUTING_ENVIRONMENT__SIZE);
					child01.setEditor(new CellEditorInt(treeComposite, cce));
					
					out.add(new TreeRow(treeViewer, new TreeCell[]{child00, child01}, this));
					out.add(createLoadBalancer("Load balancer", cce.getLoadBalancer(), this));
			
				}

				if(compEnv instanceof ScalableComputingEnvironment){
					ScalableComputingEnvironment sce = (ScalableComputingEnvironment)compEnv;
					out.add(createScalingManager("Scaling manager", sce.getScalingManager(), this));
				}
				
				return out.toArray(new TreeRow[out.size()]);
			}
    	};
    	
    	return treeRow;
	}
	
	public TreeRow createServiceDeployment(String name, ServiceDeployment sd, TreeRow parent){
		TreeCell cell0 = new TreeCell(name, ImageResource.INFRASTRUCTURE_SERVICE);
		TreeCell cell1 = new TreeCell(sd);
    	
    	TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{cell0, cell1}, parent){
			
			public boolean hasChildren(){
				return true;
			}
			
			@Override
			protected TreeRow[] doGetChildren() {
				
				ServiceDeployment sd = (ServiceDeployment)getCell(1).getData();
				
				List<TreeRow> out = new ArrayList<TreeRow>();
				
				if(sd instanceof RuntimeDeployment){
					final RuntimeDeployment rd = (RuntimeDeployment)sd;
					
					final TreeRow comEnvRow = createComputingEnvironment("Computing environment", rd.getComputingEnvironment(), this);
					TreeCell cell = comEnvRow.getCell(1);
					
					if(cell != null && cell.getData() instanceof ComputingEnvironment){
						HashMap<Class<?>, ComputingEnvironment> compEnvList = new LinkedHashMap<Class<?>, ComputingEnvironment>();
						
						//current computing environment
						ComputingEnvironment ce = (ComputingEnvironment) cell.getData();
						
						//drop down menu optional computing environments derived from current
						ComputingEnvironment c1 = DeploymentFactory.eINSTANCE.createComputingEnvironment();
						c1.setName("Single instance computing environment");
						c1.setInstanceDescriptor(ce.getInstanceDescriptor());
						
						ClusteredComputingEnvironment c2 = DeploymentFactory.eINSTANCE.createClusteredComputingEnvironment();
						c2.setName("Clustered computing environment");
						c2.setInstanceDescriptor(ce.getInstanceDescriptor());
						c2.setLoadBalancer(DeploymentFactory.eINSTANCE.createLoadBalancer());
						
						ScalableComputingEnvironment c3 = DeploymentFactory.eINSTANCE.createScalableComputingEnvironment();
						c3.setName("Scaleable computing environment");
						c3.setInstanceDescriptor(ce.getInstanceDescriptor());
						c3.setLoadBalancer(DeploymentFactory.eINSTANCE.createLoadBalancer());
						c3.setScalingManager(DeploymentFactory.eINSTANCE.createScalingManager());
						
						compEnvList.put(c1.getClass(), c1);
						compEnvList.put(c2.getClass(), c2);
						compEnvList.put(c3.getClass(), c3);
						
						compEnvList.put(ce.getClass(), ce);
						
						//TODO: find out why is this workaround necessary
						final CellEditorDropdown ced = new CellEditorDropdown(treeComposite, cell, 
								new ArrayList<ComputingEnvironment>(compEnvList.values()));
						ced.addListener(new ICellEditorListener() {
							
							@Override
							public void editorValueChanged(boolean oldValidState, boolean newValidState) {
								
							}
							
							@Override
							public void cancelEditor() {
								
							}
							
							@Override
							public void applyEditorValue() {
								TransactionalEditingDomain ed = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(
						        		rd.eResource().getResourceSet());
								
								ed.getCommandStack().execute(new RecordingCommand(ed) {
									
									@Override
									protected void doExecute() {
										rd.setComputingEnvironment((ComputingEnvironment)ced.getValue());
										comEnvRow.reload();
									}
								});
							}
						});
						cell.setEditor(ced);
						
					}
					
					cell.bind(rd, DeploymentPackage.Literals.RUNTIME_DEPLOYMENT__COMPUTING_ENVIRONMENT);
					
					out.add(comEnvRow);
				}
				else if(sd instanceof GenericDeployment){
					//GenericDeployment gd = (GenericDeployment)sd;
					//TODO: implement
				}
				
				return out.toArray(new TreeRow[out.size()]);
			}
    	};
    	
    	return treeRow;
	}
	
	/*
	public TreeRow createComputingEntity(String name, ComputingEntity computingEntity, TreeRow parent){
		if(computingEntity instanceof ComputingGroup){
			return createComputingGroup(name, (ComputingGroup)computingEntity, parent);
		}
		else if(computingEntity instanceof GenericComputingEntity){
			return createGenericComputingEntity(name, (GenericComputingEntity)computingEntity, parent);
		}
		else if(computingEntity instanceof Instance){
			return createInstance(name, (Instance)computingEntity, parent);
		}
		
		return null;
	}
	
	public TreeRow createGenericComputingEntity(String name, GenericComputingEntity ece, TreeRow parent){
		TreeCell cell0 = new TreeCell(name, ImageResource.INFRASTRUCTURE_SERVICE);
		TreeCell cell1 = new TreeCell(ece);
		
		if(ece.getDescriptor().isEditable()){
			cell1.setEditor(new CellEditorString(treeComposite, ece.eContainer()));
		}
    	
    	TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{cell0, cell1}, parent){
			
			public boolean hasChildren(){
				return true;
			}
			
			@Override
			protected TreeRow[] doGetChildren() {
				GenericComputingEntity ece = (GenericComputingEntity)getCell(1).getData();
				
				List<TreeRow> out = new ArrayList<TreeRow>();
				
				for(Measurement m : ece.getDescriptor().getAverageMeasuredValues()){
					TreeCell cellMetric = new TreeCell(m.getType());
					TreeCell cellValue = new TreeCell(m.getValue());
					
					if(ece.getDescriptor().isEditable()){
						cellValue.setEditor(new CellEditorInt(treeComposite, ece.eContainer()));
					}
					
					TreeRow mRow = new TreeRow(treeViewer, new TreeCell[]{cellMetric, cellValue}, this);
					out.add(mRow);
				}
				
				return (TreeRow[])out.toArray(new TreeRow[out.size()]);
			}
    	};
    	
    	return treeRow;
	}
	
	public TreeRow createCloudInfrastructure(String name, CloudInfrastructure cloudInfrastructure, TreeRow parent){
		TreeCell cell0 = new TreeCell(name, ImageResource.LAYER);
		TreeCell cell1 = new TreeCell(cloudInfrastructure);
		cell1.setShowText(false);
	
		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{cell0, cell1}, parent){

			public boolean hasChildren() {
				return true;
			};

			public TreeRow[] doGetChildren() {
				
				CloudInfrastructure ci = (CloudInfrastructure)getCell(1).getData();
				
				List<TreeRow> out = new ArrayList<TreeRow>();
				
				for(ComputingGroup cg : ci.getComputingGroups()){
					out.add(createComputingGroup(cg.getName(), cg, this));
				}
				
				for(GenericComputingEntity ece : ci.getGenericComputingEntities()){
					out.add(createGenericComputingEntity(ece.getName(), ece, this));
				}
				
				return (TreeRow[])out.toArray(new TreeRow[out.size()]);
			};
		};
		
		return treeRow;
	}
	*/
	
}
