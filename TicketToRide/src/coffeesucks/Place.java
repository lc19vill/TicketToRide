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
import java.awt.Image;
import java.util.*;
/**
 *
 * @author Mike
 */
public class Place {
    
    int myID;
    ArrayList<RoadPath> myRoads;
    ArrayList<Meeple> myMeeps;
    int x;
    int y;
    
    public Place(){
        
    }
    
    public Place(int ID, ArrayList<RoadPath> p,int myX, int myY){
        
        myRoads = p;
        myID = ID;
        x = myX;
        y = myY;

        
    }
    
    
    
}
