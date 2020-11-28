package raft;

public class HeartBeat {
    public static void HeartBeat(Server leader){
        String newMsg = leader.id+"-"+leader.currentTerm+"-0-";
        leader.broadCast(newMsg.getBytes());
        leader.lHeartBeat = System.currentTimeMillis();
    }
}
