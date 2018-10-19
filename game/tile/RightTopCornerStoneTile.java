package game.tile;


/**
 * Write a description of class StoneTile here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */

import game.gfx.Assets;

public class RightTopCornerStoneTile extends Tile
{
    public RightTopCornerStoneTile(int id)
    {
        super(Assets.rightTopCornerStoneTile,id);
    }
    
    public boolean isSolid()
    {
        return true;
    }
}
