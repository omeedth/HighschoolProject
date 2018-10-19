package game.entities.creature;

/**
 * Write a description of class Creature here.
 * 
 * @author Alex Thomas
 * @version (a version number or a date)
 */

//Imports for all personally made classes
import game.Handler;

import game.tile.Tile;

import game.entities.Entity;

import game.entities.creature.state.Temper;

//Imports for all Java API
import java.awt.Rectangle;

import java.awt.image.BufferedImage;

import java.util.ArrayList;

public abstract class Creature extends Entity
{ 
    //Static Variables
    public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;
    public static final float DEFAULT_HEALTH = 10;
    public static final float DEFAULT_CREATURE_SPEED = 2.5f;
    public static final int INVINCIBILITY_TIME = 30;
    //public static final float gravity = 4f;
    //public static final float friction = .25f;

    //Variables
    public float velX, velY;
    public float accelX, accelY;
    public float health, TOTAL_HEALTH;    // Display a health bar
    public float damage;
    public int experienceGranted;
    public int extraExp;
    public float speed;
    public Temper temper; // Friendly, Neutral, Hostile

    public int invTimer;
    public boolean invincible;

    public boolean falling;
    public boolean jumping;
    public boolean onGround;

    public float knockbackX, knockbackY, knockbackTimer, knockbackTime;

    public boolean hit;
    public boolean hitRight;
    public boolean hitLeft;
    public boolean hitUp;
    public boolean hitDown;

    public HealthBar healthBar;

    //Handler
    public Handler handler;

    /**
     * Creature Constructor - Initializes the creature variables plus the Entity variables
     * 
     * velocity starts at 0 and will go as fast as speed
     */
    public Creature(float x, float y, int width, int height, Rectangle bounds,Temper temper, float health, float damage, float speed, Handler handler, int extraExp)
    {
        super(x,y,width,height,bounds);
        this.temper = temper;
        this.health = health;
        TOTAL_HEALTH = health;
        this.damage = damage;
        this.handler = handler;
        this.speed = speed;
        invincible = false;
        invTimer = 0;
        accelX = 0;
        accelY = 0;
        this.extraExp = extraExp;

        knockbackTimer = 0;
        knockbackTime = 10;

        hit = false;
        hitRight = false;
        hitLeft = false;
        hitUp = false;
        hitDown = false;

        knockbackX = knockbackY = 0;

        experienceGranted = (int)(5 * health) + (int)(20 * damage) + (int)(5 * speed) + extraExp;        
    }

    public float getDamage()
    {
        return damage;
    }

    /*
    public void damage()
    {
    hurt(damage);
    }
     */

    public boolean hurt(float damage)
    {                
        if(!invincible)
        {
            //System.out.print("Health was " + health + ". ");
            health -= damage;
            //System.out.println("Now health is " + health);            
            invincible = true;
            //System.out.println("Invincible!");
            if(health <= 0)
            {
                die();
                System.out.println("Died");
                return true;
            }
        }
        return false;
    }

    public abstract void die();

    /**
     * Moves towards an Entity
     */
    public void moveTowards(float vX, float vY)
    {
        /*
        if(velX != 0)
        {
            velX += accelX;
        }
        else
        {
            accelX = 0;
        }

        if(velY != 0)
        {
            velY += accelY;
        }       
        else
        {
            accelY = 0;
        }
        */
        velX = vX;
        velY = vY;
        moveX();
        moveY();
        collision();
        //System.out.println("(" + x + "," + y + ")");
    }

    public void moveIgnoreWalls()
    {
        x += velX;
        y += velY;
        collision();
    }

    public void moveIgnoreEntityCollision()
    {
        moveX();
        moveY();
    }

    public void ignoreBoth(float vX, float vY)
    {
        velX = vX;
        velY = vY;
        x += velX;
        y += velY;
    }

    /**
     * Move - moves the player
     * Methods used:
     * moveX();
     * moveY();
     * collision();
     */
    public void move()
    {        
        moveX();
        moveY();
        collision();
    }

    /**
     * MoveX - move the player on the x axis
     * tests the direction you are moving into and checks if you are able to move
     * Methods used:
     * canMove();
     * 
     * 1. tests if you canMove()
     *      > if canMove() check if you are on the ground
     *              >if onGround() then add negative friction to the velocity
     *      > move the creature by adding velX to x, otherwise move into the tile you will be runnng into
     */
    public void moveX()
    {
        if(velX > 0) // right
        {
            int tx = (int) (x + velX + bounds.x + bounds.width) / Tile.DEFAULT_TILE_WIDTH;
            canMoveRight(tx);
        }
        else if(velX < 0) // left
        {
            int tx = (int) (x + velX + bounds.x) / Tile.DEFAULT_TILE_WIDTH;
            canMoveLeft(tx);
        }
        //bounds.x = (int)x;
    }

