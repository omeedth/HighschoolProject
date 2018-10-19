package game.entities.item;

/**
 * Write a description of class Projectile here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */

//Import for personally made classes
import game.entities.Entity;
import game.entities.Damaging;

import game.entities.creature.*;

import game.Handler;

//Import for Java API
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;

import java.awt.image.BufferedImage;

/*
 * Defines what all projectiles are
 */
public class Projectile extends Item implements Damaging
{
    //Static Variables
    public static final int DEFAULT_PROJECTILE_DAMAGE = 1;
    public static final int DEFAULT_PROJECTILE_SPEED = 8;

    //Variables
    public boolean delete;
    //public boolean playerUse;
    public float spawnX, spawnY;
    public float speed;
    public int range;
    //public float attackRange;
    public float velX, velY;
    public float damage;

    public Rectangle attackRange;
    public Rectangle attack;

    //public Creature cre;
    public RangedWeapon w;
    public BufferedImage[] img;

    public Projectile(float x, float y, int width, int height, Rectangle bounds, Handler handler, float velX, float velY,int value, float damage, float speed, int range,/* boolean playerUse,*/ /*Creature cre,*/ RangedWeapon w/*, BufferedImage... args*/)
    {
        super(x,y,width,height,bounds,handler,value);
        spawnX = x;
        spawnY = y;
        delete = false;
        this.damage = damage;
        this.velX = velX;
        this.velY = velY;
        this.range = range;
        this.speed = speed;
        this.w = w;
        //attackRange = 0;
        attackRange = new Rectangle((int)spawnX + bounds.x - range,(int)spawnY + bounds.y - range,range + bounds.width + range,range + bounds.height + range);
        attack = new Rectangle((int)(x + bounds.x),(int)(y + bounds.height),bounds.width/*width + bounds.width*/,bounds.height/*height + bounds.height*/);
        /*img = new BufferedImage[args.length];
        for(int i = 0; i < args.length; i++)
        {
        img[i] = args[i];
        }
         */
        //this.cre = cre;
    }

    public void delete()
    {
        //delete = true;
        handler.getItemManager().addKillList(this);
    }

    public void collision()
    {
        if(w.playerUse)
        {
            //System.out.println("Player using it");
            Player pl = (Player)w.cre;
            for(Entity e : handler.getEntityManager().getEntities())
            {
                if(e != w.cre)
                {
                    if(e instanceof Creature)
                    {
                        Creature c = (Creature)e;
                        if(c != null)
                        {
                            Rectangle eR = new Rectangle((int)(c.x + c.bounds.x),(int)(c.y + c.bounds.y),c.bounds.width,c.bounds.height);
                            //System.out.println("Creature's bounds: " + eR);
                            if(eR.intersects(attack))
                            {          
                                try
                                {
                                    if(c.hurt(getDamage() + pl.damage/2))
                                    {
                                        pl.gainExp(c);
                                        System.out.println("Gained Experience!");
                                        delete = true;
                                        delete();
                                        System.out.println("Added to Delete List!");                            
                                        return;
                                    }
                                    if(w.attackRight)
                                    {
                                        w.knockbackRight(c);
                                    }
                                    else if(w.attackUp)
                                    {
                                        w.knockbackRight(c);
                                    }
                                    else if(w.attackLeft)
                                    {
                                        w.knockbackRight(c);
                                    }
                                    else
                                    {
                                        w.knockbackRight(c);
                                    }
                                    delete = true;
                                    delete();
                                }
                                catch(NullPointerException excep)
                                {
                                    System.out.println("Failed to hit creature!");
                                }
                            }
                        }
                    }
                }
            }
        }
        else
        {
            //System.out.println("Creature using it");
            for(Entity e : handler.getEntityManager().getEntities())
            {
                if(e != w.cre)
                {
                    if(e instanceof Player)
                    {
                        Player pla = (Player)w.cre;
                        Rectangle eR = new Rectangle((int)(pla.x + pla.bounds.x),(int)(pla.y + pla.bounds.y),pla.bounds.width,pla.bounds.height);
                        if(eR.intersects(attack))
                        {      
                            if(pla.hurt(getDamage() + w.cre.damage))
                            {
                                pla.die();
                                delete = true;
                                delete();
                                return;
                            }
                            if(w.attackRight)
                            {
                                w.knockbackRight(pla);
                            }
                            else if(w.attackUp)
                            {
                                w.knockbackRight(pla);
                            }
                            else if(w.attackLeft)
                            {
                                w.knockbackRight(pla);
                            }
                            else
                            {
                                w.knockbackRight(pla);
                            }
                            delete = true;
                            delete();
                        }
                    }
                }
            }
        }
    }

