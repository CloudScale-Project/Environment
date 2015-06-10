package eu.cloudscaleproject.env.method.viewer.diagram.properties;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.GFPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.common.ui.resources.ResourceManager;
import eu.cloudscaleproject.env.method.common.method.*;


public class PropertySection extends GFPropertySection implements ITabbedPropertyConstants, IExecutableExtensionFactory{

	@Override
	public Object create() throws CoreException {
		return new PropertySection();
	}
	
	@Optional
	private CommandExecutor commandExecutor;
	
	private Composite mainComposite;
	
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		
		mainComposite = new Composite(parent, SWT.NONE);
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		mainComposite.setLayout(rowLayout);
		
	}
	
	@Override
	public void refresh() {
		
		//clear
		for(Control con : mainComposite.getChildren()){
			con.dispose();
		}
		
		Section section = null;
		EObject bo = (EObject)Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(getSelectedPictogramElement());
		
		if(bo == null){
			return;
		}
		
		if(!(bo instanceof Section)){
			while(bo.eContainer() != null){
				bo = bo.eContainer();
				if(bo instanceof Section){
					section = (Section)bo;
				}
			}
		}
		else{
			section = (Section)bo;
		}
		
		if(section == null || section.getWarnings() == null){
			return;
		}
		
		for(Warning warning : section.getWarnings()){
			Composite c = new Composite(mainComposite, SWT.NONE);
			c.setBackground(ResourceManager.getColor(252, 207, 153));
			
			RowData layoutData = new RowData();
			layoutData.height = 60;
			c.setLayoutData(layoutData);
			
			RowLayout rowLayoutV = new RowLayout(SWT.VERTICAL);
			c.setLayout(rowLayoutV);
			
			{
				Text title = new Text(c, SWT.NONE);
				title.setText(warning.getMessage());
				title.setData(new RowData(200, 20));
				
				Composite commandsComposite = new Composite(c, SWT.NONE);
				commandsComposite.setData(new RowData());
				
				RowLayout rowLayoutH = new RowLayout(SWT.HORIZONTAL);
				commandsComposite.setLayout(rowLayoutH);
				
				//create commands
				for(Command command : warning.getCommands()){
					Button button = new Button(commandsComposite, SWT.NONE);
					button.setText(command.getName());
					button.setData(new RowData());
				}
				commandsComposite.pack();
				
			}
			c.pack();
		}
		mainComposite.pack();
	}

}
