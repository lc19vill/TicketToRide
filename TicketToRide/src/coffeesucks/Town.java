/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeesucks;

import java.util.ArrayList;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

/**
     * draws a circle over the towns spots
     * this is useful for mousing over later
     * give it a name and a position on the map
     * @param name
     * @param x
     * @param y 
 * @author luis
 */

public class Town implements Serializable
{
    String townName = "";
    ArrayList<RoadPath> paths = new ArrayList<>();
    ArrayList<Meeple> myMeeps = new ArrayList<>();
    int xLoc = 0;
    int yLoc = 0;
    Ellipse2D.Double mask; 
    
    Town(){}
    
    Town(String name, int x, int y)
    {
        townName = name;
        xLoc = x;
        yLoc = y;
        mask = new Ellipse2D.Double(x,y,20,20);
    }
    
    public void addPath(RoadPath pathNum)
    {
        paths.add(pathNum);
    }
    
    public int meepleColorCount(String c)
    {
        int count = 0;
        for (Meeple curr: myMeeps)
        {
            if (curr.myColor.equals(c))
            {
                count++;
            }
        }
        return count;
    }
    
}