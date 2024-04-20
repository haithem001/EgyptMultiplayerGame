import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Items extends JComponent{
		private int x=125;
		private int y=135;
		private int w;
		private int h;
		private int rows=4;
		private int columns=7;
		private Item[][] list_items ={
				
				
				{	new Item(1,10,0,0,false,true,x,y),
					new Item(60,20,0,0,false,true,205,y),
					new Item(40,0,20,0,false,true,160,90),
					new Item(0,0,0,0,false,false,x+240,y),
					new Item(0,0,0,0,false,false,x+320,y),
					new Item(0,0,0,0,false,false,x+400,y),
					new Item(0,0,0,0,false,false,x+480,y)}
				,{
					new Item(0,0,0,0,false,false,x,y),
					new Item(0,0,0,0,false,false,40,y),
					new Item(0,0,0,0,false,false,x+80,y),
					new Item(0,0,0,0,false,false,x+120,y),
					new Item(0,0,0,0,false,false,x+160,y),
					new Item(0,0,0,0,false,false,x+200,y),
					new Item(0,0,0,0,false,false,x+240,y)
				}
				,{
					new Item(0,0,0,0,false,false,x,y),
					new Item(0,0,0,0,false,false,40,y),
					new Item(0,0,0,0,false,false,x+80,y),
					new Item(0,0,0,0,false,false,x+120,y),
					new Item(0,0,0,0,false,false,x+160,y),
					new Item(0,0,0,0,false,false,x+200,y),
					new Item(0,0,0,0,false,false,x+240,y)
				}
				,{
					new Item(0,0,0,0,false,false,x,y),
					new Item(0,0,0,0,false,false,40,y),
					new Item(0,0,0,0,false,false,x+80,y),
					new Item(0,0,0,0,false,false,x+120,y),
					new Item(0,0,0,0,false,false,x+160,y),
					new Item(0,0,0,0,false,false,x+200,y),
					new Item(0,0,0,0,false,false,x+240,y)
				}
				  
				 
				
		};
		
		Items(){
			
		
			
			
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
		public Image getItemImage(int i,int j) {
			return list_items[i][j].getImage();
		}
		public Item[][] getItemsList() {
			return list_items;
		}
		public JComponent getItem(int i,int j) {
			return list_items[i][j];
		}
		public int getItemX(int i,int j) {
			return list_items[i][j].getX();
		}
		public int getItemY(int i,int j) {
			return list_items[i][j].getY();
		}
		public void setItemXY(int i,int j ,int x,int y) {
			list_items[i][j].setX(x);
			list_items[i][j].setY(y);
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
		public int getrows() {
			return this.rows;
		}
		public int getcolumns() {
			return this.columns;
		}
		public int  showItemsExisted(){
			int sum=0;
			for (int i =0;i<rows;i++) {
				for (int k=0;k<columns;k++) {
					if (list_items[i][k].isExisted()==true) {
						sum+=1;
					}

				}
			
	
			
		}
		
			 return sum;
		
		
		}
		
}

