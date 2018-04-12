package coffeesucks;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mike
 */
public class RoadPath {
    Polygon boundBox = new Polygon();
    int ID;
    
    public RoadPath(int x1, int y1, int x2, int y2,int x3, int y3, int x4, int y4, String color, int length,int IDNUM){
    
    boundBox.addPoint(x1,y1);
    boundBox.addPoint(x2,y2);
    boundBox.addPoint(x3,y3);
    boundBox.addPoint(x4,y4);
    boundBox.addPoint(x1,y1);
    ID = IDNUM;
    }   
}

