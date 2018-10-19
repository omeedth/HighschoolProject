package game.entities;


/**
 * Write a description of interface Movable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;

public interface Movable
{
    //public ArrayList<Velocity> possibleMoves(float eX, float eY, float x, float y);
    public void actionMove(float x, float y);
}
