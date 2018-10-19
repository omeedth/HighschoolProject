package game.entities.creature;


/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class HealthBar
{
    public static int DEFAULT_HEIGHT = 5;
    
    private int width,height;
    //private float x,y;
    private int yDifference;
    private int fill;
    private Creature c;

    /**
     * Constructor for objects of class HealthBar
     */
    public HealthBar(/*float x, float y, */int width, int height, Creature c)
    {
        //this.x = x;
        //this.y = y;
        this.width = width;
        this.height = height;
        this.c = c;
        yDifference = 10 - c.bounds.y;
        fill = (int)c.health;
    }

    public void calcFill()
    {
        float percentage = c.health / c.TOTAL_HEALTH;
        fill = (int)(percentage * width);
    }
    
    public void update()
    {
        calcFill();
    }
    
    public void render(Graphics g, float x, float y)
    {
        Graphics2D g2 = (Graphics2D)g;
        g.setColor(Color.BLACK);
        g2.drawRect((int)x,(int)(y - yDifference),width,height);
        g.setColor(Color.WHITE);
        g.fillRect((int)x,(int)(y - yDifference),width,height);
        g.setColor(Color.RED);
        g.fillRect((int)x,(int)(y - yDifference),fill,height);
    }
}
