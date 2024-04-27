import java.awt.Color;

import java.io.IOException;

import javax.swing.*;





public class GUI extends JFrame {

	public GUI() {

		this.setBackground(Color.black);

		/*
		 * play.addActionListener(new ActionListener() {
		 * 
		 * @Override
		 * public void actionPerformed(ActionEvent e) {
		 * 
		 * getContentPane().remove(welcome);
		 * remove(welcome);
		 * setResizable(false);
		 * add(game);
		 * game.setVisible(true);
		 * 
		 * 
		 * 
		 * }
		 * });
		 * 
		 * if(welcome.isVisible()==false) {
		 * 
		 * }
		 * }
		 * 
		 */

	}

	public static void main(String args[]) throws IOException {

		InitGame initgame = new InitGame();
		initgame.setVisible(true);
		initgame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		initgame.setBackground(Color.black);

		initgame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}





