import java.awt.*;

public class BLOCK {

    private int x,y,w,h;
    public int BlockAnimCount,BlockAnimPos, BlockAnimDir ;
    final int  BLOCK_ANIM_DELAY;
    public Rectangle BODY;
    public BLOCK (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        BODY=new Rectangle(x,y,w,h);
        BLOCK_ANIM_DELAY = 60 *15;

        BlockAnimCount =BLOCK_ANIM_DELAY ;
        BlockAnimPos=0;
        BlockAnimDir= 1;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
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
    public int getW() {
        return w;
    }
    public int getH() {
        return h;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setW(int w) {
        this.w = w;
    }
    public void setH(int h) {
        this.h = h;
    }
}
