package eu.cloudscaleproject.env.csm.properties.components.tree;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Tree;

import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService;
import eu.cloudscaleproject.env.csm.architecture.DeployablePlatformService;
import eu.cloudscaleproject.env.csm.architecture.DeployableSoftwareService;
import eu.cloudscaleproject.env.csm.architecture.InfrastructureLayer;
import eu.cloudscaleproject.env.csm.architecture.InfrastructureService;
import eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer;
import eu.cloudscaleproject.env.csm.architecture.PlatformService;
import eu.cloudscaleproject.env.csm.architecture.SoftwareService;
import eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer;
import eu.cloudscaleproject.env.csm.core.CorePackage;
import eu.cloudscaleproject.env.csm.core.Entity;
import eu.cloudscaleproject.env.csm.core.Operation;
import eu.cloudscaleproject.env.csm.core.OperationInterface;
import eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.DefinitionPackage;
import eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor;
import eu.cloudscaleproject.env.csm.properties.ImageResource;
import eu.cloudscaleproject.env.csm.properties.components.editors.EditorInfrastructureLayer;
import eu.cloudscaleproject.env.csm.properties.components.editors.EditorOperations;
import eu.cloudscaleproject.env.csm.properties.components.editors.EditorProvidedInterfaces;
import eu.cloudscaleproject.env.csm.properties.components.editors.EditorRequiredInterfaces;
import eu.cloudscaleproject.env.csm.properties.components.editors.EditorSoftwareServices;
import eu.cloudscaleproject.env.csm.properties.components.editors.EditorUnresolvedInterfaces;
import eu.cloudscaleproject.env.csm.properties.components.tree.editors.CellEditorDropdown;
import eu.cloudscaleproject.env.csm.properties.components.tree.editors.CellEditorInt;
import eu.cloudscaleproject.env.csm.properties.components.tree.editors.CellEditorList;
import eu.cloudscaleproject.env.csm.properties.components.tree.editors.CellEditorOperation;
import eu.cloudscaleproject.env.csm.properties.components.tree.editors.CellEditorString;
import eu.cloudscaleproject.env.csm.util.CsmUtil;

public class TreeRowFactory {
	
	public final Tree treeComposite;
	public final TreeViewer treeViewer;
	
	public TreeRowFactory(TreeViewer treeViewer) {
		this.treeViewer = treeViewer;
		this.treeComposite = treeViewer.getTree();
	}
	
