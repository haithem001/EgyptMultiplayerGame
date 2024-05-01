import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class INIT_SERVER extends JFrame implements ActionListener {
    // JTextField
    static JTextField t;

    // JFrame
    static JFrame f = new JFrame("textfield");

    // JButton
    static JButton b= new JButton("submit");

    // label to display text
    static JLabel l= new JLabel("nothing entered");

    // default constructor
     INIT_SERVER() {
     }
    public static void text(){
         INIT_SERVER te = new INIT_SERVER();
         b.addActionListener(te);
         t= new JTextField(16);
         JPanel p = new JPanel();
         p.add(t);
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
        if (s.equals("submit")) {
            // set the text of the label to the text of the field
            f.dispose();

            l.setText(t.getText());
            LaunchGame server = new LaunchGame();
            server.setSize(this.getSize());
            server.setVisible(true);
            server.setBackground(Color.black);

            // set the text of field to blank
            t.setText("  ");
        }
    }
}





