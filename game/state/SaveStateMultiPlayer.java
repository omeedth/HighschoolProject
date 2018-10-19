package game.state;

/**
 * Write a description of class MenuState here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */

//personally made classes
import game.Handler;
import game.Saves;

import game.ui.*;

import game.gfx.*;

import game.entities.Spawn;

//Java API classes
import java.awt.Graphics;
import java.awt.Graphics2D;

public class SaveStateMultiPlayer extends State
{
    //Variables
    UIManager ui;

    public SaveStateMultiPlayer(Handler handler, String song)
    {
        super(handler,song);

        ui = new UIManager(handler);
        handler.getMouseManager().setUIManager(ui);

        //Save 4
        ui.addObject(new UIImageButton((handler.getWidth()/2) - 64,(handler.getHeight()/4) - 32, 128, 64,handler,Assets.startBtn, new Clickable(){
                    public void onClick()
                    {
                        handler.getMouseManager().setUIManager(null);                        
                        Saves.loadMultiplayerSaveFile("save4.txt");
                        State.setState(handler.getGame().gameState);
                    }
                }));

        //Save 5
        ui.addObject(new UIImageButton((handler.getWidth()/2) - 64,(handler.getHeight()/2) -32, 128, 64,handler,Assets.startBtn, new Clickable(){
                    public void onClick()
                    {
                        handler.getMouseManager().setUIManager(null);
                        Saves.loadMultiplayerSaveFile("save5.txt");
                        State.setState(handler.getGame().gameState);
                    }
                }));

        //Save 6
        ui.addObject(new UIImageButton((handler.getWidth()/2) - 64,(3*handler.getHeight()/4) - 32, 128, 64,handler,Assets.startBtn, new Clickable(){
                    public void onClick()
                    {
                        handler.getMouseManager().setUIManager(null);
                        Saves.loadMultiplayerSaveFile("save6.txt");
                        State.setState(handler.getGame().gameState);
                    }
                }));
    }

    public void init()
    {
        inGameState = false;
        handler.getMusic().doPlay(song);
        
//         handler.getCamera().setXOffset(0);
//         handler.getCamera().setYOffset(0);
        
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
        handler.getCamera().panMap(2,2);
        ui.update();
    }
}