	public List<TreeRow> createEntity(Entity entity, TreeRow parent){
		
		List<TreeRow> out = new ArrayList<TreeRow>();
		
		TreeCell cell10 = new TreeCell("Description");
		TreeCell cell11 = new TreeCell(entity.getDescription());
		cell11.bind(entity, CorePackage.Literals.ENTITY__DESCRIPTION);
		cell11.setEditor(new CellEditorString(treeComposite, entity));
		TreeRow child1 = new TreeRow(treeViewer, new TreeCell[]{cell10, cell11}, parent);
		out.add(child1);

		return out;
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

				children.add(createNetworkInfrastructureDescriptor("Internal connectios", 
						ce.getAvailabilityZoneDescriptor().getInternalConnectionDescriptor(), this));

				TreeRow inf = createInfrastructureServiceList("Infrastructure services layer", 
						ce.getInfrastructureLayer().getServices(), this);
				inf.getCell(1).bind(ce.getInfrastructureLayer(), 
						ArchitecturePackage.Literals.INFRASTRUCTURE_LAYER__SERVICES);
				inf.getCell(1).setEditor(CellEditorList.createISEditor(treeComposite, inf.getCell(1), ce.getInfrastructureLayer()));
				inf.setChildListEditor(new EditorInfrastructureLayer(treeComposite, ce.getInfrastructureLayer()));
				children.add(inf);
				
				TreeRow plat = createPlatformServiceList("Platform services layer", 
						ce.getPlatformLayer().getServices(), this);
				plat.getCell(1).bind(ce.getPlatformLayer(), 
						ArchitecturePackage.Literals.PLATFORM_LAYER__SERVICES);
				children.add(plat);
				
				TreeRow soft = createSoftwareServiceList("Software services layer", 
						ce.getSoftwareLayer().getServices(), this);
				soft.getCell(1).bind(ce.getSoftwareLayer(), 
						ArchitecturePackage.Literals.SOFTWARE_LAYER__SERVICES);
				children.add(soft);
				
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
	
	public TreeRow createNetworkInfrastructureDescriptor(String name, 
			NetworkInfrastructureServiceDescriptor nisd, TreeRow parent) {

		TreeCell cell0 = new TreeCell(name);
		TreeCell cell1 = new TreeCell(nisd);
		
		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{cell0, cell1}, parent){

			public boolean hasChildren(){
				return true;
			}
			
			@Override
			protected TreeRow[] doGetChildren() {
				
				NetworkInfrastructureServiceDescriptor nisd = (NetworkInfrastructureServiceDescriptor) getCell(
						1).getData();

				if (nisd == null) {
					return null;
				}

				List<TreeRow> children = new ArrayList<TreeRow>();
				
				TreeCell c00 = new TreeCell("Bandwidth");
				TreeCell c01 = new TreeCell(nisd.getBandwidth());
				c01.bind(nisd, DefinitionPackage.Literals.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__BANDWIDTH);
				c01.setEditor(new CellEditorInt(treeComposite, nisd));
				children.add(new TreeRow(treeViewer, new TreeCell[]{c00, c01}, this));

				TreeCell c10 = new TreeCell("Latency");
				TreeCell c11 = new TreeCell(nisd.getLatency());
				c11.bind(nisd, DefinitionPackage.Literals.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__LATENCY);
				c11.setEditor(new CellEditorInt(treeComposite, nisd));
				children.add(new TreeRow(treeViewer, new TreeCell[]{c10, c11}, this));
				
				return children.toArray(new TreeRow[children.size()]);
			}
		};

		
		return treeRow;
	}

