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

/**
 *
 * @author Mike TERMINOLOGY GUIDE ROOM describes the current window being used
 * and drawn PARENTS are abstracts or interfaces.
 */
public class CoffeeSucks extends JPanel implements ActionListener, MouseListener {

    Dimension screenSize = new Dimension(1280, 736); //set screen size (locked)
    int FPS = 60; //default is 30,  you can change it but it WILL effect your whole game
    private final Timer timer = new Timer(FPS, this);
    Image ROOM_BACKGROUND = null; //define this for your room, this is the drawn background and should match the dimensions above
    private long totalTime; //all related to framerate
    private long averageTime;
    private int frameCount;
    static String dir = System.getProperty("user.dir");
    ArrayList<GameObject> objs = new ArrayList<>();
    ArrayList<RoadPath> hitBoxes = new ArrayList<>();
    Font customFont; //Name all your fonts here, set them in the constructor
    Font bigFont;
    boolean hasSelected = false;
    boolean gameWon = false;
    JFrame currentFrame;
    String currentRoom = "";
    static CoffeeSucks mainObj;
    String snd_blip = (dir + "\\TTRAssets\\blip.wav");

    public CoffeeSucks() throws FontFormatException, IOException {
        //custom font stuff
        String fontpath = dir + "\\TTRAssets\\ttrFont.ttf";
        customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontpath)).deriveFont(12f);
        bigFont = customFont.deriveFont(24f);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontpath)));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FontFormatException, IOException {
        CoffeeSucks game = new CoffeeSucks();
        mainObj = game;
        game.beginnerRoom();
    }

    public void beginnerRoom() {

        JFrame frame = new JFrame("Ticket To Ride - powered by CoffeeSucksEngine 0.5");
        currentFrame = frame;
        currentRoom = "GameBoard";
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //what do we do on close?
        frame.add(this); //makes the paintComponent add
        frame.setResizable(false); //locks size
        frame.requestFocus();
        frame.toFront();
        //frame.addKeyListener(p1); //every object with inputs needs this
        frame.addMouseListener(this);
        frame.setSize(screenSize); //sets size
        frame.setVisible(true);
        timer.start();

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
        RoadPath rp16 = new RoadPath(591, 228, 670, 243, 673, 249, 612, 260, "Blue", 7, 16);
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
        RoadPath rp30 = new RoadPath(718, 489, 695, 535, 738, 542, 725, 488, "Orange", 4, 30);
        hitBoxes.add(rp30);
        RoadPath rp31 = new RoadPath(694, 556, 738, 597, 744, 592, 699, 550, "Red", 4, 31);
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
        RoadPath rp63 = new RoadPath(449-2, 334+8, 521-2, 376+8, 516-2, 381+8, 447-2, 339+8, "Gray", 4, 63);
        hitBoxes.add(rp63);
        RoadPath rp64 = new RoadPath(425,353,434,353,438,372,432,372, "Gray", 1, 64);
        hitBoxes.add(rp64);
        RoadPath rp65 = new RoadPath(425+8,353-2,434+8,353-2,438+8,372-2,432+8,372-2, "Gray", 1, 65);
        hitBoxes.add(rp65);
        RoadPath rp66 = new RoadPath(451,386,483,410,480,416,447,393, "Gray", 2, 66);
        hitBoxes.add(rp66);
        RoadPath rp67 = new RoadPath(451+8,386-6,483+8,410-6,480+8,416-6,447+8,393-6, "Gray", 2, 67);
        hitBoxes.add(rp67);
        RoadPath rp68 = new RoadPath(434,390,442,388,431,450,424,448, "Gray", 3, 68);
        hitBoxes.add(rp68);
        RoadPath rp69 = new RoadPath(432,452,482,417,487,423,437,457, "Gray", 3, 69);
        hitBoxes.add(rp69);
        RoadPath rp70 = new RoadPath(439,458,499,449,500,456,439,464, "Gray", 3, 70);
        hitBoxes.add(rp70);
        RoadPath rp71 = new RoadPath(438,467,494,483,492,488,438,492, "Gray", 3, 71);
        hitBoxes.add(rp71);
        RoadPath rp72 = new RoadPath(422,469,428,469,425,488,417,486, "Green", 1, 72);
        hitBoxes.add(rp72);
        RoadPath rp73 = new RoadPath(493,401,510,392,515,396,496,410, "Gray", 1, 73);
        hitBoxes.add(rp73);
        RoadPath rp74 = new RoadPath(493+3,401+8,510+3,392+8,515+3,396+8,496+3,410+8, "Gray", 1, 74);
        hitBoxes.add(rp74);
        RoadPath rp75 = new RoadPath(460,511,494,494,497,497,463,517, "Black", 2, 75);
        hitBoxes.add(rp75);
        RoadPath rp76 = new RoadPath(488,428,496,425,503,441,497,444, "Gray", 1, 76);
        hitBoxes.add(rp76);
        RoadPath rp77 = new RoadPath(488+8,428-3,496+8,425-3,503+8,441-3,497+8,444-3, "Gray", 1, 77);
        hitBoxes.add(rp77);
        RoadPath rp78 = new RoadPath(522,404,529,406,520,444,513,442, "Gray", 2, 78);
        hitBoxes.add(rp78);
        RoadPath rp79 = new RoadPath(522+8,404+1,529+8,406+1,520+8,444+1,513+8,442+1, "Gray", 2, 79);
        hitBoxes.add(rp79);
        RoadPath rp80 = new RoadPath(503,460,510,460,510,480,502,480, "Blue", 1, 80);
        hitBoxes.add(rp80);
        RoadPath rp81 = new RoadPath(526,384,557,313,564,312,558,360, "Blue", 4, 81);
        hitBoxes.add(rp81); //iffy hitboxes
        RoadPath rp82 = new RoadPath(530,385,556,351,569,316,584,376, "Yellow", 4, 82);
        hitBoxes.add(rp82); //iffy hitboxes
        RoadPath rp83 = new RoadPath(580,312,630,333,592,358,574,316, "Gray", 3, 83);
        hitBoxes.add(rp83);
        RoadPath rp84 = new RoadPath(540,389,574,404,575,411,539,397, "Gray", 2, 84);
        hitBoxes.add(rp84);
        RoadPath rp85 = new RoadPath(540-3,389+8,574-3,404+8,575-3,411+8,539-3,397+8, "Gray", 2, 85);
        hitBoxes.add(rp85);
        RoadPath rp86 = new RoadPath(540+57,389+22,574+57,404+22,575+57,411+22,539+57,397+22, "Gray", 2, 86);
        hitBoxes.add(rp86);
        RoadPath rp87 = new RoadPath(540+57,389+30,574+57,404+30,575+57,411+30,539+57,397+30, "Gray", 2, 87);
        hitBoxes.add(rp87);
        RoadPath rp88 = new RoadPath(517,489,534,494,532,501,513,495, "Pink", 1, 88);
        hitBoxes.add(rp88);
        RoadPath rp89 = new RoadPath(526,456,547,484,543,488,519,456, "Gray", 2, 89);
        hitBoxes.add(rp89);
        RoadPath rp90 = new RoadPath(526-8,456+2,547-8,484+2,543-8,488+2,519-8,456+2, "Gray", 2, 90);
        hitBoxes.add(rp90);
        RoadPath rp91 = new RoadPath(559,494,591,517,588,523,555,500, "Gray", 2, 91);
        hitBoxes.add(rp91);
        RoadPath rp92 = new RoadPath(559-3,494+8,591-3,517+8,588-3,523+8,555-3,500+8, "Gray", 2, 92);
        hitBoxes.add(rp92);
        RoadPath rp93 = new RoadPath(603,522,621,523,623,528,604,529, "Gray", 1, 93);
        hitBoxes.add(rp93);
        RoadPath rp94 = new RoadPath(603,522+8,621,523+8,623,528+8,604,529+8, "Gray", 1, 94);
        hitBoxes.add(rp94);
        RoadPath rp95 = new RoadPath(629,440,638,440,634,522,629,522, "Orange", 4, 95);
        hitBoxes.add(rp95);
        RoadPath rp96 = new RoadPath(641,529,679,537,677,545,639,535, "Gray", 2, 96);
        hitBoxes.add(rp96);
        RoadPath rp97 = new RoadPath(641-2,529+8,679-2,537+8,677-2,545+8,639-2,535+8, "Gray", 2, 97);
        hitBoxes.add(rp97);
        RoadPath rp98 = new RoadPath(589,580,686,555,653,588,589,585, "Gray", 5, 98);
        hitBoxes.add(rp98);
        RoadPath rp99 = new RoadPath(589,588,626,601,623,608,588,593, "Pink", 2, 99);
        hitBoxes.add(rp99);
        RoadPath rp100 = new RoadPath(538,609,571,592,564,618,537,613, "Blue", 2, 100);
        hitBoxes.add(rp100);
        RoadPath rp101 = new RoadPath(518,595,534,581,539,585,520,600, "White", 1, 101);
        hitBoxes.add(rp101);
        RoadPath rp102 = new RoadPath(552,575,573,579,569,587,550,584, "Yellow", 1, 102);
        hitBoxes.add(rp102);
        RoadPath rp103 = new RoadPath(587,538,593,538,584,579,575,577, "Red", 2, 103);
        hitBoxes.add(rp103);
        RoadPath rp104 = new RoadPath(540,508,549,508,549,569,540,569, "Green", 3, 104);
        hitBoxes.add(rp104);
        RoadPath rp105 = new RoadPath(495,564,535,572,532,578,494,569, "Black", 2, 105);
        hitBoxes.add(rp105);
        RoadPath rp106 = new RoadPath(494,553,532,507,539,511,499,557, "Gray", 3, 106);
        hitBoxes.add(rp106);
        RoadPath rp107 = new RoadPath(483,555,500,495,509,498,487,556, "White", 3, 107);
        hitBoxes.add(rp107);
        RoadPath rp108 = new RoadPath(436,548,476,557,472,565,433,555, "Yellow", 2, 108);
        hitBoxes.add(rp108);
        RoadPath rp109 = new RoadPath(480,575,484,607,442,596,472,572, "Orange", 2, 109);
        hitBoxes.add(rp109);
    }

    @Override
    protected void paintComponent(Graphics g) {
        long start = System.nanoTime();
        super.paintComponent(g);
        g.setFont(bigFont); // ******************SETS FONT FOR GAME**************
        int width = this.getWidth();
        int height = this.getHeight();
        g.setColor(Color.white); //BACKGROUND COLOR OF ROOM
        g.fillRect(0, 0, width, height);
        //the rest of this is frametime stuff, PUT STEPS HERE
        if (frameCount == FPS) {
            averageTime = totalTime / FPS;
            totalTime = 0;
            frameCount = 0;
        } else {
            totalTime += System.nanoTime() - start;
            step();
            frameCount++;
        }

        if (currentRoom.equals("GameBoard")) {
            //drawing specifc things
            BufferedImage imgBG = null;
            String pathBG = (dir + "\\TTRAssets\\board.png");
            BufferedImage mapBG = null;
            String pathMap = (dir + "\\TTRAssets\\map.png");
            Image coolBG = null;
            String pathcoolBG = (dir + "\\TTRAssets\\oldpaper.jpg");
            try {
                imgBG = ImageIO.read(new File(pathBG));
                coolBG = new ImageIcon(pathcoolBG).getImage();
                mapBG = ImageIO.read(new File(pathMap));
                setBackground(imgBG);
            } catch (IOException e) {
            }
            g.drawImage(coolBG, -0, 0, this);
            g.drawImage(ROOM_BACKGROUND, 0, 0, this);
            g.drawImage(mapBG, -0, 0, this);
            //the magic line. This draws every object in the objs array
            Graphics2D g2 = (Graphics2D) g;

            for (GameObject curr : objs) {
                if (curr.visible) {
                    g.drawImage(curr.sprite_index, curr.x, curr.y, this);
                    g.drawImage(curr.topSpr, curr.x, curr.y, this);

                }

            }
            for (RoadPath curr : hitBoxes) {
                g.setColor(Color.BLACK);
                //g.drawPolygon(curr.boundBox);
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
        for (GameObject curr : objs) {
            curr.step();
            curr.collisionCheck(objs);
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {

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

        for (RoadPath curr : hitBoxes) {
            if (curr.boundBox.contains(p)) {
                playSound(snd_blip, false);
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

}
