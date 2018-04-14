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
public class Card extends GameObject{
    
    Image s_card;
    String myState;
    String myColor;
    
    
    public Card(int myX,int myY, String color,String state,int destinationX,int destinationY){
        String path= (dir + "\\TTRAssets\\cards\\card_" + color + ".png");
        s_card = new ImageIcon(path).getImage();
        sprite_index = s_card;
        x= myX;
        y= myY;
        hitboxHeight = 81;
        hitboxWidth = 125;
       mask =  new Rectangle2D.Double(x,y,hitboxWidth,hitboxHeight);
       moveTowardsPoint(destinationX,destinationY,10);
       myState = state;
       myColor = color;
       Solid = false;
    }
    public void colEvent(){
    
        
        
    };
    public String toString(){return "null";}
    public void clicked(){
    
    
    }
    public void rClicked(){}
    
    public String getColor(){
        return myColor;
    }
   
    
}
