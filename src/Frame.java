import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class Frame extends JFrame implements ActionListener{
    
  

	JLabel loading ;
	JButton play;
	JLabel label; 
	Frame(){
			
	        this.setResizable(false);
	        ImageIcon image = new ImageIcon("pacman-png-25195.png");
	        this.setIconImage(image.getImage());
	  		
	        
	  		
	        
	}
	
	void SetColor(Color color){
		this.getContentPane().setBackground(color);
	}
	public void Welcome(){
		
		label =new JLabel();
	      
      	label.setText("	Play Gome!");
      	label.setBounds(400,400,100,100);
      	label.setFont(new Font("Arial",Font.BOLD,30));
      	label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
   
      	label.setForeground(Color.white);
      	label.setVisible(true);
      	
  		this.add(label);
  		
  		play = new JButton();
		play.setBounds(240,250,100,50);	
		play.setText("PLAY");
		play.setFocusable(false); 
		play.setBackground(Color.black);
        play.addActionListener(this);
        play.setFont(new Font("Arial",Font.BOLD,20));
        this.add(play);
		
		loading=new JLabel();
		loading.setText("	Loading ...");
		loading.setVerticalAlignment(JLabel.CENTER);
		loading.setHorizontalAlignment(JLabel.CENTER);
		loading.setFont(new Font("MV Boli",Font.BOLD,30));
		loading.setVisible(false);
		loading.setForeground(Color.white);
		loading.setAlignmentX(JLabel.BOTTOM_ALIGNMENT);

		this.add(loading);
		
		
  		
  		
	}
	
		
		
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==play){
			this.play.setVisible(false);
			this.label.setVisible(false);
			this.loading.setVisible(true);
			this.dispose();
					
			
 		}
	}
	
	
}

