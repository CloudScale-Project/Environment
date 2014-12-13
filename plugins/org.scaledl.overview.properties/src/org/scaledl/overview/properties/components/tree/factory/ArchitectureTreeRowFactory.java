package org.scaledl.overview.properties.components.tree.factory;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TreeViewer;
import org.scaledl.overview.application.ApplicationPackage;
import org.scaledl.overview.application.OperationInterfaceContainer;
import org.scaledl.overview.architecture.ArchitecturePackage;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.architecture.ComputingInfrastructureService;
import org.scaledl.overview.architecture.InfrastructureLayer;
import org.scaledl.overview.architecture.InfrastructureService;
import org.scaledl.overview.architecture.PlatformService;
import org.scaledl.overview.architecture.ProvidedService;
import org.scaledl.overview.architecture.SoftwareService;
import org.scaledl.overview.architecture.SoftwareServiceContainer;
import org.scaledl.overview.deployment.ServiceDeployment;
import org.scaledl.overview.properties.ImageResource;
import org.scaledl.overview.properties.components.editors.EditorInfrastructureLayer;
import org.scaledl.overview.properties.components.editors.EditorProvidedInterfaces;
import org.scaledl.overview.properties.components.editors.EditorRequiredInterfaces;
import org.scaledl.overview.properties.components.editors.EditorSoftwareServices;
import org.scaledl.overview.properties.components.editors.EditorUnresolvedInterfaces;
import org.scaledl.overview.properties.components.tree.TreeCell;
import org.scaledl.overview.properties.components.tree.TreeRow;
import org.scaledl.overview.properties.components.tree.editors.CellEditorList;
import org.scaledl.overview.properties.components.tree.editors.CellEditorString;

public class ArchitectureTreeRowFactory extends DeploymentTreeRowFactory{

	public ArchitectureTreeRowFactory(TreeViewer treeViewer) {
		super(treeViewer);
	}
	
	public TreeRow createCloudEnvironment(String name, CloudEnvironment ce, TreeRow parent) {

		TreeCell cell0 = new TreeCell(name);
		TreeCell cell1 = new TreeCell(ce);
    	cell1.setEditor(new CellEditorString(treeComposite, ce.eContainer()));

		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[] { cell0, cell1 },
				parent) {

			public boolean hasChildren() {
				return true;
			}

			@Override
			protected TreeRow[] doGetChildren() {
				CloudEnvironment ce = (CloudEnvironment) getCell(
						1).getData();

				if (ce == null) {
					return null;
				}

				List<TreeRow> children = new ArrayList<TreeRow>();
				
				TreeRow unresolved = createOperationInterfaceList("Unresolved operation interfaces", 
						ce.getArchitecture().getUnresolvedOperationInterfaces(), this);
				unresolved.getCell(1).bind(ce.getArchitecture(), ArchitecturePackage.Literals.ARCHITECTURE__UNRESOLVED_OPERATION_INTERFACES);
				unresolved.getCell(1).setEditor(CellEditorList.createUIEditor(treeComposite, unresolved.getCell(1), ce.getArchitecture()));
				unresolved.setChildListEditor(new EditorUnresolvedInterfaces(treeComposite, ce.getArchitecture()));
				children.add(unresolved);

				if (ce.getAvailabilityZoneDescriptor()!=null)
				{
					TreeRow infrastructureDescriptor = createNetworkInfrastructureDescriptor("Internal connectios", 
													ce.getAvailabilityZoneDescriptor().getNetworkInfrastructureServiceDescriptor(), this);
					children.add(infrastructureDescriptor);
				}
				
				TreeRow infServices = createInfrastructureServiceList("Infrastructure services layer", 
						ce.getInfrastructureLayer().getServices(), this);
				infServices.getCell(1).bind(ce.getInfrastructureLayer(), 
						ArchitecturePackage.Literals.INFRASTRUCTURE_LAYER__SERVICES);
				infServices.getCell(1).setEditor(CellEditorList.createISEditor(treeComposite, infServices.getCell(1), ce.getInfrastructureLayer()));
				infServices.setChildListEditor(new EditorInfrastructureLayer(treeComposite, ce.getInfrastructureLayer()));
				children.add(infServices);
				
				TreeRow platServices = createPlatformServiceList("Platform services layer", 
						ce.getPlatformLayer().getServices(), this);
				platServices.getCell(1).bind(ce.getPlatformLayer(), 
						ArchitecturePackage.Literals.PLATFORM_LAYER__SERVICES);
				children.add(platServices);
				
				TreeRow softServices = createSoftwareServiceList("Software services layer", 
						ce.getSoftwareLayer().getServices(), this);
				softServices.getCell(1).bind(ce.getSoftwareLayer(), 
						ArchitecturePackage.Literals.SOFTWARE_LAYER__SERVICES);
				children.add(softServices);
				
				return children.toArray(new TreeRow[children.size()]);
			}
		};

