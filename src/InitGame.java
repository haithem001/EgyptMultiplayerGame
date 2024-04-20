import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class InitGame extends JFrame implements ActionListener{
	
	JLabel label;
	JButton play;
	Image logo;
	JPanel p=new JPanel();
	JPanel p1=new JPanel();
	JPanel ALL=new JPanel();
	JLabel image=new JLabel();
	JPanel pbutton=new JPanel();
	JPanel ilabel=new JPanel();
	JPanel plabel=new JPanel();
	
	InitGame() throws IOException{	

        play = new JButton();
        label =new JLabel();
        
        
      
        
        ilabel.add(image);
        
        
        plabel.add(label);
        pbutton.add(play);
        this.setUndecorated(true);
		p1.setLayout(new GridLayout(2,1,0,0));
		
		p.setBackground(Color.black);
		pbutton.setBackground(Color.black);
		plabel.setBackground(Color.black);
		ilabel.setBackground(Color.black);
		
		p1.add(plabel);
		p1.add(pbutton);
		

		ALL.add(ilabel);
		ALL.setLayout(new GridLayout(2,1,0,0));
		ALL.add(p1);
		
		this.add(ALL);
		
		//PORTADOX
      	label.setText("Portadox");
      	label.setHorizontalAlignment(JLabel.CENTER);
      	label.setVerticalAlignment(JLabel.CENTER);
      	label.setForeground(Color.white);
      	label.setFont(new Font("PixellettersFull", Font.BOLD, 230));
        label.setBorder(null);
      	label.setVisible(true); 
      
      	
  		
  		
  		//PLAY BUTTON
		
		play.setText("PLAY");
		play.setFocusable(false); 
		
        play.addActionListener(this);
        play.setFont(new Font("PixellettersFull", Font.BOLD, 50));
        play.setBorder(null);
        play.setBackground(Color.black);
        play.setFocusPainted(false);
        play.setVisible(true);
        
	
}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==play) {
			LaunchGame start=new LaunchGame();
			start.getSize(this.getSize());
			start.setBackground(Color.black);
			this.dispose();
		}
		
	}
	
	    
}
