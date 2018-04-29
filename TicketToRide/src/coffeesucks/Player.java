/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeesucks;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Mike
 */
public class Player implements Serializable
{
    
   ArrayList<Card> myHand = new ArrayList<>(); //the cards i have
    ArrayList<Meeple> myMeeps = new ArrayList<>(); //the meeps i have
    ArrayList<Ticket> myTix = new ArrayList<>(); // the tix i have
    int ID; // my ID number
    int numTrains = 45; //how many trains 
    int myScore = 0; //what is my score
    int numTravel = 0; //for globe trotter stuff

    public Player(int myID)
    {
        ID = myID;
    }
    
    
    
    
}

