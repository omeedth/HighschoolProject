package game.entities.creature;

/**
 * Write a description of class Player here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */

//Imports all personally made classes
import game.entities.creature.state.Temper;
import game.gfx.Animation;
import game.gfx.Assets;

import game.Handler;
import game.Saves;

import game.tile.Tile;

import game.display.*;

import game.entities.*;

import game.entities.item.*;

import game.state.*;

import game.ui.*;

//Import all Java API
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;

/**
 * Defines a Player object
 */
public class Player extends Creature implements Damaging
{
    //Variables
    public static int level = 1;
    public static int count = 0; 
    public static boolean singlePlayer = true;

    //Variables
    private int attackCooldown = 400; //400 milliseconds
    private int playerCount;

    private int switchTimer;
    private int switchTime;

    public int currentLevel;
    public int levelUp;
    public int experience;
    public int experienceNeeded;

    private boolean added;
    public boolean attacking;
    private boolean upgrade;
    public int upgradeCount;

    public Weapon[] weapons;
    public MeleeWeapon meleeWeapon;
    public RangedWeapon rangedWeapon;
    public int weaponIndex;

    private ExperienceBar expB;
    private UpgradeManager um;
    private UpgradeButton attackI,speedI,healthI;

    public Rectangle playerSpace;

    //Keys - ASCII Values W: 87, A: 65, S: 83, D: 68
    //int[] keys; 

    public byte[] keys;

    /*
    public int up; //Player Decides
    public int left; //Player Decides
    public int down; //Player Decides
    public int right; //Player Decides
     */

    public boolean attackUp; //Player Decides
    public boolean attackLeft; //Player Decides
    public boolean attackDown; //Player Decides
    public boolean attackRight; //Player Decides

    private Animation playerRight;
    private Animation playerLeft;

    /**
     * Initializes all Player variables along with previously established variables
     */
    public Player(float x, float y, int width, int height, Rectangle bounds, Temper temper, float health, float totalHealth, MeleeWeapon mw, RangedWeapon rw, int level, int levelUp, int experience, int experienceNeeded, float damage, float speed, Handler handler, byte[] keys)
    {
        super(x,y,width,height,bounds,temper,health,damage,speed,handler,50);                        

        added = false;
        attacking = false;
        upgrade = false;
        upgradeCount = 0;

        switchTimer = 0;
        switchTime = 30;

        this.level = level;
        currentLevel = level;
        this.levelUp = levelUp;
        this.experience = experience;
        this.experienceNeeded = experienceNeeded; 
        TOTAL_HEALTH = totalHealth;
        //experienceGranted = (level * 50);

        // up,left,down,right,atkUp,atkLeft,atkDown,atkRight
        // {87,65,83,68,    38,37,40,39,   70,67,86,66,    73,74,75,76} W,A,S,D,   UP,LEFT,DOWN,RIGHT    F,C,V,B    I,J,K,L
        this.keys = new byte[keys.length];

        for(byte index = 0; index < keys.length; index++)
        {
            this.keys[index] = keys[index];
        }

        //Temporary Test Code
        /*
        up = 87;
        left = 65;
        down = 83;
        right = 68;   
         */

        attackUp = false;
        attackLeft = false;
        attackDown = false;
        attackRight = false;

        playerSpace = new Rectangle(0,0,width + (Tile.DEFAULT_TILE_WIDTH * 5),height + (Tile.DEFAULT_TILE_HEIGHT * 5));

        meleeWeapon = mw;//new Fist(0f,0f,10,10,new Rectangle(0,20,30,30),handler,1,1,30,.5f,.5f);
        rangedWeapon = rw;

        weapons = new Weapon[2];
        weapons[0] = mw;
        weapons[1] = rw;

        weaponIndex = 0;

        playerRight = new Animation(10, Assets.playerRight);    
        playerLeft = new Animation(10, Assets.playerLeft); 
        playerCount = count + 1;
        System.out.println("Player: " + playerCount);

        count++;
        System.out.println("Count: " + count);
    }

    public void levelUp()
    {           
        if(levelUp == 0)
        {
            upgrade = true;
        }
        levelUp++;
        level++;
        currentLevel++;
        System.out.println("Leveled Up!!");
        experience = experience -experienceNeeded;
        System.out.println("Starting experience is: " + experience);
        experienceNeeded += level * (experienceNeeded / 10);
        System.out.println("Experience Needed now is: " + experienceNeeded);                     
    }

    public void gainExp(Creature c)
    {
        //System.out.println("Experience granted: " + c.experienceGranted);
        experience += c.experienceGranted + level * 2;
        if(singlePlayer)
            Saves.saveFile(this,Saves.saveFile);
        else
            Saves.saveMultiPlayerFile(Saves.saveFile);
        /*
        if(count == 1)
        Saves.saveFile(this,Saves.saveFile);
        else
        Saves.saveMultiPlayerFile(Saves.saveFile);
         */
    }

