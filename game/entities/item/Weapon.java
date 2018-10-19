package game.entities.item;


/**
 * Write a description of class Weapon here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */

//imports from Java API
import java.awt.Graphics;
import java.awt.Rectangle;

import java.awt.image.BufferedImage;

import game.gfx.Animation;

//import from personally made classes
import game.Handler;

import game.entities.creature.*;

public abstract class Weapon extends Item
{
    //Variables
    public float damage;
    public int cooldown;
    public int timer;
    public float knockbackX;
    public float knockbackY;
    public long currentTime;
    
    public int maxX;
    public int maxY;
    
    public boolean attacking;
    public boolean attackRight;
    public boolean attackLeft;
    public boolean attackUp;
    public boolean attackDown;
    
    public BufferedImage img;
    
    protected Animation animRight;
    protected Animation animLeft;
    protected Animation animUp;
    protected Animation animDown;
    
    /**
     * Constructor for Weapons - sets the variables in Item initializes damage
     */
    public Weapon(float x, float y, int width, int height, Rectangle bounds, Handler handler, int value, float damage, int cooldown,float knockbackX, float knockbackY,BufferedImage img)
    {
        super(x,y,width,height,bounds,handler,value);
        this.damage = damage;
        this.cooldown = cooldown;
        this.knockbackX = knockbackX;
        this.knockbackY = knockbackY;
        this.img = img;
        attacking = false;
        attackRight = false;
        attackLeft = false;
        attackUp = false;
        attackDown = false;
        animRight = null;
        animLeft = null;
        animUp = null;
        animDown = null;
        maxX = 0;
        maxY = 0;
    }
    
    /**
     * sets the currentTime to the current time in milliseconds to begin countdown
     * 1. tests if you canAttack();
     *      > if you canAttack() then set attack = true
     * 2. sets the currentTime = System.currentTimeMillis();
     */
    public abstract void use(Player p);
    
    /**
     * checks the cooldown of the weapon and times 
     * how long you have waited since the last attack
     * 1. if statement testing if the currentTimeMillis() - the currentTime is >= the cooldown
     *      > if so set attack = false;
     * 2. return the opposite of attack (if attack is true you can NOT attack if you haven't attacked you can)
     */
    public boolean canAttack()
    {               
        return !attacking;
    }
    
    /**
     * updates all weapon variables
     * 1. updates the cooldown timer if attack is true
     */
    public void update()
    {
        if(attacking)
        {
            if(timer >= cooldown)
            {
                timer = 0;
                attacking = false;
                attackRight = false;
                attackLeft = false;
                attackUp = false;
                attackDown = false;
            }
            timer++;
        }  
        if(attacking)
        {
            if(animRight != null)
                animRight.runAnimation();
            else if(animLeft != null)
                animLeft.runAnimation();
            else if(animUp != null)
                animUp.runAnimation();
            else if(animDown != null)
                animDown.runAnimation();
        }
        /*
        else
        {
            animRight.reset();
            animLeft.reset();
            animUp.reset();
            animDown.reset();
        }
        */
        /*
        if(attackRight && animRight != null)
        {
            animRight.runAnimation();
        }
        else if(attackLeft && animLeft != null)
        {
            animLeft.runAnimation();
        }
        else if(attackUp && animUp != null)
        {
            animUp.runAnimation();
        }
        else if(attackDown && animDown != null)
        {
            animDown.runAnimation();
        }
        */
    }
    
    /**
     * Renders all weapons at their positions if necessary
     */
    /*
    public void render(Graphics g)
    {
        
    }
    */
    
    //Getters - returns designated values
    
    public float getDamage()
    {
        return damage;
    }
    
    public int getCooldown()
    {
        return cooldown;
    }
    
    public int getCooldownTimer()
    {
        return timer;
    }
    
    public boolean isAttacking()
    {
        return attacking;
    }
    
    public float getKnockbackX()
    {
        return knockbackX;
    }
    
    public float getKnockbackY()
    {
        return knockbackY;
    }
    
    //Setters - sets the designated variables 
    
    public void setDamage(int damage)
    {
        this.damage = damage;
    }
    
    public void setCooldown(int cooldown)
    {
        this.cooldown = cooldown;
    }
    
    public void setCooldownTimer(int timer)
    {
        this.timer = timer;
    }
    
    public void setAttacking(boolean attacking)
    {
        this.attacking = attacking;
    }
    
    public void setKnockbackX(float knockbackX)
    {
        this.knockbackX = knockbackX;
    }
    
     public void setKnockbackY(float knockbackY)
    {
        this.knockbackY = knockbackY;
    }
}
