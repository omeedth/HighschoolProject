package game.entities;

/**
 * Write a description of class Spawn here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import game.Handler;

import game.state.State;

import java.awt.Point;
import java.awt.Graphics;

public class Spawn /*implements Runnable*/
{
    public static boolean correctState = false;
    static int count = 1;

    //Variables
    //private Thread thread;
    private Spawnable spawn;
    private Entity e;    
    private Handler handler;

    private int timer;
    private int speed;

    private boolean running;

    public Spawn(/*Entity e,*/Handler handler, int speed, Spawnable spawn)
    {
        running = true;
        this.e = e;
        this.handler = handler;
        timer = 0;
        this.speed = speed;
        this.spawn = spawn;
        //thread = new Thread(this,"Spawn "/* + e.getClass()*/);  
    }

    public void init()
    {                 
        System.out.println("Initializing!");
        //thread.start();
        count++;
    }

    public synchronized void spawn()
    {
        spawn.canSpawn();
    }

    /*
    public synchronized void pause()
    {
        thread.interrupt();
    }
    
    public synchronized void resume()
    {
        //thread.notify();
        thread.interrupted();
    }
    
    public synchronized void stop()
    {
        if(running)
        {
            thread.interrupt();
            running = false;
        }
    }
    */

    /*
    public void run()
    {        
        long currNano = System.nanoTime();
        long lastNano = currNano;
        long lastMillis = System.currentTimeMillis();
        double delta = 0;
        int fps = 10;
        int frames = 0;
        int updates = 0;
        double secondsPerFrame = 1_000_000_000 / fps;  

        while(running)
        {
            boolean inGame = State.inGameState;
            if(inGame)
            {
                currNano = System.nanoTime();
                delta += (currNano - lastNano) / secondsPerFrame;
                lastNano = currNano;

                if(delta >= 1)
                {
                    delta--;
                    //frames++;
                    //updates++;
                    update();
                }

                if((System.currentTimeMillis() - lastMillis) >= 1000)
                {
                    lastMillis += 1000;
                    lastNano = currNano;
                    //System.out.println("Frames/Updates: " + frames);
                    //frames = 0;
                    //updates = 0;
                }
            }
            else
            {
                currNano = System.nanoTime();
                lastNano = currNano;
            }
        }
    }
    */

    public void update()
    {
        timer++;
        //System.out.println("Updating Spawn");
        if(timer >= speed)
        {
            timer = 0;
            //System.out.println("Spawning");
            spawn();
        }
    }
    
    public void render(Graphics g, int x, int y, int width, int height)
    {
        g.fillRect(x,y,width,height);
    }
}
