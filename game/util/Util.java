package game.util;


/**
 * Write a description of class Util here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */

//Imports for personally made classes

//Java API imports
import java.io.IOException;

import java.io.BufferedReader;
import java.io.FileReader;

import java.awt.Point;

/**
 * All miscellaneous Utility methods 
 */
public class Util
{
    /**
     * Load file as a string
     * 1.loads the file from the path
     * 2.converts everything into a string
     * 3.returns the string
     */
    public static String loadFileAsString(String fileName)
    {
        StringBuilder file = new StringBuilder();
        String line = "";
        try(BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            while((line = br.readLine()) != null)
            {
                file.append(line + " ");
            }
        }catch(IOException e)
        {
            System.out.println("Make File!");
            //e.printStackTrace();
        }
        
        return file.toString();
    }
    
    /**
     * converts a String into an Integer
     * 1.surround code with a try catch
     * 2.use Integer.parseInt(token);
     * 3.catch exception
     */
    public static int parseInt(String token)
    {
        try
        {
            return Integer.parseInt(token);
        }
        catch(NumberFormatException e)
        {
            System.out.println("Unable to convert to Integer . . . returning 0!");
        }
        return 0;
    }
    
    /**
     * converts a String into an Integer
     * 1.surround code with a try catch
     * 2.use Integer.parseInt(token);
     * 3.catch exception
     */
    public static float parseFloat(String token)
    {
        try
        {
            return Float.parseFloat(token);
        }
        catch(NumberFormatException e)
        {
            System.out.println("Unable to convert to Float . . . returning 0!");
        }
        return 0;
    }
    
    public static Point randomLocation(int width,int height)
    {
        int x = (int)((Math.random() * (width - 1)) + 1);
        int y = (int)((Math.random() * (height - 1)) + 1);
        return new Point(x,y);
    }
    
    public static Point randomLocationWithin(int startX, int startY, int width, int height)
    {
        int x = (int)((Math.random() * (width - startX)) + startX);
        int y = (int)((Math.random() * (height - startY)) + startY);
        return new Point(x,y);
    }
}
