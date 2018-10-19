package game.entities;


/**
 * Write a description of class Velocity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Velocity
{
    public float x;
    public float y;

    /**
     * Constructor for objects of class Velocity
     */
    public Velocity(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    
    public String toString()
    {
        return "Velocity: <" + x + "," + y + ">";
    }
}
