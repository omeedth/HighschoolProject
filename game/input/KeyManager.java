package game.input;


/**
 * Write a description of class KeyManager here.
 * 
 * @author Alex Thomas 
 * @version (a version number or a date)
 */


//Imports for personally made classes
import game.Handler;

import game.state.State;

//Java API imports
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * KeyManager holds all the values of the keys and will tell the player
 * if they are pressing the desired key
 */
public class KeyManager extends KeyAdapter
{
    public boolean[] keys = new boolean[256];
    
    public boolean esc = false;
    public boolean space = false; // pick up
    
    //Player 1
    /*
    public boolean keyUp = false;
    public boolean keyDown = false;
    public boolean keyRight = false;
    public boolean keyLeft = false;
    public boolean attackUp = false;
    public boolean attackDown = false;
    public boolean attackRight = false;
    public boolean attackLeft = false;
    */
    
    //Player 2
    /*
    public boolean keyUp2 = false;
    public boolean keyDown2 = false;
    public boolean keyRight2 = false;
    public boolean keyLeft2 = false;
    public boolean attackUp2 = false;
    public boolean attackDown2 = false;
    public boolean attackRight2 = false;
    public boolean attackLeft2 = false;
    */  
    
    public byte escV;
    public byte spaceV;
    
    // Player 1
    /*
    public byte keyUpV;
    public byte keyDownV;
    public byte keyRightV;
    public byte keyLeftV;
    public byte attackUpV;
    public byte attackDownV;
    public byte attackRightV;
    public byte attackLeftV;  
    */
    
    // Player 2    
    /*
    public byte keyUpV2;
    public byte keyDownV2;
    public byte keyRightV2;
    public byte keyLeftV2;
    public byte attackUpV2;
    public byte attackDownV2;
    public byte attackRightV2;
    public byte attackLeftV2; 
    */
    
    //Handler
    Handler handler;
   
    public KeyManager(Handler handler)
    {
        this.handler = handler;
        escV = KeyEvent.VK_ESCAPE;
        
        //Player 1
        /*
        keyUpV = KeyEvent.VK_W;
        keyDownV = KeyEvent.VK_S;
        keyRightV = KeyEvent.VK_D;
        keyLeftV = KeyEvent.VK_A;
        attackUpV = KeyEvent.VK_F;
        attackDownV = KeyEvent.VK_V;
        attackRightV = KeyEvent.VK_B;
        attackLeftV = KeyEvent.VK_C;
        */
        
        //Player 2
        /*
        keyUpV2 = KeyEvent.VK_I;
        keyDownV2 = KeyEvent.VK_K;
        keyRightV2 = KeyEvent.VK_L;
        keyLeftV2 = KeyEvent.VK_J;
        attackUpV2 = KeyEvent.VK_UP;
        attackDownV2 = KeyEvent.VK_DOWN;
        attackRightV2 = KeyEvent.VK_RIGHT;
        attackLeftV2 = KeyEvent.VK_LEFT;
        */
       
        //spaceV = ;        
    }
    
    /*
    public synchronized void setKey(KeyEvent e)
    {
        
    }
    */
    
    /**
     * modifies the variables real time
     * 1. set all of the controls to the value in the array of keys
     */
    public void update()
    {      
        esc = keys[escV];
        /*
        keyUp = keys[keyUpV];
        keyDown = keys[keyDownV];
        keyRight = keys[keyRightV];
        keyLeft = keys[keyLeftV];
        attackUp = keys[attackUpV];
        attackDown = keys[attackDownV];
        attackRight = keys[attackRightV];
        attackLeft = keys[attackLeftV];  
        */
    }
    
    /**
     * When you press a key that key will be set to true
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        keys[e.getKeyCode()] = true;        
        //System.out.println("KeyCode: " + e.getKeyCode());
    }
    
    /**
     * when you release a key it will be set to false
     */
    @Override
    public void keyReleased(KeyEvent e)
    {
        keys[e.getKeyCode()] = false;
    }
    
    /**
     * not implementing
     */
    @Override
    public void keyTyped(KeyEvent e)
    {
        
    }
    
    //Getter
    
    public boolean[] getKeys()
    {
        return keys;
    }
    
    /*
    public boolean getKeyUp()
    {
        return keyUp;
    }
    
    public boolean getKeyDown()
    {
        return keyDown;
    }
    
    public boolean getKeyRight()
    {
        return keyRight;
    }
    
    public boolean getKeyLeft()
    {
        return keyLeft;
    }
        
    public boolean getSpace()
    {
        return space;
    }
    */
    
    //Setter
    
    /*
    public void setKeyUp(boolean keyUp)
    {
        this.keyUp = keyUp;
    }
    
    public void setKeyDown(boolean keyDown)
    {
        this.keyDown = keyDown;
    }
    
    public void setKeyRight(boolean keyRight)
    {
        this.keyRight = keyRight;
    }
    
    public void setKeyLeft(boolean keyLeft)
    {
        this.keyUp = keyLeft;
    }
    
    public void setSpace(boolean space)
    {
        this.space = space;
    }
    */
}
