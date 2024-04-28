import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener {

    public Dude dude;


    public int MouseX, MouseY;
    public HUD hud;


    private Timer timer;
    private Animations anim;
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

	/*private BLOCK[] BLOCKS = new BLOCK[]{

			new BLOCK(670, 861 ,false,false),
			new BLOCK(530, 861 ,false,false),
			new BLOCK(460, 861 ,false,false),
			new BLOCK(390, 861 ,false,false),

	};*/
    private final int platform = 850;
    private final List<BLOCK> BLOCKS = new ArrayList<>();


    public Game() {
        BLOCKS.add(new BLOCK(600, 861, true, true));
        //!BASE BLOCK[0]
        BLOCKS.get(0).BODY.setLocation(600, 861);
        BLOCKS.get(0).BODY.setBounds(600, 861, 70, 70);
        initGame();
        initMap();


    }

    private void initMap() {
    }


    public void initGame() {


        this.addKeyListener(new TAdapter());
        this.setBackground(Color.black);
        this.setFocusable(true);


        hud = new HUD();
        hud.setX(90);
        hud.setY(680);


        dude = new Dude();


        dude.setX(252);
        dude.setY(platform - dude.getHeight());


        timer = new Timer(DELAY, this);
        timer.start();

        MouseEvents();
    }

    private void doAnim(int AnimCount) {

        dudeAnimCount--;

        if (dudeAnimCount <= 0) {

            dudeAnimCount = DUDE_ANIM_DELAY;
            dudeAnimPos = dudeAnimPos + dudeAnimDir;

            if (dudeAnimPos == (AnimCount - 1) || dudeAnimPos == 0) {
                dudeAnimDir = -dudeAnimDir;
            }
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

    @Override
    public void paintComponent(Graphics g) {

        //System.out.println(BLOCKS.size());

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;






        float XLEN = MouseX - (dude.getX() + (dude.getWidth() / 2));
        float YLEN = MouseY - (dude.getY() + (dude.getHeight() / 2));

        double length = Math.sqrt(XLEN * XLEN + YLEN * YLEN);

        dude.BlockAnim();
        // board
        if (dude.isWalking() == true) {

            doAnim(2);
        }


        MapAnim();
        DrawPlayer(g);



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

            }

*/
            g2d.drawImage(B.getImage(0), (int) B.BODY.getX(), (int) B.BODY.getY(), this);
            DrawBase(g);
            if (!dude.Bullets.isEmpty()){
                for (Bullet X:dude.Bullets){
                    Color c;
                    c = new Color(255, 255, 255);
                    g2d.setColor(c);
                    g2d.fillRect(0, 931, this.getWidth(), 5);

                    g2d.drawOval(X.getX(),X.getY(),10,10);
                    this.repaint();
                }
            }
        }
        if (dude.isItemchoosen()){
            dude.Axe.setX((int) (((dude.getX() + dude.getWidth() / 2) + XLEN * 40 / length) ));
            dude.Axe.setY((int) ((dude.getY() + dude.getHeight() / 2) + YLEN * 40 / length) );
            double angle = Math.atan2(YLEN, XLEN) - Math.PI / 2;
            g2d.rotate(angle, dude.Axe.getX()+10, dude.Axe.getY());
            g2d.drawImage(dude.Axe.getImage(), dude.Axe.getX(), dude.Axe.getY(), this);
            this.repaint();
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



            }

            @Override
            public void mouseClicked(MouseEvent e) {
                final Point pos = e.getPoint();
                int x = pos.x;
                int y = pos.y;

                dude.BlockAnimPos+=1;
                if(dude.isItemchoosen()){
                    Build(x,y);
                }

                else{

                    dude.Shoot(MouseX,MouseY);
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
                if (hud.getX() + hud.getW() - 15 >= x && x >= hud.getX() + hud.getW() - 75 && y >= hud.getY() + 11
                        && y <= hud.getY() + 81) {
                    hud.SetInventoryOpen(false);
                }

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
            default:
                g2d.drawImage(hud.getCoinImage(0), hud.getX() + 25, hud.getY() + 50, this);
        }

        g2d.drawString(hud.getCoins(), hud.getX() + 55, hud.getY() + 67);
        g2d.drawString(":", hud.getX() + 43, hud.getY() + 65);

        // Bag and Inventory


    }


    private void DrawPlayer(Graphics g) {

        Graphics2D playerG = (Graphics2D) g;

        if (dude.isWalking()) {
            switch (dudeAnimPos) {
                case 0:
                    if (dude.getdx() > 0) {
                        playerG.drawImage(dude.getImage(0), dude.getX(), dude.getY(), this);
                        break;
                    } else {

                        playerG.drawImage(dude.getImage(0), dude.getX(), dude.getY(), this);
                        break;
                    }

                case 1:
                    playerG.drawImage(dude.getImage(1), dude.getX(), dude.getY(), this);
                    break;

                default:
                    playerG.drawImage(dude.getImage(1), dude.getX(), dude.getY(), this);
            }

        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {


        for (BLOCK B : BLOCKS) {
            if (B.isBuilded()) {
                B.BODY.setLocation(B.getX(), B.getY());
                B.BODY.setBounds(B.getX(), B.getY(), 70, 70);
            }
        }
        dude.move(this.getWidth(), this.getHeight(), this.BLOCKS, this.platform);
        if (!dude.Bullets.isEmpty())
        {
            for (Bullet B:dude.Bullets){
                B.move();
            }
        }


    }

    public class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            dude.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            dude.keyPressed(e);
        }

    }

}
