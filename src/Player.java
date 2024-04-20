import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.*;
public class Player {
	
	private int dx ;
	private int dy;
	private int x=700;
	private int y=700;
	private int stop;
	private int w;
	private int h;
	private Image image;
	
	public Player() {
		
		loadPlayer();
		
	}
	private void loadPlayer() {
		ImageIcon ii=new ImageIcon("src/kisspng-pixel-art-8-bit-color-heart-5ae5dfc8aa6fc4.5443438815250144726981.png");
		
		image=ii.getImage();
		
		 w = image.getWidth(null);
		 h = image.getHeight(null);
	}
	public void setX(int x) {
        
        this.x=x;
    }

    public void setY(int y) {
        
        this.y=y;
    }
	public int getX() {
        
        return x;
    }

    public int getY() {
        
        return y;
    }
    
    public int getWidth() {
        
        return w;
    }
    
    public int getHeight() {
        
        return h;
    }    

    public Image getImage() {
        
        return image;
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }
 
	

	
	public void move(int minx,int miny,int maxx,int maxy) {
	
		this.x=this.x+2*dx;
		if(this.x<minx) {
			this.x=minx;
		}
		if(this.x>maxx) { 
			this.x=maxx;
		}
		this.y=this.y+2*dy;
		if(this.y<miny) {
			this.y=miny;
		}
		if(this.y>maxy) {
			this.y=maxy;
		}
	}
	
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT){
        
            dx = -2;
  
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -2;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
    }

    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

	
}
