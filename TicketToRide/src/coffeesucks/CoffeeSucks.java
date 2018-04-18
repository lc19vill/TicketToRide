/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeesucks;

import java.util.ArrayList;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.File;
import java.io.*;
import javax.swing.ImageIcon;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Collections;
import java.util.Arrays;
import java.util.Random;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Mike TERMINOLOGY GUIDE ROOM describes the current window being used
 * and drawn PARENTS are abstracts or interfaces.
 */
public class CoffeeSucks extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

    Dimension screenSize = new Dimension(1280, 736); //set screen size (locked)
    int FPS = 30; //default is 30,  you can change it but it WILL effect your whole game
    private final Timer timer = new Timer(FPS, this);
    Image ROOM_BACKGROUND = null; //define this for your room, this is the drawn background and should match the dimensions above
    private long totalTime; //all related to framerate
    private long averageTime;
    private int frameCount;
    static String dir = System.getProperty("user.dir");
    ArrayList<GameObject> objs = new ArrayList<>();
    ArrayList<GameObject> titleobjs = new ArrayList<>();
    ArrayList<RoadPath> hitBoxes = new ArrayList<>();
    Font customFont; //Name all your fonts here, set them in the constructor
    Font bigFont;
    boolean hasSelected = false;
    boolean gameWon = false;
    JFrame currentFrame;
    String currentRoom = "";
    static CoffeeSucks mainObj;
    String snd_blip = (dir + "\\TTRAssets\\blip.wav");
    String snd_bgm = (dir + "\\TTRAssets\\bgm.wav");
    String snd_title = (dir + "\\TTRAssets\\title.wav");
    String snd_drawcard = (dir + "\\TTRAssets\\drawcard.wav");
    String snd_liftcard = (dir + "\\TTRAssets\\liftcard.wav");
    String snd_retractcard = (dir + "\\TTRAssets\\retractcard.wav");
    String snd_deck = (dir + "\\TTRAssets\\deck.wav");
    String snd_nope = (dir + "\\TTRAssets\\nope.wav");
    Image player_icon;
    ArrayList<String> deck = new ArrayList<>();
    ArrayList<Card> currentHand = new ArrayList<>();
    ArrayList<Card> selectedCards = new ArrayList<>();
    int[] dock = new int[6];
    int[] hand = new int[9];
    ArrayList<Card> dockedCards = new ArrayList<>();
    ArrayList<Ticket> tickDeck = new ArrayList<>();
    ArrayList<Place> places = new ArrayList<>();
    int debugTime = 0;
    int debugFrames = 0;
    ArrayList<Integer> currentPlayerOwns = new ArrayList<>();
    int startHandSize = 0;
    String snd_train = (dir + "\\TTRAssets\\train.wav");
    String snd_money = (dir + "\\TTRAssets\\chaching.wav");
    String snd_begin = (dir + "\\TTRAssets\\epicgamestart.wav");
    int numPlayers = 2;
    Clip bgm = null;
    Clip titlemusic = null;
    boolean bgmPlay = true;
    Player[] myPlayers = new Player[5];
    int currentPlayer = 1;
    int cardsTaken = 0;
    int[][] ui_locations = new int[6][6];
    int turn = 0;
    Color[] rainbow = new Color[8];
    int randRainbow = 0;
    String rollover1 = "";
    String rollover2 = "";
    String rollover3 = "";
    int debugCount = 0;
    Color rolled = Color.BLACK;
    int[] pSelectHeights = new int[4];
    int countdown = 0;
    boolean counting = false;
    JFrame gameFrame;
    String specialTest = "";
    String test2 = "";

    public CoffeeSucks() throws FontFormatException, IOException {
        //custom font stuff
        String fontpath = dir + "\\TTRAssets\\ttrFont.ttf";
        customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontpath)).deriveFont(12f);
        bigFont = customFont.deriveFont(Font.BOLD, 24f);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontpath)));
        dock[0] = 115;
        dock[1] = 215;
        dock[2] = 315;
        dock[3] = 415;
        dock[4] = 515;
        dock[5] = 615;

        hand[0] = 204;
        hand[1] = 304;
        hand[2] = 404;
        hand[3] = 504;
        hand[4] = 604;
        hand[5] = 704;
        hand[6] = 804;
        hand[7] = 904;
        hand[8] = 1004;

        rainbow[0] = Color.RED;
        rainbow[1] = Color.YELLOW;
        rainbow[2] = Color.ORANGE;
        rainbow[3] = Color.GREEN;
        rainbow[4] = Color.BLUE;
        rainbow[5] = Color.MAGENTA;
        rainbow[6] = Color.WHITE;
        rainbow[7] = Color.BLACK;

        ui_locations[2][1] = 216 + 40;
        ui_locations[2][2] = 325 + 40;

        ui_locations[3][1] = 154 + 40;
        ui_locations[3][2] = 268 + 40;
        ui_locations[3][3] = 374 + 40;

        ui_locations[4][1] = 91 + 40;
        ui_locations[4][2] = 204 + 40;
        ui_locations[4][3] = 311 + 40;
        ui_locations[4][4] = 414 + 40;

        ui_locations[5][1] = 32 + 40;
        ui_locations[5][2] = 147 + 40;
        ui_locations[5][3] = 250 + 40;
        ui_locations[5][4] = 362 + 40;
        ui_locations[5][5] = 468 + 40;

        Arrays.fill(myPlayers, null);

        pSelectHeights[0] = 397;
        pSelectHeights[1] = 466;
        pSelectHeights[2] = 534;
        pSelectHeights[3] = 609;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FontFormatException, IOException {
        CoffeeSucks game = new CoffeeSucks();
        mainObj = game;
        game.title();
    }

    public void beginnerRoom() {
        JFrame lvlframe = new JFrame("Ticket To Ride - powered by CoffeeSucksEngine v1");
        currentFrame = lvlframe;
        currentRoom = "GameBoard";
        lvlframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //what do we do on close?
        lvlframe.add(this); //makes the paintComponent add
        lvlframe.setResizable(false); //locks size
        lvlframe.requestFocus();
        lvlframe.toFront();
        //frame.addKeyListener(p1); //every object with inputs needs this
        lvlframe.addMouseListener(this);
        lvlframe.addMouseMotionListener(this);
        lvlframe.setSize(screenSize); //sets size
        lvlframe.setVisible(true);
        timer.start();

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(snd_bgm).getAbsoluteFile());
            bgm = AudioSystem.getClip();
            bgm.open(audioInputStream);
            bgm.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex) {
        }

        //time to create funtime tons of polyboys
        RoadPath rp1 = new RoadPath(515, 122, 525, 26, 557, 27, 530, 121, "Green", 5, 1);
        hitBoxes.add(rp1);
        RoadPath rp2 = new RoadPath(532, 120, 579, 78, 583, 83, 536, 125, "Gray", 3, 2);
        hitBoxes.add(rp2);
        RoadPath rp3 = new RoadPath(538, 128, 564, 105, 592, 131, 538, 128, "Gray", 3, 3);
        hitBoxes.add(rp3);
        RoadPath rp4 = new RoadPath(584, 85, 593, 126, 599, 123, 592, 84, "Black", 2, 4);
        hitBoxes.add(rp4);
        RoadPath rp5 = new RoadPath(593, 63, 585, 32, 609, 27, 603, 65, "Gray", 2, 5);
        hitBoxes.add(rp5);
        RoadPath rp6 = new RoadPath(600, 123, 593, 83, 599, 81, 608, 120, "Pink", 2, 6);
        hitBoxes.add(rp6);
        RoadPath rp7 = new RoadPath(602, 81, 649, 117, 657, 105, 605, 76, "Yellow", 3, 7);
        hitBoxes.add(rp7);
        RoadPath rp8 = new RoadPath(602, 57, 685, 64, 683, 75, 604, 73, "Orange", 4, 8);
        hitBoxes.add(rp8);
        RoadPath rp9 = new RoadPath(661, 115, 684, 82, 691, 85, 666, 118, "Red", 2, 9);
        hitBoxes.add(rp9);
        RoadPath rp10 = new RoadPath(608, 122, 627, 106, 649, 119, 647, 125, "Green", 2, 10);
        hitBoxes.add(rp10);
        RoadPath rp11 = new RoadPath(701, 80, 749, 126, 763, 192, 751, 192, "Pink", 6, 11);
        hitBoxes.add(rp11);
        RoadPath rp12 = new RoadPath(670, 124, 723, 149, 749, 190, 742, 193, "White", 5, 12);
        hitBoxes.add(rp12);
        RoadPath rp13 = new RoadPath(616, 128, 744, 196, 742, 203, 612, 135, "Blue", 7, 13);
        hitBoxes.add(rp13);
        RoadPath rp14 = new RoadPath(611, 135, 741, 205, 737, 212, 607, 141, "Yellow", 7, 14);
        hitBoxes.add(rp14);
        RoadPath rp15 = new RoadPath(592, 217, 737, 211, 738, 219, 590, 226, "Black", 7, 15);
        hitBoxes.add(rp15);
        RoadPath rp16 = new RoadPath(591, 228, 670, 243, 673, 249, 612, 260, "Blue", 4, 16);
        hitBoxes.add(rp16);
        RoadPath rp17 = new RoadPath(689, 243, 744, 219, 748, 221, 729, 244, "Red", 3, 17);
        hitBoxes.add(rp17);
        RoadPath rp18 = new RoadPath(757, 215, 775, 212, 793, 314, 776, 314, "Green", 5, 18);
        hitBoxes.add(rp18);
        RoadPath rp19 = new RoadPath(706, 287, 750, 220, 763, 228, 714, 293, "Orange", 4, 19);
        hitBoxes.add(rp19);
        RoadPath rp20 = new RoadPath(771, 333, 780, 334, 744, 476, 726, 468, "Red", 7, 20);
        hitBoxes.add(rp20);
        RoadPath rp21 = new RoadPath(715, 294, 772, 315, 769, 322, 712, 301, "Black", 3, 21);
        hitBoxes.add(rp21);
        RoadPath rp22 = new RoadPath(748, 332, 768, 324, 770, 331, 752, 339, "Yellow", 1, 22);
        hitBoxes.add(rp22);
        RoadPath rp23 = new RoadPath(651, 329, 733, 337, 734, 344, 650, 335, "Black", 4, 23);
        hitBoxes.add(rp23);
        RoadPath rp24 = new RoadPath(686, 250, 704, 288, 695, 289, 681, 253, "Yellow", 2, 24);
        hitBoxes.add(rp24);
        RoadPath rp25 = new RoadPath(712, 303, 741, 330, 732, 335, 705, 307, "Blue", 2, 25);
        hitBoxes.add(rp25);
        RoadPath rp26 = new RoadPath(695, 296, 650, 289, 645, 326, 696, 298, "Red", 3, 26);
        hitBoxes.add(rp26);
        RoadPath rp27 = new RoadPath(734, 344, 710, 361, 720, 468, 739, 349, "Pink", 6, 27);
        hitBoxes.add(rp27);
        RoadPath rp28 = new RoadPath(650, 345, 709, 470, 716, 468, 710, 355, "White", 7, 28);
        hitBoxes.add(rp28);
        RoadPath rp29 = new RoadPath(725, 483, 771, 552, 777, 547, 732, 479, "Yellow", 4, 29);
        hitBoxes.add(rp29);
        RoadPath rp30 = new RoadPath(718, 489, 695, 535, 738, 542, 725, 488, "Orange", 3, 30);
        hitBoxes.add(rp30);
        RoadPath rp31 = new RoadPath(694, 556, 738, 597, 744, 592, 699, 550, "Red", 3, 31);
        hitBoxes.add(rp31);
        RoadPath rp32 = new RoadPath(658, 438, 696, 531, 689, 534, 647, 440, "Black", 5, 32);
        hitBoxes.add(rp32);
        RoadPath rp33 = new RoadPath(641, 443, 680, 537, 686, 536, 646, 440, "Blue", 5, 33);
        hitBoxes.add(rp33);
        RoadPath rp34 = new RoadPath(703, 468, 707, 463, 656, 428, 653, 433, "Green", 3, 34);
        hitBoxes.add(rp34);
        RoadPath rp35 = new RoadPath(643, 423, 650, 423, 646, 340, 639, 340, "Pink", 4, 35);
        hitBoxes.add(rp35);
        RoadPath rp36 = new RoadPath(643 - 8, 423, 650 - 8, 423, 646 - 8, 340, 639 - 8, 340, "Yellow", 4, 36);
        hitBoxes.add(rp36);
        RoadPath rp37 = new RoadPath(644, 320, 589, 231, 583, 235, 637, 323, "Green", 5, 37);
        hitBoxes.add(rp37);
        RoadPath rp38 = new RoadPath(644 - 10, 320 + 4, 589 - 10, 231 + 4, 583 - 10, 235 + 4, 637 - 10, 323 + 4, "Orange", 5, 38);
        hitBoxes.add(rp38);
        RoadPath rp39 = new RoadPath(580, 222, 599, 140, 607, 140, 588, 222, "White", 4, 39);
        hitBoxes.add(rp39);
        RoadPath rp40 = new RoadPath(580 - 7, 222 - 3, 599 - 7, 140 - 3, 607 - 7, 140 - 3, 588 - 7, 222 - 3, "Red", 4, 40);
        hitBoxes.add(rp40);
        RoadPath rp41 = new RoadPath(540, 165, 548, 135, 590, 136, 578, 176, "Orange", 3, 41);
        hitBoxes.add(rp41);
        RoadPath rp42 = new RoadPath(537, 175, 569, 215, 569, 222, 524, 218, "Yellow", 3, 42);
        hitBoxes.add(rp42);
        RoadPath rp43 = new RoadPath(483, 229, 521, 176, 526, 180, 492, 233, "Black", 3, 43);
        hitBoxes.add(rp43);
        RoadPath rp44 = new RoadPath(464, 142, 524, 162, 521, 169, 463, 149, "Blue", 3, 44);
        hitBoxes.add(rp44);
        RoadPath rp45 = new RoadPath(464, 136, 517, 129, 519, 120, 479, 99, "Gray", 3, 45);
        hitBoxes.add(rp45);
        RoadPath rp46 = new RoadPath(424, 182, 452, 152, 444, 147, 418, 180, "White", 2, 46);
        hitBoxes.add(rp46);
        RoadPath rp47 = new RoadPath(476, 232, 483, 231, 462, 151, 455, 152, "Red", 4, 47);
        hitBoxes.add(rp47);
        RoadPath rp48 = new RoadPath(431, 229, 433, 221, 472, 237, 468, 244, "Orange", 2, 48);
        hitBoxes.add(rp48);
        RoadPath rp49 = new RoadPath(489, 243, 567, 223, 570, 232, 506, 259, "Pink", 4, 49);
        hitBoxes.add(rp49);
        RoadPath rp50 = new RoadPath(474, 273, 478, 256, 485, 257, 482, 275, "Gray", 1, 50);
        hitBoxes.add(rp50);
        RoadPath rp51 = new RoadPath(474 - 7, 273 - 2, 478 - 7, 256 - 2, 485 - 7, 257 - 2, 482 - 7, 275 - 2, "Gray", 1, 51);
        hitBoxes.add(rp51);
        RoadPath rp52 = new RoadPath(430, 288, 437, 286, 416, 227, 408, 232, "Pink", 3, 52);
        hitBoxes.add(rp52);
        RoadPath rp53 = new RoadPath(442, 283, 460, 275, 462, 283, 444, 289, "Gray", 1, 53);
        hitBoxes.add(rp53);
        RoadPath rp54 = new RoadPath(442 + 2, 283 + 8, 460 + 2, 275 + 8, 462 + 2, 283 + 8, 444 + 2, 289 + 8, "Gray", 1, 54);
        hitBoxes.add(rp54);
        RoadPath rp55 = new RoadPath(442 + 5, 283 + 16, 460 + 5, 275 + 16, 462 + 5, 283 + 16, 444 + 5, 289 + 16, "Gray", 1, 55);
        hitBoxes.add(rp55);
        RoadPath rp56 = new RoadPath(423, 309, 430, 310, 429, 328, 423, 328, "Gray", 1, 56);
        hitBoxes.add(rp56);
        RoadPath rp57 = new RoadPath(423 + 8, 309, 430 + 8, 310, 429 + 8, 328, 423 + 8, 328, "Gray", 1, 57);
        hitBoxes.add(rp57);
        RoadPath rp58 = new RoadPath(423 + 16, 309, 430 + 16, 310, 429 + 16, 328, 423 + 16, 328, "Gray", 1, 58);
        hitBoxes.add(rp58);
        RoadPath rp59 = new RoadPath(481, 290, 556, 301, 557, 308, 476, 330, "Green", 4, 59);
        hitBoxes.add(rp59);
        RoadPath rp60 = new RoadPath(559, 235, 564, 237, 564, 297, 553, 298, "Gray", 3, 60);
        hitBoxes.add(rp60);
        RoadPath rp61 = new RoadPath(559 + 10, 235, 564 + 10, 237, 564 + 10, 297, 553 + 10, 298, "Gray", 3, 61);
        hitBoxes.add(rp61);
        RoadPath rp62 = new RoadPath(449, 334, 521, 376, 516, 381, 447, 339, "Gray", 4, 62);
        hitBoxes.add(rp62);
        RoadPath rp63 = new RoadPath(449 - 2, 334 + 8, 521 - 2, 376 + 8, 516 - 2, 381 + 8, 447 - 2, 339 + 8, "Gray", 4, 63);
        hitBoxes.add(rp63);
        RoadPath rp64 = new RoadPath(425, 353, 434, 353, 438, 372, 432, 372, "Gray", 1, 64);
        hitBoxes.add(rp64);
        RoadPath rp65 = new RoadPath(425 + 8, 353 - 2, 434 + 8, 353 - 2, 438 + 8, 372 - 2, 432 + 8, 372 - 2, "Gray", 1, 65);
        hitBoxes.add(rp65);
        RoadPath rp66 = new RoadPath(451, 386, 483, 410, 480, 416, 447, 393, "Gray", 2, 66);
        hitBoxes.add(rp66);
        RoadPath rp67 = new RoadPath(451 + 8, 386 - 6, 483 + 8, 410 - 6, 480 + 8, 416 - 6, 447 + 8, 393 - 6, "Gray", 2, 67);
        hitBoxes.add(rp67);
        RoadPath rp68 = new RoadPath(434, 390, 442, 388, 431, 450, 424, 448, "Gray", 3, 68);
        hitBoxes.add(rp68);
        RoadPath rp69 = new RoadPath(432, 452, 482, 417, 487, 423, 437, 457, "Gray", 3, 69);
        hitBoxes.add(rp69);
        RoadPath rp70 = new RoadPath(439, 458, 499, 449, 500, 456, 439, 464, "Gray", 3, 70);
        hitBoxes.add(rp70);
        RoadPath rp71 = new RoadPath(438, 467, 494, 483, 492, 488, 438, 492, "Gray", 3, 71);
        hitBoxes.add(rp71);
        RoadPath rp72 = new RoadPath(422, 469, 428, 469, 425, 488, 417, 486, "Green", 1, 72);
        hitBoxes.add(rp72);
        RoadPath rp73 = new RoadPath(493, 401, 510, 392, 515, 396, 496, 410, "Gray", 1, 73);
        hitBoxes.add(rp73);
        RoadPath rp74 = new RoadPath(493 + 3, 401 + 8, 510 + 3, 392 + 8, 515 + 3, 396 + 8, 496 + 3, 410 + 8, "Gray", 1, 74);
        hitBoxes.add(rp74);
        RoadPath rp75 = new RoadPath(460, 511, 494, 494, 497, 497, 463, 517, "Black", 2, 75);
        hitBoxes.add(rp75);
        RoadPath rp76 = new RoadPath(488, 428, 496, 425, 503, 441, 497, 444, "Gray", 1, 76);
        hitBoxes.add(rp76);
        RoadPath rp77 = new RoadPath(488 + 8, 428 - 3, 496 + 8, 425 - 3, 503 + 8, 441 - 3, 497 + 8, 444 - 3, "Gray", 1, 77);
        hitBoxes.add(rp77);
        RoadPath rp78 = new RoadPath(522, 404, 529, 406, 520, 444, 513, 442, "Gray", 2, 78);
        hitBoxes.add(rp78);
        RoadPath rp79 = new RoadPath(522 + 8, 404 + 1, 529 + 8, 406 + 1, 520 + 8, 444 + 1, 513 + 8, 442 + 1, "Gray", 2, 79);
        hitBoxes.add(rp79);
        RoadPath rp80 = new RoadPath(503, 460, 510, 460, 510, 480, 502, 480, "Blue", 1, 80);
        hitBoxes.add(rp80);
        RoadPath rp81 = new RoadPath(526, 384, 557, 313, 564, 312, 558, 360, "Blue", 4, 81);
        hitBoxes.add(rp81); //iffy hitboxes
        RoadPath rp82 = new RoadPath(530, 385, 556, 351, 569, 316, 584, 376, "Yellow", 4, 82);
        hitBoxes.add(rp82); //iffy hitboxes
        RoadPath rp83 = new RoadPath(580, 312, 630, 333, 592, 358, 574, 316, "Gray", 3, 83);
        hitBoxes.add(rp83);
        RoadPath rp84 = new RoadPath(540, 389, 574, 404, 575, 411, 539, 397, "Gray", 2, 84);
        hitBoxes.add(rp84);
        RoadPath rp85 = new RoadPath(540 - 3, 389 + 8, 574 - 3, 404 + 8, 575 - 3, 411 + 8, 539 - 3, 397 + 8, "Gray", 2, 85);
        hitBoxes.add(rp85);
        RoadPath rp86 = new RoadPath(540 + 57, 389 + 22, 574 + 57, 404 + 22, 575 + 57, 411 + 22, 539 + 57, 397 + 22, "Gray", 2, 86);
        hitBoxes.add(rp86);
        RoadPath rp87 = new RoadPath(540 + 57, 389 + 30, 574 + 57, 404 + 30, 575 + 57, 411 + 30, 539 + 57, 397 + 30, "Gray", 2, 87);
        hitBoxes.add(rp87);
        RoadPath rp88 = new RoadPath(517, 489, 534, 494, 532, 501, 513, 495, "Pink", 1, 88);
        hitBoxes.add(rp88);
        RoadPath rp89 = new RoadPath(526, 456, 547, 484, 543, 488, 519, 456, "Gray", 2, 89);
        hitBoxes.add(rp89);
        RoadPath rp90 = new RoadPath(526 - 8, 456 + 2, 547 - 8, 484 + 2, 543 - 8, 488 + 2, 519 - 8, 456 + 2, "Gray", 2, 90);
        hitBoxes.add(rp90);
        RoadPath rp91 = new RoadPath(559, 494, 591, 517, 588, 523, 555, 500, "Gray", 2, 91);
        hitBoxes.add(rp91);
        RoadPath rp92 = new RoadPath(559 - 3, 494 + 8, 591 - 3, 517 + 8, 588 - 3, 523 + 8, 555 - 3, 500 + 8, "Gray", 2, 92);
        hitBoxes.add(rp92);
        RoadPath rp93 = new RoadPath(603, 522, 621, 523, 623, 528, 604, 529, "Gray", 1, 93);
        hitBoxes.add(rp93);
        RoadPath rp94 = new RoadPath(603, 522 + 8, 621, 523 + 8, 623, 528 + 8, 604, 529 + 8, "Gray", 1, 94);
        hitBoxes.add(rp94);
        RoadPath rp95 = new RoadPath(629, 440, 638, 440, 634, 522, 629, 522, "Orange", 4, 95);
        hitBoxes.add(rp95);
        RoadPath rp96 = new RoadPath(641, 529, 679, 537, 677, 545, 639, 535, "Gray", 2, 96);
        hitBoxes.add(rp96);
        RoadPath rp97 = new RoadPath(641 - 2, 529 + 8, 679 - 2, 537 + 8, 677 - 2, 545 + 8, 639 - 2, 535 + 8, "Gray", 2, 97);
        hitBoxes.add(rp97);
        RoadPath rp98 = new RoadPath(589, 580, 686, 555, 653, 588, 589, 585, "Gray", 5, 98);
        hitBoxes.add(rp98);
        RoadPath rp99 = new RoadPath(589, 588, 626, 601, 623, 608, 588, 593, "Pink", 2, 99);
        hitBoxes.add(rp99);
        RoadPath rp100 = new RoadPath(538, 609, 571, 592, 564, 618, 537, 613, "Blue", 2, 100);
        hitBoxes.add(rp100);
        RoadPath rp101 = new RoadPath(518, 595, 534, 581, 539, 585, 520, 600, "White", 1, 101);
        hitBoxes.add(rp101);
        RoadPath rp102 = new RoadPath(552, 575, 573, 579, 569, 587, 550, 584, "Yellow", 1, 102);
        hitBoxes.add(rp102);
        RoadPath rp103 = new RoadPath(587, 538, 593, 538, 584, 579, 575, 577, "Red", 2, 103);
        hitBoxes.add(rp103);
        RoadPath rp104 = new RoadPath(540, 508, 549, 508, 549, 569, 540, 569, "Green", 3, 104);
        hitBoxes.add(rp104);
        RoadPath rp105 = new RoadPath(495, 564, 535, 572, 532, 578, 494, 569, "Black", 2, 105);
        hitBoxes.add(rp105);
        RoadPath rp106 = new RoadPath(494, 553, 532, 507, 539, 511, 499, 557, "Gray", 3, 106);
        hitBoxes.add(rp106);
        RoadPath rp107 = new RoadPath(483, 555, 500, 495, 509, 498, 487, 556, "White", 3, 107);
        hitBoxes.add(rp107);
        RoadPath rp108 = new RoadPath(436, 548, 476, 557, 472, 565, 433, 555, "Yellow", 2, 108);
        hitBoxes.add(rp108);
        RoadPath rp109 = new RoadPath(480, 575, 484, 607, 442, 596, 472, 572, "Orange", 2, 109);
        hitBoxes.add(rp109);
        RoadPath rp110 = new RoadPath(525, 140, 526, 160, 532, 160, 531, 140, "White", 1, 110);
        hitBoxes.add(rp110);
        
        //***********************************CURRENT WORK ZONE************************************
        //Task 1 - Creating the Areas
        //For now, just enter 0,0 for the x and y, I can do those later 
        //Check the "NAMING ASSIST" folder to see what labels I'd like to give them 
        //(if you can't read it, just do whatever)
        //TEMPLATE FOR THE FIRST PLACE
        ArrayList<RoadPath> L1Path = new ArrayList<>(); //initalize an arraylist for the locations
        L1Path.add(rp2); //add all the path objects, they are named rp__ with the __ being the number, p simple
        L1Path.add(rp4);
        L1Path.add(rp6);
        L1Path.add(rp7);
        L1Path.add(rp8);
        L1Path.add(rp5);
        places.add(new Place(1,L1Path,0,0)); //add a new place to the places array, (IDNUMBER,Array,x,y)
        
        
        
        for (int i = 0; i<55; i++){
        tickDeck.add(new Ticket(0,0,i,"short","deck"));    
        }
        for (int i = 55; i<90; i++){
        tickDeck.add(new Ticket(0,0,i,"long","deck"));    
        }
        
        Collections.shuffle(tickDeck);

        Deck deck1 = new Deck(1130, 605);
        objs.add(deck1);

        for (int i = 0; i < 12; i++) {

            deck.add("Pink");
            deck.add("White");
            deck.add("Blue");
            deck.add("Yellow");
            deck.add("Orange");
            deck.add("Black");
            deck.add("Red");
            deck.add("Green");
            deck.add("Rainbow");

        }

        deck.add("Rainbow");
        deck.add("Rainbow");
        Collections.shuffle(deck);

        //fillDock();
        //give first player first cards
        currentHand.add(new Card(1130, 605, deck.get(0), "hand", hand[colorID(deck.get(0))], 675));
        deck.remove(0);
        currentHand.add(new Card(1130, 605, deck.get(0), "hand", hand[colorID(deck.get(0))], 675));
        deck.remove(0);
        currentHand.add(new Card(1130, 605, deck.get(0), "hand", hand[colorID(deck.get(0))], 675));
        deck.remove(0);
        currentHand.add(new Card(1130, 605, deck.get(0), "hand", hand[colorID(deck.get(0))], 675));
        deck.remove(0);
        startHandSize = 4;

        MusicOff music = new MusicOff();
        objs.add(music);

        for (int i = 0; i < numPlayers; i++) {
            myPlayers[i] = new Player(i);
        }

        objs.add(new PlayerText(currentPlayer));

        objs.add(new AnimatedTrain(0, 1));

    }

    public void title() {

        JFrame frame = new JFrame("Ticket To Ride - powered by CoffeeSucksEngine v1");
        currentFrame = frame;
        currentRoom = "Title";
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //what do we do on close?
        frame.add(this); //makes the paintComponent add
        frame.setResizable(false); //locks size
        frame.requestFocus();
        frame.toFront();
        //frame.addKeyListener(p1); //every object with inputs needs this
        frame.addMouseListener(this);
        frame.addMouseMotionListener(this);
        frame.setSize(screenSize); //sets size
        frame.setVisible(true);
        frame.setBackground(Color.BLACK);
        timer.start();

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(snd_title).getAbsoluteFile());
            titlemusic = AudioSystem.getClip();
            titlemusic.open(audioInputStream);
            titlemusic.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex) {
        }

        String newGameButton = (dir + "\\TTRAssets\\newgame.png");
        String loadGameButton = (dir + "\\TTRAssets\\loadgame.png");

        Button twoplayers = new Button("2P", 67, 77, 45, 385, null);
        Button threeplayers = new Button("3P", 67, 77, 42, 458, null);
        Button fourplayers = new Button("4P", 67, 77, 42, 528, null);
        Button fiveplayers = new Button("5P", 67, 77, 41, 603, null);

        titleobjs.add(twoplayers);
        titleobjs.add(threeplayers);
        titleobjs.add(fourplayers);
        titleobjs.add(fiveplayers);

        Button newgame = new Button("NEW", 118, 493, 329, 361, newGameButton);
        titleobjs.add(newgame);
        Button loadgame = new Button("LOAD", 118, 493, 329, 531, loadGameButton);
        titleobjs.add(loadgame);

    }

    @Override
    protected void paintComponent(Graphics g) {
        long start = System.nanoTime();
        super.paintComponent(g);
        g.setFont(bigFont); // *SETS FONT FOR GAME**
        int width = this.getWidth();
        int height = this.getHeight();
        g.setColor(Color.white); //BACKGROUND COLOR OF ROOM
        //g.fillRect(0, 0, width, height);
        //the rest of this is frametime stuff, PUT STEPS HERE
        if (frameCount == FPS) {
            averageTime = totalTime / FPS;
            totalTime = 0;
            frameCount = 0;
            debugFrames++;
        } else {
            totalTime += System.nanoTime() - start;
            step();;
            frameCount++;
        }

        if (currentRoom.equals("GameBoard")) {
            //drawing specifc things
            BufferedImage imgBG = null;
            String pathBG = (dir + "\\TTRAssets\\boardcompressed.gif");
            BufferedImage bgFrame = null;
            String bgFramepath = (dir + "\\TTRAssets\\mapborder.gif");
            BufferedImage mapBG = null;
            String pathMap = (dir + "\\TTRAssets\\mapcompressedrugged.gif");
            Image coolBG = null;
            String pathcoolBG = (dir + "\\TTRAssets\\oldpaper.jpg");
            try {
                imgBG = ImageIO.read(new File(pathBG));
                bgFrame = ImageIO.read(new File(bgFramepath));
                coolBG = new ImageIcon(pathcoolBG).getImage();
                mapBG = ImageIO.read(new File(pathMap));
                setBackground(imgBG);
            } catch (IOException e) {
            }
            g.drawImage(coolBG, -0, 0, this);
            g.drawImage(mapBG, -0, 0, this);
            g.drawImage(bgFrame, 0, 0, this);
            g.drawImage(ROOM_BACKGROUND, 0, 0, this);
            g.setColor(Color.BLACK);
            /*
            g.drawString(Integer.toString(dockedCards.size()), 40, 50);
            g.drawString(Integer.toString(currentHand.size()), 40, 70);
            g.drawString(Integer.toString(debugTime), 40, 90);
            g.drawString(Integer.toString(debugFrames), 40, 110);
            g.drawString(Integer.toString(deck.size()), 40, 120);
             */
            //the magic line. This draws every object in the objs array
            Graphics2D g2 = (Graphics2D) g;

            for (GameObject curr : dockedCards) {
                if (curr.visible) {
                    g.drawImage(curr.sprite_index, curr.x, curr.y, this);
                    g.drawImage(curr.topSpr, curr.x, curr.y, this);
                    g2.setColor(Color.BLACK);
                    //g2.draw(curr.mask);
                }

            }
            for (Card curr : currentHand) {
                g.drawImage(curr.sprite_index, curr.x, curr.y, this);
                g.drawImage(curr.topSpr, curr.x, curr.y, this);
                g2.setColor(Color.BLACK);
                //g2.draw(curr.mask);

            }
            for (Card curr : selectedCards) {
                g.drawImage(curr.sprite_index, curr.x, curr.y, this);
                g.drawImage(curr.topSpr, curr.x, curr.y, this);
                g2.setColor(Color.BLACK);
                //g2.draw(curr.mask);

            }
            for (RoadPath curr : hitBoxes) {
                g.setColor(Color.BLACK);
                //g.drawPolygon(curr.boundBox);
            }

            Image s_sideUI = null;
            try {
                String sideUI = (dir + "\\TTRAssets\\ui_" + numPlayers + ".gif");
                s_sideUI = ImageIO.read(new File(sideUI));
            } catch (IOException e) {

            }
            g.drawImage(s_sideUI, 0, 0, this);

            //g.drawString()
            String playerArt = "";
            String playerUI = "";
            Image player_ui;
            if (currentPlayer == 1) {
                playerArt = (dir + "\\TTRAssets\\players\\" + currentPlayer + ".png");
                player_icon = new ImageIcon(playerArt).getImage();
                g.drawImage(player_icon, 42, 595, this);
                playerUI = (dir + "\\TTRAssets\\UI\\ui_" + currentPlayer + ".png");
                player_ui = new ImageIcon(playerUI).getImage();
                g.drawImage(player_ui, 144, 578, this);
            }
            if (currentPlayer == 2) {
                playerArt = (dir + "\\TTRAssets\\players\\" + currentPlayer + ".png");
                player_icon = new ImageIcon(playerArt).getImage();
                g.drawImage(player_icon, 29, 600, this);
                playerUI = (dir + "\\TTRAssets\\UI\\ui_" + currentPlayer + ".png");
                player_ui = new ImageIcon(playerUI).getImage();
                g.drawImage(player_ui, 144, 578, this);
            }
            if (currentPlayer == 3) {
                playerArt = (dir + "\\TTRAssets\\players\\" + currentPlayer + ".png");
                player_icon = new ImageIcon(playerArt).getImage();
                g.drawImage(player_icon, 40, 601, this);
                playerUI = (dir + "\\TTRAssets\\UI\\ui_" + currentPlayer + ".png");
                player_ui = new ImageIcon(playerUI).getImage();
                g.drawImage(player_ui, 144, 578, this);
            }
            if (currentPlayer == 4) {
                playerArt = (dir + "\\TTRAssets\\players\\" + currentPlayer + ".png");
                player_icon = new ImageIcon(playerArt).getImage();
                g.drawImage(player_icon, 34, 597, this);
                playerUI = (dir + "\\TTRAssets\\UI\\ui_" + currentPlayer + ".png");
                player_ui = new ImageIcon(playerUI).getImage();
                g.drawImage(player_ui, 144, 578, this);
            }
            if (currentPlayer == 5) {
                playerArt = (dir + "\\TTRAssets\\players\\" + currentPlayer + ".png");
                player_icon = new ImageIcon(playerArt).getImage();
                g.drawImage(player_icon, 35, 603, this);
                playerUI = (dir + "\\TTRAssets\\UI\\ui_" + currentPlayer + ".png");
                player_ui = new ImageIcon(playerUI).getImage();
                g.drawImage(player_ui, 144, 578, this);
            }

            for (GameObject curr : objs) {
                if (curr.visible) {
                    g2.drawImage(curr.sprite_index, curr.x, curr.y, this);
                    g.drawImage(curr.topSpr, curr.x, curr.y, this);
                    g2.setColor(Color.BLACK);
                    //g2.draw(curr.mask);
                }

            }

            if (cardCount("Red") > 1) {
                g.setColor(Color.RED);
                g.drawString(Integer.toString(cardCount("Red")), hand[0] + 55, 660);
            }
            if (cardCount("Orange") > 1) {
                g.setColor(Color.ORANGE);
                g.drawString(Integer.toString(cardCount("Orange")), hand[1] + 55, 660);
            }
            if (cardCount("Yellow") > 1) {
                g.setColor(Color.YELLOW);
                g.drawString(Integer.toString(cardCount("Yellow")), hand[2] + 55, 660);
            }
            if (cardCount("Green") > 1) {
                g.setColor(Color.GREEN);
                g.drawString(Integer.toString(cardCount("Green")), hand[3] + 55, 660);
            }
            if (cardCount("Blue") > 1) {
                g.setColor(Color.BLUE);
                g.drawString(Integer.toString(cardCount("Blue")), hand[4] + 55, 660);
            }
            if (cardCount("Pink") > 1) {
                g.setColor(Color.MAGENTA);
                g.drawString(Integer.toString(cardCount("Pink")), hand[5] + 55, 660);
            }
            if (cardCount("Black") > 1) {
                g.setColor(Color.BLACK);
                g.drawString(Integer.toString(cardCount("Black")), hand[6] + 55, 660);
            }
            if (cardCount("White") > 1) {
                g.setColor(Color.WHITE);
                g.drawString(Integer.toString(cardCount("White")), hand[7] + 55, 660);
            }
            if (cardCount("Rainbow") > 1) {
                g.setColor(rainbow[randRainbow]);
                g.drawString(Integer.toString(cardCount("Rainbow")), hand[8] + 55, 660);
            }
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(myPlayers[currentPlayer - 1].numTrains), 170, 630);

            String textBox = (dir + "\\TTRAssets\\textbox.png");
            Image tb = new ImageIcon(textBox).getImage();
            g.drawImage(tb, 853, 50, this);

            g.setColor(rolled);
            g.drawString(rollover1, 865, 110);
            g.setColor(Color.BLACK);
            g.drawString(rollover2, 865, 140);
            g.drawString(rollover3, 865, 170);
            /* //debug stuff
            g.drawString(Integer.toString(debugCount), 865, 210);
            g.drawString(Integer.toString(selectedCards.size()), 865, 240);
            g.drawString(specialTest, 865, 260);
            g.drawString(test2, 865, 280);
             */

            if (numPlayers == 2) {
                g.setColor(Color.WHITE);
                g.drawString(Integer.toString(myPlayers[0].myScore), 100, ui_locations[numPlayers][myPlayers[0].ID + 1]);
                g.drawString(Integer.toString(myPlayers[1].myScore), 100, ui_locations[numPlayers][myPlayers[1].ID + 1]);
            }

            if (numPlayers == 3) {
                g.setColor(Color.WHITE);
                g.drawString(Integer.toString(myPlayers[0].myScore), 100, ui_locations[numPlayers][myPlayers[0].ID + 1]);
                g.drawString(Integer.toString(myPlayers[1].myScore), 100, ui_locations[numPlayers][myPlayers[1].ID + 1]);
                g.drawString(Integer.toString(myPlayers[2].myScore), 100, ui_locations[numPlayers][myPlayers[2].ID + 1]);
            }

            if (numPlayers == 4) {
                g.setColor(Color.WHITE);
                g.drawString(Integer.toString(myPlayers[0].myScore), 100, ui_locations[numPlayers][myPlayers[0].ID + 1]);
                g.drawString(Integer.toString(myPlayers[1].myScore), 100, ui_locations[numPlayers][myPlayers[1].ID + 1]);
                g.drawString(Integer.toString(myPlayers[2].myScore), 100, ui_locations[numPlayers][myPlayers[2].ID + 1]);
                g.drawString(Integer.toString(myPlayers[3].myScore), 100, ui_locations[numPlayers][myPlayers[3].ID + 1]);
            }

            if (numPlayers == 5) {
                g.setColor(Color.WHITE);
                g.drawString(Integer.toString(myPlayers[0].myScore), 100, ui_locations[numPlayers][myPlayers[0].ID + 1]);
                g.drawString(Integer.toString(myPlayers[1].myScore), 100, ui_locations[numPlayers][myPlayers[1].ID + 1]);
                g.drawString(Integer.toString(myPlayers[2].myScore), 100, ui_locations[numPlayers][myPlayers[2].ID + 1]);
                g.drawString(Integer.toString(myPlayers[3].myScore), 100, ui_locations[numPlayers][myPlayers[3].ID + 1]);
                g.drawString(Integer.toString(myPlayers[4].myScore), 100, ui_locations[numPlayers][myPlayers[4].ID + 1]);
            }

        }
        if (currentRoom.equals("Title")) {
            //drawing specifc things
            BufferedImage imgBG = null;
            String pathBG = (dir + "\\TTRAssets\\titlescreen.png");
            Image aniBG = null;
            String aniPath = (dir + "\\TTRAssets\\animatedtitle.gif");
            try {
                imgBG = ImageIO.read(new File(pathBG));
                aniBG = new ImageIcon(aniPath).getImage();
                setBackground(imgBG);
            } catch (IOException e) {
            }

            g.drawImage(aniBG, 0, 100, this);
            g.drawImage(ROOM_BACKGROUND, 0, 0, this);

            g.setColor(Color.BLACK);
            Graphics2D g2 = (Graphics2D) g;

            for (GameObject curr : titleobjs) {
                if (curr.visible) {
                    g2.drawImage(curr.sprite_index, curr.x, curr.y, this);
                    g.drawImage(curr.topSpr, curr.x, curr.y, this);
                    g2.setColor(Color.BLACK);
                    //g2.draw(curr.mask);
                }

                Image arrow = null;
                String arrowPath = (dir + "\\TTRAssets\\arrows.png");
                arrow = new ImageIcon(arrowPath).getImage();
                g.drawImage(arrow, 147, pSelectHeights[numPlayers - 2], this);

            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
    }

    public void setBackground(Image bg) {
        ROOM_BACKGROUND = bg;
    }

    public void step() {

        if (currentRoom == "GameBoard") {
            if (dockedCards.size() < 5) {
                fillDock();
            }
        }

        if (counting) {
            if (countdown < 75) {
                countdown++;
            } else {
                currentFrame.setVisible(false);
                mainObj.beginnerRoom();
                counting = false;
                //stopSound(titlemusic);
                titleobjs.clear();
            }
        }

        Random r = new Random();
        randRainbow = r.nextInt(8);

        for (GameObject curr : objs) {
            curr.step();
            //curr.collisionCheck(objs);
        }
        for (Card curr2 : dockedCards) {
            curr2.step();
        }
        for (Card curr3 : currentHand) {
            curr3.step();
        }
        for (Card curr4 : selectedCards) {
            curr4.step();
        }
        debugTime++;

        boolean doneMove = false;

        for (Card curr4 : currentHand) {
            if (curr4.isMoving() == false) {
                doneMove = true;
            } else {
                doneMove = false;
            }
        }

        if (cardsTaken > 1) {
            if (doneMove) {
                endTurn();
            }
        }

        for (GameObject curr : objs) {
            if (curr.x > 1280) {
                curr.visible = false;
                curr.hspeed = 0;
                curr.vspeed = 0;
                objs.remove(curr);
            }
            if (curr instanceof PlayerText) {
                if ((curr.x > 340) && (curr.hspeed < 50)) {
                    curr.hspeed += 5;
                }
                if ((curr.x > 300) && (curr.x < 330)) {
                    curr.hspeed = 5;
                }
            }
        }

        if (deck.size() == 75) {
            for (GameObject curr : objs) {
                if (curr instanceof Deck) {
                    curr.sprite_index = ((Deck) curr).s_deck_mid;
                }
            }
        }

        if (deck.size() == 40) {
            for (GameObject curr : objs) {
                if (curr instanceof Deck) {
                    curr.sprite_index = ((Deck) curr).s_deck_low;
                }
            }
        }

    }

    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY() - 25;
        Point p = new Point(x, y);

        for (RoadPath curr : hitBoxes) {

            if (curr.boundBox.contains(p)) {

                rollover1 = curr.myColor + " path.";
                rollover2 = "Length of " + Integer.toString(curr.myLength);
                //debugCount = countCards(curr.myColor);

                if (curr.myOwn != 0) {
                    rollover3 = "Owned By:Player " + Integer.toString(curr.myOwn);
                }

                if (curr.myOwn == 0) {
                    rollover3 = "Available";
                }

                if (curr.myColor.equals("Red")) {
                    rolled = Color.RED;
                }
                if (curr.myColor.equals("Yellow")) {
                    rolled = Color.YELLOW;
                }
                if (curr.myColor.equals("Orange")) {
                    rolled = Color.ORANGE;
                }
                if (curr.myColor.equals("Green")) {
                    rolled = Color.GREEN;
                }
                if (curr.myColor.equals("Blue")) {
                    rolled = Color.CYAN;
                }
                if (curr.myColor.equals("Pink")) {
                    rolled = Color.MAGENTA;
                }
                if (curr.myColor.equals("Black")) {
                    rolled = Color.BLACK;
                }
                if (curr.myColor.equals("White")) {
                    rolled = Color.GRAY;
                }
                if (curr.myColor.equals("Gray")) {
                    rolled = Color.darkGray;
                }
            }

        }
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Point p = new Point(x, y);

    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY() - 25;
        Point p = new Point(x, y);

        for (GameObject curr : titleobjs) {
            if (curr.mask.contains(p)) {
                if (curr.visible) {
                    if (curr instanceof Button) {

                        if (((Button) curr).myID == "2P") {
                            numPlayers = 2;
                        }
                        if (((Button) curr).myID == "3P") {
                            numPlayers = 3;
                        }
                        if (((Button) curr).myID == "4P") {
                            numPlayers = 4;
                        }
                        if (((Button) curr).myID == "5P") {
                            numPlayers = 5;
                        }
                        if (((Button) curr).myID == "NEW") {
                            if (!counting) {
                                counting = true;
                                playSound(snd_begin, false);
                                stopSound(titlemusic);
                            }
                        }
                        playSound(snd_blip, false);
                    }
                }
            }
        }

        for (GameObject curr : objs) {
            if (curr.mask.contains(p)) {
                if (curr instanceof Deck) {
                    if (selectedCards.size() == 0) {
                        if (cardsTaken < 2) {
                            if (deck.get(0).equals("Rainbow")) {
                                cardsTaken++;
                            } else {
                                cardsTaken++;
                            }
                            playSound(snd_drawcard, false);
                            currentHand.add(new Card(curr.getX(), curr.getY(),
                                    deck.get(0), "hand", hand[colorID(deck.get(0))],
                                    675));
                            startHandSize++;
                            deck.remove(0);
                        }

                    }
                }

                if (curr instanceof MusicOff) {
                    if (bgmPlay) {
                        stopSound(bgm);
                        playSound(snd_blip, false);
                        bgmPlay = false;
                    } else {
                        try {
                            AudioInputStream audioInputStream
                                    = AudioSystem.getAudioInputStream(new File(snd_bgm).getAbsoluteFile());
                            bgm = AudioSystem.getClip();
                            bgm.open(audioInputStream);
                            bgm.loop(Clip.LOOP_CONTINUOUSLY);
                            bgmPlay = true;
                        } catch (Exception ex) {
                        }
                    }
                }
            }
        }

        for (Card curr : dockedCards) {
            if (curr.mask.contains(p)) {
                if (curr.vspeed == 0) {
                    if (selectedCards.size() == 0) {
                        if (cardsTaken == 1) {
                            if (curr.myColor != "Rainbow") {

                                if (curr.myColor == "Rainbow") {

                                    cardsTaken += 2;
                                } else {
                                    cardsTaken++;
                                }

                                playSound(snd_drawcard, false);
                                currentHand.add(new Card(curr.getX(), curr.getY(), curr.myColor, "hand", hand[curr.colorID], 675));
                                startHandSize++;
                                curr.visible = false;

                                for (Card curr2 : dockedCards) {
                                    if (curr == curr2) {
                                        dockedCards.remove(curr);
                                    }
                                }

                            }
                        }
                        if (cardsTaken == 0) {

                            if (curr.myColor == "Rainbow") {

                                cardsTaken += 2;
                            } else {
                                cardsTaken++;
                            }

                            playSound(snd_drawcard, false);
                            currentHand.add(new Card(curr.getX(), curr.getY(), curr.myColor, "hand", hand[curr.colorID], 675));
                            startHandSize++;
                            curr.visible = false;

                            for (Card curr2 : dockedCards) {
                                if (curr == curr2) {
                                    dockedCards.remove(curr);
                                }
                            }

                        }
                    }
                }

            }
        }
        if (cardsTaken == 0) {
            for (Card curr : currentHand) {
                if (curr.mask.contains(p)) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        if (curr.vspeed == 0) {
                            playSound(snd_liftcard, false);
                            selectedCards.add(new Card(/*curr.getX(), curr.getY(),*/curr.getX(), curr.getY(), curr.myColor, "selected", curr.getX(), curr.getY() - 60));
                            curr.visible = false;
                            for (Card curr2 : currentHand) {
                                if (curr == curr2) {
                                    currentHand.remove(curr);
                                }
                            }
                        }

                    }
                }
            }

            for (Card curr : selectedCards) {
                if (curr.mask.contains(p)) {
                    if (curr.vspeed == 0) {
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            playSound(snd_retractcard, false);
                            currentHand.add(new Card(/*curr.getX(), curr.getY(),*/curr.getX(), curr.getY(), curr.myColor, "hand", curr.getX(), curr.getY() + 60));
                            curr.visible = false;
                            for (Card curr2 : selectedCards) {
                                if (curr == curr2) {
                                    selectedCards.remove(curr);
                                }
                            }
                        }
                    }

                }
            }

            for (RoadPath curr : hitBoxes) {
                if (curr.boundBox.contains(p)) {
                    if (curr.myOwn == 0) {
                        //debugCount = countCards(curr.myColor);
                        if (countCards(curr.myColor) == curr.myLength) {
                            objs.add(new AnimatedTrain(currentPlayer, curr.ID));
                            playSound(snd_blip, false);
                            playSound(snd_train, false);
                            playSound(snd_money, false);
                            selectedCards.clear();
                            myPlayers[currentPlayer - 1].numTrains -= curr.myLength;
                            curr.myOwn = currentPlayer;
                            if (curr.myLength == 1) {
                                myPlayers[currentPlayer - 1].myScore += 1;
                            }
                            if (curr.myLength == 2) {
                                myPlayers[currentPlayer - 1].myScore += 2;
                            }
                            if (curr.myLength == 3) {
                                myPlayers[currentPlayer - 1].myScore += 4;
                            }
                            if (curr.myLength == 4) {
                                myPlayers[currentPlayer - 1].myScore += 7;
                            }
                            if (curr.myLength == 5) {
                                myPlayers[currentPlayer - 1].myScore += 10;
                            }
                            if (curr.myLength == 6) {
                                myPlayers[currentPlayer - 1].myScore += 15;
                            }
                            if (curr.myLength == 7) {
                                myPlayers[currentPlayer - 1].myScore += 18;
                            }

                            if (curr.myOwn != 0) {
                                rollover3 = "Owned By:Player " + Integer.toString(curr.myOwn);
                            }

                            if (curr.myOwn == 0) {
                                rollover3 = "Available";
                            }
                            endTurn();
                        } else {
                            playSound(snd_nope, false);
                        }
                    }

                }
            }
        }
    }

    public String toString() {
        return "hello";
    }

    public void playSound(String soundfile, boolean loop) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundfile).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            if (!loop) {
                clip.start();
            } else {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (Exception ex) {
        }
    }

    public void stopSound(Clip c) {
        try {
            c.close();
        } catch (Exception ex) {

        }
    }

    public void endTurn() {

        if (turn == 0) {
            startHandSize = 4;
        }

        myPlayers[currentPlayer - 1].myHand = currentHand;
        cardsTaken = 0;

        if (currentPlayer < numPlayers) {
            currentPlayer++;
        } else {
            currentPlayer = 1;
        }

        currentHand = myPlayers[currentPlayer - 1].myHand;

        startHandSize = currentHand.size();

        for (Card curr : currentHand) {
            curr.visible = true;
        }

        cleanUpHand();
        objs.add(new PlayerText(currentPlayer));

        if (currentPlayer == 1) {
            turn++;
        }

        if (turn == 0) {
            currentHand.add(new Card(1130, 605, deck.get(0), "hand", hand[colorID(deck.get(0))], 675));
            deck.remove(0);
            currentHand.add(new Card(1130, 605, deck.get(0), "hand", hand[colorID(deck.get(0))], 675));
            deck.remove(0);
            currentHand.add(new Card(1130, 605, deck.get(0), "hand", hand[colorID(deck.get(0))], 675));
            deck.remove(0);
            currentHand.add(new Card(1130, 605, deck.get(0), "hand", hand[colorID(deck.get(0))], 675));
            deck.remove(0);
            startHandSize = 4;
        }

    }

    public void fillDock() {
        playSound(snd_deck, false);
        while (dockedCards.size() < 5) {

            for (int i = 0; i < dockedCards.size(); i++) {
                dockedCards.get(i).moveTowardsPoint(
                        dockedCards.get(i).x, dock[i], 10);
            }
            //for (int i = 0; i<(dockedCards.size() - 6); i++){
            dockedCards.add(new Card(1130, 605, deck.get(0),
                    "docked", 1130, dock[dockedCards.size()]));
            deck.remove(0);
            //}

        }
    }

    public int countCards(String color) {
        int num = 0;
        String testCol = "";

        if (color != "Gray") {
            for (Card curr : selectedCards) {
                if (curr.myColor.equals(color)) {
                    num++;
                } else if (curr.myColor.equals("Rainbow")) {
                    num += 1;
                } else {
                    num = -99;
                }
            }
            return num;
        }

        int firstNotRainbow = 0;
        int numNotRainbow = 0;
        boolean found = false;

        while ((!found) && (firstNotRainbow < selectedCards.size())) {
            if (!selectedCards.get(firstNotRainbow).myColor.equals("Rainbow")) {
                found = true;
            } else {
                firstNotRainbow++;
            }
        }

        test2 = found + Integer.toString(firstNotRainbow);

        if (found == false) {
            testCol = "Rainbow";
            firstNotRainbow = 0;
        }

        testCol = selectedCards.get(firstNotRainbow).myColor;

        specialTest = testCol;

        if (color == "Gray") {
            for (Card curr2 : selectedCards) {
                if (curr2.myColor.equals(testCol)) {
                    num++;
                } else if (curr2.myColor.equals("Rainbow")) {
                    num += 1;
                } else {
                    num = -99;
                }
            }
            return num;
        }
        return num;
    }

    public void cleanUpHand() {
        for (int i = 0; i < currentHand.size(); i++) {
            currentHand.get(i).x = hand[currentHand.get(i).colorID];
            currentHand.get(i).y = currentHand.get(i).y;
            currentHand.get(i).inTransit = false;
            currentHand.get(i).hspeed = 0;
        }
    }

    public int cardCount(String color) {
        int count = 0;
        for (Card curr : currentHand) {
            if (curr.myColor.equals(color)) {
                count++;
            }
        }
        return count;
    }

    public int colorID(String color) {
        if (color.equals("Red")) {
            return 0;
        }
        if (color.equals("Orange")) {
            return 1;
        }
        if (color.equals("Yellow")) {
            return 2;
        }
        if (color.equals("Green")) {
            return 3;
        }
        if (color.equals("Blue")) {
            return 4;
        }
        if (color.equals("Pink")) {
            return 5;
        }
        if (color.equals("Black")) {
            return 6;
        }
        if (color.equals("White")) {
            return 7;
        }
        if (color.equals("Rainbow")) {
            return 8;
        }
        return -1;

    }

}
