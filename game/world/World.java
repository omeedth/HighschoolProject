package game.world;

/**
 * Write a description of class World here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */

//Personally made classes
import game.Handler;

import game.util.Util;

import game.tile.Tile;

import game.display.Camera;

import game.entities.*;

import game.entities.item.*;

import game.entities.creature.*;

import game.entities.creature.state.Temper;

//Java API
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Point;

import java.util.ArrayList;
import java.util.Random;

/**
 * The world class will
 * 1.read world data from a file
 * 2.manage all rendering and updating for the world
 */
public class World
{
    //Static Variables
    public static int creatureCount = 0;
    
    
    //Variables
    private Handler handler;
    private Camera cam;
    private int width,height;
    public int spawnX, spawnY;
    private int[][] tiles;
    
    //private String path;
    
    //Entities
    private EntityManager entityManager;

    //Items
    private ItemManager itemManager;

    //Test Player
    //private ArrayList<Player> players;
    public Player p1;
    public Player p2;

    /**
     * World constructor
     * 1.Uses loadWorld() method
     * 2.Initializes the managers
     */
    public World(Handler handler)
    {
        this.handler = handler;                       
        //this.path = path;
    }

    public void loadData()
    {
        cam = new Camera(handler,0,0);
        entityManager = new EntityManager(handler,cam); 
        itemManager = new ItemManager(handler,cam);   
    }
    
    public void init()
    {
        for(Entity e : entityManager.getEntities())
        {
            e.init();
        }        
    }
    
    public void addEntities()
    {
        byte[] keys1 = {87,65,83,68,70,67,86,66}; //27 for ESCAPE
        byte[] keys2 = {38,37,40,39,73,74,75,76};
        //p1 = new Player(spawnX,spawnY,31,31,new Rectangle(0,0,31,31),Temper.Friendly,10,0,2.5f,handler,keys1);
        //p2 = new Player(2*spawnX,2*spawnY,31,31,new Rectangle(0,0,31,31),Temper.Friendly,10,0,2.5f,handler,keys2);
        //entityManager.addEntity(p1);
        //entityManager.addEntity(p2);
        
        //entityManager.addEntity(new Slime(300f,300f,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT,new Rectangle(0,0,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT),5,1,4,handler));
        //entityManager.addEntity(new Slime(100f,600f,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT,new Rectangle(0,0,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT),15,1,3,handler));
        //entityManager.addEntity(new Slime(600f,100f,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT,new Rectangle(0,0,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT),10,1,2,handler));
        //entityManager.addEntity(new Slime(600f,600,2*Creature.DEFAULT_CREATURE_WIDTH,2*Creature.DEFAULT_CREATURE_HEIGHT,new Rectangle(0,0,2*Creature.DEFAULT_CREATURE_WIDTH,2*Creature.DEFAULT_CREATURE_HEIGHT),30,3,1,handler)); 
    }
    
