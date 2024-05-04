import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameServer {
    private ServerSocket ss;
    private int numPlayers;
    private int maxPlayers;
    private Socket p1Socket;
    private Socket p2Socket;
    private ReadFromClient p1ReadRunnable;
    private ReadFromClient p2ReadRunnable;
    private WriteToClient p1WriteRunnable;
    private WriteToClient p2WriteRunnable;
    private List<Integer> Data1,Data2;
    private int p1x, p1y, p2x, p2y;
    private boolean dir1,dir2;
    private int recieved;
    public GameServer() {
        System.out.println("==== Game Server ====");
        numPlayers = 0;
        maxPlayers = 2;

        Data1=new ArrayList<>();
        Data1.add(300);
        Data1.add(750);


        Data2=new ArrayList<>();
        Data2.add(100);
        Data2.add(750);



        try {
            ss= new ServerSocket(45371);

        }catch (IOException e) {
            System.out.println("Server Error");
        }
    }
    public void acceptConnection() {
        try{

            System.out.println("waiting for connection...");

            while (numPlayers < maxPlayers) {
                Socket s = ss.accept();
                DataInputStream in = new DataInputStream(s.getInputStream());
                DataOutputStream out = new DataOutputStream(s.getOutputStream());
                numPlayers++;
                out.writeInt(numPlayers);
                System.out.println("Player #"+numPlayers+ "has connected");

                ReadFromClient rfc = new ReadFromClient(numPlayers, in);
                WriteToClient wr = new WriteToClient(numPlayers, out);

                if (numPlayers == 1){
                    p1Socket  = s;
                    p1ReadRunnable = rfc;
                    p1WriteRunnable = wr;
                } else {
                    p2Socket = s;
                    p2ReadRunnable = rfc;
                    p2WriteRunnable = wr;
                    p1WriteRunnable.sendStartMsg();
                    p2WriteRunnable.sendStartMsg();
                    Thread readThread1 = new Thread(p1ReadRunnable);
                    Thread readThread2 = new Thread(p2ReadRunnable);


                    readThread1.start();
                    readThread2.start();
                    Thread writeThread1 = new Thread(p1WriteRunnable);
                    Thread writeThread2 = new Thread(p2WriteRunnable);
                    writeThread1.start();
                    writeThread2.start();
                }
            }
            System.out.println("No longer connection accepted");

        }catch (IOException e){
            System.out.println("Server Error");
        }
    }


    private class ReadFromClient implements Runnable {
        private int playerID;
        private DataInputStream in;
        private ObjectInputStream inA;
        public ReadFromClient(int pid ,DataInputStream in) {
            playerID = pid;
            this.in = in;

            System.out.println("Reading from client "+playerID+" Runnable created");
        }
        String msg;
        public void run() {
            try {


                while(true){

                    if(playerID == 1){

                            Data1.set(0, in.readInt());
                            Data1.set(1, in.readInt());
                            dir1 = in.readBoolean();






                    }else{
                        Data2.set(0, in.readInt());
                        Data2.set(1, in.readInt());
                        dir2 = in.readBoolean();


                    }
                    try {
                        Thread.sleep(25);
                    }catch (InterruptedException e){
                        System.out.println("Server Error");
                    }
                }
            } catch (IOException e){
                System.out.println("Server Error");
            }
        }
    }

    private class WriteToClient implements Runnable {
        private int playerID;
        private DataOutputStream out;
        public WriteToClient(int pid ,DataOutputStream out) {
            playerID = pid;
            this.out = out;
            System.out.println("Writing To client "+playerID+" Runnable created");
        }
        public void run() {
            try {
                while (true){
                    if(playerID == 1){
                        out.writeInt(Data2.get(0));
                        out.writeInt(Data2.get(1));
                        out.writeBoolean(dir2);


                        out.flush();
                    }else {
                        out.writeInt(Data1.get(0));
                        out.writeInt(Data1.get(1));
                        out.writeBoolean(dir1);

                        out.flush();
                    }
                    try {
                        Thread.sleep(25);
                    }catch (InterruptedException e){
                        System.out.println("Server Error");
                    }
                }
            } catch (IOException e){
                System.out.println("Server Error");
            }
        }
        public void sendStartMsg(){
            try {
                out.writeUTF("We now have 2 players");
            }catch (IOException e){
                System.out.println("Server Error");
            }
        }
    }
    public static void main(String[] args){
        GameServer gs = new GameServer();

        gs.acceptConnection();


    }
}
