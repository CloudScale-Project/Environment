package eu.cloudscaleproject.env.toolchain.util;

import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.common.CommonResources;
import eu.cloudscaleproject.env.toolchain.IPropertySheetPageProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;

public abstract class AbstractSidebarEditorComposite extends Composite implements ISidebarEditor, IPropertySheetPageProvider{
	
	private AbstractSidebarEditor sidebarBuilder;
	
	protected final Composite compositeSidebar;
	protected final Composite compositeArea;
	
	public abstract Composite createInputComposite(IEditorInput input, Composite parent, int style);
	public void handleNewInput(IEditorInput selected){};
	public void handleNewInputFrom(IEditorInput selected){};
	public void handleInputDelete(IEditorInput toDelete){};	
	public void handleSelect(IEditorInput selected){};	
	
	public AbstractSidebarEditorComposite(Composite parent, int style) {
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
		
		this.sidebarBuilder = new AbstractSidebarEditor(compositeSidebar, compositeArea) {

			@Override
			public Composite createInputComposite(IEditorInput input, Composite parent,
					int style) {
				return AbstractSidebarEditorComposite.this.createInputComposite(input, parent, style);
			}

			@Override
			public List<IEditorInput> getInputs(String section) {
				return AbstractSidebarEditorComposite.this.getInputs(section);
			}

			@Override
			public String[] getSidebarSections() {
				return AbstractSidebarEditorComposite.this.getSidebarSections();
			}

			@Override
			public IResource[] getDependentRootResource() {
				return AbstractSidebarEditorComposite.this.getDependentRootResource();
			}
			
			public Color getSidebarSectionBackgroundColor(){
				return AbstractSidebarEditorComposite.this.getSidebarSectionBackgroundColor();
			}
			
			public Color getSidebarSectionForegroundColor(){
				return AbstractSidebarEditorComposite.this.getSidebarSectionForegroundColor();
			}
			
			public Color getSidebarBackgroundColor(){
				return AbstractSidebarEditorComposite.this.getSidebarBackgroundColor();
			}
			
			public Color getSidebarForegroundColor(){
				return AbstractSidebarEditorComposite.this.getSidebarForegroundColor();
			}
			
			@Override
			public void handleNewInput(IEditorInput selected) {
				AbstractSidebarEditorComposite.this.handleNewInput(selected);
			}
			
			@Override
			public void handleNewInputFrom(IEditorInput selected) {
				AbstractSidebarEditorComposite.this.handleNewInputFrom(selected);
			}
			
			@Override
			public void handleInputDelete(IEditorInput toDelete) {
				AbstractSidebarEditorComposite.this.handleInputDelete(toDelete);
			}
			
			@Override
			public void handleSelect(IEditorInput selected) {
				AbstractSidebarEditorComposite.this.handleSelect(selected);
			}
		};
		
		this.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent e) {
				sidebarBuilder.dispose();
			}
		});
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
		sidebarBuilder.resourceChanged();
	}
	
	@Override
	public IPropertySheetPage getPropertySheetPage() {
		return null;
	}
}
