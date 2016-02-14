package eu.cloudscaleproject.env.spotter.integration.wizard;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.jface.viewers.ILabelProvider;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.ui.views.annotations.AnnotationView;
import org.reclipse.structure.inference.ui.views.annotations.AnnotationsTreeLabelProvider;
import org.spotter.eclipse.ui.UICoreException;
import org.spotter.eclipse.ui.editors.HierarchyEditorInput;
import org.spotter.eclipse.ui.util.SpotterProjectSupport;
import org.spotter.shared.environment.model.XMConfiguration;
import org.spotter.shared.hierarchy.model.XPerformanceProblem;

import eu.cloudscaleproject.env.common.dialogs.DialogUtils;
import eu.cloudscaleproject.env.common.wizard.util.AbstractProjectWizard;
import eu.cloudscaleproject.env.common.wizard.util.ObjectSelectionPage;
import eu.cloudscaleproject.env.spotter.alternatives.ConfigAlternative;
import eu.cloudscaleproject.env.spotter.integration.Activator;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputJob;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeSelectionPage;

public class ImportResultsWizard extends AbstractProjectWizard {

	private AlternativeSelectionPage spotterResultsSelectionPage;
	private AlternativeSelectionPage spotterConfigSelectionPage;
	private ObjectSelectionPage<EObject> antipaternSelectionPage;
	
	private EditorInputFolder spotterResultAlternative = null;
	private EditorInputFolder spotterConfigAlternative = null;
	private List<EObject> selectedAntipatterns = null;
	
	public ImportResultsWizard() {
		
		antipaternSelectionPage = new ObjectSelectionPage<EObject>("Select detected antipatterns", 
				  "Please select the antipatterns to import into the Dynamic Spotter", true, true){
			@Override
			public boolean handleSelectionList(List<EObject> selectedItems) {
				setAntipaterns(selectedItems);
				return true;
			}
			
			@Override
			protected ILabelProvider createLabelProvider() {
				return new AnnotationsTreeLabelProvider(new AnnotationView());
			}
		};
		
		spotterResultsSelectionPage = new AlternativeSelectionPage("Select Static Spotter result alternative", 
																   "Please select the Static spotter result alternative."){
			@Override
			public boolean handleSelection(IEditorInputResource eir) {
				if(eir instanceof EditorInputFolder){
					setSpotterResult((EditorInputFolder)eir);
					return true;
				}
				return false;
			}
		};
		
		spotterConfigSelectionPage = new AlternativeSelectionPage("Select Dynamic Spotter configuration alternative", 
				   												  "Please select the Dynamic spotter configuration alternative."){
			public boolean handleSelection(IEditorInputResource eir) {
				if(eir instanceof EditorInputFolder){
					setSpotterConfig((EditorInputFolder)eir);
					return true;
				}
				return false;
			};
		};
		
	}
	
	public void setSpotterResult(EditorInputFolder eir){
		
		this.spotterResultAlternative = eir;
		
		if(eir instanceof EditorInputEMF){
			EditorInputEMF eie = (EditorInputEMF)eir;
			Resource resource = eie.getModelResource(ToolchainUtils.KEY_FILE_ANTIPATTERNS_PSA);
						
			if(resource != null){

				List<EObject> items = new ArrayList<EObject>();
				
				//Filter synchronized method anti-patterns
				TreeIterator<EObject> iter = EcoreUtil.getAllContents(resource, true);
				while (iter.hasNext()) {
					
					EObject obj = iter.next();
					
					if (obj instanceof ASGAnnotation) {
						final ASGAnnotation annotation = (ASGAnnotation) obj;
						
						if(annotation.getPattern() == null){
							continue;
						}
						
						switch (annotation.getPattern().getName()){
							case "SynchronizedMethod": items.add(annotation); break;
							case "AcquireReleasePair": items.add(annotation); break;
							default: break;
						}
					}
				}
				
				antipaternSelectionPage.setItems(items);
			}
			
		}
	}
	
	public void setSpotterConfig(EditorInputFolder eir){
		this.spotterConfigAlternative = eir;
	}
	
	public void setAntipaterns(List<EObject> antipaterns){
		this.selectedAntipatterns = antipaterns;
	}
	
