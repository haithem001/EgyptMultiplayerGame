import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Join_Room extends JFrame implements ActionListener {
    static JTextField PORT = new JTextField(16);
    static JTextField NICKNAME = new JTextField(16);
    static JTextField Ip = new JTextField(16);

    // JFrame
    static JFrame f = new JFrame("textfield");


    // JButton
    static JButton j = new JButton("join");

    // label to display text
    static JLabel l = new JLabel("Port:");
    static JLabel l1 = new JLabel("Nickname:");
    static JLabel l2 = new JLabel("IP Address:");

    // default constructor
    Join_Room() {

    }

    public static void text() {

        Join_Room te = new Join_Room();

        j.addActionListener(te);
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(5, 1, 0, 0));
        p.setBackground(Color.black);
        p.add(l);
        p.add(PORT);
        p.add(l2);
        p.add(Ip);
        p.add(l1);
        p.add(NICKNAME);

        p.add(j);


        f.add(p);
        f.setSize(300, 300);
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

            LaunchGame server = new LaunchGame(NICKNAME.getText(),Ip.getText(), Integer.parseInt(PORT.getText()));
            System.out.println(NICKNAME.getText());
            server.setSize(this.getSize());
            server.setVisible(true);
            server.setBackground(Color.black);


            // set the text of field to blank
        }
    }
}
