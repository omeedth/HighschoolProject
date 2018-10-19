package game.entities;


/**
 * Write a description of class Entity here.
 * 
 * @author Eric Sy
 * @version (a version number or a date)
 */

//Imports for Personally made classes
import game.Handler;

import game.entities.creature.state.Temper;

//Imports for Java API
import java.awt.Graphics;
import java.awt.Rectangle;

import java.awt.image.BufferedImage;

public abstract class Entity
{    
    //Variables
    public float x,y;
    public int width, height;
    public Rectangle bounds;
    public boolean alive;
    public boolean inBounds;
    public boolean teleport;
    public boolean canTeleport;
    //public Temper temper;
    //public int damage;
    
    /**
     * Entity initializes the (x,y) position
     *                    the width and height
     *                    the bounding box of the entity
     */
    public Entity(float x, float y, int width, int height, Rectangle bounds)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bounds = bounds;
        inBounds = true;
        alive = true;
        teleport = false;
        canTeleport = false;
    }
    
    public void init()
    {
        
    }
    
    /////// MAKE DEFAULT RENDER METHOD DRAW A STILL IMAGE ////////
    
    /**
     * Render - will draw the entity to the screen
     */
    public abstract void render(Graphics g);
    
    /**
     * Update - updates all Entities variables
     */
    public abstract void update();
    
    //Getter Methods - returns all specified variables
    public float getX()
    {
        return x;
    }
    
    public float getY()
    {
        return y;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public Rectangle getBounds()
    {
        return bounds;
    }
    
    //Setter Methods - sets all specified variables
    public void setX(float x)
    {
        this.x = x;
    }
    
    public void setY(float y)
    {
        this.y = y;
    }
    
    public void setWidth(int width)
    {
        this.width = width;
    }
    
    public void setHeight(int height)
    {
        this.height = height;
    }
    
    public void setBounds(Rectangle bounds)
    {
        this.bounds = bounds;
    }
}