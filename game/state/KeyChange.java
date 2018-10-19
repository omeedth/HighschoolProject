package game.state;


/**
 * Write a description of class KeyChange here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

//Imports for personally made classes
import game.Handler;
import game.Saves;

import game.entities.creature.Player;

//Java API imports
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics;

import javax.swing.*;

public class KeyChange extends JFrame implements KeyListener
{
    //Variables
    private Handler handler;
    //private static Player p;
    String fileName;
    //char currentKey;
    char key;    
    byte[] controls;

    /**
     * Constructor for objects of class KeyChange
     */
    public KeyChange(char key, String fileName, Handler handler)
    {
        //currentKey = (char)keyV;
        this.key = key;
        //p = pl;
        this.fileName = fileName;
        controls = Saves.loadControls(fileName);
        
        for(byte b : controls)
        {
            char c = (char)b;
        }
        
        addKeyListener(this);
        this.handler = handler;
        makeWindow("",200,200);  
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
        //Setting the window size
        
        setSize(width,height);
        
        //window.setPreferredSize(new Dimension(width, height));
        //window.setMaximumSize(new Dimension(width, height));
        //window.setMinimumSize(new Dimension(width, height));
        
        //Set the start position
        setLocationRelativeTo(null);
        //Set a default close action
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Set a Title
        setTitle(title);
        //Enable resize
        setResizable(true);
        //pack();
        setVisible(true);
    }
    
    @Override
    public void paint(Graphics g)
    {
        //g.drawString("Current Key is: " + currentKey,75,90);
    }
    
    /**
     * When you press a key that key will be set to true
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        switch(key)
        {
            case 'u':
                byte k1 = (byte)(e.getKeyCode());
                System.out.println("Pressed: " + (char)k1);
                controls[0] = k1;
                handler.getSaves().saveControls(controls,fileName);
                break;
            case 'l':
                byte k2 = (byte)(e.getKeyCode());
                System.out.println("Pressed: " + (char)k2);
                controls[1] = k2; 
                handler.getSaves().saveControls(controls,fileName);
                break;
            case 'r':
                byte k3 = (byte)(e.getKeyCode());
                System.out.println("Pressed: " + (char)k3);
                controls[3] = k3; 
                handler.getSaves().saveControls(controls,fileName);
                break;
            case 'd':
                byte k4 = (byte)(e.getKeyCode());
                System.out.println("Pressed: " + (char)k4);
                controls[2] = k4; 
                handler.getSaves().saveControls(controls,fileName);
                break;
            case 't':
                byte k5 = (byte)(e.getKeyCode());
                System.out.println("Pressed: " + (char)k5);
                controls[4] = k5; 
                handler.getSaves().saveControls(controls,fileName);
                break;
            case 'f':
                byte k6 = (byte)(e.getKeyCode());
                System.out.println("Pressed: " + (char)k6);
                controls[5] = k6; 
                handler.getSaves().saveControls(controls,fileName);
                break;
            case 'h':
                byte k7 = (byte)(e.getKeyCode());
                System.out.println("Pressed: " + (char)k7);
                controls[7] = k7; 
                handler.getSaves().saveControls(controls,fileName);
                break;
            case 'g':
                byte k8 = (byte)(e.getKeyCode());
                System.out.println("Pressed: " + (char)k8);
                controls[6] = k8; 
                handler.getSaves().saveControls(controls,fileName);
                break;
            case 'q':
                byte k9 = (byte)(e.getKeyCode());
                System.out.println("Pressed: " + (char)k9);
                controls[8] = k9;
                handler.getSaves().saveControls(controls,fileName);
        }
        //System.out.println(p.toString());
        //System.out.println("Key: "  + currentKey);
        State.setState(handler.getGame().controlState);
        dispose();
        //System.out.println("KeyCode: " + e.getKeyCode());
    }
    
    /**
     * when you release a key it will be set to false
     */
    @Override
    public void keyReleased(KeyEvent e)
    {
        
    }
    
    /**
     * not implementing
     */
    @Override
    public void keyTyped(KeyEvent e)
    {
        
    }
    
    /*
    public static void setPlayer(Player pl)
    {
        p = pl;
    }
    */
}
