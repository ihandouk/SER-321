package raft;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static raft.HeartBeat.HeartBeat;
import raft.Server;

public class ServerThread implements Runnable {
    private int id;
    private Server server;

    public ServerThread(int id){
        this.id = id;

        try{
            server = new Server(this.id);
        }catch(SocketException | UnknownHostException ex){
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void run(){
        while(true){
            switch(server.state){
                case Cluster.FOLLOWER:
                    sendMessage("FOLLOWER");
                    break;
                case Cluster.LEADER:
                    sendMessage("LEADER");
                    break;
                case Cluster.CANDIDATE:
                    sendMessage("CANDIDATE");
                    break;

            }
                sendMessage(Integer.toString(server.currentTerm));
            if((server.state == Cluster.LEADER) && ((System.currentTimeMillis() - server.lHeartBeat)>Cluster.HEARTBEATTIMEOUT)){
                HeartBeat(server);
            }
        }
    }
}
