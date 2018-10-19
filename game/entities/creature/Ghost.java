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

//JAVA API
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;

import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Random;

public class Ghost extends Creature implements Movable, Idle, Damaging
{   
    public static int ghostCount = 0;

    private AI ghost;
    private int moveCount;

    private Animation ghostA;

    HealthBar healthBar;

    /**
     * Constructor for objects of class Slime
     */
    public Ghost(float x, float y, int width, int height, Rectangle bounds, float health, float damage, float speed, Handler handler)
    {
        super(x,y,width,height,bounds,Temper.Hostile,health,damage,speed,handler,5);

        moveCount = 0;

        bounds.x = width / 7;
        bounds.y = height / 5;
        bounds.width -= (width /3);
        bounds.height -= (height / 2);

        System.out.println("Experience from Ghost: " + experienceGranted);
        //experienceGranted = (int)((health * 5) + (damage * 20) + (speed * 10));

        ghostA = new Animation(10,Assets.ghost1,Assets.ghost2,Assets.ghost3,Assets.ghost2);   

        init();
    }

    public void init()
    {
        healthBar = new HealthBar(width,HealthBar.DEFAULT_HEIGHT,this);        
        //System.out.println("Initializing Random Spawn!");
        //spawn.init();
        ghost = new AI(this,this,null,this);                        
    }

    public void die()
    {
        ghost.stop();
        ghostCount--;
        handler.getEntityManager().addKillList(this);
        //handler.getEntityManager().removeEntity(this);
    }

    @Override
    public synchronized void action()
    {
        //Test if a Player is in range (10 blocks away all sides)
        //System.out.println("Slime AI performing action!");
        if(ghost.running)
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
                        if(hit)
                        {      
                            Point p = null;
                            do
                            {
                                canTeleport = true;
                                //p = Util.randomLocationWithin((int)(-handler.getCamera().getXOffset() + (Tile.DEFAULT_TILE_WIDTH * 3)),(int)(-handler.getCamera().getYOffset()) + (Tile.DEFAULT_TILE_HEIGHT * 3),handler.getWidth() - (Tile.DEFAULT_TILE_WIDTH * 3),handler.getHeight() - (Tile.DEFAULT_TILE_HEIGHT * 3));
                                p = Util.randomLocationWithin((int)(-handler.getCamera().getXOffset()),(int)(-handler.getCamera().getYOffset()),handler.getWidth(),handler.getHeight());
                                for(Entity en : handler.getEntityManager().getEntities())
                                {
                                    if(en instanceof Player)
                                    {
                                        Player pl = (Player)en;
                                        if(pl.playerSpace.contains(p))
                                        {
                                            canTeleport = false;
                                            break;
                                        }
                                    }
                                }
                                //p.x *= Tile.DEFAULT_TILE_WIDTH;
                                //p.y *= Tile.DEFAULT_TILE_HEIGHT;
                                System.out.println("Point: (" + p.x + "," + p.y + ")");
                            }while(!(handler.getCamera().bounds.contains(p)) && canTeleport);
                            x = p.x;
                            y = p.y;
                            hit = false;
                            teleport = true;
                            //update();
                        }
                        else
                        {
                            ghost.moveIgnoreBoth(e.getX() + e.bounds.x, e.getY() + e.bounds.y);
                        }
                    }
                    //Player p = (Player)e;
                    //aMove(p);
                    return;
                }
            }
        }

        actionIdle();
    }    

    /*
    public void aMove(Player p)
    {
    if(x < p.x) // go right
    {
    velX = speed;
    }
    else if(x > p.x) // go left
    {
    velX = -speed;
    }    

    if(y < p.y) // go down
    {
    velY = speed;
    }
    else if(y > p.y) // go up
    {
    velY = -speed;
    }
    ghost.move();
    }
     */

    @Override
    public void actionMove(float x, float y)
    {
        if(ghost != null)
        {            
            ghost.move();
        }
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
            ghostA.runAnimation();
        }
    }

    /**
     * 1. Get possible moves 
     *      a) test if you can move in every direction
     */
    @Override
    public void actionIdle()
    {
        if(ghost != null)
        {
            ghost.moveIdleGhost();
        }
    }

    @Override
    public void render(Graphics g)
    {       
        //if(healthBar != null)
        healthBar.render(g,x,y);
        //Rectangle eR = new Rectangle((int)(x + bounds.x),(int)(y + bounds.y),bounds.width,bounds.height);
        if(velX == 0 && velY == 0)
        {
            ghostA.reset();
            g.drawImage(Assets.ghost1,(int)x,(int)y,width,height,null);            
        }
        else
        {
            ghostA.drawAnimation(g,(int)x,(int)y,width,height);
        }
        //g.setColor(Color.BLUE);
        //g.fillRect(eR.x,eR.y,eR.width,eR.height); //bounds
        //g.fillRect((int)(x + bounds.x),(int)(y + bounds.y),bounds.width,bounds.height);
    }
}
