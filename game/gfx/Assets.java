package game.gfx;


/**
 * Write a description of class Assets here.
 * 
 * @author Eric Sy 
 * @version (a version number or a date)
 */

//Imports for personally made classes

//Java API imports
import java.awt.image.BufferedImage;

import java.io.*;

import javax.sound.sampled.*;

/**
 * Stores all image and sound files
 */
public class Assets
{
    //Variables
    private static final int KWIDTH = 27, KHEIGHT = 27;
    private static final int BWIDTH = 83, BHEIGHT = 55;
    private static final int WIDTH = 32, HEIGHT = 32;
    private static final int SWIDTH = 64, SHEIGHT = 64;
    
    //SpriteSheets
    private static SpriteSheet blocks;
    private static SpriteSheet playerTest;
    private static SpriteSheet tile_sprites;
    private static SpriteSheet enemy_spritesheet01;
    private static SpriteSheet entities_spritesheet;
    private static SpriteSheet button_spritesheet;
    private static SpriteSheet character_spritesheet;
    private static SpriteSheet animation_spritesheet;
    //BufferedImages
    public static BufferedImage[] startBtn;
    public static BufferedImage[] optionBtn;
    public static BufferedImage[] quitBtn;
    public static BufferedImage[] title;
    public static BufferedImage player;
    public static BufferedImage[] playerRight,playerLeft;
    public static BufferedImage[] up, left, down, right, upA, leftA, downA, rightA;
    public static BufferedImage grassTile, dirtTile, horStoneTile, verStoneTile, leftTopCornerStoneTile,leftBottomCornerStoneTile,rightTopCornerStoneTile,rightBottomCornerStoneTile,slime1,slime2,ghost1,ghost2,ghost3;
    public static BufferedImage Tile1, Tile2, Tile3;
    public static BufferedImage heart,sword,boot,fist;
    public static BufferedImage[] healthUpgrade,attackUpgrade,speedUpgrade;   
    public static BufferedImage[] fistRight;
    
    //Clips
    //public static Clip menuMusic;
    
