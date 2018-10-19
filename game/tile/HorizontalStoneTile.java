package game.tile;


/**
 * Write a description of class StoneTile here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */

import game.gfx.Assets;

public class HorizontalStoneTile extends Tile
{
    public HorizontalStoneTile(int id)
    {
        super(Assets.horStoneTile,id);
    }
    
    public boolean isSolid()
    {
        return true;
    }
}
