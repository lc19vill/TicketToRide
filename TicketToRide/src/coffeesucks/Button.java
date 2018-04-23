/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeesucks;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 *
 * @author Mike
 */
public class Button extends GameObject{
    
    String myID;
    Image s_butt;
    boolean active = true;
    
    public Button(String ID, int w, int h,int mx, int my, String spr){
        x= mx;
        y= my;
        hitboxHeight = w;
        hitboxWidth = h;
        myID = ID;
       mask =  new Rectangle2D.Double(x,y,hitboxWidth,hitboxHeight);
       s_butt = new ImageIcon(spr).getImage();
        sprite_index = s_butt;
       
    }
    public void colEvent(){};
    public String toString(){return "null";}
    public void clicked(){}
    public void rClicked(){}
    
    public void flipActive(){
        if (active)
            active = false;
        else
            active = true;
    }
}


