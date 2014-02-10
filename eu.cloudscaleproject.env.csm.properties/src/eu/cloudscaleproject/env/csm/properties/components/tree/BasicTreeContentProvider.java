package eu.cloudscaleproject.env.csm.properties.components.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService;
import eu.cloudscaleproject.env.csm.architecture.ExternalConnection;
import eu.cloudscaleproject.env.csm.architecture.HybridConnection;
import eu.cloudscaleproject.env.csm.architecture.InternalConnection;
import eu.cloudscaleproject.env.csm.architecture.PlatformService;
import eu.cloudscaleproject.env.csm.architecture.ServiceProxy;
import eu.cloudscaleproject.env.csm.architecture.SoftwareService;
import eu.cloudscaleproject.env.csm.architecture.UsageProxy;
import eu.cloudscaleproject.env.csm.core.Operation;
import eu.cloudscaleproject.env.csm.core.OperationInterface;
import eu.cloudscaleproject.env.csm.util.CsmUtil;

public class BasicTreeContentProvider implements ITreeContentProvider{
	
	HashSet<TreeRow> createdRows = new HashSet<TreeRow>();
	
	private final TreeRowFactory factory;
	
	public BasicTreeContentProvider(TreeViewer treeViewer){
		this.factory = new TreeRowFactory(treeViewer);
	}
	
	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
    }

    @Override
    public void dispose() {
    	for(TreeRow row : createdRows){
    		row.dispose();
    	}
    	createdRows.clear();
    }

    @Override
    public Object[] getElements(Object inputElement) {
        
    	if(inputElement instanceof List){
    		List<?> list = (List<?>)inputElement;
    		List<TreeRow> out = new ArrayList<TreeRow>();
    		
    		for(int i=0;i<list.size(); i++){
    			Object object = list.get(i);
    			TreeRow tr = getTreeRow(object);
    			if(tr != null){
    				out.add(tr);
    			}
            }
    		this.createdRows.addAll(out);
            return out.toArray();
    	}
    	else{
    		TreeRow tr = getTreeRow(inputElement);
    		this.createdRows.add(tr);
    		
    		if(tr == null){
    			return new TreeRow[]{};
    		}
    		else{
    			return new TreeRow[]{tr};
    		}
    	}
    }
    
    private TreeRow getTreeRow(Object object){
    	
    	TreeRow tr = null;

    	if(object instanceof CloudEnvironment){
    		CloudEnvironment ce = (CloudEnvironment)object;
    		tr = factory.createCloudEnvironment("Cloud environment", ce, null);
    	}
    	else if(object instanceof InternalConnection){
    		InternalConnection ic = (InternalConnection)object;
    		CloudEnvironment ce = CsmUtil.getCloudEnvironment(ic);
    		tr = factory.createNetworkInfrastructureDescriptor("Internal connection", 
    				ce.getAvailabilityZoneDescriptor().getInternalConnectionDescriptor(), null);
    	}
    	else if(object instanceof ExternalConnection){
    		ExternalConnection ec = (ExternalConnection)object;
    		tr = factory.createNetworkInfrastructureDescriptor("External connection", 
    				ec.getDescriptor(), null);
    	}
    	else if(object instanceof HybridConnection){
    		HybridConnection hc = (HybridConnection)object;
    		tr = factory.createNetworkInfrastructureDescriptor("Hybrid connection", 
    				hc.getDescriptor(), null);
    	}
    	else if(object instanceof UsageProxy){
    		UsageProxy up = (UsageProxy)object;
    		tr = factory.createOperationInterfaceList("Exposed interfaces", up.getRequiredInterfaces(), null);
    		//TODO:refactor
    		//tr.getCell(1).setEditor(new CellEditorRequiredInterfacesList(factory.treeComposite, tr.getCell(1), up));
    		tr.getCell(1).bind(up, ArchitecturePackage.Literals.OPERATION_INTERFACE_CONTAINER__REQUIRED_INTERFACES);
    	}
    	else if(object instanceof ServiceProxy){
    		ServiceProxy sp = (ServiceProxy)object;
    		tr = factory.createSoftwareService("Service proxy", sp.getSoftwareService(), null);
    	}
    	else if(object instanceof ComputingInfrastructureService){
    		ComputingInfrastructureService cis = (ComputingInfrastructureService)object;
    		tr = factory.createComputingInfrastructure("Comp. infrastructure", cis, null);
    	}
    	else if (object instanceof PlatformService){
    		PlatformService ps = (PlatformService)object;
    		tr = factory.createPlatformService("Platform service", ps, null);
    	}
    	else if (object instanceof SoftwareService){
    		SoftwareService ss = (SoftwareService)object;
    		tr = factory.createSoftwareService("Software service", ss, null);
    	}
    	else if (object instanceof OperationInterface){
    		OperationInterface oi = (OperationInterface)object;
    		tr = factory.createOperationInterface("Opeartion interface", oi, null);
    	}
    	else if (object instanceof Operation){
    		Operation o = (Operation)object;
    		tr = factory.createOperation("Opeartion", o, null);
    	}
    	
    	return tr;
    }

    @Override
    public Object[] getChildren(Object parentElement) {
    	if(parentElement instanceof TreeRow){
    		TreeRow row = (TreeRow)parentElement;
    		return row.getChildren();
    	}
    	return null;
    }

    @Override
    public Object getParent(Object element) {
    	if(element instanceof TreeRow){
    		TreeRow row = (TreeRow)element;
    		return row.getParent();
    	}
    	return null;
    }

    @Override
    public boolean hasChildren(Object element) {
    	if(element instanceof TreeRow){
    		TreeRow row = (TreeRow)element;
    		return row.hasChildren();
    	}
    	return false;
    }
}
