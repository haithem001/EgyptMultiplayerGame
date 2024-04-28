import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class Gun extends JComponent{

    private final int w;
    private final int h;
    private int x;
    private int y;

    private boolean exist ;
    private final Image image ;



    Gun(int x, int y){

        this.x=x;
        this.y=y;
        this.setVisible(true);
        ImageIcon item =new ImageIcon("src/GUN.png");
        image=item.getImage();
        w=image.getWidth(null);
        h=image.getHeight(null);

    }

    public void setX(int x) {

        this.x=x;
    }
    public void setY(int y) {

        this.y=y;
    }
    public int getX() {

        return this.x;
    }
    public int getY() {

        return this.y;
    }
    public int getW() {

        return this.w;
    }
    public int getH() {

        return this.h;
    }
    public Image getImage() {
        return image;
    }

    public boolean isExisted() {

        return this.exist;
    }
}
