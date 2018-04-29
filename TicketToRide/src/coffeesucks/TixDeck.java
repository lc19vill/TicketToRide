/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeesucks;

import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;

/**
     * more glorified sprites. this time it decides color of the pic
     * based on the type of deck (there are 2) orange and blue
     * @param myX
     * @param myY
     * @param color 
     */
public class TixDeck extends GameObject
{
    
    transient Image s_deck_blue;
    transient Image s_deck_orange;
    String myType = "";

    String path= (dir + "\\TTRAssets\\tickets\\blueDeck.png");
    String path1= (dir + "\\TTRAssets\\tickets\\orangeDeck.png");

    
    String snd_deck = (dir + "\\TTRAssets\\deck.wav");

    public TixDeck(int myX,int myY,String color)
    {
        
        s_deck_blue = new ImageIcon(path).getImage();
        s_deck_orange = new ImageIcon(path1).getImage();
        
        if (color.equals("Blue")){
        sprite_index = s_deck_blue;
        myType = color;
        }
        else{
        sprite_index = s_deck_orange;
        myType = color;
        }
        x= myX;
        y= myY;
        hitboxHeight = 76;
        hitboxWidth = 47;
       mask =  new Rectangle2D.Double(x,y,hitboxWidth,hitboxHeight);
       playSound(snd_deck,false);
       
    }
    public void colEvent(){};
    
    public String toString()
    {
        return "null";
    }
    
    public void clicked(){}
    
    public void rClicked(){}
}


