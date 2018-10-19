package game.ui;


/**
 * Write a description of class UpgradeButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import game.Handler;

import game.entities.creature.Player;

///
import java.awt.image.BufferedImage;

import java.awt.Graphics;

import java.awt.Rectangle;

public class UpgradeButton
{
    private int xOff, yOff;
    private int width, height;
    private Rectangle r;
    private boolean hovering;
    private BufferedImage[] images;
    private Handler handler;
    private ClickableP clicker;    
    
    int count;
    
    /**
     * Constructor for objects of class UpgradeButton
     */
    public UpgradeButton(int xOff, int yOff, int width, int height, Handler handler, ClickableP clicker, BufferedImage... args)
    {
        this.xOff = xOff;
        this.yOff = yOff;
        this.width = width;
        this.height = height;
        this.handler = handler;
        images = new BufferedImage[2];
        images[0] = args[0];
        images[1] = args[1];
        r = new Rectangle(0,0,width,height);  
        this.clicker = clicker;
        
        count = 0;
    }
    
    public void onClick(Player p)
    {
        clicker.onClick(p);
    }
    
    public void update(int x, int y)
    {
        r.x = x + xOff;
        r.y = y + yOff;
    }
    
    public void update(Player p)
    {
        float x = handler.getMouseManager().getX();
        float y = handler.getMouseManager().getY();
        Rectangle nR = new Rectangle((int)(r.x + p.handler.getCamera().getXOffset()),(int)(r.y + p.handler.getCamera().getYOffset()),width,height);
        count++;
        if(count >= 60)
        {
            //System.out.println("(" + x + "," + y + ")");
            //System.out.println(nR);
            count = 0;
        }
        if(nR.contains(x,y))
        {
            hovering = true;
            if(handler.getMouseManager().isLeftClicked())
            {
                onClick(p);
            }
        }
        else
        {
            hovering = false;
        }
    }
    
    public void render(Graphics g, float x, float y)
    {
        if(hovering)
            g.drawImage(images[1],(int)(x + xOff),(int)(y + yOff),width,height,null);
        else
            g.drawImage(images[0],(int)(x + xOff),(int)(y + yOff),width,height,null);
    }
}
