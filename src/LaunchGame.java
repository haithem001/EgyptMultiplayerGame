import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchGame extends JFrame  implements ActionListener{

    LaunchGame(String nickname, String ip, int port){

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//this.setUndecorated(true);


		this. setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.black);
		Game game = new Game(nickname, ip, port);

		System.out.println("LaunchGame: "+ nickname);
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


