/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeesucks;

import java.awt.*;
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.Polygon;
import javax.sound.sampled.*;
import java.io.*;
import java.io.Serializable;

/**
 *
 * @author Mike
 *all objects in the game with
 *space and info should be these
 */
abstract class GameObject implements Serializable 
{

    int x = 0;
    int y = 0;
    int gravity;
    double vspeed = 0;
    double hspeed = 0;
    transient Image sprite_index = null;
    boolean inTransit = false;
    int nextx;
    int nexty;
    int hitboxWidth;
    int hitboxHeight;
    public String dir = System.getProperty("user.dir");
    //double scale = 1;
    boolean colUp = false;
    boolean colDown = false;
    boolean colRight = false;
    boolean colLeft = false;
    ArrayList<GameObject> cols = new ArrayList<GameObject>();
    ArrayList<GameObject> smallcols = new ArrayList<GameObject>();
    boolean visible = true;
    String debugData;
    int widthX;
    int heightY;
    Rectangle2D.Double mask;
    Polygon boundBox;
    Rectangle2D.Double smallmask;
    //useful for checking to not keep playing sounds until another event sets it
    Boolean hasPlayedSFX = false; 
    boolean locked = false;
    int mySpeed;
    boolean selected = false;
    int startx;
    int starty;
    int xoffset = 0;
    int yoffset = 0;
    boolean HIT;
    AudioInputStream nowplaying;
    transient Image topSpr;
    boolean Solid = true;
	
	/**
     * jumps to the x provided
     * @param newX 
     */
    public void jumpX(int newX) 
    {
        x = newX;
    }
	/**
     * jumps to the y provided
     * @param newY 
     */
    public void jumpY(int newY) 
    {
        y = newY;
    }
	/**
     * changes the vertical speed of the object
     * @param spd 
     */
    public void setVSpeed(double spd) 
    {
        vspeed = spd;
    }
	/**
     * changes the horizontal speed of the object
     * @param spd 
     */
    public void setHSpeed(double spd) 
    {
        hspeed = spd;
    }
	 /**
     * what do i draw? easy, sprite_index
     * set it here
     * @param spr 
     */
    public void setSpriteIndex(Image spr) 
    {
        sprite_index = spr;
        Image mySprite = ((Image) sprite_index);
        //hitboxWidth = mySprite.getWidth();
        //hitboxHeight = mySprite.getHeight();
    }

    /**
     * <p>
     * Make sure if your locations are odd you use an odd speed, or likewise use
     * an even
     */
    public void moveTowardsPoint(int newx, int newy, int speed) 
    {
        nextx = newx;
        nexty = newy;
        inTransit = true;
        mySpeed = speed;

        if (x < newx) 
        {

            hspeed = speed;
        }
        if (x > newx) 
        {

            hspeed = speed * -1;
        }
        if (y < newy) 
        {

            vspeed = speed;
        }
        if (y > newy) 
        {

            vspeed = speed * -1;
        }
    }
	/**
     * am i there yet? if so, set in transit to false
     */
    public void tripCheck() 
    {
        if (inTransit)
        {
            if ((x >= nextx-4) && (x <= nextx+4)) 
            {
                hspeed = 0;
                selected = false;
            }
            if ((y >= nexty-4) && (y <= nexty+4)) 
            {
                vspeed = 0;
                selected = false;
            }
            if (((y >= nexty-4) && (y <= nexty+4)) && ((x >= nextx-4) && 
                 (x <= nextx+4)))
            {
                inTransit = false;
            }
        }
        
    }
	 /**
     * old code. don't use this
     * @param x1
     * @param y1
     * @return 
     */
    public boolean hitBoxCheck(int x1, int y1) 
    {
        if (((x1 > x) && (x1 < (x + hitboxWidth))) && ((y1 > y) && 
                (y1 < (y + hitboxHeight)))) 
        {
            return true;
        } 
        else 
        {
            return false;
        }
    }

    public boolean isMoving() 
    {
        return inTransit;
    }

    /**
     * this is what occurs every frame for the object
     */
    public void step() 
    {
        
        //make moves
        if (this.vspeed != 0) 
        {
            this.jumpY(this.y += this.vspeed);
        }
        if (this.hspeed != 0) 
        {
            this.jumpX(this.x += this.hspeed);
        }

        //check if a destination has been reached
        this.tripCheck();
        //check collisions
        if (Solid)
        this.colEvent();
        debugData = (x + ", " + y + ", " + (x+hitboxWidth) + ", " + 
                    (y+hitboxHeight) + ", " + hitboxWidth + ", " + 
                     hitboxHeight );
        mask =  new Rectangle2D.Double(x+xoffset,y+yoffset,
                                       hitboxWidth,hitboxHeight);
        int newX = x+(hitboxWidth/2);
        int newY = y+(hitboxHeight/2);
        smallmask = new Rectangle2D.Double(x+28,y+28,10,10);
        
    }
	 /**
     * this is the good stuff.
     * Give it an array of objects in the room and it checks em all
     * @param feedme
     * @return 
     */
    public ArrayList<GameObject> collisionCheck(ArrayList<GameObject> feedme) 
    {
        cols.clear();// start fresh every frame
        for (GameObject curr : feedme) 
        {
            if(mask.intersects(curr.mask)) 
                if(!cols.contains(curr))
                cols.add(curr);
        }
        return cols;
    }
public ArrayList<GameObject> smallCollisionCheck(ArrayList<GameObject> feedme) 
{
        smallcols.clear();// start fresh every frame
        for (GameObject curr : feedme) 
        {
            if(mask.intersects(curr.mask)) 
              if(!smallcols.contains(curr))
                smallcols.add(curr);
        }
        return smallcols;
    }
    
    public abstract void colEvent();
    
    public void switchVisible()
    {
        if (visible)
        {
            visible = false;
        }
        else
        {
            visible = true;
        }
    }
    
    public void playSound(String soundfile, boolean loop)
    {
        
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream
                (new File(soundfile).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            nowplaying = audioInputStream;
            if (!loop)
            {
                clip.start();
            }
            else
            {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }
        catch (Exception ex)
        {
        }  

    }
    
    public void stopSound(String soundfile)
    {
        try
        {
        nowplaying.close();
        }
        catch (Exception ex)
        {
        }  
    }
    
    public abstract String toString();

    public abstract void clicked();
    public abstract void rClicked();
    
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    
    public double getHSpeed()
    {
        return hspeed;
    }
    public double getVSpeed()
    {
        return vspeed;
    }
    
}
