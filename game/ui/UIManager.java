package game.ui;

/**
 * Write a description of class UIManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

//Personally made classes
import game.Handler;

//Imports from Java API
import java.awt.event.MouseEvent;

import java.awt.Graphics;

import java.util.ArrayList;

public class UIManager
{
    private Handler handler;
    private ArrayList<UIObject> objects;
    
    /**
     * Initialize all variables
     */
    public UIManager(Handler handler)
    {
        this.handler = handler;
        objects = new ArrayList<>();
    }
    
    /**
     * updates the variables in all the UI buttons
     * 1.cycles through the list and updates
     */
    public void update()
    {
        for(UIObject o : objects)
        {
            o.update();
        }
    }
    
    /**
     * renders all the UI buttons
     * 1.cycles through the list and ticks
     */
    public void render(Graphics g)
    {
        for(UIObject o : objects)
        {
            o.render(g);
        }
    }
    
    /**
     * renders all the UI buttons
     * 1.cycles through the list and ticks
     */
    public void render(Graphics g, float x, float y)
    {
        for(UIObject o : objects)
        {
            o.render(g,x + o.bounds.x,y + o.bounds.y);
        }
    }
    
    /**
     * updates the variables in all the UI buttons
     * 1.cycles through the list and runs onMouseMove()
     */
    public void onMouseMove(MouseEvent e)
    {
        for(UIObject o : objects)
        {
            o.onMouseMove(e);
        }
    }
    
    /**
     * updates the variables in all the UI buttons
     * 1.cycles through the list and runs onMouseRelease()
     */
    public void onMouseRelease(MouseEvent e)
    {
        for(UIObject o : objects)
        {
            o.onMouseRelease(e);
        }
    }
    
    /**
     * adds an object to the list
     */
    public void addObject(UIObject o)
    {
        objects.add(o);
    }
    
    /**
     * removes an object from the list
     */
    public void removeObject(UIObject o)
    {
        objects.remove(o);
    }
    
    //Getter
    
    public ArrayList<UIObject> getObjects()
    {
        return objects;
    }
}
