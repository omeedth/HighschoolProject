package game.entities.item;


/**
 * Write a description of class Heart here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import game.*;
import game.gfx.Assets;
import game.entities.creature.Player;

import java.awt.Rectangle;
import java.awt.Graphics;

public class Heart extends Drop
{
    //Static Variables
    
    //Variables
    int timer;
    float hp;

    /**
     * Constructor for objects of class Heart
     */
    public Heart(float x, float y, int width, int height, Rectangle bounds, Handler handler, int value)
    {
        super(x,y,width,height,bounds,handler,value);
        timer = 0;
        hp = width / 16f;
    }
    
    public void action(Player p)
    {
        p.health = Math.min(p.health + hp,p.TOTAL_HEALTH);    
        handler.getItemManager().addKillList(this);
    }
    
    public void receive(Player p)
    {
        if(p.health < p.TOTAL_HEALTH)
        {
            action(p);
        }
    }
    
    public void render(Graphics g)
    {
       g.drawImage(Assets.heart,(int)x,(int)y,2*width,2*height,null); 
    }
    
    public void update()
    {
        timer++;
        if(timer >= 600)
        {
            handler.getItemManager().addKillList(this);
        }
    }
}
