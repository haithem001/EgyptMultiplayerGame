import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class PlayerFrame extends JFrame {
    private int width, height;
    private Container contentPane;
    private PlayerSprite me;
    private PlayerSprite enemey;
    private DrawingComponent dc;
    private Timer animationTimer;
    private boolean up, down, left, right;
    private Socket socket;
    private int playerID;
    private ReadFromServer rfsRunnable;
    private WriteToServer wsRunnable;
    private Scanner sc=new Scanner(System.in);
    private String msg = "";
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public PlayerFrame(int width, int height) {
        this.width = width;
        this.height = height;
        up = false;
        down = false;
        left = false;
        right = false;
    }
    public void setUpGUI(){
        contentPane = getContentPane();
        this.setTitle("Player#"+playerID);
        contentPane.setPreferredSize(new Dimension(width, height));
        createSprites();
        dc = new DrawingComponent();
        contentPane.add(dc);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        setUpAnimationTimer();
        setUpKeyListener();
    }
    private void createSprites(){
        if (playerID == 1) {
            me = new PlayerSprite(100, 400, 50, Color.BLUE);
            enemey = new PlayerSprite(490, 400, 50, Color.RED);
        }else{
            enemey = new PlayerSprite(100, 400, 50, Color.BLUE);
            me = new PlayerSprite(490, 400, 50, Color.RED);
        }
    }
    private void setUpAnimationTimer(){
        int interval = 10;
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double speed = 5;
                if(up) {
                    me.moveV(-speed);
                }else if (down){
                    me.moveV(speed);
                }else if (left){
                    me.moveH(-speed);
                }else if (right){
                    me.moveH(speed);
                }
                dc.repaint();
            }
        };
        animationTimer = new Timer(interval, al);
        animationTimer.start();
    }
    private void setUpKeyListener(){
        KeyListener kl = new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        up = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        right = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        left = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        down = true;
                        break;
                }
            }
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        up = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        right = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        left = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        down = false;
                        break;
                }
            }
        };
        contentPane.addKeyListener(kl);
        contentPane.setFocusable(true);
    }
    private void connectToServer(){
        try{
            socket = new Socket("localhost",45371);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            playerID = in.readInt();
            System.out.println("You are player#"+playerID);
            System.out.println(socket.getInetAddress().getHostAddress());
            if(playerID ==1){
                System.out.println("Waiting for player #2 to connects...");
            }
            rfsRunnable = new ReadFromServer(in);
            wsRunnable = new WriteToServer(out);
            rfsRunnable.waitForStartMsg();
        }catch (IOException ex){
            System.out.println("IOException from connectToServer()");
        }
    }
    private class DrawingComponent extends JComponent{
        public void paintComponent(Graphics g){
            Graphics2D g2d = (Graphics2D)g;
            me.draw(g2d);
            enemey.draw(g2d);
        }
    }
    private class ReadFromServer implements Runnable{
        private DataInputStream in;
        public ReadFromServer(DataInputStream in){
            this.in = in;
            System.out.println("Reading from server Runnable created");
        }
        public void run(){
            try {
                while (true){
                    if(enemey != null) {
                        enemey.setX(in.readDouble());
                        enemey.setY(in.readDouble());
                    }
                }
            }catch (IOException e){
               System.out.println("IOException in ReadFromServer");
            }
        }
        public void waitForStartMsg(){
            try {
                String startMsg = in.readUTF();
                System.out.println("Message from server : "+startMsg);
                Thread readThread = new Thread(rfsRunnable);
                Thread writeThread = new Thread(wsRunnable);
                Thread sendmsgThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true){
                            msg = sc.nextLine();
                            setMsg(msg);
                            try{
                                Thread.sleep(25);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }

                    }
                });
                sendmsgThread.start();
                readThread.start();
                writeThread.start();
            }catch (IOException e){
                System.out.println("IOException in ReadFromServer");
            }
        }
    }

    private class WriteToServer implements Runnable{
        private DataOutputStream out;
        public WriteToServer(DataOutputStream out){
            this.out = out;
            System.out.println("Writing to server Runnable created");
        }
        public void run(){
            try {
                while(true){
                    if (me != null) {
                        out.writeDouble(me.getX());
                        out.writeDouble(me.getY());
                        out.flush();
                    }
                    try {
                        Thread.sleep(25);
                    }catch (InterruptedException ex){
                        System.out.println("InterruptedException in WriteToServer");
                    }
                }
            }catch (IOException e){
                System.out.println("IOException in WriteToServer");
            }
        }
    }
    public static void main(String[] args) {
        PlayerFrame frame = new PlayerFrame(800, 600);
        frame.connectToServer();
        frame.setUpGUI();
    }
}