    public void update()
    {       
        //System.out.println("Attack Range: " + attackRange);
        collision();
        if(inRange())
        {
            //System.out.println("Updating");
            x += velX;
            y += velY;
            attack.x = (int)x + bounds.x;
            attack.y = (int)y + bounds.y;
        }
        else
        {
            //System.out.println("Deleting");
            if(!delete)
            {
                delete();
            }
        }
    }

    public boolean inRange()
    {
        //float distance = (float)Math.sqrt(Math.pow((y - spawnY),2) + Math.pow((x - spawnX),2));
        //System.out.println("Attack: " + attack);
        if(attackRange.contains(attack)/*distance < range*/)
        {
            return true;
        }
        return false;
    }

    public void render(Graphics g)
    {
        if(inRange())
        {            
            //g.setColor(Color.YELLOW);
            //g.fillRect(attackRange.x,attackRange.y,attackRange.width,attackRange.height);
            g.setColor(Color.RED);
            g.fillRect(attack.x,attack.y,attack.width,attack.height);
            //g.drawImage(img[0],(int)x,(int)y,width,height,null);
        }
    }

    //Shoot

    public void shootRight(Player pl)
    {
        //this.pl = pl;
        x = pl.x + pl.bounds.x + pl.bounds.width;
        y = pl.y + pl.bounds.y + (pl.bounds.height/2);
        //attackRange = x + range;
        //System.out.println("(" + x + "," + y + ")");
        //System.out.println("Range: " + range);
        attackRange.x = (int)(x - range);
        attackRange.y = (int)(y - range);
        attack.x = (int)x + bounds.x;
        attack.y = (int)y + bounds.y;
        //attackRange = new Rectangle((int)(x + bounds.x - range),(int)(y + bounds.y - range),(int)(range + bounds.width + range),(int)(range + bounds.height + range));
        velX = speed;
        velY = 0;
        handler.getItemManager().addItem(this);
    }

    public void shootLeft(Player pl)
    {
        //this.pl = pl;
        x = pl.x + pl.bounds.x;
        y = pl.y + pl.bounds.y + (pl.bounds.height/2);
        //attackRange = x - range;
        //System.out.println("(" + x + "," + y + ")");
        //System.out.println("Range: " + range);
        attackRange.x = (int)(x - range);
        attackRange.y = (int)(y - range);
        attack.x = (int)x + bounds.x;
        attack.y = (int)y + bounds.y;
        /*
        attackRange = new Rectangle((int)(x + bounds.x - range),(int)(y + bounds.y - range),(int)(range + bounds.width + range),(int)(range + bounds.height + range));
         */
        velX = -speed;
        velY = 0;
        handler.getItemManager().addItem(this);
    }

    public void shootUp(Player pl)
    {
        //this.pl = pl;
        x = pl.x + pl.bounds.x + (pl.bounds.width/2);
        y = pl.y + pl.bounds.y;
        //System.out.println("(" + x + "," + y + ")");
        //System.out.println("Range: " + range);
        attackRange.x = (int)(x - range);
        attackRange.y = (int)(y - range);
        attack.x = (int)x + bounds.x;
        attack.y = (int)y + bounds.y;
        //attackRange = new Rectangle((int)(x + bounds.x - range),(int)(y + bounds.y - range),(int)(range + bounds.width + range),(int)(range + bounds.height + range));
        velX = 0;
        velY = -speed;
        handler.getItemManager().addItem(this);
    }

    public void shootDown(Player pl)
    {
        //this.pl = pl;
        x = pl.x + pl.bounds.x + (pl.bounds.width/2);
        y = pl.y + pl.bounds.y + pl.bounds.height;
        //System.out.println("(" + x + "," + y + ")");
        //System.out.println("Range: " + range);
        attackRange.x = (int)(x - range);
        attackRange.y = (int)(y - range);
        attack.x = (int)x + bounds.x;
        attack.y = (int)y + bounds.y;
        //attackRange = new Rectangle((int)(x + bounds.x - range),(int)(y + bounds.y - range),(int)(range + bounds.width + range),(int)(range + bounds.height + range));
        velX = 0;
        velY = speed;
        handler.getItemManager().addItem(this);
    }

    //Getter Methods - returns the specified variables

    public float getVelX()
    {
        return velX;
    }

    public float getVelY()
    {
        return velY;
    }

    @Override
    public float getDamage()
    {
        return damage;
    }

    //Setter Methods - sets the specified variables

    public void setWeaponUser(Creature c)
    {
        w.setWeaponUser(c);
    }

    public void setWeapon(RangedWeapon w)
    {
        this.w = w;
    }

    public void setVelX(float velX)
    {
        this.velX = velX;
    }

    public void setVelY(float velY)
    {
        this.velY = velY;
    }

    public void setDamage()
    {
        this.damage = damage;
    }
}
