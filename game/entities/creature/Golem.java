package game.entities.creature;


/**
 * Write a description of class Golem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

//Personal Made Classes
import game.Handler;

import game.util.Util;

import game.gfx.*;

import game.tile.Tile;

import game.state.State;

import game.entities.creature.state.Temper;

import game.entities.*;

import game.entities.item.*;

//JAVA API
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;

import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Random;

public class Golem extends Creature
{
    //Variables

    /**
     * Constructor for objects of class Golem
     */
    public Golem(float x, float y, int width, int height, Rectangle bounds,Temper temper, float health, float damage, float speed, Handler handler, int extraExp)
    {
        super(x,y,width,height,bounds,temper,health,damage,speed,handler,50);
    }
    
    @Override
    public void render(Graphics g)
    {
        
    }
    
    @Override
    public void update()
    {
        
    }
    
    @Override
    public void die()
    {
        
    }
    
    @Override
    public void action()
    {
        
    }
}
