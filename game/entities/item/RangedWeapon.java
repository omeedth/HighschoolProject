package game.entities.item;

/**
 * Write a description of class RangedWeapon here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */

//imports for personally made classes
import game.Handler;

import game.tile.Tile;

import game.input.MouseManager;

import game.entities.creature.*;

//Java API imports
import java.awt.Rectangle;
import java.awt.Graphics;

import java.awt.image.BufferedImage;

import java.util.Iterator;

public class RangedWeapon extends Weapon
{
    //Variables
    //public boolean canAttack;
    //public boolean attacked;
    //public int reloadTime;
    //public int reloadTimer;
    public boolean playerUse;
    public Projectile p;    
    public Creature cre;

    public RangedWeapon(float x, float y, int width, int height, Rectangle bounds, Handler handler, int value, int damage, int cooldown, float knockbackX, float knockbackY, Projectile p, boolean playerUse, Creature cre,BufferedImage img)
    {
        super(x,y,width,height,bounds,handler,value,damage,cooldown,knockbackX,knockbackY,img);
        this.p = p;
        this.playerUse = playerUse;
        this.cre = cre;
        //attacked = false;
        //canAttack = true;
        //reloadTime = 45;
        //reloadTimer = 0;
        //maxX = Tile.DEFAULT_TILE_WIDTH;
        //maxY = Tile.DEFAULT_TILE_HEIGHT;
    }

    @Override
    public void update()
    {
        if(attacking)
        {
            if(timer > cooldown)
            {
                attacking = false;
                attackRight = false;
                attackLeft = false;
                attackUp = false;
                attackDown = false;
            }
            timer++;
        }        
    }

    public void render(Graphics g)
    {
        
    }

    /**
     * Get the speed of the projectile and use trigonometry // computers are automaticallys set to radians
     * 1. test the cooldown if we canAttack();
     * 2. angle = 1 / tan(y/x) 
     * 3. x = r * cos (angle) 
     * 4. y = r * sin (angle)
     */
    public void use(Player pl)
    {
        /*
        if(p.currentWeapon instanceof RangedWeapon)
        {
        MouseManager m = handler.getMouseManager();
        RangedWeapon r = (RangedWeapon)p.currentWeapon;
        float xClick = m.getX();
        float yClick = m.getY();
        float px = p.x;
        float py = p.y;            
        float width = Math.abs(px - xClick);
        float height = Math.abs(py - yClick);
        float hypotenuse = (float)Math.sqrt(Math.pow(width,2) + Math.pow(height,2));
        float sin = height / hypotenuse;
        float cos = width / hypotenuse;
        float moveY = (float)Math.sin(sin) * r.p.speed;
        float moveX = (float)Math.sin(cos) * r.p.speed;

        float startX = 0;
        float startY = 0;

        }
         */
        if(canAttack())
        {
            //System.out.println("can Attack");
            //float pX = p.x;
            //float pY = p.y;
            //int pWidth = p.width;
            //int pHeight = p.height;
            //bounds.x = (int)pl.x + pl.bounds.x + (pl.bounds.width - maxX)/2;
            //bounds.y = (int)pl.y + pl.bounds.y + (pl.bounds.height - maxY)/2;
            if(pl.attackRight)
            {
                System.out.println("Attacking Right");
                attacking = true;
                attackRight = true;
                //attacked = true;
                useRight(pl);
            }
            else if(pl.attackLeft)
            {
                System.out.println("Attacking Left");
                attacking = true;
                attackLeft = true;
                //attacked = true;
                useLeft(pl);
            }
            else if(pl.attackUp)
            {
                System.out.println("Attacking Up");
                attacking = true;
                attackUp = true;
                //attacked = true;
                useUp(pl);
            }
            else if(pl.attackDown)
            {
                System.out.println("Attacking Down");
                attacking = true;
                attackDown = true;
                //attacked = true;
                useDown(pl);
            }
        }
    }

    public void knockbackRight(Creature c)
    {
        c.knockbackX = knockbackX;
        c.hit = true;
        c.hitRight = true;
        /*
        c.velX = knockbackX;
        c.accelX = -knockbackX/4;
         */
    }

    public void knockbackLeft(Creature c)
    {
        c.knockbackX = -knockbackX;
        c.hit = true;
        c.hitLeft = true;
    }

    public void knockbackUp(Creature c)
    {
        c.knockbackY = -knockbackY;
        c.hit = true;
        c.hitUp = true;
    }

