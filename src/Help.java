
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MenuItem;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Help {

    public static Toolkit tk = Toolkit.getDefaultToolkit();

    public Rectangle easy = new Rectangle(320, 220, 150, 30);
    //public Rectangle normal = new Rectangle(320, 250, 150, 30);
    public Rectangle intermediate = new Rectangle(320, 280, 150, 30);
    public Rectangle expert = new Rectangle(320, 350, 150, 30);

    private static Image[] tankImags = null;
    public Rectangle backButton = new Rectangle(360, 530, 85, 30);

    static {
        tankImags = new Image[]{tk.getImage(Brust.class.getResource("Images/HtankR2.gif")),
            tk.getImage(Brust.class.getResource("Images/tankR.gif")),
            tk.getImage(Brust.class.getResource("Images/Home.jpg")),};
    }

    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);

        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("WAR OF TANK", 220, 150);
        Font fnt1 = new Font("arial", Font.PLAIN, 20);
        g.setFont(fnt1);
        if (WAROFTANK.state == WAROFTANK.STATE.HIGHSCORE) {
            Font fnthi = new Font("arial", Font.BOLD, 30);
            g.setFont(fnthi);
            g.setColor(Color.RED);
            g.drawString("HIGHSCORE", 300, 210 + 60);
            g.drawString("" + WAROFTANK.highScore, 365, 270 + 30 + 10);
            g.setFont(new Font("arial", Font.BOLD, 20));
            g.setColor(Color.WHITE);
            g.drawString("BACK", 335 + 42, 530 + 22);

        } else if (WAROFTANK.state == WAROFTANK.STATE.HELP) {
            g.drawString("YOUR TANK", 182, 210);
            g.drawString("ENEMY TANK", 182 + 320, 210);
            g.drawString("Home", 182 + 170, 210 + 60);
            g.drawString("BACK", 335 + 42, 530 + 22);

            g.drawString("*", 90, 210 + 115 + 50);
            g.drawString("*", 90, 210 + 115 + 50 + 30);
            g.drawString("*", 90, 430 + 5);
            g.drawString("KILL ALL TANK AND WIN", 290, 500);
            Font fnt2 = new Font("arial", Font.BOLD, 15);
            g.setFont(fnt2);
            g.drawString("YOU HAVE TO KILL ALL THE ENEMY AND PROTECTING Home AND YOURSELF.", 100, 210 + 100 + 60);
            g.drawString("YOU CAN DISTROY BreakWall BUT CANNOT DISTROY STONE WALL.", 100, 310 + 60 + 30);
            g.drawString("TANKS CAN PASS THROUGH THE TREES AND BULLETS CAN PASS THROUGH THE RIVER.", 100, 430);

            draw(g);
        } else {
            g2d.draw(easy);
            //g2d.draw(normal);
            g2d.draw(intermediate);
            g2d.draw(expert);
            g.setFont(new Font("arial", Font.BOLD, 20));
            g.setColor(Color.WHITE);
            g.drawString("BACK", 335 + 42, 530 + 22);
            g.drawString("EASY", 350 + 20 , 222 + 20);
            //g.drawString("NORMAL", 355, 273);
            g.drawString("INTERMIDIATE", 325 , 323 - 10 - 10);
            g.drawString("EXPERT", 357, 374);
        }
//        
        g2d.setColor(Color.WHITE);
        g2d.draw(backButton);
//        g2d.draw(levelButton);
//        g2d.draw(HighScore);
//        g2d.draw(Help);
//        g2d.draw(About);
//        

    }

    void setFont(Font font) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void add(MenuItem jmi1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void draw(Graphics g) {
        g.drawImage(tankImags[0], 182 + 40, 220, null);
        g.drawImage(tankImags[1], 182 + 320 + 40, 220, null);
        g.drawImage(tankImags[2], 182 + 170 + 17, 210 + 60 + 0, null);

    }

}
