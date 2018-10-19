package game;


/**
 * Write a description of class Launcher here.
 * 
 * @author Alex Thomas
 * @version (a version number or a date)
 */

//Imports for personally made classes

//Java API imports

public class Launcher
{
    /**
     * Creates a Game Object with a title, width, and height
     * 1.initializes a Game Object
     * 2.runs the start method in the Game class
     */
    public static void main(String[] main)
    {
        Game game = new Game("Final Project So Mr Dunlea Will Stop Talking And Let me Play Super SMash Flash", 900, 750);
        game.start();
    }
}
