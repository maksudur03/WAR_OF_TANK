

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;




public class Home {
	private int x, y;
	private WAROFTANK war;
	public static final int width = 43, length = 43; 
	private boolean live = true;

	private static Toolkit tk = Toolkit.getDefaultToolkit(); 
	private static Image[] HomeImags = null;
	static {
		HomeImags = new Image[] { tk.getImage(BreakWall.class
				.getResource("Images/Home.jpg")), };
	}

	public Home(int x, int y, WAROFTANK war) {
		this.x = x;
		this.y = y;
		this.war = war; 
	}

	public void gameOver(Graphics g) {

		war.tanks.clear();
		war.steelWall.clear();
		war.otherWall.clear();
		war.Brusts.clear();
		war.theRiver.clear();
		war.trees.clear();
		war.bullets.clear();
		war.HomeTank.setLive(false);
		Color c = g.getColor(); 
		g.setColor(Color.green);
		Font f = g.getFont();
		g.setFont(new Font(" ", Font.PLAIN, 40));
		g.setFont(f);
		g.setColor(c);

	}

	public void draw(Graphics g) {

		if (live) { 
			g.drawImage(HomeImags[0], x, y, null);

			for (int i = 0; i < war.HomeWall.size(); i++) {
				BreakWall w = war.HomeWall.get(i);
				w.draw(g);
			}
		} else {
			gameOver(g); 

		}
	}

	public boolean isLive() { 
		return live;
	}

	public void setLive(boolean live) { 
		this.live = live;
	}

	public Rectangle getRect() { 
		return new Rectangle(x, y, width, length);
	}

}
