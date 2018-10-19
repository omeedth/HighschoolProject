package game.entities.creature;

/**
 * Write a description of class Slime here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

//Personal Made Classes
import game.Handler;

import game.util.Util;

import game.gfx.*;

import game.tile.Tile;

import game.state.State;

import game.entities.creature.state.Temper;

import game.entities.*;

import game.entities.item.*;

//JAVA API
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;

import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Random;

public class Slime extends Creature implements Movable, Idle, Damaging
{   
    public static int slimeCount = 0;

    private AI slime;    

    private Animation slimeA;

    HealthBar healthBar;

    int moveCount;

    /**
     * Constructor for objects of class Slime
     */
    public Slime(float x, float y, int width, int height, Rectangle bounds, float health, float damage, float speed, Handler handler)
    {
        super(x,y,width,height,bounds,Temper.Hostile,health,damage,speed,handler,5);

        bounds.x = width / 7;
        bounds.y = height / 5;
        bounds.width -= (width /3);
        bounds.height -= (height / 2);

        moveCount = 0;
        //experienceGranted = (int)((health * 5) + (damage * 20) + (speed * 10));

        slimeA = new Animation(30,Assets.slime1,Assets.slime2);       
        init();
    }

    public void init()
    {           
        healthBar = new HealthBar(width,HealthBar.DEFAULT_HEIGHT,this);
        //System.out.println("Initializing Random Spawn!");
        //spawn.init();
        slime = new AI(this,this,null,this);

        slime.idleTime = 30;
        slime.idleWait = 30;
        slime.moveWait = 30;
    }

    public void die()
    {
        if(Math.random() < .33)
        {
            handler.getItemManager().addAddList(new Heart((int)x + bounds.x + width/2,(int)y + bounds.y + height/2, width/2, height/2,new Rectangle(0,0,width/2,height/2),handler,0));
        }
        //handler.getItemManager().addAddList(new Heart((int)x + bounds.x + width/2,(int)y + bounds.y + height/2, width/2, height/2,new Rectangle(0,0,width/2,height/2),handler,0));
        /*
        if(Math.random() < .5)
        {
        handler.getItemManager().addItem(new Heart((int)x + bounds.x + width/2,(int)y + bounds.y + height/2, width/2, height/2,new Rectangle(0,0,width,height),handler,0));
        }
         */
        slime.stop();
        slimeCount--;
        handler.getEntityManager().addKillList(this);        
        //handler.getEntityManager().removeEntity(this);
    }

    @Override
    public synchronized void action()
    {
        //Test if a Player is in range (10 blocks away all sides)
        //System.out.println("Slime AI performing action!");
        if(slime.running)
        {
            for(Entity e : handler.getEntityManager().getEntities())
            {
                if(e instanceof Player)
                {
                    //System.out.println("Found player!");
                    //System.out.println("Player location: (" + e.getX() + "," + e.getY() + ")");
                    if(Math.abs((e.getX() / Tile.DEFAULT_TILE_WIDTH) - (x / Tile.DEFAULT_TILE_WIDTH)) <= 10 && Math.abs((e.getY() / Tile.DEFAULT_TILE_HEIGHT) - (y / Tile.DEFAULT_TILE_HEIGHT)) <= 10)
                    {
                        //System.out.println("Found player in range to attack!");
                        //slime.move(e.getX(),e.getY());
                        if(hit)
                        {            
                            moveCount = 0;
                            if(hitRight)
                            {
                                velX = knockbackX;
                            }
                            else if(hitLeft)
                            {
                                velX = -knockbackX;
                            }

                            if(hitUp)
                            {
                                velY = -knockbackY;
                            }
                            else if(hitDown)
                            {
                                velY = knockbackY;
                            }

                            move();

                            knockbackTimer++;
                            if(knockbackTimer > knockbackTime)
                            {
                                knockbackTimer = 0;
                                hit = false;
                                hitRight = false;
                                hitLeft = false;
                                hitUp = false;
                                hitDown = false;
                            }
                        }
                        else
                        {
                            moveCount++;
                            if(moveCount < 30)
                            {                        
                                actionMove(e.getX() + e.bounds.x, e.getY() + e.bounds.y);
                            }
                            if(moveCount >= 60)
                            {
                                moveCount = 0;
                            }
                        }
                        return;
                    }
                }
            }

            actionIdle();
        }
    }

    @Override
    public void actionMove(float x, float y)
    {
        if(slime != null)
            slime.move(x,y);
    }

    public void update()
    {
        //if(healthBar != null)
        healthBar.update();
        if(invincible)
            invTimer++;
        if(invTimer > INVINCIBILITY_TIME - 20)
        {
            //System.out.println("Not invincible anymore!");
            invTimer = 0;
            invincible = false;
        }
        if(velX != 0 || velY != 0)
        {
            slimeA.runAnimation();
        }
    }

    /**
     * 1. Get possible moves 
     *      a) test if you can move in every direction
     */
    @Override
    public void actionIdle()
    {
        if(slime != null)
            slime.moveIdle();
    }

    @Override
    public void render(Graphics g)
    {       
        //if(healthBar != null)
        healthBar.render(g,x,y);
        //Rectangle eR = new Rectangle((int)(x + bounds.x),(int)(y + bounds.y),bounds.width,bounds.height);
        if(velX == 0 && velY == 0)
        {
            slimeA.reset();
            g.drawImage(Assets.slime1,(int)x,(int)y,width,height,null);            
        }
        else
        {
            slimeA.drawAnimation(g,(int)x,(int)y,width,height);
        }
        //g.setColor(Color.BLUE);
        //g.fillRect(eR.x,eR.y,eR.width,eR.height); //bounds
        //g.fillRect((int)(x + bounds.x),(int)(y + bounds.y),bounds.width,bounds.height);
    }
}
