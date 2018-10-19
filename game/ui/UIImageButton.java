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

public class UIImageButton extends UIObject
{
    private BufferedImage[] images;
    private Clickable clicker;

    public UIImageButton(float x, float y, int width, int height, Handler handler, BufferedImage[] images, Clickable clicker)
    {
        super(x,y,width,height,handler);
        this.images = images;
        this.clicker = clicker;
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
        if(!hovering)
            g.drawImage(images[0],(int)x,(int)y,width,height,null);
        else if(hovering)
            g.drawImage(images[1],(int)x,(int)y,width,height,null);
    }
    
    /**
     * draws the button onto the screen
     */
    @Override
    public void render(Graphics g)
    {
        if(!hovering)
            g.drawImage(images[0],(int)x,(int)y,width,height,null);
        else if(hovering)
            g.drawImage(images[1],(int)x,(int)y,width,height,null);
    }

    @Override
    public void onClick()
    {
        clicker.onClick();
    }
}
