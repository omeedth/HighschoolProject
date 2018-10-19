package game.display;

/**
 * Write a description of class Window here.
 * 
 * @author Eric Sy
 * @version (a version number or a date)
 */

//Imports for personally made classes

//Imports for Java API
import java.awt.*;

import javax.swing.*;

/**
 * Window 
 * By: Eric Sy
 * 
 */
public class Window
{
    // Variables of Window Class
    private JFrame window;
    private Canvas canvas;
    
    //private int width, height;
    //private String title;

    /**
     * Window Constructor:
     * - uses the makeWindow() method
     * - add the canvas to the window
     */
    public Window(String title,int width,int height)
    {
        makeWindow(title, width, height);               
    }

    /**
     * defines Window:
     * - size
     * - location
     * - exit
     * - visibility
     * - resizability
     * - name
     */
    public void makeWindow(String title,int width, int height)
    {
        window = new JFrame();
        canvas = new Canvas();
        window.add(canvas);
        //Setting the window size
        window.setPreferredSize(new Dimension(width, height));
        window.setMaximumSize(new Dimension(width, height));
        window.setMinimumSize(new Dimension(width, height));
        //Set the start position
        window.setLocationRelativeTo(null);
        //Set a default close action
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set a Title
        window.setTitle(title);
        //Enable resize
        window.setResizable(true);
        window.pack();
        window.setVisible(true);
    }
    
    /**
     * get Frame
     */
    public JFrame getWindow()
    {
        return window;
    }
    
    /**
     * get Canvas
     */
    public Canvas getCanvas()
    {
        return canvas;
    }    
}

