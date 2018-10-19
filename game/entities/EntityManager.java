package game.entities;

/**
 * Write a description of class EntityManager here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */

//personally made classes imports
import game.Handler;

import game.display.Camera;

import game.entities.creature.*;

//Java API imports
import java.awt.Graphics;
import java.awt.Rectangle;

import java.util.ArrayList;

public class EntityManager
{
    //variables
    private Handler handler;
    private Camera cam;

    private ArrayList<Entity> entities; //Consider changing into LinkedList
    private ArrayList<Entity> killList;

    /**
     * initialize the variables
     */
    public EntityManager(Handler handler,Camera cam)
    {
        this.handler = handler;
        this.cam = cam;
        entities = new ArrayList<>();
        killList = new ArrayList<>();
    }

    /////////////// WARNING NOT USING ITERATORS MIGHT CAUSE ENTITIES TO BE SKIPPED WHEN REMOVED //////////////////

    /**
     * Renders all entities by cycling through the entities
     *1. for loop cycling through the entities
     */
    public synchronized void render(Graphics g)
    {
        for(Entity e: entities)
        {
            /*
            if(e instanceof Player)
            {
            e.render(g);
            }
            else */if(cam.bounds.intersects(new Rectangle((int)(e.getX() + e.bounds.x),(int)(e.getY() + e.bounds.y),e.bounds.width,e.bounds.height)))
            {
                e.render(g);
                //System.out.println("Rendering");
            }
            else
            {
                if(e.teleport)
                {
                    e.render(g);
                    e.teleport = false;
                }
                //System.out.println("Not Rendering");
            }
        }
    }

    /**
     * updates all entities by cycling through the entities VISIBLE ON THE SCREEN (the update range is farther)
     * 1.have a for loop for yAxis (the start is the upper most visible part
     *  of the screen while the end is the bottom most visible part of the screen)
     * 2.have a for loop for xAxis (the start is the left most visible part
     *  of the screen while the end is the right most visible part of the screen)
     */
    public synchronized void update()
    {
        for(Entity e: entities)
        {
            if(e instanceof Player)
            {
                e.update();
            }
            else if(cam.bounds.intersects(new Rectangle((int)(e.getX() + e.bounds.x),(int)(e.getY() + e.bounds.y),e.bounds.width,e.bounds.height)))
            {
                e.inBounds = true;
                e.update();
            }
            else 
            {
                e.inBounds = false;
            }
        }
        //if(killList.size() > 0)  
        if(killList.size() > 0)
        {
            for(Entity e : killList)
            {
                //if(!e.alive)
                entities.remove(e);
            }
            killList.clear();
            //kSize = killList.size();
        }
        //killList.clear();
    }

    public void clearSpawnCount()
    {
        Slime.slimeCount = 0;
        Ghost.ghostCount = 0;
    }

    /**
     * Removes an Entity from the list
     * 1.gets passed in an entity to remove
     * 2.call the list name and remove the passed in entity
     */
    public void removeEntity(Entity e)
    {
        entities.remove(e);
    }

    /**
     * Adds an Entity from the list
     * 1.gets passed in an entity to add
     * 2.call the list name and add the passed in entity
     */
    public void addEntity(Entity e)
    {
        entities.add(e);   
    }

    /**
     * Adds an Entity from the list
     * 1.gets passed in an entity to add
     * 2.call the list name and add the passed in entity
     */
    public void addKillList(Entity e)
    {
        killList.add(e);   
    }

    //Getters        

    /**
     * Returns the list of entities
     */
    public ArrayList<Entity> getEntities()
    {
        return entities;
    }

    /**
     * Returns the list of entities
     */
    public ArrayList<Entity> getKillList()
    {
        return killList;
    }
}
