package Lesson1;

public class RunTrack implements Obstacle {
    private int dist;

    public RunTrack(int dist) {
        this.dist = dist;
    }
    @Override
    public void doIt(Participant c){
        c.run(dist);
    }
}