    /**
     * renders all objects in the world
     * Renders all objects VISIBLE ON THE SCREEN
     * 1.have a for loop for yAxis (the start is the upper most visible part
     *  of the screen while the end is the bottom most visible part of the screen)
     * 2.have a for loop for xAxis (the start is the left most visible part
     *  of the screen while the end is the right most visible part of the screen)
     */
    public void render(Graphics g)
    {
        //System.out.println("Xoffset: " + handler.getCamera().getXOffset() + ", Yoffset: " + handler.getCamera().getYOffset());        
        int xStart = (int) Math.max(0,(-handler.getCamera().getXOffset() / Tile.DEFAULT_TILE_WIDTH));
        int xEnd = (int) Math.min(width, ((-handler.getCamera().getXOffset() + handler.getWidth()) / Tile.DEFAULT_TILE_WIDTH + 1));
        int yStart = (int) Math.max(0,(-handler.getCamera().getYOffset() / Tile.DEFAULT_TILE_HEIGHT));
        int yEnd = (int) Math.min(height,((-handler.getCamera().getYOffset() + handler.getHeight()) / Tile.DEFAULT_TILE_HEIGHT + 1));

        //System.out.println("XTiles: (" + xStart + "," + xEnd + ") YTiles: " + "(" + yStart + "," + yEnd + ")");
        
        //int count = 0;
        
        for(int y = yStart; y < yEnd; y++)
        {
            for(int x = xStart; x < xEnd; x++)
            {
                //count++;
                getTile(y,x).render(g,(int) (x*Tile.DEFAULT_TILE_WIDTH/*+ handler.getCamera().getXOffset()*/) , (int) (y*Tile.DEFAULT_TILE_HEIGHT/*+ handler.getCamera().getYOffset()*/));
                //g.fillRect((int) (x*Tile.DEFAULT_TILE_WIDTH) , (int) (y*Tile.DEFAULT_TILE_HEIGHT), Tile.DEFAULT_TILE_WIDTH, Tile.DEFAULT_TILE_HEIGHT);
            }
        }

        //System.out.println("Rendering " + count + " tiles!");
        
        /*
        for(Player p : players)
        {
            p.render(g);
        }
         */

        //p1.render(g);
        entityManager.render(g);
        itemManager.render(g);
        
        //Graphics2D g2 = (Graphics2D)g;
        //g2.setColor(Color.RED);
        //g2.drawRect(cam.bounds.x,cam.bounds.y,cam.bounds.width,cam.bounds.height);
    }

    /**
     * updates all objects in the world
     * Updates all objects VISIBLE ON THE SCREEN
     * 1.have a for loop for yAxis (the start is the upper most visible part
     *  of the screen while the end is the bottom most visible part of the screen)
     * 2.have a for loop for xAxis (the start is the left most visible part
     *  of the screen while the end is the right most visible part of the screen)
     */
    public void update()
    {
        /*
        for(Player p : players)
        {
            p.update();
        }
        */

        //p1.update();
        entityManager.update();
        itemManager.update();
        handler.getSpawnManager().update();
    }

    /**
     * Returns the Tile of the numerical id value in the tiles[][] array
     */
    public Tile getTile(int x, int y)
    {
        if(x < 0 ||  y < 0 || x >= width || y >= height)
            return Tile.grassTile;

        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
            return Tile.dirtTile;
        return t;
    }

    /**
     * loads the world
     * 1.takes in a path name
     * 2. reads from a text file with all of the world data as a String
     * 3. create an array of tokens with the string
     * 4. cycle through the tokens and parse them as Integers
     */
    public void loadWorld(String path)
    {
        String file = Util.loadFileAsString(path);
        String[] tokens = file.split("\\s+");

        spawnX = Util.parseInt(tokens[0]);
        spawnY = Util.parseInt(tokens[1]);
        width = Util.parseInt(tokens[2]);
        height = Util.parseInt(tokens[3]);

        tiles = new int[height][width];

        for(int row = 0; row < height; row++)
        {
            for(int col = 0; col < width; col++)
            {
                tiles[row][col] = Util.parseInt(tokens[(col + row* width) + 4]);
            }
        }

        loadData();
        
        addEntities();
        init();
        
        /* Testing Purposes
        for(int[] i : tiles)
        {
        for(int j : i)
        {
        System.out.print("" + j + " "); 
        }
        System.out.println();
        }
         */
    }

    //Getter

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    /*
    public String getPath()
    {
        return path;
    }
    */
    
    public EntityManager getEntityManager()
    {
        return entityManager;
    }

    public ItemManager getItemManager()
    {
        return itemManager;
    }

    /*
    public ArrayList<Player> getPlayerList()
    {
        return players;
    }
    */
   
    public int[][] getTilesArray()
    {
        return tiles;
    }

    public Camera getCamera()
    {
        return cam;
    }
    
    //Setter
    
    /*
    public void setPath(String path)
    {
        this.path = path;
    }
    */
}
