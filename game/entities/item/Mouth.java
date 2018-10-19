package game.entities.item;


/**
 * Write a description of class Mouth here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

//imports for personally made classes
import game.Handler;

import game.tile.Tile;

import game.input.MouseManager;

import game.entities.creature.*;

//Java API imports
import java.awt.Rectangle;
import java.awt.Graphics;

import java.awt.image.BufferedImage;

public class Mouth extends RangedWeapon
{
    //Variables
    
    /**
     * Constructor for objects of class Mouth
     */
    public Mouth(float x, float y, int width, int height, Rectangle bounds, Handler handler, int value, int damage, int cooldown, float knockbackX, float knockbackY, Projectile p, boolean playerUse, Creature c, BufferedImage img)
    {
        super(x,y,width,height,bounds,handler,value,damage,cooldown,knockbackX,knockbackY,p,playerUse,c,img);    
        //p = Weapons.spit;
    }  
    
    public void render(Graphics g)
    {
        
    }
}
