

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;




/*
    This is for the blust of tank
*/

public class Brust {
	private int x, y;
	private boolean live = true; 
	private WAROFTANK war;
	private static Toolkit tk = Toolkit.getDefaultToolkit();

	private static Image[] imgs = {
			tk.getImage(Brust.class.getClassLoader().getResource(
					"images/1.gif")),
			tk.getImage(Brust.class.getClassLoader().getResource(
					"images/2.gif")),
			tk.getImage(Brust.class.getClassLoader().getResource(
					"images/3.gif")),
			tk.getImage(Brust.class.getClassLoader().getResource(
					"images/4.gif")),
			tk.getImage(Brust.class.getClassLoader().getResource(
					"images/5.gif")),
			tk.getImage(Brust.class.getClassLoader().getResource(
					"images/6.gif")),
			tk.getImage(Brust.class.getClassLoader().getResource(
					"images/7.gif")),
			tk.getImage(Brust.class.getClassLoader().getResource(
					"images/8.gif")),
			tk.getImage(Brust.class.getClassLoader().getResource(
					"images/9.gif")),
			tk.getImage(Brust.class.getClassLoader().getResource(
					"images/10.gif")), };
	int step = 0;

	public Brust(int x, int y, WAROFTANK war) {
		this.x = x;
		this.y = y;
		this.war = war;
                new SoundPlayer("/Sounds/Bomb+2.mp3").play();
	}

	public void draw(Graphics g) { 

		if (!live) { 
			//war.Brusts.remove(this);
			return;
		}
		if (step == imgs.length) {
			live = false;
			step = 0;
			//return;
		}

		g.drawImage(imgs[step], x, y, null);
		step++;
        	}
}
