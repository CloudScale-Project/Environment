package org.scaledl.overview.diagram;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import eu.cloudscaleproject.env.common.CloudScaleConstants;

public class ExplorerDecorator extends LabelProvider implements ILabelDecorator {

	private static final String PLUGIN_ID = "org.scaledl.overview.diagram";
	private static final String ICON_PATH = "icons/explorer/";

	private static final String CSM_DIAGRAM_ICON = ICON_PATH + "csm_diagram.png";
	private static final String CSM_MODEL_ICON = ICON_PATH + "csm_model.png";

	@Override
	public Image decorateImage(Image image, Object element) {

		if (!(element instanceof IResource)) return image;

		IProject p = ((IResource)element).getProject();

		if (isCSEProject(p))
		{
			if (element instanceof IFile)
			{
				IFile f = (IFile) element;
				return getFileImage(f);
			}
		}

		return image;
	}

	@Override
	public String decorateText(String text, Object element) {
		if (element instanceof IFile)
		{
			if (text.endsWith(".sdlo"))
			{
				text = text + " - Overview model";
			}
			else if (text.endsWith(".sdlo_diagram"))
			{
				text = text + " - Overview diagram";
			}
			return text;
		}

		return null;
	}

	private boolean isCSEProject (IProject p)
	{
		try {
			if(p.isOpen()){
				return p.getDescription().hasNature(CloudScaleConstants.PROJECT_NATURE_ID);
			}
			else{
				return false;
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}

		return false;
	}

	private Image getFileImage (IFile f)
	{
		if (f.getFileExtension().equals("sdlo"))
			return getIcon(CSM_MODEL_ICON);
		if (f.getFileExtension().equals("sdlo_diagram"))
			return getIcon(CSM_DIAGRAM_ICON);

		return null;
	}

	private Image getIcon (String icon)
	{
		Image img = AbstractUIPlugin
				.imageDescriptorFromPlugin(PLUGIN_ID, icon)
				.createImage(); 
		return img;
	}
}
