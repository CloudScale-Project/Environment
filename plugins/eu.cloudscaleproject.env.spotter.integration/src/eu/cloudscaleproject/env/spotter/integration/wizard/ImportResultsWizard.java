package eu.cloudscaleproject.env.spotter.integration.wizard;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.jface.viewers.ILabelProvider;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.annotations.util.AnnotationsAdapterFactory;
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
				  "Please select the antipatterns to import into the Dynamic Spotter", true){
			@Override
			public boolean handleSelectionList(List<EObject> selectedItems) {
				setAntipaterns(selectedItems);
				return true;
			}
			
			@Override
			protected ILabelProvider createLabelProvider() {
				return new AdapterFactoryLabelProvider(new AnnotationsAdapterFactory());
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
			EObject root = eie.getModelRootObject(ToolchainUtils.KEY_FILE_ANTIPATTERNS_PSA);
			
			//TODO: filter applicable anti-patterns
			
			if(root != null){
				antipaternSelectionPage.setItems(root.eContents());
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
		
		if(selectedAntipatterns == null || selectedAntipatterns.isEmpty()){
			DialogUtils.openError("Operation fialed!", "Please select the desired antipaterns and try again.");
			return false;
		}
		
		if(this.spotterConfigAlternative == null){
			DialogUtils.openError("Operation fialed!", "Please select the desired Dynamic spotter configuration alternative and try again.");
			return false;
		}
		
		//Import Static Spotter results into the Dynamic Spotter Configuration alternative
        final List<String> scopeSpecifications = getScopeSpecifications(this.selectedAntipatterns);

        IResource res = this.spotterConfigAlternative.getResource();
        if(res instanceof IFolder){
        	IFolder folder  = (IFolder)res;
        	
        	IResource file = (IFile)this.spotterConfigAlternative.getSubResource(ToolchainUtils.KEY_FILE_ANTIPATTERNS_SCOPE);
        	        	
        	if(file == null || !(file instanceof IFile)){
        		file = folder.getFile("antipatterns.scope");
        	}
        	
        	StringBuilder out = new StringBuilder();
    		for(String scopeSpecification : scopeSpecifications){
    			out.append(scopeSpecification).append("\n");
    		}
    		
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
				return false;
			}
			
			//TODO: Configure Dynamic spotter
			IResource hierarchyFile = this.spotterConfigAlternative.getSubResource(ConfigAlternative.KEY_HIERARCHY_CONFIG);
			if(hierarchyFile instanceof IFile){
				HierarchyEditorInput hei = new HierarchyEditorInput((IFile)hierarchyFile);
				
				XPerformanceProblem problem = null;
				
				for(XPerformanceProblem p : hei.getPerformanceProblemRoot().getProblem()){
					if(Activator.STATIC_SPOTTER_PROBLEM_EXTENSION.equals(p.getExtensionName())){
						problem = p;
					}
				}
				
				if(problem == null){
					
					problem = new XPerformanceProblem();
					problem.setExtensionName(Activator.STATIC_SPOTTER_PROBLEM_EXTENSION);
					
					XMConfiguration detectableConf = new XMConfiguration();
					detectableConf.setKey(Activator.STATIC_SPOTTER_PROBLEM_DETECTIBLE);
					detectableConf.setValue("true");
					
					XMConfiguration fileConf = new XMConfiguration();
					fileConf.setKey(Activator.STATIC_SPOTTER_PROBLEM_FILE);
					fileConf.setValue(file.getFullPath().toString());
					
					problem.getConfig().add(detectableConf);
					problem.getConfig().add(fileConf);
					hei.getPerformanceProblemRoot().getProblem().add(problem);
				}
				else{
					for(XMConfiguration conf : problem.getConfig()){
						if(Activator.STATIC_SPOTTER_PROBLEM_FILE.equals(conf.getKey())){
							conf.setValue(file.getFullPath().toString());
						}
					}
				}
				
				try {
					SpotterProjectSupport.saveHierarchy((IFile)hierarchyFile, hei.getPerformanceProblemRoot());
				} catch (UICoreException e) {
					e.printStackTrace();
				}
				
			}
			
			this.spotterConfigAlternative.setSubResource(ToolchainUtils.KEY_FILE_ANTIPATTERNS_SCOPE, file);
        }
		
		return true;
	}
	
	private static List<String> getScopeSpecifications(List<EObject> antipaterns) {
        final List<String> scopeSpecifications = new ArrayList<>(antipaterns.size());

        for (final Iterator<?> iter = antipaterns.iterator(); iter.hasNext();) {
            final Object node = iter.next();

            if (node instanceof ASGAnnotation) {
                final ASGAnnotation annotation = (ASGAnnotation) node;

                switch (annotation.getPattern().getName()) {
                case "SynchronizedMethod":
                    scopeSpecifications.add(computeExportString(annotation));
                    break;
                default:
                    break;
                }
            }
        }

        return scopeSpecifications;
    }

	private static String computeExportString(final ASGAnnotation annotation) {
		
        final MethodDeclaration methodDeclaration = (MethodDeclaration) annotation.getBoundObjects().get("method").get(0);
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
