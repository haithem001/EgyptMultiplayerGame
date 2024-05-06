import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Join_Room extends JFrame implements ActionListener {
    static JTextField PORT = new JTextField(5);
    static JTextField NICKNAME = new JTextField(5);
    static JTextField Ip = new JTextField(5);

    // JFrame
    static JFrame f = new JFrame("             textfield");


    // JButton
    static JButton j = new JButton("join");

    // label to display text
    static JLabel l = new JLabel("\t\t\t                      Port");

    static JLabel l1 = new JLabel("\t\t\t                  Nickname");
    static JLabel l2 = new JLabel("\t\t\t                 IP Address");

    // default constructor
    Join_Room() {

    }

    public static void text() {

        Join_Room te = new Join_Room();

        j.addActionListener(te);
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(5, 1, 0, 0));
        p.setBackground(Color.black);
        l.setForeground(Color.orange);
        l.setFont(new Font("Serif", Font.PLAIN, 30));
        l1.setForeground(Color.orange);
        l1.setFont(new Font("Serif", Font.PLAIN, 30));
        l2.setForeground(Color.orange);
        l2.setFont(new Font("Serif", Font.PLAIN, 30));
        p.add(l);
        PORT.setFont(new Font("Serif", Font.BOLD, 30));
        PORT.setBackground(Color.darkGray);
        PORT.setForeground(Color.white);
        PORT.setHorizontalAlignment(JTextField.CENTER);
        PORT.setBorder(new LineBorder(Color.GRAY, 4));



        p.add(PORT);
        p.add(l2);
        Ip.setFont(new Font("Serif", Font.BOLD, 30));
        Ip.setBackground(Color.darkGray);
        Ip.setForeground(Color.white);
        Ip.setHorizontalAlignment(JTextField.CENTER);
        Ip.setBorder(new LineBorder(Color.GRAY, 4));



        p.add(Ip);
        p.add(l1);
        NICKNAME.setFont(new Font("Serif", Font.BOLD, 30));
        NICKNAME.setBackground(Color.darkGray);
        NICKNAME.setForeground(Color.white);
        NICKNAME.setHorizontalAlignment(JTextField.CENTER);
        NICKNAME.setBorder(new LineBorder(Color.GRAY, 4));



        p.add(NICKNAME);

        j.setFont(new Font("Serif", Font.BOLD, 50));
        j.setBackground(Color.black);

        j.setForeground(Color.white);
        j.setHorizontalAlignment(JTextField.CENTER);
        j.setBorder(new LineBorder(Color.GRAY, 4));
        p.add(j);

        p.setLayout(new GridLayout(7,1));
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
