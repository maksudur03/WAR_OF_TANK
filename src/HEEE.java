
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


public class HEEE {
    
    public Rectangle playButton  = new Rectangle(335,200,130,30);
    public Rectangle levelButton  = new Rectangle(335,250,130,30);
    public Rectangle HighScore  = new Rectangle(335,300,130,30);
    public Rectangle Help = new Rectangle(335,350,130,30);
    public Rectangle About = new Rectangle(335,400,130,30);
   
    public static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] image = null;
    private static Image[] mainImags = null;
    private static Image[] selection = null;
    private static Image[] background = null;
    
    
    List<Integer> ara = new ArrayList<Integer>();
    int x = 300,y;
    
    static {
		image = new Image[]{
				tk.getImage(HEEE.class.getResource("Images/axis_Tiger.png")),
		};
                selection = new Image[]{tk.getImage(HEEE.class.getResource("Images/HtankR2.gif")),
                
                };
                background = new Image[]{tk.getImage(HEEE.class.getResource("Images/photo.jpg")),};
                
    }
	
    public HEEE() {
        x = 60;
        ara.add(200);
    }
    
    
    public void render(Graphics g){
        
        
        HEEE h = new HEEE();
        h.draw(g);
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);
        
       
        Font fnt0 = new Font("arial",Font.BOLD,50);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("WAR OF TANK", 220, 150);
        Font fnt1 = new Font("arial",Font.BOLD,20);
        g.setFont(fnt1);
        g.drawString("New Game", 350, 222);
        g.drawString("LEVEL", 370, 273);
        g.drawString("HIGHSCORE", 342, 323);
        g.drawString("HELP", 372, 374);
        //g.drawString("About", 372, 374+51);
        
        
        g2d.draw(playButton);
        g2d.draw(levelButton);
        g2d.draw(HighScore);
        g2d.draw(Help);
        //g2d.draw(About);
        
        
        
        
    }

    void setFont(Font font) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void add(MenuItem jmi1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void draw(Graphics g) {        
                g.drawImage(background[0], 0, 0, null);
                g.drawImage(image[0], 75, 60, null);
                g.drawImage(image[0], 590, 60, null);
                
                
	}
    public void keyPressed(KeyEvent e) {  
                //HEEE h = new HEEE();
                int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_Q:
                    
                    x += 50;
                    System.err.println(x);
                   
                    
                    
                    
                    break;
                }
    }
    
        
        

    
}


