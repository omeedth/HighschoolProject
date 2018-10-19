package game.entities.item;

/**
 * Write a description of class ItemManager here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */

//Imports for personally made classes
import game.Handler;

import game.display.Camera;

//Imports for Java API

import java.util.ArrayList;
import java.util.Iterator;

import java.awt.Graphics;
import java.awt.Rectangle;

public class ItemManager
{
    //Variables
    private Handler handler;
    private Camera cam;
    private ArrayList<Item> items;
    private ArrayList<Item> killList;
    private ArrayList<Item> addList;

    /**
     * Initialize all variables
     */
    public ItemManager(Handler handler, Camera cam)
    {
        this.handler = handler;
        this.cam = cam;
        items = new ArrayList<>();
        killList = new ArrayList<>();
        addList = new ArrayList<>();
    }

    /**
     * Updates all of the items
     * 1. cycles through the list of items and update() -s them
     */
    public void update()
    {        
        if(addList.size() > 0)
        {
            for(Item i : addList)
            {
                items.add(i);
            }
            addList.clear();
        }
        for(Item i: items)
        {
            if(cam.bounds.intersects(new Rectangle((int)(i.getX() + i.bounds.x),(int)(i.getY() + i.bounds.y),i.bounds.width,i.bounds.height)))
            {
                i.update();
            }
            else
            {
                i.inBounds = true;
            }
        }       
        if(killList.size() > 0)
        {
            for(Item i : killList)
            {
                items.remove(i);
            }
            killList.clear();
        }
    }

    /**
     * Renders all of the items
     * 1. cycles through the list of items and render() -s them
     */
    public void render(Graphics g)
    {
        for(Item i: items)
        {
            if(cam.bounds.intersects(new Rectangle((int)(i.getX() + i.bounds.x),(int)(i.getY() + i.bounds.y),i.bounds.width,i.bounds.height)))
            {
                i.render(g);
            }
        }
    }

    /**
     * Adds an Item to the list
     */
    public void addItem(Item i)
    {
        items.add(i);
    }

    /**
     * Removes an Item from the list
     */
    public void removeItem(Item i)
    {
        items.remove(i);
    }

    /**
     * Adds an Entity from the list
     * 1.gets passed in an entity to add
     * 2.call the list name and add the passed in entity
     */
    public void addKillList(Item i)
    {
        killList.add(i);   
    }
    
    /**
     * Adds an Entity from the list
     * 1.gets passed in an entity to add
     * 2.call the list name and add the passed in entity
     */    
    public void addAddList(Item i)
    {
        addList.add(i);   
    }

    // Getters and Setters

    public ArrayList<Item> getItems()
    {
        return items;
    }

    /**
     * Returns the list of entities
     */
    public ArrayList<Item> getKillList()
    {
        return killList;
    }
}
