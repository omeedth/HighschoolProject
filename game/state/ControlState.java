package game.state;

/**
 * Write a description of class MenuState here.
 * 
 * @author Eric Sy
 * @version (a version number or a date)
 */

//personally made classes
import game.Handler;
import game.Saves;
import java.awt.*;

import game.ui.*;

import game.gfx.*;
import javax.swing.JLabel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import game.entities.Spawn;
import game.entities.creature.Player;

//Java API classes
import java.awt.Graphics;
import java.awt.Graphics2D;

public class ControlState extends State
{
    //Variables
    UIManager ui;   
    
    UILabel cm;
    UILabel ca;
    
    private Player pl;
    //private char[]
    public String fileName;
    
    //Controls
    char up;
    char left;
    char down;
    char right;
    
    char aup;
    char aleft;
    char adown;
    char aright;
    
    public ControlState(Handler handler, String song)
    {
        super(handler,song);
        
        fileName = "player1.txt";
        //pl = handler.getWorld().p1;       
        
        fillControls(fileName);
        
        ui = new UIManager(handler);
        handler.getMouseManager().setUIManager(ui);

                //Controls
        
        cm = new UILabel("Movement - UP: " + up + ", LEFT: " + left + ", DOWN: " + down + ", RIGHT: " + right ,(float)(handler.getWidth()/10.1428),(float)(handler.getHeight()*0.666667), 128, 64,handler,null);
        ca = new UILabel("Attack - UP: " + aup + ", LEFT: " + aleft + ", DOWN: " + adown + ", RIGHT: " + aright ,(float)(handler.getWidth()/10.1428),(float)(handler.getHeight()*0.686667), 128, 64,handler,null);        
        ui.addObject(cm);
        ui.addObject(ca);
        
        //back to Options
        ui.addObject(new UIImageButton((handler.getWidth()/2)-64,(handler.getHeight()/2)-32, 128, 64,handler,Assets.optionBtn, new Clickable()
                {
                    public void onClick()
                    {
                        handler.getMouseManager().setUIManager(null);
                        State.setState(handler.getGame().optionState);
                    }
                }));
        //button labels
        ui.addObject(new UILabel("Movement",(float)(handler.getWidth()/5.1428),(float)(handler.getHeight()/18), 128, 64,handler,null));
        ui.addObject(new UILabel("Attack",(float)(handler.getWidth()*0.8055556),(float)(handler.getHeight()*0.666667), 128, 64,handler,null));
        UILabel l = new UILabel(fileName,(float)(handler.getWidth()*0.8055556),(float)(handler.getHeight()/18), 128, 64,handler,null);
        ui.addObject(l);
        //         ui.addObject(new UIImageButton((handler.getWidth()/2), 0, (handler.getWidth()/2), (handler.getWidth()/3),handler,Assets.startBtn, new Clickable()
        //                 {
        //                     public void onClick()
        //                     {
        // 
        //                     }
        //                 });
        
        //Movement Buttons
        //up
        ui.addObject(new UIImageButton((float)(handler.getWidth()/5.1428), (float)(handler.getHeight()/7.5), 50, 50,handler,Assets.up, new Clickable()
                {
                    public void onClick()
                    {
                        handler.getMouseManager().setUIManager(null);
                        new KeyChange('u',fileName,handler);
//                         fillControls(pl);
//                         cm.setString("Movement - UP: " + up + ", LEFT: " + left + ", DOWN: " + down + ", RIGHT: " + right);
                        //handler.getKeyManager.setKey();
                    }
                }));
        //left
        ui.addObject(new UIImageButton((handler.getWidth()/9), (float)(handler.getHeight()/4.2857), 50, 50,handler,Assets.left, new Clickable()
                {
                    public void onClick()
                    {
                        handler.getMouseManager().setUIManager(null);
                        new KeyChange('l',fileName,handler);
//                         fillControls(pl);
//                         cm.setString("Movement - UP: " + up + ", LEFT: " + left + ", DOWN: " + down + ", RIGHT: " + right);
                    }
                }));
        //down
        ui.addObject(new UIImageButton((float)(handler.getWidth()/(5.1428)), (float)(handler.getHeight()/4.2857), 50, 50,handler,Assets.down, new Clickable()
                {
                    public void onClick()
                    {
                        handler.getMouseManager().setUIManager(null);
                        new KeyChange('d',fileName,handler);
//                         fillControls(pl);
//                         cm.setString("Movement - UP: " + up + ", LEFT: " + left + ", DOWN: " + down + ", RIGHT: " + right);
                    }
                }));
        //right
        ui.addObject(new UIImageButton(((float)(handler.getWidth()/3.6)), (float)(handler.getHeight()/4.2857), 50, 50,handler,Assets.right, new Clickable()
                {
                    public void onClick()
                    {
                        handler.getMouseManager().setUIManager(null);
                        new KeyChange('r',fileName,handler);
//                         fillControls(pl);
//                         cm.setString("Movement - UP: " + up + ", LEFT: " + left + ", DOWN: " + down + ", RIGHT: " + right);
                    }
                }));

        /*  Option Button
        ui.addObject(new UIImageButton((handler.getWidth()/2) - 64,(handler.getHeight()/4) - 32, 128, 64,Assets.startBtn, new Clickable(){
        public void onClick()
        {
        handler.getMouseManager().setUIManager(null);
        State.setState(handler.getGame().gameState);
        }
        }));
         */ 

        //Switch Weapons
        ui.addObject(new UIImageButton((float)(handler.getWidth()*0.805556), (float)(handler.getHeight()/7.5), 50, 50,handler,Assets.upA, new Clickable()
                {
                    public void onClick()
                    {
                        handler.getMouseManager().setUIManager(null);
                        new KeyChange('q',fileName,handler); 
                    }
                }));
        
        //Attack Buttons
        //width * .75
        //up
        ui.addObject(new UIImageButton((float)(handler.getWidth()*0.805556), (float)(handler.getHeight()*0.7666667), 50, 50,handler,Assets.upA, new Clickable()
                {
                    public void onClick()
                    {
                        handler.getMouseManager().setUIManager(null);
                        new KeyChange('t',fileName,handler);
//                         fillControls(pl);
//                         ca.setString("Attack - UP: " + up + ", LEFT: " + left + ", DOWN: " + down + ", RIGHT: " + right);  
                    }
                }));
        //left
        ui.addObject(new UIImageButton((float)(handler.getWidth()*0.722222), (float)(handler.getHeight()*0.866667), 50, 50,handler,Assets.leftA, new Clickable()
                {
                    public void onClick()
                    {
                        handler.getMouseManager().setUIManager(null);
                        new KeyChange('f',fileName,handler);
//                         fillControls(pl);
//                         ca.setString("Attack - UP: " + up + ", LEFT: " + left + ", DOWN: " + down + ", RIGHT: " + right);  
                    }
                }));
        //down
        ui.addObject(new UIImageButton((float)(handler.getWidth()*0.805556), (float)(handler.getHeight()*0.866667), 50, 50,handler,Assets.downA, new Clickable()
                {
                    public void onClick()
                    {
                        handler.getMouseManager().setUIManager(null);
                        new KeyChange('g',fileName,handler);
//                         fillControls(pl);
//                         ca.setString("Attack - UP: " + up + ", LEFT: " + left + ", DOWN: " + down + ", RIGHT: " + right);  
                    }
                }));
        //right
        ui.addObject(new UIImageButton((float)(handler.getWidth()*0.88889), (float)(handler.getHeight()*0.866667), 50, 50,handler,Assets.rightA, new Clickable()
                {
                    public void onClick()
                    {
                        handler.getMouseManager().setUIManager(null);
                        new KeyChange('h',fileName,handler);
//                         fillControls(pl);
//                         ca.setString("Attack - UP: " + up + ", LEFT: " + left + ", DOWN: " + down + ", RIGHT: " + right);  
                    }
                }));        
                
                //Change Player
        ui.addObject(new UIImageButton((3*handler.getWidth()/4)-32,(handler.getHeight()/5)-32, 64, 64,handler,Assets.startBtn, new Clickable()
                {
                    public void onClick()
                    {                        
                        fileName = "player1.txt";
                        l.setString(fileName);
                        //KeyChange.setPlayer(pl);
                                              
                        //System.out.println("Player 1");
                    }
                }));
                
        ui.addObject(new UIImageButton((3*handler.getWidth()/4)+40,(handler.getHeight()/5)-32, 64, 64,handler,Assets.startBtn, new Clickable()
                {
                    public void onClick()
                    {
                        fileName = "player2.txt";
                        l.setString(fileName);
                        //KeyChange.setPlayer(pl);
                        //fillControls(fileName);
//                         cm.setString("Movement - UP: " + up + ", LEFT: " + left + ", DOWN: " + down + ", RIGHT: " + right);
//                         ca.setString("Attack - UP: " + up + ", LEFT: " + left + ", DOWN: " + down + ", RIGHT: " + right);                        
                        //System.out.println("Player 2");
                    }
                }));
    }
    
