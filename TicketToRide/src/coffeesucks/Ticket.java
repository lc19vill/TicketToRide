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
public class Ticket extends GameObject{
   Image s_card;
   String myState;
   String myValue; // "short" or "long"
   int myID;

    
    public Ticket(int myX,int myY, int ID,String val,String state,int destinationX,int destinationY){ // TASK 2
        String path= (dir + "\\TTRAssets\\tickets\\card_" + ID + ".jpg"); 
        //this is how we name 'em, just rename the card that matches the ID to "card_X.jpg" where X is the ID
        // NAME ALL THE GREEN CARDS FIRST, THEN NAME THE ORANGE CARDS. THIS WAY WE CAN EASILY SET ALL THE VALUES
        s_card = new ImageIcon(path).getImage();
        sprite_index = s_card;
        x= myX;
        y= myY;
        hitboxHeight = 474;
        hitboxWidth = 283;
       mask =  new Rectangle2D.Double(x,y,hitboxWidth,hitboxHeight);
       moveTowardsPoint(destinationX,destinationY,10);
       myState = state;
       Solid = false;
       myID = ID;
       myValue = val;
       
    }
    public Ticket(int myX,int myY, int ID,String val,String state){ // TASK 2
        String path= (dir + "\\TTRAssets\\tickets\\card_" + ID + ".jpg"); 
        //this is how we name 'em, just rename the card that matches the ID to "card_X.jpg" where X is the ID
        // NAME ALL THE GREEN CARDS FIRST, THEN NAME THE ORANGE CARDS. THIS WAY WE CAN EASILY SET ALL THE VALUES
        s_card = new ImageIcon(path).getImage();
        sprite_index = s_card;
        x= myX;
        y= myY;
        hitboxHeight = 474;
        hitboxWidth = 283;
       mask =  new Rectangle2D.Double(x,y,hitboxWidth,hitboxHeight);
       myState = state;
       Solid = false;
       myID = ID;
       myValue = val;
       
    }
    
    public void colEvent(){ 
    };
    public String toString(){return "null";}
    public void clicked(){
    
    
    }
    public void rClicked(){}  
}
