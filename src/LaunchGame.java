
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class LaunchGame extends JFrame  implements ActionListener{

LaunchGame(){


		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//this.setUndecorated(true);


		this. setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.black);
		Game game = new Game();
		this.setVisible(true);



		game.setBackground(Color.black);
		game.setVisible(true);
		this.add(game);
		this.getContentPane().add(game, "Center");








}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

	}
}