    public void fillControls(String file)
    {
        byte[] controls = Saves.loadControls(file);
        
        for(byte b : controls)
        {
            char c = (char)b;
        }
        
        up = (char)controls[0];
        left = (char)controls[1];
        down = (char)controls[2];
        right = (char)controls[3];
        
        aup = (char)controls[4];
        aleft = (char)controls[5];
        adown = (char)controls[6];
        aright = (char)controls[7];
    }
    
    public void init()
    {
        inGameState = false;
        handler.getMusic().doPlay(song);
        
//         handler.getCamera().setXOffset(0);
//         handler.getCamera().setYOffset(0);
        
        if(handler.getMouseManager().getUIManager() == null)
            handler.getMouseManager().setUIManager(ui);
    }

    public void render(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.translate(handler.getCamera().getXOffset(),handler.getCamera().getYOffset());
        handler.getWorld().render(g);
        ui.render(g,-handler.getCamera().getXOffset(),-handler.getCamera().getYOffset());
    }

    public void update()
    {        
        handler.getCamera().panMap(1,2);
        fillControls(fileName);
        cm.setString("Movement - UP: " + up + ", LEFT: " + left + ", DOWN: " + down + ", RIGHT: " + right);
        ca.setString("Attack - UP: " + aup + ", LEFT: " + aleft + ", DOWN: " + adown + ", RIGHT: " + aright);   
        ui.update();
    }
}
