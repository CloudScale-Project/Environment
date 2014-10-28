package org.scaledl.overview.properties.components.tree.factory;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TreeViewer;
import org.scaledl.overview.architecture.ArchitecturePackage;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.architecture.Connection;
import org.scaledl.overview.architecture.ExternalConnection;
import org.scaledl.overview.architecture.InternalConnection;
import org.scaledl.overview.properties.components.tree.TreeCell;
import org.scaledl.overview.properties.components.tree.TreeRow;
import org.scaledl.overview.properties.components.tree.editors.CellEditorInt;
import org.scaledl.overview.specification.ComputingInfrastructureServiceDescriptor;
import org.scaledl.overview.specification.ComputingResourceDescriptor;
import org.scaledl.overview.specification.NetworkInfrastructureServiceDescriptor;
import org.scaledl.overview.specification.SpecificationPackage;
import org.scaledl.overview.util.OverviewUtil;

public class SpecificationTreeRowFactory extends TreeRowFactory{
	
	public SpecificationTreeRowFactory(TreeViewer treeViewer) {
		super(treeViewer);
	}

	/*
	public TreeRow createGenericComputingEntityDescriptor(String name, GenericComputingEntityDescriptor descriptor, TreeRow parent){
		
		TreeCell cell0 = new TreeCell(name, ImageResource.INFRASTRUCTURE_SERVICE);
		TreeCell cell1 = new TreeCell(descriptor);
    	
		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{cell0, cell1}, parent){
			
			public boolean hasChildren(){
				return true;
			}
		};
    	
		return treeRow;
	}
	*/
	
	public TreeRow createConnection(String name, Connection connection, TreeRow parent) {

		TreeCell cell0 = new TreeCell(name);
		TreeCell cell1 = new TreeCell(connection);
		
		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{cell0, cell1}, parent){

			public boolean hasChildren(){
				return true;
			}
			
			@Override
			protected TreeRow[] doGetChildren() {
				
				Connection connection = (Connection) getCell(
						1).getData();

				if (connection == null) {
					return null;
				}

				List<TreeRow> children = new ArrayList<TreeRow>();
				
				if(connection instanceof InternalConnection){
					
					NetworkInfrastructureServiceDescriptor nisd = ((InternalConnection)connection).getDescriptor();
					
					if(nisd == null){
						CloudEnvironment ce = OverviewUtil.getCloudEnvironment(connection);
						if(ce != null && ce.getAvailabilityZoneDescriptor() != null){
							nisd = ce.getAvailabilityZoneDescriptor().getNetworkInfrastructureServiceDescriptor();
						}
					}
					
					if(nisd == null){
						return null;
					}
					
					TreeCell c00 = new TreeCell("Bandwidth");
					TreeCell c01 = new TreeCell(nisd.getBandwidth());
					c01.bind(nisd, SpecificationPackage.Literals.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__BANDWIDTH);
					c01.setEditor(new CellEditorInt(treeComposite, nisd));
					children.add(new TreeRow(treeViewer, new TreeCell[]{c00, c01}, this));

					TreeCell c10 = new TreeCell("Latency");
					TreeCell c11 = new TreeCell(nisd.getLatency());
					c11.bind(nisd, SpecificationPackage.Literals.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__LATENCY);
					c11.setEditor(new CellEditorInt(treeComposite, nisd));
					children.add(new TreeRow(treeViewer, new TreeCell[]{c10, c11}, this));
				}
				else if(connection instanceof ExternalConnection){
					ExternalConnection ec = (ExternalConnection)connection;
					
					TreeCell c00 = new TreeCell("Bandwidth");
					TreeCell c01 = new TreeCell(ec.getBandwidth());
					c01.bind(ec, ArchitecturePackage.Literals.EXTERNAL_CONNECTION__BANDWIDTH);
					c01.setEditor(new CellEditorInt(treeComposite, ec));
					children.add(new TreeRow(treeViewer, new TreeCell[]{c00, c01}, this));

					TreeCell c10 = new TreeCell("Latency");
					TreeCell c11 = new TreeCell(ec.getLatency());
					c11.bind(ec, ArchitecturePackage.Literals.EXTERNAL_CONNECTION__LATENCY);
					c11.setEditor(new CellEditorInt(treeComposite, ec));
					children.add(new TreeRow(treeViewer, new TreeCell[]{c10, c11}, this));
				}
				
				return children.toArray(new TreeRow[children.size()]);
			}
		};

		
		return treeRow;
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
				c01.bind(nisd, SpecificationPackage.Literals.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__BANDWIDTH);
				c01.setEditor(new CellEditorInt(treeComposite, nisd));
				children.add(new TreeRow(treeViewer, new TreeCell[]{c00, c01}, this));