    public void init()
    {
        um = new UpgradeManager(handler,this);
        attackI = new UpgradeButton(-(width),bounds.y - height - 25,width,height,handler,new ClickableP(){
                @Override
                public void onClick(Player p)
                {
                    if(p.upgradeCount == 0)
                    {
                        p.levelUp--;
                        System.out.println("Adding to Attack");
                        p.damage += .25f;
                        if(p.levelUp == 0)
                            p.upgrade = false;
                        //System.out.println("Can't upgrade for 1 second!");   
                        p.upgradeCount = 60;
                    }
                }
            },Assets.attackUpgrade);
        speedI = new UpgradeButton(0,bounds.y - height - 25,width,height,handler,new ClickableP()
            {
                @Override
                public void onClick(Player p)
                {
                    if(p.upgradeCount == 0)
                    {
                        p.levelUp--;
                        System.out.println("Adding to Speed");
                        p.speed += .5f;
                        if(p.levelUp == 0)
                            p.upgrade = false;
                        //System.out.println("Can't upgrade for 1 second!");   
                        p.upgradeCount = 60;
                    }
                }
            },Assets.speedUpgrade);
        healthI = new UpgradeButton((width),bounds.y - height - 25,width,height,handler,new ClickableP()
            {
                @Override
                public void onClick(Player p)
                {
                    if(p.upgradeCount == 0)
                    {
                        p.levelUp--;
                        System.out.println("Adding to Health");
                        p.health += 2;
                        p.TOTAL_HEALTH += 2;
                        System.out.println("Health: " + health + ", TotalHealth: " + TOTAL_HEALTH);
                        if(p.levelUp == 0)
                            p.upgrade = false;
                        //System.out.println("Can't upgrade for 1 second!");   
                        p.upgradeCount = 60;
                    }
                }
            },Assets.healthUpgrade);

        um.addObject(attackI);
        um.addObject(speedI);
        um.addObject(healthI);
        healthBar = new HealthBar(width,HealthBar.DEFAULT_HEIGHT,this);
        expB = new ExperienceBar(width,ExperienceBar.DEFAULT_HEIGHT,this);
    }

    @Override
    public void die()
    {        
        if(singlePlayer)
            Saves.saveFile(this,Saves.saveFile);
        else
            Saves.saveMultiPlayerFile(Saves.saveFile);
        /*
        if(count == 1)
        Saves.saveFile(this,Saves.saveFile);
        else
        Saves.saveMultiPlayerFile(Saves.saveFile);
         */
        count--;
        if(count <= 0)
        {
            handler.getGame().stop();
        }
        else
        {
            handler.getEntityManager().addKillList(this);
        }           
        //State.setState(handler.getGame().menuState);        
        //handler.getGame().loadWorld();
        //System.out.println("Died!"); 
    }

    public void action()
    {

    }

