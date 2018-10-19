  package game.ui;

/**
 * Write a description of class UIManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

//Personally made classes
import game.Handler;

import game.entities.creature.Player;

//Imports from Java API
import java.awt.event.MouseEvent;

import java.awt.Graphics;

import java.util.ArrayList;

public class UpgradeManager
{
    private Handler handler;
    private ArrayList<UpgradeButton> objects;
    private Player p;
    
    /**
     * Initialize all variables
     */
    public UpgradeManager(Handler handler, Player p)
    {
        this.handler = handler;
        objects = new ArrayList<>();
        this.p = p;
    }
    
    /**
     * updates the variables in all the UI buttons
     * 1.cycles through the list and updates
     */
    public void update(int x, int y)
    {
        for(UpgradeButton o : objects)
        {
            o.update(x,y);
        }
    }
    
    /**
     * updates the variables in all the UI buttons
     * 1.cycles through the list and updates
     */
    public void update()
    {
        for(UpgradeButton o : objects)
        {
            o.update(p);
        }
    }
    
    /**
     * renders all the UI buttons
     * 1.cycles through the list and ticks
     */
    public void render(Graphics g, float x, float y)
    {
        for(UpgradeButton o : objects)
        {
            o.render(g,x,y);
        }
    }

    /**
     * adds an object to the list
     */
    public void addObject(UpgradeButton o)
    {
        objects.add(o);
    }
    
    /**
     * removes an object from the list
     */
    public void removeObject(UpgradeButton o)
    {
        objects.remove(o);
    }
    
    public ArrayList<UpgradeButton> getObjects()
    {
        return objects;
    }
}