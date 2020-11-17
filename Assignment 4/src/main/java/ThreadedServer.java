import java.net.*;
import java.io.*;
import java.util.*;


public class ThreadedServer extends Thread {
  private Socket conn;
  private int id;
  private String buf[] = { "The Object class also has support for wait",
      "If the timer has expired, the thread continues", "This call can cause some overhead in programs",
      "Notify signals a waiting thread to wake up", "Wait blocks the thread and releases the lock" };

  public ThreadedServer(Socket sock, int id) {
    this.conn = sock;
    this.id = id;
  }

  public void run() {

    StringList strings = new StringList();

    Performer performer = new Performer(conn, strings);
    performer.doPerform();

    
  }

  public static void main(String args[]) throws IOException {
    Socket sock = null;
    int id = 0;

    try {
      if (args.length != 1) {
        System.out.println("Usage: gradle ThreadedServer --args=<port num>");
        System.exit(1);
      }
      int portNo = Integer.parseInt(args[0]);
      if (portNo <= 1024)
        portNo = 8888;
      ServerSocket serv = new ServerSocket(portNo);
      while (true) {
        System.out.println("Threaded server waiting for connects on port " + portNo);
        sock = serv.accept();
        System.out.println("Threaded server connected to client-" + id);
        // create thread
        ThreadedServer myServerThread = new ThreadedServer(sock, id++);
        // run thread and don't care about managing it
        myServerThread.start();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (sock != null) sock.close();
    }
  }
}
