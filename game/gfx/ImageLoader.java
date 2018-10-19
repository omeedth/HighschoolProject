package game.gfx;


/**
 * Write a description of class ImageLoader here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */

//personally made classes

//Java API classes
import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageLoader
{
    public static BufferedImage loadImage(String path)
    {
        try
        {
            //String str = ImageLoader.class.getResource(path).toString();
            //System.out.println(str);
            return ImageIO.read(ImageLoader.class.getResource(path));
        }
        catch(IOException e)
        {
            System.out.println("ERROR - Image Loading Failure!/n");
            System.exit(1);
        }
        return null;
    }
}
