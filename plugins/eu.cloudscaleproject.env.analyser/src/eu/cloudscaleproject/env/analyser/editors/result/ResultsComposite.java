package eu.cloudscaleproject.env.analyser.editors.result;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.analyser.alternatives.ResultAlternative;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.toolchain.ui.TitleEditorView;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ResultsComposite extends TitleEditorView implements IRefreshable, ISelectable{

	private final ResultAlternative alternative;
	
	private CTabFolder tabFolder;
	
	private Composite basicResultComposite;
	private AdvancedResultComposite advancedResultComposite;
	
	public ResultsComposite(ResultAlternative alt, Composite parent, int style) {
		super(parent, style, alt);
		
		this.alternative = alt;
		
		getContainer().setLayout(new FillLayout());
		
		this.tabFolder = new CTabFolder(getContainer(), SWT.BORDER);
		tabFolder.setTabHeight(32);
		
		CTabItem tabBasicResults = new CTabItem(tabFolder, SWT.NONE);
		if(alternative.getTypeEnum().equals(ConfAlternative.Type.NORMAL)){
			basicResultComposite = new MonitorResultsComposite(alternative, tabFolder, SWT.NONE);
		}
		else if(alternative.getTypeEnum().equals(ConfAlternative.Type.CAPACITY)){
			basicResultComposite = new CapacityResultComposite(alternative, tabFolder, SWT.NONE);
		}
		else if(alternative.getTypeEnum().equals(ConfAlternative.Type.SCALABILITY)){
			basicResultComposite = new ScalabilityResultComposite(alternative, tabFolder, SWT.NONE);
		}
		tabBasicResults.setText("Results View");
		tabBasicResults.setControl(basicResultComposite);
		
		CTabItem tabAdvancedResults = new CTabItem(tabFolder, SWT.NONE);
		advancedResultComposite = new AdvancedResultComposite(alternative, tabFolder, SWT.NONE);
		tabAdvancedResults.setText("EDP2 Results View");
		tabAdvancedResults.setControl(advancedResultComposite);
		
		tabFolder.setSelection(tabBasicResults);
		
		refresh();
		layout();
	}
	
	@Override
	public void onSelect() {
	}
	
	@Override
	public void refresh() {
		if(basicResultComposite instanceof IRefreshable){
			((IRefreshable)basicResultComposite).refresh();
		}
		advancedResultComposite.refresh();
	}
	
}