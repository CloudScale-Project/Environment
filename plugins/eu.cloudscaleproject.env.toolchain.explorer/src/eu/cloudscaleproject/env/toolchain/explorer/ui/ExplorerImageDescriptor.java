package eu.cloudscaleproject.env.toolchain.explorer.ui;

import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

public class ExplorerImageDescriptor extends CompositeImageDescriptor{

	private final Image baseImage;
	private final Image overlayImage;
	
	public ExplorerImageDescriptor(Image image, Image overlay) {
		this.baseImage = image;
		this.overlayImage = overlay;
	}
	
	@Override
	protected void drawCompositeImage(int width, int height) {
		drawImage(baseImage.getImageData(), 0, 0);
		drawImage(overlayImage.getImageData(), width-8, height-8);
	}

	@Override
	protected Point getSize() {
		return new Point(16, 16);
	}

}