    public void drawWeapon(Graphics g)
    {
        if(playerCount == 1)
        {
            //Melee
            Graphics2D g2 = (Graphics2D)g;
            g.setColor(Color.WHITE);
            g.fillRect((int)-handler.getCamera().getXOffset(),(int)-handler.getCamera().getYOffset(),64,64);
            g.setColor(Color.BLACK);
            g2.drawRect((int)-handler.getCamera().getXOffset(),(int)-handler.getCamera().getYOffset(),64,64);
            g.drawImage(meleeWeapon.img,(int)-handler.getCamera().getXOffset(),(int)-handler.getCamera().getYOffset(),64,64,null);

            //Ranged
            g.setColor(Color.WHITE);
            g.fillRect(64 + (int)-handler.getCamera().getXOffset(),(int)-handler.getCamera().getYOffset(),64,64);
            g.setColor(Color.BLACK);
            g2.drawRect(64 + (int)-handler.getCamera().getXOffset(),(int)-handler.getCamera().getYOffset(),64,64);
            g.drawImage(rangedWeapon.img,64 + (int)-handler.getCamera().getXOffset(),(int)-handler.getCamera().getYOffset(),64,64,null);

            //Ranged Reload
            g.setColor(Color.WHITE);
            g.fillRect(138 + (int)-handler.getCamera().getXOffset(),(int)-handler.getCamera().getYOffset() + 24,64,16);
            g.setColor(Color.BLACK);
            g2.drawRect(138 + (int)-handler.getCamera().getXOffset(),(int)-handler.getCamera().getYOffset() + 24,64,16);
            g.setColor(Color.BLUE);
            float reload = ((float)weapons[1].timer / weapons[1].cooldown);
            reload = Math.min(1,reload);
            g.fillRect(138 + (int)-handler.getCamera().getXOffset(),(int)-handler.getCamera().getYOffset() + 24,(int)(64 * reload),16);
        }
        else if(playerCount == 2)
        {
            //Melee
            Graphics2D g2 = (Graphics2D)g;
            g.setColor(Color.WHITE);
            g.fillRect(handler.getWidth() - 64 - (int)handler.getCamera().getXOffset(),0  - (int)handler.getCamera().getYOffset(),64,64);
            g.setColor(Color.BLACK);
            g2.drawRect(handler.getWidth() - 64 - (int)handler.getCamera().getXOffset(),0 - (int)handler.getCamera().getYOffset(),64,64);
            g.drawImage(meleeWeapon.img,handler.getWidth() - 64 - (int)handler.getCamera().getXOffset(),0 - (int)handler.getCamera().getYOffset(),64,64,null);

            //Ranged
            g.setColor(Color.WHITE);
            g.fillRect(handler.getWidth() - 128 - (int)handler.getCamera().getXOffset(),0  - (int)handler.getCamera().getYOffset(),64,64);
            g.setColor(Color.BLACK);
            g2.drawRect(handler.getWidth() - 128 - (int)handler.getCamera().getXOffset(),0 - (int)handler.getCamera().getYOffset(),64,64);
            g.drawImage(rangedWeapon.img,handler.getWidth() - 128 - (int)handler.getCamera().getXOffset(),0 - (int)handler.getCamera().getYOffset(),64,64,null);

            //Ranged Reload
            g.setColor(Color.WHITE);
            g.fillRect(handler.getWidth() - 202 - (int)handler.getCamera().getXOffset(),0  - (int)handler.getCamera().getYOffset() + 24,64,16);
            g.setColor(Color.BLACK);
            g2.drawRect(handler.getWidth() - 202 - (int)handler.getCamera().getXOffset(),0 - (int)handler.getCamera().getYOffset() + 24,64,16);
            g.setColor(Color.BLUE);
            float reload = ((float)weapons[1].timer / weapons[1].cooldown);
            reload = Math.min(1,reload);
            g.fillRect(handler.getWidth() - 202 - (int)handler.getCamera().getXOffset(),(int)-handler.getCamera().getYOffset() + 24,(int)(64 * reload),16);
        }
    }

    /**
     * Render - draws the player on the screen
     */
    public void render(Graphics g)
    {
        //g.setColor(Color.RED);
        //g.fillRect((int)x,(int)y,16,16);

        //g.setColor(Color.BLUE);
        //g.fillRect((int)(x + bounds.x),(int)(y + bounds.y),bounds.width,bounds.height);
        //g.fillRect(playerSpace.x,playerSpace.y,playerSpace.width,playerSpace.height);
        healthBar.render(g,x,y-20);
        expB.render(g,x,y-10);
        if(upgrade)
            um.render(g,x,y);
        if(velX > 0)
        {
            playerRight.drawAnimation(g,(int)x,(int)y,width,height);
        }
        else if(velX < 0)
        {
            playerLeft.drawAnimation(g,(int)x,(int)y,width,height);
        }
        else
        {
            g.drawImage(Assets.player,(int)x,(int)y,width,height,null);
            //g.fillRect(bounds.x,bounds.y,bounds.width,bounds.height);
            /*
            g.setColor(Color.RED);
            g.fillRect((int)x,(int)y,width,height);
             */
        }
        drawWeapon(g);
        if(weapons[weaponIndex].attacking)
            weapons[weaponIndex].render(g);
    }

    /**
     * Update - updates all variables in the player
     * Methods used:
     * move();
     */
    public void update()
    {   
        handler.getCamera().centerOnEntity(this);
        healthBar.update();
        if(upgradeCount > 0)
            upgradeCount--;
        if(upgrade)
        {
            um.update((int)x,(int)y);
            um.update();
        }
        if(experience >= experienceNeeded)
        {
            levelUp();
        }
        expB.update();
        if(handler.getKeyManager().esc)
            State.setState(handler.getGame().menuState);
        if(invincible)
            invTimer++;
        if(invTimer > 2*INVINCIBILITY_TIME)
        {
            //System.out.println("Not invincible anymore!");
            invTimer = 0;
            invincible = false;
        }
        weapons[0].update();  
        weapons[1].update(); 
        keyInput();
        move();
        playerSpace.x = (int)x - (int)(Tile.DEFAULT_TILE_WIDTH * 2.5);
        playerSpace.y = (int)y - (int)(Tile.DEFAULT_TILE_HEIGHT * 2.5);
        attack();
        if(velX == 0)
        {
            playerRight.reset();
            playerLeft.reset();
        }
        else if(velX > 0)
        {
            playerRight.runAnimation();
            playerLeft.reset();
        }
        else if(velX < 0)
        {
            playerLeft.runAnimation();
            playerRight.reset();
        }
        switchTimer++;
    }

