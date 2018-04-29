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
    * perams are basic for gameobject child
    * town s and d are the start and destination of the cards
    * @param myX
    * @param myY
    * @param ID
    * @param val
    * @param state
    * @param destinationX
    * @param destinationY
    * @param s
    * @param d 
    */
public class Ticket extends GameObject
{
   transient Image s_card;
   String myState;
   int myValue; // "short" or "long"
   int myID;
   Town start;
   Town dest;
    
   String pathB = (dir + "\\TTRAssets\\tickets\\deckBlue.png"); 
   transient Image blueInDeck = new ImageIcon(pathB).getImage();
   String pathO = (dir + "\\TTRAssets\\tickets\\deckOrange.png");
   transient Image orangeInDeck = new ImageIcon(pathO).getImage();
   
    public Ticket(int myX,int myY, int ID,int val,String state,
                  int destinationX,int destinationY, Town s, Town d)
    { // TASK 2
        String path= (dir + "\\TTRAssets\\tickets\\card_" + ID + ".jpg"); 
        //this is how we name 'em, just rename the card that matches the ID to 
        //"card_X.jpg" where X is the ID
        // NAME ALL THE GREEN CARDS FIRST, THEN NAME THE ORANGE CARDS. 
        //THIS WAY WE CAN EASILY SET ALL THE VALUES
        s_card = new ImageIcon(path).getImage();
        sprite_index = s_card;
        x= myX;
        y= myY;
        hitboxHeight = 124;
        hitboxWidth = 75;
       mask =  new Rectangle2D.Double(x,y,hitboxWidth,hitboxHeight);
       moveTowardsPoint(destinationX,destinationY,10);
       myState = state;
       Solid = false;
       myID = ID;
       myValue = val;
       start = s;
       dest = d;
       
       if (state == "hand")
       {
           if (myValue <= 11)
           {
               sprite_index = blueInDeck;
           }
           else
           {
               sprite_index = orangeInDeck;
           }
           hitboxHeight = 48;
        hitboxWidth = 76;
       }
       
    }
    public Ticket(int myX,int myY, int ID,int val,String state,Town s, Town d)
    { // TASK 2
        String path= (dir + "\\TTRAssets\\tickets\\card_" + ID + ".jpg"); 
        //this is how we name 'em, just rename the card that matches the ID to 
        //"card_X.jpg" where X is the ID
        // NAME ALL THE GREEN CARDS FIRST, THEN NAME THE ORANGE CARDS. 
        //THIS WAY WE CAN EASILY SET ALL THE VALUES
        s_card = new ImageIcon(path).getImage();
        sprite_index = s_card;
        x= myX;
        y= myY;
        hitboxHeight = 124;
        hitboxWidth = 75;
       mask =  new Rectangle2D.Double(x,y,hitboxWidth,hitboxHeight);
       myState = state;
       Solid = false;
       myID = ID;
       myValue = val;
       start = s;
       dest = d;
       
       if (state == "hand")
       {
           if (myValue <= 11)
           {
               sprite_index = blueInDeck;
           }
           else
               sprite_index = orangeInDeck;
       }
       
    }
    
    public void colEvent(){};
    
    public String toString()
    {
        return "null";
    }
    
    public void clicked(){}
    
    public void rClicked(){}  
}
