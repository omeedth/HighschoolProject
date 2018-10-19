package game.tile;


/**
 * Write a description of class StoneTile here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */

import game.gfx.Assets;

public class VerticalStoneTile extends Tile
{
    public VerticalStoneTile(int id)
    {
        super(Assets.verStoneTile,id);
    }
    
    public boolean isSolid()
    {
        return true;
    }
}
