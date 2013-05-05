import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;

/**
 * This class allows for examination of different parts of the fractal by
 * creating and showing a Swing GUI and handles events caused by various
 * user interactions.
 */
public class FractalExplorer
{
    // Integer display size is the width and height of display in pixels.
    private int displaySize;
    
    // JImageDisplay reference to update display from various methods as
    // the fractal is computed.
    private JImageDisplay display;
    
    // A FractalGenerator object using a base-class reference in order to show
    // other types of fractals in the future.
    private FractalGenerator fractal;
    
    // A Rectangle2D.Double object which specifies the range of the complex
    // that which we are currently displaying. 
    private Rectangle2D.Double range;
    
    /**
     * A constructor that takes a display-size, stores it, and
     * initializes the range and fractal-generator objects.
     */
    public FractalExplorer(int size) {
        // Stores display-size
        displaySize = size;
        
        // Initializes the fractal-generator and range objects.
        fractal = new Mandelbrot();
        range = new Rectangle2D.Double();
        fractal.getInitialRange(range);
        display = new JImageDisplay(displaySize, displaySize);
    
    }
    /**
     * This method intializes the Swing GUI with a JFrame holding the 
     * JImageDisplay object and a button to reset the display.
     */
    public void createAndShowGUI()
    {
        // Set the frame to use a java.awt.BorderLayout for its contents.
        display.setLayout(new BorderLayout());
        JFrame myframe = new JFrame("Fractal Explorer");
        
        // Add the image-display object in the BorderLayout.CENTER position.
        myframe.add(display, BorderLayout.CENTER);
        
        // Create a reset button.
        JButton resetButton = new JButton("Reset Display");
        
        // Instance of the ResetHandler on the reset button.
        ResetHandler handler = new ResetHandler();
        resetButton.addActionListener(handler);
        
        // Add the reset button in the BorderLayout.SOUTH position.
        myframe.add(resetButton, BorderLayout.SOUTH);
        
        // Instance of the MouseHandler on the fractal-display component.
        MouseHandler click = new MouseHandler();
        display.addMouseListener(click);
        
        // Set the frame's default close operation to "exit".
        myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Lay out contents of the frame, cause it to be visible, and
        // disallow resizing of the window.
        myframe.pack();
        myframe.setVisible(true);
        myframe.setResizable(false);
    }
    
    /**
     * Private helper method to display the fractal.  This method loops 
     * through every pixel in the display and computes the number of 
     * iterations for the corresponding coordinates in the fractal's
     * display area.  If the number of iterations is -1 set the pixel's color
     * to black.  Otherwise, choose a value based on the number of iterations.
     * Update the display with the color for each pixel and repaint
     * JImageDisplay when all pixels have been drawn.
     */
    private void drawFractal()
    {
        // Loop through every pixel in the display
        for (int x=0; x<displaySize; x++){
            for (int y=0; y<displaySize; y++){
                
                // Find the corresponding coordinates xCoord and yCoord
                // in the fractal's display area.
                double xCoord = fractal.getCoord(range.x,
                range.x + range.width, displaySize, x);
                double yCoord = fractal.getCoord(range.y,
                range.y + range.height, displaySize, y);
                
                // Compute the number of iterations for the coordinates in
                // the fractal's display area.
                int iteration = fractal.numIterations(xCoord, yCoord);
                
                // If number of iterations is -1, set the pixel to black.
                if (iteration == -1){
                    display.drawPixel(x, y, 0);
                }
                
                //
                //CHANGED AFTER SUBMISSION: ADDED "ELSE"
                //
                else {
                    // Otherwise, choose a hue value based on the number
                    // of iterations.  
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                
                    // Update the display with the color for each pixel.
                    display.drawPixel(x, y, rgbColor);
                }
                
            }
        }
        // When all the pixels have been drawn, repaint JImageDisplay to match
        // current contents of its image. 
        display.repaint();
    }
    /**
     * An inner class to handle ActionListener events from the reset button.
     */
    private class ResetHandler implements ActionListener
    {
        /**
         * The handler resets the range to the intial range given by the 
         * generator, and then draws the fractal.
         */
        public void actionPerformed(ActionEvent e)
        {
            fractal.getInitialRange(range);
            drawFractal();
        }
    }
    /**
     * An inner class to handle MouseListener events from the display.
     */
    private class MouseHandler extends MouseAdapter
    {
        /**
         * When the handler receives a mouse-click event, it maps the pixel-
         * coordinates of the click into the area of the fractal that is being
         * displayed, and then calls the generator's recenterAndZoomRange()
         * method with coordinates that were clicked and a 0.5 scale. 
         */
        @Override
        public void mouseClicked(MouseEvent e)
        {
            // Get x coordinate of display area of mouse click.
            int x = e.getX();
            double xCoord = fractal.getCoord(range.x,
            range.x + range.width, displaySize, x);
            
            // Get y coordinate of display area of mouse click.
            int y = e.getY();
            double yCoord = fractal.getCoord(range.y,
            range.y + range.height, displaySize, y);
            
            // Call the generator's recenterAndZoomRange() method with
            // coordinates that were clicked and a 0.5 scale.
            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            
            // Redraw the fractal after the area being displayed has changed.
            drawFractal();
        }
    }
    
    /**
     * A static main() method to launch FractalExplorer.  Initializes a new
     * FractalExplorer instance with a display-size of 600, calls 
     * createAndShowGUI() on the explorer object, and then calls 
     * drawFractal() on the explorer to see the initial view.
     */
    public static void main(String[] args)
    {
        FractalExplorer displayExplorer = new FractalExplorer(600);
        displayExplorer.createAndShowGUI();
        displayExplorer.drawFractal();
    }
}