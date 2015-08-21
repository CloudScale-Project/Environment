package eu.cloudscaleproject.env.common.ui.util;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ImageHelper {

	/**
	 * Resizes an image, using the given scaling factor. Constructs a new image resource, please take care of resource
	 * disposal if you no longer need the original one. This method is optimized for quality, not for speed.
	 * 
	 * @param image source image
	 * @param scale scale factor (<1 = downscaling, >1 = upscaling)
	 * @return scaled image
	 */
	public static org.eclipse.swt.graphics.Image resize (org.eclipse.swt.graphics.Image image, int width, int height) {
	    int w = image.getBounds().width;
	    int h = image.getBounds().height;

	    if(w == width && h == height){
	    	return image;
	    }
	    
	    // convert to buffered image
	    BufferedImage img = convertToAWT(image.getImageData());

	    // resize buffered image
	    int newWidth = width;
	    int newHeight = height;
	    
	    float scaleX = (float)width/(float)w;
	    float scaleY = (float)height/(float)h;
	    float scaleAvr = (scaleX + scaleY)/2.0f; 

	    // determine scaling mode for best result: if downsizing, use area averaging, if upsizing, use smooth scaling
	    // (usually bilinear).
	    int mode = scaleAvr < 1 ? BufferedImage.SCALE_AREA_AVERAGING : BufferedImage.SCALE_SMOOTH;
	    java.awt.Image scaledImage = img.getScaledInstance(newWidth, newHeight, mode);

	    // convert the scaled image back to a buffered image
	    img = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
	    img.getGraphics().drawImage(scaledImage, 0, 0, null);

	    // reconstruct swt image
	    ImageData imageData = convertToSWT(img);
	    return new org.eclipse.swt.graphics.Image(Display.getDefault(), imageData);
	}

	public static BufferedImage convertToAWT (ImageData data) {
	    ColorModel colorModel = null;
	    PaletteData palette = data.palette;
	    if (palette.isDirect) {
	    	int alphaMask = ~(0x00000000 | palette.redMask | palette.greenMask | palette.blueMask);
	        colorModel = new DirectColorModel(data.depth, palette.redMask, palette.greenMask, palette.blueMask, alphaMask);
	        BufferedImage bufferedImage = new BufferedImage(colorModel, colorModel.createCompatibleWritableRaster(data.width, data.height),
	            false, null);
	        WritableRaster raster = bufferedImage.getRaster();
	        int[] pixelArray = new int[4];
	        for (int y = 0; y < data.height; y++) {
	            for (int x = 0; x < data.width; x++) {
	                int pixel = data.getPixel(x, y);
	                RGB rgb = palette.getRGB(pixel);
	                pixelArray[0] = rgb.red;
	                pixelArray[1] = rgb.green;
	                pixelArray[2] = rgb.blue;
	                pixelArray[3] = data.getAlpha(x, y);
	                raster.setPixels(x, y, 1, 1, pixelArray);
	            }
	        }
	        return bufferedImage;
	    } else {
	        RGB[] rgbs = palette.getRGBs();
	        byte[] red = new byte[rgbs.length];
	        byte[] green = new byte[rgbs.length];
	        byte[] blue = new byte[rgbs.length];
	        for (int i = 0; i < rgbs.length; i++) {
	            RGB rgb = rgbs[i];
	            red[i] = (byte) rgb.red;
	            green[i] = (byte) rgb.green;
	            blue[i] = (byte) rgb.blue;
	        }
	        if (data.transparentPixel != -1) {
	            colorModel = new IndexColorModel(data.depth, rgbs.length, red, green, blue, data.transparentPixel);
	        } else {
	            colorModel = new IndexColorModel(data.depth, rgbs.length, red, green, blue, data.alphaData);
	        }
	        BufferedImage bufferedImage = new BufferedImage(colorModel, colorModel.createCompatibleWritableRaster(data.width, data.height),
	            false, null);
	        WritableRaster raster = bufferedImage.getRaster();
	        int[] pixelArray = new int[1];
	        for (int y = 0; y < data.height; y++) {
	            for (int x = 0; x < data.width; x++) {
	                int pixel = data.getPixel(x, y);
	                pixelArray[0] = pixel | (data.getAlpha(x, y) << 6);
	                raster.setPixel(x, y, pixelArray);
	            }
	        }
	        return bufferedImage;
	    }
	}

	public static ImageData convertToSWT (BufferedImage bufferedImage) {
	    if (bufferedImage.getColorModel() instanceof DirectColorModel) {
	        DirectColorModel colorModel = (DirectColorModel) bufferedImage.getColorModel();
	        PaletteData palette = new PaletteData(colorModel.getRedMask(), colorModel.getGreenMask(), colorModel.getBlueMask());
	        
	        ImageData data = new ImageData(bufferedImage.getWidth(), bufferedImage.getHeight(), colorModel.getPixelSize(), palette);
	        WritableRaster raster = bufferedImage.getRaster();
	        
	        int[] pixelArray = new int[4];
	        for (int y = 0; y < data.height; y++) {
	            for (int x = 0; x < data.width; x++) {
	                raster.getPixel(x, y, pixelArray);
	                int pixel = palette.getPixel(new RGB(pixelArray[0], pixelArray[1], pixelArray[2]));
	                data.setPixel(x, y, pixel);
	                data.setAlpha(x, y, pixelArray[3]);
	            }
	        }
	        return data;
	    } else if (bufferedImage.getColorModel() instanceof IndexColorModel) {
	        IndexColorModel colorModel = (IndexColorModel) bufferedImage.getColorModel();
	        int size = colorModel.getMapSize();
	        byte[] reds = new byte[size];
	        byte[] greens = new byte[size];
	        byte[] blues = new byte[size];
	        colorModel.getReds(reds);
	        colorModel.getGreens(greens);
	        colorModel.getBlues(blues);
	        RGB[] rgbs = new RGB[size];
	        for (int i = 0; i < rgbs.length; i++) {
	            rgbs[i] = new RGB(reds[i] & 0xFF, greens[i] & 0xFF, blues[i] & 0xFF);
	        }
	        PaletteData palette = new PaletteData(rgbs);
	        ImageData data = new ImageData(bufferedImage.getWidth(), bufferedImage.getHeight(), colorModel.getPixelSize(), palette);
	        data.transparentPixel = colorModel.getTransparentPixel();
	        WritableRaster raster = bufferedImage.getRaster();
	        int[] pixelArray = new int[1];
	        for (int y = 0; y < data.height; y++) {
	            for (int x = 0; x < data.width; x++) {
	                raster.getPixel(x, y, pixelArray);
	                data.setPixel(x, y, pixelArray[0]);
	                data.setAlpha(x, y, pixelArray[0] >> 6);
	            }
	        }
	        return data;
	    }
	    return null;
	}
	
}
