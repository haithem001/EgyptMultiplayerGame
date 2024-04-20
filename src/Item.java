import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class Item extends JComponent{
	private int w;
	private int h;
	private int x;
	private int y;
	private int Id=0;
	private int damage=0;
	private int heal=0;
	private int shield=0;
	private boolean functional=false;
	private boolean exist ;
	private Image image ;
	

	
	Item(int Id,int damage,int heal,int shield,boolean functional,boolean exist,int x,int y){
		this.damage=damage;
		this.heal=heal;
		this.shield=shield;
		this.functional=functional;
		this.exist=exist;
		this.Id=Id;
		this.x=x;
		this.y=y;
		this.setVisible(true);
			ImageIcon item =new ImageIcon("src/"+Integer.toString(Id)+".png");
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
