package eu.cloudscaleproject.env.analyser.editors.config;

import java.text.ParseException;

import javax.measure.Measure;
import javax.measure.MeasureFormat;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.uml2.common.edit.command.ChangeCommand;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjective;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectiveFactory;
import org.palladiosimulator.servicelevelobjective.Threshold;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.common.CommonResources;
import eu.cloudscaleproject.env.common.emf.EObjectWrapper;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;

public class ConfigSLOComposite extends Composite implements IRefreshable{
	
	private final String DEFAULT_UPPER_BOUND = "10";
	private final String DEFAULT_LOWER_BOUND = "1";
	
	private final ConfAlternative alternative;
	
	private final ServiceLevelObjective slo;
	private final ComboViewer comboMeasurementSpecViewer;
	
		
	private Text textUpperBound;
	private Text textLowerBound;
	
	private Button btnUpperBound;
	private Button btnLowerBound;
	
	private ControlDecoration textUpperDecoration;
	private ControlDecoration textLowerDecoration;
	
	private Label lblNewLabel;
	
	private boolean refreshInProfress = false;
	
	public ConfigSLOComposite(final ConfAlternative alt, final EObjectWrapper<ServiceLevelObjective> sloWrapper, Composite parent, int style) {
		super(parent, style);
		
		this.alternative = alt;
		this.slo = sloWrapper.getMaster();
		
		setLayout(new GridLayout(2, false));
		
		lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setText("Measurement specification:");
		
		comboMeasurementSpecViewer = new ComboViewer(this, SWT.NONE);
		comboMeasurementSpecViewer.setContentProvider(new ArrayContentProvider());
		comboMeasurementSpecViewer.setInput(alternative.getActiveMeasurementSpecifications());

		Combo comboMeasuremntSpec = comboMeasurementSpecViewer.getCombo();
		GridData gd_comboMeasuremntSpec = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_comboMeasuremntSpec.widthHint = 320;
		comboMeasuremntSpec.setLayoutData(gd_comboMeasuremntSpec);
		
		btnUpperBound = new Button(this, SWT.CHECK);
		btnUpperBound.setText("Upper bound:");
		btnUpperBound.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(refreshInProfress){
					return;
				}
				
				CommandStack cs = alt.getEditingDomain().getCommandStack();
				cs.execute(new ChangeCommand(alt.getEditingDomain(), new Runnable() {
					
					@Override
					public void run() {
						ServiceLevelObjective slo = sloWrapper.getMaster();
						
						if(btnUpperBound.getSelection()){					
							Threshold tr = ServicelevelObjectiveFactory.eINSTANCE.createHardThreshold();
							slo.setUpperThreshold(tr);

							textUpperBound.setText(DEFAULT_UPPER_BOUND);
							textUpperBound.setEnabled(true);
						}
						else{
							textUpperBound.setText("");
							textUpperBound.setEnabled(false);
							slo.setUpperThreshold(null);
						}
					}
				}));
				
				super.widgetSelected(e);
			}
		});
		
		textUpperBound = new Text(this, SWT.BORDER);
		GridData gd_textUpperBound = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_textUpperBound.widthHint = 100;
		textUpperBound.setLayoutData(gd_textUpperBound);
		textUpperBound.addModifyListener(new ModifyListener() {
			
			@SuppressWarnings("rawtypes")
			@Override
			public void modifyText(ModifyEvent e) {
				if(refreshInProfress){
					return;
				}
				
				CommandStack cs = alt.getEditingDomain().getCommandStack();
				cs.execute(new ChangeCommand(alt.getEditingDomain(), new Runnable() {
					@Override
					public void run() {
						String value = textUpperBound.getText();
						ServiceLevelObjective slo = sloWrapper.getMaster();
						
						try {
							slo.getUpperThreshold().setThresholdLimit((Measure)MeasureFormat.getInstance().parseObject(value));
						} catch (ParseException e1) {
							textUpperDecoration.show();
							return;
						}
						textUpperDecoration.hide();
					}
				}));

			}
		});
		
		btnLowerBound = new Button(this, SWT.CHECK);
		btnLowerBound.setText("Lower bound:");
		btnLowerBound.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(refreshInProfress){
					return;
				}
				
				CommandStack cs = alt.getEditingDomain().getCommandStack();
				cs.execute(new ChangeCommand(alt.getEditingDomain(), new Runnable() {
					
					@Override
					public void run() {
						ServiceLevelObjective slo = sloWrapper.getMaster();
						
						if(btnLowerBound.getSelection()){
							Threshold tr = ServicelevelObjectiveFactory.eINSTANCE.createHardThreshold();
							slo.setLowerThreshold(tr);

							textLowerBound.setText(DEFAULT_LOWER_BOUND);
							textLowerBound.setEnabled(true);
						}
						else{
							textLowerBound.setText("");
							textLowerBound.setEnabled(false);
							slo.setLowerThreshold(null);
						}
					}
				}));
				
				super.widgetSelected(e);
			}
		});
		
		textLowerBound = new Text(this, SWT.BORDER);
		GridData gd_textLowerBound = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_textLowerBound.widthHint = 100;
		textLowerBound.setLayoutData(gd_textLowerBound);
		textLowerBound.addModifyListener(new ModifyListener() {
			
			@SuppressWarnings("rawtypes")
			@Override
			public void modifyText(ModifyEvent e) {
				if(refreshInProfress){
					return;
				}
				
				CommandStack cs = alt.getEditingDomain().getCommandStack();
				cs.execute(new ChangeCommand(alt.getEditingDomain(), new Runnable() {
					@Override
					public void run() {
						String value = textLowerBound.getText();
						ServiceLevelObjective slo = sloWrapper.getMaster();
						
						try {
							slo.getLowerThreshold().setThresholdLimit((Measure)MeasureFormat.getInstance().parseObject(value));
						} catch (ParseException e1) {
							textLowerDecoration.show();
							return;
						}
						textLowerDecoration.hide();
					}
				}));
					
			}
		});
		
		textUpperDecoration = new ControlDecoration(textUpperBound,  SWT.TOP | SWT.LEFT);
		textUpperDecoration.setImage(CommonResources.ERROR);
		textLowerDecoration = new ControlDecoration(textLowerBound,  SWT.TOP | SWT.LEFT);
		textLowerDecoration.setImage(CommonResources.ERROR);
	}
	
	public String getSLOName(){
		String entityName = slo.getName() == null ? "No name" : slo.getName();
		return entityName;
	}
	
	public void refresh(){
		
		try{
			refreshInProfress = true;
			
			//update combo-box
			if(comboMeasurementSpecViewer != null){
				comboMeasurementSpecViewer.setInput(alternative.getActiveMeasurementSpecifications());
			}
			
			//update lower bound
			Threshold lowerThreshold = this.slo.getLowerThreshold();
			if(lowerThreshold != null){
				btnLowerBound.setSelection(true);
				textLowerBound.setEnabled(true);
				if(lowerThreshold.getThresholdLimit() != null){
					textLowerBound.setText(MeasureFormat.getInstance().format(lowerThreshold.getThresholdLimit()));
				}
				else{
					textLowerBound.setText("");
				}
			}
			else{
				btnLowerBound.setSelection(false);
				textLowerBound.setEnabled(false);
				textLowerBound.setText("");
			}
			
			//update upper bound
			Threshold upperThreshold = this.slo.getUpperThreshold();
			if(upperThreshold != null){
				btnUpperBound.setSelection(true);
				textUpperBound.setEnabled(true);
				
				if(upperThreshold.getThresholdLimit() != null){
					textUpperBound.setText(MeasureFormat.getInstance().format(upperThreshold.getThresholdLimit()));
				}
				else{
					textUpperBound.setText("");
				}				
			}
			else{
				btnUpperBound.setSelection(false);
				textUpperBound.setEnabled(false);
				textUpperBound.setText("");
			}
		}
		finally{
			refreshInProfress = false;
		}
		
		layout();
		redraw();
	}
}
