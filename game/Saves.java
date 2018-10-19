package game;

/**
 * Write a description of class Saves here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import game.Handler;

import game.util.Util;

import game.entities.Entity;
import game.entities.creature.*;
import game.entities.creature.state.Temper;

import game.entities.item.*;

import java.util.Scanner;

import java.awt.Rectangle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Saves
{
    //Static Variables
    //public static File save1, save2, save3, controls;

    public static String saveFile;

    //public static float spawnX, spawnY;
    //public static int width, height;
    //public static Weapon currentWeapon;

    //Variables
    private static Handler handler;
    //Scanner fileInput;

    /**
     * Constructor for objects of class Saves
     */
    public Saves(Handler handler)
    {
        //fileInput = new Scanner();
        this.handler = handler;
        //save1 = new File("save1.txt");
        //save2 = new File("save2.txt");
        //save3 = new File("save3.txt");
        //controls = new File("controls.txt");
    }

    public void saveControls(byte[] controls, String fileName)
    {
        try(FileWriter fw = new FileWriter(new File(fileName)))
        {
            fw.write("");
            for(byte b : controls)
            {
                fw.append("" + b + " ");
            }
        }
        catch(IOException e)
        {
            System.out.println("Couldn't create file");
        }
    }

    public static byte[] loadControls(String fileName)
    {
        try
        {
            String files = Util.loadFileAsString(fileName);
            String[] tokens = files.split("\\s+");
            //files = files.trim();
            //char[] controls = files.toCharArray();
            //byte[] controlsV = new byte[8];

            byte[] controlsV = new byte[9];
            
            int count = 0;
            
            for(String s : tokens)
            {
                controlsV[count] = Byte.parseByte(s);
                count++;
            }
            
            
            
            
            /*
            for(char c : controls)
            {
                if(count == 8)
                    break;
                if(Character.isLetter(c))
                {
                    controlsV[count] = (byte)c;
                    count++;
                }
            }
            */

            return controlsV;
        }
        catch(Exception e)
        {
            byte[] controls = {87,65,83,68,70,67,86,66,27};
            return controls;
        }
    }

    /**
     * SinglePlayer
     * Must load:
     * 1. spawnX spawnY
     * 2. worldWidth worldHeight
     * 3. speed, attack, health
     * 4. currentWeapon
     */
    public static void loadSaveFile(String fileName)
    {
        saveFile = fileName;
        File file = new File(fileName);
        try
        {
            handler.getEntityManager().clearSpawnCount();
            handler.getEntityManager().getEntities().clear();
            handler.getItemManager().getItems().clear();
            Player.count = 0;
            /*
            for(Entity e : handler.getEntityManager().getEntities())
            {                
                if(e instanceof Player)
                    handler.getEntityManager().removeEntity(e);
                else if(e instanceof Creature)
                {
                    ((Creature)e).die();
                }
            }

            for(Item i : handler.getItemManager().getItems())
            {                
                handler.getItemManager().removeItem(i);
            }
            */
            
            String files = Util.loadFileAsString(fileName);
            String[] tokens = files.split("\\s+");

            String one = tokens[0];
            String two = tokens[1];
            String three = tokens[2];

            float spawnX = Util.parseFloat(tokens[0]);
            float spawnY = Util.parseFloat(tokens[1]);
            String melee = tokens[2];
            String ranged = tokens[3];
            int level = Util.parseInt(tokens[4]);
            int levelUp = Util.parseInt(tokens[5]);
            int experience = Util.parseInt(tokens[7]);
            int experienceNeeded = Util.parseInt(tokens[8]);
            float damage = Util.parseInt(tokens[9]);
            float health = Util.parseFloat(tokens[10]);
            float totalHealth = Util.parseFloat(tokens[11]);
            float speed = Util.parseFloat(tokens[6]);
            MeleeWeapon meleeWeapon = Weapons.fist;//new Fist(0f,0f,10,10,new Rectangle(0,20,30,30),handler,1,1,30,.5f,.5f);
            if(melee.equals("game.entities.item.Fist"))
            {
                meleeWeapon = Weapons.fist;//new Fist(0f,0f,10,10,new Rectangle(0,20,30,30),handler,1,1,30,.5f,.5f);
            }

            RangedWeapon rangedWeapon = Weapons.mouth;   
            if(ranged.equals("game.entities.item.Mouth"))
            {
                rangedWeapon = Weapons.mouth;//new Fist(0f,0f,10,10,new Rectangle(0,20,30,30),handler,1,1,30,.5f,.5f);
            }
            
            //byte[] keys1 = {87,65,83,68,70,67,86,66,27};
            handler.getWorld().p1 = new Player(spawnX,spawnY,64,64,new Rectangle(20,15,20,45),Temper.Friendly,health,totalHealth,meleeWeapon,rangedWeapon,level,levelUp,experience,experienceNeeded,damage,speed,handler,loadControls("player1.txt"));
            handler.getWorld().p1.init();
            
            rangedWeapon.setWeaponUser(handler.getWorld().p1);
            
            handler.getEntityManager().addEntity(handler.getWorld().p1);
            
            //handler.getEntityManager().addEntity(new Ghost(200f,200f,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT,new Rectangle(0,0,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT),30,3,1,handler));
            //handler.getItemManager().addItem(new Spit(200f,50f,32,32,new Rectangle(200,50,32,32),handler,-1f,0f,0,1,1,200,false));
        }
        /*
        catch(IOException e)
        {
        System.out.println("No such file! Creating . . .");
        saveFile(handler.getWorld().spawnX,handler.getWorld().spawnY,new Fist(0f,0f,10,10,new Rectangle(0,20,30,30),handler,1,1,30,.5f,.5f),fileName);
        }*/
        catch(Exception e)
        {
            System.out.println("File can not be read! Overwriting . . .");
            Player p = new Player(handler.getWorld().spawnX,handler.getWorld().spawnY,64,64,new Rectangle(20,15,20,45),Temper.Friendly,10,10,Weapons.fist,Weapons.mouth/*Weapons.fist*//*new Fist(0f,0f,10,10,new Rectangle(0,20,30,30),handler,1,1,30,.5f,.5f)*/,1,0,0,100,0,2.5f,handler,loadControls("player1.txt"));            
            saveFile(p,fileName);
            p.init();
            handler.getEntityManager().addEntity(p);
            
            //handler.getEntityManager().addEntity(new Ghost(200f,200f,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT,new Rectangle(0,0,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT),30,3,1,handler));
        }
    }

    /**
     * Single Player Save
     */
    public static void saveFile(Player p, String fileName)
    {
        try(FileWriter fw = new FileWriter(new File(fileName)))
        {
            fw.write(p.x + " " + p.y + " " + p.weapons[0].getClass().getName() + " " + p.weapons[1].getClass().getName() + " " + p.currentLevel + " " + p.levelUp + " " + p.speed + " " + p.experience + " " + p.experienceNeeded + " " + p.damage + " " + p.health + " " + p.TOTAL_HEALTH);
        }
        catch(IOException e)
        {
            System.out.println("Couldn't create file");
        }
    }

    /**
     * Multiplayer save
     */
    public static void loadMultiplayerSaveFile(String fileName)
    {
        saveFile = fileName;
        File file = new File(fileName);
        try
        {
            /*
            for(Entity e : handler.getEntityManager().getEntities())
            {                
            if(e instanceof Player)
            handler.getEntityManager().removeEntity(e);
            else if(e instanceof Creature)
            {
            ((Creature)e).die();
            }
            }
             */

            handler.getEntityManager().clearSpawnCount();
            handler.getEntityManager().getEntities().clear();
            handler.getItemManager().getItems().clear();
            Player.count = 0;

                /*
                for(Item i : handler.getItemManager().getItems())
                {                
                handler.getItemManager().removeItem(i);
                }
                 */

            String files = Util.loadFileAsString(fileName);
            String[] tokens = files.split("\\s+");

            String one = tokens[0];
            String two = tokens[1];
            String three = tokens[2];

            //Player 1
            float spawnX = Util.parseFloat(tokens[0]);
            float spawnY = Util.parseFloat(tokens[1]);
            String melee = tokens[2];
            String ranged = tokens[3];
            int level = Util.parseInt(tokens[4]);
            int levelUp = Util.parseInt(tokens[5]);
            int experience = Util.parseInt(tokens[7]);
            int experienceNeeded = Util.parseInt(tokens[8]);
            float damage = Util.parseInt(tokens[9]);
            float health = Util.parseFloat(tokens[10]);
            float totalHealth = Util.parseFloat(tokens[11]);
            float speed = Util.parseFloat(tokens[6]);
            MeleeWeapon meleeWeapon = Weapons.fist;//new Fist(0f,0f,10,10,new Rectangle(0,20,30,30),handler,1,1,30,.5f,.5f);
            if(melee.equals("game.entities.item.Fist"))
            {
                meleeWeapon = Weapons.fist;//new Fist(0f,0f,10,10,new Rectangle(0,20,30,30),handler,1,1,30,.5f,.5f);
            }

            RangedWeapon rangedWeapon = Weapons.mouth;   
            if(ranged.equals("game.entities.item.Mouth"))
            {
                rangedWeapon = Weapons.mouth;//new Fist(0f,0f,10,10,new Rectangle(0,20,30,30),handler,1,1,30,.5f,.5f);
            }

            //Player 2
            float spawnX2 = Util.parseFloat(tokens[12]);
            float spawnY2 = Util.parseFloat(tokens[3]);
            String melee2 = tokens[14];
            String ranged2 = tokens[15];
            int level2 = Util.parseInt(tokens[16]);
            int levelUp2 = Util.parseInt(tokens[17]);
            int experience2 = Util.parseInt(tokens[19]);
            int experienceNeeded2 = Util.parseInt(tokens[20]);
            float damage2 = Util.parseInt(tokens[21]);
            float health2 = Util.parseFloat(tokens[22]);
            float totalHealth2 = Util.parseFloat(tokens[23]);
            float speed2 = Util.parseFloat(tokens[18]);
            MeleeWeapon meleeWeapon2 = Weapons.fist;//new Fist(0f,0f,10,10,new Rectangle(0,20,30,30),handler,1,1,30,.5f,.5f);
            if(melee.equals("game.entities.item.Fist"))
            {
                meleeWeapon2 = Weapons.fist;//new Fist(0f,0f,10,10,new Rectangle(0,20,30,30),handler,1,1,30,.5f,.5f);
            }

            RangedWeapon rangedWeapon2 = Weapons.mouth;   
            if(ranged.equals("game.entities.item.Mouth"))
            {
                rangedWeapon2 = Weapons.mouth;//new Fist(0f,0f,10,10,new Rectangle(0,20,30,30),handler,1,1,30,.5f,.5f);
            }          

            int maxLevel = Math.max(level,level2);

            //byte[] keys1 = {87,65,83,68,70,67,86,66};
            //byte[] keys2 = {73,74,75,76,38,37,40,39};
            handler.getWorld().p1 = new Player(spawnX,spawnY,64,64,new Rectangle(20,15,20,45),Temper.Friendly,health,totalHealth,meleeWeapon,rangedWeapon,level,levelUp,experience,experienceNeeded,damage,speed,handler,loadControls("player1.txt"));
            handler.getWorld().p1.init();
            handler.getEntityManager().addEntity(handler.getWorld().p1);
            handler.getWorld().p2 = new Player(spawnX2,spawnY2,64,64,new Rectangle(20,15,20,45),Temper.Friendly,health2,totalHealth2,meleeWeapon2,rangedWeapon2,level2,levelUp2,experience2,experienceNeeded2,damage2,speed2,handler,loadControls("player2.txt"));
            handler.getWorld().p2.init();
            handler.getEntityManager().addEntity(handler.getWorld().p2);
            
            //handler.getEntityManager().addEntity(new Ghost(300f,300,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT,new Rectangle(0,0,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT),30,3,1,handler));
        }
        /*
        catch(IOException e)
        {
        System.out.println("No such file! Creating . . .");
        saveFile(handler.getWorld().spawnX,handler.getWorld().spawnY,new Fist(0f,0f,10,10,new Rectangle(0,20,30,30),handler,1,1,30,.5f,.5f),fileName);
        }*/
        catch(Exception e)
        {
            System.out.println("File can not be read! Overwriting . . .");
            handler.getWorld().p1 = new Player(handler.getWorld().spawnX,handler.getWorld().spawnY,64,64,new Rectangle(20,15,20,45),Temper.Friendly,10,10,Weapons.fist,Weapons.mouth,1,0,0,100,0,2.5f,handler,loadControls("player1.txt"));                        
            handler.getWorld().p1.init();
            handler.getWorld().p2 = new Player(handler.getWorld().spawnX + 40,handler.getWorld().spawnY + 40,64,64,new Rectangle(20,15,20,45),Temper.Friendly,10,10,Weapons.fist,Weapons.mouth,1,0,0,100,0,2.5f,handler,loadControls("player2.txt"));   
            handler.getWorld().p2.init();
            saveMultiPlayerFile(fileName);            
            handler.getEntityManager().addEntity(handler.getWorld().p1);
            handler.getEntityManager().addEntity(handler.getWorld().p2);
        }
    }

    /**
     * Single Player Save
     */
    public static void saveMultiPlayerFile(String fileName)
    {
        try(FileWriter fw = new FileWriter(new File(fileName)))
        {
            Player p1 = handler.getWorld().p1;
            Player p2 = handler.getWorld().p2;
            fw.write(p1.x + " " + p1.y + " " + p1.weapons[0].getClass().getName() + " " + p1.weapons[1].getClass().getName() + " " + p1.currentLevel + " " + p1.levelUp + " " + p1.speed + " " + p1.experience + " " + p1.experienceNeeded + " " + p1.damage + " " + p1.health + " " + p1.TOTAL_HEALTH + "\r" + p2.x + " " + p2.y + " " + p2.weapons[0].getClass().getName() + " " + p2.weapons[1].getClass().getName() + " " + p2.currentLevel + " " + p2.levelUp + " " + p2.speed + " " + p2.experience + " " + p2.experienceNeeded + " " + p2.damage + " " + p2.health + " " + p2.TOTAL_HEALTH);
        }
        catch(IOException e)
        {
            System.out.println("Couldn't create file");
        }
    }
}
