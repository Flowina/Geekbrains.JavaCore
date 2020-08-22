package Lesson2;

import java.util.Arrays;

//1. Напишите метод, на вход которого подается двумерный строковый массив размером 4х4,
// при подаче массива другого размера необходимо бросить исключение MyArraySizeException.
//2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать.
// Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа),
// должно быть брошено исключение MyArrayDataException – с детализацией, в какой именно ячейке лежат неверные данные.
//3. В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException и MyArrayDataException
// и вывести результат расчета.
public class Main {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    public static void test1() {
        String[][] str = {
                {"12", "14"},
                {"12", "14", "5432"},
                {"12", "14", "453579", "23", "3"},
                {"12", "14", "56"}
        };
        runTest(str);
    }

    public static void test2() {
        String[][] str = {
                {"12", "14", "23", "3"},
                {"12", "14", "5o", "-2"},
                {"12", "14", "4", "23"},
                {"12", "14", "56", "0"}
        };

        runTest(str);
    }


    public static void test3() {
        String[][] str = {
                {"1", "2", "1", "0"},
                {"1", "-2", "1", "0"},
                {"1", "2", "1", "10"},
                {"1", "2", "1", "0"},
        };
        runTest(str);
    }

    private static void runTest(String[][] str) {
        printArr(str);
        try {
            System.out.println("сумма = " + sum(str));
        } catch (MyArraySizeException ex) {
            System.out.println("неверный размер массива, ожидается 4x4 ");
        } catch (MyArrayDataException ex) {
            System.out.printf("нельзя преобразовать данные в ячейке [%d, %d]%n", ex.row, ex.column);
        }
    }

    public static void printArr(String[][] arr) {
        System.out.println(Arrays.deepToString(arr));
    }

    public static int sum(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        final int ARRAY_SIZE = 4;
        if (!checkArrayLenght(arr, ARRAY_SIZE)) {
            throw new MyArraySizeException();
        }
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    int c = Integer.parseInt(arr[i][j]);
                    total = total + c;
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return total;
    }

    public static boolean checkArrayLenght(String[][] arr, int size) {
        if (size != arr.length) {
            return false;
        }

        for (String[] row :
                arr) {
            if (size != row.length) {
                return false;
            }
        }
        return true;
    }
}
