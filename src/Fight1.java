import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JFrame;
public class Fight1 extends Game {
		
	Fight1(){
		this.initFight();
		this.initMap();

	}
	public void initFight(){
	

		setBackground(Color.black);
		setFocusable(true);

		hud = new HUD();
		hud.setX(90);
		hud.setY(680);


	}
	
	public void initMap() {

	}
	
	

	
}
