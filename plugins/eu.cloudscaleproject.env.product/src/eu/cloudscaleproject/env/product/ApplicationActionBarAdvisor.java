package eu.cloudscaleproject.env.product;

import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;


public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

    // Actions - important to allocate these only in makeActions, and then use them
    // in the fill methods.  This ensures that the actions aren't recreated
    // when fillActionBars is called with FILL_PROXY.
	
	//file
    private IWorkbenchAction exitAction;
    private IWorkbenchAction newAction;
    private IWorkbenchAction saveAction;
    private IWorkbenchAction printAction;
    private IWorkbenchAction saveAllAction;
    private IWorkbenchAction aboutAction;
    private IWorkbenchAction helpAction;
    private IWorkbenchAction newWindowAction;
    
    private IWorkbenchAction importAction;
    private IWorkbenchAction exportAction;
    
    //show
    private IWorkbenchAction quickAction;
    private IContributionItem showViews;

    //edit
    private IWorkbenchAction deleteAction;
    private IWorkbenchAction selectAllAction;
	private IWorkbenchAction undoAction;
	private IWorkbenchAction redoAction;
    
    //print action required in file menu : class: PalladioComponentModelDiagramActionBarContributor
    private static final String ID_MENU_FILE = IWorkbenchActionConstants.M_FILE;
    private static final String ID_MENU_EDIT = IWorkbenchActionConstants.M_EDIT;
    private static final String ID_MENU_TOOLS = "cloudscale.environment.product.menu.tools";
    private static final String ID_MENU_HELP = IWorkbenchActionConstants.M_HELP;
    

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.ui.application.ActionBarAdvisor#makeActions(org.eclipse.ui.IWorkbenchWindow)
     */
    protected void makeActions(final IWorkbenchWindow window) {
        // Creates the actions and registers them.
        // Registering is needed to ensure that key bindings work.
        // The corresponding commands keybindings are defined in the plugin.xml file.
        // Registering also provides automatic disposal of the actions when
        // the window is closed.

    	//file
        exitAction = ActionFactory.QUIT.create(window);
        register(exitAction);
        
        printAction = ActionFactory.PRINT.create(window);
        register(printAction);
        
        newAction = ActionFactory.NEW.create(window);
        newAction.setText("New");
        register(newAction);

        saveAction = ActionFactory.SAVE.create(window);
        register(saveAction);
        
        saveAllAction = ActionFactory.SAVE_ALL.create(window);
        register(saveAllAction);
        
        aboutAction = ActionFactory.ABOUT.create(window);
        register(aboutAction);
        
        helpAction = ActionFactory.HELP_CONTENTS.create(window);
        register(helpAction);
        
        newWindowAction = ActionFactory.OPEN_NEW_WINDOW.create(window);
        register(newWindowAction);
        
        importAction = ActionFactory.IMPORT.create(window);
        register(importAction);
        
        exportAction = ActionFactory.EXPORT.create(window);
        register(exportAction);
        
        //show
        quickAction = ActionFactory.SHOW_QUICK_ACCESS.create(window);
        register(quickAction);
        
        showViews = ContributionItemFactory.VIEWS_SHORTLIST.create(window);
        
        //edit
        selectAllAction = ActionFactory.SELECT_ALL.create(window);
        register(selectAllAction);
        
        deleteAction = ActionFactory.DELETE.create(window);
        register(deleteAction);
        
        undoAction = ActionFactory.UNDO.create(window);
        register(deleteAction);
        redoAction = ActionFactory.REDO.create(window);
        register(deleteAction);
        
        
    }
    
    protected void fillMenuBar(IMenuManager menuBar) {
    	
        MenuManager fileMenu = new MenuManager("&File", ID_MENU_FILE);
        
        MenuManager editMenu = new MenuManager("&Edit", ID_MENU_EDIT);
        editMenu.add(new GroupMarker(IWorkbenchActionConstants.FIND_EXT));
        
        MenuManager toolsMenu = new MenuManager("&Tools", ID_MENU_TOOLS);
        MenuManager helpMenu = new MenuManager("&Help", ID_MENU_HELP);
        
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(toolsMenu);
        // Add a group marker indicating where action set menus will appear.
        menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
        menuBar.add(helpMenu);
        
        // File
        
        //printAction.setEnabled(false);
        
        fileMenu.add(newAction);
        fileMenu.add(new Separator());
        fileMenu.add(saveAction);
        fileMenu.add(saveAllAction);
        fileMenu.add(printAction);
        fileMenu.add(new Separator());
        fileMenu.add(importAction);
        fileMenu.add(exportAction);
        fileMenu.add(new Separator());
        fileMenu.add(quickAction);
        fileMenu.add(new Separator());
        fileMenu.add(exitAction);
        
        editMenu.add(undoAction);
        editMenu.add(redoAction);
        editMenu.add(selectAllAction);
        editMenu.add(deleteAction);

        
        //tools
        //view
        showViews.update();
        
        // Help
        helpMenu.add(aboutAction);
        helpMenu.add(helpAction);
    }
    
    protected void fillCoolBar(ICoolBarManager coolBar) {
    	/*
    	coolBar.setLockLayout(false);
        
    	IToolBarManager toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
        coolBar.add(new ToolBarContributionItem(toolbar, "eu.cloudscaleproject.env.product.toolbar.toolchain"));
        
        coolBar.update(true);
        */
    }
}
