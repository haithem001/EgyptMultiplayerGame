import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Dude {
    public final int BLOCK_ANIM_DELAY;
    public int x;
    public int y;
    public boolean JUMP = false;
    public boolean on_ground = true;
    public PickAxe Axe;
    public Gun BOMBA;
    public List<Bullet> Bullets = new ArrayList<>();
    public int BlockAnimCount, BlockAnimPos = 0, BlockAnimDir = 1;
    private int DELAY = 60;
    private final int DUDE_ANIM_DELAY = DELAY * 15;
    private int dudeAnimCount = DUDE_ANIM_DELAY;
    private int dx;
    private int dy;
    private boolean stopwalk;
    private int lastdir;
    private int w;
    private int h;
    private int health = 5;
    private boolean walking = true;
    private Image[] WRIGHT = new Image[2];
    private Image[] WLEFT = new Image[2];
    private Image Over;
    private Image HEART;
    private int dudeAnimDir = 1;
    private int dudeAnimPos = 0;
    private int Velocity = 10;
    private int jump_height = 15;
    private int AnimCount=2;
    private boolean Choosiness;

    public Dude() {
        BLOCK_ANIM_DELAY = 60 * 15;

        BlockAnimCount = BLOCK_ANIM_DELAY;

        BlockAnimDir = 1;
        loadPlayerIms();

    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getDudeAnimPos() {
        return dudeAnimPos;
    }

    public void setDudeAnimPos(int dudeAnimPos) {
        this.dudeAnimPos = dudeAnimPos;
    }

    public boolean isChoosiness() {
        return Choosiness;
    }

    public void setChoosiness(boolean choosiness) {
        Choosiness = choosiness;
    }

    public void Shoot(int MouseX, int MouseY) {
        Bullets.add(new Bullet(BOMBA.getX(), BOMBA.getY(), MouseX, MouseY));
    }

    public Image GameOver() {
        return Over;
    }

    private void loadPlayerIms() {

        ImageIcon Gameover = new ImageIcon("src/GameOver.png");
        ImageIcon ii1 = new ImageIcon("src/PLAYER1_1.png");
        ImageIcon ii2 = new ImageIcon("src/PLAYER1_2.png");
        ImageIcon ii3 = new ImageIcon("src/PLAYER1_1L.png");
        ImageIcon ii4 = new ImageIcon("src/PLAYER1_2L.png");
        ImageIcon HEALTHICON = new ImageIcon("src/HEALTH.png");
        HEART = HEALTHICON.getImage();
        Over=Gameover.getImage();
        this.WRIGHT[0] = ii1.getImage();
        this.WRIGHT[1] = ii2.getImage();
        this.WLEFT[0] = ii3.getImage();
        this.WLEFT[1] = ii4.getImage();


        w = WRIGHT[0].getWidth(null);
        h = WRIGHT[0].getHeight(null);
        Axe = new PickAxe(0, 0);
        BOMBA = new Gun(0, 0);

    }

    public int getDELAY() {
        return DELAY;
    }

    public void setDELAY(int DELAY) {
        this.DELAY = DELAY;
    }

    public int getDudeAnimCount() {
        return dudeAnimCount;
    }

    public void setDudeAnimCount(int dudeAnimCount) {
        this.dudeAnimCount = dudeAnimCount;
    }

    public int getDudeAnimDir() {
        return dudeAnimDir;
    }

    public void setDudeAnimDir(int dudeAnimDir) {
        this.dudeAnimDir = dudeAnimDir;
    }

    public void doAnim() {
        {
            dudeAnimCount--;

            if (dudeAnimCount <= 0) {

                dudeAnimCount = DUDE_ANIM_DELAY;
                dudeAnimPos = dudeAnimPos + dudeAnimDir;

                if (dudeAnimPos == (AnimCount - 1) || dudeAnimPos == 0) {
                    dudeAnimDir = -dudeAnimDir;
                }
            }
        }


    }

    public int getdx() {

        return this.dx;
    }

    public Image getHEART() {
        return this.HEART;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getdy() {

        return this.dy;
    }

    public void setchoosiness(boolean choosiness) {
        this.Choosiness = choosiness;
    }

    public boolean getchoosiness() {
        return this.Choosiness;
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


    public void setWalking(boolean walking) {
        this.walking = walking;
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
                lastdir = dx;
                return WRIGHT[p];

            } else if (dx <= -1) {
                lastdir = dx;
                return WLEFT[p];

            }
        }




        if (stopwalk) {

            return WRIGHT[dx];
        } else {
            return WLEFT[dx];
        }



    }

    public void move(int W, int H, List<BLOCK> BLOCKS, int platform) {


        this.x = this.x + dx;
        if (this.y + this.h < H - this.getHeight() - 5) {
            this.Velocity += 1;

            y += this.Velocity;

        } else {
            on_ground = true;
        }

        if (this.JUMP) {
            this.on_ground = false;
            this.JUMP = false;

            this.Velocity = -this.jump_height;
            this.y += Velocity;


        }


        for (BLOCK B : BLOCKS) {
            if (B.isBuilded()) {
                if (this.x + this.getWidth() >= B.getX() && this.x <= B.getX() + B.getW()) {
                    if (this.y + this.getHeight() < B.getY() - 11) {


                    } else {
                        this.y = B.getY() - this.getHeight() - 1;
                        this.Velocity = 0;
                        on_ground = true;
                    }


                }
            }


        }


    }

    public boolean getStopWalk() {
        return stopwalk;
    }

    public void setStopWalk(boolean stopwalk) {
        this.stopwalk = stopwalk;
    }
}
