package eu.cloudscaleproject.env.toolchain.util;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.common.ColorResources;
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
		
		this.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent e) {
				sidebarBuilder.dispose();
			}
		});
	}
	
	public void setContentProvider(SidebarContentProvider compositeProvider) {
		sidebarBuilder.setContentProvider(compositeProvider);
	}

	public void setResourceProvider(ResourceProvider resourceProvider) {
		sidebarBuilder.setResourceProvider(resourceProvider);
	}

	@Override
	public void init(){
		sidebarBuilder.init();
	}
	
	@Override
	public void update() {
		sidebarBuilder.update();
		super.update();
	}
	
	@Override
	public void showInput(IEditorInput input){
		sidebarBuilder.showInput(input);
	}

	@Override
	public void addSidebarEditor(IEditorInput ei, String section){
		sidebarBuilder.addSidebarEditor(ei, section);
	}

	@Override
	public void removeSidebarEditor(IEditorInput ei){
		sidebarBuilder.removeSidebarEditor(ei);
	}

	@Override
	public Color getSidebarSectionBackgroundColor(){
		return ColorResources.COLOR_CS_BLUE;
	}

	@Override
	public Color getSidebarSectionForegroundColor(){
		return ColorResources.COLOR_BLACK;
	}

	@Override
	public Color getSidebarBackgroundColor(){
		return ColorResources.COLOR_CS_BLUE_LIGHT;
	}

	@Override
	public Color getSidebarForegroundColor(){
		return ColorResources.COLOR_CS_BLUE_DARK;
	}

	@Override
	public void setNewButtonEnabled(boolean enable){
		sidebarBuilder.setNewButtonEnabled(enable);
	}

	@Override
	public void setNewFromButtonEnabled(boolean enable){
		sidebarBuilder.setNewFromButtonEnabled(enable);
	}

	@Override
	public void setRemoveButtonEnabled(boolean enable){
		sidebarBuilder.setRemoveButtonEnabled(enable);
	}

	@Override
	public IPropertySheetPage getPropertySheetPage() {
		return sidebarBuilder.getPropertySheetPage();
	}

	@Override
	public List<IEditorInput> getInputs(String section) {
		return sidebarBuilder.getInputs(section);
	}

	@Override
	public String[] getSidebarSections() {
		return sidebarBuilder.getSidebarSections();
	}
	
}