    public void switchWeapons()
    {
        if(switchTimer > switchTime)
        {
            //System.out.println("Switching Weapons!!!");
            //System.out.print("Weapon Before: " + weapons[weaponIndex]);
            if(weaponIndex == 1)
            {
                weaponIndex = 0;
            }
            else
            {
                weaponIndex = 1;
            }
            //System.out.print("Weapon After: " + weapons[weaponIndex]);
            switchTimer = 0;
        }        
    }

    public void keyInput()
    {
        velX = 0;
        velY = 0;

        /*
        if(handler.getKeyManager().keys[keys[8]])
        {
        State.setState(handler.getGame().menuState);
        }
         */

        if(handler.getKeyManager().esc)
            State.setState(handler.getGame().menuState);

        //Switch Weapons
        if(handler.getKeyManager().keys[keys[8]])
            switchWeapons();        

        if(handler.getKeyManager().keys[keys[3]])
        {
            velX = DEFAULT_CREATURE_SPEED;
        }
        if(handler.getKeyManager().keys[keys[1]])
        {
            velX = -DEFAULT_CREATURE_SPEED;
        }        
        if(handler.getKeyManager().keys[keys[0]])
        {
            velY = -DEFAULT_CREATURE_SPEED;
        }
        if(handler.getKeyManager().keys[keys[2]])
        {
            velY = DEFAULT_CREATURE_SPEED;
        }
        attackUp = handler.getKeyManager().keys[keys[4]];
        attackDown = handler.getKeyManager().keys[keys[6]];
        attackRight = handler.getKeyManager().keys[keys[7]];
        attackLeft = handler.getKeyManager().keys[keys[5]];
        /*
        if(velX == 0)
        {
        player.reset();
        //System.out.println("called reset");
        }
         */
    }

    /**
     * player attacks if not on cooldown
     * 1. checks if can attack by looking at cooldown
     *      > create a timer to check the cooldown
     * 2. call a method use() in the weapon class to attack and it will
     * differentiate meele and ranged for you and attack
     *      > if it is meele it extends the bounding box to the side you
     *        are attacking on (decides the side by where you click relative to character)
     *      > if it is a ranged attack the point you click is found and the speed of projectile
     *        is decided using trigonometry (Pythagoren theorem y = sinr and x = cosr)
     *        
     */
    public void attack()
    {       
        weapons[weaponIndex].use(this);
    }

    /**
     * collision - checks if player collided with specific Entities
     * - checks if player collided with another damaging creature (I.E. Slime)
     * - checks if player collided with a damaging item (I.E. Arrow)
     */
    @Override
    public void collision()
    {
        //System.out.println("Detecting collision!");
        for(Entity e : handler.getEntityManager().getEntities())
        {
            if(e instanceof Damaging)
            {
                if(e != this)
                {
                    if(new Rectangle((int)(x + bounds.x),(int)(y + bounds.y),bounds.width,bounds.height).intersects(new Rectangle((int)(e.x + e.bounds.x),(int)(e.y + e.bounds.y),e.bounds.width,e.bounds.height)))
                    {
                        Damaging d = (Damaging)e;
                        //System.out.println("Player: " + bounds + ". Add (" + x + "," + y + ") to it!");
                        //System.out.println("Creature: " + e.bounds + ". Add (" + e.x + "," + e.y + ") to it!");
                        if(singlePlayer)
                            Saves.saveFile(this,Saves.saveFile);
                        else
                            Saves.saveMultiPlayerFile(Saves.saveFile);
                        /*
                        if(count == 1)
                        Saves.saveFile(this,Saves.saveFile);
                        else
                        Saves.saveMultiPlayerFile(Saves.saveFile);
                         */
                        if(hurt(d.getDamage()))
                        {
                            return;
                        }
                    }
                }
            }
        }
        if(health != TOTAL_HEALTH)
        {
            for(Item i : handler.getItemManager().getItems())
            {
                if(i instanceof Drop)
                {
                    Rectangle r = new Rectangle((int)i.x + i.bounds.x, (int) i.y + i.bounds.y, i.bounds.width,i.bounds.height);
                    if(new Rectangle((int)(x + bounds.x),(int)(y + bounds.y),bounds.width,bounds.height).intersects(r))
                    {
                        //System.out.println(r);
                        ((Drop)i).receive(this);
                        if(singlePlayer)
                            Saves.saveFile(this,Saves.saveFile);
                        else
                            Saves.saveMultiPlayerFile(Saves.saveFile);
                        /*
                        if(count == 1)
                        Saves.saveFile(this,Saves.saveFile);
                        else
                        Saves.saveMultiPlayerFile(Saves.saveFile);
                         */
                    }
                }
            }
        }
    }

    public String toString()
    {
        return "Player: " + playerCount;
    }
}