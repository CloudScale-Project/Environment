package eu.cloudscaleproject.env.common.ui.util;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class ColorHelper {

	/**
	 * Interpolate two colors by the specified factor.
	 * If 'fac' is equal to 1, this method will return new color with color values equal to 'c2'.
	 * 
	 * @param c1 - First color
	 * @param c2 - Second color
	 * @param fac - Interpolation factor.
	 * @return - New color.
	 */
	public static Color interpolate(Color c1, Color c2, double fac){
		double r = (1.0-fac)*((double)c1.getRed()) + fac*((double)c2.getRed());
		double g = (1.0-fac)*((double)c1.getGreen()) + fac*((double)c2.getGreen());
		double b = (1.0-fac)*((double)c1.getBlue()) + fac*((double)c2.getBlue());

		return new Color(Display.getDefault(), (int)r, (int)g, (int)b);
	}
	
	/**
	 * Deviates 'HSV' color saturation by the specified value.
	 * Color saturation is in range from 0 to 1. Returned color saturation is base color saturation + value.
	 * 
	 * @param color - Base color
	 * @param value - value by which to increase/decrease the base color saturation. Saturation is value in range [0-1].
	 * @return 		- New color (must be disposed).
	 */
	public static Color deviateSaturation(Color color, double value){
		double[] hsv = rgbToHsv(color.getRed(), color.getGreen(), color.getBlue());
		
		hsv[1] += value;
		
		//clamp value
		hsv[1] = Math.min(hsv[1], 1.0);
		hsv[1] = Math.max(hsv[1], 0.0);
		
		int[] rgb = hsvToRgb(hsv);
		
		return new Color(Display.getDefault(), rgb[0], rgb[1], rgb[2]);
	}
	
	/**
	 * Deviates 'HSV' color value by the specified value.
	 * Color value is in range from 0 to 1. Returned color value is base color value + value.
	 * 
	 * @param color - Base color
	 * @param value - value by which to increase/decrease the base color value. 'HSV' value is in range [0-1].
	 * @return 		- New color (must be disposed).
	 */
	public static Color deviateValue(Color color, double value){
		double[] hsv = rgbToHsv(color.getRed(), color.getGreen(), color.getBlue());
		
		hsv[2] += value;
		
		//clamp value
		hsv[2] = Math.min(hsv[2], 1.0);
		hsv[2] = Math.max(hsv[2], 0.0);
		
		int[] rgb = hsvToRgb(hsv);
		
		return new Color(Display.getDefault(), rgb[0], rgb[1], rgb[2]);
	}
	
	/**
	 * Converts an RGB color value [0-255] to HSV [0-1].
	 *
	 * @param   Number  r       The red color value
	 * @param   Number  g       The green color value
	 * @param   Number  b       The blue color value
	 * @return  double[]        The HSV representation
	 */
	public static double[] rgbToHsv(int red, int green, int blue){
	    double r = (double)red/255.0;
	    double g = (double)green/255.0;
	    double b = (double)blue/255.0;
	    
	    double max = Math.max(Math.max(r, g), b);
	    double min = Math.min(Math.min(r, g), b);
	    
	    double h = 0;
	    double s = 0; 
	    double v = max;
	    
	    double d = max - min;
	    
	    s = max == 0 ? 0 : d / max;

	    if(max == min){
	        h = 0; // achromatic
	    }
	    else{
	    	if(max == r){
	    		h = (g - b) / d + (g < b ? 6 : 0);
	    	}
	    	else if(max == g){
	    		h = (b - r) / d + 2;
	    	}
	    	else if(max == b){
	    		h = (r - g) / d + 4;
	    	}
	        h /= 6;
	    }

	    return new double[]{h, s, v};
	}

	/**
	 * Converts an HSV color value [0-1] to RGB [0-255]. 
	 *
	 * @param   Number  h       The hue
	 * @param   Number  s       The saturation
	 * @param   Number  v       The value
	 * @return  Array           The RGB representation
	 */
	public static int[] hsvToRgb(double [] hsv){
	    double r = 0.0;
	    double g = 0.0;
	    double b = 0.0;

	    double i = Math.floor(hsv[0] * 6.0);
	    double f = hsv[0] * 6.0 - i;
	    double p = hsv[2] * (1.0 - hsv[1]);
	    double q = hsv[2] * (1.0 - f * hsv[1]);
	    double t = hsv[2] * (1.0 - (1 - f) * hsv[1]);

	    double c = i % 6;
	    
	    if(c == 0){
	    	r = hsv[2]; 
	    	g = t; 
	    	b = p;
	    }
	    else if(c == 1){
	    	r = q;
	    	g = hsv[2];
	    	b = p;
	    }
	    else if(c == 2){
	    	r = p;
	    	g = hsv[2];
	    	b = t;
	    }
	    else if(c == 3){
	    	r = p;
	    	g = q;
	    	b = hsv[2];
	    }
	    else if(c == 4){
	    	r = t;
	    	g = p;
	    	b = hsv[2];
	    }
	    else if(c == 5){
	    	r = hsv[2];
	    	g = p;
	    	b = q;
	    }

	    return new int[]{(int)(r * 255.0), (int)(g * 255.0), (int)(b * 255.0)};
	}	
}
