package eu.cloudscaleproject.env.csm.diagram.wizard;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ISetSelectionTarget;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

import eu.cloudscaleproject.env.csm.diagram.Util;

public class CloudScaleProjectWizard extends BasicNewProjectResourceWizard {
	
	public static final String PROJECT_FILE = "project.properties";
	
	private static final String FOLDER_GENERATED = "Generated models";
	private static final String FOLDER_SCALEDL = "ScaleDL models";
	private static final String FOLDER_IMPORT = "Imported models";
	private static final String FOLDER_RESULTS = "Results";
	
	public static final String KEY_FOLDER_GENERATED = "generated-folder";
	public static final String KEY_FOLDER_SCALEDL = "scaledl-folder";
	public static final String KEY_FOLDER_IMPORT = "imported-folder";
	public static final String KEY_FOLDER_RESULTS = "results-folder";
	
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		
		boolean b = super.performFinish();
		
		if (b && getNewProject() != null)
		{
			try {
				IProject p = getNewProject();
				addCSProjectNature(p);
				
				// Create Project file
				IFile projectFile = p.getFile(PROJECT_FILE);
				Properties prop = createDefaultProperties();
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				prop.store(out, "");
				
				if (!projectFile.exists())
					projectFile.create(new ByteArrayInputStream(out.toByteArray()), b, null);
				
				projectFile.setHidden(true);
				
				IFolder folder = p.getFolder(FOLDER_SCALEDL);
				if (!folder.exists())
					folder.create(true, true, null);
				
				// Create default ScaleDL Overview model and diagram
				String name = p.getName();
				final IFile modelFile = folder.getFile(name+".sdlo");
				final IFile diagramFile = folder.getFile(name+".sdlo_diagram");
				URI modelURI = URI.createPlatformResourceURI(modelFile.getFullPath().toString(), true);
				URI diagramURI = URI.createPlatformResourceURI(diagramFile.getFullPath().toString(), true);
				final Resource diagramResource = Util.createDiagram(diagramURI, modelURI);
				
				// Create Generated models folder
				folder = p.getFolder(FOLDER_GENERATED);
				if (!folder.exists())
					folder.create(true, true, null);
				
				// Create Imported models folder
				folder = p.getFolder(FOLDER_IMPORT);
				if (!folder.exists())
					folder.create(true, true, null);
				
				folder = p.getFolder(FOLDER_RESULTS);
				if (!folder.exists())
					folder.create(true, true, null);
				
				// Open diagram + Select resource in the project explorer
				Display.getDefault().asyncExec(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
				        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				        IViewPart view = page.findView(IPageLayout.ID_PROJECT_EXPLORER);
				        ((ISetSelectionTarget)view).selectReveal(new StructuredSelection(diagramFile));
				        
				        Util.openDiagram(diagramResource);
						
					}
				});
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return b;
	}
	
	private void addCSProjectNature (IProject p)
	{
		try {
		    IProjectDescription desc = p.getDescription();
		    String[] prevNatures = desc.getNatureIds();
		    String[] newNatures = new String[prevNatures.length + 1];
		    System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
		    newNatures[prevNatures.length] = CloudScaleProjectNature.NATURE_ID;
		    desc.setNatureIds(newNatures);
		    p.setDescription(desc, new NullProgressMonitor());
		} catch (CoreException e) {
		    e.printStackTrace();
		}
		
	}
	
	private Properties createDefaultProperties ()
	{
		Properties prop = new Properties();
		prop.setProperty(KEY_FOLDER_GENERATED, FOLDER_GENERATED);
		prop.setProperty(KEY_FOLDER_IMPORT, FOLDER_IMPORT);
		prop.setProperty(KEY_FOLDER_SCALEDL, FOLDER_SCALEDL);
		prop.setProperty(KEY_FOLDER_RESULTS, FOLDER_RESULTS);
		return prop;
	}
	
	
	@Override
	protected void updatePerspective() {
		// TODO: Open CloudScale perspective
	}

}
