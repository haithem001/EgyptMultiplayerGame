import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class INIT_SERVER extends JFrame implements ActionListener {
    // JTextField
    static JTextField IP=new JTextField(16);
    static JTextField PORT=new JTextField(16);
    static JTextField NICKNAME= new JTextField(16);
    static JTextField PLAYER_ID= new JTextField(16);
    // JFrame
    static JFrame f = new JFrame("textfield");


    // JButton
    static JButton b= new JButton("submit");
    static JButton j= new JButton("join");

    // label to display text
    static JLabel l= new JLabel("nothing entered");

    // default constructor
     INIT_SERVER() {

     }
    public static void text(){

         INIT_SERVER te = new INIT_SERVER();
         b.addActionListener(te);
         j.addActionListener(te);
         JPanel p = new JPanel();
         p.setLayout(new GridLayout(5,1,0,0));
         p.setBackground(Color.black);
         p.add(IP);
         p.add(PORT);
         p.add(NICKNAME);
         p.add(PLAYER_ID);
         p.add(j);
         p.add(b);
         p.add(l);
         f.add(p);
         f.setSize(300,300);
         f.setVisible(true);

    }


         // main class

         // create a new frame to store text field and button


    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (s.equals("join")) {
            // set the text of the label to the text of the field
            f.dispose();
            l.setText(IP.getText());
            LaunchGame server = new LaunchGame();
            server.setSize(this.getSize());
            server.setVisible(true);
            server.setBackground(Color.black);
            // set the text of field to blank
            IP.setText("  ");
        }
        if (s.equals("submit")) {
            // set the text of the label to the text of the field

            l.setText(IP.getText());
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    GameServer gs = new GameServer("src/GameServer.java");
                    gs.acceptConnection();
                    try{
                        Thread.sleep(5);
                    }catch (InterruptedException e){
                        System.out.println("Server Error");
                    }
                }
            });
            t.start();



            // set the text of field to blank
            IP.setText("  ");
        }
    }
}





