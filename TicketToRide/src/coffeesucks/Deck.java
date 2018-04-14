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
public class Deck extends GameObject{
    
    Image s_deck;
    
    public Deck(int myX,int myY){
        String path= (dir + "\\TTRAssets\\cards\\deck_1.png");
        s_deck = new ImageIcon(path).getImage();
        sprite_index = s_deck;
        x= myX;
        y= myY;
        hitboxHeight = 100;
        hitboxWidth = 144;
       mask =  new Rectangle2D.Double(x,y,hitboxWidth,hitboxHeight);
    }
    public void colEvent(){};
    public String toString(){return "null";}
    public void clicked(){}
    public void rClicked(){}
}
