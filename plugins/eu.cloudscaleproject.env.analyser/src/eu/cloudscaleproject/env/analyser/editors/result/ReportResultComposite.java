package eu.cloudscaleproject.env.analyser.editors.result;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.palladiosimulator.edp2.local.LocalDirectoryRepository;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentGroup;

import eu.cloudscaleproject.env.analyser.alternatives.ResultAlternative;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;

public abstract class ReportResultComposite extends Composite implements IRefreshable{
	
	private TableViewer tableViewer;
	private MonitorResultsComposite monitorResults;
	
	private final ResultAlternative alternative;
	private Table table;
	private final List<TableRow> tableRows = new ArrayList<TableRow>();
	
	protected static class TableRow{
		
		public final String name;
		public double value;
		
		public TableRow(String name) {
			this.name = name;
		}
	}
	
	protected abstract void fillTable(ExperimentGroup experimentGroup, int counter, List<TableRow> results);
	
	public ReportResultComposite(ResultAlternative alternative, Composite parent, int style) {
		super(parent, style);
		
		this.alternative = alternative;
		setLayout(new GridLayout(1, false));
		
		tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		GridData gd_table = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_table.heightHint = 50;
		table.setLayoutData(gd_table);
		table.setHeaderVisible(true);
	    table.setLinesVisible(true);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnDate = tableViewerColumn.getColumn();
		tblclmnDate.setWidth(200);
		tblclmnDate.setText("Name");
		tableViewerColumn.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				TableRow cr = (TableRow)element;
				return cr.name;
			}
		});
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnCapacity = tableViewerColumn_1.getColumn();
		tblclmnCapacity.setWidth(100);
		tblclmnCapacity.setText("Value");
		tableViewerColumn_1.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				TableRow cr = (TableRow)element;
				return String.valueOf(cr.value);
			}
		});
		
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		tableViewer.setInput(tableRows);
		
		this.monitorResults = new MonitorResultsComposite(alternative, this, SWT.NONE);
		this.monitorResults.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}
	
	@Override
	public void refresh() {
		
		tableRows.clear();
		
		LocalDirectoryRepository ldr = alternative.getEDP2Model();
		EList<ExperimentGroup> egList = ldr.getExperimentGroups();
		
		for(int i=0; i<egList.size(); i++){
			fillTable(egList.get(i), i, tableRows);
		}		
		
		tableViewer.setInput(tableRows);
		monitorResults.refresh();
	}	
}