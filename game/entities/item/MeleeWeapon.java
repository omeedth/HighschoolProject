package game.entities.item;

/**
 * Write a description of class MeleeWeapon here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */

//Imports for personally made classes
import game.Handler;

import game.gfx.Assets;

import game.entities.*;

import game.entities.creature.*;

//imports for Java API
import java.awt.Rectangle;
import java.awt.Graphics;

import java.awt.image.BufferedImage;

import java.util.Iterator;

public class MeleeWeapon extends Weapon
{
    //Variables
    //public Rectangle attackBounds; //Might Need    
    //public float attackSpeed;

    /**
     * Initialize all the bounds of the attack
     */
    public MeleeWeapon(float x, float y, int width, int height, Rectangle bounds, Handler handler, int value, float damage, int cooldown, float knockbackX, float knockbackY,BufferedImage img)
    {
        super(x,y,width,height,bounds,handler,value,damage,cooldown,knockbackX,knockbackY,img); 
        maxX = bounds.width;
        maxY = bounds.height;
        //attackBounds.x = bounds.x;
        //attackBounds.y = bounds.y;
        //attackBounds.width = bounds.width;
        //attackBounds.height = bounds.height;
        //attackSpeed = 2f;
    }

    /*
    public void update()
    {

    }
     */

    public void render(Graphics g)
    {

    }

    /**
     * 1. tests the (x,y) position of where you click
     *      > if the x position is greater than the center of the player 
     *        the character attacks to the right (otherwise attack to the left)
     * 2. expand a bounding box around the player where the attack will be sent
     */
    public void use(Player p)
    {
        if(canAttack())
        {
            //System.out.println("can Attack");
            //float pX = p.x;
            //float pY = p.y;
            //int pWidth = p.width;
            //int pHeight = p.height;
            bounds.x = (int)p.x + p.bounds.x + (p.bounds.width - maxX)/2;
            bounds.y = (int)p.y + p.bounds.y + (p.bounds.height - maxY)/2;
            if(p.attackRight)
            {
                //System.out.println("Attacking Right");
                attacking = true;
                useRight(p);
            }
            else if(p.attackLeft)
            {
                //System.out.println("Attacking Left");
                attacking =true;
                useLeft(p);
            }
            else if(p.attackUp)
            {
                //System.out.println("Attacking Up");
                attacking = true;
                useUp(p);
            }
            else if(p.attackDown)
            {
                //System.out.println("Attacking Down");
                attacking = true;
                useDown(p);
            }
        }
        else
        {
            if(attackRight)
            {
               useRight(p); 
            }
            else if(attackLeft)
            {
               useLeft(p); 
            }
            else if(attackUp)
            {
               useUp(p); 
            }
            else
            {
               useDown(p); 
            }
        }
    }

    private void knockbackRight(Creature c)
    {
        c.knockbackX = knockbackX;
        c.hit = true;
        c.hitRight = true;
        /*
        c.velX = knockbackX;
        c.accelX = -knockbackX/4;
         */
    }

    private void knockbackLeft(Creature c)
    {
        c.knockbackX = -knockbackX;
        c.hit = true;
        c.hitLeft = true;
    }

    private void knockbackUp(Creature c)
    {
        c.knockbackY = -knockbackY;
        c.hit = true;
        c.hitUp = true;
    }

    private void knockbackDown(Creature c)
    {
        c.knockbackY = knockbackY;
        c.hit = true;
        c.hitDown = true;
    }

    private void useRight(Player p)
    {
        if(!attackRight)
        {
            //bounds.x = (int)(p.x + p.bounds.x);
            //bounds.y = (int)(p.y + p.bounds.y);
            attackRight = true;
        }   
        Iterator i = handler.getEntityManager().getEntities().iterator();
        while(i.hasNext())
        {
            Creature e = ((Creature)i.next());
            if(e != p)
            {
                Rectangle eR = new Rectangle((int)(e.x + e.bounds.x),(int)(e.y + e.bounds.y),e.bounds.width,e.bounds.height);
                //attackBounds.x = (int)p.x;
                //attackBounds.y = (int)p.y;
                if(bounds.intersects(eR))
                {
                    if(e.hurt(damage + p.damage))
                    {
                        p.gainExp(e);
                    }
                    knockbackRight(e);
                    attackRight = false;
                }
            }
        }
        //int displacement = (int)(bounds.x - (p.x + p.bounds.x));
        bounds.x += (maxX/cooldown);// - displacement;
    }

