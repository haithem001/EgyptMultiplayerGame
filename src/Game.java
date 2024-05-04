import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Game extends JPanel implements ActionListener{
    public Dude dude1;
    public Dude dude;
    public int playerID;
    private Socket socket;
    private ReadFromServer rfsRunnable;
    private WriteToServer wsRunnable;
    private Container contentPane;

    public int MouseX, MouseY;
    public HUD hud;
    private Timer timer;
    private int mapAnimPos;
    private int mapAnimCount;
    private int MAP_ANIM_DELAY;
    private int MAP_ANIM_COUNT;
    private int mapAnimDir;
    private int DELAY = 60;
    private final int DUDE_ANIM_DELAY = DELAY * 15;
    private int dudeAnimCount = DUDE_ANIM_DELAY;
    private int dudeAnimDir = 1;
    private int dudeAnimPos = 0;
    private int dudeAnimCount1 = DUDE_ANIM_DELAY;

    private int dudeAnimDir1 = 1;
    private int dudeAnimPos1 = 0;



    private final int platform = 850;
    private final List<BLOCK> BLOCKS = new ArrayList<>();
    private final List<ALIEN> ALIENS = new ArrayList<>();


    public Game() {

        BLOCKS.add(new BLOCK(600, 861, true, true));
        //!BASE BLOCK[0]
        BLOCKS.get(0).BODY.setLocation(600, 861);
        BLOCKS.get(0).BODY.setBounds(600, 861, 70, 70);
        initGame();



    }




    public void initGame() {


        connectToServer();
        createSprites();

        this.setFocusable(true);

        setBackground(Color.black);


        setUpKeyListener();





        MouseEvents();
        timer = new Timer(DELAY, this);
        timer.start();
    }


    private void setUpKeyListener() {
        KeyListener kl = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                int key = e.getKeyCode();
                if (key == KeyEvent.VK_F) {

                    dude.setchoosiness(!dude.getchoosiness());

                }
                if (key == KeyEvent.VK_Q) {
                    dude.setStopWalk(false);
                    dude.setDx(-10);

                }

                if (key == KeyEvent.VK_D) {
                    dude.setStopWalk(true);
                    dude.setDx(10);
                }
                if (key == KeyEvent.VK_SPACE) {
                    if(dude.on_ground){
                        dude.JUMP = true;

                    }


                }


            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_Q) {
                    dude.setDx(0);
                    dude.setStopWalk(false);
                }
                if (key == KeyEvent.VK_SPACE) {


                }
                if (key == KeyEvent.VK_D) {
                    dude.setDx(0);
                    dude.setStopWalk(true);
                }


            }


        };
        this.addKeyListener(kl);
    }



    private void createSprites(){
            if (playerID==1){
                dude = new Dude();
                dude.setX(200);
                dude.setY(750);
                dude1 = new Dude();
                dude1.setX(400);
                dude1.setY(750);

            }
            else {
                dude = new Dude();
                dude.setX(400);
                dude.setY(750);
                dude1 = new Dude();
                dude1.setX(200);
                dude1.setY(750);
            }





    }




    private void MapAnim() {

        mapAnimCount--;

        if (mapAnimCount <= 0) {

            mapAnimCount = MAP_ANIM_DELAY;
            mapAnimPos = mapAnimPos + mapAnimDir;

            if (mapAnimPos == (MAP_ANIM_COUNT - 1) || mapAnimPos == 0) {
                mapAnimDir = -mapAnimDir;
            }
        }

    }



    private void DrawPlayerHealth(Graphics2D g2d) {
        int i=50;
        for (int x = 0; x < dude.getHealth();x++) {
            g2d.drawImage(dude.getHEART(),  i, this.getHeight()-50, this);
            i+=50;

        }
    }

    private void Build(int x,int y){

            for (BLOCK B : BLOCKS) {
                if (x < B.getX()+B.getW()  && x > B.getX() &&
                        y <  B.getY() && y > B.getY() - 70 ) {
                    if (dude.getX() >B.getY()-80 || dude.getX()<B.getY()+B.getW()+80){

                        BLOCKS.add(new BLOCK(B.getX(), B.getY()-70, false, true));

                    }

                }
                if (x < B.getX()  && x > B.getX()-B.getW() &&
                        y < B.getH() + B.getY() && y > B.getY() ) {

                    BLOCKS.add(new BLOCK(B.getX()-B.getW(), B.getY(), false, true));





                }
                if (x < B.getX()+ 140 && x > B.getX()+B.getW() &&
                        y < B.getH() + B.getY() && y > B.getY()) {
                    BLOCKS.add(new BLOCK(B.getX()+B.getW(), B.getY(), false, true));

                }


        }

    }
    private void MouseEvents() {
        this.addMouseListener(new MouseListener() {

            @Override
            public void mousePressed(MouseEvent e) {


                int x = e.getX();
                int y = e.getY();
                if(!dude.isChoosiness()){
                    if (dude.Bullets.size()<3){
                        dude.Shoot(MouseX,MouseY);

                    }
                }





            }

            @Override
            public void mouseClicked(MouseEvent e) {
                final Point pos = e.getPoint();
                int x = pos.x;
                int y = pos.y;


                if(dude.isChoosiness()){
                    Build(x,y);
                }





            }

            @Override
            public void mouseReleased(MouseEvent e) {


            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                

            }


        });
        this.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {


            }

            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                MouseX = x;
                MouseY = y;

            }

        });
    }

    private void DrawBase(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(BLOCKS.get(0).getBaseImage(), (int) BLOCKS.get(0).BODY.getX(), (int) BLOCKS.get(0).BODY.getY(), this);
    }

    private void DrawBlock(Graphics g, BLOCK B) {
        Graphics2D g2d = (Graphics2D) g;
        Color c;
        g2d.drawImage(B.getImage(0), (int) B.BODY.getX(), (int) B.BODY.getY(), this);
		/*if(B.isBuilded()==false){




			switch (B.BlockAnimPos){
				case 1:
					g2d.drawImage(B.getImage(2), (int) B.BODY.getX(), (int) B.BODY.getY(), this);

					break;
				case 2:
					g2d.drawImage(B.getImage(1), (int) B.BODY.getX(), (int) B.BODY.getY(), this);
					break;
				case 3:
					g2d.drawImage(B.getImage(0), (int) B.BODY.getX(), (int) B.BODY.getY(), this);


					break;
				default:
					g2d.drawImage(B.getImage(2), (int) B.BODY.getX(), (int) B.BODY.getY(), this);
					break;

			}
		}
		else{

		}

*/


    }


    private void DrawHUD(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // HUD placement
        g2d.setFont(new Font("PixellettersFull", Font.BOLD, 30));
        g2d.setColor(new Color(132, 117, 119));
        g2d.drawImage(hud.getImage(), hud.getX(), hud.getY(), this);
        for (int i = 1; i <= dude.gethealth(); i++) {
            g2d.drawImage(hud.getHeartImage(), hud.getX() + i * 25, hud.getY() + 20, this);

        }
        g2d.drawImage(hud.getItemsImage(), hud.getX() + 140, hud.getY() + 16, this);

        // COINS ANIM
        switch (mapAnimPos) {

            case 0:
                g2d.drawImage(hud.getCoinImage(0), hud.getX() + 25, hud.getY() + 50, this);
                break;
            case 1:
                g2d.drawImage(hud.getCoinImage(1), hud.getX() + 25, hud.getY() + 50, this);
                break;

        }


        g2d.drawString(hud.getCoins(), hud.getX() + 55, hud.getY() + 67);
        g2d.drawString(":", hud.getX() + 43, hud.getY() + 65);

        // Bag and Inventory


    }


    private void DrawPlayer(int playerID, Graphics g) {

        Graphics2D playerG = (Graphics2D) g;
        playerG.drawImage(dude1.getImage(dude1.getDudeAnimPos()), dude1.getX(), dude1.getY(), this);
        playerG.drawImage(dude.getImage(dude.getDudeAnimPos()), dude.getX(), dude.getY(), this);


    }

    private void Checkill(){
        if (!ALIENS.isEmpty()&& !dude.Bullets.isEmpty()) {
            for (ALIEN A:ALIENS){
                for (Bullet B:dude.Bullets){
                        if(B.x+B.getW()<A.getX()+A.getW() && B.x>A.getX() && B.y+B.getH()<A.getY()+A.getH() && B.y>A.getY()){
                            B.setVisible(false);
                            A.setAlive(false);

                        }
                    }

                }

        }

    }
    private void CheckDestroy(){
        for(ALIEN A:ALIENS) {
            for (AlienBullet B : A.bullets) {
                for (BLOCK Block : BLOCKS) {
                    if (B.x + B.getW() < Block.getX() + Block.getW() && B.x > Block.getX() && B.y + B.getH() < Block.getY() + Block.getH() && B.y > Block.getY()) {
                            Block.setHealth(Block.getHealth() - 1);
                            B.setVisible(false);

                    }
                    if (Block.getHealth()<0){
                        Block.setVisible(false);
                    }


                }
                BLOCKS.removeIf(C -> !C.isVisible());
                if (B.x + B.getW() < dude.getX() + dude.getWidth() && B.x > dude.getX() && B.y + B.getH() < dude.getY() + dude.getHeight() && B.y > dude.getY()) {
                    dude.setHealth(dude.getHealth()-1);
                    B.setVisible(false);

                }
            }
            A.bullets.removeIf(D -> !D.getVisible());


        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        for (BLOCK B : BLOCKS) {
            if (B.isBuilded()) {
                B.BODY.setLocation(B.getX(), B.getY());
                B.BODY.setBounds(B.getX(), B.getY(), 70, 70);
            }
        }

        if (BLOCKS.size()>5){
            if(ALIENS.isEmpty()){

                ALIENS.add(new ALIEN((int) (Math.random()*getWidth()), -40));
            }
        }

        dude.move(getWidth(), getHeight(), BLOCKS, platform);
        dude1.move(getWidth(), getHeight(), BLOCKS, platform);



        Checkill();



        if (!dude.Bullets.isEmpty())
        {
            for (Bullet B:dude.Bullets){
                B.move(getWidth(),getHeight());
            }
        }
        for (ALIEN A : ALIENS) {
            if (A.getDy()==0){
                if (A.bullets.isEmpty()){
                    if (Math.abs(A.getX()-dude.getX())>Math.abs(A.getX()-BLOCKS.get(0).getY())){
                        A.shoot(A.getX(),A.getY(),BLOCKS.get(0).getX(),BLOCKS.get(0).getY());

                    }
                    else{
                        A.shoot(A.getX(),A.getY(),dude.getX(),dude.getY());
                    }
                }
            }
        }
        for (ALIEN A : ALIENS){
            if (!A.bullets.isEmpty()){
                for (AlienBullet B:A.bullets){
                    B.move(getWidth());
                }
            }
        }
        CheckDestroy();


        if (!ALIENS.isEmpty()){
            for (ALIEN A:ALIENS){
                A.move();

            }
        }

        dude.Bullets.removeIf(B -> !B.getVisible());




        ALIENS.removeIf(A -> !A.isAlive());



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
                    if(dude1 != null) {

                        dude1.setX(in.readInt());
                        dude1.setY(in.readInt());
                        dude1.setStopWalk(in.readBoolean());


                    }
                    try {
                        Thread.sleep(25);
                    }catch (InterruptedException ex){
                        System.out.println("InterruptedException in WriteToServer");
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
                    if (dude != null) {
                        out.writeInt(dude.getX());
                        out.writeInt(dude.getY());
                        out.writeBoolean(dude.getStopWalk());

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

        public void paintComponent(Graphics g){

            //System.out.println(BLOCKS.size());
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;






            float XLEN = MouseX - (dude.getX() + (dude.getWidth() / 2));
            float YLEN = MouseY - (dude.getY() + (dude.getHeight() / 2));


            double length = Math.sqrt(XLEN * XLEN + YLEN * YLEN);


        /*while (i<this.getWidth()+70){
            while(j<this.getHeight()+70) {
                g2d.drawImage(BLOCKS.get(0).getImage(0), i, j, this);
            }

        }*/
            // board
            if (dude.isWalking()) {

                dude.doAnim();
            }
            if(dude1.isWalking()){
                dude1.doAnim();
            }



            //MapAnim();
            DrawPlayer(1,g);
            DrawPlayer(2,g);

            DrawPlayerHealth(g2d);
            if (!ALIENS.isEmpty()){
                for (ALIEN alien : ALIENS) {

                    g2d.drawImage(alien.getImage(), alien.getX(),alien.getY(), this);

                }
            }

            for (BLOCK B : BLOCKS) {
            /*if (B.isBuilded()) {
                if ((dude.getX() + (dude.getWidth() / 2)) > B.getX() - (4 * (dude.getWidth() / 2)) && ((dude.getX() + (dude.getWidth() / 2) < B.getX() - (dude.getWidth() / 2)))) {
                            g2d.drawImage(B.getEmptyBlockImg(), (int) B.getX()-70, (int) B.getY(), this);
                            g2d.drawImage(B.getEmptyBlockImg(), (int) B.getX() , (int) B.getY()-70, this);
                }
                if ((dude.getX() + (dude.getWidth() / 2) < B.getX() + B.getW() + 2 * (dude.getWidth() / 2)) && ((dude.getX() + (dude.getWidth() / 2) > B.getX() + B.getW()))&&dude.getY()+dude.getHeight()<B.getY()) {
                    g2d.drawImage(B.getEmptyBlockImg(), (int) B.getX() + 70, (int) B.getY(), this);
                    g2d.drawImage(B.getEmptyBlockImg(), (int) B.getX() , (int) B.getY()-70, this);
                }
               g2d.drawImage(B.getImage(0), (int) B.BODY.getX(), (int) B.BODY.getY(), this);
            }*/
                g2d.drawImage(B.getImage(B.getHealth()), (int) B.BODY.getX(), (int) B.BODY.getY(), this);
                DrawBase(g);

            }
            for (ALIEN alien : ALIENS) {
                if (!alien.bullets.isEmpty()){
                    for (AlienBullet X:alien.bullets){
                        Color c;
                        c = new Color(255, 85, 60);
                        g2d.setColor(c);
                        g2d.fillOval(X.getX(), X.getY(), 10, 10);

                    }
                }
            }
            if (!dude.Bullets.isEmpty()){
                for (Bullet X:dude.Bullets) {
                    Color c;
                    c = new Color(255, 255, 255);
                    g2d.setColor(c);
                    g2d.fillOval(X.getX(), X.getY(), 10, 10);

                }
            }
            if (dude.isChoosiness()){
                dude.Axe.setX((int) (((dude.getX() + dude.getWidth() / 2) + XLEN * 40 / length) ));
                dude.Axe.setY((int) ((dude.getY() + dude.getHeight() / 2) + YLEN * 40 / length) );
                double angle = Math.atan2(YLEN, XLEN) - Math.PI / 2;
                g2d.rotate(angle, dude.Axe.getX()+10, dude.Axe.getY());
                g2d.drawImage(dude.Axe.getImage(), dude.Axe.getX(), dude.Axe.getY(), this);
            }
            else{
                dude.BOMBA.setX((int) (((dude.getX() + dude.getWidth() / 2) + XLEN * 40 / length) ));
                dude.BOMBA.setY((int) ((dude.getY() + dude.getHeight() / 2) + YLEN * 40 / length) );
                double angle = Math.atan2(YLEN, XLEN) - Math.PI / 2;
                g2d.rotate(angle, dude.BOMBA.getX()+10, dude.BOMBA.getY());
                g2d.drawImage(dude.BOMBA.getImage(), dude.BOMBA.getX(), dude.BOMBA.getY(), this);



            }



            this.repaint();


            Toolkit.getDefaultToolkit().sync();
        }

    public class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            if (key == KeyEvent.VK_F) {

                dude.setchoosiness(!dude.getchoosiness());

            }
            if (key == KeyEvent.VK_Q) {

                dude.setDx(-10);

            }

            if (key == KeyEvent.VK_D) {

                dude.setDx(10);
            }
            if (key == KeyEvent.VK_SPACE) {
                if(dude.on_ground){
                    dude.JUMP = true;

                }


            }


        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_Q) {
                dude.setDx(0);
                dude.setStopWalk(false);
            }
            if (key == KeyEvent.VK_SPACE) {


            }
            if (key == KeyEvent.VK_D) {
                dude.setDx(0);
                dude.setStopWalk(true);
            }


        }


    }


}
