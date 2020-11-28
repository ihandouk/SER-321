package raft;

public class Cluster {
    static final int FOLLOWER = 0;
    static final int LEADER = 1;
    static final int CANDIDATE = 2;
    static final int SIZE = 3;
    static final int PORT = 10;
    static final long HEARTBEATTIMEOUT = 50;
    static final long INITIAL = System.currentTimeMillis();
}
