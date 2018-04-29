package coffeesucks;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.Serializable;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
     * the perams basically draw the polygon. Uses 4 points to keep it simple
     * color is the color of the path
     * length is for checking with the selected cards
     * IDNUM is useful for other checks later
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x3
     * @param y3
     * @param x4
     * @param y4
     * @param color
     * @param length
     * @param IDNUM 
     */
public class RoadPath implements Serializable 
{
    Polygon boundBox = new Polygon();
    int ID;
    transient Image sprite_index = null;
    String myColor;
    int myLength;
    int myOwn = 0;
    
    
    public RoadPath(int x1, int y1, int x2, int y2,int x3, int y3, int x4, 
                    int y4, String color, int length,int IDNUM)
    {
    boundBox.addPoint(x1,y1);
    boundBox.addPoint(x2,y2);
    boundBox.addPoint(x3,y3);
    boundBox.addPoint(x4,y4);
    boundBox.addPoint(x1,y1);
    ID = IDNUM;   
    myColor = color;
    myLength = length;
    }   
    
    public void setOwn(int newOwn)
    {
        myOwn = newOwn;
    }
    
}







            