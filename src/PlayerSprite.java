import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PlayerSprite {
    private double x, y, size;
    private Color color;

    public PlayerSprite(double x, double y, double size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }
    public void draw(Graphics2D g) {
        Rectangle2D rect = new Rectangle2D.Double(x, y, size, size);
        g.setColor(color);
        g.fill(rect);
    }
    public void moveH (double  n){
        x += n;
    }
    public void moveV (double  n){
        y += n;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
