package game.ui;

/**
 * Write a description of class UIObject here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */

//Imports for personally made classes
import game.entities.creature.Player;

import game.Handler;

//Java API imports
import java.awt.event.MouseEvent;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class UIObject
{
    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;
    protected boolean hovering = false;
    protected Handler handler;

    /**
     * Initialize all variables
     */
    public UIObject(float x, float y, int width, int height, Handler handler)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.handler = handler;
        bounds = new Rectangle((int)x,(int)y,width,height);
    }

    /**
     * Override these methods
     */
    public abstract void update();
    public abstract void render(Graphics g);
    public abstract void render(Graphics g, float x, float y);
    public abstract void onClick();

    /**
     * tests if the mouse is hovering over the button or not and returns true or false
     */
    public void onMouseMove(MouseEvent e)
    {
        if(bounds.contains(e.getPoint()))
        {
            hovering = true;
        }
        else
        {
            hovering = false;
        }
    }

    /**
     * activates the button's click method once you click on it
     */
    public void onMouseRelease(MouseEvent e)
    {
        if(hovering)
            onClick();
    }

    //Getters

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

    public boolean isHovering()
    {
        return hovering;
    }

    //Setters

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

    public void setHovering(boolean hovering)
    {
        this.hovering = hovering;
    }
}
