import java.awt.*;

import javax.swing.ImageIcon;

public class AlienBullet {
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

    public AlienBullet(int startX, int startY , int BaseX, int BaseY)
    {
        x=startX;
        y=startY;
        this.w=10;
        this.h=10;
        float XLEN = BaseX - startX;
        float YLEN = BaseY - startY;

        length = Math.sqrt(XLEN * XLEN + YLEN * YLEN);

        Vx= (XLEN  / length) ;
        Vy= (YLEN / length) ;

        angle = Math.atan2(BaseX - startX, BaseY - startY) + Math.PI;









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



    public void move(int width)
    {
        x += (int) (Vx*30 );
        y += (int) (Vy*30  );
        if (y>900 || x>width || x<0){
            visible = false;

        }


        System.out.println(x);
    }

    public void setVisible(boolean isVisible)//down
    {
        visible = isVisible;
    }


}