		return treeRow;
	}
	
	public TreeRow createInfrastructureLayer(String name, InfrastructureLayer il, TreeRow parent) {
		
		TreeRow row = createInfrastructureServiceList(name, il.getServices(), parent);
		row.getCell(1).setEditor(CellEditorList.createISEditor(treeComposite, row.getCell(1), il));
		row.getCell(1).bind(il, ArchitecturePackage.Literals.INFRASTRUCTURE_LAYER__SERVICES);
		row.setChildListEditor(new EditorInfrastructureLayer(treeComposite, il));
		return row;
	}
	
	public TreeRow createComputingInfrastructureService(String name, ComputingInfrastructureService cis, TreeRow parent){
		
		TreeCell cell0 = new TreeCell(name, ImageResource.INFRASTRUCTURE_SERVICE);
		TreeCell cell1 = new TreeCell(cis);
    	cell1.setEditor(new CellEditorString(treeComposite, cis.eContainer()));
		
		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{cell0, cell1}, parent){
			
			public boolean hasChildren(){
				return true;
			}
			
			@Override
			protected TreeRow[] doGetChildren() {
				ComputingInfrastructureService cis = (ComputingInfrastructureService)getCell(1).getData();

				if(cis == null){
					return null;
				}
				
				List<TreeRow> children = new ArrayList<TreeRow>();
				children.addAll(createEntity(cis, this));
				
				/*
				TreeRow child2 = createComputingEntity("Computing group", cis.getComputingEntity(), this);
				TreeCell compGroupCell = child2.getCell(1);
				compGroupCell.setEditor(new CellEditorString(treeComposite, cis));
				children.add(child2);
				*/
				
				if(cis.getDeployment() != null){
					TreeRow child2 = createServiceDeployment("Deployment", cis.getDeployment(), this);
					children.add(child2);
				}
				
				TreeRow child3 = createPlatformServiceList("Deployed platform services", cis.getPlatformServices(), this);
				child3.getCell(1).bind(cis, ArchitecturePackage.Literals.COMPUTING_INFRASTRUCTURE_SERVICE__PLATFORM_SERVICES);
				child3.getCell(0).setImage(ImageResource.DEPLOYED_ON);
				children.add(child3);
				
				return children.toArray(new TreeRow[children.size()]);
			}
		};
		
		return treeRow;
	}
	
	public TreeRow createInfrastructureServiceList(String name, List<? extends InfrastructureService> infrastructureServices, TreeRow parent){
		
		TreeCell cell0 = new TreeCell(name, ImageResource.LAYER);
		TreeCell cell1 = new TreeCell(infrastructureServices);
		cell1.setShowText(false);
	
		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{cell0, cell1}, parent){

			public boolean hasChildren() {
				@SuppressWarnings("unchecked")
				List<InfrastructureService> isList = (List<InfrastructureService>)(getCell(1).getData());
				
				if(isList != null && !isList.isEmpty()){
					return true;
				}
				return false;
			};

			public TreeRow[] doGetChildren() {
				
				@SuppressWarnings("unchecked")
				List<InfrastructureService> isList = (List<InfrastructureService>)(getCell(1).getData());
				TreeRow[] child = new TreeRow[isList.size()];
				
				for(int i=0; i<isList.size(); i++){
					InfrastructureService is = isList.get(i);
					if(is instanceof ComputingInfrastructureService){
						child[i] = createComputingInfrastructureService("Infrastructure service: " + i, 
								(ComputingInfrastructureService)is, this);
					}
				}
				return child;
			};
		};
		
		return treeRow;
	}
	
	public TreeRow createPlatformServiceList(String name, List<? extends PlatformService> platformServices, TreeRow parent){
		TreeCell cell0 = new TreeCell(name, ImageResource.LAYER);
		TreeCell cell1 = new TreeCell(platformServices);
		cell1.setShowText(false);
	
		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{cell0, cell1}, parent){

			public boolean hasChildren() {
				@SuppressWarnings("unchecked")
				List<PlatformService> psList = (List<PlatformService>)(getCell(1).getData());
				
				if(psList != null && !psList.isEmpty()){
					return true;
				}
				return false;
			};

			public TreeRow[] doGetChildren() {
				
				@SuppressWarnings("unchecked")
				List<PlatformService> psList = (List<PlatformService>)(getCell(1).getData());
				TreeRow[] child = new TreeRow[psList.size()];
				
				for(int i=0; i<psList.size(); i++){
					child[i] = createPlatformService("Platform service: " + i, psList.get(i), this);
				}
				return child;
			};
		};
		
		return treeRow;
	}
	
	public TreeRow createPlatformService(String name, PlatformService ps, TreeRow parent){
		
		TreeCell cell0 = new TreeCell(name, ImageResource.PLATFORM_SERVICE);
		TreeCell cell1 = new TreeCell(ps);
    	cell1.setEditor(new CellEditorString(treeComposite, ps.eContainer()));
		
		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{cell0, cell1}, parent){
			public boolean hasChildren(){
				return true;
			}
			
			@Override
			protected TreeRow[] doGetChildren() {
				
				PlatformService ps = (PlatformService)getCell(1).getData();
				
				if(ps == null){
					return null;
				}
				
				List<TreeRow> childRows = new ArrayList<TreeRow>();
				
				childRows.addAll(createEntity(ps, this));
				
				if(ps instanceof OperationInterfaceContainer){
					OperationInterfaceContainer oic = (OperationInterfaceContainer)ps;
					TreeRow child1 = createOperationInterfaceList("Provided interfaces", oic.getProvidedInterfaces(), this);
					child1.getCell(1).bind(oic, ApplicationPackage.Literals.OPERATION_INTERFACE_CONTAINER__PROVIDED_INTERFACES);
					child1.getCell(1).setEditor(CellEditorList.createPIEditor(treeComposite, child1.getCell(1), oic));
					child1.setChildListEditor(new EditorProvidedInterfaces(treeComposite, oic));
					childRows.add(child1);
					
					TreeRow child2 = createOperationInterfaceList("Required interfaces", oic.getRequiredInterfaces(), this);
					child2.setChildListEditor(new EditorRequiredInterfaces(treeComposite, oic));
					child2.getCell(1).bind(oic, ApplicationPackage.Literals.OPERATION_INTERFACE_CONTAINER__REQUIRED_INTERFACES);
					child2.getCell(1).setEditor(CellEditorList.createRIEditor(treeComposite, child1.getCell(1), oic));
					childRows.add(child2);
				}
				if(ps instanceof SoftwareServiceContainer){
					SoftwareServiceContainer ssc = (SoftwareServiceContainer)ps;
					TreeRow child = createSoftwareServiceList("Deployed software services", ssc.getSoftwareServices(), this);
					child.getCell(1).bind(ssc, ArchitecturePackage.Literals.SOFTWARE_SERVICE_CONTAINER__SOFTWARE_SERVICES);
					child.getCell(1).setEditor(CellEditorList.createSSEditor(treeComposite, child.getCell(1), ssc));
					child.setChildListEditor(new EditorSoftwareServices(treeComposite, ssc));
					childRows.add(child);
				}
				if(ps instanceof ProvidedService){
					ProvidedService providedService = (ProvidedService)ps;
					ServiceDeployment sd = providedService.getDeployment();
					childRows.add(createServiceDeployment("Deployment", sd, this));
				}

				//computing infrastructure service
				ComputingInfrastructureService cis = ps.getComputingInfrastructureService();
				if (cis != null) {
					TreeRow childRow = createComputingInfrastructureService("Deployed on", cis, this);
					TreeCell cell01 = childRow.getCell(1);
					cell01.bind(ps, ArchitecturePackage.Literals.PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE);

					childRow.getCell(0).setImage(ImageResource.DEPLOYED_ON);
					childRows.add(childRow);
				}

				return childRows.toArray(new TreeRow[childRows.size()]);
			}
		};
		
		return treeRow;
	}
	
	public TreeRow createSoftwareServiceList(String name, List<? extends SoftwareService> softwareServices, TreeRow parent){

		TreeCell cell0 = new TreeCell(name, ImageResource.LAYER);
		TreeCell cell1 = new TreeCell(softwareServices);
		cell1.setShowText(false);
	
		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{cell0, cell1}, parent){

			public boolean hasChildren() {
				@SuppressWarnings("unchecked")
				List<SoftwareService> ssList = (List<SoftwareService>)(getCell(1).getData());
				
				if(ssList != null && !ssList.isEmpty()){
					return true;
				}
				return false;
			};

			public TreeRow[] doGetChildren() {
				
				@SuppressWarnings("unchecked")
				List<SoftwareService> ssList = (List<SoftwareService>)(getCell(1).getData());
				TreeRow[] child = new TreeRow[ssList.size()];
				
				for(int i=0; i<ssList.size(); i++){
					child[i] = createSoftwareService("Software service: " + i, ssList.get(i), this);
				}
				return child;
			};
		};
		
		return treeRow;
	}
	
	public TreeRow createSoftwareService(String name, SoftwareService ss, TreeRow parent){
		TreeCell cell0 = new TreeCell(name, ImageResource.SOFTWARE_SERVICE);
		TreeCell cell1 = new TreeCell(ss);
    	cell1.setEditor(new CellEditorString(treeComposite, ss.eContainer()));
	
		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{cell0, cell1}, parent){
			
			public boolean hasChildren() {
				return true;
			};
			
			public TreeRow[] doGetChildren() {
				
				SoftwareService ss = (SoftwareService)(getCell(1).getData());
				
				if(ss == null){
					return null;
				}
				
				List<TreeRow> children = new ArrayList<TreeRow>();
				children.addAll(createEntity(ss, this));
				
				//provided interfaces
				TreeRow child1 = createOperationInterfaceList("Provided interfaces", ss.getProvidedInterfaces(), this);
				child1.setChildListEditor(new EditorProvidedInterfaces(treeComposite, ss));
				child1.getCell(1).bind(ss, ApplicationPackage.Literals.OPERATION_INTERFACE_CONTAINER__PROVIDED_INTERFACES);
				child1.getCell(1).setEditor(CellEditorList.createPIEditor(treeComposite, child1.getCell(1), ss));
				children.add(child1);
				
				//required interfaces
				TreeRow child2 = createOperationInterfaceList("Required interfaces", ss.getRequiredInterfaces(), this);
				child2.setChildListEditor(new EditorRequiredInterfaces(treeComposite, ss));
				child2.getCell(1).bind(ss, ApplicationPackage.Literals.OPERATION_INTERFACE_CONTAINER__REQUIRED_INTERFACES);
				child2.getCell(1).setEditor(CellEditorList.createRIEditor(treeComposite, child2.getCell(1), ss));
				children.add(child2);
				
				if(ss instanceof ProvidedService){
					ProvidedService ps = (ProvidedService)ss;
					
					TreeRow childRow = createServiceDeployment("Deployment", ps.getDeployment(), this);
					children.add(childRow);
				}
				
				SoftwareServiceContainer ssc = ss.getRuntimePlatformService();
				if(ssc != null){
					TreeRow child3 = createPlatformService("Deployed on", (PlatformService)ssc, this);
					child3.getCell(1).bind(ss, ArchitecturePackage.Literals.SOFTWARE_SERVICE__RUNTIME_PLATFORM_SERVICE);
					child3.getCell(0).setImage(ImageResource.DEPLOYED_ON);
					children.add(child3);
				}
						
				return children.toArray(new TreeRow[children.size()]);
			};
		};
		
		return treeRow;
	}
	
}
