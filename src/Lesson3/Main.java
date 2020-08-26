package Lesson3;
/*
1. Создать массив с набором слов (10-20 слов, среди которых должны встречаться повторяющиеся).
Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
Посчитать, сколько раз встречается каждое слово.
2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
 В этот телефонный справочник с помощью метода add() можно добавлять записи. С помощью метода get() искать
 номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
 тогда при запросе такой фамилии должны выводиться все телефоны.

Желательно как можно меньше добавлять своего, чего нет в задании (т.е. не надо в телефонную запись добавлять еще дополнительные поля (имя, отчество, адрес),
 делать взаимодействие с пользователем через консоль и т.д.). Консоль желательно не использовать (в том числе Scanner), тестировать просто из метода main(), прописывая add() и get().
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        task1();
        System.out.println("*******************************");
        task2();
    }

    private static void task1() {
        String[] arr = createArray();
        System.out.println(Arrays.toString(arr));
        printUnique(arr);
    }

    private static String[] createArray() {
        String[] arr = {"cat", "dog", "robot", "flower", "John Connor", "flower", "bees", "dog", "cat", "robot", "flower", "Sarah"};
        return arr;
    }

    private static void printUnique(String[] arr) {
        Map<String, Integer> map = new HashMap<>();
        for (String key :
                arr) {
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }

        map.forEach((k, v) -> {
            System.out.printf("%s: %d%n", k, v);
        });
    }

    private static void task2() {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Bill", "+79110000000");
        phoneBook.add("Bill", "+796411111111");
        phoneBook.add("Bill", "+79110000000");// duplicate
        phoneBook.add("Bill", "+888888");
        phoneBook.add("Marry", "+77777");

        String[] bill = phoneBook.get("Bill");
        System.out.println("Bill: " + Arrays.toString(bill));

        String[] marry = phoneBook.get("Marry");
        System.out.println("Marry: " + Arrays.toString(marry));

        String[] absent = phoneBook.get("John");
        System.out.println("absent: " + Arrays.toString(absent));

    }
}
