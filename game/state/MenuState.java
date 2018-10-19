package game.state;

/**
 * Write a description of class MenuState here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */

//personally made classes
import game.Handler;

import game.ui.*;

import game.gfx.*;

import game.entities.Spawn;

//Java API classes
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MenuState extends State
{
    //Variables
    UIManager ui;

    public MenuState(Handler handler, String song)
    {
        super(handler,song);

        ui = new UIManager(handler);
        handler.getMouseManager().setUIManager(ui);

        //Title
        ui.addObject(new UIImageButton((handler.getWidth()/2) - 128,(handler.getHeight()/10) - 32, 256, 64,handler,Assets.title, new Clickable(){
                    public void onClick()
                    {

                    }
                }));

        //SinglePlayer Button
        ui.addObject(new UIImageButton((handler.getWidth()/2) - 64,(handler.getHeight()/4), 128, 64,handler,Assets.startBtn, new Clickable(){
                    public void onClick()
                    {
                        handler.getMouseManager().setUIManager(null);
                        State.setState(handler.getGame().saveState);
                    }
                }));
                
        // MultiPlayer Button
        ui.addObject(new UIImageButton((handler.getWidth()/2) - 64,(handler.getHeight()/3) + 32, 128, 64,handler,Assets.startBtn, new Clickable(){
                    public void onClick()
                    {
                        handler.getMouseManager().setUIManager(null);
                        State.setState(handler.getGame().saveStateMultiPlayer);
                    }
                }));

        //  Option Button
        ui.addObject(new UIImageButton((handler.getWidth()/2) - 64,(handler.getHeight()/2), 128, 64,handler,Assets.optionBtn, new Clickable(){
                    public void onClick()
                    {
                        handler.getMouseManager().setUIManager(null);
                        State.setState(handler.getGame().optionState);
                    }
                }));

        //Quit Button
        ui.addObject(new UIImageButton((handler.getWidth()/2) - 64,(3*handler.getHeight()/4), 128, 64,handler,Assets.quitBtn, new Clickable(){
                    public void onClick()
                    {
                        System.exit(1);
                    }
                }));
    }

    public void init()
    {
        inGameState = false;
        handler.getMusic().doPlay(song);
        
        //handler.getCamera().setXOffset(0);
        //handler.getCamera().setYOffset(0);
        
        //if(!handler.getSpawnManager().waiting)
        //handler.getSpawnManager().pause();
        if(handler.getMouseManager().getUIManager() == null)
            handler.getMouseManager().setUIManager(ui);
    }

    public void render(Graphics g)
    {        
        Graphics2D g2 = (Graphics2D)g;
        g2.translate(handler.getCamera().getXOffset(),handler.getCamera().getYOffset());
        handler.getWorld().render(g);
        ui.render(g,-handler.getCamera().getXOffset(),-handler.getCamera().getYOffset());
    }

    public void update()
    {
        handler.getCamera().panMap(2,1);
        ui.update();
    }
}
