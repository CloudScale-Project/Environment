package org.scaledl.overview.properties.components.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.scaledl.overview.application.ApplicationPackage;
import org.scaledl.overview.application.Operation;
import org.scaledl.overview.application.OperationInterface;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.architecture.ComputingInfrastructureService;
import org.scaledl.overview.architecture.Connection;
import org.scaledl.overview.architecture.ExternalConnection;
import org.scaledl.overview.architecture.InternalConnection;
import org.scaledl.overview.architecture.PlatformService;
import org.scaledl.overview.architecture.ServiceProxy;
import org.scaledl.overview.architecture.SoftwareService;
import org.scaledl.overview.architecture.UsageProxy;
import org.scaledl.overview.properties.components.tree.factory.ArchitectureTreeRowFactory;

public class BasicTreeContentProvider implements ITreeContentProvider{
	
	HashSet<TreeRow> createdRows = new HashSet<TreeRow>();
	
	private final ArchitectureTreeRowFactory architectureFactory;
	
	public BasicTreeContentProvider(TreeViewer treeViewer){
		this.architectureFactory = new ArchitectureTreeRowFactory(treeViewer);
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
    		tr = architectureFactory.createCloudEnvironment("Cloud environment", ce, null);
    	}
    	else if(object instanceof Connection){
    		Connection connection = (Connection)object;
    		if(connection instanceof InternalConnection){
    			tr = architectureFactory.createConnection("Internal connection", connection, null);
    		}
    		else if(connection instanceof ExternalConnection){
    			tr = architectureFactory.createConnection("External connection", connection, null);

    		}
    	}
    	else if(object instanceof UsageProxy){
    		UsageProxy up = (UsageProxy)object;
    		tr = architectureFactory.createOperationInterfaceList("Exposed interfaces", up.getRequiredInterfaces(), null);
    		//TODO:refactor
    		//tr.getCell(1).setEditor(new CellEditorRequiredInterfacesList(factory.treeComposite, tr.getCell(1), up));
    		tr.getCell(1).bind(up, ApplicationPackage.Literals.OPERATION_INTERFACE_CONTAINER__REQUIRED_INTERFACES);
    	}
    	else if(object instanceof ServiceProxy){
    		ServiceProxy sp = (ServiceProxy)object;
    		tr = architectureFactory.createSoftwareService("Service proxy", sp.getSoftwareService(), null);
    	}
    	else if(object instanceof ComputingInfrastructureService){
    		ComputingInfrastructureService cis = (ComputingInfrastructureService)object;
    		tr = architectureFactory.createComputingInfrastructureService("Comp. infrastructure", cis, null);
    	}
    	else if (object instanceof PlatformService){
    		PlatformService ps = (PlatformService)object;
    		tr = architectureFactory.createPlatformService("Platform service", ps, null);
    	}
    	else if (object instanceof SoftwareService){
    		SoftwareService ss = (SoftwareService)object;
    		tr = architectureFactory.createSoftwareService("Software service", ss, null);
    	}
    	else if (object instanceof OperationInterface){
    		OperationInterface oi = (OperationInterface)object;
    		tr = architectureFactory.createOperationInterface("Opeartion interface", oi, null);
    	}
    	else if (object instanceof Operation){
    		Operation o = (Operation)object;
    		tr = architectureFactory.createOperation("Opeartion", o, null);
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
