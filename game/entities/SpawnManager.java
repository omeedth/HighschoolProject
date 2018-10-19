package game.entities;

/**
 * Write a description of class SpawnManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import game.Handler;

import game.util.Util;

import game.tile.Tile;

import game.entities.creature.*;

import java.util.ArrayList;
import java.util.Random;

import java.awt.Rectangle;
import java.awt.Point;

public class SpawnManager
{
    //List
    ArrayList<Spawn> spawnList;

    //Variables
    public boolean waiting;

    //Spawn
    private Spawn spawnSlime;
    private Spawn spawnGhost;

    private Handler handler;

    /**
     * Constructor for objects of class SpawnManager
     */
    public SpawnManager(Handler handler)
    {
        this.handler = handler;
        waiting = false;
        spawnList = new ArrayList<>();
        spawnSlime = new Spawn(handler,180, new Spawnable(){
                @Override
                public void canSpawn()
                {                
                    ArrayList<Rectangle> rs = new ArrayList<>();
                    boolean canMove;
                    int maxLevel = 1;
                    int spawnDistance = 20;
                    Point p = null;
                    Point newP = null;
                    if(Slime.slimeCount < Math.min(Player.level, 10))
                    {
                        do
                        {
                            canMove = true;
                            p = Util.randomLocation(handler.getWorld().getWidth(),handler.getWorld().getHeight());
                            int px = p.x;
                            int py = p.y;
                            if(!handler.getWorld().getTile(px,py).isSolid())
                            {
                                newP = new Point(p.x * Tile.DEFAULT_TILE_WIDTH, p.y * Tile.DEFAULT_TILE_HEIGHT);
                                int npx = newP.x;
                                int npy = newP.y;
                                for(Entity e : handler.getEntityManager().getEntities())
                                {
                                    if(e instanceof Player)
                                    {
                                        for(Entity en : handler.getEntityManager().getEntities())
                                        {
                                            if(en instanceof Player)
                                            {
                                                Player pl = (Player)en;
                                                if(pl.level > maxLevel)
                                                    maxLevel = pl.level;
                                                if(pl.playerSpace.contains(p))
                                                {
                                                    canMove = false;
                                                    break;
                                                }
                                            }
                                        }    

                                        //Player pl = (Player)e;
                                        //if(pl.level > maxLevel)
                                        //    maxLevel = pl.level;
                                        /*
                                        float xMin = Math.max(e.x - (spawnDistance * Tile.DEFAULT_TILE_WIDTH),0);
                                        float xMax = Math.min(e.x + (spawnDistance * Tile.DEFAULT_TILE_WIDTH), handler.getWorld().getWidth() * Tile.DEFAULT_TILE_WIDTH);
                                        float yMin = Math.min(e.y + (spawnDistance * Tile.DEFAULT_TILE_HEIGHT),handler.getWorld().getWidth() * Tile.DEFAULT_TILE_HEIGHT);
                                        float yMax = Math.max(e.y - (spawnDistance * Tile.DEFAULT_TILE_HEIGHT),0);
                                        rs.add(new Rectangle((int)xMin,(int)yMin,(int)(xMax - xMin),(int)(yMax - yMin)));
                                         */
                                    }
                                    if(!canMove)
                                        break;
                                }
                                for(Rectangle r : rs)
                                {
                                    if(r.contains(newP))
                                    {
                                        System.out.println("Point: (" + newP.x + "," + newP.y + ") is not in " + r);
                                        System.out.println("Can't move");
                                        canMove = false;
                                    }
                                }
                            }
                        }while(!canMove);   
                        Random r = new Random();
                        int widHei = Math.min(r.nextInt(maxLevel * 16) + 32, 320);
                        float hp = widHei / 32f + (maxLevel * .3f);
                        float atk = widHei / 32f + (maxLevel * .03f);
                        float spd = Math.min((320f/32) - ((float)widHei / 32) + (maxLevel * .3f), maxLevel * .5f + 1);                        
                        //Slime slimeS = new Slime(newP.x,newP.y,widHei,widHei,new Rectangle(0,0,widHei,widHei),hp,atk,spd,handler);
                        //handler.getEntityManager().addEntity(slimeS);
                        //handler.getEntityManager().addEntity(new Slime(newP.x,newP.y,widHei,widHei,new Rectangle(0,0,widHei,widHei),hp,atk,spd,handler).init());
                        //slimeS.init();

                        try
                        {
                            handler.getEntityManager().addEntity(new Slime(newP.x,newP.y,widHei,widHei,new Rectangle(0,0,widHei,widHei),hp,atk,spd,handler));
                            Slime.slimeCount++;
                        }
                        catch(NullPointerException e)
                        {
                            System.out.println("Null Slime");
                        }                         
                    }
                }                
            });

        spawnGhost = new Spawn(/*this,*/handler,180, new Spawnable(){
                @Override
                public void canSpawn()
                {                
                    if(Player.level > 2)
                    {
                        ArrayList<Rectangle> rs = new ArrayList<>();
                        boolean canMove;
                        int maxLevel = 1;
                        int spawnDistance = 20;
                        Point p = null;
                        Point newP = null;
                        if(Ghost.ghostCount < Math.min(Player.level / 3, 5))
                        {
                            do
                            {
                                canMove = true;
                                p = Util.randomLocation(handler.getWorld().getWidth(),handler.getWorld().getHeight());
                                int px = p.x;
                                int py = p.y;
                                if(!handler.getWorld().getTile(px,py).isSolid())
                                {
                                    newP = new Point(p.x * Tile.DEFAULT_TILE_WIDTH, p.y * Tile.DEFAULT_TILE_HEIGHT);
                                    int npx = newP.x;
                                    int npy = newP.y;
                                    for(Entity e : handler.getEntityManager().getEntities())
                                    {
                                        if(e instanceof Player)
                                        {
                                            for(Entity en : handler.getEntityManager().getEntities())
                                            {
                                                if(en instanceof Player)
                                                {
                                                    Player pl = (Player)en;
                                                    if(pl.level > maxLevel)
                                                        maxLevel = pl.level;
                                                    if(pl.playerSpace.contains(p))
                                                    {
                                                        canMove = false;
                                                        break;
                                                    }
                                                }
                                            } 
                                            /*
                                            Player pl = (Player)e;
                                            if(pl.level > maxLevel)
                                                maxLevel = pl.level;
                                            float xMin = Math.max(e.x - (spawnDistance * Tile.DEFAULT_TILE_WIDTH),0);
                                            float xMax = Math.min(e.x + (spawnDistance * Tile.DEFAULT_TILE_WIDTH), handler.getWorld().getWidth() * Tile.DEFAULT_TILE_WIDTH);
                                            float yMin = Math.min(e.y + (spawnDistance * Tile.DEFAULT_TILE_HEIGHT),handler.getWorld().getWidth() * Tile.DEFAULT_TILE_HEIGHT);
                                            float yMax = Math.max(e.y - (spawnDistance * Tile.DEFAULT_TILE_HEIGHT),0);
                                            rs.add(new Rectangle((int)xMin,(int)yMin,(int)(xMax - xMin),(int)(yMax - yMin)));
                                            */
                                        }
                                    }
                                    for(Rectangle r : rs)
                                    {
                                        if(r.contains(newP))
                                        {
                                            //System.out.println("Point: (" + newP.x + "," + newP.y + ") is not in " + r);
                                            //System.out.println("Can't move");
                                            canMove = false;
                                        }
                                    }
                                }
                            }while(!canMove);   
                            Random r = new Random();
                            int widHei = Math.min(r.nextInt(maxLevel * 16) + 32, 160);
                            float hp = widHei / 32f + (maxLevel * .1f);
                            float atk = widHei / 32f + (maxLevel * .05f);
                            float spd = Math.min((320f/32) - ((float)widHei / 32) + (maxLevel * .4f), maxLevel * .6f);
                            //Ghost ghostS = new Ghost(newP.x,newP.y,widHei,widHei,new Rectangle(0,0,widHei,widHei),hp,atk,spd,handler);
                            //handler.getEntityManager().addEntity(ghostS);
                            //ghostS.init();

                            try
                            {
                                handler.getEntityManager().addEntity(new Ghost(newP.x,newP.y,widHei,widHei,new Rectangle(0,0,widHei,widHei),hp,atk,spd,handler));
                                Ghost.ghostCount++;
                            }
                            catch(NullPointerException e)
                            {
                                System.out.println("Null Ghost");
                            }                                                            
                        }
                    }
                }                
            });
        spawnList.add(spawnSlime);
        spawnList.add(spawnGhost);
    }

    public synchronized void init()
    {
        for(Spawn s : spawnList)
        {
            s.init();
        }
    }

    public void update()
    {
        for(Spawn s : spawnList)
        {
            s.update();
        }
    }

    /*
    public synchronized void stop()
    {
    for(Spawn s : spawnList)
    {
    s.stop();
    }
    }

    public synchronized void pause()
    {
    waiting = true;
    for(Spawn s : spawnList)
    {
    s.pause();
    }
    }

    public synchronized void resume()
    {
    waiting = false;
    for(Spawn s : spawnList)
    {
    s.resume();
    }
    }
     */
}
