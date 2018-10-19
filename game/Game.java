package game;


/**
 * Write a description of class Game here.
 * 
 * @author Alex Thomas
 * @version (a version number or a date)
 */

//Imports all personally made classes
import game.display.*;

import game.world.*;

import game.state.*;

import game.gfx.*;

import game.input.*;

import game.tile.*;

import game.entities.*;

import game.util.Util;

import game.entities.creature.*;

import game.entities.item.Weapons;

//Imports all Java API
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Point;

import java.awt.image.BufferStrategy;

import java.util.ArrayList;
import java.util.Random;

import java.io.File;
import java.nio.file.Files;

/**
 * Game Loop
 * By: Alex Thomas
 */
public class Game implements Runnable
{
    //Variables    
    private int width, height;
    private String title;
    private boolean running;
    
    //Input
    MouseManager mouse;
    KeyManager  keys;
    
    //Display
    private Window window;
    private Camera cam;
    private Handler handler;
    
    //State
    public State gameState;
    public State menuState;
    public State optionState;
    public State controlState;
    public State saveState;
    public State saveStateMultiPlayer;
    
    //World
    private World world;
    
    //Weapons
    private Weapons weapons;
    
    //Music
    private Music music;
    
    //Thread
    private Thread thread; 
    
    //Saves
    public Saves save;
    
    //SpawnManager
    SpawnManager spawns;
            
    /**
     * This is the Game Constructor we will
     * 1. initializes the title, width, and height variables as well as some others
     */
    public Game(String title,int width,int height)
    {
        running = false;
        this.title = title;
        this.width = width;
        this.height = height;        
        mouse = new MouseManager(handler);
        keys = new KeyManager(handler);        
    }
    
    public void reset()
    {
        int size = handler.getEntityManager().getEntities().size();
        for(int index = size - 1; index >= 0; index--)
        {
            Creature c = ((Creature)handler.getEntityManager().getEntities().get(index));
            if(!(c instanceof Player))
                c.die();
            else 
                System.out.println("Player!");
        }
        //spawns.stop();
        //spawns.spawnSlime.stop();
        //spawns.spawnGhost.stop(); 
        File file = new File(Saves.saveFile);
        try
        {
            Files.delete(file.toPath());
        }
        catch(Exception e)
        {
            System.out.println("File did not exist!");
        }
        handler.getFrame().dispose();
        Game game = new Game("Final Project So Mr Dunlea Will Stop Talking And Let me Play Super SMash Flash", width, height);
        game.start(); 
    }
    
    /**
     * Initializes all the variables here
     * 1. creates a Window for the game by calling the Window Class's constructor
     */
    public void init()
    {
        window = new Window(title,width,height); 
        Assets.init();
        
        handler = new Handler(this); 
        
        weapons = new Weapons(handler);
        weapons.init();
        
        save = new Saves(handler);
        
        music = new Music(null/*"res/Sound/woman.wav"*/);
        music.start();        
        
        //cam = new Camera(handler,0,0);              
        
        window.getCanvas().addMouseListener(mouse);
        window.getCanvas().addMouseMotionListener(mouse);
        window.getCanvas().addKeyListener(keys);
        window.getWindow().addMouseListener(mouse);
        window.getWindow().addMouseMotionListener(mouse);
        window.getWindow().addKeyListener(keys);
        
        world = new World(handler);
        world.loadWorld("World.txt");
        
        spawns = new SpawnManager(handler);
        //spawns.init();
        
        gameState = new GameState(handler,null/*"res/Sound/woman.wav"*/);
        optionState = new OptionState(handler,"res/Sound/titlesong.wav");
        controlState = new ControlState(handler,"res/Sound/titlesong.wav");
        saveState = new SaveState(handler,"res/Sound/titlesong.wav");
        saveStateMultiPlayer = new SaveStateMultiPlayer(handler,"res/Sound/titlesong.wav");
        menuState = new MenuState(handler,"res/Sound/titlesong.wav");       
        State.setState(menuState);         
        //window.getWindow().setVisible(true);
    }
    
    public void loadWorld()
    {
        world.loadWorld("World.txt");
    }
    
