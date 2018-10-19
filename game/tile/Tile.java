package game.tile;


/**
 * Write a description of class Tile here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */

//Imports for Personally made classes
import game.gfx.*;

//Imports for Java API
import java.awt.Graphics;
import java.awt.Rectangle;

import java.awt.image.BufferedImage;

/**
 * Defines what all tiles will be like
 */
public class Tile
{
    ///////////////////////
    //TileManager
    
    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile dirtTile = new DirtTile(1);
    public static Tile horStoneTile = new HorizontalStoneTile(2);
    public static Tile verStoneTile = new VerticalStoneTile(3);
    public static Tile leftTopCornerStoneTile = new LeftTopCornerStoneTile(4);
    public static Tile leftBottomCornerStoneTile = new LeftBottomCornerStoneTile(5);
    public static Tile rightTopCornerStoneTile = new RightTopCornerStoneTile(6);
    public static Tile rightBottomCornerStoneTile = new RightBottomCornerStoneTile(7);
    
    ///////////////////////
    //Class
    
    //Static Tile Variables
    public static final int DEFAULT_TILE_WIDTH = 32, DEFAULT_TILE_HEIGHT = 32;    
    
    //Tile Variables
    protected BufferedImage texture;
    protected int id;
    //public Rectangle bounds;
  
    /**
     * Creates a Tile object with all the set parameters
     */
    public Tile(BufferedImage texture, int id)
    {
        this.texture = texture;
        this.id = id;
        
        tiles[id] = this;
    }
    
    /**
     * Render method - draws the tile to the screen
     */
    public void render(Graphics g, int x, int y)
    {
        g.drawImage(texture,x,y,DEFAULT_TILE_WIDTH,DEFAULT_TILE_HEIGHT,null);
    }

    public void update()
    {
        
    }
    
    /**
     * returns if the tile is walkable through or not
     * 
     * I.E. BACKGROUND would return FALSE while FOREGROUND would be TRUE
     */
    public boolean isSolid()
    {
        return false;
    }
}
