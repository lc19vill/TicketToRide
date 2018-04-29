/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeesucks;

import java.io.Serializable;

/**
     * all this needs is a string color
     * @param c 
     */
public class Meeple implements Serializable
{
    
    String myColor;
    
    public Meeple(String c)
    {
        myColor = c;
    }
    
}
