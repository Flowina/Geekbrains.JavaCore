package Lesson1;

public class Main {
//  1.Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса.
//  Эти классы должны уметь бегать и прыгать (методы просто выводят информацию о действии в консоль).
//  2.Создайте два класса: беговая дорожка и стена, при прохождении через которые, участники должны выполнять
//  соответствующие действия (бежать или прыгать), результат выполнения печатаем в консоль (успешно пробежал,
//  не смог пробежать и т.д.).
//  3.Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти этот набор препятствий.
//4.* У препятствий есть длина (для дорожки) или высота (для стены), а участников ограничения на бег и прыжки.
// Если участник не смог пройти одно из препятствий, то дальше по списку он препятствий не идет.

    public static void main(String[] args) {
        task1();
        System.out.println("===================================");
        task2();
    }

    private static void task1() {
        Participant[] participants = {
                new Cat("Cat"),
                new Human("Bob"),
                new Robot("Robot"),
        };

        Obstacle[] obstacles = {
                new RunTrack(500),
                new Wall(2),

        };
        for (Participant c : participants) {
            for (Obstacle o : obstacles) {
                o.doIt(c);
                if (!c.isOnDistance()) {
                    break;
                }
            }
        }
    }

    private static void task2() {
        for (DayOfWeek day : DayOfWeek.values()) {
            System.out.println(day + ": " + getWorkingHours(day));
        }
    }

    private static String getWorkingHours(DayOfWeek dayOfWeek) {
        return dayOfWeek.IsWorkingDay()
                ? String.format("Осталось %d рабочих часов", dayOfWeek.gotHour())
                : "Сегодня выходной";
    }
}
