/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeesucks;

import static coffeesucks.CoffeeSucks.dir;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author Mike
 */
public class Deck extends GameObject{
    
    transient Image s_deck;
    transient Image s_deck_mid;
    transient Image s_deck_low;
    String path= (dir + "\\TTRAssets\\cards\\deck_1.png");
    String path1= (dir + "\\TTRAssets\\cards\\deck_2.png");
    String path2= (dir + "\\TTRAssets\\cards\\deck_3.png");
    
    String snd_deck = (dir + "\\TTRAssets\\deck.wav");

    public Deck(int myX,int myY){
        
        s_deck = new ImageIcon(path).getImage();
        s_deck_mid = new ImageIcon(path1).getImage();
        s_deck_low = new ImageIcon(path2).getImage();
        sprite_index = s_deck;
        x= myX;
        y= myY;
        hitboxHeight = 100;
        hitboxWidth = 144;
       mask =  new Rectangle2D.Double(x,y,hitboxWidth,hitboxHeight);
       playSound(snd_deck,false);
       
    }
    public void colEvent(){};
    public String toString(){return "null";}
    public void clicked(){}
    public void rClicked(){}
}
