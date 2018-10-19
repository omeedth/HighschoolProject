package game.gfx;

import java.io.*;

import javax.sound.sampled.*;

/**
 * Write a description of class SoundLoader here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SoundLoader
{
    public static File loadSound(String path)
    {
        //String str = SoundLoader.class.getResource(path).toString();
        //System.out.println(str);
        return new File(path);
    }
}
