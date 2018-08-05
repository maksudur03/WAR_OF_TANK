

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;



public class River {
	public static final int riverWidth = 154;
	public static final int riverLength = 55;
	private int x, y;
	WAROFTANK war ;
	
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] riverImags = null;
	
	static {   
		riverImags = new Image[]{
				tk.getImage(BreakWall.class.getResource("Images/river.jpg")),
		};
	}
	
	
	public River(int x, int y, WAROFTANK war) {   
		this.x = x;
		this.y = y;
		this.war = war;           
	}
	
	public void draw(Graphics g) {
		
                g.drawImage(riverImags[0],x, y, null);          
	}
////	
	public Rectangle getRect() {
		return new Rectangle(x, y, riverWidth, riverLength);
	}


}
