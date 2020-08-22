package Lesson1;

public class Human implements Participant {
    private String name;
    private int maxRunDistance;
    private int maxJumpHeight;

    private boolean onDistance;

    public Human(String name, int maxJumpHeight, int maxRunDistance) {
        this.name = name;
        this.maxJumpHeight = maxJumpHeight;
        this.maxRunDistance = maxRunDistance;
        this.onDistance = true;
    }

    public Human(String name) {
        this(name, 1, 10000);
    }

    @Override
    public void run(int dist) {
        if (dist <= maxRunDistance) {
            System.out.printf("%s успешно пробежал %d метров\n", name, dist);
        } else {
            System.out.printf("%s не смог пробежать %d метров\n", name, dist);
            onDistance = false;
        }
    }
    @Override
    public void jump( int height){
        if (height <= maxJumpHeight) {
            System.out.printf("%s успешно прыгнул на %d метров\n", name, height);
        } else {
            System.out.printf("%s не смог прыгнуть на %d метров\n", name, height);
            onDistance = false;
        }
    }
    @Override
    public boolean isOnDistance ()
    {
        return onDistance;
    }
    @Override
    public void info () {
        System.out.printf("%s %b\n", name, onDistance);
    }
}
