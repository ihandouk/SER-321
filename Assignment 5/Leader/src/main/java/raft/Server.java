package raft;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static raft.leaderTimer.leaderTimer;

public class Server{
    public DatagramSocket rSocket;
    public DatagramSocket sSocket;
    public InetAddress IPAddress;
    public int id;
    public int leaderId;
    public int voteCount;
    public int state;
    public int currentTerm;
    public int voteGranted;
    public long sTime;
    public long lHeartBeat;

    float initialTimeOut = leaderTimer(150, 350);

    public Server(int newId)throws SocketException, UnknownHostException{
        this.id = newId;
        this.IPAddress = InetAddress.getByName("localhost");
        this.rSocket = new DatagramSocket(id, IPAddress);
        this.sSocket = new DatagramSocket();
        this.state = Cluster.FOLLOWER;
        this.leaderId = 0;
        this.currentTerm = 0;
        this.voteCount = 0;
        this.voteGranted = 0;
        System.out.println("SERVER "+id+" IS ON WITH: "+ initialTimeOut);
    }

    public void sendMessage(int port, byte sendData[]){
        DatagramPacket Packet = new DatagramPacket(sendData, sendData.length, IPAddress, port);

        try{
            sSocket.send(Packet);
        }catch(IOException ex){
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String receiveMessage(){
        byte receiveData[] = new byte[1024];
        String Received;
        DatagramPacket Packet2 = new DatagramPacket(receiveData, receiveData.length);
        float timeOut;

        if(id == leaderId){
            timeOut = 1;
        }else{
            timeOut = leaderTimer(150, 350);

        }

        try{
            if((System.currentTimeMillis() - Cluster.INITIAL) < 500){
                rSocket.setSoTimeout((int)initialTimeOut);
            }else{
                rSocket.setSoTimeout((int)timeOut);
            }
            rSocket.receive(Packet2);
            Received = new String(Packet2.getData());
            return Received;
        }catch(IOException ex){
            if(leaderId != id){
                if(state == Cluster.FOLLOWER){
                    newElection();
                }else if(state == Cluster.CANDIDATE){
                if( (System.currentTimeMillis() - sTime) > (long)(leaderTimer(150, 350)) ) // timeout à espera de voto
                    newElection();
                    }
                }
            }
            return null;
        }
        public void broadCast(byte sendData[]){
            for(int i = Cluster.PORT; i<(Cluster.PORT+Cluster.SIZE); i++){
                if(i != id){
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, i);
                    try{
                        sSocket.send(sendPacket);
                    }catch(IOException ex){
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        private void newElection(){
            state = Cluster.CANDIDATE;
            currentTerm += 1;
            voteCount = 1;
            voteGranted = currentTerm;
            leaderId = id;
            sTime = System.currentTimeMillis();
            String newMsg = id+"-"+currentTerm+"-2-";
            broadCast(newMsg.getBytes());
            System.out.print(""+id+" Started and Election! Current Term: "+currentTerm);
        }
    }
