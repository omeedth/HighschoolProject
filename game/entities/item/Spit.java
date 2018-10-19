package game.entities.item;


/**
 * Write a description of class Spit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

//Import for personally made classes
import game.entities.Entity;
import game.entities.Damaging;

import game.Handler;

//Import for Java API
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;

import java.awt.image.BufferedImage;

public class Spit extends Projectile
{
    //Variables
    
    /**
     * Constructor for objects of class Spit
     */
    public Spit(float x, float y, int width, int height, Rectangle bounds, Handler handler, float velX, float velY,int value, float damage, float speed, int range/*, BufferedImage... args*/)
    {
        super(x,y,width,height,bounds,handler,velX,velY,value,damage,speed,range,Weapons.mouth/*,args*/);
    }
}
