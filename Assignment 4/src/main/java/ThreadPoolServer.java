import java.net.*;
import java.io.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class Worker implements Runnable {
    private Socket sock;
    ServerSocket server;
    protected int id;
    protected int sleepDelay;
    protected int loopCount;
    
    public Worker (Socket csock, ServerSocket serv, int assignedID, int sd) {
        sock = csock;
        server = serv;
        id = assignedID;
        sleepDelay = sd;
    }

    public void run() {
        StringList strings = new StringList();
        while(true){
            try{
                sock = server.accept();
                System.out.println("Threaded server connected to client-" + id);
                Performer performer = new Performer(sock, strings);
                performer.doPerform();
                sock.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        
    }
}

public class ThreadPoolServer {
    public static void main(String args[]) throws Exception {
        Socket sock = null;
        int id = 0;
        if (args.length != 3) {
          System.out.println("Expected Arguments: gradle ThreadPoolServer <workers(int)> <sleep(int)> <loop count(int)>");
          System.exit(0);
        }
        int portNo = Integer.parseInt(args[0]);
        if(portNo<= 1024)
        portNo=8888;
        int sleepDelay = 10; // default value
        int numWorkers = 25; // default value
        int loopCount = 5; // default value
        try {
            numWorkers = Integer.parseInt(args[0]);
            sleepDelay = Integer.parseInt(args[1]);
            loopCount = Integer.parseInt(args[2]);
        } catch (NumberFormatException nfe) {
            System.out.println("[workers|sleep|loop count] must be integer");
            System.exit(0);
        }finally{
            if(sock != null)
                sock.close();
        }
        ServerSocket s = new ServerSocket(portNo);
        if (numWorkers < 6) {
          numWorkers = 6;
        }
        int poolSize = numWorkers - 5;

        // lower thread pool than numWorkers;
        Executor pool = Executors.newFixedThreadPool(poolSize);

        for (int i=0; i < numWorkers; i++) {
            pool.execute(new Worker(sock, s, i, sleepDelay)); 
        }
    }
}