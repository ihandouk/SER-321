package raft;
import java.util.Random;

public class leaderTimer {
    
    public static float leaderTimer(int min, int max){
        Random rand = new Random();
        double random = min + rand.nextDouble() * (max - min);
        return (float) random;
    }
}
