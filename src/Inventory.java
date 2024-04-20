import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Inventory extends JPanel{
	private int x=20;
	private int y=20;
	private int w;
	private int h;
	private Image inventoryimg;
	public Items items;
	public int items_number;
	
	
	Inventory(){
		
		
		items = new Items();
		
		ImageIcon Invimg= new ImageIcon("src/Inventory.png");
		inventoryimg=Invimg.getImage();
		
		w=inventoryimg.getWidth(null); 
		h=inventoryimg.getHeight(null);
		
			
		
	}
	public void setX(int x) {
		this.x=x;
		this.getItems().setX(x);
	}
	public void setY(int y) {
		this.y=y;
		this.getItems().setY(y);

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
	public Image getInventoryImage() {
		return inventoryimg;
	}
	
	public Items getItems() {
		return items;
	}

	public int getItemsrows() {
		return items.getrows();
	}
	public int getItemscolumns() {
		return items.getcolumns();
	}
	
}
