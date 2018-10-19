package game.tile;


/**
 * Write a description of class StoneTile here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */

import game.gfx.Assets;

public class LeftTopCornerStoneTile extends Tile
{
    public LeftTopCornerStoneTile(int id)
    {
        super(Assets.leftTopCornerStoneTile,id);
    }
    
    public boolean isSolid()
    {
        return true;
    }
}
