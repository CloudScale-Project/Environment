package eu.cloudscaleproject.env.toolchain.util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;

import eu.cloudscaleproject.env.common.CommonResources;
import eu.cloudscaleproject.env.common.ui.GradientComposite;
import eu.cloudscaleproject.env.common.ui.HoverButton;
import eu.cloudscaleproject.env.common.ui.HoverToggleButton;
import eu.cloudscaleproject.env.common.ui.util.ColorHelper;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public abstract class AbstractSidebarEditor implements ISidebarEditor{
	
	private StackLayout stackLayout;
	
	private final Composite compositeSidebar;
	private final Composite compositeArea;
	
	private Composite emptyPanel;

	private Composite compositeSidebarList = null;
	private Composite compositeSidebarControls = null;
	
	private boolean btnNewFromEnabled = true;
	private boolean btnNewEnabled = true;
	private boolean btnRemoveEnabled = true;
				
	protected final LinkedHashMap<IEditorInput, EditorItem> entries 
			= new LinkedHashMap<IEditorInput, EditorItem>();
	
	// user-implemented methods ////////////
	public abstract Composite createInputComposite(IEditorInput input, Composite parent, int style);
	public void handleNewInput(IEditorInput selected){};
	public void handleNewInputFrom(IEditorInput selected){};
	public void handleInputDelete(IEditorInput toDelete){};
	public void handleSelect(IEditorInput selected){};
	//////////////////////////////////////////////////
	
	private class EditorItem{
		
		private final IEditorInput input;
		private final String sectionName;
		
		private HoverToggleButton btnSelect;
		private Composite composite;
		
		private Color color_select;
		private Color color_hover;
		
		public boolean isSelected = false;
		
		//TODO: name change should be handled differently! Fix this. 
		final PropertyChangeListener inputListener = new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg) {
				if("name".equals(arg.getPropertyName()) && btnSelect != null && !btnSelect.isDisposed()){
					btnSelect.setText((String)arg.getNewValue());
					btnSelect.redraw();
				}
			}
		};
		
		public EditorItem(IEditorInput input, String sectionName, int style) {
			this.input = input;
			this.sectionName = sectionName;
			
			if(input instanceof IEditorInputResource){
				((IEditorInputResource)input).load();
			}
			
			checkWidgets();
						
			this.input.addPropertyChangeListener(inputListener);
		}
		
		private void checkWidgets(){
			initButton();
			initComposite();
		}
		
		private void initComposite(){
			if(composite == null || composite.isDisposed()){
				composite = createInputComposite(input, compositeArea, SWT.NONE);
			}
		}
		
		private void initButton(){
			if(btnSelect == null || btnSelect.isDisposed()){
				
				btnSelect = new HoverToggleButton(compositeSidebarList, SWT.NONE);
				Control c = getLastControlInSidebar(sectionName);
				if(c != null){
					btnSelect.moveBelow(c);
				}
				
				btnSelect.setAlignmentHorizontal(SWT.LEFT);
				btnSelect.setIndentHorizontal(10);
				GridData gd_btnSelect = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
				btnSelect.setLayoutData(gd_btnSelect);
				btnSelect.setText(input.getName());
				
				//style
				if(color_select == null || color_select.isDisposed()){
					color_select = ColorHelper.deviateSaturation(getSidebarBackgroundColor(), 0.1);
				}
				if(color_hover == null || color_hover.isDisposed()){
					color_hover = ColorHelper.deviateValue(color_select, 0.1);
				}
				
				btnSelect.setBackground(getSidebarBackgroundColor());
				btnSelect.setBackgroundSelected(color_select);
				btnSelect.setBackgroundHover(color_hover);
				btnSelect.setForeground(getSidebarForegroundColor());
				
				btnSelect.addSelectionListener(new SelectionAdapter() {
					
					@Override
					public void widgetSelected(SelectionEvent e) {
						select();
					}
				});
				
				btnSelect.addDisposeListener(new DisposeListener() {
					
					@Override
					public void widgetDisposed(DisposeEvent e) {
						EditorItem.this.dispose();
					}
				});
			}
		}
		
		public void update() {
			checkWidgets();
			
			composite.update();
			btnSelect.setText(this.input.getName());
			btnSelect.update();
		}
		
		public void select(){
			checkWidgets();
			
			for(EditorItem ei : entries.values()){
				ei.checkWidgets();
				ei.btnSelect.setSelection(false);
				ei.isSelected = false;
			}
			
			update();
			
			btnSelect.setSelection(true);
			stackLayout.topControl = composite;
			isSelected = true;
			
			handleSelect(input);
			
			compositeArea.layout();
		}
		
		public void dispose() {
			input.removePropertyChangeListener(inputListener);
			if(btnSelect != null){
				btnSelect.dispose();
			}
			if(composite != null){
				composite.dispose();
			}
			if(color_hover != null){
				color_hover.dispose();
			}
			if(color_select != null){
				color_select.dispose();	
			}
		}
	}
	
	public AbstractSidebarEditor(Composite sidebar, Composite area) {
		this.compositeSidebar = sidebar;
		this.compositeArea = area;
	}
	
	public void init(){
		
		//dispose composites first
		dispose();
		
		//rebuild side-bar items and area composites
		GridLayout gl_compositSidebar = new GridLayout(1, false);
		gl_compositSidebar.marginTop = 0;
		gl_compositSidebar.marginHeight = 0;
		gl_compositSidebar.marginWidth = 0;
		gl_compositSidebar.verticalSpacing = 0;
		compositeSidebar.setLayout(gl_compositSidebar);
		
		//composite that holds side-bar hover buttons
		compositeSidebarList = new GradientComposite(compositeSidebar, SWT.NONE);
		compositeSidebarList.setBackground(getSidebarBackgroundColor());
		compositeSidebarList.setBounds(10, 10, 129, 280);
		GridLayout gl_compositSidebarList = new GridLayout(1, false);
		gl_compositSidebarList.marginTop = 0;
		gl_compositSidebarList.marginHeight = 0;
		gl_compositSidebarList.marginWidth = 1;
		gl_compositSidebarList.verticalSpacing = 1;
		compositeSidebarList.setLayout(gl_compositSidebarList);
		GridData gd_compositeSidebarList = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_compositeSidebarList.widthHint = 160;
		gd_compositeSidebarList.minimumWidth = 120;
		compositeSidebarList.setLayoutData(gd_compositeSidebarList);
		
		//create or retrieve stack layout of the area composite
		Layout areaLayout = compositeArea.getLayout();
		if(areaLayout == null || !(areaLayout instanceof StackLayout)){
			stackLayout = new StackLayout();
			stackLayout.marginHeight = 0;
			compositeArea.setLayout(stackLayout);
		}
		else{
			stackLayout = (StackLayout)areaLayout;
		}
		
		//create empty panel
		emptyPanel = createEmptyPanel(compositeArea);
		
		//create hover buttons and area composites from IEditorInput objects
		String[] sections = getSidebarSections();
		if(sections != null){
			for(String section : sections){
				createSidebarSection(section, SWT.NONE);
			}
		}
		else{
			createSidebarSection(null, SWT.NONE);
		}
		
		//select the first item or show the empty panel
		{
			Iterator<EditorItem> iter = entries.values().iterator();
			if(iter.hasNext()){
				iter.next().select();
			}
			else{
				stackLayout.topControl = emptyPanel;
			}
		}
		
		//rebuild controls composite
		initControls();

		//layout recreated composites
		if(compositeSidebar != null && !compositeSidebar.isDisposed()){
			compositeSidebar.layout();
		}
		if(compositeArea != null && !compositeArea.isDisposed()){
			compositeArea.layout();
		}
	}
	
	private void initControls(){
		
		if(compositeSidebarList == null || compositeSidebarList.isDisposed()){
			return;
		}
		
		if(compositeSidebarControls != null){
			compositeSidebarControls.dispose();
		}
		
		compositeSidebarControls = new GradientComposite(compositeSidebar, SWT.NONE);
		compositeSidebarControls.setBackground(getSidebarBackgroundColor());
		GridLayout gl_compositeControls = new GridLayout(1, false);
		gl_compositeControls.horizontalSpacing = 0;
		gl_compositeControls.verticalSpacing = 1;
		gl_compositeControls.marginWidth = 1;
		gl_compositeControls.marginHeight = 1;

		compositeSidebarControls.setLayout(gl_compositeControls);
		GridData gd_compositeControls = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_compositeControls.widthHint = 96;
		compositeSidebarControls.setLayoutData(gd_compositeControls);
		
		if(btnNewEnabled){
			HoverButton btnNew = new HoverButton(compositeSidebarControls, SWT.NONE);
			btnNew.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
			btnNew.setText("New");
			btnNew.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					IEditorInput editorInput = null;
					for(EditorItem c : entries.values()){
						if(c.composite.equals(stackLayout.topControl)){
							editorInput = c.input;
						}
					}
					if(editorInput != null){
						handleNewInput(editorInput);
					}
					else{
						handleNewInput(null);
					}
				}
			});
		}
		
		if(btnNewFromEnabled){
			HoverButton btnNewFromSelection = new HoverButton(compositeSidebarControls, SWT.NONE);
			btnNewFromSelection.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
			btnNewFromSelection.setText("New from selection");
			btnNewFromSelection.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					IEditorInput editorInput = null;
					for(EditorItem c : entries.values()){
						if(c.composite.equals(stackLayout.topControl)){
							editorInput = c.input;
						}
					}
					if(editorInput != null){
						handleNewInputFrom(editorInput);
					}
				}
			});
		}
		
		if(btnRemoveEnabled){
			HoverButton btnRemove = new HoverButton(compositeSidebarControls, SWT.NONE);
			btnRemove.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
			btnRemove.setText("Remove");
			btnRemove.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					
					IEditorInput editorInput = null;
					for(EditorItem c : entries.values()){
						if(c.composite.equals(stackLayout.topControl)){
							editorInput = c.input;
						}
					}
					if(editorInput != null){
						handleInputDelete(editorInput);
					}
				}
			});
		}
	}
	
	private Composite createEmptyPanel(Composite parent){
		Composite c = new Composite(parent, SWT.NONE);
		
		FormLayout layout= new FormLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 5;
		c.setLayout(layout);
		
		Label label = new Label(c, SWT.NONE);
		label.setText("Currently there are no entries to display. Create new one?");
		
		FormData lb_formData = new FormData();
		lb_formData.top = new FormAttachment(0,30);
		lb_formData.left = new FormAttachment(15,0);
		label.setLayoutData(lb_formData);
		
		Button button = new Button(c, SWT.NONE);
		button.setText("Create new...");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleNewInput(null);
			}
		});
		
		FormData b_formData = new FormData();
		b_formData.top = new FormAttachment(label, 5);
		b_formData.left = new FormAttachment(15,0);
		button.setLayoutData(b_formData);
		
		return c;
	}
	
	private EditorItem getCurrentSelectionItem(){
		for(EditorItem ei : entries.values()){
			if(ei.isSelected){
				return ei;
			}
		}
		return null;
	}
	
	public int getCurrentSelectionIndex(){
		int i = 0;
		for(EditorItem ei : entries.values()){
			if(ei.isSelected){
				return i;
			}
			i++;
		}
		return -1;
	}
	
	public void setCurrentSelectionIndex(int index){
		int i = 0;
		for(EditorItem ei : entries.values()){
			if(i == index){
				ei.select();
				return;
			}
			i++;
		}
	}
	
	public Composite getCurrentSelection(){
		EditorItem ei = getCurrentSelectionItem();
		if(ei != null){
			return ei.composite;
		}
		return null;
	}
	
	@Override
	public void setNewButtonEnabled(boolean enable){
		this.btnNewEnabled = enable;
		initControls();
	}
	
	@Override
	public void setNewFromButtonEnabled(boolean enabled){
		this.btnNewFromEnabled = enabled;
		initControls();
	}
	
	@Override
	public void setRemoveButtonEnabled(boolean enabled){
		this.btnRemoveEnabled = enabled;
		initControls();
	}
	
	public void showInput(IEditorInput input){
		EditorItem epc = entries.get(input);
		epc.select();
	}
	
	public void addSidebarEditor(IEditorInput ei, String section){
		EditorItem newEditorItem = new EditorItem(ei, section, SWT.NONE);
		entries.put(ei, newEditorItem);
		
		compositeArea.layout(true);
		compositeSidebarList.layout(true);
		newEditorItem.select();
	}
	
	public void removeSidebarEditor(IEditorInput editorInput){
		//select another item
		int selIndex = getCurrentSelectionIndex();
		int newIndex = selIndex > 0 ? selIndex - 1 : selIndex;
				
		EditorItem editorItem = entries.get(editorInput);
		if(editorItem != null){
			editorItem.dispose();
		}
		entries.remove(editorInput);

		compositeArea.layout(true);
		compositeSidebarList.layout(true);
		
		if(newIndex < entries.size()){
			setCurrentSelectionIndex(newIndex);
		}
		else{
			stackLayout.topControl = emptyPanel;
			compositeArea.layout();
		}
	}
	
	public void update(){
		
		//update selection
		EditorItem selectedItem = getCurrentSelectionItem();
		if(selectedItem != null){
			selectedItem.update();
		}
		
		compositeArea.update();
		compositeSidebar.update();
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
	
	private Control getLastControlInSidebar(String section){
		Control out = null;
		boolean inSection = false;
		for(Control c : compositeSidebarList.getChildren()){
			if(c instanceof Label){
				Label l = (Label)c;
				if(l.getText().equals(section)){
					inSection = true;
				}
				else{
					inSection = false;
				}
			}
			if(inSection){
				out = c;
			}
		}
		return out;
	}
	
	private void createSidebarSection(String sectionName, int style){
		
		//generate section label
		if(sectionName != null){
			final GradientComposite c = new GradientComposite(compositeSidebarList, SWT.NONE);
			c.setGradientDirection(false);
			
			c.setGradientColorStart(getSidebarSectionBackgroundColor());
			c.setGradientColorEnd(getSidebarBackgroundColor());
			
			c.setLayout(new GridLayout(1, false));
			GridData gd_c = new GridData(SWT.FILL, SWT.FILL, true, false);
			gd_c.heightHint = 25;
			c.setLayoutData(gd_c);
			
			Label label = new Label(c, SWT.NONE);
			label.setForeground(getSidebarSectionForegroundColor());
			label.setText(sectionName);
			label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		}
		
		//generate section entries
		List<IEditorInput> inputs = getInputs(sectionName);
		if(inputs != null){
			for(IEditorInput input : inputs){
				entries.put(input, new EditorItem(input, sectionName, style));
			}
		}
	}
	
	public void dispose() {
		
		for(EditorItem item : entries.values()){
			item.dispose();
		}
		entries.clear();
		
		if(compositeSidebarList != null && !compositeSidebarList.isDisposed()){
			compositeSidebarList.dispose();
		}
		if(compositeSidebarControls != null && !compositeSidebarControls.isDisposed()){
			compositeSidebarControls.dispose();
		}
		
	}
}