				TreeCell c10 = new TreeCell("Latency");
				TreeCell c11 = new TreeCell(nisd.getLatency());
				c11.bind(nisd, SpecificationPackage.Literals.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__LATENCY);
				c11.setEditor(new CellEditorInt(treeComposite, nisd));
				children.add(new TreeRow(treeViewer, new TreeCell[]{c10, c11}, this));
				
				return children.toArray(new TreeRow[children.size()]);
			}
		};

		
		return treeRow;
	}
	
	public TreeRow createComputingInfrastructureServiceDescriptor(String name,
			ComputingInfrastructureServiceDescriptor cisd, TreeRow parent) {

		TreeCell cell0 = new TreeCell(name);
		TreeCell cell1 = new TreeCell(cisd);
		
		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{cell0, cell1}, parent){

			public boolean hasChildren(){
				return true;
			}
			
			@Override
			protected TreeRow[] doGetChildren() {
								
				ComputingInfrastructureServiceDescriptor cisd = 
						(ComputingInfrastructureServiceDescriptor)getCell(1).getData();
				
				if(cisd == null){
					return null;
				}
				
				List<TreeRow> rows = new ArrayList<TreeRow>();
				rows.addAll(createEntity(cisd, this));
				
				int counter = 0;
				for(ComputingResourceDescriptor crd : cisd.getComputingResourceDescriptors()){
					counter++;
					rows.add(createComputingResourceDescriptor("Computing resource "+counter+":", crd, this));
				}
				
				return rows.toArray(new TreeRow[rows.size()]);
			}
		};

		
		return treeRow;
	}
	
	public TreeRow createComputingResourceDescriptor(String name,
			ComputingResourceDescriptor crd, TreeRow parent) {

		TreeCell cell0 = new TreeCell(name);
		TreeCell cell1 = new TreeCell(crd);
		
		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{cell0, cell1}, parent){

			public boolean hasChildren(){
				return true;
			}
			
			@Override
			protected TreeRow[] doGetChildren() {
				
				TreeRow[] c = new TreeRow[5];
				
				ComputingResourceDescriptor crd = 
						(ComputingResourceDescriptor)getCell(1).getData();
				
				if(crd == null){
					return null;
				}
				
				TreeCell child00 = new TreeCell("Description");
				TreeCell child01 = new TreeCell(crd.getDescription());
				c[0] = new TreeRow(treeViewer, new TreeCell[]{child00, child01}, this);

				TreeCell child10 = new TreeCell("CPU");
				TreeCell child11 = new TreeCell(crd.getCpu());
				child11.bind(crd, SpecificationPackage.Literals.COMPUTING_RESOURCE_DESCRIPTOR__CPU);
				c[1] = new TreeRow(treeViewer, new TreeCell[]{child10, child11}, this);

				TreeCell child20 = new TreeCell("CPU units");
				TreeCell child21 = new TreeCell(crd.getCpuUnits());
				child21.bind(crd, SpecificationPackage.Literals.COMPUTING_RESOURCE_DESCRIPTOR__CPU_UNITS);
				c[2] = new TreeRow(treeViewer, new TreeCell[]{child20, child21}, this);

				TreeCell child30 = new TreeCell("Memory");
				TreeCell child31 = new TreeCell(crd.getMemory());
				child31.bind(crd, SpecificationPackage.Literals.COMPUTING_RESOURCE_DESCRIPTOR__MEMORY);
				c[3] = new TreeRow(treeViewer, new TreeCell[]{child30, child31}, this);

				TreeCell child40 = new TreeCell("Storage");
				TreeCell child41 = new TreeCell(crd.getStorage());
				child41.bind(crd, SpecificationPackage.Literals.COMPUTING_RESOURCE_DESCRIPTOR__STORAGE);
				c[4] = new TreeRow(treeViewer, new TreeCell[]{child40, child41}, this);
				
				if(crd.isEditable()){
					child11.setEditor(new CellEditorInt(treeComposite, crd));
					child21.setEditor(new CellEditorInt(treeComposite, crd));
					child31.setEditor(new CellEditorInt(treeComposite, crd));
					child41.setEditor(new CellEditorInt(treeComposite, crd));
				}
				
				return c;
			}
		};

		
		return treeRow;
	}

}
