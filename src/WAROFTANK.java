

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WAROFTANK extends Frame {

    public enum STATE {
        MENU,
        GAME,
        LEVEL,
        HIGHSCORE,
        HELP,
        ABOUT,
        EASY,
        NORMAL,
        INTERMIDIATE,
        EXPERT,
        GAMEOVER

    };
    public static STATE state = STATE.MENU;

    HEEE heee = new HEEE();
    Help help = new Help();
    public static WAROFTANK war = null;
    private static final long serialVersionUID = 1L;
    public static final int Fram_width = 800;
    public static final int Fram_length = 600;
    public static boolean printable = true;
    public Rectangle backButton = new Rectangle(360, 530, 85, 30);
    MenuBar jmb = null;
    Menu jm1 = null, jm2 = null, jm3 = null, jm4 = null, jm5 = null;
    MenuItem jmi1 = null, jmi2 = null, jmi3 = null, jmi4 = null, jmi5 = null,
            jmi6 = null, jmi7 = null, jmi8 = null, jmi9 = null, jmi10 = null, jmi11 = null;
    public static Image screenImage = null;

    Tank HomeTank = new Tank(300, 560, true, Direction.STOP, this, 1);
    Tank HomeTank2 = new Tank(449, 560, true, Direction.STOP, this, 2);
    Boolean Player2 = false;
    //GetBlood blood = new GetBlood();
    Home Home = new Home(373, 557, this);
    Boolean win = false, lose = false;
    List<River> theRiver = new ArrayList<River>();
    List<Tank> tanks = new ArrayList<Tank>();
    List<Brust> Brusts = new ArrayList<Brust>();
    List<Bullets> bullets = new ArrayList<Bullets>();
    List<Tree> trees = new ArrayList<Tree>();
    List<BreakWall> HomeWall = new ArrayList<BreakWall>();
    List<BreakWall> otherWall = new ArrayList<BreakWall>();
    List<SteelWall> steelWall = new ArrayList<SteelWall>();
    public static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] image = null;
    private int sightImgMiddleHeight, sightImgMiddleWidth;
    public static int highScore = 0;
    public static int score = 0;
    private int cnt = 1;

    public void update(Graphics g) {

        screenImage = this.createImage(Fram_width, Fram_length);
        Graphics gps = screenImage.getGraphics();
        Color c = gps.getColor();
        gps.setColor(Color.BLACK);
        gps.fillRect(0, 0, Fram_width, Fram_length);
        gps.setColor(c);
        this.addMouseListener(new Mouseinput());

        framPaint(gps);
        g.drawImage(screenImage, 0, 0, null);
        
        if (state == STATE.EASY) {
            state = STATE.GAME;
            Tank.count = 12;
            Tank.speedX = 6;
            Tank.speedY = 6;
            Bullets.speedX = 10;
            Bullets.speedY = 10;

            this.dispose();
            new WAROFTANK();

        }

        if (state == STATE.NORMAL) {
            state = STATE.GAME;
            Tank.count = 12;
            Tank.speedX = 10;
            Tank.speedY = 10;
            Bullets.speedX = 12;
            Bullets.speedY = 12;

            this.dispose();
            new WAROFTANK();

        }

        if (state == STATE.INTERMIDIATE) {
            state = STATE.GAME;
            Tank.count = 20;
            Tank.speedX = 14;
            Tank.speedY = 14;
            Bullets.speedX = 16;
            Bullets.speedY = 16;

            this.dispose();
            new WAROFTANK();

        }

        if (state == STATE.EXPERT) {
            state = STATE.GAME;
            Tank.count = 20;
            Tank.speedX = 16;
            Tank.speedY = 16;
            Bullets.speedX = 18;
            Bullets.speedY = 18;

            this.dispose();
            new WAROFTANK();

        }
        
        if(state == STATE.GAMEOVER){
            
            Tank.count = 12;
            Tank.speedX = 6;
            Tank.speedY = 6;
            Bullets.speedX = 10;
            Bullets.speedY = 10;
            state = STATE.MENU;
            System.err.println("HHHKJKJH");
            this.dispose();
            new WAROFTANK();
        }
        
        

    }

    public void framPaint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        if (state == STATE.GAME) {
            Color c = g.getColor();
            g.setColor(Color.WHITE);

            Font f1 = g.getFont();
            g.setFont(new Font("Times New Roman", Font.BOLD, 20));
            if (!Player2) {
                g.drawString("SCORE :", 30, 60);
            } 
            g.setFont(new Font("Times New Roman", Font.BOLD, 25));

            if (!Player2) {
                g.drawString("" + score, 120, 62);
            }

            g.setFont(new Font("Times New Roman", Font.BOLD, 20));
            if (!Player2) {
                g.drawString("HEALTH: ", 620, 60);
            } 
            g.setFont(new Font("Times New Roman", Font.BOLD, 20));
            g.setColor(Color.RED);
            if (!Player2) {
                g.drawString("" + HomeTank.getLife(), 720, 60);
            } 
            g.setFont(f1);
            if (!Player2) {
                if (tanks.size() == 0 && Home.isLive() && HomeTank.isLive() && lose == false) {
                    if (cnt == 1 && highScore < score) {
                        File fil = new File("E:\\highscore.txt");
                        state = STATE.GAMEOVER;

                        BufferedWriter bfw = null;
                        try {
                            bfw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fil)));
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(WAROFTANK.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        String s = "";
                        try {
                            s = Integer.toString(score);
                            bfw.write(s);
                            cnt++;
                            System.err.println(score);
                            bfw.close();

                        } catch (IOException ex) {
                            Logger.getLogger(WAROFTANK.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    Font f = g.getFont();

                    g.setFont(new Font("Times New Roman", Font.BOLD, 60));
                    this.otherWall.clear();
                    this.steelWall.clear();
                    this.theRiver.clear();
                    this.trees.clear();
                    this.HomeWall.clear();
                    g.drawString("Hey! You Won.. ", 200, 300);
                    g.setFont(new Font("arial", Font.BOLD, 20));
                    g.drawString("BACK", 335 + 42, 530 + 22);
                    g2d.draw(backButton);
                    g.setFont(f);
                    win = true;
                }

                if (HomeTank.isLive() == false && win == false) {
                    if (cnt == 1 && highScore < score) {
                        File fil = new File("E:\\highscore.txt");
                        state = STATE.GAMEOVER;
                        BufferedWriter bfw = null;
                        try {
                            bfw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fil)));
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(WAROFTANK.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        String s = "";
                        try {
                            s = Integer.toString(score);
                            bfw.write(s);
                            cnt++;
                            System.err.println(score);
                            bfw.close();

                        } catch (IOException ex) {
                            Logger.getLogger(WAROFTANK.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    Font f = g.getFont();
                    g.setFont(new Font("Times New Roman", Font.BOLD, 40));
                    tanks.clear();
                    bullets.clear();
                    HomeWall.clear();
                    this.otherWall.clear();
                    this.steelWall.clear();
                    this.theRiver.clear();
                    this.trees.clear();
                    this.HomeWall.clear();
                    g.drawString("Sorry. You lose!", 250, 300);
                    g.setFont(new Font("arial", Font.BOLD, 20));
                    g.drawString("BACK", 335 + 42, 530 + 22);
                    g2d.draw(backButton);
                    lose = true;
                    g.setFont(f);
                }
            } else {
                if (tanks.size() == 0 && Home.isLive() && (HomeTank.isLive() || HomeTank2.isLive()) && lose == false) {
                    if (cnt == 1 && highScore < score) {
                        File fil = new File("E:\\highscore.txt");
                        state = STATE.GAMEOVER;
                        BufferedWriter bfw = null;
                        try {
                            bfw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fil)));
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(WAROFTANK.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        String s = "";
                        try {
                            s = Integer.toString(score);
                            bfw.write(s);
                            cnt++;
                            System.err.println(score);
                            bfw.close();

                        } catch (IOException ex) {
                            Logger.getLogger(WAROFTANK.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    Font f = g.getFont();
                    g.setFont(new Font("Times New Roman", Font.BOLD, 60));
                    this.otherWall.clear();
                    this.steelWall.clear();
                    this.theRiver.clear();
                    this.trees.clear();
                    this.HomeWall.clear();
                    this.otherWall.clear();
                    this.steelWall.clear();
                    this.theRiver.clear();
                    this.trees.clear();
                    this.HomeWall.clear();
                    g.drawString("Hey! You Won.. ", 200, 300);
                    g.setFont(new Font("arial", Font.BOLD, 20));
                    g.drawString("BACK", 335 + 42, 530 + 22);
                    g2d.draw(backButton);
                    g.setFont(f);
                    win = true;
                }

                if (HomeTank.isLive() == false && HomeTank2.isLive() == false && win == false) {
                    if (cnt == 1 && highScore < score) {
                        File fil = new File("E:\\highscore.txt");
                        state = STATE.GAMEOVER;
                        BufferedWriter bfw = null;
                        try {
                            bfw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fil)));
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(WAROFTANK.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        String s = "";
                        try {
                            s = Integer.toString(score);
                            bfw.write(s);
                            cnt++;
                            System.err.println(score);
                            bfw.close();

                        } catch (IOException ex) {
                            Logger.getLogger(WAROFTANK.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    Font f = g.getFont();
                    g.setFont(new Font("Times New Roman", Font.BOLD, 40));
                    tanks.clear();
                    bullets.clear();
                    HomeWall.clear();
                    this.otherWall.clear();
                    this.steelWall.clear();
                    this.theRiver.clear();
                    this.trees.clear();
                    this.HomeWall.clear();
                    g.drawString("Sorry. You lose!", 250, 300);
                    g.setFont(new Font("arial", Font.BOLD, 20));
                    g.drawString("BACK", 335 + 42, 530 + 22);
                    g2d.draw(backButton);
                    System.out.println("2");
                    g.setFont(f);
                    lose = true;
                }
            }
            g.setColor(c);

            for (int i = 0; i < theRiver.size(); i++) {
                River r = theRiver.get(i);
                r.draw(g);
            }
//            
            int k = 0;
            while(k<theRiver.size()){
                River r = theRiver.get(k);
                r.draw(g);
                k++;
            }

            for (int i = 0; i < theRiver.size(); i++) {
                River r = theRiver.get(i);
                HomeTank.collideRiver(r);
                r.draw(g);
            }
            if (win == false) {
                Home.draw(g);
            }
            if (win == false) {
                HomeTank.draw(g);
            }
            
         

            for (int i = 0; i < bullets.size(); i++) {
                //System.err.println(bullets.size());
                Bullets m = bullets.get(i);
                m.hitTanks(tanks);
                m.hitTank(HomeTank);
                m.hitTank(HomeTank2);
                m.hitHome();
                for (int j = 0; j < bullets.size(); j++) {
                    if (i == j) {
                        continue;
                    }
                    Bullets bts = bullets.get(j);
                    m.hitBullet(bts);
                }
                for (int j = 0; j < steelWall.size(); j++) {
                    SteelWall mw = steelWall.get(j);
                    m.hitWall(mw);
                }

                for (int j = 0; j < otherWall.size(); j++) {
                    BreakWall w = otherWall.get(j);
                    m.hitWall(w);
                }

                for (int j = 0; j < HomeWall.size(); j++) {
                    BreakWall cw = HomeWall.get(j);
                    m.hitWall(cw);
                }
                if (win != true || lose != true) {
                    m.draw(g);
                }
            }

            for (int i = 0; i < tanks.size(); i++) {
                Tank t = tanks.get(i);
                // System.err.println(tanks.size());
                for (int j = 0; j < HomeWall.size(); j++) {
                    BreakWall cw = HomeWall.get(j);
                    t.collideWithWall(cw);
                    //		cw.draw(g);
                }
                for (int j = 0; j < otherWall.size(); j++) {
                    BreakWall cw = otherWall.get(j);
                    t.collideWithWall(cw);
                    //		cw.draw(g);
                }
                for (int j = 0; j < steelWall.size(); j++) {
                    SteelWall mw = steelWall.get(j);
                    t.collideWithWall(mw);
                    //		mw.draw(g);
                }
                for (int j = 0; j < theRiver.size(); j++) {
                    River r = theRiver.get(j);
                    t.collideRiver(r);
            
                }

                t.collideWithTanks(tanks);
                t.collideHome(Home);

                t.draw(g);
            }


//
            for (int i = 0; i < trees.size(); i++) {
                Tree tr = trees.get(i);
                tr.draw(g);
            }

            for (int i = 0; i < Brusts.size(); i++) {
                Brust bt = Brusts.get(i);
                //  System.err.println(Brusts.size());
                bt.draw(g);
            }

            for (int i = 0; i < otherWall.size(); i++) {
                BreakWall cw = otherWall.get(i);
                cw.draw(g);
            }

            for (int i = 0; i < steelWall.size(); i++) {
                SteelWall mw = steelWall.get(i);
                mw.draw(g);
            }

            HomeTank.collideWithTanks(tanks);
            HomeTank.collideHome(Home);
            

            for (int i = 0; i < steelWall.size(); i++) {
                SteelWall w = steelWall.get(i);
                HomeTank.collideWithWall(w);
                
            }

            for (int i = 0; i < otherWall.size(); i++) {
                BreakWall cw = otherWall.get(i);
                HomeTank.collideWithWall(cw);
                

            }

            for (int i = 0; i < HomeWall.size(); i++) {
                BreakWall w = HomeWall.get(i);
                HomeTank.collideWithWall(w);
                

            }
        }
        if (state == STATE.MENU) {
            heee.render(g);
        }
        if (state == STATE.HELP) {
            help.render(g);
        }
        if (state == STATE.HIGHSCORE) {
            help.render(g);
        }
        if (state == STATE.LEVEL) {
            help.render(g);
        }
        

    }

    public WAROFTANK() {

        image = new Image[]{tk.getImage(BreakWall.class.getResource("Images/sight.png")),};

        for (int i = 0; i < 10; i++) {
            if (i < 4) {
                HomeWall.add(new BreakWall(350, 580 - 21 * i, this));
            } else if (i < 7) {
                HomeWall.add(new BreakWall(372 + 22 * (i - 4), 517, this));
            } else {
                HomeWall.add(new BreakWall(416, 538 + (i - 7) * 21, this));
            }

        }

        for (int i = 0; i < 32; i++) {
            if (i < 16) {
                otherWall.add(new BreakWall(200 + 21 * i, 280, this));
                otherWall.add(new BreakWall(500 + 21 * i, 180, this));
                otherWall.add(new BreakWall(200, 400 + 21 * i, this));
                otherWall.add(new BreakWall(500, 400 + 21 * i, this));
            } else if (i < 32) {
                //otherWall.add(new BreakWall(200 + 21 * (i - 16), 320, this));
                otherWall.add(new BreakWall(500 + 21 * (i - 16), 220, this));
                otherWall.add(new BreakWall(222, 400 + 21 * (i - 16), this));
                otherWall.add(new BreakWall(522, 400 + 21 * (i - 16), this));
            }
        }

        for (int i = 0; i < 20; i++) {
            if (i < 10) {
                steelWall.add(new SteelWall(210 + 30 * i, 150, this));
                steelWall.add(new SteelWall(600, 400 + 20 * (i), this));
            } else if (i < 20) {
                steelWall.add(new SteelWall(210 + 30 * (i - 10), 180, this));
            }

        }

        for (int i = 0; i < 4; i++) {
            if (i < 4) {
                trees.add(new Tree(0 + 30 * i, 360, this));
                trees.add(new Tree(220 + 30 * i, 360, this));
                trees.add(new Tree(440 + 30 * i, 360, this));
                trees.add(new Tree(660 + 30 * i, 360, this));
            }

        }

        theRiver.add(new River(220, 300, this));
        theRiver.add(new River(380, 300, this));

        for (int i = 0; i < 20; i++) {
            if (i < 9) {
                tanks.add(new Tank(150 + 70 * i, 40, false, Direction.D, this, 0));
            } else if (i < 15) {
                tanks.add(new Tank(700, 140 + 50 * (i - 6), false, Direction.D,
                        this, 0));
            } else {
                tanks.add(new Tank(10, 50 * (i - 12), false, Direction.D, this, 0));
            }
        }

        this.setSize(Fram_width, Fram_length);  // Frame size
        this.setLocation(280, 50);
        this.setTitle("WAR OF TANK  ");

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.setVisible(true);

        this.addKeyListener(new KeyMonitor());

        new Thread(new PaintThread()).start();
    }

    public static void main(String[] args) {

        File fil = new File("E:\\highscore.txt");
        if (!fil.exists()) {
            try {
                fil.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(WAROFTANK.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        FileReader inputFil = null;

        try {
            inputFil = new FileReader(fil);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WAROFTANK.class.getName()).log(Level.SEVERE, null, ex);
        }
        String s = "";
        BufferedReader in = new BufferedReader(inputFil);

        try {
            s = in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(WAROFTANK.class.getName()).log(Level.SEVERE, null, ex);
        }

        highScore = Integer.parseInt(s);
        System.out.println(highScore);
        war = new WAROFTANK();

        //        }
    }

    private class PaintThread implements Runnable {

        public void run() {
            // TODO Auto-generated method stub

            while (printable) {
                //System.err.println("HIII");

                repaint();
                try {

                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    private class KeyMonitor extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            HomeTank.keyReleased(e);
            HomeTank2.keyReleased(e);
            //heee.keyReleased(e);

        }

        public void keyPressed(KeyEvent e) {
            HomeTank.keyPressed(e);
            HomeTank2.keyPressed(e);

            war.keyPressed(e);

        }

    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_Q:
                state = STATE.MENU;
                break;
        }
    }
}
