package game.state;


/**
 * Write a description of class GameState here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */

//personally made classes
import game.Handler;

import game.gfx.*;

//Java API classes
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameState extends State
{
    //Variables
        
    /**
     * pass in handler object
     */
    public GameState(Handler handler, String song)
    {
        super(handler,song);
    }
    
    public void init()
    {
        inGameState = true;
        //if(handler.getSpawnManager().waiting)
            //handler.getSpawnManager().resume();
        handler.getMusic().doPlay(song);
    }
    
    public void render(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        //Camera Translation
        g2.translate(handler.getCamera().getXOffset(),handler.getCamera().getYOffset());         
        handler.getWorld().render(g);
    }
    
    public void update()
    {
        handler.getKeyManager().update();
        handler.getWorld().update();
    }
}