    private void useLeft(Player p)
    {
        if(!attackLeft)
        {
            //bounds.x = (int)(p.x + p.bounds.x - bounds.width);
            //bounds.y = (int)(p.y + p.bounds.y);
            attackLeft = true;
        }
        Iterator i = handler.getEntityManager().getEntities().iterator();
        while(i.hasNext())
        {
            Creature e = ((Creature)i.next());
            if(e != p)
            {
                Rectangle eR = new Rectangle((int)(e.x + e.bounds.x),(int)(e.y + e.bounds.y),e.bounds.width,e.bounds.height);
                //attackBounds.x = (int)p.x;
                //attackBounds.y = (int)p.y;
                if(bounds.intersects(eR))
                {
                    if(e.hurt(damage + p.damage))
                    {
                        p.gainExp(e);
                    }
                    knockbackLeft(e);
                    attackLeft = false;
                }
            }
        }       
        //int displacement = (int)(bounds.x - (p.x + p.bounds.x));
        bounds.x -= (maxX/cooldown);// + displacement;
    }

    private void useUp(Player p)
    {
        if(!attackUp)
        {
            //bounds.x = (int)(p.x + p.bounds.x);
            //bounds.y = (int)(p.y + p.bounds.y - bounds.height);
            attackUp = true;
        }
        Iterator i = handler.getEntityManager().getEntities().iterator();
        while(i.hasNext())
        {
            Creature e = ((Creature)i.next());
            if(e != p)
            {
                Rectangle eR = new Rectangle((int)(e.x + e.bounds.x),(int)(e.y + e.bounds.y),e.bounds.width,e.bounds.height);
                //attackBounds.x = (int)p.x;
                //attackBounds.y = (int)p.y;
                if(bounds.intersects(eR))
                {
                    if(e.hurt(damage + p.damage))
                    {
                        p.gainExp(e);
                    }
                    knockbackUp(e);
                    attackUp = false;
                }
            }
        }     
        //int displacement = (int)(bounds.y - (p.y + p.bounds.y));
        bounds.y -= (maxY/cooldown);// + displacement;
    }

    private void useDown(Player p)
    {
        if(!attackDown)
        {
            //bounds.x = (int)(p.x + p.bounds.x);
            //bounds.y = (int)(p.y + p.bounds.y + p.bounds.height);
            attackDown = true;
        }
        Iterator i = handler.getEntityManager().getEntities().iterator();
        while(i.hasNext())
        {
            Creature e = ((Creature)i.next());
            if(e != p)
            {
                Rectangle eR = new Rectangle((int)(e.x + e.bounds.x),(int)(e.y + e.bounds.y),e.bounds.width,e.bounds.height);
                //attackBounds.x = (int)p.x;
                //attackBounds.y = (int)p.y;
                if(bounds.intersects(eR))
                {
                    if(e.hurt(damage + p.damage))
                    {
                        p.gainExp(e);
                    }
                    knockbackDown(e);
                    attackDown = false;
                }
            }
        }
        //int displacement = (int)(bounds.y - (p.y + p.bounds.y));
        bounds.y += (maxY/cooldown); //- displacement;
        /*
        for(Entity e : handler.getEntityManager().getEntities())
        {
        if(e != p)
        {
        Rectangle eR = new Rectangle((int)(e.x + e.bounds.x),(int)(e.y + e.bounds.y),e.bounds.width,e.bounds.height);
        if(bounds.intersects(eR))
        {
        ((Creature)e).hurt(damage);
        }
        }
        }
         */
    }
}
