package game.entities.item;


/**
 * Write a description of class Weapons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import game.Handler;

import game.gfx.Assets;

import game.tile.Tile;

import java.awt.Rectangle;

public class Weapons
{
    //Variables
    private static Handler handler;
    
    //Static Variables
    
    //Weapons
    public static MeleeWeapon fist;
    public static RangedWeapon mouth;
    
    //Projectiles
    public static Projectile spit;
    
    public Weapons(Handler handler)
    {
        this.handler = handler;
    }
    
    public static void init()
    {           
        //Weapons
        fist = new Fist(0f,0f,32,32,new Rectangle(0,0,32,32),handler,1,.25f,20,.2f,.2f);
        mouth = new Mouth(0f,0f,32,32,new Rectangle(0,0,32,32),handler,0,0,60,.1f,.1f,null/*spit*/,true,null,Assets.Tile1);
        
        //Projectile
        spit = new Spit(0f,0f,10,10,new Rectangle(0,0,10,10),handler,2.1f,2.5f,0,.1f,2.5f,(int)(1.5 * Tile.DEFAULT_TILE_WIDTH)/*,Assets.Tile1*/);       
        
        initializeWeapons();
    }
    
    public static void initializeWeapons()
    {
        mouth.setProjectile(spit);
    }
}
