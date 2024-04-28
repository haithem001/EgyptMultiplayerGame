import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class PickAxe extends JComponent{
	private int w;
	private int h;
	private int x;
	private int y;

	private boolean exist ;
	private Image image ;
	

	
	PickAxe(int x, int y){

		this.x=x;
		this.y=y;
		this.setVisible(true);
			ImageIcon item =new ImageIcon("src/PICKAXE.png");
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
