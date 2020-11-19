import java.net.*;
import java.io.*;

class Server {

    public static void main(String args[]) throws Exception {
        Socket sock = null;
        int id = 0;

        StringList strings = new StringList();

        if (args.length != 1) {
            System.out.println("Usage: gradle Server --args=<port num>");
            System.exit(0);
        }
        int portNo = Integer.parseInt(args[0]);
        if(portNo <= 1024)
        portNo = 8888;
        ServerSocket server = new ServerSocket(portNo);
        System.out.println("Server Started...");
        while (true) {
            System.out.println("Accepting a Request...");
            System.out.println("Threaded server waiting for connects on port " + portNo);
            sock = server.accept();
            System.out.println("Threaded server connected to client-" + id);

            Performer performer = new Performer(sock, strings);
            performer.doPerform();
        }
    }
}