    public void knockbackDown(Creature c)
    {
        c.knockbackY = knockbackY;
        c.hit = true;
        c.hitDown = true;
    }

    private void useRight(Player pl)
    {
        //Spawn it when you attack        
        p.shootRight(pl);
        /*
        p.setX(pl.x + pl.bounds.x + pl.bounds.width);
        p.setY(pl.y + pl.bounds.y + (pl.bounds.height/2));
        p.setVelX(p.speed);
        p.setVelY(0);
        handler.getItemManager().addAddList(p);
        */
        timer = 0;
        /*
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
        if(e != pl)
        {
        Rectangle eR = new Rectangle((int)(e.x + e.bounds.x),(int)(e.y + e.bounds.y),e.bounds.width,e.bounds.height);
        //attackBounds.x = (int)p.x;
        //attackBounds.y = (int)p.y;
        if(p.bounds.intersects(eR))
        {
        if(e.hurt(p.damage + pl.damage))
        {
        pl.gainExp(e);
        }
        knockbackRight(e);
        attackRight = false;
        }
        }
        }
        p.x = 
         */
        //int displacement = (int)(bounds.x - (p.x + p.bounds.x));
        //bounds.x += (maxX/cooldown);// - displacement;
    }

    private void useLeft(Player pl)
    {
        //Spawn it when you attack
        p.shootLeft(pl);
        /*
        p.x = pl.x + pl.bounds.x;
        p.y = pl.y + pl.bounds.y + (pl.bounds.height/2);
        p.velX = -p.speed;
        p.velY = 0;
        handler.getItemManager().addAddList(p);
        */
        timer = 0;
        /*
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
        if(e != pl)
        {
        Rectangle eR = new Rectangle((int)(e.x + e.bounds.x),(int)(e.y + e.bounds.y),e.bounds.width,e.bounds.height);
        //attackBounds.x = (int)p.x;
        //attackBounds.y = (int)p.y;
        if(p.bounds.intersects(eR))
        {
        if(e.hurt(p.damage + pl.damage))
        {
        pl.gainExp(e);
        }
        knockbackLeft(e);
        attackLeft = false;
        }
        }
        }       
        //int displacement = (int)(bounds.x - (p.x + p.bounds.x));
        bounds.x -= (maxX/cooldown);// + displacement;
         */
    }

    private void useUp(Player pl)
    {
        //Spawn it when you attack
        p.shootUp(pl);
        /*
        p.x = pl.x + pl.bounds.x + (pl.bounds.width/2);
        p.y = pl.y + pl.bounds.y;
        p.velX = 0;
        p.velY = -p.speed;
        handler.getItemManager().addAddList(p);
        */
        timer = 0;
        /*
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
        if(e != pl)
        {
        Rectangle eR = new Rectangle((int)(e.x + e.bounds.x),(int)(e.y + e.bounds.y),e.bounds.width,e.bounds.height);
        //attackBounds.x = (int)p.x;
        //attackBounds.y = (int)p.y;
        if(p.bounds.intersects(eR))
        {
        if(e.hurt(p.damage + pl.damage))
        {
        pl.gainExp(e);
        }
        knockbackUp(e);
        attackUp = false;
        }
        }
        }     
        //int displacement = (int)(bounds.y - (p.y + p.bounds.y));
        bounds.y -= (maxY/cooldown);// + displacement;
         */
    }

    private void useDown(Player pl)
    {
        //Spawn it when you attack
        p.shootDown(pl);
        /*
        p.x = pl.x + pl.bounds.x + (pl.bounds.width/2);
        p.y = pl.y + pl.bounds.y + pl.bounds.height;
        p.velX = 0;
        p.velY = p.speed;
        handler.getItemManager().addAddList(p);
        */
        timer = 0;
        /*
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
        if(e != pl)
        {
        Rectangle eR = new Rectangle((int)(e.x + e.bounds.x),(int)(e.y + e.bounds.y),e.bounds.width,e.bounds.height);
        //attackBounds.x = (int)p.x;
        //attackBounds.y = (int)p.y;
        if(p.bounds.intersects(eR))
        {
        if(e.hurt(p.damage + pl.damage))
        {
        pl.gainExp(e);
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
    
    //Setter
    
    public void setProjectile(Projectile p)
    {
        this.p = p;
    }
    
    public void setWeaponUser(Creature cre)
    {
        this.cre = cre;
    }
}
