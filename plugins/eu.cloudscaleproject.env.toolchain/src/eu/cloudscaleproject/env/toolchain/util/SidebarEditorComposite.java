package eu.cloudscaleproject.env.toolchain.util;

import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.common.CommonResources;
import eu.cloudscaleproject.env.toolchain.IPropertySheetPageProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;

public class SidebarEditorComposite extends Composite implements ISidebarEditor, IPropertySheetPageProvider{

	private SidebarEditor sidebarBuilder;

	protected final Composite compositeSidebar;
	protected final Composite compositeArea;
	
	public SidebarEditorComposite(Composite parent, int style) {
		super(parent, style);
		
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginHeight = 0;
		gridLayout.marginWidth = 0;
		gridLayout.verticalSpacing = 0;
		gridLayout.horizontalSpacing = 0;
		setLayout(gridLayout);
		
		compositeSidebar = new Composite(this, SWT.NONE);
		GridData gd_compositSidebar = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_compositSidebar.widthHint = 160;
		gd_compositSidebar.minimumWidth = 120;
		compositeSidebar.setLayoutData(gd_compositSidebar);
		
		compositeArea = new Composite(this, SWT.NONE);
		GridData gd_compositeArea = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		compositeArea.setLayoutData(gd_compositeArea);
		
		sidebarBuilder = new SidebarEditor(compositeSidebar, compositeArea){
			
			@Override
			public void resourceChanged() {
				super.resourceChanged();
				SidebarEditorComposite.this.resourceChanged();
			}
			
			public Color getSidebarSectionBackgroundColor(){
				return SidebarEditorComposite.this.getSidebarSectionBackgroundColor();
			}
			
			public Color getSidebarSectionForegroundColor(){
				return SidebarEditorComposite.this.getSidebarSectionForegroundColor();
			}
			
			public Color getSidebarBackgroundColor(){
				return SidebarEditorComposite.this.getSidebarBackgroundColor();
			}
			
			public Color getSidebarForegroundColor(){
				return SidebarEditorComposite.this.getSidebarForegroundColor();
			}
		};
	}
	
	public void setContentProvider(SidebarContentProvider compositeProvider) {
		sidebarBuilder.setContentProvider(compositeProvider);
	}

	public void setResourceProvider(ResourceProvider resourceProvider) {
		sidebarBuilder.setResourceProvider(resourceProvider);
	}

	public void init(){
		sidebarBuilder.init();
	}
	
	public void showInput(IEditorInput input){
		sidebarBuilder.showInput(input);
	}
	
	public void addSidebarEditor(IEditorInput ei, String section){
		sidebarBuilder.addSidebarEditor(ei, section);
	}
	
	public void removeSidebarEditor(IEditorInput ei){
		sidebarBuilder.removeSidebarEditor(ei);
	}
	
	public void update(){
		sidebarBuilder.update();
		super.update();
	}
	
	public Color getSidebarSectionBackgroundColor(){
		return CommonResources.COLOR_CS_BLUE;
	}
	
	public Color getSidebarSectionForegroundColor(){
		return CommonResources.COLOR_BLACK;
	}
	
	public Color getSidebarBackgroundColor(){
		return CommonResources.COLOR_CS_BLUE_LIGHT;
	}
	
	public Color getSidebarForegroundColor(){
		return CommonResources.COLOR_CS_BLUE_DARK;
	}
	
	public void setNewButtonEnabled(boolean enable){
		sidebarBuilder.setNewButtonEnabled(enable);
	}
	
	public void setNewFromButtonEnabled(boolean enable){
		sidebarBuilder.setNewFromButtonEnabled(enable);
	}
	
	public void setRemoveButtonEnabled(boolean enable){
		sidebarBuilder.setRemoveButtonEnabled(enable);
	}

	@Override
	public IResource[] getResources() {
		return sidebarBuilder.getResources();
	}

	@Override
	public void resourceChanged() {		
	}

	@Override
	public IPropertySheetPage getPropertySheetPage() {
		return null;
	}

	@Override
	public List<IEditorInput> getInputs(String section) {
		return sidebarBuilder.getInputs(section);
	}

	@Override
	public String[] getSidebarSections() {
		return sidebarBuilder.getSidebarSections();
	}

	@Override
	public IResource[] getDependentRootResource() {
		return sidebarBuilder.getDependentRootResource();
	}
}
