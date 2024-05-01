import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ALIEN {

    private int x;
    private int y;
    private int startX,startY;
    private boolean alive = true;
    private int w;
    private int h;



    private int dx=5;
    private int dy=5;

    public Rectangle BODY;
    public List<AlienBullet> bullets=new ArrayList<>();
    private final Image img ;

    public  ALIEN (int x, int y) {

        this.x = x;
        this.y = y;
        this.startX=x;

        BODY=new Rectangle(x,y,w,h);
        this.alive=true;
        ImageIcon rect = new ImageIcon("src/ALIEN.png");
        this.img = rect.getImage();

        w = img.getWidth(null);
        h =img.getHeight(null);




    }
    public int getDy() {
        return dy;
    }
    public Image getImage() {
        return this.img;
    }

    public  int getX() {
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
    public void move(){
        this.x+=dx;
        if(x>startX+100){
            dx=-dx;
        }else if (x<startX-100){
            dx=-dx;
        }
        if (y>startY+400){
            dy=0;

        }



        this.y+=dy;
    }
    public void shoot(int x, int y, int basex,int basey){
        bullets.add(new AlienBullet(x,y,basex,basey));
        System.out.println(bullets.size());
    }
    public void setW(int w) {
        this.w = w;
    }
    public void setH(int h) {
        this.h = h;
    }


    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
