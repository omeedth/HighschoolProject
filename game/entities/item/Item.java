package game.entities.item;


/**
 * Abstract class Item - write a description of the class here
 * 
 * @author Alex Thomas
 * @version (version number or date here)
 */

//Imports for personally made  classes
import game.entities.Entity;

import game.*;

//Imports for Java API
import java.awt.Graphics;
import java.awt.Rectangle;

import java.awt.image.BufferedImage;

public abstract class Item extends Entity
{
    //Static Variables
        
    //Variables    
    public int value;
    
    public boolean inInventory;

    protected Handler handler;
    
    /*
     * Item Constructor that passes a value along with all previous entity variables
     */
    public Item(float x, float y, int width, int height, Rectangle bounds, Handler handler, int value)
    {
        super(x,y,width,height,bounds);
        this.value = value;
        this.handler = handler;
    }
    
    /**
     * Renders the items when they are not in inventory
     */
    public void render(Graphics g, float x, float y)
    {
        
    }
    
    /**
     * picks up item to store it in inventory
     * 1. tests if you canPickUp() an item
     *      > if you canPickUp() item the item is placed in the empty inventory space
     */
    public void pickUp()
    {
        
    }
    
    /**
     * tests the inventory if there is a free space
     * 1. returns the empty space inventory position
     * 2. returns -1 if no empty positions
     */
    private int canPickUp()
    {
        return -1;
    }
    
    //Getters
    
    public int getValue()
    {
        return value;
    }
    
    public boolean isInInventory()
    {
        return inInventory;
    }
    
    //Setters
    
    public void setValue(int value)
    {
        this.value = value;
    }
    
    public void setInInventory(boolean inInventory)
    {
        this.inInventory = inInventory;
    }
}
