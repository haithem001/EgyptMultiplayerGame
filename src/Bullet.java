import java.awt.*;

import javax.swing.ImageIcon;

public class Bullet {
    double angle;
    int x,y;
    int dx,dy;
    double Vx=1,Vy=1;
    double length;

    Image img;
    boolean visible;
    public Bullet(int startX, int startY ,int MouseX, int MouseY)
    {
        x=startX;
        y=startY;

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

    public void move()

    {



        x += (int) (Vx*20 );


        y += (int) (Vy*20  );

        System.out.println(x);
    }

    public void setVisible(boolean isVisible)//down
    {
        visible = isVisible;
    }


}