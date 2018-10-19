package game.tile;


/**
 * Write a description of class StoneTile here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */

import game.gfx.Assets;

public class LeftBottomCornerStoneTile extends Tile
{
    public LeftBottomCornerStoneTile(int id)
    {
        super(Assets.leftBottomCornerStoneTile,id);
    }
    
    public boolean isSolid()
    {
        return true;
    }
}
