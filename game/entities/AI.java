package game.entities;

/**
 * Write a description of class AI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import game.entities.creature.*;

import game.entities.creature.state.Temper;

import game.tile.Tile;

import game.state.State;

import java.util.ArrayList;

import java.util.Random;

import java.awt.Point;

public class AI implements Runnable
{
    public static int count = 1;

    public float xMove;
    public float yMove;

    public int idleCount;
    public int moveCount;

    public int idleTime;
    public int idleWait;
    public int moveTime;
    public int moveWait;

    public boolean running;

    //int count;

    private Creature creature;
    private Movable mover;
    private Attackable attacker;
    private Idle idle;

    private Thread thread;

    /**
     * Constructor for objects of class AI
     */
    public AI(Creature creature, Movable mover, Attackable attacker, Idle idle)
    {
        this.creature = creature;
        thread = new Thread(this,"AI: " + count);
        count++;

        this.mover = mover;
        this.attacker = attacker;
        this.idle = idle;

        idleTime = 0;
        idleWait = 0;
        moveCount = 0;

        xMove = 0;
        yMove = 0;

        //count = 0;

        running = true;

        thread.start();
    }

    public synchronized void stop()
    {
        if(running)
        {
            //thread.interrupt();
            running = false;            
        }
    }

    public void init()
    {

    }

    public void run()
    {
        init();
        try
        {
            thread.sleep(200);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        long currNano = System.nanoTime();
        long lastNano = currNano;
        long timer = 0;
        //long lastMillis = System.currentTimeMillis();
        double delta = 0;
        int fps = 60;
        //int frames = 0;
        //int updates = 0;
        double secondsPerFrame = 1_000_000_000 / fps;  

        while(running)
        {            
            if(State.getState() == creature.handler.getGame().gameState && creature.inBounds)//State.inGameState)
            {
                currNano = System.nanoTime();
                delta += (currNano - lastNano) / secondsPerFrame;
                timer += (currNano - lastNano);
                lastNano = currNano;

                if(delta >= 1)
                {
                    delta--;
                    //frames++;
                    action();
                    //System.out.println("Active");
                }

                if(timer >= 1_000_000_000)
                {
                    timer = 0;
                    lastNano = currNano;                    
                    //System.out.println("Slime AI Updates/Frames: " + frames);
                    //frames = 0;
                }
            }
            else
            {
                currNano = System.nanoTime();
                lastNano = currNano;
                //System.out.println("Not Active");
            }
        }
        System.out.println("Ending Thread: " + thread);        
        creature.alive = false;
        //creature.handler.getGame().getThread().notify();
    }

    public void action()
    {
        //System.out.println("Starting action!");
        creature.action();
    }

    public void actionMove(float x, float y)
    {
        //idleCount = 0;
        //idleCount = 0;
        mover.actionMove(x,y);
        moveCount++;
    }

    public void actionAttack()
    {
        idleCount = 0;
    }

    //Move methods

    public int chooseRandomDir()
    {
        int[]randDir = new int [3];
        randDir[0] = 0;
        randDir[1] = 1;
        randDir[2] = -1;

        Random rand = new Random ();
        int randChoice = rand.nextInt(3);//((int)(Math.random() * 2) + 1);
        //System.out.println(randChoice);
        return randDir[randChoice];       

    }

    public void actionIdle()
    {
        //moveCount = 0;
        idle.actionIdle();
        idleCount++;
    }

    /**
     * 1. Gets possible moves
     * 2. Chooses random location out of those moves
     * 3. executes that move
     */
    public void move(float x, float y)
    {
        /*
        if(moveCount <= moveWait)
        {
        if(creature.x < x) // move right
        {
        creature.velX = creature.speed;
        }
        else if(creature.x > x) // move left
        {
        creature.velX = -creature.speed;
        }

        if(creature.y < y) // move down
        {
        creature.velY = creature.speed;
        }
        else if(creature.y > y) // move up
        {
        creature.velY = -creature.speed;
        }

        creature.move();
        }
        else if(moveCount >= (moveWait * 2))
        {
        moveCount = 0;
        }
         */

        if(creature.x < x) // move right
        {
            xMove = creature.speed;
        }
        else if(creature.x > x) // move left
        {
            xMove = -creature.speed;
        }

        if(creature.y < y) // move down
        {
            yMove = creature.speed;
        }
        else if(creature.y > y) // move up
        {
            yMove = -creature.speed;
        }

        creature.moveTowards(xMove,yMove);
    }

    public void moveIgnoreBoth(float x, float y)
    {
        if(creature.x < x) // move right
        {
            xMove = creature.speed;
        }
        else if(creature.x > x) // move left
        {
            xMove = -creature.speed;
        }

        if(creature.y < y) // move down
        {
            yMove = creature.speed;
        }
        else if(creature.y > y) // move up
        {
            yMove = -creature.speed;
        }

        creature.ignoreBoth(xMove,yMove);
    }

    /*
    idleCount = 0;
    ArrayList<Velocity> moves = possibleMoves(x,y);
    int size = moves.size();
    int randomMove = (int)(Math.random() * size);
    creature.moveTowards(moves.get(randomMove).x,moves.get(randomMove).y);
     */     

    public void move()
    {
        creature.ignoreBoth(xMove,yMove);
    }

    /**
     * 1. Gets possible moves
     * 2. Chooses random location out of those moves
     * 3. executes that move
     */
    public void moveIdle()
    {
        if(idleCount <= 0)
        {
            xMove = chooseRandomDir() * creature.speed;
            yMove = chooseRandomDir() * creature.speed;
        }
        if(idleCount <= idleTime)
            creature.moveTowards(xMove,yMove);
        else if(idleCount >= (idleTime + idleWait))
        {
            idleCount = 0;
            return;
        }
        idleCount++;
    } 

    public void moveIdleGhost()
    {
        if(idleCount <= 0)
        {
            if(Math.random() < .5)
            {
                xMove = chooseRandomDir() * creature.speed;
                yMove = 0;
            }
            else
            {
                xMove = 0;
                yMove = chooseRandomDir() * creature.speed;
            }
        }
        if(idleCount <= idleTime)
            moveIgnoreBoth(xMove,yMove);
        else if(idleCount >= (idleTime + idleWait))
        {
            idleCount = 0;
            return;
        }
        idleCount++;
    }

    /**
     * 1. Test every direction 8 of them with the canMoveTo() method
     * 2. add them to the list if they can move there
     * 3. return that list
     */
    /*
    public ArrayList<Velocity> getPossibleMoves() // up -speed for y
    {

    }
     */

    /**
     * 1.
     */
    /*
    public boolean canMoveTo()
    {
    ArrayList<Velocity> possibleMoves = new ArrayList<>();
    // Add all 8 moves
    possibleMoves.add(new Velocity(creature.speed, 0));
    possibleMoves.add(new Velocity(creature.speed,-creature.speed));
    possibleMoves.add(new Velocity(0,-creature.speed));
    possibleMoves.add(new Velocity(-creature.speed,-creature.speed));
    possibleMoves.add(new Velocity(-creature.speed,0));
    possibleMoves.add(new Velocity(-creature.speed,creature.speed));
    possibleMoves.add(new Velocity(0,creature.speed));
    possibleMoves.add(new Velocity(creature.speed,creature.speed));

    }
     */
    /**
     * Where to move given the (x,y) position of the location the creature is moving towards
     */
    /*
    public ArrayList<Velocity> possibleMoves(float x, float y)
    {
    //return mover.possibleMoves(x,y);
    ArrayList<Velocity> moves = new ArrayList<>();

    float eX = x;
    float eY = y;

    //System.out.println("This Creature's location is (" + creature.x + "," + creature.y + ")");
    //System.out.println("The players location is (" + eX + "," + eY + ")");

    if(creature.x < eX) //Desired Location to the right
    {
    //System.out.println("Player to the right");
    if(creature.y < eY) // Desired Location below
    {
    //System.out.println("Player below");
    moves.add(new Velocity(creature.speed, 0));
    moves.add(new Velocity(creature.speed, creature.speed));
    moves.add(new Velocity(0, creature.speed));
    }
    else // Desired Location above
    {
    //System.out.println("Player above");
    moves.add(new Velocity(0, -creature.speed));
    moves.add(new Velocity(creature.speed, -creature.speed));
    moves.add(new Velocity(creature.speed, 0));
    }
    }
    else // Desired Location to the left
    {
    //System.out.println("Player to the left");
    if(creature.y < eY) // Desired Location below
    {
    //System.out.println("Player below");
    moves.add(new Velocity(-creature.speed, 0));
    moves.add(new Velocity(-creature.speed, creature.speed));
    moves.add(new Velocity(0, creature.speed));
    }
    else // Desired Location above
    {
    //System.out.println("Player above");
    moves.add(new Velocity(0, -creature.speed));
    moves.add(new Velocity(-creature.speed, -creature.speed));
    moves.add(new Velocity(-creature.speed, 0));
    }
    }

    //System.out.println("Possible moves: " + moves);

    return moves;
    }
     */
}
