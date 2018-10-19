package game.state;

//personally made classes
import game.Handler;

import game.ui.*;

import game.gfx.*;

import game.entities.Spawn;

//Java API classes
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Write a description of class OptionState here.
 * 
 * @author (Bryant Huang) 
 * @version (a version number or a date)
 */
public class OptionState extends State
{
    UIManager ui;

    public OptionState(Handler handler, String song)
    {
        super(handler,song);

        ui = new UIManager(handler);
        handler.getMouseManager().setUIManager(ui);

        //Controls Button
        ui.addObject(new UIImageButton((handler.getWidth()/2) - 64,(handler.getHeight()/4) - 32, 128, 64,handler,Assets.startBtn, new Clickable()
                {
                    public void onClick()
                    {
                        handler.getMouseManager().setUIManager(null);
                        State.setState(handler.getGame().controlState);
                    }
                }));

        // Sound Button
        ui.addObject(new UIImageButton((handler.getWidth()/2) - 64,(handler.getHeight()/4) + 64, 128, 64,handler,Assets.startBtn, new Clickable()
                {
                    public void onClick()
                    {

                    }
                }));

        //Back Button
        ui.addObject(new UIImageButton((handler.getWidth()/2) - 64,(handler.getHeight()/4) + 160, 128, 64,handler,Assets.startBtn, new Clickable()
                {
                    public void onClick()
                    {
                        handler.getMouseManager().setUIManager(null);
                        State.setState(handler.getGame().menuState);
                    }
                }));

    }

    public void init()
    {
        inGameState = false;
        handler.getMusic().doPlay(song);
        
//         handler.getCamera().setXOffset(0);
//         handler.getCamera().setYOffset(0);
        
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
        handler.getCamera().panMap(1,1);
        ui.update();
    }

}