	@Override
	public void addPages() {
		
		super.addPages();
		
		if(spotterResultAlternative == null){
			addPage(spotterResultsSelectionPage);
		}
		
		if(selectedAntipatterns == null){
			addPage(antipaternSelectionPage);
		}
		
		if(spotterConfigAlternative == null){
			addPage(spotterConfigSelectionPage);
		}
		
	}
	
	public void setProject(IProject project) {
		super.setProject(project);
		
		spotterResultsSelectionPage.setResourceProvider(ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.SPOTTER_STA_RES));
		spotterConfigSelectionPage.setResourceProvider(ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.SPOTTER_DYN_CONF));
	}
	
	@Override
	public boolean performFinish() {
		
		if(spotterConfigAlternative != null && spotterConfigAlternative.isDirty()){
			boolean save = DialogUtils.openConfirm("Save Dynamic Spotter configuration alternative", 
				"Dynamic Spotter configuration alternative needs to be saved before this operation can complete.");
			if(!save){
				return false;
			}
		}
		
		ExportResultsJob job = new ExportResultsJob(spotterConfigAlternative, selectedAntipatterns);
		job.schedule();
		
		return true;
	}
	
	private static class ExportResultsJob extends EditorInputJob{
		
		private EditorInputFolder spotterConfigAlternative = null;
		private List<EObject> selectedAntipatterns = null;

		public ExportResultsJob(EditorInputFolder spotterConfig, List<EObject> antipatterns) {
			super("Static spotter antipatterns export", spotterConfig);
			
			this.spotterConfigAlternative = spotterConfig;
			this.selectedAntipatterns = antipatterns;
		}
		
		@Override
		public IStatus execute(IProgressMonitor monitor) {
			
			monitor.beginTask("Exporting Static spotter results into the Dynamic spotter.", 8);
			
			if(selectedAntipatterns == null || selectedAntipatterns.isEmpty()){
				//DialogUtils.openError("Operation fialed!", "Please select the desired antipaterns and try again.");
				return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Please select the desired antipaterns and try again.");
			}
			
			if(this.spotterConfigAlternative == null){
				//DialogUtils.openError("Operation fialed!", "Please select the desired Dynamic spotter configuration alternative and try again.");
				return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Please select the desired Dynamic spotter configuration alternative and try again.");
			}
			
			if(this.spotterConfigAlternative.isDirty()){
				this.spotterConfigAlternative.save();
			}
			
			//Import Static Spotter results into the Dynamic Spotter Configuration alternative
	        final List<String> scopeSpecifications = getScopeSpecifications(this.selectedAntipatterns);

	        IResource altResource = this.spotterConfigAlternative.getResource();
	        if(altResource instanceof IFolder){
	        	IFolder folder  = (IFolder)altResource;
	        	
	        	IResource file = (IFile)this.spotterConfigAlternative.getSubResource(ToolchainUtils.KEY_FILE_ANTIPATTERNS_SCOPE);
	        	
	        	monitor.worked(1);
	        	
	        	if(file == null || !(file instanceof IFile)){
	        		file = folder.getFile("antipatterns.scope");
	        	}
	        	
	        	StringBuilder out = new StringBuilder();
	    		for(String scopeSpecification : scopeSpecifications){
	    			out.append(scopeSpecification).append("\n");
	    		}
	    		
	        	monitor.worked(1);
	    		
	    		byte[] bytes = out.toString().getBytes();
	    		InputStream is = new ByteArrayInputStream(bytes);
	        	
				try {
					if (!file.exists()) {
						((IFile) file).create(is, IResource.NONE, new NullProgressMonitor());

					} else {
						((IFile) file).setContents(is, false, false, new NullProgressMonitor());
					}
				} 
				catch (CoreException e) {
					e.printStackTrace();
					return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Destination file can not be created!");
				}
				
	        	monitor.worked(1);
				
				//Configure Dynamic spotter
				IResource hierarchyFile = this.spotterConfigAlternative.getSubResource(ConfigAlternative.KEY_HIERARCHY_CONFIG);
				if(hierarchyFile instanceof IFile){
					HierarchyEditorInput hei = new HierarchyEditorInput((IFile)hierarchyFile);
					if (hei.getPerformanceProblemRoot().getProblem() == null)
						hei.getPerformanceProblemRoot().setProblem(new LinkedList<XPerformanceProblem>() );
					
					XPerformanceProblem problem = null;

					for(XPerformanceProblem p : hei.getPerformanceProblemRoot().getProblem()){
						if(Activator.STATIC_SPOTTER_PROBLEM_EXTENSION.equals(p.getExtensionName())){
							problem = p;
						}
					}
					
		        	monitor.worked(1);
					
					if(problem == null){
						
						problem = new XPerformanceProblem();
						problem.setExtensionName(Activator.STATIC_SPOTTER_PROBLEM_EXTENSION);
						
						XMConfiguration detectableConf = new XMConfiguration();
						detectableConf.setKey(Activator.STATIC_SPOTTER_PROBLEM_DETECTIBLE);
						detectableConf.setValue("true");
						
						XMConfiguration fileConf = new XMConfiguration();
						fileConf.setKey(Activator.STATIC_SPOTTER_PROBLEM_FILE);
						fileConf.setValue(file.getLocationURI().getPath().toString());
						
						List<XMConfiguration> confList = new ArrayList<XMConfiguration>();
						confList.add(fileConf);
						confList.add(detectableConf);
						problem.setConfig(confList);
						
						hei.getPerformanceProblemRoot().getProblem().add(problem);
					}
					else{
						for(XMConfiguration conf : problem.getConfig()){
							if(Activator.STATIC_SPOTTER_PROBLEM_FILE.equals(conf.getKey())){
								conf.setValue(file.getFullPath().toString());
							}
						}
					}
					
		        	monitor.worked(1);
					
					try {
						SpotterProjectSupport.saveHierarchy((IFile)hierarchyFile, hei.getPerformanceProblemRoot());
					} catch (UICoreException e) {
						e.printStackTrace();
					}
					
		        	monitor.worked(1);
					
				}
				
				this.spotterConfigAlternative.setSubResource(ToolchainUtils.KEY_FILE_ANTIPATTERNS_SCOPE, file);
				
				// Load alternative instead of save, so the alternative reflects current file-system status.
				// The resources have already been altered and saved in previous steps.
				this.spotterConfigAlternative.load();
				ResourceRegistry.getInstance().openResourceEditor(this.spotterConfigAlternative);
				
	        	monitor.worked(1);
	        }
			
			return Status.OK_STATUS;
		}
		
	}
	
	private static List<String> getScopeSpecifications(List<EObject> antipaterns) {
        final List<String> scopeSpecifications = new ArrayList<>(antipaterns.size());

        for (final Iterator<?> iter = antipaterns.iterator(); iter.hasNext();) {
            final Object node = iter.next();

            if (node instanceof ASGAnnotation) {
                final ASGAnnotation annotation = (ASGAnnotation) node;

                switch (annotation.getPattern().getName()) {
                case "SynchronizedMethod":{
					MethodDeclaration methodDeclaration = (MethodDeclaration) annotation.getBoundObjects().get("method").get(0);
                    scopeSpecifications.add(computeExportString(methodDeclaration));
                    break;
                }
                case "AcquireReleasePair":{
                	ASGAnnotation seff = (ASGAnnotation) annotation.getBoundObjects().get("seff").get(0);
                	MethodDeclaration methodDeclaration = (MethodDeclaration)seff.getBoundObjects().get("containingMethod").get(0);
                    scopeSpecifications.add(computeExportString(methodDeclaration));
                    break;
                }
                default:
                    break;
                }
            }
        }

        return scopeSpecifications;
    }

	private static String computeExportString(final MethodDeclaration methodDeclaration) {
		
        final ClassDeclaration classDeclaration = (ClassDeclaration) methodDeclaration.getAbstractTypeDeclaration();

        final StringBuilder sb = new StringBuilder();
        sb.append(getFullQualifiedName(classDeclaration.getPackage()));
        sb.append(classDeclaration.getName());
        sb.append(".");
        sb.append(methodDeclaration.getName());
        sb.append("*");
        return sb.toString();
    }
	
	private static String getFullQualifiedName(Package pkg) {
        final StringBuilder sb = new StringBuilder();

        while (pkg != null) {
            sb.insert(0, ".");
            sb.insert(0, pkg.getName());
            pkg = pkg.getPackage();
        }

        return sb.toString();
    }
}