    private void fixPositionX(int newX)
    {
        x = newX;
    }

    private void fixPositionY(int newY)
    {
        y = newY;
    }

    private boolean canMoveRight(int tx)
    {
        if(!collisionWithTile(tx,(int) (y + bounds.y) / Tile.DEFAULT_TILE_HEIGHT) && !collisionWithTile(tx,(int)(y + bounds.y + bounds.height) / Tile.DEFAULT_TILE_HEIGHT))
        {
            x += velX;
            return true;
        }  
        else
        {
            fixPositionX(tx * Tile.DEFAULT_TILE_WIDTH - bounds.x - bounds.width - 1);
            return false;
        }   
    }

    private boolean canMoveLeft(int tx)
    {
        if(!collisionWithTile(tx,(int) (y + bounds.y) / Tile.DEFAULT_TILE_HEIGHT) && !collisionWithTile(tx,(int)(y + bounds.y + bounds.height) / Tile.DEFAULT_TILE_HEIGHT))
        {
            x += velX;
            return true;
        } 
        else 
        {
            fixPositionX(tx * Tile.DEFAULT_TILE_WIDTH + Tile.DEFAULT_TILE_WIDTH - bounds.x);
            return false;
        }
    }

    private boolean canMoveUp(int ty)
    {
        if(!collisionWithTile((int) (x + bounds.x) / Tile.DEFAULT_TILE_WIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.DEFAULT_TILE_WIDTH, ty))
        {
            y += velY;
            return true;
        }
        else
        {
            fixPositionY(ty * Tile.DEFAULT_TILE_HEIGHT + Tile.DEFAULT_TILE_HEIGHT - bounds.y);
            return false;
        }
    }

    private boolean canMoveDown(int ty)
    {
        if(!collisionWithTile((int) (x + bounds.x) / Tile.DEFAULT_TILE_WIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.DEFAULT_TILE_WIDTH, ty))
        {
            y += velY;
            return true;
        }
        else 
        {
            fixPositionY(ty * Tile.DEFAULT_TILE_HEIGHT - bounds.y - bounds.height - 1);
            return false;
        }
    }

    /**
     * MoveY - move the player on the y axis
     * tests the direction you are moving into and checks if you are able to move
     * 
     * 1. tests if you canMove()
     *      > if canMove() check if you are not on the ground
     *              >if not onGround() then add negative gravity to the velocity
     *      > move the creature by adding velY to y, otherwise move into the tile you will be runnng into
     */
    public void moveY()
    {
        if(velY < 0) // up
        {
            int ty = (int) (y + velY + bounds.y) / Tile.DEFAULT_TILE_HEIGHT;
            canMoveUp(ty);
        }
        else if(velY > 0) // down
        {
            int ty = (int) (y + velY + bounds.y + bounds.height) / Tile.DEFAULT_TILE_HEIGHT;
            canMoveDown(ty);
        } 
        //bounds.y = (int)y;
    }

    public abstract void action();

    protected boolean collisionWithTile(int x, int y)
    {
        return handler.getWorld().getTile(y,x).isSolid();
    }

    /**
     * Default for creatures
     * 
     * CanMove - Can the player move?\
     * checks if player is running against a solid object
     * Methods used:
     * canMove();
     */
    /*
    public boolean canMove()
    {
    float xPos = x + velX;
    float yPos = y + velY;
    if(handler.getWorld().getTile((int)(xPos/Tile.DEFAULT_TILE_WIDTH),(int)(yPos/Tile.DEFAULT_TILE_HEIGHT)).isSolid())
    {
    return false;
    }
    return true ;
    }
     */