    /**
     * initializes all variables
     */
    public static void init()
    {
        blocks = new SpriteSheet(ImageLoader.loadImage("/res/Textures/sprite_sheet.png"));
        playerTest = new SpriteSheet(ImageLoader.loadImage("/res/Textures/testPaint.png"));
        tile_sprites = new SpriteSheet(ImageLoader.loadImage("/res/Textures/tile_sprites.png"));
        enemy_spritesheet01 = new SpriteSheet(ImageLoader.loadImage("/res/Textures/enemy_spritesheet01.png"));
        entities_spritesheet = new SpriteSheet(ImageLoader.loadImage("/res/Textures/sprites/entities_spritesheet.png"));
        button_spritesheet = new SpriteSheet(ImageLoader.loadImage("/res/Textures/button_spritesheet.png"));
        character_spritesheet = new SpriteSheet(ImageLoader.loadImage("/res/Textures/character_sprite_sheet.png"));
        animation_spritesheet = new SpriteSheet(ImageLoader.loadImage("/res/Textures/sprites/animation_spritesheet.png"));
        
        title = new BufferedImage[2];
        up = new BufferedImage[2];
        left = new BufferedImage[2];
        down = new BufferedImage[2];
        right = new BufferedImage[2];
        upA = new BufferedImage[2];
        leftA = new BufferedImage[2];
        downA = new BufferedImage[2];
        rightA = new BufferedImage[2];
        startBtn = new BufferedImage[2];
        optionBtn =  new BufferedImage[2];
        quitBtn = new BufferedImage[2];
        healthUpgrade = new BufferedImage[2];
        attackUpgrade = new BufferedImage[2];
        speedUpgrade = new BufferedImage[2];
        fistRight = new BufferedImage[3];
        playerRight = new BufferedImage[3];
        playerLeft = new BufferedImage[3];
        
        heart = entities_spritesheet.crop(0*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
        healthUpgrade[0] = entities_spritesheet.crop(1*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
        healthUpgrade[1] = entities_spritesheet.crop(2*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
        sword = entities_spritesheet.crop(3*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
        attackUpgrade[0] = entities_spritesheet.crop(4*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
        attackUpgrade[1] = entities_spritesheet.crop(5*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
        boot = entities_spritesheet.crop(6*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
        speedUpgrade[0] = entities_spritesheet.crop(7*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
        speedUpgrade[1] = entities_spritesheet.crop(8*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
        
        fist = entities_spritesheet.crop(3*WIDTH,HEIGHT,WIDTH,HEIGHT);
        fistRight[0] = animation_spritesheet.crop(0,0,WIDTH,HEIGHT);
        fistRight[1] = animation_spritesheet.crop(WIDTH,0,WIDTH,HEIGHT);
        fistRight[2] = animation_spritesheet.crop(2*WIDTH,0,WIDTH,HEIGHT);
        
        player =  character_spritesheet.crop(0*SWIDTH,0*SHEIGHT,SWIDTH,SHEIGHT);
        playerRight[0] = character_spritesheet.crop(0,SHEIGHT,SWIDTH,SHEIGHT);
        playerRight[1] = character_spritesheet.crop(SWIDTH,SHEIGHT,SWIDTH,SHEIGHT);
        playerRight[2] = character_spritesheet.crop(2*SWIDTH,SHEIGHT,SWIDTH,SHEIGHT);
        playerLeft[0] = character_spritesheet.crop(0,2*SHEIGHT,SWIDTH,SHEIGHT);
        playerLeft[1] = character_spritesheet.crop(SWIDTH,2*SHEIGHT,SWIDTH,SHEIGHT);
        playerLeft[2] = character_spritesheet.crop(2*SWIDTH,2*SHEIGHT,SWIDTH,SHEIGHT);
        
        Tile1 = playerTest.crop(0*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
        Tile2 = playerTest.crop(1*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
        Tile3 = playerTest.crop(2*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
               
        title[0] = button_spritesheet.crop(0*(BHEIGHT),0*BHEIGHT,2*BHEIGHT,BHEIGHT);
        title[1] = button_spritesheet.crop(0*(BHEIGHT),0*BHEIGHT,2*BHEIGHT,BHEIGHT);
        startBtn[0] = button_spritesheet.crop(0*(BWIDTH),1*BHEIGHT,BWIDTH,BHEIGHT);
        startBtn[1] = button_spritesheet.crop(1*(BWIDTH),1*BHEIGHT,BWIDTH,BHEIGHT);
        optionBtn[0] = button_spritesheet.crop(0*(BWIDTH),2*BHEIGHT,BWIDTH,BHEIGHT);
        optionBtn[1] = button_spritesheet.crop(1*(BWIDTH),2*BHEIGHT,BWIDTH,BHEIGHT);
        quitBtn[0] = button_spritesheet.crop(0*(BWIDTH),3*BHEIGHT,BWIDTH,BHEIGHT);
        quitBtn[1] = button_spritesheet.crop(1*(BWIDTH),3*BHEIGHT,BWIDTH,BHEIGHT);
        
        upA[0] = upA[1] = button_spritesheet.crop(0*KWIDTH,4*BHEIGHT,KWIDTH,KHEIGHT);
        leftA[0] = leftA[1] = button_spritesheet.crop(1*KWIDTH,4*BHEIGHT,KWIDTH,KHEIGHT);
        downA[0] = downA[1] = button_spritesheet.crop(2*KWIDTH,4*BHEIGHT,KWIDTH,KHEIGHT);
        rightA[0] = rightA[1] = button_spritesheet.crop(3*KWIDTH,4*BHEIGHT,KWIDTH,KHEIGHT);
        
        up[0] = up[1] = button_spritesheet.crop(0*KWIDTH,4*BHEIGHT + KHEIGHT,KWIDTH,KHEIGHT);
        left[0] = left[1] = button_spritesheet.crop(1*KWIDTH,4*BHEIGHT + KHEIGHT,KWIDTH,KHEIGHT);
        down[0] = down[1] = button_spritesheet.crop(2*KWIDTH,4*BHEIGHT + KHEIGHT,KWIDTH,KHEIGHT);
        right[0] = right[1] = button_spritesheet.crop(3*KWIDTH,4*BHEIGHT + KHEIGHT,KWIDTH,KHEIGHT);
        
        dirtTile = blocks.crop(1*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
        grassTile = tile_sprites.crop(0*SWIDTH,0*SHEIGHT,SWIDTH,SHEIGHT);
        horStoneTile = tile_sprites.crop(1*SWIDTH,0*SHEIGHT,SWIDTH,SHEIGHT);
        verStoneTile = tile_sprites.crop(2*SWIDTH,0*SHEIGHT,SWIDTH,SHEIGHT);
        leftTopCornerStoneTile = tile_sprites.crop(3*SWIDTH,0*SHEIGHT,SWIDTH,SHEIGHT);
        leftBottomCornerStoneTile = tile_sprites.crop(1*SWIDTH,1*SHEIGHT,SWIDTH,SHEIGHT);
        rightTopCornerStoneTile = tile_sprites.crop(4*SWIDTH,0*SHEIGHT,SWIDTH,SHEIGHT);
        rightBottomCornerStoneTile = tile_sprites.crop(5*SWIDTH,0*SHEIGHT,SWIDTH,SHEIGHT);
        
        slime1 = enemy_spritesheet01.crop(0*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
        slime2 = enemy_spritesheet01.crop(1*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
        ghost1 = enemy_spritesheet01.crop(2*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
        ghost2 = enemy_spritesheet01.crop(3*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
        ghost3 = enemy_spritesheet01.crop(4*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
    }
}