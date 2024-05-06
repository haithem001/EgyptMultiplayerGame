import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Messenger extends JPanel implements ActionListener {
    private JTextField textField;
    private JTextArea Messages;
    private JScrollPane scroll;
    private Border border;
    private JPanel panel;
    private String message;

    private Image messageimage;
    public  Thread sendmsgThread;
    private JPanel panel1,panel2;
    private JFrame MessageFrame;
    Game game;
    public Messenger(Game game){
        this.game=game;
        panel=new JPanel();
        MessageFrame=new JFrame();
        textField=new JTextField(1);

        panel1=new JPanel();
        panel2=new JPanel();
        Messages = new JTextArea(10, 50);
        textField = new JTextField();

        border = BorderFactory.createLineBorder(Color.BLUE, 1);
        textField.setBorder(border);
        textField.setFont(new Font("Serif",Font.BOLD,40));


        Messages.setBackground(Color.BLACK);
        Messages.setForeground(Color.white);
        Messages.append("             Chats \n");
        Messages.setEditable(false);
        Messages.setFont(new Font("Serif",Font.BOLD,40));
        textField.setText("> ");
        panel.add(textField, BorderLayout.PAGE_START);
        panel.add(Messages);
        panel.setBackground(Color.BLACK);
        panel1.setBackground(Color.BLACK);
        panel2.setBackground(Color.BLACK);
        panel.add(panel2);
        panel.add(panel1);
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new GridLayout(5,1));

        textField.setBackground(Color.BLACK);
        textField.setBorder(null);
        textField.setForeground(Color.white);

        textField.setFont(new Font("Serif", Font.PLAIN, 17));
        Messages.setFont(new Font("Serif", Font.PLAIN, 17));

        MessageFrame.add(panel);
        MessageFrame.setVisible(true);


        textField.addActionListener(this);

        sendmsgThread = new Thread(new Runnable() {



            @Override
            public void run() {

                while(true){

                    if(!game.getMsg().equals("")){

                        Messages.append(game.getNickname()+": "+game.getMsg()+"\n");


                    }
                    else if(!game.getMsg1().equals("")){

                        Messages.append(game.getNickname1()+": "+game.getMsg1()+"\n");



                    }

                    try{
                        Thread.sleep(27);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

            }
        });

    }
    private void setMessage(Game game){
        this.message=game.getMsg();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.setMsg(textField.getText());
    }
}
