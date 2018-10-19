package game.ui;

/**
 * Write a description of class UIImageButton here.
 * 
 * @author Alex Thomas
 * @version (a version number or a date)
 */

//Imports for personally made classes
import game.entities.creature.Player;

import game.Handler;

//Java API imports
import java.awt.image.BufferedImage;

import java.awt.Graphics;

public class UILabel extends UIObject
{
    private BufferedImage[] images;
    private Clickable clicker;
    private String string;
    
    public UILabel(String string, float x, float y, int width, int height, Handler handler, Clickable clicker)
    {
        super(x,y,width,height,handler);
        this.clicker = clicker;
        this.string = string;
    }

    /**
     * updates the buttons variables
     */
    @Override
    public void update()
    {

    }

    public void render(Graphics g,float x,float y)
    {
        g.drawString(string,(int)x,(int)y);
    }

    /**
     * draws the button onto the screen
     */
    @Override
    public void render(Graphics g)
    {
            g.drawString(string,(int)x,(int)y);
    }

    @Override
    public void onClick()
    {
        clicker.onClick();
    }
    
    public void setString(String string)
    {
        this.string = string;
    }
}
