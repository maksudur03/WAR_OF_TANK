

import java.awt.*;


public class BreakWall {
	public static final int width = 22; 
	public static final int length = 21;
	int x, y;

	WAROFTANK war;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] wallImags = null;
	static {
		wallImags = new Image[] { 
		tk.getImage(BreakWall.class.getResource("Images/breakWall.gif")), };
	}

	public BreakWall(int x, int y, WAROFTANK war) { 
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