	public TreeRow createComputingInfrastructureDescriptor(String name,
			ComputingInfrastructureServiceDescriptor cisd, TreeRow parent) {

		TreeCell cell0 = new TreeCell(name);
		TreeCell cell1 = new TreeCell(cisd);
		
		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{cell0, cell1}, parent){

			public boolean hasChildren(){
				return true;
			}
			
			@Override
			protected TreeRow[] doGetChildren() {
				
				TreeRow[] c = new TreeRow[5];
				
				ComputingInfrastructureServiceDescriptor cisd = 
						(ComputingInfrastructureServiceDescriptor)this.getCell(1).getData();
				
				if(cisd == null){
					return null;
				}
				
				TreeCell child00 = new TreeCell("Description");
				TreeCell child01 = new TreeCell(cisd.getDescription());
				c[0] = new TreeRow(treeViewer, new TreeCell[]{child00, child01}, this);

				TreeCell child10 = new TreeCell("CPU");
				TreeCell child11 = new TreeCell(cisd.getCpu());
				child11.bind(cisd, DefinitionPackage.Literals.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__CPU);
				c[1] = new TreeRow(treeViewer, new TreeCell[]{child10, child11}, this);

				TreeCell child20 = new TreeCell("CPU units");
				TreeCell child21 = new TreeCell(cisd.getCpuUnits());
				child21.bind(cisd, DefinitionPackage.Literals.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__CPU_UNITS);
				c[2] = new TreeRow(treeViewer, new TreeCell[]{child20, child21}, this);

				TreeCell child30 = new TreeCell("Memory");
				TreeCell child31 = new TreeCell(cisd.getMemory());
				child31.bind(cisd, DefinitionPackage.Literals.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__MEMORY);
				c[3] = new TreeRow(treeViewer, new TreeCell[]{child30, child31}, this);

				TreeCell child40 = new TreeCell("Storage");
				TreeCell child41 = new TreeCell(cisd.getStorage());
				child41.bind(cisd, DefinitionPackage.Literals.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__STORAGE);
				c[4] = new TreeRow(treeViewer, new TreeCell[]{child40, child41}, this);
				
				return c;
			}
		};

		
		return treeRow;
	}
	
	public TreeRow createComputingInfrastructure(String name, ComputingInfrastructureService cis, TreeRow parent){
		
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
				
				TreeRow child2 = createComputingInfrastructureDescriptor("Comp. IaaS descriptor", cis.getDescriptor(), this);
				
				TreeCell cell20 = child2.getCell(0);
				cell20.setData("Type");
				
				TreeCell cell21 = child2.getCell(1); 
				cell21.bind(cis, ArchitecturePackage.Literals.COMPUTING_INFRASTRUCTURE_SERVICE__DESCRIPTOR);
				cell21.setEditor(new CellEditorDropdown(treeComposite, cell21,
						CsmUtil.getComputingInfrastructureServicesDescriptors(cis
						.getInfrastructureLayer().getCloudProvider())));
				children.add(child2);
				
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
						child[i] = createComputingInfrastructure("Infrastructure service: " + i, 
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
					child1.getCell(1).bind(oic, ArchitecturePackage.Literals.OPERATION_INTERFACE_CONTAINER__PROVIDED_INTERFACES);
					child1.getCell(1).setEditor(CellEditorList.createPIEditor(treeComposite, child1.getCell(1), oic));
					child1.setChildListEditor(new EditorProvidedInterfaces(treeComposite, oic));
					childRows.add(child1);
					
					TreeRow child2 = createOperationInterfaceList("Required interfaces", oic.getRequiredInterfaces(), this);
					child2.setChildListEditor(new EditorRequiredInterfaces(treeComposite, oic));
					child2.getCell(1).bind(oic, ArchitecturePackage.Literals.OPERATION_INTERFACE_CONTAINER__REQUIRED_INTERFACES);
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
				if(ps instanceof DeployablePlatformService){
					DeployablePlatformService dps = (DeployablePlatformService)ps;
					
					ComputingInfrastructureService cis = dps.getComputingInfrastructureService();
					TreeRow childRow = createComputingInfrastructure("Deployed on", cis, this);
					TreeCell cell01 = childRow.getCell(1);
					cell01.bind(dps, ArchitecturePackage.Literals.DEPLOYABLE_PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE);
					cell01.setEditor(new CellEditorDropdown(treeComposite, cell01, 
							dps.getPlatformLayer().getCloudEnvironment().getInfrastructureLayer().getServices()));
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
				child1.getCell(1).bind(ss, ArchitecturePackage.Literals.OPERATION_INTERFACE_CONTAINER__PROVIDED_INTERFACES);
				child1.getCell(1).setEditor(CellEditorList.createPIEditor(treeComposite, child1.getCell(1), ss));
				children.add(child1);
				
				//required interfaces
				TreeRow child2 = createOperationInterfaceList("Required interfaces", ss.getRequiredInterfaces(), this);
				child2.setChildListEditor(new EditorRequiredInterfaces(treeComposite, ss));
				child2.getCell(1).bind(ss, ArchitecturePackage.Literals.OPERATION_INTERFACE_CONTAINER__REQUIRED_INTERFACES);
				child2.getCell(1).setEditor(CellEditorList.createRIEditor(treeComposite, child2.getCell(1), ss));
				children.add(child2);
				
				//deployed on
				if(ss instanceof DeployableSoftwareService){
					DeployableSoftwareService dss = (DeployableSoftwareService)ss;
					TreeRow child3 = createPlatformService("Deployed on", (PlatformService)dss.getRuntimePlatformService(), this);
					child3.getCell(1).bind(dss, ArchitecturePackage.Literals.DEPLOYABLE_SOFTWARE_SERVICE__RUNTIME_PLATFORM_SERVICE);
					child3.getCell(0).setImage(ImageResource.DEPLOYED_ON);
					children.add(child3);
				}
						
				return children.toArray(new TreeRow[children.size()]);
			};
		};
		
		return treeRow;
	}
	
	public TreeRow createOperationInterfaceList(String name, List<? extends OperationInterface> operationInterfaces, TreeRow parent){
		TreeCell cell0 = new TreeCell(name, ImageResource.INTERFACE_LIST);
		TreeCell cell1 = new TreeCell(operationInterfaces);
		cell1.setShowText(false);
	
		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{cell0, cell1}, parent){
			public boolean hasChildren() {

				@SuppressWarnings("unchecked")
				List<OperationInterface> opList = (List<OperationInterface>)(getCell(1).getData());
				if(opList != null && !opList.isEmpty()){
					return true;
				}
				return false;
			};
			public TreeRow[] doGetChildren() {
				
				@SuppressWarnings("unchecked")
				List<OperationInterface> opList = (List<OperationInterface>)(getCell(1).getData());
				TreeRow[] child = new TreeRow[opList.size()];
				
				for(int i=0; i<opList.size(); i++){
					child[i] = createOperationInterface(String.valueOf(i+1)+". Interface", opList.get(i), this);
				}
				return child;
			};
		};
		return treeRow;
	}
	
	public TreeRow createOperationInterface(String name, OperationInterface oi , TreeRow parent){
		TreeCell cell0 = new TreeCell(name, ImageResource.INTERFACE);
		TreeCell cell1 = new TreeCell(oi);
    	cell1.setEditor(new CellEditorString(treeComposite, oi.eContainer()));
    	
    	TreeCell cell2 = null;
    	if(!oi.getRequiringContainer().isEmpty()){
    		List<OperationInterfaceContainer> choices = new ArrayList<OperationInterfaceContainer>();
    		for(OperationInterfaceContainer oic : oi.getRequiringContainer()){
    			if(choices.isEmpty()){
    				choices.addAll(CsmUtil.collectPotentialyResolveConteiners(oic));
    			}
    			else{
    				choices.retainAll(CsmUtil.collectPotentialyResolveConteiners(oic));
    			}
    		}
    		cell2 = new TreeCell(oi.getProvidingContainer());
    		if(!choices.isEmpty()){
    			cell2.setEditor(new CellEditorDropdown(treeComposite, cell2, choices));
    			cell2.bind(oi, CorePackage.Literals.OPERATION_INTERFACE__PROVIDING_CONTAINER);
    		}
    	}
    	
		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{cell0, cell1, cell2}, parent){
			@Override
			public boolean hasChildren() {
				OperationInterface oi = (OperationInterface)getCell(1).getData();
				
				if(oi != null && !oi.getOperations().isEmpty()){
					return true;
				}
				return false;
			}
			
			@Override
			public TreeRow[] doGetChildren() {
				OperationInterface oi = (OperationInterface)getCell(1).getData();
				
				List<TreeRow> children = new ArrayList<TreeRow>();
				children.addAll(createEntity(oi, this));
				
				for(int i=0; i<oi.getOperations().size(); i++){
					children.add(createOperation(String.valueOf(i+1), oi.getOperations().get(i), this));
				}
				
				return children.toArray(new TreeRow[children.size()]);
			}
		};
		
		treeRow.reloadOnListChange(oi, CorePackage.Literals.OPERATION_INTERFACE__OPERATIONS);
		treeRow.setChildListEditor(new EditorOperations(treeComposite, oi));
		
		return treeRow;
	}
	
	public TreeRow createOperationList(String name, List<? extends Operation> operation, TreeRow parent){
		TreeCell cell0 = new TreeCell(name);
		TreeCell cell1 = new TreeCell(operation);
		cell1.setShowText(false);
	
		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{cell0, cell1}, parent){
			public boolean hasChildren() {

				@SuppressWarnings("unchecked")
				List<Operation> opList = (List<Operation>)(getCell(1).getData());
				if(opList != null && !opList.isEmpty()){
					return true;
				}
				return false;
			};
			public TreeRow[] doGetChildren() {
				
				@SuppressWarnings("unchecked")
				List<Operation> opList = (List<Operation>)(getCell(1).getData());
				TreeRow[] child = new TreeRow[opList.size()];
				
				for(int i=0; i<opList.size(); i++){
					child[i] = createOperation(String.valueOf(i+1), opList.get(i), this);
				}
				return child;
			};
		};
		
		return treeRow;
	}
	
	public TreeRow createOperation(String name, Operation op, TreeRow parent) {
		TreeCell cell0 = new TreeCell(name, ImageResource.OPERATION);
		TreeCell cell1 = new TreeCell(op);
		cell1.setEditor(new CellEditorOperation(treeComposite, op));

		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[] { cell0, cell1 },parent);
		return treeRow;
	}
}
