package Lesson3;

import java.util.HashMap;
import java.util.HashSet;

/*
 Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
 В этот телефонный справочник с помощью метода add() можно добавлять записи. С помощью метода get() искать
 номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
 тогда при запросе такой фамилии должны выводиться все телефоны.
* */
public class PhoneBook {
    private HashMap<String, HashSet<String>> phoneBook;

    public PhoneBook() {
        phoneBook = new HashMap<>();
    }

    public void add(String name, String phone) {
        if (!phoneBook.containsKey(name)) {
            phoneBook.put(name, new HashSet<>());
        }
        HashSet<String> phones = phoneBook.get(name);
        phones.add(phone);
    }

    public String[] get(String name) {
        if (phoneBook.containsKey(name)) {
            HashSet<String> phones = phoneBook.get(name);
            return phones.toArray(new String[phones.size()]);
        }
        return new String[0];
    }
}
