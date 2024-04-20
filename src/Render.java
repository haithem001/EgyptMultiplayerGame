import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class Render extends JFrame{
	Render(){
		
		this.setSize(1000,1000);
		this.setVisible(true);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

		
		this.add(new Game()); 
		
	    
		
		 
		
		
	}
	 
}
