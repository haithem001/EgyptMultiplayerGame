import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InitGame extends JFrame implements ActionListener{

	JLabel label;
	JButton CreateRoom,JoinRoom;
	Image logo;
	JPanel p=new JPanel();
	JPanel p1=new JPanel();
	JPanel ALL=new JPanel();
	JLabel image,image1,image2=new JLabel();
	JPanel Cbutton=new JPanel();
	JPanel Jbutton=new JPanel();
	JPanel ilabel=new JPanel();
	JPanel plabel=new JPanel();
	
	InitGame() throws IOException{

		BufferedImage i = ImageIO.read(new File("src/BuildEgypt.png"));
		image=new JLabel(new ImageIcon(i));
		image.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		//welcoming
		//p1(plabel,pbutton)

		CreateRoom = new JButton();
		JoinRoom=new JButton();
		label =new JLabel();




		ilabel.add(image);


		plabel.add(label);
		Cbutton.add(CreateRoom);
		Jbutton.add(JoinRoom);
		this.setUndecorated(true);
		p1.setLayout(new GridLayout(3,1,0,0));

		p.setBackground(Color.black);
		Cbutton.setBackground(Color.black);
		Jbutton.setBackground(Color.black);

		plabel.setBackground(Color.black);
		ilabel.setBackground(Color.black);
		ALL.setBackground(Color.BLACK);
		p1.add(ilabel);
		p1.add(Cbutton);
		p1.add(Jbutton);



		ALL.add(plabel);
		ALL.setLayout(new GridLayout(3,1,0,40));
		ALL.add(p1);

		this.add(ALL);

		//PORTADOX

		BufferedImage j = ImageIO.read(new File("src/CR.png"));
		image1=new JLabel(new ImageIcon(j));
		image1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		BufferedImage k = ImageIO.read(new File("src/JR.png"));
		image2=new JLabel(new ImageIcon(k));
		image2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		//PLAY BUTTON

		CreateRoom.setBounds(40,40,50,50);
		CreateRoom.setFocusable(false);
		CreateRoom.add(image1);
		CreateRoom.addActionListener(this);
		CreateRoom.setFont(new Font("PixellettersFull", Font.BOLD, 50));
		CreateRoom.setBorder(null);
		CreateRoom.setBackground(Color.black);
		CreateRoom.setFocusPainted(false);
		CreateRoom.setVisible(true);

		JoinRoom.setBounds(40,40,50,50);
		JoinRoom.setFocusable(false);
		JoinRoom.add(image2);
		JoinRoom.addActionListener(this);
		JoinRoom.setFont(new Font("PixellettersFull", Font.BOLD, 50));
		JoinRoom.setBorder(null);
		JoinRoom.setBackground(Color.black);
		JoinRoom.setFocusPainted(false);
		JoinRoom.setVisible(true);



	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()== CreateRoom) {
			INIT_SERVER server= new INIT_SERVER();
			server.setSize(this.getSize());
			server.setVisible(true);
			server.setBackground(Color.black);

			this.dispose();

		}

	}
	
	    
}
