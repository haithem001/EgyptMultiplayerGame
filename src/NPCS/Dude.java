package NPCS;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

public class Dude {

	private int dx;
	private int dy;
	public int x;
	public int y;
	private boolean pushTalk =false;
	private boolean stopwalk;
	private int lastdir;
	private boolean resetanim;
	private boolean stopclimb = true;
	private int w;
	private int h;
	private int health = 3;
	private boolean interact = false;
	private boolean climbing = false;
	private boolean interactPressed = false;
	private boolean walking = true;
	private int intomap = 1;
	private int intofight = 0;
	private Image[] WRIGHT = new Image[2];
	private Image[] WLEFT = new Image[2];

	private Image[] Climb = new Image[4];
	private Image[] Swalk = new Image[2];
	private Image[] SwalkL = new Image[2];
	private boolean dark = false;
	public Rectangle solidarea;
	private boolean allowWalk = false;
	private int Velocity=10;
	private boolean JUMP = false;
	private int jump_height = 15;

	private int velocity = -jump_height;
	private boolean on_ground = false;
	public Dude() {

		loadPlayerIms();

	}

	private void loadPlayerIms() {

		ImageIcon ii1 = new ImageIcon("src/player1.png");
		ImageIcon ii2 = new ImageIcon("src/Player2.png");
		ImageIcon ii3 = new ImageIcon("src/player1L.png");
		ImageIcon ii4 = new ImageIcon("src/Player2L.png");
		ImageIcon climb1 = new ImageIcon("src/playerClimb1.png");
		ImageIcon climb2 = new ImageIcon("src/playerClimb2.png");
		ImageIcon climb3 = new ImageIcon("src/playerClimb3.png");
		ImageIcon climb4 = new ImageIcon("src/playerClimb4.png");
		ImageIcon Swalk1 = new ImageIcon("src/PlayerShadowwalk1.png");
		ImageIcon Swalk2 = new ImageIcon("src/PlayerShadowwalk2.png");
		ImageIcon Swalk3 = new ImageIcon("src/PlayerShadowwalk1L.png");
		ImageIcon Swalk4 = new ImageIcon("src/PlayerShadowwalk2L.png");

		this.Climb[0] = climb1.getImage();
		this.Climb[1] = climb2.getImage();
		this.Climb[2] = climb3.getImage();
		this.Climb[3] = climb4.getImage();
		this.WRIGHT[0] = ii1.getImage();
		this.WRIGHT[1] = ii2.getImage();
		this.WLEFT[0] = ii3.getImage();
		this.WLEFT[1] = ii4.getImage();
		this.Swalk[0] = Swalk1.getImage();
		this.Swalk[1] = Swalk2.getImage();
		this.SwalkL[0] = Swalk3.getImage();
		this.SwalkL[1] = Swalk4.getImage();

		w = WRIGHT[0].getWidth(null);
		h = WRIGHT[0].getHeight(null);

	}

	public int getdx() {

		return this.dx;
	}

	public int getdy() {

		return this.dy;
	}

	public void setX(int x) {

		this.x = x;
	}

	public int gethealth() {
		return this.health;
	}

	public void setY(int y) {

		this.y = y;
	}

	public int getX() {

		return this.x;
	}

	public int getY() {

		return this.y;
	}

	public int getWidth() {

		return w;
	}

	public int getHeight() {

		return h;
	}

	public int getIntoMap() {

		return intomap;
	}
	public int getIntoFight() {

		return intofight;
	}

	public boolean getInteract() {

		return interact;
	}

	public boolean isClimbing() {

		return climbing;
	}

	public boolean isWalking() {

		return walking;
	}
	public boolean Talkmessage() {
		return pushTalk;
	}
	

	public void setInteract(boolean inter) {
			this.interact = inter;
		
			
		
		
	}

	public void setDark(boolean dark) {
		this.dark = dark;
	}

	public boolean getDark() {
		return this.dark;
	}

	public Image getImage(int p) {

		if (climbing) {
			if (dy == 0) {
				return Climb[dx];
			}
			return Climb[p];
		}
		if (walking) {
			if (dx == 0 && dy == 0) {
				if (lastdir == -1) {
					return WLEFT[dx] ;
				}
				if (lastdir == 1) {
					return  WRIGHT[dx];
				}
			}
			if (dx >= 1) {
				stopwalk = true;
				lastdir = dx;
				return WRIGHT[p];

			} else if (dx <= -1) {
				stopwalk = true;
				lastdir = dx;
				return WLEFT[p];

			}

			if (dy >= 1 || dy <= -1) {

				if (stopwalk == true) {

					return WRIGHT[p] ;
				} else {
					return  WLEFT[p] ;
				}

			}

		}

		return (stopwalk == true) ? WRIGHT[dx] : WLEFT[dx];

	}

	public void move(int W,int H) {
		System.out.printf("(%d,%d)\n)",this.x, this.y);




						this.x = this.x + dx;
						if (this.JUMP &&this.y +this.h>=H-100 ){
							this.Velocity = -this.jump_height;
							this.y+=velocity;
							System.out.println(this.Velocity);

						}
						if (this.y + this.h < H - 100 ){
							this.Velocity += 1;

							y+=this.Velocity;

						}
						if (this.y +this.h>=H-100 ){

							this.Velocity=0;

						}

	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_Q) {

			dx = -10;

		}

		if (key == KeyEvent.VK_D) {

			dx = 10;
		}
		if (key == KeyEvent.VK_SPACE) {
			this.JUMP=true;


		}

		if (key == KeyEvent.VK_Z) {
			dy = 10;
		}

		if (key == KeyEvent.VK_S) {
			dy = -10;
		}
		if (key == KeyEvent.VK_F) {
			interactPressed = true;
		}
	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_Q) {
			dx = 0;
			stopwalk = true;
		}
		if (key == KeyEvent.VK_SPACE) {
			this.JUMP=false;


		}
		if (key == KeyEvent.VK_D) {
			dx = 0;
			stopwalk = true;
		}
		if (key == KeyEvent.VK_F) {
			interactPressed = true;
			pushTalk=false;
		}
		if (key == KeyEvent.VK_Z) {
			dy = 0;
			stopwalk = true;
		}

		if (key == KeyEvent.VK_S) {
			dy = 0;
			stopwalk = true;
		}
	}

}
