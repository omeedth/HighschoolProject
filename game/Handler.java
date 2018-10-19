package game;

/**
 * Write a description of class Handler here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */

//Imports for Personally made classes
import game.display.*;

import game.world.*;

import game.gfx.*;

import game.input.*;

import game.entities.*;

import game.entities.creature.*;

import game.entities.item.*;

//Imports for Java API
import java.awt.Canvas;

import javax.swing.JFrame;

import java.util.ArrayList;

/**
 * Holds all the useful project classes for easy access
 */
public class Handler
{
    //Variables
    private Game game;
    //private ArrayList<Player> players;

    /**
     * Constructor that initializes all important class variables here
     */
    public Handler(Game game)
    {
        this.game = game;
    }

    /*
    public void addPlayer(Player p)
    {
        players.add(p);
    }
    
    public void removePlayer(Player p)
    {
        players.remove(p);
    }
    */
   
    ///////////////////////////////////////
    
    /**
     * get Width
     */
    public int getWidth()
    {
        return game.getWidth();
    }
    
    /**
     * get Height
     */
    public int getHeight()
    {
        return game.getHeight();
    }
    
    /**
     * Returns Game
     */
    public Game getGame()
    {
        return game;
    }
    
    /**
     * Returns Game
     */
    /*
    public ArrayList<Player> getPlayers()
    {
        return players;
    }
    */
    /**
     * returns Saves object
     */
    public Saves getSaves()
    {
        return game.save;
    }
   
    /**
     * Returns MouseManager
     */
    public MouseManager getMouseManager()
    {
        return game.getMouseManager();
    }
    
    /**
     * Returns KeyManager
     */
    public KeyManager getKeyManager()
    {
        return game.getKeyManager();
    }
    
    /**
     * Returns Camera
     */
    public Camera getCamera()
    {
        return game.getWorld().getCamera();
    }
    
    /**
     * Returns World
     */
    public World getWorld()
    {
        return game.getWorld();
    }
    
    /**
     * Returns World
     */
    public Canvas getCanvas()
    {
        return game.getWindow().getCanvas();
    }
    
    /**
     * Returns JFrame
     */
    public JFrame getFrame()
    {
        return game.getWindow().getWindow();
    }
    
    /**
     * Returns entity manager
     */
    public EntityManager getEntityManager()
    {
        return getWorld().getEntityManager();
    }
    
    /**
     * Returns entity manager
     */
    public ItemManager getItemManager()
    {
        return getWorld().getItemManager();
    }
    
    public Music getMusic()
    {
        return game.getMusic();
    }
    
    public SpawnManager getSpawnManager()
    {
        return game.getSpawnManager();
    }
}
