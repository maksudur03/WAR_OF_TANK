

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.sound.sampled.AudioInputStream;



public class Tank {
	public static  int speedX = 6, speedY =6; 
	public static int count = 0;
	public static final int width = 35, length = 35;
	private Direction direction = Direction.STOP;
	private Direction Kdirection = Direction.U; 
	WAROFTANK war;
	private int player=0;
	private boolean good;
	private int x, y;
	private int oldX, oldY;
	private boolean live = true;
	private int life = 200;
	private int rate=1;
	private static Random r = new Random();
	private int step = r.nextInt(10)+5 ; 
        
        
	
        
        
        
        private boolean bL = false, bU = false, bR = false, bD = false;
	

	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] tankImags = null; 
	static {
		tankImags = new Image[] {
				tk.getImage(Tank.class.getResource("Images/tankD.gif")),
				tk.getImage(Tank.class.getResource("Images/tankU.gif")),
				tk.getImage(Tank.class.getResource("Images/tankL.gif")),
				tk.getImage(Tank.class.getResource("Images/tankR.gif")), 
				tk.getImage(Tank.class.getResource("Images/HtankD2.gif")),
				tk.getImage(Tank.class.getResource("Images/HtankU2.gif")),
				tk.getImage(Tank.class.getResource("Images/HtankL2.gif")),
				tk.getImage(Tank.class.getResource("Images/HtankR2.gif")),
                                tk.getImage(Tank.class.getResource("Images/HtankD.gif")), 
				tk.getImage(Tank.class.getResource("Images/HtankU.gif")), 
				tk.getImage(Tank.class.getResource("Images/HtankL.gif")),
				tk.getImage(Tank.class.getResource("Images/HtankR.gif")),
				};

	}

	public Tank(int x, int y, boolean good) {
		this.x = x;
		this.y = y;
		this.oldX = x;
		this.oldY = y;
		this.good = good;
	}

	public Tank(int x, int y, boolean good, Direction dir, WAROFTANK war, int player) {
		this(x, y, good);
		this.direction = dir;
		this.war = war;
		this.player=player;
	}

	public void draw(Graphics g) {
		if (!live) {
			if (!good) {
				war.tanks.remove(this);
			}
			return;
		}
		if (good)
			new DrawBloodbBar().draw(g); 
		switch (Kdirection) {
							
		case D:
			if(player==1){	g.drawImage(tankImags[4], x, y, null);
			}
			else if(war.Player2&&player==2){
				g.drawImage(tankImags[8], x, y, null);
			}else{
			g.drawImage(tankImags[0], x, y, null);}
			break;

		case U:
			if(player==1){	g.drawImage(tankImags[5], x, y, null);
			}else if(war.Player2&&player==2){
				g.drawImage(tankImags[9], x, y, null);
			}else{
			g.drawImage(tankImags[1], x, y, null);}
			break;
		case L:if(player==1){	g.drawImage(tankImags[6], x, y, null);
		}else if(war.Player2&&player==2){
			g.drawImage(tankImags[10], x, y, null);
		}else{
			g.drawImage(tankImags[2], x, y, null);}
			break;

		case R:if(player==1){	g.drawImage(tankImags[7], x, y, null);
		}else if(war.Player2&&player==2){
			g.drawImage(tankImags[11], x, y, null);
		}else{
			g.drawImage(tankImags[3], x, y, null);}
			break;

		}

		move();   
	}

	void move() {

		this.oldX = x;
		this.oldY = y;

		switch (direction) {  
		case L:
			x -= speedX;
			break;
		case U:
			y -= speedY;
			break;
		case R:
			x += speedX;
			break;
		case D:
			y += speedY;
			break;
		case STOP:
			break;
		}

		if (this.direction != Direction.STOP) {
			this.Kdirection = this.direction;
		}

		if (x < 0)
			x = 0;
		if (y < 40)     
			y = 40;
		if (x + Tank.width > WAROFTANK.Fram_width)  
			x = WAROFTANK.Fram_width - Tank.width;
		if (y + Tank.length > WAROFTANK.Fram_length)
			y = WAROFTANK.Fram_length - Tank.length;

		if (!good) {
			Direction[] directons = Direction.values();
			if (step == 0) {                  
				step = r.nextInt(12) + 3;  
				int mod=r.nextInt(9);
				if (playertankaround()){
					if(x==war.HomeTank.x){ if(y>war.HomeTank.y) direction=directons[1];
					else if (y<war.HomeTank.y) direction=directons[3];
					}else if(y==war.HomeTank.y){ if(x>war.HomeTank.x) direction=directons[0];
					else if (x<war.HomeTank.x) direction=directons[2];
					}
					else{
						int rn = r.nextInt(directons.length);
						direction = directons[rn]; 
					}
					rate=2;
				}else if (mod==1){
					rate=1;
				}else if(1<mod&&mod<=3){
					rate=1;
				}else{
				int rn = r.nextInt(directons.length);
				direction = directons[rn]; 
				rate=1;}    
			}
			step--;
			if(rate==2){
                            if(WAROFTANK.state == WAROFTANK.STATE.EASY){
				if (r.nextInt(40) > 36)          ///// CHANGE FIRE RATE
					this.fire();
                            }
                            else if(WAROFTANK.state == WAROFTANK.STATE.NORMAL){
				if (r.nextInt(20) > 12)          ///// CHANGE FIRE RATE
					this.fire();
                            }
                            else{
                                if (r.nextInt(30) > 5)          ///// CHANGE FIRE RATE
					this.fire();
                            }
			}else if (r.nextInt(40) > 38)
				if(WAROFTANK.state == WAROFTANK.STATE.EASY){
				if (r.nextInt(40) > 36)          ///// CHANGE FIRE RATE
					this.fire();
                            }
                                else if(WAROFTANK.state == WAROFTANK.STATE.NORMAL){
				if (r.nextInt(20) > 10)          ///// CHANGE FIRE RATE
					this.fire();
                            }
                            else{
                                if (r.nextInt(200) > 1)          ///// CHANGE FIRE RATE
					this.fire();
                            }
		}
	}
	public boolean playertankaround(){
		int rx=x-15,ry=y-15;
		if((x-15)<0) rx=0;
		if((y-15)<0)ry=0;
		Rectangle a=new Rectangle(rx, ry,60,60);
		if (this.live && a.intersects(war.HomeTank.getRect())) {
		return true;	
		}
		return false;	
	}
	public int getdirect(int a,int b){
		if(b==13){
			
		}
		return 4;
	}
	private void changToOldDir() {  
		x = oldX;
		y = oldY;
	}

	public void keyPressed(KeyEvent e) {  
		int key = e.getKeyCode();
		if (player==1){
		switch (key) {
		case KeyEvent.VK_R:  
			war.tanks.clear(); 
			war.bullets.clear();
			war.trees.clear();
			war.otherWall.clear();
			war.HomeWall.clear();
			war.steelWall.clear();
			war.HomeTank.setLive(false);
			if (war.tanks.size() == 0) {        
				for (int i = 0; i < 20; i++) {
					if (i < 9)                             
						war.tanks.add(new Tank(150 + 70 * i, 40, false,
								Direction.R, war,0));
					else if (i < 15)
						war.tanks.add(new Tank(700, 140 + 50 * (i -6), false,
								Direction.D, war,0));
					else
						war.tanks.add(new Tank(10,  50 * (i - 12), false,
								Direction.L, war,0));
				}
			}
			
			war.HomeTank = new Tank(300, 560, true, Direction.STOP, war,0);
			if (!war.Home.isLive()) 
				war.Home.setLive(true);
			WAROFTANK abc=new WAROFTANK();
			if (war.Player2) abc.Player2=true;
			break;
		case KeyEvent.VK_RIGHT:
			bR = true;
			break;
			
		case KeyEvent.VK_LEFT:
			bL = true;
			break;
		
		case KeyEvent.VK_UP:  
			bU = true;
			break;
		
		case KeyEvent.VK_DOWN:
			bD = true;
			break;
		}}
		
		decideDirection();
	}

	void decideDirection() {
		if (!bL && !bU && bR && !bD) 
			direction = Direction.R;

		else if (bL && !bU && !bR && !bD) 
			direction = Direction.L;

		else if (!bL && bU && !bR && !bD) 
			direction = Direction.U;

		else if (!bL && !bU && !bR && bD) 
			direction = Direction.D;

		else if (!bL && !bU && !bR && !bD)
			direction = Direction.STOP; 
	}

	public void keyReleased(KeyEvent e) {  
		int key = e.getKeyCode();
		if (player==1){
		switch (key) {
		
		case KeyEvent.VK_SPACE:
			new SoundPlayer("/Sounds/gun-gunshot-02.mp3").play();
                        fire();
			break;
			
		case KeyEvent.VK_RIGHT:
			bR = false;
			break;
		
		case KeyEvent.VK_LEFT:
			bL = false;
			break;
		
		case KeyEvent.VK_UP:
			bU = false;
			break;
		
		case KeyEvent.VK_DOWN:
			bD = false;
			break;
			

		}}
		
		decideDirection(); 
	}

	public Bullets fire() { 
		if (!live)
			return null;
		int x = this.x + Tank.width / 2 - Bullets.width / 2; 
		int y = this.y + Tank.length / 2 - Bullets.length / 2;
                System.err.println(x);
		Bullets m = new Bullets(x, y + 2, good, Kdirection, this.war); 
		war.bullets.add(m);                                                
		return m;
	}


	public Rectangle getRect() {
		return new Rectangle(x, y, width, length);
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public boolean isGood() {
		return good;
	}

	public boolean collideWithWall(BreakWall w) {  
		if (this.live && this.getRect().intersects(w.getRect())) {
			 this.changToOldDir();    
			return true;
		}
		return false;
	}

	public boolean collideWithWall(SteelWall w) { 
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.changToOldDir();     
			return true;
		}
		return false;
	}

	public boolean collideRiver(River r) {    
		if (this.live && this.getRect().intersects(r.getRect())) {
			this.changToOldDir();
			return true;
		}
		return false;
	}

	public boolean collideHome(Home h) {  
		if (this.live && this.getRect().intersects(h.getRect())) {
			this.changToOldDir();
			return true;
		}
		return false;
	}

	public boolean collideWithTanks(java.util.List<Tank> tanks) {
		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i);
			if (this != t) {
				if (this.live && t.isLive()
						&& this.getRect().intersects(t.getRect())) {
					this.changToOldDir();
					t.changToOldDir();
					return true;
				}
			}
		}
		return false;
	}


	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	private class DrawBloodbBar {
		public void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.RED);
			g.drawRect(375, 585, width, 10);
			int w = width * life / 200;
			g.fillRect(375, 585, w, 10);
			g.setColor(c);
		}
	}

	


	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}