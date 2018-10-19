package game.gfx;


/**
 * Write a description of class SpriteSheet here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */

//Imports for personally made classes

//Java API imports
import java.awt.image.BufferedImage;

/**
 * Holds sprite sheets and have cropping methods
 */
public class SpriteSheet
{
    //Variables
    BufferedImage sheet;
    
    /**
     * Takes in a file name for an image file that is a spritesheet
     */
    public SpriteSheet(BufferedImage sheet)
    {
        this.sheet = sheet;
    }
    
    /**
     * returns the sub-image cropped out in the designated part of the spritesheet
     */
    public BufferedImage crop(int x, int y, int width, int height)
    {
        return sheet.getSubimage(x,y,width,height);
    }
}
