

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;



public class SteelWall {
	public static final int width = 36; 
	public static final int length = 37;
	private int x, y;
	WAROFTANK war;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] wallImags = null;
	static {
		wallImags = new Image[] { tk.getImage(BreakWall.class
				.getResource("Images/steelWall.gif")), };
	}

	public SteelWall(int x, int y, WAROFTANK war) {
		this.x = x;
		this.y = y;
		this.war = war;
	}

	public void draw(Graphics g) { 
		g.drawImage(wallImags[0], x, y, null);
	}

	public Rectangle getRect() { 
		return new Rectangle(x, y, width, length);
	}
}
