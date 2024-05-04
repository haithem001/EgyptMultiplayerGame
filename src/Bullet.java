import javax.swing.*;
import java.awt.*;

public class Bullet {
    double angle;
    int x,y,w,h;
    int dx,dy;
    double Vx=1,Vy=1;
    double length;

    Image img;
    boolean visible;

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public Bullet(int startX, int startY , int MouseX, int MouseY)
    {
        x=startX;
        y=startY;
        this.w=10;
        this.h=10;
        float XLEN = MouseX - startX;
        float YLEN = MouseY - startY;

        length = Math.sqrt(XLEN * XLEN + YLEN * YLEN);

        Vx= (XLEN  / length) ;
        Vy= (YLEN / length) ;

        angle = Math.atan2(MouseX - startX, MouseY - startY) + Math.PI;









        ImageIcon newBullet = new ImageIcon("src/BULLET.png");
        img = newBullet.getImage();
        visible = true;
    }

    public Rectangle getBounds()
    {
        return new Rectangle(x,y, 19, 23);
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public boolean getVisible()
    {
        return visible;
    }
    public Image getImage()
    {
        return img;
    }



    public void move(int width, int height)
    {
        x += (int) (Vx*40 );
        y += (int) (Vy*40  );
        if (x>width || y>height||x<0||y<0){
            visible = false;

        }



    }

    public void setVisible(boolean isVisible)//down
    {
        visible = isVisible;
    }


}