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
 *
 * @author Mike
 */
public class MusicOff extends GameObject{
    
      transient Image s_deck;

    public MusicOff(){
        String path= (dir + "\\TTRAssets\\nomusic.gif");
        s_deck = new ImageIcon(path).getImage();
        sprite_index = s_deck;
        x= 0;
        y= 0;
        hitboxHeight = 132;
        hitboxWidth = 78;
       mask =  new Rectangle2D.Double(x,y,hitboxWidth,hitboxHeight);
       
    }
    public void colEvent(){};
    public String toString(){return "null";}
    public void clicked(){}
    public void rClicked(){}
}


