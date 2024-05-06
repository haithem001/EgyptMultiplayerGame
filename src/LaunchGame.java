import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LaunchGame extends JFrame  implements ActionListener{
	public Messenger messenger;
	public Game game;
    LaunchGame(String nickname, String ip, int port){

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
			//this.setUndecorated(true);

		this. setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.black);
		game = new Game(nickname, ip, port);
		game.setFocusable(true);

		System.out.println("LaunchGame: "+ nickname);
		this.setVisible(true);




		messenger=new Messenger(game);

		this.add(messenger);

		this.add(game);



		this.messenger.sendmsgThread.start();


		this.pack();






}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	}
}


