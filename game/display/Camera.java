package game.display;

/**
 * Write a description of class Camera here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */

//Java API imports
import java.awt.Rectangle;

//Personal Class imports
import game.entities.Entity;

import game.Handler;

import game.tile.*;

/**
 * Keeps the Camera locked on an entity
 */
public class Camera
{
    private Handler handler;
    public Rectangle bounds;
    private float xOffset;
    private float yOffset;
    private float offsetMaxX;
    private float offsetMinX;
    private float offsetMaxY;
    private float offsetMinY;
    
    public boolean right, up;
    
    //int count;

    /**
     * Initialize Offsets to 0
     */
    public Camera(Handler handler, float xOffset, float yOffset)
    {
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        bounds = new Rectangle((int)xOffset,(int)yOffset,handler.getWidth(),handler.getHeight());
        offsetMaxX = (handler.getWorld().getWidth() * Tile.DEFAULT_TILE_WIDTH) - handler.getWidth()/2; //maps total width - half of the visible screens width
        offsetMaxY = (handler.getWorld().getHeight() * Tile.DEFAULT_TILE_HEIGHT) - handler.getHeight()/2; //maps total height - half of visible screen
        offsetMinX = handler.getWidth()/2;
        offsetMinY = handler.getHeight()/2;
        //count = 0;
        
        right = true;
        up = false;
    }

    /**
     * Center on certain entity (gets placed in the tick method)
     * 1.half of the screen to the right and left
     * 2.half of the screen above and below
     */
    public void centerOnEntity(Entity e)
    {
        xOffset = -e.getX() + handler.getWidth()/2;            
        yOffset = -e.getY() + handler.getHeight()/2;
        
        if(e.getX() > offsetMaxX)
            xOffset = offsetMinX - offsetMaxX;
        else if(e.getX() < offsetMinX)
            xOffset = offsetMinX - handler.getWidth()/2;
            
        if(e.getY() > offsetMaxY)
            yOffset = offsetMinY - offsetMaxY;
        else if(e.getY() < offsetMinY)
            yOffset = offsetMinY - handler.getHeight()/2;
          
        //int newX = (int)(-xOffset);
        //int newY = (int)(-yOffset);
        //System.out.println("(" + newX + "," + newY + ")");
        bounds.x = (int)(-xOffset);
        bounds.y = (int)(-yOffset);
        
        /*
        count++;
        if(count >= 60)
        {
            count = 0;
            System.out.println(bounds);
        }
        */
       
        //System.out.println("(" + xOffset + "," + yOffset + ")");
        //System.out.println("Width of the world is: " + (handler.getWorld().getWidth() * Tile.DEFAULT_TILE_WIDTH));    
        //System.out.println("Height of the world is: " + (handler.getWorld().getHeight() * Tile.DEFAULT_TILE_HEIGHT));   
        
        /*
        xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth()/2;
        yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight()/2;
        System.out.println("(" + xOffset + "," + yOffset + ")");
        checkOffset();
        */
    }

    /**
     * Makes sure the camera is within the bounds of the world
     * 1.does not exceed the map boundaries left, right, up, and down
     */
    public void checkOffset()
    {
        
    }

    public void move(float xAmt, float yAmt)
    {
        xOffset += xAmt;
        yOffset += yAmt;
        checkOffset();
    }

    public int decideXDirection()
    {
        if(right)
        {
            return -1;
        }
        return 1;
    }
    
    public int decideYDirection()
    {
        if(up)
        {
            return 1;
        }
        return -1;
    }
    
    public void panMap(float xPanSpeed, float yPanSpeed)
    {
        //float moveX = xPanSpeed;
        //float moveY = yPanSpeed;
        
        float newX = -xOffset;
        float newY = -yOffset;
        
        //System.out.println("XOffset: " + xOffset + ", offsetMinX: " + offsetMinX);
        
        if(newX < 0)
        {
            //System.out.println("Go right!");
            right = true;
        }
        if(newX > Math.abs((offsetMaxX - offsetMinX)))
        {
            //System.out.println("Go left!");
            right = false;
        }
        
        if(newY > (offsetMaxY - offsetMinY))
        {
            //System.out.println("Go up!");
            up = true;  
        }
        if(newY < 0)
        {
            //System.out.println("Go down!");
            up = false;  
        }
                
        move((decideXDirection() * xPanSpeed),(decideYDirection() * yPanSpeed));
    }
    
    //getter methods

    public float getXOffset()
    {
        return xOffset;
    }

    public float getYOffset()
    {
        return yOffset;
    }

    //setter methods

    public void setXOffset(float xOffset)
    {
        this.xOffset = xOffset;
    }

    public void setYOffset(float yOffset)
    {
        this.yOffset = yOffset;
    }
}
