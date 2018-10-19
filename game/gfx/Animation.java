package game.gfx;

/**
 * Write a description of class Animation here.
 * 
 * @author Eric Sy 
 * @version (a version number or a date)
 */

//Personal class imports

//Java API imports
import java.awt.image.BufferedImage;

import java.awt.*;

public class Animation
{
    private int frames;
    private int speed;
    private int index = 0;
    private int count = 0;
    private BufferedImage[] scenes;
    private BufferedImage currentImg;
    
    public Animation(int speed,  BufferedImage... args)
    {
        this.speed = speed;
        scenes = new BufferedImage[args.length];
        for(int i = 0; i < args.length; i++)
        {
            scenes[i] = args[i];
            //System.out.println(scenes[i] + " " + args[i]);
        }
        frames = args.length;
    }

    /**
     * 1. Tests if the index is index is greater
     *    than or equal to the speed of the animation
     *          > if so then it uses the nextFrame() method
     */
    public void runAnimation()
    { 
        index++;
        if(index > speed)
        {
            index = 0;
            //System.out.println("ran animation" + index);
            nextFrame();
        }
        //System.out.println("ran animation methods" + index);
    }

    /**
     * Precondition: The animation has atlease 1 image in it
     * 1. Tests if there is a next frame
     *      > if there is a next frame it adds one to the current frame
     *        (if not it resets back to 0)
     */
    public void nextFrame()
    {
        count++;
        //System.out.println("count increased to " + count);
        if(count == frames)
            reset();
        else
            currentImg = scenes[count];
    }

    /**
     * resets the currentFrame to 0
     */
    public void reset()
    {
        count = 0;
        currentImg = scenes[0];
        index = 0;
        //System.out.println("Reset");
    }

    public void drawAnimation(Graphics g, int x, int y)
    {
        g.drawImage(currentImg, x, y, null);
    }

    public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY)
    {
        g.drawImage(currentImg, x, y, scaleX, scaleY, null);
    }

    /**
     * Gets the currentFrame of the list and returns it
     */
    public BufferedImage getCurrentFrame()
    {
        return scenes[index];
    }
}