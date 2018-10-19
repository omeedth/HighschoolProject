package game.input;

/**
 * Write a description of class MouseManager here.
 * 
 * @author Eric Sy 
 * @version (a version number or a date)
 */

//import for personally made classes
import game.Handler;

import game.state.*;

import game.ui.*;

//Java API imports
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import java.awt.Point;

/**
 * Everything dealing with mouse events
 */
public class MouseManager extends MouseAdapter
{
    //Variables
    public float x, y;

    public boolean leftClicked = false; // attack
    public boolean middleClicked = false; // special???
    public boolean rightClicked = false; // magic

    //Manager
    private UIManager uiManager;

    //Handler
    Handler handler;

    public MouseManager(Handler handler)
    {
        this.handler = handler;    
        uiManager = null;
    }
    
    public void setUIManager(UIManager ui)
    {
        uiManager = ui;
    }
    
    public UIManager getUIManager()
    {
        return uiManager;
    }

    /**
     * Mouse is clicked on any button
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    /**
     * mouse dragging
     */
    @Override
    public void mouseDragged(MouseEvent e)
    {
        mouseMoved(e);
    }

    /**
     * mouse entered JFrame
     */
    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    /**
     * mouse exited JFrame
     */
    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    /**
     * mouse moved
     * 1. save the x and y positions of the mouse
     */
    @Override
    public void mouseMoved(MouseEvent e)
    {
        x = e.getX();
        y = e.getY();

        if(uiManager != null)
            uiManager.onMouseMove(e);
        //System.out.println("(" + x + "," + y + ")"); Test Code
    }

    /**
     * mouse Pressed
     */
    @Override
    public void mousePressed(MouseEvent e)
    {
        if (e.getButton() == MouseEvent.BUTTON1)
        {
            leftClicked = true;
            //System.out.println("Left Clicked"); Test Code
        }
        if (e.getButton() == MouseEvent.BUTTON2)
        {
            middleClicked = true;
            //System.out.println("Middle Clicked"); Test Code
        }
        if (e.getButton() == MouseEvent.BUTTON3)
        {
            rightClicked = true;
            //System.out.println("Right Clicked"); Test Code
        }                
    }

    /**
     * mouse Released
     */
    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (e.getButton() == MouseEvent.BUTTON1)
        {
            leftClicked = false;
        }
        if (e.getButton() == MouseEvent.BUTTON2)
        {
            rightClicked = false;
        }
        if (e.getButton() == MouseEvent.BUTTON3)
        {
            middleClicked = false;
        }
        
        if(uiManager != null)
            uiManager.onMouseRelease(e);
    }

    /**
     * mouse wheel moved
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {

    }

    //Getter

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public boolean isLeftClicked()
    {
        return leftClicked;
    }

    public boolean isMiddleClicked()
    {
        return middleClicked;
    }

    public boolean isRightClicked()
    {
        return rightClicked;
    }

    //Setter

    public void setPoint(Point p)
    {
        this.x = (float) p.getX();
        this.y = (float) p.getY();
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public void setLeftClicked(boolean leftClicked)
    {
        this.leftClicked = leftClicked;
    }

    public void setMiddleClicked(boolean middleClicked)
    {
        this.middleClicked = middleClicked;
    }

    public void setRightClicked(boolean rightClicked)
    {
        this.rightClicked = rightClicked;
    }
}