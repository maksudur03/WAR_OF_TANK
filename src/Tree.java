

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class Tree {
	public static final int width = 300;
	public static final int length = 300;
	int x, y;
	WAROFTANK war ;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] treeImags = null;
	static {
		treeImags = new Image[]{
				tk.getImage(Tree.class.getResource("Images/tree.gif")),
		};
	}
	
	
	public Tree(int x, int y, WAROFTANK war) { 
		this.x = x;
		this.y = y;
		this.war = war;
	}
	
	public void draw(Graphics g) {        
		g.drawImage(treeImags[0],x, y, null);
                
	}
	
}
