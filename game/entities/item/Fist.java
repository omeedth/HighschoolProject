package game.entities.item;

/**
 * Write a description of class Fist here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

//Imports for personally made classes
import game.Handler;

import game.gfx.Animation;
import game.gfx.Assets;

import game.entities.creature.*;

//imports for Java API
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;

public class Fist extends MeleeWeapon
{
    /**
     * Constructor for objects of class Fist
     */
    public Fist(float x, float y, int width, int height, Rectangle bounds, Handler handler, int value, float damage, int cooldown, float knockbackX, float knockbackY)
    {
        super(x,y,width,height,bounds,handler,value,damage,cooldown,knockbackX,knockbackY,Assets.fist);
        animRight = new Animation(cooldown,Assets.fistRight);
    }   

    public void render(Graphics g)
    {
        g.setColor(Color.BLUE);
        g.fillRect(bounds.x,bounds.y,bounds.width,bounds.height);
        if(attackRight)
        {
            if(animRight != null)
                animRight.drawAnimation(g,bounds.x,bounds.y);
            else
            {
                animLeft.reset();
            }
        }
        else
        {
            
        }
        if(attackLeft)
        {
            if(animLeft != null)
                animLeft.drawAnimation(g,bounds.x,bounds.y);
            else
            {
                animRight.reset();
            }
        }
        /*
        else
        {
            
        }
        if(attackUp && animUp != null)
        {
            animUp.drawAnimation(g,bounds.x,bounds.y);
        }
        else
        {
            
        }
        if(attackDown && animDown != null)
        {
            animDown.drawAnimation(g,bounds.x,bounds.y);
        }
        */
        //else
        //{            
            //g.drawImage(Assets.fistRight[0]);            
            /*
            g.setColor(Color.BLUE);
            g.fillRect(bounds.x,bounds.y,bounds.width,bounds.height);
            */
        //}
    }

    /*
    public void update()
    {

    }
     */
}
