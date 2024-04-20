import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HUD extends JPanel{
		private int x;
		private int y;
		private int w;
		private int h;
		private int coins=0;
		private boolean inventory=false;
		private Image[] COINS = new Image [2];
		private Image[] Bag = new Image [2];
		private Image img;
		private Image Heartimg;
		private Image itemsList;
		ImageIcon coin1=new ImageIcon("src/COIN.png");
		ImageIcon coin2=new ImageIcon("src/COIN1.png");
		ImageIcon hudimg=new ImageIcon("src/Hud.png");
		ImageIcon Items=new ImageIcon("src/ItemsList.png");
		ImageIcon heartimg=new ImageIcon("src/heart.png");
		
		ImageIcon bag=new ImageIcon("src/Bag.png");
		ImageIcon bagclicked=new ImageIcon("src/BagClicked.png");
		
		HUD(){
			this.Bag[0]=bag.getImage();
			this.Bag[1]=bagclicked.getImage();
			this.COINS[0]=coin1.getImage();
			this.COINS[1]=coin2.getImage();
			
			w = hudimg.getIconWidth();
			h = hudimg.getIconHeight();
			
			this.img=hudimg.getImage();
			this.Heartimg=heartimg.getImage();
			this.itemsList=Items.getImage();
		}
		public String getCoins() {
			return Integer.toString(coins);
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
		public boolean GetInventoryOpen() {
			return inventory;
		}
		public void SetInventoryOpen(boolean inventory) {
			this.inventory=inventory;
		}
		public Image getImage() {
			return img;
		}
		public Image getHeartImage() {
			return Heartimg;
		}
		public Image getItemsImage() {
			return itemsList;
		}
		public Image getCoinImage(int i) {
		    	return COINS[i];
		   }
		public Image getBagImage(int p) {
	    	return (p==0)? Bag[0]:Bag[1] ;
	   }
		
		 
}
