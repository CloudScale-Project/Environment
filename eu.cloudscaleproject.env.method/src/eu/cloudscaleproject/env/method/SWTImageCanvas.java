package eu.cloudscaleproject.env.method;



import java.awt.geom.AffineTransform;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.method.layer.MethodLayer;

/**
 * A scrollable image canvas that extends org.eclipse.swt.graphics.Canvas.
 * <p/>
 * It requires Eclipse (version >= 2.1) on Win32/win32; Linux/gtk; MacOSX/carbon.
 * <p/>
 * This implementation using the pure SWT, no UI AWT package is used. For 
 * convenience, I put everything into one class. However, the best way to
 * implement this is to use inheritance to create multiple hierarchies.
 * 
 * @author Chengdong Li: cli4@uky.edu
 */
public class SWTImageCanvas extends Canvas {
        /* zooming rates in x and y direction are equal.*/
        final float ZOOMIN_RATE = 1.1f; /* zoomin rate */
        final float ZOOMOUT_RATE = 0.9f; /* zoomout rate */
        private Image sourceImage; /* original image */
        private Image screenImage; /* screen image */
        private AffineTransform transform = new AffineTransform();
        

		private MethodLayer layer;

        public SWTImageCanvas(final Composite parent) {
                this(parent, SWT.NULL);
        }

        /**
         * Constructor for ScrollableCanvas.
         * @param parent the parent of this control.
         * @param style the style of this control.
         */
        public SWTImageCanvas(final Composite parent, int style) {
                super( parent, style|SWT.BORDER | SWT.NO_BACKGROUND);
                
                this.layer = new MethodLayer(this, transform);
                
                addControlListener(new ControlAdapter() { /* resize listener. */
                        public void controlResized(ControlEvent event) {
                                fitCanvas();
                        }
                });
                addPaintListener(new PaintListener() { /* paint listener. */
                        public void paintControl(final PaintEvent event) {
                                paint(event.gc);
                        }
                });
                
                addKeyListener(new KeyAdapter() {
                	@Override
                	public void keyReleased(KeyEvent e) {
                		if (e.keyCode == SWT.KEYPAD_ADD)
                		{
                			zoomIn();
                			redraw();
                		}
                		else if (e.keyCode == SWT.KEYPAD_SUBTRACT)
                		{
                			zoomOut();
                			redraw();
                		}
                	}
				
                });
        }

        /**
         * Dispose the garbage here
         */
        public void dispose() {
                if (sourceImage != null && !sourceImage.isDisposed()) {
                        sourceImage.dispose();
                }
                if (screenImage != null && !screenImage.isDisposed()) {
                        screenImage.dispose();
                }
        }

        /* Paint function */
        private void paint(GC gc) {
                Rectangle clientRect = getClientArea(); /* Canvas' painting area */
                if (sourceImage != null) {

                        
                        Rectangle imageRect =
                                SWT2DUtil.inverseTransformRect(transform, clientRect);
                        
                        
                        int gap = 2; /* find a better start point to render */
                        imageRect.x -= gap; imageRect.y -= gap;
                        imageRect.width += 2 * gap; imageRect.height += 2 * gap;

                        Rectangle imageBound = sourceImage.getBounds();
                        imageRect = imageBound; //imageRect.intersection(imageBound);
                        Rectangle destRect = SWT2DUtil.transformRect(transform, imageRect);

                        if (screenImage != null)
                                screenImage.dispose();
                        screenImage =
                                new Image(getDisplay(), clientRect.width, clientRect.height);
                        GC newGC = new GC(screenImage);
                        newGC.setClipping(clientRect);
                        newGC.setInterpolation(SWT.HIGH);
                        newGC.setAntialias(SWT.ON);
                        newGC.drawImage(
                                sourceImage,
                                imageRect.x,
                                imageRect.y,
                                imageRect.width,
                                imageRect.height,
                                destRect.x,
                                destRect.y,
                                destRect.width,
                                destRect.height);
                        newGC.dispose();
                        
                        gc.drawImage(screenImage, 0, 0);
                        
                        this.layer.paint(gc);
                } else {
                        gc.setClipping(clientRect);
                        gc.fillRectangle(clientRect);
                }
                
        }

        /* Initalize the scrollbar and register listeners. */
        /**
         * Source image getter.
         * @return sourceImage.
         */
        public Image getSourceImage() {
                return sourceImage;
        }
        
        /**
         * Reload image from a file
         * @param filename image file
         * @return swt image created from image file
         */
        public Image loadImage(Image img) {
                if (sourceImage != null && !sourceImage.isDisposed()) {
                        sourceImage.dispose();
                        sourceImage = null;
                }
                sourceImage = img;
                showOriginal();
                
                
                return sourceImage;
                
        }

        /**
         * Fit the image onto the canvas
         */
        public void fitCanvas() {
                if (sourceImage == null)
                        return;
                Rectangle imageBound = sourceImage.getBounds();
                Rectangle destRect = getClientArea();
                double sx = (double) destRect.width / (double) imageBound.width;
                double sy = (double) destRect.height / (double) imageBound.height;
                double s = Math.min(sx, sy);
                
                double idx = 0.5 * imageBound.width;
                double idy = 0.5 * imageBound.height;
                
                double ddx = 0.5 * destRect.width;
                double ddy = 0.5 * destRect.height;
                
                transform.setToIdentity();
                transform.preConcatenate(AffineTransform.getTranslateInstance(-idx, -idy));
                transform.preConcatenate(AffineTransform.getScaleInstance(s, s));
                transform.preConcatenate(AffineTransform.getTranslateInstance(ddx, ddy));
        }

        /**
         * Show the image with the original size
         */
        public void showOriginal() {
                if (sourceImage == null)
                        return;
                transform.setToIdentity();
        }

        /**
         * Perform a zooming operation centered on the given point
         * (dx, dy) and using the given scale factor. 
         * The given AffineTransform instance is preconcatenated.
         * @param dx center x
         * @param dy center y
         * @param scale zoom rate
         * @param af original affinetransform
         */
        public void centerZoom(
                double dx,
                double dy,
                double scale,
                AffineTransform af) {
                af.preConcatenate(AffineTransform.getTranslateInstance(-dx, -dy));
                af.preConcatenate(AffineTransform.getScaleInstance(scale, scale));
                af.preConcatenate(AffineTransform.getTranslateInstance(dx, dy));
        }

        /**
         * Zoom in around the center of client Area.
         */
        public void zoomIn() {
                if (sourceImage == null)
                        return;
                Rectangle rect = getClientArea();
                int w = rect.width, h = rect.height;
                double dx = ((double) w) / 2;
                double dy = ((double) h) / 2;
                centerZoom(dx, dy, ZOOMIN_RATE, transform);
        }

        /**
         * Zoom out around the center of client Area.
         */
        public void zoomOut() {
                if (sourceImage == null)
                        return;
                Rectangle rect = getClientArea();
                int w = rect.width, h = rect.height;
                double dx = ((double) w) / 2;
                double dy = ((double) h) / 2;
                centerZoom(dx, dy, ZOOMOUT_RATE, transform);
        }
}
