package Lesson1;

public class Wall implements Obstacle {
    private int height;

    public Wall(int height) {
        this.height = height;
    }
    @Override
    public void doIt(Participant c) {
        c.jump(height);
    }
}
