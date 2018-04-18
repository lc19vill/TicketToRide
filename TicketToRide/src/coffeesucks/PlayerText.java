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
public class PlayerText extends GameObject{
 
    String snd_voice="";
    String snd_woosh="";
    Image s_text;
    Image s_rails;
      public PlayerText(int player){
        String path= (dir + "\\TTRAssets\\"+player+"_text.png");
        s_text = new ImageIcon(path).getImage();
        sprite_index = s_text;
        
        x= -200;
        y= 200;
        hitboxHeight = 100;
        hitboxWidth = 144;
        hspeed = 30;
       mask =  new Rectangle2D.Double(x,y,hitboxWidth,hitboxHeight);
       
       snd_voice = (dir + "\\TTRAssets\\player" + player +".wav");
       playSound(snd_voice,false);
       snd_woosh = (dir + "\\TTRAssets\\switchplayer.wav");
       playSound(snd_woosh,false);
       
       
    }
    public void colEvent(){};
    public String toString(){return "null";}
    public void clicked(){}
    public void rClicked(){}
}


