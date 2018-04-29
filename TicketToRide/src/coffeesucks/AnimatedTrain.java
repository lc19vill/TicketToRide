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
public class AnimatedTrain extends GameObject 
{

   transient Image s_trainani; //the sprite image instance 
    int myID; //holds an ID num for reference
    int myPlayer; //who owns me?
    
    
    /**
     * this produces the animated train images
     * ID is what path it coincides with
     * Player changes the color
     * @param player
     * @param ID 
     */
    public AnimatedTrain(int player, int ID) 
    {
        String path = "";
        
        myID = ID;
        myPlayer = player;
        
        if (player == 1) 
        {
            path = (dir + "\\TTRAssets\\trainanimations\\player1\\1_" + 
                    ID + ".gif");
        }
        if (player == 2) 
        {
            path = (dir + "\\TTRAssets\\trainanimations\\player2\\1_" + 
                    ID + ".gif");
        }
        if (player == 3) 
        {
            path = (dir + "\\TTRAssets\\trainanimations\\player3\\1_" + 
                    ID + ".gif");
        }
        if (player == 4) 
        {
            path = (dir + "\\TTRAssets\\trainanimations\\player4\\1_" + 
                    ID + ".gif");
        }
        if (player == 5) 
        {
            path = (dir + "\\TTRAssets\\trainanimations\\player5\\1_" + 
                    ID + ".gif");
        }
        s_trainani = new ImageIcon(path).getImage();
   //s_trainani = s_trainani.getScaledInstance(1280, 720, Image.SCALE_DEFAULT);
        sprite_index = s_trainani;

        if (ID == 1) 
        {
            x = 507;
            y = 5;
        }
        if (ID == 2) 
        {
            x = 505;
            y = 59;
        }
        if (ID == 3) 
        {
            x = 533;
            y = 90;
        }

        if (ID == 4) 
        {
            x = 572;
            y = 61;
        }
        if (ID == 5) 
        {
            x = 571;
            y = 17;
        }
        if (ID == 6) 
        {
            x = 580;
            y = 66;
        }
        if (ID == 7) 
        {
            x = 590;
            y = 61;
        }
        if (ID == 8) 
        {
            x = 600;
            y = 33;
        }
        if (ID == 9) 
        {
            x = 651;
            y = 68;
        }
        if (ID == 10) 
        {
            x = 587;
            y = 88;
        }
        if (ID == 11) 
        {
            x = 688;
            y = 72;
        }
        if (ID == 12) 
        {
            x = 651;
            y =109;
        }
        if (ID == 13) 
        {
            x = 605;
            y = 118;
        }
        if (ID == 14) 
        {
            x = 604;
            y = 115;
        }
        if (ID == 15) 
        {
            x = 583;
            y = 201;
        }
        if (ID == 16) 
        {
            x = 580;
            y = 206;
        }
        if (ID == 17) 
        {
            x = 685;
            y = 198;
        }
        if (ID == 18) 
        {
            x = 754;
            y = 202;
        }if (ID == 19) 
        {
            x = 690;
            y = 210;
        }if (ID == 20) 
        {
            x = 725;
            y = 326;
        }
        if (ID == 21) 
        {
            x = 699;
            y = 286;
        }
        if (ID == 22) 
        {
            x = 728;
            y = 309;
        }
        if (ID == 23) 
        {
            x = 639;
            y = 315;
        }
        if (ID == 24) 
        {
            x = 664;
            y = 232;
        }
        if (ID == 25) 
        {
            x = 695;
            y = 283;
        }
        if (ID == 26) 
        {
            x = 626;
            y = 280;
        }
        if (ID == 27) 
        {
            x = 690;
            y = 327;
        }
        if (ID == 28) 
        {
            x = 642;
            y = 333;
        }
        if (ID == 29) 
        {
            x = 706;
            y = 462;
        }
        if (ID == 30) 
        {
            x = 676;
            y = 456;
        }
        if (ID == 31) 
        {
            x = 675;
            y = 544;
        }
        if (ID == 32) 
        {
            x = 639;
            y = 424;
        }
        if (ID == 33) 
        {
            x = 635;
            y = 431;
        }
        if (ID == 34) 
        {
            x = 649;
            y = 418;
        }
        if (ID == 35) 
        {
            x = 631;
            y = 327;
        }
        if (ID == 36) 
        {
            x = 617;
            y = 335;
        }
        if (ID == 37) 
        {
            x = 570;
            y = 227;
        }
        if (ID == 38) 
        {
            x = 556;
            y = 223;
        }
        if (ID == 39) 
        {
            x = 558;
            y = 136;
        }
        if (ID == 40) 
        {
            x = 561;
            y = 119;
        }
        if (ID == 41) 
        {
            x = 533;
            y = 126;
        }
        if (ID == 42) 
        {
            x = 521;
            y = 168;
        }
        if (ID == 43) 
        {
            x = 475;
            y = 167;
        }
        if (ID == 44) 
        {
            x = 439;
            y = 138;
        }
        if (ID == 45) 
        {
            x = 448;
            y = 95;
        }
        if (ID == 46) 
        {
            x = 410;
            y = 138;
        }
        if (ID == 47) 
        {
            x = 439;
            y = 132;
        }
        if (ID == 48) 
        {
            x = 419;
            y = 212;
        }if (ID == 49) 
        {
            x = 445;
            y = 211;
        }
        if (ID == 50) 
        {
            x = 459;
            y = 227;
        }
        if (ID == 51) 
        {
            x = 453;
            y = 237;
        }
        if (ID == 52) 
        {
            x = 404;
            y = 224;
        }
        if (ID == 53) 
        {
            x = 429;
            y = 260;
        }

        if (ID == 54) 
        {
            x = 435;
            y = 268;
        }
        if (ID == 55) 
        {
            x = 431;
            y = 272;
        }
        if (ID == 56) 
        {
            x = 416;
            y = 297;
        }
        if (ID == 57) 
        {
            x = 414;
            y = 292;
        }
        if (ID == 58) 
        {
            x = 433;
            y = 292;
        }
        if (ID == 59) 
        {
            x = 476;
            y = 273;
        }
        if (ID == 60) 
        {
            x = 543;
            y = 236;
        }
        if (ID == 61) 
        {
            x = 549;
            y = 224;
        }
        if (ID == 62) 
        {
            x = 424;
            y =328;
        }
        if (ID == 63) 
        {
            x = 425;
            y = 329;
        }
        if (ID == 64) 
        {
            x = 414;
            y = 335;
        }
        if (ID == 65) 
        {
            x = 428;
            y = 339;
        }
        if (ID == 66) 
        {
            x = 423;
            y = 368;
        }
        if (ID == 67) 
        {
            x = 430;
            y = 365;
        }
        if (ID == 68) 
        {
            x = 403;
            y = 377;
        }if (ID == 69) 
        {
            x = 425;
            y = 402;
        }if (ID == 70) 
        {
            x = 423;
            y = 440;
        }
        if (ID == 71) 
        {
            x = 423;
            y = 453;
        }
        if (ID == 72) 
        {
            x = 397;
            y = 450;
        }
        if (ID == 73) 
        {
            x = 467;
            y = 373;
        }
        if (ID == 74) 
        {
            x = 483;
            y = 385;
        }
        if (ID == 75) 
        {
            x = 452;
            y = 475;
        }
        if (ID == 76) 
        {
            x = 479;
            y = 409;
        }
        if (ID == 77) 
        {
            x = 490;
            y = 410;
        }
        if (ID == 78) 
        {
            x = 506;
            y = 384;
        }
        if (ID == 79) 
        {
            x = 514;
            y = 387;
        }
        if (ID == 80) 
        {
            x = 487;
            y = 441;
        }
        if (ID == 81) 
        {
            x = 510;
            y = 300;
        }
        if (ID == 82) 
        {
            x = 512;
            y = 306;
        }
        if (ID == 83) 
        {
            x = 566;
            y = 296;
        }
        if (ID == 84) 
        {
            x = 533;
            y = 379;
        }
        if (ID == 85) 
        {
            x = 533;
            y = 384;
        }
        if (ID == 86) 
        {
            x = 571;
            y = 397;
        }
        if (ID == 87) 
        {
            x = 568;
            y = 407;
        }
        if (ID == 88) 
        {
            x = 490;
            y = 477;
        }
        if (ID == 89) 
        {
            x = 504;
            y = 436;
        }
        if (ID == 90) 
        {
            x = 496;
            y = 437;
        }
        if (ID == 91) 
        {
            x = 536;
            y = 487;
        }
        if (ID == 92) 
        {
            x = 531;
            y = 485;
        }
        if (ID == 93) 
        {
            x = 596;
            y = 510;
        }
        if (ID == 94) 
        {
            x = 593;
            y = 519;
        }
        if (ID == 95) 
        {
            x = 624;
            y = 429;
        }
        if (ID == 96) 
        {
            x = 625;
            y = 504;
        }
        if (ID == 97) 
        {
            x = 619;
            y = 519;
        }
        if (ID == 98) 
        {
            x = 588;
            y = 539;
        }
        if (ID == 99) 
        {
            x = 577;
            y = 567;
        }
        if (ID == 100) 
        {
            x = 527;
            y = 582;
        }
        if (ID == 101) 
        {
            x = 502;
            y = 566;
        }
        if (ID == 102) 
        {
            x = 531;
            y = 560;
        }if (ID == 103) 
        {
            x = 567;
            y = 524;
        }
        if (ID == 104) 
        {
            x = 531;
            y = 485;
        }
        if (ID == 105) 
        {
            x = 480;
            y = 554;
        }
        if (ID == 106) 
        {
            x = 478;
            y = 499;
        }
        if (ID == 107) 
        {
            x = 472;
            y = 476;
        }if (ID == 108) 
        {
            x = 413;
            y = 530;
        }if (ID == 109) 
        {
            x = 463;
            y = 574;
        }
        if (ID == 110) 
        {
            x = 519;
            y = 129;
        }

    }

    public void colEvent() 
    {
    }

    ;
    public String toString() 
    {
        return "null";
    }

    public void clicked() 
    {
    }

    public void rClicked() 
    {
    }
}
