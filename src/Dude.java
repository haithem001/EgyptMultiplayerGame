import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


import javax.swing.ImageIcon;

public class Dude {
    public int x;
    public int y;
    private int dx;
    private int dy;
    private boolean stopwalk;
    private int lastdir;
    private int w;
    private int h;
    private int health = 3;
    private boolean walking = true;
    private Image[] WRIGHT = new Image[2];
    private Image[] WLEFT = new Image[2];
    private int Velocity = 10;
    private boolean JUMP = false;
    private int jump_height = 15;
    private boolean on_ground = true;
    public PickAxe Axe;
    public Gun BOMBA;
    public List<Bullet> Bullets = new ArrayList<>();
    private boolean Itemchoosen;
    public int BlockAnimCount,BlockAnimPos=0, BlockAnimDir ;
    public final int  BLOCK_ANIM_DELAY;
    public Dude() {
        BLOCK_ANIM_DELAY = 60 *15;

        BlockAnimCount =BLOCK_ANIM_DELAY ;
        BlockAnimPos=0;
        BlockAnimDir= 1;
        loadPlayerIms();

    }
    public boolean isItemchoosen() {
        return Itemchoosen;
    }

    public void setItemchoosen(boolean itemchoosen) {
        Itemchoosen = itemchoosen;
    }
    public void Shoot(int MouseX, int MouseY){
            Bullets.add(new Bullet(BOMBA.getX(),BOMBA.getY(),MouseX,MouseY));
        System.out.println(Bullets.size());
    }
    private void loadPlayerIms() {



        ImageIcon ii1 = new ImageIcon("src/PLAYER1_1.png");
        ImageIcon ii2 = new ImageIcon("src/PLAYER1_2.png");
        ImageIcon ii3 = new ImageIcon("src/PLAYER1_1L.png");
        ImageIcon ii4 = new ImageIcon("src/PLAYER1_2L.png");



        this.WRIGHT[0] = ii1.getImage();
        this.WRIGHT[1] = ii2.getImage();
        this.WLEFT[0] = ii3.getImage();
        this.WLEFT[1] = ii4.getImage();




        w = WRIGHT[0].getWidth(null);
        h = WRIGHT[0].getHeight(null);
        Axe=new PickAxe(0,0);
        BOMBA=new Gun(0,0);

    }

    public int getdx() {

        return this.dx;
    }

    public int getdy() {

        return this.dy;
    }

    public int gethealth() {
        return this.health;
    }

    public int getX() {

        return this.x;
    }

    public void setX(int x) {

        this.x = x;
    }

    public int getY() {

        return this.y;
    }

    public void setY(int y) {

        this.y = y;
    }

    public int getWidth() {

        return w;
    }

    public int getHeight() {

        return h;
    }






    public boolean isWalking() {

        return walking;
    }






    public Image getImage(int p) {


        if (walking) {
            if (dx == 0 && dy == 0) {
                if (lastdir == -1) {
                    return WLEFT[dx];
                }
                if (lastdir == 1) {
                    return WRIGHT[dx];
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

                    return WRIGHT[p];
                } else {
                    return WLEFT[p];
                }

            }

        }

        return (stopwalk == true) ? WRIGHT[dx] : WLEFT[dx];

    }

    public void move(int W, int H, List<BLOCK> BLOCKS, int platform) {
        System.out.printf("(%d,%d)\n)", this.x, this.y);


        this.x = this.x + dx;
        if (this.y + this.h < H - this.getHeight() - 5) {
            this.Velocity += 1;

            y += this.Velocity;

        }
		else{
			on_ground=true;
		}

        if (this.JUMP ) {
			this.on_ground=false;
            this.JUMP = false;

            this.Velocity = -this.jump_height;
            this.y += Velocity;
            System.out.println(this.Velocity);


        }


            for (BLOCK B:BLOCKS ){
                if (B.isBuilded()){
                    if (this.x + this.getWidth() >= B.getX() && this.x <= B.getX() + B.getW()) {
                        if (this.y + this.getHeight() < B.getY() - 11) {


                        } else {
                            this.y = B.getY() - this.getHeight() - 1;
                            this.Velocity = 0;
                            on_ground=true;
                        }


                    }
                }




        }


    }
    public void BlockAnim() {

        this.BlockAnimCount--;

        if (BlockAnimCount <= 0) {

            BlockAnimCount = BLOCK_ANIM_DELAY;
            BlockAnimPos = BlockAnimPos + BlockAnimDir;

            if (BlockAnimPos == (BlockAnimCount - 1) ) {
                BlockAnimPos=0;
            }
        }

    }
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        if (key == KeyEvent.VK_F) {

            this.Itemchoosen=!this.Itemchoosen;

        }
        if (key == KeyEvent.VK_Q) {

            dx = -10;

        }

        if (key == KeyEvent.VK_D) {

            dx = 10;
        }
        if (key == KeyEvent.VK_SPACE) {
			if(on_ground){
				this.JUMP = true;

			}


        }

        if (key == KeyEvent.VK_Z) {
            dy = 10;
        }

        if (key == KeyEvent.VK_S) {
            dy = -10;
        }

    }


    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_Q) {
            dx = 0;
            stopwalk = true;
        }
        if (key == KeyEvent.VK_SPACE) {


        }
        if (key == KeyEvent.VK_D) {
            dx = 0;
            stopwalk = true;
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
