import java.awt.Image;

import javax.swing.ImageIcon;

public class InteractionPanel  {
	private int x=1000;
	private int y=50;


	private int selector=1;
	private int interaction;
	private int AnimPos=0;
	private final Image[] InteractImgs =new Image[2];
	private int w;
	private int h;
	
	InteractionPanel(){
		
		
		LoadImage();
		
	}
	private void LoadImage() {
		
		ImageIcon i1=new ImageIcon("src/Interactions/1_0.png");
		ImageIcon i2=new ImageIcon("src/Interactions/1_1.png");
		this.InteractImgs[0]=i1.getImage();
		this.InteractImgs[1]=i2.getImage();
		 w=InteractImgs[0].getWidth(null);
		 h=InteractImgs[0].getHeight(null);
	}
	public void setSelector(int selector) {
		this.selector=selector;
	}
	
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public void setAnimPos(int AnimPos) {
		this.AnimPos=AnimPos;
	}
	
	
	  public Image getImage(int p) {
	        return InteractImgs[p];
	  
	    }

	public int getInteraction() {
		return interaction;
	}
	public void setInteraction(int interaction) {
		this.interaction=interaction;
	}
	
	
	
	
	
	
}