    /**
     * This is the game loop
     * 1. run the init() method
     * 2. create game loop (timer) <while the game is running>
     *      > render()
     *      > update()
     */
    @Override
    public void run()
    {
        init();
        
        long currNano = System.nanoTime();
        long lastNano = currNano;
        long lastMillis = System.currentTimeMillis();
        double delta = 0;
        int fps = 60;
        int frames = 0;
        int updates = 0;
        double secondsPerFrame = 1_000_000_000 / fps;  
        
        //spawnSlime.init();
        //spawnGhost.init();
        
        while(running)
        {
            currNano = System.nanoTime();
            delta += (currNano - lastNano) / secondsPerFrame;
            lastNano = currNano;
            
            if(delta >= 1)
            {
                delta--;
                frames++;
                updates++;
                if(frames % 2 == 0)
                    render();
                //render();
                update();
            }
            
            if((System.currentTimeMillis() - lastMillis) >= 1000)
            {
                lastMillis += 1000;
                lastNano = currNano;
                //System.out.println("Frames/Updates: " + frames);
                frames = 0;
                updates = 0;
            }
        }
        handler.getMusic().stop();
        reset();
    }
    
    /**
     * Updates all variables in game
     * - calls any other classes tick method
     * 1. updates the camera
     * 2. updates according to the currentState
     */
    public synchronized void update()
    {
        if(State.getState() != null)
        {
            State.getState().update();
        }
        else
            System.out.println("problem detecting which state the game is in!!!");
    }
    
    /**
     * renders all objects in the game
     * - calls any other classes render method
     * 1. getBufferStrategy() of the canvas
     * 2. if the buffer strategy is null
     *      > createBufferStrategy();
     * 3. make a Graphics object equal to the graphics of the buffer strategy
     * 4. make the Grapphics2D object with the Graphics object
     * 5. translate() the screen using the camera
     * 6. render everything - currentState.render(g);
     *      > if currentState is the gameState then world.render()
     *      > if currentState is the menuState then menu.render()
     * 7. bs.show() and g.dispose() to show the graphics and dispose of the graphics until we use it again
     */
    public synchronized void render()
    {
        BufferStrategy bs = window.getCanvas().getBufferStrategy();
        
        if(bs == null)
        {
            window.getCanvas().createBufferStrategy(2);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        
        //ClearScreen
        g.clearRect(0,0,width,height);                 
        
        ////////////////////////////
        //Begin Drawing
                            
        if(State.getState() != null)
        {
            State.getState().render(g);
        }
        
        //End Drawing
        ////////////////////////////
        
        g.dispose();
        bs.show();
    }
    
    /**
     * Creates a thread of this class
     * Starts the thread (Game Loop)
     * 1. tests if the game is NOT already started
     *      > if not then start the game by initializing the thread and start() -ing it
     */
    public synchronized void start()
    {
        if(!running)
        {
            thread = new Thread(this,"Game Main");
            running = true;
            thread.start();
        }
    }
    
    /**
     * deletes a thread of this class
     * stops the thread (Game Loop)
     * 1. tests if the game is already stopped
     *      > if yes then stop the game by join() -ing this thread with the main thread
     */
    public synchronized void stop()
    {
        try
        {
           if(running)
           {
               //thread.join();
               running = false;               
           }
        }
        catch(Exception e)
        {
            System.out.println("error");
        }
    }
    
    /**
     * get Width
     */
    public int getWidth()
    {
        return width;
    }
    
    /**
     * get Height
     */
    public int getHeight()
    {
        return height;
    }
    
    /**
     * Returns the current game object
     */
    public Game getGame()
    {
        return this;
    }
    
    /**
     * Returns the game thread
     */
    public Thread getThread()
    {
        return thread;
    }
    
    /**
     * Returns the game camera
     */
    public Camera getCamera()
    {
        return cam;
    }
    
    /**
     * Returns the game world
     */
    public World getWorld()
    {
        return world;
    }
    
    /**
     * Returns the window object
     */
    public Window getWindow()
    {
        return window;
    }
    
    /**
     * Returns MouseManager
     */
    public MouseManager getMouseManager()
    {
        return mouse;
    }
    
    /**
     * Returns KeyManager
     */
    public KeyManager getKeyManager()
    {
        return keys;
    }
    
    public Music getMusic()
    {
        return music;
    }
    
    public SpawnManager getSpawnManager()
    {
        return spawns;
    }
}
