/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeesucks;

import java.util.ArrayList;

/**
 *
 * @author Mike
 */
public class Player {
    
    ArrayList<Card> myHand = new ArrayList<>();
    ArrayList<Meeple> myMeeps = new ArrayList<>();
    ArrayList<Ticket> myTix = new ArrayList<>();
    int ID;
    int numTrains = 3;
    int myScore = 0;
    int numTravel = 0;

    public Player(int myID){
        ID = myID;
    }
    
    
    
    
}

