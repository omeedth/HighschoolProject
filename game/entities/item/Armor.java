package game.entities.item;


/**
 * Write a description of class Armor here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */

//Imports for personally made classes
import game.Handler;

//Java API imports
import java.awt.Rectangle;
import java.awt.Graphics;

public class Armor extends Item
{
    //Variables
    public int defense;
    public int durability;
    
    /**
     * Initialize defense and durability
     */
    public Armor(float x, float y, int width, int height, Rectangle bounds, Handler handler, int value, int defense, int durability)
    {
        super(x,y,width,height,bounds,handler,value);
    }
    
    public void update()
    {
        
    }
    
    public void render(Graphics g)
    {
        
    }
    
    //getter methods
    
    public int getDefense()
    {
        return defense;
    }
    
    public int getDurability()
    {
        return durability;
    }
    
    //setter methods
    
    public void setDefense(int defense)
    {
        this.defense = defense;
    }
    
    public void setDurability(int durability)
    {
        this.durability = durability;
    }
}