    /**
     * collision - checks if player collided with specific Entities
     * - checks if player collided with another damaging creature (I.E. Slime)
     * - checks if player collided with a damaging item (I.E. Arrow)
     */
    public void collision()
    {
        for(Entity e : handler.getEntityManager().getEntities())
        {
            if(e instanceof Creature)
            {
                if(e != this &&  !(e instanceof Player))
                {
                    Rectangle eR = new Rectangle((int)(e.x + e.bounds.x),(int)(e.y + e.bounds.y),e.bounds.width,e.bounds.height);
                    if(collidedRight(eR)) // going right
                    {
                        collisionRight(eR);
                        int tx = (int) (x + bounds.x) / Tile.DEFAULT_TILE_WIDTH;                                     
                        //System.out.println("Collided Right");
                        if(!canMoveLeft(tx))
                        {
                            //System.out.println("Can't be pushed");
                            e.x = (int)(x + bounds.width + 1);
                        }
                    }
                    else if(collidedLeft(eR)) // going left
                    {
                        collisionLeft(eR);
                        int tx = (int) (x + bounds.x + bounds.width) / Tile.DEFAULT_TILE_WIDTH;   
                        //System.out.println("Collided Left");
                        if(!canMoveRight(tx))
                        {
                            //System.out.println("Can't be pushed");
                            e.x = (int)(x - e.bounds.width - 1);                           
                        }
                    }                    
                    else if(collidedUp(eR)) // going up
                    {
                        collisionDown(eR);
                        int ty = (int) (y + bounds.y + bounds.height) / Tile.DEFAULT_TILE_HEIGHT;
                        //System.out.println("Collided Up");
                        if(!canMoveDown(ty))
                        {
                            //System.out.println("Can't be pushed");
                            e.y = (int)(y - e.bounds.height - 1);
                        }
                    }
                    else if(collidedDown(eR)) // going down
                    {
                        collisionUp(eR);
                        int ty = (int) (y + bounds.y) / Tile.DEFAULT_TILE_HEIGHT;                        
                        //System.out.println("Collided Down");
                        if(!canMoveUp(ty))
                        {
                            //System.out.println("Can't be pushed");
                            e.y = (int)(y + bounds.height + 1);
                        }
                    }
                    /*
                    if(new Rectangle((int)(x + bounds.x),(int)(y + bounds.y),bounds.width,bounds.height).intersects(eR))
                    {
                    if(velX > 0) // going right
                    {
                    collisionRight(eR);
                    }
                    else if(velX < 0) // going left
                    {
                    //collisionLeft(eR);
                    }
                    else if(velY > 0) // going down
                    {
                    //collisionDown(eR);
                    }
                    else if(velY < 0) // going up
                    {
                    //collisionUp(eR);
                    }
                    }
                     */
                }
            }
        }
    }

    private boolean collidedRight(Rectangle e)
    {
        if(e.contains((int)(x + bounds.x + bounds.width),(int)(y + bounds.y)) || e.contains((int)(x + bounds.x + bounds.width),(int)(y + bounds.y + bounds.height)))
        {
            return true;
        }
        return false;
    }

    private boolean collidedLeft(Rectangle e)
    {
        if(e.contains((int)(x + bounds.x),(int)(y + bounds.y)) || e.contains((int)(x + bounds.x),(int)(y + bounds.y + bounds.height)))
        {
            return true;
        }
        return false;
    }

    private boolean collidedUp(Rectangle e)
    {
        if(e.contains((int)(x + bounds.x),(int)(y + bounds.y)) || e.contains((int)(x + bounds.x + bounds.width),(int)(y + bounds.y)))
        {
            return true;
        }
        return false;
    }

    private boolean collidedDown(Rectangle e)
    {
        if(e.contains((int)(x + bounds.x),(int)(y + bounds.y + bounds.height)) || e.contains((int)(x + bounds.x + bounds.width),(int)(y + bounds.y + bounds.height)))
        {
            return true;
        }
        return false;
    }

    private void collisionRight(Rectangle e)
    {
        x = e.x - bounds.width; // - 1 ?
    }

    private void collisionLeft(Rectangle e)
    {
        x = e.x + e.width;
    }

    private void collisionUp(Rectangle e)
    {
        y = e.y + e.height; // - 1 ?
    }

    private void collisionDown(Rectangle e)
    {
        y = e.y - bounds.height;
    }

    /**
     * tests whether the creature is jumping or falling
     * 1. tests if the creature is not isJumping() and is not isFalling()
     *      > if the creature isnt jumping or falling then the creature is on the ground
     */
    public boolean onGround()
    {
        return false;
    }

    //Getter Methods - returns all specified variables
    public float getVelX()
    {
        return velX;
    }

    public float getVelY()
    {
        return velY;
    }

    public float getHealth()
    {
        return health;
    }

    public Temper getTemper()
    {
        return temper;
    }

    /**
     * if velY is greater than 0
     */
    public boolean isJumping()
    {
        return jumping;
    }

    /**
     * if velY is less than 0
     */
    public boolean isFalling()
    {
        return falling;
    }

    //Setter Methods - sets all specified variables
    public void setVelX(float velX)
    {
        this.velX = velX;
    }

    public void setVelY(float velY)
    {
        this.velY = velY;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public void setTemper(Temper temper)
    {
        this.temper = temper;
    }

    public void setJumping(boolean jumping)
    {
        this.jumping = jumping;
    }

    public void setFalling(boolean falling)
    {
        this.falling = falling;
    }
}
