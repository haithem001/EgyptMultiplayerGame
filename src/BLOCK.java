import javax.swing.*;
import java.awt.*;

public class BLOCK {

    private int x;
    private int y;
    private int w;
    private int h;
    private int health=2;
    private boolean visible=true;
    private  boolean base=false;
    private  boolean builded;
    public int BlockAnimCount,BlockAnimPos, BlockAnimDir ;
    final int  BLOCK_ANIM_DELAY;
    public Rectangle BODY;
    private final Image[] BlockImgs = new Image[3];
    private final Image[] BaseImg =new Image[1];
    private final Image[] Build = new Image[1];
    public BLOCK (int x, int y, boolean Base , boolean Built) {
        this.x = x;
        this.y = y;


        this.builded = Built;
        BODY=new Rectangle(x,y,w,h);
        BLOCK_ANIM_DELAY = 60 *15;

        BlockAnimCount =BLOCK_ANIM_DELAY ;
        BlockAnimPos=0;
        BlockAnimDir= 1;
        ImageIcon empty = new ImageIcon("src/EMPTY.png");
        this.Build[0] = empty.getImage();
        if (Base){
            ImageIcon baseimg = new ImageIcon("src/BASE.png");
            ImageIcon ii1 = new ImageIcon("src/BLOCK1.png");
            this.BlockImgs[0] = ii1.getImage();
            this.BaseImg[0] = baseimg.getImage();
            this.w = BaseImg[0].getWidth(null);

            this.h = BaseImg[0].getHeight(null);
            this.x = x;
            this.y = y;

            this.visible=true;

        }
        else {

            ImageIcon ii1 = new ImageIcon("src/BLOCK3.png");
            ImageIcon ii2 = new ImageIcon("src/BLOCK2.png");
            ImageIcon ii3 = new ImageIcon("src/BLOCK1.png");
            this.BlockImgs[0] = ii1.getImage();
            this.BlockImgs[1] = ii2.getImage();
            this.BlockImgs[2] = ii3.getImage();
            this.w = BlockImgs[0].getWidth(null);
            this.h = BlockImgs[0].getHeight(null);
        }




    }
    public Image getEmptyBlockImg(){
        return Build[0];
    }
    public Image getBaseImage() {
        return BaseImg[0];
    }
    public Image getImage(int p) {
        return BlockImgs[p];
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }



    public int getW() {
        return w;
    }
    public int getH() {
        return h;
    }
    public void setX(int x) {
        this.x = x;
        this.BODY.x=x;
    }
    public void setY(int y) {
        this.y = y;
        this.BODY.y=y;
    }
    public void setW(int w) {
        this.w = w;
    }
    public void setH(int h) {
        this.h = h;
    }

    public boolean isBase() {
        return base;
    }

    public void setBase(boolean base) {
        this.base = base;
    }

    public boolean isBuilded() {
        return builded;
    }

    public void setBuilded(boolean builded) {
        this.builded = builded;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
