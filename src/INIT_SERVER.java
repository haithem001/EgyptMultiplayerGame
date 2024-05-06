import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class INIT_SERVER extends JFrame implements ActionListener {
    // JTextField


    static JTextField PORT=new JTextField(16);
    static JTextField NICKNAME= new JTextField(16);

    // JFrame
    static JFrame f = new JFrame("textfield");


    // JButton
    static JButton j= new JButton("join");

    // label to display text
    static JLabel l= new JLabel("Port:");
    static JLabel l1= new JLabel("Nickname:");

    // default constructor
     INIT_SERVER() {

     }
    public static void text(){

         INIT_SERVER te = new INIT_SERVER();

         j.addActionListener(te);
         JPanel p = new JPanel();
         p.setLayout(new GridLayout(5,1,0,0));
         p.setBackground(Color.black);
        l.setForeground(Color.orange);
        l.setFont(new Font("Serif", Font.PLAIN, 30));
        l1.setForeground(Color.orange);
        l1.setFont(new Font("Serif", Font.PLAIN, 30));

         p.add(l);
        PORT.setFont(new Font("Serif", Font.BOLD, 30));
        PORT.setBackground(Color.darkGray);
        PORT.setForeground(Color.white);
        PORT.setHorizontalAlignment(JTextField.CENTER);
        PORT.setBorder(new LineBorder(Color.GRAY, 4));
         p.add(PORT);
        NICKNAME.setFont(new Font("Serif", Font.BOLD, 30));
        NICKNAME.setBackground(Color.darkGray);
        NICKNAME.setForeground(Color.white);
        NICKNAME.setHorizontalAlignment(JTextField.CENTER);
        NICKNAME.setBorder(new LineBorder(Color.GRAY, 4));
         p.add(l1);

         p.add(NICKNAME);
        j.setFont(new Font("Serif", Font.BOLD, 50));
        j.setBackground(Color.black);

        j.setForeground(Color.white);
        j.setHorizontalAlignment(JTextField.CENTER);
        j.setBorder(new LineBorder(Color.GRAY, 4));
         p.add(j);


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
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                GameServer gs = new GameServer(Integer.parseInt(PORT.getText()));
           // Set the port first
                gs.acceptConnection(); // Then accept the connection
                try{
                    Thread.sleep(5);
                }catch (InterruptedException e){
                    System.out.println("Server Error");
                }
            }
        });

        t.start();
        LaunchGame server = null;
        server = new LaunchGame(NICKNAME.getText(),"localhost", Integer.parseInt(PORT.getText()));
        System.out.println(NICKNAME.getText());
        server.setSize(this.getSize());
        server.setVisible(true);
        server.setBackground(Color.black);
    }
}






}






