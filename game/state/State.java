package game.state;


/**
 * Write a description of class States here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */

//import personally made classes
import game.Handler;

//import Java API
import java.awt.Graphics;

public abstract class State
{
    //Variables
    public static State currentState = null;
    public static boolean inGameState = false;
    
    public String song;
    
    protected Handler handler;
    
    /**
     * pass in handler object
     */
    public State(Handler handler, String song)
    {
        this.handler = handler;
        this.song = song;
    }
    
    public abstract void init();
    public abstract void render(Graphics g);
    public abstract void update();
    
    //getter methods
    
    public static State getState()
    {                
        return currentState;
    }
    
    //setter methods
    
    public static void setState(State state)
    {
        currentState = state;
        currentState.init();
    }
}
