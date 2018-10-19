package game.entities.item;


/**
 * Write a description of class Drop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import game.*;
import game.entities.creature.Player;

import java.awt.Rectangle;

public abstract class Drop extends Item
{
    //Static Variables
    
    //Variables

    /**
     * Constructor for objects of class Drop
     */
    public Drop(float x, float y, int width, int height, Rectangle bounds, Handler handler, int value)
    {
        super(x,y,width,height,bounds,handler,value);
    }

    public abstract void action(Player p);    
    public abstract void receive(Player p);
}
