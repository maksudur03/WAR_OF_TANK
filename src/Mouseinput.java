
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Mouseinput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent me) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
        
        int mx = me.getX();
        int my = me.getY();

        if (mx >= 335 && mx <= 335 + 130) {
            if (my >= 200 && my <= 230) {
                if (WAROFTANK.state == WAROFTANK.STATE.MENU) {
                    System.err.println("new");
                    WAROFTANK.state = WAROFTANK.STATE.GAME;
                    new SoundPlayer("/Sounds/gun-gunshot-02.mp3").play();
                }
            }
        }

//        320, 300, 150, 3
        if (mx >= 335 && mx <= 335 + 130) {
            if (my >= 300 && my <= 330) {
                if (WAROFTANK.state == WAROFTANK.STATE.MENU) {
                    System.err.println("high");
                    new SoundPlayer("/Sounds/gun-gunshot-02.mp3").play();
                    WAROFTANK.state = WAROFTANK.STATE.HIGHSCORE;
                }
            }

        }

        if (mx >= 335 && mx <= 335 + 130) {
            if (my >= 350 && my <= 380) {
                if (WAROFTANK.state == WAROFTANK.STATE.MENU) {
                    System.err.println("help");
                    new SoundPlayer("/Sounds/gun-gunshot-02.mp3").play();
                    WAROFTANK.state = WAROFTANK.STATE.HELP;

                }
            }

        }

//        if(mx >= 335 && mx<=335 + 130 && WAROFTANK.state == WAROFTANK.STATE.MENU){
//            if(my>=400 && my<=430){
//                System.err.println("ABOUT");
//                WAROFTANK.state = WAROFTANK.STATE.ABOUT;
//                
//            }
//        }

        System.err.println(WAROFTANK.state);
        if (mx >= 360 && mx <= 360 + 85 ) {
            if (my >= 530 && my <= 560) {
                
                if(WAROFTANK.state != WAROFTANK.STATE.GAME && WAROFTANK.state!=WAROFTANK.STATE.GAMEOVER)
                    WAROFTANK.state = WAROFTANK.STATE.MENU;
                if(WAROFTANK.state == WAROFTANK.STATE.GAME)
                    WAROFTANK.state = WAROFTANK.STATE.GAMEOVER;
            }
        }

        if (mx >= 320 && mx <= 150+320 && WAROFTANK.state == WAROFTANK.STATE.LEVEL) {
            if (my >= 220 && my <= 220 + 30) {
                new SoundPlayer("/Sounds/gun-gunshot-02.mp3").play();
                WAROFTANK.state = WAROFTANK.STATE.EASY;
            }
        }
        if (mx >= 320 && mx <= 150+320 && WAROFTANK.state == WAROFTANK.STATE.LEVEL) {
            if (my >= 280 && my <= 280 + 30) {
                new SoundPlayer("/Sounds/gun-gunshot-02.mp3").play();
                WAROFTANK.state = WAROFTANK.STATE.NORMAL;
            }
        }

        if (mx >= 320 && mx <= 150+320 && WAROFTANK.state == WAROFTANK.STATE.LEVEL) {
            if (my >= 350 && my <= 350 + 30) {
                new SoundPlayer("/Sounds/gun-gunshot-02.mp3").play();
                WAROFTANK.state = WAROFTANK.STATE.INTERMIDIATE;
            }
        }

        //       System.err.println(mx);(335,530,90,30)
//        System.err.println(my );
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        int mx = me.getX();
        int my = me.getY();

        if (mx >= 335 && mx <= 335 + 130) {
            if (my >= 250 && my <= 250 + 30) {
                if (WAROFTANK.state == WAROFTANK.STATE.MENU) {
                    new SoundPlayer("/Sounds/gun-gunshot-02.mp3").play();
                    System.err.println("level");
                    WAROFTANK.state = WAROFTANK.STATE.LEVEL;
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static Point mousePosition() {
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        return b;

    }
}
