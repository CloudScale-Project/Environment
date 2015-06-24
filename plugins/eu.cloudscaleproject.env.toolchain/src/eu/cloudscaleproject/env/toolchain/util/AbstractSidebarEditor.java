package eu.cloudscaleproject.env.toolchain.util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.common.ColorResources;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.common.ui.GradientComposite;
import eu.cloudscaleproject.env.common.ui.HoverButton;
import eu.cloudscaleproject.env.common.ui.HoverToggleButton;
import eu.cloudscaleproject.env.common.ui.util.ColorHelper;
import eu.cloudscaleproject.env.toolchain.IDirtyAdapter;
import eu.cloudscaleproject.env.toolchain.IPropertySheetPageProvider;
import eu.cloudscaleproject.env.toolchain.ProjectEditorSelectionService;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public abstract class AbstractSidebarEditor implements ISidebarEditor{
	
	//private static final Logger logger = Logger.getLogger(AbstractSidebarEditor.class.getName());
	
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
	
	public class EditorItem implements IPropertySheetPageProvider{
		
		private final IEditorInput input;
		private final String sectionName;
		
		private HoverToggleButton btnSelect;
		private Composite composite;
				
		private Color color_select;
		private Color color_hover;
		
		public boolean isSelected = false;
				
		final PropertyChangeListener eirChangeListener = new PropertyChangeListener() {
					
			@Override
			public void propertyChange(final PropertyChangeEvent evt) {
				
				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						resourceChanged(evt);
					};
				});

				// When non GUI thread => WorkbenchWidow == null
                if (Display.getDefault().getThread() != Thread.currentThread()) return;
				IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();

				if(editor != null){
					IDirtyAdapter dirtyAdapter = (IDirtyAdapter)editor.getAdapter(IDirtyAdapter.class);
					if(dirtyAdapter != null){
						dirtyAdapter.fireDirtyState();
					}
				}
			}
		};
		
		public EditorItem(IEditorInput input, String sectionName, int style) {
			this.input = input;
			this.sectionName = sectionName;
			
			initialize();
			
			this.input.addPropertyChangeListener(eirChangeListener);
			EditorRegistry.getInstance().registerEditorItem(AbstractSidebarEditor.this, EditorItem.this);
		}
		
		public void resourceChanged(PropertyChangeEvent evt){
			
			if(composite == null || composite.isDisposed()){
				return;
			}
			if(btnSelect == null || btnSelect.isDisposed()){
				return;
			}
			
			if (IEditorInputResource.PROP_LOADED.equals(evt.getPropertyName()))
			{
				btnSelect.setText(input.getName());
				btnSelect.redraw();
				
				if(composite instanceof IRefreshable){
					((IRefreshable)composite).refresh();
				}
			}
			
			if(IEditorInputResource.PROP_NAME.equals(evt.getPropertyName())){
				btnSelect.setText((String)evt.getNewValue());
				btnSelect.redraw();
			}
		}
		
		public IEditorInput getInput()
		{
			return input;
		}

		public Composite getComposite()
		{
			return composite;
		}
		
		private List<ISaveable> getSaveables(){
			List<ISaveable> out = new ArrayList<>();
			collectSaveableComposites(out, composite);
			return out;
		}
		
		public boolean isDirty(){
			if(this.input instanceof IEditorInputResource){
				IEditorInputResource res = (IEditorInputResource)input;
				return res.isDirty();
			}
			for(ISaveable s : getSaveables()){
				if(s.isDirty()){
					return true;
				}
			}
			return false;
		}
		
		private void collectSaveableComposites(List<ISaveable> composites, Composite c){
			
			if(c == null || c.isDisposed()){
				return;
			}
			
			if(c instanceof ISaveable){
				composites.add((ISaveable)c);
				return;
			}
			
			for(Control control : c.getChildren()){
				if(control instanceof Composite){
					collectSaveableComposites(composites, (Composite)control);
				}
			}
		}
		
		public void save(){
			
			BusyIndicator.showWhile(Display.getDefault(), new Runnable() {

				@Override
				public void run() {
					if(input instanceof IEditorInputResource){
						IEditorInputResource res = (IEditorInputResource)input;
						if(res.isDirty()){
							res.save();
						}
					}
					
					for(ISaveable s : getSaveables()){
						if(s.isDirty()){
							s.save();
						}
					}
				}
			});
		}
		
		public void load(final boolean force){
			
			BusyIndicator.showWhile(Display.getDefault(), new Runnable() {

				@Override
				public void run() {
					if(input instanceof IEditorInputResource){
						
						if(composite != null && !composite.isDisposed()){
							composite.dispose();
						}
						
						IEditorInputResource res = (IEditorInputResource)input;
						synchronized (res) {
							if(!res.isLoaded() || force){
								res.load();
							}
						}
						
						/*
						if(isSelected){
							select();
						}
						*/
					}
				}
			});
			
		}
		
		private void initialize(){
			BusyIndicator.showWhile(Display.getDefault(), new Runnable() {
				
				@Override
				public void run() {
					load(false);
				}
			});
			
			initButton();
		}
		
		private void checkWidgets(){
			initButton();
			initComposite();
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
		
		private void initComposite(){
			if(composite == null || composite.isDisposed()){
				composite = createInputComposite(input, compositeArea, SWT.NONE);
			}
		}
		
		public void update() {
			checkWidgets();
			
			if(composite instanceof IRefreshable){
				((IRefreshable)composite).refresh(); 
			}
			btnSelect.setText(this.input.getName());
			btnSelect.update();
		}
		
		public void select(){
			checkWidgets();
			
			for(EditorItem ei : entries.values()){
				ei.initButton();
				ei.btnSelect.setSelection(false);
				ei.isSelected = false;
			}
			
			btnSelect.setSelection(true);
			stackLayout.topControl = composite;
			isSelected = true;
			
			update();

			handleSelect(input);
			
			compositeArea.layout();
			composite.setFocus();
			
			ProjectEditorSelectionService.getInstance().reloadPropertySheetPage();
			
			if(composite instanceof ISelectable){
				((ISelectable)composite).onSelect(); 
			}
		}
		
		public void dispose() {
			input.removePropertyChangeListener(eirChangeListener);
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

		@Override
		public IPropertySheetPage getPropertySheetPage() {
			if(composite instanceof IPropertySheetPageProvider){
				IPropertySheetPageProvider propProvider = (IPropertySheetPageProvider)composite;
				return propProvider.getPropertySheetPage();
			}
			return null;
		}
	}
	
	public AbstractSidebarEditor(Composite sidebar, Composite area) {
		this.compositeSidebar = sidebar;
		this.compositeArea = area;
		
		EditorRegistry.getInstance().registerEditor(this);
	}

	public Map<IEditorInput, EditorItem> getEntries()
	{
		return entries;
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
			btnNew.setText("Create");
			btnNew.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					EditorItem selected = getCurrentSelectionItem();
					if(selected != null){
						handleNewInput(selected.input);
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
			btnNewFromSelection.setText("Clone");
			btnNewFromSelection.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					EditorItem selected = getCurrentSelectionItem();
					if(selected != null){
						handleNewInputFrom(selected.input);
					}
				}
			});
		}
		
		if(btnRemoveEnabled){
			HoverButton btnRemove = new HoverButton(compositeSidebarControls, SWT.NONE);
			btnRemove.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
			btnRemove.setText("Delete");
			btnRemove.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					EditorItem selected = getCurrentSelectionItem();
					if(selected != null){
						handleInputDelete(selected.input);
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
	
	public EditorItem getCurrentSelectionItem(){
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
		if(epc == null){
			return;
		}
		epc.select();
	}
	
	public void addSidebarEditor(IEditorInput ei, String section){

		// Workaround - entry can already exist - thread fuck
		if (entries.get(ei) != null) return;

		EditorItem newEditorItem = new EditorItem(ei, section, SWT.NONE);
		entries.put(ei, newEditorItem);
		
		compositeArea.redraw();
		compositeArea.layout(true);
		
		compositeSidebarList.redraw();
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
		else{
			//editor item already removed
			return;
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
	
	public void save(){
		for(EditorItem ei : entries.values()){
			ei.save();
		}
	}
	
	public void load(boolean force){
		EditorItem ei = getCurrentSelectionItem();
		if(ei != null){
			ei.load(force);
		}
	}
	
	public boolean isDirty(){
		for(EditorItem ei : entries.values()){
			if(ei.isDirty()){
				return true;
			}
		}
		return false;
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
		return ColorResources.COLOR_CS_BLUE;
	}
	
	public Color getSidebarSectionForegroundColor(){
		return ColorResources.COLOR_BLACK;
	}
	
	public Color getSidebarBackgroundColor(){
		return ColorResources.COLOR_CS_BLUE_LIGHT;
	}
	
	public Color getSidebarForegroundColor(){
		return ColorResources.COLOR_CS_BLUE_DARK;
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

	@Override
	public IPropertySheetPage getPropertySheetPage() {
		EditorItem ei = getCurrentSelectionItem();
		if(ei != null){
			return ei.getPropertySheetPage();
		}
		return null;
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

